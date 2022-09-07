package com.service;

import com.model.Video;

import java.util.List;

public interface VideoService {
    void saveVideo(Video newVideo,Long lessonId);
    void updateVideo(Long id ,Video newVideo);

    void deleteVideoById(Long id);

    List<Video> getVideos(Long id);

    Video getVideoById(Long id);
}
