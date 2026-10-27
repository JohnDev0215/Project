package b5;

import android.content.Context;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.dialog.ChargePortCoverTipDialog;
import com.byd.uereport.dialog.MemoryCleanTipDialog;
import com.byd.uereport.dialog.OpenBackDoorDialog;
import com.byd.uereport.dialog.OverhaulEPBDialog;
import com.byd.uereport.dialog.RegisterDialog;
import com.byd.uereport.dialog.TransportModeDialog;
import com.byd.uereport.dialog.WeakCloudSignalDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoNoticeDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoPairDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoStartDialog;
import com.byd.uereport.dialog.screencast.CarPlayNeedDialog;
import com.byd.uereport.dialog.screencast.CarPlayStartDialog;
import com.byd.uereport.dialog.screencast.CarPlayWirelessDialog;
import com.byd.uereport.dialog.screencast.CastConnectingDialog;
import com.byd.uereport.dialog.screencast.CastProtectionNoticeDialog;
import com.byd.uereport.dialog.screencast.CastSwitchDeviceDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: DialogContainer.java */
/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile h f2079a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap<Integer, g> f2080b = new LinkedHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f2081c;

    /* JADX INFO: compiled from: DialogContainer.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ g f2082d;

        public a(g gVar) {
            this.f2082d = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            g gVar = this.f2082d;
            if (gVar instanceof AndroidAutoNoticeDialog) {
                int iC = ((AndroidAutoNoticeDialog) gVar).c();
                this.f2082d.dismiss();
                AndroidAutoNoticeDialog androidAutoNoticeDialog = new AndroidAutoNoticeDialog(h.this.f2081c);
                androidAutoNoticeDialog.d(iC);
                androidAutoNoticeDialog.show();
                return;
            }
            if (gVar instanceof AndroidAutoPairDialog) {
                gVar.dismiss();
                new AndroidAutoPairDialog(h.this.f2081c).show();
                return;
            }
            if (gVar instanceof AndroidAutoStartDialog) {
                AndroidAutoStartDialog androidAutoStartDialog = (AndroidAutoStartDialog) gVar;
                String deviceName = androidAutoStartDialog.getDeviceName();
                int iE = androidAutoStartDialog.e();
                androidAutoStartDialog.dismiss();
                AndroidAutoStartDialog androidAutoStartDialog2 = new AndroidAutoStartDialog(h.this.f2081c);
                androidAutoStartDialog2.setDeviceName(deviceName);
                androidAutoStartDialog2.f(iE);
                androidAutoStartDialog2.show();
                return;
            }
            if (gVar instanceof CarPlayNeedDialog) {
                gVar.dismiss();
                new CarPlayNeedDialog(h.this.f2081c).show();
                return;
            }
            if (gVar instanceof CarPlayWirelessDialog) {
                CarPlayWirelessDialog carPlayWirelessDialog = (CarPlayWirelessDialog) gVar;
                String strD = carPlayWirelessDialog.d();
                carPlayWirelessDialog.dismiss();
                CarPlayWirelessDialog carPlayWirelessDialog2 = new CarPlayWirelessDialog(h.this.f2081c);
                carPlayWirelessDialog2.f(strD);
                carPlayWirelessDialog2.show();
                return;
            }
            if (gVar instanceof CastConnectingDialog) {
                CastConnectingDialog castConnectingDialog = (CastConnectingDialog) gVar;
                String deviceName2 = castConnectingDialog.getDeviceName();
                int iC2 = castConnectingDialog.c();
                this.f2082d.dismiss();
                CastConnectingDialog castConnectingDialog2 = new CastConnectingDialog(h.this.f2081c);
                castConnectingDialog2.setDeviceName(deviceName2);
                castConnectingDialog2.d(iC2);
                if (iC2 == 0) {
                    CarPlayManager.getInstance(h.this.f2081c).setCarPlayConnectingDialog(castConnectingDialog2);
                    return;
                } else {
                    AndroidAutoManager.getInstance(h.this.f2081c).setAndroidAutoConnectingDialog(castConnectingDialog2);
                    return;
                }
            }
            if (gVar instanceof CastProtectionNoticeDialog) {
                CastProtectionNoticeDialog castProtectionNoticeDialog = (CastProtectionNoticeDialog) gVar;
                boolean zE = castProtectionNoticeDialog.e();
                String strD2 = castProtectionNoticeDialog.d();
                this.f2082d.dismiss();
                CastProtectionNoticeDialog castProtectionNoticeDialog2 = new CastProtectionNoticeDialog(h.this.f2081c);
                castProtectionNoticeDialog2.g(zE);
                castProtectionNoticeDialog2.f(strD2);
                castProtectionNoticeDialog2.show();
                return;
            }
            if (gVar instanceof CastSwitchDeviceDialog) {
                CastSwitchDeviceDialog castSwitchDeviceDialog = (CastSwitchDeviceDialog) gVar;
                String strF = castSwitchDeviceDialog.f();
                int iG = castSwitchDeviceDialog.g();
                castSwitchDeviceDialog.dismiss();
                CastSwitchDeviceDialog castSwitchDeviceDialog2 = new CastSwitchDeviceDialog(h.this.f2081c);
                castSwitchDeviceDialog2.l(iG);
                castSwitchDeviceDialog2.k(strF);
                castSwitchDeviceDialog2.show();
                return;
            }
            if (gVar instanceof WeakCloudSignalDialog) {
                gVar.dismiss();
                new WeakCloudSignalDialog(h.this.f2081c).show();
                return;
            }
            if (gVar instanceof MemoryCleanTipDialog) {
                MemoryCleanTipDialog memoryCleanTipDialog = (MemoryCleanTipDialog) gVar;
                int iC3 = memoryCleanTipDialog.c();
                memoryCleanTipDialog.dismiss();
                MemoryCleanTipDialog memoryCleanTipDialog2 = new MemoryCleanTipDialog(h.this.f2081c);
                memoryCleanTipDialog2.e(iC3);
                memoryCleanTipDialog2.show();
                return;
            }
            if (gVar instanceof TransportModeDialog) {
                TransportModeDialog transportModeDialog = (TransportModeDialog) gVar;
                int iG2 = transportModeDialog.g();
                int iE2 = transportModeDialog.e();
                transportModeDialog.dismiss();
                TransportModeDialog transportModeDialog2 = new TransportModeDialog(h.this.f2081c);
                transportModeDialog2.j(iG2);
                transportModeDialog2.setFlag(iG2);
                transportModeDialog2.i(iE2);
                transportModeDialog2.show();
                return;
            }
            if (gVar instanceof RegisterDialog) {
                RegisterDialog registerDialog = (RegisterDialog) gVar;
                int iD = registerDialog.d();
                String strC = registerDialog.c();
                String strE = registerDialog.e();
                int iF = registerDialog.f();
                boolean zK = registerDialog.k();
                boolean zI = registerDialog.i();
                this.f2082d.dismiss();
                RegisterDialog registerDialog2 = new RegisterDialog(h.this.f2081c);
                registerDialog2.l(strC);
                registerDialog2.n(strE);
                registerDialog2.m(iD);
                registerDialog2.p(iF);
                registerDialog2.show();
                if (zK) {
                    registerDialog2.s(zI);
                }
                n4.a.z(h.this.f2081c).O(registerDialog2);
                return;
            }
            if (gVar instanceof OpenBackDoorDialog) {
                int iE3 = ((OpenBackDoorDialog) gVar).e();
                this.f2082d.dismiss();
                OpenBackDoorDialog openBackDoorDialog = new OpenBackDoorDialog(h.this.f2081c);
                openBackDoorDialog.g(iE3);
                openBackDoorDialog.show();
                return;
            }
            if (gVar instanceof ChargePortCoverTipDialog) {
                int iE4 = ((ChargePortCoverTipDialog) gVar).e();
                this.f2082d.dismiss();
                ChargePortCoverTipDialog chargePortCoverTipDialog = new ChargePortCoverTipDialog(h.this.f2081c);
                chargePortCoverTipDialog.g(iE4);
                chargePortCoverTipDialog.show();
                return;
            }
            if (gVar instanceof CarPlayStartDialog) {
                CarPlayStartDialog carPlayStartDialog = (CarPlayStartDialog) gVar;
                boolean zG = carPlayStartDialog.g();
                String strE2 = carPlayStartDialog.e();
                String deviceName3 = carPlayStartDialog.getDeviceName();
                String strF2 = carPlayStartDialog.f();
                this.f2082d.dismiss();
                CarPlayStartDialog carPlayStartDialog2 = new CarPlayStartDialog(h.this.f2081c);
                carPlayStartDialog2.i(zG);
                carPlayStartDialog2.setDeviceName(deviceName3);
                carPlayStartDialog2.h(strE2);
                carPlayStartDialog2.j(strF2);
                carPlayStartDialog2.show();
            }
        }
    }

    public h(Context context) {
        this.f2081c = context.getApplicationContext();
    }

    public static h e(Context context) {
        if (f2079a == null) {
            synchronized (h.class) {
                if (f2079a == null) {
                    f2079a = new h(context);
                }
            }
        }
        return f2079a;
    }

    public boolean b(g gVar) {
        int iInitLayoutRes = gVar.initLayoutRes() + gVar.getFlag();
        if (this.f2080b.containsKey(Integer.valueOf(iInitLayoutRes))) {
            return false;
        }
        this.f2080b.put(Integer.valueOf(iInitLayoutRes), gVar);
        return true;
    }

    public void c() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.f2080b.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            g gVar = this.f2080b.get((Integer) it2.next());
            if (gVar instanceof OverhaulEPBDialog) {
                g5.a.i("clearAllDialog OverhaulEPBDialog");
            } else {
                gVar.dismiss();
            }
        }
        g5.a.i("mDialogContainer size ():" + this.f2080b.size());
    }

    public LinkedHashMap<Integer, g> d() {
        return this.f2080b;
    }

    public void f() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Integer num : this.f2080b.keySet()) {
            linkedHashMap.put(num, this.f2080b.get(num));
        }
        Iterator it = linkedHashMap.keySet().iterator();
        while (it.hasNext()) {
            p2.g.a().d(new a((g) linkedHashMap.get((Integer) it.next())), 500L);
        }
    }

    public boolean g(g gVar) {
        int iInitLayoutRes = gVar.initLayoutRes() + gVar.getFlag();
        if (!this.f2080b.containsKey(Integer.valueOf(iInitLayoutRes))) {
            return false;
        }
        this.f2080b.remove(Integer.valueOf(iInitLayoutRes));
        return true;
    }
}
