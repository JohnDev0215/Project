package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.p4;
import org.chromium.ui.R$dimen;
import org.chromium.ui.R$drawable;
import org.chromium.ui.R$id;

/* JADX INFO: compiled from: DropdownPopupWindowImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class te0 implements p4.c, ue0 {
    public final Context e;
    public final View f;
    public boolean g;
    public int h = -1;
    public final re0 i;
    public CharSequence j;
    public final p4 k;
    public ListAdapter l;
    public final ListView m;
    public final Drawable n;
    public final int o;

    public te0(Context context, View view, sq3 sq3Var) {
        this.e = context;
        this.f = view;
        view.setId(R$id.dropdown_popup_window);
        view.setTag(this);
        re0 re0Var = new re0(this);
        this.i = re0Var;
        view.addOnLayoutChangeListener(re0Var);
        se0 se0Var = new se0(this);
        ListView listView = new ListView(context);
        this.m = listView;
        nn3 nn3Var = new nn3(view);
        nn3Var.d(true);
        Drawable drawableA = AppCompatResources.a(context, R$drawable.menu_bg_baseline);
        this.n = drawableA;
        p4 p4Var = new p4(context, view, drawableA, listView, nn3Var, sq3Var);
        this.k = p4Var;
        p4Var.addOnDismissListener(se0Var);
        p4Var.p = this;
        float dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.dropdown_elevation);
        PopupWindow popupWindow = p4Var.j;
        popupWindow.setElevation(dimensionPixelSize);
        Rect rect = new Rect();
        drawableA.getPadding(rect);
        nn3Var.e(new Rect(0, rect.bottom, 0, rect.top));
        this.o = rect.right + rect.left;
        p4Var.u = 1;
        p4Var.y = true;
        popupWindow.setOutsideTouchable(true);
    }

    @Override // p4.c
    public final void a(boolean z, int i, int i2, Rect rect) {
        this.n.setBounds(rect);
        this.k.j.setBackgroundDrawable(AppCompatResources.a(this.e, R$drawable.menu_bg_baseline));
    }

    public final void b() {
        p4 p4Var = this.k;
        boolean zC = p4Var.c();
        p4Var.w = false;
        p4Var.x = true;
        int i = this.e.getResources().getDisplayMetrics().widthPixels;
        int iA = uh3.a(this.l, null);
        int i2 = this.o;
        int i3 = iA + i2;
        if (i < i3) {
            p4Var.r = i - i2;
        } else {
            View view = this.f;
            if (view.getWidth() < iA) {
                p4Var.r = i3;
            } else {
                p4Var.r = view.getWidth() + i2;
            }
        }
        p4Var.d();
        ListView listView = this.m;
        listView.setDividerHeight(0);
        listView.setLayoutDirection(this.g ? 1 : 0);
        if (!zC) {
            listView.setContentDescription(this.j);
            listView.sendAccessibilityEvent(32);
        }
        int i4 = this.h;
        if (i4 >= 0) {
            listView.setSelection(i4);
            this.h = -1;
        }
    }

    @Override // defpackage.ue0
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.k.addOnDismissListener(onDismissListener);
    }

    @Override // defpackage.ue0
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.m.setOnItemClickListener(onItemClickListener);
    }
}
