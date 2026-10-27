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
    private g f3416b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BottomNavigationMenuView f3417c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3418d = false;
    private int e;

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ParcelableSparseArray f3420c;

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
            parcel.writeInt(this.f3419b);
            parcel.writeParcelable(this.f3420c, 0);
        }

        SavedState(Parcel parcel) {
            this.f3419b = parcel.readInt();
            this.f3420c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
    }

    @Override // androidx.appcompat.view.menu.m
    public void b(Context context, g gVar) {
        this.f3416b = gVar;
        this.f3417c.b(gVar);
    }

    public void c(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f3417c = bottomNavigationMenuView;
    }

    @Override // androidx.appcompat.view.menu.m
    public void d(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f3417c.j(savedState.f3419b);
            this.f3417c.setBadgeDrawables(com.google.android.material.badge.a.b(this.f3417c.getContext(), savedState.f3420c));
        }
    }

    public void e(int i) {
        this.e = i;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean f(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void g(boolean z) {
        if (this.f3418d) {
            return;
        }
        if (z) {
            this.f3417c.d();
        } else {
            this.f3417c.k();
        }
    }

    public void h(boolean z) {
        this.f3418d = z;
    }

    @Override // androidx.appcompat.view.menu.m
    public int i() {
        return this.e;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable k() {
        SavedState savedState = new SavedState();
        savedState.f3419b = this.f3417c.getSelectedItemId();
        savedState.f3420c = com.google.android.material.badge.a.c(this.f3417c.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean l(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(g gVar, i iVar) {
        return false;
    }
}
