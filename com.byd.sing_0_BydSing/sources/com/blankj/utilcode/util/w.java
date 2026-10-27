package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Objects;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static Application f2622a;

    /* JADX INFO: compiled from: Utils.java */
    public static class a {
        public void a(Activity activity) {
        }

        public void b(Activity activity) {
        }

        public void c(Activity activity) {
        }

        public void d(Activity activity) {
        }

        public void e(Activity activity) {
        }

        public void f(Activity activity) {
        }

        public void g(Activity activity, Lifecycle.Event event) {
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    public interface b<T> {
        void accept(T t);
    }

    /* JADX INFO: compiled from: Utils.java */
    public interface c {
        void a(Activity activity);

        void b(Activity activity);
    }

    /* JADX INFO: compiled from: Utils.java */
    public static abstract class d<Result> extends ThreadUtils.d<Result> {
        private b<Result> mConsumer;

        public d(b<Result> bVar) {
            this.mConsumer = bVar;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.e
        public void onSuccess(Result result) {
            b<Result> bVar = this.mConsumer;
            if (bVar != null) {
                bVar.accept(result);
            }
        }
    }

    public static Application a() {
        Application application = f2622a;
        if (application != null) {
            return application;
        }
        b(y.i());
        Objects.requireNonNull(f2622a, "reflect failed.");
        Log.i("Utils", y.j() + " reflect app success.");
        return f2622a;
    }

    public static void b(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = f2622a;
        if (application2 == null) {
            f2622a = application;
            y.q(application);
            y.z();
        } else {
            if (application2.equals(application)) {
                return;
            }
            y.E(f2622a);
            f2622a = application;
            y.q(application);
        }
    }
}
