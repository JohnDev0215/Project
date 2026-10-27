package com.bumptech.glide.load.n;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f2200b = new Handler(Looper.getMainLooper(), new a());

    private static final class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((v) message.obj).f();
            return true;
        }
    }

    y() {
    }

    synchronized void a(v<?> vVar, boolean z) {
        if (this.f2199a || z) {
            this.f2200b.obtainMessage(1, vVar).sendToTarget();
        } else {
            this.f2199a = true;
            vVar.f();
            this.f2199a = false;
        }
    }
}
