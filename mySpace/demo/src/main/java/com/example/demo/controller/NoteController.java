/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NoteService;

/**
 * @author Debu-PC_2
 *
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {
	@Autowired
	NoteService noteService;
	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping
	public List<Note> getAllNotes(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@Fetch all data@@@@@@@@@@@@@@@@@@@@@@");
		return noteService.findAllNotes();
	}
	
	@GetMapping("/single/{id}")
	public ResponseEntity<Note> getSingleNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.getOne(noteId);
	    if(note == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(note);
	}
	
	@PostMapping("/add")
	public Note createNote(@RequestBody Note note) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@Note data inserted@@@@@@@@@@@@@@@@@@@@@");
	    return noteService.createNote(note);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable("id") Long notId, @RequestBody Note noteDetail) {
		Note note = noteRepository.getOne(notId);
		if(note == null) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Note data is not available @@@@@@@@@@@@@@@@@@@@@");
		        return ResponseEntity.notFound().build();
		    }
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Note data updated @@@@@@@@@@@@@@@@@@@@@");
	    return ResponseEntity.ok(noteService.updateNote(note, noteDetail));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.getOne(noteId);
	    if(note == null) {
	        return ResponseEntity.notFound().build();
	    }
	    System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Note data deleted @@@@@@@@@@@@@@@@@@@@@");
	    noteService.deleteNote(note);
	    return ResponseEntity.ok().build();
	}
}
