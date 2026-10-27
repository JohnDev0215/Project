package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class i<E> extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final Activity f695c;

    @NonNull
    private final Context d;

    @NonNull
    private final Handler e;
    final FragmentManager f;

    i(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // androidx.fragment.app.e
    @Nullable
    public View g(int i) {
        return null;
    }

    @Override // androidx.fragment.app.e
    public boolean i() {
        return true;
    }

    @Nullable
    Activity j() {
        return this.f695c;
    }

    @NonNull
    Context k() {
        return this.d;
    }

    @NonNull
    Handler l() {
        return this.e;
    }

    @Nullable
    public abstract E m();

    @NonNull
    public LayoutInflater n() {
        return LayoutInflater.from(this.d);
    }

    public boolean o(@NonNull Fragment fragment) {
        return true;
    }

    public void p() {
    }

    i(@Nullable Activity activity, @NonNull Context context, @NonNull Handler handler, int i) {
        this.f = new l();
        this.f695c = activity;
        androidx.core.d.e.c(context, "context == null");
        this.d = context;
        androidx.core.d.e.c(handler, "handler == null");
        this.e = handler;
    }
}
