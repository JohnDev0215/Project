package com.amazon.music.media.playback;

import com.amazon.digitalmusicplayback.PlayerAudioQuality;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackAudioQuality {
    UNKNOWN(PlayerAudioQuality.UNKNOWN),
    LOW(PlayerAudioQuality.LOW),
    MEDIUM(PlayerAudioQuality.MEDIUM),
    HIGH(PlayerAudioQuality.HIGH),
    HD44(PlayerAudioQuality.HD44),
    HD48(PlayerAudioQuality.HD48),
    HD96(PlayerAudioQuality.HD96),
    HD192(PlayerAudioQuality.HD192),
    UHD44(PlayerAudioQuality.UHD44),
    UHD48(PlayerAudioQuality.UHD48),
    UHD96(PlayerAudioQuality.UHD96),
    UHD192(PlayerAudioQuality.UHD192),
    THREE_DIMENSIONAL(null),
    THREED_DATMOS_LOW(PlayerAudioQuality.THREED_DATMOS_LOW),
    THREED_DATMOS_MED(PlayerAudioQuality.THREED_DATMOS_MED),
    THREED_DATMOS_HIGH(PlayerAudioQuality.THREED_DATMOS_HIGH),
    THREED_S360RA_L0(PlayerAudioQuality.THREED_S360RA_L0),
    THREED_S360RA_L1(PlayerAudioQuality.THREED_S360RA_L1),
    THREED_S360RA_L2(PlayerAudioQuality.THREED_S360RA_L2),
    THREED_S360RA_L3(PlayerAudioQuality.THREED_S360RA_L3);

    private final PlayerAudioQuality playerAudioQuality;

    PlaybackAudioQuality(PlayerAudioQuality playerAudioQuality) {
        this.playerAudioQuality = playerAudioQuality;
    }

    public static PlaybackAudioQuality fromPlayerAudioQuality(PlayerAudioQuality playerAudioQuality) {
        for (PlaybackAudioQuality playbackAudioQuality : values()) {
            if (playbackAudioQuality.getPlayerAudioQuality() != null && playbackAudioQuality.getPlayerAudioQuality().equals(playerAudioQuality)) {
                return playbackAudioQuality;
            }
        }
        return HIGH;
    }

    public PlayerAudioQuality getPlayerAudioQuality() {
        return this.playerAudioQuality;
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.PlaybackAudioQuality$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality;

        static {
            int[] iArr = new int[PlayerAudioQuality.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality = iArr;
            try {
                iArr[PlayerAudioQuality.THREED_DATMOS_LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_DATMOS_MED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_DATMOS_HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_S360RA_L0.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_S360RA_L1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_S360RA_L2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.THREED_S360RA_L3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.UHD44.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.UHD48.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.UHD96.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.UHD192.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.HD44.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.HD48.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.HD96.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[PlayerAudioQuality.HD192.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public boolean isKatanaQuality() {
        switch (AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$PlayerAudioQuality[this.playerAudioQuality.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            default:
                return false;
        }
    }
}
