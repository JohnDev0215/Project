package i;

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
    public final AlertController f3136d;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.f f3137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3138b;

        public a(Context context) {
            this(context, c.f(context, 0));
        }

        public c a() {
            c cVar = new c(this.f3137a.f175a, this.f3138b);
            this.f3137a.a(cVar.f3136d);
            cVar.setCancelable(this.f3137a.f192r);
            if (this.f3137a.f192r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f3137a.f193s);
            cVar.setOnDismissListener(this.f3137a.f194t);
            DialogInterface.OnKeyListener onKeyListener = this.f3137a.f195u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context b() {
            return this.f3137a.f175a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f3137a;
            fVar.f197w = listAdapter;
            fVar.f198x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f3137a.f181g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f3137a.f178d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f3137a.f195u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i3, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f3137a;
            fVar.f197w = listAdapter;
            fVar.f198x = onClickListener;
            fVar.I = i3;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f3137a.f180f = charSequence;
            return this;
        }

        public a(Context context, int i3) {
            this.f3137a = new AlertController.f(new ContextThemeWrapper(context, c.f(context, i3)));
            this.f3138b = i3;
        }
    }

    public c(Context context, int i3) {
        super(context, f(context, i3));
        this.f3136d = new AlertController(getContext(), this, getWindow());
    }

    public static int f(Context context, int i3) {
        if (((i3 >>> 24) & 255) >= 1) {
            return i3;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.f3136d.d();
    }

    @Override // i.f, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3136d.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i3, KeyEvent keyEvent) {
        if (this.f3136d.g(i3, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i3, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i3, KeyEvent keyEvent) {
        if (this.f3136d.h(i3, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i3, keyEvent);
    }

    @Override // i.f, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f3136d.q(charSequence);
    }
}
