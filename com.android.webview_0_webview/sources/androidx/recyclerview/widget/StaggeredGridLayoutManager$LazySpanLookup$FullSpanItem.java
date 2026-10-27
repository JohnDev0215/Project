package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C1144m6;
import defpackage.Z7;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1144m6();
    public int s;
    public int t;
    public int[] u;
    public boolean v;

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(Parcel parcel) {
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.v = parcel.readInt() == 1;
        int i = parcel.readInt();
        if (i > 0) {
            int[] iArr = new int[i];
            this.u = iArr;
            parcel.readIntArray(iArr);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sbF = Z7.f("FullSpanItem{mPosition=");
        sbF.append(this.s);
        sbF.append(", mGapDir=");
        sbF.append(this.t);
        sbF.append(", mHasUnwantedGapAfter=");
        sbF.append(this.v);
        sbF.append(", mGapPerSpan=");
        sbF.append(Arrays.toString(this.u));
        sbF.append('}');
        return sbF.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.v ? 1 : 0);
        int[] iArr = this.u;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.u);
        }
    }
}
