package com.amazon.music.platform.aom.launcher.events;

import kotlin.Metadata;

/* JADX INFO: compiled from: LaunchOutcome.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/events/LaunchOutcome;", "", "outcome", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getOutcome", "()Ljava/lang/String;", "toString", "TARGET_LAUNCHED", "PLATFORM_DETERMINED_TARGET_LAUNCHED", "TARGET_NOT_LAUNCHED", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum LaunchOutcome {
    TARGET_LAUNCHED("TARGET_LAUNCHED"),
    PLATFORM_DETERMINED_TARGET_LAUNCHED("PLATFORM_DETERMINED_TARGET_LAUNCHED"),
    TARGET_NOT_LAUNCHED("TARGET_NOT_LAUNCHED");

    private final String outcome;

    LaunchOutcome(String str) {
        this.outcome = str;
    }

    public final String getOutcome() {
        return this.outcome;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.outcome;
    }
}
