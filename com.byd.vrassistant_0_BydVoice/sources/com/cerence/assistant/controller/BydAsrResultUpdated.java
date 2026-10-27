package com.cerence.assistant.controller;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BydAsrResultUpdated {
    private static final String[] mappingWordsArray = {"嗰檔", "大檔", "濃檔", "淡檔", "低檔", "中檔", "高檔", "細檔", "兩檔", "零檔", "一檔", "二檔", "三檔", "四檔", "五檔", "六檔", "七檔", "八檔", "九檔", "十檔", "两档", "低档", "高档", "中档", "零档", "一档", "二档", "三档", "四档", "五档", "六档", "七档", "八档", "九档", "十档", "档位"};
    private static final Map<String, String> replaceWordMapTable;
    public String mUtterance;

    public BydAsrResultUpdated(String str) {
        this.mUtterance = str;
        parseParam();
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public String targetStringReplace(String str) {
        String strReplaceAll = str;
        for (Map.Entry<String, String> entry : replaceWordMapTable.entrySet()) {
            for (String str2 : mappingWordsArray) {
                if (str.matches(".*\\d" + entry.getKey() + ".*") || str.contains(str2)) {
                    strReplaceAll = strReplaceAll.replaceAll(entry.getKey(), entry.getValue());
                }
            }
        }
        return strReplaceAll;
    }

    protected void parseParam() {
        if (TextUtils.isEmpty(this.mUtterance)) {
            return;
        }
        this.mUtterance = targetStringReplace(this.mUtterance);
    }

    static {
        HashMap map = new HashMap();
        replaceWordMapTable = map;
        map.put("档", "挡");
        map.put("檔", "擋");
    }
}
