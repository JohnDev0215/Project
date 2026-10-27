package com.android.car.media.byd;

import android.car.utils.Logs;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.common.playback.PlaybackViewModel;

/* JADX INFO: loaded from: classes.dex */
public abstract class BydBaseCarMediaReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int b = 0;
    public final transient Handler a = new Handler(Looper.getMainLooper());

    public final PlaybackViewModel a() {
        return PlaybackViewModel.a(BaseMediaApplication.b, 1);
    }

    public final boolean b() {
        if (a() != null && a().e != null) {
            return a().e.getValue() != null;
        }
        Logs.e("BydCarMediaReceiver", "isAmazonRunning: getPlaybackViewModel is null!");
        return false;
    }

    public final void c() {
        Logs.i("BydCarMediaReceiver", "openApp");
        Intent intent = new Intent();
        intent.setClassName(BaseMediaApplication.b.getPackageName(), "com.android.car.media.MediaDispatcherActivity");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.INFO");
        intent.addCategory("android.intent.category.APP_MUSIC");
        intent.setFlags(268435456);
        BaseMediaApplication.b.startActivity(intent);
    }
}
