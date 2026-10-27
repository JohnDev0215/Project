package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.android.amazonprofile.AmazonProfileManager;
import com.amazon.android.amazonprofile.Profile;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.e7;
import com.amazon.identity.auth.device.f7;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class e2 implements f2 {
    public static e2 e;
    public static final Map<String, Integer> f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f288a;
    public final u7 b;
    public final AmazonAccountManager c;
    public final OAuthTokenManager d;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f289a;
        public final /* synthetic */ y5 b;
        public final /* synthetic */ String c;
        public final /* synthetic */ String d;
        public final /* synthetic */ Bundle e;
        public final /* synthetic */ s2 f;
        public final /* synthetic */ Context g;

        public a(String str, y5 y5Var, String str2, String str3, Bundle bundle, s2 s2Var, Context context) {
            this.f289a = str;
            this.b = y5Var;
            this.c = str2;
            this.d = str3;
            this.e = bundle;
            this.f = s2Var;
            this.g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                f7.a aVarC = new e7.b(e2.this.f288a, this.f289a, this.d, e2.this.a("com.amazon.dcp.sso.token.oauth.amazon.access_token", this.f289a, (String) null, (ActorInfo) null, this.b, (String) null), this.e, e2.a(e2.this, this.c, this.f289a, (String) null, this.b)).c(this.b);
                aVarC.a();
                g2 g2Var = new g2(e2.this.d);
                if (AuthEndpointErrorParser.a(aVarC.b)) {
                    g2Var.a(this.f, aVarC);
                    Log.e(ga.a("ActorManagerLogic"), "Fail to call Panda for signup and enroll actor");
                } else {
                    Log.i(ga.a("ActorManagerLogic"), "Handling success response for signUpAndEnrollActor call. Proceed to webview.");
                    g2Var.a(this.g, this.f289a, this.f, this.e, aVarC.f312a, this.b);
                }
            } catch (MAPCallbackErrorException e) {
                Log.e(ga.a("ActorManagerLogic"), String.format(Locale.ENGLISH, "Received an error when getting token or cookies before sending the signUpAndEnrollActorWithUI request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.getError().getErrorCode()), e.getError().getErrorMessage()));
                this.f.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(e.getError(), e.getErrorMessage(), true));
            } catch (IOException e2) {
                Log.e(ga.a("ActorManagerLogic"), "Received an IOException when parsing the signUpAndEnrollActorWithUI response.");
                this.f.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, e2.getMessage(), true));
            } catch (JSONException e3) {
                Log.e(ga.a("ActorManagerLogic"), "Received a JSONException when parsing the signUpAndEnrollActorWithUI response");
                this.f.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, e3.getMessage(), true));
            } catch (Exception e4) {
                Locale locale = Locale.ENGLISH;
                MAPError.CommonError commonError = MAPError.CommonError.INTERNAL_ERROR;
                Log.e(ga.a("ActorManagerLogic"), String.format(locale, "Received an exception before sending the signUpAndEnrollActorWithUI request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(commonError.getErrorCode()), e4.getMessage()));
                this.f.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(commonError, e4.getMessage(), true));
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f290a;
        public final /* synthetic */ String b;
        public final /* synthetic */ y5 c;
        public final /* synthetic */ String d;
        public final /* synthetic */ String e;
        public final /* synthetic */ Bundle f;
        public final /* synthetic */ s2 g;
        public final /* synthetic */ Context h;

        public b(String str, String str2, y5 y5Var, String str3, String str4, Bundle bundle, s2 s2Var, Context context) {
            this.f290a = str;
            this.b = str2;
            this.c = y5Var;
            this.d = str3;
            this.e = str4;
            this.f = bundle;
            this.g = s2Var;
            this.h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                f7.a aVarC = new e7.a(e2.this.f288a, this.f290a, this.e, e2.this.a("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token", this.f290a, this.b, (ActorInfo) null, this.c, (String) null), this.f, e2.a(e2.this, this.d, this.f290a, this.b, this.c)).c(this.c);
                aVarC.a();
                g2 g2Var = new g2(e2.this.d);
                if (AuthEndpointErrorParser.a(aVarC.b)) {
                    g2Var.a(this.g, aVarC);
                    Log.e(ga.a("ActorManagerLogic"), "Fail to call Panda for actor enroll call.");
                } else {
                    Log.i(ga.a("ActorManagerLogic"), "Handling success response for enroll actor call. Proceed to webview.");
                    g2Var.a(this.h, this.f290a, this.b, this.g, this.f, aVarC.f312a, this.c);
                }
            } catch (MAPCallbackErrorException e) {
                Log.e(ga.a("ActorManagerLogic"), String.format(Locale.ENGLISH, "Received an error when getting actor token or cookies before sending the enroll actor request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.getError().getErrorCode()), e.getError().getErrorMessage()));
                this.g.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(e.getError(), e.getErrorMessage(), true));
            } catch (IOException e2) {
                Log.e(ga.a("ActorManagerLogic"), "Received an IOException when parsing the enroll actor response.");
                this.g.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, e2.getMessage(), true));
            } catch (JSONException e3) {
                Log.e(ga.a("ActorManagerLogic"), "Received a JSONException when parsing the enroll actor response");
                this.g.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, e3.getMessage(), true));
            } catch (Exception e4) {
                Locale locale = Locale.ENGLISH;
                MAPError.CommonError commonError = MAPError.CommonError.INTERNAL_ERROR;
                Log.e(ga.a("ActorManagerLogic"), String.format(locale, "Received an exception before sending the enroll actor request. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(commonError.getErrorCode()), e4.getMessage()));
                this.g.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(commonError, e4.getMessage(), true));
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ActorInfo f291a;
        public final /* synthetic */ String b;
        public final /* synthetic */ y5 c;
        public final /* synthetic */ MAPActorManager.ActorSwitchMode d;
        public final /* synthetic */ s2 e;

        public c(ActorInfo actorInfo, String str, y5 y5Var, MAPActorManager.ActorSwitchMode actorSwitchMode, s2 s2Var) {
            this.f291a = actorInfo;
            this.b = str;
            this.c = y5Var;
            this.d = actorSwitchMode;
            this.e = s2Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = e2.a(e2.this, this.f291a.getAccountDirectedId(), this.f291a.getActorDirectedId(), this.f291a, this.b, this.c);
                if (TextUtils.isEmpty(strA)) {
                    if (!MAPActorManager.ActorSwitchMode.Force.equals(this.d)) {
                        Log.e(ga.a("ActorManagerLogic"), "MAP is not able to get a valid actor type for the switch actor call");
                        this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Actor type is null or unknown!", true));
                        return;
                    }
                    strA = this.f291a.getSuggestedActorType();
                }
                e2.a(e2.this, this.f291a, strA, this.e);
            } catch (MAPCallbackErrorException e) {
                Log.w(ga.a("ActorManagerLogic"), "Received MAPCallbackErrorException for getActorType, checking detailed error...", e);
                Bundle errorBundle = e.getErrorBundle();
                MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
                if (commonError.getErrorCode() != errorBundle.getInt(MAPError.KEY_ERROR_CODE)) {
                    this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(commonError, e.getErrorMessage(), true));
                    return;
                }
                String suggestedActorType = e2.this.e(this.f291a.getAccountDirectedId(), this.f291a.getActorDirectedId()).c;
                if (TextUtils.isEmpty(suggestedActorType)) {
                    Log.w(ga.a("ActorManagerLogic"), "No cached actor type. Fail the call if not in force switch mode.");
                    if (!MAPActorManager.ActorSwitchMode.Force.equals(this.d)) {
                        this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "No cached actor type, please retry", true));
                        return;
                    }
                    suggestedActorType = this.f291a.getSuggestedActorType();
                }
                e2.a(e2.this, this.f291a, suggestedActorType, this.e);
            } catch (TimeoutException unused) {
                Log.e(ga.a("ActorManagerLogic"), "Timeout waiting for actor token after 15s, please check your network status.");
                this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Timeout fetching actor type, please retry", true));
            } catch (Exception e2) {
                Log.e(ga.a("ActorManagerLogic"), "Received Exception for getActorType", e2);
                this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor type, please retry", true));
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ActorInfo f292a;
        public final /* synthetic */ String b;
        public final /* synthetic */ y5 c;
        public final /* synthetic */ MAPActorManager.ActorSwitchMode d;
        public final /* synthetic */ s2 e;
        public final /* synthetic */ String f;

        public d(ActorInfo actorInfo, String str, y5 y5Var, MAPActorManager.ActorSwitchMode actorSwitchMode, s2 s2Var, String str2) {
            this.f292a = actorInfo;
            this.b = str;
            this.c = y5Var;
            this.d = actorSwitchMode;
            this.e = s2Var;
            this.f = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = e2.a(e2.this, this.f292a.getAccountDirectedId(), this.f292a.getActorDirectedId(), this.f292a, this.b, this.c);
                if (TextUtils.isEmpty(strA)) {
                    if (!MAPActorManager.ActorSwitchMode.Force.equals(this.d)) {
                        Log.e(ga.a("ActorManagerLogic"), "MAP is not able to get a valid actor type for switch actor call. Received actor type: " + strA);
                        yd.b("1P_SWITCH_ACTOR_FAILED:CANNOT_FETCH_ACTOR_TYPE_NORMAL", new String[0]);
                        this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Actor type is null or unknown!", true));
                        return;
                    }
                    strA = this.f292a.getSuggestedActorType();
                }
                e2.a(e2.this, this.f292a, strA, this.f, this.e);
            } catch (MAPCallbackErrorException e) {
                Log.w(ga.a("ActorManagerLogic"), "Received MAPCallbackErrorException for getActorType, checking detailed error...", e);
                Bundle errorBundle = e.getErrorBundle();
                if (MAPError.CommonError.NETWORK_ERROR.getErrorCode() != errorBundle.getInt(MAPError.KEY_ERROR_CODE)) {
                    this.e.onError(e.getErrorBundle());
                    yd.b("1P_SWITCH_ACTOR_FAILED:MAP_CALLBACK_ERROR_WITH_CODE:" + errorBundle.getInt(MAPError.KEY_ERROR_CODE), new String[0]);
                    return;
                }
                String suggestedActorType = e2.this.e(this.f292a.getAccountDirectedId(), this.f292a.getActorDirectedId()).c;
                if (TextUtils.isEmpty(suggestedActorType)) {
                    Log.w(ga.a("ActorManagerLogic"), "No cached actor type. Fail the call if not in force switch mode.");
                    if (!MAPActorManager.ActorSwitchMode.Force.equals(this.d)) {
                        yd.b("1P_SWITCH_ACTOR_FAILED:CANNOT_FETCH_ACTOR_TYPE_AND_NO_CACHE", new String[0]);
                        this.e.onError(e.getErrorBundle());
                        return;
                    }
                    suggestedActorType = this.f292a.getSuggestedActorType();
                }
                e2.a(e2.this, this.f292a, suggestedActorType, this.f, this.e);
            } catch (TimeoutException unused) {
                Log.e(ga.a("ActorManagerLogic"), "Timeout waiting for actor token after 15s, please check your network status.");
                this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Timeout fetching actor type, please retry", true));
                yd.b("1P_SWITCH_ACTOR_FAILED:TIMEOUT_FETCHING_ACTOR_TOKEN", new String[0]);
            } catch (Exception e2) {
                Log.e(ga.a("ActorManagerLogic"), "Received Exception for getActorType", e2);
                this.e.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor type, please retry", true));
                yd.b("1P_SWITCH_ACTOR_FAILED:OTHER_EXCEPTION", new String[0]);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f293a;
        public final String b;
        public final String c;

        public e(String str, String str2, String str3) {
            this.f293a = str;
            this.c = str3;
            this.b = str2;
        }
    }

    static {
        HashMap map = new HashMap();
        f = map;
        map.put(ActorInfo.ACTOR_TYPE_ADULT, 1);
        map.put(ActorInfo.ACTOR_TYPE_CHILD, 2);
        map.put(ActorInfo.ACTOR_TYPE_TEEN, 3);
    }

    public e2(t5 t5Var) {
        this.f288a = t5Var;
        u7 u7VarA = t5Var.a();
        this.b = u7VarA;
        this.d = new OAuthTokenManager(t5Var);
        this.c = new AmazonAccountManager(u7VarA);
    }

    public static Bundle a(e2 e2Var, String str, String str2, String str3, y5 y5Var) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        e2Var.getClass();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, true);
        return (Bundle) ((s2) (TextUtils.isEmpty(str3) ? h9.a(e2Var.f288a).a(str2, str, bundle, (Callback) null, y5Var) : h9.a(e2Var.f288a).a(str2, str3, str, bundle, (Callback) null, y5Var))).get(15L, TimeUnit.SECONDS);
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle b(String str, String str2) {
        return a(str, str2, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle c(String str, String str2) {
        if (!a(str, str2)) {
            return a(5, "accountId/actorMapping cannot be null", false);
        }
        if (!this.c.a(str)) {
            Log.i(ga.a("ActorManagerLogic"), "The account doesn't exist in MAP. Remove actor mapping success.");
            return a();
        }
        try {
            u7 u7Var = this.b;
            u7Var.getClass();
            u7Var.d(str, "actor/" + str2, null);
            return a();
        } catch (Exception e2) {
            Log.e(ga.a("ActorManagerLogic"), "Exception happened when trying to remove actor mapping.", e2);
            return a(3, "Fail to insert into database.", true);
        }
    }

    public final String d(String str, String str2) {
        return "actor_info/" + str + "/" + str2;
    }

    public final e e(String str, String str2) {
        return new e(this.b.a(str, str2, AccountConstants.KEY_ACTOR_SUBTYPE), this.b.a(str, str2, AccountConstants.KEY_ACTOR_ENTITYTYPE), this.b.a(str, str2, AccountConstants.KEY_ACTOR_CONVERTEDTYPE));
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle b(String str) {
        return a(str, "com.amazon.identity.auth.device.current.actor");
    }

    public static String a(e2 e2Var, String str, String str2, ActorInfo actorInfo, String str3, y5 y5Var) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        e2Var.getClass();
        Log.i(ga.a("ActorManagerLogic"), "Fetching actor type from server side.");
        e2Var.a("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token", str, str2, actorInfo, y5Var, str3);
        return e2Var.e(str, str2).c;
    }

    public static void a(e2 e2Var, ActorInfo actorInfo, String str, Callback callback) {
        e2Var.getClass();
        String accountDirectedId = actorInfo.getAccountDirectedId();
        String program = actorInfo.getProgram();
        if (!f.containsKey(str)) {
            Log.e(ga.a("ActorManagerLogic"), "Unknown actor type: " + str);
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unknown actor type.", true));
        }
        try {
            f8 f8VarA = f8.a(e2Var.f288a, "actor_info_storage_" + accountDirectedId);
            f8VarA.a(e2Var.d(program, "actor_id"), actorInfo.getActorDirectedId());
            f8VarA.a(e2Var.d(program, "actor_type"), str);
            Bundle bundleA = e2Var.a();
            bundleA.putString(MAPActorManager.KEY_RESULT_ACTOR_TYPE, str);
            callback.onSuccess(bundleA);
        } catch (Exception e2) {
            Log.e(ga.a("ActorManagerLogic"), "Exception happened when trying to set actor mapping.", e2);
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Fail to insert into database, please retry", true));
        }
    }

    public static void a(e2 e2Var, ActorInfo actorInfo, String str, String str2, Callback callback) {
        e2Var.getClass();
        Map<String, Integer> map = f;
        if (!map.containsKey(str)) {
            Log.e(ga.a("ActorManagerLogic"), "Unknown actor type: " + str);
            callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unknown actor type.", true));
        }
        String.format("Passing profile to APS. Program: %s, ActorId: %s, ActorType: %s, PackageName: %s, AccountId: %s", actorInfo.getProgram(), actorInfo.getActorDirectedId(), str, str2, actorInfo.getAccountDirectedId());
        ga.a("ActorManagerLogic");
        int activeProfile = AmazonProfileManager.getAmazonProfileManager(e2Var.f288a).setActiveProfile(new Profile(actorInfo.getProgram(), actorInfo.getActorDirectedId(), map.get(str).intValue(), str2, actorInfo.getAccountDirectedId()));
        if (activeProfile == AmazonProfileManager.SUCCESS) {
            Log.i(ga.a("ActorManagerLogic"), "Switch actor success!");
            Bundle bundleA = e2Var.a();
            bundleA.putString(MAPActorManager.KEY_RESULT_ACTOR_TYPE, str);
            yd.b("SWITCH_ACTOR_SUCCESS", new String[0]);
            callback.onSuccess(bundleA);
            return;
        }
        Log.e(ga.a("ActorManagerLogic"), "Failed to switch actor from APS, APS error code: " + activeProfile);
        yd.b("1P_SWITCH_ACTOR_FAILED:APS_FAILURE", new String[0]);
        callback.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.INTERNAL_ERROR, "Unable to switch actor type", true));
    }

    public static synchronized e2 a(t5 t5Var) {
        if (e == null || xa.a()) {
            e = new e2(t5Var);
        }
        return e;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, String str2, String str3) {
        if (!a(str, str2, str3)) {
            return a(5, "accountId/actorId/actorMapping cannot be null", false);
        }
        if (!this.c.a(str)) {
            Log.e(ga.a("ActorManagerLogic"), "The account doesn't exist in MAP.");
            return a(2, "The account doesn't exist in MAP.", false);
        }
        try {
            u7 u7Var = this.b;
            u7Var.getClass();
            u7Var.d(str, "actor/" + str3, str2);
            return a();
        } catch (Exception e2) {
            Log.e(ga.a("ActorManagerLogic"), "Exception happened when trying to set actor mapping.", e2);
            return a(3, "Fail to insert into database.", true);
        }
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback, y5 y5Var) {
        Log.i(ga.a("ActorManagerLogic"), "Enrolling actor with ui called");
        s2 s2Var = new s2(callback);
        String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
        if (!a(string)) {
            s2Var.a(2, MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "KEY_SIGN_IN_ENDPOINT in option bundle cannot be empty!", false));
            return s2Var;
        }
        va.a(new b(str, str2, y5Var, string, str3, bundle, s2Var, context));
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback, y5 y5Var) {
        Log.i(ga.a("ActorManagerLogic"), "Signing up and enrolling actor with ui called");
        s2 s2Var = new s2(callback);
        String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
        if (!a(string)) {
            s2Var.a(2, MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "KEY_SIGN_IN_ENDPOINT in option bundle cannot be empty!", false));
            return s2Var;
        }
        va.a(new a(str, y5Var, string, str2, bundle, s2Var, context));
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public MAPFuture<Bundle> a(@NonNull MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, String str, Bundle bundle, Callback callback, y5 y5Var) {
        boolean zA;
        Log.i(ga.a("ActorManagerLogic"), "Switch actor is called.");
        s2 s2Var = new s2(callback);
        if (actorSwitchMode != MAPActorManager.ActorSwitchMode.Force || (!TextUtils.isEmpty(actorInfo.getSuggestedActorType()) && f.containsKey(actorInfo.getSuggestedActorType()))) {
            zA = a(actorInfo.getAccountDirectedId(), actorInfo.getActorDirectedId(), actorInfo.getProgram());
        } else {
            Log.e(ga.a("ActorManagerLogic"), "Null or invalid suggested actor type is passed in with ActorSwitchMode.Force");
            zA = false;
        }
        if (!zA) {
            s2Var.a(2, MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "The information in ActorInfo is not correct.", false));
            return s2Var;
        }
        if (!this.c.a(actorInfo.getAccountDirectedId())) {
            Log.e(ga.a("ActorManagerLogic"), "The account for switching doesn't exist in MAP.");
            s2Var.a(2, MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "The account for switching doesn't exist in MAP.", false));
            return s2Var;
        }
        if (qe.m(this.f288a)) {
            va.a(new c(actorInfo, str, y5Var, actorSwitchMode, s2Var));
            return s2Var;
        }
        if (!qe.n(this.f288a)) {
            s2Var.a(2, MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.UNSUPPORTED_OPERATION, "AmazonProfileService 4.0 is not available on this device. Please contact device type owner to merge AmazonProfileService 4.0 to the device.", false));
            return s2Var;
        }
        va.a(new d(actorInfo, str, y5Var, actorSwitchMode, s2Var, (bundle == null ? new Bundle() : bundle).getBoolean(MAPActorManager.KEY_DO_NOT_PASS_PACKAGE_NAME_TO_APS) ? null : str));
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, String str2) {
        if (!a(str, str2)) {
            return a(5, "accountId/actorMapping cannot be null", false);
        }
        if (!this.c.a(str)) {
            Log.e(ga.a("ActorManagerLogic"), "Account no longer exist, returning null for actor mapping.");
            return a(2, "The account doesn't exist in MAP.", false);
        }
        try {
            u7 u7Var = this.b;
            u7Var.getClass();
            String strD = u7Var.d(str, "actor/" + str2);
            Bundle bundleA = a();
            bundleA.putString("actor_id", strD);
            return bundleA;
        } catch (Exception e2) {
            Log.e(ga.a("ActorManagerLogic"), "Exception happened when trying to get actor for mapping.", e2);
            return a(3, "Fail to query database.", true);
        }
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str) {
        return c(str, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.f2
    public Bundle a(String str, Bundle bundle) {
        String string = bundle.getString("program");
        if (!a(str, string)) {
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "accountId/Program cannot be null", false);
        }
        if (!this.c.a(str)) {
            Log.e(ga.a("ActorManagerLogic"), "Account no longer exist, returning null for actor info mapping.");
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "The account doesn't exist in MAP.", false);
        }
        try {
            Bundle bundleA = a();
            f8 f8VarA = f8.a(this.f288a, "actor_info_storage_" + str);
            String strB = f8VarA.b(d(string, "actor_id"));
            String strB2 = f8VarA.b(d(string, "actor_type"));
            if (a(strB, strB2)) {
                bundleA.putString("actor_id", strB);
                bundleA.putString("actor_type", strB2);
                return bundleA;
            }
            Log.e(ga.a("ActorManagerLogic"), "accountId/Program does not have associated actor id or actor type");
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "accountId/Program does not have associated actor id or actor type.", false);
        } catch (Exception e2) {
            Log.e(ga.a("ActorManagerLogic"), "Exception happened when trying to get actor info for mapping.", e2);
            return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, "Fail to query database.", false);
        }
    }

    public final boolean a(String... strArr) {
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public final Bundle a(int i, String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("result_code", i);
        bundle.putString(MAPActorManager.KEY_ERROR_MESSAGE, str);
        bundle.putBoolean(MAPActorManager.KEY_RETRYABLE, z);
        return bundle;
    }

    public final Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putInt("result_code", 1);
        return bundle;
    }

    public final String a(String str, String str2, String str3, ActorInfo actorInfo, y5 y5Var, String str4) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        MAPFuture<Bundle> mAPFutureA;
        Bundle bundle = new Bundle();
        bundle.putBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH, true);
        if (actorInfo != null) {
            bundle.putString("program", actorInfo.getProgram());
            bundle.putString("app_identifier", str4);
        }
        if (TextUtils.isEmpty(str3)) {
            mAPFutureA = h9.a(this.f288a).b(str2, str, bundle, (Callback) null, y5Var);
        } else {
            mAPFutureA = h9.a(this.f288a).a((Context) null, str2, str3, str, (String) null, bundle, (Callback) null, y5Var);
        }
        return ((Bundle) ((s2) mAPFutureA).get(15L, TimeUnit.SECONDS)).getString("value_key");
    }
}
