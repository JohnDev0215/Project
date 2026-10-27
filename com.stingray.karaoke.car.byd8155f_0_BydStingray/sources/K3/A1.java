package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class A1 {
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f17715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f17716f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17717a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17718b;
    public final Bundle c;

    static {
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(0, 36);
        f17715e = Integer.toString(1, 36);
        f17716f = Integer.toString(2, 36);
    }

    public A1(int i7) {
        this("no error message provided", i7, Bundle.EMPTY);
    }

    public static A1 a(Bundle bundle) {
        int i7 = bundle.getInt(d, 1000);
        String string = bundle.getString(f17715e, "");
        Bundle bundle2 = bundle.getBundle(f17716f);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new A1(string, i7, bundle2);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt(d, this.f17717a);
        bundle.putString(f17715e, this.f17718b);
        Bundle bundle2 = this.c;
        if (!bundle2.isEmpty()) {
            bundle.putBundle(f17716f, bundle2);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof A1)) {
            return false;
        }
        A1 a12 = (A1) obj;
        return this.f17717a == a12.f17717a && Objects.equals(this.f17718b, a12.f17718b);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f17717a), this.f17718b);
    }

    public A1(String str, int i7, Bundle bundle) {
        boolean z6 = true;
        if (i7 >= 0 && i7 != 1) {
            z6 = false;
        }
        AbstractC1346c.c(z6);
        this.f17717a = i7;
        this.f17718b = str;
        this.c = bundle;
    }
}
