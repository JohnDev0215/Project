package com.byd.headset;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetButtonReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.byd.headset.a f3541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static HeadsetButtonReceiver f3542b = new HeadsetButtonReceiver();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3543c = "DANDAN";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Handler f3544d = new a(Looper.getMainLooper());

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (HeadsetButtonReceiver.f3541a == null) {
                return;
            }
            int i = message.what;
            if (i == 85) {
                BydLogHelper.logD("DANDAN", "KEYCODE_MEDIA_PLAY_PAUSE");
                HeadsetButtonReceiver.f3541a.c();
                return;
            }
            if (i == 87) {
                BydLogHelper.logD("DANDAN", "KEYCODE_MEDIA_NEXT");
                HeadsetButtonReceiver.f3541a.b();
                return;
            }
            if (i == 88) {
                BydLogHelper.logD("DANDAN", "KEYCODE_MEDIA_PREVIOUS");
                HeadsetButtonReceiver.f3541a.d();
            } else if (i == 126) {
                BydLogHelper.logD("DANDAN", "KEYCODE_MEDIA_PLAY");
                HeadsetButtonReceiver.f3541a.e();
            } else {
                if (i != 127) {
                    return;
                }
                BydLogHelper.logD("DANDAN", "KEYCODE_MEDIA_PAUSE");
                HeadsetButtonReceiver.f3541a.a();
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logE("DANDAN", "[onReceive] action:" + action);
        if ("android.intent.action.MEDIA_BUTTON".equals(action)) {
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent.getKeyCode() == 79 && keyEvent.getAction() == 1) {
                this.f3544d.sendEmptyMessage(85);
            } else if (keyEvent.getAction() == 1) {
                this.f3544d.sendEmptyMessage(keyEvent.getKeyCode());
            }
        }
    }
}
