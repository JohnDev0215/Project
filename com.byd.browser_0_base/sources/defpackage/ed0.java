package defpackage;

/* JADX INFO: compiled from: DownloadMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public final class ed0 {
    public static void a(int i, String str) {
        int iP = n3.P(str);
        if (iP == 2) {
            oj2.U(i, 16, "Android.DownloadManager.OpenSource.Video");
        } else if (iP == 3) {
            oj2.U(i, 16, "Android.DownloadManager.OpenSource.Audio");
        } else {
            oj2.U(i, 16, "Android.DownloadManager.OpenSource.Other");
        }
    }
}
