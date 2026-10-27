package android.graphics;

import android.graphics.fonts.FontVariationAxis;
import android.media.TtmlUtils;
import android.media.tv.TvContract;
import android.os.DropBoxManager;
import android.security.KeyChain;
import android.text.FontConfig;
import android.util.Xml;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class FontListParser {
    private static final Pattern FILENAME_WHITESPACE_PATTERN = Pattern.compile("^[ \\n\\r\\t]+|[ \\n\\r\\t]+$");

    public static FontConfig parse(InputStream inputStream) throws XmlPullParserException, IOException {
        return parse(inputStream, "/system/fonts");
    }

    public static FontConfig parse(InputStream inputStream, String str) throws XmlPullParserException, IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            xmlPullParserNewPullParser.nextTag();
            return readFamilies(xmlPullParserNewPullParser, str);
        } finally {
            inputStream.close();
        }
    }

    private static FontConfig readFamilies(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        xmlPullParser.require(2, null, "familyset");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("family")) {
                    arrayList.add(readFamily(xmlPullParser, str));
                } else if (name.equals(KeyChain.EXTRA_ALIAS)) {
                    arrayList2.add(readAlias(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return new FontConfig((FontConfig.Family[]) arrayList.toArray(new FontConfig.Family[arrayList.size()]), (FontConfig.Alias[]) arrayList2.toArray(new FontConfig.Alias[arrayList2.size()]));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.text.FontConfig.Family readFamily(org.xmlpull.v1.XmlPullParser r7, java.lang.String r8) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = 0
            java.lang.String r1 = "name"
            java.lang.String r1 = r7.getAttributeValue(r0, r1)
            java.lang.String r2 = ""
            java.lang.String r3 = "lang"
            java.lang.String r2 = r7.getAttributeValue(r2, r3)
            java.lang.String r3 = "variant"
            java.lang.String r0 = r7.getAttributeValue(r0, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L1d:
            int r4 = r7.next()
            r5 = 3
            r6 = 2
            if (r4 == r5) goto L44
            int r4 = r7.getEventType()
            if (r4 == r6) goto L2c
            goto L1d
        L2c:
            java.lang.String r4 = r7.getName()
            java.lang.String r5 = "font"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L40
            android.text.FontConfig$Font r4 = readFont(r7, r8)
            r3.add(r4)
            goto L1d
        L40:
            skip(r7)
            goto L1d
        L44:
            r7 = 0
            if (r0 == 0) goto L5a
            java.lang.String r8 = "compact"
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L51
            r6 = 1
            goto L5b
        L51:
            java.lang.String r8 = "elegant"
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L5a
            goto L5b
        L5a:
            r6 = r7
        L5b:
            android.text.FontConfig$Family r7 = new android.text.FontConfig$Family
            int r8 = r3.size()
            android.text.FontConfig$Font[] r8 = new android.text.FontConfig.Font[r8]
            java.lang.Object[] r8 = r3.toArray(r8)
            android.text.FontConfig$Font[] r8 = (android.text.FontConfig.Font[]) r8
            r7.<init>(r1, r8, r2, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.FontListParser.readFamily(org.xmlpull.v1.XmlPullParser, java.lang.String):android.text.FontConfig$Family");
    }

    private static FontConfig.Font readFont(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "index");
        int i = attributeValue == null ? 0 : Integer.parseInt(attributeValue);
        ArrayList arrayList = new ArrayList();
        String attributeValue2 = xmlPullParser.getAttributeValue(null, TvContract.PreviewPrograms.COLUMN_WEIGHT);
        int i2 = attributeValue2 == null ? 400 : Integer.parseInt(attributeValue2);
        boolean zEquals = "italic".equals(xmlPullParser.getAttributeValue(null, TtmlUtils.TAG_STYLE));
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "fallbackFor");
        StringBuilder sb = new StringBuilder();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 4) {
                sb.append(xmlPullParser.getText());
            }
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("axis")) {
                    arrayList.add(readAxis(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return new FontConfig.Font(str + FILENAME_WHITESPACE_PATTERN.matcher(sb).replaceAll(""), i, (FontVariationAxis[]) arrayList.toArray(new FontVariationAxis[arrayList.size()]), i2, zEquals, attributeValue3);
    }

    private static FontVariationAxis readAxis(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, DropBoxManager.EXTRA_TAG);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "stylevalue");
        skip(xmlPullParser);
        return new FontVariationAxis(attributeValue, Float.parseFloat(attributeValue2));
    }

    public static FontConfig.Alias readAlias(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, TypedValues.TransitionType.S_TO);
        String attributeValue3 = xmlPullParser.getAttributeValue(null, TvContract.PreviewPrograms.COLUMN_WEIGHT);
        int i = attributeValue3 == null ? 400 : Integer.parseInt(attributeValue3);
        skip(xmlPullParser);
        return new FontConfig.Alias(attributeValue, attributeValue2, i);
    }

    public static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
