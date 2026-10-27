package com.spotify.cosmos.cosmonautatoms;

import com.google.common.base.Optional;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.annotations.Body;
import com.spotify.cosmos.cosmonaut.annotations.BodyPart;
import com.spotify.cosmos.cosmonautatoms.ReflectionUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.bz0;
import p.oz0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class BodyAtom {
    public static final Companion Companion = new Companion(null);
    private static final byte[] DEFAULT_VALUE = new byte[0];
    private final Optional<bz0> argumentTransformation;
    private final Method method;
    private final RequestTransformers transformers;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] getDEFAULT_VALUE$src_main_java_com_spotify_cosmos_cosmonautatoms_cosmonautatoms() {
            return BodyAtom.DEFAULT_VALUE;
        }
    }

    public BodyAtom(Method method, RequestTransformers requestTransformers) {
        v03.j(method, "method");
        v03.j(requestTransformers, "transformers");
        this.method = method;
        this.transformers = requestTransformers;
        this.argumentTransformation = createOptionalArgumentTransformer(method, requestTransformers);
    }

    private final Optional<bz0> createOptionalArgumentTransformer(Method method, RequestTransformers requestTransformers) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        v03.i(parameterAnnotations, "parameterAnnotations");
        int length = parameterAnnotations.length;
        for (int i = 0; i < length; i++) {
            Annotation[] annotationArr = parameterAnnotations[i];
            ReflectionUtil.Companion companion = ReflectionUtil.Companion;
            v03.i(annotationArr, "parameterAnnotation");
            if (((Body) companion.findAnnotation(annotationArr, Body.class)) != null) {
                Type type = method.getGenericParameterTypes()[i];
                v03.i(type, RxProductState.Keys.KEY_TYPE);
                return Optional.e(new BodyTransformation(i, type, requestTransformers.find(type)));
            }
        }
        ArrayList arrayList = new ArrayList(1);
        int length2 = parameterAnnotations.length;
        for (int i2 = 0; i2 < length2; i2++) {
            Annotation[] annotationArr2 = parameterAnnotations[i2];
            ReflectionUtil.Companion companion2 = ReflectionUtil.Companion;
            v03.i(annotationArr2, "parameterAnnotation");
            BodyPart bodyPart = (BodyPart) companion2.findAnnotation(annotationArr2, BodyPart.class);
            if (bodyPart != null) {
                arrayList.add(new PartArgument(bodyPart.value(), i2));
            }
        }
        if (!arrayList.isEmpty()) {
            return Optional.e(new BodyPartTransformation(arrayList, requestTransformers.find(Map.class)));
        }
        Optional<bz0> optionalA = Optional.a();
        v03.i(optionalA, "absent()");
        return optionalA;
    }

    public final byte[] call(final Object[] objArr) {
        Object objG = this.argumentTransformation.i(new oz0() { // from class: com.spotify.cosmos.cosmonautatoms.BodyAtom.call.1
            @Override // p.oz0
            public final byte[] apply(bz0 bz0Var) {
                if (bz0Var == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Object[] objArr2 = objArr;
                if (objArr2 != null) {
                    return (byte[]) bz0Var.apply(objArr2);
                }
                throw new IllegalStateException("Required value was null.".toString());
            }
        }).g(DEFAULT_VALUE);
        v03.i(objG, "args: Array<Any?>?): Byt…       .or(DEFAULT_VALUE)");
        return (byte[]) objG;
    }

    public final Method getMethod() {
        return this.method;
    }

    public final RequestTransformers getTransformers() {
        return this.transformers;
    }
}
