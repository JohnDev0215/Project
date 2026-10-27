package com.neusoft.optimus.utility.file;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FileFilter {
    protected FileFilter _next;

    public abstract String filterTag();

    public abstract boolean isFilterEx(String str);

    public FileFilter() {
        this._next = null;
    }

    public FileFilter(FileFilter fileFilter) {
        this._next = fileFilter;
    }

    public void attachNextFilter(FileFilter fileFilter) {
        this._next = fileFilter;
    }

    protected boolean isFilter(String str) {
        FileFilter fileFilter;
        return isFilterEx(str) || ((fileFilter = this._next) != null && fileFilter.isFilter(str));
    }
}
