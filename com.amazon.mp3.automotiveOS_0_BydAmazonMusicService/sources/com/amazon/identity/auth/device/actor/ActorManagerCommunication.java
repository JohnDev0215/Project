package com.amazon.identity.auth.device.actor;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e2;
import com.amazon.identity.auth.device.f2;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z4;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ActorManagerCommunication implements f2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4 f192a;

    /* JADX INFO: compiled from: DCP */
    public static class EnrollActorWithUIAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_POLICY_HANDLE = "policy_handle";

        public static Bundle parametersToBundle(String str, String str2, String str3, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("account_id", str);
            bundle2.putString("actor_id", str2);
            bundle2.putString("policy_handle", str3);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("policy_handle");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "MAPActorManager:enrollActorWithUI");
            e2.a(t5Var).a(t5Var, string, string2, string3, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetActorForMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString("account", str);
            bundle.putString("actor_mapping", str2);
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return e2.a(t5Var).a(bundle.getString("account"), bundle.getString("actor_mapping"));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class RemoveActorMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString("account", str);
            bundle.putString("actor_mapping", str2);
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return e2.a(t5Var).c(bundle.getString("account"), bundle.getString("actor_mapping"));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SetActorMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR = "actor";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2, String str3) {
            Bundle bundle = new Bundle();
            bundle.putString("account", str);
            bundle.putString(KEY_ACTOR, str2);
            bundle.putString("actor_mapping", str3);
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return e2.a(t5Var).a(bundle.getString("account"), bundle.getString(KEY_ACTOR), bundle.getString("actor_mapping"));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SignUpAndEnrollActorWithUIAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_POLICY_HANDLE = "policy_handle";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("account_id", str);
            bundle2.putString("policy_handle", str2);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("policy_handle");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "MAPActorManager:signUpAndEnrollActorWithUI");
            e2.a(t5Var).a(t5Var, string, string2, bundle3, yd.a(y5VarA, (de) null, callback), y5VarA);
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SwitchActorAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_ACTOR_SWITCH_MODE = "actor_switch_mode";
        public static final String KEY_ACTOR_TYPE = "actor_type";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_PACAKGE_NAME = "package_name";
        public static final String KEY_PROGRAM = "program";

        public static Bundle parametersToBundle(MAPActorManager.ActorSwitchMode actorSwitchMode, ActorInfo actorInfo, String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(KEY_ACTOR_SWITCH_MODE, actorSwitchMode.name());
            bundle2.putString("account_id", actorInfo.getAccountDirectedId());
            bundle2.putString("actor_id", actorInfo.getActorDirectedId());
            bundle2.putString("actor_type", actorInfo.getSuggestedActorType());
            bundle2.putString("program", actorInfo.getProgram());
            bundle2.putString(KEY_PACAKGE_NAME, str);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            MAPActorManager.ActorSwitchMode actorSwitchModeValueOf = MAPActorManager.ActorSwitchMode.valueOf(bundle.getString(KEY_ACTOR_SWITCH_MODE));
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("program");
            String string4 = bundle.getString("actor_type");
            String string5 = bundle.getString(KEY_PACAKGE_NAME);
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putInt("callingUid", bundle.getInt("callingUid", -1));
            y5 y5VarA = y5.a(bundle, "MAPActorManager:switchActor");
            Callback callbackA = yd.a(y5VarA, (de) null, callback);
            e2.a(t5Var).a(actorSwitchModeValueOf, new ActorInfo(string3, string, string2, string4), string5, bundle2, callbackA, y5VarA);
            return null;
        }
    }

    public ActorManagerCommunication(t5 t5Var, z4 z4Var) {
        this.f192a = z4Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, String str2, String str3) {
        Bundle bundleA = this.f192a.a(SetActorMappingAction.class, SetActorMappingAction.parametersToBundle(str, str2, str3));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle b(String str, String str2) {
        Bundle bundleA = this.f192a.a(SetActorMappingAction.class, SetActorMappingAction.parametersToBundle(str, str2, "com.amazon.identity.auth.device.current.actor"));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle c(String str, String str2) {
        Bundle bundleA = this.f192a.a(RemoveActorMappingAction.class, RemoveActorMappingAction.parametersToBundle(str, str2));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, String str2) {
        Bundle bundleA = this.f192a.a(GetActorForMappingAction.class, GetActorForMappingAction.parametersToBundle(str, str2));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle b(String str) {
        Bundle bundleA = this.f192a.a(GetActorForMappingAction.class, GetActorForMappingAction.parametersToBundle(str, "com.amazon.identity.auth.device.current.actor"));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str) {
        Bundle bundleA = this.f192a.a(RemoveActorMappingAction.class, RemoveActorMappingAction.parametersToBundle(str, "com.amazon.identity.auth.device.current.actor"));
        a(bundleA);
        return bundleA;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, Bundle bundle) {
        return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.UNSUPPORTED_OPERATION, "This API can only be called on 3P devices", false);
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.f192a.a(SignUpAndEnrollActorWithUIAction.class, SignUpAndEnrollActorWithUIAction.parametersToBundle(str, str2, bundle), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.f192a.a(EnrollActorWithUIAction.class, EnrollActorWithUIAction.parametersToBundle(str, str2, str3, bundle), s2Var);
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, String str, Bundle bundle, Callback callback, y5 y5Var) {
        s2 s2Var = new s2(callback);
        this.f192a.a(SwitchActorAction.class, SwitchActorAction.parametersToBundle(actorSwitchMode, actorInfo, str, bundle), s2Var);
        return s2Var;
    }

    public final void a(Bundle bundle) {
        if (bundle.getInt("result_code") == 4) {
            String string = bundle.getString(MAPActorManager.KEY_ERROR_MESSAGE);
            bundle.putBoolean(MAPActorManager.KEY_RETRYABLE, !(TextUtils.equals(string, "Error parsing IPC request") || TextUtils.equals(string, "The given IPC information was not of a valid form")));
        }
    }
}
