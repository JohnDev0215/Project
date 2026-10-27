package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.annotation.RuntimeAnnotationReader;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElementRef;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet;
import com.sun.xml.bind.v2.runtime.FilterTransducer;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.InlineBinaryTransducer;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.MimeTypedTransducer;
import com.sun.xml.bind.v2.runtime.SchemaTypeTransducer;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import javax.activation.MimeType;
import javax.xml.namespace.QName;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class RuntimeModelBuilder extends ModelBuilder<Type, Class, Field, Method> {
    public final JAXBContextImpl context;

    public RuntimeModelBuilder(JAXBContextImpl jAXBContextImpl, RuntimeAnnotationReader runtimeAnnotationReader, Map<Class, Class> map, String str) {
        super(runtimeAnnotationReader, Utils.REFLECTION_NAVIGATOR, map, str);
        this.context = jAXBContextImpl;
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeNonElement getClassInfo(Class cls, Locatable locatable) {
        return (RuntimeNonElement) super.getClassInfo(cls, locatable);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeNonElement getClassInfo(Class cls, boolean z, Locatable locatable) {
        return (RuntimeNonElement) super.getClassInfo(cls, z, locatable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeEnumLeafInfoImpl createEnumLeafInfo(Class cls, Locatable locatable) {
        return new RuntimeEnumLeafInfoImpl(this, locatable, cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeClassInfoImpl createClassInfo(Class cls, Locatable locatable) {
        return new RuntimeClassInfoImpl(this, locatable, cls);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeElementInfoImpl createElementInfo(RegistryInfoImpl<Type, Class, Field, Method> registryInfoImpl, Method method) throws IllegalAnnotationException {
        return new RuntimeElementInfoImpl(this, registryInfoImpl, method);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public RuntimeArrayInfoImpl createArrayInfo(Locatable locatable, Type type) {
        return new RuntimeArrayInfoImpl(this, locatable, (Class) type);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    /* JADX INFO: renamed from: createTypeInfoSet, reason: merged with bridge method [inline-methods] */
    public TypeInfoSetImpl<Type, Class, Field, Method> createTypeInfoSet2() {
        return new RuntimeTypeInfoSetImpl(this.reader);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilder
    public TypeInfoSet<Type, Class, Field, Method> link() {
        return (RuntimeTypeInfoSet) super.link();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement] */
    public static Transducer createTransducer(RuntimeNonElementRef runtimeNonElementRef) {
        Transducer transducer = runtimeNonElementRef.getTarget().getTransducer();
        PropertyInfo<Type, Class> source = runtimeNonElementRef.getSource();
        ID id = source.id();
        if (id == ID.IDREF) {
            return RuntimeBuiltinLeafInfoImpl.STRING;
        }
        if (id == ID.ID) {
            transducer = new IDTransducerImpl(transducer);
        }
        MimeType expectedMimeType = source.getExpectedMimeType();
        if (expectedMimeType != null) {
            transducer = new MimeTypedTransducer(transducer, expectedMimeType);
        }
        if (source.inlineBinaryData()) {
            transducer = new InlineBinaryTransducer(transducer);
        }
        if (source.getSchemaType() == null) {
            return transducer;
        }
        if (source.getSchemaType().equals(createXSSimpleType())) {
            return RuntimeBuiltinLeafInfoImpl.STRING;
        }
        return new SchemaTypeTransducer(transducer, source.getSchemaType());
    }

    private static QName createXSSimpleType() {
        return new QName(WellKnownNamespace.XML_SCHEMA, "anySimpleType");
    }

    private static final class IDTransducerImpl<ValueT> extends FilterTransducer<ValueT> {
        public IDTransducerImpl(Transducer<ValueT> transducer) {
            super(transducer);
        }

        @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
        public ValueT parse(CharSequence charSequence) throws SAXException, AccessorException {
            String string = WhiteSpaceProcessor.trim(charSequence).toString();
            UnmarshallingContext.getInstance().addToIdTable(string);
            return (ValueT) this.core.parse(string);
        }
    }
}
