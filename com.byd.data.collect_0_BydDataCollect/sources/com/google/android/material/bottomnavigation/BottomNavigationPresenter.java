package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.i;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.r;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationPresenter implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f3780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BottomNavigationMenuView f3781c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3782d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3783e;

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3784b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ParcelableSparseArray f3785c;

        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f3784b);
            parcel.writeParcelable(this.f3785c, 0);
        }

        SavedState(Parcel parcel) {
            this.f3784b = parcel.readInt();
            this.f3785c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f3781c = bottomNavigationMenuView;
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f3783e;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f3784b = this.f3781c.getSelectedItemId();
        savedState.f3785c = com.google.android.material.badge.a.c(this.f3781c.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        this.f3780b = gVar;
        this.f3781c.b(gVar);
    }

    public void g(int i2) {
        this.f3783e = i2;
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f3781c.j(savedState.f3784b);
            this.f3781c.setBadgeDrawables(com.google.android.material.badge.a.b(this.f3781c.getContext(), savedState.f3785c));
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    public void l(boolean z) {
        this.f3782d = z;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        if (this.f3782d) {
            return;
        }
        if (z) {
            this.f3781c.d();
        } else {
            this.f3781c.k();
        }
    }
}
