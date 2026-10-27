package com.spotify.cosmos.converters;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ByteArrayConverters implements Converter.Factory {
    public static final Companion Companion = new Companion(null);
    private static final Converter<byte[], byte[]> PASS_THROUGH = new Converter<byte[], byte[]>() { // from class: com.spotify.cosmos.converters.ByteArrayConverters$Companion$PASS_THROUGH$1
        @Override // com.spotify.cosmos.cosmonaut.Converter
        public boolean canHandle(Type type) {
            v03.j(type, RxProductState.Keys.KEY_TYPE);
            return type instanceof GenericArrayType ? v03.b(((GenericArrayType) type).getGenericComponentType(), Byte.TYPE) : v03.b(type, byte[].class);
        }

        @Override // com.spotify.cosmos.cosmonaut.Converter
        public byte[] convert(Type type, byte[] bArr) {
            v03.j(type, RxProductState.Keys.KEY_TYPE);
            return bArr;
        }
    };

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<byte[], byte[]> createRequestConverter() {
        return PASS_THROUGH;
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<byte[], ?> createResponseConverter() {
        return PASS_THROUGH;
    }
}
