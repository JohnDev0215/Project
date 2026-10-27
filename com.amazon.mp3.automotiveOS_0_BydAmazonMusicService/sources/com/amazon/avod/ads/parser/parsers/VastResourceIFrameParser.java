package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastResourceIFrame;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastResourceIFrameParser {
    @Nonnull
    public static VastResourceIFrame parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        return new VastResourceIFrame(ParserUtils.parseUri(xmlPullParser, "IFrameResource"));
    }
}
