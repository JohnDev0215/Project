package androidx.swiperefreshlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class SwipeRefreshLayout$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SwipeRefreshLayout$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1232c;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<SwipeRefreshLayout$SavedState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SwipeRefreshLayout$SavedState createFromParcel(Parcel parcel) {
            return new SwipeRefreshLayout$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public SwipeRefreshLayout$SavedState[] newArray(int i) {
            return new SwipeRefreshLayout$SavedState[i];
        }
    }

    SwipeRefreshLayout$SavedState(Parcel parcel) {
        super(parcel);
        this.f1232c = parcel.readByte() != 0;
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.f1232c ? (byte) 1 : (byte) 0);
    }
}
