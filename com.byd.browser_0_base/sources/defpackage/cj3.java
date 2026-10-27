package defpackage;

import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* JADX INFO: compiled from: UnprecomputeTextOnModificationSpannable.java */
/* JADX INFO: loaded from: classes.dex */
public final class cj3 implements Spannable {
    public boolean e = false;
    public Spannable f;

    public cj3(Spannable spannable) {
        this.f = spannable;
    }

    public final void a() {
        Spannable spannable = this.f;
        if (!this.e) {
            if ((spannable instanceof PrecomputedText) || (spannable instanceof j32)) {
                this.f = new SpannableString(spannable);
            }
        }
        this.e = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.f.charAt(i);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.f.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.f.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return (T[]) this.f.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i, int i2, Class cls) {
        return this.f.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.f.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i, int i2, int i3) {
        a();
        this.f.setSpan(obj, i, i2, i3);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i, int i2) {
        return this.f.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f.toString();
    }

    public cj3(CharSequence charSequence) {
        this.f = new SpannableString(charSequence);
    }
}
