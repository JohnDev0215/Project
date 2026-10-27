package com.spotify.cosmos.converters;

import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.ds1;
import p.i01;
import p.u4;
import p.v03;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class ProtoJavaliteConverters implements Converter.Factory {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isProtobufModel(Type type) {
            return (type instanceof Class) && wj1.class.isAssignableFrom((Class) type);
        }
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<?, byte[]> createRequestConverter() {
        return new Converter<Object, byte[]>() { // from class: com.spotify.cosmos.converters.ProtoJavaliteConverters.createRequestConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return ProtoJavaliteConverters.Companion.isProtobufModel(type);
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public byte[] convert(Type type, Object obj) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                wj1 wj1Var = (wj1) obj;
                v03.h(wj1Var);
                return wj1Var.toByteArray();
            }
        };
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<byte[], ?> createResponseConverter() {
        return new Converter<byte[], Object>() { // from class: com.spotify.cosmos.converters.ProtoJavaliteConverters.createResponseConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return ProtoJavaliteConverters.Companion.isProtobufModel(type);
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public Object convert(Type type, byte[] bArr) throws IOException {
                String strConcat;
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                Class cls = (Class) type;
                try {
                    Object objInvoke = cls.getDeclaredMethod("parser", new Class[0]).invoke(null, new Object[0]);
                    v03.g(objInvoke, "null cannot be cast to non-null type com.google.protobuf.Parser<com.google.protobuf.MessageLite>");
                    return ((i01) ((ds1) objInvoke)).b(bArr);
                } catch (InvalidProtocolBufferException e) {
                    if (bArr == null) {
                        strConcat = "Body is missing when parsing ".concat(cls.getName());
                    } else if (bArr.length == 0) {
                        strConcat = "Body is empty when parsing ".concat(cls.getName());
                    } else {
                        strConcat = "Invalid body when parsing " + cls.getName() + ", length=" + bArr.length + ", source=" + v03.R(bArr);
                    }
                    throw new IOException(strConcat, e);
                } catch (IllegalAccessException e2) {
                    throw new IOException(u4.d(cls, new StringBuilder("failed to access "), "#parser"), e2);
                } catch (NoSuchMethodException e3) {
                    throw new IOException(u4.d(cls, new StringBuilder("failed to find method "), "#parser"), e3);
                } catch (InvocationTargetException e4) {
                    throw new IOException(u4.d(cls, new StringBuilder("failed to invoke method "), "#parser"), e4);
                }
            }
        };
    }
}
