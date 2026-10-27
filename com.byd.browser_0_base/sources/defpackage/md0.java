package defpackage;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import org.chromium.components.browser_ui.util.R$string;

/* JADX INFO: compiled from: DownloadUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class md0 {
    public static final int[] a = {R$string.download_ui_kb, R$string.download_ui_mb, R$string.download_ui_gb};

    public static String a(Context context, long j) {
        return b(context, a, j);
    }

    public static String b(Context context, int[] iArr, long j) {
        int i;
        float f;
        float f2;
        if (j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED < 1) {
            i = iArr[0];
            f = j;
            f2 = 1024.0f;
        } else if (j / 1073741824 < 1) {
            i = iArr[1];
            f = j;
            f2 = 1048576.0f;
        } else {
            i = iArr[2];
            f = j;
            f2 = 1.0737418E9f;
        }
        return context.getResources().getString(i, Float.valueOf(f / f2));
    }

    public static Uri c(String str) {
        Uri uri = TextUtils.isEmpty(str) ? null : Uri.parse(str);
        if (uri != null) {
            String scheme = uri.normalizeScheme().getScheme();
            if (scheme == null) {
                return null;
            }
            if (!scheme.equals("https") && !scheme.equals("http")) {
                return null;
            }
        }
        return uri;
    }
}
