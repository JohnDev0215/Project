package p;

import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class zc0 {
    public final u11 a;

    public zc0(TextView textView) {
        if (textView == null) {
            throw new NullPointerException("textView cannot be null");
        }
        this.a = new yc0(textView);
    }
}
