package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONObjectCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0 f365a = new f0();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        p0 p0Var = p0.j;
        try {
            Field declaredField = obj.getClass().getDeclaredField("map");
            if (Modifier.isPrivate(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
            }
            p0Var.c(i0Var, declaredField.get(obj), obj2, type, i);
        } catch (Exception unused) {
            d1Var.J();
        }
    }
}
