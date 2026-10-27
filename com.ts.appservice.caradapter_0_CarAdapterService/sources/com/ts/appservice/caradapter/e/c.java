package com.ts.appservice.caradapter.e;

import a.a.a.a.a.a;
import a.a.a.a.a.b;
import android.car.hardware.CarPropertyValue;
import android.os.RemoteException;
import android.util.Log;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CarPropertyUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a.a.a.a.a.a f85a;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CarPropertyUtils.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f86a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a.a.a.a.a.b f87b;

        private b() {
        }
    }

    public static CarPropertyValue a(int i, int i2) {
        a.a.a.a.a.a aVar = f85a;
        if (aVar == null) {
            LogUtil.error("CarPropertyUtils", "getCarPropertyValue IVehicle is null");
            return null;
        }
        a.a.a.a.a.b bVar = c(aVar, i, i2).f87b;
        if (bVar != null) {
            return g(bVar, i);
        }
        LogUtil.info("CarPropertyUtils", "getCarPropertyValue = null");
        return null;
    }

    private static Class<?> b(int i) {
        if (i == 1048576) {
            return String.class;
        }
        if (i == 2097152) {
            return Boolean.class;
        }
        if (i == 4194304) {
            return Integer.class;
        }
        if (i == 4259840) {
            return Integer[].class;
        }
        if (i == 5242880) {
            return Long.class;
        }
        if (i == 5308416) {
            return Long[].class;
        }
        if (i == 6291456) {
            return Float.class;
        }
        if (i == 6356992) {
            return Float[].class;
        }
        if (i == 7340032) {
            return byte[].class;
        }
        if (i != 14680064) {
            Log.e("CarPropertyUtils", "getJavaClass Unexpected type: " + Integer.toHexString(i));
        }
        return Object[].class;
    }

    private static b c(a.a.a.a.a.a aVar, int i, int i2) {
        a.a.a.a.a.b bVar = new a.a.a.a.a.b();
        bVar.c = i;
        bVar.f2b = i2;
        final b bVar2 = new b();
        try {
            LogUtil.verbose("CarPropertyUtils", "Vehicle Service call hal get. 0x" + String.format("%x", Integer.valueOf(bVar.c)));
            aVar.e(bVar, new a.b() { // from class: com.ts.appservice.caradapter.e.a
                @Override // a.a.a.a.a.a.b
                public final void a(int i3, a.a.a.a.a.b bVar3) {
                    c.e(bVar2, i3, bVar3);
                }
            });
        } catch (RemoteException e) {
            LogUtil.error("CarPropertyUtils", "getVehiclePropValue=" + e.getMessage());
        }
        return bVar2;
    }

    public static void d(a.a.a.a.a.a aVar) {
        f85a = aVar;
    }

    static /* synthetic */ void e(b bVar, int i, a.a.a.a.a.b bVar2) {
        LogUtil.verbose("CarPropertyUtils", "getVehicle prop value status " + i + " getVehicle prop value " + bVar2);
        bVar.f86a = i;
        bVar.f87b = bVar2;
    }

    private static byte[] f(List<Byte> list) {
        int size = list.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = list.get(i).byteValue();
        }
        return bArr;
    }

    public static CarPropertyValue<?> g(a.a.a.a.a.b bVar, int i) {
        int iIntValue;
        Class<?> clsB = b(bVar.c & 16711680);
        int i2 = bVar.f2b;
        int i3 = bVar.d;
        long j = bVar.f1a;
        b.a aVar = bVar.e;
        int iIntValue2 = 0;
        if (Boolean.class == clsB) {
            ArrayList<Integer> arrayList = aVar.f3a;
            if (arrayList.isEmpty()) {
                Log.e("CarPropertyUtils", "Boolean type int32Values is Null");
                iIntValue = 0;
            } else {
                iIntValue = arrayList.get(0).intValue();
            }
            return new CarPropertyValue<>(i, i2, i3, j, Boolean.valueOf(iIntValue == 1));
        }
        if (Float.class == clsB) {
            ArrayList<Float> arrayList2 = aVar.f4b;
            float fFloatValue = 0.0f;
            if (arrayList2.isEmpty()) {
                Log.e("CarPropertyUtils", "Float type floatValues is Null");
            } else {
                fFloatValue = arrayList2.get(0).floatValue();
            }
            return new CarPropertyValue<>(i, i2, i3, j, Float.valueOf(fFloatValue));
        }
        if (Integer.class == clsB) {
            ArrayList<Integer> arrayList3 = aVar.f3a;
            if (arrayList3.isEmpty()) {
                Log.e("CarPropertyUtils", "Integer type int32Values is Null");
            } else {
                iIntValue2 = arrayList3.get(0).intValue();
            }
            return new CarPropertyValue<>(i, i2, i3, j, Integer.valueOf(iIntValue2));
        }
        if (Long.class == clsB) {
            ArrayList<Long> arrayList4 = aVar.c;
            long jLongValue = 0;
            if (arrayList4.isEmpty()) {
                Log.e("CarPropertyUtils", "Long type int64Values is Null");
            } else {
                jLongValue = arrayList4.get(0).longValue();
            }
            return new CarPropertyValue<>(i, i2, i3, j, Long.valueOf(jLongValue));
        }
        if (Float[].class == clsB) {
            int size = aVar.f4b.size();
            Float[] fArr = new Float[size];
            for (int i4 = 0; i4 < size; i4++) {
                fArr[i4] = aVar.f4b.get(i4);
            }
            return new CarPropertyValue<>(i, i2, i3, j, fArr);
        }
        if (Integer[].class == clsB) {
            int size2 = aVar.f3a.size();
            Integer[] numArr = new Integer[size2];
            for (int i5 = 0; i5 < size2; i5++) {
                numArr[i5] = aVar.f3a.get(i5);
            }
            return new CarPropertyValue<>(i, i2, i3, j, numArr);
        }
        if (Long[].class == clsB) {
            int size3 = aVar.c.size();
            Long[] lArr = new Long[size3];
            for (int i6 = 0; i6 < size3; i6++) {
                lArr[i6] = aVar.c.get(i6);
            }
            return new CarPropertyValue<>(i, i2, i3, j, lArr);
        }
        if (String.class == clsB) {
            return new CarPropertyValue<>(i, i2, i3, j, aVar.e);
        }
        if (byte[].class == clsB) {
            return new CarPropertyValue<>(i, i2, i3, j, f(aVar.d));
        }
        if (Object[].class != clsB) {
            throw new IllegalArgumentException("Unexpected type in: " + i);
        }
        try {
            return new CarPropertyValue<>(i, i2, i3, j, aVar.toString());
        } catch (Exception unused) {
            Log.e("CarPropertyUtils", "Unexpected type in: " + i + " CarPropertyUtils data type convert exception!!!!!!!");
            return new CarPropertyValue<>(i, i2, i3, j, Boolean.FALSE);
        }
    }

    public static int[] h(Integer[] numArr) {
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }
}
