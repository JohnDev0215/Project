package I1;

import A7.m;
import P2.k;
import P2.l;
import P2.n;
import P2.o;
import R2.j;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import d5.I;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2930a;

    public /* synthetic */ g(int i7) {
        this.f2930a = i7;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f2930a) {
            case 0:
                h hVar = new h(parcel);
                hVar.l = parcel.readInt();
                return hVar;
            case 1:
                String string = parcel.readString();
                m.c(string);
                int i7 = parcel.readInt();
                LinkedHashMap linkedHashMap = new LinkedHashMap(i7);
                for (int i10 = 0; i10 < i7; i10++) {
                    String string2 = parcel.readString();
                    m.c(string2);
                    String string3 = parcel.readString();
                    m.c(string3);
                    linkedHashMap.put(string2, string3);
                }
                return new I3.a(string, linkedHashMap);
            case 2:
                String string4 = parcel.readString();
                string4.getClass();
                return new L2.a(parcel.readInt(), string4);
            case 3:
                return new M2.a(parcel);
            case 4:
                return new N2.a(parcel);
            case 5:
                return new N2.b(parcel);
            case 6:
                return new O2.b(parcel);
            case 7:
                return new O2.c(parcel);
            case 8:
                return new P2.a(parcel);
            case 9:
                return new P2.b(parcel);
            case 10:
                return new P2.c(parcel);
            case 11:
                return new P2.d(parcel);
            case 12:
                return new P2.e(parcel);
            case 13:
                return new P2.f(parcel);
            case 14:
                return new k(parcel);
            case 15:
                return new l(parcel);
            case 16:
                return new P2.m(parcel);
            case 17:
                String string5 = parcel.readString();
                string5.getClass();
                String string6 = parcel.readString();
                String[] strArrCreateStringArray = parcel.createStringArray();
                strArrCreateStringArray.getClass();
                return new n(string5, string6, I.x(strArrCreateStringArray));
            case 18:
                return new o(parcel);
            case 19:
                return new Q2.a(parcel);
            case 20:
                ArrayList arrayList = new ArrayList();
                parcel.readList(arrayList, Q2.b.class.getClassLoader());
                return new Q2.c(arrayList);
            case 21:
                return new Q2.b(parcel.readInt(), parcel.readLong(), parcel.readLong());
            case 22:
                return new Q2.d(parcel);
            case 23:
                return new R2.a(parcel);
            case 24:
                return new R2.e(parcel);
            case 25:
                return new R2.f();
            case 26:
                return new R2.i(parcel);
            case 27:
                return new j(parcel.readLong(), parcel.readLong());
            case 28:
                return new S2.a(parcel);
            default:
                return new MediaBrowserCompat$MediaItem(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f2930a) {
            case 0:
                return new h[i7];
            case 1:
                return new I3.a[i7];
            case 2:
                return new L2.a[i7];
            case 3:
                return new M2.a[i7];
            case 4:
                return new N2.a[i7];
            case 5:
                return new N2.b[i7];
            case 6:
                return new O2.b[i7];
            case 7:
                return new O2.c[i7];
            case 8:
                return new P2.a[i7];
            case 9:
                return new P2.b[i7];
            case 10:
                return new P2.c[i7];
            case 11:
                return new P2.d[i7];
            case 12:
                return new P2.e[i7];
            case 13:
                return new P2.f[i7];
            case 14:
                return new k[i7];
            case 15:
                return new l[i7];
            case 16:
                return new P2.m[i7];
            case 17:
                return new n[i7];
            case 18:
                return new o[i7];
            case 19:
                return new Q2.a[i7];
            case 20:
                return new Q2.c[i7];
            case 21:
                return new Q2.b[i7];
            case 22:
                return new Q2.d[i7];
            case 23:
                return new R2.a[i7];
            case 24:
                return new R2.e[i7];
            case 25:
                return new R2.f[i7];
            case 26:
                return new R2.i[i7];
            case 27:
                return new j[i7];
            case 28:
                return new S2.a[i7];
            default:
                return new MediaBrowserCompat$MediaItem[i7];
        }
    }
}
