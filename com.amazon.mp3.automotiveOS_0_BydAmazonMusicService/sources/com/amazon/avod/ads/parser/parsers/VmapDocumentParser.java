package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vmap.VmapDocument;
import com.amazon.avod.ads.parser.vmap.VmapExtension;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapDocumentParser {
    @Nonnull
    public static VmapDocument parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        int attributeCount = xmlPullParser.getAttributeCount();
        List<VmapExtension> list = null;
        String strTrim = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("version")) {
                strTrim = attributeValue.trim();
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "VMAP")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("AdBreak")) {
                builder.add(VmapAdBreakParser.parse(xmlPullParser));
            } else if (name.equals("Extensions")) {
                list = VmapExtensionsParser.parse(xmlPullParser);
            }
        }
        return new VmapDocument(strTrim, builder.build(), list);
    }
}
