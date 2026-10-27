package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastTrackingEventsParser {
    @Nonnull
    public static List<VastTracking> parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        while (!ParserUtils.closingTagReached(xmlPullParser, "TrackingEvents")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("Tracking")) {
                try {
                    builder.add(VastTrackingParser.parse(xmlPullParser));
                } catch (XmlPullParserException e) {
                    DLog.warnf("Ignoring un-parsable VastTracking element, %s", e);
                }
            }
        }
        return builder.build();
    }
}
