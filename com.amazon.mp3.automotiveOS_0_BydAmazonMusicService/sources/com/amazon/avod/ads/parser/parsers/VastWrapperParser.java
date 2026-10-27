package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAdSystem;
import com.amazon.avod.ads.parser.vast.VastExtension;
import com.amazon.avod.ads.parser.vast.VastImpression;
import com.amazon.avod.ads.parser.vast.VastWrapper;
import com.amazon.avod.ads.parser.vast.VastWrapperCreative;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperParser {
    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public static VastWrapper parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        VastAdSystem vastAdSystem = null;
        URI uri = null;
        List<VastWrapperCreative> list = null;
        List<VastExtension> list2 = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "Wrapper")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("AdSystem")) {
                vastAdSystem = VastAdSystemParser.parse(xmlPullParser);
            } else if (name.equals("VASTAdTagURI")) {
                uri = ParserUtils.parseUri(xmlPullParser, name);
            } else if (name.equals("Error")) {
                builder.add(ParserUtils.parseUri(xmlPullParser, name));
            } else if (name.equals("Impression")) {
                builder2.add(VastImpressionParser.parse(xmlPullParser));
            } else if (name.equals("Creatives")) {
                list = VastWrapperCreativesParser.parse(xmlPullParser);
            } else if (name.equals("Extensions")) {
                list2 = VastExtensionsParser.parse(xmlPullParser);
            }
        }
        ImmutableList.Builder builder3 = ImmutableList.builder();
        UnmodifiableIterator it = builder2.build().iterator();
        while (it.hasNext()) {
            builder3.add(((VastImpression) it.next()).getUri());
        }
        return new VastWrapper(vastAdSystem, uri, builder.build(), builder3.build(), list, list2);
    }
}
