package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import r0.h;
import r0.j;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f1275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<String> f1276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f1277d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f1278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1279f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1280g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1281h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1282i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1283j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final CharSequence f1284k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f1285l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f1286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList<String> f1287n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList<String> f1288o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f1289p;

    public static class a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i3) {
            return new BackStackState[i3];
        }
    }

    public BackStackState(r0.a aVar) {
        int size = aVar.f4744a.size();
        this.f1275b = new int[size * 5];
        if (!aVar.f4751h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1276c = new ArrayList<>(size);
        this.f1277d = new int[size];
        this.f1278e = new int[size];
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            j.a aVar2 = aVar.f4744a.get(i3);
            int i5 = i4 + 1;
            this.f1275b[i4] = aVar2.f4762a;
            ArrayList<String> arrayList = this.f1276c;
            Fragment fragment = aVar2.f4763b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1275b;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f4764c;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.f4765d;
            int i8 = i7 + 1;
            iArr[i7] = aVar2.f4766e;
            iArr[i8] = aVar2.f4767f;
            this.f1277d[i3] = aVar2.f4768g.ordinal();
            this.f1278e[i3] = aVar2.f4769h.ordinal();
            i3++;
            i4 = i8 + 1;
        }
        this.f1279f = aVar.f4749f;
        this.f1280g = aVar.f4750g;
        this.f1281h = aVar.f4753j;
        this.f1282i = aVar.f4676u;
        this.f1283j = aVar.f4754k;
        this.f1284k = aVar.f4755l;
        this.f1285l = aVar.f4756m;
        this.f1286m = aVar.f4757n;
        this.f1287n = aVar.f4758o;
        this.f1288o = aVar.f4759p;
        this.f1289p = aVar.f4760q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public r0.a f(h hVar) {
        r0.a aVar = new r0.a(hVar);
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.f1275b.length) {
            j.a aVar2 = new j.a();
            int i5 = i3 + 1;
            aVar2.f4762a = this.f1275b[i3];
            if (h.f4686d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i4 + " base fragment #" + this.f1275b[i5]);
            }
            String str = this.f1276c.get(i4);
            if (str != null) {
                aVar2.f4763b = hVar.f4693k.get(str);
            } else {
                aVar2.f4763b = null;
            }
            aVar2.f4768g = Lifecycle.State.values()[this.f1277d[i4]];
            aVar2.f4769h = Lifecycle.State.values()[this.f1278e[i4]];
            int[] iArr = this.f1275b;
            int i6 = i5 + 1;
            int i7 = iArr[i5];
            aVar2.f4764c = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.f4765d = i9;
            int i10 = i8 + 1;
            int i11 = iArr[i8];
            aVar2.f4766e = i11;
            int i12 = iArr[i10];
            aVar2.f4767f = i12;
            aVar.f4745b = i7;
            aVar.f4746c = i9;
            aVar.f4747d = i11;
            aVar.f4748e = i12;
            aVar.c(aVar2);
            i4++;
            i3 = i10 + 1;
        }
        aVar.f4749f = this.f1279f;
        aVar.f4750g = this.f1280g;
        aVar.f4753j = this.f1281h;
        aVar.f4676u = this.f1282i;
        aVar.f4751h = true;
        aVar.f4754k = this.f1283j;
        aVar.f4755l = this.f1284k;
        aVar.f4756m = this.f1285l;
        aVar.f4757n = this.f1286m;
        aVar.f4758o = this.f1287n;
        aVar.f4759p = this.f1288o;
        aVar.f4760q = this.f1289p;
        aVar.h(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeIntArray(this.f1275b);
        parcel.writeStringList(this.f1276c);
        parcel.writeIntArray(this.f1277d);
        parcel.writeIntArray(this.f1278e);
        parcel.writeInt(this.f1279f);
        parcel.writeInt(this.f1280g);
        parcel.writeString(this.f1281h);
        parcel.writeInt(this.f1282i);
        parcel.writeInt(this.f1283j);
        TextUtils.writeToParcel(this.f1284k, parcel, 0);
        parcel.writeInt(this.f1285l);
        TextUtils.writeToParcel(this.f1286m, parcel, 0);
        parcel.writeStringList(this.f1287n);
        parcel.writeStringList(this.f1288o);
        parcel.writeInt(this.f1289p ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1275b = parcel.createIntArray();
        this.f1276c = parcel.createStringArrayList();
        this.f1277d = parcel.createIntArray();
        this.f1278e = parcel.createIntArray();
        this.f1279f = parcel.readInt();
        this.f1280g = parcel.readInt();
        this.f1281h = parcel.readString();
        this.f1282i = parcel.readInt();
        this.f1283j = parcel.readInt();
        this.f1284k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1285l = parcel.readInt();
        this.f1286m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1287n = parcel.createStringArrayList();
        this.f1288o = parcel.createStringArrayList();
        this.f1289p = parcel.readInt() != 0;
    }
}
