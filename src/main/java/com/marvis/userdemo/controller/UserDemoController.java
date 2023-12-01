package com.marvis.userdemo.controller;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class UserDemoController {


    private static final String MESSAGES = "src/main/resources/messages.txt";
    private static final String LOG = "src/main/resources/log.txt";


    @GetMapping("/all")
    public List<String> getMessages() throws IOException {
        assert MESSAGES.isEmpty();
        return Files.readAllLines(getFilePath(MESSAGES));
    }


    @GetMapping("/count")
    public int getMessageCount() throws IOException {
        return Files.readAllLines(getFilePath(LOG)).size();
    }

    @PostMapping("/save")
    public void postMessage(@RequestBody String message) throws IOException {
        Files.write(getFilePath(MESSAGES), message.concat("\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
        logActivity();
    }

    @GetMapping("/log")
    public List<String> getLog() throws IOException {
        return Files.readAllLines(getFilePath(LOG));
    }



    private void logActivity() throws IOException {
        Files.write(getFilePath(LOG), "New message created".concat("\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
    }


    // This method converts a file string to a path
    private Path getFilePath(String fileName) {
        return Paths.get(fileName);
    }
}