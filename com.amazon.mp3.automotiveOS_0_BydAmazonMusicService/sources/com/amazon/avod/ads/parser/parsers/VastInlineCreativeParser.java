package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastException;
import com.amazon.avod.ads.parser.vast.VastInlineCreative;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineCreativeParser {
    @Nonnull
    public static VastInlineCreative parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        Object obj2 = null;
        int i2 = 0;
        String str = null;
        String str2 = null;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (attributeName.equals("AdID")) {
                str = attributeValue;
            } else if (attributeName.equals("id")) {
                str2 = attributeValue;
            } else if (attributeName.equals("sequence")) {
                i2 = ParserUtils.parseInt(attributeValue);
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "Creative")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("Linear")) {
                obj = VastInlineLinearParser.parse(xmlPullParser);
            } else if (name.equals("CompanionAds")) {
                obj = VastCompanionAdsParser.parse(xmlPullParser);
            } else if (name.equals("NonLinearAds")) {
                obj = VastInlineNonLinearAdsParser.parse(xmlPullParser);
            }
            i++;
            obj2 = obj;
        }
        if (i != 1) {
            throw new VastException(VastError.CREATIVE_EXCESS_CHILD_TAGS);
        }
        return new VastInlineCreative(str2, i2, str, obj2);
    }
}
