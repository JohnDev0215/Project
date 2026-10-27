package b.b.k;

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
    public final AlertController s;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.f f781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f782b;

        public a(Context context) {
            this(context, b.f(context, 0));
        }

        public b a() {
            b bVar = new b(this.f781a.f101a, this.f782b);
            this.f781a.a(bVar.s);
            bVar.setCancelable(this.f781a.r);
            if (this.f781a.r) {
                bVar.setCanceledOnTouchOutside(true);
            }
            bVar.setOnCancelListener(this.f781a.s);
            bVar.setOnDismissListener(this.f781a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f781a.u;
            if (onKeyListener != null) {
                bVar.setOnKeyListener(onKeyListener);
            }
            return bVar;
        }

        public Context b() {
            return this.f781a.f101a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f781a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f781a.f107g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f781a.f104d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f781a.u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f781a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i2;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f781a.f106f = charSequence;
            return this;
        }

        public a(Context context, int i2) {
            this.f781a = new AlertController.f(new ContextThemeWrapper(context, b.f(context, i2)));
            this.f782b = i2;
        }
    }

    public b(Context context, int i2) {
        super(context, f(context, i2));
        this.s = new AlertController(getContext(), this, getWindow());
    }

    public static int f(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(b.b.a.p, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.s.d();
    }

    @Override // b.b.k.e, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.s.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.s.g(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.s.h(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // b.b.k.e, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.s.q(charSequence);
    }
}
