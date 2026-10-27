package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CallAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public interface e<R, T> {

    /* JADX INFO: compiled from: CallAdapter.java */
    public static abstract class a {
        protected static Type b(int i, ParameterizedType parameterizedType) {
            return w.g(i, parameterizedType);
        }

        protected static Class<?> c(Type type) {
            return w.h(type);
        }

        @Nullable
        public abstract e<?, ?> a(Type type, Annotation[] annotationArr, s sVar);
    }

    Type a();

    T b(d<R> dVar);
}
