package com.example.learnjpaandhibernate.course.jdbc;

import com.example.learnjpaandhibernate.course.Course;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    private final JdbcTemplate springJdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate springJdbcTemplate) {
        this.springJdbcTemplate = springJdbcTemplate;
    }

    private static final String INSERT_QUERY =
            """
                INSERT INTO COURSE (id, name, author) 
                VALUES (?, ?, ?);  
            """;

    private static final String DELETE_QUERY =
            """
                DELETE FROM COURSE WHERE id = ?;
            """;

    private static final String SELECT_QUERY =
            """
                SELECT * FROM COURSE WHERE id = ?;
            """;

    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void delete(int i) {
        springJdbcTemplate.update(DELETE_QUERY, i);
    }

    public Course findById(long id) {
        return springJdbcTemplate.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class), id);
    }
}
