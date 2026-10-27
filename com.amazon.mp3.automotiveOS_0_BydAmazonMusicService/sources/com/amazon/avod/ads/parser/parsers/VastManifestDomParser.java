package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.api.AttributeData;
import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.api.ElementNodeData;
import com.amazon.avod.ads.api.TextNodeData;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastManifestDomParser {
    @Nonnull
    public static ElementNode parse(@Nonnull XmlPullParser xmlPullParser, @Nonnull String str) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        ArrayDeque arrayDequeNewArrayDeque = Queues.newArrayDeque();
        ElementNodeData elementNodeData = new ElementNodeData(xmlPullParser.getName());
        arrayDequeNewArrayDeque.push(elementNodeData);
        while (!arrayDequeNewArrayDeque.isEmpty()) {
            parseNode(xmlPullParser, (ElementNodeData) arrayDequeNewArrayDeque.pop(), arrayDequeNewArrayDeque);
            if (xmlPullParser.getEventType() == 3 && !str.equals(xmlPullParser.getName())) {
                xmlPullParser.nextTag();
                if (xmlPullParser.getEventType() == 2) {
                    ElementNodeData elementNodeData2 = new ElementNodeData(xmlPullParser.getName());
                    if (!arrayDequeNewArrayDeque.isEmpty()) {
                        ((ElementNodeData) arrayDequeNewArrayDeque.peek()).getChildren().add(elementNodeData2);
                    }
                    arrayDequeNewArrayDeque.push(elementNodeData2);
                }
            }
        }
        return elementNodeData;
    }

    private static void parseNode(@Nonnull XmlPullParser xmlPullParser, @Nonnull ElementNodeData elementNodeData, @Nonnull Deque<ElementNodeData> deque) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        Preconditions.checkNotNull(elementNodeData, "parentNode");
        Preconditions.checkNotNull(deque, "stack");
        while (!ParserUtils.closingTagReached(xmlPullParser, elementNodeData.getName())) {
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                elementNodeData.getAttributes().add(new AttributeData(xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i)));
            }
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                String strTrim = xmlPullParser.getText().trim();
                xmlPullParser.nextTag();
                if (xmlPullParser.getEventType() == 3) {
                    elementNodeData.setTextNode(new TextNodeData(strTrim));
                }
            }
            if (xmlPullParser.getEventType() == 2) {
                ElementNodeData elementNodeData2 = new ElementNodeData(xmlPullParser.getName());
                elementNodeData.getChildren().add(elementNodeData2);
                deque.push(elementNodeData);
                deque.push(elementNodeData2);
                return;
            }
        }
    }
}
