package com.byd.cleardata.i;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public class j {
    public static float a(int i, double d2) {
        return new BigDecimal(d2).setScale(i, 4).floatValue();
    }
}
