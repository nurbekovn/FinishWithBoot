package com.serviceImp;

import com.model.Lesson;
import com.repository.CourseRepository;
import com.repository.LessonRepository;
import com.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        lesson.setCourse(courseRepository.findById(courseId).get());
        lessonRepository.save(lesson);
    }

    @Override
    public void updateLesson(Long id,Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found lesson"));
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.delete(lessonRepository.findById(id).get());
    }

    @Override
    public List<Lesson> getLessons(Long id) {
        return lessonRepository.getLessonByCourseId(id);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }
}
