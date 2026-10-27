package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastImpression;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastImpressionParser {
    @Nonnull
    public static VastImpression parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        String str = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("id")) {
                str = attributeValue;
            }
        }
        return new VastImpression(str, ParserUtils.parseUri(xmlPullParser, "Impression"));
    }
}
