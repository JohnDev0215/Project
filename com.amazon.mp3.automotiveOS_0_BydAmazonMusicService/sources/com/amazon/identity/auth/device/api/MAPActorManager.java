package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPActorManager {

    @FireOsSdk
    public static final int ERROR_ACCOUNT_NOT_EXIST = 2;

    @FireOsSdk
    public static final int ERROR_INVALID_INPUT = 5;

    @FireOsSdk
    public static final int ERROR_UNKNOWN_DB_FAILURE = 3;

    @FireOsSdk
    public static final int ERROR_UNKNOWN_IPC_ERROR = 4;

    @FireOsSdk
    public static final String KEY_ACCOUNT_ID = "account_id";

    @FireOsSdk
    public static final String KEY_ACTOR_ID = "actor_id";

    @FireOsSdk
    public static final String KEY_ACTOR_TYPE = "actor_type";

    @FireOsSdk
    public static final String KEY_DO_NOT_PASS_PACKAGE_NAME_TO_APS = "do_not_pass_package_name_to_aps";

    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "error_message";

    @FireOsSdk
    public static final String KEY_PROGRAM = "program";

    @FireOsSdk
    public static final String KEY_RESULT_ACTOR_TYPE = "result_actor_type";

    @FireOsSdk
    public static final String KEY_RESULT_CODE = "result_code";

    @FireOsSdk
    public static final String KEY_RETRYABLE = "retryable";

    @FireOsSdk
    public static final int SUCCESS = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f210a;

    /* JADX INFO: compiled from: DCP */
    public enum ActorSwitchMode {
        Normal,
        Force
    }

    @FireOsSdk
    public MAPActorManager(Context context) {
        MAPInit.getInstance(context).initialize();
        this.f210a = t5.a(context);
    }

    @FireOsSdk
    public MAPFuture<Bundle> enrollActorWithUI(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback) {
        y5 y5Var = new y5("EnrollActorWithUI");
        return e.a(this.f210a).a(context, str, str2, str3, bundle, yd.a(y5Var, y5Var.b(), callback), y5Var);
    }

    public Bundle getActor(String str, Bundle bundle) {
        return e.a(this.f210a).a(str, bundle);
    }

    @FireOsSdk
    public Bundle getActorForMapping(String str, String str2) {
        return e.a(this.f210a).a(str, str2);
    }

    @FireOsSdk
    public Bundle getCurrentActor(String str) {
        return e.a(this.f210a).b(str);
    }

    @FireOsSdk
    public Bundle removeActorMapping(String str, String str2) {
        return e.a(this.f210a).c(str, str2);
    }

    @FireOsSdk
    public Bundle removeCurrentActor(String str) {
        return e.a(this.f210a).a(str);
    }

    @FireOsSdk
    public Bundle setActorMapping(String str, String str2, String str3) {
        return e.a(this.f210a).a(str, str2, str3);
    }

    @FireOsSdk
    public Bundle setCurrentActor(String str, String str2) {
        return e.a(this.f210a).b(str, str2);
    }

    @FireOsSdk
    public MAPFuture<Bundle> signUpAndEnrollActorWithUI(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback) {
        y5 y5Var = new y5("SignUpAndEnrollActorWithUI");
        return e.a(this.f210a).a(context, str, str2, bundle, yd.a(y5Var, y5Var.b(), callback), y5Var);
    }

    @FireOsSdk
    public MAPFuture<Bundle> switchActor(@NonNull ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("switchActor");
        return e.a(this.f210a).a(actorSwitchMode, actorInfo, this.f210a.getPackageName(), bundle, yd.a(y5Var, y5Var.b(), callback), y5Var);
    }
}
