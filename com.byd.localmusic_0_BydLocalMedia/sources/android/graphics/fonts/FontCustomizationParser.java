package android.graphics.fonts;

import android.graphics.FontListParser;
import android.security.KeyChain;
import android.text.FontConfig;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class FontCustomizationParser {

    public static class Result {
        ArrayList<FontConfig.Family> mAdditionalNamedFamilies = new ArrayList<>();
        ArrayList<FontConfig.Alias> mAdditionalAliases = new ArrayList<>();
    }

    public static Result parse(InputStream inputStream, String str) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        xmlPullParserNewPullParser.setInput(inputStream, null);
        xmlPullParserNewPullParser.nextTag();
        return readFamilies(xmlPullParserNewPullParser, str);
    }

    private static void validate(Result result) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < result.mAdditionalNamedFamilies.size(); i++) {
            String name = result.mAdditionalNamedFamilies.get(i).getName();
            if (name == null) {
                throw new IllegalArgumentException("new-named-family requires name attribute");
            }
            if (!hashSet.add(name)) {
                throw new IllegalArgumentException("new-named-family requires unique name attribute");
            }
        }
    }

    private static Result readFamilies(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        Result result = new Result();
        xmlPullParser.require(2, null, "fonts-modification");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("family")) {
                    readFamily(xmlPullParser, str, result);
                } else if (name.equals(KeyChain.EXTRA_ALIAS)) {
                    result.mAdditionalAliases.add(FontListParser.readAlias(xmlPullParser));
                } else {
                    FontListParser.skip(xmlPullParser);
                }
            }
        }
        validate(result);
        return result;
    }

    private static void readFamily(XmlPullParser xmlPullParser, String str, Result result) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "customizationType");
        if (attributeValue == null) {
            throw new IllegalArgumentException("customizationType must be specified");
        }
        if (attributeValue.equals("new-named-family")) {
            result.mAdditionalNamedFamilies.add(FontListParser.readFamily(xmlPullParser, str));
            return;
        }
        throw new IllegalArgumentException("Unknown customizationType=" + attributeValue);
    }
}
