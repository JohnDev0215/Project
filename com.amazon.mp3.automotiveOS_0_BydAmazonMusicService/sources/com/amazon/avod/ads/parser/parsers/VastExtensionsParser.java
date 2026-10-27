package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastExtension;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionsParser {
    @Nonnull
    public static List<VastExtension> parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ImmutableList.Builder builder = ImmutableList.builder();
        while (!ParserUtils.closingTagReached(xmlPullParser, "Extensions")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("Extension")) {
                builder.add(VastExtensionParser.parse(xmlPullParser));
            }
        }
        return builder.build();
    }
}
