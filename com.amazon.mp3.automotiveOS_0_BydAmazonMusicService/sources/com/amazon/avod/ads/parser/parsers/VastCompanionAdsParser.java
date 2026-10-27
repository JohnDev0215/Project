package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastCompanionAds;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanionAdsParser {
    @Nonnull
    public static VastCompanionAds parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        VastCompanionAds.AdsRequired adsRequired = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("required")) {
                adsRequired = (VastCompanionAds.AdsRequired) ParserUtils.enumValueOf(VastCompanionAds.AdsRequired.class, attributeValue);
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "CompanionAds")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("Companion")) {
                builder.add(VastCompanionParser.parse(xmlPullParser));
            }
        }
        return new VastCompanionAds(adsRequired, builder.build());
    }
}
