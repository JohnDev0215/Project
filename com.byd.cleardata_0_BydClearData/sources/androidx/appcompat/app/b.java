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

/* JADX INFO: loaded from: classes.dex */
public class b extends g implements DialogInterface {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AlertController f711d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.f f712a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f713b;

        public a(Context context) {
            this(context, b.f(context, 0));
        }

        public a(Context context, int i) {
            this.f712a = new AlertController.f(new ContextThemeWrapper(context, b.f(context, i)));
            this.f713b = i;
        }

        public b a() {
            b bVar = new b(this.f712a.f695a, this.f713b);
            this.f712a.a(bVar.f711d);
            bVar.setCancelable(this.f712a.r);
            if (this.f712a.r) {
                bVar.setCanceledOnTouchOutside(true);
            }
            bVar.setOnCancelListener(this.f712a.s);
            bVar.setOnDismissListener(this.f712a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f712a.u;
            if (onKeyListener != null) {
                bVar.setOnKeyListener(onKeyListener);
            }
            return bVar;
        }

        public Context b() {
            return this.f712a.f695a;
        }

        public a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f712a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a d(View view) {
            this.f712a.g = view;
            return this;
        }

        public a e(Drawable drawable) {
            this.f712a.f698d = drawable;
            return this;
        }

        public a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f712a.u = onKeyListener;
            return this;
        }

        public a g(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f712a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public a h(CharSequence charSequence) {
            this.f712a.f = charSequence;
            return this;
        }
    }

    protected b(Context context, int i) {
        super(context, f(context, i));
        this.f711d = new AlertController(getContext(), this, getWindow());
    }

    static int f(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.a.a.o, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.f711d.d();
    }

    @Override // androidx.appcompat.app.g, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f711d.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f711d.g(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f711d.h(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.g, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f711d.q(charSequence);
    }
}
