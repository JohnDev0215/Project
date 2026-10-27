package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DropDownListView;
import androidx.appcompat.widget.MenuPopupWindow;

/* JADX INFO: loaded from: classes.dex */
final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public static final int z = R.layout.abc_popup_menu_item_layout;
    public final Context f;
    public final MenuBuilder g;
    public final MenuAdapter h;
    public final boolean i;
    public final int j;
    public final int k;
    public final int l;
    public final MenuPopupWindow m;
    public PopupWindow.OnDismissListener p;
    public View q;
    public View r;
    public MenuPresenter.Callback s;
    public ViewTreeObserver t;
    public boolean u;
    public boolean v;
    public int w;
    public boolean y;
    public final ViewTreeObserver.OnGlobalLayoutListener n = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
            if (!standardMenuPopup.b() || standardMenuPopup.m.C) {
                return;
            }
            View view = standardMenuPopup.r;
            if (view == null || !view.isShown()) {
                standardMenuPopup.dismiss();
            } else {
                standardMenuPopup.m.a();
            }
        }
    };
    public final View.OnAttachStateChangeListener o = new View.OnAttachStateChangeListener() { // from class: androidx.appcompat.view.menu.StandardMenuPopup.2
        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            StandardMenuPopup standardMenuPopup = StandardMenuPopup.this;
            ViewTreeObserver viewTreeObserver = standardMenuPopup.t;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    standardMenuPopup.t = view.getViewTreeObserver();
                }
                standardMenuPopup.t.removeGlobalOnLayoutListener(standardMenuPopup.n);
            }
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }
    };
    public int x = 0;

    public StandardMenuPopup(int i, int i2, Context context, View view, MenuBuilder menuBuilder, boolean z2) {
        this.f = context;
        this.g = menuBuilder;
        this.i = z2;
        this.h = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z2, z);
        this.k = i;
        this.l = i2;
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.q = view;
        this.m = new MenuPopupWindow(context, i, i2);
        menuBuilder.b(this, context);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void a() {
        View view;
        boolean z2 = true;
        if (!b()) {
            if (this.u || (view = this.q) == null) {
                z2 = false;
            } else {
                this.r = view;
                MenuPopupWindow menuPopupWindow = this.m;
                menuPopupWindow.setOnDismissListener(this);
                menuPopupWindow.setOnItemClickListener(this);
                menuPopupWindow.C = true;
                PopupWindow popupWindow = menuPopupWindow.D;
                popupWindow.setFocusable(true);
                View view2 = this.r;
                boolean z3 = this.t == null;
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                this.t = viewTreeObserver;
                if (z3) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.n);
                }
                view2.addOnAttachStateChangeListener(this.o);
                menuPopupWindow.s = view2;
                menuPopupWindow.p = this.x;
                boolean z4 = this.v;
                Context context = this.f;
                MenuAdapter menuAdapter = this.h;
                if (!z4) {
                    this.w = MenuPopup.o(menuAdapter, context, this.j);
                    this.v = true;
                }
                menuPopupWindow.f(this.w);
                popupWindow.setInputMethodMode(2);
                Rect rect = this.e;
                menuPopupWindow.B = rect != null ? new Rect(rect) : null;
                menuPopupWindow.a();
                DropDownListView dropDownListView = menuPopupWindow.g;
                dropDownListView.setOnKeyListener(this);
                if (this.y) {
                    MenuBuilder menuBuilder = this.g;
                    if (menuBuilder.m != null) {
                        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) dropDownListView, false);
                        TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                        if (textView != null) {
                            textView.setText(menuBuilder.m);
                        }
                        frameLayout.setEnabled(false);
                        dropDownListView.addHeaderView(frameLayout, null, false);
                    }
                }
                menuPopupWindow.v(menuAdapter);
                menuPopupWindow.a();
            }
        }
        if (!z2) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final boolean b() {
        return !this.u && this.m.b();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z2) {
        if (menuBuilder != this.g) {
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.s;
        if (callback != null) {
            callback.c(menuBuilder, z2);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z2) {
        this.v = false;
        MenuAdapter menuAdapter = this.h;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void dismiss() {
        if (b()) {
            this.m.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void f(MenuPresenter.Callback callback) {
        this.s = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final ListView j() {
        return this.m.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean k(androidx.appcompat.view.menu.SubMenuBuilder r10) {
        /*
            r9 = this;
            boolean r0 = r10.hasVisibleItems()
            r1 = 0
            if (r0 == 0) goto L75
            androidx.appcompat.view.menu.MenuPopupHelper r0 = new androidx.appcompat.view.menu.MenuPopupHelper
            android.content.Context r5 = r9.f
            android.view.View r6 = r9.r
            boolean r8 = r9.i
            int r3 = r9.k
            int r4 = r9.l
            r2 = r0
            r7 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            androidx.appcompat.view.menu.MenuPresenter$Callback r2 = r9.s
            r0.i = r2
            androidx.appcompat.view.menu.MenuPopup r3 = r0.j
            if (r3 == 0) goto L23
            r3.f(r2)
        L23:
            boolean r2 = androidx.appcompat.view.menu.MenuPopup.v(r10)
            r0.d(r2)
            android.widget.PopupWindow$OnDismissListener r2 = r9.p
            r0.setOnDismissListener(r2)
            r2 = 0
            r9.p = r2
            androidx.appcompat.view.menu.MenuBuilder r2 = r9.g
            r2.c(r1)
            androidx.appcompat.widget.MenuPopupWindow r2 = r9.m
            int r3 = r2.j
            int r2 = r2.u()
            int r4 = r9.x
            android.view.View r5 = r9.q
            java.util.WeakHashMap<android.view.View, in3> r6 = defpackage.om3.a
            int r5 = om3.e.c(r5)
            int r4 = android.view.Gravity.getAbsoluteGravity(r4, r5)
            r4 = r4 & 7
            r5 = 5
            if (r4 != r5) goto L59
            android.view.View r4 = r9.q
            int r4 = r4.getWidth()
            int r3 = r3 + r4
        L59:
            boolean r4 = r0.b()
            r5 = 1
            if (r4 == 0) goto L61
            goto L6a
        L61:
            android.view.View r4 = r0.f
            if (r4 != 0) goto L67
            r0 = r1
            goto L6b
        L67:
            r0.e(r3, r2, r5, r5)
        L6a:
            r0 = r5
        L6b:
            if (r0 == 0) goto L75
            androidx.appcompat.view.menu.MenuPresenter$Callback r9 = r9.s
            if (r9 == 0) goto L74
            r9.d(r10)
        L74:
            return r5
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.StandardMenuPopup.k(androidx.appcompat.view.menu.SubMenuBuilder):boolean");
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        return null;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.u = true;
        this.g.close();
        ViewTreeObserver viewTreeObserver = this.t;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.t = this.r.getViewTreeObserver();
            }
            this.t.removeGlobalOnLayoutListener(this.n);
            this.t = null;
        }
        this.r.removeOnAttachStateChangeListener(this.o);
        PopupWindow.OnDismissListener onDismissListener = this.p;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void p(View view) {
        this.q = view;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void q(boolean z2) {
        this.h.g = z2;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void r(int i) {
        this.x = i;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void s(int i) {
        this.m.j = i;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.p = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void t(boolean z2) {
        this.y = z2;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void u(int i) {
        this.m.s(i);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void n(MenuBuilder menuBuilder) {
    }
}
