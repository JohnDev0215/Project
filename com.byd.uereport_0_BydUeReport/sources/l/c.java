package l;

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

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AlertController f5930f;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.f f5931a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f5932b;

        public a(Context context) {
            this(context, c.f(context, 0));
        }

        public c a() {
            c cVar = new c(this.f5931a.f282a, this.f5932b);
            this.f5931a.a(cVar.f5930f);
            cVar.setCancelable(this.f5931a.f299r);
            if (this.f5931a.f299r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.f5931a.f300s);
            cVar.setOnDismissListener(this.f5931a.f301t);
            DialogInterface.OnKeyListener onKeyListener = this.f5931a.f302u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context b() {
            return this.f5931a.f282a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f5931a;
            fVar.f304w = listAdapter;
            fVar.f305x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f5931a.f288g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f5931a.f285d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f5931a.f302u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i8, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f5931a;
            fVar.f304w = listAdapter;
            fVar.f305x = onClickListener;
            fVar.I = i8;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f5931a.f287f = charSequence;
            return this;
        }

        public a(Context context, int i8) {
            this.f5931a = new AlertController.f(new ContextThemeWrapper(context, c.f(context, i8)));
            this.f5932b = i8;
        }
    }

    public c(Context context, int i8) {
        super(context, f(context, i8));
        this.f5930f = new AlertController(getContext(), this, getWindow());
    }

    public static int f(Context context, int i8) {
        if (((i8 >>> 24) & 255) >= 1) {
            return i8;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.f5930f.d();
    }

    @Override // l.f, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5930f.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i8, KeyEvent keyEvent) {
        if (this.f5930f.g(i8, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i8, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i8, KeyEvent keyEvent) {
        if (this.f5930f.h(i8, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i8, keyEvent);
    }

    @Override // l.f, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f5930f.q(charSequence);
    }
}
