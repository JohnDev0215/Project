package com.amazon.avod.ads.parser.parsers;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.ads.parser.vmap.VmapTracking;
import com.amazon.avod.ads.parser.vmap.VmapTrackingEventType;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapTrackingParser {
    @Nonnull
    public static VmapTracking parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        VmapTrackingEventType vmapTrackingEventType = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals(NotificationCompat.CATEGORY_EVENT)) {
                vmapTrackingEventType = (VmapTrackingEventType) ParserUtils.enumValueOf(VmapTrackingEventType.class, attributeValue);
            }
        }
        return new VmapTracking(vmapTrackingEventType, ParserUtils.parseUri(xmlPullParser, "Tracking"));
    }
}
