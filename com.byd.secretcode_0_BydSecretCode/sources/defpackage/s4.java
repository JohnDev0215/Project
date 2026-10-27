package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: Key.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s4 {
    public static int a = -1;
    public int b;
    public int c;
    public String d;
    public int e;
    public HashMap<String, ConstraintAttribute> f;

    public s4() {
        int i = a;
        this.b = i;
        this.c = i;
        this.d = null;
    }

    public abstract void a(HashMap<String, h5> map);

    public abstract void b(HashSet<String> hashSet);

    public abstract void c(Context context, AttributeSet attributeSet);

    public boolean d(String str) {
        String str2 = this.d;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public void e(HashMap<String, Integer> map) {
    }
}
