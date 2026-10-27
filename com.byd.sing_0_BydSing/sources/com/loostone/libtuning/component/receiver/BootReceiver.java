package com.loostone.libtuning.component.receiver;

import OooO00o.OooO00o.OooO0O0.b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.f;

/* JADX INFO: loaded from: classes.dex */
public final class BootReceiver extends BroadcastReceiver {

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    static {
        new OooO00o(null);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b.f41a.e("BootReceiver", "  开机启动");
    }
}
