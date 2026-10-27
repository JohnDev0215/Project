package org.chromium.components.browser_ui.contacts_picker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import defpackage.i9;
import defpackage.ky1;
import defpackage.xx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.contacts_picker.ChromePickerAdapter;

/* JADX INFO: compiled from: ContactsFetcherWorkerTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends i9<ArrayList<xx>> {
    public static final String[] n = {"_id", "lookup", "display_name"};
    public final ContentResolver h;
    public final a i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;

    /* JADX INFO: compiled from: ContactsFetcherWorkerTask.java */
    public interface a {
    }

    public c(Context context, ChromePickerAdapter chromePickerAdapter, boolean z, boolean z2, boolean z3, boolean z4) {
        this.h = context.getContentResolver();
        this.i = chromePickerAdapter;
        this.j = z;
        this.k = z2;
        this.l = z3;
        this.m = z4;
    }

    @Override // defpackage.i9
    public final ArrayList<xx> b() {
        Map map;
        boolean z;
        HashMap map2;
        Map map3;
        List list;
        if (i()) {
            return null;
        }
        boolean z2 = this.k;
        HashMap mapO = z2 ? o(ContactsContract.CommonDataKinds.Email.CONTENT_URI) : null;
        boolean z3 = this.l;
        HashMap mapO2 = z3 ? o(ContactsContract.CommonDataKinds.Phone.CONTENT_URI) : null;
        boolean z4 = this.m;
        if (z4) {
            map2 = new HashMap();
            Cursor cursorQuery = this.h.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, null, null, "contact_id ASC, data1 ASC");
            ArrayList arrayList = new ArrayList();
            String str = "";
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("contact_id"));
                String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data7"));
                String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data10"));
                String string4 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data1"));
                String string5 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data9"));
                boolean z5 = z4;
                String string6 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data8"));
                HashMap map4 = mapO2;
                ky1 ky1Var = new ky1();
                if (string2 == null) {
                    string2 = "";
                }
                ky1Var.e = string2;
                if (string3 == null) {
                    string3 = "";
                }
                ky1Var.b = string3;
                ky1Var.c = string4 != null ? new String[]{string4} : new String[0];
                if (string5 == null) {
                    string5 = "";
                }
                ky1Var.g = string5;
                if (string6 == null) {
                    string6 = "";
                }
                ky1Var.d = string6;
                ky1Var.f = "";
                ky1Var.h = "";
                ky1Var.i = "";
                ky1Var.j = "";
                ky1Var.k = "";
                if (str.isEmpty()) {
                    arrayList.add(ky1Var);
                } else if (str.equals(string)) {
                    arrayList.add(ky1Var);
                    z4 = z5;
                    mapO2 = map4;
                } else {
                    map2.put(str, arrayList);
                    arrayList = new ArrayList();
                    arrayList.add(ky1Var);
                }
                str = string;
                z4 = z5;
                mapO2 = map4;
            }
            map = mapO2;
            z = z4;
            map2.put(str, arrayList);
            cursorQuery.close();
        } else {
            map = mapO2;
            z = z4;
            map2 = null;
        }
        Cursor cursorQuery2 = this.h.query(ContactsContract.Contacts.CONTENT_URI, n, null, null, "sort_key ASC");
        if (!cursorQuery2.moveToFirst()) {
            cursorQuery2.close();
            return new ArrayList<>();
        }
        ArrayList<xx> arrayList2 = new ArrayList<>(cursorQuery2.getCount());
        while (true) {
            String string7 = cursorQuery2.getString(cursorQuery2.getColumnIndexOrThrow("_id"));
            String string8 = cursorQuery2.getString(cursorQuery2.getColumnIndexOrThrow("display_name"));
            List list2 = z2 ? (List) mapO.get(string7) : null;
            if (z3) {
                map3 = map;
                list = (List) map3.get(string7);
            } else {
                map3 = map;
                list = null;
            }
            List list3 = z ? (List) map2.get(string7) : null;
            if (this.j || list2 != null || list != null || list3 != null) {
                arrayList2.add(new xx(string7, string8, list2, list, list3));
            }
            if (!cursorQuery2.moveToNext()) {
                cursorQuery2.close();
                return arrayList2;
            }
            map = map3;
        }
    }

    @Override // defpackage.i9
    public final void l(ArrayList<xx> arrayList) {
        ArrayList<xx> arrayList2 = arrayList;
        if (i()) {
            return;
        }
        ((f) this.i).E(arrayList2);
    }

    public final HashMap o(Uri uri) {
        HashMap map = new HashMap();
        Cursor cursorQuery = this.h.query(uri, null, null, null, "contact_id ASC, data1 ASC");
        ArrayList arrayList = new ArrayList();
        String str = "";
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("contact_id"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data1"));
            if (string2 == null) {
                string2 = "";
            }
            if (str.isEmpty()) {
                arrayList.add(string2);
            } else if (str.equals(string)) {
                arrayList.add(string2);
            } else {
                map.put(str, arrayList);
                arrayList = new ArrayList();
                arrayList.add(string2);
            }
            str = string;
        }
        map.put(str, arrayList);
        cursorQuery.close();
        return map;
    }
}
