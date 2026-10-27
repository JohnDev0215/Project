package a.l.d;

import a.b.k.l;
import a.n.g;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class e extends ComponentActivity {
    public final m g;
    public final a.n.l h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public a.e.i<String> m;

    public class a extends o<e> implements a.n.e0, a.a.c {
        public a() {
            super(e.this);
        }

        @Override // a.n.k
        public a.n.g a() {
            return e.this.h;
        }

        @Override // a.l.d.k
        public View b(int i) {
            return e.this.findViewById(i);
        }

        @Override // a.a.c
        public OnBackPressedDispatcher c() {
            return e.this.f1368f;
        }

        @Override // a.n.e0
        public a.n.d0 e() {
            return e.this.e();
        }

        @Override // a.l.d.k
        public boolean f() {
            Window window = e.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    public e() {
        a aVar = new a();
        l.i.i(aVar, "callbacks == null");
        this.g = new m(aVar);
        this.h = new a.n.l(this);
        this.k = true;
    }

    public static void l(int i) {
        if ((i & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public static boolean n(r rVar, g.b bVar) {
        boolean zN = false;
        for (Fragment fragment : rVar.f1027c.g()) {
            if (fragment != null) {
                o<?> oVar = fragment.s;
                if ((oVar == null ? null : e.this) != null) {
                    zN |= n(fragment.k(), bVar);
                }
                if (fragment.P.f1094b.compareTo(g.b.STARTED) >= 0) {
                    fragment.P.f(bVar);
                    zN = true;
                }
            }
        }
        return zN;
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
        printWriter.print(this.i);
        printWriter.print(" mResumed=");
        printWriter.print(this.j);
        printWriter.print(" mStopped=");
        printWriter.print(this.k);
        if (getApplication() != null) {
            a.o.a.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.g.f1014a.f1020d.x(str, fileDescriptor, printWriter, strArr);
    }

    public final View m(View view, String str, Context context, AttributeSet attributeSet) {
        return this.g.f1014a.f1020d.f1030f.onCreateView(view, str, context, attributeSet);
    }

    public void o() {
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.g.a();
        int i3 = i >> 16;
        if (i3 == 0) {
            a.h.c.a.e();
            super.onActivityResult(i, i2, intent);
            return;
        }
        int i4 = i3 - 1;
        String strD = this.m.d(i4);
        this.m.h(i4);
        if (strD == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
        } else if (this.g.f1014a.f1020d.F(strD) == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strD);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.g.a();
        this.g.f1014a.f1020d.j(configuration);
    }

    @Override // androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        o<?> oVar = this.g.f1014a;
        oVar.f1020d.c(oVar, oVar, null);
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            o<?> oVar2 = this.g.f1014a;
            if (!(oVar2 instanceof a.n.e0)) {
                throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
            }
            oVar2.f1020d.W(parcelable);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.l = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.m = new a.e.i<>(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.m.g(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.m == null) {
            this.m = new a.e.i<>(10);
            this.l = 0;
        }
        super.onCreate(bundle);
        this.h.d(g.a.ON_CREATE);
        this.g.f1014a.f1020d.l();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean zOnCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        m mVar = this.g;
        return mVar.f1014a.f1020d.m(menu, getMenuInflater()) | zOnCreatePanelMenu;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewM = m(view, str, context, attributeSet);
        return viewM == null ? super.onCreateView(view, str, context, attributeSet) : viewM;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = this.g.f1014a.f1020d.f1030f.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.g.f1014a.f1020d.n();
        this.h.d(g.a.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.g.f1014a.f1020d.o();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.g.f1014a.f1020d.q(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.g.f1014a.f1020d.k(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.g.f1014a.f1020d.p(z);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.g.a();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.g.f1014a.f1020d.r(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.j = false;
        this.g.f1014a.f1020d.v(3);
        this.h.d(g.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.g.f1014a.f1020d.t(z);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.h.d(g.a.ON_RESUME);
        r rVar = this.g.f1014a.f1020d;
        rVar.u = false;
        rVar.v = false;
        rVar.v(4);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return super.onPreparePanel(i, view, menu);
        }
        return this.g.f1014a.f1020d.u(menu) | super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.g.a();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String strD = this.m.d(i3);
            this.m.h(i3);
            if (strD == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            } else if (this.g.f1014a.f1020d.F(strD) == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strD);
            }
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.j = true;
        this.g.a();
        this.g.f1014a.f1020d.A(true);
    }

    @Override // androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        while (n(this.g.f1014a.f1020d, g.b.CREATED)) {
        }
        this.h.d(g.a.ON_STOP);
        Parcelable parcelableX = this.g.f1014a.f1020d.X();
        if (parcelableX != null) {
            bundle.putParcelable("android:support:fragments", parcelableX);
        }
        if (this.m.i() > 0) {
            bundle.putInt("android:support:next_request_index", this.l);
            int[] iArr = new int[this.m.i()];
            String[] strArr = new String[this.m.i()];
            for (int i = 0; i < this.m.i(); i++) {
                iArr[i] = this.m.f(i);
                strArr[i] = this.m.j(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.k = false;
        if (!this.i) {
            this.i = true;
            r rVar = this.g.f1014a.f1020d;
            rVar.u = false;
            rVar.v = false;
            rVar.v(2);
        }
        this.g.a();
        this.g.f1014a.f1020d.A(true);
        this.h.d(g.a.ON_START);
        r rVar2 = this.g.f1014a.f1020d;
        rVar2.u = false;
        rVar2.v = false;
        rVar2.v(3);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.g.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.k = true;
        while (n(this.g.f1014a.f1020d, g.b.CREATED)) {
        }
        r rVar = this.g.f1014a.f1020d;
        rVar.v = true;
        rVar.v(2);
        this.h.d(g.a.ON_STOP);
    }

    @Deprecated
    public void p() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        if (i != -1) {
            l(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            l(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (i != -1) {
            l(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            l(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }
}
