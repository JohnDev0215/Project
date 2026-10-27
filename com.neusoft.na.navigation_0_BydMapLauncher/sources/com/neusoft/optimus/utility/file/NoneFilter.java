package com.neusoft.optimus.utility.file;

/* JADX INFO: compiled from: FileFilter.java */
/* JADX INFO: loaded from: classes2.dex */
class NoneFilter extends FileFilter {
    @Override // com.neusoft.optimus.utility.file.FileFilter
    public String filterTag() {
        return "none";
    }

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public boolean isFilterEx(String str) {
        return true;
    }

    NoneFilter() {
    }
}
