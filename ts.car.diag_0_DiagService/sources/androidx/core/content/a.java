package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.e;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f449a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static TypedValue f450b;

    /* JADX INFO: renamed from: androidx.core.content.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static class ExecutorC0011a implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Handler f451a;

        ExecutorC0011a(@NonNull Handler handler) {
            this.f451a = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.f451a.post(runnable)) {
                return;
            }
            throw new RejectedExecutionException(this.f451a + " is shutting down");
        }
    }

    @Nullable
    public static Context a(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext();
        }
        return null;
    }

    @ColorInt
    public static int b(@NonNull Context context, @ColorRes int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColor(i) : context.getResources().getColor(i);
    }

    @Nullable
    public static ColorStateList c(@NonNull Context context, @ColorRes int i) {
        return e.c(context.getResources(), i, context.getTheme());
    }

    @Nullable
    public static Drawable d(@NonNull Context context, @DrawableRes int i) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            return context.getDrawable(i);
        }
        if (i3 >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (f449a) {
            if (f450b == null) {
                f450b = new TypedValue();
            }
            context.getResources().getValue(i, f450b, true);
            i2 = f450b.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }

    @NonNull
    public static File[] e(@NonNull Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }

    @NonNull
    public static File[] f(@NonNull Context context, @Nullable String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static Executor g(Context context) {
        return Build.VERSION.SDK_INT >= 28 ? context.getMainExecutor() : new ExecutorC0011a(new Handler(context.getMainLooper()));
    }

    public static boolean h(@NonNull Context context, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intentArr, bundle);
            return true;
        }
        context.startActivities(intentArr);
        return true;
    }
}
