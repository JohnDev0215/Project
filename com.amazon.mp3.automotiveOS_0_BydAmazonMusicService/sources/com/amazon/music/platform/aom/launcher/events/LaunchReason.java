package com.amazon.music.platform.aom.launcher.events;

import kotlin.Metadata;

/* JADX INFO: compiled from: LaunchReason.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/events/LaunchReason;", "", "launchReason", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLaunchReason", "()Ljava/lang/String;", "toString", "NOT_INSTALLED", "INCOMPAT_VERSION_INSTALLED", "SCREEN_UNLOCK_TIMED_OUT", "SINGLE_ELIGIBLE_TARGET", "HIGHEST_RANKED_TARGET_SELECTED", "UNKNOWN_ERROR", "NONE_INSTALLED", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum LaunchReason {
    NOT_INSTALLED("NOT_INSTALLED"),
    INCOMPAT_VERSION_INSTALLED("INCOMPAT_VERSION_INSTALLED"),
    SCREEN_UNLOCK_TIMED_OUT("SCREEN_UNLOCK_TIMED_OUT"),
    SINGLE_ELIGIBLE_TARGET("SINGLE_ELIGIBLE_TARGET"),
    HIGHEST_RANKED_TARGET_SELECTED("HIGHEST_RANKED_TARGET_SELECTED"),
    UNKNOWN_ERROR("UNKNOWN_ERROR"),
    NONE_INSTALLED("NONE_INSTALLED");

    private final String launchReason;

    LaunchReason(String str) {
        this.launchReason = str;
    }

    public final String getLaunchReason() {
        return this.launchReason;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.launchReason;
    }
}
