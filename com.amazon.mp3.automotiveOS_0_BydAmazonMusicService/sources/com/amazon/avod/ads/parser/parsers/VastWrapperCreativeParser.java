package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastWrapperCreative;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperCreativeParser {
    @Nonnull
    public static VastWrapperCreative parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        Object obj2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("AdID")) {
                str = attributeValue;
            } else if (attributeName.equals("id")) {
                str2 = attributeValue;
            } else if (attributeName.equals("sequence")) {
                str3 = attributeValue;
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "Creative")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("Linear")) {
                obj = VastWrapperLinearParser.parse(xmlPullParser);
            } else if (name.equals("CompanionAds")) {
                obj = VastCompanionAdsParser.parse(xmlPullParser);
            } else if (name.equals("NonLinearAds")) {
                obj = VastWrapperNonLinearAdsParser.parse(xmlPullParser);
            }
            obj2 = obj;
        }
        return new VastWrapperCreative(str2, str3, str, obj2);
    }
}
