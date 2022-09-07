package com.api;

import com.model.Video;
import com.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @GetMapping("/videos/{id}")
    private String getAllVideos(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("videos", videoService.getVideos(id));
        return "video/videos";
    }

    @GetMapping("/{id}/addVideo")
    private String addVideo(@PathVariable Long id, Model model) {
        model.addAttribute("id", videoService.getVideoById(id));
        model.addAttribute("video", new Video());
        return "video/addVideo";
    }

    @PostMapping("/{id}/saveVideo")
    private String saveVideo(@PathVariable Long id,
                             @ModelAttribute ("video") Video video) {
        videoService.saveVideo(video,id);
        return "redirect:/videos/videos/ " +id;
    }

    @GetMapping("/edit/{videoId}")
    private String updateVideo(@PathVariable("videoId") Long id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video",video);
        model.addAttribute("lessonIdd",video.getLesson().getId());
        return "video/updateVideo";
    }

    @PostMapping("{id}/{lessonId}/update")
    private String saveUpdateVideo(@PathVariable ("id") Long id,
                                   @PathVariable ("lessonId") Long lessonId,
                                   @ModelAttribute("video") Video video) {
        videoService.updateVideo(id,video);
        return "redirect:/videos/videos/ " + lessonId;
    }


    @PostMapping("/{lessonId}/{videoId}/deleteVideo")
    private String deleteVideo(@PathVariable Long lessonId ,
                               @PathVariable Long videoId) {
        videoService.deleteVideoById(videoId);
        return "redirect:/videos/videos/ " + lessonId;
    }
}
