package com.amazon.identity.auth.device;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class rb {
    public List<qb> a(Element element) {
        ArrayList arrayList = new ArrayList();
        if (element == null) {
            return arrayList;
        }
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                Element elementA = e.a(element2, ImagesContract.URL);
                Element elementA2 = e.a(element2, "value");
                String strA = e.a(elementA);
                String strA2 = e.a(elementA2);
                qb qbVar = new qb();
                if (!id.b(strA)) {
                    qbVar.f516a = strA;
                }
                if (!id.b(strA2)) {
                    qbVar.b = strA2;
                }
                arrayList.add(qbVar);
            }
        }
        return arrayList;
    }
}
