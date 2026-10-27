package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAdParameters;
import com.amazon.avod.ads.parser.vast.VastCompanion;
import com.amazon.avod.ads.parser.vast.VastException;
import com.amazon.avod.ads.parser.vast.VastResource;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanionParser {
    @Nonnull
    public static VastCompanion parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        VastResource vastResource;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        String str = null;
        Integer numValueOf = null;
        Integer numValueOf2 = null;
        Integer numValueOf3 = null;
        Integer numValueOf4 = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            if (attributeName.equals("id")) {
                str = attributeValue;
            } else if (attributeName.equals("width")) {
                i = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("height")) {
                i2 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("assetWidth")) {
                numValueOf = Integer.valueOf(ParserUtils.parseInt(attributeValue));
            } else if (attributeName.equals("assetHeight")) {
                numValueOf2 = Integer.valueOf(ParserUtils.parseInt(attributeValue));
            } else if (attributeName.equals("expandedWidth")) {
                numValueOf3 = Integer.valueOf(ParserUtils.parseInt(attributeValue));
            } else if (attributeName.equals("expandedHeight")) {
                numValueOf4 = Integer.valueOf(ParserUtils.parseInt(attributeValue));
            } else if (attributeName.equals("apiFramework")) {
                str2 = attributeValue;
            } else if (attributeName.equals("adSlotID")) {
                str3 = attributeValue;
            }
        }
        VastResource vastResource2 = null;
        List<VastTracking> list = null;
        URI uri = null;
        String textNode = null;
        VastAdParameters vastAdParameters = null;
        int i4 = 0;
        while (!ParserUtils.closingTagReached(xmlPullParser, "Companion")) {
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
            } else if (name.equals("AltText")) {
                textNode = ParserUtils.getTextNode(xmlPullParser, "AltText");
            } else if (name.equals("CompanionClickThrough")) {
                uri = ParserUtils.parseUri(xmlPullParser, name);
            } else if (name.equals("CompanionClickTracking")) {
                builder.add(VastCompanionClickTrackingParser.parse(xmlPullParser));
            } else if (name.equals("TrackingEvents")) {
                list = VastTrackingEventsParser.parse(xmlPullParser);
            }
            i4++;
            vastResource2 = vastResource;
        }
        if (i4 != 1) {
            throw new VastException(VastError.COMPANION_MULTIPLE_CHILD_TAGS);
        }
        return new VastCompanion(str, i, i2, numValueOf, numValueOf2, numValueOf3, numValueOf4, str2, str3, vastResource2, list, uri, builder.build(), textNode, vastAdParameters);
    }
}
