package h;

import f.f0;
import f.h0;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Converter.java */
/* JADX INFO: loaded from: classes.dex */
public interface h<F, T> {

    /* JADX INFO: compiled from: Converter.java */
    public static abstract class a {
        protected static Type a(int i2, ParameterizedType parameterizedType) {
            return y.g(i2, parameterizedType);
        }

        protected static Class<?> b(Type type) {
            return y.h(type);
        }

        @Nullable
        public h<?, f0> c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, u uVar) {
            return null;
        }

        @Nullable
        public h<h0, ?> d(Type type, Annotation[] annotationArr, u uVar) {
            return null;
        }

        @Nullable
        public h<?, String> e(Type type, Annotation[] annotationArr, u uVar) {
            return null;
        }
    }

    @Nullable
    T a(F f2);
}
