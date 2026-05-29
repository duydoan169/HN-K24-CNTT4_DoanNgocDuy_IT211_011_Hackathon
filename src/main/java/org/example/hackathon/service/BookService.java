package org.example.hackathon.service;

import lombok.RequiredArgsConstructor;
import org.example.hackathon.dto.BookRequest;
import org.example.hackathon.dto.BookResponse;
import org.example.hackathon.dto.PatchBookRequest;
import org.example.hackathon.entity.Book;
import org.example.hackathon.exception.BookNotFoundException;
import org.example.hackathon.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Page<BookResponse> getAllBooks(String search, Pageable pageable){
        return bookRepository.findBookByAuthorContainingAndTitleContaining(search, pageable).map(this::entityToResponse);
    }

    public BookResponse createBook(BookRequest bookRequest){
        Book book = requestToEntity(bookRequest);
        return entityToResponse(bookRepository.save(book));
    }

    public BookResponse deleteBook(Long id){
        Book target = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Không tìm thấy id: " + id));
        target.setIsDeleted(true);
        return entityToResponse(bookRepository.save(target));
    }

    public BookResponse updateBook(Long id, BookRequest bookRequest){
        Book target = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Không tìm thấy id: " + id));

        target.setAuthor(bookRequest.getAuthor());
        target.setPrice(bookRequest.getPrice());
        target.setTitle(bookRequest.getTitle());
        target.setStatus(bookRequest.getStatus());

        return entityToResponse(bookRepository.save(target));
    }

    public BookResponse patchBook(Long id, PatchBookRequest bookRequest){
        Book target = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Không tìm thấy id: " + id));

        if (bookRequest.getAuthor() != null && !bookRequest.getAuthor().isBlank()){
            target.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getPrice() != null){
            target.setPrice(bookRequest.getPrice());
        }
        if (bookRequest.getTitle() != null && !bookRequest.getTitle().isBlank()){
            target.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getStatus() != null){
            target.setStatus(bookRequest.getStatus());
        }

        return entityToResponse(bookRepository.save(target));
    }

    private BookResponse entityToResponse(Book book){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setStatus(book.getStatus());
        return bookResponse;
    }

    private Book requestToEntity(BookRequest bookRequest){
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setStatus(bookRequest.getStatus());
        book.setPrice(bookRequest.getPrice());
        book.setIsDeleted(bookRequest.getIsDeleted() != null && bookRequest.getIsDeleted());
        return book;
    }
}
