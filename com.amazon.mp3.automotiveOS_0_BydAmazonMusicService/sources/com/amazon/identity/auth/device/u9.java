package com.amazon.identity.auth.device;

import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class u9 {
    public static final SecureRandom d = new SecureRandom();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f613a;
    public String b;
    public String c;

    public void a() {
        try {
            byte[] bArr = new byte[32];
            d.nextBytes(bArr);
            String strEncodeToString = Base64.encodeToString(bArr, 11);
            this.f613a = strEncodeToString;
            this.b = "SHA-256";
            this.c = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(strEncodeToString.getBytes()), 11);
            ga.a("CodeChallengeUtil");
        } catch (NoSuchAlgorithmException e) {
            Log.e(ga.a("CodeChallengeUtil"), "Your JRE does not support the required SHA-256 algorithm.", e);
        }
    }
}
