package org.chromium.components.browser_ui.site_settings;

import J.N;
import defpackage.d02;
import defpackage.i00;
import defpackage.no;
import defpackage.wy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: loaded from: classes2.dex */
public class WebsitePreferenceBridge {

    public interface StorageInfoClearedCallback {
        @CalledByNative
        void onStorageInfoCleared();
    }

    public static ArrayList a(BrowserContextHandle browserContextHandle, int i) {
        ArrayList<wy> arrayList = new ArrayList();
        N.MI$w5f76(browserContextHandle, i, arrayList);
        if (!N.MnAiqOhu(browserContextHandle, i)) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (wy wyVar : arrayList) {
            if (wyVar.i.equals("policy")) {
                arrayList2.add(wyVar);
            }
        }
        return arrayList2;
    }

    @CalledByNative
    public static void addContentSettingExceptionToList(ArrayList<wy> arrayList, int i, String str, String str2, int i2, String str3, boolean z) {
        if (str3 == null) {
            str3 = "";
        }
        arrayList.add(new wy(i, str, str2, Integer.valueOf(i2), str3, z));
    }

    public static void b(BrowserContextHandle browserContextHandle, int i, String str, String str2, int i2) {
        if (i == 0 && !str2.equals("*")) {
            str2.isEmpty();
        }
        N.M2hAJhrO(browserContextHandle, i, str, str2, i2);
    }

    @CalledByNative
    public static Object createCookiesInfoMap() {
        return new HashMap();
    }

    @CalledByNative
    public static Object createLocalStorageInfoMap() {
        return new HashMap();
    }

    @CalledByNative
    public static Object createStorageInfoList() {
        return new ArrayList();
    }

    @CalledByNative
    public static void insertChosenObjectInfoIntoList(ArrayList<no> arrayList, int i, String str, String str2, String str3, boolean z) {
        arrayList.add(new no(i, str, str2, str3, z));
    }

    @CalledByNative
    public static void insertCookieIntoMap(Map<String, i00> map, String str) {
        i00 i00Var = map.get(str);
        if (i00Var == null) {
            i00Var = new i00();
            map.put(str, i00Var);
        }
        i00Var.e++;
    }

    @CalledByNative
    public static void insertLocalStorageInfoIntoMap(HashMap map, String str, long j, boolean z) {
        map.put(str, new b(j, str, z));
    }

    @CalledByNative
    public static void insertPermissionInfoIntoList(int i, ArrayList<d02> arrayList, String str, String str2, boolean z) {
        if (i == 9 || i == 8) {
            for (d02 d02Var : arrayList) {
                if (d02Var.g.equals(str) && d02Var.f.equals(str2)) {
                    return;
                }
            }
        }
        arrayList.add(new d02(str, str2, z, i));
    }

    @CalledByNative
    public static void insertStorageInfoIntoList(ArrayList<e> arrayList, String str, int i, long j) {
        arrayList.add(new e(i, str, j));
    }
}
