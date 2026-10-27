package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioStreamAndQualitySelector {
    @Nonnull
    List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list);
}
