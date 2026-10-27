package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f1349f = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f1352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected int f1353d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    HashMap<String, androidx.constraintlayout.widget.a> f1354e;

    public d() {
        int i7 = f1349f;
        this.f1350a = i7;
        this.f1351b = i7;
        this.f1352c = null;
    }

    public abstract void a(HashMap<String, t.d> map);

    @Override // 
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract d clone();

    public d c(d dVar) {
        this.f1350a = dVar.f1350a;
        this.f1351b = dVar.f1351b;
        this.f1352c = dVar.f1352c;
        this.f1353d = dVar.f1353d;
        this.f1354e = dVar.f1354e;
        return this;
    }

    abstract void d(HashSet<String> hashSet);

    abstract void e(Context context, AttributeSet attributeSet);

    boolean f(String str) {
        String str2 = this.f1352c;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void g(HashMap<String, Integer> map) {
    }

    public d h(int i7) {
        this.f1351b = i7;
        return this;
    }
}
