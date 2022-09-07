package com.serviceImp;

import com.model.Task;
import com.repository.LessonRepository;
import com.repository.TaskRepository;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
        this.lessonRepository = lessonRepository;
    }


    public void saveTask(Task task, Long id) {
        task.setLesson(lessonRepository.getById(id));
//        Lesson lesson = lessonRepository.findById(id).get();
//        lesson.addTask(task);

        taskRepository.save(task);
    }


    public void updateTask(Long id, Task task) {
        Task task1 = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found task ")
        );
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        taskRepository.save(task1);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).get();
        task.setLesson(null);
        taskRepository.delete(task);
//        taskRepository.delete(taskRepository.findById(id).get());

    }


    public List<Task> getTasks(Long id) {
        return taskRepository.getTaskByLessonId(id);
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }
}
