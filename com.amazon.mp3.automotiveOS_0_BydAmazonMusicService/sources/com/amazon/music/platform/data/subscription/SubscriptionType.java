package com.amazon.music.platform.data.subscription;

import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: SubscriptionType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\r\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bJ\b\u0010\t\u001a\u0004\u0018\u00010\u0004j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "", "(Ljava/lang/String;I)V", "browseSubscriptionMode", "", "contentSubscriptionMode", "isUnlimited", "", "isUnlimited$MusicPlatformCore_release", "subContentSubscriptionMode", UpsellType.PRIME, "MUSIC_UNLIMITED", "MUSIC_HD", "MUSIC_HOME", UpsellType.FREE, "SONIC_RUSH", PlaybackMode.NONE, "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum SubscriptionType {
    PRIME,
    MUSIC_UNLIMITED,
    MUSIC_HD,
    MUSIC_HOME,
    FREE,
    SONIC_RUSH,
    NONE;

    /* JADX INFO: compiled from: SubscriptionType.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubscriptionType.values().length];
            iArr[SubscriptionType.PRIME.ordinal()] = 1;
            iArr[SubscriptionType.MUSIC_UNLIMITED.ordinal()] = 2;
            iArr[SubscriptionType.MUSIC_HD.ordinal()] = 3;
            iArr[SubscriptionType.MUSIC_HOME.ordinal()] = 4;
            iArr[SubscriptionType.FREE.ordinal()] = 5;
            iArr[SubscriptionType.SONIC_RUSH.ordinal()] = 6;
            iArr[SubscriptionType.NONE.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final boolean isUnlimited$MusicPlatformCore_release() {
        return this == MUSIC_UNLIMITED || this == MUSIC_HD;
    }

    public final String contentSubscriptionMode() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 6:
                return UpsellType.PRIME;
            case 2:
            case 3:
            case 4:
                return UpsellType.UNLIMITED;
            case 5:
                return "AMF";
            case 7:
                return PlaybackMode.NONE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String subContentSubscriptionMode() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 7:
                return null;
            case 3:
                return "UNLIMITED_HD";
            case 6:
                return "SONIC_RUSH";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String browseSubscriptionMode() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return UpsellType.PRIME;
            case 2:
            case 3:
            case 4:
                return UpsellType.UNLIMITED;
            case 5:
                return "AMF";
            case 6:
                return "SONIC_RUSH";
            case 7:
                return PlaybackMode.NONE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
