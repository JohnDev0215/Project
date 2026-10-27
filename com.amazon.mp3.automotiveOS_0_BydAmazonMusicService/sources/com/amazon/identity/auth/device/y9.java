package com.amazon.identity.auth.device;

import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class y9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, Boolean> f680a = new ConcurrentHashMap<>();
    public static volatile boolean b = false;

    public static void a(String str) {
        if (b) {
            String stackTraceString = Log.getStackTraceString(new Throwable());
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(stackTraceString.getBytes());
                Boolean boolPut = f680a.put(Base64.encodeToString(messageDigest.digest(), 0), Boolean.TRUE);
                ga.a("MAP_OUT_BINDER");
                if (boolPut == null || !boolPut.booleanValue()) {
                    ga.a("HASH_STACK_TRACE");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
