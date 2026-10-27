package com.byd.filemanager.k;

import android.text.TextUtils;
import android.util.ArraySet;
import android.webkit.MimeTypeMap;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: compiled from: MimeTypes.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f3622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Set<String> f3623b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set<String> f3624c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set<String> f3625d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Set<String> f3626e;
    public static final Set<String> f;
    public static final Set<String> g;

    /* JADX INFO: compiled from: MimeTypes.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3627a;

        static {
            int[] iArr = new int[FileType.values().length];
            f3627a = iArr;
            try {
                iArr[FileType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3627a[FileType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3627a[FileType.IMAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3627a[FileType.APK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3627a[FileType.DOCUMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3627a[FileType.COMPRESS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3627a[FileType.OTHER.ordinal()] = 7;
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
        f3623b = arraySet2;
        arraySet2.add("application/x-rar-compressed");
        arraySet2.add("application/zip");
        arraySet2.add("application/x-zip-compressed");
        arraySet2.add("multipart/x-zip");
        ArraySet arraySet3 = new ArraySet();
        f3622a = arraySet3;
        ArraySet arraySet4 = new ArraySet();
        f3624c = arraySet4;
        arraySet4.add("application/msword");
        arraySet4.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ArraySet arraySet5 = new ArraySet();
        f3625d = arraySet5;
        arraySet5.add("application/vnd.ms-excel");
        arraySet5.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ArraySet arraySet6 = new ArraySet();
        f3626e = arraySet6;
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
        return (String[]) f3623b.toArray(new String[0]);
    }

    public static String[] c() {
        return (String[]) f3622a.toArray(new String[0]);
    }

    public static FileType d(FileItem fileItem) {
        if (fileItem == null || !fileItem.u()) {
            return FileType.UNKNOWN;
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(fileItem.C());
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            fileExtensionFromUrl = d.z(fileItem.C());
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        return (mimeTypeFromExtension == null && "ape".equals(fileExtensionFromUrl)) ? FileType.AUDIO : e(mimeTypeFromExtension);
    }

    public static FileType e(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("video/")) {
                return FileType.VIDEO;
            }
            if (str.startsWith("audio/") || f.contains(str)) {
                return FileType.AUDIO;
            }
            if (str.startsWith("image/")) {
                return FileType.IMAGE;
            }
            if (str.equals("text/plain")) {
                return FileType.TXT;
            }
            if (str.equals("application/pdf")) {
                return FileType.PDF;
            }
            if (str.equals("text/html")) {
                return FileType.HTML;
            }
            if (str.equals("application/vnd.android.package-archive")) {
                return FileType.APK;
            }
            if (str.equals("text/x-chdr")) {
                return FileType.C;
            }
            if (f3624c.contains(str)) {
                return FileType.WORD;
            }
            if (f3625d.contains(str)) {
                return FileType.EXCEL;
            }
            if (f3626e.contains(str)) {
                return FileType.PPT;
            }
            if (f3623b.contains(str)) {
                return FileType.COMPRESS;
            }
        }
        return FileType.UNKNOWN;
    }

    public static String[] f(FileType fileType) {
        switch (a.f3627a[fileType.ordinal()]) {
            case 1:
                return a();
            case 2:
                return new String[]{"video/*"};
            case 3:
                return new String[]{"image/*"};
            case 4:
                return new String[]{"application/vnd.android.package-archive"};
            case 5:
                return c();
            case 6:
                return b();
            default:
                return g();
        }
    }

    public static String[] g() {
        return (String[]) g.toArray(new String[0]);
    }
}
