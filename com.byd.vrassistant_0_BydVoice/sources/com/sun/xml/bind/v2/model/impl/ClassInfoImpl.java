package com.sun.xml.bind.v2.model.impl;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.annotation.MethodLocatable;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.ValuePropertyInfo;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import com.sun.xml.bind.v2.util.EditDistance;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public class ClassInfoImpl<T, C, F, M> extends TypeInfoImpl<T, C, F, M> implements ClassInfo<T, C>, Element<T, C> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashMap<Class, Integer> ANNOTATION_NUMBER_MAP;
    private static final String[] DEFAULT_ORDER;
    protected PropertySeed<T, C, F, M> attributeWildcard;
    private ClassInfoImpl<T, C, F, M> baseClass;
    private boolean baseClassComputed;
    protected final C clazz;
    private final QName elementName;
    private M factoryMethod;
    private boolean hasSubClasses;
    private String[] propOrder;
    private FinalArrayList<PropertyInfoImpl<T, C, F, M>> properties;
    private final QName typeName;
    private static final SecondaryAnnotation[] SECONDARY_ANNOTATIONS = SecondaryAnnotation.values();
    private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];

    protected void checkFieldXmlLocation(F f) {
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public ClassInfoImpl<T, C, F, M> getScope() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoImpl, com.sun.xml.bind.v2.model.annotation.Locatable
    public /* bridge */ /* synthetic */ Locatable getUpstream() {
        return super.getUpstream();
    }

    ClassInfoImpl(ModelBuilder<T, C, F, M> modelBuilder, Locatable locatable, C c) {
        super(modelBuilder, locatable);
        this.baseClassComputed = false;
        this.hasSubClasses = false;
        this.factoryMethod = null;
        this.clazz = c;
        QName elementName = parseElementName(c);
        this.elementName = elementName;
        XmlType xmlType = (XmlType) reader().getClassAnnotation(XmlType.class, c, this);
        this.typeName = parseTypeName(c, xmlType);
        if (xmlType != null) {
            String[] strArrPropOrder = xmlType.propOrder();
            if (strArrPropOrder.length == 0) {
                this.propOrder = null;
            } else if (strArrPropOrder[0].length() == 0) {
                this.propOrder = DEFAULT_ORDER;
            } else {
                this.propOrder = strArrPropOrder;
            }
        } else {
            this.propOrder = DEFAULT_ORDER;
        }
        XmlAccessorOrder xmlAccessorOrder = (XmlAccessorOrder) reader().getPackageAnnotation(XmlAccessorOrder.class, c, this);
        if (xmlAccessorOrder != null && xmlAccessorOrder.value() == XmlAccessOrder.UNDEFINED) {
            this.propOrder = null;
        }
        XmlAccessorOrder xmlAccessorOrder2 = (XmlAccessorOrder) reader().getClassAnnotation(XmlAccessorOrder.class, c, this);
        if (xmlAccessorOrder2 != null && xmlAccessorOrder2.value() == XmlAccessOrder.UNDEFINED) {
            this.propOrder = null;
        }
        if (nav().isInterface(c)) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.CANT_HANDLE_INTERFACE.format(nav().getClassName(c)), this));
        }
        if (hasFactoryConstructor(xmlType) || nav().hasDefaultConstructor(c)) {
            return;
        }
        if (nav().isInnerClass(c)) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.CANT_HANDLE_INNER_CLASS.format(nav().getClassName(c)), this));
        } else if (elementName != null) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.NO_DEFAULT_CONSTRUCTOR.format(nav().getClassName(c)), this));
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public ClassInfoImpl<T, C, F, M> getBaseClass() {
        if (!this.baseClassComputed) {
            C superClass = nav().getSuperClass(this.clazz);
            if (superClass == null || superClass == nav().asDecl(Object.class)) {
                this.baseClass = null;
            } else {
                NonElement classInfo = this.builder.getClassInfo((ClassDeclT) superClass, true, this);
                if (classInfo instanceof ClassInfoImpl) {
                    ClassInfoImpl<T, C, F, M> classInfoImpl = (ClassInfoImpl) classInfo;
                    this.baseClass = classInfoImpl;
                    classInfoImpl.hasSubClasses = true;
                } else {
                    this.baseClass = null;
                }
            }
            this.baseClassComputed = true;
        }
        return this.baseClass;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public final Element<T, C> getSubstitutionHead() {
        ClassInfoImpl<T, C, F, M> baseClass = getBaseClass();
        while (baseClass != null && !baseClass.isElement()) {
            baseClass = baseClass.getBaseClass();
        }
        return baseClass;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final C getClazz() {
        return this.clazz;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final T getType() {
        return nav().use(this.clazz);
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public boolean canBeReferencedByIDREF() {
        Iterator<? extends PropertyInfo<T, C>> it = getProperties().iterator();
        while (it.hasNext()) {
            if (it.next().id() == ID.ID) {
                return true;
            }
        }
        ClassInfoImpl<T, C, F, M> baseClass = getBaseClass();
        if (baseClass != null) {
            return baseClass.canBeReferencedByIDREF();
        }
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final String getName() {
        return nav().getClassName(this.clazz);
    }

    public <A extends Annotation> A readAnnotation(Class<A> cls) {
        return (A) reader().getClassAnnotation(cls, this.clazz, this);
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public Element<T, C> asElement() {
        if (isElement()) {
            return this;
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public List<? extends PropertyInfo<T, C>> getProperties() {
        FinalArrayList<PropertyInfoImpl<T, C, F, M>> finalArrayList = this.properties;
        if (finalArrayList != null) {
            return finalArrayList;
        }
        XmlAccessType accessType = getAccessType();
        this.properties = new FinalArrayList<>();
        findFieldProperties(this.clazz, accessType);
        findGetterSetterProperties(accessType);
        String[] strArr = this.propOrder;
        if (strArr == DEFAULT_ORDER || strArr == null) {
            if (getAccessorOrder() == XmlAccessOrder.ALPHABETICAL) {
                Collections.sort(this.properties);
            }
        } else {
            PropertySorter propertySorter = new PropertySorter();
            Iterator<PropertyInfoImpl<T, C, F, M>> it = this.properties.iterator();
            while (it.hasNext()) {
                propertySorter.checkedGet(it.next());
            }
            Collections.sort(this.properties, propertySorter);
            propertySorter.checkUnusedProperties();
        }
        PropertyInfoImpl<T, C, F, M> propertyInfoImpl = null;
        PropertyInfoImpl<T, C, F, M> propertyInfoImpl2 = null;
        for (PropertyInfoImpl<T, C, F, M> propertyInfoImpl3 : this.properties) {
            int i = AnonymousClass1.$SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[propertyInfoImpl3.kind().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                propertyInfoImpl = propertyInfoImpl3;
            } else if (i == 4) {
                if (propertyInfoImpl2 != null) {
                    this.builder.reportError(new IllegalAnnotationException(Messages.MULTIPLE_VALUE_PROPERTY.format(new Object[0]), propertyInfoImpl2, propertyInfoImpl3));
                }
                if (getBaseClass() != null) {
                    this.builder.reportError(new IllegalAnnotationException(Messages.XMLVALUE_IN_DERIVED_TYPE.format(new Object[0]), propertyInfoImpl3));
                }
                propertyInfoImpl2 = propertyInfoImpl3;
            }
        }
        if (propertyInfoImpl != null && propertyInfoImpl2 != null) {
            this.builder.reportError(new IllegalAnnotationException(Messages.ELEMENT_AND_VALUE_PROPERTY.format(new Object[0]), propertyInfoImpl2, propertyInfoImpl));
        }
        return this.properties;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void findFieldProperties(C c, XmlAccessType xmlAccessType) {
        Object superClass = nav().getSuperClass(c);
        if (shouldRecurseSuperClass(superClass)) {
            findFieldProperties(superClass, xmlAccessType);
        }
        for (F f : nav().getDeclaredFields(c)) {
            Annotation[] allFieldAnnotations = reader().getAllFieldAnnotations(f, this);
            boolean zHasFieldAnnotation = reader().hasFieldAnnotation(OverrideAnnotationOf.class, f);
            if (nav().isTransient(f)) {
                if (hasJAXBAnnotation(allFieldAnnotations)) {
                    this.builder.reportError(new IllegalAnnotationException(Messages.TRANSIENT_FIELD_NOT_BINDABLE.format(nav().getFieldName(f)), getSomeJAXBAnnotation(allFieldAnnotations)));
                }
            } else if (nav().isStaticField(f)) {
                if (hasJAXBAnnotation(allFieldAnnotations)) {
                    addProperty(createFieldSeed(f), allFieldAnnotations, false);
                }
            } else {
                if (xmlAccessType == XmlAccessType.FIELD || ((xmlAccessType == XmlAccessType.PUBLIC_MEMBER && nav().isPublicField(f)) || hasJAXBAnnotation(allFieldAnnotations))) {
                    if (zHasFieldAnnotation) {
                        ClassInfo baseClass = getBaseClass();
                        while (baseClass != null && baseClass.getProperty("content") == null) {
                            baseClass = baseClass.getBaseClass();
                        }
                        ((DummyPropertyInfo) baseClass.getProperty("content")).addType(createReferenceProperty(createFieldSeed(f)));
                    } else {
                        addProperty(createFieldSeed(f), allFieldAnnotations, false);
                    }
                }
                checkFieldXmlLocation(f);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean hasValueProperty() {
        ClassInfoImpl<T, C, F, M> baseClass = getBaseClass();
        if (baseClass != null && baseClass.hasValueProperty()) {
            return true;
        }
        Iterator<? extends PropertyInfo<T, C>> it = getProperties().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof ValuePropertyInfo) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public PropertyInfo<T, C> getProperty(String str) {
        for (PropertyInfo<T, C> propertyInfo : getProperties()) {
            if (propertyInfo.getName().equals(str)) {
                return propertyInfo;
            }
        }
        return null;
    }

    /* JADX WARN: Incorrect return type in method signature: <T::Ljava/lang/annotation/Annotation;>(Ljava/lang/Class<TT;>;)TT; */
    private Annotation getClassOrPackageAnnotation(Class cls) {
        Annotation classAnnotation = reader().getClassAnnotation(cls, this.clazz, this);
        return classAnnotation != null ? classAnnotation : reader().getPackageAnnotation(cls, this.clazz, this);
    }

    private XmlAccessType getAccessType() {
        XmlAccessorType xmlAccessorType = (XmlAccessorType) getClassOrPackageAnnotation(XmlAccessorType.class);
        if (xmlAccessorType != null) {
            return xmlAccessorType.value();
        }
        return XmlAccessType.PUBLIC_MEMBER;
    }

    private XmlAccessOrder getAccessorOrder() {
        XmlAccessorOrder xmlAccessorOrder = (XmlAccessorOrder) getClassOrPackageAnnotation(XmlAccessorOrder.class);
        if (xmlAccessorOrder != null) {
            return xmlAccessorOrder.value();
        }
        return XmlAccessOrder.UNDEFINED;
    }

    private final class PropertySorter extends HashMap<String, Integer> implements Comparator<PropertyInfoImpl> {
        private Set<String> collidedNames;
        PropertyInfoImpl[] used;

        PropertySorter() {
            super(ClassInfoImpl.this.propOrder.length);
            this.used = new PropertyInfoImpl[ClassInfoImpl.this.propOrder.length];
            for (String str : ClassInfoImpl.this.propOrder) {
                if (put(str, Integer.valueOf(size())) != null) {
                    ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_ENTRY_IN_PROP_ORDER.format(str), ClassInfoImpl.this));
                }
            }
        }

        @Override // java.util.Comparator
        public int compare(PropertyInfoImpl propertyInfoImpl, PropertyInfoImpl propertyInfoImpl2) {
            return checkedGet(propertyInfoImpl) - checkedGet(propertyInfoImpl2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int checkedGet(PropertyInfoImpl propertyInfoImpl) {
            Integer numValueOf = get(propertyInfoImpl.getName());
            if (numValueOf == null) {
                if (propertyInfoImpl.kind().isOrdered) {
                    ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_MISSING_FROM_ORDER.format(propertyInfoImpl.getName()), propertyInfoImpl));
                }
                numValueOf = Integer.valueOf(size());
                put(propertyInfoImpl.getName(), numValueOf);
            }
            int iIntValue = numValueOf.intValue();
            PropertyInfoImpl[] propertyInfoImplArr = this.used;
            if (iIntValue < propertyInfoImplArr.length) {
                PropertyInfoImpl propertyInfoImpl2 = propertyInfoImplArr[iIntValue];
                if (propertyInfoImpl2 != null && propertyInfoImpl2 != propertyInfoImpl) {
                    if (this.collidedNames == null) {
                        this.collidedNames = new HashSet();
                    }
                    if (this.collidedNames.add(propertyInfoImpl.getName())) {
                        ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_PROPERTIES.format(propertyInfoImpl.getName()), propertyInfoImpl, this.used[iIntValue]));
                    }
                }
                this.used[iIntValue] = propertyInfoImpl;
            }
            return numValueOf.intValue();
        }

        public void checkUnusedProperties() {
            int i = 0;
            while (true) {
                PropertyInfoImpl[] propertyInfoImplArr = this.used;
                if (i >= propertyInfoImplArr.length) {
                    return;
                }
                if (propertyInfoImplArr[i] == null) {
                    String str = ClassInfoImpl.this.propOrder[i];
                    String strFindNearest = EditDistance.findNearest(str, new AbstractList<String>() { // from class: com.sun.xml.bind.v2.model.impl.ClassInfoImpl.PropertySorter.1
                        @Override // java.util.AbstractList, java.util.List
                        public String get(int i2) {
                            return ((PropertyInfoImpl) ClassInfoImpl.this.properties.get(i2)).getName();
                        }

                        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                        public int size() {
                            return ClassInfoImpl.this.properties.size();
                        }
                    });
                    if (!(i > ClassInfoImpl.this.properties.size() - 1 ? false : ((PropertyInfoImpl) ClassInfoImpl.this.properties.get(i)).hasAnnotation(OverrideAnnotationOf.class))) {
                        ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_ORDER_CONTAINS_UNUSED_ENTRY.format(str, strFindNearest), ClassInfoImpl.this));
                    }
                }
                i++;
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public boolean hasProperties() {
        return !this.properties.isEmpty();
    }

    private static <T> T pickOne(T... tArr) {
        for (T t : tArr) {
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    private static <T> List<T> makeSet(T... tArr) {
        FinalArrayList finalArrayList = new FinalArrayList();
        for (T t : tArr) {
            if (t != null) {
                finalArrayList.add(t);
            }
        }
        return finalArrayList;
    }

    private static final class ConflictException extends Exception {
        final List<Annotation> annotations;

        public ConflictException(List<Annotation> list) {
            this.annotations = list;
        }
    }

    private static final class DuplicateException extends Exception {
        final Annotation a1;
        final Annotation a2;

        public DuplicateException(Annotation annotation, Annotation annotation2) {
            this.a1 = annotation;
            this.a2 = annotation2;
        }
    }

    private enum SecondaryAnnotation {
        JAVA_TYPE(1, XmlJavaTypeAdapter.class),
        ID_IDREF(2, XmlID.class, XmlIDREF.class),
        BINARY(4, XmlInlineBinaryData.class, XmlMimeType.class, XmlAttachmentRef.class),
        ELEMENT_WRAPPER(8, XmlElementWrapper.class),
        LIST(16, XmlList.class),
        SCHEMA_TYPE(32, XmlSchemaType.class);

        final int bitMask;
        final Class<? extends Annotation>[] members;

        SecondaryAnnotation(int i, Class... clsArr) {
            this.bitMask = i;
            this.members = clsArr;
        }
    }

    static {
        HashMap<Class, Integer> map = new HashMap<>();
        ANNOTATION_NUMBER_MAP = map;
        Class[] clsArr = {XmlTransient.class, XmlAnyAttribute.class, XmlAttribute.class, XmlValue.class, XmlElement.class, XmlElements.class, XmlElementRef.class, XmlElementRefs.class, XmlAnyElement.class, XmlMixed.class, OverrideAnnotationOf.class};
        for (int i = 0; i < 11; i++) {
            map.put(clsArr[i], Integer.valueOf(map.size()));
        }
        int i2 = 20;
        for (SecondaryAnnotation secondaryAnnotation : SECONDARY_ANNOTATIONS) {
            for (Class<? extends Annotation> cls : secondaryAnnotation.members) {
                map.put(cls, Integer.valueOf(i2));
            }
            i2++;
        }
        DEFAULT_ORDER = new String[0];
    }

    private enum PropertyGroup {
        TRANSIENT(false, false, false, false, false, false),
        ANY_ATTRIBUTE(true, false, false, false, false, false),
        ATTRIBUTE(true, true, true, false, true, true),
        VALUE(true, true, true, false, true, true),
        ELEMENT(true, true, true, true, true, true),
        ELEMENT_REF(true, false, false, true, false, false),
        MAP(false, false, false, true, false, false);

        static final /* synthetic */ boolean $assertionsDisabled = false;
        final int allowedsecondaryAnnotations;

        PropertyGroup(boolean... zArr) {
            int i = 0;
            for (int i2 = 0; i2 < zArr.length; i2++) {
                if (zArr[i2]) {
                    i |= ClassInfoImpl.SECONDARY_ANNOTATIONS[i2].bitMask;
                }
            }
            this.allowedsecondaryAnnotations = ~i;
        }

        boolean allows(SecondaryAnnotation secondaryAnnotation) {
            return (secondaryAnnotation.bitMask & this.allowedsecondaryAnnotations) == 0;
        }
    }

    private void checkConflict(Annotation annotation, Annotation annotation2) throws DuplicateException {
        if (annotation != null) {
            throw new DuplicateException(annotation, annotation2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addProperty(PropertySeed<T, C, F, M> propertySeed, Annotation[] annotationArr, boolean z) {
        PropertyGroup propertyGroup;
        int i;
        PropertySeed<T, C, F, M> propertySeed2;
        Annotation[] annotationArr2 = annotationArr;
        try {
            try {
                int length = annotationArr2.length;
                XmlElementRefs xmlElementRefs = null;
                XmlAnyElement xmlAnyElement = null;
                OverrideAnnotationOf overrideAnnotationOf = null;
                XmlMixed xmlMixed = null;
                XmlElementRef xmlElementRef = null;
                int i2 = 0;
                XmlTransient xmlTransient = null;
                XmlAnyAttribute xmlAnyAttribute = null;
                XmlAttribute xmlAttribute = null;
                XmlValue xmlValue = null;
                XmlElement xmlElement = null;
                XmlElements xmlElements = null;
                int iIntValue = 0;
                while (i2 < length) {
                    int i3 = length;
                    try {
                        Annotation annotation = annotationArr2[i2];
                        Integer num = ANNOTATION_NUMBER_MAP.get(annotation.annotationType());
                        if (num != null) {
                            switch (num.intValue()) {
                                case 0:
                                    checkConflict(xmlTransient, annotation);
                                    xmlTransient = (XmlTransient) annotation;
                                    break;
                                case 1:
                                    checkConflict(xmlAnyAttribute, annotation);
                                    xmlAnyAttribute = (XmlAnyAttribute) annotation;
                                    break;
                                case 2:
                                    checkConflict(xmlAttribute, annotation);
                                    xmlAttribute = (XmlAttribute) annotation;
                                    break;
                                case 3:
                                    checkConflict(xmlValue, annotation);
                                    xmlValue = (XmlValue) annotation;
                                    break;
                                case 4:
                                    checkConflict(xmlElement, annotation);
                                    xmlElement = (XmlElement) annotation;
                                    break;
                                case 5:
                                    checkConflict(xmlElements, annotation);
                                    xmlElements = (XmlElements) annotation;
                                    break;
                                case 6:
                                    checkConflict(xmlElementRef, annotation);
                                    xmlElementRef = (XmlElementRef) annotation;
                                    break;
                                case 7:
                                    checkConflict(xmlElementRefs, annotation);
                                    xmlElementRefs = (XmlElementRefs) annotation;
                                    break;
                                case 8:
                                    checkConflict(xmlAnyElement, annotation);
                                    xmlAnyElement = (XmlAnyElement) annotation;
                                    break;
                                case 9:
                                    checkConflict(xmlMixed, annotation);
                                    xmlMixed = (XmlMixed) annotation;
                                    break;
                                case 10:
                                    checkConflict(overrideAnnotationOf, annotation);
                                    overrideAnnotationOf = (OverrideAnnotationOf) annotation;
                                    break;
                                default:
                                    iIntValue |= 1 << (num.intValue() - 20);
                                    break;
                            }
                        }
                        i2++;
                        annotationArr2 = annotationArr;
                        length = i3;
                    } catch (ConflictException e) {
                        e = e;
                        List<Annotation> list = e.annotations;
                        this.builder.reportError(new IllegalAnnotationException(Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS.format(nav().getClassName(getClazz()) + '#' + propertySeed.getName(), list.get(0).annotationType().getName(), list.get(1).annotationType().getName()), list.get(0), list.get(1)));
                        return;
                    }
                }
                if (xmlTransient != null) {
                    propertyGroup = PropertyGroup.TRANSIENT;
                    i = 1;
                } else {
                    propertyGroup = null;
                    i = 0;
                }
                if (xmlAnyAttribute != null) {
                    propertyGroup = PropertyGroup.ANY_ATTRIBUTE;
                    i++;
                }
                if (xmlAttribute != null) {
                    propertyGroup = PropertyGroup.ATTRIBUTE;
                    i++;
                }
                if (xmlValue != null) {
                    propertyGroup = PropertyGroup.VALUE;
                    i++;
                }
                if (xmlElement != null || xmlElements != null) {
                    propertyGroup = PropertyGroup.ELEMENT;
                    i++;
                }
                if (xmlElementRef != null || xmlElementRefs != null || xmlAnyElement != null || xmlMixed != null || overrideAnnotationOf != null) {
                    propertyGroup = PropertyGroup.ELEMENT_REF;
                    i++;
                }
                if (i > 1) {
                    throw new ConflictException(makeSet(xmlTransient, xmlAnyAttribute, xmlAttribute, xmlValue, (Annotation) pickOne(xmlElement, xmlElements), (Annotation) pickOne(xmlElementRef, xmlElementRefs, xmlAnyElement)));
                }
                if (propertyGroup == null) {
                    if (nav().isSubClassOf(propertySeed.getRawType(), nav().ref(Map.class))) {
                        propertySeed2 = propertySeed;
                        if (!propertySeed2.hasAnnotation(XmlJavaTypeAdapter.class)) {
                            propertyGroup = PropertyGroup.MAP;
                        }
                    } else {
                        propertySeed2 = propertySeed;
                    }
                    propertyGroup = PropertyGroup.ELEMENT;
                } else {
                    propertySeed2 = propertySeed;
                    if (propertyGroup.equals(PropertyGroup.ELEMENT) && nav().isSubClassOf(propertySeed.getRawType(), nav().ref(Map.class)) && !propertySeed2.hasAnnotation(XmlJavaTypeAdapter.class)) {
                        propertyGroup = PropertyGroup.MAP;
                    }
                }
                if ((iIntValue & propertyGroup.allowedsecondaryAnnotations) != 0) {
                    for (SecondaryAnnotation secondaryAnnotation : SECONDARY_ANNOTATIONS) {
                        if (!propertyGroup.allows(secondaryAnnotation)) {
                            for (Class<A> cls : secondaryAnnotation.members) {
                                Annotation annotation2 = propertySeed2.readAnnotation(cls);
                                if (annotation2 != null) {
                                    this.builder.reportError(new IllegalAnnotationException(Messages.ANNOTATION_NOT_ALLOWED.format(cls.getSimpleName()), annotation2));
                                    return;
                                }
                            }
                        }
                    }
                }
                switch (AnonymousClass1.$SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[propertyGroup.ordinal()]) {
                    case 1:
                        return;
                    case 2:
                        if (this.attributeWildcard != null) {
                            this.builder.reportError(new IllegalAnnotationException(Messages.TWO_ATTRIBUTE_WILDCARDS.format(nav().getClassName(getClazz())), xmlAnyAttribute, this.attributeWildcard));
                            return;
                        }
                        this.attributeWildcard = propertySeed2;
                        if (inheritsAttributeWildcard()) {
                            this.builder.reportError(new IllegalAnnotationException(Messages.SUPER_CLASS_HAS_WILDCARD.format(new Object[0]), xmlAnyAttribute, getInheritedAttributeWildcard()));
                            return;
                        } else {
                            if (nav().isSubClassOf(propertySeed.getRawType(), nav().ref(Map.class))) {
                                return;
                            }
                            this.builder.reportError(new IllegalAnnotationException(Messages.INVALID_ATTRIBUTE_WILDCARD_TYPE.format(nav().getTypeName(propertySeed.getRawType())), xmlAnyAttribute, getInheritedAttributeWildcard()));
                            return;
                        }
                    case 3:
                        this.properties.add(createAttributeProperty(propertySeed));
                        return;
                    case 4:
                        this.properties.add(createValueProperty(propertySeed));
                        return;
                    case 5:
                        this.properties.add(createElementProperty(propertySeed));
                        return;
                    case 6:
                        this.properties.add(createReferenceProperty(propertySeed));
                        return;
                    case 7:
                        this.properties.add(createMapProperty(propertySeed));
                        return;
                    default:
                        return;
                }
            } catch (ConflictException e2) {
                e = e2;
            }
        } catch (DuplicateException e3) {
            this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_ANNOTATIONS.format(e3.a1.annotationType().getName()), e3.a1, e3.a2));
        }
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.model.impl.ClassInfoImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup;

        static {
            int[] iArr = new int[PropertyGroup.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup = iArr;
            try {
                iArr[PropertyGroup.TRANSIENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.ANY_ATTRIBUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.ATTRIBUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.ELEMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.ELEMENT_REF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$impl$ClassInfoImpl$PropertyGroup[PropertyGroup.MAP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[PropertyKind.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind = iArr2;
            try {
                iArr2[PropertyKind.ELEMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.MAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.ATTRIBUTE.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    protected ReferencePropertyInfoImpl<T, C, F, M> createReferenceProperty(PropertySeed<T, C, F, M> propertySeed) {
        return new ReferencePropertyInfoImpl<>(this, propertySeed);
    }

    protected AttributePropertyInfoImpl<T, C, F, M> createAttributeProperty(PropertySeed<T, C, F, M> propertySeed) {
        return new AttributePropertyInfoImpl<>(this, propertySeed);
    }

    protected ValuePropertyInfoImpl<T, C, F, M> createValueProperty(PropertySeed<T, C, F, M> propertySeed) {
        return new ValuePropertyInfoImpl<>(this, propertySeed);
    }

    protected ElementPropertyInfoImpl<T, C, F, M> createElementProperty(PropertySeed<T, C, F, M> propertySeed) {
        return new ElementPropertyInfoImpl<>(this, propertySeed);
    }

    protected MapPropertyInfoImpl<T, C, F, M> createMapProperty(PropertySeed<T, C, F, M> propertySeed) {
        return new MapPropertyInfoImpl<>(this, propertySeed);
    }

    private void findGetterSetterProperties(XmlAccessType xmlAccessType) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        C superClass = this.clazz;
        do {
            collectGetterSetters(this.clazz, linkedHashMap, linkedHashMap2);
            superClass = nav().getSuperClass(superClass);
        } while (shouldRecurseSuperClass(superClass));
        TreeSet treeSet = new TreeSet(linkedHashMap.keySet());
        treeSet.retainAll(linkedHashMap2.keySet());
        resurrect(linkedHashMap, treeSet);
        resurrect(linkedHashMap2, treeSet);
        for (String str : treeSet) {
            M m = linkedHashMap.get(str);
            M m2 = linkedHashMap2.get(str);
            Annotation[] allMethodAnnotations = m != null ? reader().getAllMethodAnnotations(m, new MethodLocatable(this, m, nav())) : EMPTY_ANNOTATIONS;
            Annotation[] allMethodAnnotations2 = m2 != null ? reader().getAllMethodAnnotations(m2, new MethodLocatable(this, m2, nav())) : EMPTY_ANNOTATIONS;
            boolean z = hasJAXBAnnotation(allMethodAnnotations) || hasJAXBAnnotation(allMethodAnnotations2);
            boolean z2 = (z || m == null || !nav().isOverriding(m, superClass) || m2 == null || !nav().isOverriding(m2, superClass)) ? false : true;
            if ((xmlAccessType == XmlAccessType.PROPERTY && !z2) || ((xmlAccessType == XmlAccessType.PUBLIC_MEMBER && isConsideredPublic(m) && isConsideredPublic(m2) && !z2) || z)) {
                if (m != null && m2 != null && !nav().isSameType(nav().getReturnType(m), nav().getMethodParameters(m2)[0])) {
                    this.builder.reportError(new IllegalAnnotationException(Messages.GETTER_SETTER_INCOMPATIBLE_TYPE.format(nav().getTypeName(nav().getReturnType(m)), nav().getTypeName(nav().getMethodParameters(m2)[0])), new MethodLocatable(this, m, nav()), new MethodLocatable(this, m2, nav())));
                } else {
                    if (allMethodAnnotations.length == 0) {
                        allMethodAnnotations = allMethodAnnotations2;
                    } else if (allMethodAnnotations2.length != 0) {
                        Annotation[] annotationArr = new Annotation[allMethodAnnotations.length + allMethodAnnotations2.length];
                        System.arraycopy(allMethodAnnotations, 0, annotationArr, 0, allMethodAnnotations.length);
                        System.arraycopy(allMethodAnnotations2, 0, annotationArr, allMethodAnnotations.length, allMethodAnnotations2.length);
                        allMethodAnnotations = annotationArr;
                    }
                    addProperty(createAccessorSeed(m, m2), allMethodAnnotations, false);
                }
            }
        }
        linkedHashMap.keySet().removeAll(treeSet);
        linkedHashMap2.keySet().removeAll(treeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void collectGetterSetters(C c, Map<String, M> map, Map<String, M> map2) {
        Object superClass = nav().getSuperClass(c);
        if (shouldRecurseSuperClass(superClass)) {
            collectGetterSetters(superClass, map, map2);
        }
        Collection<? extends M> declaredMethods = nav().getDeclaredMethods(c);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<? extends M> it = declaredMethods.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            M next = it.next();
            if (!nav().isBridgeMethod(next)) {
                String methodName = nav().getMethodName(next);
                int length = nav().getMethodParameters(next).length;
                if (nav().isStaticMethod(next)) {
                    ensureNoAnnotation(next);
                } else {
                    String propertyNameFromGetMethod = getPropertyNameFromGetMethod(methodName);
                    boolean z2 = true;
                    if (propertyNameFromGetMethod != null && length == 0) {
                        map.put(propertyNameFromGetMethod, next);
                        z = true;
                    }
                    String propertyNameFromSetMethod = getPropertyNameFromSetMethod(methodName);
                    if (propertyNameFromSetMethod == null || length != 1) {
                        z2 = z;
                    } else {
                        List arrayList = (List) linkedHashMap.get(propertyNameFromSetMethod);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            linkedHashMap.put(propertyNameFromSetMethod, arrayList);
                        }
                        arrayList.add(next);
                    }
                    if (!z2) {
                        ensureNoAnnotation(next);
                    }
                }
            }
        }
        for (Map.Entry<String, M> entry : map.entrySet()) {
            String key = entry.getKey();
            M value = entry.getValue();
            List list = (List) linkedHashMap.remove(key);
            if (list != null) {
                Object returnType = nav().getReturnType(value);
                Iterator it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object next2 = it2.next();
                        if (nav().isSameType(nav().getMethodParameters(next2)[0], returnType)) {
                            map2.put(key, next2);
                            break;
                        }
                    }
                }
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            map2.put(entry2.getKey(), ((List) entry2.getValue()).get(0));
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private boolean shouldRecurseSuperClass(C c) {
        return c != null && (this.builder.isReplaced((ClassDeclT) c) || reader().hasClassAnnotation(c, XmlTransient.class));
    }

    private boolean isConsideredPublic(M m) {
        return m == null || nav().isPublicMethod(m);
    }

    private void resurrect(Map<String, M> map, Set<String> set) {
        for (Map.Entry<String, M> entry : map.entrySet()) {
            if (!set.contains(entry.getKey()) && hasJAXBAnnotation(reader().getAllMethodAnnotations(entry.getValue(), this))) {
                set.add(entry.getKey());
            }
        }
    }

    private void ensureNoAnnotation(M m) {
        for (Annotation annotation : reader().getAllMethodAnnotations(m, this)) {
            if (isJAXBAnnotation(annotation)) {
                this.builder.reportError(new IllegalAnnotationException(Messages.ANNOTATION_ON_WRONG_METHOD.format(new Object[0]), annotation));
                return;
            }
        }
    }

    private static boolean isJAXBAnnotation(Annotation annotation) {
        return ANNOTATION_NUMBER_MAP.containsKey(annotation.annotationType());
    }

    private static boolean hasJAXBAnnotation(Annotation[] annotationArr) {
        return getSomeJAXBAnnotation(annotationArr) != null;
    }

    private static Annotation getSomeJAXBAnnotation(Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            if (isJAXBAnnotation(annotation)) {
                return annotation;
            }
        }
        return null;
    }

    private static String getPropertyNameFromGetMethod(String str) {
        if (str.startsWith("get") && str.length() > 3) {
            return str.substring(3);
        }
        if (!str.startsWith("is") || str.length() <= 2) {
            return null;
        }
        return str.substring(2);
    }

    private static String getPropertyNameFromSetMethod(String str) {
        if (!str.startsWith("set") || str.length() <= 3) {
            return null;
        }
        return str.substring(3);
    }

    protected PropertySeed<T, C, F, M> createFieldSeed(F f) {
        return new FieldPropertySeed(this, f);
    }

    protected PropertySeed<T, C, F, M> createAccessorSeed(M m, M m2) {
        return new GetterSetterPropertySeed(this, m, m2);
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public final boolean isElement() {
        return this.elementName != null;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public boolean isAbstract() {
        return nav().isAbstract(this.clazz);
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public boolean isOrdered() {
        return this.propOrder != null;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean isFinal() {
        return nav().isFinal(this.clazz);
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean hasSubClasses() {
        return this.hasSubClasses;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean hasAttributeWildcard() {
        return declaresAttributeWildcard() || inheritsAttributeWildcard();
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean inheritsAttributeWildcard() {
        return getInheritedAttributeWildcard() != null;
    }

    @Override // com.sun.xml.bind.v2.model.core.ClassInfo
    public final boolean declaresAttributeWildcard() {
        return this.attributeWildcard != null;
    }

    private PropertySeed<T, C, F, M> getInheritedAttributeWildcard() {
        for (ClassInfoImpl<T, C, F, M> baseClass = getBaseClass(); baseClass != null; baseClass = baseClass.getBaseClass()) {
            PropertySeed<T, C, F, M> propertySeed = baseClass.attributeWildcard;
            if (propertySeed != null) {
                return propertySeed;
            }
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.MaybeElement
    public final QName getElementName() {
        return this.elementName;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public final QName getTypeName() {
        return this.typeName;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public final boolean isSimpleType() {
        List<? extends PropertyInfo<T, C>> properties = getProperties();
        return properties.size() == 1 && properties.get(0).kind() == PropertyKind.VALUE;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoImpl
    void link() {
        getProperties();
        HashMap map = new HashMap();
        for (PropertyInfoImpl<T, C, F, M> propertyInfoImpl : this.properties) {
            propertyInfoImpl.link();
            PropertyInfoImpl propertyInfoImpl2 = (PropertyInfoImpl) map.put(propertyInfoImpl.getName(), propertyInfoImpl);
            if (propertyInfoImpl2 != null) {
                this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_COLLISION.format(propertyInfoImpl.getName()), propertyInfoImpl, propertyInfoImpl2));
            }
        }
        super.link();
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return nav().getClassLocation(this.clazz);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean hasFactoryConstructor(XmlType xmlType) {
        if (xmlType == null) {
            return false;
        }
        String strFactoryMethod = xmlType.factoryMethod();
        T classValue = reader().getClassValue(xmlType, "factoryClass");
        if (strFactoryMethod.length() > 0) {
            if (nav().isSameType(classValue, nav().ref(XmlType.DEFAULT.class))) {
                classValue = nav().use(this.clazz);
            }
            Iterator it = nav().getDeclaredMethods(nav().asDecl(classValue)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                M m = (M) it.next();
                if (nav().getMethodName(m).equals(strFactoryMethod) && nav().isSameType(nav().getReturnType(m), nav().use(this.clazz)) && nav().getMethodParameters(m).length == 0 && nav().isStaticMethod(m)) {
                    this.factoryMethod = m;
                    break;
                }
            }
            if (this.factoryMethod == null) {
                this.builder.reportError(new IllegalAnnotationException(Messages.NO_FACTORY_METHOD.format(nav().getClassName(nav().asDecl(classValue)), strFactoryMethod), this));
            }
        } else if (!nav().isSameType(classValue, nav().ref(XmlType.DEFAULT.class))) {
            this.builder.reportError(new IllegalAnnotationException(Messages.FACTORY_CLASS_NEEDS_FACTORY_METHOD.format(nav().getClassName(nav().asDecl(classValue))), this));
        }
        return this.factoryMethod != null;
    }

    public Method getFactoryMethod() {
        return (Method) this.factoryMethod;
    }

    public String toString() {
        return "ClassInfo(" + this.clazz + ')';
    }
}
