package o3;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import n3.C1930E;
import n3.C1945j;
import n3.C1946k;
import n3.O;
import n7.AbstractC1999n;
import n7.C1995j;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: o3.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2043p extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2043p f20480m = new C2043p(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        Bundle bundle;
        C1930E c1930e = (C1930E) obj2;
        c1930e.getClass();
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : n7.z.y(c1930e.f19817v.f19844a).entrySet()) {
            ((O) entry.getValue()).getClass();
        }
        if (arrayList.isEmpty()) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        }
        C1995j c1995j = c1930e.f19804g;
        if (!c1995j.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[c1995j.f20158n];
            Iterator<E> it = c1995j.iterator();
            int i7 = 0;
            while (it.hasNext()) {
                parcelableArr[i7] = new C1946k((C1945j) it.next());
                i7++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        LinkedHashMap linkedHashMap = c1930e.f19809m;
        if (!linkedHashMap.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            int[] iArr = new int[linkedHashMap.size()];
            ArrayList<String> arrayList2 = new ArrayList<>();
            int i10 = 0;
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                int iIntValue = ((Number) entry2.getKey()).intValue();
                String str = (String) entry2.getValue();
                iArr[i10] = iIntValue;
                arrayList2.add(str);
                i10++;
            }
            bundle.putIntArray("android-support-nav:controller:backStackDestIds", iArr);
            bundle.putStringArrayList("android-support-nav:controller:backStackIds", arrayList2);
        }
        LinkedHashMap linkedHashMap2 = c1930e.f19810n;
        if (!linkedHashMap2.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            ArrayList<String> arrayList3 = new ArrayList<>();
            for (Map.Entry entry3 : linkedHashMap2.entrySet()) {
                String str2 = (String) entry3.getKey();
                C1995j c1995j2 = (C1995j) entry3.getValue();
                arrayList3.add(str2);
                Parcelable[] parcelableArr2 = new Parcelable[c1995j2.f20158n];
                int i11 = 0;
                for (Object obj3 : c1995j2) {
                    int i12 = i11 + 1;
                    if (i11 < 0) {
                        AbstractC1999n.I();
                        throw null;
                    }
                    parcelableArr2[i11] = (C1946k) obj3;
                    i11 = i12;
                }
                bundle.putParcelableArray(M5.b.g("android-support-nav:controller:backStackStates:", str2), parcelableArr2);
            }
            bundle.putStringArrayList("android-support-nav:controller:backStackStates", arrayList3);
        }
        if (c1930e.f19803f) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", c1930e.f19803f);
        }
        return bundle;
    }
}
