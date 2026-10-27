package p;

import com.spotify.player.model.command.options.PlayOperation;
import com.spotify.player.model.command.options.PlayTrigger;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class qt1 {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[PlayOperation.values().length];
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
        a = iArr;
        int[] iArr2 = new int[PlayTrigger.values().length];
        try {
            iArr2[0] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[1] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[2] = 3;
        } catch (NoSuchFieldError unused6) {
        }
        b = iArr2;
    }
}
