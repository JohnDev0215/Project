package p;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class la {
    public abstract void a(StaticLayout.Builder builder, TextView textView);

    public boolean b(TextView textView) {
        return ((Boolean) ma.e(textView, Boolean.FALSE, "getHorizontallyScrolling")).booleanValue();
    }
}
