package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class O3 extends J1 implements R4 {
    public int A;
    public C1688w1 B;
    public Q4 v;
    public boolean w;
    public boolean x;
    public boolean z;
    public final Handler t = new L3(this);
    public final Q3 u = new Q3(new M3(this));
    public boolean y = true;

    public static void d(int i) {
        if ((i & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public static boolean f(S3 s3, G4 g4) {
        List<K3> listEmptyList;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = (LayoutInflaterFactory2C0974j4) s3;
        if (layoutInflaterFactory2C0974j4.v.isEmpty()) {
            listEmptyList = Collections.emptyList();
        } else {
            synchronized (layoutInflaterFactory2C0974j4.v) {
                listEmptyList = (List) layoutInflaterFactory2C0974j4.v.clone();
            }
        }
        boolean zF = false;
        for (K3 k3 : listEmptyList) {
            if (k3 != null) {
                if (k3.j0.b.compareTo(G4.STARTED) >= 0) {
                    k3.j0.c(g4);
                    zF = true;
                }
                LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j42 = k3.K;
                if (layoutInflaterFactory2C0974j42 != null) {
                    zF |= f(layoutInflaterFactory2C0974j42, g4);
                }
            }
        }
        return zF;
    }

    @Override // defpackage.R4
    public Q4 a() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.v == null) {
            N3 n3 = (N3) getLastNonConfigurationInstance();
            if (n3 != null) {
                this.v = n3.a;
            }
            if (this.v == null) {
                this.v = new Q4();
            }
        }
        return this.v;
    }

    @Override // defpackage.I4
    public H4 b() {
        return this.s;
    }

    public final int c(K3 k3) {
        if (this.B.i() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (true) {
            C1688w1 c1688w1 = this.B;
            int i = this.A;
            if (c1688w1.s) {
                c1688w1.c();
            }
            if (AbstractC1139m1.a(c1688w1.t, c1688w1.v, i) < 0) {
                int i2 = this.A;
                this.B.g(i2, k3.w);
                this.A = (this.A + 1) % 65534;
                return i2;
            }
            this.A = (this.A + 1) % 65534;
        }
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
        printWriter.print(this.w);
        printWriter.print(" mResumed=");
        printWriter.print(this.x);
        printWriter.print(" mStopped=");
        printWriter.print(this.y);
        if (getApplication() != null) {
            S4.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.u.a.d.L(str, fileDescriptor, printWriter, strArr);
    }

    public S3 e() {
        return this.u.a.d;
    }

    public void g() {
    }

    public void h() {
        this.u.a.d.I();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.u.b();
        int i3 = i >> 16;
        if (i3 == 0) {
            I1.e();
            super.onActivityResult(i, i2, intent);
            return;
        }
        int i4 = i3 - 1;
        String str = (String) this.B.d(i4);
        this.B.h(i4);
        if (str == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        K3 k3T = this.u.a.d.T(str);
        if (k3T != null) {
            k3T.A(i & 65535, i2, intent);
            return;
        }
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.u.a.d;
        boolean zV = layoutInflaterFactory2C0974j4.V();
        if (!zV || Build.VERSION.SDK_INT > 25) {
            if (zV || !layoutInflaterFactory2C0974j4.e0()) {
                super.onBackPressed();
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.u.b();
        this.u.a.d.j(configuration);
    }

    @Override // defpackage.J1, android.app.Activity
    public void onCreate(Bundle bundle) {
        Q4 q4;
        R3 r3 = this.u.a;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = r3.d;
        if (layoutInflaterFactory2C0974j4.D != null) {
            throw new IllegalStateException("Already attached");
        }
        layoutInflaterFactory2C0974j4.D = r3;
        layoutInflaterFactory2C0974j4.E = r3;
        layoutInflaterFactory2C0974j4.F = null;
        super.onCreate(bundle);
        N3 n3 = (N3) getLastNonConfigurationInstance();
        if (n3 != null && (q4 = n3.a) != null && this.v == null) {
            this.v = q4;
        }
        if (bundle != null) {
            this.u.a.d.j0(bundle.getParcelable("android:support:fragments"), n3 != null ? n3.b : null);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.A = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.B = new C1688w1(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.B.g(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.B == null) {
            this.B = new C1688w1(10);
            this.A = 0;
        }
        this.u.a.d.l();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean zOnCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        Q3 q3 = this.u;
        return zOnCreatePanelMenu | q3.a.d.m(menu, getMenuInflater());
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = this.u.a.d.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.v != null && !isChangingConfigurations()) {
            this.v.a();
        }
        this.u.a.d.n();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.u.a.d.o();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.u.a.d.E(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.u.a.d.k(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.u.a.d.p(z);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.u.a.d.F(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.x = false;
        if (this.t.hasMessages(2)) {
            this.t.removeMessages(2);
            h();
        }
        this.u.a.d.K(3);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.u.a.d.G(z);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.t.removeMessages(2);
        h();
        this.u.a();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return (i != 0 || menu == null) ? super.onPreparePanel(i, view, menu) : super.onPreparePanel(0, view, menu) | this.u.a.d.H(menu);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.u.b();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String str = (String) this.B.d(i3);
            this.B.h(i3);
            if (str == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            } else if (this.u.a.d.T(str) == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + str);
            }
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.t.sendEmptyMessage(2);
        this.x = true;
        this.u.a();
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.u.a.d;
        LayoutInflaterFactory2C0974j4.r0(layoutInflaterFactory2C0974j4.S);
        C1030k4 c1030k4 = layoutInflaterFactory2C0974j4.S;
        if (c1030k4 == null && this.v == null) {
            return null;
        }
        N3 n3 = new N3();
        n3.a = this.v;
        n3.b = c1030k4;
        return n3;
    }

    @Override // defpackage.J1, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        while (f(e(), G4.CREATED)) {
        }
        Parcelable parcelableL0 = this.u.a.d.l0();
        if (parcelableL0 != null) {
            bundle.putParcelable("android:support:fragments", parcelableL0);
        }
        if (this.B.i() > 0) {
            bundle.putInt("android:support:next_request_index", this.A);
            int[] iArr = new int[this.B.i()];
            String[] strArr = new String[this.B.i()];
            for (int i = 0; i < this.B.i(); i++) {
                iArr[i] = this.B.f(i);
                strArr[i] = (String) this.B.j(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.y = false;
        if (!this.w) {
            this.w = true;
            this.u.a.d.i();
        }
        this.u.b();
        this.u.a();
        this.u.a.d.J();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.u.b();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.y = true;
        while (f(e(), G4.CREATED)) {
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.u.a.d;
        layoutInflaterFactory2C0974j4.f24J = true;
        layoutInflaterFactory2C0974j4.K(2);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        if (!this.z && i != -1) {
            d(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (i != -1) {
            d(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            d(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = this.u.a.d.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (!this.z && i != -1) {
            d(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }
}
