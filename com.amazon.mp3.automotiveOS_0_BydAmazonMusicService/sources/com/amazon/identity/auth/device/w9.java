package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class w9 implements v4<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f649a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ String[] d;
    public final /* synthetic */ String e;

    public w9(Uri uri, String str, String str2, String[] strArr, String str3) {
        this.f649a = uri;
        this.b = str;
        this.c = str2;
        this.d = strArr;
        this.e = str3;
    }

    @Override // com.amazon.identity.auth.device.v4
    public String a(ContentProviderClient contentProviderClient) throws Exception {
        Cursor cursorQuery = contentProviderClient.query(this.f649a, new String[]{this.b}, this.c, this.d, this.e);
        String string = null;
        if (cursorQuery != null) {
            try {
                String str = this.b;
                if (cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex(str));
                }
            } finally {
                cursorQuery.close();
            }
        }
        return string;
    }
}
