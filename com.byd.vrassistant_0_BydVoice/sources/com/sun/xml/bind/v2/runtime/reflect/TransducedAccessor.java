package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.istack.SAXException2;
import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.impl.RuntimeModelBuilder;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElementRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.opt.OptimizedTransducedAccessorFactory;
import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx;
import com.sun.xml.bind.v2.runtime.unmarshaller.Patcher;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TransducedAccessor<BeanT> {
    public void declareNamespace(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, AccessorException {
    }

    public abstract boolean hasValue(BeanT beant) throws AccessorException;

    public abstract void parse(BeanT beant, CharSequence charSequence) throws SAXException, AccessorException;

    public abstract CharSequence print(BeanT beant) throws SAXException, AccessorException;

    public boolean useNamespace() {
        return false;
    }

    public abstract void writeLeafElement(XMLSerializer xMLSerializer, Name name, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException;

    public abstract void writeText(XMLSerializer xMLSerializer, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException;

    /* JADX WARN: Type inference failed for: r5v1, types: [com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo] */
    public static <T> TransducedAccessor<T> get(JAXBContextImpl jAXBContextImpl, RuntimeNonElementRef runtimeNonElementRef) {
        TransducedAccessor<T> transducedAccessor;
        Transducer transducerCreateTransducer = RuntimeModelBuilder.createTransducer(runtimeNonElementRef);
        ?? source = runtimeNonElementRef.getSource();
        if (source.isCollection()) {
            return new ListTransducedAccessorImpl(transducerCreateTransducer, source.getAccessor(), Lister.create(Utils.REFLECTION_NAVIGATOR.erasure(source.getRawType()), source.id(), source.getAdapter()));
        }
        if (source.id() == ID.IDREF) {
            return new IDREFTransducedAccessorImpl(source.getAccessor());
        }
        if (transducerCreateTransducer.isDefault() && jAXBContextImpl != null && !jAXBContextImpl.fastBoot && (transducedAccessor = OptimizedTransducedAccessorFactory.get(source)) != null) {
            return transducedAccessor;
        }
        if (transducerCreateTransducer.useNamespace()) {
            return new CompositeContextDependentTransducedAccessorImpl(jAXBContextImpl, transducerCreateTransducer, source.getAccessor());
        }
        return new CompositeTransducedAccessorImpl(jAXBContextImpl, transducerCreateTransducer, source.getAccessor());
    }

    static class CompositeContextDependentTransducedAccessorImpl<BeanT, ValueT> extends CompositeTransducedAccessorImpl<BeanT, ValueT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public boolean useNamespace() {
            return true;
        }

        public CompositeContextDependentTransducedAccessorImpl(JAXBContextImpl jAXBContextImpl, Transducer<ValueT> transducer, Accessor<BeanT, ValueT> accessor) {
            super(jAXBContextImpl, transducer, accessor);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void declareNamespace(BeanT beant, XMLSerializer xMLSerializer) throws AccessorException {
            ValueT valuet = this.acc.get(beant);
            if (valuet != null) {
                this.xducer.declareNamespace(valuet, xMLSerializer);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor.CompositeTransducedAccessorImpl, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void writeLeafElement(XMLSerializer xMLSerializer, Name name, BeanT beant, String str) throws XMLStreamException, SAXException, IOException, AccessorException {
            xMLSerializer.startElement(name, null);
            declareNamespace(beant, xMLSerializer);
            xMLSerializer.endNamespaceDecls(null);
            xMLSerializer.endAttributes();
            this.xducer.writeText(xMLSerializer, this.acc.get(beant), str);
            xMLSerializer.endElement();
        }
    }

    public static class CompositeTransducedAccessorImpl<BeanT, ValueT> extends TransducedAccessor<BeanT> {
        protected final Accessor<BeanT, ValueT> acc;
        protected final Transducer<ValueT> xducer;

        public CompositeTransducedAccessorImpl(JAXBContextImpl jAXBContextImpl, Transducer<ValueT> transducer, Accessor<BeanT, ValueT> accessor) {
            this.xducer = transducer;
            this.acc = accessor.optimize(jAXBContextImpl);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public CharSequence print(BeanT beant) throws AccessorException {
            ValueT valuet = this.acc.get(beant);
            if (valuet == null) {
                return null;
            }
            return this.xducer.print(valuet);
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void parse(BeanT beant, CharSequence charSequence) throws SAXException, AccessorException {
            this.acc.set(beant, this.xducer.parse(charSequence));
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public boolean hasValue(BeanT beant) throws AccessorException {
            return this.acc.getUnadapted(beant) != null;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void writeLeafElement(XMLSerializer xMLSerializer, Name name, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            this.xducer.writeLeafElement(xMLSerializer, name, this.acc.get(beant), str);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void writeText(XMLSerializer xMLSerializer, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            this.xducer.writeText(xMLSerializer, this.acc.get(beant), str);
        }
    }

    private static final class IDREFTransducedAccessorImpl<BeanT, TargetT> extends DefaultTransducedAccessor<BeanT> {
        private final Accessor<BeanT, TargetT> acc;
        private final Class<TargetT> targetType;

        public IDREFTransducedAccessorImpl(Accessor<BeanT, TargetT> accessor) {
            this.acc = accessor;
            this.targetType = accessor.getValueType();
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public String print(BeanT beant) throws SAXException, AccessorException {
            TargetT targett = this.acc.get(beant);
            if (targett == null) {
                return null;
            }
            XMLSerializer xMLSerializer = XMLSerializer.getInstance();
            try {
                String id = xMLSerializer.grammar.getBeanInfo((Object) targett, true).getId(targett, xMLSerializer);
                if (id == null) {
                    xMLSerializer.errorMissingId(targett);
                }
                return id;
            } catch (JAXBException e) {
                xMLSerializer.reportError(null, e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assign(BeanT beant, TargetT targett, UnmarshallingContext unmarshallingContext) throws AccessorException {
            if (this.targetType.isInstance(targett)) {
                this.acc.set(beant, targett);
            } else {
                unmarshallingContext.handleError(Messages.UNASSIGNABLE_TYPE.format(this.targetType, targett.getClass()));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public void parse(final BeanT beant, CharSequence charSequence) throws SAXException, AccessorException {
            final String string = WhiteSpaceProcessor.trim(charSequence).toString();
            final UnmarshallingContext unmarshallingContext = UnmarshallingContext.getInstance();
            final Callable objectFromId = unmarshallingContext.getObjectFromId(string, this.acc.valueType);
            if (objectFromId == null) {
                unmarshallingContext.errorUnresolvedIDREF(beant, string, unmarshallingContext.getLocator());
                return;
            }
            try {
                Object objCall = objectFromId.call();
                if (objCall != null) {
                    assign(beant, objCall, unmarshallingContext);
                } else {
                    final LocatorEx.Snapshot snapshot = new LocatorEx.Snapshot(unmarshallingContext.getLocator());
                    unmarshallingContext.addPatcher(new Patcher() { // from class: com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor.IDREFTransducedAccessorImpl.1
                        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Patcher
                        public void run() throws SAXException {
                            try {
                                Object objCall2 = objectFromId.call();
                                if (objCall2 != null) {
                                    IDREFTransducedAccessorImpl.this.assign(beant, objCall2, unmarshallingContext);
                                } else {
                                    unmarshallingContext.errorUnresolvedIDREF(beant, string, snapshot);
                                }
                            } catch (AccessorException e) {
                                unmarshallingContext.handleError(e);
                            } catch (RuntimeException e2) {
                                throw e2;
                            } catch (SAXException e3) {
                                throw e3;
                            } catch (Exception e4) {
                                throw new SAXException2(e4);
                            }
                        }
                    });
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (SAXException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new SAXException2(e3);
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
        public boolean hasValue(BeanT beant) throws AccessorException {
            return this.acc.get(beant) != null;
        }
    }
}
