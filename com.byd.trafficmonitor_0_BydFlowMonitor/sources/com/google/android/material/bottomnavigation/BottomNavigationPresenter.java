package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import b.b.p.j.g;
import b.b.p.j.i;
import b.b.p.j.m;
import b.b.p.j.r;
import c.b.a.a.o.a;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationPresenter implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f4565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BottomNavigationMenuView f4566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4567d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4568e;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4569b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ParcelableSparseArray f4570c;

        public static class a implements Parcelable.Creator<SavedState> {
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

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f4569b);
            parcel.writeParcelable(this.f4570c, 0);
        }

        public SavedState(Parcel parcel) {
            this.f4569b = parcel.readInt();
            this.f4570c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f4566c = bottomNavigationMenuView;
    }

    @Override // b.b.p.j.m
    public int c() {
        return this.f4568e;
    }

    @Override // b.b.p.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f4569b = this.f4566c.getSelectedItemId();
        savedState.f4570c = a.c(this.f4566c.getBadgeDrawables());
        return savedState;
    }

    @Override // b.b.p.j.m
    public void f(Context context, g gVar) {
        this.f4565b = gVar;
        this.f4566c.b(gVar);
    }

    public void g(int i2) {
        this.f4568e = i2;
    }

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f4566c.j(savedState.f4569b);
            this.f4566c.setBadgeDrawables(a.b(this.f4566c.getContext(), savedState.f4570c));
        }
    }

    @Override // b.b.p.j.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    public void l(boolean z) {
        this.f4567d = z;
    }

    @Override // b.b.p.j.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public void n(boolean z) {
        if (this.f4567d) {
            return;
        }
        if (z) {
            this.f4566c.d();
        } else {
            this.f4566c.k();
        }
    }
}
