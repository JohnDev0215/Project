package com.byd.auto_photo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.util.e;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class VideoControlReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1143a;

    public interface a {
        void a(int i, boolean z);

        void b(boolean z);
    }

    public VideoControlReceiver(a aVar) {
        this.f1143a = aVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.byd.ACTION_VIDEO_CONTROL".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("pkgName");
            w.c("ACTION_VIDEO_CONTROL : " + stringExtra);
            if (e.I(stringExtra)) {
                int intExtra = intent.getIntExtra("opt", 0);
                w.c("VideoController receiv : " + intExtra);
                if (intExtra == 1) {
                    this.f1143a.b(intent.getBooleanExtra("fullstate", false));
                } else {
                    if (intExtra != 2) {
                        return;
                    }
                    int intExtra2 = intent.getIntExtra("time", 0);
                    boolean booleanExtra = intent.getBooleanExtra("baseCurrent", false);
                    if (intExtra2 != 0) {
                        this.f1143a.a(intExtra2, booleanExtra);
                    }
                }
            }
        }
    }
}
