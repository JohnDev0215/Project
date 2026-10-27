package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoBodyworkDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b f1262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoBodyworkDevice f1263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1264c = -1;

    public static synchronized b c() {
        if (f1262a == null) {
            f1262a = new b();
        }
        return f1262a;
    }

    public int a() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f1263b;
        if (bYDAutoBodyworkDevice == null) {
            return 0;
        }
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        this.f1264c = autoType;
        return autoType;
    }

    public int b() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f1263b;
        if (bYDAutoBodyworkDevice != null) {
            return bYDAutoBodyworkDevice.getElecSlideDoorState();
        }
        return 0;
    }

    public int d() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f1263b;
        if (bYDAutoBodyworkDevice != null) {
            return bYDAutoBodyworkDevice.getPowerLevel();
        }
        return 0;
    }

    public int e(int i) {
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f1263b;
            if (bYDAutoBodyworkDevice != null) {
                return bYDAutoBodyworkDevice.hasMessage(i);
            }
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public void f(Context context) {
        this.f1263b = BYDAutoBodyworkDevice.getInstance(context);
    }

    public boolean g() {
        int iA = a();
        this.f1264c = iA;
        switch (iA) {
            case 0:
            case 14:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
            case 317:
                return true;
            default:
                return false;
        }
    }

    public void h(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (this.f1263b == null) {
            return;
        }
        String simpleName = b.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydBodyworkListener start listener == null : ");
        sb.append(absBYDAutoBodyworkListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1263b.registerListener(absBYDAutoBodyworkListener);
    }

    public void i(int i) {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f1263b;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.setElecSlideDoorState(i);
        }
    }

    public void j(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (this.f1263b == null) {
            return;
        }
        String simpleName = b.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydBodyworkListener start listener == null : ");
        sb.append(absBYDAutoBodyworkListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1263b.unregisterListener(absBYDAutoBodyworkListener);
    }
}
