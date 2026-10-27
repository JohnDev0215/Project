package p;

import com.spotify.player.model.AudioStream;
import com.spotify.player.model.command.options.PrefetchLevel;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class bx1 {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[PrefetchLevel.values().length];
        try {
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
        int[] iArr2 = new int[AudioStream.values().length];
        try {
            iArr2[0] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[1] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        b = iArr2;
    }
}
