package p;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k6 extends yv implements DialogInterface, w7 {
    public s8 l;
    public final t8 m;
    public final i6 n;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v1, types: [p.t8] */
    public k6(Context context, int i) {
        int iR = r(context, i);
        super(context, n(context, iR));
        this.m = new m81() { // from class: p.t8
            @Override // p.m81
            public final boolean d(KeyEvent keyEvent) {
                return this.c.x(keyEvent);
            }
        };
        e8 e8VarM = m();
        ((s8) e8VarM).b0 = n(context, iR);
        e8VarM.b();
        this.n = new i6(getContext(), this, getWindow());
    }

    public static int n(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public static int r(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // p.yv, android.app.Dialog
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        s8 s8Var = (s8) m();
        s8Var.u();
        ((ViewGroup) s8Var.I.findViewById(android.R.id.content)).addView(view, layoutParams);
        s8Var.u.a(s8Var.t.getCallback());
    }

    @Override // p.w7
    public final /* bridge */ /* synthetic */ void f() {
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final void dismiss() {
        super.dismiss();
        m().c();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return q30.l(this.m, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // p.w7
    public final /* bridge */ /* synthetic */ void i() {
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final View findViewById(int i) {
        s8 s8Var = (s8) m();
        s8Var.u();
        return s8Var.t.findViewById(i);
    }

    @Override // p.w7
    public final /* bridge */ /* synthetic */ void k() {
    }

    public final e8 m() {
        if (this.l == null) {
            va vaVar = e8.c;
            this.l = new s8(getContext(), getWindow(), this, this);
        }
        return this.l;
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final void invalidateOptionsMenu() {
        s8 s8Var = (s8) m();
        if (s8Var.w != null) {
            s8Var.y();
            s8Var.w.getClass();
            s8Var.z(0);
        }
    }

    @Override // p.yv, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        ListAdapter listAdapter;
        View viewFindViewById;
        p(bundle);
        i6 i6Var = this.n;
        i6Var.b.setContentView(i6Var.r);
        Window window = i6Var.c;
        View viewFindViewById2 = window.findViewById(R.id.parentPanel);
        View viewFindViewById3 = viewFindViewById2.findViewById(R.id.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(R.id.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R.id.customPanel);
        window.setFlags(131072, 131072);
        viewGroup.setVisibility(8);
        View viewFindViewById6 = viewGroup.findViewById(R.id.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup viewGroupB = i6.b(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupB2 = i6.b(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupB3 = i6.b(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R.id.scrollView);
        i6Var.i = nestedScrollView;
        nestedScrollView.setFocusable(false);
        i6Var.i.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupB2.findViewById(android.R.id.message);
        i6Var.n = textView;
        if (textView != null) {
            textView.setVisibility(8);
            i6Var.i.removeView(i6Var.n);
            if (i6Var.e != null) {
                ViewGroup viewGroup2 = (ViewGroup) i6Var.i.getParent();
                int iIndexOfChild = viewGroup2.indexOfChild(i6Var.i);
                viewGroup2.removeViewAt(iIndexOfChild);
                viewGroup2.addView(i6Var.e, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                viewGroupB2.setVisibility(8);
            }
        }
        Button button = (Button) viewGroupB3.findViewById(android.R.id.button1);
        i6Var.f = button;
        e6 e6Var = i6Var.x;
        button.setOnClickListener(e6Var);
        if (TextUtils.isEmpty(null)) {
            i6Var.f.setVisibility(8);
            i = 0;
        } else {
            i6Var.f.setText((CharSequence) null);
            i6Var.f.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroupB3.findViewById(android.R.id.button2);
        i6Var.g = button2;
        button2.setOnClickListener(e6Var);
        if (TextUtils.isEmpty(null)) {
            i6Var.g.setVisibility(8);
        } else {
            i6Var.g.setText((CharSequence) null);
            i6Var.g.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroupB3.findViewById(android.R.id.button3);
        i6Var.h = button3;
        button3.setOnClickListener(e6Var);
        if (TextUtils.isEmpty(null)) {
            i6Var.h.setVisibility(8);
        } else {
            i6Var.h.setText((CharSequence) null);
            i6Var.h.setVisibility(0);
            i |= 4;
        }
        TypedValue typedValue = new TypedValue();
        i6Var.a.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                Button button4 = i6Var.f;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i == 2) {
                Button button5 = i6Var.g;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i == 4) {
                Button button6 = i6Var.h;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i == 0) {
            viewGroupB3.setVisibility(8);
        }
        if (i6Var.o != null) {
            viewGroupB.addView(i6Var.o, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R.id.title_template).setVisibility(8);
        } else {
            i6Var.l = (ImageView) window.findViewById(android.R.id.icon);
            if ((!TextUtils.isEmpty(i6Var.d)) && i6Var.v) {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                i6Var.m = textView2;
                textView2.setText(i6Var.d);
                int i2 = i6Var.j;
                if (i2 != 0) {
                    i6Var.l.setImageResource(i2);
                } else {
                    Drawable drawable = i6Var.k;
                    if (drawable != null) {
                        i6Var.l.setImageDrawable(drawable);
                    } else {
                        i6Var.m.setPadding(i6Var.l.getPaddingLeft(), i6Var.l.getPaddingTop(), i6Var.l.getPaddingRight(), i6Var.l.getPaddingBottom());
                        i6Var.l.setVisibility(8);
                    }
                }
            } else {
                window.findViewById(R.id.title_template).setVisibility(8);
                i6Var.l.setVisibility(8);
                viewGroupB.setVisibility(8);
            }
        }
        boolean z = viewGroup.getVisibility() != 8;
        int i3 = (viewGroupB == null || viewGroupB.getVisibility() == 8) ? 0 : 1;
        boolean z2 = viewGroupB3.getVisibility() != 8;
        if (!z2 && (viewFindViewById = viewGroupB2.findViewById(R.id.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i3 != 0) {
            NestedScrollView nestedScrollView2 = i6Var.i;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = i6Var.e != null ? viewGroupB.findViewById(R.id.titleDividerNoCustom) : null;
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupB2.findViewById(R.id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = i6Var.e;
        if (alertController$RecycleListView instanceof AlertController$RecycleListView) {
            alertController$RecycleListView.getClass();
            if (!z2 || i3 == 0) {
                alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i3 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.c, alertController$RecycleListView.getPaddingRight(), z2 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.e);
            }
        }
        if (!z) {
            View view = i6Var.e;
            if (view == null) {
                view = i6Var.i;
            }
            if (view != null) {
                int i4 = i3 | (z2 ? 2 : 0);
                View viewFindViewById11 = window.findViewById(R.id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R.id.scrollIndicatorDown);
                WeakHashMap weakHashMap = zt2.a;
                pt2.d(view, i4, 3);
                if (viewFindViewById11 != null) {
                    viewGroupB2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupB2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = i6Var.e;
        if (alertController$RecycleListView2 == null || (listAdapter = i6Var.f131p) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i5 = i6Var.q;
        if (i5 > -1) {
            alertController$RecycleListView2.setItemChecked(i5, true);
            alertController$RecycleListView2.setSelection(i5);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.n.i;
        if (nestedScrollView == null || !nestedScrollView.i(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.n.i;
        if (nestedScrollView == null || !nestedScrollView.i(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    public final void p(Bundle bundle) {
        s8 s8Var = (s8) m();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(s8Var.s);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(s8Var);
        } else {
            boolean z = layoutInflaterFrom.getFactory2() instanceof s8;
        }
        super.onCreate(bundle);
        m().b();
    }

    @Override // p.yv, android.app.Dialog
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final void onStop() {
        super.onStop();
        s8 s8Var = (s8) m();
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (ow2Var != null) {
            ow2Var.B = false;
            vu2 vu2Var = ow2Var.A;
            if (vu2Var != null) {
                vu2Var.a();
            }
        }
    }

    @Override // p.yv, android.app.Dialog
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public final void setContentView(int i) {
        m().g(i);
    }

    @Override // p.yv, android.app.Dialog
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final void setContentView(View view) {
        m().i(view);
    }

    @Override // p.yv, android.app.Dialog
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        m().j(view, layoutParams);
    }

    @Override // android.app.Dialog
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public final void setTitle(int i) {
        super.setTitle(i);
        m().k(getContext().getString(i));
    }

    public final void w(CharSequence charSequence) {
        super.setTitle(charSequence);
        m().k(charSequence);
    }

    public final boolean x(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        w(charSequence);
        i6 i6Var = this.n;
        i6Var.d = charSequence;
        TextView textView = i6Var.m;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
