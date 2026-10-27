package h2;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ReferenceCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class x0 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x0 f5312a = new x0();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Object objE0 = aVar.e0(parameterizedType.getActualTypeArguments()[0]);
        Type rawType = parameterizedType.getRawType();
        if (rawType == AtomicReference.class) {
            return (T) new AtomicReference(objE0);
        }
        if (rawType == WeakReference.class) {
            return (T) new WeakReference(objE0);
        }
        if (rawType == SoftReference.class) {
            return (T) new SoftReference(objE0);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        i0Var.H(obj instanceof AtomicReference ? ((AtomicReference) obj).get() : ((Reference) obj).get());
    }

    @Override // g2.t
    public int e() {
        return 12;
    }
}
