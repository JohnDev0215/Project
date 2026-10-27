package com.byd.systemsettings;

import com.byd.uereport.UeReportApplication;
import o2.b;

/* JADX INFO: loaded from: classes2.dex */
public class DxJumpService implements b {
    public static final String TAG = "DxJumpService";

    @Override // o2.b
    public void jump2DeleteWarningDialog(String str, String str2, b.a aVar) {
        DeleteWarningDialog deleteWarningDialog = new DeleteWarningDialog(UeReportApplication.f3064d);
        deleteWarningDialog.setDeviceType(str);
        deleteWarningDialog.setDeviceName(str2);
        deleteWarningDialog.setCallBack(aVar);
        deleteWarningDialog.show();
    }

    @Override // o2.b
    public void jump2DeviceTipsDialog() {
        new DeviceTipsDialog(UeReportApplication.f3064d).show();
    }

    @Override // o2.b
    public void jump2DisconnectWarningDialog(String str, b.a aVar) {
        DisconnectWarningDialog disconnectWarningDialog = new DisconnectWarningDialog(UeReportApplication.f3064d);
        disconnectWarningDialog.setDeviceType(str);
        disconnectWarningDialog.setCallBack(aVar);
        disconnectWarningDialog.show();
    }

    @Override // o2.b
    public void jump2OffBtWarningDialog(b.a aVar) {
        OffBtWarningDialog offBtWarningDialog = new OffBtWarningDialog(UeReportApplication.f3064d);
        offBtWarningDialog.setCallBack(aVar);
        offBtWarningDialog.show();
    }

    @Override // o2.b
    public void jump2OffHotspotWarningDialog(int i8, String str, b.a aVar) {
        OffHotspotWarningDialog offHotspotWarningDialog = new OffHotspotWarningDialog(UeReportApplication.f3064d);
        offHotspotWarningDialog.setCallBack(aVar);
        offHotspotWarningDialog.setMsg(str);
        offHotspotWarningDialog.setFlag(i8);
        offHotspotWarningDialog.show();
    }

    @Override // o2.b
    public void jump2SwitchWarningDialog(String str, b.a aVar) {
        SwitchWarningDialog switchWarningDialog = new SwitchWarningDialog(UeReportApplication.f3064d);
        switchWarningDialog.setCallBack(aVar);
        switchWarningDialog.setType(str);
        switchWarningDialog.setFlag(4);
        switchWarningDialog.show();
    }
}
