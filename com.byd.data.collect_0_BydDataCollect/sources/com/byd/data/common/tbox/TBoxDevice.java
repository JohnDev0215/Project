package com.byd.data.common.tbox;

import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.data.common.g.a;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.b;
import com.byd.data.common.i.f;
import com.byd.data.common.server.CloudCanService;
import i.a.a.a.c;
import i.a.a.a.d;
import i.a.a.a.e;

/* JADX INFO: loaded from: classes.dex */
public class TBoxDevice extends e.a {

    /* JADX INFO: renamed from: b */
    private d f3543b;

    /* JADX INFO: renamed from: a */
    private final String f3542a = "TBoxDevice" + b.f3423a;

    /* JADX INFO: renamed from: c */
    private boolean f3544c = false;

    /* JADX INFO: renamed from: d */
    private String f3545d = "89852111239881468924";

    public int A() {
        if (this.f3543b == null) {
            f.g(this.f3542a, "boxService is null");
            return 0;
        }
        if (ApiRetrofit.h().k()) {
            return 90131;
        }
        try {
            String strL = this.f3543b.l();
            if ((strL != null) & false) {
                f.j(this.f3542a, "imsi= ", strL);
            }
            if (strL != null && strL.length() >= 8) {
                int i2 = Integer.parseInt(strL.substring(0, strL.length() == 15 ? 5 : 6));
                if (i2 == 33402) {
                    return 334020;
                }
                return i2;
            }
            return 0;
        } catch (RemoteException | IndexOutOfBoundsException | NumberFormatException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public boolean B() {
        d dVar = this.f3543b;
        if (dVar == null) {
            return this.f3544c;
        }
        try {
            this.f3544c = dVar.j() == 0;
            String strH = this.f3543b.h();
            if (strH != null && strH.length() == 20) {
                this.f3545d = strH;
                f.j(this.f3542a, "iccId end get ", strH);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return this.f3544c;
    }

    public void C() {
        try {
            d dVarP = d.p();
            this.f3543b = dVarP;
            dVarP.b(this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        f.g(this.f3542a, "initTBox is end " + this.f3544c);
    }

    public void D() {
        d dVar = this.f3543b;
        if (dVar != null) {
            try {
                dVar.c(this);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        this.f3543b = null;
    }

    @Override // i.a.a.a.e
    public void d(byte b2) {
    }

    @Override // i.a.a.a.e
    public void f(byte b2) {
    }

    @Override // i.a.a.a.e
    public void g(byte b2) {
        boolean z = b2 == 0;
        if (z != this.f3544c) {
            this.f3544c = z;
            if (z) {
                a.i().g().h();
            }
        } else if (z) {
            CloudCanService cloudCanServiceE = a.i().e();
            if (cloudCanServiceE == null) {
                return;
            }
            com.byd.data.common.h.c.a aVarJ = cloudCanServiceE.j();
            boolean zC = aVarJ.c();
            byte bX = a.i().g().x((byte) 2);
            f.g(this.f3542a, "onApn3Changed power =" + aVarJ + ", bms step =" + ((int) bX));
            if (zC && bX == 3) {
                aVarJ.onPowerSTRChanged(10);
            }
        }
        f.g(this.f3542a, "onApn3Changed: " + ((int) b2) + ",enable = " + z);
    }

    @Override // i.a.a.a.e
    public void i(String str) {
        if (str == null || str.length() != 20) {
            return;
        }
        this.f3545d = str;
        f.j(this.f3542a, "iccId change =", str);
    }

    @Override // i.a.a.a.e
    public void k(c cVar) {
    }

    @Override // i.a.a.a.e
    public void n(byte b2) {
    }

    @Override // i.a.a.a.e
    public void o(byte b2) {
    }

    public boolean x() {
        if (com.byd.data.common.server.b.e().p()) {
            f.g(this.f3542a, "getApn3Enable =" + this.f3544c);
        }
        return this.f3544c;
    }

    public String y() {
        if (this.f3543b == null) {
            return null;
        }
        if (!TextUtils.isEmpty(this.f3545d)) {
            return this.f3545d;
        }
        try {
            String strH = this.f3543b.h();
            if (strH != null && strH.length() == 20) {
                this.f3545d = strH;
                f.j(this.f3542a, "iccId end get ", strH);
            }
            return strH;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int z() {
        return A();
    }
}
