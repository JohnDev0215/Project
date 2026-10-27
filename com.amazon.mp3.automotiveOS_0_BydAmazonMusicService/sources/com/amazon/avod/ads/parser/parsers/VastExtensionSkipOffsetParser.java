package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.api.Attribute;
import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.api.Node;
import com.amazon.avod.ads.api.NodeType;
import com.amazon.avod.ads.parser.vast.VastExtensionSkipOffset;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionSkipOffsetParser {
    @Nonnull
    public static VastExtensionSkipOffset parse(@Nonnull XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Preconditions.checkNotNull(xmlPullParser, "parser");
        VastTimeSpan zeroTimeSpan = VastTimeSpan.getZeroTimeSpan();
        int attributeCount = xmlPullParser.getAttributeCount();
        boolean z = false;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (attributeName.equals("showTimer")) {
                z = ParserUtils.parseBoolean(attributeValue);
            }
        }
        String textNode = ParserUtils.getTextNode(xmlPullParser, "SkipOffset");
        if (textNode != null) {
            zeroTimeSpan = VastTimeSpan.parseXmlTime(textNode);
        }
        return new VastExtensionSkipOffset(zeroTimeSpan, z);
    }

    @Nonnull
    public static VastExtensionSkipOffset parse(@Nonnull ElementNode elementNode) throws XmlPullParserException {
        Preconditions.checkNotNull(elementNode, "extension");
        VastTimeSpan zeroTimeSpan = VastTimeSpan.getZeroTimeSpan();
        List<Node> children = elementNode.getChildren();
        boolean z = false;
        for (int i = 0; i < children.size(); i++) {
            if (NodeType.Element.equals(children.get(i).getNodeType())) {
                ElementNode elementNode2 = (ElementNode) children.get(i);
                if ("SkipOffset".equals(elementNode2.getName())) {
                    List<Attribute> attributes = elementNode2.getAttributes();
                    for (int i2 = 0; i2 < attributes.size(); i2++) {
                        String name = attributes.get(i).getName();
                        String value = attributes.get(i).getValue();
                        if ("showTimer".equals(name)) {
                            z = ParserUtils.parseBoolean(value);
                        }
                    }
                    String value2 = elementNode2.getTextNode() != null ? elementNode2.getTextNode().getValue() : null;
                    if (value2 != null) {
                        zeroTimeSpan = VastTimeSpan.parseXmlTime(value2);
                    }
                }
            }
        }
        return new VastExtensionSkipOffset(zeroTimeSpan, z);
    }
}
