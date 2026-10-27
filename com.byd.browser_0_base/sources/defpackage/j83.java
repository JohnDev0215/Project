package defpackage;

import android.text.TextUtils;
import android.view.inputmethod.SurroundingText;
import java.util.Locale;

/* JADX INFO: compiled from: TextInputState.java */
/* JADX INFO: loaded from: classes3.dex */
public class j83 {
    public final CharSequence a;
    public final y82 b;
    public final y82 c;
    public final boolean d;
    public final boolean e;

    public j83(CharSequence charSequence, y82 y82Var, y82 y82Var2, boolean z, boolean z2) {
        int length = charSequence.length();
        y82Var.a = Math.min(Math.max(y82Var.a, 0), length);
        y82Var.b = Math.max(Math.min(y82Var.b, length), 0);
        if (y82Var2.a != -1 || y82Var2.b != -1) {
            int length2 = charSequence.length();
            y82Var2.a = Math.min(Math.max(y82Var2.a, 0), length2);
            y82Var2.b = Math.max(Math.min(y82Var2.b, length2), 0);
        }
        this.a = charSequence;
        this.b = y82Var;
        this.c = y82Var2;
        this.d = z;
        this.e = z2;
    }

    public final SurroundingText a(int i, int i2) {
        y82 y82Var = this.b;
        int iMax = Math.max(0, Math.min(i, y82Var.a));
        CharSequence charSequence = this.a;
        return new SurroundingText(TextUtils.substring(charSequence, y82Var.a - iMax, y82Var.b + Math.max(0, Math.min(i2, charSequence.length() - y82Var.b))), iMax, y82Var.b - (y82Var.a - iMax), -1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j83)) {
            return false;
        }
        j83 j83Var = (j83) obj;
        if (j83Var == this) {
            return true;
        }
        return TextUtils.equals(this.a, j83Var.a) && this.b.equals(j83Var.b) && this.c.equals(j83Var.c) && this.d == j83Var.d && this.e == j83Var.e;
    }

    public final int hashCode() {
        return (this.c.hashCode() * 13) + (this.b.hashCode() * 11) + (this.a.hashCode() * 7) + (this.d ? 19 : 0) + (this.e ? 23 : 0);
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = this.a;
        objArr[1] = this.b;
        objArr[2] = this.c;
        objArr[3] = this.d ? "SIN" : "MUL";
        objArr[4] = this.e ? " ReplyToRequest" : "";
        return String.format(locale, "TextInputState {[%s] SEL%s COM%s %s%s}", objArr);
    }
}
