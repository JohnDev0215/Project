package com.neusoft.optimus.utility.file;

/* JADX INFO: compiled from: FileFilter.java */
/* JADX INFO: loaded from: classes2.dex */
class ImageFileFilter extends FileFilter {
    @Override // com.neusoft.optimus.utility.file.FileFilter
    public String filterTag() {
        return "image";
    }

    public ImageFileFilter(FileFilter fileFilter) {
        super(fileFilter);
    }

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public boolean isFilterEx(String str) {
        String lowerCase = str.substring(str.length() - 5).toLowerCase();
        return FileUtil.isSpecialFile(lowerCase, ".jpg") || FileUtil.isSpecialFile(lowerCase, ".jpeg") || FileUtil.isSpecialFile(lowerCase, ".png") || FileUtil.isSpecialFile(lowerCase, ".bmp") || FileUtil.isSpecialFile(lowerCase, ".gif") || FileUtil.isSpecialFile(lowerCase, ".raw");
    }
}
