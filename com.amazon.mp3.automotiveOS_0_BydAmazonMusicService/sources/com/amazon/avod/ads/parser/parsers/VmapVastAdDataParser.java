package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastDocument;
import com.amazon.avod.ads.parser.vmap.VmapVastAdData;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VmapVastAdDataParser {
    @Nonnull
    public static VmapVastAdData parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        VastDocument vastDocument = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "VASTAdData")) {
            xmlPullParser.nextTag();
            if (xmlPullParser.getName().equals("VAST")) {
                vastDocument = VastDocumentParser.parse(xmlPullParser);
            }
        }
        return new VmapVastAdData(vastDocument);
    }
}
