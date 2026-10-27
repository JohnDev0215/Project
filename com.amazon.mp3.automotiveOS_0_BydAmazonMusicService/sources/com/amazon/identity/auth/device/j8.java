package com.amazon.identity.auth.device;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f395a;
    public final s5 b;
    public final j5 c;

    public j8(Context context, j5 j5Var) {
        this.f395a = context;
        this.b = new s5(context);
        this.c = j5Var;
    }

    public boolean a(Collection<Map<String, String>> collection) {
        Uri uriA = g8.a(this.c.c, "/bulk_data");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        a.a(linkedHashMap, arrayList, "bulk_data", ma.a(collection));
        String strA = da.a(linkedHashMap);
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        try {
            s5 s5Var = this.b;
            boolean z = ((Integer) s5Var.a(uriA, new q5(s5Var, uriA, strA, strArr))).intValue() > 0;
            if (z) {
                String.format("clear bulk data was successful with package %s.", this.c.b);
                ga.a("RemoteAmazonDataStorage");
            } else {
                Log.i(ga.a("RemoteAmazonDataStorage"), String.format("clear bulk data was not successful with package %s.", this.c.b));
            }
            return z;
        } catch (RemoteMAPException e) {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("clear bulk data was not successful with package %s.", this.c.b), e);
            return false;
        }
    }

    public boolean b(Collection<Map<String, String>> collection) {
        Uri uriA = g8.a(this.c.c, "/bulk_data");
        ContentValues contentValues = new ContentValues();
        contentValues.put("bulk_data", ma.a(collection));
        try {
            s5 s5Var = this.b;
            boolean z = ((Uri) s5Var.a(uriA, new r5(s5Var, uriA, contentValues))) != null;
            if (z) {
                String.format("set bulk data was successful with package %s.", this.c.b);
                ga.a("RemoteAmazonDataStorage");
            } else {
                Log.i(ga.a("RemoteAmazonDataStorage"), String.format("set bulk data was not successful with package %s.", this.c.b));
            }
            return z;
        } catch (RemoteMAPException e) {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("set bulk data was not successful with package %s.", this.c.b), e);
            return false;
        }
    }

    public boolean c(String str, String str2, String str3, Date date) {
        Uri uriA = g8.a(this.c.c, "/userdata");
        ContentValues contentValues = new ContentValues();
        contentValues.put("directedId", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.f395a.getContentResolver().insert(uriA, contentValues) != null;
        if (z) {
            String.format("set userdata was successful with package %s.", this.c.b);
            ga.a("RemoteAmazonDataStorage");
        } else {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("set userdata was not successful with package %s.", this.c.b));
        }
        return z;
    }

    /* JADX INFO: compiled from: DCP */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f396a;
        public final String[] b;

        public a(String str, String[] strArr) {
            this.f396a = str;
            this.b = strArr;
        }

        public static a a(String str, String str2, String str3, String str4, Date date) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ArrayList arrayList = new ArrayList();
            a(linkedHashMap, arrayList, "directedId", str);
            a(linkedHashMap, arrayList, "key", str2);
            a(linkedHashMap, arrayList, "value", null);
            a(linkedHashMap, arrayList, "namespace", str4);
            a(linkedHashMap, arrayList, "timestamp_key", wa.a(date));
            return new a(da.a(linkedHashMap), (String[]) arrayList.toArray(new String[0]));
        }

        public static void a(LinkedHashMap<String, String> linkedHashMap, List<String> list, String str, String str2) {
            if (str2 == null) {
                return;
            }
            linkedHashMap.put(str, "?");
            list.add(str2);
        }
    }

    public boolean b(String str, String str2, String str3, Date date) {
        Uri uriA = g8.a(this.c.c, "/tokens");
        ContentValues contentValues = new ContentValues();
        contentValues.put("directedId", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.f395a.getContentResolver().insert(uriA, contentValues) != null;
        if (z) {
            String.format("set token was successful with package %s.", this.c.b);
            ga.a("RemoteAmazonDataStorage");
        } else {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("set token was not successful with package %s.", this.c.b));
        }
        return z;
    }

    public boolean a(String str, Date date) {
        Uri uriA = g8.a(this.c.c, "/accounts");
        a aVarA = a.a(str, null, null, null, date);
        try {
            s5 s5Var = this.b;
            int iIntValue = ((Integer) s5Var.a(uriA, new q5(s5Var, uriA, aVarA.f396a, aVarA.b))).intValue();
            String.format("Removed %d accounts from package %s", Integer.valueOf(iIntValue), this.c.b);
            ga.a("RemoteAmazonDataStorage");
            return iIntValue != 0;
        } catch (RemoteMAPException e) {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("Failed to remove accounts from package %s", this.c.b), e);
            return false;
        }
    }

    public boolean a(String str, String str2, Date date) {
        Uri uriA = g8.a(this.c.c, "/tokens");
        a aVarA = a.a(str, str2, null, null, date);
        int iDelete = this.f395a.getContentResolver().delete(uriA, aVarA.f396a, aVarA.b);
        String.format("Expired %d tokens from package %s", Integer.valueOf(iDelete), this.c.b);
        ga.a("RemoteAmazonDataStorage");
        return iDelete != 0;
    }

    public boolean a(String str, String str2, String str3, Date date) {
        Uri uriA = g8.a(this.c.c, "/device_data");
        ContentValues contentValues = new ContentValues();
        contentValues.put("namespace", str);
        contentValues.put("key", str2);
        contentValues.put("value", str3);
        if (date != null) {
            contentValues.put("timestamp_key", Long.valueOf(date.getTime()));
        }
        boolean z = this.f395a.getContentResolver().insert(uriA, contentValues) != null;
        if (z) {
            String.format("set device data was successful with package %s.", this.c.b);
            ga.a("RemoteAmazonDataStorage");
        } else {
            Log.i(ga.a("RemoteAmazonDataStorage"), String.format("set device data was not successful with package %s.", this.c.b));
        }
        return z;
    }
}
