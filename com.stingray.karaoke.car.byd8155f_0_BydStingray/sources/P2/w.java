package p2;

import B2.C0063c;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.media.AudioAttributes;
import android.media.AudioTrack;
import e2.C1195c;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f20835a = new w();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f20836b = new w();

    public static AudioAttributes b(C1195c c1195c, boolean z6) {
        return z6 ? new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build() : (AudioAttributes) c1195c.b().f13998a;
    }

    public static int c(int i7) {
        if (i7 == 20) {
            return 63750;
        }
        if (i7 == 30) {
            return 2250000;
        }
        switch (i7) {
            case 5:
                return 80000;
            case 6:
                return 768000;
            case 7:
                return 192000;
            case 8:
                return 2250000;
            case 9:
                return 40000;
            case 10:
                return 100000;
            case 11:
                return 16000;
            case 12:
                return 7000;
            default:
                switch (i7) {
                    case 14:
                        return 3062500;
                    case 15:
                        return BYDAutoEngineDevice.ENGINE_SPEED_MAX;
                    case 16:
                        return 256000;
                    case 17:
                        return 336000;
                    case 18:
                        return 768000;
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    public AudioTrack a(C0063c c0063c, C1195c c1195c, int i7) {
        int i10 = AbstractC1343A.f15195a;
        boolean z6 = c0063c.d;
        int i11 = c0063c.f551a;
        int i12 = c0063c.c;
        int i13 = c0063c.f552b;
        if (i10 < 23) {
            return new AudioTrack(b(c1195c, z6), AbstractC1343A.o(i13, i12, i11), c0063c.f554f, 1, i7);
        }
        AudioTrack.Builder sessionId = new AudioTrack.Builder().setAudioAttributes(b(c1195c, z6)).setAudioFormat(AbstractC1343A.o(i13, i12, i11)).setTransferMode(1).setBufferSizeInBytes(c0063c.f554f).setSessionId(i7);
        if (i10 >= 29) {
            sessionId.setOffloadedPlayback(c0063c.f553e);
        }
        return sessionId.build();
    }
}
