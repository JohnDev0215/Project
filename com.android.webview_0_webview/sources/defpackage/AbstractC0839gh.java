package defpackage;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: gh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0839gh {
    public static HashMap f;
    public static Object k;
    public static boolean l;
    public static final Uri a = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri b = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern c = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern d = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final AtomicBoolean e = new AtomicBoolean();
    public static final HashMap g = new HashMap();
    public static final HashMap h = new HashMap();
    public static final HashMap i = new HashMap();
    public static final HashMap j = new HashMap();
    public static String[] m = new String[0];

    public static String a(ContentResolver contentResolver, String str) {
        Cursor cursorQuery;
        synchronized (AbstractC0839gh.class) {
            c(contentResolver);
            Object obj = k;
            if (f.containsKey(str)) {
                String str2 = (String) f.get(str);
                return str2 != null ? str2 : null;
            }
            for (String str3 : m) {
                if (str.startsWith(str3)) {
                    if (!l || f.isEmpty()) {
                        String[] strArr = m;
                        HashMap map = f;
                        cursorQuery = contentResolver.query(b, null, null, strArr, null);
                        TreeMap treeMap = new TreeMap();
                        if (cursorQuery != null) {
                            while (cursorQuery.moveToNext()) {
                                try {
                                    treeMap.put(cursorQuery.getString(0), cursorQuery.getString(1));
                                } finally {
                                }
                            }
                        }
                        map.putAll(treeMap);
                        l = true;
                        if (f.containsKey(str)) {
                            String str4 = (String) f.get(str);
                            return str4 != null ? str4 : null;
                        }
                    }
                    return null;
                }
            }
            cursorQuery = contentResolver.query(a, null, null, new String[]{str}, null);
            if (cursorQuery == null) {
                if (cursorQuery != null) {
                }
                return null;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    b(obj, str, null);
                    return null;
                }
                String string = cursorQuery.getString(1);
                if (string != null && string.equals(null)) {
                    string = null;
                }
                b(obj, str, string);
                return string != null ? string : null;
            } finally {
            }
        }
    }

    public static void b(Object obj, String str, String str2) {
        synchronized (AbstractC0839gh.class) {
            if (obj == k) {
                f.put(str, str2);
            }
        }
    }

    public static void c(ContentResolver contentResolver) {
        if (f == null) {
            e.set(false);
            f = new HashMap();
            k = new Object();
            l = false;
            contentResolver.registerContentObserver(a, true, new C0895hh());
            return;
        }
        if (e.getAndSet(false)) {
            f.clear();
            g.clear();
            h.clear();
            i.clear();
            j.clear();
            k = new Object();
            l = false;
        }
    }
}
