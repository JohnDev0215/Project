package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import b.b.e.j.g;
import b.b.e.j.i;
import b.b.e.j.m;
import b.b.e.j.r;
import c.b.a.a.c.a;
import com.google.android.material.internal.ParcelableSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationPresenter implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f2090b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BottomNavigationMenuView f2091c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2092d = false;
    public int e;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2093b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ParcelableSparseArray f2094c;

        public static class a implements Parcelable.Creator<SavedState> {
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

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f2093b);
            parcel.writeParcelable(this.f2094c, 0);
        }

        public SavedState(Parcel parcel) {
            this.f2093b = parcel.readInt();
            this.f2094c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // b.b.e.j.m
    public void a(g gVar, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.f2091c = bottomNavigationMenuView;
    }

    @Override // b.b.e.j.m
    public int c() {
        return this.e;
    }

    @Override // b.b.e.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.e.j.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f2093b = this.f2091c.getSelectedItemId();
        savedState.f2094c = a.c(this.f2091c.getBadgeDrawables());
        return savedState;
    }

    @Override // b.b.e.j.m
    public void f(Context context, g gVar) {
        this.f2090b = gVar;
        this.f2091c.b(gVar);
    }

    public void g(int i) {
        this.e = i;
    }

    @Override // b.b.e.j.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f2091c.j(savedState.f2093b);
            this.f2091c.setBadgeDrawables(a.b(this.f2091c.getContext(), savedState.f2094c));
        }
    }

    @Override // b.b.e.j.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    public void l(boolean z) {
        this.f2092d = z;
    }

    @Override // b.b.e.j.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public void n(boolean z) {
        if (this.f2092d) {
            return;
        }
        if (z) {
            this.f2091c.d();
        } else {
            this.f2091c.k();
        }
    }
}
