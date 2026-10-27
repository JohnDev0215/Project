package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y31 {
    public int a = -1;
    public int b = -1;
    public String c = null;
    public HashMap<String, ox> d;

    public static float g(Number number) {
        return number instanceof Float ? ((Float) number).floatValue() : Float.parseFloat(number.toString());
    }

    public abstract void a(HashMap<String, rn3> map);

    @Override // 
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public abstract y31 clone();

    public y31 c(y31 y31Var) {
        this.a = y31Var.a;
        this.b = y31Var.b;
        this.c = y31Var.c;
        this.d = y31Var.d;
        return this;
    }

    public abstract void d(HashSet<String> hashSet);

    public abstract void e(Context context, AttributeSet attributeSet);

    public void f(HashMap<String, Integer> map) {
    }
}
