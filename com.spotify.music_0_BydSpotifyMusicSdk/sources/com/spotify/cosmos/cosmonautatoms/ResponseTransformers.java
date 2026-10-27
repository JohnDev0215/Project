package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.Converter;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.o;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.lu;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseTransformers {
    public static final Companion Companion = new Companion(null);
    private static final ResponseTransformer PASS_THROUGH = new ResponseTransformer() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers$Companion$PASS_THROUGH$1
        @Override // com.spotify.cosmos.cosmonautatoms.ResponseTransformer, io.reactivex.rxjava3.core.ObservableTransformer
        public ObservableSource<Object> apply(Observable<Response> observable) {
            v03.j(observable, "upstream");
            ObservableSource map = observable.map(new o() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers$Companion$PASS_THROUGH$1$apply$1
                @Override // io.reactivex.rxjava3.functions.o
                public final Object apply(Response response) {
                    v03.j(response, "response");
                    return response;
                }
            });
            v03.i(map, "upstream.map {\n         …esponse\n                }");
            return map;
        }
    };
    private final List<Converter<byte[], ?>> converters;
    private final List<Converter.Factory> factories;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Type getActualTypeArgument(Type type) {
            v03.j(type, "returnType");
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length != 1) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Type type2 = actualTypeArguments[0];
            v03.i(type2, "actualTypeArguments[0]");
            return type2;
        }

        public final ResponseTransformer getPASS_THROUGH() {
            return ResponseTransformers.PASS_THROUGH;
        }

        public final boolean isTypedResponse(Type type) {
            v03.j(type, "typeArgument");
            if (!(type instanceof ParameterizedType)) {
                return false;
            }
            Type rawType = ((ParameterizedType) type).getRawType();
            return (rawType instanceof Class) && TypedResponse.class.isAssignableFrom((Class) rawType);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseTransformers(List<? extends Converter.Factory> list) {
        v03.j(list, "factories");
        this.factories = list;
        ArrayList arrayList = new ArrayList(lu.W(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Converter.Factory) it.next()).createResponseConverter());
        }
        this.converters = arrayList;
    }

    public final ResponseTransformer create(Type type) {
        v03.j(type, "genericReturnType");
        if (!(type instanceof ParameterizedType)) {
            return PASS_THROUGH;
        }
        Companion companion = Companion;
        final Type actualTypeArgument = companion.getActualTypeArgument(type);
        if (Response.class.equals(actualTypeArgument)) {
            return PASS_THROUGH;
        }
        if (!companion.isTypedResponse(actualTypeArgument)) {
            final Converter<byte[], ?> converterFindConverter = findConverter(actualTypeArgument);
            return new ResponseTransformer() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers.create.2
                @Override // com.spotify.cosmos.cosmonautatoms.ResponseTransformer, io.reactivex.rxjava3.core.ObservableTransformer
                public ObservableSource<Object> apply(Observable<Response> observable) {
                    v03.j(observable, "upstream");
                    final Converter<byte[], ?> converter = converterFindConverter;
                    final Type type2 = actualTypeArgument;
                    ObservableSource map = observable.map(new o() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers$create$2$apply$1
                        @Override // io.reactivex.rxjava3.functions.o
                        public final Object apply(Response response) {
                            v03.j(response, "response");
                            Object objConvert = converter.convert(type2, response.getBody());
                            Type type3 = type2;
                            if (objConvert != null) {
                                return objConvert;
                            }
                            throw new IllegalStateException(("failed to decode '" + response + "' as " + type3).toString());
                        }
                    });
                    v03.i(map, "typeArgument = getActual…      }\n                }");
                    return map;
                }
            };
        }
        final Type actualTypeArgument2 = companion.getActualTypeArgument(actualTypeArgument);
        final Converter<byte[], ?> converterFindConverter2 = findConverter(actualTypeArgument2);
        return new ResponseTransformer() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers.create.1
            @Override // com.spotify.cosmos.cosmonautatoms.ResponseTransformer, io.reactivex.rxjava3.core.ObservableTransformer
            public ObservableSource<Object> apply(Observable<Response> observable) {
                v03.j(observable, "upstream");
                final Converter<byte[], ?> converter = converterFindConverter2;
                final Type type2 = actualTypeArgument2;
                ObservableSource map = observable.map(new o() { // from class: com.spotify.cosmos.cosmonautatoms.ResponseTransformers$create$1$apply$1
                    @Override // io.reactivex.rxjava3.functions.o
                    public final Object apply(Response response) {
                        v03.j(response, "response");
                        int status = response.getStatus();
                        String uri = response.getUri();
                        if (uri == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        Map<String, String> headers = response.getHeaders();
                        if (headers != null) {
                            return new TypedResponse(status, uri, headers, converter.convert(type2, response.getBody()));
                        }
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                });
                v03.i(map, "nestedType = getActualTy…  )\n                    }");
                return map;
            }
        };
    }

    public final Converter<byte[], ?> findConverter(Type type) {
        v03.j(type, "typeArgument");
        for (Converter<byte[], ?> converter : this.converters) {
            if (converter.canHandle(type)) {
                return converter;
            }
        }
        throw new IllegalArgumentException(type + " is not supported by ResponseTransformers");
    }

    public final List<Converter.Factory> getFactories() {
        return this.factories;
    }
}
