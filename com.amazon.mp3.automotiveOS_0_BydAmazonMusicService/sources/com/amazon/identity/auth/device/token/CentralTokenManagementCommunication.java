package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e9;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.h9;
import com.amazon.identity.auth.device.r8;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.xd;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z4;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class CentralTokenManagementCommunication implements e9 {
    public static final String c = "com.amazon.identity.auth.device.token.CentralTokenManagementCommunication";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f589a;
    public final z4 b;

    /* JADX INFO: compiled from: DCP */
    public static class GetCookiesCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directed_id", str);
            bundle2.putString("domain", str2);
            bundle2.putBundle("options_key", bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "TokenManagement:GetCookies");
            h9.a(t5Var).a(string, string2, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetCookiesForActorCommand implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, String str3, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("account_id", str);
            bundle2.putString("actor_id", str2);
            bundle2.putString("domain", str3);
            bundle2.putBundle("options_key", bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "TokenManagement:GetCookiesForActor");
            h9.a(t5Var).a(string, string2, string3, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetTokenCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_TOKEN = "token_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directed_id", str);
            bundle2.putString(KEY_TOKEN, str2);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString(KEY_TOKEN);
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "TokenManagement:GetToken");
            h9.a(t5Var).b(string, string2, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetTokenForActorCommand implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_TOKEN_TYPE = "token_type";
        public static final String KEY_TOKEN_VALI_FAIL_CONTEXT = "failure_context";

        public static Bundle parametersToBundle(String str, String str2, String str3, String str4, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("account_id", str);
            bundle2.putString("actor_id", str2);
            bundle2.putString(KEY_TOKEN_TYPE, str3);
            bundle2.putString(KEY_TOKEN_VALI_FAIL_CONTEXT, str4);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString(KEY_TOKEN_TYPE);
            String string4 = bundle.getString(KEY_TOKEN_VALI_FAIL_CONTEXT);
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "TokenManagement:GetTokenForActor");
            h9.a(t5Var).a(t5Var, string, string2, string3, string4, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class InvalidateCookiesCommand implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directed_id";
        public static final String KEY_DOMAIN = "domain";
        public static final String KEY_OPTIONS = "options_key";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("directed_id", str);
            bundle2.putString("domain", str2);
            bundle2.putBundle("options_key", bundle);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directed_id");
            String string2 = bundle.getString("domain");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "TokenManagement:InvalidateCookies");
            h9.a(t5Var).c(string, string2, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class UpdateTokenCommand implements IPCCommand {
        public static Bundle parametersToBundle(String str, String str2, Bundle bundle, r8 r8Var, y5 y5Var) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("key_account_id", str);
            bundle2.putString("key_actor_id", str2);
            bundle2.putBundle("key_option", bundle);
            Bundle bundle3 = new Bundle();
            bundle3.putParcelable("key_remote_listener", new RemoteInternalListenerWrapper(r8Var));
            bundle2.putBundle("key_remote_listener_bundle", bundle3);
            y5Var.a(bundle2);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            Bundle bundle2 = bundle != null ? bundle : new Bundle();
            Bundle bundle3 = bundle2.getBundle("key_option") == null ? new Bundle() : bundle2.getBundle("key_option");
            bundle3.putInt("callingUid", bundle2.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle2, "TokenManagement:UpgradeToken");
            Bundle bundle4 = bundle.getBundle("key_remote_listener_bundle");
            RemoteInternalListenerWrapper remoteInternalListenerWrapper = bundle4 != null ? (RemoteInternalListenerWrapper) bundle4.getParcelable("key_remote_listener") : null;
            h9 h9VarA = h9.a(t5Var);
            String string = bundle2.getString("key_account_id");
            String string2 = bundle2.getString("key_actor_id");
            Context context = yd.f685a;
            h9VarA.a(string, string2, bundle3, new xd(null, y5VarA, false, callback, remoteInternalListenerWrapper, null, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED), remoteInternalListenerWrapper, y5VarA);
            return null;
        }
    }

    public CentralTokenManagementCommunication(Context context) {
        t5 t5VarA = t5.a(context);
        this.f589a = t5VarA;
        this.b = new z4(t5VarA, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value()));
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.b.a(GetCookiesCommand.class, GetCookiesCommand.parametersToBundle(str, str2, bundle, y5Var), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.b.a(GetTokenCommand.class, GetTokenCommand.parametersToBundle(str, str2, bundle), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> c(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(c);
        s2 s2Var = new s2(callback);
        this.b.a(InvalidateCookiesCommand.class, InvalidateCookiesCommand.parametersToBundle(str, str2, bundle, y5Var), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(c);
        s2 s2Var = new s2(callback);
        this.b.a(GetTokenForActorCommand.class, GetTokenForActorCommand.parametersToBundle(str, str2, str3, str4, bundle), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, y5 y5Var) {
        ga.a(c);
        s2 s2Var = new s2(callback);
        this.b.a(GetCookiesForActorCommand.class, GetCookiesForActorCommand.parametersToBundle(str, str2, str3, bundle, y5Var), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.e9
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, r8 r8Var, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.b.a(UpdateTokenCommand.class, UpdateTokenCommand.parametersToBundle(str, str2, bundle, r8Var, y5Var), s2Var);
        return s2Var;
    }
}
