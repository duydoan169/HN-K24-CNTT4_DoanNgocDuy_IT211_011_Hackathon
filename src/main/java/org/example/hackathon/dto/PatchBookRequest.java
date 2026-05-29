package org.example.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.hackathon.entity.StatusEnum;

@Data
public class PatchBookRequest {
    private String title;
    private String author;
    private Double price;
    private StatusEnum status = StatusEnum.AVAILABLE;
    private Boolean isDeleted = false;
}
