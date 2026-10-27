package org.chromium.components.media_router;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class MediaStatusBridge {
    @CalledByNative
    public boolean canMute() {
        return false;
    }

    @CalledByNative
    public boolean canPlayPause() {
        return false;
    }

    @CalledByNative
    public boolean canSeek() {
        return false;
    }

    @CalledByNative
    public boolean canSetVolume() {
        return false;
    }

    @CalledByNative
    public long currentTime() {
        return 0L;
    }

    @CalledByNative
    public long duration() {
        return 0L;
    }

    @CalledByNative
    public int idleReason() {
        return 0;
    }

    @CalledByNative
    public boolean isMuted() {
        return false;
    }

    @CalledByNative
    public int playerState() {
        return 0;
    }

    @CalledByNative
    public String title() {
        return "";
    }

    @CalledByNative
    public double volume() {
        return 0.0d;
    }
}
