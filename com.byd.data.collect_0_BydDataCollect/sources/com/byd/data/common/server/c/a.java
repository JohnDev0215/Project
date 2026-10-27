package com.byd.data.common.server.c;

import com.byd.data.common.a;
import com.byd.data.common.i.f;
import com.byd.data.common.server.CloudCanService;

/* JADX INFO: compiled from: ICloudConnect.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends a.AbstractBinderC0063a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CloudCanService f3467a;

    public a(CloudCanService cloudCanService) {
        this.f3467a = cloudCanService;
    }

    @Override // com.byd.data.common.a
    public void a(int i2, int i3) {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService != null) {
            cloudCanService.p(i2, i3);
        }
    }

    @Override // com.byd.data.common.a
    public String b() {
        return "CloudCanService";
    }

    @Override // com.byd.data.common.a
    public void f(byte b2) {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService != null) {
            cloudCanService.q(b2);
        }
    }

    @Override // com.byd.data.common.a
    public boolean i(int i2) {
        CloudCanService cloudCanService = this.f3467a;
        return cloudCanService != null && cloudCanService.m(i2);
    }

    @Override // com.byd.data.common.a
    public void j() {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService == null) {
            return;
        }
        cloudCanService.s();
    }

    @Override // com.byd.data.common.a
    public void k() {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService != null) {
            cloudCanService.d();
        }
    }

    @Override // com.byd.data.common.a
    public void m(byte b2) {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService != null) {
            cloudCanService.o(b2);
        }
    }

    @Override // com.byd.data.common.a
    public void n(boolean z) {
        CloudCanService cloudCanService = this.f3467a;
        if (cloudCanService == null) {
            return;
        }
        com.byd.data.common.h.c.a aVarJ = cloudCanService.j();
        f.g(this.f3467a.i(), "refreshLocalConfig powSta=" + aVarJ);
        if (aVarJ == null || !aVarJ.c()) {
            this.f3467a.b(true);
        } else {
            aVarJ.onPowerSTRChanged(10);
        }
    }
}
