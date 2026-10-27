package k3;

import android.os.Bundle;
import android.text.TextUtils;
import d5.AbstractC1095q;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class y1 {
    public static final d5.d0 d = d5.I.B(40010);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d5.d0 f18163e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f18164f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f18165g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f18166h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f18168b;
    public final Bundle c;

    static {
        Object[] objArr = {50000, 50001, 50002, 50003, 50004, 50005, 50006};
        AbstractC1095q.d(7, objArr);
        f18163e = d5.I.t(7, objArr);
        int i7 = AbstractC1343A.f15195a;
        f18164f = Integer.toString(0, 36);
        f18165g = Integer.toString(1, 36);
        f18166h = Integer.toString(2, 36);
    }

    public y1(int i7) {
        AbstractC1346c.b("commandCode shouldn't be COMMAND_CODE_CUSTOM", i7 != 0);
        this.f18167a = i7;
        this.f18168b = "";
        this.c = Bundle.EMPTY;
    }

    public static y1 a(Bundle bundle) {
        int i7 = bundle.getInt(f18164f, 0);
        if (i7 != 0) {
            return new y1(i7);
        }
        String string = bundle.getString(f18165g);
        string.getClass();
        Bundle bundle2 = bundle.getBundle(f18166h);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new y1(string, bundle2);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt(f18164f, this.f18167a);
        bundle.putString(f18165g, this.f18168b);
        bundle.putBundle(f18166h, this.c);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y1)) {
            return false;
        }
        y1 y1Var = (y1) obj;
        return this.f18167a == y1Var.f18167a && TextUtils.equals(this.f18168b, y1Var.f18168b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f18168b, Integer.valueOf(this.f18167a)});
    }

    public y1(String str, Bundle bundle) {
        this.f18167a = 0;
        str.getClass();
        this.f18168b = str;
        bundle.getClass();
        this.c = new Bundle(bundle);
    }
}
