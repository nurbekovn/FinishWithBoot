package com.repository;

import com.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long > {
    List<Lesson> getLessonByCourseId(Long courseId);


}
