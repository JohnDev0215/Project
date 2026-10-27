package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.AccessorFactory;
import com.sun.xml.bind.InternalAccessorFactory;
import com.sun.xml.bind.XmlAccessorFactory;
import com.sun.xml.bind.annotation.XmlLocation;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeElement;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeValuePropertyInfo;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
class RuntimeClassInfoImpl extends ClassInfoImpl<Type, Class, Field, Method> implements RuntimeClassInfo, RuntimeElement {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private AccessorFactory accessorFactory;
    private Accessor<?, Map<QName, String>> attributeWildcardAccessor;
    private boolean computedTransducer;
    private boolean supressAccessorWarnings;
    private Transducer xducer;
    private Accessor<?, Locator> xmlLocationAccessor;

    public RuntimeClassInfoImpl(RuntimeModelBuilder runtimeModelBuilder, Locatable locatable, Class cls) {
        super(runtimeModelBuilder, locatable, cls);
        this.supressAccessorWarnings = false;
        this.computedTransducer = false;
        this.xducer = null;
        this.accessorFactory = createAccessorFactory(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.sun.xml.bind.AccessorFactory createAccessorFactory(java.lang.Class r8) {
        /*
            r7 = this;
            com.sun.xml.bind.v2.model.impl.ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> r0 = r7.builder
            com.sun.xml.bind.v2.model.impl.RuntimeModelBuilder r0 = (com.sun.xml.bind.v2.model.impl.RuntimeModelBuilder) r0
            com.sun.xml.bind.v2.runtime.JAXBContextImpl r0 = r0.context
            if (r0 == 0) goto L71
            boolean r1 = r0.supressAccessorWarnings
            r7.supressAccessorWarnings = r1
            boolean r0 = r0.xmlAccessorFactorySupport
            if (r0 == 0) goto L71
            com.sun.xml.bind.XmlAccessorFactory r0 = r7.findXmlAccessorFactoryAnnotation(r8)
            if (r0 == 0) goto L71
            r1 = 1
            r2 = 0
            r3 = 2
            java.lang.Class r4 = r0.value()     // Catch: java.lang.IllegalAccessException -> L24 java.lang.InstantiationException -> L4b
            java.lang.Object r4 = r4.newInstance()     // Catch: java.lang.IllegalAccessException -> L24 java.lang.InstantiationException -> L4b
            com.sun.xml.bind.AccessorFactory r4 = (com.sun.xml.bind.AccessorFactory) r4     // Catch: java.lang.IllegalAccessException -> L24 java.lang.InstantiationException -> L4b
            goto L72
        L24:
            com.sun.xml.bind.v2.model.impl.ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> r4 = r7.builder
            com.sun.xml.bind.v2.runtime.IllegalAnnotationException r5 = new com.sun.xml.bind.v2.runtime.IllegalAnnotationException
            com.sun.xml.bind.v2.model.impl.Messages r6 = com.sun.xml.bind.v2.model.impl.Messages.ACCESSORFACTORY_ACCESS_EXCEPTION
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r3[r2] = r0
            com.sun.xml.bind.v2.model.nav.Navigator r0 = r7.nav()
            java.lang.String r8 = r0.getClassName(r8)
            r3[r1] = r8
            java.lang.String r8 = r6.format(r3)
            r5.<init>(r8, r7)
            r4.reportError(r5)
            goto L71
        L4b:
            com.sun.xml.bind.v2.model.impl.ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> r4 = r7.builder
            com.sun.xml.bind.v2.runtime.IllegalAnnotationException r5 = new com.sun.xml.bind.v2.runtime.IllegalAnnotationException
            com.sun.xml.bind.v2.model.impl.Messages r6 = com.sun.xml.bind.v2.model.impl.Messages.ACCESSORFACTORY_INSTANTIATION_EXCEPTION
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r3[r2] = r0
            com.sun.xml.bind.v2.model.nav.Navigator r0 = r7.nav()
            java.lang.String r8 = r0.getClassName(r8)
            r3[r1] = r8
            java.lang.String r8 = r6.format(r3)
            r5.<init>(r8, r7)
            r4.reportError(r5)
        L71:
            r4 = 0
        L72:
            if (r4 != 0) goto L78
            com.sun.xml.bind.AccessorFactoryImpl r4 = com.sun.xml.bind.AccessorFactoryImpl.getInstance()
        L78:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.model.impl.RuntimeClassInfoImpl.createAccessorFactory(java.lang.Class):com.sun.xml.bind.AccessorFactory");
    }

    protected XmlAccessorFactory findXmlAccessorFactoryAnnotation(Class cls) {
        XmlAccessorFactory xmlAccessorFactory = (XmlAccessorFactory) reader().getClassAnnotation(XmlAccessorFactory.class, cls, this);
        return xmlAccessorFactory == null ? (XmlAccessorFactory) reader().getPackageAnnotation(XmlAccessorFactory.class, cls, this) : xmlAccessorFactory;
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl, com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo
    public Method getFactoryMethod() {
        return super.getFactoryMethod();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl, com.sun.xml.bind.v2.model.core.ClassInfo
    /* JADX INFO: renamed from: getBaseClass, reason: merged with bridge method [inline-methods] */
    public final ClassInfo<Type, Class> getBaseClass2() {
        return (RuntimeClassInfoImpl) super.getBaseClass2();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    protected ReferencePropertyInfoImpl<Type, Class, Field, Method> createReferenceProperty(PropertySeed<Type, Class, Field, Method> propertySeed) {
        return new RuntimeReferencePropertyInfoImpl(this, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    protected AttributePropertyInfoImpl<Type, Class, Field, Method> createAttributeProperty(PropertySeed<Type, Class, Field, Method> propertySeed) {
        return new RuntimeAttributePropertyInfoImpl(this, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    protected ValuePropertyInfoImpl<Type, Class, Field, Method> createValueProperty(PropertySeed<Type, Class, Field, Method> propertySeed) {
        return new RuntimeValuePropertyInfoImpl(this, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    protected ElementPropertyInfoImpl<Type, Class, Field, Method> createElementProperty(PropertySeed<Type, Class, Field, Method> propertySeed) {
        return new RuntimeElementPropertyInfoImpl(this, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    protected MapPropertyInfoImpl<Type, Class, Field, Method> createMapProperty(PropertySeed<Type, Class, Field, Method> propertySeed) {
        return new RuntimeMapPropertyInfoImpl(this, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl, com.sun.xml.bind.v2.model.core.ClassInfo
    public List<? extends PropertyInfo<Type, Class>> getProperties() {
        return super.getProperties();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl, com.sun.xml.bind.v2.model.core.ClassInfo
    public PropertyInfo<Type, Class> getProperty(String str) {
        return (RuntimePropertyInfo) super.getProperty(str);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl, com.sun.xml.bind.v2.model.impl.TypeInfoImpl
    public void link() {
        getTransducer();
        super.link();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo
    public <B> Accessor<B, Map<QName, String>> getAttributeWildcard() {
        for (RuntimeClassInfoImpl baseClass2 = this; baseClass2 != 0; baseClass2 = baseClass2.getBaseClass2()) {
            if (baseClass2.attributeWildcard != null) {
                if (baseClass2.attributeWildcardAccessor == null) {
                    baseClass2.attributeWildcardAccessor = baseClass2.createAttributeWildcardAccessor();
                }
                return (Accessor<B, Map<QName, String>>) baseClass2.attributeWildcardAccessor;
            }
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeNonElement
    public Transducer getTransducer() {
        if (!this.computedTransducer) {
            this.computedTransducer = true;
            this.xducer = calcTransducer();
        }
        return this.xducer;
    }

    private Transducer calcTransducer() {
        if (hasAttributeWildcard()) {
            return null;
        }
        RuntimeValuePropertyInfo runtimeValuePropertyInfo = null;
        for (ClassInfo<Type, Class> baseClass2 = this; baseClass2 != null; baseClass2 = baseClass2.getBaseClass2()) {
            Iterator<? extends PropertyInfo<Type, Class>> it = baseClass2.getProperties().iterator();
            while (it.hasNext()) {
                RuntimePropertyInfo runtimePropertyInfo = (RuntimePropertyInfo) it.next();
                if (runtimePropertyInfo.kind() != PropertyKind.VALUE) {
                    return null;
                }
                runtimeValuePropertyInfo = (RuntimeValuePropertyInfo) runtimePropertyInfo;
            }
        }
        if (runtimeValuePropertyInfo != null && runtimeValuePropertyInfo.getTarget().isSimpleType()) {
            return new TransducerImpl(getClazz(), TransducedAccessor.get(((RuntimeModelBuilder) this.builder).context, runtimeValuePropertyInfo));
        }
        return null;
    }

    private Accessor<?, Map<QName, String>> createAttributeWildcardAccessor() {
        return ((RuntimePropertySeed) this.attributeWildcard).getAccessor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    public RuntimePropertySeed createFieldSeed(Field field) {
        Accessor errorInstance;
        boolean zIsStatic = Modifier.isStatic(field.getModifiers());
        try {
            if (this.supressAccessorWarnings) {
                errorInstance = ((InternalAccessorFactory) this.accessorFactory).createFieldAccessor((Class) this.clazz, field, zIsStatic, this.supressAccessorWarnings);
            } else {
                errorInstance = this.accessorFactory.createFieldAccessor((Class) this.clazz, field, zIsStatic);
            }
        } catch (JAXBException e) {
            this.builder.reportError(new IllegalAnnotationException(Messages.CUSTOM_ACCESSORFACTORY_FIELD_ERROR.format(nav().getClassName(this.clazz), e.toString()), this));
            errorInstance = Accessor.getErrorInstance();
        }
        return new RuntimePropertySeed(super.createFieldSeed(field), errorInstance);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    public RuntimePropertySeed createAccessorSeed(Method method, Method method2) {
        Accessor errorInstance;
        try {
            errorInstance = this.accessorFactory.createPropertyAccessor((Class) this.clazz, method, method2);
        } catch (JAXBException e) {
            this.builder.reportError(new IllegalAnnotationException(Messages.CUSTOM_ACCESSORFACTORY_PROPERTY_ERROR.format(nav().getClassName(this.clazz), e.toString()), this));
            errorInstance = Accessor.getErrorInstance();
        }
        return new RuntimePropertySeed(super.createAccessorSeed(method, method2), errorInstance);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.ClassInfoImpl
    public void checkFieldXmlLocation(Field field) {
        if (reader().hasFieldAnnotation(XmlLocation.class, field)) {
            this.xmlLocationAccessor = new Accessor.FieldReflection(field);
        }
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeClassInfo
    public Accessor<?, Locator> getLocatorField() {
        return this.xmlLocationAccessor;
    }

    static final class RuntimePropertySeed implements PropertySeed<Type, Class, Field, Method> {
        private final Accessor acc;
        private final PropertySeed<Type, Class, Field, Method> core;

        public RuntimePropertySeed(PropertySeed<Type, Class, Field, Method> propertySeed, Accessor accessor) {
            this.core = propertySeed;
            this.acc = accessor;
        }

        @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
        public String getName() {
            return this.core.getName();
        }

        @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
        public <A extends Annotation> A readAnnotation(Class<A> cls) {
            return (A) this.core.readAnnotation(cls);
        }

        @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
        public boolean hasAnnotation(Class<? extends Annotation> cls) {
            return this.core.hasAnnotation(cls);
        }

        @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
        public Type getRawType() {
            return this.core.getRawType();
        }

        @Override // com.sun.xml.bind.v2.model.annotation.Locatable
        public Location getLocation() {
            return this.core.getLocation();
        }

        @Override // com.sun.xml.bind.v2.model.annotation.Locatable
        public Locatable getUpstream() {
            return this.core.getUpstream();
        }

        public Accessor getAccessor() {
            return this.acc;
        }
    }

    private static final class TransducerImpl<BeanT> implements Transducer<BeanT> {
        private final Class<BeanT> ownerClass;
        private final TransducedAccessor<BeanT> xacc;

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public QName getTypeName(BeanT beant) {
            return null;
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public boolean isDefault() {
            return false;
        }

        public TransducerImpl(Class<BeanT> cls, TransducedAccessor<BeanT> transducedAccessor) {
            this.xacc = transducedAccessor;
            this.ownerClass = cls;
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public boolean useNamespace() {
            return this.xacc.useNamespace();
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public void declareNamespace(BeanT beant, XMLSerializer xMLSerializer) throws AccessorException {
            try {
                this.xacc.declareNamespace(beant, xMLSerializer);
            } catch (SAXException e) {
                throw new AccessorException(e);
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public CharSequence print(BeanT beant) throws AccessorException {
            try {
                CharSequence charSequencePrint = this.xacc.print(beant);
                if (charSequencePrint != null) {
                    return charSequencePrint;
                }
                throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(beant));
            } catch (SAXException e) {
                throw new AccessorException(e);
            }
        }

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
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public BeanT parse(CharSequence charSequence) throws SAXException, AccessorException {
            BeanT beant;
            UnmarshallingContext unmarshallingContext = UnmarshallingContext.getInstance();
            if (unmarshallingContext != null) {
                beant = (BeanT) unmarshallingContext.createInstance((Class<?>) this.ownerClass);
            } else {
                beant = (BeanT) ClassFactory.create(this.ownerClass);
            }
            this.xacc.parse(beant, charSequence);
            return beant;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public void writeText(XMLSerializer xMLSerializer, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            if (!this.xacc.hasValue(beant)) {
                throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(beant));
            }
            this.xacc.writeText(xMLSerializer, beant, str);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public void writeLeafElement(XMLSerializer xMLSerializer, Name name, BeanT beant, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            if (!this.xacc.hasValue(beant)) {
                throw new AccessorException(Messages.THERE_MUST_BE_VALUE_IN_XMLVALUE.format(beant));
            }
            this.xacc.writeLeafElement(xMLSerializer, name, beant, str);
        }
    }
}
