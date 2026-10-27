package b.f.a.b;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2038a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2040c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2041d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2042e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f2043f;

    public c() {
        int i2 = f2038a;
        this.f2039b = i2;
        this.f2040c = i2;
        this.f2041d = null;
    }

    public abstract void a(HashMap<String, r> map);

    public abstract void b(HashSet<String> hashSet);

    public abstract void c(Context context, AttributeSet attributeSet);

    public boolean d(String str) {
        String str2 = this.f2041d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
