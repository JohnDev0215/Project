package com.byd.filemanager.task;

import android.net.Uri;
import android.provider.MediaStore;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.k.h;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Request {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Uri f3671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f3673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3674d = "date_modified DESC";

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Request f3675a = new Request();

        public Request a() {
            return this.f3675a;
        }

        public Builder b(String str) {
            this.f3675a.f3674d = str;
            return this;
        }

        public Builder c(String str) {
            this.f3675a.f3672b = str;
            return this;
        }

        public Builder d(String[] strArr) {
            this.f3675a.f3673c = strArr;
            return this;
        }

        public Builder e(Uri uri) {
            this.f3675a.f3671a = uri;
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

    public static Request f(String str) {
        Uri uri = Uri.parse("content://media/external/file");
        String[] strArrA = h.a();
        return new Builder().e(uri).c("_data like ? or (_data like ? and (" + j(strArrA, true) + "))").d(e(new String[]{str + "%.ape", str + "%"}, o(strArrA))).a();
    }

    public static Request g(String str, FileType fileType) {
        String[] strArrF = h.f(fileType);
        return new Builder().e(MediaStore.Downloads.EXTERNAL_CONTENT_URI).c("_data like ? and (" + j(strArrF, fileType != FileType.OTHER) + ")").d(e(new String[]{str + "%"}, o(strArrF))).a();
    }

    public static Request h(String str) {
        return new Builder().e(MediaStore.Files.getContentUri("external")).c("_display_name LIKE ?").d(new String[]{"%" + str + "%"}).a();
    }

    public static Request i(String str) {
        return new Builder().e(MediaStore.Images.Media.EXTERNAL_CONTENT_URI).c("_data like ?").d(new String[]{str + "%"}).a();
    }

    private static String j(String[] strArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].contains("*")) {
                sb.append("mime_type");
                sb.append(z ? "" : " NOT");
                sb.append(" LIKE ?");
            } else {
                sb.append("mime_type");
                sb.append(z ? "" : "!");
                sb.append("=?");
            }
            if (i != strArr.length - 1) {
                sb.append(z ? " or " : " and ");
            }
        }
        return sb.toString();
    }

    public static Request l(String str) {
        return new Builder().e(Uri.parse("content://media/external/file")).c("_data like ? AND mime_type IS NOT NULL AND mime_type!= '' AND _data NOT like ?").d(new String[]{str + "%", "%/.%"}).b("date_modified DESC LIMIT 100").a();
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

    public static Request q(String str) {
        return new Builder().e(MediaStore.Video.Media.EXTERNAL_CONTENT_URI).c("_data like ?").d(new String[]{str + "%"}).a();
    }

    public String k() {
        return this.f3674d;
    }

    public String m() {
        return this.f3672b;
    }

    public String[] n() {
        return this.f3673c;
    }

    public Uri p() {
        return this.f3671a;
    }

    public String toString() {
        return "Request{uri=" + this.f3671a.toString() + ", selection='" + this.f3672b + "', selectionArgs=" + Arrays.toString(this.f3673c) + ", order='" + this.f3674d + "'}";
    }
}
