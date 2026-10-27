package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: compiled from: AutocompleteState.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pa {
    public String a;
    public String b;
    public int c;
    public int d;

    public pa(pa paVar) {
        a(paVar);
    }

    public static boolean e(String str, String str2) {
        return str2.startsWith(str) && str2.length() > str.length();
    }

    public final void a(pa paVar) {
        String str = paVar.a;
        String str2 = paVar.b;
        int i = paVar.c;
        int i2 = paVar.d;
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
    }

    public final String b() {
        return this.a + this.b;
    }

    public final boolean c() {
        return !TextUtils.isEmpty(this.b);
    }

    public final boolean d() {
        return this.c == this.a.length() && this.d == this.a.length();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pa)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        pa paVar = (pa) obj;
        return this.a.equals(paVar.a) && this.b.equals(paVar.b) && this.c == paVar.c && this.d == paVar.d;
    }

    public final int hashCode() {
        return (this.d * 7) + (this.c * 5) + (this.b.hashCode() * 3) + (this.a.hashCode() * 2);
    }

    public final String toString() {
        return String.format(Locale.US, "AutocompleteState {[%s][%s] [%d-%d]}", this.a, this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    public pa(int i, int i2, String str) {
        this.a = str;
        this.b = "";
        this.c = i;
        this.d = i2;
    }
}
