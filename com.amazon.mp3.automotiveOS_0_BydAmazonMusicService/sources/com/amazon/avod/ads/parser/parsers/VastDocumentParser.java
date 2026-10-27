package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastDocument;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastDocumentParser {
    @Nonnull
    public static VastDocument parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        String str = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("version")) {
                str = attributeValue;
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "VAST")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("Ad")) {
                builder.add(VastAdParser.parse(xmlPullParser));
            }
        }
        return new VastDocument(str, builder.build());
    }
}
