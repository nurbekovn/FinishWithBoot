package com.service;

import com.model.Lesson;

import java.util.List;

public interface LessonService {
    void saveLesson(Long courseId, Lesson lesson);

    void updateLesson(Long id,  Lesson lesson);

    void deleteLesson(Long id);

    List<Lesson> getLessons(Long id);

    Lesson getLessonById(Long id);
}
