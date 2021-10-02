package com.raystatic.cleannotes.feature_note.domain.use_case

import com.google.common.truth.Truth.*

import com.raystatic.cleannotes.feature_note.data.repository.FakeNoteRepository
import com.raystatic.cleannotes.feature_note.domain.model.InvalidNoteException
import com.raystatic.cleannotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest{

    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup(){
        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Add note with empty title throws Invalid Note Exception`(){
        val note = Note(
            title = "",
            content = "abc",
            timestamp = 100L,
            color = 2
        )

        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(note)
            }
        }

        assertEquals("Title of note cannot be empty",exception.message)

    }

    @Test
    fun `Add note with empty content throws Invalid Note Exception`(){
        val note = Note(
            title = "abc",
            content = "",
            timestamp = 100L,
            color = 2
        )

        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNoteUseCase(note)
            }
        }

        assertEquals("Content of note cannot be empty",exception.message)

    }

    @Test
    fun `Add note with valid title and content adds note successfully`(){
        val note = Note(
            title = "test",
            content = "abc",
            timestamp = 100L,
            color = 2
        )

        runBlocking {
            addNoteUseCase(note)
            assertThat(fakeNoteRepository.getNotes().first().contains(note))
        }

    }

}