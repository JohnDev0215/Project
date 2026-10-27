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
public class b extends e implements DialogInterface {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AlertController f167d;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.f f168a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f169b;

        public a(Context context) {
            this(context, b.f(context, 0));
        }

        public b a() {
            b bVar = new b(this.f168a.f106a, this.f169b);
            this.f168a.a(bVar.f167d);
            bVar.setCancelable(this.f168a.r);
            if (this.f168a.r) {
                bVar.setCanceledOnTouchOutside(true);
            }
            bVar.setOnCancelListener(this.f168a.s);
            bVar.setOnDismissListener(this.f168a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f168a.u;
            if (onKeyListener != null) {
                bVar.setOnKeyListener(onKeyListener);
            }
            return bVar;
        }

        public Context b() {
            return this.f168a.f106a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f168a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f168a.f112g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f168a.f109d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f168a.u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f168a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i2;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f168a.f111f = charSequence;
            return this;
        }

        public a(Context context, int i2) {
            this.f168a = new AlertController.f(new ContextThemeWrapper(context, b.f(context, i2)));
            this.f169b = i2;
        }
    }

    protected b(Context context, int i2) {
        super(context, f(context, i2));
        this.f167d = new AlertController(getContext(), this, getWindow());
    }

    static int f(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(b.a.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.f167d.d();
    }

    @Override // androidx.appcompat.app.e, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f167d.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.f167d.g(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.f167d.h(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // androidx.appcompat.app.e, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f167d.q(charSequence);
    }
}
