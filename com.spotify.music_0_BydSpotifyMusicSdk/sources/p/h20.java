package p;

import com.spotify.player.legacyplayer.PlayOptions$AudioStream;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h20 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PlayOptions$AudioStream.values().length];
        try {
            iArr[1] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[0] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
