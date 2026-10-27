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
    public static int f1076a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1077b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1078c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f1079d;
    protected int e;
    HashMap<String, ConstraintAttribute> f;

    public c() {
        int i = f1076a;
        this.f1077b = i;
        this.f1078c = i;
        this.f1079d = null;
    }

    public abstract void a(HashMap<String, r> map);

    abstract void b(HashSet<String> hashSet);

    abstract void c(Context context, AttributeSet attributeSet);

    boolean d(String str) {
        String str2 = this.f1079d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
