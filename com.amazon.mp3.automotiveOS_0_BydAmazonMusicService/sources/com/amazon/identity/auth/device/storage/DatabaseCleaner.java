package com.amazon.identity.auth.device.storage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.amazon.identity.auth.device.framework.AmazonIntentService;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.g8;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.h4;
import com.amazon.identity.auth.device.i8;
import com.amazon.identity.auth.device.j5;
import com.amazon.identity.auth.device.j8;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.v7;
import com.amazon.identity.auth.device.w5;
import com.amazon.identity.auth.device.wa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class DatabaseCleaner {
    public static final String f = "com.amazon.identity.auth.device.storage.DatabaseCleaner";
    public static final Object[] g = new Object[0];
    public static long h = wa.a(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f550a;
    public final u7 b;
    public final LocalDataStorage c;
    public final h4 d;
    public final w5 e;

    /* JADX INFO: compiled from: DCP */
    public static class DatabaseCleaningService extends AmazonIntentService {
        public static final /* synthetic */ int c = 0;
        public t5 b;

        public DatabaseCleaningService() {
            super(DatabaseCleaningService.class.getName());
            a(this);
        }

        public void a(Context context) {
            this.b = t5.a(context);
        }

        @Override // com.amazon.identity.auth.device.framework.AmazonIntentService
        public void a(Intent intent) {
            Collection<Map<String, String>> collection;
            if (!((v7) this.b.getSystemService("dcp_data_storage_factory")).b()) {
                Log.e(ga.a(AmazonIntentService.f318a), "Ignoring Database cleaning request because this platform does not use distributed data storage");
                return;
            }
            Log.i(ga.a(AmazonIntentService.f318a), "Cleaning database of unneeded items");
            DatabaseCleaner databaseCleaner = new DatabaseCleaner(this.b);
            Collection<Map<String, String>> collectionD = databaseCleaner.c.d();
            if (!((collectionD == null || collectionD.isEmpty()) ? false : true)) {
                Log.i(ga.a(DatabaseCleaner.f), "No Deleted items in local app, skipping cleanup.");
                return;
            }
            ArrayList<j5> arrayList = (ArrayList) MAPApplicationInformationQueryer.a(databaseCleaner.f550a).a();
            Collection<Map<String, String>> collection2 = null;
            for (j5 j5Var : arrayList) {
                j8 j8Var = new j8(databaseCleaner.f550a, j5Var);
                try {
                    Uri uriA = g8.a(j8Var.c.c, "/all_deleted_data");
                    collection = (Collection) j8Var.b.a(uriA, new i8(j8Var, uriA));
                } catch (RemoteMAPException e) {
                    Log.w(ga.a(DatabaseCleaner.f), "Failed to get deleted data from " + j5Var.b, e);
                    collection = null;
                }
                if (collection != null) {
                    if (collection2 != null) {
                        collection2.retainAll(collection);
                        if (collection2.isEmpty()) {
                            break;
                        }
                    } else {
                        collection2 = collection;
                    }
                } else {
                    Log.w(ga.a(DatabaseCleaner.f), String.format("Remote Package %s is unable to provide any deleted data", j5Var.toString()));
                }
            }
            String str = DatabaseCleaner.f;
            if (collection2 != null) {
                collection2.toString();
            }
            ga.a(str);
            if (collection2 == null || collection2.size() == 0) {
                Log.i(ga.a(str), "No Deleted items to clean from the MAP databases");
                return;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!new j8(databaseCleaner.f550a, (j5) it.next()).a(collection2)) {
                    Log.e(ga.a(DatabaseCleaner.f), "Was not fully successful remotely removing deleted items");
                }
            }
        }
    }

    public DatabaseCleaner(Context context) {
        t5 t5VarA = t5.a(context);
        this.f550a = t5VarA;
        this.b = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
        this.c = (LocalDataStorage) t5VarA.getSystemService("sso_local_datastorage");
        this.d = (h4) t5VarA.getSystemService("sso_alarm_maanger");
        this.e = (w5) t5VarA.getSystemService("dcp_system");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f A[Catch: all -> 0x0071, TryCatch #0 {, blocks: (B:4:0x0003, B:18:0x004f, B:20:0x006f, B:19:0x0055, B:13:0x002d, B:16:0x0047, B:7:0x001f), top: B:25:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0055 A[Catch: all -> 0x0071, TryCatch #0 {, blocks: (B:4:0x0003, B:18:0x004f, B:20:0x006f, B:19:0x0055, B:13:0x002d, B:16:0x0047, B:7:0x001f), top: B:25:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a() {
        /*
            r8 = this;
            java.lang.Object[] r0 = com.amazon.identity.auth.device.storage.DatabaseCleaner.g
            monitor-enter(r0)
            com.amazon.identity.auth.device.w5 r1 = r8.e     // Catch: java.lang.Throwable -> L71
            r1.getClass()     // Catch: java.lang.Throwable -> L71
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L71
            com.amazon.identity.auth.device.u7 r3 = r8.b     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = "clean_database_store"
            java.lang.String r5 = "clean_database_time_ms_key"
            java.lang.String r3 = r3.b(r4, r5)     // Catch: java.lang.Throwable -> L71
            java.lang.Long r3 = com.amazon.identity.auth.device.e.k(r3)     // Catch: java.lang.Throwable -> L71
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L1f
            goto L27
        L1f:
            long r6 = r3.longValue()     // Catch: java.lang.Throwable -> L71
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 > 0) goto L29
        L27:
            r3 = r5
            goto L2a
        L29:
            r3 = r4
        L2a:
            if (r3 != 0) goto L2d
            goto L45
        L2d:
            com.amazon.identity.auth.device.t5 r3 = r8.f550a     // Catch: java.lang.Throwable -> L71
            android.content.Intent r6 = new android.content.Intent     // Catch: java.lang.Throwable -> L71
            java.lang.String r7 = "com.amazon.identity.action.CLEAN_DATA"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L71
            java.lang.Class<com.amazon.identity.auth.device.storage.DatabaseCleaner$DatabaseCleaningService> r7 = com.amazon.identity.auth.device.storage.DatabaseCleaner.DatabaseCleaningService.class
            r6.setClass(r3, r7)     // Catch: java.lang.Throwable -> L71
            r7 = 1140850688(0x44000000, float:512.0)
            android.app.PendingIntent r3 = android.app.PendingIntent.getService(r3, r4, r6, r7)     // Catch: java.lang.Throwable -> L71
            com.amazon.identity.auth.device.framework.PendingIntentWrapper$PendingIntentType r4 = com.amazon.identity.auth.device.framework.PendingIntentWrapper.PendingIntentType.Service     // Catch: java.lang.Throwable -> L71
            if (r3 != 0) goto L47
        L45:
            r3 = 0
            goto L4d
        L47:
            com.amazon.identity.auth.device.framework.PendingIntentWrapper r7 = new com.amazon.identity.auth.device.framework.PendingIntentWrapper     // Catch: java.lang.Throwable -> L71
            r7.<init>(r3, r4, r6)     // Catch: java.lang.Throwable -> L71
            r3 = r7
        L4d:
            if (r3 != 0) goto L55
            java.lang.String r1 = com.amazon.identity.auth.device.storage.DatabaseCleaner.f     // Catch: java.lang.Throwable -> L71
            com.amazon.identity.auth.device.ga.a(r1)     // Catch: java.lang.Throwable -> L71
            goto L6f
        L55:
            java.lang.String r4 = com.amazon.identity.auth.device.storage.DatabaseCleaner.f     // Catch: java.lang.Throwable -> L71
            com.amazon.identity.auth.device.ga.a(r4)     // Catch: java.lang.Throwable -> L71
            long r6 = com.amazon.identity.auth.device.storage.DatabaseCleaner.h     // Catch: java.lang.Throwable -> L71
            long r1 = r1 + r6
            com.amazon.identity.auth.device.h4 r4 = r8.d     // Catch: java.lang.Throwable -> L71
            r4.a(r5, r1, r3)     // Catch: java.lang.Throwable -> L71
            com.amazon.identity.auth.device.u7 r3 = r8.b     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r2 = "clean_database_store"
            java.lang.String r4 = "clean_database_time_ms_key"
            r3.b(r2, r4, r1)     // Catch: java.lang.Throwable -> L71
        L6f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
            return
        L71:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.storage.DatabaseCleaner.a():void");
    }
}
