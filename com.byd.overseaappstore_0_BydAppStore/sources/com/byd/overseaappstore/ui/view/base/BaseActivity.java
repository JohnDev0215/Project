package com.byd.overseaappstore.ui.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.viewbinding.ViewBinding;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.d;
import spi.sr;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/BaseActivity;", "Landroidx/viewbinding/ViewBinding;", "VB", "Landroidx/appcompat/app/AppCompatActivity;", "Landroidx/lifecycle/LifecycleEventObserver;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity implements LifecycleEventObserver {

    @Nullable
    public VB a;

    @Nullable
    public Toast b;

    public static void m(BaseActivity baseActivity, String tip, int i, int i2, Object obj) {
        Objects.requireNonNull(baseActivity);
        Intrinsics.checkNotNullParameter(tip, "tip");
        Toast toast = baseActivity.b;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(baseActivity.getApplicationContext(), tip, 0);
        baseActivity.b = toastMakeText;
        if (toastMakeText != null) {
            toastMakeText.show();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(@Nullable MotionEvent motionEvent) {
        ct.a("dispatchTouchEvent BaseActivity MotionEvent action : " + (motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null), new Object[0]);
        return super.dispatchTouchEvent(motionEvent);
    }

    @NotNull
    public abstract VB h();

    public abstract void i();

    public abstract void j();

    public abstract void k();

    public abstract void l();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getLifecycle().addObserver(this);
        sr srVar = sr.a;
        String strE = srVar.e(this);
        int iD = srVar.d(this);
        int iHashCode = bundle != null ? bundle.hashCode() : 0;
        StringBuilder sbK = d.k("baseActivity onCreate version versionName:", strE, " versionCode:", iD, " savedInstanceState:");
        sbK.append(iHashCode);
        ct.a(sbK.toString(), new Object[0]);
        VB vb = (VB) h();
        this.a = vb;
        setContentView(vb != null ? vb.getRoot() : null);
        j();
        i();
        k();
        if (!(bundle == null)) {
            ct.a("baseActivity onCreate savedInstanceState notNull noIntervene", new Object[0]);
        } else {
            getIntent();
            l();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(@Nullable Intent intent) {
        super.onNewIntent(intent);
        ct.a("baseActivity onNewIntent ===", new Object[0]);
        setIntent(intent);
        l();
    }
}
