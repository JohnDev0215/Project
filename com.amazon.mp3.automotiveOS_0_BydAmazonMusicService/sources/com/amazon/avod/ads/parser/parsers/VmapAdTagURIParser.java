package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vmap.VmapAdTagURI;
import com.amazon.avod.ads.parser.vmap.VmapAdTagURITemplateType;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdTagURIParser {
    @Nonnull
    public static VmapAdTagURI parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        VmapAdTagURITemplateType vmapAdTagURITemplateType = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("templateType")) {
                vmapAdTagURITemplateType = (VmapAdTagURITemplateType) ParserUtils.enumValueOf(VmapAdTagURITemplateType.class, attributeValue);
            }
        }
        return new VmapAdTagURI(vmapAdTagURITemplateType, ParserUtils.parseUri(xmlPullParser, "AdTagURI"));
    }
}
