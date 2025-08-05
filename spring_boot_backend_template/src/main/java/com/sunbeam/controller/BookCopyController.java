package com.sunbeam.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/copies")
@Getter
@Setter
@AllArgsConstructor
public class BookCopyController {
    @Autowired private BookCopyService bookCopyService;

    @PostMapping("/{bookId}")
    public ResponseEntity<BookCopyDTO> addCopy(@PathVariable Long bookId, @RequestBody BookCopyDTO dto) {
        return ResponseEntity.ok(bookCopyService.addCopy(bookId, dto));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BookCopyDTO>> getCopiesByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookCopyService.getCopiesByBook(bookId));
    }

    @PutMapping("/{copyId}")
    public ResponseEntity<BookCopyDTO> updateCopy(@PathVariable Long copyId, @RequestBody BookCopyDTO dto) {
        return ResponseEntity.ok(bookCopyService.updateCopy(copyId, dto));
    }

    @DeleteMapping("/{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) {
        bookCopyService.deleteCopy(copyId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{copyId}/status")
    public ResponseEntity<BookCopyDTO> changeStatus(@PathVariable Long copyId, @RequestParam String status) {
        return ResponseEntity.ok(bookCopyService.changeStatus(copyId, status));
    }
}
