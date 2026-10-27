package com.byd.auto_emanual.util;

import com.byd.auto_emanual.MApp;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ServerUtil {
    public static String getServerPara(int resId) {
        return MApp.context.getResources().getString(resId);
    }

    public static List<String> getCountryCodeList(int resId) {
        return Arrays.asList(MApp.context.getResources().getStringArray(resId));
    }
}
