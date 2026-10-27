package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vmap.VmapAdSource;
import com.amazon.avod.ads.parser.vmap.VmapAdTagURI;
import com.amazon.avod.ads.parser.vmap.VmapCustomAdData;
import com.amazon.avod.ads.parser.vmap.VmapException;
import com.amazon.avod.ads.parser.vmap.VmapVastAdData;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdSourceParser {
    @Nonnull
    public static VmapAdSource parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        String str = null;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (attributeName.equals("id")) {
                str = attributeValue;
            } else if (attributeName.equals("allowMultipleAds")) {
                z = ParserUtils.parseBoolean(attributeValue);
            } else if (attributeName.equals("followRedirects")) {
                z2 = ParserUtils.parseBoolean(attributeValue);
            }
        }
        VmapVastAdData vmapVastAdData = null;
        VmapAdTagURI vmapAdTagURI = null;
        VmapCustomAdData vmapCustomAdData = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "AdSource")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("VASTAdData")) {
                i++;
                vmapVastAdData = VmapVastAdDataParser.parse(xmlPullParser);
            } else if (name.equals("CustomAdData")) {
                i++;
                vmapCustomAdData = VmapCustomAdDataParser.parse(xmlPullParser);
            } else if (name.equals("AdTagURI")) {
                i++;
                vmapAdTagURI = VmapAdTagURIParser.parse(xmlPullParser);
            }
        }
        if (i != 1) {
            throw new VmapException(VmapError.ADSOURCE_MULTIPLE_CHILD_TAGS);
        }
        return new VmapAdSource(str, Boolean.valueOf(z), Boolean.valueOf(z2), vmapVastAdData, vmapAdTagURI, vmapCustomAdData);
    }
}
