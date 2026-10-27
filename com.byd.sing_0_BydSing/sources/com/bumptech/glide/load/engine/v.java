package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: ResourceRecycler.java */
/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f2838b = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: compiled from: ResourceRecycler.java */
    private static final class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((s) message.obj).a();
            return true;
        }
    }

    v() {
    }

    synchronized void a(s<?> sVar, boolean z) {
        if (this.f2837a || z) {
            this.f2838b.obtainMessage(1, sVar).sendToTarget();
        } else {
            this.f2837a = true;
            sVar.a();
            this.f2837a = false;
        }
    }
}
