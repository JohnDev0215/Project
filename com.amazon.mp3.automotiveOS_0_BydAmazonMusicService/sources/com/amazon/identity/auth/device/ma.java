package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class ma {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f435a = "com.amazon.identity.auth.device.ma";

    public static String a(Collection<Map<String, String>> collection) {
        if (collection == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<Map<String, String>> it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(new HashMap(it.next()));
            }
            parcelObtain.writeSerializable(arrayList);
            return Base64.encodeToString(parcelObtain.marshall(), 0);
        } finally {
            parcelObtain.recycle();
        }
    }

    public static Bundle b(String str) {
        if (str == null) {
            return null;
        }
        try {
            Parcel parcelA = a(str);
            if (parcelA == null) {
                return null;
            }
            try {
                return parcelA.readBundle();
            } finally {
                parcelA.recycle();
            }
        } catch (Exception e) {
            Log.w(ga.a(f435a), "Failed to deserialize parcel", e);
            return null;
        }
    }

    public static Collection<Map<String, String>> c(String str) {
        Parcel parcelA;
        if (str == null || (parcelA = a(str)) == null) {
            return null;
        }
        try {
            return (Collection) parcelA.readSerializable();
        } finally {
            parcelA.recycle();
        }
    }

    public static String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeBundle(bundle);
            return Base64.encodeToString(parcelObtain.marshall(), 0);
        } finally {
            parcelObtain.recycle();
        }
    }

    public static Parcel a(String str) {
        byte[] bArrDecode = Base64.decode(str, 0);
        if (bArrDecode == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
        parcelObtain.setDataPosition(0);
        return parcelObtain;
    }
}
