package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastException;
import com.amazon.avod.ads.parser.vast.VastInlineNonLinearAds;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineNonLinearAdsParser {
    @Nonnull
    public static VastInlineNonLinearAds parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        List<VastTracking> list = null;
        int i = 0;
        while (!ParserUtils.closingTagReached(xmlPullParser, "NonLinearAds")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("NonLinear")) {
                builder.add(VastInlineNonLinearParser.parse(xmlPullParser));
            } else if (name.equals("TrackingEvents")) {
                list = VastTrackingEventsParser.parse(xmlPullParser);
                i++;
            }
        }
        if (i > 1) {
            throw new VastException(VastError.NONLINEAR_ADS_EXCESS_TRACKING_EVENTS);
        }
        return new VastInlineNonLinearAds(list, builder.build());
    }
}
