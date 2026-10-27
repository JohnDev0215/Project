package com.amazon.avod.playbackclient.subtitle.views.util;

import com.amazon.avod.playbackclient.subtitle.SubtitlePresetSource;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleRenderPresets {
    private final ImmutableList<SubtitleRenderPreset> mList;
    private final SubtitlePresetSource mPresetSource;

    public SubtitleRenderPresets(@Nonnull SubtitlePresetSource subtitlePresetSource, @Nonnull ImmutableList<SubtitleRenderPreset> immutableList) {
        this.mPresetSource = (SubtitlePresetSource) Preconditions.checkNotNull(subtitlePresetSource);
        this.mList = (ImmutableList) Preconditions.checkNotNull(immutableList);
    }

    @Nonnull
    public SubtitlePresetSource getPresetSource() {
        return this.mPresetSource;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass().getSimpleName()).add(PageUriUtils.SOURCE_QUERY_PARAM, this.mPresetSource).add("numPresets", this.mList.size()).toString();
    }
}
