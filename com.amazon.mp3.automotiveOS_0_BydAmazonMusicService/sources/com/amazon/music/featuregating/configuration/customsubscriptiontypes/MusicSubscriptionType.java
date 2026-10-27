package com.amazon.music.featuregating.configuration.customsubscriptiontypes;

import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;
import kotlin.Metadata;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: MusicSubscriptionType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0087\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/featuregating/configuration/customsubscriptiontypes/MusicSubscriptionType;", "", "(Ljava/lang/String;I)V", UpsellType.PRIME, UpsellType.FREE, "MUSIC_UNLIMITED", "MUSIC_HD", "MUSIC_HOME", PlaybackMode.NONE, "SONIC_RUSH", "$serializer", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Serializable
public enum MusicSubscriptionType {
    PRIME,
    FREE,
    MUSIC_UNLIMITED,
    MUSIC_HD,
    MUSIC_HOME,
    NONE,
    SONIC_RUSH;
}
