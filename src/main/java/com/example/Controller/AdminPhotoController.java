package com.example.Controller;

import com.example.demo.Photo;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminPhotoController {
    @Autowired
    PhotoService photoService;

    public AdminPhotoController() {


    }

    @RequestMapping("/admin/api/photos")
    public Iterable<Photo> getAll() {
        return photoService.getAll();
    }

    @RequestMapping("/admin/api/photos/{id}")
    public Photo getById(@PathVariable int id) {
        Optional<Photo> photo = photoService.getById(id);

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");

        }
        return photo.get();
    }

    @RequestMapping(value = "/admin/api/photos", method = RequestMethod.POST)
    public Photo create(@RequestBody Photo photo) {

        photoService.create(photo);
        return photo;
    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.PUT)
    public Photo update(@PathVariable int id, @RequestBody Photo photo) {

        Optional<Photo> updatePhoto = photoService.update(id,photo);

        if (updatePhoto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");

        }
        return updatePhoto.get();

    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {


        if (!photoService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");

        }

    }
}

