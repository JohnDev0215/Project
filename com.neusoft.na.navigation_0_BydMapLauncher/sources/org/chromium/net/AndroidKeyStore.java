package org.chromium.net;

import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("net::android")
public class AndroidKeyStore {
    private static final String TAG = "AndroidKeyStore";

    private static byte[] getRSAKeyModulus(PrivateKey privateKey) {
        if (privateKey instanceof RSAKey) {
            return ((RSAKey) privateKey).getModulus().toByteArray();
        }
        Log.w(TAG, "Not a RSAKey instance!", new Object[0]);
        return null;
    }

    private static byte[] getECKeyOrder(PrivateKey privateKey) {
        if (privateKey instanceof ECKey) {
            return ((ECKey) privateKey).getParams().getOrder().toByteArray();
        }
        Log.w(TAG, "Not an ECKey instance!", new Object[0]);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0024, code lost:
    
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] rawSignDigestWithPrivateKey(java.security.PrivateKey r5, byte[] r6) {
        /*
            r0 = 0
            java.lang.String r1 = r5.getAlgorithm()     // Catch: java.security.NoSuchAlgorithmException -> L23
            java.lang.String r2 = "RSA"
            boolean r2 = r2.equalsIgnoreCase(r1)     // Catch: java.security.NoSuchAlgorithmException -> L23
            if (r2 == 0) goto L14
            java.lang.String r1 = "NONEwithRSA"
            java.security.Signature r1 = java.security.Signature.getInstance(r1)     // Catch: java.security.NoSuchAlgorithmException -> L23
            goto L24
        L14:
            java.lang.String r2 = "EC"
            boolean r1 = r2.equalsIgnoreCase(r1)     // Catch: java.security.NoSuchAlgorithmException -> L23
            if (r1 == 0) goto L23
            java.lang.String r1 = "NONEwithECDSA"
            java.security.Signature r1 = java.security.Signature.getInstance(r1)     // Catch: java.security.NoSuchAlgorithmException -> L23
            goto L24
        L23:
            r1 = r0
        L24:
            r2 = 0
            java.lang.String r3 = "AndroidKeyStore"
            if (r1 != 0) goto L42
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported private key algorithm: "
            r6.<init>(r1)
            java.lang.String r5 = r5.getAlgorithm()
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r5 = r5.toString()
            java.lang.Object[] r6 = new java.lang.Object[r2]
            org.chromium.base.Log.e(r3, r5, r6)
            return r0
        L42:
            r1.initSign(r5)     // Catch: java.lang.Exception -> L4d
            r1.update(r6)     // Catch: java.lang.Exception -> L4d
            byte[] r5 = r1.sign()     // Catch: java.lang.Exception -> L4d
            return r5
        L4d:
            r6 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Exception while signing message with "
            r1.<init>(r4)
            java.lang.String r5 = r5.getAlgorithm()
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r1 = " private key: "
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.Object[] r6 = new java.lang.Object[r2]
            org.chromium.base.Log.e(r3, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidKeyStore.rawSignDigestWithPrivateKey(java.security.PrivateKey, byte[]):byte[]");
    }

    private static int getPrivateKeyType(PrivateKey privateKey) {
        String algorithm = privateKey.getAlgorithm();
        if ("RSA".equalsIgnoreCase(algorithm)) {
            return 0;
        }
        return "EC".equalsIgnoreCase(algorithm) ? 2 : 255;
    }

    private static Object getOpenSSLKeyForPrivateKey(PrivateKey privateKey) {
        if (privateKey == null) {
            Log.e(TAG, "privateKey == null", new Object[0]);
            return null;
        }
        if (!(privateKey instanceof RSAPrivateKey)) {
            Log.e(TAG, "does not implement RSAPrivateKey", new Object[0]);
            return null;
        }
        try {
            Class<?> cls = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLRSAPrivateKey");
            if (!cls.isInstance(privateKey)) {
                Log.e(TAG, "Private key is not an OpenSSLRSAPrivateKey instance, its class name is:" + privateKey.getClass().getCanonicalName(), new Object[0]);
                return null;
            }
            try {
                Method declaredMethod = cls.getDeclaredMethod("getOpenSSLKey", new Class[0]);
                declaredMethod.setAccessible(true);
                try {
                    Object objInvoke = declaredMethod.invoke(privateKey, new Object[0]);
                    if (objInvoke != null) {
                        return objInvoke;
                    }
                    Log.e(TAG, "getOpenSSLKey() returned null", new Object[0]);
                    return null;
                } finally {
                    declaredMethod.setAccessible(false);
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception while trying to retrieve system EVP_PKEY handle: " + e, new Object[0]);
                return null;
            }
        } catch (Exception e2) {
            Log.e(TAG, "Cannot find system OpenSSLRSAPrivateKey class: " + e2, new Object[0]);
            return null;
        }
    }

    private static long getOpenSSLHandleForPrivateKey(PrivateKey privateKey) {
        Object openSSLKeyForPrivateKey = getOpenSSLKeyForPrivateKey(privateKey);
        if (openSSLKeyForPrivateKey == null) {
            return 0L;
        }
        try {
            try {
                Method declaredMethod = openSSLKeyForPrivateKey.getClass().getDeclaredMethod("getPkeyContext", new Class[0]);
                declaredMethod.setAccessible(true);
                try {
                    long jLongValue = ((Number) declaredMethod.invoke(openSSLKeyForPrivateKey, new Object[0])).longValue();
                    declaredMethod.setAccessible(false);
                    if (jLongValue == 0) {
                        Log.e(TAG, "getPkeyContext() returned null", new Object[0]);
                    }
                    return jLongValue;
                } catch (Throwable th) {
                    declaredMethod.setAccessible(false);
                    throw th;
                }
            } catch (Exception e) {
                Log.e(TAG, "No getPkeyContext() method on OpenSSLKey member:" + e, new Object[0]);
                return 0L;
            }
        } catch (Exception e2) {
            Log.e(TAG, "Exception while trying to retrieve system EVP_PKEY handle: " + e2, new Object[0]);
            return 0L;
        }
    }

    private static Object getOpenSSLEngineForPrivateKey(PrivateKey privateKey) {
        try {
            Class<?> cls = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLEngine");
            Object openSSLKeyForPrivateKey = getOpenSSLKeyForPrivateKey(privateKey);
            if (openSSLKeyForPrivateKey == null) {
                return null;
            }
            try {
                try {
                    Method declaredMethod = openSSLKeyForPrivateKey.getClass().getDeclaredMethod("getEngine", new Class[0]);
                    declaredMethod.setAccessible(true);
                    try {
                        Object objInvoke = declaredMethod.invoke(openSSLKeyForPrivateKey, new Object[0]);
                        if (objInvoke == null) {
                            Log.e(TAG, "getEngine() returned null", new Object[0]);
                        }
                        if (cls.isInstance(objInvoke)) {
                            return objInvoke;
                        }
                        Log.e(TAG, "Engine is not an OpenSSLEngine instance, its class name is:" + objInvoke.getClass().getCanonicalName(), new Object[0]);
                        return null;
                    } finally {
                        declaredMethod.setAccessible(false);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception while trying to retrieve OpenSSLEngine object: " + e, new Object[0]);
                    return null;
                }
            } catch (Exception e2) {
                Log.e(TAG, "No getEngine() method on OpenSSLKey member:" + e2, new Object[0]);
                return null;
            }
        } catch (Exception e3) {
            Log.e(TAG, "Cannot find system OpenSSLEngine class: " + e3, new Object[0]);
            return null;
        }
    }
}
