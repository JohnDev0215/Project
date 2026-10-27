package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.SessionUserChanger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z0 implements w0 {
    public static final String g = "com.amazon.identity.auth.device.z0";
    public static z0 h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f688a;
    public final AmazonAccountManager b;
    public final i5 c;
    public final z5 d;
    public final Map<List<MultipleAccountManager.AccountMappingType>, c6<String>> e;
    public boolean f;

    /* JADX INFO: compiled from: DCP */
    public interface a {
        boolean a();

        boolean a(String str);

        List<e> b(String str);

        List<e> c(String str);

        List<e> d(String str);
    }

    /* JADX INFO: compiled from: DCP */
    public static class b extends d {
        public b(t5 t5Var, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(t5Var, accountMappingType, amazonAccountManager);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return MultipleAccountManager.CustomKeyMappingType.isSupportedOnThisPlatform(this.f690a);
        }

        @Override // com.amazon.identity.auth.device.z0.d
        public List<e> b() {
            String accountMappingValue = this.b.getAccountMappingValue();
            String str = "com.amazon.identity.action.ACCOUNT_FOR_KEY." + accountMappingValue;
            Bundle bundle = new Bundle();
            bundle.putString(MAPAccountManager.KEY_EXTRA_KEY, accountMappingValue);
            return Arrays.asList(new e(str, null, bundle), new e(MAPAccountManager.ACCOUNT_FOR_KEY_HAS_CHANGED_INTENT_ACTION, null, bundle));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class c implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f689a;
        public final AmazonAccountManager b;

        public c(t5 t5Var, AmazonAccountManager amazonAccountManager) {
            this.f689a = t5Var;
            this.b = amazonAccountManager;
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return MultipleAccountManager.PrimaryUserMappingType.isSupportedOnThisPlatform(this.f689a);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> b(String str) {
            ga.a(z0.g);
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> c(String str) {
            Log.e(ga.a(z0.g), "Primary user mapping cannot be removed");
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> d(String str) {
            Log.e(ga.a(z0.g), "Primary user mapping cannot be changed");
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a(String str) {
            return this.b.d(str);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f691a;
        public final String b;
        public final Bundle c;

        public e(String str, String str2, Bundle bundle) {
            this.b = str;
            this.f691a = str2;
            this.c = bundle;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class f extends d {
        public f(t5 t5Var, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(t5Var, accountMappingType, amazonAccountManager);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return MultipleAccountManager.PackageMappingType.isSupportedOnThisPlatform(this.f690a);
        }

        @Override // com.amazon.identity.auth.device.z0.d
        public List<e> b() {
            String str = z0.g;
            String.format("%s PackageMappingLogic will notify other apps by sending action %s", this.f690a.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
            ga.a(str);
            return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, this.b.getAccountMappingValue(), null));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class h implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t5 f692a;
        public final MultipleAccountManager.SessionPackageMappingType b;
        public final AmazonAccountManager c;
        public final BackwardsCompatiableDataStorage d;

        public h(t5 t5Var, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            this.f692a = t5Var;
            if (!(accountMappingType instanceof MultipleAccountManager.SessionPackageMappingType)) {
                throw new IllegalArgumentException("SessionPackageMappingLogic only allows SessionPackageMappingType");
            }
            this.b = (MultipleAccountManager.SessionPackageMappingType) accountMappingType;
            this.d = new BackwardsCompatiableDataStorage(t5Var);
            this.c = amazonAccountManager;
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a(String str) {
            String strD = this.d.d(str, this.b.getAccountMappingType());
            if (!TextUtils.isEmpty(strD)) {
                try {
                    JSONArray jSONArray = new JSONObject(strD).getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
                    if (jSONArray != null) {
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            if (TextUtils.equals(this.b.getCallingPackage(), jSONArray.getString(i))) {
                                return true;
                            }
                        }
                    }
                } catch (JSONException e) {
                    Log.e(ga.a(z0.g), "JSONException when trying to de-serialize the session package mapping json", e);
                }
            }
            return false;
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> b(String str) {
            return b(str, false);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> c(String str) {
            return b(str, true);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> d(String str) {
            Set<String> setE;
            try {
                setE = e(str);
            } catch (JSONException e) {
                Log.e(ga.a(z0.g), "JSONException happened when trying to parse the session package mapping json", e);
                setE = null;
            }
            if (com.amazon.identity.auth.device.e.a(setE)) {
                ga.c(z0.g, "Setting mapping type %s with value %s did not change. Not notifing.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
                return new ArrayList();
            }
            ga.c(z0.g, "Notifying of user change of type %s set. Account for profile %s changed.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
            return a(setE);
        }

        public final Set<String> e(String str) throws JSONException {
            Set<String> setC;
            Set<String> setA = this.c.a();
            HashSet hashSet = new HashSet();
            if (!com.amazon.identity.auth.device.e.a(setA)) {
                JSONObject jSONObjectB = b();
                if (jSONObjectB != null) {
                    try {
                        String string = jSONObjectB.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                        if (!TextUtils.equals(this.f692a.getPackageName(), string)) {
                            throw new MultipleAccountManager.SessionPackageMappingAlreadySetException(string);
                        }
                    } catch (JSONException e) {
                        Log.e(ga.a(z0.g), "JSONException when trying to get session package mapping owner", e);
                    }
                }
                HashMap map = new HashMap();
                for (String str2 : setA) {
                    map.put(str2, a(str2, false));
                }
                for (String str3 : setA) {
                    if (str3.equals(str)) {
                        String accountMappingType = this.b.getAccountMappingType();
                        String accountMappingValue = this.b.getAccountMappingValue();
                        setC = this.b.getSessionPackages();
                        JSONObject jSONObject = (JSONObject) map.get(str3);
                        if (jSONObject == null) {
                            String str4 = z0.g;
                            Log.i(ga.a(str4), "Setting a new session package mapping.");
                            ga.a(str4);
                            this.d.d(str3, accountMappingType, accountMappingValue);
                        } else {
                            HashSet hashSet2 = new HashSet();
                            JSONArray jSONArray = jSONObject.getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                hashSet2.add(jSONArray.getString(i));
                            }
                            if (setC == null ? false : setC.equals(hashSet2)) {
                                Log.i(ga.a(z0.g), "No packages changes to the session package mapping.");
                                this.d.d(str3, accountMappingType, accountMappingValue);
                                setC = null;
                            } else {
                                this.d.d(str3, accountMappingType, accountMappingValue);
                                String str5 = z0.g;
                                String.format("Session package mapping changed. it changed from %s to %s", hashSet2.toString(), setC.toString());
                                ga.a(str5);
                                if (com.amazon.identity.auth.device.e.a(setC)) {
                                    setC = hashSet2;
                                } else if (!com.amazon.identity.auth.device.e.a(hashSet2)) {
                                    HashSet hashSet3 = new HashSet(setC);
                                    hashSet3.removeAll(hashSet2);
                                    HashSet hashSet4 = new HashSet(hashSet2);
                                    hashSet4.removeAll(setC);
                                    hashSet3.addAll(hashSet4);
                                    setC = hashSet3;
                                }
                                String.format("The different packages compared with old session packages mapping: " + setC.toString(), new Object[0]);
                                ga.a(str5);
                            }
                        }
                    } else {
                        setC = c(str3, false);
                    }
                    if (!com.amazon.identity.auth.device.e.a(setC)) {
                        hashSet.addAll(setC);
                    }
                }
            }
            return hashSet;
        }

        public List<e> b(String str, boolean z) {
            Set<String> setC;
            try {
                setC = c(str, z);
            } catch (JSONException e) {
                Log.e(ga.a(z0.g), "JSONException happened when trying to parse the session package mapping json", e);
                setC = null;
            }
            if (com.amazon.identity.auth.device.e.a(setC)) {
                ga.c(z0.g, "Cannot remove mapping type %s with value %s did not change. Not notifing.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
                return new ArrayList();
            }
            ga.c(z0.g, "Notifying of user change of type %s removed. Account for profile %s changed.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
            return a(setC);
        }

        public final Set<String> c(String str, boolean z) throws JSONException {
            JSONObject jSONObjectA = a(str, z);
            if (jSONObjectA == null) {
                ga.a(z0.g);
                return null;
            }
            HashSet hashSet = new HashSet();
            JSONArray jSONArray = jSONObjectA.getJSONArray(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                hashSet.add(jSONArray.getString(i));
            }
            this.d.d(str, this.b.getAccountMappingType(), null);
            return hashSet;
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return MultipleAccountManager.SessionPackageMappingType.isSupportedOnThisPlatform(this.f692a);
        }

        public final JSONObject a(String str, boolean z) {
            String strD = this.d.d(str, this.b.getAccountMappingType());
            if (strD == null) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(strD);
                if (z) {
                    String string = jSONObject.getString(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
                    if (!TextUtils.equals(string, this.b.getCallingPackage())) {
                        throw new MultipleAccountManager.SessionPackageMappingAlreadySetException(string);
                    }
                }
                return jSONObject;
            } catch (JSONException e) {
                Log.e(ga.a(z0.g), "JSONException when trying to de-serialize the session package mapping json", e);
                return null;
            }
        }

        public final JSONObject b() {
            Set<String> setA = this.c.a();
            if (com.amazon.identity.auth.device.e.a(setA)) {
                return null;
            }
            Iterator<String> it = setA.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectA = a(it.next(), false);
                if (jSONObjectA != null) {
                    return jSONObjectA;
                }
            }
            return null;
        }

        public final List<e> a(Set<String> set) {
            ArrayList arrayList = new ArrayList();
            for (String str : set) {
                String str2 = z0.g;
                String.format(Locale.US, "Going to notify package: %s about the account change:", str);
                ga.a(str2);
                arrayList.add(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, str, null));
            }
            return arrayList;
        }
    }

    public z0(Context context) {
        t5 t5VarA = t5.a(context);
        this.f688a = t5VarA;
        this.b = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
        this.c = (i5) t5VarA.getSystemService("sso_platform");
        this.d = new z5(t5VarA);
        this.e = Collections.synchronizedMap(new HashMap());
    }

    public static synchronized z0 a(Context context) {
        if (h == null || xa.a()) {
            h = new z0(context.getApplicationContext());
        }
        return h;
    }

    public final String b(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        List<a> listC = c(accountMappingTypeArr);
        Set<String> setC = this.b.c();
        for (a aVar : (ArrayList) listC) {
            for (String str : setC) {
                if (aVar.a(str)) {
                    return str;
                }
            }
        }
        Log.e(ga.a(g), "No account mapping found for any account, returning null");
        return null;
    }

    public final List<a> c(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        ArrayList arrayList = new ArrayList();
        if (accountMappingTypeArr == null) {
            return arrayList;
        }
        for (MultipleAccountManager.AccountMappingType accountMappingType : accountMappingTypeArr) {
            a aVarC = c(accountMappingType);
            if (aVarC != null) {
                if (aVarC.a()) {
                    arrayList.add(aVarC);
                } else {
                    ga.a(g, "Mapping Type %s is not supported on this platform. Ignoring", accountMappingType.getAccountMappingType());
                }
            }
        }
        return arrayList;
    }

    public boolean a(String str) {
        if (this.b.a(str)) {
            return ((ArrayList) g.a(this.f688a, this.b, str)).size() != 0 || this.b.d(str);
        }
        Log.e(ga.a(g), "The account does not exist so it cannot be a primary");
        return false;
    }

    /* JADX INFO: compiled from: DCP */
    public static class i implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t5 f693a;
        public final AmazonAccountManager b;
        public final SessionUserChanger c;

        public i(t5 t5Var, AmazonAccountManager amazonAccountManager) {
            this.f693a = t5Var;
            this.b = amazonAccountManager;
            this.c = new SessionUserChanger(amazonAccountManager);
        }

        public final List<e> a(boolean z) {
            if (!z) {
                return new ArrayList();
            }
            String str = z0.g;
            String.format("%s creates changed notification and will send action %s", this.f693a.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
            ga.a(str);
            return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, null, null));
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0087  */
        @Override // com.amazon.identity.auth.device.z0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<com.amazon.identity.auth.device.z0.e> b(java.lang.String r8) {
            /*
                r7 = this;
                com.amazon.identity.auth.accounts.AmazonAccountManager r0 = r7.b
                java.lang.String r1 = "com.amazon.dcp.sso.property.sessionuser"
                java.lang.String r0 = r0.a(r8, r1)
                r2 = 0
                r3 = 1
                if (r0 == 0) goto Le
                r0 = r3
                goto Lf
            Le:
                r0 = r2
            Lf:
                if (r0 != 0) goto L22
                java.lang.String r8 = com.amazon.identity.auth.device.z0.g
                java.lang.String r8 = com.amazon.identity.auth.device.ga.a(r8)
                java.lang.String r0 = "Account is not a session user, so cannot remove"
                android.util.Log.w(r8, r0)
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                return r8
            L22:
                java.lang.String r0 = com.amazon.identity.auth.device.z0.g
                com.amazon.identity.auth.device.ga.a(r0)
                com.amazon.identity.auth.accounts.SessionUserChanger r0 = r7.c
                com.amazon.identity.auth.accounts.AmazonAccountManager r4 = r0.f152a
                java.lang.String r4 = r4.a(r8, r1)
                if (r4 == 0) goto L33
                r4 = r3
                goto L34
            L33:
                r4 = r2
            L34:
                if (r4 != 0) goto L4a
                java.lang.String r4 = com.amazon.identity.auth.accounts.SessionUserChanger.c
                java.lang.Object[] r5 = new java.lang.Object[r3]
                r5[r2] = r8
                java.lang.String r6 = "%s is not a session user, so nothing to notify about it being deregistered"
                java.lang.String.format(r6, r5)
                com.amazon.identity.auth.device.ga.a(r4)
                com.amazon.identity.auth.accounts.AmazonAccountManager r0 = r0.f152a
                r0.d()
                goto L98
            L4a:
                java.util.HashSet r4 = new java.util.HashSet
                r4.<init>()
                com.amazon.identity.auth.accounts.AmazonAccountManager r5 = r0.f152a
                java.lang.String r6 = "com.amazon.dcp.sso.property.secondary"
                java.lang.String r5 = r5.a(r8, r6)
                if (r5 == 0) goto L5b
                r5 = r3
                goto L5c
            L5b:
                r5 = r2
            L5c:
                if (r5 != 0) goto L5f
                goto L87
            L5f:
                com.amazon.identity.auth.accounts.AmazonAccountManager r5 = r0.f152a
                java.util.Set r5 = r5.d()
                java.util.HashSet r5 = (java.util.HashSet) r5
                int r5 = r5.size()
                if (r5 == r3) goto L6e
                goto L87
            L6e:
                com.amazon.identity.auth.accounts.AmazonAccountManager r5 = r0.f152a
                java.lang.String r5 = r5.b()
                com.amazon.identity.auth.accounts.AmazonAccountManager r6 = r0.f152a
                boolean r6 = r6.a(r5)
                if (r6 != 0) goto L7e
                r6 = r2
                goto L85
            L7e:
                com.amazon.identity.auth.accounts.AmazonAccountManager r6 = r0.f152a
                boolean r6 = r6.c(r5)
                r6 = r6 ^ r3
            L85:
                if (r6 != 0) goto L88
            L87:
                r5 = 0
            L88:
                if (r5 == 0) goto L8d
                r4.add(r5)
            L8d:
                java.lang.String[] r5 = new java.lang.String[r3]
                r5[r2] = r8
                java.util.Set r5 = com.amazon.identity.auth.device.e.a(r5)
                r0.a(r4, r5)
            L98:
                com.amazon.identity.auth.accounts.AmazonAccountManager r0 = r7.b
                java.lang.String r8 = r0.a(r8, r1)
                if (r8 == 0) goto La1
                r2 = r3
            La1:
                r8 = r2 ^ 1
                java.util.List r8 = r7.a(r8)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z0.i.b(java.lang.String):java.util.List");
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> c(String str) {
            if (this.b.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null) {
                ga.a(z0.g);
                return d(this.b.b());
            }
            Log.w(ga.a(z0.g), "Account is not a session user, so cannot remove");
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> d(String str) {
            if (this.b.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null) {
                Log.w(ga.a(z0.g), "Account is already a session user");
                return new ArrayList();
            }
            SessionUserChanger sessionUserChanger = this.c;
            Set setA = com.amazon.identity.auth.device.e.a(str);
            sessionUserChanger.f152a.d();
            HashSet hashSet = new HashSet();
            for (String str2 : (HashSet) setA) {
                if (!sessionUserChanger.f152a.a(str2) ? false : !sessionUserChanger.f152a.c(str2)) {
                    hashSet.add(str2);
                }
            }
            if (hashSet.size() == 0) {
                Log.e(ga.a(SessionUserChanger.c), "Cannot remove all session users. Change aborted");
            } else {
                Set<String> setD = sessionUserChanger.f152a.d();
                HashSet hashSet2 = new HashSet();
                hashSet2.addAll(hashSet);
                hashSet2.removeAll(setD);
                HashSet hashSet3 = new HashSet();
                hashSet3.addAll(setD);
                hashSet3.removeAll(hashSet);
                sessionUserChanger.a(hashSet2, hashSet3);
            }
            return a(this.b.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return MultipleAccountManager.SessionUserMappingType.isSupportedOnThisPlatform(this.f693a);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a(String str) {
            return this.b.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static abstract class d implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t5 f690a;
        public final MultipleAccountManager.AccountMappingType b;
        public final AmazonAccountManager c;
        public final BackwardsCompatiableDataStorage d;

        public d(t5 t5Var, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            this.f690a = t5Var;
            this.c = amazonAccountManager;
            this.d = new BackwardsCompatiableDataStorage(t5Var);
            this.b = accountMappingType;
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a(String str) {
            Set<String> setA = a(this.d, str, this.b.getAccountMappingType());
            String str2 = z0.g;
            this.b.getAccountMappingValue();
            ga.a(str2);
            HashSet<String> hashSet = (HashSet) setA;
            for (String str3 : hashSet) {
                ga.a(z0.g);
            }
            return hashSet.contains(this.b.getAccountMappingValue());
        }

        public abstract List<e> b();

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> b(String str) {
            return c(str);
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> c(String str) {
            if (e(str)) {
                ga.c(z0.g, "Notifying of user change of type %s removed. Account for profile %s changed.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
                return b();
            }
            ga.c(z0.g, "Cannot remove mapping type %s for key %s did not change. Not notifing.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
            return new ArrayList();
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public List<e> d(String str) {
            if (f(str)) {
                ga.c(z0.g, "Notifying of user change of type %s set. Account for profile %s changed.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
                return b();
            }
            ga.c(z0.g, "Setting mapping type %s for key %s did not change. Not notifing.", this.b.getAccountMappingType(), this.b.getAccountMappingValue());
            return new ArrayList();
        }

        public final boolean e(String str) {
            String accountMappingType = this.b.getAccountMappingType();
            String accountMappingValue = this.b.getAccountMappingValue();
            Set<String> setA = a(this.d, str, accountMappingType);
            String str2 = z0.g;
            ga.a(str2, "Current values of %s before remove are %s", accountMappingType, setA.toString());
            HashSet hashSet = (HashSet) setA;
            if (!hashSet.contains(accountMappingValue)) {
                ga.d(str2, "Cannot remove %s for type %s from account", accountMappingValue, accountMappingType);
                return false;
            }
            hashSet.remove(accountMappingValue);
            ga.a(str2, "Current values of %s after remove are %s", accountMappingType, setA.toString());
            this.d.d(str, accountMappingType, TextUtils.join(",", setA));
            return true;
        }

        public final boolean f(String str) {
            boolean z = false;
            for (String str2 : this.c.a()) {
                if (str2.equals(str)) {
                    String accountMappingType = this.b.getAccountMappingType();
                    String accountMappingValue = this.b.getAccountMappingValue();
                    Set<String> setA = a(this.d, str2, accountMappingType);
                    String str3 = z0.g;
                    boolean z2 = true;
                    ga.a(str3, "Current values for type %s before add are %s", accountMappingType, setA.toString());
                    HashSet hashSet = (HashSet) setA;
                    if (hashSet.contains(accountMappingValue)) {
                        ga.d(str3, "Cannot create mapping of type with value %s to account", accountMappingType, accountMappingValue);
                        z2 = false;
                    } else {
                        hashSet.add(accountMappingValue);
                        ga.a(str3, "Current values for %s after add are %s", accountMappingType, setA.toString());
                        this.d.d(str2, accountMappingType, TextUtils.join(",", setA));
                    }
                    z |= z2;
                } else {
                    e(str2);
                }
            }
            return z;
        }

        public static Set<String> a(u7 u7Var, String str, String str2) {
            String strD = u7Var.d(str, str2);
            HashSet hashSet = new HashSet();
            if (TextUtils.isEmpty(strD)) {
                return hashSet;
            }
            hashSet.addAll(Arrays.asList(strD.split(",")));
            return hashSet;
        }
    }

    public Set<Integer> a(t5 t5Var, String str) {
        Set<String> setA = d.a(new BackwardsCompatiableDataStorage(t5Var, t5Var.a()), str, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
        HashSet hashSet = new HashSet();
        Iterator it = ((HashSet) setA).iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(Integer.valueOf((String) it.next()));
            } catch (NumberFormatException unused) {
                Log.w(ga.a(g), "Ignoring invalid profile id");
            }
        }
        return hashSet;
    }

    /* JADX INFO: compiled from: DCP */
    public static class g extends d {
        public g(t5 t5Var, MultipleAccountManager.AccountMappingType accountMappingType, AmazonAccountManager amazonAccountManager) {
            super(t5Var, accountMappingType, amazonAccountManager);
        }

        public static List<g> a(t5 t5Var, AmazonAccountManager amazonAccountManager, String str) {
            Set<String> setA = d.a(new BackwardsCompatiableDataStorage(t5Var, t5Var.a()), str, "com.amazon.dcp.sso.property.account.extratokens.account_profiles");
            ArrayList arrayList = new ArrayList();
            for (String str2 : (HashSet) setA) {
                Integer numJ = com.amazon.identity.auth.device.e.j(str2);
                if (numJ == null) {
                    ga.b(z0.g, "%s is not a valid profile id", str2);
                } else {
                    arrayList.add(new g(t5Var, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(numJ.intValue()), amazonAccountManager));
                }
            }
            return arrayList;
        }

        @Override // com.amazon.identity.auth.device.z0.d
        public List<e> b() {
            String str = z0.g;
            String.format("%s ProfilePrimaryMappingLogic will notify other apps by sending action %s", this.f690a.getPackageName(), MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION);
            ga.a(str);
            return Arrays.asList(new e(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION, null, null));
        }

        @Override // com.amazon.identity.auth.device.z0.a
        public boolean a() {
            return qe.b(((i5) this.f690a.getSystemService("sso_platform")).f382a);
        }
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean b(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        c();
        List<a> listC = c(accountMappingTypeArr);
        if (this.b.a(str) && !this.b.c(str)) {
            b();
            ArrayList arrayList = new ArrayList();
            Iterator it = ((ArrayList) listC).iterator();
            while (it.hasNext()) {
                arrayList.addAll(((a) it.next()).d(str));
            }
            a(arrayList);
            return arrayList.size() > 0;
        }
        Log.e(ga.a(g), "Cannot set account mappings since it doesn't exist or is deregistering");
        return false;
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean a(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        c();
        List<a> listC = c(accountMappingTypeArr);
        if (!this.b.a(str)) {
            Log.e(ga.a(g), "Cannot remove account mappings since it doesn't exist");
            return false;
        }
        b();
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) listC).iterator();
        while (it.hasNext()) {
            arrayList.addAll(((a) it.next()).c(str));
        }
        a(arrayList);
        return arrayList.size() > 0;
    }

    public final a c(MultipleAccountManager.AccountMappingType accountMappingType) {
        if (accountMappingType == null) {
            Log.e(ga.a(g), "Account Mapping Type given was null. Ignoring");
            return null;
        }
        String accountMappingType2 = accountMappingType.getAccountMappingType();
        if (AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT.equals(accountMappingType2)) {
            return new i(this.f688a, this.b);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_profiles".equals(accountMappingType2)) {
            boolean zB = qe.b(this.c.f382a);
            boolean z = (zB || qe.m(this.f688a) || Integer.toString(0).equals(accountMappingType.getAccountMappingValue())) ? false : true;
            if (!zB && !z) {
                return new c(this.f688a, this.b);
            }
            if (z) {
                yd.b("UsingUnsupportedProfile", new String[0]);
            }
            synchronized (this) {
                if (!this.f) {
                    this.f = true;
                    if (qe.b(this.c.f382a) && t9.f(this.c.f382a)) {
                        String strB = this.b.b();
                        if (!TextUtils.isEmpty(strB)) {
                            g gVar = new g(this.f688a, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(0), this.b);
                            Iterator<String> it = this.b.a().iterator();
                            while (it.hasNext()) {
                                if (gVar.a(it.next())) {
                                }
                            }
                            ga.a(g);
                            gVar.d(strB);
                        }
                    }
                }
                break;
            }
            return new g(this.f688a, accountMappingType, this.b);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_packages".equals(accountMappingType2)) {
            return new f(this.f688a, accountMappingType, this.b);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.custom_keys".equals(accountMappingType2)) {
            return new b(this.f688a, accountMappingType, this.b);
        }
        if ("primary_account_type".equals(accountMappingType2)) {
            return new c(this.f688a, this.b);
        }
        if ("com.amazon.dcp.sso.property.account.extratokens.account_session_packages".equals(accountMappingType2)) {
            return new h(this.f688a, accountMappingType, this.b);
        }
        ga.b(g, "Account mapping type %s was not recongized", accountMappingType2);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x004f A[EDGE_INSN: B:39:0x004f->B:21:0x004f BREAK  A[LOOP:0: B:9:0x0022->B:43:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[LOOP:0: B:9:0x0022->B:43:?, LOOP_END, SYNTHETIC] */
    @Override // com.amazon.identity.auth.device.w0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.content.Intent b(com.amazon.identity.auth.device.api.MultipleAccountManager.AccountMappingType r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType
            if (r0 == 0) goto L8d
            com.amazon.identity.auth.device.t5 r0 = r6.f688a
            com.amazon.identity.auth.device.api.MultipleAccountManager$SessionPackageMappingType r7 = (com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType) r7
            com.amazon.identity.auth.accounts.AmazonAccountManager r1 = r6.b
            boolean r2 = r7 instanceof com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType
            if (r2 == 0) goto L85
            com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage r2 = new com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage
            r2.<init>(r0)
            java.util.Set r0 = r1.a()
            boolean r1 = com.amazon.identity.auth.device.e.a(r0)
            r3 = 0
            if (r1 != 0) goto L4e
            java.util.Iterator r0 = r0.iterator()
        L22:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L4e
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r4 = r7.getAccountMappingType()
            java.lang.String r1 = r2.d(r1, r4)
            if (r1 == 0) goto L4a
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L3e
            r4.<init>(r1)     // Catch: org.json.JSONException -> L3e
            goto L4b
        L3e:
            r1 = move-exception
            java.lang.String r4 = com.amazon.identity.auth.device.z0.g
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)
            java.lang.String r5 = "JSONException when trying to de-serialize the session package mapping json"
            android.util.Log.e(r4, r5, r1)
        L4a:
            r4 = r3
        L4b:
            if (r4 == 0) goto L22
            goto L4f
        L4e:
            r4 = r3
        L4f:
            if (r4 == 0) goto L84
            java.lang.String r7 = "owner"
            java.lang.String r7 = r4.getString(r7)     // Catch: org.json.JSONException -> L78
            java.lang.String r0 = "activity"
            java.lang.String r0 = r4.getString(r0)     // Catch: org.json.JSONException -> L78
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch: org.json.JSONException -> L78
            if (r1 != 0) goto L84
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L78
            if (r1 != 0) goto L84
            android.content.Intent r1 = new android.content.Intent     // Catch: org.json.JSONException -> L78
            r1.<init>()     // Catch: org.json.JSONException -> L78
            java.lang.String r2 = "com.amazon.identity.auth.device.session_package_mapping.remove.action"
            r1.setAction(r2)     // Catch: org.json.JSONException -> L78
            r1.setClassName(r7, r0)     // Catch: org.json.JSONException -> L78
            r3 = r1
            goto L84
        L78:
            r7 = move-exception
            java.lang.String r0 = com.amazon.identity.auth.device.z0.g
            java.lang.String r0 = com.amazon.identity.auth.device.ga.a(r0)
            java.lang.String r1 = "JSONException happens when trying get owner and remove activity of the session package mapping."
            android.util.Log.e(r0, r1, r7)
        L84:
            return r3
        L85:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "SessionPackageMappingLogic only allows SessionPackageMappingType"
            r7.<init>(r0)
            throw r7
        L8d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "getIntentToRemoveAccountMapping() currently only accepts SessionPackageMappingType"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z0.b(com.amazon.identity.auth.device.api.MultipleAccountManager$AccountMappingType):android.content.Intent");
    }

    @Override // com.amazon.identity.auth.device.w0
    public boolean a(String str, MultipleAccountManager.AccountMappingType accountMappingType) {
        a aVarC = c(accountMappingType);
        if (!aVarC.a()) {
            ga.b(g, "Mapping Type %s is not supported on this platform. Ignoring", accountMappingType.getAccountMappingType());
            return false;
        }
        return aVarC.a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x004f A[EDGE_INSN: B:35:0x004f->B:21:0x004f BREAK  A[LOOP:0: B:9:0x0022->B:39:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[LOOP:0: B:9:0x0022->B:39:?, LOOP_END, SYNTHETIC] */
    @Override // com.amazon.identity.auth.device.w0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(com.amazon.identity.auth.device.api.MultipleAccountManager.AccountMappingType r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType
            if (r0 == 0) goto L6d
            com.amazon.identity.auth.device.t5 r0 = r6.f688a
            com.amazon.identity.auth.device.api.MultipleAccountManager$SessionPackageMappingType r7 = (com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType) r7
            com.amazon.identity.auth.accounts.AmazonAccountManager r1 = r6.b
            boolean r2 = r7 instanceof com.amazon.identity.auth.device.api.MultipleAccountManager.SessionPackageMappingType
            if (r2 == 0) goto L65
            com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage r2 = new com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage
            r2.<init>(r0)
            java.util.Set r0 = r1.a()
            boolean r1 = com.amazon.identity.auth.device.e.a(r0)
            r3 = 0
            if (r1 != 0) goto L4e
            java.util.Iterator r0 = r0.iterator()
        L22:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L4e
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r4 = r7.getAccountMappingType()
            java.lang.String r1 = r2.d(r1, r4)
            if (r1 == 0) goto L4a
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L3e
            r4.<init>(r1)     // Catch: org.json.JSONException -> L3e
            goto L4b
        L3e:
            r1 = move-exception
            java.lang.String r4 = com.amazon.identity.auth.device.z0.g
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)
            java.lang.String r5 = "JSONException when trying to de-serialize the session package mapping json"
            android.util.Log.e(r4, r5, r1)
        L4a:
            r4 = r3
        L4b:
            if (r4 == 0) goto L22
            goto L4f
        L4e:
            r4 = r3
        L4f:
            if (r4 == 0) goto L64
            java.lang.String r7 = "owner"
            java.lang.String r3 = r4.getString(r7)     // Catch: org.json.JSONException -> L58
            goto L64
        L58:
            r7 = move-exception
            java.lang.String r0 = com.amazon.identity.auth.device.z0.g
            java.lang.String r0 = com.amazon.identity.auth.device.ga.a(r0)
            java.lang.String r1 = "JSONException happens when trying get owner of the session package mapping."
            android.util.Log.e(r0, r1, r7)
        L64:
            return r3
        L65:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "SessionPackageMappingLogic only allows SessionPackageMappingType"
            r7.<init>(r0)
            throw r7
        L6d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "getAccountMappingOwner() currently only accepts SessionPackageMappingType"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z0.a(com.amazon.identity.auth.device.api.MultipleAccountManager$AccountMappingType):java.lang.String");
    }

    public void b() {
        this.e.clear();
        ga.a(g);
    }

    public void a() {
        boolean z;
        List<e> arrayList;
        if (MultipleAccountManager.SessionPackageMappingType.isSupportedOnThisPlatform(this.f688a)) {
            MultipleAccountManager.AccountMappingType accountMappingTypeCreateSessionPackageMappingInstance = MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(this.f688a);
            String strA = a(accountMappingTypeCreateSessionPackageMappingInstance);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            try {
                z5.a(strA, 64, this.f688a.getPackageManager());
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
                z = false;
            }
            if (z) {
                return;
            }
            String str = g;
            String.format(Locale.US, "Session package mapping owner is: %s, but it is already uninstalled from the device. Going to clear the session package mapping.", strA);
            ga.a(str);
            h hVar = new h(this.f688a, accountMappingTypeCreateSessionPackageMappingInstance, this.b);
            ArrayList arrayList2 = new ArrayList();
            Set<String> setA = hVar.c.a();
            if (!com.amazon.identity.auth.device.e.a(setA)) {
                String accountMappingType = hVar.b.getAccountMappingType();
                for (String str2 : setA) {
                    if (hVar.d.d(str2, accountMappingType) != null) {
                        arrayList = hVar.b(str2, false);
                        break;
                    }
                }
                arrayList = new ArrayList<>();
            } else {
                arrayList = new ArrayList<>();
            }
            arrayList2.addAll(arrayList);
            a(arrayList2);
        }
    }

    public final void c() {
        if (qe.j(this.c.f382a) && !t9.f(this.c.f382a)) {
            throw new IllegalStateException("getAccount write call cannot be called from this app on this platform");
        }
    }

    public final void a(List<e> list) {
        Set<String> set;
        Set<String> set2;
        if (list.size() == 0) {
            return;
        }
        c7.a(this.f688a, new MAPAccountManager(this.f688a).getAccount());
        Set<String> setA = this.d.a();
        HashMap map = new HashMap();
        for (e eVar : list) {
            if (eVar.c != null) {
                Intent intent = new Intent(eVar.b);
                intent.putExtras(eVar.c);
                String str = eVar.f691a;
                if (str == null) {
                    set = setA;
                } else {
                    HashSet hashSet = new HashSet();
                    hashSet.add(str);
                    set = hashSet;
                }
                a(intent, set);
            } else {
                Set hashSet2 = (Set) map.get(eVar.b);
                if (hashSet2 == null) {
                    hashSet2 = new HashSet();
                    map.put(eVar.b, hashSet2);
                }
                String str2 = eVar.f691a;
                if (str2 == null) {
                    set2 = setA;
                } else {
                    HashSet hashSet3 = new HashSet();
                    hashSet3.add(str2);
                    set2 = hashSet3;
                }
                hashSet2.addAll(set2);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            String str3 = (String) entry.getKey();
            a(new Intent(str3), (Set<String>) entry.getValue());
        }
    }

    public final void a(Intent intent, Set<String> set) {
        for (String str : set) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(str);
            this.f688a.sendBroadcast(intent2, AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        }
    }

    @Override // com.amazon.identity.auth.device.w0
    public String a(MultipleAccountManager.AccountMappingType... accountMappingTypeArr) {
        if (!t9.f(this.c.f382a)) {
            return b(accountMappingTypeArr);
        }
        List<MultipleAccountManager.AccountMappingType> listAsList = accountMappingTypeArr == null ? null : Arrays.asList(accountMappingTypeArr);
        c6<String> c6Var = this.e.get(listAsList);
        if (c6Var == null) {
            c6Var = new c6<>(b(accountMappingTypeArr));
            this.e.put(listAsList, c6Var);
        }
        return c6Var.f261a;
    }
}
