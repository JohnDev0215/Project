package defpackage;

import java.util.Locale;

/* JADX INFO: compiled from: VideoTutorialUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class im3 {
    public static String a(int i) {
        int i2 = i / 3600;
        int i3 = (i / 60) % 60;
        int i4 = i % 60;
        return i2 > 0 ? String.format(Locale.US, "%d:%02d:%02d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)) : String.format(Locale.US, "%d:%02d", Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
