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
import androidx.lifecycle.Lifecycle;
import b.d.h;
import b.f.e.a;
import b.j.a.c;
import b.j.a.e;
import b.j.a.f;
import b.l.g;
import b.l.p;
import b.l.q;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity {
    public boolean I3;
    public boolean J3;
    public boolean L3;
    public boolean M3;
    public int N3;
    public h<String> O3;
    public final c G3 = c.b(new a());
    public final g H3 = new g(this);
    public boolean K3 = true;

    public class a extends e<FragmentActivity> implements q, b.a.c {
        public a() {
            super(FragmentActivity.this);
        }

        @Override // b.l.f
        public Lifecycle a() {
            return FragmentActivity.this.H3;
        }

        @Override // b.a.c
        public OnBackPressedDispatcher b() {
            return FragmentActivity.this.b();
        }

        @Override // b.j.a.e, b.j.a.b
        public View e(int i2) {
            return FragmentActivity.this.findViewById(i2);
        }

        @Override // b.j.a.e, b.j.a.b
        public boolean f() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // b.l.q
        public p h() {
            return FragmentActivity.this.h();
        }

        @Override // b.j.a.e
        public void k(Fragment fragment) {
            FragmentActivity.this.r(fragment);
        }

        @Override // b.j.a.e
        public void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // b.j.a.e
        public LayoutInflater n() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // b.j.a.e
        public int o() {
            Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // b.j.a.e
        public boolean p() {
            return FragmentActivity.this.getWindow() != null;
        }

        @Override // b.j.a.e
        public boolean q(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // b.j.a.e
        public void r() {
            FragmentActivity.this.u();
        }

        @Override // b.j.a.e
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public FragmentActivity m() {
            return FragmentActivity.this;
        }
    }

    public static void m(int i2) {
        if ((i2 & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public static boolean q(f fVar, Lifecycle.State state) {
        boolean zQ = false;
        for (Fragment fragment : fVar.f()) {
            if (fragment != null) {
                if (fragment.a().b().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.s4.p(state);
                    zQ = true;
                }
                if (fragment.u() != null) {
                    zQ |= q(fragment.n(), state);
                }
            }
        }
        return zQ;
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
        printWriter.print(this.I3);
        printWriter.print(" mResumed=");
        printWriter.print(this.J3);
        printWriter.print(" mStopped=");
        printWriter.print(this.K3);
        if (getApplication() != null) {
            b.m.a.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.G3.u().b(str, fileDescriptor, printWriter, strArr);
    }

    public final View n(View view, String str, Context context, AttributeSet attributeSet) {
        return this.G3.w(view, str, context, attributeSet);
    }

    public f o() {
        return this.G3.u();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        this.G3.v();
        int i4 = i2 >> 16;
        if (i4 == 0) {
            a.InterfaceC0019a interfaceC0019aH = b.f.e.a.h();
            if (interfaceC0019aH == null || !interfaceC0019aH.a(this, i2, i3, intent)) {
                super.onActivityResult(i2, i3, intent);
                return;
            }
            return;
        }
        int i5 = i4 - 1;
        String strG = this.O3.g(i5);
        this.O3.k(i5);
        if (strG == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentT = this.G3.t(strG);
        if (fragmentT != null) {
            fragmentT.T(i2 & 65535, i3, intent);
            return;
        }
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strG);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.G3.v();
        this.G3.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.G3.a(null);
        if (bundle != null) {
            this.G3.x(bundle.getParcelable("android:support:fragments"));
            if (bundle.containsKey("android:support:next_request_index")) {
                this.N3 = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.O3 = new h<>(intArray.length);
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        this.O3.j(intArray[i2], stringArray[i2]);
                    }
                }
            }
        }
        if (this.O3 == null) {
            this.O3 = new h<>();
            this.N3 = 0;
        }
        super.onCreate(bundle);
        this.H3.i(Lifecycle.Event.ON_CREATE);
        this.G3.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return i2 == 0 ? super.onCreatePanelMenu(i2, menu) | this.G3.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i2, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewN = n(view, str, context, attributeSet);
        return viewN == null ? super.onCreateView(view, str, context, attributeSet) : viewN;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.G3.h();
        this.H3.i(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.G3.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            return this.G3.k(menuItem);
        }
        if (i2 != 6) {
            return false;
        }
        return this.G3.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.G3.j(z);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.G3.v();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        if (i2 == 0) {
            this.G3.l(menu);
        }
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.J3 = false;
        this.G3.m();
        this.H3.i(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.G3.n(z);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        t();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        return i2 == 0 ? s(view, menu) | this.G3.o(menu) : super.onPreparePanel(i2, view, menu);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.G3.v();
        int i3 = (i2 >> 16) & 65535;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String strG = this.O3.g(i4);
            this.O3.k(i4);
            if (strG == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentT = this.G3.t(strG);
            if (fragmentT != null) {
                fragmentT.s0(i2 & 65535, strArr, iArr);
                return;
            }
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strG);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.J3 = true;
        this.G3.v();
        this.G3.s();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        p();
        this.H3.i(Lifecycle.Event.ON_STOP);
        Parcelable parcelableY = this.G3.y();
        if (parcelableY != null) {
            bundle.putParcelable("android:support:fragments", parcelableY);
        }
        if (this.O3.l() > 0) {
            bundle.putInt("android:support:next_request_index", this.N3);
            int[] iArr = new int[this.O3.l()];
            String[] strArr = new String[this.O3.l()];
            for (int i2 = 0; i2 < this.O3.l(); i2++) {
                iArr[i2] = this.O3.i(i2);
                strArr[i2] = this.O3.m(i2);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.K3 = false;
        if (!this.I3) {
            this.I3 = true;
            this.G3.c();
        }
        this.G3.v();
        this.G3.s();
        this.H3.i(Lifecycle.Event.ON_START);
        this.G3.q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.G3.v();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.K3 = true;
        p();
        this.G3.r();
        this.H3.i(Lifecycle.Event.ON_STOP);
    }

    public final void p() {
        while (q(o(), Lifecycle.State.CREATED)) {
        }
    }

    public void r(Fragment fragment) {
    }

    @Deprecated
    public boolean s(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i2) {
        if (!this.M3 && i2 != -1) {
            m(i2);
        }
        super.startActivityForResult(intent, i2);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) throws IntentSender.SendIntentException {
        if (!this.L3 && i2 != -1) {
            m(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    public void t() {
        this.H3.i(Lifecycle.Event.ON_RESUME);
        this.G3.p();
    }

    @Deprecated
    public void u() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewN = n(null, str, context, attributeSet);
        return viewN == null ? super.onCreateView(str, context, attributeSet) : viewN;
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i2, Bundle bundle) {
        if (!this.M3 && i2 != -1) {
            m(i2);
        }
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.L3 && i2 != -1) {
            m(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}
