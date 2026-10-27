package androidx.drawerlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<DrawerLayout$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2085h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f2086i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f2087j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f2088k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f2089l;

    class a implements Parcelable.ClassLoaderCreator<DrawerLayout$SavedState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DrawerLayout$SavedState createFromParcel(Parcel parcel) {
            return new DrawerLayout$SavedState(parcel, null);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public DrawerLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new DrawerLayout$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public DrawerLayout$SavedState[] newArray(int i7) {
            return new DrawerLayout$SavedState[i7];
        }
    }

    public DrawerLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f2085h = 0;
        this.f2085h = parcel.readInt();
        this.f2086i = parcel.readInt();
        this.f2087j = parcel.readInt();
        this.f2088k = parcel.readInt();
        this.f2089l = parcel.readInt();
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeInt(this.f2085h);
        parcel.writeInt(this.f2086i);
        parcel.writeInt(this.f2087j);
        parcel.writeInt(this.f2088k);
        parcel.writeInt(this.f2089l);
    }
}
