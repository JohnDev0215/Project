package com.amazon.video.sdk;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlayerSdk.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/RendererDrmFlag;", "", "", "flag", "", "mediaCodecNoDrmOverride", "mediaCodecWidevine", "mediaCodecSoftwarePlayReady", "widevine", "<init>", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class RendererDrmFlag {
    public static final RendererDrmFlag INSTANCE = new RendererDrmFlag();

    public final boolean mediaCodecNoDrmOverride(int flag) {
        return (flag ^ 16) == 0;
    }

    public final boolean mediaCodecSoftwarePlayReady(int flag) {
        return (flag ^ 22) == 0;
    }

    public final boolean widevine(int flag) {
        return ((flag & 1) == 1) & ((flag & 2) != 2);
    }

    private RendererDrmFlag() {
    }

    public final boolean mediaCodecWidevine(int flag) {
        return ((flag & 16) == 16) & widevine(flag);
    }
}
