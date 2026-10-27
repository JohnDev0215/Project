package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.PendingIntentWrapper;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.storage.DatabaseCleaner;
import com.amazon.identity.auth.device.storage.DirtyDataSyncingService;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.u7;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class y7 extends u7 {
    public static y7 g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f668a;
    public final LocalDataStorage b;
    public final k8 c;
    public final LambortishClock d;
    public final MAPApplicationInformationQueryer e;
    public static final Set<String> f = new HashSet(Arrays.asList(e("dcp.third.party.device.state", "serial.number"), e("dcp.only.protected.store", "dcp.only.encrypt.key")));
    public static final Executor h = new a6(Executors.newFixedThreadPool(1));

    /* JADX INFO: compiled from: DCP */
    public class a implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Collection f669a;
        public final /* synthetic */ Date b;

        public a(Collection collection, Date date) {
            this.f669a = collection;
            this.b = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.b(this.f669a);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "SetBulkData";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            SQLiteDatabase sQLiteDatabaseA;
            Throwable th;
            LocalDataStorage localDataStorage = y7.this.b;
            Date date = this.b;
            synchronized (localDataStorage) {
                if (date != null) {
                    synchronized (localDataStorage) {
                        try {
                            sQLiteDatabaseA = d8.a(localDataStorage.b);
                        } catch (Throwable th2) {
                            sQLiteDatabaseA = null;
                            th = th2;
                        }
                        try {
                            sQLiteDatabaseA.beginTransaction();
                            localDataStorage.a(sQLiteDatabaseA, "accounts", "account_timestamp", "account_dirty", date);
                            localDataStorage.a(sQLiteDatabaseA, "userdata", "userdata_timestamp", "userdata_dirty", date);
                            localDataStorage.a(sQLiteDatabaseA, "tokens", "token_timestamp", "token_dirty", date);
                            localDataStorage.a(sQLiteDatabaseA, "device_data", "device_data_timestamp", "device_data_dirty", date);
                            sQLiteDatabaseA.setTransactionSuccessful();
                            localDataStorage.b(sQLiteDatabaseA);
                            localDataStorage.b.close();
                            Map<String, x7<m7>> map = localDataStorage.e;
                            if (map != null) {
                                for (x7<m7> x7Var : map.values()) {
                                    if (!x7Var.b.after(date)) {
                                        x7Var.c = false;
                                    }
                                    for (x7<String> x7Var2 : x7Var.f659a.c.values()) {
                                        if (!x7Var2.b.after(date)) {
                                            x7Var2.c = false;
                                        }
                                    }
                                    for (x7<String> x7Var3 : x7Var.f659a.d.values()) {
                                        if (!x7Var3.b.after(date)) {
                                            x7Var3.c = false;
                                        }
                                    }
                                }
                            }
                            Map<String, Map<String, x7<String>>> map2 = localDataStorage.d;
                            if (map2 != null) {
                                Iterator<Map<String, x7<String>>> it = map2.values().iterator();
                                while (it.hasNext()) {
                                    for (x7<String> x7Var4 : it.next().values()) {
                                        if (!x7Var4.b.after(date)) {
                                            x7Var4.c = false;
                                        }
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (sQLiteDatabaseA != null) {
                                localDataStorage.b(sQLiteDatabaseA);
                                localDataStorage.b.close();
                            }
                            throw th;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f671a;
        public final /* synthetic */ Date b;

        public c(String str, Date date) {
            this.f671a = str;
            this.b = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.a(this.f671a, this.b);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "RemovedAccount";
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            SQLiteDatabase sQLiteDatabaseA;
            x7<m7> x7Var;
            LocalDataStorage localDataStorage = y7.this.b;
            String str = this.f671a;
            Date date = this.b;
            synchronized (localDataStorage) {
                com.amazon.identity.auth.device.e.a((Object) str, "directedId");
                com.amazon.identity.auth.device.e.a(date, "dateTime");
                synchronized (localDataStorage) {
                    SQLiteDatabase sQLiteDatabase = null;
                    x7Var = null;
                    try {
                        sQLiteDatabaseA = d8.a(localDataStorage.b);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        sQLiteDatabaseA.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("account_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("accounts", contentValues, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "directed_id", "account_timestamp", "account_deleted", "account_dirty"), new String[]{str, Long.toString(date.getTime())});
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("userdata_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("userdata", contentValues2, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "userdata_account_id", "userdata_timestamp", "userdata_deleted", "userdata_dirty"), new String[]{str, Long.toString(date.getTime())});
                        ContentValues contentValues3 = new ContentValues();
                        contentValues3.put("token_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("tokens", contentValues3, String.format("%s = ? and %s = ? and %s = 1 and %s = 1", "token_account_id", "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, Long.toString(date.getTime())});
                        sQLiteDatabaseA.setTransactionSuccessful();
                        localDataStorage.b(sQLiteDatabaseA);
                        localDataStorage.b.close();
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase = sQLiteDatabaseA;
                        if (sQLiteDatabase != null) {
                            localDataStorage.b(sQLiteDatabase);
                            localDataStorage.b.close();
                        }
                        throw th;
                    }
                }
            }
            Map<String, x7<m7>> map = localDataStorage.e;
            if (map != null) {
                x7Var = map.get(str);
            }
            if (x7Var != null) {
                if (x7Var.b.equals(date)) {
                    x7Var.c = false;
                }
                for (x7<String> x7Var2 : x7Var.f659a.c.values()) {
                    if (x7Var2.b.equals(date)) {
                        x7Var2.c = false;
                    }
                }
                for (x7<String> x7Var3 : x7Var.f659a.d.values()) {
                    if (x7Var3.b.equals(date)) {
                        x7Var3.c = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f672a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ Date d;

        public d(String str, String str2, String str3, Date date) {
            this.f672a = str;
            this.b = str2;
            this.c = str3;
            this.d = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.c(this.f672a, this.b, this.c, this.d);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "SetUserdata";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            y7.this.b.b(this.f672a, this.b, this.d);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f673a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ Date d;

        public e(String str, String str2, String str3, Date date) {
            this.f673a = str;
            this.b = str2;
            this.c = str3;
            this.d = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.b(this.f673a, this.b, this.c, this.d);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "SetToken";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            y7.this.b.a(this.f673a, this.b, this.d);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class g implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f675a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Date c;

        public g(String str, String str2, Date date) {
            this.f675a = str;
            this.b = str2;
            this.c = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.a(this.f675a, this.b, this.c);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "ExpireToken";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            x7<String> x7Var;
            LocalDataStorage localDataStorage = y7.this.b;
            String str = this.f675a;
            String str2 = this.b;
            Date date = this.c;
            synchronized (localDataStorage) {
                com.amazon.identity.auth.device.e.a((Object) str, "directedId");
                com.amazon.identity.auth.device.e.a((Object) str2, "key");
                com.amazon.identity.auth.device.e.a(date, "dateTime");
                synchronized (localDataStorage) {
                    try {
                        SQLiteDatabase sQLiteDatabaseA = d8.a(localDataStorage.b);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("token_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("tokens", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 1 and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
                    } finally {
                        localDataStorage.b.close();
                    }
                }
            }
            Map<String, x7<m7>> map = localDataStorage.e;
            x7<m7> x7Var2 = map == null ? null : map.get(str);
            if (x7Var2 != null && (x7Var = x7Var2.f659a.d.get(str2)) != null && x7Var.f659a == null && x7Var.b.equals(date)) {
                x7Var.c = false;
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class h implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f676a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ Date d;

        public h(String str, String str2, String str3, Date date) {
            this.f676a = str;
            this.b = str2;
            this.c = str3;
            this.d = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            return j8Var.a(this.f676a, this.b, this.c, this.d);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "SetDeviceData";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            Map<String, x7<String>> map;
            x7<String> x7Var;
            LocalDataStorage localDataStorage = y7.this.b;
            String str = this.f676a;
            String str2 = this.b;
            Date date = this.d;
            synchronized (localDataStorage) {
                com.amazon.identity.auth.device.e.a((Object) str, "namespace");
                com.amazon.identity.auth.device.e.a((Object) str2, "key");
                com.amazon.identity.auth.device.e.a(date, "dateTime");
                synchronized (localDataStorage) {
                    try {
                        SQLiteDatabase sQLiteDatabaseA = d8.a(localDataStorage.b);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("device_data_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("device_data", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 0 and %s = 1", "device_data_namespace", "device_data_key", "device_data_timestamp", "device_data_deleted", "device_data_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
                    } finally {
                        localDataStorage.b.close();
                    }
                }
            }
            Map<String, Map<String, x7<String>>> map2 = localDataStorage.d;
            if (map2 != null && (map = map2.get(str)) != null && (x7Var = map.get(str2)) != null && x7Var.b.equals(date)) {
                x7Var.c = false;
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface i {
        void a();

        boolean a(j8 j8Var);

        String b();
    }

    public y7(Context context) {
        t5 t5VarA = t5.a(context);
        this.f668a = t5VarA;
        this.b = (LocalDataStorage) t5VarA.getSystemService("sso_local_datastorage");
        this.c = new k8(t5VarA);
        this.d = LambortishClock.a(t5VarA);
        this.e = MAPApplicationInformationQueryer.a(t5VarA);
    }

    public static synchronized y7 a(Context context) {
        if (g == null || xa.a()) {
            g = new y7(context.getApplicationContext());
        }
        return g;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized Set<String> b() {
        d();
        return this.b.b();
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized Set<String> c(String str) {
        HashSet hashSet;
        x7<m7> x7Var;
        d();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            hashSet = new HashSet();
            if (!TextUtils.isEmpty(str) && (x7Var = localDataStorage.a().get(str)) != null && !x7Var.d) {
                for (Map.Entry<String, x7<String>> entry : x7Var.f659a.c.entrySet()) {
                    if (entry.getKey().startsWith("actor/")) {
                        hashSet.add(entry.getValue().f659a);
                    }
                }
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized String d(String str, String str2) {
        d();
        return this.b.c(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void e() {
        ga.a("DistributedDataStorage");
        try {
        } catch (Exception e2) {
            Log.w(ga.a("DistributedDataStorage"), "Failed to initialize DatabaseCleaner", e2);
        }
        if (this.b.d().isEmpty()) {
            ga.a("DistributedDataStorage");
        } else {
            Log.i(ga.a("DistributedDataStorage"), "Data to delete in the local app. Setting up alarm to clean database");
            new DatabaseCleaner(this.f668a).a();
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void f() {
        Collection<Map<String, String>> collectionA;
        d();
        Date dateA = this.d.a();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            collectionA = localDataStorage.a(dateA, EnumSet.of(LocalDataStorage.GetDataOptions.DirtyOnly));
        }
        if (collectionA.size() == 0) {
            ga.a("DistributedDataStorage");
        } else {
            b(new a(collectionA, dateA));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x010f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0096 A[SYNTHETIC] */
    @Override // com.amazon.identity.auth.device.u7
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String b(java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.y7.b(java.lang.String, java.lang.String):java.lang.String");
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void d(String str, String str2, String str3) {
        boolean zA;
        d();
        if (TextUtils.equals(str3, this.b.c(str, str2))) {
            ga.a("DistributedDataStorage");
            return;
        }
        Date dateA = this.d.a();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            zA = localDataStorage.a(new o7(str, Collections.singletonMap(str2, str3), null, null), dateA, false);
        }
        if (!zA) {
            Log.e(ga.a("DistributedDataStorage"), "Setting the userdata was not successful");
        } else {
            b(new d(str, str2, str3, dateA));
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        Log.e(ga.a("DistributedDataStorage"), "Replace accounts not supported");
        return false;
    }

    public void a(Collection<Map<String, String>> collection) {
        Iterator<Map<String, String>> it = collection.iterator();
        long j = -1;
        while (it.hasNext()) {
            try {
                long j2 = Long.parseLong(it.next().get("timestamp_key"));
                if (j2 > j) {
                    j = j2;
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (j == -1) {
            Log.e(ga.a("DistributedDataStorage"), "Not able to find a timestamp from the DB snapshot used to initialize the current apps");
        } else {
            this.d.a(new Date(j));
        }
    }

    public static String e(String str, String str2) {
        return str + "#" + str2;
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void e(String str) {
        d();
        Date dateA = this.d.a();
        if (!this.b.a(str, dateA, false)) {
            Log.e(ga.a("DistributedDataStorage"), "Removing the account was not successful");
        } else {
            b(new c(str, dateA));
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        HashSet hashSet;
        d();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            Map<String, x7<m7>> mapA = localDataStorage.a();
            hashSet = new HashSet();
            for (x7<m7> x7Var : mapA.values()) {
                if (!x7Var.d) {
                    hashSet.add(x7Var.f659a.b);
                }
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void d() {
        if (Boolean.valueOf(new f8(this.f668a, "distributed.datastore.info.store", 0).f313a.getBoolean("distributed.datastore.init.key", false)).booleanValue()) {
            return;
        }
        this.f668a.getPackageName();
        ga.a("DistributedDataStorage");
        Collection<Map<String, String>> collection = null;
        for (j5 j5Var : (ArrayList) MAPApplicationInformationQueryer.a(this.f668a).c()) {
            if (!j5Var.e()) {
                try {
                    j5Var.a();
                    Integer num = j5Var.j;
                    if (num != null && 3 <= num.intValue()) {
                        try {
                            ga.a("DistributedDataStorage");
                            j5Var.toString();
                            ga.a("DistributedDataStorage");
                            j8 j8Var = new j8(this.f668a, j5Var);
                            Uri uriA = g8.a(j8Var.c.c, "/all_data");
                            Collection<Map<String, String>> collection2 = (Collection) j8Var.b.a(uriA, new i8(j8Var, uriA));
                            collection = collection2;
                            if (collection2 != null) {
                                break;
                            }
                        } catch (RemoteMAPException unused) {
                            Log.w(ga.a("DistributedDataStorage"), "Failed to get all data from the package");
                            MAPApplicationInformationQueryer.a(this.f668a).d();
                        }
                    } else {
                        ga.a("DistributedDataStorage");
                    }
                } catch (RemoteMAPException unused2) {
                    Log.w(ga.a("DistributedDataStorage"), "Failed to get MAP init version");
                    MAPApplicationInformationQueryer.a(this.f668a).d();
                }
            }
        }
        if (collection == null) {
            Log.i(ga.a("DistributedDataStorage"), "Did not find another MAP application to get initial data from.");
        } else {
            LocalDataStorage localDataStorage = this.b;
            synchronized (localDataStorage) {
                synchronized (localDataStorage) {
                    Context context = localDataStorage.f556a;
                    if (context != null) {
                        context.deleteDatabase("map_data_storage.db");
                    }
                    localDataStorage.e = null;
                    localDataStorage.d = null;
                }
                a(collection);
            }
            localDataStorage.b(collection);
            a(collection);
        }
        new f8(this.f668a, "distributed.datastore.info.store", 0).a("distributed.datastore.init.key", Boolean.TRUE);
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized String c(String str, String str2) {
        d();
        return this.b.b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void c(String str, String str2, String str3) {
        boolean zA;
        d();
        Date dateA = this.d.a();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            zA = localDataStorage.a(new o7(str, null, Collections.singletonMap(str2, str3), null), dateA, false);
        }
        if (!zA) {
            Log.e(ga.a("DistributedDataStorage"), "Setting the token was not successful");
        } else {
            b(new e(str, str2, str3, dateA));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class f implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Map f674a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Date c;
        public final /* synthetic */ Map d;

        public f(Map map, String str, Date date, Map map2) {
            this.f674a = map;
            this.b = str;
            this.c = date;
            this.d = map2;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            Map map = this.f674a;
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    if (!j8Var.b(this.b, (String) entry.getKey(), (String) entry.getValue(), this.c)) {
                        return false;
                    }
                }
            }
            Map map2 = this.d;
            if (map2 == null) {
                return true;
            }
            for (Map.Entry entry2 : map2.entrySet()) {
                if (!j8Var.c(this.b, (String) entry2.getKey(), (String) entry2.getValue(), this.c)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "SetData";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            Map map = this.f674a;
            if (map != null) {
                Iterator it = map.keySet().iterator();
                while (it.hasNext()) {
                    y7.this.b.a(this.b, (String) it.next(), this.c);
                }
            }
            Map map2 = this.d;
            if (map2 != null) {
                Iterator it2 = map2.keySet().iterator();
                while (it2.hasNext()) {
                    y7.this.b.b(this.b, (String) it2.next(), this.c);
                }
            }
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized boolean a(String str, o7 o7Var, u7.a aVar) {
        Collection<Map<String, String>> collectionA;
        boolean zA;
        d();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            collectionA = localDataStorage.a((Date) null, EnumSet.noneOf(LocalDataStorage.GetDataOptions.class));
        }
        a(collectionA);
        Date dateA = this.d.a();
        zA = this.b.a(str, o7Var, dateA, false);
        h.execute(new z7(this, new b(str, o7Var, dateA), aVar));
        return zA;
    }

    public final boolean c(i iVar) {
        boolean z = true;
        for (j5 j5Var : (ArrayList) this.e.a()) {
            if (!j5Var.e()) {
                ga.a("DistributedDataStorage", "Propogating action %s to package %s from package %s", iVar.b(), j5Var.b, this.f668a.getPackageName());
                j8 j8Var = new j8(this.f668a, j5Var);
                int i2 = 0;
                boolean z2 = false;
                boolean zA = false;
                do {
                    i2++;
                    try {
                        zA = iVar.a(j8Var);
                        z2 = true;
                    } catch (RuntimeException e2) {
                        Log.e(ga.a("DistributedDataStorage"), String.format("Package threw runtime exception while propogating action %s", iVar.b()), e2);
                    }
                    if (z2) {
                        break;
                    }
                } while (i2 < 2);
                if (!zA) {
                    Log.w(ga.a("DistributedDataStorage"), String.format("Failed action %s with remote package.", iVar.b()));
                }
                z &= zA;
            }
        }
        if (z) {
            String.format("Action %s was synced to all other MAP instances successfully", iVar.b());
            ga.a("DistributedDataStorage");
            iVar.a();
        }
        return z;
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f670a;
        public final /* synthetic */ o7 b;
        public final /* synthetic */ Date c;

        public b(String str, o7 o7Var, Date date) {
            this.f670a = str;
            this.b = o7Var;
            this.c = date;
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public boolean a(j8 j8Var) {
            String str = this.f670a;
            o7 o7Var = this.b;
            Date date = this.c;
            String str2 = o7Var.f476a;
            if (str == null || str2 == null || date == null) {
                return false;
            }
            ArrayList<Map> arrayList = new ArrayList();
            HashMap map = new HashMap();
            map.put("display_name", str);
            map.put("directedId", str2);
            arrayList.add(map);
            for (Map.Entry<String, String> entry : o7Var.b.entrySet()) {
                HashMap map2 = new HashMap();
                map2.put("userdata_account", str2);
                map2.put("userdata_key", entry.getKey());
                map2.put("userdata_value", entry.getValue());
                arrayList.add(map2);
            }
            for (Map.Entry<String, String> entry2 : o7Var.c.entrySet()) {
                HashMap map3 = new HashMap();
                map3.put("token_account", str2);
                map3.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, entry2.getKey());
                map3.put("token_value", entry2.getValue());
                arrayList.add(map3);
            }
            String strValueOf = String.valueOf(date.getTime());
            for (Map map4 : arrayList) {
                map4.put("timestamp_key", strValueOf);
                map4.put("deleted_key", "false");
            }
            return j8Var.b(arrayList);
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public String b() {
            return "AddAccount";
        }

        @Override // com.amazon.identity.auth.device.y7.i
        public void a() {
            LocalDataStorage localDataStorage = y7.this.b;
            o7 o7Var = this.b;
            Date date = this.c;
            synchronized (localDataStorage) {
                com.amazon.identity.auth.device.e.a(date, "dateTime");
                synchronized (localDataStorage) {
                    SQLiteDatabase sQLiteDatabaseA = null;
                    try {
                        sQLiteDatabaseA = d8.a(localDataStorage.b);
                        sQLiteDatabaseA.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("account_dirty", (Integer) 0);
                        sQLiteDatabaseA.update("accounts", contentValues, String.format("%s = ? and %s = ? and %s = 1 and %s = 0", "directed_id", "account_timestamp", "account_dirty", "account_deleted"), new String[]{o7Var.f476a, Long.toString(date.getTime())});
                        Iterator<Map.Entry<String, String>> it = o7Var.b.entrySet().iterator();
                        while (it.hasNext()) {
                            localDataStorage.b(sQLiteDatabaseA, o7Var.f476a, it.next().getKey(), date);
                        }
                        Iterator<Map.Entry<String, String>> it2 = o7Var.c.entrySet().iterator();
                        while (it2.hasNext()) {
                            localDataStorage.a(sQLiteDatabaseA, o7Var.f476a, it2.next().getKey(), date);
                        }
                        sQLiteDatabaseA.setTransactionSuccessful();
                        localDataStorage.b(sQLiteDatabaseA);
                        localDataStorage.b.close();
                    } catch (Throwable th) {
                        if (sQLiteDatabaseA != null) {
                            localDataStorage.b(sQLiteDatabaseA);
                            localDataStorage.b.close();
                        }
                        throw th;
                    }
                }
            }
            x7<m7> x7VarA = localDataStorage.a(o7Var.f476a);
            if (x7VarA != null) {
                if (x7VarA.b.equals(date)) {
                    x7VarA.c = false;
                }
                Iterator<Map.Entry<String, x7<String>>> it3 = x7VarA.f659a.c.entrySet().iterator();
                while (it3.hasNext()) {
                    x7<String> value = it3.next().getValue();
                    if (value.b.equals(date)) {
                        value.c = false;
                    }
                }
                Iterator<Map.Entry<String, x7<String>>> it4 = x7VarA.f659a.d.entrySet().iterator();
                while (it4.hasNext()) {
                    x7<String> value2 = it4.next().getValue();
                    if (value2.b.equals(date)) {
                        value2.c = false;
                    }
                }
            }
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        boolean z;
        d();
        String str = o7Var.f476a;
        Map<String, String> map = o7Var.c;
        Map<String, String> map2 = o7Var.b;
        if (map.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = map2.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if (!TextUtils.equals(next.getValue(), this.b.c(str, next.getKey()))) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                ga.a("DistributedDataStorage");
                return;
            }
        }
        Date dateA = this.d.a();
        if (!this.b.a(o7Var, dateA, false)) {
            Log.e(ga.a("DistributedDataStorage"), "Setting the data was not successful");
        } else {
            b(new f(map, str, dateA, map2));
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void a(String str, String str2) {
        d();
        Date dateA = this.d.a();
        if (!this.b.a(str, str2, dateA, false)) {
            Log.e(ga.a("DistributedDataStorage"), "Expiring the token was not successful");
        } else {
            b(new g(str, str2, dateA));
        }
    }

    public final boolean a(i iVar) {
        PendingIntentWrapper pendingIntentWrapper;
        Long lValueOf;
        k8 k8Var = this.c;
        synchronized (k8Var.f407a) {
            k8Var.d.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            f8 f8Var = new f8(k8Var.b, "sync_dirty_data_store", 0);
            pendingIntentWrapper = null;
            lValueOf = f8Var.f313a.contains("sync_dirty_data_store_time") ? Long.valueOf(f8Var.f313a.getLong("sync_dirty_data_store_time", 0L)) : null;
            if (lValueOf == null || lValueOf.longValue() <= jCurrentTimeMillis) {
                t5 t5Var = k8Var.b;
                Intent intent = new Intent("com.amazon.identity.action.SYNC_DIRTY_DATA");
                intent.setClass(t5Var, DirtyDataSyncingService.class);
                PendingIntent service = PendingIntent.getService(t5Var, 0, intent, 1140850688);
                PendingIntentWrapper.PendingIntentType pendingIntentType = PendingIntentWrapper.PendingIntentType.Service;
                if (service != null) {
                    pendingIntentWrapper = new PendingIntentWrapper(service, pendingIntentType, intent);
                }
            }
            if (pendingIntentWrapper == null) {
                ga.a(k8.f);
            } else {
                ga.a(k8.f);
                long j = jCurrentTimeMillis + k8.e;
                k8Var.c.a(1, j, pendingIntentWrapper);
                k8Var.a(Long.valueOf(j));
            }
        }
        boolean zC = c(iVar);
        if (zC) {
            synchronized (k8Var.f407a) {
                if (pendingIntentWrapper == null) {
                    ga.a(k8.f);
                } else {
                    h4 h4Var = k8Var.c;
                    h4Var.getClass();
                    try {
                        h4Var.f359a.cancel(pendingIntentWrapper.f329a);
                    } catch (SecurityException e2) {
                        Log.e(ga.a(h4.b), "AlarmManagerWrapper cancel failed!", e2);
                    }
                    k8Var.a(lValueOf);
                }
            }
        }
        return zC;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        String string;
        StringBuilder sb = new StringBuilder();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            StringBuilder sb2 = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, x7<m7>>> it = localDataStorage.a().entrySet().iterator();
            while (it.hasNext()) {
                x7<m7> value = it.next().getValue();
                m7 m7Var = value.f659a;
                if (!arrayList.contains(m7Var.f431a)) {
                    arrayList.add(m7Var.f431a);
                }
                int iIndexOf = arrayList.indexOf(m7Var.f431a);
                localDataStorage.a(sb2, Integer.valueOf(iIndexOf));
                sb2.append((Object) "");
                sb2.append(",");
                localDataStorage.a(sb2, (x7<?>) value);
                for (Map.Entry<String, x7<String>> entry : m7Var.c.entrySet()) {
                    localDataStorage.a(sb2, String.valueOf(iIndexOf), entry.getKey(), entry.getValue());
                }
                for (Map.Entry<String, x7<String>> entry2 : m7Var.d.entrySet()) {
                    localDataStorage.a(sb2, String.valueOf(iIndexOf), entry2.getKey(), entry2.getValue());
                }
            }
            for (Map.Entry<String, Map<String, x7<String>>> entry3 : localDataStorage.e().entrySet()) {
                for (Map.Entry<String, x7<String>> entry4 : entry3.getValue().entrySet()) {
                    localDataStorage.a(sb2, entry3.getKey(), entry4.getKey(), entry4.getValue());
                }
            }
            string = sb2.toString();
        }
        sb.append(string);
        Iterator it2 = ((ArrayList) this.e.a()).iterator();
        while (it2.hasNext()) {
            sb.append(((j5) it2.next()).toString());
        }
        return sb.toString();
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized Set<String> d(String str) {
        HashSet hashSet;
        LocalDataStorage.a aVar;
        d();
        LocalDataStorage localDataStorage = this.b;
        synchronized (localDataStorage) {
            hashSet = new HashSet();
            Cursor cursorQuery = null;
            try {
                cursorQuery = localDataStorage.b.getReadableDatabase().query("tokens", new String[]{"token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_deleted"}, String.format("%s = ? and %s = 0", "token_account_id", "token_deleted"), new String[]{str}, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN));
                        if (!TextUtils.isEmpty(string)) {
                            hashSet.add(string);
                        }
                    }
                    x9.a(cursorQuery);
                    aVar = localDataStorage.b;
                } else {
                    x9.a(cursorQuery);
                    aVar = localDataStorage.b;
                }
                aVar.close();
            } catch (Throwable th) {
                x9.a(cursorQuery);
                localDataStorage.b.close();
                throw th;
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void b(String str, String str2, String str3) {
        d();
        Date dateA = this.d.a();
        if (!this.b.a(str, str2, str3, dateA, false)) {
            Log.e(ga.a("DistributedDataStorage"), "Setting the token was not successful");
        } else {
            b(new h(str, str2, str3, dateA));
        }
    }

    public final void b(i iVar) {
        h.execute(new z7(this, iVar, null));
    }
}
