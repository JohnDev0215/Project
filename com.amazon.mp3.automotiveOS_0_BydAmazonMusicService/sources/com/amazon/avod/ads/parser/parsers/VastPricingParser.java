package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.parser.vast.VastPricing;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Currency;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastPricingParser {
    @Nonnull
    public static VastPricing parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        int attributeCount = xmlPullParser.getAttributeCount();
        VastPricing.VastPricingModel vastPricingModel = null;
        Currency currency = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("model")) {
                vastPricingModel = (VastPricing.VastPricingModel) ParserUtils.enumValueOf(VastPricing.VastPricingModel.class, attributeValue);
            } else if (attributeName.equals("currency")) {
                currency = ParserUtils.parseCurrency(attributeValue);
            }
        }
        while (!ParserUtils.closingTagReached(xmlPullParser, "Pricing")) {
            xmlPullParser.next();
        }
        return new VastPricing(vastPricingModel, currency);
    }
}
