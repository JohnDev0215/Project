package x;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f8764a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8765b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8766c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f8767d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8768e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f8769f;

    public c() {
        int i8 = f8764a;
        this.f8765b = i8;
        this.f8766c = i8;
        this.f8767d = null;
    }

    public abstract void a(HashMap<String, r> map);

    public abstract void b(HashSet<String> hashSet);

    public abstract void c(Context context, AttributeSet attributeSet);

    public boolean d(String str) {
        String str2 = this.f8767d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
