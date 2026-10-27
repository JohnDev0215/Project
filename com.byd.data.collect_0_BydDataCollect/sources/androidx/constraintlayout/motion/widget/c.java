package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f832a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f835d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    HashMap<String, androidx.constraintlayout.widget.a> f837f;

    public c() {
        int i2 = f832a;
        this.f833b = i2;
        this.f834c = i2;
        this.f835d = null;
    }

    public abstract void a(HashMap<String, r> map);

    abstract void b(HashSet<String> hashSet);

    abstract void c(Context context, AttributeSet attributeSet);

    boolean d(String str) {
        String str2 = this.f835d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
