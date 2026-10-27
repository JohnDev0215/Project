package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoChannelSeed {
    private final VideoShow activeShow;
    private final String channelId;
    private final List<VideoShow> shows;

    public interface ActiveShowStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ActiveShowStep, ShowsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface ShowsStep {
    }

    public String getChannelId() {
        return this.channelId;
    }

    public VideoShow getActiveShow() {
        return this.activeShow;
    }

    public List<VideoShow> getShows() {
        return this.shows;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || VideoChannelSeed.class != obj.getClass()) {
            return false;
        }
        VideoChannelSeed videoChannelSeed = (VideoChannelSeed) obj;
        return ObjectsCompat.equals(getChannelId(), videoChannelSeed.getChannelId()) && ObjectsCompat.equals(getActiveShow(), videoChannelSeed.getActiveShow()) && ObjectsCompat.equals(getShows(), videoChannelSeed.getShows());
    }

    public int hashCode() {
        return (getChannelId() + getActiveShow() + getShows()).hashCode();
    }
}
