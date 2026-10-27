package com.spotify.cosmos.converters;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class StringConverters implements Converter.Factory {
    public static final Companion Companion = new Companion(null);
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<?, byte[]> createRequestConverter() {
        return new Converter<Object, byte[]>() { // from class: com.spotify.cosmos.converters.StringConverters.createRequestConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return v03.b(type, String.class);
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public byte[] convert(Type type, Object obj) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                String str = (String) obj;
                v03.h(str);
                Charset charset = StringConverters.DEFAULT_CHARSET;
                v03.i(charset, "DEFAULT_CHARSET");
                byte[] bytes = str.getBytes(charset);
                v03.i(bytes, "this as java.lang.String).getBytes(charset)");
                return bytes;
            }
        };
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<byte[], ?> createResponseConverter() {
        return new Converter<byte[], Object>() { // from class: com.spotify.cosmos.converters.StringConverters.createResponseConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return v03.b(type, String.class);
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public Object convert(Type type, byte[] bArr) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                v03.h(bArr);
                Charset charset = StringConverters.DEFAULT_CHARSET;
                v03.i(charset, "DEFAULT_CHARSET");
                return new String(bArr, charset);
            }
        };
    }
}
