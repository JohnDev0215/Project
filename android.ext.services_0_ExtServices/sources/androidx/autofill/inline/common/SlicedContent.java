package androidx.autofill.inline.common;

import android.app.slice.Slice;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public abstract class SlicedContent {
    protected final Slice mSlice;

    static {
        Uri.parse("inline.slice");
    }

    protected SlicedContent(Slice slice) {
        this.mSlice = slice;
    }

    public static String getVersion(Slice slice) {
        return slice.getSpec().getType();
    }
}
