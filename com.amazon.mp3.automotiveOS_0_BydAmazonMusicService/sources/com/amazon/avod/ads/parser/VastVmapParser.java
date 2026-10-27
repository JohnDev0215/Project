package com.amazon.avod.ads.parser;

import android.util.Xml;
import com.amazon.avod.ads.parser.parsers.VastDocumentParser;
import com.amazon.avod.ads.parser.parsers.VmapDocumentParser;
import com.amazon.avod.ads.parser.vast.VastDocument;
import com.amazon.avod.ads.parser.vmap.VmapDocument;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastVmapParser {
    @Nonnull
    public static VastDocument loadVastDocument(@Nonnull String str) throws XmlPullParserException, IOException {
        InputStream inputStreamCreateInputStreamFromText = createInputStreamFromText(str);
        try {
            return VastDocumentParser.parse(createParser(inputStreamCreateInputStreamFromText));
        } finally {
            if (inputStreamCreateInputStreamFromText != null) {
                inputStreamCreateInputStreamFromText.close();
            }
        }
    }

    @Nonnull
    public static VmapDocument loadVmapDocument(@Nonnull String str) throws XmlPullParserException, IOException {
        InputStream inputStreamCreateInputStreamFromText = createInputStreamFromText(str);
        try {
            return VmapDocumentParser.parse(createParser(inputStreamCreateInputStreamFromText));
        } finally {
            if (inputStreamCreateInputStreamFromText != null) {
                inputStreamCreateInputStreamFromText.close();
            }
        }
    }

    @Nonnull
    private static XmlPullParser createParser(@Nonnull InputStream inputStream) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(inputStream, "inputStream");
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        xmlPullParserNewPullParser.setInput(inputStream, null);
        xmlPullParserNewPullParser.nextTag();
        return xmlPullParserNewPullParser;
    }

    private static InputStream createInputStreamFromText(@Nonnull String str) {
        Preconditions.checkNotNull(str, "documentText");
        return new BufferedInputStream(new ByteArrayInputStream(str.getBytes(Charsets.UTF_8)));
    }
}
