package h;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CallAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public interface e<R, T> {

    /* JADX INFO: compiled from: CallAdapter.java */
    public static abstract class a {
        protected static Type b(int i2, ParameterizedType parameterizedType) {
            return y.g(i2, parameterizedType);
        }

        protected static Class<?> c(Type type) {
            return y.h(type);
        }

        @Nullable
        public abstract e<?, ?> a(Type type, Annotation[] annotationArr, u uVar);
    }

    Type a();

    T b(d<R> dVar);
}
