package p;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle$Event;
import com.spotify.music.R;
import com.spotify.webapi.service.models.Search;

/* JADX INFO: loaded from: classes.dex */
public class yv extends Dialog implements s91, op1, x62 {
    public androidx.lifecycle.a c;
    public final w62 e;
    public final androidx.activity.b k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yv(Context context, int i) {
        super(context, i);
        v03.j(context, "context");
        this.e = zb0.s(this);
        this.k = new androidx.activity.b(new nv(2, this));
    }

    public static void b(yv yvVar) {
        v03.j(yvVar, "this$0");
        super.onBackPressed();
    }

    @Override // p.x62
    public final v62 a() {
        return this.e.b;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v03.j(view, Search.Type.VIEW);
        d();
        super.addContentView(view, layoutParams);
    }

    public final androidx.lifecycle.a c() {
        androidx.lifecycle.a aVar = this.c;
        if (aVar != null) {
            return aVar;
        }
        androidx.lifecycle.a aVar2 = new androidx.lifecycle.a(this);
        this.c = aVar2;
        return aVar2;
    }

    public final void d() {
        Window window = getWindow();
        v03.h(window);
        View decorView = window.getDecorView();
        v03.i(decorView, "window!!.decorView");
        l13.l(decorView, this);
        Window window2 = getWindow();
        v03.h(window2);
        View decorView2 = window2.getDecorView();
        v03.i(decorView2, "window!!.decorView");
        decorView2.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        Window window3 = getWindow();
        v03.h(window3);
        View decorView3 = window3.getDecorView();
        v03.i(decorView3, "window!!.decorView");
        l13.m(decorView3, this);
    }

    @Override // p.s91
    public final l91 l() {
        return c();
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.k.b();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            v03.i(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            androidx.activity.b bVar = this.k;
            bVar.getClass();
            bVar.e = onBackInvokedDispatcher;
            bVar.c();
        }
        this.e.b(bundle);
        c().f(Lifecycle$Event.ON_CREATE);
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        v03.i(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.e.c(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        c().f(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    public void onStop() {
        c().f(Lifecycle$Event.ON_DESTROY);
        this.c = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        d();
        super.setContentView(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        v03.j(view, Search.Type.VIEW);
        d();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v03.j(view, Search.Type.VIEW);
        d();
        super.setContentView(view, layoutParams);
    }
}
