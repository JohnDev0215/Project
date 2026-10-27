package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopup;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.a {
    public ActionMenuPopupCallback A;
    public final PopupPresenterCallback B;
    public int C;
    public OverflowMenuButton n;
    public Drawable o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public final SparseBooleanArray w;
    public OverflowPopup x;
    public ActionButtonSubmenu y;
    public OpenOverflowRunnable z;

    public class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, R.attr.actionOverflowMenuStyle);
            if (!((((MenuItemImpl) subMenuBuilder.getItem()).x & 32) == 32)) {
                View view2 = ActionMenuPresenter.this.n;
                this.f = view2 == null ? (View) ActionMenuPresenter.this.l : view2;
            }
            PopupPresenterCallback popupPresenterCallback = ActionMenuPresenter.this.B;
            this.i = popupPresenterCallback;
            MenuPopup menuPopup = this.j;
            if (menuPopup != null) {
                menuPopup.f(popupPresenterCallback);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public final void c() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.y = null;
            actionMenuPresenter.C = 0;
            super.c();
        }
    }

    public class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        public ActionMenuPopupCallback() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.PopupCallback
        public final ShowableListMenu a() {
            ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.y;
            if (actionButtonSubmenu != null) {
                return actionButtonSubmenu.a();
            }
            return null;
        }
    }

    public class OpenOverflowRunnable implements Runnable {
        public final OverflowPopup e;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.e = overflowPopup;
        }

        @Override // java.lang.Runnable
        public final void run() {
            MenuBuilder.Callback callback;
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            MenuBuilder menuBuilder = actionMenuPresenter.g;
            if (menuBuilder != null && (callback = menuBuilder.e) != null) {
                callback.b(menuBuilder);
            }
            View view = (View) actionMenuPresenter.l;
            if (view != null && view.getWindowToken() != null) {
                OverflowPopup overflowPopup = this.e;
                boolean z = true;
                if (!overflowPopup.b()) {
                    if (overflowPopup.f == null) {
                        z = false;
                    } else {
                        overflowPopup.e(0, 0, false, false);
                    }
                }
                if (z) {
                    actionMenuPresenter.x = overflowPopup;
                }
            }
            actionMenuPresenter.z = null;
        }
    }

    public class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        public OverflowMenuButton(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setTooltipText(getContentDescription());
            setOnTouchListener(new ForwardingListener(this) { // from class: androidx.appcompat.widget.ActionMenuPresenter.OverflowMenuButton.1
                @Override // androidx.appcompat.widget.ForwardingListener
                public final ShowableListMenu b() {
                    OverflowPopup overflowPopup = ActionMenuPresenter.this.x;
                    if (overflowPopup == null) {
                        return null;
                    }
                    return overflowPopup.a();
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public final boolean c() {
                    ActionMenuPresenter.this.r();
                    return true;
                }

                @Override // androidx.appcompat.widget.ForwardingListener
                public final boolean d() {
                    ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                    if (actionMenuPresenter.z != null) {
                        return false;
                    }
                    actionMenuPresenter.o();
                    return true;
                }
            });
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public final boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public final boolean b() {
            return false;
        }

        @Override // android.view.View
        public final boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.r();
            return true;
        }

        @Override // android.widget.ImageView
        public final boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                background.setHotspotBounds(paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view) {
            super(context, menuBuilder, view, true, R.attr.actionOverflowMenuStyle);
            this.g = 8388613;
            PopupPresenterCallback popupPresenterCallback = ActionMenuPresenter.this.B;
            this.i = popupPresenterCallback;
            MenuPopup menuPopup = this.j;
            if (menuPopup != null) {
                menuPopup.f(popupPresenterCallback);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public final void c() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            MenuBuilder menuBuilder = actionMenuPresenter.g;
            if (menuBuilder != null) {
                menuBuilder.close();
            }
            actionMenuPresenter.x = null;
            super.c();
        }
    }

    public class PopupPresenterCallback implements MenuPresenter.Callback {
        public PopupPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void c(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.k().c(false);
            }
            MenuPresenter.Callback callback = ActionMenuPresenter.this.i;
            if (callback != null) {
                callback.c(menuBuilder, z);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean d(MenuBuilder menuBuilder) {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            if (menuBuilder == actionMenuPresenter.g) {
                return false;
            }
            actionMenuPresenter.C = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            MenuPresenter.Callback callback = actionMenuPresenter.i;
            if (callback != null) {
                return callback.d(menuBuilder);
            }
            return false;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.appcompat.widget.ActionMenuPresenter.SavedState.1
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int e;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.e);
        }

        public SavedState(Parcel parcel) {
            this.e = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
        this.w = new SparseBooleanArray();
        this.B = new PopupPresenterCallback();
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public final void a(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.c(menuItemImpl);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.l);
        if (this.A == null) {
            this.A = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.A);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public final boolean b(int i, ViewGroup viewGroup) {
        if (viewGroup.getChildAt(i) == this.n) {
            return false;
        }
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z) {
        o();
        ActionButtonSubmenu actionButtonSubmenu = this.y;
        if (actionButtonSubmenu != null && actionButtonSubmenu.b()) {
            actionButtonSubmenu.j.dismiss();
        }
        super.c(menuBuilder, z);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z) {
        ArrayList<MenuItemImpl> arrayList;
        super.d(z);
        ((View) this.l).requestLayout();
        MenuBuilder menuBuilder = this.g;
        boolean z2 = false;
        if (menuBuilder != null) {
            menuBuilder.i();
            ArrayList<MenuItemImpl> arrayList2 = menuBuilder.i;
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                ActionProvider actionProvider = arrayList2.get(i).A;
                if (actionProvider != null) {
                    actionProvider.b = this;
                }
            }
        }
        MenuBuilder menuBuilder2 = this.g;
        if (menuBuilder2 != null) {
            menuBuilder2.i();
            arrayList = menuBuilder2.j;
        } else {
            arrayList = null;
        }
        if (this.q && arrayList != null) {
            int size2 = arrayList.size();
            if (size2 == 1) {
                z2 = !arrayList.get(0).C;
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.n == null) {
                this.n = new OverflowMenuButton(this.e);
            }
            ViewGroup viewGroup = (ViewGroup) this.n.getParent();
            if (viewGroup != this.l) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.n);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.l;
                OverflowMenuButton overflowMenuButton = this.n;
                actionMenuView.getClass();
                ActionMenuView.LayoutParams layoutParams = new ActionMenuView.LayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
                layoutParams.a = true;
                actionMenuView.addView(overflowMenuButton, layoutParams);
            }
        } else {
            OverflowMenuButton overflowMenuButton2 = this.n;
            if (overflowMenuButton2 != null) {
                Object parent = overflowMenuButton2.getParent();
                Object obj = this.l;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.n);
                }
            }
        }
        ((ActionMenuView) this.l).setOverflowReserved(this.q);
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        int size;
        ArrayList<MenuItemImpl> arrayListL;
        int i;
        boolean z;
        ActionMenuPresenter actionMenuPresenter = this;
        MenuBuilder menuBuilder = actionMenuPresenter.g;
        if (menuBuilder != null) {
            arrayListL = menuBuilder.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i2 = actionMenuPresenter.u;
        int i3 = actionMenuPresenter.t;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.l;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            z = true;
            if (i4 >= size) {
                break;
            }
            MenuItemImpl menuItemImpl = arrayListL.get(i4);
            int i7 = menuItemImpl.y;
            if ((i7 & 2) == 2) {
                i5++;
            } else if ((i7 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (actionMenuPresenter.v && menuItemImpl.C) {
                i2 = 0;
            }
            i4++;
        }
        if (actionMenuPresenter.q && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i8 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.w;
        sparseBooleanArray.clear();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            MenuItemImpl menuItemImpl2 = arrayListL.get(i9);
            int i11 = menuItemImpl2.y;
            boolean z3 = (i11 & 2) == i ? z : false;
            int i12 = menuItemImpl2.b;
            if (z3) {
                View viewJ = actionMenuPresenter.j(menuItemImpl2, null, viewGroup);
                viewJ.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewJ.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i10 == 0) {
                    i10 = measuredWidth;
                }
                if (i12 != 0) {
                    sparseBooleanArray.put(i12, z);
                }
                menuItemImpl2.g(z);
            } else if ((i11 & 1) == z ? z : false) {
                boolean z4 = sparseBooleanArray.get(i12);
                boolean z5 = ((i8 > 0 || z4) && i3 > 0) ? z : false;
                if (z5) {
                    View viewJ2 = actionMenuPresenter.j(menuItemImpl2, null, viewGroup);
                    viewJ2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewJ2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i10 == 0) {
                        i10 = measuredWidth2;
                    }
                    z5 &= i3 + i10 > 0;
                }
                if (z5 && i12 != 0) {
                    sparseBooleanArray.put(i12, true);
                } else if (z4) {
                    sparseBooleanArray.put(i12, false);
                    for (int i13 = 0; i13 < i9; i13++) {
                        MenuItemImpl menuItemImpl3 = arrayListL.get(i13);
                        if (menuItemImpl3.b == i12) {
                            if ((menuItemImpl3.x & 32) == 32) {
                                i8++;
                            }
                            menuItemImpl3.g(false);
                        }
                    }
                }
                if (z5) {
                    i8--;
                }
                menuItemImpl2.g(z5);
            } else {
                menuItemImpl2.g(false);
                i9++;
                i = 2;
                actionMenuPresenter = this;
                z = true;
            }
            i9++;
            i = 2;
            actionMenuPresenter = this;
            z = true;
        }
        return z;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public final void h(Context context, MenuBuilder menuBuilder) {
        super.h(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy actionBarPolicy = new ActionBarPolicy(context);
        if (!this.r) {
            this.q = true;
        }
        this.s = context.getResources().getDisplayMetrics().widthPixels / 2;
        this.u = actionBarPolicy.a();
        int measuredWidth = this.s;
        if (this.q) {
            if (this.n == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.e);
                this.n = overflowMenuButton;
                if (this.p) {
                    overflowMenuButton.setImageDrawable(this.o);
                    this.o = null;
                    this.p = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.n.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.n.getMeasuredWidth();
        } else {
            this.n = null;
        }
        this.t = measuredWidth;
        float f = resources.getDisplayMetrics().density;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i = ((SavedState) parcelable).e) > 0 && (menuItemFindItem = this.g.findItem(i)) != null) {
            k((SubMenuBuilder) menuItemFindItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public final View j(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.e()) {
            actionView = super.j(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!((ActionMenuView) viewGroup).checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.j(layoutParams));
        }
        return actionView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public final boolean k(SubMenuBuilder subMenuBuilder) {
        View childAt;
        boolean z;
        boolean z2 = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (true) {
            MenuBuilder menuBuilder = subMenuBuilder2.z;
            if (menuBuilder == this.g) {
                break;
            }
            subMenuBuilder2 = (SubMenuBuilder) menuBuilder;
        }
        MenuItem item = subMenuBuilder2.getItem();
        ViewGroup viewGroup = (ViewGroup) this.l;
        if (viewGroup == null) {
            childAt = 0;
        } else {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == item) {
                    break;
                }
            }
            childAt = 0;
        }
        if (childAt == 0) {
            return false;
        }
        this.C = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z = false;
                break;
            }
            MenuItem item2 = subMenuBuilder.getItem(i2);
            if (item2.isVisible() && item2.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.f, subMenuBuilder, childAt);
        this.y = actionButtonSubmenu;
        actionButtonSubmenu.d(z);
        ActionButtonSubmenu actionButtonSubmenu2 = this.y;
        if (actionButtonSubmenu2.b()) {
            z2 = true;
        } else if (actionButtonSubmenu2.f != null) {
            actionButtonSubmenu2.e(0, 0, false, false);
            z2 = true;
        }
        if (!z2) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
        super.k(subMenuBuilder);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        SavedState savedState = new SavedState();
        savedState.e = this.C;
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public final boolean n(MenuItemImpl menuItemImpl) {
        return (menuItemImpl.x & 32) == 32;
    }

    public final boolean o() {
        Object obj;
        OpenOverflowRunnable openOverflowRunnable = this.z;
        if (openOverflowRunnable != null && (obj = this.l) != null) {
            ((View) obj).removeCallbacks(openOverflowRunnable);
            this.z = null;
            return true;
        }
        OverflowPopup overflowPopup = this.x;
        if (overflowPopup == null) {
            return false;
        }
        if (overflowPopup.b()) {
            overflowPopup.j.dismiss();
        }
        return true;
    }

    public final boolean p() {
        OverflowPopup overflowPopup = this.x;
        return overflowPopup != null && overflowPopup.b();
    }

    public final void q(boolean z) {
        if (z) {
            super.k(null);
            return;
        }
        MenuBuilder menuBuilder = this.g;
        if (menuBuilder != null) {
            menuBuilder.c(false);
        }
    }

    public final boolean r() {
        MenuBuilder menuBuilder;
        if (!this.q || p() || (menuBuilder = this.g) == null || this.l == null || this.z != null) {
            return false;
        }
        menuBuilder.i();
        if (menuBuilder.j.isEmpty()) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.f, this.g, this.n));
        this.z = openOverflowRunnable;
        ((View) this.l).post(openOverflowRunnable);
        return true;
    }
}
