package com.sun.xml.bind.v2.model.impl;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.ReferencePropertyInfo;
import com.sun.xml.bind.v2.model.core.WildcardMode;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class ReferencePropertyInfoImpl<T, C, F, M> extends ERPropertyInfoImpl<T, C, F, M> implements ReferencePropertyInfo<T, C>, DummyPropertyInfo<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean is2_2 = true;
    private final C domHandler;
    private final boolean isMixed;
    private Boolean isRequired;
    private Set<ReferencePropertyInfoImpl<T, C, F, M>> subTypes;
    private Set<Element<T, C>> types;
    private final WildcardMode wildcard;

    public ReferencePropertyInfoImpl(ClassInfoImpl<T, C, F, M> classInfoImpl, PropertySeed<T, C, F, M> propertySeed) {
        super(classInfoImpl, propertySeed);
        this.subTypes = new LinkedHashSet();
        this.isMixed = propertySeed.readAnnotation(XmlMixed.class) != null;
        XmlAnyElement xmlAnyElement = (XmlAnyElement) propertySeed.readAnnotation(XmlAnyElement.class);
        if (xmlAnyElement == null) {
            this.wildcard = null;
            this.domHandler = null;
        } else {
            this.wildcard = xmlAnyElement.lax() ? WildcardMode.LAX : WildcardMode.SKIP;
            this.domHandler = nav().asDecl(reader().getClassValue2(xmlAnyElement, WuwInfoBean.ValueKey));
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public Set<? extends Element<T, C>> ref() {
        return getElements();
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public PropertyKind kind() {
        return PropertyKind.REFERENCE;
    }

    @Override // com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public Set<? extends Element<T, C>> getElements() {
        if (this.types == null) {
            calcTypes(false);
        }
        return this.types;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void calcTypes(boolean z) {
        XmlElementRef[] xmlElementRefArrValue;
        XmlElementRef[] xmlElementRefArrValue2;
        boolean zAddAllSubtypes;
        boolean zAddAllSubtypes2;
        this.types = new LinkedHashSet();
        XmlElementRefs xmlElementRefs = (XmlElementRefs) this.seed.readAnnotation(XmlElementRefs.class);
        XmlElementRef xmlElementRef = (XmlElementRef) this.seed.readAnnotation(XmlElementRef.class);
        int i = 1;
        if (xmlElementRefs != null && xmlElementRef != null) {
            this.parent.builder.reportError(new IllegalAnnotationException(Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS.format(nav().getClassName(this.parent.getClazz()) + '#' + this.seed.getName(), xmlElementRef.annotationType().getName(), xmlElementRefs.annotationType().getName()), xmlElementRef, xmlElementRefs));
        }
        if (xmlElementRefs != null) {
            xmlElementRefArrValue = xmlElementRefs.value();
        } else {
            xmlElementRefArrValue = xmlElementRef != null ? new XmlElementRef[]{xmlElementRef} : null;
        }
        this.isRequired = Boolean.valueOf(!isCollection());
        if (xmlElementRefArrValue != null) {
            Navigator navigatorNav = nav();
            AnnotationReader erVar = reader();
            Object objRef = navigatorNav.ref(XmlElementRef.DEFAULT.class);
            Object objAsDecl = navigatorNav.asDecl(JAXBElement.class);
            for (XmlElementRef xmlElementRef2 : xmlElementRefArrValue) {
                Object classValue = erVar.getClassValue2(xmlElementRef2, "type");
                if (nav().isSameType(classValue, objRef)) {
                    classValue = navigatorNav.erasure(getIndividualType());
                }
                if (navigatorNav.getBaseClass(classValue, objAsDecl) != null) {
                    zAddAllSubtypes2 = addGenericElement(xmlElementRef2);
                } else {
                    zAddAllSubtypes2 = addAllSubtypes(classValue);
                }
                if (this.isRequired.booleanValue() && !isRequired(xmlElementRef2)) {
                    this.isRequired = false;
                }
                if (z && !zAddAllSubtypes2) {
                    if (nav().isSameType(classValue, navigatorNav.ref(JAXBElement.class))) {
                        this.parent.builder.reportError(new IllegalAnnotationException(Messages.NO_XML_ELEMENT_DECL.format(getEffectiveNamespaceFor(xmlElementRef2), xmlElementRef2.name()), this));
                        return;
                    } else {
                        this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ELEMENT_REF.format(classValue), this));
                        return;
                    }
                }
            }
        }
        for (ReferencePropertyInfoImpl<T, C, F, M> referencePropertyInfoImpl : this.subTypes) {
            PropertySeed<T, C, F, M> propertySeed = referencePropertyInfoImpl.seed;
            XmlElementRefs xmlElementRefs2 = (XmlElementRefs) propertySeed.readAnnotation(XmlElementRefs.class);
            XmlElementRef xmlElementRef3 = (XmlElementRef) propertySeed.readAnnotation(XmlElementRef.class);
            if (xmlElementRefs2 != null && xmlElementRef3 != null) {
                ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> modelBuilder = this.parent.builder;
                Messages messages = Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS;
                Object[] objArr = new Object[3];
                objArr[0] = nav().getClassName(this.parent.getClazz()) + '#' + this.seed.getName();
                objArr[i] = xmlElementRef3.annotationType().getName();
                objArr[2] = xmlElementRefs2.annotationType().getName();
                modelBuilder.reportError(new IllegalAnnotationException(messages.format(objArr), xmlElementRef3, xmlElementRefs2));
            }
            if (xmlElementRefs2 != null) {
                xmlElementRefArrValue2 = xmlElementRefs2.value();
            } else if (xmlElementRef3 != null) {
                XmlElementRef[] xmlElementRefArr = new XmlElementRef[i];
                xmlElementRefArr[0] = xmlElementRef3;
                xmlElementRefArrValue2 = xmlElementRefArr;
            } else {
                xmlElementRefArrValue2 = null;
            }
            if (xmlElementRefArrValue2 != null) {
                Navigator navigatorNav2 = nav();
                AnnotationReader erVar2 = reader();
                Object objRef2 = navigatorNav2.ref(XmlElementRef.DEFAULT.class);
                Object objAsDecl2 = navigatorNav2.asDecl(JAXBElement.class);
                for (XmlElementRef xmlElementRef4 : xmlElementRefArrValue2) {
                    Object classValue2 = erVar2.getClassValue2(xmlElementRef4, "type");
                    if (nav().isSameType(classValue2, objRef2)) {
                        classValue2 = navigatorNav2.erasure(getIndividualType());
                    }
                    if (navigatorNav2.getBaseClass(classValue2, objAsDecl2) != null) {
                        zAddAllSubtypes = addGenericElement(xmlElementRef4, referencePropertyInfoImpl);
                    } else {
                        zAddAllSubtypes = addAllSubtypes(classValue2);
                    }
                    if (z && !zAddAllSubtypes) {
                        if (nav().isSameType(classValue2, navigatorNav2.ref(JAXBElement.class))) {
                            this.parent.builder.reportError(new IllegalAnnotationException(Messages.NO_XML_ELEMENT_DECL.format(getEffectiveNamespaceFor(xmlElementRef4), xmlElementRef4.name()), this));
                            return;
                        } else {
                            this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ELEMENT_REF.format(new Object[0]), this));
                            return;
                        }
                    }
                    i = 1;
                }
            }
        }
        this.types = Collections.unmodifiableSet(this.types);
    }

    @Override // com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public boolean isRequired() {
        if (this.isRequired == null) {
            calcTypes(false);
        }
        return this.isRequired.booleanValue();
    }

    private boolean isRequired(XmlElementRef xmlElementRef) {
        if (!is2_2) {
            return true;
        }
        try {
            return xmlElementRef.required();
        } catch (LinkageError unused) {
            is2_2 = false;
            return true;
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private boolean addGenericElement(XmlElementRef xmlElementRef) {
        String effectiveNamespaceFor = getEffectiveNamespaceFor(xmlElementRef);
        return addGenericElement(this.parent.owner.getElementInfo((ClassDeclT) this.parent.getClazz(), new QName(effectiveNamespaceFor, xmlElementRef.name())));
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private boolean addGenericElement(XmlElementRef xmlElementRef, ReferencePropertyInfoImpl<T, C, F, M> referencePropertyInfoImpl) {
        String effectiveNamespaceFor = referencePropertyInfoImpl.getEffectiveNamespaceFor(xmlElementRef);
        this.types.add(this.parent.owner.getElementInfo((ClassDeclT) referencePropertyInfoImpl.parent.getClazz(), new QName(effectiveNamespaceFor, xmlElementRef.name())));
        return true;
    }

    private String getEffectiveNamespaceFor(XmlElementRef xmlElementRef) {
        String strNamespace = xmlElementRef.namespace();
        XmlSchema xmlSchema = (XmlSchema) reader().getPackageAnnotation(XmlSchema.class, this.parent.getClazz(), this);
        return (xmlSchema != null && xmlSchema.attributeFormDefault() == XmlNsForm.QUALIFIED && strNamespace.length() == 0) ? this.parent.builder.defaultNsUri : strNamespace;
    }

    private boolean addGenericElement(ElementInfo<T, C> elementInfo) {
        if (elementInfo == null) {
            return false;
        }
        this.types.add(elementInfo);
        Iterator<? extends ElementInfo<T, C>> it = elementInfo.getSubstitutionMembers().iterator();
        while (it.hasNext()) {
            addGenericElement(it.next());
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private boolean addAllSubtypes(T t) {
        Navigator<T, C, F, M> navigatorNav = nav();
        NonElement classInfo = this.parent.builder.getClassInfo((ClassDeclT) navigatorNav.asDecl(t), this);
        boolean z = false;
        if (!(classInfo instanceof ClassInfo)) {
            return false;
        }
        ClassInfo classInfo2 = (ClassInfo) classInfo;
        if (classInfo2.isElement()) {
            this.types.add(classInfo2.asElement());
            z = true;
        }
        for (ClassInfo classInfo3 : this.parent.owner.beans().values()) {
            if (classInfo3.isElement() && navigatorNav.isSubClassOf(classInfo3.getType2(), t)) {
                this.types.add(classInfo3.asElement());
                z = true;
            }
        }
        for (ElementInfo elementInfo : this.parent.owner.getElementMappings(null).values()) {
            if (navigatorNav.isSubClassOf(elementInfo.getType2(), t)) {
                this.types.add(elementInfo);
                z = true;
            }
        }
        return z;
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl
    protected void link() {
        super.link();
        calcTypes(true);
    }

    @Override // com.sun.xml.bind.v2.model.impl.DummyPropertyInfo
    public final void addType(PropertyInfoImpl<T, C, F, M> propertyInfoImpl) {
        this.subTypes.add((ReferencePropertyInfoImpl) propertyInfoImpl);
    }

    @Override // com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public final boolean isMixed() {
        return this.isMixed;
    }

    @Override // com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public final WildcardMode getWildcard() {
        return this.wildcard;
    }

    @Override // com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public final C getDOMHandler() {
        return this.domHandler;
    }
}
