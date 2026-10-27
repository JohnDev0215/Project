package defpackage;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: jh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1006jh implements InterfaceC1173mh {
    public static final Map g = new C0971j1();
    public static final String[] h = {"key", "value"};
    public final ContentResolver a;
    public final Uri b;
    public volatile Map e;
    public final ContentObserver c = new C1062kh(this);
    public final Object d = new Object();
    public final List f = new ArrayList();

    public C1006jh(ContentResolver contentResolver, Uri uri) {
        this.a = contentResolver;
        this.b = uri;
        contentResolver.registerContentObserver(uri, false, this.c);
    }

    public static C1006jh b(ContentResolver contentResolver, Uri uri) {
        C1006jh c1006jh;
        Object obj = g;
        synchronized (C1006jh.class) {
            c1006jh = (C1006jh) ((C1633v1) obj).get(uri);
            if (c1006jh == null) {
                try {
                    C1006jh c1006jh2 = new C1006jh(contentResolver, uri);
                    try {
                        ((C1633v1) obj).put(uri, c1006jh2);
                    } catch (SecurityException unused) {
                    }
                    c1006jh = c1006jh2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return c1006jh;
    }

    public static synchronized void c() {
        Object obj = g;
        synchronized (C1006jh.class) {
            for (C1006jh c1006jh : (C1523t1) ((C0971j1) obj).values()) {
                c1006jh.a.unregisterContentObserver(c1006jh.c);
            }
            ((C1633v1) obj).clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // defpackage.InterfaceC1173mh
    public final Object a(String str) {
        ?? r0;
        Map map;
        Map map2 = this.e;
        Map mapEmptyMap = map2;
        if (map2 == null) {
            synchronized (this.d) {
                Map map3 = this.e;
                r0 = map3;
                if (map3 == null) {
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            map = (Map) AbstractC1338ph.a(new InterfaceC1283oh(this) { // from class: lh
                                public final C1006jh a;

                                {
                                    this.a = this;
                                }

                                @Override // defpackage.InterfaceC1283oh
                                public final Object a() {
                                    C1006jh c1006jh = this.a;
                                    Cursor cursorQuery = c1006jh.a.query(c1006jh.b, C1006jh.h, null, null, null);
                                    if (cursorQuery == null) {
                                        return Collections.emptyMap();
                                    }
                                    try {
                                        int count = cursorQuery.getCount();
                                        if (count == 0) {
                                            return Collections.emptyMap();
                                        }
                                        Map c0971j1 = count <= 256 ? new C0971j1(count) : new HashMap(count, 1.0f);
                                        while (cursorQuery.moveToNext()) {
                                            c0971j1.put(cursorQuery.getString(0), cursorQuery.getString(1));
                                        }
                                        cursorQuery.close();
                                        return c0971j1;
                                    } finally {
                                        cursorQuery.close();
                                    }
                                }
                            });
                        } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            map = null;
                        }
                        this.e = map;
                        threadPolicyAllowThreadDiskReads = map;
                        r0 = threadPolicyAllowThreadDiskReads;
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    }
                }
            }
            mapEmptyMap = r0;
        }
        if (mapEmptyMap == 0) {
            mapEmptyMap = Collections.emptyMap();
        }
        return (String) mapEmptyMap.get(str);
    }
}
