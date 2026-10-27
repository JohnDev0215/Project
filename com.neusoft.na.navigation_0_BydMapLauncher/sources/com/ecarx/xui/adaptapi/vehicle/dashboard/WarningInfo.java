package com.ecarx.xui.adaptapi.vehicle.dashboard;

/* JADX INFO: loaded from: classes2.dex */
public class WarningInfo implements IWarningInfo {
    int priority;
    int warningId;

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IWarningInfo
    public int getWarningPriority() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IWarningInfo
    public int getWarningId() {
        return this.warningId;
    }

    public void setWarningId(int i) {
        this.warningId = i;
    }

    public void setWarningPriority(int i) {
        this.priority = i;
    }
}
