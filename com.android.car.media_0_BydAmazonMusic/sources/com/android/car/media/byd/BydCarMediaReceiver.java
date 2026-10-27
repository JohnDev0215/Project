package com.android.car.media.byd;

import android.car.XCarRoute;
import android.car.media.IXCarMediaControl;
import android.car.utils.Logs;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.blankj.utilcode.util.ActivityUtils;
import spi.m0;
import spi.r7;

/* JADX INFO: loaded from: classes.dex */
public class BydCarMediaReceiver extends BydBaseCarMediaReceiver {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            Log.d("BydCarMediaReceiver_8155f", "amazon music BydCarButtonReceiver:carMediaControl is null");
            return;
        }
        Log.d("BydCarMediaReceiver_8155f", "amazon music BydCarButtonReceiver:action:" + action);
        IXCarMediaControl iXCarMediaControl = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
        byte b = -1;
        boolean z = false;
        switch (action.hashCode()) {
            case -1788671334:
                if (action.equals("com.byd.systemui.ACTION_KILL_PROCESS")) {
                    b = 0;
                }
                break;
            case -1008782642:
                if (action.equals("byd.intent.action.MEDIA_AMAZON.PAUSE")) {
                    b = 1;
                }
                break;
            case 798686971:
                if (action.equals("byd.intent.action.MEDIA_AMAZON.NEXT")) {
                    b = 2;
                }
                break;
            case 798752572:
                if (action.equals("byd.intent.action.MEDIA_AMAZON.PLAY")) {
                    b = 3;
                }
                break;
            case 921859774:
                if (action.equals("byd.intent.action.AMAZON_MEMORY_PLAY")) {
                    b = 4;
                }
                break;
            case 990044927:
                if (action.equals("byd.intent.action.MEDIA_AMAZON.PREVIOUS")) {
                    b = 5;
                }
                break;
        }
        if (b == 0) {
            if (iXCarMediaControl == null) {
                Log.e("BydCarMediaReceiver_8155f", "killAmazon:carMediaControl is null");
                return;
            }
            iXCarMediaControl.stop();
            r7.a.a.a(BaseMediaApplication.b.getApplicationContext());
            ActivityUtils.finishAllActivities();
            return;
        }
        if (b == 1) {
            if (iXCarMediaControl == null || !b()) {
                return;
            }
            iXCarMediaControl.pause();
            Log.d("BydCarMediaReceiver", "amazon music BydCarButtonReceiver:test pause");
            return;
        }
        if (b == 2) {
            if (iXCarMediaControl != null && b()) {
                if (a() == null || a().j == null) {
                    Logs.e("BydCarMediaReceiver", "isSkipNextEnabled: getPlaybackViewModel is null!");
                } else {
                    PlaybackViewModel.e value = a().j.getValue();
                    if (value != null && value.e()) {
                        z = true;
                    }
                }
                if (z) {
                    iXCarMediaControl.next();
                    Log.d("BydCarMediaReceiver", "amazon music BydCarButtonReceiver:test next");
                    return;
                }
            }
            c();
            this.a.postDelayed(m0.d, 1500L);
            return;
        }
        if (b == 3 || b == 4) {
            if (iXCarMediaControl == null || !b()) {
                c();
                this.a.postDelayed(m0.b, 1500L);
                return;
            } else {
                iXCarMediaControl.play();
                Log.d("BydCarMediaReceiver", "amazon music BydCarButtonReceiver:test play");
                return;
            }
        }
        if (b != 5) {
            return;
        }
        if (iXCarMediaControl != null && b()) {
            if (a() == null || a().j == null) {
                Logs.e("BydCarMediaReceiver", "isSkipPreviousEnabled: getPlaybackViewModel is null!");
            } else {
                PlaybackViewModel.e value2 = a().j.getValue();
                if (value2 != null && value2.f()) {
                    z = true;
                }
            }
            if (z) {
                iXCarMediaControl.previous();
                Log.d("BydCarMediaReceiver", "amazon music BydCarButtonReceiver:test previous");
                return;
            }
        }
        c();
        this.a.postDelayed(m0.c, 1500L);
    }
}
