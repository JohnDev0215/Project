package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.SAX2DOMEx;
import com.sun.xml.bind.v2.runtime.AssociationMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class DOMOutput extends SAXOutput {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AssociationMap assoc;

    public DOMOutput(Node node, AssociationMap associationMap) {
        super(new SAX2DOMEx(node));
        this.assoc = associationMap;
    }

    private SAX2DOMEx getBuilder() {
        return (SAX2DOMEx) this.out;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.SAXOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws SAXException {
        super.endStartTag();
        Object outerPeer = this.nsContext.getCurrent().getOuterPeer();
        if (outerPeer != null) {
            this.assoc.addOuter(getBuilder().getCurrentElement(), outerPeer);
        }
        Object innerPeer = this.nsContext.getCurrent().getInnerPeer();
        if (innerPeer != null) {
            this.assoc.addInner(getBuilder().getCurrentElement(), innerPeer);
        }
    }
}
