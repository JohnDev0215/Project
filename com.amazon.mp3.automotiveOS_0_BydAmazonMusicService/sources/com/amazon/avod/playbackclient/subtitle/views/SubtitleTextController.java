package com.amazon.avod.playbackclient.subtitle.views;

import com.amazon.avod.playbackclient.subtitle.SubtitleEventReporter;
import com.amazon.avod.playbackclient.subtitle.internal.CompositeSubtitleElement;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SubtitleTextController {
    public abstract void applyLanguageCode(@Nonnull String str);

    public abstract void applyRenderPreset(@Nonnull SubtitleRenderPreset subtitleRenderPreset);

    public void hideSample() {
    }

    public abstract void hideSubtitle();

    public abstract void initialize(SubtitleEventReporter subtitleEventReporter);

    public abstract void reset();

    public abstract void setSubtitle(CompositeSubtitleElement compositeSubtitleElement);

    public abstract void showSubtitle();
}
