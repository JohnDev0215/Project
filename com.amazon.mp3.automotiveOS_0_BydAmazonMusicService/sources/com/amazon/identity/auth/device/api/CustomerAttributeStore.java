package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.fa;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t1;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.wd;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import java.util.EnumSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class CustomerAttributeStore {

    @FireOsSdk
    public static final String AMAZON_ACCOUNT_PROPERTY_CHANGED_INTENT_ACTION = "com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed";

    @FireOsSdk
    public static final String COR_PFM_CHANGED_INTENT_ACTION = "com.amazon.dcp.sso.broadcast.CORPFMHasChanged";

    @FireOsSdk
    public static final int ERROR_CODE_ACCOUNT_NOT_REGISTERED = 3;

    @FireOsSdk
    public static final int ERROR_CODE_GENERIC_INTERNAL_ERROR = 1;

    @FireOsSdk
    public static final int ERROR_CODE_KEY_NOT_FOUND = 2;

    @FireOsSdk
    public static final int ERROR_CODE_UNABLE_TO_GET_ATTRIBUTE_ERROR = 4;

    @FireOsSdk
    public static final int ERROR_CODE_UNABLE_TO_SET_ATTRIBUTE_ERROR = 5;

    @FireOsSdk
    public static final String KEY_DEFAULT_VALUE = "defaut_value_key";

    @FireOsSdk
    public static final String KEY_DEVICE_EMAIL = "com.amazon.dcp.sso.property.deviceemail";

    @FireOsSdk
    public static final String KEY_DEVICE_NAME = "com.amazon.dcp.sso.property.devicename";

    @FireOsSdk
    public static final String KEY_DIRECTED_ID = "new.account.property.changed";

    @FireOsSdk
    public static final String KEY_ERROR_CODE = "error_code_key";

    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "error_message_key";

    @FireOsSdk
    public static final String KEY_NEW_DEVICE_NAME = "new_device_name";

    @FireOsSdk
    public static final String KEY_PACKAGE_NAME_FOR_RENAMING_CHILD_DEVICE_3P_DEVICES = "key_package_name_for_renaming_child_device_3p_devices";

    @FireOsSdk
    public static final String KEY_VALUE = "value_key";
    public static CustomerAttributeStore d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f202a;
    public t1 b;
    public f c;

    /* JADX INFO: compiled from: DCP */
    public enum GetAttributeOptions {
        ForceRefresh("forceRefresh");

        private final String mUniqueValue;

        GetAttributeOptions(String str) {
            this.mUniqueValue = str;
        }

        public static EnumSet<GetAttributeOptions> deserializeList(JSONArray jSONArray) {
            EnumSet<GetAttributeOptions> enumSetNoneOf = EnumSet.noneOf(GetAttributeOptions.class);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    enumSetNoneOf.add(getOptionFromValue(jSONArray.getString(i)));
                } catch (JSONException e) {
                    Log.e(ga.a("com.amazon.identity.auth.device.api.CustomerAttributeStore"), "Could not deseralize part of getAttribute options", e);
                }
            }
            return enumSetNoneOf;
        }

        public static GetAttributeOptions getOptionFromValue(String str) {
            GetAttributeOptions[] getAttributeOptionsArrValues = values();
            for (int i = 0; i < 1; i++) {
                GetAttributeOptions getAttributeOptions = getAttributeOptionsArrValues[i];
                if (getAttributeOptions.getValue().equals(str)) {
                    return getAttributeOptions;
                }
            }
            return null;
        }

        public static JSONArray serializeList(EnumSet<GetAttributeOptions> enumSet) {
            JSONArray jSONArray = new JSONArray();
            Iterator<GetAttributeOptions> it = enumSet.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getValue());
            }
            return jSONArray;
        }

        public String getValue() {
            return this.mUniqueValue;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class RenameDeviceError {

        @FireOsSdk
        public static final int AUTHENTICATION_FAILED = 2;

        @FireOsSdk
        public static final int INVALID_INPUT = 3;

        @FireOsSdk
        public static final int NAME_ALREADY_USED = 5;

        @FireOsSdk
        public static final int NETWORK_FAILURE = 1;

        @FireOsSdk
        public static final int NO_AMAZON_ACCOUNT = 6;

        @FireOsSdk
        public static final int OVERRIDE_DEVICE_TYPE_NOT_DETERMINED = 8;

        @FireOsSdk
        public static final int PACKAGE_NAME_UNRECOGNIZED = 9;

        @FireOsSdk
        public static final int PACKAGE_RUNNING_ON_FIRST_PARTY_DEVICE = 11;

        @FireOsSdk
        public static final int PACKAGE_USING_CENTRAL_DEVICE_TYPE = 10;

        @FireOsSdk
        public static final int PARSE_ERROR = 4;

        @FireOsSdk
        public static final int UNRECOGNIZED = 7;
    }

    public CustomerAttributeStore(Context context) {
        MAPInit.getInstance(context).initialize();
        this.f202a = t5.a(context);
        this.c = null;
    }

    @FireOsSdk
    public static void generateNewInstance(Context context) {
        d = new CustomerAttributeStore(context.getApplicationContext());
    }

    @FireOsSdk
    public static synchronized CustomerAttributeStore getInstance(Context context) {
        e.a((Object) context, "context");
        if (d == null) {
            generateNewInstance(context);
        }
        return d;
    }

    @FireOsSdk
    public static String getValueOrAttributeDefault(Bundle bundle) {
        e.a(bundle, "attributeResult");
        String string = bundle.getString("value_key");
        return string != null ? string : bundle.getString(KEY_DEFAULT_VALUE);
    }

    public final void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (str == null && !e.a(fa.a(str2))) {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback, Bundle bundle, EnumSet<GetAttributeOptions> enumSet) {
        y5 y5Var = new y5("CustomerAttributeStore:GetAttribute");
        a(str, str2);
        de deVarB = y5Var.b();
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        if (enumSet == null) {
            enumSet = EnumSet.noneOf(GetAttributeOptions.class);
        }
        return a().a(str, str2, yd.a(y5Var, deVarB, callback), bundle2, enumSet, y5Var);
    }

    @FireOsSdk
    public Bundle peekAttribute(String str, String str2) {
        y5 y5Var = new y5("CustomerAttributeStore:peekAttribute");
        a(str, str2);
        de deVarB = y5Var.b();
        try {
            return a().a(str, str2);
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> renameDevice(String str, String str2, Bundle bundle, Callback callback) {
        y5 y5Var = new y5("RenameDevice");
        ga.c("com.amazon.identity.auth.device.api.CustomerAttributeStore", "renameDevice called by %s", this.f202a.getPackageName());
        de deVarB = y5Var.b();
        if (this.c == null) {
            this.c = e.b(this.f202a);
        }
        return this.c.b(str, str2, bundle, yd.a(y5Var, deVarB, callback), y5Var);
    }

    @FireOsSdk
    @Deprecated
    public MAPFuture<Bundle> setAttribute(String str, String str2, String str3, Callback callback) {
        y5 y5Var = new y5("CustomerAttributeStore:setAttribute");
        a(str, str2);
        return a().a(str, str2, str3, new wd(y5Var.b(), callback));
    }

    public final synchronized t1 a() {
        if (this.b == null) {
            this.b = e.c(this.f202a);
        }
        return this.b;
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback) {
        return getAttribute(str, str2, callback, EnumSet.noneOf(GetAttributeOptions.class));
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback, EnumSet<GetAttributeOptions> enumSet) {
        return getAttribute(str, str2, callback, new Bundle(), enumSet);
    }
}
