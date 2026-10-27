package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastIcon;
import com.amazon.avod.ads.parser.vast.VastIconClicks;
import com.amazon.avod.ads.parser.vast.VastResource;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastIconParser {
    @Nonnull
    public static VastIcon parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        VastResource vastResource;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        VastTimeSpan xmlTime = null;
        VastTimeSpan xmlTime2 = null;
        String str4 = null;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (attributeName.equals("program")) {
                str = attributeValue;
            } else if (attributeName.equals("width")) {
                i = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("height")) {
                i2 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("xPosition")) {
                str2 = attributeValue;
            } else if (attributeName.equals("yPosition")) {
                str3 = attributeValue;
            } else if (attributeName.equals("duration")) {
                xmlTime2 = VastTimeSpan.parseXmlTime(attributeValue);
            } else if (attributeName.equals("offset")) {
                xmlTime = VastTimeSpan.parseXmlTime(attributeValue);
            } else if (attributeName.equals("apiFramework")) {
                str4 = attributeValue;
            }
        }
        VastResource vastResource2 = null;
        VastIconClicks vastIconClicks = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "Icon")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("StaticResource")) {
                vastResource = VastResourceStaticParser.parse(xmlPullParser);
            } else if (name.equals("IFrameResource")) {
                vastResource = VastResourceIFrameParser.parse(xmlPullParser);
            } else if (name.equals("HTMLResource")) {
                vastResource = VastResourceHTMLParser.parse(xmlPullParser);
            } else if (name.equals("IconClicks")) {
                vastIconClicks = VastIconClicksParser.parse(xmlPullParser);
            } else if (name.equals("IconViewTracking")) {
                builder.add(ParserUtils.parseUri(xmlPullParser, name));
            }
            vastResource2 = vastResource;
        }
        return new VastIcon(str, i, i2, str2, str3, xmlTime, xmlTime2, str4, vastResource2, vastIconClicks, builder.build());
    }
}
