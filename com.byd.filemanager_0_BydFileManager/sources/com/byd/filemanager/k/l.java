package com.byd.filemanager.k;

import com.byd.filemanager.bean.FileItem;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: SortUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: compiled from: SortUtil.java */
    public static class a implements Comparator<FileItem> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f3635b;

        public a(String str) {
            this.f3635b = str;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(FileItem fileItem, FileItem fileItem2) {
            if (fileItem == null || fileItem2 == null) {
                return 0;
            }
            String strC = fileItem.C();
            String strC2 = fileItem2.C();
            int iIndexOf = strC.toLowerCase().indexOf(this.f3635b);
            int iIndexOf2 = strC2.toLowerCase().indexOf(this.f3635b);
            return iIndexOf != iIndexOf2 ? Integer.compare(iIndexOf, iIndexOf2) : Integer.compare(strC.length(), strC2.length());
        }
    }

    /* JADX INFO: compiled from: SortUtil.java */
    public static class b implements Comparator<FileItem> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f3636b;

        public b(int i) {
            this.f3636b = i;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(FileItem fileItem, FileItem fileItem2) {
            if (fileItem == null || fileItem2 == null) {
                return 0;
            }
            return l.a(fileItem, fileItem2, this.f3636b);
        }
    }

    public static int a(FileItem fileItem, FileItem fileItem2, int i) {
        if (fileItem == null || fileItem2 == null) {
            return 0;
        }
        if (i == 0) {
            return b(fileItem, fileItem2);
        }
        if (i == 1) {
            if (!fileItem.N() || !fileItem2.N()) {
                return (fileItem.N() || fileItem2.N()) ? fileItem.N() ? 1 : -1 : b(fileItem, fileItem2);
            }
            long jB = fileItem.B();
            long jB2 = fileItem2.B();
            return jB == jB2 ? b(fileItem, fileItem2) : Long.compare(jB2, jB);
        }
        if (i == 2) {
            long jA = fileItem.A();
            long jA2 = fileItem2.A();
            return jA == jA2 ? b(fileItem, fileItem2) : Long.compare(jA2, jA);
        }
        if (i != 3) {
            return 0;
        }
        int iOrdinal = fileItem.G().ordinal();
        int iOrdinal2 = fileItem2.G().ordinal();
        return iOrdinal == iOrdinal2 ? b(fileItem, fileItem2) : Integer.compare(iOrdinal, iOrdinal2);
    }

    private static int b(FileItem fileItem, FileItem fileItem2) {
        if (fileItem == null || fileItem2 == null) {
            return 0;
        }
        return c(fileItem.C(), fileItem2.C());
    }

    private static int c(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        return Collator.getInstance(Locale.CHINA).compare(str.toLowerCase(), str2.toLowerCase());
    }

    public static void d(int i, List<FileItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.sort(new b(i));
    }

    public static void e(List<FileItem> list) {
        d(com.byd.filemanager.f.h().p(), list);
    }
}
