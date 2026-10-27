package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.annotation.FieldLocatable;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.runtime.RuntimeEnumLeafInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import java.lang.Enum;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeEnumLeafInfoImpl<T extends Enum<T>, B> extends EnumLeafInfoImpl<Type, Class, Field, Method> implements RuntimeEnumLeafInfo, Transducer<T> {
    private final Transducer<B> baseXducer;
    private final Map<B, T> parseMap;
    private final Map<T, B> printMap;

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo, com.sun.xml.bind.v2.model.runtime.RuntimeNonElement
    public Transducer<T> getTransducer() {
        return this;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public QName getTypeName(T t) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public boolean isDefault() {
        return false;
    }

    RuntimeEnumLeafInfoImpl(RuntimeModelBuilder runtimeModelBuilder, Locatable locatable, Class<T> cls) {
        super(runtimeModelBuilder, locatable, cls, cls);
        this.parseMap = new HashMap();
        this.printMap = new EnumMap(cls);
        this.baseXducer = ((RuntimeNonElement) this.baseType).getTransducer();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.model.impl.EnumLeafInfoImpl
    public RuntimeEnumConstantImpl createEnumConstant(String str, String str2, Field field, EnumConstantImpl<Type, Class, Field, Method> enumConstantImpl) {
        try {
            try {
                field.setAccessible(true);
            } catch (SecurityException unused) {
            }
            B b = null;
            Enum r2 = (Enum) field.get(null);
            try {
                b = this.baseXducer.parse(str2);
            } catch (Exception e) {
                this.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ENUM_VALUE.format(str2, ((Type) this.baseType.getType()).toString()), e, new FieldLocatable(this, field, nav())));
            }
            this.parseMap.put(b, (T) r2);
            this.printMap.put((T) r2, b);
            return new RuntimeEnumConstantImpl(this, str, str2, enumConstantImpl);
        } catch (IllegalAccessException e2) {
            throw new IllegalAccessError(e2.getMessage());
        }
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo
    public QName[] getTypeNames() {
        return new QName[]{getTypeName()};
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.impl.EnumLeafInfoImpl, com.sun.xml.bind.v2.model.core.EnumLeafInfo
    public Class getClazz() {
        return (Class) this.clazz;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public boolean useNamespace() {
        return this.baseXducer.useNamespace();
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void declareNamespace(T t, XMLSerializer xMLSerializer) throws AccessorException {
        this.baseXducer.declareNamespace(this.printMap.get(t), xMLSerializer);
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public CharSequence print(T t) throws AccessorException {
        return this.baseXducer.print(this.printMap.get(t));
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public T parse(CharSequence charSequence) throws SAXException, AccessorException {
        Object objTrim = this.baseXducer.parse(charSequence);
        if (this.tokenStringType) {
            objTrim = ((String) objTrim).trim();
        }
        return this.parseMap.get(objTrim);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void writeText(XMLSerializer xMLSerializer, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        this.baseXducer.writeText(xMLSerializer, this.printMap.get(t), str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        this.baseXducer.writeLeafElement(xMLSerializer, name, this.printMap.get(t), str);
    }
}
