package a.b.k;

import a.b.o.a;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class r extends Dialog implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f51a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.h.k.e f52b;

    public class a implements a.h.k.e {
        public a() {
        }

        @Override // a.h.k.e
        public boolean h(KeyEvent keyEvent) {
            return r.this.c(keyEvent);
        }
    }

    public r(Context context, int i) {
        int i2;
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(a.b.a.dialogTheme, typedValue, true);
            i2 = typedValue.resourceId;
        } else {
            i2 = i;
        }
        super(context, i2);
        this.f52b = new a();
        k kVarA = a();
        if (i == 0) {
            TypedValue typedValue2 = new TypedValue();
            context.getTheme().resolveAttribute(a.b.a.dialogTheme, typedValue2, true);
            i = typedValue2.resourceId;
        }
        ((l) kVarA).N = i;
        kVarA.i(null);
    }

    public k a() {
        if (this.f51a == null) {
            this.f51a = k.e(this, this);
        }
        return this.f51a;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().c(view, layoutParams);
    }

    @Override // a.b.k.j
    public void b(a.b.o.a aVar) {
    }

    public boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().j();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        getWindow().getDecorView();
        a.h.k.e eVar = this.f52b;
        if (eVar == null) {
            return false;
        }
        return eVar.h(keyEvent);
    }

    @Override // a.b.k.j
    public void f(a.b.o.a aVar) {
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        l lVar = (l) a();
        lVar.A();
        return (T) lVar.f22e.findViewById(i);
    }

    @Override // a.b.k.j
    public a.b.o.a g(a.InterfaceC0001a interfaceC0001a) {
        return null;
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().h();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().g();
        super.onCreate(bundle);
        a().i(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        l lVar = (l) a();
        lVar.K = false;
        lVar.G();
        e eVar = lVar.h;
        if (eVar != null) {
            eVar.h(false);
        }
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().m(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().n(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().o(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().p(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().p(charSequence);
    }
}
