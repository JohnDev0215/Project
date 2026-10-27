package com.spotify.automotive.packagevalidator;

import java.util.Arrays;
import kotlin.jvm.internal.Lambda;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class SignatureBasedPackageValidator$Companion$getByteArrayAsString$1 extends Lambda implements ry0 {
    public static final SignatureBasedPackageValidator$Companion$getByteArrayAsString$1 c = new SignatureBasedPackageValidator$Companion$getByteArrayAsString$1();

    public SignatureBasedPackageValidator$Companion$getByteArrayAsString$1() {
        super(1);
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(((Number) obj).byteValue())}, 1));
        v03.i(str, "format(format, *args)");
        return str;
    }
}
