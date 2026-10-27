package androidx.slice.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
class SliceMetrics {
    protected abstract void logHidden();

    protected abstract void logTouch(int i, Uri uri);

    protected abstract void logVisible();

    SliceMetrics() {
    }

    public static SliceMetrics getInstance(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new SliceMetricsWrapper(context, uri);
        }
        return null;
    }
}
