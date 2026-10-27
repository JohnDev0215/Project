package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f925a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f926b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f927c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f928d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f929e;
    HashMap<String, ConstraintAttribute> f;

    public e() {
        int i = f925a;
        this.f926b = i;
        this.f927c = i;
        this.f928d = null;
    }

    public abstract void a(HashMap<String, b.e.a.a.d> map);

    @Override // 
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract e clone();

    public e c(e eVar) {
        this.f926b = eVar.f926b;
        this.f927c = eVar.f927c;
        this.f928d = eVar.f928d;
        this.f929e = eVar.f929e;
        this.f = eVar.f;
        return this;
    }

    abstract void d(HashSet<String> hashSet);

    abstract void e(Context context, AttributeSet attributeSet);

    boolean f(String str) {
        String str2 = this.f928d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void g(int i) {
        this.f926b = i;
    }

    public void h(HashMap<String, Integer> map) {
    }

    public e i(int i) {
        this.f927c = i;
        return this;
    }

    boolean j(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(obj.toString());
    }

    float k(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    int l(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }
}
