package com.example.demo.Files;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileController {

  @PostMapping("/upload")
  public void uploadFile(@RequestParam("file") MultipartFile file) {
    // Handle the file upload logic here
    if (!file.isEmpty()) {
      // Process the file, save it, or perform any required operations
      // You can access the file using file.getInputStream()
      System.out.println("File uploaded successfully");
    }{
      System.out.println("Please select a file!");
    }
  }
}
