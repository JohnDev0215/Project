package com.sun.xml.bind.v2.model.impl;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.runtime.Location;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class EnumLeafInfoImpl<T, C, F, M> extends TypeInfoImpl<T, C, F, M> implements EnumLeafInfo<T, C>, Element<T, C>, Iterable<EnumConstantImpl<T, C, F, M>> {
    NonElement<T, C> baseType;
    final C clazz;
    private QName elementName;
    private EnumConstantImpl<T, C, F, M> firstConstant;
    protected boolean tokenStringType;
    private final T type;
    private final QName typeName;

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final boolean canBeReferencedByIDREF() {
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public ClassInfo<T, C> getScope() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public Element<T, C> getSubstitutionHead() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public boolean isSimpleType() {
        return true;
    }

    public EnumLeafInfoImpl(ModelBuilder<T, C, F, M> modelBuilder, Locatable locatable, C c, T t) {
        super(modelBuilder, locatable);
        this.clazz = c;
        this.type = t;
        this.elementName = parseElementName(c);
        this.typeName = parseTypeName(c);
        XmlEnum xmlEnum = (XmlEnum) modelBuilder.reader.getClassAnnotation(XmlEnum.class, c, this);
        if (xmlEnum != null) {
            this.baseType = modelBuilder.getTypeInfo(modelBuilder.reader.getClassValue2(xmlEnum, WuwInfoBean.ValueKey), this);
        } else {
            this.baseType = modelBuilder.getTypeInfo(modelBuilder.nav.ref2(String.class), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected void calcConstants() {
        XmlSchemaType xmlSchemaType;
        Iterator<? extends F> it = nav().getDeclaredFields(this.clazz).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            F next = it.next();
            if (nav().isSameType(nav().getFieldType(next), nav().ref2(String.class)) && (xmlSchemaType = (XmlSchemaType) this.builder.reader.getFieldAnnotation(XmlSchemaType.class, next, this)) != null && "token".equals(xmlSchemaType.name())) {
                this.tokenStringType = true;
                break;
            }
        }
        Object[] enumConstants = nav().getEnumConstants(this.clazz);
        EnumConstantImpl<T, C, F, M> enumConstantImplCreateEnumConstant = null;
        for (int length = enumConstants.length - 1; length >= 0; length--) {
            Object obj = enumConstants[length];
            String fieldName = nav().getFieldName(obj);
            XmlEnumValue xmlEnumValue = (XmlEnumValue) this.builder.reader.getFieldAnnotation(XmlEnumValue.class, (F) obj, this);
            enumConstantImplCreateEnumConstant = createEnumConstant(fieldName, xmlEnumValue == null ? fieldName : xmlEnumValue.value(), obj, enumConstantImplCreateEnumConstant);
        }
        this.firstConstant = enumConstantImplCreateEnumConstant;
    }

    protected EnumConstantImpl<T, C, F, M> createEnumConstant(String str, String str2, F f, EnumConstantImpl<T, C, F, M> enumConstantImpl) {
        return new EnumConstantImpl<>(this, str, str2, enumConstantImpl);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public T getType() {
        return this.type;
    }

    public boolean isToken() {
        return this.tokenStringType;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public QName getTypeName() {
        return this.typeName;
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumLeafInfo
    public C getClazz() {
        return this.clazz;
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumLeafInfo
    public NonElement<T, C> getBaseType() {
        return this.baseType;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return nav().getClassLocation(this.clazz);
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumLeafInfo
    public Iterable<? extends EnumConstantImpl<T, C, F, M>> getConstants() {
        if (this.firstConstant == null) {
            calcConstants();
        }
        return this;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoImpl
    public void link() {
        getConstants();
        super.link();
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public QName getElementName() {
        return this.elementName;
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public boolean isElement() {
        return this.elementName != null;
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public Element<T, C> asElement() {
        if (isElement()) {
            return this;
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<EnumConstantImpl<T, C, F, M>> iterator() {
        return new Iterator<EnumConstantImpl<T, C, F, M>>() { // from class: com.sun.xml.bind.v2.model.impl.EnumLeafInfoImpl.1
            private EnumConstantImpl<T, C, F, M> next;

            {
                this.next = EnumLeafInfoImpl.this.firstConstant;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            @Override // java.util.Iterator
            public EnumConstantImpl<T, C, F, M> next() {
                EnumConstantImpl<T, C, F, M> enumConstantImpl = this.next;
                this.next = enumConstantImpl.next;
                return enumConstantImpl;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
