package com.amazon.identity.auth.device.api;

import android.content.Context;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.z9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class AuthenticationMethodFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f199a;
    public final i5 b;
    public final String c;
    public String d;

    /* JADX INFO: renamed from: com.amazon.identity.auth.device.api.AuthenticationMethodFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: DCP */
    public static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f200a;

        static {
            AuthenticationType.values();
            int[] iArr = new int[4];
            f200a = iArr;
            try {
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f200a;
                AuthenticationType authenticationType = AuthenticationType.None;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = f200a;
                AuthenticationType authenticationType2 = AuthenticationType.None;
                iArr3[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = f200a;
                AuthenticationType authenticationType3 = AuthenticationType.None;
                iArr4[0] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @FireOsSdk
    public AuthenticationMethodFactory(Context context) {
        this(context, null);
    }

    public final boolean a() {
        if (t9.c(this.b.f382a) && !qe.i(this.b.f382a)) {
            return z9.d(this.f199a, this.d);
        }
        return false;
    }

    @FireOsSdk
    public AuthenticationMethod newAuthenticationMethod(AuthenticationType authenticationType) {
        AuthenticationMethod inProcessAdpAuthenticationMethod;
        if (authenticationType == null) {
            return null;
        }
        if (a()) {
            int iOrdinal = authenticationType.ordinal();
            return (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) ? new CentralDcpAuthenticationMethod(this.f199a, this.c, authenticationType) : new DefaultAuthenticationMethod(this.f199a, this.c);
        }
        int iOrdinal2 = authenticationType.ordinal();
        if (iOrdinal2 == 1 || iOrdinal2 == 2) {
            inProcessAdpAuthenticationMethod = new InProcessAdpAuthenticationMethod(this.f199a, this.c, this.d, authenticationType);
        } else {
            if (iOrdinal2 != 3) {
                return new DefaultAuthenticationMethod(this.f199a, this.c);
            }
            inProcessAdpAuthenticationMethod = new InProcessOauthAuthenticationMethod(this.f199a, this.c, this.d, authenticationType);
        }
        return inProcessAdpAuthenticationMethod;
    }

    @FireOsSdk
    public void setPackageName(String str) {
        this.d = str;
    }

    @FireOsSdk
    public AuthenticationMethodFactory(Context context, String str) {
        MAPInit.getInstance(context).initialize();
        t5 t5VarA = t5.a(context);
        this.f199a = t5VarA;
        this.c = str;
        this.d = t5VarA.getPackageName();
        this.b = (i5) t5VarA.getSystemService("sso_platform");
    }

    @FireOsSdk
    public AuthenticationMethod newAuthenticationMethod(String str) {
        AuthenticationType authenticationType = AuthenticationType.parse(str);
        if (authenticationType != null) {
            return newAuthenticationMethod(authenticationType);
        }
        if (a()) {
            return new CentralDcpAuthenticationMethod(this.f199a, this.c, str);
        }
        if ("BustedIdentityADPAuthenticator".equals(str)) {
            return new InProcessAdpAuthenticationMethod(this.f199a, this.c, this.d, str);
        }
        throw new UnsupportedOperationException(String.format("Authentication Type %s is not supported on this build", str));
    }
}
