package com.neusoft.optimus.utility.file;

/* JADX INFO: compiled from: FileFilter.java */
/* JADX INFO: loaded from: classes2.dex */
class VideoFileFilter extends FileFilter {
    private String[] suffixs;

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public String filterTag() {
        return "video";
    }

    public VideoFileFilter(FileFilter fileFilter) {
        super(fileFilter);
        this.suffixs = new String[]{".avi", ".3gp", ".3g2", ".flv", ".f4v", ".webm", ".mov", ".m4v", ".mkv", ".mp4"};
    }

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public boolean isFilterEx(String str) {
        return FileUtil.checkFile(this.suffixs, str.substring(str.length() - 5).toLowerCase());
    }
}
