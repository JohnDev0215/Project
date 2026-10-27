package com.spotify.cosmos.converters;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Type;
import p.cs;
import p.fo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MoshiLiteConverters implements Converter.Factory {
    private final o mMoshi;

    public MoshiLiteConverters(o oVar) {
        v03.j(oVar, "mMoshi");
        this.mMoshi = oVar;
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<?, byte[]> createRequestConverter() {
        return new Converter<Object, byte[]>() { // from class: com.spotify.cosmos.converters.MoshiLiteConverters.createRequestConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return true;
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public byte[] convert(Type type, Object obj) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                fo foVar = new fo();
                MoshiLiteConverters.this.mMoshi.d(type).toJson(m.L0(foVar), obj);
                return foVar.M0(foVar.e);
            }
        };
    }

    @Override // com.spotify.cosmos.cosmonaut.Converter.Factory
    public Converter<byte[], ?> createResponseConverter() {
        return new Converter<byte[], Object>() { // from class: com.spotify.cosmos.converters.MoshiLiteConverters.createResponseConverter.1
            @Override // com.spotify.cosmos.cosmonaut.Converter
            public boolean canHandle(Type type) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                return true;
            }

            @Override // com.spotify.cosmos.cosmonaut.Converter
            public Object convert(Type type, byte[] bArr) {
                v03.j(type, RxProductState.Keys.KEY_TYPE);
                f fVarD = MoshiLiteConverters.this.mMoshi.d(type);
                v03.h(bArr);
                return fVarD.fromJson(new String(bArr, cs.a));
            }
        };
    }
}
