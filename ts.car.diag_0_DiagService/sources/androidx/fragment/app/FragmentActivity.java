package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.Lifecycle;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity {
    boolean l;
    boolean m;
    final g j = g.b(new a());
    final androidx.lifecycle.g k = new androidx.lifecycle.g(this);
    boolean n = true;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends i<FragmentActivity> implements androidx.lifecycle.q, androidx.activity.c, androidx.activity.result.c, n {
        public a() {
            super(FragmentActivity.this);
        }

        @Override // androidx.lifecycle.f
        @NonNull
        public Lifecycle a() {
            return FragmentActivity.this.k;
        }

        @Override // androidx.activity.c
        @NonNull
        public OnBackPressedDispatcher b() {
            return FragmentActivity.this.b();
        }

        @Override // androidx.activity.result.c
        @NonNull
        public ActivityResultRegistry d() {
            return FragmentActivity.this.d();
        }

        @Override // androidx.fragment.app.n
        public void e(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            FragmentActivity.this.r(fragment);
        }

        @Override // androidx.fragment.app.i, androidx.fragment.app.e
        @Nullable
        public View g(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        @Override // androidx.lifecycle.q
        @NonNull
        public androidx.lifecycle.p h() {
            return FragmentActivity.this.h();
        }

        @Override // androidx.fragment.app.i, androidx.fragment.app.e
        public boolean i() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.fragment.app.i
        @NonNull
        public LayoutInflater n() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // androidx.fragment.app.i
        public boolean o(@NonNull Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // androidx.fragment.app.i
        public void p() {
            FragmentActivity.this.u();
        }

        @Override // androidx.fragment.app.i
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public FragmentActivity m() {
            return FragmentActivity.this;
        }
    }

    private void p() {
        while (q(o(), Lifecycle.State.CREATED)) {
        }
    }

    private static boolean q(FragmentManager fragmentManager, Lifecycle.State state) {
        boolean zQ = false;
        for (Fragment fragment : fragmentManager.r0()) {
            if (fragment != null) {
                if (fragment.w() != null) {
                    zQ |= q(fragment.q(), state);
                }
                v vVar = fragment.U;
                if (vVar != null && vVar.a().b().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.U.i(state);
                    zQ = true;
                }
                if (fragment.T.b().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.T.n(state);
                    zQ = true;
                }
            }
        }
        return zQ;
    }

    @Override // android.app.Activity
    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.l);
        printWriter.print(" mResumed=");
        printWriter.print(this.m);
        printWriter.print(" mStopped=");
        printWriter.print(this.n);
        if (getApplication() != null) {
            androidx.loader.a.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.j.t().W(str, fileDescriptor, printWriter, strArr);
    }

    @Nullable
    final View n(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.j.v(view, str, context, attributeSet);
    }

    @NonNull
    public FragmentManager o() {
        return this.j.t();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @CallSuper
    protected void onActivityResult(int i, int i2, @Nullable Intent intent) {
        this.j.u();
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.j.u();
        this.j.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        this.j.a(null);
        if (bundle != null) {
            this.j.w(bundle.getParcelable("android:support:fragments"));
        }
        super.onCreate(bundle);
        this.k.g(Lifecycle.Event.ON_CREATE);
        this.j.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, @NonNull Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        return this.j.g(menu, getMenuInflater()) | super.onCreatePanelMenu(i, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View viewN = n(view, str, context, attributeSet);
        return viewN == null ? super.onCreateView(view, str, context, attributeSet) : viewN;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.j.h();
        this.k.g(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.j.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, @NonNull MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.j.k(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.j.e(menuItem);
    }

    @Override // android.app.Activity
    @CallSuper
    public void onMultiWindowModeChanged(boolean z) {
        this.j.j(z);
    }

    @Override // android.app.Activity
    @CallSuper
    protected void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.j.u();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, @NonNull Menu menu) {
        if (i == 0) {
            this.j.l(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.m = false;
        this.j.m();
        this.k.g(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    @CallSuper
    public void onPictureInPictureModeChanged(boolean z) {
        this.j.n(z);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        t();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, @Nullable View view, @NonNull Menu menu) {
        if (i != 0) {
            return super.onPreparePanel(i, view, menu);
        }
        return this.j.o(menu) | s(view, menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @CallSuper
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.j.u();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.m = true;
        this.j.u();
        this.j.s();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        p();
        this.k.g(Lifecycle.Event.ON_STOP);
        Parcelable parcelableX = this.j.x();
        if (parcelableX != null) {
            bundle.putParcelable("android:support:fragments", parcelableX);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.n = false;
        if (!this.l) {
            this.l = true;
            this.j.c();
        }
        this.j.u();
        this.j.s();
        this.k.g(Lifecycle.Event.ON_START);
        this.j.q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.j.u();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.n = true;
        p();
        this.j.r();
        this.k.g(Lifecycle.Event.ON_STOP);
    }

    @MainThread
    @Deprecated
    public void r(@NonNull Fragment fragment) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    protected boolean s(@Nullable View view, @NonNull Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    protected void t() {
        this.k.g(Lifecycle.Event.ON_RESUME);
        this.j.p();
    }

    @Deprecated
    public void u() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View viewN = n(null, str, context, attributeSet);
        return viewN == null ? super.onCreateView(str, context, attributeSet) : viewN;
    }
}
