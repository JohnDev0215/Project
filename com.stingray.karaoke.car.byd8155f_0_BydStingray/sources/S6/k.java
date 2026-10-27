package S6;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k implements Appendable, CharSequence {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayDeque f8076m = new ArrayDeque(8);
    public final StringBuilder l = new StringBuilder((CharSequence) "");

    public k() {
        b(0, "");
    }

    public static void c(k kVar, Object obj, int i7, int i10) {
        if (obj != null) {
            if (!obj.getClass().isArray()) {
                kVar.f8076m.push(new i(i7, i10, 33, obj));
                return;
            }
            for (Object obj2 : (Object[]) obj) {
                c(kVar, obj2, i7, i10);
            }
        }
    }

    public final void a(char c) {
        this.l.append(c);
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c) {
        this.l.append(c);
        return this;
    }

    public final void b(int i7, CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            boolean z6 = spanned instanceof j;
            Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
            int length = spans != null ? spans.length : 0;
            if (length > 0) {
                ArrayDeque arrayDeque = this.f8076m;
                if (!z6) {
                    for (int i10 = 0; i10 < length; i10++) {
                        Object obj = spans[i10];
                        arrayDeque.push(new i(spanned.getSpanStart(obj) + i7, spanned.getSpanEnd(obj) + i7, spanned.getSpanFlags(obj), obj));
                    }
                    return;
                }
                for (int i11 = length - 1; i11 >= 0; i11--) {
                    Object obj2 = spans[i11];
                    arrayDeque.push(new i(spanned.getSpanStart(obj2) + i7, spanned.getSpanEnd(obj2) + i7, spanned.getSpanFlags(obj2), obj2));
                }
            }
        }
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i7) {
        return this.l.charAt(i7);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.l.length();
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i7, int i10) {
        List<i> listUnmodifiableList;
        int i11;
        StringBuilder sb = this.l;
        int length = sb.length();
        if (i10 > i7 && i7 >= 0 && i10 <= length) {
            ArrayDeque arrayDeque = this.f8076m;
            if (i7 == 0 && length == i10) {
                ArrayList arrayList = new ArrayList(arrayDeque);
                Collections.reverse(arrayList);
                listUnmodifiableList = Collections.unmodifiableList(arrayList);
            } else {
                ArrayList arrayList2 = new ArrayList(0);
                Iterator itDescendingIterator = arrayDeque.descendingIterator();
                while (itDescendingIterator.hasNext()) {
                    i iVar = (i) itDescendingIterator.next();
                    int i12 = iVar.f8075b;
                    if ((i12 >= i7 && i12 < i10) || (((i11 = iVar.c) <= i10 && i11 > i7) || (i12 < i7 && i11 > i10))) {
                        arrayList2.add(iVar);
                    }
                }
                listUnmodifiableList = Collections.unmodifiableList(arrayList2);
            }
        } else {
            listUnmodifiableList = Collections.emptyList();
        }
        if (listUnmodifiableList.isEmpty()) {
            return sb.subSequence(i7, i10);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.subSequence(i7, i10));
        int length2 = spannableStringBuilder.length();
        for (i iVar2 : listUnmodifiableList) {
            int iMax = Math.max(0, iVar2.f8075b - i7);
            spannableStringBuilder.setSpan(iVar2.f8074a, iMax, Math.min(length2, (iVar2.c - iVar2.f8075b) + iMax), iVar2.d);
        }
        return spannableStringBuilder;
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.l.toString();
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i7, int i10) {
        CharSequence charSequenceSubSequence = charSequence.subSequence(i7, i10);
        StringBuilder sb = this.l;
        b(sb.length(), charSequenceSubSequence);
        sb.append(charSequenceSubSequence);
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        StringBuilder sb = this.l;
        b(sb.length(), charSequence);
        sb.append(charSequence);
        return this;
    }
}
