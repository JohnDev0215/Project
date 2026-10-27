package com.byd.data.collect.h;

import android.os.IBinder;
import com.byd.data.collect.R;
import com.byd.data.collect.g;
import com.byd.data.collect.server.DataCollectService;

/* JADX INFO: compiled from: DataCollectProxy.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends g.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DataCollectService f3133a;

    public a(DataCollectService dataCollectService) {
        this.f3133a = dataCollectService;
    }

    @Override // com.byd.data.collect.g
    public String b() {
        DataCollectService dataCollectService = this.f3133a;
        return dataCollectService != null ? dataCollectService.getString(R.string.data_collect) : "CollectDeath";
    }

    @Override // com.byd.data.collect.g
    public void o() {
        DataCollectService dataCollectService = this.f3133a;
        if (dataCollectService != null) {
            dataCollectService.c();
        }
    }

    @Override // android.os.Binder, android.os.IBinder
    public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i2) {
        this.f3133a = null;
        return super.unlinkToDeath(deathRecipient, i2);
    }
}
