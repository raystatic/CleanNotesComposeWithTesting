package com.raystatic.cleannotes.feature_note.presentation.notes

import com.raystatic.cleannotes.feature_note.domain.model.Note
import com.raystatic.cleannotes.feature_note.domain.util.NoteOrder
import com.raystatic.cleannotes.feature_note.domain.util.OrderType

data class NoteState(
    val notes:List<Note> = emptyList(),
    val notesOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
