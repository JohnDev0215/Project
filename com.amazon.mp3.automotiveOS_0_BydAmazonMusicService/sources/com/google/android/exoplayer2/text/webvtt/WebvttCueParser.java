package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.webvtt.WebvttCue;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes6.dex */
final class WebvttCueParser {
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    private final StringBuilder textBuilder = new StringBuilder();

    boolean parseCue(ParsableByteArray parsableByteArray, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        String line = parsableByteArray.readLine();
        if (line == null) {
            return false;
        }
        Pattern pattern = CUE_HEADER_PATTERN;
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return parseCue(null, matcher, parsableByteArray, builder, this.textBuilder, list);
        }
        String line2 = parsableByteArray.readLine();
        if (line2 == null) {
            return false;
        }
        Matcher matcher2 = pattern.matcher(line2);
        if (matcher2.matches()) {
            return parseCue(line.trim(), matcher2, parsableByteArray, builder, this.textBuilder, list);
        }
        return false;
    }

    static void parseCueSettingsList(String str, WebvttCue.Builder builder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            try {
                if ("line".equals(strGroup)) {
                    parseLineAttribute(strGroup2, builder);
                } else if ("align".equals(strGroup)) {
                    builder.setTextAlignment(parseTextAlignment(strGroup2));
                } else if ("position".equals(strGroup)) {
                    parsePositionAttribute(strGroup2, builder);
                } else if ("size".equals(strGroup)) {
                    builder.setWidth(WebvttParserUtil.parsePercentage(strGroup2));
                } else {
                    Log.w("WebvttCueParser", "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    static void parseCueText(String str, String str2, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Stack stack = new Stack();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char cCharAt = str2.charAt(i);
            if (cCharAt == '&') {
                i++;
                int iIndexOf = str2.indexOf(59, i);
                int iIndexOf2 = str2.indexOf(32, i);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    applyEntity(str2.substring(i, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append(" ");
                    }
                    i = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt == '<') {
                int iFindEndOfTag = i + 1;
                if (iFindEndOfTag < str2.length()) {
                    boolean z = str2.charAt(iFindEndOfTag) == '/';
                    iFindEndOfTag = findEndOfTag(str2, iFindEndOfTag);
                    int i2 = iFindEndOfTag - 2;
                    boolean z2 = str2.charAt(i2) == '/';
                    int i3 = i + (z ? 2 : 1);
                    if (!z2) {
                        i2 = iFindEndOfTag - 1;
                    }
                    String strSubstring = str2.substring(i3, i2);
                    String tagName = getTagName(strSubstring);
                    if (tagName != null && isSupportedTag(tagName)) {
                        if (z) {
                            while (!stack.isEmpty()) {
                                StartTag startTag = (StartTag) stack.pop();
                                applySpansForTag(str, startTag, spannableStringBuilder, list, arrayList);
                                if (startTag.name.equals(tagName)) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            stack.push(StartTag.buildStartTag(strSubstring, spannableStringBuilder.length()));
                        }
                    }
                }
                i = iFindEndOfTag;
            } else {
                spannableStringBuilder.append(cCharAt);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            applySpansForTag(str, (StartTag) stack.pop(), spannableStringBuilder, list, arrayList);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), spannableStringBuilder, list, arrayList);
        builder.setText(spannableStringBuilder);
    }

    private static boolean parseCue(String str, Matcher matcher, ParsableByteArray parsableByteArray, WebvttCue.Builder builder, StringBuilder sb, List<WebvttCssStyle> list) {
        try {
            builder.setStartTime(WebvttParserUtil.parseTimestampUs(matcher.group(1))).setEndTime(WebvttParserUtil.parseTimestampUs(matcher.group(2)));
            parseCueSettingsList(matcher.group(3), builder);
            sb.setLength(0);
            while (true) {
                String line = parsableByteArray.readLine();
                if (!TextUtils.isEmpty(line)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line.trim());
                } else {
                    parseCueText(str, sb.toString(), builder, list);
                    return true;
                }
            }
        } catch (NumberFormatException unused) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }

    private static void parseLineAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setLineAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setLineAnchor(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            builder.setLine(WebvttParserUtil.parsePercentage(str)).setLineType(0);
            return;
        }
        int i = Integer.parseInt(str);
        if (i < 0) {
            i--;
        }
        builder.setLine(i).setLineType(1);
    }

    private static void parsePositionAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setPositionAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setPositionAnchor(Integer.MIN_VALUE);
        }
        builder.setPosition(WebvttParserUtil.parsePercentage(str));
    }

    private static int parsePositionAnchor(String str) {
        str.hashCode();
        switch (str) {
            case "center":
            case "middle":
                return 1;
            case "end":
                return 2;
            case "start":
                return 0;
            default:
                Log.w("WebvttCueParser", "Invalid anchor value: " + str);
                return Integer.MIN_VALUE;
        }
    }

    private static Layout.Alignment parseTextAlignment(String str) {
        str.hashCode();
        switch (str) {
            case "center":
            case "middle":
                return Layout.Alignment.ALIGN_CENTER;
            case "end":
            case "right":
                return Layout.Alignment.ALIGN_OPPOSITE;
            case "left":
            case "start":
                return Layout.Alignment.ALIGN_NORMAL;
            default:
                Log.w("WebvttCueParser", "Invalid alignment value: " + str);
                return null;
        }
    }

    private static int findEndOfTag(String str, int i) {
        int iIndexOf = str.indexOf(62, i);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    private static void applyEntity(String str, SpannableStringBuilder spannableStringBuilder) {
        str.hashCode();
        switch (str) {
            case "gt":
                spannableStringBuilder.append(Typography.greater);
                break;
            case "lt":
                spannableStringBuilder.append(Typography.less);
                break;
            case "amp":
                spannableStringBuilder.append(Typography.amp);
                break;
            case "nbsp":
                spannableStringBuilder.append(' ');
                break;
            default:
                Log.w("WebvttCueParser", "ignoring unsupported entity: '&" + str + ";'");
                break;
        }
    }

    private static boolean isSupportedTag(String str) {
        str.hashCode();
        switch (str) {
            case "b":
            case "c":
            case "i":
            case "u":
            case "v":
            case "lang":
                return true;
            default:
                return false;
        }
    }

    private static void applySpansForTag(String str, StartTag startTag, SpannableStringBuilder spannableStringBuilder, List<WebvttCssStyle> list, List<StyleMatch> list2) {
        int i;
        int length;
        i = startTag.position;
        length = spannableStringBuilder.length();
        String str2 = startTag.name;
        str2.hashCode();
        switch (str2) {
            case "":
            case "c":
            case "v":
            case "lang":
                break;
            case "b":
                spannableStringBuilder.setSpan(new StyleSpan(1), i, length, 33);
                break;
            case "i":
                spannableStringBuilder.setSpan(new StyleSpan(2), i, length, 33);
                break;
            case "u":
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, length, 33);
                break;
            default:
                return;
        }
        list2.clear();
        getApplicableStyles(list, str, startTag, list2);
        int size = list2.size();
        for (int i2 = 0; i2 < size; i2++) {
            applyStyleToText(spannableStringBuilder, list2.get(i2).style, i, length);
        }
    }

    private static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i, int i2) {
        if (webvttCssStyle == null) {
            return;
        }
        if (webvttCssStyle.getStyle() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(webvttCssStyle.getStyle()), i, i2, 33);
        }
        if (webvttCssStyle.isLinethrough()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (webvttCssStyle.isUnderline()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (webvttCssStyle.hasFontColor()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(webvttCssStyle.getFontColor()), i, i2, 33);
        }
        if (webvttCssStyle.hasBackgroundColor()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i, i2, 33);
        }
        if (webvttCssStyle.getFontFamily() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(webvttCssStyle.getFontFamily()), i, i2, 33);
        }
        if (webvttCssStyle.getTextAlign() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(webvttCssStyle.getTextAlign()), i, i2, 33);
        }
        int fontSizeUnit = webvttCssStyle.getFontSizeUnit();
        if (fontSizeUnit == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) webvttCssStyle.getFontSize(), true), i, i2, 33);
        } else if (fontSizeUnit == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize()), i, i2, 33);
        } else {
            if (fontSizeUnit != 3) {
                return;
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize() / 100.0f), i, i2, 33);
        }
    }

    private static String getTagName(String str) {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return strTrim.split("[ \\.]")[0];
    }

    private static void getApplicableStyles(List<WebvttCssStyle> list, String str, StartTag startTag, List<StyleMatch> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WebvttCssStyle webvttCssStyle = list.get(i);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                list2.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(list2);
    }

    private static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i, WebvttCssStyle webvttCssStyle) {
            this.score = i;
            this.style = webvttCssStyle;
        }

        @Override // java.lang.Comparable
        public int compareTo(StyleMatch styleMatch) {
            return this.score - styleMatch.score;
        }
    }

    private static final class StartTag {
        private static final String[] NO_CLASSES = new String[0];
        public final String[] classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String str, int i, String str2, String[] strArr) {
            this.position = i;
            this.name = str;
            this.voice = str2;
            this.classes = strArr;
        }

        public static StartTag buildStartTag(String str, int i) {
            String str2;
            String[] strArr;
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            int iIndexOf = strTrim.indexOf(" ");
            if (iIndexOf == -1) {
                str2 = "";
            } else {
                String strTrim2 = strTrim.substring(iIndexOf).trim();
                strTrim = strTrim.substring(0, iIndexOf);
                str2 = strTrim2;
            }
            String[] strArrSplit = strTrim.split("\\.");
            String str3 = strArrSplit[0];
            if (strArrSplit.length > 1) {
                strArr = (String[]) Arrays.copyOfRange(strArrSplit, 1, strArrSplit.length);
            } else {
                strArr = NO_CLASSES;
            }
            return new StartTag(str3, i, str2, strArr);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", new String[0]);
        }
    }
}
