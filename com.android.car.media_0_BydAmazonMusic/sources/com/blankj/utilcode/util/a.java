package com.blankj.utilcode.util;

import com.blankj.utilcode.util.CacheDiskUtils;
import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: compiled from: CacheDiskUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements FilenameFilter {
    public a(CacheDiskUtils.c cVar) {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.startsWith("cdu_");
    }
}
