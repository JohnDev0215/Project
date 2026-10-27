package org.chromium.components.browser_ui.widget.listmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import defpackage.f52;
import defpackage.k81;
import defpackage.l81;
import defpackage.p4;
import defpackage.pm1;
import defpackage.vt;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$string;
import org.chromium.components.browser_ui.widget.R$style;
import org.chromium.components.browser_ui.widget.R$styleable;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class ListMenuButton extends ChromeImageButton implements p4.c {
    public static final /* synthetic */ int q = 0;
    public final boolean h;
    public final boolean i;
    public int j;
    public p4 k;
    public l81 l;
    public final b<a> m;
    public boolean n;
    public final boolean o;
    public boolean p;

    public ListMenuButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = new b<>();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListMenuButton);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ListMenuButton_menuMaxWidth, getResources().getDimensionPixelSize(R$dimen.list_menu_width));
        this.i = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ListMenuButton_menuHorizontalOverlapAnchor, true);
        this.h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ListMenuButton_menuVerticalOverlapAnchor, true);
        this.o = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ListMenuButton_menuPositionedAtEnd, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // p4.c
    public final void a(boolean z, int i, int i2, Rect rect) {
        if (this.o) {
            p4 p4Var = this.k;
            int i3 = z ? R$style.EndIconMenuAnim : R$style.EndIconMenuAnimBottom;
            p4Var.A = i3;
            p4Var.j.setAnimationStyle(i3);
            return;
        }
        p4 p4Var2 = this.k;
        int i4 = z ? R$style.StartIconMenuAnim : R$style.StartIconMenuAnimBottom;
        p4Var2.A = i4;
        p4Var2.j.setAnimationStyle(i4);
    }

    public final void b() {
        p4 p4Var = this.k;
        if (p4Var != null) {
            p4Var.a();
        }
    }

    public final void d(l81 l81Var, boolean z) {
        b();
        this.l = l81Var;
        if (z) {
            setOnClickListener(new vt(28, this));
        }
    }

    public final void f() {
        if (this.p) {
            b();
            l81 l81Var = this.l;
            if (l81Var == null) {
                throw new IllegalStateException("Delegate was not set.");
            }
            k81 k81VarB = l81Var.b();
            k81VarB.a(new f52(26, this));
            View viewD = k81VarB.d();
            ViewParent parent = viewD.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(viewD);
            }
            p4 p4Var = new p4(getContext(), this, new ColorDrawable(0), viewD, this.l.a(this));
            this.k = p4Var;
            p4Var.w = this.h;
            p4Var.x = this.i;
            p4Var.r = this.j;
            if (this.n) {
                int paddingRight = viewD.getPaddingRight() + viewD.getPaddingLeft();
                this.k.s = k81VarB.b() + paddingRight;
            }
            this.k.j.setFocusable(true);
            p4 p4Var2 = this.k;
            p4Var2.p = this;
            p4Var2.addOnDismissListener(new pm1(2, this));
            this.k.j.setOutsideTouchable(true);
            this.k.d();
            Iterator it = this.m.e.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a();
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = true;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        b();
        this.p = false;
        super.onDetachedFromWindow();
    }

    @Override // org.chromium.ui.widget.ChromeImageButton, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (TextUtils.isEmpty(getContentDescription())) {
            setContentDescriptionContext("");
        }
    }

    public void setContentDescriptionContext(String str) {
        if (TextUtils.isEmpty(str)) {
            setContentDescription(getContext().getResources().getString(R$string.accessibility_toolbar_btn_menu));
        } else {
            setContentDescription(getContext().getResources().getString(R$string.accessibility_list_menu_button, str));
        }
    }

    public void setDelegate(l81 l81Var) {
        d(l81Var, true);
    }

    public void setMenuMaxWidth(int i) {
        this.j = i;
    }

    @FunctionalInterface
    public interface a {
        void a();

        default void b() {
        }
    }
}
