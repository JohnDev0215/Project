package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAdParameters;
import com.amazon.avod.ads.parser.vast.VastInlineNonLinear;
import com.amazon.avod.ads.parser.vast.VastResource;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineNonLinearParser {
    @Nonnull
    public static VastInlineNonLinear parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        VastResource vastResource;
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        Boolean boolValueOf = null;
        Boolean boolValueOf2 = null;
        VastTimeSpan xmlTime = null;
        String str2 = null;
        for (int i5 = 0; i5 < attributeCount; i5++) {
            String attributeName = xmlPullParser.getAttributeName(i5);
            String attributeValue = xmlPullParser.getAttributeValue(i5);
            if (attributeName.equals("id")) {
                str = attributeValue;
            } else if (attributeName.equals("width")) {
                i = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("height")) {
                i2 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("expandedWidth")) {
                i3 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("expandedHeight")) {
                i4 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("scalable")) {
                boolValueOf = Boolean.valueOf(ParserUtils.parseBoolean(attributeValue));
            } else if (attributeName.equals("maintainAspectRatio")) {
                boolValueOf2 = Boolean.valueOf(ParserUtils.parseBoolean(attributeValue));
            } else if (attributeName.equals("minSuggestedDuration")) {
                xmlTime = VastTimeSpan.parseXmlTime(attributeValue);
            } else if (attributeName.equals("apiFramework")) {
                str2 = attributeValue;
            }
        }
        List<VastTracking> list = null;
        VastResource vastResource2 = null;
        URI uri = null;
        VastAdParameters vastAdParameters = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "NonLinear")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("StaticResource")) {
                vastResource = VastResourceStaticParser.parse(xmlPullParser);
            } else if (name.equals("IFrameResource")) {
                vastResource = VastResourceIFrameParser.parse(xmlPullParser);
            } else if (name.equals("HTMLResource")) {
                vastResource = VastResourceHTMLParser.parse(xmlPullParser);
            } else if (name.equals("AdParameters")) {
                vastAdParameters = VastAdParametersParser.parse(xmlPullParser);
            } else if (name.equals("NonLinearClickThrough")) {
                uri = ParserUtils.parseUri(xmlPullParser, name);
            } else if (name.equals("NonLinearClickTracking")) {
                builder.add(ParserUtils.parseUri(xmlPullParser, name));
            } else if (name.equals("TrackingEvents")) {
                list = VastTrackingEventsParser.parse(xmlPullParser);
            }
            vastResource2 = vastResource;
        }
        return new VastInlineNonLinear(str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), boolValueOf, boolValueOf2, xmlTime, str2, builder.build(), list, vastResource2, uri, vastAdParameters);
    }
}
