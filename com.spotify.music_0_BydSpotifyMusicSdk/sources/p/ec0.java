package p;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.inputmethod.EditorInfo;

/* JADX INFO: loaded from: classes.dex */
public final class ec0 extends p01 {
    public volatile wc0 l;
    public volatile gh2 m;

    public final void b0() {
        try {
            ((jc0) this.e).f.a(new dc0(this));
        } catch (Throwable th) {
            ((jc0) this.e).d(th);
        }
    }

    public final CharSequence c0(CharSequence charSequence, int i, int i2, boolean z) {
        tq2 tq2Var;
        int iCharCount;
        yo2[] yo2VarArr;
        wc0 wc0Var = this.l;
        wc0Var.getClass();
        boolean z2 = charSequence instanceof oe2;
        if (z2) {
            ((oe2) charSequence).a();
        }
        if (!z2) {
            try {
                tq2Var = charSequence instanceof Spannable ? new tq2((Spannable) charSequence) : (!(charSequence instanceof Spanned) || ((Spanned) charSequence).nextSpanTransition(i + (-1), i2 + 1, yo2.class) > i2) ? null : new tq2(charSequence);
            } finally {
                if (z2) {
                    ((oe2) charSequence).b();
                }
            }
        }
        if (tq2Var != null && (yo2VarArr = (yo2[]) tq2Var.e.getSpans(i, i2, yo2.class)) != null && yo2VarArr.length > 0) {
            for (yo2 yo2Var : yo2VarArr) {
                int spanStart = tq2Var.e.getSpanStart(yo2Var);
                int spanEnd = tq2Var.e.getSpanEnd(yo2Var);
                if (spanStart != i2) {
                    tq2Var.removeSpan(yo2Var);
                }
                i = Math.min(spanStart, i);
                i2 = Math.max(spanEnd, i2);
            }
        }
        if (i != i2 && i < charSequence.length()) {
            vc0 vc0Var = new vc0((fk1) ((gh2) wc0Var.c).l, wc0Var.a, (int[]) wc0Var.e);
            int iCodePointAt = Character.codePointAt(charSequence, i);
            int i3 = 0;
            tq2 tq2Var2 = tq2Var;
            loop1: while (true) {
                iCharCount = i;
                while (i < i2 && i3 < Integer.MAX_VALUE) {
                    int iA = vc0Var.a(iCodePointAt);
                    if (iA == 1) {
                        iCharCount += Character.charCount(Character.codePointAt(charSequence, iCharCount));
                        if (iCharCount < i2) {
                            iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                        }
                        i = iCharCount;
                    } else if (iA == 2) {
                        i += Character.charCount(iCodePointAt);
                        if (i < i2) {
                            iCodePointAt = Character.codePointAt(charSequence, i);
                        }
                    } else if (iA == 3) {
                        if (z || !wc0Var.d(charSequence, iCharCount, i, vc0Var.d.b)) {
                            if (tq2Var2 == null) {
                                tq2Var2 = new tq2((Spannable) new SpannableString(charSequence));
                            }
                            uc0 uc0Var = vc0Var.d.b;
                            ((zb0) wc0Var.b).getClass();
                            tq2Var2.setSpan(new yo2(uc0Var), iCharCount, i, 33);
                            i3++;
                        }
                    }
                }
                break loop1;
            }
            if (vc0Var.a == 2 && vc0Var.c.b != null && ((vc0Var.f > 1 || vc0Var.c()) && i3 < Integer.MAX_VALUE && (z || !wc0Var.d(charSequence, iCharCount, i, vc0Var.c.b)))) {
                if (tq2Var2 == null) {
                    tq2Var2 = new tq2(charSequence);
                }
                uc0 uc0Var2 = vc0Var.c.b;
                ((zb0) wc0Var.b).getClass();
                tq2Var2.setSpan(new yo2(uc0Var2), iCharCount, i, 33);
            }
            if (tq2Var2 != null) {
                Spannable spannable = tq2Var2.e;
                if (z2) {
                    ((oe2) charSequence).b();
                }
                return spannable;
            }
            if (!z2) {
                return charSequence;
            }
        } else if (!z2) {
            return charSequence;
        }
        return charSequence;
    }

    public final void d0(EditorInfo editorInfo) {
        Bundle bundle = editorInfo.extras;
        ek1 ek1Var = (ek1) this.m.e;
        int iA = ek1Var.a(4);
        bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? ek1Var.b.getInt(iA + ek1Var.a) : 0);
        Bundle bundle2 = editorInfo.extras;
        ((jc0) this.e).getClass();
        bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
    }
}
