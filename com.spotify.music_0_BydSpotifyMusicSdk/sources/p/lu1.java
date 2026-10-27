package p;

import com.spotify.player.esperanto.proto.EsContextPlayerState$PlaybackQuality;
import com.spotify.player.esperanto.proto.EsPreparePlayOptions$PreparePlayOptions;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.BitrateLevel;
import com.spotify.player.model.BitrateStrategy;
import com.spotify.player.model.HiFiStatus;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class lu1 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[EsContextPlayerState$PlaybackQuality.BitrateLevel.values().length];
        try {
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[2] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[3] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[4] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[5] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[6] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[7] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        int[] iArr2 = new int[BitrateLevel.values().length];
        try {
            iArr2[0] = 1;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr2[1] = 2;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr2[2] = 3;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr2[3] = 4;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr2[4] = 5;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            iArr2[5] = 6;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            iArr2[6] = 7;
        } catch (NoSuchFieldError unused15) {
        }
        int[] iArr3 = new int[EsContextPlayerState$PlaybackQuality.BitrateStrategy.values().length];
        try {
            iArr3[0] = 1;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            iArr3[1] = 2;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            iArr3[2] = 3;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            iArr3[3] = 4;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            iArr3[4] = 5;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            iArr3[5] = 6;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            iArr3[6] = 7;
        } catch (NoSuchFieldError unused22) {
        }
        int[] iArr4 = new int[BitrateStrategy.values().length];
        try {
            iArr4[0] = 1;
        } catch (NoSuchFieldError unused23) {
        }
        try {
            iArr4[1] = 2;
        } catch (NoSuchFieldError unused24) {
        }
        try {
            iArr4[2] = 3;
        } catch (NoSuchFieldError unused25) {
        }
        try {
            iArr4[3] = 4;
        } catch (NoSuchFieldError unused26) {
        }
        try {
            iArr4[4] = 5;
        } catch (NoSuchFieldError unused27) {
        }
        try {
            iArr4[5] = 6;
        } catch (NoSuchFieldError unused28) {
        }
        int[] iArr5 = new int[EsContextPlayerState$PlaybackQuality.HiFiStatus.values().length];
        try {
            iArr5[0] = 1;
        } catch (NoSuchFieldError unused29) {
        }
        try {
            iArr5[1] = 2;
        } catch (NoSuchFieldError unused30) {
        }
        try {
            iArr5[2] = 3;
        } catch (NoSuchFieldError unused31) {
        }
        try {
            iArr5[3] = 4;
        } catch (NoSuchFieldError unused32) {
        }
        int[] iArr6 = new int[HiFiStatus.values().length];
        try {
            iArr6[0] = 1;
        } catch (NoSuchFieldError unused33) {
        }
        try {
            iArr6[1] = 2;
        } catch (NoSuchFieldError unused34) {
        }
        try {
            iArr6[2] = 3;
        } catch (NoSuchFieldError unused35) {
        }
        int[] iArr7 = new int[AudioStream.values().length];
        try {
            iArr7[0] = 1;
        } catch (NoSuchFieldError unused36) {
        }
        try {
            iArr7[1] = 2;
        } catch (NoSuchFieldError unused37) {
        }
        int[] iArr8 = new int[EsPreparePlayOptions$PreparePlayOptions.AudioStream.values().length];
        try {
            iArr8[0] = 1;
        } catch (NoSuchFieldError unused38) {
        }
        try {
            iArr8[1] = 2;
        } catch (NoSuchFieldError unused39) {
        }
        a = iArr8;
    }
}
