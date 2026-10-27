package com.byd.cleardata.i;

import android.text.TextUtils;
import android.util.ArraySet;
import android.webkit.MimeTypeMap;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f2707a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Set<String> f2708b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set<String> f2709c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set<String> f2710d;
    public static final Set<String> e;
    public static final Set<String> f;
    public static final Set<String> g;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2711a;

        static {
            int[] iArr = new int[com.byd.cleardata.d.f.values().length];
            f2711a = iArr;
            try {
                iArr[com.byd.cleardata.d.f.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.IMAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.APK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.DOCUMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.COMPRESS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2711a[com.byd.cleardata.d.f.OTHER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        ArraySet arraySet = new ArraySet();
        f = arraySet;
        arraySet.add("audio/*");
        arraySet.add("application/ogg");
        arraySet.add("application/x-flac");
        ArraySet arraySet2 = new ArraySet();
        f2708b = arraySet2;
        arraySet2.add("application/x-rar-compressed");
        arraySet2.add("application/zip");
        arraySet2.add("application/x-zip-compressed");
        arraySet2.add("multipart/x-zip");
        ArraySet arraySet3 = new ArraySet();
        f2707a = arraySet3;
        ArraySet arraySet4 = new ArraySet();
        f2709c = arraySet4;
        arraySet4.add("application/msword");
        arraySet4.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ArraySet arraySet5 = new ArraySet();
        f2710d = arraySet5;
        arraySet5.add("application/vnd.ms-excel");
        arraySet5.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ArraySet arraySet6 = new ArraySet();
        e = arraySet6;
        arraySet6.add("application/vnd.ms-powerpoint");
        arraySet6.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        arraySet3.add("text/plain");
        arraySet3.add("application/pdf");
        arraySet3.addAll((Collection) arraySet4);
        arraySet3.addAll((Collection) arraySet5);
        arraySet3.addAll((Collection) arraySet6);
        ArraySet arraySet7 = new ArraySet();
        g = arraySet7;
        arraySet7.addAll((Collection) arraySet2);
        arraySet7.addAll((Collection) arraySet3);
        arraySet7.addAll((Collection) arraySet);
        arraySet7.add("video/*");
        arraySet7.add("image/*");
        arraySet7.add("application/vnd.android.package-archive");
    }

    public static String[] a() {
        return (String[]) f.toArray(new String[0]);
    }

    public static String[] b() {
        return (String[]) f2708b.toArray(new String[0]);
    }

    public static String[] c() {
        return (String[]) f2707a.toArray(new String[0]);
    }

    public static com.byd.cleardata.d.f d(com.byd.cleardata.d.d dVar) {
        if (dVar == null || !dVar.u()) {
            return com.byd.cleardata.d.f.UNKNOWN;
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(dVar.C());
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            fileExtensionFromUrl = e.z(dVar.C());
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        return (mimeTypeFromExtension == null && "ape".equals(fileExtensionFromUrl)) ? com.byd.cleardata.d.f.AUDIO : e(mimeTypeFromExtension);
    }

    public static com.byd.cleardata.d.f e(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("video/")) {
                return com.byd.cleardata.d.f.VIDEO;
            }
            if (str.startsWith("audio/") || f.contains(str)) {
                return com.byd.cleardata.d.f.AUDIO;
            }
            if (str.startsWith("image/")) {
                return com.byd.cleardata.d.f.IMAGE;
            }
            if (str.equals("text/plain")) {
                return com.byd.cleardata.d.f.TXT;
            }
            if (str.equals("application/pdf")) {
                return com.byd.cleardata.d.f.PDF;
            }
            if (str.equals("text/html")) {
                return com.byd.cleardata.d.f.HTML;
            }
            if (str.equals("application/vnd.android.package-archive")) {
                return com.byd.cleardata.d.f.APK;
            }
            if (str.equals("text/x-chdr")) {
                return com.byd.cleardata.d.f.C;
            }
            if (f2709c.contains(str)) {
                return com.byd.cleardata.d.f.WORD;
            }
            if (f2710d.contains(str)) {
                return com.byd.cleardata.d.f.EXCEL;
            }
            if (e.contains(str)) {
                return com.byd.cleardata.d.f.PPT;
            }
            if (f2708b.contains(str)) {
                return com.byd.cleardata.d.f.COMPRESS;
            }
        }
        return com.byd.cleardata.d.f.UNKNOWN;
    }

    public static String[] f(com.byd.cleardata.d.f fVar) {
        String str;
        switch (a.f2711a[fVar.ordinal()]) {
            case 1:
                return a();
            case 2:
                str = "video/*";
                break;
            case 3:
                str = "image/*";
                break;
            case 4:
                str = "application/vnd.android.package-archive";
                break;
            case 5:
                return c();
            case 6:
                return b();
            default:
                return g();
        }
        return new String[]{str};
    }

    public static String[] g() {
        return (String[]) g.toArray(new String[0]);
    }
}
