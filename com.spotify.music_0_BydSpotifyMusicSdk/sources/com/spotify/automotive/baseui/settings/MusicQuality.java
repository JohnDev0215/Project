package com.spotify.automotive.baseui.settings;

import com.spotify.automotive.playerapi.BitrateSetting;
import com.spotify.music.R;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public enum MusicQuality {
    AUTO(BitrateSetting.AUTO, R.string.music_quality_automatic),
    LOW(BitrateSetting.LOW, R.string.music_quality_low),
    NORMAL(BitrateSetting.MEDIUM, R.string.music_quality_normal),
    HIGH(BitrateSetting.HIGH, R.string.music_quality_high),
    VERY_HIGH(BitrateSetting.VERY_HIGH, R.string.music_quality_very_high);

    public static final List n = Arrays.asList(values());
    private final BitrateSetting mBitrateSetting;
    private final int mStringRes;

    MusicQuality(BitrateSetting bitrateSetting, int i) {
        this.mBitrateSetting = bitrateSetting;
        this.mStringRes = i;
    }

    public final BitrateSetting a() {
        return this.mBitrateSetting;
    }

    public final int b() {
        return this.mStringRes;
    }
}
