package com.amazon.mp3.starlight.provider;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: VideoPlayerStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\rJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "", "", "toString", "", "hashCode", "other", "", "equals", "isCaptionsEnabled", "Z", "()Z", "setCaptionsEnabled", "(Z)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class VideoPlayerStateProvider {
    private boolean isCaptionsEnabled;

    public VideoPlayerStateProvider() {
        this(false, 1, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VideoPlayerStateProvider) && this.isCaptionsEnabled == ((VideoPlayerStateProvider) other).isCaptionsEnabled;
    }

    public int hashCode() {
        boolean z = this.isCaptionsEnabled;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "VideoPlayerStateProvider(isCaptionsEnabled=" + this.isCaptionsEnabled + ')';
    }

    public VideoPlayerStateProvider(boolean z) {
        this.isCaptionsEnabled = z;
    }

    public /* synthetic */ VideoPlayerStateProvider(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    /* JADX INFO: renamed from: isCaptionsEnabled, reason: from getter */
    public final boolean getIsCaptionsEnabled() {
        return this.isCaptionsEnabled;
    }

    public final void setCaptionsEnabled(boolean z) {
        this.isCaptionsEnabled = z;
    }
}
