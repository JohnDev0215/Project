package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i8 implements v4<Collection<Map<String, String>>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f383a;

    public i8(j8 j8Var, Uri uri) {
        this.f383a = uri;
    }

    @Override // com.amazon.identity.auth.device.v4
    public Collection<Map<String, String>> a(ContentProviderClient contentProviderClient) throws Exception {
        Uri uri = this.f383a;
        List<String> list = g8.b;
        Cursor cursorQuery = contentProviderClient.query(uri, (String[]) list.toArray(new String[list.size()]), null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            LinkedList linkedList = new LinkedList();
            if (cursorQuery.moveToFirst()) {
                do {
                    HashMap map = new HashMap();
                    for (String str : g8.b) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex(str));
                        if (str != null) {
                            map.put(str, string);
                        }
                    }
                    linkedList.add(map);
                } while (cursorQuery.moveToNext());
            }
            return linkedList;
        } finally {
            x9.a(cursorQuery);
        }
    }
}
