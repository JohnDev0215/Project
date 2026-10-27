package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import androidx.emoji2.text.d;
import androidx.emoji2.text.h;
import defpackage.cj3;
import defpackage.kh3;
import defpackage.lh3;
import defpackage.mh1;
import defpackage.mv1;
import defpackage.pg0;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: EmojiProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public final d.j a;
    public final h b;
    public final d.e c;

    /* JADX INFO: compiled from: EmojiProcessor.java */
    public static class a implements b<cj3> {
        public cj3 a;
        public final d.j b;

        public a(cj3 cj3Var, d.j jVar) {
            this.a = cj3Var;
            this.b = jVar;
        }

        @Override // androidx.emoji2.text.f.b
        public final boolean a(CharSequence charSequence, int i, int i2, kh3 kh3Var) {
            if ((kh3Var.c & 4) > 0) {
                return true;
            }
            if (this.a == null) {
                this.a = new cj3(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
            }
            ((d.C0008d) this.b).getClass();
            this.a.setSpan(new lh3(kh3Var), i, i2, 33);
            return true;
        }

        @Override // androidx.emoji2.text.f.b
        public final cj3 getResult() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: EmojiProcessor.java */
    public interface b<T> {
        boolean a(CharSequence charSequence, int i, int i2, kh3 kh3Var);

        T getResult();
    }

    /* JADX INFO: compiled from: EmojiProcessor.java */
    public static final class d {
        public int a = 1;
        public final h.a b;
        public h.a c;
        public h.a d;
        public int e;
        public int f;
        public final boolean g;
        public final int[] h;

        public d(h.a aVar, boolean z, int[] iArr) {
            this.b = aVar;
            this.c = aVar;
            this.g = z;
            this.h = iArr;
        }

        public final void a() {
            this.a = 1;
            this.c = this.b;
            this.f = 0;
        }

        public final boolean b() {
            int[] iArr;
            mh1 mh1VarC = this.c.b.c();
            int iA = mh1VarC.a(6);
            if ((iA == 0 || mh1VarC.b.get(iA + mh1VarC.a) == 0) ? false : true) {
                return true;
            }
            if (this.e == 65039) {
                return true;
            }
            return this.g && ((iArr = this.h) == null || Arrays.binarySearch(iArr, this.c.b.a(0)) < 0);
        }
    }

    public f(h hVar, d.C0008d c0008d, androidx.emoji2.text.b bVar, Set set) {
        this.a = c0008d;
        this.b = hVar;
        this.c = bVar;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            c(str, 0, str.length(), 1, true, new c(str));
        }
    }

    public static boolean a(Editable editable, KeyEvent keyEvent, boolean z) {
        pg0[] pg0VarArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!(selectionStart == -1 || selectionEnd == -1 || selectionStart != selectionEnd) && (pg0VarArr = (pg0[]) editable.getSpans(selectionStart, selectionEnd, pg0.class)) != null && pg0VarArr.length > 0) {
            for (pg0 pg0Var : pg0VarArr) {
                int spanStart = editable.getSpanStart(pg0Var);
                int spanEnd = editable.getSpanEnd(pg0Var);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean b(CharSequence charSequence, int i, int i2, kh3 kh3Var) {
        if ((kh3Var.c & 3) == 0) {
            d.e eVar = this.c;
            mh1 mh1VarC = kh3Var.c();
            int iA = mh1VarC.a(8);
            if (iA != 0) {
                mh1VarC.b.getShort(iA + mh1VarC.a);
            }
            androidx.emoji2.text.b bVar = (androidx.emoji2.text.b) eVar;
            bVar.getClass();
            ThreadLocal<StringBuilder> threadLocal = androidx.emoji2.text.b.b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = bVar.a;
            String string = sb.toString();
            int i3 = mv1.a;
            boolean zHasGlyph = textPaint.hasGlyph(string);
            int i4 = kh3Var.c & 4;
            kh3Var.c = zHasGlyph ? i4 | 2 : i4 | 1;
        }
        return (kh3Var.c & 3) == 2;
    }

    public final <T> T c(CharSequence charSequence, int i, int i2, int i3, boolean z, b<T> bVar) {
        char c2;
        h.a aVar = null;
        d dVar = new d(this.b.c, false, null);
        int iCharCount = i;
        int iCodePointAt = Character.codePointAt(charSequence, i);
        int i4 = 0;
        boolean z2 = true;
        int iCharCount2 = iCharCount;
        while (iCharCount2 < i2 && i4 < i3 && z2) {
            SparseArray<h.a> sparseArray = dVar.c.a;
            h.a aVar2 = sparseArray == null ? aVar : sparseArray.get(iCodePointAt);
            if (dVar.a == 2) {
                if (aVar2 != null) {
                    dVar.c = aVar2;
                    dVar.f++;
                } else {
                    if (iCodePointAt == 65038) {
                        dVar.a();
                    } else {
                        if (!(iCodePointAt == 65039)) {
                            h.a aVar3 = dVar.c;
                            if (aVar3.b != null) {
                                if (dVar.f != 1) {
                                    dVar.d = aVar3;
                                    dVar.a();
                                } else if (dVar.b()) {
                                    dVar.d = dVar.c;
                                    dVar.a();
                                } else {
                                    dVar.a();
                                }
                                c2 = 3;
                            } else {
                                dVar.a();
                            }
                        }
                    }
                    c2 = 1;
                }
                c2 = 2;
            } else if (aVar2 == null) {
                dVar.a();
                c2 = 1;
            } else {
                dVar.a = 2;
                dVar.c = aVar2;
                dVar.f = 1;
                c2 = 2;
            }
            dVar.e = iCodePointAt;
            if (c2 != 1) {
                if (c2 == 2) {
                    iCharCount2 += Character.charCount(iCodePointAt);
                    if (iCharCount2 < i2) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount2);
                    }
                } else if (c2 == 3) {
                    if (z || !b(charSequence, iCharCount, iCharCount2, dVar.d.b)) {
                        boolean zA = bVar.a(charSequence, iCharCount, iCharCount2, dVar.d.b);
                        i4++;
                        iCharCount = iCharCount2;
                        z2 = zA;
                    } else {
                        iCharCount = iCharCount2;
                    }
                }
                aVar = null;
            } else {
                iCharCount += Character.charCount(Character.codePointAt(charSequence, iCharCount));
                if (iCharCount < i2) {
                    iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                }
            }
            iCharCount2 = iCharCount;
            aVar = null;
        }
        if ((dVar.a == 2 && dVar.c.b != null && (dVar.f > 1 || dVar.b())) && i4 < i3 && z2 && (z || !b(charSequence, iCharCount, iCharCount2, dVar.c.b))) {
            bVar.a(charSequence, iCharCount, iCharCount2, dVar.c.b);
        }
        return bVar.getResult();
    }

    /* JADX INFO: compiled from: EmojiProcessor.java */
    public static class c implements b<c> {
        public final String a;

        public c(String str) {
            this.a = str;
        }

        @Override // androidx.emoji2.text.f.b
        public final boolean a(CharSequence charSequence, int i, int i2, kh3 kh3Var) {
            if (!TextUtils.equals(charSequence.subSequence(i, i2), this.a)) {
                return true;
            }
            kh3Var.c = (kh3Var.c & 3) | 4;
            return false;
        }

        @Override // androidx.emoji2.text.f.b
        public final c getResult() {
            return this;
        }
    }
}
