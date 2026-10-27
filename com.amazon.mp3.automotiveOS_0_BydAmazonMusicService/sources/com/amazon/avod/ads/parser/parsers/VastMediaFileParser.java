package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastMediaFile;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastMediaFileParser {
    @Nonnull
    public static VastMediaFile parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        VastMediaFile.DeliveryType deliveryType = null;
        String str2 = null;
        Boolean boolValueOf = null;
        Boolean boolValueOf2 = null;
        String str3 = null;
        String str4 = null;
        int i5 = 0;
        for (int i6 = 0; i6 < attributeCount; i6++) {
            String attributeName = xmlPullParser.getAttributeName(i6);
            String attributeValue = xmlPullParser.getAttributeValue(i6);
            if (attributeName.equals("id")) {
                str = attributeValue;
            } else if (attributeName.equals("delivery")) {
                deliveryType = (VastMediaFile.DeliveryType) ParserUtils.enumValueOf(VastMediaFile.DeliveryType.class, attributeValue);
            } else if (attributeName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                str2 = attributeValue;
            } else if (attributeName.equals("bitrate")) {
                i5 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("minBitrate")) {
                i = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("maxBitrate")) {
                i2 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("width")) {
                i3 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("height")) {
                i4 = ParserUtils.parseInt(attributeValue);
            } else if (attributeName.equals("scalable")) {
                boolValueOf = Boolean.valueOf(ParserUtils.parseBoolean(attributeValue));
            } else if (attributeName.equals("maintainAspectRatio")) {
                boolValueOf2 = Boolean.valueOf(ParserUtils.parseBoolean(attributeValue));
            } else if (attributeName.equals("codec")) {
                str4 = attributeValue;
            } else if (attributeName.equals("apiFramework")) {
                str3 = attributeValue;
            }
        }
        return new VastMediaFile(str, deliveryType, str2, Integer.valueOf(i5), Integer.valueOf(i), Integer.valueOf(i2), i3, i4, boolValueOf, boolValueOf2, str3, str4, ParserUtils.parseUri(xmlPullParser, "MediaFile"));
    }
}
