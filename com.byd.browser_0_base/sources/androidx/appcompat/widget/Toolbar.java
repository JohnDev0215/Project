package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import androidx.fragment.app.FragmentManager;
import defpackage.gf1;
import defpackage.hf1;
import defpackage.in3;
import defpackage.kf1;
import defpackage.om3;
import defpackage.pb3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup implements gf1 {
    public final int A;
    public CharSequence B;
    public CharSequence C;
    public ColorStateList D;
    public ColorStateList E;
    public boolean F;
    public boolean G;
    public final ArrayList<View> H;
    public final ArrayList<View> I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int[] f5J;
    public final hf1 K;
    public ArrayList<MenuItem> L;
    public OnMenuItemClickListener M;
    public final ActionMenuView.OnMenuItemClickListener N;
    public ToolbarWidgetWrapper O;
    public ActionMenuPresenter P;
    public ExpandedActionViewMenuPresenter Q;
    public MenuPresenter.Callback R;
    public MenuBuilder.Callback S;
    public boolean T;
    public pb3 U;
    public OnBackInvokedDispatcher V;
    public boolean W;
    public final Runnable a0;
    public ActionMenuView e;
    public AppCompatTextView f;
    public AppCompatTextView g;
    public AppCompatImageButton h;
    public AppCompatImageView i;
    public final Drawable j;
    public final CharSequence k;
    public AppCompatImageButton l;
    public View m;
    public Context n;
    public int o;
    public int p;
    public int q;
    public final int r;
    public final int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public RtlSpacingHelper x;
    public int y;
    public int z;

    public static class Api33Impl {
        private Api33Impl() {
        }

        public static OnBackInvokedDispatcher a(View view) {
            return view.findOnBackInvokedDispatcher();
        }

        public static void b(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj2);
        }

        public static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<Toolbar> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapObject("collapseContentDescription", R.attr.collapseContentDescription);
            this.c = propertyMapper.mapObject("collapseIcon", R.attr.collapseIcon);
            this.d = propertyMapper.mapInt("contentInsetEnd", R.attr.contentInsetEnd);
            this.e = propertyMapper.mapInt("contentInsetEndWithActions", R.attr.contentInsetEndWithActions);
            this.f = propertyMapper.mapInt("contentInsetLeft", R.attr.contentInsetLeft);
            this.g = propertyMapper.mapInt("contentInsetRight", R.attr.contentInsetRight);
            this.h = propertyMapper.mapInt("contentInsetStart", R.attr.contentInsetStart);
            this.i = propertyMapper.mapInt("contentInsetStartWithNavigation", R.attr.contentInsetStartWithNavigation);
            this.j = propertyMapper.mapObject("logo", R.attr.logo);
            this.k = propertyMapper.mapObject("logoDescription", R.attr.logoDescription);
            this.l = propertyMapper.mapObject("menu", R.attr.menu);
            this.m = propertyMapper.mapObject("navigationContentDescription", R.attr.navigationContentDescription);
            this.n = propertyMapper.mapObject("navigationIcon", R.attr.navigationIcon);
            this.o = propertyMapper.mapResourceId("popupTheme", R.attr.popupTheme);
            this.p = propertyMapper.mapObject("subtitle", R.attr.subtitle);
            this.q = propertyMapper.mapObject("title", R.attr.title);
            this.r = propertyMapper.mapInt("titleMarginBottom", R.attr.titleMarginBottom);
            this.s = propertyMapper.mapInt("titleMarginEnd", R.attr.titleMarginEnd);
            this.t = propertyMapper.mapInt("titleMarginStart", R.attr.titleMarginStart);
            this.u = propertyMapper.mapInt("titleMarginTop", R.attr.titleMarginTop);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(Toolbar toolbar, PropertyReader propertyReader) {
            Toolbar toolbar2 = toolbar;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.b, toolbar2.getCollapseContentDescription());
            propertyReader.readObject(this.c, toolbar2.getCollapseIcon());
            propertyReader.readInt(this.d, toolbar2.getContentInsetEnd());
            propertyReader.readInt(this.e, toolbar2.getContentInsetEndWithActions());
            propertyReader.readInt(this.f, toolbar2.getContentInsetLeft());
            propertyReader.readInt(this.g, toolbar2.getContentInsetRight());
            propertyReader.readInt(this.h, toolbar2.getContentInsetStart());
            propertyReader.readInt(this.i, toolbar2.getContentInsetStartWithNavigation());
            propertyReader.readObject(this.j, toolbar2.getLogo());
            propertyReader.readObject(this.k, toolbar2.getLogoDescription());
            propertyReader.readObject(this.l, toolbar2.getMenu());
            propertyReader.readObject(this.m, toolbar2.getNavigationContentDescription());
            propertyReader.readObject(this.n, toolbar2.getNavigationIcon());
            propertyReader.readResourceId(this.o, toolbar2.getPopupTheme());
            propertyReader.readObject(this.p, toolbar2.getSubtitle());
            propertyReader.readObject(this.q, toolbar2.getTitle());
            propertyReader.readInt(this.r, toolbar2.getTitleMarginBottom());
            propertyReader.readInt(this.s, toolbar2.getTitleMarginEnd());
            propertyReader.readInt(this.t, toolbar2.getTitleMarginStart());
            propertyReader.readInt(this.u, toolbar2.getTitleMarginTop());
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i = 0; i < menu.size(); i++) {
            arrayList.add(menu.getItem(i));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    public static LayoutParams j(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ActionBar.LayoutParams ? new LayoutParams((ActionBar.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int m(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    @Override // defpackage.gf1
    public final void A0(FragmentManager.c cVar) {
        hf1 hf1Var = this.K;
        hf1Var.b.add(cVar);
        hf1Var.a.run();
    }

    @Override // defpackage.gf1
    public final void U0(FragmentManager.c cVar) {
        hf1 hf1Var = this.K;
        hf1Var.b.remove(cVar);
        if (((hf1.a) hf1Var.c.remove(cVar)) != null) {
            throw null;
        }
        hf1Var.a.run();
    }

    public final void a(int i, ArrayList arrayList) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = om3.e.c(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, om3.e.c(this));
        arrayList.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b == 0 && v(childAt)) {
                    int i3 = layoutParams.a;
                    WeakHashMap<View, in3> weakHashMap2 = om3.a;
                    int iC = om3.e.c(this);
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i3, iC) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = iC == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.b == 0 && v(childAt2)) {
                int i5 = layoutParams2.a;
                WeakHashMap<View, in3> weakHashMap3 = om3.a;
                int iC2 = om3.e.c(this);
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i5, iC2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = iC2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams layoutParams2 = layoutParams == null ? new LayoutParams() : !checkLayoutParams(layoutParams) ? j(layoutParams) : (LayoutParams) layoutParams;
        layoutParams2.b = 1;
        if (!z || this.m == null) {
            addView(view, layoutParams2);
        } else {
            view.setLayoutParams(layoutParams2);
            this.I.add(view);
        }
    }

    public final void c() {
        if (this.l == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.l = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.j);
            this.l.setContentDescription(this.k);
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = (this.r & 112) | 8388611;
            layoutParams.b = 2;
            this.l.setLayoutParams(layoutParams);
            this.l.setOnClickListener(new View.OnClickListener() { // from class: androidx.appcompat.widget.Toolbar.4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = Toolbar.this.Q;
                    MenuItemImpl menuItemImpl = expandedActionViewMenuPresenter == null ? null : expandedActionViewMenuPresenter.f;
                    if (menuItemImpl != null) {
                        menuItemImpl.collapseActionView();
                    }
                }
            });
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public final void g() {
        h();
        ActionMenuView actionMenuView = this.e;
        if (actionMenuView.t == null) {
            MenuBuilder menuBuilder = (MenuBuilder) actionMenuView.getMenu();
            if (this.Q == null) {
                this.Q = new ExpandedActionViewMenuPresenter();
            }
            this.e.setExpandedActionViewsExclusive(true);
            menuBuilder.b(this.Q, this.n);
            w();
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        AppCompatImageButton appCompatImageButton = this.l;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        AppCompatImageButton appCompatImageButton = this.l;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper rtlSpacingHelper = this.x;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.g ? rtlSpacingHelper.a : rtlSpacingHelper.b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.z;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        RtlSpacingHelper rtlSpacingHelper = this.x;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        RtlSpacingHelper rtlSpacingHelper = this.x;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        RtlSpacingHelper rtlSpacingHelper = this.x;
        if (rtlSpacingHelper != null) {
            return rtlSpacingHelper.g ? rtlSpacingHelper.b : rtlSpacingHelper.a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.y;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        MenuBuilder menuBuilder;
        ActionMenuView actionMenuView = this.e;
        return actionMenuView != null && (menuBuilder = actionMenuView.t) != null && menuBuilder.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.z, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.y, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        AppCompatImageView appCompatImageView = this.i;
        if (appCompatImageView != null) {
            return appCompatImageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        AppCompatImageView appCompatImageView = this.i;
        if (appCompatImageView != null) {
            return appCompatImageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        g();
        return this.e.getMenu();
    }

    public View getNavButtonView() {
        return this.h;
    }

    public CharSequence getNavigationContentDescription() {
        AppCompatImageButton appCompatImageButton = this.h;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        AppCompatImageButton appCompatImageButton = this.h;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.P;
    }

    public Drawable getOverflowIcon() {
        g();
        return this.e.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.n;
    }

    public int getPopupTheme() {
        return this.o;
    }

    public CharSequence getSubtitle() {
        return this.C;
    }

    public final TextView getSubtitleTextView() {
        return this.g;
    }

    public CharSequence getTitle() {
        return this.B;
    }

    public int getTitleMarginBottom() {
        return this.w;
    }

    public int getTitleMarginEnd() {
        return this.u;
    }

    public int getTitleMarginStart() {
        return this.t;
    }

    public int getTitleMarginTop() {
        return this.v;
    }

    public final TextView getTitleTextView() {
        return this.f;
    }

    public DecorToolbar getWrapper() {
        if (this.O == null) {
            this.O = new ToolbarWidgetWrapper(this, true);
        }
        return this.O;
    }

    public final void h() {
        if (this.e == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.e = actionMenuView;
            actionMenuView.setPopupTheme(this.o);
            this.e.setOnMenuItemClickListener(this.N);
            ActionMenuView actionMenuView2 = this.e;
            MenuPresenter.Callback callback = this.R;
            MenuBuilder.Callback callback2 = new MenuBuilder.Callback() { // from class: androidx.appcompat.widget.Toolbar.3
                @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
                public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
                    MenuBuilder.Callback callback3 = Toolbar.this.S;
                    return callback3 != null && callback3.a(menuBuilder, menuItem);
                }

                @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
                public final void b(MenuBuilder menuBuilder) {
                    Toolbar toolbar = Toolbar.this;
                    ActionMenuPresenter actionMenuPresenter = toolbar.e.x;
                    if (!(actionMenuPresenter != null && actionMenuPresenter.p())) {
                        Iterator<kf1> it = toolbar.K.b.iterator();
                        while (it.hasNext()) {
                            it.next().d(menuBuilder);
                        }
                    }
                    MenuBuilder.Callback callback3 = toolbar.S;
                    if (callback3 != null) {
                        callback3.b(menuBuilder);
                    }
                }
            };
            actionMenuView2.y = callback;
            actionMenuView2.z = callback2;
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = (this.r & 112) | 8388613;
            this.e.setLayoutParams(layoutParams);
            b(this.e, false);
        }
    }

    public final void i() {
        if (this.h == null) {
            this.h = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.a = (this.r & 112) | 8388611;
            this.h.setLayoutParams(layoutParams);
        }
    }

    public final int k(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = layoutParams.a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.A & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public final boolean n() {
        ActionMenuView actionMenuView = this.e;
        if (actionMenuView == null) {
            return false;
        }
        ActionMenuPresenter actionMenuPresenter = actionMenuView.x;
        return actionMenuPresenter != null && actionMenuPresenter.o();
    }

    public final void o(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        w();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.a0);
        w();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.G = false;
        }
        if (!this.G) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.G = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.G = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0292 A[LOOP:0: B:102:0x0290->B:103:0x0292, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02ae A[LOOP:1: B:105:0x02ac->B:106:0x02ae, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02cd A[LOOP:2: B:108:0x02cb->B:109:0x02cd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x031b A[LOOP:3: B:117:0x0319->B:118:0x031b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x021d  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 812
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iL;
        int iMax;
        int iCombineMeasuredStates;
        int iL2;
        int iM;
        int iCombineMeasuredStates2;
        int iMax2;
        boolean z;
        boolean zA = ViewUtils.a(this);
        int i3 = !zA ? 1 : 0;
        if (v(this.h)) {
            u(this.h, i, 0, i2, this.s);
            iL = l(this.h) + this.h.getMeasuredWidth();
            iMax = Math.max(0, m(this.h) + this.h.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.h.getMeasuredState());
        } else {
            iL = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (v(this.l)) {
            u(this.l, i, 0, i2, this.s);
            iL = l(this.l) + this.l.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.l) + this.l.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.l.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iL) + 0;
        int iMax4 = Math.max(0, currentContentInsetStart - iL);
        int[] iArr = this.f5J;
        iArr[zA ? 1 : 0] = iMax4;
        if (v(this.e)) {
            u(this.e, i, iMax3, i2, this.s);
            iL2 = l(this.e) + this.e.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.e) + this.e.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.e.getMeasuredState());
        } else {
            iL2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, iL2);
        iArr[i3] = Math.max(0, currentContentInsetEnd - iL2);
        if (v(this.m)) {
            iMax5 += t(this.m, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.m) + this.m.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.m.getMeasuredState());
        }
        if (v(this.i)) {
            iMax5 += t(this.i, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.i) + this.i.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.i.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (((LayoutParams) childAt.getLayoutParams()).b == 0 && v(childAt)) {
                iMax5 += t(childAt, i, iMax5, i2, 0, iArr);
                iMax = Math.max(iMax, m(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i5 = this.v + this.w;
        int i6 = this.t + this.u;
        if (v(this.f)) {
            t(this.f, i, iMax5 + i6, i2, i5, iArr);
            int iL3 = l(this.f) + this.f.getMeasuredWidth();
            iM = m(this.f) + this.f.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f.getMeasuredState());
            iMax2 = iL3;
        } else {
            iM = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (v(this.g)) {
            iMax2 = Math.max(iMax2, t(this.g, i, iMax5 + i6, i2, iM + i5, iArr));
            iM += m(this.g) + this.g.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.g.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, iM);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (!this.T) {
            z = false;
            break;
        }
        int childCount2 = getChildCount();
        for (int i7 = 0; i7 < childCount2; i7++) {
            View childAt2 = getChildAt(i7);
            if (v(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                z = false;
                break;
            }
        }
        z = true;
        setMeasuredDimension(iResolveSizeAndState, z ? 0 : iResolveSizeAndState2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        ActionMenuView actionMenuView = this.e;
        MenuBuilder menuBuilder = actionMenuView != null ? actionMenuView.t : null;
        int i = savedState.g;
        if (i != 0 && this.Q != null && menuBuilder != null && (menuItemFindItem = menuBuilder.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.h) {
            Runnable runnable = this.a0;
            removeCallbacks(runnable);
            post(runnable);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.x == null) {
            this.x = new RtlSpacingHelper();
        }
        RtlSpacingHelper rtlSpacingHelper = this.x;
        boolean z = i == 1;
        if (z == rtlSpacingHelper.g) {
            return;
        }
        rtlSpacingHelper.g = z;
        if (!rtlSpacingHelper.h) {
            rtlSpacingHelper.a = rtlSpacingHelper.e;
            rtlSpacingHelper.b = rtlSpacingHelper.f;
            return;
        }
        if (z) {
            int i2 = rtlSpacingHelper.d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = rtlSpacingHelper.e;
            }
            rtlSpacingHelper.a = i2;
            int i3 = rtlSpacingHelper.c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = rtlSpacingHelper.f;
            }
            rtlSpacingHelper.b = i3;
            return;
        }
        int i4 = rtlSpacingHelper.c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = rtlSpacingHelper.e;
        }
        rtlSpacingHelper.a = i4;
        int i5 = rtlSpacingHelper.d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = rtlSpacingHelper.f;
        }
        rtlSpacingHelper.b = i5;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        MenuItemImpl menuItemImpl;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter = this.Q;
        if (expandedActionViewMenuPresenter != null && (menuItemImpl = expandedActionViewMenuPresenter.f) != null) {
            savedState.g = menuItemImpl.a;
        }
        ActionMenuView actionMenuView = this.e;
        boolean z = false;
        if (actionMenuView != null) {
            ActionMenuPresenter actionMenuPresenter = actionMenuView.x;
            if (actionMenuPresenter != null && actionMenuPresenter.p()) {
                z = true;
            }
        }
        savedState.h = z;
        return savedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.F = false;
        }
        if (!this.F) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.F = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.F = false;
        }
        return true;
    }

    public final void p() {
        Iterator<MenuItem> it = this.L.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(it.next().getItemId());
        }
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        MenuInflater menuInflater = getMenuInflater();
        Iterator<kf1> it2 = this.K.b.iterator();
        while (it2.hasNext()) {
            it2.next().c(menu, menuInflater);
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.L = currentMenuItems2;
    }

    public final boolean q(View view) {
        return view.getParent() == this || this.I.contains(view);
    }

    public final int r(View view, int i, int i2, int[] iArr) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - iArr[0];
        int iMax = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iK, iMax + measuredWidth, view.getMeasuredHeight() + iK);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + iMax;
    }

    public final int s(View view, int i, int i2, int[] iArr) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iK, iMax, view.getMeasuredHeight() + iK);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.W != z) {
            this.W = z;
            w();
        }
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(AppCompatResources.a(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.T = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.z) {
            this.z = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.y) {
            this.y = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(AppCompatResources.a(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(AppCompatResources.a(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        i();
        this.h.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.M = onMenuItemClickListener;
    }

    public void setOverflowIcon(Drawable drawable) {
        g();
        this.e.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.o != i) {
            this.o = i;
            if (i == 0) {
                this.n = getContext();
            } else {
                this.n = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.w = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.u = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.t = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.v = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public final int t(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void u(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + 0, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final boolean v(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void w() {
        /*
            r4 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r0 < r1) goto L55
            android.window.OnBackInvokedDispatcher r0 = androidx.appcompat.widget.Toolbar.Api33Impl.a(r4)
            androidx.appcompat.widget.Toolbar$ExpandedActionViewMenuPresenter r1 = r4.Q
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L16
            androidx.appcompat.view.menu.MenuItemImpl r1 = r1.f
            if (r1 == 0) goto L16
            r1 = r2
            goto L17
        L16:
            r1 = r3
        L17:
            if (r1 == 0) goto L28
            if (r0 == 0) goto L28
            java.util.WeakHashMap<android.view.View, in3> r1 = defpackage.om3.a
            boolean r1 = om3.g.b(r4)
            if (r1 == 0) goto L28
            boolean r1 = r4.W
            if (r1 == 0) goto L28
            goto L29
        L28:
            r2 = r3
        L29:
            if (r2 == 0) goto L47
            android.window.OnBackInvokedDispatcher r1 = r4.V
            if (r1 != 0) goto L47
            pb3 r1 = r4.U
            if (r1 != 0) goto L3f
            androidx.appcompat.widget.a r1 = new androidx.appcompat.widget.a
            r1.<init>(r3, r4)
            pb3 r2 = new pb3
            r2.<init>(r1)
            r4.U = r2
        L3f:
            pb3 r1 = r4.U
            androidx.appcompat.widget.Toolbar.Api33Impl.b(r0, r1)
            r4.V = r0
            goto L55
        L47:
            if (r2 != 0) goto L55
            android.window.OnBackInvokedDispatcher r0 = r4.V
            if (r0 == 0) goto L55
            pb3 r1 = r4.U
            androidx.appcompat.widget.Toolbar.Api33Impl.c(r0, r1)
            r0 = 0
            r4.V = r0
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.w():void");
    }

    public static class LayoutParams extends ActionBar.LayoutParams {
        public int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        public LayoutParams() {
            this.b = 0;
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.b = 0;
            this.b = layoutParams.b;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = 0;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        AppCompatImageButton appCompatImageButton = this.l;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.l.setImageDrawable(drawable);
        } else {
            AppCompatImageButton appCompatImageButton = this.l;
            if (appCompatImageButton != null) {
                appCompatImageButton.setImageDrawable(this.j);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.i == null) {
                this.i = new AppCompatImageView(getContext());
            }
            if (!q(this.i)) {
                b(this.i, true);
            }
        } else {
            AppCompatImageView appCompatImageView = this.i;
            if (appCompatImageView != null && q(appCompatImageView)) {
                removeView(this.i);
                this.I.remove(this.i);
            }
        }
        AppCompatImageView appCompatImageView2 = this.i;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.i == null) {
            this.i = new AppCompatImageView(getContext());
        }
        AppCompatImageView appCompatImageView = this.i;
        if (appCompatImageView != null) {
            appCompatImageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        AppCompatImageButton appCompatImageButton = this.h;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
            this.h.setTooltipText(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!q(this.h)) {
                b(this.h, true);
            }
        } else {
            AppCompatImageButton appCompatImageButton = this.h;
            if (appCompatImageButton != null && q(appCompatImageButton)) {
                removeView(this.h);
                this.I.remove(this.h);
            }
        }
        AppCompatImageButton appCompatImageButton2 = this.h;
        if (appCompatImageButton2 != null) {
            appCompatImageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.g;
            if (appCompatTextView != null && q(appCompatTextView)) {
                removeView(this.g);
                this.I.remove(this.g);
            }
        } else {
            if (this.g == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.g = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.g.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.q;
                if (i != 0) {
                    this.g.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.E;
                if (colorStateList != null) {
                    this.g.setTextColor(colorStateList);
                }
            }
            if (!q(this.g)) {
                b(this.g, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.g;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.C = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.E = colorStateList;
        AppCompatTextView appCompatTextView = this.g;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.f;
            if (appCompatTextView != null && q(appCompatTextView)) {
                removeView(this.f);
                this.I.remove(this.f);
            }
        } else {
            if (this.f == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.f = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.f.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.p;
                if (i != 0) {
                    this.f.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.D;
                if (colorStateList != null) {
                    this.f.setTextColor(colorStateList);
                }
            }
            if (!q(this.f)) {
                b(this.f, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.f;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.B = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.D = colorStateList;
        AppCompatTextView appCompatTextView = this.f;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.appcompat.widget.Toolbar.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public int g;
        public boolean h;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readInt();
            this.h = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A = 8388627;
        this.H = new ArrayList<>();
        this.I = new ArrayList<>();
        this.f5J = new int[2];
        final int i2 = 1;
        this.K = new hf1(new Runnable() { // from class: androidx.appcompat.widget.b
            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                Object obj = this;
                switch (i3) {
                    case 0:
                        ((TooltipCompatHandler) obj).a();
                        throw null;
                    default:
                        ((Toolbar) obj).p();
                        return;
                }
            }
        });
        this.L = new ArrayList<>();
        this.N = new ActionMenuView.OnMenuItemClickListener() { // from class: androidx.appcompat.widget.Toolbar.1
            @Override // androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean z;
                Toolbar toolbar = Toolbar.this;
                Iterator<kf1> it = toolbar.K.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (it.next().a(menuItem)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    return true;
                }
                OnMenuItemClickListener onMenuItemClickListener = toolbar.M;
                if (onMenuItemClickListener != null) {
                    return onMenuItemClickListener.onMenuItemClick(menuItem);
                }
                return false;
            }
        };
        this.a0 = new Runnable() { // from class: androidx.appcompat.widget.Toolbar.2
            @Override // java.lang.Runnable
            public final void run() {
                ActionMenuPresenter actionMenuPresenter;
                ActionMenuView actionMenuView = Toolbar.this.e;
                if (actionMenuView == null || (actionMenuPresenter = actionMenuView.x) == null) {
                    return;
                }
                actionMenuPresenter.r();
            }
        };
        Context context2 = getContext();
        int[] iArr = R.styleable.Toolbar;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context2, attributeSet, iArr, i);
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArray, i, 0);
        this.p = tintTypedArrayM.i(R.styleable.Toolbar_titleTextAppearance, 0);
        this.q = tintTypedArrayM.i(R.styleable.Toolbar_subtitleTextAppearance, 0);
        int i3 = R.styleable.Toolbar_android_gravity;
        TypedArray typedArray2 = tintTypedArrayM.b;
        this.A = typedArray2.getInteger(i3, 8388627);
        this.r = typedArray2.getInteger(R.styleable.Toolbar_buttonGravity, 48);
        int iC = tintTypedArrayM.c(R.styleable.Toolbar_titleMargin, 0);
        int i4 = R.styleable.Toolbar_titleMargins;
        iC = tintTypedArrayM.l(i4) ? tintTypedArrayM.c(i4, iC) : iC;
        this.w = iC;
        this.v = iC;
        this.u = iC;
        this.t = iC;
        int iC2 = tintTypedArrayM.c(R.styleable.Toolbar_titleMarginStart, -1);
        if (iC2 >= 0) {
            this.t = iC2;
        }
        int iC3 = tintTypedArrayM.c(R.styleable.Toolbar_titleMarginEnd, -1);
        if (iC3 >= 0) {
            this.u = iC3;
        }
        int iC4 = tintTypedArrayM.c(R.styleable.Toolbar_titleMarginTop, -1);
        if (iC4 >= 0) {
            this.v = iC4;
        }
        int iC5 = tintTypedArrayM.c(R.styleable.Toolbar_titleMarginBottom, -1);
        if (iC5 >= 0) {
            this.w = iC5;
        }
        this.s = tintTypedArrayM.d(R.styleable.Toolbar_maxButtonHeight, -1);
        int iC6 = tintTypedArrayM.c(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iC7 = tintTypedArrayM.c(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iD = tintTypedArrayM.d(R.styleable.Toolbar_contentInsetLeft, 0);
        int iD2 = tintTypedArrayM.d(R.styleable.Toolbar_contentInsetRight, 0);
        if (this.x == null) {
            this.x = new RtlSpacingHelper();
        }
        RtlSpacingHelper rtlSpacingHelper = this.x;
        rtlSpacingHelper.h = false;
        if (iD != Integer.MIN_VALUE) {
            rtlSpacingHelper.e = iD;
            rtlSpacingHelper.a = iD;
        }
        if (iD2 != Integer.MIN_VALUE) {
            rtlSpacingHelper.f = iD2;
            rtlSpacingHelper.b = iD2;
        }
        if (iC6 != Integer.MIN_VALUE || iC7 != Integer.MIN_VALUE) {
            rtlSpacingHelper.a(iC6, iC7);
        }
        this.y = tintTypedArrayM.c(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.z = tintTypedArrayM.c(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.j = tintTypedArrayM.e(R.styleable.Toolbar_collapseIcon);
        this.k = tintTypedArrayM.k(R.styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceK = tintTypedArrayM.k(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceK)) {
            setTitle(charSequenceK);
        }
        CharSequence charSequenceK2 = tintTypedArrayM.k(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceK2)) {
            setSubtitle(charSequenceK2);
        }
        this.n = getContext();
        setPopupTheme(tintTypedArrayM.i(R.styleable.Toolbar_popupTheme, 0));
        Drawable drawableE = tintTypedArrayM.e(R.styleable.Toolbar_navigationIcon);
        if (drawableE != null) {
            setNavigationIcon(drawableE);
        }
        CharSequence charSequenceK3 = tintTypedArrayM.k(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceK3)) {
            setNavigationContentDescription(charSequenceK3);
        }
        Drawable drawableE2 = tintTypedArrayM.e(R.styleable.Toolbar_logo);
        if (drawableE2 != null) {
            setLogo(drawableE2);
        }
        CharSequence charSequenceK4 = tintTypedArrayM.k(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceK4)) {
            setLogoDescription(charSequenceK4);
        }
        int i5 = R.styleable.Toolbar_titleTextColor;
        if (tintTypedArrayM.l(i5)) {
            setTitleTextColor(tintTypedArrayM.b(i5));
        }
        int i6 = R.styleable.Toolbar_subtitleTextColor;
        if (tintTypedArrayM.l(i6)) {
            setSubtitleTextColor(tintTypedArrayM.b(i6));
        }
        int i7 = R.styleable.Toolbar_menu;
        if (tintTypedArrayM.l(i7)) {
            o(tintTypedArrayM.i(i7, 0));
        }
        tintTypedArrayM.n();
    }

    public class ExpandedActionViewMenuPresenter implements MenuPresenter {
        public MenuBuilder e;
        public MenuItemImpl f;

        public ExpandedActionViewMenuPresenter() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final void d(boolean z) {
            if (this.f != null) {
                MenuBuilder menuBuilder = this.e;
                boolean z2 = false;
                if (menuBuilder != null) {
                    int size = menuBuilder.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.e.getItem(i) == this.f) {
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z2) {
                    return;
                }
                g(this.f);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final boolean e() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final boolean g(MenuItemImpl menuItemImpl) {
            Toolbar toolbar = Toolbar.this;
            KeyEvent.Callback callback = toolbar.m;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewCollapsed();
            }
            toolbar.removeView(toolbar.m);
            toolbar.removeView(toolbar.l);
            toolbar.m = null;
            ArrayList<View> arrayList = toolbar.I;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    arrayList.clear();
                    this.f = null;
                    toolbar.requestLayout();
                    menuItemImpl.C = false;
                    menuItemImpl.n.p(false);
                    toolbar.w();
                    return true;
                }
                toolbar.addView(arrayList.get(size));
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final int getId() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final void h(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl menuItemImpl;
            MenuBuilder menuBuilder2 = this.e;
            if (menuBuilder2 != null && (menuItemImpl = this.f) != null) {
                menuBuilder2.d(menuItemImpl);
            }
            this.e = menuBuilder;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final boolean k(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final Parcelable l() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final boolean m(MenuItemImpl menuItemImpl) {
            Toolbar toolbar = Toolbar.this;
            toolbar.c();
            ViewParent parent = toolbar.l.getParent();
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.l);
                }
                toolbar.addView(toolbar.l);
            }
            View actionView = menuItemImpl.getActionView();
            toolbar.m = actionView;
            this.f = menuItemImpl;
            ViewParent parent2 = actionView.getParent();
            if (parent2 != toolbar) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar.m);
                }
                LayoutParams layoutParams = new LayoutParams();
                layoutParams.a = (toolbar.r & 112) | 8388611;
                layoutParams.b = 2;
                toolbar.m.setLayoutParams(layoutParams);
                toolbar.addView(toolbar.m);
            }
            int childCount = toolbar.getChildCount();
            while (true) {
                childCount--;
                if (childCount < 0) {
                    break;
                }
                View childAt = toolbar.getChildAt(childCount);
                if (((LayoutParams) childAt.getLayoutParams()).b != 2 && childAt != toolbar.e) {
                    toolbar.removeViewAt(childCount);
                    toolbar.I.add(childAt);
                }
            }
            toolbar.requestLayout();
            menuItemImpl.C = true;
            menuItemImpl.n.p(false);
            KeyEvent.Callback callback = toolbar.m;
            if (callback instanceof CollapsibleActionView) {
                ((CollapsibleActionView) callback).onActionViewExpanded();
            }
            toolbar.w();
            return true;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final void i(Parcelable parcelable) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public final void c(MenuBuilder menuBuilder, boolean z) {
        }
    }
}
