package com.courses.web.rest;

import com.courses.domain.Students;
import com.courses.repository.StudentsRepository;
import com.courses.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.courses.domain.Students}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StudentsResource {

    private final Logger log = LoggerFactory.getLogger(StudentsResource.class);

    private static final String ENTITY_NAME = "students";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StudentsRepository studentsRepository;

    public StudentsResource(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    /**
     * {@code POST  /students} : Create a new students.
     *
     * @param students the students to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new students, or with status {@code 400 (Bad Request)} if the students has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/students")
    public ResponseEntity<Students> createStudents(@RequestBody Students students) throws URISyntaxException {
        log.debug("REST request to save Students : {}", students);
        if (students.getId() != null) {
            throw new BadRequestAlertException("A new students cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Students result = studentsRepository.save(students);
        return ResponseEntity.created(new URI("/api/students/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /students} : Updates an existing students.
     *
     * @param students the students to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated students,
     * or with status {@code 400 (Bad Request)} if the students is not valid,
     * or with status {@code 500 (Internal Server Error)} if the students couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/students")
    public ResponseEntity<Students> updateStudents(@RequestBody Students students) throws URISyntaxException {
        log.debug("REST request to update Students : {}", students);
        if (students.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Students result = studentsRepository.save(students);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, students.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /students} : get all the students.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of students in body.
     */
    @GetMapping("/students")
    public List<Students> getAllStudents() {
        log.debug("REST request to get all Students");
        return studentsRepository.findAll();
    }

    /**
     * {@code GET  /students/:id} : get the "id" students.
     *
     * @param id the id of the students to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the students, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<Students> getStudents(@PathVariable Long id) {
        log.debug("REST request to get Students : {}", id);
        Optional<Students> students = studentsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(students);
    }

    /**
     * {@code DELETE  /students/:id} : delete the "id" students.
     *
     * @param id the id of the students to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudents(@PathVariable Long id) {
        log.debug("REST request to delete Students : {}", id);
        studentsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
