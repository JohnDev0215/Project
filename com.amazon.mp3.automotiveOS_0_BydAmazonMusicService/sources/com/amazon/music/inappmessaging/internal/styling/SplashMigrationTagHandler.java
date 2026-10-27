package com.amazon.music.inappmessaging.internal.styling;

import android.text.Editable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import com.amazon.music.inappmessaging.internal.styling.HtmlParser;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes4.dex */
public class SplashMigrationTagHandler implements HtmlParser.TagHandler {
    private static final String TAG = "SplashMigrationTagHandler";
    private String NON_DECIMAL_REGEX = "\\D+";

    @Override // com.amazon.music.inappmessaging.internal.styling.HtmlParser.TagHandler
    public boolean handleTag(boolean opening, String tag, Editable output, Attributes attributes) {
        if (tag.startsWith(SplashMigrationHTMLTransformer.FONT_SIZE_TAG_NAME)) {
            return processFontSize(opening, output, tag);
        }
        if (tag.equals(SplashMigrationHTMLTransformer.RED_STRIKETHROUGH_TAG_NAME)) {
            return processLineThrough(opening, output, attributes);
        }
        return false;
    }

    public boolean processLineThrough(boolean opening, Editable output, Attributes attributes) {
        int length = output.length();
        try {
            if (opening) {
                output.setSpan(new RedStrikeThroughSpan(HtmlParser.getValue(attributes, "font-color")), length, length, 17);
            } else {
                Object last = getLast(output, RedStrikeThroughSpan.class);
                int spanStart = output.getSpanStart(last);
                output.removeSpan(last);
                if (spanStart != length) {
                    output.setSpan(last, spanStart, length, 33);
                }
            }
            return false;
        } catch (Exception unused) {
            Log.e(TAG, "Failed to build spannable for the custom html tag: red-strike");
            return false;
        }
    }

    public boolean processFontSize(boolean opening, Editable output, String tag) {
        Object last;
        try {
            int length = output.length();
            float f = Float.parseFloat(tag.replaceAll(this.NON_DECIMAL_REGEX, ""));
            boolean zContains = tag.contains(SplashMigrationHTMLTransformer.PERCENTAGE_INDICATOR);
            boolean z = tag.contains("dp") || tag.contains("dip");
            if (!opening) {
                if (zContains) {
                    last = getLast(output, RelativeSizeSpan.class);
                } else {
                    last = getLast(output, AbsoluteSizeSpan.class);
                }
                int spanStart = output.getSpanStart(last);
                output.removeSpan(last);
                if (spanStart != length) {
                    output.setSpan(last, spanStart, length, 33);
                }
            } else if (zContains) {
                output.setSpan(new RelativeSizeSpan(f / 100.0f), length, length, 17);
            } else {
                output.setSpan(new AbsoluteSizeSpan((int) f, z), length, length, 17);
            }
        } catch (Exception unused) {
            Log.e(TAG, "Failed to build spannable for the custom html tag: fontSize");
        }
        return false;
    }

    private Object getLast(Editable text, Class kind) {
        Object[] spans = text.getSpans(0, text.length(), kind);
        if (spans.length == 0) {
            return null;
        }
        for (int length = spans.length - 1; length >= 0; length--) {
            if (text.getSpanFlags(spans[length]) == 17) {
                return spans[length];
            }
        }
        return null;
    }
}
