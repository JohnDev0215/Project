package defpackage;

import android.text.TextUtils;

/* JADX INFO: compiled from: VisibleUrlText.java */
/* JADX INFO: loaded from: classes2.dex */
public final class po3 {
    public final CharSequence a;
    public final CharSequence b;

    public po3(CharSequence charSequence, CharSequence charSequence2) {
        this.a = charSequence;
        boolean z = false;
        if (charSequence != null && !TextUtils.isEmpty(charSequence2) && charSequence2.length() <= charSequence.length() && TextUtils.indexOf(charSequence, charSequence2, 0, charSequence2.length()) == 0) {
            z = true;
        }
        this.b = z ? charSequence2 : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof po3)) {
            return false;
        }
        po3 po3Var = (po3) obj;
        CharSequence charSequence = this.b;
        if (charSequence == null || !TextUtils.equals(charSequence, po3Var.b)) {
            return TextUtils.equals(this.a, po3Var.a);
        }
        return true;
    }
}
