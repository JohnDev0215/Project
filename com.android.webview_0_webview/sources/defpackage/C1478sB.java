package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: renamed from: sB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1478sB {
    public final CharSequence a;
    public final XA b;
    public final XA c;
    public final boolean d;
    public final boolean e;

    public C1478sB(CharSequence charSequence, XA xa, XA xa2, boolean z, boolean z2) {
        xa.a(0, charSequence.length());
        if (xa2.a != -1 || xa2.b != -1) {
            xa2.a(0, charSequence.length());
        }
        this.a = charSequence;
        this.b = xa;
        this.c = xa2;
        this.d = z;
        this.e = z2;
    }

    public boolean a() {
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1478sB)) {
            return false;
        }
        C1478sB c1478sB = (C1478sB) obj;
        if (c1478sB == this) {
            return true;
        }
        return TextUtils.equals(this.a, c1478sB.a) && this.b.equals(c1478sB.b) && this.c.equals(c1478sB.c) && this.d == c1478sB.d && this.e == c1478sB.e;
    }

    public int hashCode() {
        return (this.c.hashCode() * 13) + (this.b.hashCode() * 11) + (this.a.hashCode() * 7) + (this.d ? 19 : 0) + (this.e ? 23 : 0);
    }

    public String toString() {
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
