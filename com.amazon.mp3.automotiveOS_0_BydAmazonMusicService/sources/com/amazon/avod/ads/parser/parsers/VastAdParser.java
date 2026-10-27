package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAd;
import com.amazon.avod.ads.parser.vast.VastAdData;
import com.amazon.avod.ads.parser.vast.VastException;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastAdParser {
    @Nonnull
    public static VastAd parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        VastAdData vastAdData;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        VastAdData vastAdData2 = null;
        int i = 0;
        String str = null;
        int i2 = 0;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (attributeName.equals("id")) {
                str = attributeValue;
            } else if (attributeName.equals("sequence")) {
                i2 = ParserUtils.parseInt(attributeValue);
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "Ad")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("Wrapper")) {
                vastAdData = VastWrapperParser.parse(xmlPullParser);
            } else if (name.equals("InLine")) {
                vastAdData = VastInlineParser.parse(xmlPullParser);
            }
            i++;
            vastAdData2 = vastAdData;
        }
        if (i != 1) {
            throw new VastException(VastError.AD_MULTIPLE_CHILD_TAGS);
        }
        return new VastAd(str, Integer.valueOf(i2), vastAdData2);
    }
}
