package com.amazon.identity.auth.device.storage;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.o7;
import com.amazon.identity.auth.device.t1;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.v7;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class NonCanonicalDataStorage extends u7 {
    public static final String d = "com.amazon.identity.auth.device.storage.NonCanonicalDataStorage";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f557a;
    public final f b;
    public final t1 c;

    /* JADX INFO: compiled from: DCP */
    public static class GetAccountsAction implements IPCCommand {
        public static final String KEY_VALUES = "values";

        public static Set<String> getResult(Bundle bundle) {
            String[] stringArray = bundle.getStringArray("values");
            HashSet hashSet = new HashSet();
            hashSet.addAll(Arrays.asList(stringArray));
            return hashSet;
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            u7 u7VarA = NonCanonicalDataStorage.a(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putStringArray("values", (String[]) u7VarA.b().toArray(new String[0]));
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetTokenAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString("key");
            u7 u7VarA = NonCanonicalDataStorage.a(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", u7VarA.c(string, string2));
            return bundle2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class GetUserDataAction implements IPCCommand {
        public static final String KEY_DIRECTED_ID = "directedId";
        public static final String KEY_KEY = "key";
        public static final String KEY_VALUE = "value";

        public static String getResult(Bundle bundle) {
            return bundle.getString("value");
        }

        public Bundle performIPCAction(t5 t5Var, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("directedId");
            String string2 = bundle.getString("key");
            u7 u7VarA = NonCanonicalDataStorage.a(t5Var);
            Bundle bundle2 = new Bundle();
            bundle2.putString("value", u7VarA.d(string, string2));
            return bundle2;
        }
    }

    public NonCanonicalDataStorage(Context context) {
        t5 t5VarA = t5.a(context);
        this.f557a = t5VarA;
        this.b = e.b(t5VarA);
        this.c = e.c(t5VarA);
    }

    public static u7 a(Context context) {
        u7 u7VarA = ((v7) t5.a(context).getSystemService("dcp_data_storage_factory")).a();
        if (u7VarA instanceof NonCanonicalDataStorage) {
            throw new IllegalStateException("Invalid datastorage");
        }
        return u7VarA;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        return this.b.c();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        throw f("Cannot call getActors on NonCanonicalStorage.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        if (TextUtils.equals(str2, AccountConstants.KEY_ACCOUNT_UUID) || TextUtils.equals(str2, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT) || TextUtils.equals(str2, AccountConstants.KEY_ACCOUNT_STATUS)) {
            return CustomerAttributeStore.getValueOrAttributeDefault(this.c.a(str, str2));
        }
        throw f("Cannot call getUserData on NonCanonicalDataStorage for: " + str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
        throw f("Cannot call sync dirty data on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        throw f("Cannot call device data operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        throw f("Cannot call getToken on NonCanonicalStorage. Please use MAP's API instead.");
    }

    public final RuntimeException f(String str) {
        Log.e(ga.a(d), str);
        return new IllegalStateException(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        throw f("Cannot call device data operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void c(String str, String str2, String str3) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        throw new UnsupportedOperationException("getAccountForDirectedId not supported on NonCanonicalDataStorage.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        Log.i(ga.a(d), "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        throw f("getAccountNames not supported in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        throw new UnsupportedOperationException("replaceAccounts not supported on NonCanonicalDataStorage.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        throw f("Cannot call write operations on data storage in non-canonical process");
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        throw f("Cannot get all token keys in non-canonical process");
    }
}
