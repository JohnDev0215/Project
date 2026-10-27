package com.amazon.avod.ads.parser.parsers;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastTrackingParser {
    @Nonnull
    public static VastTracking parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        VastTrackingEventType vastTrackingEventType = null;
        VastOffset xmlString = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals(NotificationCompat.CATEGORY_EVENT)) {
                vastTrackingEventType = (VastTrackingEventType) ParserUtils.enumValueOf(VastTrackingEventType.class, attributeValue);
            } else if (attributeName.equals("offset")) {
                xmlString = VastOffset.parseXmlString(attributeValue);
            }
        }
        return new VastTracking(vastTrackingEventType, xmlString, ParserUtils.parseUri(xmlPullParser, "Tracking"));
    }
}
