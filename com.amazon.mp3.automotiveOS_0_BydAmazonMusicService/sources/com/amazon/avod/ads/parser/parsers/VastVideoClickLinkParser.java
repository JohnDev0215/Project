package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastVideoClickLink;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastVideoClickLinkParser {
    @Nonnull
    public static VastVideoClickLink parse(@Nonnull XmlPullParser xmlPullParser, @Nonnull String str) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        String str2 = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("id")) {
                str2 = attributeValue;
            }
        }
        return new VastVideoClickLink(str2, ParserUtils.parseUri(xmlPullParser, str));
    }
}
