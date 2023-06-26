package com.example.estok_app.api_estok_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estok_app.api_estok_app.json.ImageResponse;
import com.example.estok_app.api_estok_app.json.Response;
import com.example.estok_app.api_estok_app.models.ImageModel;
import com.example.estok_app.api_estok_app.repository.ImageRepository;

@RestController
@RequestMapping("/images")
public class ImageController {
    
  @Autowired
    private ImageRepository imageRepository;



    @GetMapping("/{imageName}")
    public ResponseEntity<?> pegarImage(@PathVariable("imageName") String imageName) {
        ImageModel image = imageRepository.findByFileName(imageName);

        if (image == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(404, "Imagem não encontrada"));
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getMimeType()))
                .body(image.getBase64());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestBody ImageModel image) {
        ImageModel savedImage = imageRepository.save(image);
      
        String imageUrl = "http://localhost:8080/images/" + image.getFileName();
        savedImage.setUrlImage(imageUrl);
        imageRepository.save(savedImage);

        return ResponseEntity.ok()
                .body(new ImageResponse(200, "Imagem adicionada com sucesso", savedImage.getUrlImage()));
    }
}

