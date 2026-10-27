package com.neusoft.optimus.utility.file;

import com.neusoft.optimus.utility.log.LogTool;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class FileSearcher {
    public static final int cAspect = 8;
    public static final int cSearchFile = 1;
    public static final int cSearchFolder = 2;
    private static final String cTag = "FileSearcher";

    public interface IFileSearchListener {
        boolean onFileSearched(String str, int i, int i2);

        void onSearchBegin();

        void onSearchEnd();
    }

    public static void searchFile(String str, FileFilter fileFilter, IFileSearchListener iFileSearchListener) {
        if (iFileSearchListener != null) {
            iFileSearchListener.onSearchBegin();
        }
        searchFileBFs(0, new File(str), fileFilter, iFileSearchListener, new boolean[1]);
        if (iFileSearchListener != null) {
            iFileSearchListener.onSearchEnd();
        }
    }

    static class SearchCtrl {
        File folder;
        int level;

        SearchCtrl(File file, int i) {
            this.folder = file;
            this.level = i;
        }
    }

    private static void searchFileBFs(int i, File file, FileFilter fileFilter, IFileSearchListener iFileSearchListener, boolean[] zArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(new SearchCtrl(file, i));
        while (((SearchCtrl) arrayList.get(0)).level < 8) {
            File[] fileArrListFiles = ((SearchCtrl) arrayList.get(0)).folder.listFiles();
            if (fileArrListFiles != null) {
                try {
                    for (File file2 : fileArrListFiles) {
                        try {
                            if (fileFilter.isFilter(file2.getAbsolutePath()) && iFileSearchListener != null) {
                                if (!iFileSearchListener.onFileSearched(file2.getAbsolutePath(), file2.isFile() ? 1 : 2, ((SearchCtrl) arrayList.get(0)).level)) {
                                    if (file2.isDirectory()) {
                                        arrayList.add(new SearchCtrl(file2, ((SearchCtrl) arrayList.get(0)).level + 1));
                                    }
                                } else {
                                    arrayList.clear();
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            LogTool.d(cTag, "操作文件异常:" + e);
                        }
                    }
                } catch (Exception e2) {
                    LogTool.d(cTag, "操作文件夹异常:" + e2);
                }
            }
            arrayList.remove(0);
            if (arrayList.isEmpty()) {
                return;
            }
        }
    }
}
