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
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.i;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import w.a;

/* JADX INFO: compiled from: FragmentActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ComponentActivity implements a.b {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    boolean f2309s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    boolean f2310t;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    final g f2307q = g.b(new c());

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    final androidx.lifecycle.p f2308r = new androidx.lifecycle.p(this);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f2311u = true;

    /* JADX INFO: compiled from: FragmentActivity.java */
    class a implements SavedStateRegistry.b {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            Bundle bundle = new Bundle();
            d.this.x();
            d.this.f2308r.h(i.b.ON_STOP);
            Parcelable parcelableX = d.this.f2307q.x();
            if (parcelableX != null) {
                bundle.putParcelable("android:support:fragments", parcelableX);
            }
            return bundle;
        }
    }

    /* JADX INFO: compiled from: FragmentActivity.java */
    class b implements c.b {
        b() {
        }

        @Override // c.b
        public void a(Context context) {
            d.this.f2307q.a(null);
            Bundle bundleA = d.this.d().a("android:support:fragments");
            if (bundleA != null) {
                d.this.f2307q.w(bundleA.getParcelable("android:support:fragments"));
            }
        }
    }

    /* JADX INFO: compiled from: FragmentActivity.java */
    class c extends i<d> implements e0, androidx.activity.c, androidx.activity.result.c, n {
        public c() {
            super(d.this);
        }

        @Override // androidx.lifecycle.o
        public androidx.lifecycle.i a() {
            return d.this.f2308r;
        }

        @Override // androidx.fragment.app.n
        public void b(FragmentManager fragmentManager, Fragment fragment) {
            d.this.z(fragment);
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher c() {
            return d.this.c();
        }

        @Override // androidx.fragment.app.i, androidx.fragment.app.f
        public View f(int i7) {
            return d.this.findViewById(i7);
        }

        @Override // androidx.activity.result.c
        public ActivityResultRegistry g() {
            return d.this.g();
        }

        @Override // androidx.fragment.app.i, androidx.fragment.app.f
        public boolean h() {
            Window window = d.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.lifecycle.e0
        public d0 k() {
            return d.this.k();
        }

        @Override // androidx.fragment.app.i
        public void m(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            d.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.i
        public LayoutInflater o() {
            return d.this.getLayoutInflater().cloneInContext(d.this);
        }

        @Override // androidx.fragment.app.i
        public boolean p(Fragment fragment) {
            return !d.this.isFinishing();
        }

        @Override // androidx.fragment.app.i
        public void q() {
            d.this.C();
        }

        @Override // androidx.fragment.app.i
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public d n() {
            return d.this;
        }
    }

    public d() {
        w();
    }

    private void w() {
        d().d("android:support:fragments", new a());
        o(new b());
    }

    private static boolean y(FragmentManager fragmentManager, i.c cVar) {
        boolean zY = false;
        for (Fragment fragment : fragmentManager.s0()) {
            if (fragment != null) {
                if (fragment.B() != null) {
                    zY |= y(fragment.r(), cVar);
                }
                v vVar = fragment.Y;
                if (vVar != null && vVar.a().b().isAtLeast(i.c.STARTED)) {
                    fragment.Y.i(cVar);
                    zY = true;
                }
                if (fragment.X.b().isAtLeast(i.c.STARTED)) {
                    fragment.X.o(cVar);
                    zY = true;
                }
            }
        }
        return zY;
    }

    @Deprecated
    protected boolean A(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    protected void B() {
        this.f2308r.h(i.b.ON_RESUME);
        this.f2307q.p();
    }

    @Deprecated
    public void C() {
        invalidateOptionsMenu();
    }

    @Override // w.a.b
    @Deprecated
    public final void b(int i7) {
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f2309s);
        printWriter.print(" mResumed=");
        printWriter.print(this.f2310t);
        printWriter.print(" mStopped=");
        printWriter.print(this.f2311u);
        if (getApplication() != null) {
            androidx.loader.app.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.f2307q.t().W(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i7, int i8, Intent intent) {
        this.f2307q.u();
        super.onActivityResult(i7, i8, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2307q.u();
        this.f2307q.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, w.f, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2308r.h(i.b.ON_CREATE);
        this.f2307q.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i7, Menu menu) {
        return i7 == 0 ? super.onCreatePanelMenu(i7, menu) | this.f2307q.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i7, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewU = u(view, str, context, attributeSet);
        return viewU == null ? super.onCreateView(view, str, context, attributeSet) : viewU;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f2307q.h();
        this.f2308r.h(i.b.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.f2307q.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        if (i7 == 0) {
            return this.f2307q.k(menuItem);
        }
        if (i7 != 6) {
            return false;
        }
        return this.f2307q.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z6) {
        this.f2307q.j(z6);
    }

    @Override // android.app.Activity
    protected void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.f2307q.u();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i7, Menu menu) {
        if (i7 == 0) {
            this.f2307q.l(menu);
        }
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.f2310t = false;
        this.f2307q.m();
        this.f2308r.h(i.b.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z6) {
        this.f2307q.n(z6);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        B();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i7, View view, Menu menu) {
        return i7 == 0 ? A(view, menu) | this.f2307q.o(menu) : super.onPreparePanel(i7, view, menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        this.f2307q.u();
        super.onRequestPermissionsResult(i7, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f2310t = true;
        this.f2307q.u();
        this.f2307q.s();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f2311u = false;
        if (!this.f2309s) {
            this.f2309s = true;
            this.f2307q.c();
        }
        this.f2307q.u();
        this.f2307q.s();
        this.f2308r.h(i.b.ON_START);
        this.f2307q.q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.f2307q.u();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.f2311u = true;
        x();
        this.f2307q.r();
        this.f2308r.h(i.b.ON_STOP);
    }

    final View u(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2307q.v(view, str, context, attributeSet);
    }

    public FragmentManager v() {
        return this.f2307q.t();
    }

    void x() {
        while (y(v(), i.c.CREATED)) {
        }
    }

    @Deprecated
    public void z(Fragment fragment) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewU = u(null, str, context, attributeSet);
        return viewU == null ? super.onCreateView(str, context, attributeSet) : viewU;
    }
}
