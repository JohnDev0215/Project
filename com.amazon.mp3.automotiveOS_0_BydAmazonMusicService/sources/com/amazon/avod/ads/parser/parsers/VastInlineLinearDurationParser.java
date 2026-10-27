package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineLinearDurationParser {
    @Nullable
    public static VastTimeSpan parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        String textNode = ParserUtils.getTextNode(xmlPullParser, "Duration");
        if (textNode == null) {
            return null;
        }
        return VastTimeSpan.parseXmlTime(textNode);
    }
}
