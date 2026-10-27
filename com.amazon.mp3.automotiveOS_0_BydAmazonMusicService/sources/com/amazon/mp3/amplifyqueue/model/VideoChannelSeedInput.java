package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoChannelSeedInput {
    private final VideoShowInput activeShow;
    private final String channelId;
    private final List<VideoShowInput> shows;

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

    public VideoShowInput getActiveShow() {
        return this.activeShow;
    }

    public List<VideoShowInput> getShows() {
        return this.shows;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || VideoChannelSeedInput.class != obj.getClass()) {
            return false;
        }
        VideoChannelSeedInput videoChannelSeedInput = (VideoChannelSeedInput) obj;
        return ObjectsCompat.equals(getChannelId(), videoChannelSeedInput.getChannelId()) && ObjectsCompat.equals(getActiveShow(), videoChannelSeedInput.getActiveShow()) && ObjectsCompat.equals(getShows(), videoChannelSeedInput.getShows());
    }

    public int hashCode() {
        return (getChannelId() + getActiveShow() + getShows()).hashCode();
    }
}
