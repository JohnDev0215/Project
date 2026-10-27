package com.amazon.identity.auth.device;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.io.Serializable;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o3 implements s3 {
    public static final Uri d = Uri.parse("content://com.amazon.sso.device.data");
    public static final Uri e = Uri.parse("content://com.amazon.sso.device.data.directboot");
    public static final String[] f = {"value", "isPersistent"};
    public static final String g = o3.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s5 f471a;
    public final i5 b;
    public final t5 c;

    public o3(t5 t5Var) {
        this(t5Var, new s5(t5Var), new i5(t5Var));
    }

    public static void a(o3 o3Var, Cursor cursor) throws DeviceDataStoreException {
        o3Var.getClass();
        int columnIndex = cursor.getColumnIndex("exception");
        Serializable serializable = null;
        String string = columnIndex == -1 ? null : cursor.getString(columnIndex);
        int columnIndex2 = cursor.getColumnIndex("exception_message");
        String string2 = columnIndex2 == -1 ? null : cursor.getString(columnIndex2);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (string != null) {
            try {
                Parcel parcelA = ma.a(string);
                try {
                    serializable = parcelA.readSerializable();
                } finally {
                    parcelA.recycle();
                }
            } catch (Exception e2) {
                Log.e(ga.a(g), "Unable to deserialize exception from DeviceDataProvider", e2);
                throw new DeviceDataStoreException(String.format("Unable to deserialize exception from DeviceDataProvider, exception message from DeviceDataProvider is %s.", string2));
            }
        }
        if (!(serializable instanceof DeviceDataStoreException)) {
        }
    }

    public o3(t5 t5Var, s5 s5Var, i5 i5Var) {
        this.f471a = s5Var;
        this.b = i5Var;
        this.c = t5Var;
    }

    @Override // com.amazon.identity.auth.device.s3
    public p3 a(String str) throws DeviceDataStoreException {
        if (this.b.e()) {
            Log.i(ga.a(g), String.format("%s try get device data in direct mode for %s", this.c.getPackageName(), str));
            return a(str, e);
        }
        String str2 = g;
        String.format("%s try get device data out of direct mode for %s", this.c.getPackageName(), str);
        ga.a(str2);
        return a(str, d);
    }

    public final p3 a(String str, Uri uri) throws DeviceDataStoreException {
        try {
            return (p3) this.f471a.a(uri, new n3(this, uri, str));
        } catch (RemoteMAPException e2) {
            Log.e(ga.a(g), "Got a RemoteMAPException", e2);
            if (e2.getCause() instanceof DeviceDataStoreException) {
                throw ((DeviceDataStoreException) e2.getCause());
            }
            throw new DeviceDataStoreException("Failed to query device data store: " + e2.getMessage());
        }
    }
}
