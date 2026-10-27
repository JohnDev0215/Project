package a.b.k;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.core.widget.NestedScrollView;

/* JADX INFO: loaded from: classes.dex */
public class h extends r implements DialogInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AlertController f15c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.b f16a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f17b;

        public a(Context context) {
            int iD = h.d(context, 0);
            this.f16a = new AlertController.b(new ContextThemeWrapper(context, h.d(context, iD)));
            this.f17b = iD;
        }

        public h a() {
            h hVar = new h(this.f16a.f1395a, this.f17b);
            AlertController.b bVar = this.f16a;
            AlertController alertController = hVar.f15c;
            View view = bVar.g;
            if (view != null) {
                alertController.G = view;
            } else {
                CharSequence charSequence = bVar.f1400f;
                if (charSequence != null) {
                    alertController.f1390e = charSequence;
                    TextView textView = alertController.E;
                    if (textView != null) {
                        textView.setText(charSequence);
                    }
                }
                Drawable drawable = bVar.f1398d;
                if (drawable != null) {
                    alertController.C = drawable;
                    alertController.B = 0;
                    ImageView imageView = alertController.D;
                    if (imageView != null) {
                        imageView.setVisibility(0);
                        alertController.D.setImageDrawable(drawable);
                    }
                }
                int i = bVar.f1397c;
                if (i != 0) {
                    alertController.e(i);
                }
                int i2 = bVar.f1399e;
                if (i2 != 0) {
                    if (alertController == null) {
                        throw null;
                    }
                    TypedValue typedValue = new TypedValue();
                    alertController.f1386a.getTheme().resolveAttribute(i2, typedValue, true);
                    alertController.e(typedValue.resourceId);
                }
            }
            if (bVar.l != null) {
                AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) bVar.f1396b.inflate(alertController.L, (ViewGroup) null);
                int i3 = bVar.o ? alertController.N : alertController.O;
                ListAdapter dVar = bVar.l;
                if (dVar == null) {
                    dVar = new AlertController.d(bVar.f1395a, i3, R.id.text1, null);
                }
                alertController.H = dVar;
                alertController.I = bVar.p;
                if (bVar.m != null) {
                    recycleListView.setOnItemClickListener(new g(bVar, alertController));
                }
                if (bVar.o) {
                    recycleListView.setChoiceMode(1);
                }
                alertController.g = recycleListView;
            }
            hVar.setCancelable(this.f16a.h);
            if (this.f16a.h) {
                hVar.setCanceledOnTouchOutside(true);
            }
            hVar.setOnCancelListener(this.f16a.i);
            hVar.setOnDismissListener(this.f16a.j);
            DialogInterface.OnKeyListener onKeyListener = this.f16a.k;
            if (onKeyListener != null) {
                hVar.setOnKeyListener(onKeyListener);
            }
            return hVar;
        }
    }

    public h(Context context, int i) {
        super(context, d(context, i));
        this.f15c = new AlertController(getContext(), this, getWindow());
    }

    public static int d(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.b.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x02e8 A[PHI: r1
  0x02e8: PHI (r1v7 android.view.View) = (r1v6 android.view.View), (r1v13 android.view.View) binds: [B:134:0x02e6, B:131:0x02dd] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // a.b.k.r, android.app.Dialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate(android.os.Bundle r15) {
        /*
            Method dump skipped, instruction units count: 860
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.h.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f15c.A;
        if (nestedScrollView != null && nestedScrollView.j(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f15c.A;
        if (nestedScrollView != null && nestedScrollView.j(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // a.b.k.r, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AlertController alertController = this.f15c;
        alertController.f1390e = charSequence;
        TextView textView = alertController.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
