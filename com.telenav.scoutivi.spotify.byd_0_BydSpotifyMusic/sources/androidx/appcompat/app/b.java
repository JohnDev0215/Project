package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertController;

/* JADX INFO: compiled from: AlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends f {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final AlertController f502h;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.b f503a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f504b;

        public a(Context context) {
            this(context, b.g(context, 0));
        }

        public b a() {
            b bVar = new b(this.f503a.f391a, this.f504b);
            this.f503a.a(bVar.f502h);
            bVar.setCancelable(this.f503a.f408r);
            if (this.f503a.f408r) {
                bVar.setCanceledOnTouchOutside(true);
            }
            bVar.setOnCancelListener(this.f503a.f409s);
            bVar.setOnDismissListener(this.f503a.f410t);
            DialogInterface.OnKeyListener onKeyListener = this.f503a.f411u;
            if (onKeyListener != null) {
                bVar.setOnKeyListener(onKeyListener);
            }
            return bVar;
        }

        public Context b() {
            return this.f503a.f391a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f503a;
            bVar.f413w = listAdapter;
            bVar.f414x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f503a.f397g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f503a.f394d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f503a.f411u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i7, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f503a;
            bVar.f413w = listAdapter;
            bVar.f414x = onClickListener;
            bVar.I = i7;
            bVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f503a.f396f = charSequence;
            return this;
        }

        public a(Context context, int i7) {
            this.f503a = new AlertController.b(new ContextThemeWrapper(context, b.g(context, i7)));
            this.f504b = i7;
        }
    }

    protected b(Context context, int i7) {
        super(context, g(context, i7));
        this.f502h = new AlertController(getContext(), this, getWindow());
    }

    static int g(Context context, int i7) {
        if (((i7 >>> 24) & 255) >= 1) {
            return i7;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(e.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView f() {
        return this.f502h.d();
    }

    @Override // androidx.appcompat.app.f, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f502h.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (this.f502h.f(i7, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i7, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i7, KeyEvent keyEvent) {
        if (this.f502h.g(i7, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i7, keyEvent);
    }

    @Override // androidx.appcompat.app.f, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f502h.p(charSequence);
    }
}
