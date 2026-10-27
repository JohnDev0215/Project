package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastAdSystem;
import com.amazon.avod.ads.parser.vast.VastExtension;
import com.amazon.avod.ads.parser.vast.VastInline;
import com.amazon.avod.ads.parser.vast.VastInlineCreative;
import com.amazon.avod.ads.parser.vast.VastPricing;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineParser {
    @Nonnull
    public static VastInline parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        VastAdSystem vastAdSystem = null;
        String textNode = null;
        List<VastInlineCreative> list = null;
        String textNode2 = null;
        String textNode3 = null;
        VastPricing vastPricing = null;
        List<VastExtension> list2 = null;
        while (!ParserUtils.closingTagReached(xmlPullParser, "InLine")) {
            xmlPullParser.nextTag();
            String name = xmlPullParser.getName();
            if (name.equals("AdSystem")) {
                vastAdSystem = VastAdSystemParser.parse(xmlPullParser);
            } else if (name.equals("AdTitle")) {
                textNode = ParserUtils.getTextNode(xmlPullParser, name);
            } else if (name.equals("Description")) {
                textNode2 = ParserUtils.getTextNode(xmlPullParser, name);
            } else if (name.equals("Advertiser")) {
                textNode3 = ParserUtils.getTextNode(xmlPullParser, name);
            } else if (name.equals("Pricing")) {
                vastPricing = VastPricingParser.parse(xmlPullParser);
            } else if (name.equals("Survey")) {
                builder.add(ParserUtils.parseUri(xmlPullParser, name));
            } else if (name.equals("Error")) {
                builder2.add(ParserUtils.parseUri(xmlPullParser, name));
            } else if (name.equals("Impression")) {
                builder3.add(VastImpressionParser.parse(xmlPullParser));
            } else if (name.equals("Creatives")) {
                list = VastInlineCreativesParser.parse(xmlPullParser);
            } else if (name.equals("Extensions")) {
                list2 = VastExtensionsParser.parse(xmlPullParser);
            }
        }
        return new VastInline(vastAdSystem, textNode, builder3.build(), list, textNode2, textNode3, vastPricing, builder.build(), builder2.build(), list2);
    }
}
