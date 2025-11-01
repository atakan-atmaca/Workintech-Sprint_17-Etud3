package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos = new HashMap<>();

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable Integer id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroo == null) throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        return kangaroo;
    }

    @PostMapping
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        if (!kangaroos.containsKey(id)) throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public void deleteKangaroo(@PathVariable Integer id) {
        if (!kangaroos.containsKey(id)) throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        kangaroos.remove(id);
    }
}
