package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n1 implements t1 {
    public static final Uri e = Uri.parse("content://amazon_customer_attribute_store");
    public static final Uri f = Uri.parse("content://amazon_customer_attribute_store_directboot");
    public static final List<String> g = Arrays.asList("bundle_value");
    public static final String h = n1.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f444a;
    public final i5 b;
    public final s5 c;
    public final boolean d;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f445a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Bundle c;
        public final /* synthetic */ EnumSet d;
        public final /* synthetic */ s2 e;

        public a(String str, String str2, Bundle bundle, EnumSet enumSet, s2 s2Var) {
            this.f445a = str;
            this.b = str2;
            this.c = bundle;
            this.d = enumSet;
            this.e = s2Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObjectA = n1.this.a("getAttribute", this.f445a, this.b, this.c, this.d);
            if (jSONObjectA == null) {
                e.a(this.e, MAPError.CommonError.INTERNAL_ERROR, "Cannot construct command", 1, "Cannot construct command");
                return;
            }
            try {
                Bundle bundleA = n1.this.a(jSONObjectA);
                String str = String.format("Key %s not supported", this.b);
                n1.a(n1.this, this.b, this.e, bundleA, 2, str, MAPError.AttributeError.KEY_NOT_FOUND, str);
            } catch (RemoteMAPException e) {
                Log.e(ga.a(n1.h), "Failed to call getAttribute", e);
                e.a(this.e, MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "Failed to call getAttribute", 4, "Failed to call getAttribute");
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f446a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ s2 d;

        public b(String str, String str2, String str3, s2 s2Var) {
            this.f446a = str;
            this.b = str2;
            this.c = str3;
            this.d = s2Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString(this.f446a, this.b);
            JSONObject jSONObjectA = n1.this.a("setAttribute", this.c, this.f446a, bundle, null);
            if (jSONObjectA == null) {
                e.a(this.d, MAPError.CommonError.INTERNAL_ERROR, "Cannot construct command", 1, "Cannot construct command");
                return;
            }
            Bundle bundleA = null;
            try {
                bundleA = n1.this.a(jSONObjectA);
            } catch (RemoteMAPException e) {
                Log.e(ga.a(n1.h), "Failed to setAttribute in central customer attribute store", e);
            }
            String str = String.format("Unable to set the attribute for key: %s.", this.f446a);
            n1.a(n1.this, this.f446a, this.d, bundleA, 5, str, MAPError.AttributeError.SET_ATTRIBUTE_FAILED, str);
        }
    }

    public n1(t5 t5Var, i5 i5Var, s5 s5Var, boolean z) {
        this.f444a = t5Var;
        this.b = i5Var;
        this.c = s5Var;
        this.d = z;
    }

    public static void a(n1 n1Var, String str, s2 s2Var, Bundle bundle, int i, String str2, MAPError mAPError, String str3) {
        n1Var.getClass();
        if (bundle == null) {
            e.a(s2Var, mAPError, str3, i, str2);
        } else if (bundle.containsKey("error_code_key")) {
            s2Var.onError(bundle);
        } else {
            s2Var.onSuccess(bundle);
        }
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, y5 y5Var) {
        s2 s2Var = new s2(callback);
        va.f637a.execute(new a(str, str2, bundle, enumSet, s2Var));
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.t1
    public Bundle a(String str, String str2) {
        JSONObject jSONObjectA = a("peekAttribute", str, str2, null, null);
        if (jSONObjectA == null) {
            Log.e(ga.a(h), "Failed to construct peek attribute command");
            return null;
        }
        try {
            Bundle bundleA = a(jSONObjectA);
            return bundleA == null ? e.a(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "CustomerAttributeStore returned null", 4, "CustomerAttributeStore returned null") : bundleA;
        } catch (RemoteMAPException e2) {
            Log.e(ga.a(h), "Failed to call peekAttribute", e2);
            return e.a(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, "Failed to call peekAttribute", 4, "Failed to call peekAttribute");
        }
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, String str3, Callback callback) {
        s2 s2Var = new s2(callback);
        va.f637a.execute(new b(str2, str3, str, s2Var));
        return s2Var;
    }

    public final Bundle a(JSONObject jSONObject) throws RemoteMAPException {
        String string = jSONObject.toString();
        if (this.b.e()) {
            Log.i(ga.a(h), String.format("%s try get customer attribute in direct mode for %s", this.f444a.getPackageName(), jSONObject.optString("key")));
            s5 s5Var = this.c;
            Uri uri = f;
            return (Bundle) s5Var.a(uri, new o1(this, uri, string));
        }
        String str = h;
        String.format("%s try get customer attribute out of direct mode fo %s", this.f444a.getPackageName(), jSONObject.optString("key"));
        ga.a(str);
        s5 s5Var2 = this.c;
        Uri uri2 = e;
        return (Bundle) s5Var2.a(uri2, new o1(this, uri2, string));
    }

    public final JSONObject a(String str, String str2, String str3, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet) {
        Account accountA;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("command", str);
            jSONObject.put("directedId", str2);
            if (this.d && (accountA = r9.a(this.f444a, str2)) != null) {
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE, accountA.type);
                jSONObject.put("accountName", accountA.name);
            }
            jSONObject.put("key", str3);
            jSONObject.put("bundleInfo", ma.a(bundle));
            if (enumSet != null) {
                jSONObject.put("getOptions", CustomerAttributeStore.GetAttributeOptions.serializeList(enumSet));
            }
            return jSONObject;
        } catch (JSONException e2) {
            Log.e(ga.a(h), "Error creating Customer Attribute IPC Command", e2);
            return null;
        }
    }
}
