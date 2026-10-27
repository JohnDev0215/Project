package com.amazon.music.inappmessaging.internal.styling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class SplashMigrationHTMLTransformer {
    public static String FONT_SIZE_TAG_NAME = "fontSize";
    public static String PERCENTAGE_INDICATOR = "pct";
    public static String RED_STRIKETHROUGH_TAG_NAME = "red-strike";
    private static final String TAG = "SplashMigrationHTMLTransformer";
    private static Pattern STRIKE_THROUGH_ATTRIBUTE_PATTERN = Pattern.compile("text-decoration:[' ']*line-through;?");
    private static Pattern FONT_SIZE_ATTRIBUTE_PATTERN = Pattern.compile("font-size:[' ']*[0-9]*%?");
    private static Pattern COLOR_PATTERN = Pattern.compile("color:\\s*(#[a-zA-Z0-9_.-]*|[a-zA-Z]*)\\s*");
    private static Pattern STRIKE_THROUGH_TAGS = Pattern.compile("<red-strike>\\s*(.*?)\\s*<\\/red-strike>");

    public static String retagHTML(String html) {
        while (containsStrikeThroughAttribute(html).booleanValue()) {
            html = tagStrikeThrough(html);
        }
        while (containsFontSizeAttribute(html).booleanValue()) {
            html = tagFontSize(html);
        }
        return html;
    }

    private static Boolean containsFontSizeAttribute(String html) {
        return Boolean.valueOf(FONT_SIZE_ATTRIBUTE_PATTERN.matcher(html).find());
    }

    private static Boolean containsStrikeThroughAttribute(String html) {
        return Boolean.valueOf(STRIKE_THROUGH_ATTRIBUTE_PATTERN.matcher(html).find());
    }

    private static String tagFontSize(String html) {
        Matcher matcher = FONT_SIZE_ATTRIBUTE_PATTERN.matcher(html);
        if (!matcher.find()) {
            return html;
        }
        String strInsertTags = insertTags(html, matcher.end(), buildFontSizeReplacementTagString(html.substring(matcher.start(), matcher.end())));
        return strInsertTags.substring(0, matcher.start()) + strInsertTags.substring(matcher.end());
    }

    private static String tagStrikeThrough(String html) {
        Matcher matcher = STRIKE_THROUGH_ATTRIBUTE_PATTERN.matcher(html);
        if (!matcher.find()) {
            return html;
        }
        String strInsertTags = insertTags(html, matcher.end(), RED_STRIKETHROUGH_TAG_NAME);
        int[] iArr = {matcher.start(), matcher.end()};
        getContainingTagBoundaries(strInsertTags, iArr);
        String str = strInsertTags.substring(0, matcher.start()) + strInsertTags.substring(matcher.end());
        int iEnd = iArr[1] - (matcher.end() - matcher.start());
        iArr[1] = iEnd;
        return addStrikeThroughAttributesToTag(removeColorPatternFromTag(str, iArr[0], iEnd), iArr[0], iArr[1]);
    }

    private static String addStrikeThroughAttributesToTag(String html, int tagStart, int tagEnd) {
        Matcher matcher = STRIKE_THROUGH_TAGS.matcher(html);
        if (!matcher.find()) {
            return html;
        }
        String strGroup = matcher.group();
        StringBuilder sb = new StringBuilder();
        Matcher matcher2 = COLOR_PATTERN.matcher(strGroup);
        if (matcher2.find()) {
            sb.append(" font-color=" + matcher2.group(1).trim());
        }
        return StringUtils.isNotBlank(sb.toString()) ? new StringBuilder(html).insert(matcher.start() + RED_STRIKETHROUGH_TAG_NAME.length() + 1, (CharSequence) sb).toString() : html;
    }

    private static String insertTags(String html, int attributePos, String tagName) {
        int iFindInsertTagClosePos = findInsertTagClosePos(html, attributePos);
        String string = new StringBuilder(html).insert(iFindInsertTagClosePos, "</" + tagName + ">").toString();
        int iFindInsertTagOpenPos = findInsertTagOpenPos(string, attributePos) + 1;
        return new StringBuilder(string).insert(iFindInsertTagOpenPos, "<" + tagName + ">").toString();
    }

    private static String buildFontSizeReplacementTagString(String originalString) {
        return originalString.replace("font-size", FONT_SIZE_TAG_NAME).replace("%", PERCENTAGE_INDICATOR).replace(" ", "").replace(":", "");
    }

    private static String removeColorPatternFromTag(String html, int tagStart, int tagEnd) {
        String strSubstring = html.substring(tagStart, tagEnd);
        Matcher matcher = COLOR_PATTERN.matcher(strSubstring);
        if (!matcher.find()) {
            return html;
        }
        return html.substring(0, tagStart) + (strSubstring.substring(0, matcher.start()) + strSubstring.substring(matcher.end())) + html.substring(tagEnd);
    }

    private static int findInsertTagOpenPos(String html, int pos) {
        return html.indexOf(62, pos);
    }

    private static int findInsertTagClosePos(String html, int pos) {
        int i;
        int i2 = 0;
        while (pos < html.length()) {
            char cCharAt = html.charAt(pos);
            if (cCharAt == '<') {
                if (pos < html.length()) {
                    int i3 = pos + 1;
                    i = i3;
                    cCharAt = html.charAt(i3);
                } else {
                    i = pos;
                }
                while (cCharAt == ' ' && i < html.length()) {
                    cCharAt = html.charAt(i);
                    i++;
                }
                if (cCharAt != '/') {
                    i2++;
                } else {
                    i2--;
                    if (i2 == -1) {
                        return pos;
                    }
                }
                pos = i;
            }
            pos++;
        }
        return html.length();
    }

    private static void getContainingTagBoundaries(String html, int[] boundary) {
        int i;
        while (html.charAt(boundary[0]) != '<' && (i = boundary[0]) > 0) {
            boundary[0] = i - 1;
        }
        while (html.charAt(boundary[1]) != '>' && boundary[1] < html.length()) {
            boundary[1] = boundary[1] + 1;
        }
    }
}
