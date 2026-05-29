package org.example.hackathon.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.hackathon.entity.StatusEnum;

@Data
public class BookRequest {
    @NotBlank(message = "Title không được để trống")
    private String title;
    @NotBlank(message = "Author không được để trống")
    private String author;
    @NotNull(message = "Price không được để trống")
    @Min(value = 1, message = "Price không được nhỏ hơn 0")
    private Double price;
    private StatusEnum status = StatusEnum.AVAILABLE;
    private Boolean isDeleted = false;
}
