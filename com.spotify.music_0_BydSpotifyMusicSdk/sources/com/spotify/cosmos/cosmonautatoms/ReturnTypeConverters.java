package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public enum ReturnTypeConverters implements ReturnTypeConverter {
    OBSERVABLE { // from class: com.spotify.cosmos.cosmonautatoms.ReturnTypeConverters.OBSERVABLE
        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public Object convert(ResponseTransformer responseTransformer, Observable<Response> observable) {
            v03.j(responseTransformer, "converter");
            v03.j(observable, "source");
            ObservableSource observableSourceCompose = observable.compose(responseTransformer);
            v03.i(observableSourceCompose, "source.compose(converter)");
            return observableSourceCompose;
        }

        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public boolean isSupported(Type type, String str) {
            v03.j(type, "returnType");
            v03.j(str, "action");
            Companion companion = ReturnTypeConverters.Companion;
            return companion.isSub(str) && companion.isGenericTypeOf(type, Observable.class);
        }
    },
    SINGLE { // from class: com.spotify.cosmos.cosmonautatoms.ReturnTypeConverters.SINGLE
        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public Object convert(ResponseTransformer responseTransformer, Observable<Response> observable) {
            v03.j(responseTransformer, "converter");
            v03.j(observable, "source");
            Single singleSingleOrError = observable.compose(responseTransformer).singleOrError();
            v03.i(singleSingleOrError, "source.compose(converter).singleOrError()");
            return singleSingleOrError;
        }

        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public boolean isSupported(Type type, String str) {
            v03.j(type, "returnType");
            v03.j(str, "action");
            Companion companion = ReturnTypeConverters.Companion;
            return !companion.isSub(str) && companion.isGenericTypeOf(type, Single.class);
        }
    },
    COMPLETABLE { // from class: com.spotify.cosmos.cosmonautatoms.ReturnTypeConverters.COMPLETABLE
        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public Object convert(ResponseTransformer responseTransformer, Observable<Response> observable) {
            v03.j(responseTransformer, "converter");
            v03.j(observable, "source");
            Completable completableIgnoreElements = observable.ignoreElements();
            v03.i(completableIgnoreElements, "source.ignoreElements()");
            return completableIgnoreElements;
        }

        @Override // com.spotify.cosmos.cosmonautatoms.ReturnTypeConverter
        public boolean isSupported(Type type, String str) {
            v03.j(type, "returnType");
            v03.j(str, "action");
            Companion companion = ReturnTypeConverters.Companion;
            return (companion.isSub(str) || companion.isGet(str) || !type.equals(Completable.class)) ? false : true;
        }
    };

    public static final Companion Companion = new Companion(null);
    private static final ReturnTypeConverters[] VALUES = values();

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ReturnTypeConverter find(Method method) {
            v03.j(method, "method");
            String strFindAction = ReflectionUtil.Companion.findAction(method);
            Type genericReturnType = method.getGenericReturnType();
            for (ReturnTypeConverters returnTypeConverters : ReturnTypeConverters.VALUES) {
                v03.i(genericReturnType, "returnType");
                if (returnTypeConverters.isSupported(genericReturnType, strFindAction)) {
                    return returnTypeConverters;
                }
            }
            throw new IllegalArgumentException(method.toString() + "'s (action, return type) pair is not supported: " + strFindAction + ", " + genericReturnType);
        }

        public final boolean isGenericTypeOf(Type type, Class<?> cls) {
            v03.j(type, "returnType");
            v03.j(cls, "clazz");
            return (type instanceof ParameterizedType) && ((ParameterizedType) type).getRawType().equals(cls);
        }

        public final boolean isGet(String str) {
            v03.j(str, "action");
            return Request.GET.equals(str);
        }

        public final boolean isSub(String str) {
            v03.j(str, "action");
            return Request.SUB.equals(str);
        }
    }

    /* synthetic */ ReturnTypeConverters(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final ReturnTypeConverter find(Method method) {
        return Companion.find(method);
    }
}
