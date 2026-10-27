package com.neusoft.na.byd;

import android.content.Context;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.util.Log;
import com.neusoft.na.common.IBydBootAdapterComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Byd8155fBootAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fBootAdapter;", "Lcom/neusoft/na/common/IBydBootAdapterComponent;", "()V", "bydAutoSettingDevice", "Landroid/hardware/bydauto/setting/BYDAutoSettingDevice;", "notifyDiLinkStarted", "", "context", "Landroid/content/Context;", "sendNotifyDiLinkStarted", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Byd8155fBootAdapter implements IBydBootAdapterComponent {
    private static final String TAG = "Byd8155fBootAdapter";
    private BYDAutoSettingDevice bydAutoSettingDevice;

    @Override // com.neusoft.na.common.IBydBootAdapterComponent
    public void notifyDiLinkStarted(Context context) throws InterruptedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "[notifyDiLinkStarted]");
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
        this.bydAutoSettingDevice = bYDAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.registerListener(new AbsBYDAutoSettingListener() { // from class: com.neusoft.na.byd.Byd8155fBootAdapter.notifyDiLinkStarted.1
                @Override // android.hardware.IBYDAutoListener
                public void isConnected(boolean isBinding) {
                    super.isConnected(isBinding);
                    Log.i(Byd8155fBootAdapter.TAG, "[isConnected] --> isBinding == " + isBinding);
                    if (isBinding) {
                        Byd8155fBootAdapter.this.sendNotifyDiLinkStarted();
                    }
                }
            });
        }
        Thread.sleep(300L);
        sendNotifyDiLinkStarted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNotifyDiLinkStarted() {
        Log.i(TAG, "[sendNotifyDiLinkStarted]");
        BYDAutoSettingDevice bYDAutoSettingDevice = this.bydAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.notifyDilinkStarted();
        }
    }
}
