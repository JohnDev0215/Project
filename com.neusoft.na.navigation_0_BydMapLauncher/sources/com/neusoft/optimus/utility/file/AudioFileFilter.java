package com.neusoft.optimus.utility.file;

import android.car.Car;

/* JADX INFO: compiled from: FileFilter.java */
/* JADX INFO: loaded from: classes2.dex */
class AudioFileFilter extends FileFilter {
    private String[] suffixs;

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public String filterTag() {
        return Car.AUDIO_SERVICE;
    }

    public AudioFileFilter(FileFilter fileFilter) {
        super(fileFilter);
        this.suffixs = new String[]{".aac", ".m4a", ".amr", ".awb", ".mid", ".midi", ".smf", ".rtttl", ".xmf", ".rtx", ".ota", ".imy", ".mp2", ".mp3", ".ogg", ".oga", ".wav", ".ape", ".flac", ".caf", ".opus"};
    }

    @Override // com.neusoft.optimus.utility.file.FileFilter
    public boolean isFilterEx(String str) {
        return FileUtil.checkFile(this.suffixs, str.substring(str.length() - 5).toLowerCase());
    }
}
