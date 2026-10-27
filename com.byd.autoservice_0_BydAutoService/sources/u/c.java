package u;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5050a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5052c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5053d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5054e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f5055f;

    public c() {
        int i3 = f5050a;
        this.f5051b = i3;
        this.f5052c = i3;
        this.f5053d = null;
    }

    public abstract void a(HashMap<String, r> map);

    public abstract void b(HashSet<String> hashSet);

    public abstract void c(Context context, AttributeSet attributeSet);

    public boolean d(String str) {
        String str2 = this.f5053d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
