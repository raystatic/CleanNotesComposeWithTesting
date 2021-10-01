package com.raystatic.cleannotes.feature_note.domain.use_case

import com.raystatic.cleannotes.feature_note.domain.model.InvalidNoteException
import com.raystatic.cleannotes.feature_note.domain.model.Note
import com.raystatic.cleannotes.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("Title of note cannot be empty")
        }

        if (note.content.isBlank()){
            throw InvalidNoteException("Content of note cannot be empty")
        }
        repository.insertNote(note = note)
    }

}