package android.media;

import android.app.backup.FullBackup;
import android.app.blob.XmlTags;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: TtmlRenderer.java */
/* JADX INFO: loaded from: classes2.dex */
final class TtmlUtils {
    public static final String ATTR_BEGIN = "begin";
    public static final String ATTR_DURATION = "dur";
    public static final String ATTR_END = "end";
    public static final long INVALID_TIMESTAMP = Long.MAX_VALUE;
    public static final String PCDATA = "#pcdata";
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SMPTE_DATA = "smpte:data";
    public static final String TAG_SMPTE_IMAGE = "smpte:image";
    public static final String TAG_SMPTE_INFORMATION = "smpte:information";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");

    private TtmlUtils() {
    }

    public static long parseTimeExpression(String str, int i, int i2, int i3) throws NumberFormatException {
        double d;
        double d2;
        Matcher matcher = CLOCK_TIME.matcher(str);
        if (matcher.matches()) {
            double d3 = (Long.parseLong(matcher.group(1)) * 3600) + (Long.parseLong(matcher.group(2)) * 60) + Long.parseLong(matcher.group(3));
            String strGroup = matcher.group(4);
            return (long) ((d3 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d) + (matcher.group(5) != null ? Long.parseLong(r0) / ((double) i) : 0.0d) + (matcher.group(6) != null ? (Long.parseLong(r0) / ((double) i2)) / ((double) i) : 0.0d)) * 1000.0d);
        }
        Matcher matcher2 = OFFSET_TIME.matcher(str);
        if (matcher2.matches()) {
            double d4 = Double.parseDouble(matcher2.group(1));
            String strGroup2 = matcher2.group(2);
            if (strGroup2.equals("h")) {
                d2 = 3.6E9d;
            } else {
                if (!strGroup2.equals("m")) {
                    if (strGroup2.equals(XmlTags.TAG_SESSION)) {
                        d4 *= 1000000.0d;
                    } else if (strGroup2.equals("ms")) {
                        d4 *= 1000.0d;
                    } else {
                        if (strGroup2.equals(FullBackup.FILES_TREE_TOKEN)) {
                            d = i;
                        } else if (strGroup2.equals("t")) {
                            d = i3;
                        }
                        d4 /= d;
                        d4 *= 1000000.0d;
                    }
                    return (long) d4;
                }
                d2 = 6.0E7d;
            }
            d4 *= d2;
            return (long) d4;
        }
        throw new NumberFormatException("Malformed time expression : " + str);
    }

    public static String applyDefaultSpacePolicy(String str) {
        return applySpacePolicy(str, true);
    }

    public static String applySpacePolicy(String str, boolean z) {
        String strReplaceAll = str.replaceAll("\r\n", BydAdbHelper.COMMAND_LINE_END).replaceAll(" *\n *", BydAdbHelper.COMMAND_LINE_END);
        if (z) {
            strReplaceAll = strReplaceAll.replaceAll(BydAdbHelper.COMMAND_LINE_END, " ");
        }
        return strReplaceAll.replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    public static String extractText(TtmlNode ttmlNode, long j, long j2) {
        StringBuilder sb = new StringBuilder();
        extractText(ttmlNode, j, j2, sb, false);
        return sb.toString().replaceAll("\n$", "");
    }

    private static void extractText(TtmlNode ttmlNode, long j, long j2, StringBuilder sb, boolean z) {
        if (ttmlNode.mName.equals(PCDATA) && z) {
            sb.append(ttmlNode.mText);
            return;
        }
        if (ttmlNode.mName.equals(TAG_BR) && z) {
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            return;
        }
        if (!ttmlNode.mName.equals(TAG_METADATA) && ttmlNode.isActive(j, j2)) {
            boolean zEquals = ttmlNode.mName.equals("p");
            int length = sb.length();
            for (int i = 0; i < ttmlNode.mChildren.size(); i++) {
                extractText(ttmlNode.mChildren.get(i), j, j2, sb, zEquals || z);
            }
            if (!zEquals || length == sb.length()) {
                return;
            }
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
    }

    public static String extractTtmlFragment(TtmlNode ttmlNode, long j, long j2) {
        StringBuilder sb = new StringBuilder();
        extractTtmlFragment(ttmlNode, j, j2, sb);
        return sb.toString();
    }

    private static void extractTtmlFragment(TtmlNode ttmlNode, long j, long j2, StringBuilder sb) {
        if (ttmlNode.mName.equals(PCDATA)) {
            sb.append(ttmlNode.mText);
            return;
        }
        if (ttmlNode.mName.equals(TAG_BR)) {
            sb.append("<br/>");
            return;
        }
        if (ttmlNode.isActive(j, j2)) {
            sb.append("<");
            sb.append(ttmlNode.mName);
            sb.append(ttmlNode.mAttributes);
            sb.append(">");
            for (int i = 0; i < ttmlNode.mChildren.size(); i++) {
                extractTtmlFragment(ttmlNode.mChildren.get(i), j, j2, sb);
            }
            sb.append("</");
            sb.append(ttmlNode.mName);
            sb.append(">");
        }
    }
}
