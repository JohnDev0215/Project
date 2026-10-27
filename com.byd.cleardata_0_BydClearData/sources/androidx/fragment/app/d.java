package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.a;
import androidx.lifecycle.d;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class d extends ComponentActivity implements a.b, a.d {
    boolean k;
    boolean l;
    boolean n;
    boolean o;
    boolean p;
    int q;
    a.d.h<String> r;
    final f i = f.b(new a());
    final androidx.lifecycle.h j = new androidx.lifecycle.h(this);
    boolean m = true;

    class a extends h<d> implements androidx.lifecycle.s, androidx.activity.c {
        public a() {
            super(d.this);
        }

        @Override // androidx.lifecycle.g
        public androidx.lifecycle.d a() {
            return d.this.j;
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher c() {
            return d.this.c();
        }

        @Override // androidx.fragment.app.h, androidx.fragment.app.e
        public View e(int i) {
            return d.this.findViewById(i);
        }

        @Override // androidx.fragment.app.h, androidx.fragment.app.e
        public boolean f() {
            Window window = d.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.fragment.app.h
        public void j(Fragment fragment) {
            d.this.B(fragment);
        }

        @Override // androidx.fragment.app.h
        public void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            d.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.h
        public LayoutInflater m() {
            return d.this.getLayoutInflater().cloneInContext(d.this);
        }

        @Override // androidx.fragment.app.h
        public int n() {
            Window window = d.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // androidx.lifecycle.s
        public androidx.lifecycle.r o() {
            return d.this.o();
        }

        @Override // androidx.fragment.app.h
        public boolean p() {
            return d.this.getWindow() != null;
        }

        @Override // androidx.fragment.app.h
        public boolean q(Fragment fragment) {
            return !d.this.isFinishing();
        }

        @Override // androidx.fragment.app.h
        public void r(Fragment fragment, Intent intent, int i, Bundle bundle) {
            d.this.E(fragment, intent, i, bundle);
        }

        @Override // androidx.fragment.app.h
        public void s() {
            d.this.F();
        }

        @Override // androidx.fragment.app.h
        /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
        public d l() {
            return d.this;
        }
    }

    private static boolean A(i iVar, d.b bVar) {
        boolean zA = false;
        for (Fragment fragment : iVar.e()) {
            if (fragment != null) {
                if (fragment.a().b().a(d.b.STARTED)) {
                    fragment.U.p(bVar);
                    zA = true;
                }
                if (fragment.E() != null) {
                    zA |= A(fragment.x(), bVar);
                }
            }
        }
        return zA;
    }

    private int v(Fragment fragment) {
        if (this.r.l() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.r.g(this.q) >= 0) {
            this.q = (this.q + 1) % 65534;
        }
        int i = this.q;
        this.r.j(i, fragment.g);
        this.q = (this.q + 1) % 65534;
        return i;
    }

    static void w(int i) {
        if ((i & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    private void z() {
        while (A(y(), d.b.CREATED)) {
        }
    }

    public void B(Fragment fragment) {
    }

    @Deprecated
    protected boolean C(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    protected void D() {
        this.j.i(d.a.ON_RESUME);
        this.i.p();
    }

    public void E(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        this.p = true;
        try {
            if (i == -1) {
                androidx.core.app.a.m(this, intent, -1, bundle);
            } else {
                w(i);
                androidx.core.app.a.m(this, intent, ((v(fragment) + 1) << 16) + (i & 65535), bundle);
            }
        } finally {
            this.p = false;
        }
    }

    @Deprecated
    public void F() {
        invalidateOptionsMenu();
    }

    @Override // androidx.core.app.a.d
    public final void b(int i) {
        if (this.n || i == -1) {
            return;
        }
        w(i);
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
        printWriter.print(this.k);
        printWriter.print(" mResumed=");
        printWriter.print(this.l);
        printWriter.print(" mStopped=");
        printWriter.print(this.m);
        if (getApplication() != null) {
            a.l.a.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.i.u().b(str, fileDescriptor, printWriter, strArr);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        this.i.v();
        int i3 = i >> 16;
        if (i3 == 0) {
            a.c cVarI = androidx.core.app.a.i();
            if (cVarI == null || !cVarI.b(this, i, i2, intent)) {
                super.onActivityResult(i, i2, intent);
                return;
            }
            return;
        }
        int i4 = i3 - 1;
        String strE = this.r.e(i4);
        this.r.k(i4);
        if (strE == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentT = this.i.t(strE);
        if (fragmentT != null) {
            fragmentT.f0(i & 65535, i2, intent);
            return;
        }
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strE);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.i.v();
        this.i.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.i.a(null);
        if (bundle != null) {
            this.i.x(bundle.getParcelable("android:support:fragments"));
            if (bundle.containsKey("android:support:next_request_index")) {
                this.q = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.r = new a.d.h<>(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.r.j(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.r == null) {
            this.r = new a.d.h<>();
            this.q = 0;
        }
        super.onCreate(bundle);
        this.j.i(d.a.ON_CREATE);
        this.i.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return i == 0 ? super.onCreatePanelMenu(i, menu) | this.i.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewX = x(view, str, context, attributeSet);
        return viewX == null ? super.onCreateView(view, str, context, attributeSet) : viewX;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewX = x(null, str, context, attributeSet);
        return viewX == null ? super.onCreateView(str, context, attributeSet) : viewX;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.i.h();
        this.j.i(d.a.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.i.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.i.k(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.i.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.i.j(z);
    }

    @Override // android.app.Activity
    protected void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.i.v();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.i.l(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.l = false;
        this.i.m();
        this.j.i(d.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.i.n(z);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        D();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return i == 0 ? C(view, menu) | this.i.o(menu) : super.onPreparePanel(i, view, menu);
    }

    @Override // android.app.Activity, androidx.core.app.a.b
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.i.v();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String strE = this.r.e(i3);
            this.r.k(i3);
            if (strE == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentT = this.i.t(strE);
            if (fragmentT != null) {
                fragmentT.E0(i & 65535, strArr, iArr);
                return;
            }
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strE);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.l = true;
        this.i.v();
        this.i.s();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        z();
        this.j.i(d.a.ON_STOP);
        Parcelable parcelableY = this.i.y();
        if (parcelableY != null) {
            bundle.putParcelable("android:support:fragments", parcelableY);
        }
        if (this.r.l() > 0) {
            bundle.putInt("android:support:next_request_index", this.q);
            int[] iArr = new int[this.r.l()];
            String[] strArr = new String[this.r.l()];
            for (int i = 0; i < this.r.l(); i++) {
                iArr[i] = this.r.i(i);
                strArr[i] = this.r.m(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.m = false;
        if (!this.k) {
            this.k = true;
            this.i.c();
        }
        this.i.v();
        this.i.s();
        this.j.i(d.a.ON_START);
        this.i.q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.i.v();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.m = true;
        z();
        this.i.r();
        this.j.i(d.a.ON_STOP);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        if (!this.p && i != -1) {
            w(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (!this.p && i != -1) {
            w(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (!this.o && i != -1) {
            w(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.o && i != -1) {
            w(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    final View x(View view, String str, Context context, AttributeSet attributeSet) {
        return this.i.w(view, str, context, attributeSet);
    }

    public i y() {
        return this.i.u();
    }
}
