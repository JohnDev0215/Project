package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.internal.ParcelableSparseArray;
import defpackage.a2;
import defpackage.p1;
import defpackage.r1;
import defpackage.rh;
import defpackage.v1;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationPresenter implements v1 {
    public p1 b;
    public BottomNavigationMenuView c;
    public boolean d = false;
    public int e;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int b;
        public ParcelableSparseArray c;

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
            parcel.writeInt(this.b);
            parcel.writeParcelable(this.c, 0);
        }

        public SavedState(Parcel parcel) {
            this.b = parcel.readInt();
            this.c = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
    }

    public void b(BottomNavigationMenuView bottomNavigationMenuView) {
        this.c = bottomNavigationMenuView;
    }

    @Override // defpackage.v1
    public int c() {
        return this.e;
    }

    @Override // defpackage.v1
    public boolean d() {
        return false;
    }

    @Override // defpackage.v1
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.b = this.c.getSelectedItemId();
        savedState.c = rh.c(this.c.getBadgeDrawables());
        return savedState;
    }

    @Override // defpackage.v1
    public void f(Context context, p1 p1Var) {
        this.b = p1Var;
        this.c.b(p1Var);
    }

    public void g(int i) {
        this.e = i;
    }

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.c.j(savedState.b);
            this.c.setBadgeDrawables(rh.b(this.c.getContext(), savedState.c));
        }
    }

    @Override // defpackage.v1
    public boolean i(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public boolean j(p1 p1Var, r1 r1Var) {
        return false;
    }

    public void l(boolean z) {
        this.d = z;
    }

    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        return false;
    }

    @Override // defpackage.v1
    public void n(boolean z) {
        if (this.d) {
            return;
        }
        if (z) {
            this.c.d();
        } else {
            this.c.k();
        }
    }
}
