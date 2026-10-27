package com.byd.logtools.auth.utils;

import android.os.RemoteException;
import com.bydux.api.common.tools.log.BydLogHelper;
import h.a.b.a.d;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: TBoxDevice.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends d.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h.a.b.a.c f3150b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3149a = "TBoxDevice";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3151c = HttpUrl.FRAGMENT_ENCODE_SET;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3152d = false;

    @Override // h.a.b.a.d
    public void B(byte b2) throws RemoteException {
    }

    @Override // h.a.b.a.d
    public void C(byte b2) throws RemoteException {
    }

    public boolean K() {
        return this.f3152d;
    }

    public void L() {
        try {
            h.a.b.a.c cVarD = h.a.b.a.c.d();
            this.f3150b = cVarD;
            cVarD.g(this);
            h.a.b.a.c cVar = this.f3150b;
            if (cVar != null) {
                this.f3152d = cVar.x() == 0;
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        BydLogHelper.logI("TBoxDevice", "initTBox is end, apn3Enable = " + this.f3152d);
    }

    public void M() {
        h.a.b.a.c cVar = this.f3150b;
        if (cVar != null) {
            try {
                cVar.i(this);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        this.f3150b = null;
    }

    @Override // h.a.b.a.d
    public void l(byte b2) throws RemoteException {
    }

    @Override // h.a.b.a.d
    public void m(byte b2) throws RemoteException {
    }

    @Override // h.a.b.a.d
    public void q(byte b2) throws RemoteException {
        boolean z = b2 == 0;
        if (z != this.f3152d) {
            this.f3152d = z;
        }
        BydLogHelper.logI("TBoxDevice", "onApn3Changed is ret =" + ((int) b2));
    }

    public String r() {
        String str = this.f3151c;
        if (str != null && !str.isEmpty()) {
            return this.f3151c;
        }
        try {
            h.a.b.a.c cVar = this.f3150b;
            if (cVar != null) {
                this.f3151c = cVar.r();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return this.f3151c;
    }

    @Override // h.a.b.a.d
    public void y(h.a.b.a.b bVar) throws RemoteException {
    }
}
