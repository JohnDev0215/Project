package com.byd.logtools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.logtools.logservice.BYDLogService;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BydLogReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DROPBOX_ENTRY_ADDED".equals(intent.getAction())) {
            HashSet hashSet = new HashSet();
            hashSet.add("system_app_anr");
            hashSet.add("system_app_native_crash");
            hashSet.add("system_app_crash");
            hashSet.add("data_app_anr");
            hashSet.add("data_app_native_crash");
            hashSet.add("data_app_crash");
            String stringExtra = intent.getStringExtra("tag");
            Long lValueOf = Long.valueOf(intent.getLongExtra("time", 0L));
            BydLogHelper.logD("action = android.intent.action.DROPBOX_ENTRY_ADDED, tag = " + stringExtra + ", time =" + lValueOf);
            if (hashSet.contains(stringExtra)) {
                Intent intent2 = new Intent(context, (Class<?>) BYDLogService.class);
                intent2.setAction("android.intent.action.DROPBOX_ENTRY_ADDED");
                intent2.putExtra("crashTag", stringExtra);
                intent2.putExtra("crashTime", String.valueOf(lValueOf));
                context.startService(intent2);
                return;
            }
            return;
        }
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            BydLogHelper.logD("receive ACTION_BOOT_COMPLETED");
            Intent intent3 = new Intent(context, (Class<?>) BYDLogService.class);
            intent3.setAction("android.intent.action.BOOT_COMPLETED");
            context.startService(intent3);
            return;
        }
        if ("android.intent.action.CMD_SAVE_ALL_LOG".equals(intent.getAction())) {
            BydLogHelper.logD("receive ACTION_CMD_SAVE_ALL_LOG");
            Intent intent4 = new Intent(context, (Class<?>) BYDLogService.class);
            intent4.setAction("android.intent.action.CMD_SAVE_ALL_LOG");
            context.startService(intent4);
            return;
        }
        if ("bydlogtool.action.savelog".equals(intent.getAction())) {
            BydLogHelper.logD("receive ACTION_VR_SAVE_LOG");
            String stringExtra2 = intent.getStringExtra("paths");
            boolean booleanExtra = intent.getBooleanExtra("needAp", true);
            boolean booleanExtra2 = intent.getBooleanExtra("needResult", true);
            Intent intent5 = new Intent(context, (Class<?>) BYDLogService.class);
            intent5.setAction("bydlogtool.action.savelog");
            intent5.putExtra("paths", stringExtra2);
            intent5.putExtra("needAp", booleanExtra);
            intent5.putExtra("needResult", booleanExtra2);
            context.startService(intent5);
        }
    }
}
