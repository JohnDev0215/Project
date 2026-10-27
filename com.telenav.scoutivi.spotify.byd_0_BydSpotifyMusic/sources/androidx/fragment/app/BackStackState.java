package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.r;
import androidx.lifecycle.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int[] f2090e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final ArrayList<String> f2091g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final int[] f2092h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int[] f2093i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final int f2094j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final String f2095k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final int f2096l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final int f2097m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final CharSequence f2098n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final int f2099o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    final CharSequence f2100p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    final ArrayList<String> f2101q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    final ArrayList<String> f2102r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    final boolean f2103s;

    class a implements Parcelable.Creator<BackStackState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i7) {
            return new BackStackState[i7];
        }
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.f2368c.size();
        this.f2090e = new int[size * 5];
        if (!aVar.f2374i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f2091g = new ArrayList<>(size);
        this.f2092h = new int[size];
        this.f2093i = new int[size];
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            r.a aVar2 = aVar.f2368c.get(i7);
            int i9 = i8 + 1;
            this.f2090e[i8] = aVar2.f2385a;
            ArrayList<String> arrayList = this.f2091g;
            Fragment fragment = aVar2.f2386b;
            arrayList.add(fragment != null ? fragment.f2115k : null);
            int[] iArr = this.f2090e;
            int i10 = i9 + 1;
            iArr[i9] = aVar2.f2387c;
            int i11 = i10 + 1;
            iArr[i10] = aVar2.f2388d;
            int i12 = i11 + 1;
            iArr[i11] = aVar2.f2389e;
            iArr[i12] = aVar2.f2390f;
            this.f2092h[i7] = aVar2.f2391g.ordinal();
            this.f2093i[i7] = aVar2.f2392h.ordinal();
            i7++;
            i8 = i12 + 1;
        }
        this.f2094j = aVar.f2373h;
        this.f2095k = aVar.f2376k;
        this.f2096l = aVar.f2242v;
        this.f2097m = aVar.f2377l;
        this.f2098n = aVar.f2378m;
        this.f2099o = aVar.f2379n;
        this.f2100p = aVar.f2380o;
        this.f2101q = aVar.f2381p;
        this.f2102r = aVar.f2382q;
        this.f2103s = aVar.f2383r;
    }

    public androidx.fragment.app.a b(FragmentManager fragmentManager) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
        int i7 = 0;
        int i8 = 0;
        while (i7 < this.f2090e.length) {
            r.a aVar2 = new r.a();
            int i9 = i7 + 1;
            aVar2.f2385a = this.f2090e[i7];
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i8 + " base fragment #" + this.f2090e[i9]);
            }
            String str = this.f2091g.get(i8);
            if (str != null) {
                aVar2.f2386b = fragmentManager.f0(str);
            } else {
                aVar2.f2386b = null;
            }
            aVar2.f2391g = i.c.values()[this.f2092h[i8]];
            aVar2.f2392h = i.c.values()[this.f2093i[i8]];
            int[] iArr = this.f2090e;
            int i10 = i9 + 1;
            int i11 = iArr[i9];
            aVar2.f2387c = i11;
            int i12 = i10 + 1;
            int i13 = iArr[i10];
            aVar2.f2388d = i13;
            int i14 = i12 + 1;
            int i15 = iArr[i12];
            aVar2.f2389e = i15;
            int i16 = iArr[i14];
            aVar2.f2390f = i16;
            aVar.f2369d = i11;
            aVar.f2370e = i13;
            aVar.f2371f = i15;
            aVar.f2372g = i16;
            aVar.f(aVar2);
            i8++;
            i7 = i14 + 1;
        }
        aVar.f2373h = this.f2094j;
        aVar.f2376k = this.f2095k;
        aVar.f2242v = this.f2096l;
        aVar.f2374i = true;
        aVar.f2377l = this.f2097m;
        aVar.f2378m = this.f2098n;
        aVar.f2379n = this.f2099o;
        aVar.f2380o = this.f2100p;
        aVar.f2381p = this.f2101q;
        aVar.f2382q = this.f2102r;
        aVar.f2383r = this.f2103s;
        aVar.x(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeIntArray(this.f2090e);
        parcel.writeStringList(this.f2091g);
        parcel.writeIntArray(this.f2092h);
        parcel.writeIntArray(this.f2093i);
        parcel.writeInt(this.f2094j);
        parcel.writeString(this.f2095k);
        parcel.writeInt(this.f2096l);
        parcel.writeInt(this.f2097m);
        TextUtils.writeToParcel(this.f2098n, parcel, 0);
        parcel.writeInt(this.f2099o);
        TextUtils.writeToParcel(this.f2100p, parcel, 0);
        parcel.writeStringList(this.f2101q);
        parcel.writeStringList(this.f2102r);
        parcel.writeInt(this.f2103s ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f2090e = parcel.createIntArray();
        this.f2091g = parcel.createStringArrayList();
        this.f2092h = parcel.createIntArray();
        this.f2093i = parcel.createIntArray();
        this.f2094j = parcel.readInt();
        this.f2095k = parcel.readString();
        this.f2096l = parcel.readInt();
        this.f2097m = parcel.readInt();
        this.f2098n = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f2099o = parcel.readInt();
        this.f2100p = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f2101q = parcel.createStringArrayList();
        this.f2102r = parcel.createStringArrayList();
        this.f2103s = parcel.readInt() != 0;
    }
}
