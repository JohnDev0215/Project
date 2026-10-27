package com.amazon.avod.playback.player.actions;

import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LaunchAction extends Action {
    private final VideoRenderingSettings mDisplaySettings;
    private final DrmScheme mDrmScheme;
    private final boolean mIsDash;
    private final String mOfflineKeyId;
    private final RendererSchemeType mRendererSchemeType;

    public LaunchAction(@Nonnull VideoRenderingSettings videoRenderingSettings, @Nullable String str, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, boolean z) {
        this.mDisplaySettings = (VideoRenderingSettings) Preconditions.checkNotNull(videoRenderingSettings);
        this.mOfflineKeyId = str;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        this.mIsDash = z;
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.LaunchPlayback;
    }

    @Nonnull
    public VideoRenderingSettings getDisplaySettings() {
        return this.mDisplaySettings;
    }

    @Nullable
    public String getOfflineKeyId() {
        return this.mOfflineKeyId;
    }

    @Nonnull
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }

    @Nonnull
    public RendererSchemeType getRendererSchemeType() {
        return this.mRendererSchemeType;
    }

    public boolean isDash() {
        return this.mIsDash;
    }
}
