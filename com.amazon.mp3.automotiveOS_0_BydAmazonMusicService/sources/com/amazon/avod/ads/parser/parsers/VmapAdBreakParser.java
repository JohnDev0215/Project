package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.amazon.avod.ads.parser.vmap.VmapAdBreak;
import com.amazon.avod.ads.parser.vmap.VmapAdSource;
import com.amazon.avod.ads.parser.vmap.VmapBreakType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.LinkedList;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdBreakParser {
    @Nonnull
    public static VmapAdBreak parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        int attributeCount = xmlPullParser.getAttributeCount();
        VastOffset xmlString = null;
        VmapBreakType xmlString2 = null;
        String str = null;
        VastTimeSpan xmlTime = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("timeOffset")) {
                xmlString = VastOffset.parseXmlString(attributeValue);
            } else if (attributeName.equals("breakType")) {
                xmlString2 = VmapBreakType.parseXmlString(attributeValue);
            } else if (attributeName.equals("breakId")) {
                str = attributeValue;
            } else if (attributeName.equals("repeatAfter")) {
                xmlTime = VastTimeSpan.parseXmlTime(attributeValue);
            }
        }
        VmapAdSource vmapAdSource = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "AdBreak")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("AdSource")) {
                vmapAdSource = VmapAdSourceParser.parse(xmlPullParser);
            } else if (name.equals("TrackingEvents")) {
                linkedListNewLinkedList.addAll(VmapTrackingEventsParser.parse(xmlPullParser));
            } else if (name.equals("Extensions")) {
                DLog.warnf("<Extensions>: Tag encountered but is currently not supported.");
            }
        }
        return new VmapAdBreak(xmlString, xmlString2, str, xmlTime, vmapAdSource, linkedListNewLinkedList);
    }
}
