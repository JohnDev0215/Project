package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;
import n.g;
import n.i;
import n.m;
import n.r;
import u1.a;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationPresenter implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f2073b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BottomNavigationMenuView f2074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2075d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2076e;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2077b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ParcelableSparseArray f2078c;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeInt(this.f2077b);
            parcel.writeParcelable(this.f2078c, 0);
        }

        public SavedState(Parcel parcel) {
            this.f2077b = parcel.readInt();
            this.f2078c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // n.m
    public void a(g gVar, boolean z3) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f2074c = bottomNavigationMenuView;
    }

    @Override // n.m
    public int c() {
        return this.f2076e;
    }

    @Override // n.m
    public boolean d() {
        return false;
    }

    @Override // n.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f2077b = this.f2074c.getSelectedItemId();
        savedState.f2078c = a.c(this.f2074c.getBadgeDrawables());
        return savedState;
    }

    @Override // n.m
    public void f(Context context, g gVar) {
        this.f2073b = gVar;
        this.f2074c.b(gVar);
    }

    public void g(int i3) {
        this.f2076e = i3;
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f2074c.j(savedState.f2077b);
            this.f2074c.setBadgeDrawables(a.b(this.f2074c.getContext(), savedState.f2078c));
        }
    }

    @Override // n.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // n.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    public void l(boolean z3) {
        this.f2075d = z3;
    }

    @Override // n.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // n.m
    public void n(boolean z3) {
        if (this.f2075d) {
            return;
        }
        if (z3) {
            this.f2074c.d();
        } else {
            this.f2074c.k();
        }
    }
}
