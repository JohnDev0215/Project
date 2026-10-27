package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyProtection;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.h7;
import com.amazon.identity.auth.device.storage.KeystoreProvider;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w5;
import com.amazon.identity.auth.device.wa;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.android.gms.stats.CodePackage;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MobileAuthEncryptionKeyManager {
    public static final long c;
    public static final long d;
    public static final String e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f591a;
    public final w5 b;

    /* JADX INFO: compiled from: DCP */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f592a;
        public final String b;
        public final long c;
        public final long d;

        public a(String str, String str2, long j, long j2) {
            this.f592a = str;
            this.b = str2;
            this.c = j;
            this.d = j2;
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        c = wa.a(180L, timeUnit);
        d = wa.b(1L, timeUnit);
        e = MobileAuthEncryptionKeyManager.class.getName();
    }

    public MobileAuthEncryptionKeyManager(Context context) {
        t5 t5VarA = t5.a(context);
        this.f591a = t5VarA;
        this.b = (w5) t5VarA.getSystemService("dcp_system");
    }

    public final void a(KeystoreProvider keystoreProvider, f8 f8Var, a aVar, String str) throws KeyStoreException {
        byte[] bArrDecode = Base64.decode(aVar.f592a, 0);
        keystoreProvider.f552a.setEntry(keystoreProvider.b, new KeyStore.SecretKeyEntry(new SecretKeySpec(bArrDecode, 0, bArrDecode.length, "AES")), new KeyProtection.Builder(3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build());
        f8Var.a(a(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."), aVar.b);
        f8Var.a(a(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_VERSION, str, "."), aVar.c);
        f8Var.a(a(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_CREATION_TIME, str, "."), aVar.d);
    }

    /* JADX INFO: compiled from: DCP */
    public static final class MobileAuthEncryptionKeyManagerException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;

        public MobileAuthEncryptionKeyManagerException(MAPError mAPError, String str) {
            super(str);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public MAPError a() {
            return this.mError;
        }

        public String b() {
            return this.mErrorMessage;
        }

        public MobileAuthEncryptionKeyManagerException(MAPError mAPError, String str, Throwable th) {
            super(th.getMessage(), th);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }
    }

    public Bundle a(String str, y5 y5Var) throws MobileAuthEncryptionKeyManagerException {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            try {
                KeystoreProvider keystoreProvider = new KeystoreProvider(a("mobile_auth_storage", str, Attributes.PREDEFINED_ATTRIBUTE_PREFIX));
                f8 f8VarA = f8.a(this.f591a, "mobile_auth_storage");
                SecretKey secretKeyA = keystoreProvider.a();
                String strB = f8VarA.b(a(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."));
                if (secretKeyA != null && !e.g(strB)) {
                    y5Var.b("MOBILE_AUTH_GET_ENCRYPTION_KEY");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("value_key", secretKeyA);
                    bundle.putString(TokenKeys.Options.KEY_MOBILE_AUTH_ENCRYPTION_KEY_ID, strB);
                    return bundle;
                }
                a(str);
                throw null;
            } catch (KeystoreProvider.KeystoreProviderException e2) {
                String str2 = String.format("KeystoreProviderException encountered while fetching encryption key. %s", e2.a());
                Log.e(ga.a(e), str2, e2);
                yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:KeystoreProviderException", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, str2, e2);
            } catch (MobileAuthEncryptionKeyManagerException e3) {
                throw e3;
            } catch (Exception e4) {
                String str3 = String.format("Exception encountered while fetching encryption key. %s", e4.getMessage());
                Log.e(ga.a(e), str3, e4);
                yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:Exception", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, str3, e4);
            }
        }
        String str4 = String.format("Currently GetMobileAuthEncryptionKey operation is not supported in %d version of Android.", Integer.valueOf(i));
        Log.e(ga.a(e), str4);
        yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:UnsupportedOperation", new String[0]);
        throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.UNSUPPORTED_OPERATION, str4);
    }

    public boolean a(String str, String str2, y5 y5Var) throws MobileAuthEncryptionKeyManagerException {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            try {
                KeystoreProvider keystoreProvider = new KeystoreProvider(a("mobile_auth_storage", str, Attributes.PREDEFINED_ATTRIBUTE_PREFIX));
                f8 f8VarA = f8.a(this.f591a, "mobile_auth_storage");
                SecretKey secretKeyA = keystoreProvider.a();
                String strB = f8VarA.b(a(AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER, str, "."));
                if (secretKeyA != null && !e.g(strB) && !a(f8VarA, str)) {
                    return false;
                }
                a(keystoreProvider, f8VarA, a(str2, strB, str, y5Var), str);
                y5Var.b("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY");
                return true;
            } catch (KeystoreProvider.KeystoreProviderException e2) {
                String str3 = String.format("KeystoreProviderException encountered while creating or updating encryption key. %s", e2.a());
                Log.e(ga.a(e), str3, e2);
                yd.b("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:KeystoreProviderException", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, str3, e2);
            } catch (JSONException e3) {
                String str4 = String.format("JSONException encountered while parsing MobileAuthEncryptionKey response. %s", e3.getMessage());
                Log.e(ga.a(e), str4, e3);
                yd.b("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:JSONException", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INVALID_RESPONSE, str4, e3);
            } catch (Exception e4) {
                String str5 = String.format("Exception encountered while creating or updating encryption key. %s", e4.getMessage());
                Log.e(ga.a(e), str5, e4);
                yd.b("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:Exception", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, str5, e4);
            }
        }
        String str6 = String.format("Currently UpsertMobileAuthEncryptionKey operation is not supported in %d version of Android.", Integer.valueOf(i));
        Log.e(ga.a(e), str6);
        yd.b("MOBILE_AUTH_UPSERT_ENCRYPTION_KEY:UnsupportedOperation", new String[0]);
        throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.UNSUPPORTED_OPERATION, str6);
    }

    public final void a(String str) throws MobileAuthEncryptionKeyManagerException {
        if (this.f591a.a() != null) {
            if (this.f591a.a().a(str)) {
                Log.e(ga.a(e), "Null/Invalid encryption key or key identifier received.");
                yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:KeyNotFoundException", new String[0]);
                throw new MobileAuthEncryptionKeyManagerException(MAPError.AccountError.ACCOUNT_ENCRYPTION_KEY_NOT_FOUND, "Null/Invalid encryption key or key identifier received.");
            }
            Log.e(ga.a(e), "Account already deregistered. So, no encryption key or key identifier received.");
            yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:AccountDeregistered", new String[0]);
            throw new MobileAuthEncryptionKeyManagerException(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Account already deregistered. So, no encryption key or key identifier received.");
        }
        Log.e(ga.a(e), "MAP data storage is null/invalid.");
        yd.b("MOBILE_AUTH_GET_ENCRYPTION_KEY:InvalidMAPDataStorage", new String[0]);
        throw new MobileAuthEncryptionKeyManagerException(MAPError.CommonError.INTERNAL_ERROR, "MAP data storage is null/invalid.");
    }

    public final boolean a(f8 f8Var, String str) {
        long jA = f8Var.a(String.format("%s.%s", AccountConstants.TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_CREATION_TIME, str)) + c + d;
        this.b.getClass();
        return jA <= System.currentTimeMillis();
    }

    public final String a(String str, String str2, String str3) {
        return String.format("%s%s%s", str, str3, str2);
    }

    public final a a(String str, String str2, String str3, y5 y5Var) throws JSONException {
        JSONObject jSONObject = new h7(this.f591a, str3, str, str2).c(y5Var).f312a;
        return new a(jSONObject.getString("encryptionKey"), jSONObject.getString("keyIdentifier"), Long.parseLong(jSONObject.getJSONObject("keyMetadata").getString("keyVersion")), Long.parseLong(jSONObject.getJSONObject("keyMetadata").getString("creationTime")));
    }
}
