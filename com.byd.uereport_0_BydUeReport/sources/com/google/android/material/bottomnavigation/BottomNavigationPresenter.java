package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;
import l5.a;
import q.g;
import q.i;
import q.m;
import q.r;

/* JADX INFO: loaded from: classes2.dex */
public class BottomNavigationPresenter implements m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3656d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BottomNavigationMenuView f3657e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3658f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3659g;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3660d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ParcelableSparseArray f3661e;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f3660d);
            parcel.writeParcelable(this.f3661e, 0);
        }

        public SavedState(Parcel parcel) {
            this.f3660d = parcel.readInt();
            this.f3661e = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // q.m
    public void a(g gVar, boolean z7) {
    }

    @Override // q.m
    public void b(Context context, g gVar) {
        this.f3656d = gVar;
        this.f3657e.b(gVar);
    }

    public void c(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f3657e = bottomNavigationMenuView;
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f3657e.j(savedState.f3660d);
            this.f3657e.setBadgeDrawables(a.b(this.f3657e.getContext(), savedState.f3661e));
        }
    }

    public void e(int i8) {
        this.f3659g = i8;
    }

    @Override // q.m
    public boolean f(r rVar) {
        return false;
    }

    @Override // q.m
    public void g(boolean z7) {
        if (this.f3658f) {
            return;
        }
        if (z7) {
            this.f3657e.d();
        } else {
            this.f3657e.k();
        }
    }

    public void h(boolean z7) {
        this.f3658f = z7;
    }

    @Override // q.m
    public int i() {
        return this.f3659g;
    }

    @Override // q.m
    public boolean j() {
        return false;
    }

    @Override // q.m
    public Parcelable k() {
        SavedState savedState = new SavedState();
        savedState.f3660d = this.f3657e.getSelectedItemId();
        savedState.f3661e = a.c(this.f3657e.getBadgeDrawables());
        return savedState;
    }

    @Override // q.m
    public boolean l(g gVar, i iVar) {
        return false;
    }

    @Override // q.m
    public boolean m(g gVar, i iVar) {
        return false;
    }
}
