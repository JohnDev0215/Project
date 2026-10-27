package com.amazon.music.views.library.styles.keys;

import kotlin.Metadata;

/* JADX INFO: compiled from: ColorKey.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/views/library/styles/keys/ColorKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "COLOR_ACCENT", "COLOR_BASE", "COLOR_ERROR", "COLOR_PRIMARY", "COLOR_SECONDARY", "COLOR_BRAND_COACHELLA", "COLOR_BRAND_GRAMMYS", "COLOR_BRAND_MTV", "COLOR_BRAND_OUTSIDE_LANDS", "COLOR_TIER_HD", "COLOR_TIER_PRIME", "COLOR_TIER_UNLIMITED", "COLOR_LIVE", "COLOR_PRIMARY_GLASS_2_BASE_FALLBACK", "COLOR_CAR_MODE_EQUALIZER", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ColorKey {
    COLOR_ACCENT("Color_Accent"),
    COLOR_BASE("Color_Base"),
    COLOR_ERROR("Color_Error"),
    COLOR_PRIMARY("Color_Primary"),
    COLOR_SECONDARY("Color_Secondary"),
    COLOR_BRAND_COACHELLA("Color_Coachella"),
    COLOR_BRAND_GRAMMYS("COLOR_Grammys"),
    COLOR_BRAND_MTV("Color_MTV"),
    COLOR_BRAND_OUTSIDE_LANDS("Color_Outside_Lands"),
    COLOR_TIER_HD("Color_Tier_HD"),
    COLOR_TIER_PRIME("Color_Tier_Prime"),
    COLOR_TIER_UNLIMITED("Color_Tier_Unlimited"),
    COLOR_LIVE("Live"),
    COLOR_PRIMARY_GLASS_2_BASE_FALLBACK("Color_Primary_Glass_2_Base_Fallback"),
    COLOR_CAR_MODE_EQUALIZER("Color_Car_Mode_Equalizer");

    private final String key;

    ColorKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
