package defpackage;

import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: WebApkShareTargetUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vp3 {

    /* JADX INFO: compiled from: WebApkShareTargetUtil.java */
    public static class a {
        public final boolean a;
        public final ArrayList<String> b = new ArrayList<>();
        public final ArrayList<Boolean> c = new ArrayList<>();
        public final ArrayList<String> d = new ArrayList<>();
        public final ArrayList<String> e = new ArrayList<>();
        public final ArrayList<String> f = new ArrayList<>();

        public a(boolean z) {
            this.a = z;
        }

        public static void a(a aVar, String str, String str2, boolean z, String str3, String str4) {
            aVar.b.add(str);
            aVar.d.add(str2);
            aVar.c.add(Boolean.valueOf(z));
            aVar.e.add(str3);
            aVar.f.add(str4);
        }

        public static void b(a aVar, String str, String str2) {
            aVar.b.add(str);
            aVar.c.add(Boolean.FALSE);
            aVar.d.add(str2);
            aVar.e.add("");
            aVar.f.add("text/plain");
        }
    }

    public static String a(Uri uri, String str, String[] strArr, String[][] strArr2) {
        boolean z;
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return null;
        }
        for (int i = 0; i < strArr.length; i++) {
            List listAsList = Arrays.asList(strArr2[i]);
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            HashSet hashSet3 = new HashSet();
            Iterator it = listAsList.iterator();
            boolean z2 = false;
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                String lowerCase = ((String) it.next()).trim().toLowerCase(Locale.US);
                if (lowerCase.startsWith(".")) {
                    hashSet.add(lowerCase.substring(1));
                } else if (lowerCase.equals("*/*")) {
                    z2 = true;
                } else if (lowerCase.endsWith("/*")) {
                    hashSet3.add(lowerCase.substring(0, lowerCase.length() - 2));
                } else if (lowerCase.contains("/")) {
                    hashSet2.add(lowerCase);
                }
            }
            MimeTypeMap.getSingleton();
            if ((uri == null || !hashSet.contains(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.US))) && !z2 && !hashSet2.contains(str) && !hashSet3.contains(str.split("/", 2)[0])) {
                z = false;
            }
            if (z) {
                return strArr[i];
            }
        }
        return null;
    }

    public static String b(Uri uri) {
        if (uri.getScheme().equals("content")) {
            Cursor cursorQuery = xz.a.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                        if (string != null) {
                            return string;
                        }
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Throwable unused) {
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
        return uri.getPath();
    }
}
