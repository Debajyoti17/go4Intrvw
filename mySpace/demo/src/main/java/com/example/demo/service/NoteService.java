package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Note;

public interface NoteService {
	List<Note> findAllNotes();

	Note createNote(Note note);

	Note updateNote(Note noteProxy, Note note);

	void deleteNote(Note note);
}
