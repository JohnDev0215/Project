package okhttp3.internal.http2;

import com.spotify.playbacknative.AudioDriver;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Settings {
    public int a;
    public final int[] b = new int[10];

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    public final int a() {
        return (this.a & 128) != 0 ? this.b[7] : AudioDriver.SPOTIFY_MAX_VOLUME;
    }

    public final void b(Settings settings) {
        v03.j(settings, "other");
        for (int i = 0; i < 10; i++) {
            if (((1 << i) & settings.a) != 0) {
                c(i, settings.b[i]);
            }
        }
    }

    public final void c(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.b;
            if (i >= iArr.length) {
                return;
            }
            this.a = (1 << i) | this.a;
            iArr[i] = i2;
        }
    }
}
