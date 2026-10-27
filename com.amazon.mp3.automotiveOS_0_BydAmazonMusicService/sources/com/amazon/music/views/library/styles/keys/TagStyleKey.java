package com.amazon.music.views.library.styles.keys;

import kotlin.Metadata;

/* JADX INFO: compiled from: TagStyleKey.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/styles/keys/TagStyleKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "SOLID_PRIMARY", "GLASS_PRIMARY", "GLASS_SECONDARY", "GLASS_PRIMARY_ALERT", "OUTLINE_PRIMARY", "GLASS_TIER_HD", "OUTLINE_TIER_HD", "OUTLINE_TIER_HD_ALERT", "LIVE", "LIVE_VIEWER", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum TagStyleKey {
    SOLID_PRIMARY("Solid_Primary"),
    GLASS_PRIMARY("Glass_Primary"),
    GLASS_SECONDARY("Glass_Secondary"),
    GLASS_PRIMARY_ALERT("Glass_Primary_Alert"),
    OUTLINE_PRIMARY("Outline_Primary"),
    GLASS_TIER_HD("Glass_Tier_Hd"),
    OUTLINE_TIER_HD("Outline_Tier_Hd"),
    OUTLINE_TIER_HD_ALERT("Outline_Tier_Hd_Alert"),
    LIVE("Live"),
    LIVE_VIEWER("Live_Viewer");

    private final String key;

    TagStyleKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
