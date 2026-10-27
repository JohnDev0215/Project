package com.amazon.avod.ads.parser.parsers;

import com.amazon.music.destination.parser.ParserUtil;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URI;
import java.util.Currency;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
class ParserUtils {
    ParserUtils() {
    }

    static boolean closingTagReached(@Nonnull XmlPullParser xmlPullParser, @Nonnull String str) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        Preconditions.checkNotNull(str, ParserUtil.TAG_QUERY_PARAM_NAME);
        return xmlPullParser.getEventType() == 3 && xmlPullParser.getName().equals(str);
    }

    @Nullable
    static String getTextNode(@Nonnull XmlPullParser xmlPullParser, @Nonnull String str) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        Preconditions.checkNotNull(str, "endTag");
        String strTrim = null;
        while (!closingTagReached(xmlPullParser, str)) {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                strTrim = xmlPullParser.getText().trim();
            }
        }
        return strTrim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.net.URI] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    @Nullable
    static URI parseUri(@Nonnull XmlPullParser xmlPullParser, @Nonnull String str) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        Preconditions.checkNotNull(str, "endTag");
        String textNode = getTextNode(xmlPullParser, str);
        try {
            textNode = textNode == 0 ? URI.create("") : URI.create(textNode);
            return textNode;
        } catch (IllegalArgumentException unused) {
            throw new XmlPullParserException("Error while parsing URI string: " + textNode);
        }
    }

    static int parseInt(@Nonnull String str) throws XmlPullParserException {
        Preconditions.checkNotNull(str, "val");
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new XmlPullParserException("Error while parsing Integer value: " + str);
        }
    }

    static boolean parseBoolean(@Nonnull String str) throws XmlPullParserException {
        Preconditions.checkNotNull(str, "val");
        if (!str.equalsIgnoreCase("true") && !str.equalsIgnoreCase("false")) {
            throw new XmlPullParserException("Error while parsing Boolean value: " + str);
        }
        return Boolean.parseBoolean(str);
    }

    static Currency parseCurrency(@Nonnull String str) throws XmlPullParserException {
        Preconditions.checkNotNull(str, "val");
        try {
            return Currency.getInstance(str);
        } catch (IllegalArgumentException unused) {
            throw new XmlPullParserException("Error while parsing Currency value: " + str);
        }
    }

    static <T extends Enum<T>> T enumValueOf(Class<T> cls, @Nonnull String str) throws XmlPullParserException {
        Preconditions.checkNotNull(str, "name");
        try {
            return (T) Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            throw new XmlPullParserException("Error while parsing enum value: " + str);
        }
    }
}
