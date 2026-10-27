package h;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CallAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public interface c<R, T> {

    /* JADX INFO: compiled from: CallAdapter.java */
    public static abstract class a {
        public static Type b(int i2, ParameterizedType parameterizedType) {
            return o.i(i2, parameterizedType);
        }

        public static Class<?> c(Type type) {
            return o.j(type);
        }

        @Nullable
        public abstract c<?, ?> a(Type type, Annotation[] annotationArr, m mVar);
    }

    Type a();

    T b(b<R> bVar);
}
