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
    private g f4067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BottomNavigationMenuView f4068c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4069d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4070e;

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4071b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ParcelableSparseArray f4072c;

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
            parcel.writeInt(this.f4071b);
            parcel.writeParcelable(this.f4072c, 0);
        }

        SavedState(Parcel parcel) {
            this.f4071b = parcel.readInt();
            this.f4072c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f4068c = bottomNavigationMenuView;
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f4070e;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f4071b = this.f4068c.getSelectedItemId();
        savedState.f4072c = com.google.android.material.badge.a.c(this.f4068c.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        this.f4067b = gVar;
        this.f4068c.b(gVar);
    }

    public void g(int i) {
        this.f4070e = i;
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f4068c.j(savedState.f4071b);
            this.f4068c.setBadgeDrawables(com.google.android.material.badge.a.b(this.f4068c.getContext(), savedState.f4072c));
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
        this.f4069d = z;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        if (this.f4069d) {
            return;
        }
        if (z) {
            this.f4068c.d();
        } else {
            this.f4068c.k();
        }
    }
}
