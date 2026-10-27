package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j5 {
    public static final String o = "com.amazon.identity.auth.device.j5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f392a;
    public final String b;
    public final String c;
    public Integer d;
    public Integer e;
    public Integer f;
    public String g;
    public String h;
    public Long i;
    public Integer j;
    public String k;
    public boolean l;
    public RemoteMAPException m;
    public volatile boolean n;

    /* JADX INFO: compiled from: DCP */
    public class a implements v4<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f393a;

        public a(Uri uri) {
            this.f393a = uri;
        }

        @Override // com.amazon.identity.auth.device.v4
        public Object a(ContentProviderClient contentProviderClient) throws Exception {
            Cursor cursorQuery = contentProviderClient.query(this.f393a, (String[]) g8.f349a.toArray(new String[0]), null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        j5.this.d = e.j(cursorQuery.getString(cursorQuery.getColumnIndex("map_major_version")));
                        j5.this.e = e.j(cursorQuery.getString(cursorQuery.getColumnIndex("map_minor_version")));
                        j5.this.f = e.j(cursorQuery.getString(cursorQuery.getColumnIndex("map_sw_version")));
                        j5.this.k = cursorQuery.getString(cursorQuery.getColumnIndex("map_brazil_version"));
                        j5.this.g = cursorQuery.getString(cursorQuery.getColumnIndex("current_device_type"));
                        j5 j5Var = j5.this;
                        if (j5Var.g == null) {
                            ga.b(j5.o, "Package %s has a null device type. Defaulting to the central device type", j5Var.b);
                            j5 j5Var2 = j5.this;
                            j5Var2.g = oa.a(j5Var2.f392a, DeviceAttribute.CentralDeviceType);
                        }
                        if (cursorQuery.getColumnIndex("dsn_override") >= 0) {
                            j5.this.h = cursorQuery.getString(cursorQuery.getColumnIndex("dsn_override"));
                        } else {
                            ga.c(j5.o, "Package %s does not provide a custom DSN override", j5.this.b);
                        }
                        j5.this.j = e.j(cursorQuery.getString(cursorQuery.getColumnIndex("map_init_version")));
                        return null;
                    }
                } finally {
                    x9.a(cursorQuery);
                }
            }
            Log.e(ga.a(j5.o), String.format("No version info returned from package %s.", j5.this.b));
            return null;
        }
    }

    public j5(Context context, ProviderInfo providerInfo) {
        this.f392a = t5.a(context);
        this.b = providerInfo.packageName;
        this.c = providerInfo.authority;
        this.l = false;
        this.n = false;
    }

    public final synchronized void a() throws RemoteMAPException {
        this.l = true;
        Uri uriA = g8.a(this.c, "/map_info");
        Context context = this.f392a;
        try {
            new s5(context, context.getContentResolver()).a(uriA, new a(uriA));
            this.m = null;
        } catch (Exception e) {
            Log.e(ga.a(o), "Failed to query " + this.b, e);
            yd.a("RemoteMapInfoFailure:" + this.b, new String[0]);
            RemoteMAPException remoteMAPException = new RemoteMAPException(e);
            this.m = remoteMAPException;
            throw remoteMAPException;
        }
    }

    public int b() throws RemoteMAPException {
        String str = this.c;
        if (str == null) {
            ga.a(o);
            t4.a(this.f392a).a();
            return 1;
        }
        Uri uriA = g8.a(str, "/generate_common_info");
        String str2 = o;
        uriA.toString();
        this.f392a.getPackageName();
        ga.a(str2);
        Context context = this.f392a;
        Integer numJ = e.j((String) new s5(context, context.getContentResolver()).a(uriA, new w9(uriA, "value", null, null, null)));
        if (numJ != null) {
            return numJ.intValue();
        }
        throw new RemoteMAPException("Common info version String not a valid integer.");
    }

    public synchronized String c() throws RemoteMAPException {
        String str = this.g;
        if (str != null) {
            return str;
        }
        if (f0.b(this.f392a, this.b)) {
            f();
            return this.g;
        }
        ga.a(o);
        String strB = z9.b(this.f392a, this.b);
        this.g = strB;
        return strB;
    }

    public String d() throws RemoteMAPException {
        String str;
        if (!f0.b(this.f392a, this.b)) {
            ga.a(o);
            return null;
        }
        synchronized (this) {
            f();
            str = this.h;
        }
        return str;
    }

    public boolean e() {
        return this.f392a.getPackageName().equals(this.b);
    }

    public final synchronized void f() throws RemoteMAPException {
        RemoteMAPException remoteMAPException = this.m;
        if (remoteMAPException != null) {
            throw remoteMAPException;
        }
        if (this.l) {
            return;
        }
        a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        a(sb, "PackageName", this.b);
        try {
            a(sb, "DeviceType", c());
            f();
            a(sb, "MajorVersion", this.d);
            f();
            a(sb, "MinorVersion", this.e);
            f();
            a(sb, "SWVersion", this.f);
            f();
            a(sb, "BrazilVersion", this.k);
            a(sb, RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, d());
        } catch (RemoteMAPException e) {
            Log.w(ga.a(o), "Failed to query " + this.b, e);
        }
        a(sb, "MAPInitVersion", this.j);
        sb.append("]");
        return sb.toString();
    }

    public j5(Context context) {
        t5 t5VarA = t5.a(context);
        this.f392a = t5VarA;
        this.b = t5VarA.getPackageName();
        this.c = null;
        this.l = false;
        this.n = false;
    }

    public final void a(StringBuilder sb, String str, Object obj) {
        if (obj == null) {
            return;
        }
        sb.append(str);
        sb.append(" = ");
        sb.append(obj);
        sb.append(", ");
    }
}
