package org.example.hackathon.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hackathon.entity.StatusEnum;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private StatusEnum status;
}
