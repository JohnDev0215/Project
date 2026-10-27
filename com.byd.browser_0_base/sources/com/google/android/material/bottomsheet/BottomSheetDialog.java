package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import defpackage.in3;
import defpackage.li;
import defpackage.mi;
import defpackage.ni;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pt3;
import defpackage.rt3;
import defpackage.sc1;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetDialog extends AppCompatDialog {
    public BottomSheetBehavior<FrameLayout> j;
    public FrameLayout k;
    public CoordinatorLayout l;
    public FrameLayout m;
    public boolean n;
    public boolean o;
    public boolean p;
    public b q;
    public final boolean r;
    public final a s;

    public static class b extends BottomSheetBehavior.c {
        public final Boolean a;
        public final pt3 b;
        public Window c;
        public boolean d;

        public b(FrameLayout frameLayout, pt3 pt3Var) {
            ColorStateList colorStateListC;
            this.b = pt3Var;
            sc1 sc1Var = BottomSheetBehavior.w(frameLayout).h;
            if (sc1Var != null) {
                colorStateListC = sc1Var.e.c;
            } else {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                colorStateListC = om3.i.c(frameLayout);
            }
            if (colorStateListC != null) {
                this.a = Boolean.valueOf(oj2.x(colorStateListC.getDefaultColor()));
            } else if (frameLayout.getBackground() instanceof ColorDrawable) {
                this.a = Boolean.valueOf(oj2.x(((ColorDrawable) frameLayout.getBackground()).getColor()));
            } else {
                this.a = null;
            }
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public final void a(View view) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public final void b(View view) {
            d(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public final void c(View view, int i) {
            d(view);
        }

        public final void d(View view) {
            int top = view.getTop();
            pt3 pt3Var = this.b;
            if (top < pt3Var.e()) {
                Window window = this.c;
                if (window != null) {
                    Boolean bool = this.a;
                    new rt3(window, window.getDecorView()).a.d(bool == null ? this.d : bool.booleanValue());
                }
                view.setPadding(view.getPaddingLeft(), pt3Var.e() - view.getTop(), view.getPaddingRight(), view.getPaddingBottom());
                return;
            }
            if (view.getTop() != 0) {
                Window window2 = this.c;
                if (window2 != null) {
                    new rt3(window2, window2.getDecorView()).a.d(this.d);
                }
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            }
        }

        public final void e(Window window) {
            if (this.c == window) {
                return;
            }
            this.c = window;
            if (window != null) {
                this.d = new rt3(window, window.getDecorView()).a.b();
            }
        }
    }

    public BottomSheetDialog(Context context) {
        TypedValue typedValue = new TypedValue();
        super(context, context.getTheme().resolveAttribute(R$attr.bottomSheetDialogTheme, typedValue, true) ? typedValue.resourceId : R$style.Theme_Design_Light_BottomSheetDialog);
        this.n = true;
        this.o = true;
        this.s = new a();
        c().x(1);
        Resources.Theme theme = getContext().getTheme();
        int i = R$attr.enableEdgeToEdge;
        this.r = theme.obtainStyledAttributes(new int[]{i}).getBoolean(0, false);
        this.r = getContext().getTheme().obtainStyledAttributes(new int[]{i}).getBoolean(0, false);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void cancel() {
        if (this.j == null) {
            e();
        }
        super.cancel();
    }

    public final void e() {
        if (this.k == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R$layout.design_bottom_sheet_dialog, null);
            this.k = frameLayout;
            this.l = (CoordinatorLayout) frameLayout.findViewById(R$id.coordinator);
            FrameLayout frameLayout2 = (FrameLayout) this.k.findViewById(R$id.design_bottom_sheet);
            this.m = frameLayout2;
            BottomSheetBehavior<FrameLayout> bottomSheetBehaviorW = BottomSheetBehavior.w(frameLayout2);
            this.j = bottomSheetBehaviorW;
            ArrayList<BottomSheetBehavior.c> arrayList = bottomSheetBehaviorW.U;
            a aVar = this.s;
            if (!arrayList.contains(aVar)) {
                arrayList.add(aVar);
            }
            this.j.A(this.n);
        }
    }

    public final FrameLayout f(View view, int i, ViewGroup.LayoutParams layoutParams) {
        e();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.k.findViewById(R$id.coordinator);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, (ViewGroup) coordinatorLayout, false);
        }
        if (this.r) {
            FrameLayout frameLayout = this.m;
            com.google.android.material.bottomsheet.a aVar = new com.google.android.material.bottomsheet.a(this);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.i.m(frameLayout, aVar);
        }
        this.m.removeAllViews();
        if (layoutParams == null) {
            this.m.addView(view);
        } else {
            this.m.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R$id.touch_outside).setOnClickListener(new li(this));
        om3.k(this.m, new mi(this));
        this.m.setOnTouchListener(new ni());
        return this.k;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        if (window != null) {
            boolean z = this.r && Color.alpha(window.getNavigationBarColor()) < 255;
            FrameLayout frameLayout = this.k;
            if (frameLayout != null) {
                frameLayout.setFitsSystemWindows(!z);
            }
            CoordinatorLayout coordinatorLayout = this.l;
            if (coordinatorLayout != null) {
                coordinatorLayout.setFitsSystemWindows(!z);
            }
            if (z) {
                window.getDecorView().setSystemUiVisibility(768);
            }
            b bVar = this.q;
            if (bVar != null) {
                bVar.e(window);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        b bVar = this.q;
        if (bVar != null) {
            bVar.e(null);
        }
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.j;
        if (bottomSheetBehavior == null || bottomSheetBehavior.f28J != 5) {
            return;
        }
        bottomSheetBehavior.C(4);
    }

    @Override // android.app.Dialog
    public final void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.n != z) {
            this.n = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.j;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.A(z);
            }
        }
    }

    @Override // android.app.Dialog
    public final void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.n) {
            this.n = true;
        }
        this.o = z;
        this.p = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void setContentView(int i) {
        super.setContentView(f(null, i, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void setContentView(View view) {
        super.setContentView(f(view, 0, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(f(view, 0, layoutParams));
    }

    public class a extends BottomSheetBehavior.c {
        public a() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public final void c(View view, int i) {
            if (i == 5) {
                BottomSheetDialog.this.cancel();
            }
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.c
        public final void b(View view) {
        }
    }
}
