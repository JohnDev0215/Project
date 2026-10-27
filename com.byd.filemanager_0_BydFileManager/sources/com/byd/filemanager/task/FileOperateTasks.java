package com.byd.filemanager.task;

import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FileOperateTasks {
    public static FileOperateTask a(List<FileItem> list, String str) {
        return new CompressTask(list, str);
    }

    public static FileOperateTask b(List<FileItem> list, String str) {
        return new CopyTask(list, str);
    }

    public static FileOperateTask c(List<FileItem> list) {
        return new DeleteTask(list);
    }

    public static FileOperateTask d(int i, FileType fileType) {
        return new LoadDownloadTask(i, fileType);
    }

    public static FileOperateTask e(String str) {
        return new FindTask(str);
    }

    public static FileOperateTask f(int i, int i2) {
        return new LoadContentProviderTask(i, i2);
    }

    public static LoadPathTask g(String str) {
        return new LoadPathTask(str);
    }

    public static FileOperateTask h(List<FileItem> list, String str) {
        return new MoveTask(list, str);
    }
}
