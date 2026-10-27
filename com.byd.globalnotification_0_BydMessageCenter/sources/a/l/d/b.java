package a.l.d;

import a.l.d.z;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<String> f935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f936c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f937d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f938e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f939f;
    public final int g;
    public final int h;
    public final CharSequence i;
    public final int j;
    public final CharSequence k;
    public final ArrayList<String> l;
    public final ArrayList<String> m;
    public final boolean n;

    public static class a implements Parcelable.Creator<b> {
        @Override // android.os.Parcelable.Creator
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(a.l.d.a aVar) {
        int size = aVar.f1061a.size();
        this.f934a = new int[size * 5];
        if (!aVar.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f935b = new ArrayList<>(size);
        this.f936c = new int[size];
        this.f937d = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            z.a aVar2 = aVar.f1061a.get(i);
            int i3 = i2 + 1;
            this.f934a[i2] = aVar2.f1067a;
            ArrayList<String> arrayList = this.f935b;
            Fragment fragment = aVar2.f1068b;
            arrayList.add(fragment != null ? fragment.f1557e : null);
            int[] iArr = this.f934a;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1069c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1070d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1071e;
            iArr[i6] = aVar2.f1072f;
            this.f936c[i] = aVar2.g.ordinal();
            this.f937d[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f938e = aVar.f1066f;
        this.f939f = aVar.h;
        this.g = aVar.r;
        this.h = aVar.i;
        this.i = aVar.j;
        this.j = aVar.k;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.n;
        this.n = aVar.o;
    }

    public b(Parcel parcel) {
        this.f934a = parcel.createIntArray();
        this.f935b = parcel.createStringArrayList();
        this.f936c = parcel.createIntArray();
        this.f937d = parcel.createIntArray();
        this.f938e = parcel.readInt();
        this.f939f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.createStringArrayList();
        this.m = parcel.createStringArrayList();
        this.n = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f934a);
        parcel.writeStringList(this.f935b);
        parcel.writeIntArray(this.f936c);
        parcel.writeIntArray(this.f937d);
        parcel.writeInt(this.f938e);
        parcel.writeString(this.f939f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeStringList(this.l);
        parcel.writeStringList(this.m);
        parcel.writeInt(this.n ? 1 : 0);
    }
}
