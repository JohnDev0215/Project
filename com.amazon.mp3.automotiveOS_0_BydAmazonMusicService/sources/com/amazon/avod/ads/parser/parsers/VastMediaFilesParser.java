package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastMediaFile;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastMediaFilesParser {
    @Nonnull
    public static List<VastMediaFile> parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        while (!ParserUtils.closingTagReached(xmlPullParser, "MediaFiles")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("MediaFile")) {
                builder.add(VastMediaFileParser.parse(xmlPullParser));
            }
        }
        return builder.build();
    }
}
