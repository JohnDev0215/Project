package spi;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$dimen;
import com.android.car.ui.toolbar.b;

/* JADX INFO: compiled from: MenuItemRenderer.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class f7 implements b.d {
    public static final int[] l = {R$attr.state_ux_restricted};
    public final int a;
    public boolean b;
    public final com.android.car.ui.toolbar.b c;
    public final ViewGroup d;
    public View e;
    public View f;
    public ImageView g;
    public Switch h;
    public TextView i;
    public TextView j;
    public boolean k;

    public f7(com.android.car.ui.toolbar.b bVar, ViewGroup viewGroup) {
        this.c = bVar;
        this.d = viewGroup;
        bVar.d(this);
        this.a = viewGroup.getContext().getResources().getDimensionPixelSize(R$dimen.car_ui_toolbar_menu_item_icon_size);
    }

    @Override // com.android.car.ui.toolbar.b.d
    public final void a(com.android.car.ui.toolbar.b bVar) {
        c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(View view) {
        view.setEnabled(this.c.n);
        int[] iArr = this.c.a() ? l : null;
        if (view instanceof ImageView) {
            ((ImageView) view).setImageState(iArr, true);
        } else if (view instanceof n3) {
            ((n3) view).d(iArr, null);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                b(viewGroup.getChildAt(i));
            }
        }
    }

    public final void c() {
        View view;
        View view2 = this.e;
        if (view2 == null) {
            return;
        }
        view2.setId(this.c.f);
        com.android.car.ui.toolbar.b bVar = this.c;
        boolean z = bVar.j != null;
        boolean z2 = !TextUtils.isEmpty(bVar.i);
        com.android.car.ui.toolbar.b bVar2 = this.c;
        boolean z3 = bVar2.d;
        boolean z4 = bVar2.a;
        if (!bVar2.p || ((bVar2.c && this.b) || !(z4 || z || z2))) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.e.setContentDescription(this.c.i);
        if (z4) {
            this.h.setChecked(this.c.o);
            view = this.h;
        } else if (z2 && z && z3) {
            Drawable drawable = this.c.j;
            int i = this.a;
            drawable.setBounds(0, 0, i, i);
            this.j.setCompoundDrawables(this.c.j, null, null, null);
            this.j.setText(this.c.i);
            view = this.j;
        } else if (z) {
            this.g.setImageDrawable(this.c.j);
            view = this.f;
        } else {
            this.i.setText(this.c.i);
            view = this.i;
        }
        View view3 = this.f;
        view3.setVisibility(view == view3 ? 0 : 8);
        TextView textView = this.i;
        textView.setVisibility(view == textView ? 0 : 8);
        TextView textView2 = this.j;
        textView2.setVisibility(view == textView2 ? 0 : 8);
        Switch r4 = this.h;
        r4.setVisibility(view == r4 ? 0 : 8);
        if (!this.k) {
            view = this.e;
        }
        com.android.car.ui.toolbar.b bVar3 = this.c;
        if (!bVar3.e && z) {
            bVar3.j.setTintList(null);
        }
        b(this.e);
        this.e.setActivated(this.c.q);
        com.android.car.ui.toolbar.b bVar4 = this.c;
        if (bVar4.k != null || bVar4.a || bVar4.b) {
            view.setOnClickListener(new k8(this, 3));
            return;
        }
        View view4 = this.e;
        if (view == view4) {
            view4.setOnClickListener(null);
            this.e.setClickable(false);
        }
    }
}
