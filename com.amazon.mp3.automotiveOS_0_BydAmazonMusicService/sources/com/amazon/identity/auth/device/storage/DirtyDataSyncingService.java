package com.amazon.identity.auth.device.storage;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPInit;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.v7;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class DirtyDataSyncingService extends IntentService {
    public static final String c = DirtyDataSyncingService.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f551a;
    public u7 b;

    public DirtyDataSyncingService() {
        super(DirtyDataSyncingService.class.getSimpleName());
        a(this);
    }

    public void a(Context context) {
        this.f551a = t5.a(context);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        MAPInit.getInstance(this).initialize();
        this.b = ((v7) this.f551a.getSystemService("dcp_data_storage_factory")).a();
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        String str = c;
        Log.i(ga.a(str), String.format("Package %s is syncing dirty data to other processes", getPackageName()));
        if (((v7) this.f551a.getSystemService("dcp_data_storage_factory")).b()) {
            this.b.f();
        } else {
            Log.e(ga.a(str), "Ignoring dirty data sync request because this platform does not use the distributed datastorage");
        }
    }
}
