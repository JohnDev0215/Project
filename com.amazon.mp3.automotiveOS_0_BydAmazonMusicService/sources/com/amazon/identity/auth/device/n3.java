package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n3 implements v4<p3> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f450a;
    public final /* synthetic */ String b;
    public final /* synthetic */ o3 c;

    public n3(o3 o3Var, Uri uri, String str) {
        this.c = o3Var;
        this.f450a = uri;
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.v4
    public p3 a(ContentProviderClient contentProviderClient) throws Exception {
        Cursor cursorQuery = contentProviderClient.query(this.f450a, o3.f, this.b, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    o3.a(this.c, cursorQuery);
                    int columnIndex = cursorQuery.getColumnIndex("value");
                    String string = null;
                    String string2 = columnIndex == -1 ? null : cursorQuery.getString(columnIndex);
                    int columnIndex2 = cursorQuery.getColumnIndex("isPersistent");
                    if (columnIndex2 != -1) {
                        string = cursorQuery.getString(columnIndex2);
                    }
                    boolean z = Boolean.parseBoolean(string);
                    String str = o3.g;
                    String.format("Received Key=%s, Value=%s, Persistent=%s", this.b, string2, string);
                    ga.a(str);
                    p3 p3Var = new p3(string2, z);
                    cursorQuery.close();
                    return p3Var;
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        String string3 = String.format("Key %s was not found in the device data store.", this.b);
        if (this.b.equals(DeviceDataKeys.KEY_KE_DEVICE) || this.b.equals(DeviceDataKeys.KEY_RE_DEVICE)) {
            StringBuilder sb = new StringBuilder();
            sb.append(string3);
            String str2 = this.b;
            sb.append(String.format(" This device does not support %s. This error is expected if the device not support %s.", str2, str2));
            string3 = sb.toString();
        }
        throw new DeviceDataStoreException(string3);
    }
}
