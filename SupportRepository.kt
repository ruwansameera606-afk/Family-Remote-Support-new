package com.family.remotesupport.data.repository

import com.family.remotesupport.data.model.FamilyMember
import com.family.remotesupport.data.model.SupportHistoryRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupportRepository {

    fun getSampleHistory(): List<SupportHistoryRecord> {
        return listOf(
            SupportHistoryRecord(
                id = "hist-1",
                familyMemberName = "Kasun (Son)",
                relationship = "Son",
                date = "15 Aug 2026",
                durationMinutes = 14,
                status = "Completed",
                troubleshootingSummary = "Reconnected home Wi-Fi and adjusted system font size.",
                category = "Wi-Fi / Network"
            ),
            SupportHistoryRecord(
                id = "hist-2",
                familyMemberName = "Nishi (Daughter)",
                relationship = "Daughter",
                date = "12 Aug 2026",
                durationMinutes = 8,
                status = "Completed",
                troubleshootingSummary = "Unmuted notification volume and configured ringtone alert.",
                category = "Sound / Volume"
            )
        )
    }
}
