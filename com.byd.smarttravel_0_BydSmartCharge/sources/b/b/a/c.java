package b.b.a;

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
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertController;

/* JADX INFO: compiled from: AlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends f implements DialogInterface {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AlertController f572d;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.f f573a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f574b;

        public a(Context context) {
            this(context, c.f(context, 0));
        }

        public c a() {
            c cVar = new c(this.f573a.f89a, this.f574b);
            this.f573a.a(cVar.f572d);
            cVar.setCancelable(this.f573a.r);
            if (this.f573a.r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f573a.s);
            cVar.setOnDismissListener(this.f573a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f573a.u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context b() {
            return this.f573a.f89a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f573a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f573a.g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f573a.f92d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f573a.u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f573a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f573a.f = charSequence;
            return this;
        }

        public a(Context context, int i) {
            this.f573a = new AlertController.f(new ContextThemeWrapper(context, c.f(context, i)));
            this.f574b = i;
        }
    }

    public c(Context context, int i) {
        super(context, f(context, i));
        this.f572d = new AlertController(getContext(), this, getWindow());
    }

    public static int f(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.f572d.d();
    }

    @Override // b.b.a.f, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f572d.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f572d.g(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f572d.h(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // b.b.a.f, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f572d.q(charSequence);
    }
}
