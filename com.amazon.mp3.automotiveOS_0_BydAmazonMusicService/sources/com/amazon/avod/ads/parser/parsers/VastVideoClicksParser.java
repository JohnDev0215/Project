package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastVideoClickLink;
import com.amazon.avod.ads.parser.vast.VastVideoClicks;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastVideoClicksParser {
    @Nonnull
    public static VastVideoClicks parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        VastVideoClickLink vastVideoClickLink = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "VideoClicks")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("ClickTracking")) {
                builder.add(VastVideoClickLinkParser.parse(xmlPullParser, "ClickTracking"));
            } else if (name.equals("CustomClick")) {
                builder2.add(VastVideoClickLinkParser.parse(xmlPullParser, "CustomClick"));
            } else if (name.equals("ClickThrough")) {
                vastVideoClickLink = VastVideoClickLinkParser.parse(xmlPullParser, "ClickThrough");
            }
        }
        return new VastVideoClicks(vastVideoClickLink, builder.build(), builder2.build());
    }
}
