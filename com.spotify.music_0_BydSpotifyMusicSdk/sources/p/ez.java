package p;

import android.app.ForegroundServiceStartNotAllowedException;
import android.content.ClipData;
import android.view.ContentInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ez {
    public static /* bridge */ /* synthetic */ ForegroundServiceStartNotAllowedException b(Object obj) {
        return (ForegroundServiceStartNotAllowedException) obj;
    }

    public static /* synthetic */ ContentInfo.Builder d(ClipData clipData, int i) {
        return new ContentInfo.Builder(clipData, i);
    }

    public static /* bridge */ /* synthetic */ ContentInfo f(Object obj) {
        return (ContentInfo) obj;
    }

    public static /* bridge */ /* synthetic */ boolean l(Object obj) {
        return obj instanceof ForegroundServiceStartNotAllowedException;
    }
}
