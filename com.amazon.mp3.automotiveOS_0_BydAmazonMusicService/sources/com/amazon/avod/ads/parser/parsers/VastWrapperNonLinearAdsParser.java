package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastWrapperNonLinearAds;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperNonLinearAdsParser {
    @Nonnull
    public static VastWrapperNonLinearAds parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        List<VastTracking> list = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "NonLinearAds")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("TrackingEvents")) {
                list = VastTrackingEventsParser.parse(xmlPullParser);
            } else if (name.equals("NonLinear")) {
                DLog.warnf("<NonLinear>: Tag encountered but is currently not supported.");
            }
        }
        return new VastWrapperNonLinearAds(list, null);
    }
}
