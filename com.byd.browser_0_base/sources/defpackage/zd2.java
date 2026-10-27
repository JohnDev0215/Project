package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import androidx.savedstate.a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zd2 implements a.b {
    public final /* synthetic */ ae2 a;

    @Override // androidx.savedstate.a.b
    public final Bundle a() {
        ae2 ae2Var = this.a;
        i21.e(ae2Var, "this$0");
        LinkedHashMap linkedHashMap = ae2Var.b;
        i21.e(linkedHashMap, "<this>");
        int size = linkedHashMap.size();
        Iterator it = (size != 0 ? size != 1 ? new LinkedHashMap(linkedHashMap) : oj2.g0(linkedHashMap) : hh0.e).entrySet().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            LinkedHashMap linkedHashMap2 = ae2Var.a;
            int i = 0;
            if (!zHasNext) {
                Set<String> setKeySet = linkedHashMap2.keySet();
                ArrayList arrayList = new ArrayList(setKeySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : setKeySet) {
                    arrayList.add(str);
                    arrayList2.add(linkedHashMap2.get(str));
                }
                ov1[] ov1VarArr = {new ov1("keys", arrayList), new ov1("values", arrayList2)};
                Bundle bundle = new Bundle(2);
                while (i < 2) {
                    ov1 ov1Var = ov1VarArr[i];
                    String str2 = (String) ov1Var.e;
                    Object obj = ov1Var.f;
                    if (obj == null) {
                        bundle.putString(str2, null);
                    } else if (obj instanceof Boolean) {
                        bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Byte) {
                        bundle.putByte(str2, ((Number) obj).byteValue());
                    } else if (obj instanceof Character) {
                        bundle.putChar(str2, ((Character) obj).charValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str2, ((Number) obj).doubleValue());
                    } else if (obj instanceof Float) {
                        bundle.putFloat(str2, ((Number) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str2, ((Number) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str2, ((Number) obj).longValue());
                    } else if (obj instanceof Short) {
                        bundle.putShort(str2, ((Number) obj).shortValue());
                    } else if (obj instanceof Bundle) {
                        bundle.putBundle(str2, (Bundle) obj);
                    } else if (obj instanceof CharSequence) {
                        bundle.putCharSequence(str2, (CharSequence) obj);
                    } else if (obj instanceof Parcelable) {
                        bundle.putParcelable(str2, (Parcelable) obj);
                    } else if (obj instanceof boolean[]) {
                        bundle.putBooleanArray(str2, (boolean[]) obj);
                    } else if (obj instanceof byte[]) {
                        bundle.putByteArray(str2, (byte[]) obj);
                    } else if (obj instanceof char[]) {
                        bundle.putCharArray(str2, (char[]) obj);
                    } else if (obj instanceof double[]) {
                        bundle.putDoubleArray(str2, (double[]) obj);
                    } else if (obj instanceof float[]) {
                        bundle.putFloatArray(str2, (float[]) obj);
                    } else if (obj instanceof int[]) {
                        bundle.putIntArray(str2, (int[]) obj);
                    } else if (obj instanceof long[]) {
                        bundle.putLongArray(str2, (long[]) obj);
                    } else if (obj instanceof short[]) {
                        bundle.putShortArray(str2, (short[]) obj);
                    } else if (obj instanceof Object[]) {
                        Class<?> componentType = obj.getClass().getComponentType();
                        i21.b(componentType);
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            bundle.putParcelableArray(str2, (Parcelable[]) obj);
                        } else if (String.class.isAssignableFrom(componentType)) {
                            bundle.putStringArray(str2, (String[]) obj);
                        } else if (CharSequence.class.isAssignableFrom(componentType)) {
                            bundle.putCharSequenceArray(str2, (CharSequence[]) obj);
                        } else {
                            if (!Serializable.class.isAssignableFrom(componentType)) {
                                throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str2 + '\"');
                            }
                            bundle.putSerializable(str2, (Serializable) obj);
                        }
                    } else if (obj instanceof Serializable) {
                        bundle.putSerializable(str2, (Serializable) obj);
                    } else if (obj instanceof IBinder) {
                        i21.e(str2, "key");
                        bundle.putBinder(str2, (IBinder) obj);
                    } else if (obj instanceof Size) {
                        i21.e(str2, "key");
                        bundle.putSize(str2, (Size) obj);
                    } else {
                        if (!(obj instanceof SizeF)) {
                            throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str2 + '\"');
                        }
                        i21.e(str2, "key");
                        bundle.putSizeF(str2, (SizeF) obj);
                    }
                    i++;
                }
                return bundle;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str3 = (String) entry.getKey();
            Bundle bundleA = ((a.b) entry.getValue()).a();
            i21.e(str3, "key");
            if (bundleA == null) {
                i = 1;
                break;
            }
            Class<? extends Object>[] clsArr = ae2.f;
            for (int i2 = 0; i2 < 29; i2++) {
                Class<? extends Object> cls = clsArr[i2];
                i21.b(cls);
                if (cls.isInstance(bundleA)) {
                    i = 1;
                    break;
                }
            }
            if (i == 0) {
                StringBuilder sb = new StringBuilder("Can't put value with type ");
                i21.b(bundleA);
                sb.append(bundleA.getClass());
                sb.append(" into saved state");
                throw new IllegalArgumentException(sb.toString());
            }
            Object obj2 = ae2Var.c.get(str3);
            zj1 zj1Var = obj2 instanceof zj1 ? (zj1) obj2 : null;
            if (zj1Var != null) {
                zj1Var.i(bundleA);
            } else {
                linkedHashMap2.put(str3, bundleA);
            }
            ak1 ak1Var = (ak1) ae2Var.d.get(str3);
            if (ak1Var != null) {
                ak1Var.b();
            }
        }
    }
}
