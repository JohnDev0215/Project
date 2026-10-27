package com.amazon.video.sdk.player;

import android.view.Surface;
import android.view.ViewGroup;
import kotlin.Metadata;

/* JADX INFO: compiled from: RenderingConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\r\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0011\u001a\u00020\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/player/RenderingConfig;", "", "Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "getSurfaceHandlingMode", "()Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "surfaceHandlingMode", "Landroid/view/ViewGroup;", "getParentView", "()Landroid/view/ViewGroup;", "parentView", "Landroid/view/Surface;", "getPlaybackSurface", "()Landroid/view/Surface;", "playbackSurface", "", "getSystemUiFlags", "()I", "systemUiFlags", "", "getEnableVideoTrackRecreation", "()Z", "enableVideoTrackRecreation", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface RenderingConfig {
    boolean getEnableVideoTrackRecreation();

    ViewGroup getParentView();

    Surface getPlaybackSurface();

    SurfaceHandlingMode getSurfaceHandlingMode();

    int getSystemUiFlags();
}
