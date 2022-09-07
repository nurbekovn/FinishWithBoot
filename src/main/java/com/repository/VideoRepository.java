package com.repository;

import com.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("select v from Video v where v.lesson.id =:lessonId")
    List<Video> getVideoByLessonId(@Param("lessonId") Long lessonId);

//    void saveVideo(Video newVideo,Long lessonId);
//    void updateVideo(Long id,Video newVideo);
//
//    void deleteVideoById(Long id);
//
//    List<Video> getVideos(Long id);
//
//    Video getVideoById(Long id);

}
