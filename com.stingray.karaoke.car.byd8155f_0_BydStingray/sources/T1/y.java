package T1;

import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes.dex */
public final class y implements Spannable {
    public boolean l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Spannable f8529m;

    public y(Spannable spannable) {
        this.f8529m = spannable;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i7) {
        return this.f8529m.charAt(i7);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.f8529m.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.f8529m.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f8529m.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f8529m.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f8529m.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i7, int i10, Class cls) {
        return this.f8529m.getSpans(i7, i10, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f8529m.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i7, int i10, Class cls) {
        return this.f8529m.nextSpanTransition(i7, i10, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        Spannable spannable = this.f8529m;
        if (!this.l && (spannable instanceof PrecomputedText)) {
            this.f8529m = new SpannableString(spannable);
        }
        this.l = true;
        this.f8529m.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i7, int i10, int i11) {
        Spannable spannable = this.f8529m;
        if (!this.l && (spannable instanceof PrecomputedText)) {
            this.f8529m = new SpannableString(spannable);
        }
        this.l = true;
        this.f8529m.setSpan(obj, i7, i10, i11);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i7, int i10) {
        return this.f8529m.subSequence(i7, i10);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f8529m.toString();
    }
}
