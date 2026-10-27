package com.android.permissioncontroller.incident;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IncidentManager;

/* JADX INFO: loaded from: classes8.dex */
public class ApprovalReceiver extends BroadcastReceiver {
    public static final String ACTION_APPROVE = "com.android.permissioncontroller.incident.APPROVE";
    public static final String ACTION_DENY = "com.android.permissioncontroller.incident.DENY";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            IncidentManager incidentManager = (IncidentManager) context.getSystemService(IncidentManager.class);
            if (ACTION_APPROVE.equals(intent.getAction())) {
                incidentManager.approveReport(uri);
            } else if (ACTION_DENY.equals(intent.getAction())) {
                incidentManager.denyReport(uri);
            }
        }
        PendingList.getInstance().updateState(context, 1);
    }
}
