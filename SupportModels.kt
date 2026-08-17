package com.family.remotesupport.data.model

data class FamilyMember(
    val id: String,
    val name: String,
    val relationship: String,
    val phoneNumber: String,
    val deviceModel: String,
    val isOnline: Boolean,
    val isTrusted: Boolean,
    val lastSessionDate: String? = null
)

data class SupportSession(
    val id: String,
    val code: String,
    val requesterName: String,
    val requesterDevice: String,
    val helperName: String? = null,
    val startedAt: Long = System.currentTimeMillis(),
    val connectionQuality: String = "Excellent",
    val latencyMs: Int = 32
)

data class SupportHistoryRecord(
    val id: String,
    val familyMemberName: String,
    val relationship: String,
    val date: String,
    val durationMinutes: Int,
    val status: String,
    val troubleshootingSummary: String,
    val category: String
)
