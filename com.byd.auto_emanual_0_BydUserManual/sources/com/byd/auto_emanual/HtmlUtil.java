package com.byd.auto_emanual;

import android.text.TextUtils;
import com.byd.auto_emanual.util.HtmlTagHandler;
import com.byd.auto_emanual.util.MLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HtmlUtil {
    public static final String IMAGE = "image:";
    private static final String TAG = "HtmlUtil";
    private static final String TAG_COLOR = "color:";
    private static final String TAG_FONT = "font-size:";
    private static final String TAG_IMG = "<img";
    private static final String TAG_P_END = "</p>";
    private static final String TAG_P_START = "<p>";
    private static final String TAG_QUOTATION = "\"";
    private static final String TAG_SPAN = "span";
    private static final String TAG_SPAN_END = "</span>";
    private static final String TAG_SPAN_START = "<span";
    private static final String TAG_SRC = "src=";
    public static final String TEXT = "text:";

    public static List<String> parse(String html) {
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = html.split(TAG_P_START);
        if (strArrSplit == null) {
            MLog.d(TAG, "error html format");
            return arrayList;
        }
        for (String str : strArrSplit) {
            if (!TextUtils.isEmpty(str)) {
                String strReplace = str.replace(TAG_P_END, "");
                if (strReplace.contains(TAG_IMG)) {
                    parseImage(arrayList, strReplace);
                } else {
                    parseText(arrayList, strReplace);
                }
            }
        }
        return arrayList;
    }

    private static void parseText(List<String> list, String section) {
        list.add(TEXT + section.replaceAll(TAG_SPAN, HtmlTagHandler.HTML_TAG));
    }

    private static void parseImage(List<String> list, String section) {
        String strSubstring = section.substring(section.indexOf(TAG_SRC) + 4);
        int iIndexOf = strSubstring.indexOf(TAG_QUOTATION) + 1;
        list.add(IMAGE + strSubstring.substring(iIndexOf, strSubstring.indexOf(TAG_QUOTATION, iIndexOf)));
    }
}
