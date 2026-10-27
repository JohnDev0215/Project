package com.byd.cleardata.h;

import android.net.Uri;
import android.provider.MediaStore;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Uri f2683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f2685c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2686d = "date_modified DESC";

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        l f2687a = new l();

        public l a() {
            return this.f2687a;
        }

        public a b(String str) {
            this.f2687a.f2686d = str;
            return this;
        }

        public a c(String str) {
            this.f2687a.f2684b = str;
            return this;
        }

        public a d(String[] strArr) {
            this.f2687a.f2685c = strArr;
            return this;
        }

        public a e(Uri uri) {
            this.f2687a.f2683a = uri;
            return this;
        }
    }

    private static String[] e(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length <= 0 || strArr2.length <= 0) {
            return new String[0];
        }
        int length = strArr.length + strArr2.length;
        String[] strArr3 = new String[length];
        int length2 = strArr.length;
        for (int i = 0; i < length; i++) {
            if (i < length2) {
                strArr3[i] = strArr[i];
            } else {
                strArr3[i] = strArr2[i - length2];
            }
        }
        return strArr3;
    }

    public static l f(String str) {
        Uri uri = Uri.parse("content://media/external/file");
        String[] strArrA = com.byd.cleardata.i.i.a();
        return new a().e(uri).c("_data like ? or (_data like ? and (" + j(strArrA, true) + "))").d(e(new String[]{str + "%.ape", str + "%"}, o(strArrA))).a();
    }

    public static l g(String str, com.byd.cleardata.d.f fVar) {
        String[] strArrF = com.byd.cleardata.i.i.f(fVar);
        return new a().e(MediaStore.Downloads.EXTERNAL_CONTENT_URI).c("_data like ? and (" + j(strArrF, fVar != com.byd.cleardata.d.f.OTHER) + ")").d(e(new String[]{str + "%"}, o(strArrF))).a();
    }

    public static l h(String str) {
        return new a().e(MediaStore.Files.getContentUri("external")).c("_display_name LIKE ?").d(new String[]{"%" + str + "%"}).a();
    }

    public static l i(String str) {
        return new a().e(MediaStore.Images.Media.EXTERNAL_CONTENT_URI).c("_data like ?").d(new String[]{str + "%"}).a();
    }

    private static String j(String[] strArr, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            boolean zContains = strArr[i].contains("*");
            sb.append("mime_type");
            if (zContains) {
                sb.append(z ? "" : " NOT");
                str = " LIKE ?";
            } else {
                sb.append(z ? "" : "!");
                str = "=?";
            }
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(z ? " or " : " and ");
            }
        }
        return sb.toString();
    }

    public static l l(String str) {
        return new a().e(Uri.parse("content://media/external/file")).c("_data like ? AND mime_type IS NOT NULL AND mime_type!= '' AND _data NOT like ?").d(new String[]{str + "%", "%/.%"}).b("date_modified DESC LIMIT 100").a();
    }

    private static String[] o(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].contains("*")) {
                strArr2[i] = strArr[i].replace("*", "%");
            } else {
                strArr2[i] = strArr[i];
            }
        }
        return strArr2;
    }

    public static l q(String str) {
        return new a().e(MediaStore.Video.Media.EXTERNAL_CONTENT_URI).c("_data like ?").d(new String[]{str + "%"}).a();
    }

    public String k() {
        return this.f2686d;
    }

    public String m() {
        return this.f2684b;
    }

    public String[] n() {
        return this.f2685c;
    }

    public Uri p() {
        return this.f2683a;
    }

    public String toString() {
        return "Request{uri=" + this.f2683a.toString() + ", selection='" + this.f2684b + "', selectionArgs=" + Arrays.toString(this.f2685c) + ", order='" + this.f2686d + "'}";
    }
}
