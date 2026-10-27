package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.c;
import com.amazon.identity.auth.device.c7;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ia;
import com.amazon.identity.auth.device.m4;
import com.amazon.identity.auth.device.n2;
import com.amazon.identity.auth.device.o2;
import com.amazon.identity.auth.device.oa;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u2;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.v2;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.x2;
import com.amazon.identity.auth.device.y;
import com.amazon.identity.auth.device.y2;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPAccountManager {

    @FireOsSdk
    public static final String ACCOUNT_ADDED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.auth.account.added.on.device";

    @FireOsSdk
    public static final String ACCOUNT_CHANGED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_CHANGED_ON_DEVICE";

    @FireOsSdk
    public static final String ACCOUNT_FOR_KEY_HAS_CHANGED_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_FOR_KEY";

    @FireOsSdk
    public static final String ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_FOR_PACKAGE_CHANGED";

    @FireOsSdk
    public static final String ACCOUNT_REMOVED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.auth.account.removed.on.device";

    @FireOsSdk
    public static final String KEY_3P_CALLBACK_QUERY_PARAM = "3pCallbackQuery";

    @FireOsSdk
    public static final String KEY_3P_LOGIN_URL_STRING = "thirdPartyLoginUrl";

    @FireOsSdk
    public static final String KEY_ACCESS_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AccessToken";

    @FireOsSdk
    public static final String KEY_ACCOUNT_COR = "account_cor";

    @FireOsSdk
    public static final String KEY_ACCOUNT_MISSING_ATTRIBUTES = "AccountMissingAttributes";

    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVERY_BY_USING_NEW_ACCOUNT = "account_recovery_by_using_new_account";

    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVER_CONTEXT_BUNDLE = "com.amazon.identity.mobi.account.recover.context";

    @FireOsSdk
    public static final String KEY_ACCOUNT_STATUS_POLICY = "account_status_policy";

    @FireOsSdk
    public static final String KEY_ACTOR_ID_FOR_PREAUTHORIZED_LINK_CODE = "actor_id_for_preauthorized_link_code";

    @FireOsSdk
    public static final String KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS = "additionalReturnToUrlParams";

    @FireOsSdk
    public static final String KEY_ADDITIONAL_SIGNIN_DOMAINS = "signin_domains";

    @FireOsSdk
    public static final String KEY_ADD_ACCOUNT_AMAZON_DOMAIN = "com.amazon.identity.ap.domain";

    @FireOsSdk
    public static final String KEY_ADP_TOKEN = "adp_token";

    @FireOsSdk
    public static final String KEY_ALLOW_ADDING_MULTIPLE_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsSecondary";
    public static final String KEY_ALLOW_ADDING_MULTIPLE_PRIMARY_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsPrimary";

    @FireOsSdk
    public static final String KEY_ALLOW_ALL_SIGNIN_PATHS = "allow_all_signin_paths";

    @FireOsSdk
    public static final String KEY_AMAZON_ACCOUNT_LOGIN_NAME = "authAccount";

    @FireOsSdk
    public static final String KEY_AMAZON_ACCOUNT_PASSWORD = "password";

    @FireOsSdk
    public static final String KEY_AMAZON_DOMAIN = "com.amazon.identity.ap.domain";

    @FireOsSdk
    @Deprecated
    public static final String KEY_AP_ASSOC_HANDLE = "com.amazon.identity.ap.assoc_handle";

    @FireOsSdk
    @Deprecated
    public static final String KEY_AP_PAGEID = "com.amazon.identity.ap.pageid";

    @FireOsSdk
    @Deprecated
    public static final String KEY_AT_MAIN = "com.amazon.dcp.sso.AddAccount.options.ATMain";

    @FireOsSdk
    public static final String KEY_AUTHENTICATION_CHALLENGE = "com.amazon.identity.auth.ChallengeException";

    @FireOsSdk
    public static final String KEY_AUTH_DATA_ADDITIONAL_INFO = "auth_data_additional_info";

    @FireOsSdk
    public static final String KEY_AUTH_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AuthToken";

    @FireOsSdk
    public static final String KEY_AUTH_TOKEN_CONTEXT = "com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext";

    @FireOsSdk
    public static final String KEY_BUSINESS_INFO_EXTENSION = "business_info";

    @FireOsSdk
    public static final String KEY_CLAIM_TYPE = "claim_type";

    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT = "com.amazon.dcp.sso.extra.client_event_context";

    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT_NAMESPACE = "com.amazon.dcp.sso.extra.client_event_context.namespace";

    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT_PROPERTIES = "com.amazon.dcp.sso.extra.client_event_context.properties";

    @FireOsSdk
    public static final String KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB = "color_code";

    @FireOsSdk
    public static final String KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT = "deregisterall_register_this_as_primary";

    @FireOsSdk
    public static final String KEY_DEREGISTRATION_METADATA = "deregistration_metadata";

    @FireOsSdk
    public static final String KEY_DEVICE_NAME = "device_name";

    @FireOsSdk
    public static final String KEY_DIRECTED_ID = "com.amazon.dcp.sso.property.account.acctId";

    @FireOsSdk
    public static final String KEY_DIRECTED_ID_CONFIRM = "directed_id_confirm";

    @FireOsSdk
    public static final String KEY_DIRECTED_ID_DELEGATEE = "com.amazon.dcp.sso.property.account.delegateeaccount";

    @FireOsSdk
    public static final String KEY_DIRECTED_ID_POST_ACCOUNT_CHANGE = "key_directed_id_post_account_change";

    @FireOsSdk
    public static final String KEY_DISABLE_GLOBAL_SIGNIN = "disable_global_signin";

    @FireOsSdk
    public static final String KEY_DISABLE_REGISTER_WITHUI_PRE_POPULATION = "disable_user_name_pre_population";

    @FireOsSdk
    public static final String KEY_DISABLE_USERNAME_AUTO_SUGGESTION = "disable_user_name_auto_suggestion";

    @FireOsSdk
    public static final String KEY_DMS_GET_CREDENTIALS_URL = "com.amazon.dcp.sso.AddAccount.options.URL";

    @FireOsSdk
    public static final String KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES = "EnsureAccountStateAttributes";

    @FireOsSdk
    public static final String KEY_ERROR_CODE = "com.amazon.dcp.sso.ErrorCode";

    @FireOsSdk
    public static final String KEY_ERROR_CODE_WEBVIEW_SSL_ERROR = "com.amazon.identity.WebViewSSLErrorCode";

    @FireOsSdk
    public static final String KEY_ERROR_DOMAIN_PATH_WEBVIEW_SSL_ERROR = "com.amazon.identity.WebViewSSLErrorDomainPath";

    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "com.amazon.dcp.sso.ErrorMessage";

    @FireOsSdk
    @Deprecated
    public static final String KEY_EXTRA_DIRECTED_ID = "com.amazon.dcp.sso.extra.account.directed_id";

    @FireOsSdk
    public static final String KEY_EXTRA_KEY = "account_key";

    @FireOsSdk
    public static final String KEY_EXTRA_PROFILES_ACCOUNT_ADDED_OR_REMOVED_BELONG_TO = "com.amazon.identity.auth.extra.account.profiles";

    @FireOsSdk
    public static final String KEY_FEDERATED_AUTH_CONFIG = "federated_auth_config";

    @FireOsSdk
    public static final String KEY_GET_LINK_CODE_WITH_LENGTH = "get_link_code_with_length";

    @FireOsSdk
    public static final String KEY_IGNORE_NAME_FOR_ISOLATED_APP = "ignore_name_for_isolated_app";

    @FireOsSdk
    public static final String KEY_INTENT = "intent";

    @FireOsSdk
    public static final String KEY_IS_AMAZON_BUSINESS_ACCOUNT = "isBusinessAccount";

    @FireOsSdk
    public static final String KEY_IS_CALLBACK_FROM_3P_PARAM = "isCallbackFrom3pLogin";

    @FireOsSdk
    public static final String KEY_IS_NEW_ACCOUNT = "com.amazon.identity.auth.device.accountManager.newaccount";

    @FireOsSdk
    public static final String KEY_LINK_CODE = "link_code";

    @FireOsSdk
    public static final String KEY_LINK_CODE_DOMAIN = "link_code_domain";

    @FireOsSdk
    public static final String KEY_LINK_CODE_POLLING_INTERVAL = "link_code_polling_interval";

    @FireOsSdk
    public static final String KEY_LINK_CODE_TIME_TO_LIVE = "link_code_expiry";

    @FireOsSdk
    public static final String KEY_MARKETPLACE_BUNDLE = "marketplace_bundle";

    @FireOsSdk
    public static final String KEY_MARKETPLACE_DOMAIN = "marketplace_domain";

    @FireOsSdk
    public static final String KEY_OVERRIDE_RETURN_TO_DOMAIN = "return_to_domain";

    @FireOsSdk
    public static final String KEY_PRE_AUTHORIZED_LINK_CODE = "pre_authorized_link_code";

    @FireOsSdk
    public static final String KEY_PRIMARY_DIRECTED_ID = "com.amazon.dcp.sso.property.account.primary.acctId";

    @FireOsSdk
    public static final String KEY_PRIVATE_KEY = "adp_private_key";

    @FireOsSdk
    public static final String KEY_PROFILE_MAPPING = "profile_mapping";

    @FireOsSdk
    public static final String KEY_REGISTRATION_COOKIE_DOMAIN = "key_registration_cookie_domain";

    @FireOsSdk
    public static final String KEY_REGISTRATION_DOMAIN = "registration_domain";

    @FireOsSdk
    public static final String KEY_REQUESTED_EXTENSIONS = "requested_extensions";

    @FireOsSdk
    public static final String KEY_RESULT_CODE = "result code";

    @FireOsSdk
    public static final String KEY_RESUME_AUTHENTICATION_URL = "resume_authentication_url";

    @FireOsSdk
    public static final String KEY_SERVER_SIDE_DEREGISTRATION_RESULT = "server_side_deregistration_result";

    @FireOsSdk
    public static final String KEY_SIGN_IN_ENDPOINT = "key_sign_in_full_endpoint";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNTS_LIST = "ssoAccountsList";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_BUSINESS_DATA = "businessData";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_CUSTOMER_NAME = "accountCustomerName";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_DIRECTED_ID = "accountDirectedId";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_IS_BUSINESS = "accountIsBusiness";

    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_LOGIN = "accountLoginName";

    @FireOsSdk
    public static final String KEY_SSO_ALLOWED_HOST_APPS = "bootstrapAllowedHostApps";

    @FireOsSdk
    public static final String KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_SERIAL = "bootstrapHostDSN";

    @FireOsSdk
    public static final String KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_TYPE = "bootstrapHostDeviceType";

    @FireOsSdk
    public static final String KEY_SSO_CODE = "ssoCode";

    @FireOsSdk
    public static final String KEY_SSO_CODE_TIME_TO_LIVE = "ssoCodeExpiresIn";

    @FireOsSdk
    public static final String KEY_SSO_REQUIRED_ADDITIONAL_DATA = "bootstrapRequiredAdditionalData";

    @FireOsSdk
    public static final String PRIMARY_AMAZON_ACCOUNT_ADDED_INTENT_ACTION = "com.amazon.dcp.sso.action.account.added";

    @FireOsSdk
    public static final String PRIMARY_AMAZON_ACCOUNT_REMOVED_INTENT_ACTION = "com.amazon.dcp.sso.action.account.removed";

    @FireOsSdk
    public static final String SECONDARY_AMAZON_ACCOUNT_ADDED_INTENT_ACTION = "com.amazon.dcp.sso.action.secondary.account.added";

    @FireOsSdk
    public static final String SECONDARY_AMAZON_ACCOUNT_REMOVED_INTENT_ACTION = "com.amazon.dcp.sso.action.secondary.account.removed";
    public static final Set<String> d = new HashSet(Arrays.asList("com.amazon.map.sample.one", "com.amazon.map.sample.two", "com.amazon.map.sample", "com.amazon.map.client.sample.three", "com.amazon.kindle.otter.oobe", "com.amazon.kindle.otter.settings", "com.amazon.avod", "com.amazon.alta.h2debug", "com.amazon.venezia", "com.amazon.kor.demo", "com.amazon.h2settingsfortablet", "com.amazon.tv.oobe", "com.googlecode.android_scripting", "com.amazon.aiv.us", "com.amazon.aiv.eu", "com.amazon.aiv.fe", "com.amazon.aiv.blast", "com.amazon.asxr", "com.android.settings", "com.amazon.alexa.multimodal.tv", "com.amazon.demoman.app.android"));
    public static final String e = MAPAccountManager.class.getName();
    public static final String f = MAPAccountManager.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f206a = new Object[0];
    public final t5 b;
    public f c;

    /* JADX INFO: compiled from: DCP */
    public static final class AuthPortalActivityUIOptions {

        @FireOsSdk
        public static final String KEY_ABOVE_LOCKSCREEN = "AuthPortalActivityUIOptions.aboveLockScreen";

        @Deprecated
        public static final String KEY_AMAZON_SCREEN_MODES = "AuthPortalActivityUIOptions.amazonScreenModes";

        @FireOsSdk
        public static final String KEY_INVERT_SPINNER = "progressbar_invert_spinner";

        @FireOsSdk
        public static final String KEY_IS_FULLSCREEN = "AuthPortalActivityUIOptions.fullscreen";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_BACKGROUND_RESOURCE = "progressbar_background_resource";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_FADE = "progressbar_fade";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_POSITION = "progressbar_position";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_PRIMARY_COLOR = "progressbar_primary_color";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_RESOURCE = "progressbar_resource";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_SECONDARY_COLOR = "progressbar_secondary_color";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_STATE = "progressbar_state";

        @FireOsSdk
        public static final String KEY_PROGRESSBAR_STRETCH = "progressbar_stretch";

        @FireOsSdk
        public static final String KEY_REQUESTED_ORIENTATION = "AuthPortalActivityUIOptions.requestedOrientation";

        @FireOsSdk
        public static final String KEY_SPLASH_SCREEN_RESOURCE = "splashscreen_resource";

        @FireOsSdk
        public static final String KEY_SPLASH_SCREEN_SCALE_TYPE = "splashscreen_scale_type";

        @FireOsSdk
        public static final String KEY_SYSTEM_UI_VISIBILITY = "AuthPortalActivityUIOptions.systemUiVisibility";

        @FireOsSdk
        public static final String KEY_WINDOW_FLAGS = "AuthPortalActivityUIOptions.windowFlags";

        /* JADX INFO: compiled from: DCP */
        public enum ProgressBarState {
            OFF(DebugKt.DEBUG_PROPERTY_VALUE_OFF),
            PROGRESS_BAR("progress_bar"),
            SPINNER_SMALL("spinner_small"),
            SPINNER_MEDIUM("spinner_medium"),
            SPINNER_LARGE("spinner_large");

            private String mValue;

            ProgressBarState(String str) {
                this.mValue = str;
            }

            public static ProgressBarState get(String str) {
                ProgressBarState[] progressBarStateArrValues = values();
                for (int i = 0; i < 5; i++) {
                    ProgressBarState progressBarState = progressBarStateArrValues[i];
                    if (progressBarState.getValue().equals(str)) {
                        return progressBarState;
                    }
                }
                ga.b(MAPAccountManager.e, "Invalid ProgressBarState value: %s", str);
                return null;
            }

            public String getValue() {
                return this.mValue;
            }

            @Override // java.lang.Enum
            @FireOsSdk
            public String toString() {
                return getValue();
            }
        }

        /* JADX INFO: compiled from: DCP */
        public enum ScreenPosition {
            TOP_LEFT("top_left"),
            TOP_CENTER("top_center"),
            TOP_RIGHT("top_right"),
            CENTER_LEFT("center_left"),
            CENTER_CENTER("center_center"),
            CENTER_RIGHT("center_right"),
            BOTTOM_LEFT("bottom_left"),
            BOTTOM_CENTER("bottom_center"),
            BOTTOM_RIGHT("bottom_right");

            private String mValue;

            ScreenPosition(String str) {
                this.mValue = str;
            }

            public static ScreenPosition get(String str) {
                ScreenPosition[] screenPositionArrValues = values();
                for (int i = 0; i < 9; i++) {
                    ScreenPosition screenPosition = screenPositionArrValues[i];
                    if (screenPosition.getValue().equals(str)) {
                        return screenPosition;
                    }
                }
                ga.b(MAPAccountManager.e, "Invalid ScreenPosition value: %s", str);
                return null;
            }

            public String getValue() {
                return this.mValue;
            }

            @Override // java.lang.Enum
            @FireOsSdk
            public String toString() {
                return getValue();
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class AuthPortalOptions {
        public static final String KEY_ADDITIONAL_SIGN_IN_PARAMETERS = "com.amazon.identity.ap.additionalSignInParameters";

        @FireOsSdk
        @Deprecated
        public static final String KEY_ASSOCIATION_HANDLE = "com.amazon.identity.ap.assoc_handle";

        @FireOsSdk
        @Deprecated
        public static final String KEY_CLIENT_CONTEXT = "com.amazon.identity.ap.clientContext";

        @FireOsSdk
        public static final String KEY_DOMAIN = "com.amazon.identity.ap.domain";

        @FireOsSdk
        @Deprecated
        public static final String KEY_PAGEID = "com.amazon.identity.ap.pageid";
        public static final String KEY_PREFIX = "com.amazon.identity.ap";

        @FireOsSdk
        public static final String KEY_REQUEST_PARAMETERS = "com.amazon.identity.ap.request.parameters";
    }

    /* JADX INFO: compiled from: DCP */
    public enum BootstrapError {
        NO_SERVICE_AVAILABLE(0, "NoServiceAvailable"),
        TIMEOUT_SERVICE(1, "NoResponseReceived"),
        NO_ACCOUNT(2, "NoAccount"),
        NO_SIGNATURE(3, "NoSignatureObtained"),
        INVALID_RESPONSE(4, "InvalidResponseFromServer"),
        NETWORK_FAILURE(5, "NetworkFailed"),
        PARSE_ERROR(6, "ErrorParsingResponse"),
        BOOTSTRAP_NOT_ALLOWED(7, "BootstrapNotAllowed"),
        SERVICE_ERROR(8, "ServiceError"),
        FRAUDULENT_PACKAGE(9, "FraudulentPackage"),
        UNCATEGORIZED_ERROR(10, "UncategorizedError");

        private final String mName;
        private final int mValue;

        BootstrapError(int i, String str) {
            this.mValue = i;
            this.mName = str;
        }

        @FireOsSdk
        public static BootstrapError fromValue(int i) {
            BootstrapError bootstrapErrorFromValueHelper = fromValueHelper(i);
            if (bootstrapErrorFromValueHelper != null) {
                return bootstrapErrorFromValueHelper;
            }
            return null;
        }

        private static BootstrapError fromValueHelper(int i) {
            BootstrapError[] bootstrapErrorArrValues = values();
            for (int i2 = 0; i2 < 11; i2++) {
                BootstrapError bootstrapError = bootstrapErrorArrValues[i2];
                if (bootstrapError.value() == i) {
                    return bootstrapError;
                }
            }
            return null;
        }

        @FireOsSdk
        public String getName() {
            return this.mName;
        }

        @FireOsSdk
        public int value() {
            return this.mValue;
        }

        @FireOsSdk
        public static BootstrapError fromValue(int i, BootstrapError bootstrapError) {
            BootstrapError bootstrapErrorFromValueHelper = fromValueHelper(i);
            return bootstrapErrorFromValueHelper != null ? bootstrapErrorFromValueHelper : bootstrapError;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface MAPAccountChangeObserver {
        @FireOsSdk
        void onAccountChange(AccountChangeEvent accountChangeEvent);
    }

    @FireOsSdk
    public MAPAccountManager(Context context) {
        MAPInit.getInstance(context).initialize();
        this.b = t5.a(context);
    }

    public static void a(MAPAccountManager mAPAccountManager, Bundle bundle, Bundle bundle2) {
        mAPAccountManager.getClass();
        EnvironmentUtils environmentUtils = EnvironmentUtils.c;
        String strA = environmentUtils.a(bundle);
        if (TextUtils.isEmpty(strA)) {
            strA = environmentUtils.a(q9.a(bundle));
        }
        String strB = EnvironmentUtils.c.b(strA);
        if (strA.equals("www.amazon.com") || strA.equals("development.amazon.com") || strA.equals("pre-prod.amazon.com")) {
            return;
        }
        Bundle bundle3 = bundle2.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle3 == null) {
            bundle3 = new Bundle();
        }
        if (TextUtils.isEmpty(bundle3.getString("openid.assoc_handle"))) {
            if (TextUtils.isEmpty(bundle2.getString("com.amazon.identity.ap.assoc_handle"))) {
                if (TextUtils.isEmpty(strB)) {
                    strB = "amzn_device_android";
                }
                bundle3.putString("openid.assoc_handle", strB);
            } else {
                bundle3.putString("openid.assoc_handle", bundle2.getString("com.amazon.identity.ap.assoc_handle"));
            }
        }
        if (TextUtils.isEmpty(bundle3.getString("pageId"))) {
            if (TextUtils.isEmpty(bundle2.getString("com.amazon.identity.ap.pageid"))) {
                bundle3.putString("pageId", "amzn_device_common_dark");
            } else {
                bundle3.putString("pageId", bundle2.getString("com.amazon.identity.ap.pageid"));
            }
        }
        bundle2.putBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle3);
    }

    @Deprecated
    public MAPFuture<Bundle> authenticateAccount(Bundle bundle, Callback callback) {
        s2 s2Var = new s2(callback);
        y5 y5Var = new y5("AuthenticateAccountWithoutActivity");
        Bundle bundleA = a(bundle);
        if (!d.contains(this.b.getPackageName())) {
            return a(s2Var, "This API has been deprecated. Please use our new API MAPAccountManager.authenticateAccount(Activity, SigninOptions, Bundle, Bundle, Callback). Please refer to the authenticateAccount API Java doc for more details.");
        }
        a().a(bundleA, yd.a(y5Var, y5Var.b(), s2Var, this.b, false), y5Var);
        return s2Var;
    }

    @FireOsSdk
    public MAPFuture<Bundle> authenticateAccountWithUI(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback) {
        s2 s2VarA = s2.a(callback);
        y5 y5Var = new y5(a(bundle, "AuthenticateAccountWithUI:" + signinOption.name()));
        a().a(activity, signinOption, bundle, yd.a(y5Var, y5Var.b(), callback, this.b, b(bundle)), y5Var);
        return s2VarA;
    }

    @FireOsSdk
    public MAPFuture<Bundle> authorizeLinkCode(Bundle bundle, Callback callback) {
        y5 y5Var = new y5("AuthorizeLinkCode");
        de deVarB = y5Var.b();
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        s2 s2VarA = s2.a(callback);
        Callback callbackA = yd.a(y5Var, deVarB, s2VarA, this.b, false);
        z2 z2Var = new z2(this.b);
        String string = bundle2.getString(KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            ga.a(z2.f);
        }
        String string2 = bundle2.getString(KEY_LINK_CODE);
        if (TextUtils.isEmpty(string2)) {
            z2.a(2, "Required value MAPAccountManager.KEY_LINK_CODE is missing for the API authorizeLinkCode", callbackA, (Bundle) null);
        } else {
            String string3 = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
            if (TextUtils.isEmpty(string3)) {
                z2.a(2, "Required value DirectedID(MAPAccountManager.KEY_AUTHORIZE_LINK_CODE_DIRECTED_ID) is missing for the API authorizeLinkCode", callbackA, (Bundle) null);
            } else {
                g.q.f252a.execute(new c.a(new x2(z2Var, string3, string, string2, bundle2, y5Var), callbackA, "authorizeLinkCode"));
            }
        }
        return s2VarA;
    }

    public final boolean b(Bundle bundle) {
        return (bundle == null || !bundle.containsKey(KEY_RESUME_AUTHENTICATION_URL) || TextUtils.isEmpty(bundle.getString(KEY_RESUME_AUTHENTICATION_URL))) ? false : true;
    }

    public MAPFuture<Bundle> bootStrapSSO(Bundle bundle, Callback callback) {
        y5 y5Var = new y5("bootstrapSSO");
        de deVarB = y5Var.b();
        if (bundle == null) {
            bundle = new Bundle();
        }
        s2 s2VarA = s2.a(callback);
        n2 n2Var = new n2(this.b, bundle, yd.a(y5Var, deVarB, s2VarA));
        n2Var.e.set(false);
        Context context = n2Var.f447a;
        if (ia.a(context, context.getPackageName(), "MAPBootstrapSSOTargetApplication", false).booleanValue() || IsolatedModeSwitcher.isAppInStaticIsolatedMode(n2Var.f447a)) {
            n2Var.g.submit(new o2(n2Var));
        } else {
            n2Var.a(BootstrapError.BOOTSTRAP_NOT_ALLOWED, "Bootstrap not allowed for your application. Currently it is allowed for applications explicitly declaring meta-data \"MAPBootstrapSSOTargetApplication\" in manifest, or for isolated applications");
        }
        return s2VarA;
    }

    public final String c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getString(KEY_CLIENT_EVENT_CONTEXT_NAMESPACE) == null) {
            return "No namespace provided in the client event context";
        }
        if (bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT_PROPERTIES) == null) {
            return "No properties provided in the client event context";
        }
        return null;
    }

    @Deprecated
    public MAPFuture<Bundle> confirmCredential(Activity activity, String str, Bundle bundle, Callback callback) {
        s2 s2Var = new s2(callback);
        y5 y5Var = new y5("DeregisterDevice");
        a().b(activity, str, bundle, yd.a(y5Var, y5Var.b(), s2Var, this.b, false), y5Var);
        return s2Var;
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterAccount(String str, Callback callback) {
        return deregisterAccount(str, callback, null);
    }

    @FireOsSdk
    public void deregisterAccountChangeObserver(MAPAccountChangeObserver mAPAccountChangeObserver) {
        y5 y5Var = new y5("UnregisterAccountChangeObserver");
        de deVarB = y5Var.b();
        c7.a(this.b);
        if (c7.b != null) {
            Log.i(ga.a(c7.f262a), "Deregistering account change observer");
            c7.b.remove(mAPAccountChangeObserver);
        }
        deVarB.c();
        y5Var.e.a();
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterDevice(Callback callback) {
        return deregisterDevice(callback, null);
    }

    @FireOsSdk
    public MAPFuture<Bundle> ensureAccountState(Activity activity, String str, Bundle bundle, Callback callback) {
        s2 s2VarA = s2.a(callback);
        y5 y5Var = new y5("EnsureAccountState");
        de deVarB = y5Var.b();
        if (activity == null) {
            a("For ensureAccountState activity can not be null");
            throw null;
        }
        if (TextUtils.isEmpty(str)) {
            a("For ensureAccountState directedId can not be null");
            throw null;
        }
        if (bundle == null) {
            a("For ensureAccountState options can not be null");
            throw null;
        }
        String string = bundle.getString("com.amazon.identity.ap.domain");
        Bundle bundle2 = bundle.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle2 == null || bundle2.isEmpty()) {
            a("For ensureAccountState requestParameters which contains associationHandle can not be null");
            throw null;
        }
        String string2 = bundle2.getString("openid.assoc_handle");
        ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES);
        if (TextUtils.isEmpty(string)) {
            a("For ensureAccountState domain can not be null");
            throw null;
        }
        if (TextUtils.isEmpty(string2)) {
            a("For ensureAccountState associationHandle can not be null");
            throw null;
        }
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            a("For ensureAccountState assertExistingAttributes can not be null");
            throw null;
        }
        if (isAccountRegistered(str)) {
            a().a(activity, str, bundle, yd.a(y5Var, deVarB, s2VarA, this.b, true), y5Var);
        } else {
            Log.e(ga.a(e), "The given account is not registered");
            String str2 = String.format("Given directedId %s is not registered.", str);
            callback.onError(y.b(MAPError.AccountError.CUSTOMER_NOT_FOUND, str2, RegistrationError.CUSTOMER_NOT_FOUND.value(), str2));
            y5Var.e.a();
        }
        return s2VarA;
    }

    @Deprecated
    public String ensureAmazonAccount(Activity activity) {
        synchronized (this.f206a) {
            if (isDeviceRegistered()) {
                Log.i(ga.a(e), "Already have an amazon account.");
                return getAccount();
            }
            String str = e;
            Log.i(ga.a(str), "No amazon account found. Try to create one.");
            if (va.a()) {
                Log.e(ga.a(str), "Invoked on main thread.");
                throw new IllegalStateException("Do not run on main thread.");
            }
            try {
                registerAccountWithUI(activity, SigninOption.WebviewSignin, null, null).get();
                return getAccount();
            } catch (Exception e2) {
                Log.e(ga.a(e), "Got exception when creating amazon account.", e2);
                return null;
            }
        }
    }

    @FireOsSdk
    public String generateCNEPUrl(Context context, Bundle bundle) {
        Uri uri;
        yd.b("MAPGenerateCNEPUrl:" + this.b.getPackageName(), new String[0]);
        String strA = OpenIdRequest.a(qe.c(context), oa.a(context, DeviceAttribute.CentralDeviceType));
        String string = bundle.getString(KEY_DIRECTED_ID_CONFIRM);
        OpenIdRequest openIdRequest = new OpenIdRequest(strA, OpenIdRequest.REQUEST_TYPE.CNEP, bundle);
        String strD = openIdRequest.d();
        if (TextUtils.isEmpty(string) || (uri = Uri.parse(strD)) == null) {
            return strD;
        }
        openIdRequest.d(uri.getScheme() + "://" + uri.getHost() + "/ap/id/" + string);
        return openIdRequest.d();
    }

    @FireOsSdk
    public String generateForgotPasswordUrl(Bundle bundle) {
        yd.b("MAPGenerateForgotPasswordUrl:" + this.b.getPackageName(), new String[0]);
        y5 y5Var = new y5("generateForgotPasswordUrl");
        de deVarB = y5Var.b();
        try {
            return new OpenIdRequest(null, OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD, bundle).d();
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> generateLinkCode(Bundle bundle, Callback callback) {
        y5 y5Var = new y5("GenerateLinkCode");
        de deVarB = y5Var.b();
        if (bundle == null) {
            bundle = new Bundle();
        }
        s2 s2VarA = s2.a(callback);
        Callback callbackA = yd.a(y5Var, deVarB, s2VarA, this.b, false);
        z2 z2Var = new z2(this.b);
        String string = bundle.getString(KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            ga.a(z2.f);
        }
        g.q.f252a.execute(new c.a(new u2(z2Var, string, bundle, y5Var), callbackA, "generateLinkCode"));
        return s2VarA;
    }

    @FireOsSdk
    public MAPFuture<Bundle> generatePreAuthorizedLinkCode(Bundle bundle, Callback callback) {
        y5 y5Var = new y5("GeneratePreAuthorizedLinkCode");
        de deVarB = y5Var.b();
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        s2 s2VarA = s2.a(callback);
        Callback callbackA = yd.a(y5Var, deVarB, s2VarA, this.b, false);
        z2 z2Var = new z2(this.b);
        String string = bundle2.getString(KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            ga.a(z2.f);
        }
        String string2 = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
        if (TextUtils.isEmpty(string2)) {
            z2.a(2, "Required value DirectedID(MAPAccountManager.KEY_DIRECTED_ID) is missing for the API generatePreAuthorizedLinkCode", callbackA, (Bundle) null);
        } else {
            long j = bundle2.getLong(KEY_LINK_CODE_TIME_TO_LIVE, 0L);
            String string3 = bundle2.getString(KEY_ACTOR_ID_FOR_PREAUTHORIZED_LINK_CODE);
            g.q.f252a.execute(new c.a(new v2(z2Var, string2, string3, y5Var, string, j, bundle2), callbackA, "generatePreAuthorizedLinkCode"));
        }
        return s2VarA;
    }

    @FireOsSdk
    public String getAccount() {
        de deVarB = yd.b(f, "getAccount");
        try {
            return a().b(this.b.getPackageName());
        } finally {
            deVarB.c();
        }
    }

    @FireOsSdk
    public Set<String> getAccounts() {
        de deVarB = yd.b(f, "getAccounts");
        try {
            return a().c();
        } finally {
            deVarB.c();
        }
    }

    @FireOsSdk
    @Deprecated
    public String getPrimaryAccount() {
        de deVarB = yd.b(f, "getPrimaryAccount");
        try {
            return a().b();
        } finally {
            deVarB.c();
        }
    }

    @FireOsSdk
    public boolean isAccountRegistered(String str) {
        de deVarB = yd.b(f, "isAccountRegistered");
        try {
            return a().a(str);
        } finally {
            deVarB.c();
        }
    }

    @FireOsSdk
    @Deprecated
    public boolean isDeviceRegistered() {
        de deVarB = yd.b(f, "isDeviceRegistered");
        try {
            return a().a();
        } finally {
            deVarB.c();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> recoverAccount(Context context, Bundle bundle, Bundle bundle2, Callback callback) {
        return a(context, bundle, bundle2, callback);
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccount(RegistrationType registrationType, Bundle bundle, Callback callback) {
        Context context = yd.f685a;
        y5 y5VarA = y5.a("RegisterAccountWithoutActivity:" + (registrationType == null ? "NullRegType" : registrationType.name()));
        s2 s2VarA = s2.a(callback);
        if ((RegistrationType.WITH_LOGIN_CREDENTIALS == registrationType || RegistrationType.WITH_DIRECTEDID_CREDENTIALS == registrationType || RegistrationType.WITH_PRIMARY_DIRECTEDID_CREDENTIALS == registrationType) && !d.contains(this.b.getPackageName())) {
            return a(s2VarA, "Invalid RegistrationType. RegisterAccount API with RegistrationType:" + registrationType.getName() + " has been removed. Please use our new API MAPAccountManager.registerAccount(Activity, Bundle, Bundle, Callback). Please refer to the registerAccount API Java doc for more details.");
        }
        if (RegistrationType.WITH_LINK_CODE == registrationType) {
            String string = bundle.getString(KEY_LINK_CODE);
            String string2 = bundle.getString(KEY_PRE_AUTHORIZED_LINK_CODE);
            if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
                Log.i(ga.a(e), "No link code passed in the registerAccount API WITH_LINK_CODE");
                return a(s2VarA, "A required parameter link code was not passed in the API. Call MAPAccountManager#generateLinkCode() or MAPAccountManager#generatePreAuthorizedLinkCode() to get a link code based on your use-case and pass them in the key MAPAccountManager#KEY_LINK_CODE or MAPAccountManager#KEY_PRE_AUTHORIZED_LINK_CODE respectively.");
            }
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                Log.i(ga.a(e), "Multiple link code keys set");
                return a(s2VarA, "You cannot set both the keys MAPAccountManager#KEY_LINK_CODE and MAPAccountManager#KEY_PRE_AUTHORIZED_LINK_CODE. Based on the type of link code you have, set the appropriate key as specified in the javadoc of RegistrationType#WITH_LINK_CODE.");
            }
            if (!TextUtils.isEmpty(string)) {
                z2.a aVarA = z2.a(this.b);
                if (aVarA == null) {
                    return a(s2VarA, "The link code that MAP had has expired or it has not been generated yet. Please call MAPAccountManager#generateLinkCode to generate the link code.");
                }
                if (!aVarA.f696a.equals(string)) {
                    return a(s2VarA, "The link code does not match the one that MAP has. Please call MAPAccountManager#generateLinkCode to get the link code.");
                }
                y2 y2Var = new y2(this.b, s2VarA);
                bundle.putString(AccountConstants.KEY_CBL_PUBLIC_CODE, aVarA.f696a);
                bundle.putString(AccountConstants.KEY_CBL_PRIVATE_CODE, aVarA.b);
                s2VarA = y2Var;
            }
        }
        a().a(registrationType, a(bundle), yd.a(y5VarA, y5VarA.b(), s2VarA, this.b, false), y5VarA);
        return s2VarA;
    }

    @FireOsSdk
    public void registerAccountChangeObserver(MAPAccountChangeObserver mAPAccountChangeObserver) {
        y5 y5Var = new y5("RegisterAccountChangeObserver");
        de deVarB = y5Var.b();
        c7.a(this.b);
        synchronized (MAPAccountChangeObserver.class) {
            if (c7.b == null) {
                c7.b = new CopyOnWriteArrayList();
            }
        }
        Log.i(ga.a(c7.f262a), "Registering account change observer");
        c7.b.add(mAPAccountChangeObserver);
        deVarB.c();
        y5Var.e.a();
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccountWithUI(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback) {
        e.a(signinOption, "option");
        y5 y5VarA = y5.a(a(bundle, "RegisterAccountWithUI:" + signinOption.name()));
        de deVarB = y5VarA.b();
        s2 s2VarA = s2.a(callback);
        a().b(activity, signinOption, bundle, yd.a(y5VarA, deVarB, s2VarA, this.b, b(bundle)), y5VarA);
        return s2VarA;
    }

    @Deprecated
    public MAPFuture<Bundle> registerDelegatedAccount(String str, String str2, Bundle bundle, Callback callback) {
        s2 s2VarA = s2.a(callback);
        y5 y5Var = new y5("registerDelegatedAccount");
        de deVarB = y5Var.e.b("registerDelegatedAccount_totalTime");
        deVarB.b();
        y5Var.b("registerDelegatedAccount_count");
        Log.i(ga.a(e), "registerDelegatedAccount() is deprecated. Please use registerAccount().");
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", str2);
        bundle.putString("com.amazon.dcp.sso.property.account.delegateeaccount", str);
        bundle.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
        a().a(RegistrationType.REGISTER_DELEGATED_ACCOUNT, bundle, yd.a(y5Var, deVarB, s2VarA, this.b, false), y5Var);
        return s2VarA;
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterAccount(String str, Callback callback, Bundle bundle) {
        String strC;
        ga.c(e, "deregisterAccount called by %s", this.b.getPackageName());
        if (bundle != null && (strC = c(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            return a(s2.a(callback), strC);
        }
        y5 y5Var = new y5("DeregisterAccount");
        return a().a(str, yd.a(y5Var, y5Var.b(), callback, this.b, false), y5Var, bundle);
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterDevice(Callback callback, Bundle bundle) {
        String strC;
        ga.c(e, "deregisterDevice called by %s", this.b.getPackageName());
        if (bundle != null && (strC = c(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            return a(s2.a(callback), strC);
        }
        y5 y5Var = new y5("DeregisterDevice");
        return a().a(yd.a(y5Var, y5Var.b(), callback, this.b, false), y5Var, bundle);
    }

    @Deprecated
    public MAPFuture<Bundle> recoverAccount(Activity activity, Bundle bundle, Bundle bundle2, Callback callback) {
        return a(activity, bundle, bundle2, callback);
    }

    /* JADX INFO: compiled from: DCP */
    public enum RegistrationError {
        ACCOUNT_ALREADY_EXISTS(0, "AccountAlreadyExists"),
        NETWORK_FAILURE(1, "NetworkFailure"),
        AUTHENTICATION_FAILED(2, "AuthenticationFailed"),
        PARSE_ERROR(3, "ParseError"),
        CUSTOMER_NOT_FOUND(4, "CustomerNotFound"),
        DEVICE_ALREADY_REGISTERED(5, "DeviceAlreadyRegistered"),
        DUPLICATE_DEVICE_NAME(6, "DuplicateDeviceName"),
        DEREGISTER_FAILED(7, "DeregisterFailed"),
        UNRECOGNIZED(8, "Unrecognized"),
        REGISTER_FAILED(9, "RegisterFailed"),
        BAD_REQUEST(10, "BadRequest"),
        ALREADY_DEREGISTERED(11, "AlreadyDeregistered"),
        BAD_SECRET(12, "BadSecret"),
        NO_ACCOUNT(13, "NoAccount"),
        UI_NOT_FOUND(14, "UINotFound"),
        DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED(15, "DelegateeAccountAlreadyDeregistered"),
        AUTHENTICATION_CHALLENGED(16, "AuthenticationChallenged"),
        INTERNAL_ERROR(17, "InternalError"),
        REQUIRED_3P_AUTHENTICATION(18, "Required3PAuthentication"),
        ACTOR_NOT_ASSOCIATED(19, "ActorNotAssociated"),
        LEGACY_ERROR_CODE_NOT_SUPPORTED_ERROR(20, "LegacyErrorCodeNotSupportedError");

        private final String mName;
        private final int mValue;

        RegistrationError(int i, String str) {
            this.mValue = i;
            this.mName = str;
        }

        @FireOsSdk
        public static RegistrationError fromValue(int i) {
            RegistrationError registrationErrorFromValueHelper = fromValueHelper(i);
            if (registrationErrorFromValueHelper != null) {
                return registrationErrorFromValueHelper;
            }
            throw new IndexOutOfBoundsException();
        }

        private static RegistrationError fromValueHelper(int i) {
            RegistrationError[] registrationErrorArrValues = values();
            for (int i2 = 0; i2 < 21; i2++) {
                RegistrationError registrationError = registrationErrorArrValues[i2];
                if (registrationError.value() == i) {
                    return registrationError;
                }
            }
            return null;
        }

        @FireOsSdk
        public String getName() {
            return this.mName;
        }

        @FireOsSdk
        public int value() {
            return this.mValue;
        }

        @FireOsSdk
        public static RegistrationError fromValue(int i, RegistrationError registrationError) {
            RegistrationError registrationErrorFromValueHelper = fromValueHelper(i);
            return registrationErrorFromValueHelper != null ? registrationErrorFromValueHelper : registrationError;
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> bootStrapSSO(Callback callback) {
        return bootStrapSSO(new Bundle(), callback);
    }

    @FireOsSdk
    public MAPFuture<Bundle> authenticateAccount(final Activity activity, final SigninOption signinOption, final Bundle bundle, final Bundle bundle2, Callback callback) {
        final s2 s2Var = new s2(callback);
        y5 y5Var = new y5("AuthenticateAccount");
        if (activity == null) {
            return a(s2Var, "Activity passed should not be null. Please pass non null activity");
        }
        a().a(a(bundle), yd.a(y5Var, y5Var.b(), new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.2
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle3) {
                if (bundle3.getInt(MAPError.KEY_ERROR_CODE, -1) != MAPError.AccountError.AUTHENTICATION_CHALLENGED.getErrorCode()) {
                    s2Var.onError(bundle3);
                    return;
                }
                Bundle bundle4 = bundle3.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
                Bundle bundle5 = new Bundle();
                Bundle bundle6 = bundle2;
                if (bundle6 != null) {
                    bundle5.putAll(bundle6);
                }
                MAPAccountManager.a(MAPAccountManager.this, bundle, bundle5);
                if (bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") && !bundle5.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
                    bundle5.putString("com.amazon.dcp.sso.property.account.acctId", bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
                }
                bundle5.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle4);
                MAPAccountManager.this.authenticateAccountWithUI(activity, signinOption, bundle5, s2Var);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle3) {
                s2Var.onSuccess(bundle3);
            }
        }, this.b, false), y5Var);
        return s2Var;
    }

    public final MAPFuture<Bundle> a(s2 s2Var, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", RegistrationError.BAD_REQUEST.value());
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
        bundle.putInt(MAPError.KEY_ERROR_CODE, commonError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, commonError.getErrorType());
        s2Var.onError(bundle);
        return s2Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.identity.auth.device.api.MAPFuture<android.os.Bundle> a(android.content.Context r16, android.os.Bundle r17, android.os.Bundle r18, com.amazon.identity.auth.device.api.Callback r19) {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.api.MAPAccountManager.a(android.content.Context, android.os.Bundle, android.os.Bundle, com.amazon.identity.auth.device.api.Callback):com.amazon.identity.auth.device.api.MAPFuture");
    }

    public final void a(String str) {
        Log.e(ga.a(e), str);
        throw new IllegalArgumentException(str);
    }

    public final String a(Bundle bundle, String str) {
        Bundle bundle2;
        if (bundle != null && bundle.containsKey(KEY_AUTHENTICATION_CHALLENGE) && (bundle2 = bundle.getBundle(KEY_AUTHENTICATION_CHALLENGE)) != null) {
            String string = bundle2.getString("com.amazon.identity.auth.ChallengeException.Reason");
            if (TextUtils.isEmpty(string)) {
                string = "Challenge";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(":");
            sb.append(string);
            TextUtils.isEmpty(bundle2.getString("com.amazon.identity.auth.ChallengeException.requiredAuthenticationMethod"));
        }
        if (!b(bundle)) {
            return str;
        }
        return str + ":ResumeUrl";
    }

    public final Bundle a(Bundle bundle) {
        Bundle bundleA = e.a(bundle);
        bundleA.putString("calling_package", this.b.getPackageName());
        bundleA.putInt("calling_profile", m4.b());
        return bundleA;
    }

    public final f a() {
        f fVar;
        synchronized (this.f206a) {
            if (this.c == null) {
                this.c = e.b(this.b);
            }
            fVar = this.c;
        }
        return fVar;
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccount(final Activity activity, final Bundle bundle, final Bundle bundle2, Callback callback) {
        String strC;
        final s2 s2VarA = s2.a(callback);
        if (activity == null) {
            return a(s2VarA, "Activity passed should not be null. Please pass non null activity");
        }
        if (bundle != null && (strC = c(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            a(s2VarA, strC);
        }
        RegistrationType registrationType = RegistrationType.WITH_LOGIN_CREDENTIALS;
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") && bundle.containsKey("com.amazon.dcp.sso.property.account.primary.acctId")) {
            return a(s2VarA, "You pass account direct id for both secondary and primary registration: MAPAccountManager.KEY_DIRECTED_ID and MAPAccountManager.KEY_PRIMARY_DIRECTED_ID.\nPlease pass either of it.");
        }
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.primary.acctId")) {
            registrationType = RegistrationType.WITH_PRIMARY_DIRECTEDID_CREDENTIALS;
        }
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
            registrationType = RegistrationType.WITH_DIRECTEDID_CREDENTIALS;
        }
        RegistrationType registrationType2 = registrationType;
        final y5 y5VarA = y5.a("RegisterAccount:" + registrationType2.name());
        a().a(registrationType2, a(bundle), yd.a(y5VarA, y5VarA.b(), new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle3) {
                if (bundle3.getInt(MAPError.KEY_ERROR_CODE, -1) != MAPError.AccountError.AUTHENTICATION_CHALLENGED.getErrorCode()) {
                    s2VarA.onError(bundle3);
                    return;
                }
                Bundle bundle4 = bundle3.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
                String str = MAPAccountManager.e;
                ga.a(str);
                e.c(bundle4);
                ga.a(str);
                Bundle bundle5 = bundle2;
                if (bundle5 == null) {
                    bundle5 = new Bundle();
                }
                MAPAccountManager.a(MAPAccountManager.this, bundle, bundle5);
                bundle5.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle4);
                MAPAccountManager.this.registerAccountWithUI(activity, SigninOption.WebviewSignin, bundle5, s2VarA);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(final Bundle bundle3) {
                Bundle bundle4 = bundle2;
                if (bundle4 == null || !bundle4.containsKey(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES)) {
                    s2VarA.onSuccess(bundle3);
                    return;
                }
                String str = MAPAccountManager.e;
                Log.i(ga.a(str), "Register account is done, going to check whether the account has assert attributes");
                final String string = bundle3.getString("com.amazon.dcp.sso.property.account.acctId");
                if (TextUtils.isEmpty(string)) {
                    Log.i(ga.a(str), "Did not get the directedID in the reg success respone so we are going to fix the account we get from getAccount(). It should not happen on Gen6+ and normal 3P devices.");
                    string = MAPAccountManager.this.getAccount();
                }
                Bundle bundle5 = new Bundle();
                bundle5.putString("com.amazon.identity.ap.domain", bundle.getString("com.amazon.identity.ap.domain"));
                bundle5.putBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle2.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS));
                bundle5.putStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES, bundle2.getStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES));
                Callback callback2 = new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.1.1
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle6) {
                        try {
                            y5VarA.b("DeregisterAfterEnsuringAccountStateFail");
                            Log.i(ga.a(MAPAccountManager.e), "Going to degister the account, since the account doesn't have assert attributes");
                            MAPAccountManager.this.deregisterAccount(string, null).get();
                        } catch (Exception unused) {
                            Log.e(ga.a(MAPAccountManager.e), "Exception happened when try to degister account because of missing assert attributes");
                        }
                        s2VarA.onError(bundle6);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(Bundle bundle6) {
                        bundle3.putAll(bundle6);
                        s2VarA.onSuccess(bundle3);
                    }
                };
                y5VarA.b("RegisterAccountWithEnsuringAccountState");
                MAPAccountManager.this.ensureAccountState(activity, string, bundle5, callback2);
            }
        }, this.b, false), y5VarA);
        return s2VarA;
    }
}
