package org.example.hackathon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hackathon.dto.BookRequest;
import org.example.hackathon.dto.BookResponse;
import org.example.hackathon.dto.PatchBookRequest;
import org.example.hackathon.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(@PageableDefault(size = 3, page = 0, direction = Sort.Direction.ASC)Pageable pageable, @RequestParam(defaultValue = "") String search){
        return new ResponseEntity<>(bookService.getAllBooks(search, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest){
        return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookRequest bookRequest, @PathVariable Long id){
        return new ResponseEntity<>(bookService.updateBook(id, bookRequest), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> patchBook(@RequestBody PatchBookRequest bookRequest, @PathVariable Long id){
        return new ResponseEntity<>(bookService.patchBook(id, bookRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
