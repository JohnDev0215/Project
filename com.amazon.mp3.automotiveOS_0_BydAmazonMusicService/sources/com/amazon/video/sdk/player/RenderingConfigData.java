package com.amazon.video.sdk.player;

import android.view.Surface;
import android.view.ViewGroup;
import com.google.common.base.Objects;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RenderingConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B=\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0004¢\u0006\u0004\b&\u0010'J\u0013\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u0004\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0013¨\u0006("}, d2 = {"Lcom/amazon/video/sdk/player/RenderingConfigData;", "Lcom/amazon/video/sdk/player/RenderingConfig;", "", "other", "", "equals", "", "hashCode", "", "toString", "Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "surfaceHandlingMode", "Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "getSurfaceHandlingMode", "()Lcom/amazon/video/sdk/player/SurfaceHandlingMode;", "Landroid/view/ViewGroup;", "originalParentView", "Landroid/view/ViewGroup;", "getOriginalParentView", "()Landroid/view/ViewGroup;", "Landroid/view/Surface;", "playbackSurface", "Landroid/view/Surface;", "getPlaybackSurface", "()Landroid/view/Surface;", "systemUiFlags", "I", "getSystemUiFlags", "()I", "enableVideoTrackRecreation", "Z", "getEnableVideoTrackRecreation", "()Z", "Ljava/lang/ref/WeakReference;", "parentViewWeakReference", "Ljava/lang/ref/WeakReference;", "getParentView", "parentView", "<init>", "(Lcom/amazon/video/sdk/player/SurfaceHandlingMode;Landroid/view/ViewGroup;Landroid/view/Surface;IZ)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class RenderingConfigData implements RenderingConfig {
    private final boolean enableVideoTrackRecreation;
    private final ViewGroup originalParentView;
    private final WeakReference<ViewGroup> parentViewWeakReference;
    private final Surface playbackSurface;
    private final SurfaceHandlingMode surfaceHandlingMode;
    private final int systemUiFlags;

    @JvmOverloads
    public RenderingConfigData(SurfaceHandlingMode surfaceHandlingMode, ViewGroup viewGroup, Surface surface, int i, boolean z) {
        Intrinsics.checkNotNullParameter(surfaceHandlingMode, "surfaceHandlingMode");
        this.surfaceHandlingMode = surfaceHandlingMode;
        this.originalParentView = viewGroup;
        this.playbackSurface = surface;
        this.systemUiFlags = i;
        this.enableVideoTrackRecreation = z;
        this.parentViewWeakReference = new WeakReference<>(viewGroup);
    }

    public /* synthetic */ RenderingConfigData(SurfaceHandlingMode surfaceHandlingMode, ViewGroup viewGroup, Surface surface, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(surfaceHandlingMode, (i2 & 2) != 0 ? null : viewGroup, (i2 & 4) != 0 ? null : surface, (i2 & 8) != 0 ? -1 : i, (i2 & 16) != 0 ? false : z);
    }

    @Override // com.amazon.video.sdk.player.RenderingConfig
    public SurfaceHandlingMode getSurfaceHandlingMode() {
        return this.surfaceHandlingMode;
    }

    @Override // com.amazon.video.sdk.player.RenderingConfig
    public Surface getPlaybackSurface() {
        return this.playbackSurface;
    }

    @Override // com.amazon.video.sdk.player.RenderingConfig
    public int getSystemUiFlags() {
        return this.systemUiFlags;
    }

    @Override // com.amazon.video.sdk.player.RenderingConfig
    public boolean getEnableVideoTrackRecreation() {
        return this.enableVideoTrackRecreation;
    }

    @Override // com.amazon.video.sdk.player.RenderingConfig
    public ViewGroup getParentView() {
        return this.parentViewWeakReference.get();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(RenderingConfigData.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            RenderingConfigData renderingConfigData = (RenderingConfigData) other;
            return getSurfaceHandlingMode() == renderingConfigData.getSurfaceHandlingMode() && Intrinsics.areEqual(getParentView(), renderingConfigData.getParentView()) && Intrinsics.areEqual(getPlaybackSurface(), renderingConfigData.getPlaybackSurface()) && getSystemUiFlags() == renderingConfigData.getSystemUiFlags() && getEnableVideoTrackRecreation() == renderingConfigData.getEnableVideoTrackRecreation();
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.video.sdk.player.RenderingConfigData");
    }

    public int hashCode() {
        return Objects.hashCode(getSurfaceHandlingMode(), getParentView(), getPlaybackSurface(), Integer.valueOf(getSystemUiFlags()), Boolean.valueOf(getEnableVideoTrackRecreation()));
    }

    public String toString() {
        return "RenderingConfigData(surfaceHandlingMode=" + getSurfaceHandlingMode() + ", parentView=" + getParentView() + ", playbackSurface=" + getPlaybackSurface() + ", systemUiFlags=" + getSystemUiFlags() + ", enableVideoTrackRecreation=" + getEnableVideoTrackRecreation() + ')';
    }
}
