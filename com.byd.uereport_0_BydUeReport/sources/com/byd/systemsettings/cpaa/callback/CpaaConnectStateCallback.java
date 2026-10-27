package com.byd.systemsettings.cpaa.callback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CpaaConnectStateCallback {
    public static final int ACTION_AA_TO_AA = 1;
    public static final int ACTION_AA_TO_CP = 2;
    public static final int ACTION_CP_TO_AA = 4;
    public static final int ACTION_CP_TO_CP = 3;
    public static final int ACTION_TO_BT = 0;
    private int action = 0;

    public int getAction() {
        return this.action;
    }

    public abstract void onConnectChanged(boolean z7);

    public void setAction(int i8) {
        this.action = i8;
    }
}
