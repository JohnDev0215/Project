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
    private g f3394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BottomNavigationMenuView f3395c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3396d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3397e;

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3398b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ParcelableSparseArray f3399c;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f3398b);
            parcel.writeParcelable(this.f3399c, 0);
        }

        SavedState(Parcel parcel) {
            this.f3398b = parcel.readInt();
            this.f3399c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f3395c = bottomNavigationMenuView;
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f3397e;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f3398b = this.f3395c.getSelectedItemId();
        savedState.f3399c = com.google.android.material.badge.a.c(this.f3395c.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        this.f3394b = gVar;
        this.f3395c.b(gVar);
    }

    public void g(int i) {
        this.f3397e = i;
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f3395c.j(savedState.f3398b);
            this.f3395c.setBadgeDrawables(com.google.android.material.badge.a.b(this.f3395c.getContext(), savedState.f3399c));
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
        this.f3396d = z;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        if (this.f3396d) {
            return;
        }
        if (z) {
            this.f3395c.d();
        } else {
            this.f3395c.k();
        }
    }
}
