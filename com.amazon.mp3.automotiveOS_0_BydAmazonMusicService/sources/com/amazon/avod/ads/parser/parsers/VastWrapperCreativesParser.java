package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastWrapperCreative;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperCreativesParser {
    @Nonnull
    public static List<VastWrapperCreative> parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        while (!ParserUtils.closingTagReached(xmlPullParser, "Creatives")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("Creative")) {
                builder.add(VastWrapperCreativeParser.parse(xmlPullParser));
            }
        }
        return builder.build();
    }
}
