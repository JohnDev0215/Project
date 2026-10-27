package h2;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONObjectCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0 f5235a = new f0();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        p0 p0Var = p0.f5298j;
        try {
            Field declaredField = obj.getClass().getDeclaredField("map");
            if (Modifier.isPrivate(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
            }
            p0Var.c(i0Var, declaredField.get(obj), obj2, type, i8);
        } catch (Exception unused) {
            d1Var.Z();
        }
    }
}
