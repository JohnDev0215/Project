package com.spotify.cosmos.cosmonautatoms;

import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.Converter;
import java.io.IOException;
import java.lang.reflect.Type;
import p.bz0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class BodyTransformation implements bz0 {
    private final Converter<?, byte[]> converter;
    private final int index;
    private final Type type;

    public BodyTransformation(int i, Type type, Converter<?, byte[]> converter) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(converter, "converter");
        this.index = i;
        this.type = type;
        this.converter = converter;
    }

    @Override // p.bz0
    public byte[] apply(Object[] objArr) {
        v03.j(objArr, "args");
        try {
            Converter<?, byte[]> converter = this.converter;
            v03.g(converter, "null cannot be cast to non-null type com.spotify.cosmos.cosmonaut.Converter<kotlin.Any, kotlin.ByteArray?>");
            byte[] bArrConvert = converter.convert(this.type, objArr[this.index]);
            if (bArrConvert != null) {
                return bArrConvert;
            }
            throw new IllegalStateException("Required value was null.".toString());
        } catch (IOException e) {
            Logger.c(e, "Unexpected IO exception", new Object[0]);
            return BodyAtom.Companion.getDEFAULT_VALUE$src_main_java_com_spotify_cosmos_cosmonautatoms_cosmonautatoms();
        }
    }
}
