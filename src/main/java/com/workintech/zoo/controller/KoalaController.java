package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas = new HashMap<>();

    @GetMapping
    public List<Koala> getAllKoalas() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable Integer id) {
        Koala koala = koalas.get(id);
        if (koala == null) throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        return koala;
    }

    @PostMapping
    public Koala createKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable Integer id) {
        if (!koalas.containsKey(id)) throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        koalas.remove(id);
    }
}
