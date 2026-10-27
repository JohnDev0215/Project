package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class ListTransducedAccessorImpl<BeanT, ListT, ItemT, PackT> extends DefaultTransducedAccessor<BeanT> {
    private final Accessor<BeanT, ListT> acc;
    private final Lister<BeanT, ListT, ItemT, PackT> lister;
    private final Transducer<ItemT> xducer;

    public ListTransducedAccessorImpl(Transducer<ItemT> transducer, Accessor<BeanT, ListT> accessor, Lister<BeanT, ListT, ItemT, PackT> lister) {
        this.xducer = transducer;
        this.lister = lister;
        this.acc = accessor;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public boolean useNamespace() {
        return this.xducer.useNamespace();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void declareNamespace(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, AccessorException {
        ListT listt = this.acc.get(beant);
        if (listt != null) {
            ListIterator<ItemT> it = this.lister.iterator(listt, xMLSerializer);
            while (it.hasNext()) {
                try {
                    ItemT next = it.next();
                    if (next != null) {
                        this.xducer.declareNamespace(next, xMLSerializer);
                    }
                } catch (JAXBException e) {
                    xMLSerializer.reportError(null, e);
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public String print(BeanT beant) throws SAXException, AccessorException {
        ListT listt = this.acc.get(beant);
        if (listt == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        XMLSerializer xMLSerializer = XMLSerializer.getInstance();
        ListIterator<ItemT> it = this.lister.iterator(listt, xMLSerializer);
        while (it.hasNext()) {
            try {
                ItemT next = it.next();
                if (next != null) {
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    sb.append(this.xducer.print(next));
                }
            } catch (JAXBException e) {
                xMLSerializer.reportError(null, e);
            }
        }
        return sb.toString();
    }

    private void processValue(BeanT beant, CharSequence charSequence) throws SAXException, AccessorException {
        PackT packtStartPacking = this.lister.startPacking(beant, this.acc);
        int length = charSequence.length();
        int i = 0;
        do {
            int i2 = i;
            while (i2 < length && !WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i2))) {
                i2++;
            }
            CharSequence charSequenceSubSequence = charSequence.subSequence(i, i2);
            if (!charSequenceSubSequence.equals("")) {
                this.lister.addToPack(packtStartPacking, this.xducer.parse(charSequenceSubSequence));
            }
            if (i2 == length) {
                break;
            }
            i = i2;
            while (i < length && WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i))) {
                i++;
            }
        } while (i != length);
        this.lister.endPacking(packtStartPacking, beant, this.acc);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void parse(BeanT beant, CharSequence charSequence) throws SAXException, AccessorException {
        processValue(beant, charSequence);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public boolean hasValue(BeanT beant) throws AccessorException {
        return this.acc.get(beant) != null;
    }
}
