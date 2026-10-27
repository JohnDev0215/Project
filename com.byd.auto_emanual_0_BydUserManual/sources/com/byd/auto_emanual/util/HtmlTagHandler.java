package com.byd.auto_emanual.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.HttpUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.config.Ini;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: classes.dex */
public class HtmlTagHandler implements Html.TagHandler {
    public static final String HTML_TAG = "span-k";
    private static final String TAG = "HtmlTagHandler";
    private Context context;
    private int defaultSizeInAndroid;
    private int endIndex;
    private int startIndex;
    private String tagSize;
    private Map<String, String> attributes = new HashMap();
    private int defaultSizeInWeb = 18;

    public HtmlTagHandler(Context context, String tagSize) {
        this.context = context;
        this.tagSize = tagSize;
        this.defaultSizeInAndroid = context.getResources().getDimensionPixelOffset(R.dimen.media_image_text_content_text_size);
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        MLog.d(TAG, "opening = " + opening + ", tag = " + tag);
        if (tag.equalsIgnoreCase(this.tagSize)) {
            parseAttributes(xmlReader);
            if (opening) {
                handStartTag(tag, output, xmlReader);
            } else {
                handEndTag(tag, output, xmlReader);
            }
        }
    }

    private void handEndTag(String tag, Editable output, XMLReader xmlReader) {
        this.endIndex = output.length();
        String str = this.attributes.get("color");
        String str2 = this.attributes.get("font-size");
        if (str2 != null) {
            str2 = str2.split("px")[0];
        }
        if (!TextUtils.isEmpty(str2)) {
            output.setSpan(new AbsoluteSizeSpan(getRealSize(str2)), this.startIndex, this.endIndex, 33);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        output.setSpan(new ForegroundColorSpan(Color.parseColor(str)), this.startIndex, this.endIndex, 33);
    }

    public void handStartTag(String tag, Editable output, XMLReader xmlReader) {
        this.startIndex = output.length();
    }

    private void parseAttributes(final XMLReader xmlReader) {
        try {
            Field declaredField = xmlReader.getClass().getDeclaredField("theNewElement");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(xmlReader);
            Field declaredField2 = obj.getClass().getDeclaredField("theAtts");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj2.getClass().getDeclaredField(HttpUtil.Response.DATA);
            declaredField3.setAccessible(true);
            String[] strArr = (String[]) declaredField3.get(obj2);
            Field declaredField4 = obj2.getClass().getDeclaredField("length");
            declaredField4.setAccessible(true);
            int iIntValue = ((Integer) declaredField4.get(obj2)).intValue();
            for (int i = 0; i < iIntValue; i++) {
                parseAttributesValue(strArr[(i * 5) + 4]);
            }
        } catch (Exception unused) {
        }
    }

    private void parseAttributesValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        for (String str : value.split(Ini.COMMENT_SEMICOLON)) {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit = str.split(":");
                this.attributes.put(strArrSplit[0].trim(), strArrSplit[1].trim());
            }
        }
    }

    private int getRealSize(String sizeString) {
        return (int) (((Integer.parseInt(sizeString) * 1.0f) / this.defaultSizeInWeb) * this.defaultSizeInAndroid);
    }
}
