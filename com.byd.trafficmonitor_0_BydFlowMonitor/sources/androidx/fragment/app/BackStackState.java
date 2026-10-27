package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.l.d.p;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<String> f866c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f867d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f869f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f870g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f871h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f872i;
    public final CharSequence j;
    public final int k;
    public final CharSequence l;
    public final ArrayList<String> m;
    public final ArrayList<String> n;
    public final boolean o;

    public class a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    }

    public BackStackState(b.l.d.a aVar) {
        int size = aVar.f2844c.size();
        this.f865b = new int[size * 5];
        if (!aVar.f2850i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f866c = new ArrayList<>(size);
        this.f867d = new int[size];
        this.f868e = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            p.a aVar2 = aVar.f2844c.get(i2);
            int i4 = i3 + 1;
            this.f865b[i3] = aVar2.f2851a;
            ArrayList<String> arrayList = this.f866c;
            Fragment fragment = aVar2.f2852b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f865b;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f2853c;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f2854d;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.f2855e;
            iArr[i7] = aVar2.f2856f;
            this.f867d[i2] = aVar2.f2857g.ordinal();
            this.f868e[i2] = aVar2.f2858h.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.f869f = aVar.f2849h;
        this.f870g = aVar.k;
        this.f871h = aVar.v;
        this.f872i = aVar.l;
        this.j = aVar.m;
        this.k = aVar.n;
        this.l = aVar.o;
        this.m = aVar.p;
        this.n = aVar.q;
        this.o = aVar.r;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public b.l.d.a j(FragmentManager fragmentManager) {
        b.l.d.a aVar = new b.l.d.a(fragmentManager);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f865b.length) {
            p.a aVar2 = new p.a();
            int i4 = i2 + 1;
            aVar2.f2851a = this.f865b[i2];
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i3 + " base fragment #" + this.f865b[i4]);
            }
            String str = this.f866c.get(i3);
            if (str != null) {
                aVar2.f2852b = fragmentManager.h0(str);
            } else {
                aVar2.f2852b = null;
            }
            aVar2.f2857g = Lifecycle.State.values()[this.f867d[i3]];
            aVar2.f2858h = Lifecycle.State.values()[this.f868e[i3]];
            int[] iArr = this.f865b;
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            aVar2.f2853c = i6;
            int i7 = i5 + 1;
            int i8 = iArr[i5];
            aVar2.f2854d = i8;
            int i9 = i7 + 1;
            int i10 = iArr[i7];
            aVar2.f2855e = i10;
            int i11 = iArr[i9];
            aVar2.f2856f = i11;
            aVar.f2845d = i6;
            aVar.f2846e = i8;
            aVar.f2847f = i10;
            aVar.f2848g = i11;
            aVar.e(aVar2);
            i3++;
            i2 = i9 + 1;
        }
        aVar.f2849h = this.f869f;
        aVar.k = this.f870g;
        aVar.v = this.f871h;
        aVar.f2850i = true;
        aVar.l = this.f872i;
        aVar.m = this.j;
        aVar.n = this.k;
        aVar.o = this.l;
        aVar.p = this.m;
        aVar.q = this.n;
        aVar.r = this.o;
        aVar.u(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f865b);
        parcel.writeStringList(this.f866c);
        parcel.writeIntArray(this.f867d);
        parcel.writeIntArray(this.f868e);
        parcel.writeInt(this.f869f);
        parcel.writeString(this.f870g);
        parcel.writeInt(this.f871h);
        parcel.writeInt(this.f872i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f865b = parcel.createIntArray();
        this.f866c = parcel.createStringArrayList();
        this.f867d = parcel.createIntArray();
        this.f868e = parcel.createIntArray();
        this.f869f = parcel.readInt();
        this.f870g = parcel.readString();
        this.f871h = parcel.readInt();
        this.f872i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
