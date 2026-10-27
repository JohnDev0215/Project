package com.android.inputmethod.latin.utils;

import android.content.res.TypedArray;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public final class XmlParseUtils {
    private XmlParseUtils() {
    }

    public static class ParseException extends XmlPullParserException {
        public ParseException(final String msg, final XmlPullParser parser) {
            super(msg + " at " + parser.getPositionDescription());
        }
    }

    public static final class IllegalStartTag extends ParseException {
        public IllegalStartTag(final XmlPullParser parser, final String tag, final String parent) {
            super("Illegal start tag " + tag + " in " + parent, parser);
        }
    }

    public static final class IllegalEndTag extends ParseException {
        public IllegalEndTag(final XmlPullParser parser, final String tag, final String parent) {
            super("Illegal end tag " + tag + " in " + parent, parser);
        }
    }

    public static final class IllegalAttribute extends ParseException {
        public IllegalAttribute(final XmlPullParser parser, final String tag, final String attribute) {
            super("Tag " + tag + " has illegal attribute " + attribute, parser);
        }
    }

    public static final class NonEmptyTag extends ParseException {
        public NonEmptyTag(final XmlPullParser parser, final String tag) {
            super(tag + " must be empty tag", parser);
        }
    }

    public static void checkEndTag(final String tag, final XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.next() != 3 || !tag.equals(parser.getName())) {
            throw new NonEmptyTag(parser, tag);
        }
    }

    public static void checkAttributeExists(final TypedArray attr, final int attrId, final String attrName, final String tag, final XmlPullParser parser) throws XmlPullParserException {
        if (!attr.hasValue(attrId)) {
            throw new ParseException("No " + attrName + " attribute found in <" + tag + "/>", parser);
        }
    }
}
