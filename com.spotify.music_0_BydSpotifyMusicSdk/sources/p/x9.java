package p;

import android.graphics.Typeface;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class x9 implements Runnable {
    public final /* synthetic */ TextView c;
    public final /* synthetic */ Typeface e;
    public final /* synthetic */ int k;

    public x9(TextView textView, Typeface typeface, int i) {
        this.c = textView;
        this.e = typeface;
        this.k = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.setTypeface(this.e, this.k);
    }
}
