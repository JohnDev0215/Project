package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.d;
import androidx.lifecycle.g;
import defpackage.aw;
import defpackage.b71;
import defpackage.fe2;
import defpackage.ge2;
import defpackage.i21;
import defpackage.n3;
import defpackage.z7;
import defpackage.zr1;

/* JADX INFO: compiled from: ComponentDialog.kt */
/* JADX INFO: loaded from: classes.dex */
public class ComponentDialog extends Dialog implements b71, zr1, ge2 {
    public g e;
    public final fe2 f;
    public final OnBackPressedDispatcher g;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context) {
        this(context, 0);
        i21.e(context, "context");
    }

    public static void a(ComponentDialog componentDialog) {
        i21.e(componentDialog, "this$0");
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        i21.e(view, "view");
        b();
        super.addContentView(view, layoutParams);
    }

    public final void b() {
        Window window = getWindow();
        i21.b(window);
        View decorView = window.getDecorView();
        i21.d(decorView, "window!!.decorView");
        z7.f0(decorView, this);
        Window window2 = getWindow();
        i21.b(window2);
        View decorView2 = window2.getDecorView();
        i21.d(decorView2, "window!!.decorView");
        decorView2.setTag(R$id.view_tree_on_back_pressed_dispatcher_owner, this);
        Window window3 = getWindow();
        i21.b(window3);
        View decorView3 = window3.getDecorView();
        i21.d(decorView3, "window!!.decorView");
        n3.g1(decorView3, this);
    }

    @Override // defpackage.b71
    public final g j1() {
        g gVar = this.e;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this);
        this.e = gVar2;
        return gVar2;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.g.c();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            i21.d(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            OnBackPressedDispatcher onBackPressedDispatcher = this.g;
            onBackPressedDispatcher.getClass();
            onBackPressedDispatcher.e = onBackInvokedDispatcher;
            onBackPressedDispatcher.d();
        }
        this.f.b(bundle);
        g gVar = this.e;
        if (gVar == null) {
            gVar = new g(this);
            this.e = gVar;
        }
        gVar.e(d.a.ON_CREATE);
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        i21.d(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.f.c(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        g gVar = this.e;
        if (gVar == null) {
            gVar = new g(this);
            this.e = gVar;
        }
        gVar.e(d.a.ON_RESUME);
    }

    @Override // android.app.Dialog
    public void onStop() {
        g gVar = this.e;
        if (gVar == null) {
            gVar = new g(this);
            this.e = gVar;
        }
        gVar.e(d.a.ON_DESTROY);
        this.e = null;
        super.onStop();
    }

    @Override // defpackage.ge2
    public final androidx.savedstate.a s0() {
        return this.f.b;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        b();
        super.setContentView(i);
    }

    @Override // defpackage.zr1
    public final OnBackPressedDispatcher v() {
        return this.g;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context, int i) {
        super(context, i);
        i21.e(context, "context");
        this.f = new fe2(this);
        this.g = new OnBackPressedDispatcher(new aw(0, this));
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        i21.e(view, "view");
        b();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        i21.e(view, "view");
        b();
        super.setContentView(view, layoutParams);
    }
}
