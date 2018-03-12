package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	NoteRepository noteRepository;

	@Override
	public List<Note> findAllNotes() {
		// TODO Auto-generated method stub
		return noteRepository.findAllNoteByStatus("yes");
	}

	@Override
	public void deleteNote(Note note) {
		note.setStatus("no");
		noteRepository.save(note);
	}

	@Override
	public Note createNote(Note note) {
		note.setStatus("yes");
		return noteRepository.save(note);
	}

	@Override
	public Note updateNote(Note noteProxy, Note note) {
		noteProxy.setContent(note.getContent());
		noteProxy.setTitle(note.getTitle());
		noteProxy.setStatus("yes");
		noteProxy.setUpdatedAt(new Timestamp(new Date().getTime()));
		return noteRepository.save(noteProxy);	
	}

}
