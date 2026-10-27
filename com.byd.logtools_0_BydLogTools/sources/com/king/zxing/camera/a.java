package com.king.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: AutoFocusManager.java */
/* JADX INFO: loaded from: classes.dex */
final class a implements Camera.AutoFocusCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4556a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Collection<String> f4557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4558c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4559d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f4560e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Camera f4561f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private AsyncTask<?, ?, ?> f4562g;

    /* JADX INFO: renamed from: com.king.zxing.camera.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoFocusManager.java */
    private static class AsyncTaskC0081a extends AsyncTask<Object, Object, Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<a> f4563a;

        public AsyncTaskC0081a(a aVar) {
            this.f4563a = new WeakReference<>(aVar);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(1200L);
            } catch (InterruptedException unused) {
            }
            a aVar = this.f4563a.get();
            if (aVar == null) {
                return null;
            }
            aVar.c();
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(2);
        f4557b = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    a(Context context, Camera camera) {
        this.f4561f = camera;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera.getParameters().getFocusMode();
        boolean z = defaultSharedPreferences.getBoolean("preferences_auto_focus", true) && f4557b.contains(focusMode);
        this.f4560e = z;
        Log.i(f4556a, "Current focus mode '" + focusMode + "'; use auto focus? " + z);
        c();
    }

    private synchronized void a() {
        if (!this.f4558c && this.f4562g == null) {
            AsyncTaskC0081a asyncTaskC0081a = new AsyncTaskC0081a(this);
            try {
                asyncTaskC0081a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                this.f4562g = asyncTaskC0081a;
            } catch (RejectedExecutionException e2) {
                Log.w(f4556a, "Could not request auto focus", e2);
            }
        }
    }

    private synchronized void b() {
        AsyncTask<?, ?, ?> asyncTask = this.f4562g;
        if (asyncTask != null) {
            if (asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.f4562g.cancel(true);
            }
            this.f4562g = null;
        }
    }

    synchronized void c() {
        if (this.f4560e) {
            this.f4562g = null;
            if (!this.f4558c && !this.f4559d) {
                try {
                    this.f4561f.autoFocus(this);
                    this.f4559d = true;
                } catch (RuntimeException e2) {
                    Log.w(f4556a, "Unexpected exception while focusing", e2);
                    a();
                }
            }
        }
    }

    synchronized void d() {
        this.f4558c = true;
        if (this.f4560e) {
            b();
            try {
                this.f4561f.cancelAutoFocus();
            } catch (RuntimeException e2) {
                Log.w(f4556a, "Unexpected exception while cancelling focusing", e2);
            }
        }
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public synchronized void onAutoFocus(boolean z, Camera camera) {
        this.f4559d = false;
        a();
    }
}
