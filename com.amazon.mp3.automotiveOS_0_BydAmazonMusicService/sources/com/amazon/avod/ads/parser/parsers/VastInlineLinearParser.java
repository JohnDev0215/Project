package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAdParameters;
import com.amazon.avod.ads.parser.vast.VastInlineLinear;
import com.amazon.avod.ads.parser.vast.VastMediaFile;
import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastVideoClicks;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineLinearParser {
    @Nonnull
    public static VastInlineLinear parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        VastOffset xmlString = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("skipOffset")) {
                xmlString = VastOffset.parseXmlString(attributeValue);
            }
        }
        List<VastTracking> list = null;
        VastVideoClicks vastVideoClicks = null;
        VastTimeSpan vastTimeSpan = null;
        VastAdParameters vastAdParameters = null;
        List<VastMediaFile> list2 = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "Linear")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("AdParameters")) {
                vastAdParameters = VastAdParametersParser.parse(xmlPullParser);
            } else if (name.equals("Duration")) {
                vastTimeSpan = VastInlineLinearDurationParser.parse(xmlPullParser);
            } else if (name.equals("MediaFiles")) {
                list2 = VastMediaFilesParser.parse(xmlPullParser);
            } else if (name.equals("TrackingEvents")) {
                list = VastTrackingEventsParser.parse(xmlPullParser);
            } else if (name.equals("VideoClicks")) {
                vastVideoClicks = VastVideoClicksParser.parse(xmlPullParser);
            } else if (name.equals("Icons")) {
                builder.add(VastIconParser.parse(xmlPullParser));
            }
        }
        return new VastInlineLinear(builder.build(), list, vastVideoClicks, xmlString, vastTimeSpan, vastAdParameters, list2);
    }
}
