package com.fasterxml.jackson.module.jaxb;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.module.jaxb.deser.DataHandlerJsonDeserializer;
import com.fasterxml.jackson.module.jaxb.ser.DataHandlerJsonSerializer;
import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

/* JADX INFO: loaded from: classes6.dex */
public class JaxbAnnotationIntrospector extends AnnotationIntrospector {
    protected static final boolean DEFAULT_IGNORE_XMLIDREF = false;
    protected static final String DEFAULT_NAME_FOR_XML_VALUE = "value";
    protected static final String MARKER_FOR_DEFAULT = "##default";
    private static final long serialVersionUID = -1;
    protected final JsonDeserializer<?> _dataHandlerDeserializer;
    protected final JsonSerializer<?> _dataHandlerSerializer;
    protected final boolean _ignoreXmlIDREF;
    protected final String _jaxbPackageName;
    protected JsonInclude.Include _nonNillableInclusion;
    protected final TypeFactory _typeFactory;
    protected String _xmlValueName;
    protected static final JsonFormat.Value FORMAT_STRING = new JsonFormat.Value().withShape(JsonFormat.Shape.STRING);
    protected static final JsonFormat.Value FORMAT_INT = new JsonFormat.Value().withShape(JsonFormat.Shape.NUMBER_INT);

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findContentDeserializer(Annotated annotated) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findContentSerializer(Annotated annotated) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findKeyDeserializer(Annotated annotated) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        return null;
    }

    @Deprecated
    public JaxbAnnotationIntrospector() {
        this(TypeFactory.defaultInstance());
    }

    public JaxbAnnotationIntrospector(MapperConfig<?> mapperConfig) {
        this(mapperConfig.getTypeFactory());
    }

    public JaxbAnnotationIntrospector(TypeFactory typeFactory) {
        this(typeFactory, false);
    }

    public JaxbAnnotationIntrospector(TypeFactory typeFactory, boolean z) {
        JsonSerializer<?> jsonSerializer;
        this._xmlValueName = "value";
        JsonDeserializer<?> jsonDeserializer = null;
        this._nonNillableInclusion = null;
        this._typeFactory = typeFactory == null ? TypeFactory.defaultInstance() : typeFactory;
        this._ignoreXmlIDREF = z;
        this._jaxbPackageName = XmlElement.class.getPackage().getName();
        try {
            jsonSerializer = (JsonSerializer) DataHandlerJsonSerializer.class.newInstance();
            try {
                jsonDeserializer = (JsonDeserializer) DataHandlerJsonDeserializer.class.newInstance();
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            jsonSerializer = null;
        }
        this._dataHandlerSerializer = jsonSerializer;
        this._dataHandlerDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    public void setNameUsedForXmlValue(String str) {
        this._xmlValueName = str;
    }

    public String getNameUsedForXmlValue() {
        return this._xmlValueName;
    }

    public JaxbAnnotationIntrospector setNonNillableInclusion(JsonInclude.Include include) {
        this._nonNillableInclusion = include;
        return this;
    }

    public JsonInclude.Include getNonNillableInclusion() {
        return this._nonNillableInclusion;
    }

    public String findNamespace(Annotated annotated) {
        XmlAttribute xmlAttribute;
        String strNamespace;
        if (annotated instanceof AnnotatedClass) {
            XmlRootElement xmlRootElementFindRootElementAnnotation = findRootElementAnnotation((AnnotatedClass) annotated);
            strNamespace = xmlRootElementFindRootElementAnnotation != null ? xmlRootElementFindRootElementAnnotation.namespace() : null;
        } else {
            XmlElement xmlElement = (XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false);
            String strNamespace2 = xmlElement != null ? xmlElement.namespace() : null;
            strNamespace = ((strNamespace2 == null || "##default".equals(strNamespace2)) && (xmlAttribute = (XmlAttribute) findAnnotation(XmlAttribute.class, annotated, false, false, false)) != null) ? xmlAttribute.namespace() : strNamespace2;
        }
        if ("##default".equals(strNamespace)) {
            return null;
        }
        return strNamespace;
    }

    public Boolean isOutputAsAttribute(Annotated annotated) {
        if (((XmlAttribute) findAnnotation(XmlAttribute.class, annotated, false, false, false)) != null) {
            return Boolean.TRUE;
        }
        if (((XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false)) != null) {
            return Boolean.FALSE;
        }
        return null;
    }

    public Boolean isOutputAsText(Annotated annotated) {
        if (((XmlValue) findAnnotation(XmlValue.class, annotated, false, false, false)) != null) {
            return Boolean.TRUE;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        PropertyName propertyNameFindJaxbPropertyName;
        if (!(annotated instanceof AnnotatedClass)) {
            return null;
        }
        AnnotatedClass annotatedClass = (AnnotatedClass) annotated;
        Iterator<AnnotatedMethod> it = annotatedClass.memberMethods().iterator();
        while (true) {
            if (!it.hasNext()) {
                propertyNameFindJaxbPropertyName = null;
                break;
            }
            AnnotatedMethod next = it.next();
            if (((XmlID) next.getAnnotation(XmlID.class)) != null) {
                int parameterCount = next.getParameterCount();
                if (parameterCount == 0) {
                    propertyNameFindJaxbPropertyName = findJaxbPropertyName(next, next.getRawType(), _okNameForGetter(next));
                    break;
                }
                if (parameterCount == 1) {
                    propertyNameFindJaxbPropertyName = findJaxbPropertyName(next, next.getRawType(), _okNameForMutator(next));
                    break;
                }
            }
        }
        if (propertyNameFindJaxbPropertyName == null) {
            Iterator<AnnotatedField> it2 = annotatedClass.fields().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                AnnotatedField next2 = it2.next();
                if (((XmlID) next2.getAnnotation(XmlID.class)) != null) {
                    propertyNameFindJaxbPropertyName = findJaxbPropertyName(next2, next2.getRawType(), next2.getName());
                    break;
                }
            }
        }
        if (propertyNameFindJaxbPropertyName != null) {
            return new ObjectIdInfo(propertyNameFindJaxbPropertyName, (Class<?>) Object.class, (Class<? extends ObjectIdGenerator<?>>) ObjectIdGenerators.PropertyGenerator.class, (Class<? extends ObjectIdResolver>) SimpleObjectIdResolver.class);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        if (this._ignoreXmlIDREF || ((XmlIDREF) annotated.getAnnotation(XmlIDREF.class)) == null) {
            return objectIdInfo;
        }
        if (objectIdInfo == null) {
            objectIdInfo = ObjectIdInfo.empty();
        }
        return objectIdInfo.withAlwaysAsId(true);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        XmlRootElement xmlRootElementFindRootElementAnnotation = findRootElementAnnotation(annotatedClass);
        if (xmlRootElementFindRootElementAnnotation != null) {
            return _combineNames(xmlRootElementFindRootElementAnnotation.name(), xmlRootElementFindRootElementAnnotation.namespace(), "");
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return annotatedMember.getAnnotation(XmlTransient.class) != null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        XmlAttribute xmlAttribute = (XmlAttribute) annotatedMember.getAnnotation(XmlAttribute.class);
        if (xmlAttribute != null) {
            return Boolean.valueOf(xmlAttribute.required());
        }
        XmlElement xmlElement = (XmlElement) annotatedMember.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            return Boolean.valueOf(xmlElement.required());
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findWrapperName(Annotated annotated) {
        String str_okNameForMutator;
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) findAnnotation(XmlElementWrapper.class, annotated, false, false, false);
        if (xmlElementWrapper == null) {
            return null;
        }
        PropertyName propertyName_combineNames = _combineNames(xmlElementWrapper.name(), xmlElementWrapper.namespace(), "");
        if (propertyName_combineNames.hasSimpleName()) {
            return propertyName_combineNames;
        }
        if (annotated instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotated;
            if (annotatedMethod.getParameterCount() == 0) {
                str_okNameForMutator = _okNameForGetter(annotatedMethod);
            } else {
                str_okNameForMutator = _okNameForMutator(annotatedMethod);
            }
            if (str_okNameForMutator != null) {
                return propertyName_combineNames.withSimpleName(str_okNameForMutator);
            }
        }
        return propertyName_combineNames.withSimpleName(annotated.getName());
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        if (((XmlValue) annotatedMember.getAnnotation(XmlValue.class)) != null) {
            return this._xmlValueName;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JsonFormat.Value findFormat(Annotated annotated) {
        XmlEnum xmlEnum;
        if (!(annotated instanceof AnnotatedClass) || (xmlEnum = (XmlEnum) annotated.getAnnotation(XmlEnum.class)) == null) {
            return null;
        }
        Class<?> clsValue = xmlEnum.value();
        if (clsValue == String.class || clsValue.isEnum()) {
            return FORMAT_STRING;
        }
        if (Number.class.isAssignableFrom(clsValue)) {
            return FORMAT_INT;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        XmlAccessType xmlAccessTypeFindAccessType = findAccessType(annotatedClass);
        if (xmlAccessTypeFindAccessType == null) {
            return visibilityChecker;
        }
        int i = AnonymousClass1.$SwitchMap$javax$xml$bind$annotation$XmlAccessType[xmlAccessTypeFindAccessType.ordinal()];
        if (i == 1) {
            VisibilityChecker visibilityCheckerWithFieldVisibility = visibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.ANY);
            JsonAutoDetect.Visibility visibility = JsonAutoDetect.Visibility.NONE;
            return visibilityCheckerWithFieldVisibility.withSetterVisibility(visibility).withGetterVisibility(visibility).withIsGetterVisibility(visibility);
        }
        if (i == 2) {
            JsonAutoDetect.Visibility visibility2 = JsonAutoDetect.Visibility.NONE;
            return visibilityChecker.withFieldVisibility(visibility2).withSetterVisibility(visibility2).withGetterVisibility(visibility2).withIsGetterVisibility(visibility2);
        }
        if (i == 3) {
            VisibilityChecker visibilityCheckerWithFieldVisibility2 = visibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
            JsonAutoDetect.Visibility visibility3 = JsonAutoDetect.Visibility.PUBLIC_ONLY;
            return visibilityCheckerWithFieldVisibility2.withSetterVisibility(visibility3).withGetterVisibility(visibility3).withIsGetterVisibility(visibility3);
        }
        if (i != 4) {
            return visibilityChecker;
        }
        JsonAutoDetect.Visibility visibility4 = JsonAutoDetect.Visibility.PUBLIC_ONLY;
        return visibilityChecker.withFieldVisibility(visibility4).withSetterVisibility(visibility4).withGetterVisibility(visibility4).withIsGetterVisibility(visibility4);
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$xml$bind$annotation$XmlAccessType;

        static {
            int[] iArr = new int[XmlAccessType.values().length];
            $SwitchMap$javax$xml$bind$annotation$XmlAccessType = iArr;
            try {
                iArr[XmlAccessType.FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PROPERTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PUBLIC_MEMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected XmlAccessType findAccessType(Annotated annotated) {
        XmlAccessorType xmlAccessorType = (XmlAccessorType) findAnnotation(XmlAccessorType.class, annotated, true, true, true);
        if (xmlAccessorType == null) {
            return null;
        }
        return xmlAccessorType.value();
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return null;
        }
        return _typeResolverFromXmlElements(annotatedMember);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.getContentType() == null) {
            throw new IllegalArgumentException("Must call method with a container or reference type (got " + javaType + ")");
        }
        return _typeResolverFromXmlElements(annotatedMember);
    }

    protected TypeResolverBuilder<?> _typeResolverFromXmlElements(AnnotatedMember annotatedMember) {
        XmlElements xmlElements = (XmlElements) findAnnotation(XmlElements.class, annotatedMember, false, false, false);
        XmlElementRefs xmlElementRefs = (XmlElementRefs) findAnnotation(XmlElementRefs.class, annotatedMember, false, false, false);
        if (xmlElements == null && xmlElementRefs == null) {
            return null;
        }
        return new StdTypeResolverBuilder().init(JsonTypeInfo.Id.NAME, (TypeIdResolver) null).inclusion(JsonTypeInfo.As.WRAPPER_OBJECT);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public List<NamedType> findSubtypes(Annotated annotated) {
        XmlRootElement xmlRootElement;
        XmlElements xmlElements = (XmlElements) findAnnotation(XmlElements.class, annotated, false, false, false);
        ArrayList arrayList = null;
        if (xmlElements != null) {
            ArrayList arrayList2 = new ArrayList();
            for (XmlElement xmlElement : xmlElements.value()) {
                String strName = xmlElement.name();
                if ("##default".equals(strName)) {
                    strName = null;
                }
                arrayList2.add(new NamedType(xmlElement.type(), strName));
            }
            arrayList = arrayList2;
        } else {
            XmlElementRefs xmlElementRefs = (XmlElementRefs) findAnnotation(XmlElementRefs.class, annotated, false, false, false);
            if (xmlElementRefs != null) {
                arrayList = new ArrayList();
                for (XmlElementRef xmlElementRef : xmlElementRefs.value()) {
                    Class clsType = xmlElementRef.type();
                    if (!JAXBElement.class.isAssignableFrom(clsType)) {
                        String strName2 = xmlElementRef.name();
                        if ((strName2 == null || "##default".equals(strName2)) && (xmlRootElement = (XmlRootElement) clsType.getAnnotation(XmlRootElement.class)) != null) {
                            strName2 = xmlRootElement.name();
                        }
                        if (strName2 == null || "##default".equals(strName2)) {
                            strName2 = _decapitalize(clsType.getSimpleName());
                        }
                        arrayList.add(new NamedType(clsType, strName2));
                    }
                }
            }
        }
        XmlSeeAlso xmlSeeAlso = (XmlSeeAlso) annotated.getAnnotation(XmlSeeAlso.class);
        if (xmlSeeAlso != null) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            for (Class cls : xmlSeeAlso.value()) {
                arrayList.add(new NamedType(cls));
            }
        }
        return arrayList;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public String findTypeName(AnnotatedClass annotatedClass) {
        XmlType xmlType = (XmlType) findAnnotation(XmlType.class, annotatedClass, false, false, false);
        if (xmlType == null) {
            return null;
        }
        String strName = xmlType.name();
        if ("##default".equals(strName)) {
            return null;
        }
        return strName;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JsonSerializer<?> findSerializer(Annotated annotated) {
        Class<?> cls_rawSerializationType = _rawSerializationType(annotated);
        if (cls_rawSerializationType == null || this._dataHandlerSerializer == null || !isDataHandler(cls_rawSerializationType)) {
            return null;
        }
        return this._dataHandlerSerializer;
    }

    private boolean isDataHandler(Class<?> cls) {
        return (cls == null || Object.class == cls || (!"javax.activation.DataHandler".equals(cls.getName()) && !isDataHandler(cls.getSuperclass()))) ? false : true;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Deprecated
    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> cls_getTypeFromXmlElement = _getTypeFromXmlElement(annotated);
        if (cls_getTypeFromXmlElement == null || isContainerType(_rawSerializationType(annotated))) {
            return null;
        }
        return cls_getTypeFromXmlElement;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JsonInclude.Value findPropertyInclusion(Annotated annotated) {
        JsonInclude.Include include_serializationInclusion = _serializationInclusion(annotated, null);
        if (include_serializationInclusion == null) {
            return JsonInclude.Value.empty();
        }
        return JsonInclude.Value.construct(include_serializationInclusion, null);
    }

    JsonInclude.Include _serializationInclusion(Annotated annotated, JsonInclude.Include include) {
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) annotated.getAnnotation(XmlElementWrapper.class);
        if (xmlElementWrapper != null) {
            if (xmlElementWrapper.nillable()) {
                return JsonInclude.Include.ALWAYS;
            }
            JsonInclude.Include include2 = this._nonNillableInclusion;
            if (include2 != null) {
                return include2;
            }
        }
        XmlElement xmlElement = (XmlElement) annotated.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            if (xmlElement.nillable()) {
                return JsonInclude.Include.ALWAYS;
            }
            JsonInclude.Include include3 = this._nonNillableInclusion;
            if (include3 != null) {
                return include3;
            }
        }
        return include;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JavaType refineSerializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        JavaType javaTypeConstructGeneralizedType;
        Class<?> cls_getTypeFromXmlElement = _getTypeFromXmlElement(annotated);
        if (cls_getTypeFromXmlElement == null) {
            return javaType;
        }
        TypeFactory typeFactory = mapperConfig.getTypeFactory();
        if (javaType.getContentType() == null) {
            if (!cls_getTypeFromXmlElement.isAssignableFrom(javaType.getRawClass())) {
                return javaType;
            }
            if (javaType.hasRawClass(cls_getTypeFromXmlElement)) {
                return javaType.withStaticTyping();
            }
            try {
                return typeFactory.constructGeneralizedType(javaType, cls_getTypeFromXmlElement);
            } catch (IllegalArgumentException e) {
                throw new JsonMappingException((Closeable) null, String.format("Failed to widen type %s with annotation (value %s), from '%s': %s", javaType, cls_getTypeFromXmlElement.getName(), annotated.getName(), e.getMessage()), e);
            }
        }
        JavaType contentType = javaType.getContentType();
        if (contentType == null || !cls_getTypeFromXmlElement.isAssignableFrom(contentType.getRawClass())) {
            return javaType;
        }
        if (contentType.hasRawClass(cls_getTypeFromXmlElement)) {
            javaTypeConstructGeneralizedType = contentType.withStaticTyping();
        } else {
            try {
                javaTypeConstructGeneralizedType = typeFactory.constructGeneralizedType(contentType, cls_getTypeFromXmlElement);
            } catch (IllegalArgumentException e2) {
                throw new JsonMappingException((Closeable) null, String.format("Failed to widen value type of %s with concrete-type annotation (value %s), from '%s': %s", javaType, cls_getTypeFromXmlElement.getName(), annotated.getName(), e2.getMessage()), e2);
            }
        }
        return javaType.withContentType(javaTypeConstructGeneralizedType);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        String[] strArrPropOrder;
        XmlType xmlType = (XmlType) findAnnotation(XmlType.class, annotatedClass, true, true, true);
        if (xmlType == null || (strArrPropOrder = xmlType.propOrder()) == null || strArrPropOrder.length == 0) {
            return null;
        }
        return strArrPropOrder;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        return _findAlpha(annotated);
    }

    private final Boolean _findAlpha(Annotated annotated) {
        XmlAccessorOrder xmlAccessorOrder = (XmlAccessorOrder) findAnnotation(XmlAccessorOrder.class, annotated, true, true, true);
        if (xmlAccessorOrder == null) {
            return null;
        }
        return Boolean.valueOf(xmlAccessorOrder.value() == XmlAccessOrder.ALPHABETICAL);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findSerializationConverter(Annotated annotated) {
        XmlAdapter<Object, Object> xmlAdapterFindAdapter = findAdapter(annotated, true, _rawSerializationType(annotated));
        if (xmlAdapterFindAdapter != null) {
            return _converter(xmlAdapterFindAdapter, true);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        XmlAdapter<?, ?> xmlAdapter_findContentAdapter;
        if (!isContainerType(_rawSerializationType(annotatedMember)) || (xmlAdapter_findContentAdapter = _findContentAdapter(annotatedMember, true)) == null) {
            return null;
        }
        return _converter(xmlAdapter_findContentAdapter, true);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findNameForSerialization(Annotated annotated) {
        if (annotated instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotated;
            if (isVisible(annotatedMethod)) {
                return findJaxbPropertyName(annotatedMethod, annotatedMethod.getRawType(), _okNameForGetter(annotatedMethod));
            }
            return null;
        }
        if (!(annotated instanceof AnnotatedField)) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) annotated;
        if (isVisible(annotatedField)) {
            return findJaxbPropertyName(annotatedField, annotatedField.getRawType(), null);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public String[] findEnumValues(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        XmlEnumValue xmlEnumValue;
        HashMap map = null;
        for (Field field : cls.getDeclaredFields()) {
            if (field.isEnumConstant() && (xmlEnumValue = (XmlEnumValue) field.getAnnotation(XmlEnumValue.class)) != null) {
                String strValue = xmlEnumValue.value();
                if (!strValue.isEmpty()) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    map.put(field.getName(), strValue);
                }
            }
        }
        if (map != null) {
            int length = enumArr.length;
            for (int i = 0; i < length; i++) {
                String str = (String) map.get(enumArr[i].name());
                if (str != null) {
                    strArr[i] = str;
                }
            }
        }
        return strArr;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findDeserializer(Annotated annotated) {
        Class<?> cls_rawDeserializationType = _rawDeserializationType(annotated);
        if (cls_rawDeserializationType == null || this._dataHandlerDeserializer == null || !isDataHandler(cls_rawDeserializationType)) {
            return null;
        }
        return this._dataHandlerDeserializer;
    }

    protected Class<?> _doFindDeserializationType(Annotated annotated, JavaType javaType) {
        XmlElement xmlElement;
        Class<?> clsType;
        if (annotated.hasAnnotation(XmlJavaTypeAdapter.class) || (xmlElement = (XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false)) == null || (clsType = xmlElement.type()) == XmlElement.DEFAULT.class) {
            return null;
        }
        return clsType;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JavaType refineDeserializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        Class<?> cls_getTypeFromXmlElement = _getTypeFromXmlElement(annotated);
        if (cls_getTypeFromXmlElement == null) {
            return javaType;
        }
        TypeFactory typeFactory = mapperConfig.getTypeFactory();
        if (javaType.getContentType() == null) {
            if (javaType.hasRawClass(cls_getTypeFromXmlElement) || !javaType.getRawClass().isAssignableFrom(cls_getTypeFromXmlElement)) {
                return javaType;
            }
            try {
                return typeFactory.constructSpecializedType(javaType, cls_getTypeFromXmlElement);
            } catch (IllegalArgumentException e) {
                throw new JsonMappingException((Closeable) null, String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", javaType, cls_getTypeFromXmlElement.getName(), annotated.getName(), e.getMessage()), e);
            }
        }
        JavaType contentType = javaType.getContentType();
        if (contentType == null || !contentType.getRawClass().isAssignableFrom(cls_getTypeFromXmlElement)) {
            return javaType;
        }
        try {
            return javaType.withContentType(typeFactory.constructSpecializedType(contentType, cls_getTypeFromXmlElement));
        } catch (IllegalArgumentException e2) {
            throw new JsonMappingException((Closeable) null, String.format("Failed to narrow type %s with annotation (value %s), from '%s': %s", javaType, cls_getTypeFromXmlElement.getName(), annotated.getName(), e2.getMessage()), e2);
        }
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findNameForDeserialization(Annotated annotated) {
        if (annotated instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotated;
            if (isVisible(annotatedMethod)) {
                return findJaxbPropertyName(annotatedMethod, annotatedMethod.getRawParameterType(0), _okNameForMutator(annotatedMethod));
            }
            return null;
        }
        if (!(annotated instanceof AnnotatedField)) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) annotated;
        if (isVisible(annotatedField)) {
            return findJaxbPropertyName(annotatedField, annotatedField.getRawType(), null);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findDeserializationConverter(Annotated annotated) {
        Class<?> cls_rawDeserializationType = _rawDeserializationType(annotated);
        if (isContainerType(cls_rawDeserializationType)) {
            XmlAdapter<Object, Object> xmlAdapterFindAdapter = findAdapter(annotated, true, cls_rawDeserializationType);
            if (xmlAdapterFindAdapter != null) {
                return _converter(xmlAdapterFindAdapter, false);
            }
            return null;
        }
        XmlAdapter<Object, Object> xmlAdapterFindAdapter2 = findAdapter(annotated, true, cls_rawDeserializationType);
        if (xmlAdapterFindAdapter2 != null) {
            return _converter(xmlAdapterFindAdapter2, false);
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        XmlAdapter<?, ?> xmlAdapter_findContentAdapter;
        if (!isContainerType(_rawDeserializationType(annotatedMember)) || (xmlAdapter_findContentAdapter = _findContentAdapter(annotatedMember, false)) == null) {
            return null;
        }
        return _converter(xmlAdapter_findContentAdapter, false);
    }

    private boolean isVisible(AnnotatedField annotatedField) {
        for (Annotation annotation : annotatedField.getAnnotated().getDeclaredAnnotations()) {
            if (isJAXBAnnotation(annotation)) {
                return true;
            }
        }
        XmlAccessType xmlAccessType = XmlAccessType.PUBLIC_MEMBER;
        XmlAccessorType xmlAccessorType = (XmlAccessorType) findAnnotation(XmlAccessorType.class, annotatedField, true, true, true);
        XmlAccessType xmlAccessTypeValue = xmlAccessorType != null ? xmlAccessorType.value() : xmlAccessType;
        if (xmlAccessTypeValue == XmlAccessType.FIELD) {
            return true;
        }
        if (xmlAccessTypeValue == xmlAccessType) {
            return Modifier.isPublic(annotatedField.getAnnotated().getModifiers());
        }
        return false;
    }

    private boolean isVisible(AnnotatedMethod annotatedMethod) {
        for (Annotation annotation : annotatedMethod.getAnnotated().getDeclaredAnnotations()) {
            if (isJAXBAnnotation(annotation)) {
                return true;
            }
        }
        XmlAccessType xmlAccessType = XmlAccessType.PUBLIC_MEMBER;
        XmlAccessorType xmlAccessorType = (XmlAccessorType) findAnnotation(XmlAccessorType.class, annotatedMethod, true, true, true);
        XmlAccessType xmlAccessTypeValue = xmlAccessorType != null ? xmlAccessorType.value() : xmlAccessType;
        if (xmlAccessTypeValue == XmlAccessType.PROPERTY || xmlAccessTypeValue == xmlAccessType) {
            return Modifier.isPublic(annotatedMethod.getModifiers());
        }
        return false;
    }

    private <A extends Annotation> A findAnnotation(Class<A> cls, Annotated annotated, boolean z, boolean z2, boolean z3) {
        Class<?> declaringClass;
        A a2;
        A a3 = (A) annotated.getAnnotation(cls);
        if (a3 != null) {
            return a3;
        }
        if (annotated instanceof AnnotatedParameter) {
            declaringClass = ((AnnotatedParameter) annotated).getDeclaringClass();
        } else {
            AnnotatedElement annotated2 = annotated.getAnnotated();
            if (annotated2 instanceof Member) {
                declaringClass = ((Member) annotated2).getDeclaringClass();
                if (z2 && (a2 = (A) declaringClass.getAnnotation(cls)) != null) {
                    return a2;
                }
            } else {
                declaringClass = annotated2 instanceof Class ? (Class) annotated2 : null;
            }
        }
        if (declaringClass != null) {
            if (z3) {
                for (Class<? super Object> superclass = declaringClass.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    A a4 = (A) superclass.getAnnotation(cls);
                    if (a4 != null) {
                        return a4;
                    }
                }
            }
            if (z && declaringClass.getPackage() != null) {
                return (A) declaringClass.getPackage().getAnnotation(cls);
            }
        }
        return null;
    }

    protected boolean isJAXBAnnotation(Annotation annotation) {
        Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
        Package r0 = clsAnnotationType.getPackage();
        return (r0 != null ? r0.getName() : clsAnnotationType.getName()).startsWith(this._jaxbPackageName);
    }

    private PropertyName findJaxbPropertyName(Annotated annotated, Class<?> cls, String str) {
        XmlRootElement xmlRootElement;
        XmlAttribute xmlAttribute = (XmlAttribute) annotated.getAnnotation(XmlAttribute.class);
        if (xmlAttribute != null) {
            return _combineNames(xmlAttribute.name(), xmlAttribute.namespace(), str);
        }
        XmlElement xmlElement = (XmlElement) annotated.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            return _combineNames(xmlElement.name(), xmlElement.namespace(), str);
        }
        XmlElementRef xmlElementRef = (XmlElementRef) annotated.getAnnotation(XmlElementRef.class);
        boolean z = true;
        boolean z2 = xmlElementRef != null;
        if (z2) {
            if (!"##default".equals(xmlElementRef.name())) {
                return _combineNames(xmlElementRef.name(), xmlElementRef.namespace(), str);
            }
            if (cls != null && (xmlRootElement = (XmlRootElement) cls.getAnnotation(XmlRootElement.class)) != null) {
                String strName = xmlRootElement.name();
                if (!"##default".equals(strName)) {
                    return _combineNames(strName, xmlRootElement.namespace(), str);
                }
                return new PropertyName(_decapitalize(cls.getSimpleName()));
            }
        }
        if (!z2) {
            if (!annotated.hasAnnotation(XmlElementWrapper.class) && !annotated.hasAnnotation(XmlElements.class) && !annotated.hasAnnotation(XmlValue.class)) {
                z = false;
            }
            z2 = z;
        }
        if (z2) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }

    private static PropertyName _combineNames(String str, String str2, String str3) {
        if ("##default".equals(str)) {
            if ("##default".equals(str2)) {
                return new PropertyName(str3);
            }
            return new PropertyName(str3, str2);
        }
        if ("##default".equals(str2)) {
            return new PropertyName(str);
        }
        return new PropertyName(str, str2);
    }

    private XmlRootElement findRootElementAnnotation(AnnotatedClass annotatedClass) {
        return (XmlRootElement) findAnnotation(XmlRootElement.class, annotatedClass, true, false, true);
    }

    private XmlAdapter<Object, Object> findAdapter(Annotated annotated, boolean z, Class<?> cls) {
        XmlAdapter<Object, Object> xmlAdapterCheckAdapter;
        if (annotated instanceof AnnotatedClass) {
            return findAdapterForClass((AnnotatedClass) annotated, z);
        }
        XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter) findAnnotation(XmlJavaTypeAdapter.class, annotated, true, false, false);
        if (xmlJavaTypeAdapter != null && (xmlAdapterCheckAdapter = checkAdapter(xmlJavaTypeAdapter, cls, z)) != null) {
            return xmlAdapterCheckAdapter;
        }
        XmlJavaTypeAdapters xmlJavaTypeAdapters = (XmlJavaTypeAdapters) findAnnotation(XmlJavaTypeAdapters.class, annotated, true, false, false);
        if (xmlJavaTypeAdapters == null) {
            return null;
        }
        for (XmlJavaTypeAdapter xmlJavaTypeAdapter2 : xmlJavaTypeAdapters.value()) {
            XmlAdapter<Object, Object> xmlAdapterCheckAdapter2 = checkAdapter(xmlJavaTypeAdapter2, cls, z);
            if (xmlAdapterCheckAdapter2 != null) {
                return xmlAdapterCheckAdapter2;
            }
        }
        return null;
    }

    private final XmlAdapter<Object, Object> checkAdapter(XmlJavaTypeAdapter xmlJavaTypeAdapter, Class<?> cls, boolean z) {
        Class<?> clsType = xmlJavaTypeAdapter.type();
        if (clsType == XmlJavaTypeAdapter.DEFAULT.class) {
            clsType = this._typeFactory.findTypeParameters(this._typeFactory.constructType(xmlJavaTypeAdapter.value()), XmlAdapter.class)[1].getRawClass();
        }
        if (clsType.isAssignableFrom(cls)) {
            return (XmlAdapter) ClassUtil.createInstance(xmlJavaTypeAdapter.value(), true);
        }
        return null;
    }

    private XmlAdapter<Object, Object> findAdapterForClass(AnnotatedClass annotatedClass, boolean z) {
        XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter) annotatedClass.getAnnotated().getAnnotation(XmlJavaTypeAdapter.class);
        if (xmlJavaTypeAdapter != null) {
            return (XmlAdapter) ClassUtil.createInstance(xmlJavaTypeAdapter.value(), true);
        }
        return null;
    }

    protected final TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    private boolean isContainerType(Class<?> cls) {
        return cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    private boolean adapterTypeMatches(XmlAdapter<?, ?> xmlAdapter, Class<?> cls) {
        return findAdapterBoundType(xmlAdapter).isAssignableFrom(cls);
    }

    private Class<?> findAdapterBoundType(XmlAdapter<?, ?> xmlAdapter) {
        TypeFactory typeFactory = getTypeFactory();
        JavaType[] javaTypeArrFindTypeParameters = typeFactory.findTypeParameters(typeFactory.constructType(xmlAdapter.getClass()), XmlAdapter.class);
        if (javaTypeArrFindTypeParameters == null || javaTypeArrFindTypeParameters.length < 2) {
            return Object.class;
        }
        return javaTypeArrFindTypeParameters[1].getRawClass();
    }

    protected XmlAdapter<?, ?> _findContentAdapter(Annotated annotated, boolean z) {
        if (!isContainerType(z ? _rawSerializationType(annotated) : _rawDeserializationType(annotated)) || !(annotated instanceof AnnotatedMember)) {
            return null;
        }
        AnnotatedMember annotatedMember = (AnnotatedMember) annotated;
        Class<?> rawClass = (z ? _fullSerializationType(annotatedMember) : _fullDeserializationType(annotatedMember)).getContentType().getRawClass();
        XmlAdapter<?, ?> xmlAdapterFindAdapter = findAdapter(annotatedMember, z, rawClass);
        if (xmlAdapterFindAdapter == null || !adapterTypeMatches(xmlAdapterFindAdapter, rawClass)) {
            return null;
        }
        return xmlAdapterFindAdapter;
    }

    protected String _propertyNameToString(PropertyName propertyName) {
        if (propertyName == null) {
            return null;
        }
        return propertyName.getSimpleName();
    }

    protected Class<?> _rawDeserializationType(Annotated annotated) {
        if (annotated instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotated;
            if (annotatedMethod.getParameterCount() == 1) {
                return annotatedMethod.getRawParameterType(0);
            }
        }
        return annotated.getRawType();
    }

    protected JavaType _fullDeserializationType(AnnotatedMember annotatedMember) {
        if (annotatedMember instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
            if (annotatedMethod.getParameterCount() == 1) {
                return annotatedMethod.getParameterType(0);
            }
        }
        return annotatedMember.getType();
    }

    protected Class<?> _rawSerializationType(Annotated annotated) {
        return annotated.getRawType();
    }

    protected JavaType _fullSerializationType(AnnotatedMember annotatedMember) {
        return annotatedMember.getType();
    }

    protected Converter<Object, Object> _converter(XmlAdapter<?, ?> xmlAdapter, boolean z) {
        TypeFactory typeFactory = getTypeFactory();
        JavaType[] javaTypeArrFindTypeParameters = typeFactory.findTypeParameters(typeFactory.constructType(xmlAdapter.getClass()), XmlAdapter.class);
        if (z) {
            return new AdapterConverter(xmlAdapter, javaTypeArrFindTypeParameters[1], javaTypeArrFindTypeParameters[0], z);
        }
        return new AdapterConverter(xmlAdapter, javaTypeArrFindTypeParameters[0], javaTypeArrFindTypeParameters[1], z);
    }

    protected Class<?> _getTypeFromXmlElement(Annotated annotated) {
        Class<?> clsType;
        XmlElement xmlElement = (XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false);
        if (xmlElement == null || annotated.getAnnotation(XmlJavaTypeAdapter.class) != null || (clsType = xmlElement.type()) == XmlElement.DEFAULT.class) {
            return null;
        }
        return clsType;
    }

    protected String _okNameForGetter(AnnotatedMethod annotatedMethod) {
        Class<?> rawType;
        String name = annotatedMethod.getName();
        if (name.startsWith("is") && ((rawType = annotatedMethod.getRawType()) == Boolean.class || rawType == Boolean.TYPE)) {
            return _stdManglePropertyName(name, 2);
        }
        if (name.startsWith("get")) {
            return _stdManglePropertyName(name, 3);
        }
        return null;
    }

    protected String _okNameForMutator(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        if (name.startsWith("set")) {
            return _stdManglePropertyName(name, 3);
        }
        return null;
    }

    protected String _stdManglePropertyName(String str, int i) {
        int length = str.length();
        if (length == i) {
            return null;
        }
        char cCharAt = str.charAt(i);
        char lowerCase = Character.toLowerCase(cCharAt);
        if (cCharAt == lowerCase) {
            return str.substring(i);
        }
        int i2 = i + 1;
        if (i2 < length && Character.isUpperCase(str.charAt(i2))) {
            return str.substring(i);
        }
        StringBuilder sb = new StringBuilder(length - i);
        sb.append(lowerCase);
        sb.append((CharSequence) str, i2, length);
        return sb.toString();
    }

    protected String _decapitalize(String str) {
        char cCharAt;
        char lowerCase;
        if (str.length() > 0 && cCharAt != (lowerCase = Character.toLowerCase((cCharAt = str.charAt(0))))) {
            if (str.length() == 1) {
                return String.valueOf(lowerCase);
            }
            if (!Character.isUpperCase(str.charAt(1))) {
                char[] charArray = str.toCharArray();
                charArray[0] = Character.toLowerCase(charArray[0]);
                return new String(charArray);
            }
        }
        return str;
    }
}
