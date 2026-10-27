package com.spotify.cosmos.cosmonautatoms;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.annotations.DELETE;
import com.spotify.cosmos.cosmonaut.annotations.GET;
import com.spotify.cosmos.cosmonaut.annotations.POST;
import com.spotify.cosmos.cosmonaut.annotations.PUT;
import com.spotify.cosmos.cosmonaut.annotations.SUB;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.ux2;
import p.v03;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class ReflectionUtil {
    public static final Companion Companion = new Companion(null);
    private static final Set<Class<? extends Annotation>> ACTIONS = ux2.z(GET.class, POST.class, SUB.class, DELETE.class, PUT.class);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String findAction(Method method) {
            v03.j(method, "method");
            return zu.f0(zu.d0(findActionAnnotation(method))).getSimpleName();
        }

        public final Annotation findActionAnnotation(Method method) {
            v03.j(method, "method");
            Annotation[] annotations = method.getAnnotations();
            v03.i(annotations, "annotations");
            for (Annotation annotation : annotations) {
                if (ReflectionUtil.ACTIONS.contains(zu.f0(zu.d0(annotation)))) {
                    return annotation;
                }
            }
            throw new IllegalArgumentException(method.toString() + " does not declare one of " + ReflectionUtil.ACTIONS + ", found " + Arrays.toString(annotations));
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.annotation.Annotation] */
        public final <T> T findAnnotation(Annotation[] annotationArr, Class<T> cls) {
            v03.j(annotationArr, "parameterAnnotations");
            v03.j(cls, RxProductState.Keys.KEY_TYPE);
            for (Annotation annotation : annotationArr) {
                ?? r2 = (T) annotation;
                if (cls.isAssignableFrom(zu.f0(zu.d0(r2)))) {
                    return r2;
                }
            }
            return null;
        }
    }

    private ReflectionUtil() {
    }

    public static final String findAction(Method method) {
        return Companion.findAction(method);
    }

    public static final Annotation findActionAnnotation(Method method) {
        return Companion.findActionAnnotation(method);
    }

    public static final <T> T findAnnotation(Annotation[] annotationArr, Class<T> cls) {
        return (T) Companion.findAnnotation(annotationArr, cls);
    }
}
