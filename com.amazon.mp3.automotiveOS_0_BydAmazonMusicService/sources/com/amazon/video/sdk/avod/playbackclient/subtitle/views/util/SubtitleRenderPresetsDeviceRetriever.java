package com.amazon.video.sdk.avod.playbackclient.subtitle.views.util;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPresets;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleDefaults;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleRenderPresetsDeviceRetriever {
    public static final SubtitleRenderPresetsDeviceRetriever DEFAULT_RETRIEVER = new SubtitleRenderPresetsDeviceRetriever();
    protected static final Drawable ICON = null;
    private static final Typeface DEFAULT_TYPEFACE = Typeface.DEFAULT;

    @Nonnull
    public SubtitleRenderPresets getDefaultPresets() {
        return fromSinglePreset(new SubtitleRenderPreset("Default Device", ICON, DEFAULT_TYPEFACE, TransformationMethods.NO_OP, EdgeRenderer.EdgeType.NONE, 1.0f, SubtitleDefaults.TEXT_ARGB_COLOR, SubtitleDefaults.BACKGROUND_ARGB_COLOR, SubtitleDefaults.WINDOW_ARGB_COLOR));
    }

    @Nonnull
    protected static SubtitleRenderPresets fromSinglePreset(@Nonnull SubtitleRenderPreset subtitleRenderPreset) {
        return new SubtitleRenderPresets(SubtitlePresetSource.DEVICE, ImmutableList.of(subtitleRenderPreset));
    }
}
