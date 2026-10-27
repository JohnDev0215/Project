package p;

import com.spotify.playbacknative.AudioFeatures;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class cd {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[AudioFeatures.AudioUsage.values().length];
        b = iArr;
        try {
            iArr[AudioFeatures.AudioUsage.ALARM.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[AudioFeatures.AudioUsage.MEDIA.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[AudioFeatures.AudioType.values().length];
        a = iArr2;
        try {
            iArr2[AudioFeatures.AudioType.SPEECH.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[AudioFeatures.AudioType.MUSIC.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
