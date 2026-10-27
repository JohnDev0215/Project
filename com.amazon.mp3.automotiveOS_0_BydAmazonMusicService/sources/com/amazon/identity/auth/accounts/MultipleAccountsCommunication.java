package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w0;
import com.amazon.identity.auth.device.z0;
import com.amazon.identity.auth.device.z4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MultipleAccountsCommunication implements w0 {
    public static final String c = "com.amazon.identity.auth.accounts.MultipleAccountsCommunication";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f147a;
    public final z4 b;

    /* JADX INFO: compiled from: DCP */
    public static class DoesAccountHaveMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPING = "mapping";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
            Bundle bundle = new Bundle();
            bundle.putString("directedId", str);
            bundle.putString(KEY_ACCOUNT_MAPPING, MultipleAccountsCommunication.b(new MultipleAccountManager.AccountMappingType[]{accountMappingType}));
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            MultipleAccountManager.AccountMappingType[] accountMappingTypeArrA = MultipleAccountsCommunication.a(bundle.getString(KEY_ACCOUNT_MAPPING));
            boolean zA = false;
            if (accountMappingTypeArrA == null || accountMappingTypeArrA.length != 1) {
                Log.e(ga.a(MultipleAccountsCommunication.c), "Did not specify the mappings properly");
            } else {
                zA = z0.a(t5Var).a(string, accountMappingTypeArrA[0]);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", zA);
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetAccountForMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public static Bundle parametersToBundle(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle bundle = new Bundle();
            bundle.putString("mappings", MultipleAccountsCommunication.b(accountMappingTypeArr));
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String strA = z0.a(t5Var).a(MultipleAccountsCommunication.a(bundle.getString("mappings")));
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", strA);
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class RemoveAccountMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle bundle = new Bundle();
            bundle.putString("directedId", str);
            bundle.putString("mappings", MultipleAccountsCommunication.b(accountMappingTypeArr));
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            boolean zA = z0.a(t5Var).a(bundle.getString("directedId"), MultipleAccountsCommunication.a(bundle.getString("mappings")));
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", zA);
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class SetAccountMappingsUserAction implements IPCCommand {
        public static final String KEY_ACCOUNT_MAPPINGS = "mappings";
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_VALUE = "value";

        public static boolean getResult(Bundle bundle) {
            return bundle.getBoolean("value");
        }

        public static Bundle parametersToBundle(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
            Bundle bundle = new Bundle();
            bundle.putString("directedId", str);
            bundle.putString("mappings", MultipleAccountsCommunication.b(accountMappingTypeArr));
            return bundle;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            boolean zB = z0.a(t5Var).b(bundle.getString("directedId"), MultipleAccountsCommunication.a(bundle.getString("mappings")));
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("value", zB);
            return bundle2;
        }
    }

    public MultipleAccountsCommunication(Context context) {
        t5 t5VarA = t5.a(context);
        this.f147a = t5VarA;
        this.b = new z4(t5VarA, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value()));
    }

    @Override // com.amazon.identity.auth.device.w0
    public String a(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return GetAccountForMappingsUserAction.getResult(this.b.a(GetAccountForMappingsUserAction.class, GetAccountForMappingsUserAction.parametersToBundle(accountMappingTypeArr)));
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean b(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return SetAccountMappingsUserAction.getResult(this.b.a(SetAccountMappingsUserAction.class, SetAccountMappingsUserAction.parametersToBundle(str, accountMappingTypeArr)));
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean a(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        return RemoveAccountMappingsUserAction.getResult(this.b.a(RemoveAccountMappingsUserAction.class, RemoveAccountMappingsUserAction.parametersToBundle(str, accountMappingTypeArr)));
    }

    @Override // com.amazon.identity.auth.device.w0
    public Intent b(MultipleAccountManager.AccountMappingType accountMappingType) {
        Log.e(ga.a(c), "getIntentToRemoveAccountMapping() currently is not supported on 1P devices");
        throw new IllegalArgumentException("getIntentToRemoveAccountMapping() currently is not supported on 1P devices");
    }

    public static String b(MultipleAccountManager.AccountMappingType[] accountMappingTypeArr) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (MultipleAccountManager.AccountMappingType accountMappingType : accountMappingTypeArr) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, accountMappingType.getAccountMappingType());
                jSONObject2.put("value", accountMappingType.getAccountMappingValue());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("mappings", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(ga.a(c), "Could not seralize all mappings", e);
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean a(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
        return DoesAccountHaveMappingAction.getResult(this.b.a(DoesAccountHaveMappingAction.class, DoesAccountHaveMappingAction.parametersToBundle(str, accountMappingType)));
    }

    @Override // com.amazon.identity.auth.device.w0
    public String a(MultipleAccountManager.AccountMappingType accountMappingType) {
        Log.e(ga.a(c), "getAccountMappingOwner() currently is not supported on 1P devices");
        throw new IllegalArgumentException("getAccountMappingOwner() currently is not supported on 1P devices");
    }

    public static MultipleAccountManager.AccountMappingType[] a(String str) {
        MultipleAccountManager.AccountMappingType primaryUserMappingType;
        MultipleAccountManager.AccountMappingType customKeyMappingType;
        if (str == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("mappings");
            MultipleAccountManager.AccountMappingType[] accountMappingTypeArr = new MultipleAccountManager.AccountMappingType[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                String string2 = jSONObject.getString("value");
                if (AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT.equals(string)) {
                    primaryUserMappingType = new MultipleAccountManager.SessionUserMappingType();
                } else if ("com.amazon.dcp.sso.property.account.extratokens.account_profiles".equals(string)) {
                    Integer numJ = e.j(string2);
                    if (numJ != null) {
                        primaryUserMappingType = new MultipleAccountManager.ProfileMappingType(numJ.intValue());
                    } else {
                        ga.b(c, "%s is not a valid profile id", string2);
                    }
                } else {
                    if ("com.amazon.dcp.sso.property.account.extratokens.account_packages".equals(string)) {
                        customKeyMappingType = new MultipleAccountManager.PackageMappingType(string2);
                    } else if ("com.amazon.dcp.sso.property.account.extratokens.custom_keys".equals(string)) {
                        customKeyMappingType = new MultipleAccountManager.CustomKeyMappingType(string2);
                    } else {
                        primaryUserMappingType = "primary_account_type".equals(string) ? new MultipleAccountManager.PrimaryUserMappingType() : null;
                    }
                    primaryUserMappingType = customKeyMappingType;
                }
                if (primaryUserMappingType != null) {
                    accountMappingTypeArr[i] = primaryUserMappingType;
                }
            }
            return accountMappingTypeArr;
        } catch (JSONException e) {
            Log.e(ga.a(c), "Could not deserialize all mappings", e);
            return null;
        }
    }
}
