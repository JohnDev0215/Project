package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.transition.AutoTransition;
import androidx.transition.h;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.badge.a;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationBarPresenter implements MenuPresenter {
    public NavigationBarMenuView e;
    public boolean f = false;
    public int g;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int e;
        public ParcelableSparseArray f;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.e = parcel.readInt();
            this.f = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.e);
            parcel.writeParcelable(this.f, 0);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z) {
        AutoTransition autoTransition;
        if (this.f) {
            return;
        }
        if (z) {
            this.e.a();
            return;
        }
        NavigationBarMenuView navigationBarMenuView = this.e;
        MenuBuilder menuBuilder = navigationBarMenuView.F;
        if (menuBuilder == null || navigationBarMenuView.j == null) {
            return;
        }
        int size = menuBuilder.size();
        if (size != navigationBarMenuView.j.length) {
            navigationBarMenuView.a();
            return;
        }
        int i = navigationBarMenuView.k;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = navigationBarMenuView.F.getItem(i2);
            if (item.isChecked()) {
                navigationBarMenuView.k = item.getItemId();
                navigationBarMenuView.l = i2;
            }
        }
        if (i != navigationBarMenuView.k && (autoTransition = navigationBarMenuView.e) != null) {
            h.a(navigationBarMenuView, autoTransition);
        }
        boolean zF = NavigationBarMenuView.f(navigationBarMenuView.i, navigationBarMenuView.F.l().size());
        for (int i3 = 0; i3 < size; i3++) {
            navigationBarMenuView.E.f = true;
            navigationBarMenuView.j[i3].setLabelVisibilityMode(navigationBarMenuView.i);
            navigationBarMenuView.j[i3].setShifting(zF);
            navigationBarMenuView.j[i3].c((MenuItemImpl) navigationBarMenuView.F.getItem(i3));
            navigationBarMenuView.E.f = false;
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean g(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final int getId() {
        return this.g;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void h(Context context, MenuBuilder menuBuilder) {
        this.e.F = menuBuilder;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
        SparseArray<a> sparseArray;
        if (parcelable instanceof SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.e;
            SavedState savedState = (SavedState) parcelable;
            int i = savedState.e;
            int size = navigationBarMenuView.F.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.F.getItem(i2);
                if (i == item.getItemId()) {
                    navigationBarMenuView.k = i;
                    navigationBarMenuView.l = i2;
                    item.setChecked(true);
                    break;
                }
                i2++;
            }
            Context context = this.e.getContext();
            ParcelableSparseArray parcelableSparseArray = savedState.f;
            SparseArray sparseArray2 = new SparseArray(parcelableSparseArray.size());
            for (int i3 = 0; i3 < parcelableSparseArray.size(); i3++) {
                int iKeyAt = parcelableSparseArray.keyAt(i3);
                BadgeState.State state = (BadgeState.State) parcelableSparseArray.valueAt(i3);
                if (state == null) {
                    throw new IllegalArgumentException("BadgeDrawable's savedState cannot be null");
                }
                sparseArray2.put(iKeyAt, new a(context, state));
            }
            NavigationBarMenuView navigationBarMenuView2 = this.e;
            navigationBarMenuView2.getClass();
            int i4 = 0;
            while (true) {
                int size2 = sparseArray2.size();
                sparseArray = navigationBarMenuView2.u;
                if (i4 >= size2) {
                    break;
                }
                int iKeyAt2 = sparseArray2.keyAt(i4);
                if (sparseArray.indexOfKey(iKeyAt2) < 0) {
                    sparseArray.append(iKeyAt2, (a) sparseArray2.get(iKeyAt2));
                }
                i4++;
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.j;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    navigationBarItemView.setBadge(sparseArray.get(navigationBarItemView.getId()));
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean k(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        SavedState savedState = new SavedState();
        savedState.e = this.e.getSelectedItemId();
        SparseArray<a> badgeDrawables = this.e.getBadgeDrawables();
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i = 0; i < badgeDrawables.size(); i++) {
            int iKeyAt = badgeDrawables.keyAt(i);
            a aVarValueAt = badgeDrawables.valueAt(i);
            if (aVarValueAt == null) {
                throw new IllegalArgumentException("badgeDrawable cannot be null");
            }
            parcelableSparseArray.put(iKeyAt, aVarValueAt.i.a);
        }
        savedState.f = parcelableSparseArray;
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean m(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z) {
    }
}
