package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.emoji2.text.d;

/* JADX INFO: compiled from: DefaultGlyphChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements d.e {
    public static final ThreadLocal<StringBuilder> b = new ThreadLocal<>();
    public final TextPaint a;

    public b() {
        TextPaint textPaint = new TextPaint();
        this.a = textPaint;
        textPaint.setTextSize(10.0f);
    }
}
