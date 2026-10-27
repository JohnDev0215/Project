package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastException;
import com.amazon.avod.ads.parser.vast.VastIconClicks;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.net.URI;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastIconClicksParser {
    @Nonnull
    public static VastIconClicks parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        URI uri = null;
        int i = 0;
        while (!ParserUtils.closingTagReached(xmlPullParser, "IconClicks")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("IconClickThrough")) {
                uri = ParserUtils.parseUri(xmlPullParser, name);
                i++;
            } else if (name.equals("IconClickTracking")) {
                builder.add(ParserUtils.parseUri(xmlPullParser, name));
            }
        }
        if (i > 1) {
            throw new VastException(VastError.ICON_CLICKS_EXCESS_ICON_CLICK_THROUGH);
        }
        return new VastIconClicks(builder.build(), uri);
    }
}
