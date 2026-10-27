package com.king.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: InactivityTimer.java */
/* JADX INFO: loaded from: classes.dex */
final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4632a = "l";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f4633b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BroadcastReceiver f4634c = new b(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4635d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private AsyncTask<Object, Object, Object> f4636e;

    /* JADX INFO: compiled from: InactivityTimer.java */
    private static class a extends AsyncTask<Object, Object, Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<Activity> f4637a;

        public a(Activity activity) {
            this.f4637a = new WeakReference<>(activity);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000L);
                Log.i(l.f4632a, "Finishing activity due to inactivity");
                Activity activity = this.f4637a.get();
                if (activity == null) {
                    return null;
                }
                activity.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: InactivityTimer.java */
    private static class b extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<l> f4638a;

        public b(l lVar) {
            this.f4638a = new WeakReference<>(lVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            l lVar;
            if (!"android.intent.action.BATTERY_CHANGED".equals(intent.getAction()) || (lVar = this.f4638a.get()) == null) {
                return;
            }
            if (intent.getIntExtra("plugged", -1) <= 0) {
                lVar.d();
            } else {
                lVar.c();
            }
        }
    }

    l(Activity activity) {
        this.f4633b = activity;
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        AsyncTask<Object, Object, Object> asyncTask = this.f4636e;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f4636e = null;
        }
    }

    void d() {
        c();
        a aVar = new a(this.f4633b);
        this.f4636e = aVar;
        try {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } catch (RejectedExecutionException unused) {
            Log.w(f4632a, "Couldn't schedule inactivity task; ignoring");
        }
    }

    void e() {
        c();
        if (!this.f4635d) {
            Log.w(f4632a, "PowerStatusReceiver was never registered?");
        } else {
            this.f4633b.unregisterReceiver(this.f4634c);
            this.f4635d = false;
        }
    }

    void f() {
        if (this.f4635d) {
            Log.w(f4632a, "PowerStatusReceiver was already registered?");
        } else {
            this.f4633b.registerReceiver(this.f4634c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.f4635d = true;
        }
        d();
    }

    void g() {
        c();
    }
}
