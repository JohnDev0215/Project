package b5;

import android.content.Context;
import com.byd.systemsettings.DeleteWarningDialog;
import com.byd.systemsettings.DeviceTipsDialog;
import com.byd.systemsettings.DisconnectWarningDialog;
import com.byd.systemsettings.OffBtWarningDialog;
import com.byd.systemsettings.OffHotspotWarningDialog;
import com.byd.systemsettings.SwitchWarningDialog;
import com.byd.uereport.UeReportApplication;
import java.util.Iterator;
import java.util.LinkedHashMap;
import o2.b;

/* JADX INFO: compiled from: SystemSettingsDialogContainer.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static j f2098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f2099b;

    /* JADX INFO: compiled from: SystemSettingsDialogContainer.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ LinkedHashMap f2100d;

        public a(LinkedHashMap linkedHashMap) {
            this.f2100d = linkedHashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2100d.keySet().iterator();
            while (it.hasNext()) {
                g gVar = (g) this.f2100d.get((Integer) it.next());
                if (gVar instanceof DeviceTipsDialog) {
                    gVar.dismiss();
                    new DeviceTipsDialog(j.this.f2099b).show();
                } else if (gVar instanceof DisconnectWarningDialog) {
                    DisconnectWarningDialog disconnectWarningDialog = (DisconnectWarningDialog) gVar;
                    String type = disconnectWarningDialog.getType();
                    boolean zIsCheck = disconnectWarningDialog.isCheck();
                    b.a callBack = disconnectWarningDialog.getCallBack();
                    disconnectWarningDialog.dismiss();
                    DisconnectWarningDialog disconnectWarningDialog2 = new DisconnectWarningDialog(j.this.f2099b);
                    disconnectWarningDialog2.setDeviceType(type);
                    disconnectWarningDialog2.setCallBack(callBack);
                    disconnectWarningDialog2.show();
                    disconnectWarningDialog2.setCheck(zIsCheck);
                } else if (gVar instanceof DeleteWarningDialog) {
                    DeleteWarningDialog deleteWarningDialog = (DeleteWarningDialog) gVar;
                    DeleteWarningDialog deleteWarningDialog2 = new DeleteWarningDialog(j.this.f2099b);
                    deleteWarningDialog2.setDeviceType(deleteWarningDialog.getType());
                    deleteWarningDialog2.setDeviceName(deleteWarningDialog.getDeviceName());
                    deleteWarningDialog2.setCallBack(deleteWarningDialog.getCallBack());
                    deleteWarningDialog.dismiss();
                    deleteWarningDialog2.show();
                } else if (gVar instanceof SwitchWarningDialog) {
                    SwitchWarningDialog switchWarningDialog = (SwitchWarningDialog) gVar;
                    SwitchWarningDialog switchWarningDialog2 = new SwitchWarningDialog(j.this.f2099b);
                    switchWarningDialog2.setCallBack(switchWarningDialog.getCallBack());
                    switchWarningDialog2.setType(switchWarningDialog.getType());
                    switchWarningDialog2.setFlag(4);
                    switchWarningDialog.dismiss();
                    switchWarningDialog2.show();
                } else if (gVar instanceof OffHotspotWarningDialog) {
                    OffHotspotWarningDialog offHotspotWarningDialog = new OffHotspotWarningDialog(UeReportApplication.f3064d);
                    offHotspotWarningDialog.setCallBack(((OffHotspotWarningDialog) gVar).getCallBack());
                    offHotspotWarningDialog.setFlag(gVar.getFlag());
                    offHotspotWarningDialog.setMsg(((OffHotspotWarningDialog) gVar).getMsg());
                    gVar.dismiss();
                    offHotspotWarningDialog.show();
                } else if (gVar instanceof OffBtWarningDialog) {
                    OffBtWarningDialog offBtWarningDialog = new OffBtWarningDialog(UeReportApplication.f3064d);
                    offBtWarningDialog.setCallBack(((OffBtWarningDialog) gVar).getCallBack());
                    gVar.dismiss();
                    offBtWarningDialog.show();
                }
            }
        }
    }

    public j(Context context) {
        this.f2099b = context.getApplicationContext();
    }

    public static j b(Context context) {
        if (f2098a == null) {
            synchronized (h.class) {
                if (f2098a == null) {
                    f2098a = new j(context);
                }
            }
        }
        return f2098a;
    }

    public void c(LinkedHashMap<Integer, g> linkedHashMap) {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Integer num : linkedHashMap.keySet()) {
            linkedHashMap2.put(num, linkedHashMap.get(num));
        }
        p2.g.a().d(new a(linkedHashMap2), 380L);
    }
}
