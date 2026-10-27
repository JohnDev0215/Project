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
    public static int f619a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f621c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f622d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f623e;
    HashMap<String, ConstraintAttribute> f;

    public c() {
        int i = f619a;
        this.f620b = i;
        this.f621c = i;
        this.f622d = null;
    }

    public abstract void a(HashMap<String, r> map);

    abstract void b(HashSet<String> hashSet);

    abstract void c(Context context, AttributeSet attributeSet);

    boolean d(String str) {
        String str2 = this.f622d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
