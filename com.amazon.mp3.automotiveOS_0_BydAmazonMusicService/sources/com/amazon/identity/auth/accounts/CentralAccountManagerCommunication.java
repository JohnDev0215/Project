package com.amazon.identity.auth.accounts;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import com.amazon.identity.auth.device.d0;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.e0;
import com.amazon.identity.auth.device.e1;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.f1;
import com.amazon.identity.auth.device.fa;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.g.d;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.p9;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.r9;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.vc;
import com.amazon.identity.auth.device.y;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.y9;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z3;
import com.amazon.identity.auth.device.z4;
import com.amazon.identity.auth.device.z9;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class CentralAccountManagerCommunication implements f {
    public static final String g = "com.amazon.identity.auth.accounts.CentralAccountManagerCommunication";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f145a;
    public final p9 b;
    public final z4 c;
    public i5 d;
    public g e;
    public z3 f;

    /* JADX INFO: compiled from: DCP */
    public static class AuthenticateAccountAction implements IPCCommand {
        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            g gVarB = g.b(t5Var);
            y5 y5VarA = y5.a(bundle, "AuthenticateAccount:");
            gVarB.a(bundle, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class DeregisterAccountAction implements IPCCommand {
        public static final String KEY_DEREG_DATA = "deregData";
        public static final String KEY_DIRECTED_ID = "directedId";

        public static Bundle parametersToBundle(String str, y5 y5Var, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directedId", str);
            y5Var.a(bundle2);
            bundle2.putBundle("deregData", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            Bundle bundle2 = bundle.getBundle("deregData");
            g gVarB = g.b(t5Var);
            y5 y5VarA = y5.a(bundle, "DeregisterAccount");
            gVarB.a(string, yd.a(callback, y5VarA, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), y5VarA, bundle2);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class DeregisterDeviceAction implements IPCCommand {
        public static final String KEY_DEREG_DATA = "deregData";

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            Bundle bundle2 = (bundle != null ? bundle : new Bundle()).getBundle("deregData");
            g gVarB = g.b(t5Var);
            y5 y5VarA = y5.a(bundle, "DeregisterDevice");
            gVarB.a(yd.a(callback, y5VarA, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), y5VarA, bundle2);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetAccountAction implements IPCCommand {
        public static final String KEY_PACKAGE_NAME = "packageName";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public static Bundle parametersToBundle(String str) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_PACKAGE_NAME, str);
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString(KEY_PACKAGE_NAME);
            g gVarB = g.b(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", gVarB.b(string));
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetAccountsAction implements IPCCommand {
        public static final String KEY_VALUES = "values";

        public static Set<String> getResult(Bundle bundle) {
            String[] stringArray = bundle.getStringArray("values");
            HashSet hashSet = new HashSet();
            if (stringArray != null) {
                hashSet.addAll(Arrays.asList(stringArray));
            }
            return hashSet;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            g gVarB = g.b(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putStringArray("values", (String[]) gVarB.e.c().toArray(new String[0]));
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetPrimaryAccountAction implements IPCCommand {
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            g gVarB = g.b(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", gVarB.b());
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class IsAccountRegisteredAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("directed_id", str);
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            g gVarB = g.b(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", gVarB.a(string));
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class IsDeviceRegisteredAction implements IPCCommand {
        public static final String KEY_CALLING_APPLICATION_INFO_BUNDLE = "calling.app.info";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            g gVarB = g.b(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", gVarB.e.e());
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class RegisterAccountAction implements IPCCommand {
        public static final String KEY_REG_DATA = "regData";
        public static final String KEY_REG_TYPE = "regType";

        public static Bundle parametersToBundle(RegistrationType registrationType, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(KEY_REG_TYPE, registrationType.getName());
            bundle2.putBundle(KEY_REG_DATA, bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            RegistrationType registrationTypeFromName = RegistrationType.fromName(bundle.getString(KEY_REG_TYPE));
            Bundle bundle2 = bundle.getBundle(KEY_REG_DATA);
            g gVarB = g.b(t5Var);
            StringBuilder sb = new StringBuilder();
            sb.append("RegisterAccount:");
            Context context = yd.f685a;
            sb.append(registrationTypeFromName == null ? "NullRegType" : registrationTypeFromName.name());
            y5 y5VarA = y5.a(bundle, sb.toString());
            y5VarA.b("Count");
            gVarB.a(registrationTypeFromName, e.a(bundle2), yd.a(callback, y5VarA, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class RegisterChildApplication implements IPCCommand {
        public static final String KEY_DEVICE_TYPE = "device_type";
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directed_id", str);
            bundle2.putString(KEY_DEVICE_TYPE, str2);
            bundle2.putBundle("options", bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString(KEY_DEVICE_TYPE);
            Bundle bundle2 = bundle.getBundle("options");
            g gVarB = g.b(t5Var);
            y5 y5VarA = y5.a(bundle, "RegisterChildApplication");
            gVarB.a(string, string2, bundle2, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class RenameDeviceAction implements IPCCommand {
        public static final String KEY_DEVICE_NAME_ERROR = "deviceNameError";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_NEW_DEVICE_NAME = "newDeviceName";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directedId", str);
            bundle2.putString(KEY_NEW_DEVICE_NAME, str2);
            bundle2.putBundle("options", bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString(KEY_NEW_DEVICE_NAME);
            Bundle bundle2 = bundle.getBundle("options");
            g gVarB = g.b(t5Var);
            y5 y5VarA = y5.a(bundle, "RenameDevice");
            gVarB.b(string, string2, bundle2, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class UpdateCredentialsAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_OPTIONS = "options";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directedId", str);
            bundle2.putString("key", str2);
            bundle2.putBundle("options", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            g.b(t5Var).a(bundle.getString("directedId"), fa.a(bundle.getString("key")), bundle.getBundle("options"), callback, y5.a(bundle, "UpdateCredentials"));
            return null;
        }
    }

    public CentralAccountManagerCommunication(Context context, z4 z4Var) {
        t5 t5VarA = t5.a(context);
        this.f145a = t5VarA;
        this.b = (p9) t5VarA.getSystemService("dcp_account_manager");
        this.d = (i5) t5VarA.getSystemService("sso_platform");
        this.c = z4Var;
        this.f = t5VarA.b();
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(RegistrationType registrationType, Bundle bundle, Callback callback, y5 y5Var) {
        if (this.d.c() || this.d.a()) {
            bundle.remove("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary");
        }
        if (registrationType == RegistrationType.FROM_ADP_TOKEN) {
            d().b(bundle, callback, y5Var);
            return;
        }
        if (e()) {
            this.c.a(RegisterAccountAction.class, RegisterAccountAction.parametersToBundle(registrationType, bundle, y5Var), callback);
            return;
        }
        bundle.putString(AccountConstants.KEY_REGISTRATION_TYPE, registrationType.getName());
        if (registrationType.equals(RegistrationType.FROM_AUTH_TOKEN) && !this.f.a(Feature.RegistrationViaAuthToken)) {
            Log.e(ga.a(g), "Registration via auth token is not supported on this platform.");
            callback.onError(y.b(MAPError.CommonError.UNSUPPORTED_OPERATION, "Registration via auth token is not supported on this platform.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Registration via auth token is not supported on this platform."));
            return;
        }
        e1 e1Var = callback == null ? null : new e1(callback, this.f145a);
        p9 p9Var = this.b;
        p9Var.getClass();
        y9.a("addAccount");
        p9Var.b.addAccount(AccountConstants.AMAZON_ACCOUNT_TYPE, null, null, bundle, null, new p9.a(e1Var, yd.b("AccountManagerWrapper", "addAccount")), null);
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var) {
        d().b(activity, signinOption, e.a(bundle), callback, y5Var);
    }

    @Override // com.amazon.identity.auth.device.f
    public Set<String> c() {
        return e() ? GetAccountsAction.getResult(this.c.a(GetAccountsAction.class, (Bundle) null)) : d().e.c();
    }

    public final synchronized g d() {
        if (this.e == null) {
            this.e = g.b(this.f145a);
        }
        return this.e;
    }

    public final boolean e() {
        return qe.j(this.d.f382a);
    }

    @Override // com.amazon.identity.auth.device.f
    @Deprecated
    public void b(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var) {
        d().a((Context) activity, str, true, e.a(bundle), callback, y5Var);
    }

    @Override // com.amazon.identity.auth.device.f
    public String b() {
        if (e()) {
            return GetPrimaryAccountAction.getResult(this.c.a(GetPrimaryAccountAction.class, (Bundle) null));
        }
        return d().b();
    }

    @Override // com.amazon.identity.auth.device.f
    public String b(String str) {
        if (e()) {
            return GetAccountAction.getResult(this.c.a(GetAccountAction.class, GetAccountAction.parametersToBundle(str)));
        }
        return d().b(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        if (e()) {
            s2 s2Var = new s2(callback);
            this.c.a(RenameDeviceAction.class, RenameDeviceAction.parametersToBundle(str, str2, bundle, y5Var), s2Var);
            return s2Var;
        }
        return d().b(str, str2, bundle, callback, y5Var);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Bundle bundle, Callback callback, y5 y5Var) {
        if (e()) {
            y5Var.a(bundle);
            this.c.a(AuthenticateAccountAction.class, bundle, callback);
        } else {
            d().a(bundle, callback, y5Var);
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var) {
        d().a((Context) activity, signinOption, e.a(bundle), callback, y5Var);
    }

    public final MAPFuture<Bundle> a(Account account, String str, Bundle bundle, Activity activity, Callback callback, Handler handler) {
        e1 e1Var = callback == null ? null : new e1(callback, this.f145a);
        p9 p9Var = this.b;
        p9Var.getClass();
        y9.a("updateCredentials");
        return new f1(p9Var.b != null ? p9Var.b.updateCredentials(account, str, bundle, null, new p9.a(e1Var, yd.b("AccountManagerWrapper", "updateCredentials")), null) : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0123  */
    @Override // com.amazon.identity.auth.device.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.api.MAPFuture<android.os.Bundle> a(java.lang.String r11, java.lang.String r12, android.os.Bundle r13, com.amazon.identity.auth.device.api.Callback r14, com.amazon.identity.auth.device.y5 r15) {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.accounts.CentralAccountManagerCommunication.a(java.lang.String, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.y5):com.amazon.identity.auth.device.api.MAPFuture");
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, Callback callback, y5 y5Var, Bundle bundle) {
        if (e()) {
            s2 s2Var = new s2(callback);
            this.c.a(DeregisterAccountAction.class, DeregisterAccountAction.parametersToBundle(str, y5Var, bundle), s2Var);
            return s2Var;
        }
        Account accountA = r9.a(this.f145a, str);
        if (accountA == null) {
            Bundle bundleB = y.b(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Account given does not exist or was already deregistered", MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED.value(), "Account given does not exist or was already deregistered");
            s2 s2Var2 = new s2(callback);
            s2Var2.a(1, bundleB);
            return s2Var2;
        }
        return new d0(this, this.b.a(accountA, (AccountManagerCallback<Boolean>) (callback == null ? null : new e0(this, callback)), false));
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(Callback callback, y5 y5Var, Bundle bundle) {
        if (e()) {
            s2 s2Var = new s2(callback);
            Bundle bundle2 = new Bundle();
            y5Var.a(bundle2);
            bundle2.putBundle("deregData", bundle);
            this.c.a(DeregisterDeviceAction.class, bundle2, s2Var);
            return s2Var;
        }
        return a(((AmazonAccountManager) this.f145a.getSystemService("dcp_amazon_account_man")).b(), callback, y5Var, bundle);
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, fa faVar, Bundle bundle, Callback callback, y5 y5Var) {
        String str2;
        s2 s2Var = new s2(null);
        if (e()) {
            this.c.a(UpdateCredentialsAction.class, UpdateCredentialsAction.parametersToBundle(str, faVar.f315a, null), s2Var);
            return s2Var;
        }
        Account accountA = r9.a(this.f145a, str);
        if (accountA == null) {
            y.a(s2Var, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Account given does not exist or was already deregistered", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.value(), "Account given does not exist or was already deregistered", null);
            return s2Var;
        }
        String string = faVar.f315a;
        if (e()) {
            str2 = string;
        } else if (z9.d(this.f145a, faVar.b)) {
            str2 = "com.amazon.dcp.sso.token.devicedevicetype";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(faVar.b);
            stringBuffer.append(".tokens.");
            if (faVar.c.equals("com.amazon.dcp.sso.token.devicedevicetype")) {
                stringBuffer.append(RegisterChildApplication.KEY_DEVICE_TYPE);
            } else if (faVar.c.equals("com.amazon.dcp.sso.token.device.deviceserialname")) {
                stringBuffer.append("dsn");
            } else if (faVar.c.equals("com.amazon.dcp.sso.property.deviceemail")) {
                stringBuffer.append("email");
            } else if (faVar.c.equals("com.amazon.dcp.sso.property.devicename")) {
                stringBuffer.append(MAPAccountManager.KEY_DEVICE_NAME);
            } else if (faVar.c.equals("com.amazon.dcp.sso.property.username")) {
                stringBuffer.append("user_name");
            } else {
                string = faVar.f315a;
                str2 = string;
            }
            string = stringBuffer.toString();
            str2 = string;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("com.amazon.dcp.sso.AddAccount.options.URL", vc.a());
        return a(accountA, str2, bundle2, null, null, null);
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean a() {
        if (e()) {
            return IsDeviceRegisteredAction.getResult(this.c.a(IsDeviceRegisteredAction.class, (Bundle) null));
        }
        return d().e.e();
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean a(String str) {
        if (e()) {
            return IsAccountRegisteredAction.getResult(this.c.a(IsAccountRegisteredAction.class, IsAccountRegisteredAction.parametersToBundle(str)));
        }
        return d().e.c().contains(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, y5 y5Var) {
        if (!bundle2.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle2.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)) {
            d().a(context, bundle, bundle2, callback, y5Var);
        } else {
            a(RegistrationType.WITH_LINK_CODE, bundle2, callback, y5Var);
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var) {
        g gVarD = d();
        gVarD.getClass();
        va.f637a.execute(gVarD.new d(bundle, str, callback, y5Var, activity));
    }
}
