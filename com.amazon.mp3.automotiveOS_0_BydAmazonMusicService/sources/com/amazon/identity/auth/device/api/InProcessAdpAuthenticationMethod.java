package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.cb;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ea;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.wa;
import com.amazonaws.util.DateUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class InProcessAdpAuthenticationMethod extends AuthenticationMethod implements ADPCorpusSigningAuthenticationMethod {
    public static final long h = wa.c(2, TimeUnit.MILLISECONDS);
    public static final byte[] i = "\n".getBytes();
    public final TokenManagement f;
    public final String g;

    public InProcessAdpAuthenticationMethod(Context context, String str, String str2, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
        this.f = (TokenManagement) this.b.getSystemService("dcp_token_mangement");
        this.g = str2;
    }

    public final String a(Uri uri, String str, Map map, byte[] bArr, String str2) throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        String currentTimestamp = getCurrentTimestamp();
        byte[] bArrA = a(uri, str, bArr, currentTimestamp, str2);
        if (bArrA == null || currentTimestamp == null) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Could not sign request because we were unable to create a corpus");
            return null;
        }
        String strA = a(bArrA);
        if (strA == null) {
            return null;
        }
        return String.format("%s:%s", strA, currentTimestamp);
    }

    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ignore.platform.restrictions", true);
        return bundle;
    }

    public final boolean d() {
        return AuthenticationType.DeviceAuthenticator.getValue().equals(this.d);
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    public MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, s2 s2Var) throws IOException {
        try {
            String value = this.f.getValue(this.c, TokenKeys.getAdpTokenKeyForPackage(this.g), c(), h);
            if (value == null) {
                AuthenticationMethod.a(s2Var, 2, "Unable to sign request. Could not retrieve ADP token for account", null);
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Unable to sign request. Could not retrieve ADP token for account");
                return s2Var;
            }
            String strA = a(uri, str, map, bArr, value);
            if (strA == null) {
                AuthenticationMethod.a(s2Var, 3, "Unable to sign request. Could not generate ADP signature. Please check provided parameters.", null);
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Unable to sign request. Could not generate ADP signature.");
                return s2Var;
            }
            Bundle bundle = new Bundle();
            cb.a(bundle, d() ? "X-ADP-Request-Digest" : "x-adp-signature", strA);
            cb.a(bundle, d() ? "X-ADP-Authentication-Token" : "x-adp-token", value);
            String str2 = d() ? null : "x-adp-alg";
            if (str2 != null) {
                cb.a(bundle, str2, d() ? null : "SHA256WithRSA:1.0");
            }
            if (s2Var != null) {
                s2Var.onSuccess(bundle);
            }
            return s2Var;
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            d7 d7VarA = d7.a(e);
            if (d7VarA != null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP token failed and return recover bundle");
                AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed because of callback error. Error Bundle: " + e.c(errorBundle), d7VarA.c());
                throw new AuthenticatedURLConnection.AccountNeedsRecoveryException("Database is corrupted", d7VarA.b());
            }
            AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed because of callback error. Error Bundle: " + e.c(errorBundle), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP Token failed because of callback error. Error Bundle: " + e.c(errorBundle));
            return s2Var;
        } catch (BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException e2) {
            d7 d7VarA2 = e2.a();
            if (d7VarA2 != null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP token failed. Return recover bundle");
                AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed bacause of BackwardsCompatibleDataStorageException", d7VarA2.c());
            }
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP Token failed because of BackwardsCompatibleDataStorageException.");
            return s2Var;
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e3.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP Token failed because of InterruptedException. This can happen if the caller kills the thread or asnc task that is calling MAP's api. Exception message: " + e3.getMessage(), e3);
            return s2Var;
        } catch (ExecutionException e4) {
            AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e4.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP Token failed because of ExecutionException. This can happen when the thread or task was aborted. Exception message: " + e4.getMessage(), e4);
            return s2Var;
        } catch (TimeoutException e5) {
            AuthenticationMethod.a(s2Var, 6, "Getting ADP Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e5.getMessage(), null);
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting ADP Token failed because of TimeoutException. This happens when the timeout passed into the future object occurs. Exception message: " + e5.getMessage(), e5);
            return s2Var;
        }
    }

    public String getCurrentTimestamp() {
        return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, Locale.US).format(new Date());
    }

    @Override // com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod
    public MAPFuture<Bundle> signCorpus(final byte[] bArr, Bundle bundle, Callback callback) {
        final s2 s2Var = new s2(callback);
        if (bArr == null) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Cannot sign a null corpus.");
            e.a(s2Var, 3, "Cannot sign a null corpus.");
            return s2Var;
        }
        if (TextUtils.isEmpty(this.c) || !this.f198a.isAccountRegistered(this.c)) {
            e.a(s2Var, 2, "The account is not valid or no longer registered.");
            return s2Var;
        }
        this.f.getToken(this.c, TokenKeys.getAdpTokenKeyForPackage(this.g), c(), new Callback() { // from class: com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle2) {
                byte[] bArr2 = InProcessAdpAuthenticationMethod.i;
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Unknown error during signCorpus execution.");
                s2 s2Var2 = s2Var;
                Bundle bundle3 = new Bundle();
                bundle3.putInt("error_code_key", 5);
                bundle3.putString("error_message_key", "Unknown error during signCorpus execution.");
                if (bundle2 != null) {
                    bundle3.putAll(bundle2);
                }
                s2Var2.onError(bundle3);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle2) {
                String strA;
                String string = bundle2.getString("value_key");
                try {
                    strA = InProcessAdpAuthenticationMethod.this.a(bArr);
                } catch (BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException e) {
                    d7 d7VarA = e.a();
                    if (d7VarA != null) {
                        s2 s2Var2 = s2Var;
                        Bundle bundleC = d7VarA.c();
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("error_code_key", 5);
                        bundle3.putString("error_message_key", "The account db is corrupted");
                        bundle3.putAll(bundleC);
                        s2Var2.onError(bundle3);
                        return;
                    }
                    strA = null;
                }
                if (string == null || strA == null) {
                    e.a(s2Var, 2, "The account is not valid or no longer registered.");
                    return;
                }
                Bundle bundle4 = new Bundle();
                bundle4.putString(ADPCorpusSigningAuthenticationMethod.KEY_ADP_AUTH_SIGNATURE, strA);
                bundle4.putString("adp_token", string);
                s2Var.onSuccess(bundle4);
            }
        });
        return s2Var;
    }

    public InProcessAdpAuthenticationMethod(Context context, String str, String str2, String str3) {
        super(context, str, str3);
        this.f = (TokenManagement) this.b.getSystemService("dcp_token_mangement");
        this.g = str2;
    }

    public String a(byte[] bArr) throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        PrivateKey privateKeyB;
        byte[] bArrDoFinal;
        try {
            privateKeyB = ea.b(this.f.getValue(this.c, TokenKeys.getPrivateKeyKeyForPackage(this.g), c(), h));
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            Bundle bundle = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context");
            if (bundle == null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting private key failed because of callback error. Error Bundle: " + e.c(errorBundle));
                privateKeyB = null;
            } else {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting private key failed because of BackwardsCompatibleDataStorageException");
                throw new BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException(d7.a(bundle));
            }
        } catch (InterruptedException e2) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting private key failed because of InterruptedException: " + e2.getMessage());
            privateKeyB = null;
        } catch (InvalidKeySpecException e3) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Could not sign request because there was an invalid key", e3);
            privateKeyB = null;
        } catch (ExecutionException e4) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting private key failed because of ExecutionException: " + e4.getMessage());
            privateKeyB = null;
        } catch (TimeoutException e5) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Getting private key failed because of TimeoutException: " + e5.getMessage());
            privateKeyB = null;
        }
        if (privateKeyB == null) {
            return null;
        }
        if (d()) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(bArr);
                byte[] bArrDigest = messageDigest.digest();
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, privateKeyB);
                cipher.update(bArrDigest);
                bArrDoFinal = cipher.doFinal();
            } catch (InvalidKeyException e6) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Signing request with old auth failed because of InvalidKeyException: " + e6.getMessage());
                bArrDoFinal = null;
            } catch (NoSuchAlgorithmException e7) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Signing request with old auth failed because of NoSuchAlgorithmException: " + e7.getMessage());
                bArrDoFinal = null;
            } catch (BadPaddingException e8) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Signing request with old auth failed because of BadPaddingException: " + e8.getMessage());
                bArrDoFinal = null;
            } catch (IllegalBlockSizeException e9) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Signing request with old auth failed because of IllegalBlockSizeException: " + e9.getMessage());
                bArrDoFinal = null;
            } catch (NoSuchPaddingException e10) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Signing request with old auth failed because of NoSuchPaddingException: " + e10.getMessage());
                bArrDoFinal = null;
            }
        } else {
            try {
                Signature signature = Signature.getInstance("SHA256WithRSA");
                signature.initSign(privateKeyB);
                signature.update(bArr);
                bArrDoFinal = signature.sign();
            } catch (InvalidKeyException e11) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Request signing failed because it was given an invalid key", e11);
                bArrDoFinal = null;
            } catch (NoSuchAlgorithmException e12) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Request signing failed because of No such algorithm found.", e12);
                bArrDoFinal = null;
            } catch (SignatureException e13) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Request signing failed because there was a problem with the signature", e13);
                bArrDoFinal = null;
            }
        }
        if (bArrDoFinal != null) {
            return new String(Base64.encode(bArrDoFinal, 2));
        }
        return null;
    }

    public final byte[] a(Uri uri, String str, byte[] bArr, String str2, String str3) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (str == null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "No verb specified. Cannot create corpus");
                return null;
            }
            String strA = a(uri);
            if (strA == null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "No path specified. Cannot create corpus");
                return null;
            }
            if ("BustedIdentityADPAuthenticator".equals(this.d)) {
                bArr = new byte[0];
            } else if (bArr == null) {
                bArr = new byte[0];
            }
            if (str3 == null) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Unable to retrieve ADP token for given account. Cannot generate corpus.");
                return null;
            }
            byteArrayOutputStream.write(str.getBytes());
            byte[] bArr2 = i;
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(strA.getBytes());
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(str2.getBytes());
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(str3.getBytes());
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.InProcessAdpAuthenticationMethod"), "Could not construct a corpus because an IOException occured", e);
            return null;
        }
    }

    public final String a(Uri uri) {
        if (uri == null) {
            return null;
        }
        if ("BustedIdentityADPAuthenticator".equals(this.d)) {
            return uri.toString();
        }
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null) {
            encodedPath = "";
        }
        if (!encodedPath.startsWith("/")) {
            encodedPath = "/" + encodedPath;
        }
        String encodedQuery = uri.getEncodedQuery();
        if (TextUtils.isEmpty(encodedQuery)) {
            return encodedPath;
        }
        return encodedPath + "?" + encodedQuery;
    }
}
