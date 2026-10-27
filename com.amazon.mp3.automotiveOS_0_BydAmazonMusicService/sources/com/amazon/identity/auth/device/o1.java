package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o1 implements v4<Bundle> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f469a;
    public final /* synthetic */ String b;

    public o1(n1 n1Var, Uri uri, String str) {
        this.f469a = uri;
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.v4
    public Bundle a(ContentProviderClient contentProviderClient) throws Exception {
        Cursor cursorQuery = contentProviderClient.query(this.f469a, (String[]) n1.g.toArray(new String[0]), this.b, null, null);
        try {
            String str = this.b;
            Bundle bundleB = null;
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                String str2 = n1.h;
                String.format("No results found from central store: %s", str);
                ga.a(str2);
            } else {
                int columnIndex = cursorQuery.getColumnIndex("bundle_value");
                String string = columnIndex == -1 ? null : cursorQuery.getString(columnIndex);
                if (string == null) {
                    ga.a(n1.h);
                } else {
                    bundleB = ma.b(string);
                }
            }
            return bundleB;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }
}
