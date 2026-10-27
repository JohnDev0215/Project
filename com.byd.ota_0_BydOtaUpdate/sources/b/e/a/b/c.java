package b.e.a.b;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1240a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1244e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f1245f;

    public c() {
        int i2 = f1240a;
        this.f1241b = i2;
        this.f1242c = i2;
        this.f1243d = null;
    }

    public abstract void a(HashMap<String, r> map);

    public abstract void b(HashSet<String> hashSet);

    public abstract void c(Context context, AttributeSet attributeSet);

    public boolean d(String str) {
        String str2 = this.f1243d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
