package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.api.Node;
import com.amazon.avod.ads.api.NodeType;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionMinAdLengthParser {
    public static VastTimeSpan parse(@Nonnull ElementNode elementNode) {
        Preconditions.checkNotNull(elementNode, "extension");
        VastTimeSpan zeroTimeSpan = VastTimeSpan.getZeroTimeSpan();
        List<Node> children = elementNode.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (NodeType.Element.equals(children.get(i).getNodeType())) {
                ElementNode elementNode2 = (ElementNode) children.get(i);
                if ("MinAdLength".equals(elementNode2.getName())) {
                    String value = elementNode2.getTextNode() != null ? elementNode2.getTextNode().getValue() : null;
                    if (value != null) {
                        zeroTimeSpan = VastTimeSpan.parseXmlTime(value);
                    }
                }
            }
        }
        return zeroTimeSpan;
    }
}
