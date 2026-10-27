package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar.ExpandedActionViewMenuPresenter;
import defpackage.in3;
import defpackage.kn3;
import defpackage.om3;

/* JADX INFO: loaded from: classes.dex */
public class ToolbarWidgetWrapper implements DecorToolbar {
    public final Toolbar a;
    public int b;
    public ScrollingTabContainerView c;
    public View d;
    public Drawable e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public ActionMenuPresenter n;
    public int o;
    public Drawable p;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        int i;
        Drawable drawable;
        int i2 = R.string.abc_action_bar_up_description;
        this.o = 0;
        this.a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        TintTypedArray tintTypedArrayM = TintTypedArray.m(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle);
        this.p = tintTypedArrayM.e(R.styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceK = tintTypedArrayM.k(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceK)) {
                setTitle(charSequenceK);
            }
            CharSequence charSequenceK2 = tintTypedArrayM.k(R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceK2)) {
                this.j = charSequenceK2;
                if ((this.b & 8) != 0) {
                    toolbar.setSubtitle(charSequenceK2);
                }
            }
            Drawable drawableE = tintTypedArrayM.e(R.styleable.ActionBar_logo);
            if (drawableE != null) {
                this.f = drawableE;
                u();
            }
            Drawable drawableE2 = tintTypedArrayM.e(R.styleable.ActionBar_icon);
            if (drawableE2 != null) {
                setIcon(drawableE2);
            }
            if (this.g == null && (drawable = this.p) != null) {
                this.g = drawable;
                if ((this.b & 4) != 0) {
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            k(tintTypedArrayM.h(R.styleable.ActionBar_displayOptions, 0));
            int i3 = tintTypedArrayM.i(R.styleable.ActionBar_customNavigationLayout, 0);
            if (i3 != 0) {
                View viewInflate = LayoutInflater.from(toolbar.getContext()).inflate(i3, (ViewGroup) toolbar, false);
                View view = this.d;
                if (view != null && (this.b & 16) != 0) {
                    toolbar.removeView(view);
                }
                this.d = viewInflate;
                if (viewInflate != null && (this.b & 16) != 0) {
                    toolbar.addView(viewInflate);
                }
                k(this.b | 16);
            }
            int layoutDimension = tintTypedArrayM.b.getLayoutDimension(R.styleable.ActionBar_height, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height = layoutDimension;
                toolbar.setLayoutParams(layoutParams);
            }
            int iC = tintTypedArrayM.c(R.styleable.ActionBar_contentInsetStart, -1);
            int iC2 = tintTypedArrayM.c(R.styleable.ActionBar_contentInsetEnd, -1);
            if (iC >= 0 || iC2 >= 0) {
                int iMax = Math.max(iC, 0);
                int iMax2 = Math.max(iC2, 0);
                if (toolbar.x == null) {
                    toolbar.x = new RtlSpacingHelper();
                }
                toolbar.x.a(iMax, iMax2);
            }
            int i4 = tintTypedArrayM.i(R.styleable.ActionBar_titleTextStyle, 0);
            if (i4 != 0) {
                Context context = toolbar.getContext();
                toolbar.p = i4;
                AppCompatTextView appCompatTextView = toolbar.f;
                if (appCompatTextView != null) {
                    appCompatTextView.setTextAppearance(context, i4);
                }
            }
            int i5 = tintTypedArrayM.i(R.styleable.ActionBar_subtitleTextStyle, 0);
            if (i5 != 0) {
                Context context2 = toolbar.getContext();
                toolbar.q = i5;
                AppCompatTextView appCompatTextView2 = toolbar.g;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTextAppearance(context2, i5);
                }
            }
            int i6 = tintTypedArrayM.i(R.styleable.ActionBar_popupTheme, 0);
            if (i6 != 0) {
                toolbar.setPopupTheme(i6);
            }
        } else {
            if (toolbar.getNavigationIcon() != null) {
                this.p = toolbar.getNavigationIcon();
                i = 15;
            } else {
                i = 11;
            }
            this.b = i;
        }
        tintTypedArrayM.n();
        if (i2 != this.o) {
            this.o = i2;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                q(this.o);
            }
        }
        this.k = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.ToolbarWidgetWrapper.1
            public final ActionMenuItem e;

            {
                this.e = new ActionMenuItem(ToolbarWidgetWrapper.this.a.getContext(), ToolbarWidgetWrapper.this.i);
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ToolbarWidgetWrapper toolbarWidgetWrapper = ToolbarWidgetWrapper.this;
                Window.Callback callback = toolbarWidgetWrapper.l;
                if (callback == null || !toolbarWidgetWrapper.m) {
                    return;
                }
                callback.onMenuItemSelected(0, this.e);
            }
        });
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final boolean a() {
        ActionMenuView actionMenuView = this.a.e;
        if (actionMenuView == null) {
            return false;
        }
        ActionMenuPresenter actionMenuPresenter = actionMenuView.x;
        return actionMenuPresenter != null && actionMenuPresenter.p();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void b() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final boolean c() {
        ActionMenuView actionMenuView;
        Toolbar toolbar = this.a;
        return toolbar.getVisibility() == 0 && (actionMenuView = toolbar.e) != null && actionMenuView.w;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void collapseActionView() {
        Toolbar.ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.a.Q;
        MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter == null ? null : expandedActionViewMenuPresenter.f;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void d(MenuBuilder menuBuilder, MenuPresenter.Callback callback) {
        ActionMenuPresenter actionMenuPresenter = this.n;
        Toolbar toolbar = this.a;
        if (actionMenuPresenter == null) {
            ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(toolbar.getContext());
            this.n = actionMenuPresenter2;
            actionMenuPresenter2.m = R.id.action_menu_presenter;
        }
        ActionMenuPresenter actionMenuPresenter3 = this.n;
        actionMenuPresenter3.i = callback;
        if (menuBuilder == null && toolbar.e == null) {
            return;
        }
        toolbar.h();
        MenuBuilder menuBuilder2 = toolbar.e.t;
        if (menuBuilder2 == menuBuilder) {
            return;
        }
        if (menuBuilder2 != null) {
            menuBuilder2.r(toolbar.P);
            menuBuilder2.r(toolbar.Q);
        }
        if (toolbar.Q == null) {
            toolbar.Q = toolbar.new ExpandedActionViewMenuPresenter();
        }
        actionMenuPresenter3.v = true;
        if (menuBuilder != null) {
            menuBuilder.b(actionMenuPresenter3, toolbar.n);
            menuBuilder.b(toolbar.Q, toolbar.n);
        } else {
            actionMenuPresenter3.h(toolbar.n, null);
            toolbar.Q.h(toolbar.n, null);
            actionMenuPresenter3.d(true);
            toolbar.Q.d(true);
        }
        toolbar.e.setPopupTheme(toolbar.o);
        toolbar.e.setPresenter(actionMenuPresenter3);
        toolbar.P = actionMenuPresenter3;
        toolbar.w();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001e  */
    @Override // androidx.appcompat.widget.DecorToolbar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e() {
        /*
            r3 = this;
            androidx.appcompat.widget.Toolbar r3 = r3.a
            androidx.appcompat.widget.ActionMenuView r3 = r3.e
            r0 = 0
            if (r3 == 0) goto L22
            androidx.appcompat.widget.ActionMenuPresenter r3 = r3.x
            r1 = 1
            if (r3 == 0) goto L1e
            androidx.appcompat.widget.ActionMenuPresenter$OpenOverflowRunnable r2 = r3.z
            if (r2 != 0) goto L19
            boolean r3 = r3.p()
            if (r3 == 0) goto L17
            goto L19
        L17:
            r3 = r0
            goto L1a
        L19:
            r3 = r1
        L1a:
            if (r3 == 0) goto L1e
            r3 = r1
            goto L1f
        L1e:
            r3 = r0
        L1f:
            if (r3 == 0) goto L22
            r0 = r1
        L22:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ToolbarWidgetWrapper.e():boolean");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final boolean f() {
        return this.a.n();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final boolean g() {
        ActionMenuView actionMenuView = this.a.e;
        if (actionMenuView == null) {
            return false;
        }
        ActionMenuPresenter actionMenuPresenter = actionMenuView.x;
        return actionMenuPresenter != null && actionMenuPresenter.r();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final Context getContext() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final CharSequence getTitle() {
        return this.a.getTitle();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void h() {
        ActionMenuPresenter actionMenuPresenter;
        ActionMenuView actionMenuView = this.a.e;
        if (actionMenuView == null || (actionMenuPresenter = actionMenuView.x) == null) {
            return;
        }
        actionMenuPresenter.o();
        ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = actionMenuPresenter.y;
        if (actionButtonSubmenu == null || !actionButtonSubmenu.b()) {
            return;
        }
        actionButtonSubmenu.j.dismiss();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final boolean j() {
        Toolbar.ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.a.Q;
        return (expandedActionViewMenuPresenter == null || expandedActionViewMenuPresenter.f == null) ? false : true;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void k(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
        if (i2 != 0) {
            int i3 = i2 & 4;
            Toolbar toolbar = this.a;
            if (i3 != 0) {
                if ((i & 4) != 0 && (i & 4) != 0) {
                    if (TextUtils.isEmpty(this.k)) {
                        toolbar.setNavigationContentDescription(this.o);
                    } else {
                        toolbar.setNavigationContentDescription(this.k);
                    }
                }
                if ((this.b & 4) != 0) {
                    Drawable drawable = this.g;
                    if (drawable == null) {
                        drawable = this.p;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i2 & 3) != 0) {
                u();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    toolbar.setTitle(this.i);
                    toolbar.setSubtitle(this.j);
                } else {
                    toolbar.setTitle((CharSequence) null);
                    toolbar.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                toolbar.addView(view);
            } else {
                toolbar.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void l() {
        ScrollingTabContainerView scrollingTabContainerView = this.c;
        if (scrollingTabContainerView != null) {
            ViewParent parent = scrollingTabContainerView.getParent();
            Toolbar toolbar = this.a;
            if (parent == toolbar) {
                toolbar.removeView(this.c);
            }
        }
        this.c = null;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void m(int i) {
        this.f = i != 0 ? AppCompatResources.a(getContext(), i) : null;
        u();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final in3 o(final int i, long j) {
        in3 in3VarB = om3.b(this.a);
        in3VarB.a(i == 0 ? 1.0f : 0.0f);
        in3VarB.c(j);
        in3VarB.d(new kn3() { // from class: androidx.appcompat.widget.ToolbarWidgetWrapper.2
            public boolean a = false;

            @Override // defpackage.kn3, defpackage.jn3
            public final void a() {
                if (this.a) {
                    return;
                }
                ToolbarWidgetWrapper.this.a.setVisibility(i);
            }

            @Override // defpackage.kn3, defpackage.jn3
            public final void b(View view) {
                this.a = true;
            }

            @Override // defpackage.kn3, defpackage.jn3
            public final void c() {
                ToolbarWidgetWrapper.this.a.setVisibility(0);
            }
        });
        return in3VarB;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final int p() {
        return this.b;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void q(int i) {
        String string = i == 0 ? null : getContext().getString(i);
        this.k = string;
        if ((this.b & 4) != 0) {
            boolean zIsEmpty = TextUtils.isEmpty(string);
            Toolbar toolbar = this.a;
            if (zIsEmpty) {
                toolbar.setNavigationContentDescription(this.o);
            } else {
                toolbar.setNavigationContentDescription(this.k);
            }
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void s() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setIcon(int i) {
        setIcon(i != 0 ? AppCompatResources.a(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setTitle(CharSequence charSequence) {
        this.h = true;
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            Toolbar toolbar = this.a;
            toolbar.setTitle(charSequence);
            if (this.h) {
                om3.l(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setVisibility(int i) {
        this.a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            Toolbar toolbar = this.a;
            toolbar.setTitle(charSequence);
            if (this.h) {
                om3.l(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void t(boolean z) {
        this.a.setCollapsible(z);
    }

    public final void u() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void setIcon(Drawable drawable) {
        this.e = drawable;
        u();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void i() {
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public final void n() {
    }
}
