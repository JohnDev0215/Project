package cerence.ark.assistant.core;

import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.api.notification.INotification;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.INotificationListener;

/* JADX INFO: loaded from: classes.dex */
public class NotificationListenerImpl extends INotificationListener.Stub {
    private static final String TAG = "NotificationListenerImpl";
    private final HmiListener mHmiListener;
    private StringAssembler mNotificationAssembler;

    public NotificationListenerImpl(HmiListener hmiListener) {
        this.mHmiListener = hmiListener;
    }

    @Override // cerence.ark.assistant.service.INotificationListener
    public void onNotificationReceived(String str, boolean z, boolean z2) {
        if (z) {
            this.mNotificationAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mNotificationAssembler.assemble(str);
            this.mNotificationAssembler = null;
            handleNotification(strAssemble);
            return;
        }
        this.mNotificationAssembler.assemble(str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleNotification(java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cerence.ark.assistant.core.NotificationListenerImpl.handleNotification(java.lang.String):void");
    }

    /* JADX INFO: renamed from: lambda$handleNotification$0$cerence-ark-assistant-core-NotificationListenerImpl, reason: not valid java name */
    /* synthetic */ void m42x8b932451(INotification iNotification) {
        HmiListener hmiListener = this.mHmiListener;
        if (hmiListener == null) {
            Logger.warn(TAG, "handleNotification: HmiListener is null");
        } else {
            hmiListener.onNotification(iNotification);
        }
    }
}
