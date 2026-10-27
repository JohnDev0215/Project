package com.amazon.identity.auth.device;

import android.util.Base64;
import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class l7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f419a = "com.amazon.identity.auth.device.l7";

    @Deprecated
    public String a(String str) throws BadPaddingException {
        c4 c4VarA = a();
        if (c4VarA == null || str == null) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(str, 0);
        if (bArrDecode == null) {
            throw new IllegalArgumentException("dataToDecrypt is null");
        }
        byte[] bArrA = c4.a(c4VarA.a(2, "AES", (AlgorithmParameterSpec) null, true), bArrDecode, 0, bArrDecode.length);
        if (bArrA == null) {
            return null;
        }
        try {
            return new String(bArrA, ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException unused) {
            Log.e(ga.a(f419a), "Cannot encode the given bytes as aUTF-8 string on this platform.");
            return null;
        }
    }

    @Deprecated
    public String b(String str) {
        byte[] bytes;
        byte[] bArrA;
        if (str == null) {
            bytes = null;
        } else {
            try {
                bytes = str.getBytes(ParserUtil.UTF_8);
            } catch (UnsupportedEncodingException unused) {
                Log.e(ga.a(f419a), "Cannot encode a string as UTF-8 on this platform.");
                bytes = null;
            }
        }
        c4 c4VarA = a();
        if (bytes == null || c4VarA == null) {
            return null;
        }
        try {
            bArrA = c4.a(c4VarA.a(1, "AES", (AlgorithmParameterSpec) null, true), bytes, 0, bytes.length);
        } catch (BadPaddingException e) {
            Log.e(ga.a(c4.b), "BadPaddingException in encryption, should never happen.", e);
            bArrA = null;
        }
        if (bArrA == null) {
            return null;
        }
        return Base64.encodeToString(bArrA, 0);
    }

    public abstract byte[] b();

    public final c4 a() {
        byte[] bArrB = b();
        if (bArrB == null) {
            Log.e(ga.a(f419a), "Aborting encrypt/decrypt because a valid cipher could not be created.");
            return null;
        }
        return new c4(bArrB);
    }
}
