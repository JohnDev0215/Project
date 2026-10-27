package cerence.ark.assistant.core;

import android.os.Handler;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.api.notification.INotification;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.service.INotificationListener;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationListenerImpl extends INotificationListener.Stub {
    private static final String TAG = "NotificationListenerImpl";
    private final Handler mHandler;
    private final HmiListener mHmiListener;

    public NotificationListenerImpl(HmiListener hmiListener, Handler handler) {
        this.mHmiListener = hmiListener;
        this.mHandler = handler;
    }

    @Override // cerence.ark.assistant.service.INotificationListener
    public void onNotificationReceived(String str, String str2, int i, int i2) {
        Utils.processStringAssembler(str, str2, i, i2, new IAssemblerListener() { // from class: cerence.ark.assistant.core.NotificationListenerImpl$$ExternalSyntheticLambda0
            @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
            public final void onFinal(Object obj) {
                this.f$0.handleNotification((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleNotification(java.lang.String r4) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cerence.ark.assistant.core.NotificationListenerImpl.handleNotification(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendNotification, reason: merged with bridge method [inline-methods] */
    public void m60x8b932451(INotification iNotification) {
        HmiListener hmiListener = this.mHmiListener;
        if (hmiListener == null) {
            Logger.warn(TAG, "handleNotification: HmiListener is null");
        } else {
            hmiListener.onNotification(iNotification);
        }
    }
}
