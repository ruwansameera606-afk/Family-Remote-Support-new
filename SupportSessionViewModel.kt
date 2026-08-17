package com.family.remotesupport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.family.remotesupport.data.model.SupportSession
import com.family.remotesupport.data.model.SupportHistoryRecord
import com.family.remotesupport.data.repository.SupportRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.SecureRandom

sealed class SessionUiState {
    object Idle : SessionUiState()
    data class WaitingForPeer(val sessionCode: String, val remainingSeconds: Int) : SessionUiState()
    data class RequestReceived(val requesterName: String, val deviceModel: String) : SessionUiState()
    data class ConsentRequired(val peerName: String) : SessionUiState()
    data class Active(val session: SupportSession, val elapsedSeconds: Int) : SessionUiState()
    data class Ended(val summary: String) : SessionUiState()
    data class Error(val message: String) : SessionUiState()
}

class SupportSessionViewModel(
    private val repository: SupportRepository = SupportRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<SessionUiState>(SessionUiState.Idle)
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    private val _history = MutableStateFlow<List<SupportHistoryRecord>>(emptyList())
    val history: StateFlow<List<SupportHistoryRecord>> = _history.asStateFlow()

    private var sessionTimerJob: Job? = null

    init {
        loadHistory()
    }

    /**
     * Generates a cryptographically secure 6-digit session code (e.g. 482 731).
     */
    fun createSupportSession(requesterName: String = "Amma (Mother)") {
        val secureRandom = SecureRandom()
        val num1 = 100 + secureRandom.nextInt(900)
        val num2 = 100 + secureRandom.nextInt(900)
        val code = "$num1 $num2"

        _uiState.value = SessionUiState.WaitingForPeer(code, remainingSeconds = 600)
    }

    /**
     * Validates entered 6-digit code and checks requester info.
     */
    fun validateAndJoin(code: String) {
        val cleanCode = code.replace(" ", "")
        if (cleanCode.length != 6) {
            _uiState.value = SessionUiState.Error("Please enter a valid 6-digit code.")
            return
        }

        _uiState.value = SessionUiState.RequestReceived(
            requesterName = "Amma (Mother)",
            deviceModel = "Samsung Galaxy A54 5G"
        )
    }

    /**
     * Shows the explicit consent permission dialog.
     */
    fun proceedToConsent() {
        _uiState.value = SessionUiState.ConsentRequired("Amma (Mother)")
    }

    /**
     * Explicit User Consent granted. Starts active remote support session.
     */
    fun allowSupportAndStart() {
        val session = SupportSession(
            id = "sess-" + System.currentTimeMillis(),
            code = "482 731",
            requesterName = "Amma (Mother)",
            requesterDevice = "Samsung Galaxy A54 5G",
            helperName = "Kasun (Son)",
            startedAt = System.currentTimeMillis()
        )
        _uiState.value = SessionUiState.Active(session, elapsedSeconds = 0)
        startElapsedTimer(session)
    }

    private fun startElapsedTimer(session: SupportSession) {
        sessionTimerJob?.cancel()
        sessionTimerJob = viewModelScope.launch {
            var elapsed = 0
            while (true) {
                delay(1000)
                elapsed++
                _uiState.value = SessionUiState.Active(session, elapsed)
            }
        }
    }

    /**
     * Terminates the session immediately with user confirmation.
     */
    fun endSession() {
        sessionTimerJob?.cancel()
        _uiState.value = SessionUiState.Ended("Support session ended safely.")
    }

    fun resetToIdle() {
        _uiState.value = SessionUiState.Idle
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _history.value = repository.getSampleHistory()
        }
    }

    fun clearHistory() {
        _history.value = emptyList()
    }
}
