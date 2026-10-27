package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.config.AdsConfig;
import com.amazon.avod.ads.parser.vast.VastExtension;
import com.amazon.avod.ads.parser.vast.VastExtensionSkipOffset;
import com.amazon.avod.ads.parser.vast.VastExtensionType;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionParser {
    @Nonnull
    public static VastExtension parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        VastExtensionSkipOffset vastExtensionSkipOffset;
        VastTimeSpan vastTimeSpan;
        String textNode;
        String textNode2;
        VastExtensionSkipOffset vastExtensionSkipOffset2;
        VastTimeSpan vastTimeSpan2;
        String str;
        Preconditions.checkNotNull(xmlPullParser, "parser");
        String name = xmlPullParser.getName();
        int attributeCount = xmlPullParser.getAttributeCount();
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(attributeName)) {
                str2 = attributeValue;
            }
            if ("title".equals(attributeName)) {
                str3 = attributeValue;
            }
        }
        String textNode3 = null;
        if (AdsConfig.getInstance().shouldParseVastExtensionIntoDom()) {
            ElementNode elementNode = VastManifestDomParser.parse(xmlPullParser, "Extension");
            if (VastExtensionType.SKIPPABLE.equals(str2)) {
                vastExtensionSkipOffset2 = VastExtensionSkipOffsetParser.parse(elementNode);
                vastTimeSpan2 = VastExtensionMinAdLengthParser.parse(elementNode);
                str = null;
            } else if (VastExtensionType.CONTENT_METADATA.equals(str2)) {
                str = VastExtensionContentTypeParser.parse(elementNode);
                vastExtensionSkipOffset2 = null;
                vastTimeSpan2 = null;
            } else {
                vastExtensionSkipOffset2 = null;
                vastTimeSpan2 = null;
                str = null;
            }
            return new VastExtension(str2, null, vastTimeSpan2, vastExtensionSkipOffset2, str3, name, elementNode.getAttributes(), elementNode.getChildren(), elementNode.getTextNode(), str);
        }
        if (VastExtensionType.SKIPPABLE.equals(str2)) {
            vastExtensionSkipOffset = new VastExtensionSkipOffset(VastTimeSpan.getZeroTimeSpan(), false);
            VastTimeSpan zeroTimeSpan = VastTimeSpan.getZeroTimeSpan();
            while (!ParserUtils.closingTagReached(xmlPullParser, "Extension")) {
                xmlPullParser.nextTag();
                String name2 = xmlPullParser.getName();
                if ("SkipOffset".equals(name2)) {
                    vastExtensionSkipOffset = VastExtensionSkipOffsetParser.parse(xmlPullParser);
                }
                if ("MinAdLength".equals(name2) && (textNode2 = ParserUtils.getTextNode(xmlPullParser, "MinAdLength")) != null) {
                    zeroTimeSpan = VastTimeSpan.parseXmlTime(textNode2);
                }
            }
            vastTimeSpan = zeroTimeSpan;
            textNode = null;
        } else {
            if (VastExtensionType.CONTENT_METADATA.equals(str2)) {
                while (!ParserUtils.closingTagReached(xmlPullParser, "Extension")) {
                    xmlPullParser.nextTag();
                    if ("ContentType".equals(xmlPullParser.getName())) {
                        textNode = ParserUtils.getTextNode(xmlPullParser, "ContentType");
                        vastExtensionSkipOffset = null;
                        vastTimeSpan = null;
                        break;
                    }
                }
            } else {
                textNode3 = ParserUtils.getTextNode(xmlPullParser, "Extension");
            }
            vastExtensionSkipOffset = null;
            vastTimeSpan = null;
            textNode = null;
        }
        return new VastExtension(str2, textNode3, vastTimeSpan, vastExtensionSkipOffset, str3, name, textNode);
    }
}
