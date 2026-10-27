package b.a.a.k;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ReferenceCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class x0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x0 f409a = new x0();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Object objO = aVar.O(parameterizedType.getActualTypeArguments()[0]);
        Type rawType = parameterizedType.getRawType();
        if (rawType == AtomicReference.class) {
            return (T) new AtomicReference(objO);
        }
        if (rawType == WeakReference.class) {
            return (T) new WeakReference(objO);
        }
        if (rawType == SoftReference.class) {
            return (T) new SoftReference(objO);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        i0Var.H(obj instanceof AtomicReference ? ((AtomicReference) obj).get() : ((Reference) obj).get());
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }
}
