package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastIcon;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastVideoClicks;
import com.amazon.avod.ads.parser.vast.VastWrapperLinear;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperLinearParser {
    @Nonnull
    public static VastWrapperLinear parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        List<VastIcon> list = null;
        List<VastTracking> list2 = null;
        VastVideoClicks vastVideoClicks = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "Linear")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("TrackingEvents")) {
                list2 = VastTrackingEventsParser.parse(xmlPullParser);
            } else if (name.equals("VideoClicks")) {
                vastVideoClicks = VastVideoClicksParser.parse(xmlPullParser);
            } else if (name.equals("Icons")) {
                list = VastIconsParser.parse(xmlPullParser);
            }
        }
        return new VastWrapperLinear(list, list2, vastVideoClicks);
    }
}
