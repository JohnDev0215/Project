package com.byd.data.common.i;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeFormatUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static String a(long j, String str) {
        return new SimpleDateFormat(str).format(new Date(j));
    }
}
