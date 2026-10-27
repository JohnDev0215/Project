package defpackage;

/* JADX INFO: compiled from: MimeTypeUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class th1 {
    public static String a(int i) {
        boolean z = true;
        if (yj.a()) {
            if (xz.a.getApplicationInfo().targetSdkVersion >= 33) {
                z = false;
            }
        }
        if (z) {
            return "android.permission.READ_EXTERNAL_STORAGE";
        }
        if (i == 2) {
            return "android.permission.READ_MEDIA_IMAGES";
        }
        if (i == 3) {
            return "android.permission.READ_MEDIA_AUDIO";
        }
        if (i != 4) {
            return null;
        }
        return "android.permission.READ_MEDIA_VIDEO";
    }
}
