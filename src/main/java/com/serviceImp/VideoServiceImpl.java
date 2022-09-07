package com.serviceImp;

import com.model.Video;
import com.repository.LessonRepository;
import com.repository.VideoRepository;
import com.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    private final LessonRepository lessonRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository,
                            LessonRepository lessonRepository) {
        this.videoRepository = videoRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveVideo(Video newVideo, Long lessonId) {
        newVideo.setLesson(lessonRepository.findById(lessonId).get());
        videoRepository.save(newVideo);
    }

    @Override
    public void updateVideo(Long id, Video newVideo) {
        Video video = videoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found"));
        video.setVideoName(newVideo.getVideoName());
        video.setLink(newVideo.getLink());
        videoRepository.save(video);
    }

    @Override
    public void deleteVideoById(Long id) {
        Video video = videoRepository.findById(id).get();
        video.setLesson(null);
        videoRepository.delete(video);
    }

    @Override
    public List<Video> getVideos(Long id) {
        return videoRepository.getVideoByLessonId(id);
    }

    @Override
    public Video getVideoById(Long id) {
        return videoRepository.getById(id);
    }
}
