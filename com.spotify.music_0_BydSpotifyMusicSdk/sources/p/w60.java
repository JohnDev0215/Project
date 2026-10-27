package p;

import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class w60 implements gc0 {
    public static final ThreadLocal b = new ThreadLocal();
    public final TextPaint a;

    public w60() {
        TextPaint textPaint = new TextPaint();
        this.a = textPaint;
        textPaint.setTextSize(10.0f);
    }
}
