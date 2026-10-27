package com.byd.card.utils;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PinyinUtils {
    public static void initPinyin() {
        Pinyin.init(Pinyin.newConfig().with(new PinyinMapDict() { // from class: com.byd.card.utils.PinyinUtils.1
            @Override // com.github.promeg.pinyinhelper.PinyinMapDict
            public Map<String, String[]> mapping() {
                HashMap map = new HashMap();
                map.put("空调", new String[]{"KONG", "TIAO"});
                return map;
            }
        }));
    }
}
