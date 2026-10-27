package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastCompanionClickTracking;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanionClickTrackingParser {
    @Nonnull
    public static VastCompanionClickTracking parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (attributeName.equals("id")) {
                i = ParserUtils.parseInt(attributeValue);
            }
        }
        return new VastCompanionClickTracking(i, ParserUtils.parseUri(xmlPullParser, "CompanionClickTracking"));
    }
}
