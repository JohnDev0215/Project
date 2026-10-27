package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.k;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f1339b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<String> f1340c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f1341d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int[] f1342e;
    final int f;
    final int g;
    final String h;
    final int i;
    final int j;
    final CharSequence k;
    final int l;
    final CharSequence m;
    final ArrayList<String> n;
    final ArrayList<String> o;
    final boolean p;

    static class a implements Parcelable.Creator<BackStackState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.f1413a.size();
        this.f1339b = new int[size * 5];
        if (!aVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1340c = new ArrayList<>(size);
        this.f1341d = new int[size];
        this.f1342e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            k.a aVar2 = aVar.f1413a.get(i);
            int i3 = i2 + 1;
            this.f1339b[i2] = aVar2.f1418a;
            ArrayList<String> arrayList = this.f1340c;
            Fragment fragment = aVar2.f1419b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1339b;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1420c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1421d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1422e;
            iArr[i6] = aVar2.f;
            this.f1341d[i] = aVar2.g.ordinal();
            this.f1342e[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.j;
        this.i = aVar.u;
        this.j = aVar.k;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.n;
        this.n = aVar.o;
        this.o = aVar.p;
        this.p = aVar.q;
    }

    public androidx.fragment.app.a a(h hVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(hVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1339b.length) {
            k.a aVar2 = new k.a();
            int i3 = i + 1;
            aVar2.f1418a = this.f1339b[i];
            if (h.f1378d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f1339b[i3]);
            }
            String str = this.f1340c.get(i2);
            if (str != null) {
                aVar2.f1419b = hVar.k.get(str);
            } else {
                aVar2.f1419b = null;
            }
            aVar2.g = Lifecycle.State.values()[this.f1341d[i2]];
            aVar2.h = Lifecycle.State.values()[this.f1342e[i2]];
            int[] iArr = this.f1339b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f1420c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.f1421d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.f1422e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.f1414b = i5;
            aVar.f1415c = i7;
            aVar.f1416d = i9;
            aVar.f1417e = i10;
            aVar.c(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.f = this.f;
        aVar.g = this.g;
        aVar.j = this.h;
        aVar.u = this.i;
        aVar.h = true;
        aVar.k = this.j;
        aVar.l = this.k;
        aVar.m = this.l;
        aVar.n = this.m;
        aVar.o = this.n;
        aVar.p = this.o;
        aVar.q = this.p;
        aVar.m(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1339b);
        parcel.writeStringList(this.f1340c);
        parcel.writeIntArray(this.f1341d);
        parcel.writeIntArray(this.f1342e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeInt(this.l);
        TextUtils.writeToParcel(this.m, parcel, 0);
        parcel.writeStringList(this.n);
        parcel.writeStringList(this.o);
        parcel.writeInt(this.p ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1339b = parcel.createIntArray();
        this.f1340c = parcel.createStringArrayList();
        this.f1341d = parcel.createIntArray();
        this.f1342e = parcel.createIntArray();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.readInt();
        this.m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.n = parcel.createStringArrayList();
        this.o = parcel.createStringArrayList();
        this.p = parcel.readInt() != 0;
    }
}
