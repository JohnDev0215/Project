package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.m4;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w0;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.z5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class MultipleAccountManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f229a;
    public final w0 b;

    /* JADX INFO: compiled from: DCP */
    public static abstract class AccountMappingType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f230a;
        public final String b;

        public AccountMappingType(String str, String str2) {
            this.f230a = str;
            this.b = str2;
        }

        @FireOsSdk
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AccountMappingType accountMappingType = (AccountMappingType) obj;
            return TextUtils.equals(this.f230a, accountMappingType.f230a) && TextUtils.equals(this.b, accountMappingType.b);
        }

        public String getAccountMappingType() {
            return this.f230a;
        }

        public String getAccountMappingValue() {
            return this.b;
        }

        @FireOsSdk
        public int hashCode() {
            String str = this.f230a;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.b;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class CustomKeyMappingType extends AccountMappingType {
        @FireOsSdk
        public CustomKeyMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.custom_keys", str);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return ((i5) t5.a(context).getSystemService("sso_platform")).b();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class PackageMappingType extends AccountMappingType {
        @FireOsSdk
        public PackageMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_packages", str);
        }

        @FireOsSdk
        public static PackageMappingType createCurrentPackageMapping(Context context) {
            return new PackageMappingType(context.getPackageName());
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return ((i5) t5.a(context).getSystemService("sso_platform")).b();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class PrimaryUserMappingType extends AccountMappingType {
        @FireOsSdk
        public static PrimaryUserMappingType createPrimaryMappingForProfile(int i) {
            return new PrimaryUserMappingType(i);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            return true;
        }

        @FireOsSdk
        public PrimaryUserMappingType() {
            this(m4.b());
        }

        public PrimaryUserMappingType(int i) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_profiles", Integer.toString(i));
        }
    }

    /* JADX INFO: compiled from: DCP */
    @Deprecated
    public static class ProfileMappingType extends PrimaryUserMappingType {
        public ProfileMappingType(int i) {
            super(i);
        }

        public static ProfileMappingType createCurrentProfile(Context context) {
            return new ProfileMappingType(0);
        }

        public static ProfileMappingType createCurrentViewableProfile(Context context) {
            return new ProfileMappingType(0);
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.MultipleAccountManager"), "ProfileMappingType is deprecated. Please use PrimaryUserMappingType instead");
            return false;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SessionPackageMappingAlreadySetException extends RuntimeException {
        private final String mOwner;

        @FireOsSdk
        public SessionPackageMappingAlreadySetException(String str) {
            this.mOwner = str;
        }

        @FireOsSdk
        public String getOwner() {
            return this.mOwner;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SessionUserMappingType extends AccountMappingType {
        @FireOsSdk
        public SessionUserMappingType() {
            super(AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
        }

        public static boolean isSupportedOnThisPlatform(Context context) {
            i5 i5Var = (i5) t5.a(context).getSystemService("sso_platform");
            if (i5Var.b()) {
                return true;
            }
            Context context2 = i5Var.f382a;
            Boolean boolPutIfAbsent = qe.f.get(AccountConstants.INTENT_ACTION_SESSION_USER_CHANGE_SERVICE);
            if (boolPutIfAbsent == null) {
                boolean z = ((ArrayList) new z5(context2, false).a(new Intent(AccountConstants.INTENT_ACTION_SESSION_USER_CHANGE_SERVICE), 0)).size() > 0;
                boolPutIfAbsent = qe.f.putIfAbsent(AccountConstants.INTENT_ACTION_SESSION_USER_CHANGE_SERVICE, Boolean.valueOf(z));
                if (boolPutIfAbsent == null) {
                    boolPutIfAbsent = Boolean.valueOf(z);
                }
            }
            return boolPutIfAbsent.booleanValue();
        }
    }

    @FireOsSdk
    public MultipleAccountManager(Context context) {
        MAPInit.getInstance(context).initialize();
        t5 t5VarA = t5.a(context);
        this.f229a = t5VarA;
        this.b = e.d(t5VarA);
    }

    @FireOsSdk
    public boolean doesAccountHaveMapping(String str, AccountMappingType accountMappingType) {
        y5 y5Var = new y5("MultipleAccountManager:doesAccountHaveMapping");
        de deVarA = y5Var.a(this.f229a, "Time");
        try {
            return this.b.a(str, accountMappingType);
        } finally {
            deVarA.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public String getAccountForMapping(AccountMappingType... accountMappingTypeArr) {
        return this.b.a(accountMappingTypeArr);
    }

    @FireOsSdk
    public String getAccountMappingOwner(AccountMappingType accountMappingType) {
        y5 y5Var = new y5("MultipleAccountManager:getAccountMappingOwner");
        de deVarB = y5Var.b();
        try {
            return this.b.a(accountMappingType);
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public Intent getIntentToRemoveAccountMapping(AccountMappingType accountMappingType) {
        y5 y5Var = new y5("MultipleAccountManager:getIntentToRemoveAccountMapping");
        de deVarB = y5Var.b();
        try {
            return this.b.b(accountMappingType);
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public boolean removeAccountMappings(String str, AccountMappingType... accountMappingTypeArr) {
        y5 y5Var = new y5("MultipleAccountManager:removeAccountMappings");
        de deVarB = y5Var.b();
        try {
            return this.b.a(str, accountMappingTypeArr);
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    @FireOsSdk
    public boolean setAccountMappings(String str, AccountMappingType... accountMappingTypeArr) {
        y5 y5Var = new y5("MultipleAccountManager:setAccountMappings");
        de deVarB = y5Var.b();
        try {
            return this.b.b(str, accountMappingTypeArr);
        } finally {
            deVarB.c();
            y5Var.e.a();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class SessionPackageMappingType extends AccountMappingType {
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER = "owner";
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES = "packages";
        public static final String JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME = "activity";
        public final String c;
        public final Set<String> d;
        public final String e;

        public SessionPackageMappingType(Context context, Set<String> set, String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_session_packages", a(context, set, str));
            this.d = set;
            this.c = context.getPackageName();
            this.e = str;
        }

        public static String a(Context context, Set<String> set, String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, context.getPackageName());
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, str);
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put(JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES, jSONArray);
            } catch (JSONException e) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.MultipleAccountManager"), "Unable to create session package mapping json.", e);
            }
            return jSONObject.toString();
        }

        @FireOsSdk
        public static SessionPackageMappingType createNewSessionPackageMapping(Context context, Set<String> set, String str) {
            if (context == null || e.a(set) || TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("You cannot pass empty or null parameters when construct a new session package mapping.");
            }
            return new SessionPackageMappingType(context, set, str);
        }

        @FireOsSdk
        public static SessionPackageMappingType createSessionPackageMappingInstance(Context context) {
            if (context != null) {
                return new SessionPackageMappingType(context.getPackageName());
            }
            throw new IllegalArgumentException("You cannot pass null context when construct session package mapping.");
        }

        @FireOsSdk
        public static boolean isSupportedOnThisPlatform(Context context) {
            return qe.m(((i5) t5.a(context).getSystemService("sso_platform")).f382a);
        }

        @FireOsSdk
        public String getCallingPackage() {
            return this.c;
        }

        @FireOsSdk
        public String getRemoveActivityClassName() {
            return this.e;
        }

        @FireOsSdk
        public Set<String> getSessionPackages() {
            return this.d;
        }

        public SessionPackageMappingType(String str) {
            super("com.amazon.dcp.sso.property.account.extratokens.account_session_packages", null);
            this.d = null;
            this.c = str;
            this.e = null;
        }
    }
}
