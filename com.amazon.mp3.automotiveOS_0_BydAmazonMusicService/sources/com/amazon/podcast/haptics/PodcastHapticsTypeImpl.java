package com.amazon.podcast.haptics;

import androidx.annotation.NonNull;
import com.amazon.music.platform.providers.HapticsType;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastHapticsTypeImpl implements HapticsType {
    private final String type;

    public PodcastHapticsTypeImpl(String type) {
        this.type = type;
    }

    @Override // com.amazon.music.platform.providers.HapticsType
    @NonNull
    public String getType() {
        return this.type;
    }
}
