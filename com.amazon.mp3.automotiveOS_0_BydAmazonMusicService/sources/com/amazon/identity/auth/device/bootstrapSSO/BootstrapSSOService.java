package com.amazon.identity.auth.device.bootstrapSSO;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.l2;
import com.amazon.identity.auth.device.m2;
import com.amazon.identity.auth.device.q2;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.device.z9;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class BootstrapSSOService extends Service {
    public static final String b = BootstrapSSOService.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IBinder f250a = new a();

    /* JADX INFO: compiled from: DCP */
    public class a extends IBootstrapSSOService.Stub {
        public a() {
        }

        @Override // com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService
        public Bundle bootstrapForPackage(Bundle bundle) throws RemoteException {
            String[] packagesForUid;
            boolean zContains;
            Context applicationContext = BootstrapSSOService.this.getApplicationContext();
            String string = bundle.getString("appPackageName");
            ArrayList<String> stringArrayList = bundle.getStringArrayList(MAPAccountManager.KEY_SSO_REQUIRED_ADDITIONAL_DATA);
            BootstrapSSOService bootstrapSSOService = BootstrapSSOService.this;
            MAPAccountManager mAPAccountManager = new MAPAccountManager(applicationContext);
            l2 l2Var = new l2(applicationContext, new PandaServiceAccessor(applicationContext));
            bootstrapSSOService.getClass();
            String primaryAccount = mAPAccountManager.getPrimaryAccount();
            if (primaryAccount == null) {
                return bootstrapSSOService.a(MAPAccountManager.BootstrapError.NO_ACCOUNT.value(), "No Account Registered");
            }
            int callingUid = Binder.getCallingUid();
            PackageManager packageManager = applicationContext.getPackageManager();
            try {
                packagesForUid = packageManager.getPackagesForUid(callingUid);
            } catch (Exception e) {
                Log.w(ga.a(BootstrapSSOService.b), "PackageManager call failed; retrying", e);
                yd.a("PackageManagerError", new String[0]);
                packagesForUid = packageManager.getPackagesForUid(callingUid);
            }
            if (packagesForUid == null) {
                Log.e(ga.a(BootstrapSSOService.b), "Could not get packages for uid");
                yd.a("PackageManagerErrorAfterRetry", new String[0]);
                zContains = false;
            } else {
                zContains = Arrays.asList(packagesForUid).contains(string);
            }
            if (!zContains) {
                return bootstrapSSOService.a(MAPAccountManager.BootstrapError.FRAUDULENT_PACKAGE.value(), "Package name does not match caller");
            }
            Set<String> setA = q2.a(applicationContext, string);
            if (setA.isEmpty()) {
                return bootstrapSSOService.a(MAPAccountManager.BootstrapError.NO_SIGNATURE.value(), "Signature couldn't be obtained");
            }
            y5 y5Var = new y5("BootstrapSSO");
            m2 m2Var = new m2(applicationContext, primaryAccount, setA.iterator().next(), q2.a(applicationContext, applicationContext.getPackageName()).iterator().next(), new TokenManagement(applicationContext), string, stringArrayList);
            s2 s2Var = new s2(null);
            try {
                JSONObject jSONObject = l2Var.b.a(primaryAccount, m2Var, y5Var).b;
                String string2 = jSONObject.getString(MAPAccountManager.KEY_SSO_CODE);
                long j = Long.parseLong(jSONObject.getString("expiresIn"));
                JSONArray jSONArray = jSONObject.getJSONArray("listOfAccounts");
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                int i = 0;
                while (i < length) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string3 = jSONObject2.getString("directedId");
                    String string4 = jSONObject2.getString("loginName");
                    JSONArray jSONArray2 = jSONArray;
                    String string5 = jSONObject2.getString("customerName");
                    if (!e.i(string3) && !e.i(string4) && !e.i(string5)) {
                        HashMap map = new HashMap();
                        int i2 = length;
                        map.put(MAPAccountManager.KEY_SSO_ACCOUNT_DIRECTED_ID, string3);
                        map.put(MAPAccountManager.KEY_SSO_ACCOUNT_LOGIN, string4);
                        map.put(MAPAccountManager.KEY_SSO_ACCOUNT_CUSTOMER_NAME, string5);
                        JSONObject jSONObjectA = da.a(jSONObject2, MAPAccountManager.KEY_SSO_ACCOUNT_BUSINESS_DATA);
                        if (jSONObjectA != null) {
                            map.put(MAPAccountManager.KEY_SSO_ACCOUNT_IS_BUSINESS, String.valueOf(da.a(jSONObjectA, "isBusiness", false)));
                        }
                        arrayList.add(map);
                        i++;
                        jSONArray = jSONArray2;
                        length = i2;
                    }
                    l2Var.a(s2Var, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), "Response contains empty fields");
                    break;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(MAPAccountManager.KEY_SSO_CODE, string2);
                bundle2.putLong(MAPAccountManager.KEY_SSO_CODE_TIME_TO_LIVE, j);
                bundle2.putSerializable(MAPAccountManager.KEY_SSO_ACCOUNTS_LIST, arrayList);
                t5 t5Var = m2Var.f391a;
                bundle2.putString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_TYPE, z9.c(t5Var, t5Var.getPackageName()));
                bundle2.putString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_SERIAL, m2Var.b.a());
                s2Var.a(1, bundle2);
            } catch (PandaServiceAccessor.PandaServiceException e2) {
                Log.e(ga.a(l2.c), "service exception when calling panda", e2);
                l2Var.a(s2Var, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), e2.getMessage());
            } catch (IOException e3) {
                Log.e(ga.a(l2.c), "i/o exception when calling panda", e3);
                l2Var.a(s2Var, MAPAccountManager.BootstrapError.NETWORK_FAILURE.value(), e3.getMessage());
            } catch (JSONException e4) {
                Log.e(ga.a(l2.c), "Json exception when calling panda", e4);
                l2Var.a(s2Var, MAPAccountManager.BootstrapError.INVALID_RESPONSE.value(), e4.getMessage());
            }
            try {
                Bundle bundle3 = s2Var.get();
                bundle3.putBoolean("bootstrapSuccess", true);
                return bundle3;
            } catch (MAPCallbackErrorException e5) {
                return e5.getErrorBundle();
            } catch (InterruptedException e6) {
                Log.e(ga.a(BootstrapSSOService.b), "Bootstrap call was interrupted", e6);
                return bootstrapSSOService.a(MAPAccountManager.BootstrapError.UNCATEGORIZED_ERROR.value(), "Bootstrap call was interrupted");
            } catch (ExecutionException e7) {
                Log.e(ga.a(BootstrapSSOService.b), "Unexpected error calling bootstrap", e7);
                return bootstrapSSOService.a(MAPAccountManager.BootstrapError.UNCATEGORIZED_ERROR.value(), "Unexpected error calling bootstrap");
            }
        }
    }

    public final Bundle a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("bootstrapSuccess", false);
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        return bundle;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ga.a(b);
        return this.f250a;
    }
}
