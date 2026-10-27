package com.byd.systemsettings.cpaa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.bt.model.BTDevice;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class SystemPanelChangeReceiver extends BroadcastReceiver {
    public static int CURRENT_PANEL_STATE = 0;
    public static final int PANEL_STATE_CLOSE = 2;
    public static final int PANEL_STATE_DEF = 0;
    public static final int PANEL_STATE_OPEN = 1;
    public static final String SYS_PANEL_STATE_CHANGE_ACTION = "com.byd.action.panel.state.change";
    public static final String TAG = "SystemPanelChangeReceiver";
    private final Runnable runnable = new Runnable() { // from class: com.byd.systemsettings.cpaa.SystemPanelChangeReceiver.1
        @Override // java.lang.Runnable
        public void run() {
            SystemPanelChangeReceiver.CURRENT_PANEL_STATE = 0;
            a.a(SystemPanelChangeReceiver.TAG, "面板关闭后，3s恢复默认值");
        }
    };

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && SYS_PANEL_STATE_CHANGE_ACTION.equals(intent.getAction())) {
            boolean carPlayDisplayStatus = CarPlayManager.getInstance(context).getCarPlayDisplayStatus();
            boolean booleanExtra = intent.getBooleanExtra("expanded", false);
            a.a(TAG, "PanelChangeReceiver expanded:" + booleanExtra);
            if (!carPlayDisplayStatus) {
                g.a().f(this.runnable);
                g.a().d(this.runnable, BTDevice.CONNECTED_TIME_STANDARD);
                a.a(TAG, "CP不在投屏页 return");
            } else if (booleanExtra) {
                CURRENT_PANEL_STATE = 1;
                g.a().f(this.runnable);
            } else {
                CURRENT_PANEL_STATE = 2;
                g.a().d(this.runnable, 3000L);
            }
        }
    }
}
