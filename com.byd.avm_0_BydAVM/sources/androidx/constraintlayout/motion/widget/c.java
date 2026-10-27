package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f557a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f558b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f559c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f560d;
    protected int e;
    HashMap<String, ConstraintAttribute> f;

    public c() {
        int i = f557a;
        this.f558b = i;
        this.f559c = i;
        this.f560d = null;
    }

    public abstract void a(HashMap<String, r> map);

    abstract void b(HashSet<String> hashSet);

    abstract void c(Context context, AttributeSet attributeSet);

    boolean d(String str) {
        String str2 = this.f560d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
