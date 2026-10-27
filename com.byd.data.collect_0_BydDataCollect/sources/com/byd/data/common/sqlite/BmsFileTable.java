package com.byd.data.common.sqlite;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class BmsFileTable extends CloudFileTable {
    @Override // com.byd.data.common.sqlite.CloudFileTable
    protected String f() {
        return "app_bms";
    }

    @Override // com.byd.data.common.sqlite.CloudFileTable
    protected String i(File file, byte b2, long j) {
        String name = file.getName();
        return name.substring(0, name.lastIndexOf(".data")) + "-" + (j / 1000) + ".zip";
    }

    @Override // com.byd.data.common.sqlite.CloudFileTable
    public void w(String str, com.byd.data.common.c.d.c cVar) {
        String string = Integer.toString(2);
        if (string.equals(str)) {
            super.v("type =?", new String[]{str}, cVar);
            return;
        }
        super.v("type IN (?, ?)", new String[]{str, string}, cVar);
    }
}
