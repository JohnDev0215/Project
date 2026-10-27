package com.amazon.avod.ads.parser.parsers;

import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.api.Node;
import com.amazon.avod.ads.api.NodeType;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtensionContentTypeParser {
    @Nullable
    public static String parse(@Nonnull ElementNode elementNode) {
        Preconditions.checkNotNull(elementNode, "extension");
        ArrayList<Node> arrayList = new ArrayList();
        Iterator<Node> it = elementNode.getChildren().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Node next = it.next();
            if (NodeType.Element.equals(next.getNodeType())) {
                ElementNode elementNode2 = (ElementNode) next;
                if ("ContentMetadata".equals(elementNode2.getName())) {
                    arrayList.addAll(elementNode2.getChildren());
                    break;
                }
            }
        }
        for (Node node : arrayList) {
            if (NodeType.Element.equals(node.getNodeType())) {
                ElementNode elementNode3 = (ElementNode) node;
                if ("ContentType".equals(elementNode3.getName())) {
                    if (elementNode3.getTextNode() != null) {
                        return elementNode3.getTextNode().getValue();
                    }
                    return null;
                }
            }
        }
        return null;
    }
}
