package com.sun.xml.bind.v2.model.impl;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.util.Which;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.annotation.ClassLocatable;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.ErrorHandler;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.Ref;
import com.sun.xml.bind.v2.model.core.RegistryInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public class ModelBuilder<T, C, F, M> implements ModelBuilderI<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger;
    public final String defaultNsUri;
    private ErrorHandler errorHandler;
    private boolean hadError;
    public boolean hasSwaRef;
    private boolean linked;
    public final Navigator<T, C, F, M> nav;
    private final ErrorHandler proxyErrorHandler;
    public final AnnotationReader<T, C, F, M> reader;
    private final Map<C, C> subclassReplacements;
    final TypeInfoSetImpl<T, C, F, M> typeInfoSet;
    private final Map<QName, TypeInfo> typeNames = new HashMap();
    final Map<String, RegistryInfoImpl<T, C, F, M>> registries = new HashMap();

    static {
        Messages messages;
        XmlSchema xmlSchema = null;
        try {
            try {
                xmlSchema.location();
                throw null;
            } catch (NoSuchMethodError unused) {
                if (SecureLoader.getClassClassLoader(XmlSchema.class) == null) {
                    messages = Messages.INCOMPATIBLE_API_VERSION_MUSTANG;
                } else {
                    messages = Messages.INCOMPATIBLE_API_VERSION;
                }
                throw new LinkageError(messages.format(Which.which(XmlSchema.class), Which.which(ModelBuilder.class)));
            } catch (NullPointerException unused2) {
                WhiteSpaceProcessor.isWhiteSpace("xyz");
                logger = Logger.getLogger(ModelBuilder.class.getName());
            }
        } catch (NoSuchMethodError unused3) {
            throw new LinkageError(Messages.RUNNING_WITH_1_0_RUNTIME.format(Which.which(WhiteSpaceProcessor.class), Which.which(ModelBuilder.class)));
        }
    }

    public ModelBuilder(AnnotationReader<T, C, F, M> annotationReader, Navigator<T, C, F, M> navigator, Map<C, C> map, String str) {
        ErrorHandler errorHandler = new ErrorHandler() { // from class: com.sun.xml.bind.v2.model.impl.ModelBuilder.1
            @Override // com.sun.xml.bind.v2.model.core.ErrorHandler
            public void error(IllegalAnnotationException illegalAnnotationException) {
                ModelBuilder.this.reportError(illegalAnnotationException);
            }
        };
        this.proxyErrorHandler = errorHandler;
        this.reader = annotationReader;
        this.nav = navigator;
        this.subclassReplacements = map;
        this.defaultNsUri = str == null ? "" : str;
        annotationReader.setErrorHandler(errorHandler);
        this.typeInfoSet = createTypeInfoSet();
    }

    protected TypeInfoSetImpl<T, C, F, M> createTypeInfoSet() {
        Navigator<T, C, F, M> navigator = this.nav;
        return new TypeInfoSetImpl<>(navigator, this.reader, BuiltinLeafInfoImpl.createLeaves(navigator));
    }

    public NonElement<T, C> getClassInfo(C c, Locatable locatable) {
        return getClassInfo(c, false, locatable);
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
    public NonElement<T, C> getClassInfo(C c, boolean z, Locatable locatable) {
        NonElement<T, C> classInfo;
        NonElement<T, C> classInfo2 = this.typeInfoSet.getClassInfo(c);
        if (classInfo2 != null) {
            return classInfo2;
        }
        if (this.nav.isEnum(c)) {
            classInfo = createEnumLeafInfo(c, locatable);
            this.typeInfoSet.add(classInfo);
            addTypeName(classInfo);
        } else {
            boolean zContainsKey = this.subclassReplacements.containsKey(c);
            if (zContainsKey && !z) {
                classInfo = getClassInfo(this.subclassReplacements.get(c), locatable);
            } else if (this.reader.hasClassAnnotation(c, XmlTransient.class) || zContainsKey) {
                classInfo = getClassInfo(this.nav.getSuperClass(c), z, new ClassLocatable(locatable, c, this.nav));
            } else {
                classInfo = createClassInfo(c, locatable);
                this.typeInfoSet.add(classInfo);
                for (PropertyInfo<T, C> propertyInfo : classInfo.getProperties()) {
                    if (propertyInfo.kind() == PropertyKind.REFERENCE) {
                        Locatable locatable2 = (Locatable) propertyInfo;
                        addToRegistry(c, locatable2);
                        Class[] parametrizedTypes = getParametrizedTypes(propertyInfo);
                        if (parametrizedTypes != null) {
                            for (Class cls : parametrizedTypes) {
                                if (cls != c) {
                                    addToRegistry(cls, locatable2);
                                }
                            }
                        }
                    }
                    for (TypeInfo<T, C> typeInfo : propertyInfo.ref()) {
                    }
                }
                classInfo.getBaseClass();
                addTypeName(classInfo);
            }
        }
        XmlSeeAlso xmlSeeAlso = (XmlSeeAlso) this.reader.getClassAnnotation(XmlSeeAlso.class, c, locatable);
        if (xmlSeeAlso != null) {
            for (T t : this.reader.getClassArrayValue(xmlSeeAlso, WuwInfoBean.ValueKey)) {
                getTypeInfo(t, (Locatable) xmlSeeAlso);
            }
        }
        return classInfo;
    }

    private void addToRegistry(C c, Locatable locatable) {
        C cLoadObjectFactory;
        String packageName = this.nav.getPackageName(c);
        if (this.registries.containsKey(packageName) || (cLoadObjectFactory = this.nav.loadObjectFactory(c, packageName)) == null) {
            return;
        }
        addRegistry(cLoadObjectFactory, locatable);
    }

    private Class[] getParametrizedTypes(PropertyInfo propertyInfo) {
        try {
            Type individualType = ((RuntimePropertyInfo) propertyInfo).getIndividualType();
            if (!(individualType instanceof ParameterizedType)) {
                return null;
            }
            ParameterizedType parameterizedType = (ParameterizedType) individualType;
            if (parameterizedType.getRawType() != JAXBElement.class) {
                return null;
            }
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class[] clsArr = new Class[actualTypeArguments.length];
            for (int i = 0; i < actualTypeArguments.length; i++) {
                clsArr[i] = (Class) actualTypeArguments[i];
            }
            return clsArr;
        } catch (Exception e) {
            logger.log(Level.FINE, "Error in ModelBuilder.getParametrizedTypes. " + e.getMessage());
            return null;
        }
    }

    private void addTypeName(NonElement<T, C> nonElement) {
        TypeInfo typeInfoPut;
        QName typeName = nonElement.getTypeName();
        if (typeName == null || (typeInfoPut = this.typeNames.put(typeName, nonElement)) == null) {
            return;
        }
        reportError(new IllegalAnnotationException(Messages.CONFLICTING_XML_TYPE_MAPPING.format(nonElement.getTypeName()), typeInfoPut, nonElement));
    }

    public NonElement<T, C> getTypeInfo(T t, Locatable locatable) {
        NonElement<T, C> typeInfo = this.typeInfoSet.getTypeInfo(t);
        if (typeInfo != null) {
            return typeInfo;
        }
        if (this.nav.isArray(t)) {
            ArrayInfoImpl<T, C, F, M> arrayInfoImplCreateArrayInfo = createArrayInfo(locatable, t);
            addTypeName(arrayInfoImplCreateArrayInfo);
            this.typeInfoSet.add(arrayInfoImplCreateArrayInfo);
            return arrayInfoImplCreateArrayInfo;
        }
        return getClassInfo(this.nav.asDecl(t), locatable);
    }

    public NonElement<T, C> getTypeInfo(Ref<T, C> ref) {
        C cAsDecl = this.nav.asDecl(ref.type);
        if (cAsDecl != null && this.reader.getClassAnnotation(XmlRegistry.class, cAsDecl, null) != null) {
            if (!this.registries.containsKey(this.nav.getPackageName(cAsDecl))) {
                addRegistry(cAsDecl, null);
            }
            return null;
        }
        return getTypeInfo(ref.type, null);
    }

    protected EnumLeafInfoImpl<T, C, F, M> createEnumLeafInfo(C c, Locatable locatable) {
        return new EnumLeafInfoImpl<>(this, locatable, c, this.nav.use(c));
    }

    protected ClassInfoImpl<T, C, F, M> createClassInfo(C c, Locatable locatable) {
        return new ClassInfoImpl<>(this, locatable, c);
    }

    protected ElementInfoImpl<T, C, F, M> createElementInfo(RegistryInfoImpl<T, C, F, M> registryInfoImpl, M m) throws IllegalAnnotationException {
        return new ElementInfoImpl<>(this, registryInfoImpl, m);
    }

    protected ArrayInfoImpl<T, C, F, M> createArrayInfo(Locatable locatable, T t) {
        return new ArrayInfoImpl<>(this, locatable, t);
    }

    public RegistryInfo<T, C> addRegistry(C c, Locatable locatable) {
        return new RegistryInfoImpl(this, locatable, c);
    }

    public RegistryInfo<T, C> getRegistry(String str) {
        return this.registries.get(str);
    }

    public TypeInfoSet<T, C, F, M> link() {
        this.linked = true;
        Iterator<? extends ElementInfoImpl<T, C, F, M>> it = this.typeInfoSet.getAllElements().iterator();
        while (it.hasNext()) {
            it.next().link();
        }
        Iterator<? extends ClassInfoImpl<T, C, F, M>> it2 = this.typeInfoSet.beans().values().iterator();
        while (it2.hasNext()) {
            it2.next().link();
        }
        Iterator<? extends EnumLeafInfoImpl<T, C, F, M>> it3 = this.typeInfoSet.enums().values().iterator();
        while (it3.hasNext()) {
            it3.next().link();
        }
        if (this.hadError) {
            return null;
        }
        return this.typeInfoSet;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public final void reportError(IllegalAnnotationException illegalAnnotationException) {
        this.hadError = true;
        ErrorHandler errorHandler = this.errorHandler;
        if (errorHandler != null) {
            errorHandler.error(illegalAnnotationException);
        }
    }

    public boolean isReplaced(C c) {
        return this.subclassReplacements.containsKey(c);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilderI
    public Navigator<T, C, F, M> getNavigator() {
        return this.nav;
    }

    @Override // com.sun.xml.bind.v2.model.impl.ModelBuilderI
    public AnnotationReader<T, C, F, M> getReader() {
        return this.reader;
    }
}
