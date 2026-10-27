package com.neusoft.optimus.utility.file;

/* JADX INFO: loaded from: classes2.dex */
public class FileFilterBuilder {
    public static FileFilter buildAudioFileFilter() {
        return new AudioFileFilter(null);
    }

    public static FileFilter buildVideoFileFilter() {
        return new VideoFileFilter(null);
    }

    public static FileFilter buildImageFileFilter() {
        return new ImageFileFilter(null);
    }

    public static FileFilter buildNoneFilter() {
        return new NoneFilter();
    }

    public static FileFilter buildMediaFileFilter() {
        FileFilter fileFilterBuildAudioFileFilter = buildAudioFileFilter();
        FileFilter fileFilterBuildVideoFileFilter = buildVideoFileFilter();
        FileFilter fileFilterBuildImageFileFilter = buildImageFileFilter();
        fileFilterBuildImageFileFilter.attachNextFilter(fileFilterBuildAudioFileFilter);
        fileFilterBuildAudioFileFilter.attachNextFilter(fileFilterBuildVideoFileFilter);
        return fileFilterBuildImageFileFilter;
    }
}
