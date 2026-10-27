package com.byd.data.collect.k;

import android.hardware.bydauto.collectdata.tsimpl.CollectDataHalId;
import com.byd.data.collect.subscribe.present.ExtensionPresent;
import com.byd.data.common.entiry.gson.DataCollectBean;

/* JADX INFO: compiled from: CollectDataListener.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DataCollectBean f3200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f3201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final byte f3202c = 85;

    public c(DataCollectBean dataCollectBean, e eVar) {
        this.f3200a = dataCollectBean;
        this.f3201b = eVar;
    }

    public void a(int i2) {
        e eVar;
        if (this.f3200a == null || (eVar = this.f3201b) == null || eVar.r() == null) {
            return;
        }
        ExtensionPresent extensionPresentR = this.f3201b.r();
        extensionPresentR.p(this.f3200a.extensionAlarm.collisionAlarm, i2 == 85);
        extensionPresentR.z((byte) (i2 & 255));
    }

    public void b(int i2) {
        e eVar;
        if (this.f3200a == null || (eVar = this.f3201b) == null || eVar.r() == null) {
            return;
        }
        this.f3201b.r().p(this.f3200a.extensionAlarm.dcChargeFault, i2 == 1);
    }

    public void c(int i2, byte[] bArr, int i3) {
        int i4 = 0;
        if (1 == i3) {
            i4 = bArr[0];
        } else if (i3 == 2) {
            i4 = (short) ((bArr[0] & 255) | ((bArr[1] & 255) << 8));
        } else if (i3 == 4) {
            i4 = (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
        }
        switch (i2) {
            case CollectDataHalId.ID_DC_CHARGING_FAULT /* 557872139 */:
                b(i4);
                break;
            case CollectDataHalId.ID_WATER_TEMPERATION_ALARM /* 557872140 */:
                d(i4);
                break;
            case CollectDataHalId.ID_COLLISION /* 557872141 */:
                a(i4);
                e eVar = this.f3201b;
                if (eVar != null && i4 == 85) {
                    eVar.C();
                    break;
                }
                break;
        }
    }

    public void d(int i2) {
        e eVar;
        if (this.f3200a == null || (eVar = this.f3201b) == null || eVar.r() == null) {
            return;
        }
        this.f3201b.r().p(this.f3200a.extensionAlarm.waterTempAlarm, i2 == 1);
    }
}
