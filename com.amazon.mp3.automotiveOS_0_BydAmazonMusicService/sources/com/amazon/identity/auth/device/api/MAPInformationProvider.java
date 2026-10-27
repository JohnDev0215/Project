package com.amazon.identity.auth.device.api;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.c7;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f0;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ma;
import com.amazon.identity.auth.device.o7;
import com.amazon.identity.auth.device.s9;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import com.amazon.identity.auth.device.t4;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.wa;
import com.amazon.identity.auth.device.x9;
import com.amazon.identity.auth.device.z9;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MAPInformationProvider extends ContentProvider {

    @FireOsSdk
    public static final String TOKEN_PROVIDER_AUTHORITY_PREFIX = "com.amazon.identity.auth.device.MapInfoProvider.";
    public static final String f = MAPInformationProvider.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile s9 f222a;
    public Context b;
    public LocalDataStorage c;
    public LambortishClock d;
    public boolean e = false;

    @FireOsSdk
    public MAPInformationProvider() {
    }

    public final synchronized void a() {
        if (this.e) {
            return;
        }
        MAPInit.getInstance(this.b).initialize();
        t5 t5VarA = t5.a(this.b);
        this.b = t5VarA;
        this.c = (LocalDataStorage) t5VarA.getSystemService("sso_local_datastorage");
        this.d = LambortishClock.a(this.b);
        this.e = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    @Override // android.content.ContentProvider
    @FireOsSdk
    public final int delete(Uri uri, String str, String[] strArr) {
        Collection<Map<String, String>> collection;
        Date date;
        Date date2;
        e.a(this.b);
        a();
        a(uri);
        SelectionInfo selection = SelectionInfo.parseSelection(str, strArr);
        if (selection == null) {
            throw new IllegalArgumentException("Invalid selection");
        }
        String path = uri.getPath();
        ?? A = 0;
        A = 0;
        A = 0;
        A = 0;
        A = 0;
        A = 0;
        A = 0;
        if ("/accounts".equals(path)) {
            if (selection.directedId != null && (date2 = selection.timestamp) != null) {
                this.d.a(date2);
                A = this.c.a(selection.directedId, selection.timestamp, true);
            }
        } else if ("/tokens".equals(path)) {
            if (selection.directedId != null && selection.key != null && (date = selection.timestamp) != null) {
                this.d.a(date);
                A = this.c.a(selection.directedId, selection.key, selection.timestamp, true);
            }
        } else if ("/bulk_data".equals(path) && (collection = selection.bulkData) != null) {
            a(collection);
            A = this.c.a(selection.bulkData);
        }
        c7.a(this.b, new MAPAccountManager(this.b).getAccount());
        return A;
    }

    @FireOsSdk
    public String getDsnOverrideForChildDeviceType() {
        ga.a(f);
        return null;
    }

    @FireOsSdk
    public String getOverrideChildDeviceType() {
        ga.a(f);
        return null;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final String getType(Uri uri) {
        throw new UnsupportedOperationException("getType currently not supported");
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final Uri insert(Uri uri, ContentValues contentValues) {
        e.a(this.b);
        a();
        boolean zA = a(uri, contentValues);
        c7.a(this.b, new MAPAccountManager(this.b).getAccount());
        if (zA) {
            return uri;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public boolean onCreate() {
        if (this.b != null) {
            return true;
        }
        this.b = getContext();
        return true;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Collection<Map<String, String>> collectionA;
        String str3;
        String str4;
        String strB;
        e.a(this.b);
        a(uri);
        SelectionInfo selection = SelectionInfo.parseSelection(str, strArr2);
        if (selection == null) {
            throw new IllegalArgumentException("Invalid selection");
        }
        String path = uri.getPath();
        Cursor cursorA = null;
        if ("/map_info".equals(path)) {
            MAPInit.getInstance(this.b).initialize();
            s9 s9VarA = this.f222a;
            if (s9VarA == null) {
                s9VarA = s9.a();
                this.f222a = s9VarA;
            }
            HashMap map = new HashMap();
            map.put("map_major_version", Integer.toString(s9VarA.f544a));
            map.put("map_minor_version", Integer.toString(s9VarA.b));
            Context context = this.b;
            if (f0.b(context, context.getPackageName())) {
                ga.a(f);
                strB = getOverrideChildDeviceType();
            } else {
                ga.a(f);
                strB = null;
            }
            if (TextUtils.isEmpty(strB)) {
                Context context2 = this.b;
                strB = z9.b(context2, context2.getPackageName());
            }
            map.put("current_device_type", strB);
            Context context3 = this.b;
            map.put("dsn_override", f0.b(context3, context3.getPackageName()) ? getDsnOverrideForChildDeviceType() : null);
            map.put("map_sw_version", Integer.toString(s9VarA.c));
            map.put("map_brazil_version", s9VarA.d);
            map.put("map_init_version", Integer.toString(new f8(this.b, "SSOInfo.config", 0).f313a.getInt("SSOInfo.config", 0)));
            if (strArr != null) {
                return x9.a(strArr, Collections.singletonList(map));
            }
            return null;
        }
        a();
        if ("/accounts".equals(path)) {
            Set<String> setB = this.c.b();
            cursorA = a(strArr, (String[]) setB.toArray(new String[setB.size()]));
        } else if ("/userdata".equals(path)) {
            String str5 = selection.directedId;
            if (str5 != null && (str4 = selection.key) != null) {
                cursorA = a(strArr, this.c.c(str5, str4));
            }
        } else if ("/tokens".equals(path)) {
            String str6 = selection.directedId;
            if (str6 != null && (str3 = selection.key) != null) {
                cursorA = a(strArr, this.c.b(str6, str3));
            }
        } else if ("/device_data".equals(path)) {
            if (selection.namespace != null && selection.key != null) {
                t4 t4VarA = t4.a(this.b);
                if (TextUtils.isEmpty(this.c.a(selection.namespace, selection.key))) {
                    Log.i(ga.a(f), String.format(Locale.ENGLISH, "Device data for %s is empty, generate or fetch it.", selection.key));
                    t4VarA.a();
                }
                cursorA = a(strArr, this.c.a(selection.namespace, selection.key));
            }
        } else if ("/all_data".equals(path)) {
            LocalDataStorage localDataStorage = this.c;
            synchronized (localDataStorage) {
                collectionA = localDataStorage.a((Date) null, EnumSet.noneOf(LocalDataStorage.GetDataOptions.class));
            }
            cursorA = x9.a(strArr, collectionA);
        } else if ("/all_deleted_data".equals(path)) {
            cursorA = x9.a(strArr, this.c.d());
        } else if ("/generate_common_info".equals(path)) {
            t4.a(this.b).a();
            cursorA = a(strArr, Integer.toString(1));
        }
        c7.a(this.b, new MAPAccountManager(this.b).getAccount());
        return cursorA;
    }

    @Override // android.content.ContentProvider
    @FireOsSdk
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        e.a(this.b);
        a();
        boolean zA = a(uri, contentValues);
        c7.a(this.b, new MAPAccountManager(this.b).getAccount());
        return zA ? 1 : 0;
    }

    /* JADX INFO: compiled from: DCP */
    public static class SelectionInfo {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SelectionInfo f223a = new SelectionInfo(null, null, null, null, null, null, null, null);
        public final Collection<Map<String, String>> bulkData;
        public final String directedId;
        public final String displayName;
        public final String key;
        public final String namespace;
        public final Date timestamp;
        public final Bundle userdata;
        public final String value;

        public SelectionInfo(String str, String str2, String str3, String str4, String str5, String str6, Date date, String str7) {
            this.directedId = str;
            this.key = str2;
            this.value = str3;
            this.namespace = str4;
            this.displayName = str5;
            this.userdata = ma.b(str6);
            this.timestamp = date;
            this.bulkData = ma.c(str7);
        }

        public static SelectionInfo parseSelection(ContentValues contentValues) {
            String asString = contentValues.getAsString("directedId");
            String asString2 = contentValues.getAsString("key");
            String asString3 = contentValues.getAsString("value");
            String asString4 = contentValues.getAsString("namespace");
            String asString5 = contentValues.getAsString("display_name");
            String asString6 = contentValues.getAsString("userdata_bundle_key");
            Long asLong = contentValues.getAsLong("timestamp_key");
            return new SelectionInfo(asString, asString2, asString3, asString4, asString5, asString6, asLong == null ? null : new Date(asLong.longValue()), contentValues.getAsString("bulk_data"));
        }

        public static SelectionInfo parseSelection(String str, String[] strArr) {
            String[] strArr2;
            try {
                if (str == null) {
                    return f223a;
                }
                if (strArr == null) {
                    strArr2 = new String[0];
                } else {
                    String[] strArr3 = new String[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        strArr3[i] = JSONObject.quote(strArr[i]);
                    }
                    strArr2 = strArr3;
                }
                JSONObject jSONObject = new JSONObject(String.format(str.replace("\"?\"", "%s"), strArr2));
                return new SelectionInfo(da.a(jSONObject, "directedId", (String) null), da.a(jSONObject, "key", (String) null), da.a(jSONObject, "value", (String) null), da.a(jSONObject, "namespace", (String) null), da.a(jSONObject, "display_name", (String) null), da.a(jSONObject, "userdata_bundle_key", (String) null), wa.a(da.a(jSONObject, "timestamp_key", (String) null)), da.a(jSONObject, "bulk_data", (String) null));
            } catch (IllegalFormatException e) {
                Log.e(ga.a(MAPInformationProvider.f), "Format not valid. Error: " + e.getMessage());
                return null;
            } catch (JSONException unused) {
                Log.e(ga.a(MAPInformationProvider.f), "Format was not valid JSON");
                return null;
            }
        }
    }

    public final void a(Collection<Map<String, String>> collection) {
        Iterator<Map<String, String>> it = collection.iterator();
        while (it.hasNext()) {
            this.d.a(wa.a(it.next().get("timestamp_key")));
        }
    }

    public final boolean a(Uri uri, ContentValues contentValues) {
        Collection<Map<String, String>> collection;
        Date date;
        Date date2;
        boolean zA;
        Date date3;
        boolean zA2;
        Date date4;
        a(uri);
        SelectionInfo selection = SelectionInfo.parseSelection(contentValues);
        if (selection != null) {
            String path = uri.getPath();
            if ("/accounts".equals(path)) {
                if (selection.displayName == null || selection.directedId == null || (date4 = selection.timestamp) == null) {
                    return false;
                }
                this.d.a(date4);
                return this.c.a(selection.displayName, new o7(selection.directedId, e.d(selection.userdata), null, null), selection.timestamp, true);
            }
            if ("/userdata".equals(path)) {
                if (selection.directedId == null || selection.key == null || (date3 = selection.timestamp) == null) {
                    return false;
                }
                this.d.a(date3);
                LocalDataStorage localDataStorage = this.c;
                String str = selection.directedId;
                String str2 = selection.key;
                String str3 = selection.value;
                Date date5 = selection.timestamp;
                synchronized (localDataStorage) {
                    zA2 = localDataStorage.a(new o7(str, Collections.singletonMap(str2, str3), null, null), date5, true);
                }
                return zA2;
            }
            if ("/tokens".equals(path)) {
                if (selection.directedId == null || selection.key == null || (date2 = selection.timestamp) == null) {
                    return false;
                }
                this.d.a(date2);
                LocalDataStorage localDataStorage2 = this.c;
                String str4 = selection.directedId;
                String str5 = selection.key;
                String str6 = selection.value;
                Date date6 = selection.timestamp;
                synchronized (localDataStorage2) {
                    zA = localDataStorage2.a(new o7(str4, null, Collections.singletonMap(str5, str6), null), date6, true);
                }
                return zA;
            }
            if ("/device_data".equals(path)) {
                if (selection.namespace == null || selection.key == null || (date = selection.timestamp) == null) {
                    return false;
                }
                this.d.a(date);
                return this.c.a(selection.namespace, selection.key, selection.value, selection.timestamp, true);
            }
            if (!"/bulk_data".equals(path) || (collection = selection.bulkData) == null) {
                return false;
            }
            a(collection);
            return this.c.b(selection.bulkData);
        }
        throw new IllegalArgumentException("Invalid selection");
    }

    public final void a(Uri uri) {
        e.a(this.b);
        if (uri.getAuthority().startsWith(TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
            return;
        }
        throw new IllegalArgumentException("Unknown supported authority " + uri.getAuthority());
    }

    public final Cursor a(String[] strArr, String... strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr2) {
            HashMap map = new HashMap();
            map.put("value", str);
            arrayList.add(map);
        }
        return x9.a(strArr, arrayList);
    }
}
