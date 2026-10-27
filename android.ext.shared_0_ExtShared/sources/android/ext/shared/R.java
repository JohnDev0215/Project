package android.ext.shared;

/* JADX INFO: loaded from: classes.dex */
public final class R {

    public static final class string {
        public static int app_name = 0x00010000;
    }

    public static void onResourcesLoaded(int i) {
        R.string.app_name = (i << 24) | (R.string.app_name & 16777215);
    }
}
