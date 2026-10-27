package com.spotify.eventsender.utils.extensions;

import com.google.protobuf.ByteString;
import java.util.Arrays;
import p.ou;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final String a(ByteString byteString) {
        String upperCase = ou.h0(byteString, "", null, null, new ry0() { // from class: com.spotify.eventsender.utils.extensions.StringExtensionsKt$toHexString$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                String str = String.format("%02x", Arrays.copyOf(new Object[]{(Byte) obj}, 1));
                v03.i(str, "format(this, *args)");
                return str;
            }
        }, 30).toUpperCase();
        v03.i(upperCase, "this as java.lang.String).toUpperCase()");
        return upperCase;
    }
}
