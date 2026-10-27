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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertController;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a extends c implements DialogInterface {
    final AlertController e;

    /* JADX INFO: renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static class C0003a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.f f138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f139b;

        public C0003a(@NonNull Context context) {
            this(context, a.f(context, 0));
        }

        @NonNull
        public a a() {
            a aVar = new a(this.f138a.f107a, this.f139b);
            this.f138a.a(aVar.e);
            aVar.setCancelable(this.f138a.r);
            if (this.f138a.r) {
                aVar.setCanceledOnTouchOutside(true);
            }
            aVar.setOnCancelListener(this.f138a.s);
            aVar.setOnDismissListener(this.f138a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f138a.u;
            if (onKeyListener != null) {
                aVar.setOnKeyListener(onKeyListener);
            }
            return aVar;
        }

        @NonNull
        public Context b() {
            return this.f138a.f107a;
        }

        public C0003a c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f138a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public C0003a d(@Nullable View view) {
            this.f138a.g = view;
            return this;
        }

        public C0003a e(@Nullable Drawable drawable) {
            this.f138a.d = drawable;
            return this;
        }

        public C0003a f(DialogInterface.OnKeyListener onKeyListener) {
            this.f138a.u = onKeyListener;
            return this;
        }

        public C0003a g(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f138a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public C0003a h(@Nullable CharSequence charSequence) {
            this.f138a.f = charSequence;
            return this;
        }

        public C0003a(@NonNull Context context, @StyleRes int i) {
            this.f138a = new AlertController.f(new ContextThemeWrapper(context, a.f(context, i)));
            this.f139b = i;
        }
    }

    protected a(@NonNull Context context, @StyleRes int i) {
        super(context, f(context, i));
        this.e = new AlertController(getContext(), this, getWindow());
    }

    static int f(@NonNull Context context, @StyleRes int i) {
        if (((i >>> 24) & MediaControl.ACTION_NONE) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.e.d();
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e.e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.e.g(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.e.h(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.e.q(charSequence);
    }
}
