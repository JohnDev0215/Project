package com.sun.xml.bind.v2.model.impl;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import com.sun.xml.bind.v2.runtime.SwaRefAdapter;
import java.lang.annotation.Annotation;
import java.util.Collection;
import javax.activation.MimeType;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
abstract class PropertyInfoImpl<T, C, F, M> implements PropertyInfo<T, C>, Locatable, Comparable<PropertyInfoImpl> {
    private final Adapter<T, C> adapter;
    private final MimeType expectedMimeType;
    private final ID id;
    private final boolean inlineBinary;
    private final boolean isCollection;
    protected final ClassInfoImpl<T, C, F, M> parent;
    private final QName schemaType;
    protected final PropertySeed<T, C, F, M> seed;

    protected PropertyInfoImpl(ClassInfoImpl<T, C, F, M> classInfoImpl, PropertySeed<T, C, F, M> propertySeed) {
        this.seed = propertySeed;
        this.parent = classInfoImpl;
        if (classInfoImpl == null) {
            throw new AssertionError();
        }
        MimeType mimeTypeCalcExpectedMediaType = Util.calcExpectedMediaType(propertySeed, classInfoImpl.builder);
        if (mimeTypeCalcExpectedMediaType != null && !kind().canHaveXmlMimeType) {
            classInfoImpl.builder.reportError(new IllegalAnnotationException(Messages.ILLEGAL_ANNOTATION.format(XmlMimeType.class.getName()), propertySeed.readAnnotation(XmlMimeType.class)));
            mimeTypeCalcExpectedMediaType = null;
        }
        this.expectedMimeType = mimeTypeCalcExpectedMediaType;
        this.inlineBinary = propertySeed.hasAnnotation(XmlInlineBinaryData.class);
        T rawType = propertySeed.getRawType();
        XmlJavaTypeAdapter applicableAdapter = getApplicableAdapter(rawType);
        if (applicableAdapter != null) {
            this.isCollection = false;
            this.adapter = new Adapter<>(applicableAdapter, reader(), nav());
        } else {
            this.isCollection = nav().isSubClassOf(rawType, nav().ref2(Collection.class)) || nav().isArrayButNotByteArray(rawType);
            XmlJavaTypeAdapter applicableAdapter2 = getApplicableAdapter(getIndividualType());
            if (applicableAdapter2 == null) {
                if (((XmlAttachmentRef) propertySeed.readAnnotation(XmlAttachmentRef.class)) != null) {
                    classInfoImpl.builder.hasSwaRef = true;
                    this.adapter = new Adapter<>(nav().asDecl(SwaRefAdapter.class), nav());
                } else {
                    this.adapter = null;
                    XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter) propertySeed.readAnnotation(XmlJavaTypeAdapter.class);
                    if (xmlJavaTypeAdapter != null) {
                        classInfoImpl.builder.reportError(new IllegalAnnotationException(Messages.UNMATCHABLE_ADAPTER.format(nav().getTypeName(reader().getClassValue2(xmlJavaTypeAdapter, WuwInfoBean.ValueKey)), nav().getTypeName(rawType)), xmlJavaTypeAdapter));
                    }
                }
            } else {
                this.adapter = new Adapter<>(applicableAdapter2, reader(), nav());
            }
        }
        this.id = calcId();
        this.schemaType = Util.calcSchemaType(reader(), propertySeed, classInfoImpl.clazz, getIndividualType(), this);
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public ClassInfoImpl<T, C, F, M> parent() {
        return this.parent;
    }

    protected final Navigator<T, C, F, M> nav() {
        return this.parent.nav();
    }

    protected final AnnotationReader<T, C, F, M> reader() {
        return this.parent.reader();
    }

    public T getRawType() {
        return this.seed.getRawType();
    }

    public T getIndividualType() {
        Adapter<T, C> adapter = this.adapter;
        if (adapter != null) {
            return adapter.defaultType;
        }
        T rawType = getRawType();
        if (!isCollection()) {
            return rawType;
        }
        if (nav().isArrayButNotByteArray(rawType)) {
            return nav().getComponentType(rawType);
        }
        T baseClass = nav().getBaseClass(rawType, nav().asDecl(Collection.class));
        if (nav().isParameterizedType(baseClass)) {
            return nav().getTypeArgument(baseClass, 0);
        }
        return nav().ref2(Object.class);
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final String getName() {
        return this.seed.getName();
    }

    private boolean isApplicable(XmlJavaTypeAdapter xmlJavaTypeAdapter, T t) {
        if (xmlJavaTypeAdapter == null) {
            return false;
        }
        if (nav().isSameType(t, reader().getClassValue2(xmlJavaTypeAdapter, "type"))) {
            return true;
        }
        T baseClass = nav().getBaseClass(reader().getClassValue2(xmlJavaTypeAdapter, WuwInfoBean.ValueKey), nav().asDecl(XmlAdapter.class));
        if (!nav().isParameterizedType(baseClass)) {
            return true;
        }
        return nav().isSubClassOf(t, nav().getTypeArgument(baseClass, 1));
    }

    private XmlJavaTypeAdapter getApplicableAdapter(T t) {
        XmlJavaTypeAdapter xmlJavaTypeAdapter;
        XmlJavaTypeAdapter xmlJavaTypeAdapter2 = (XmlJavaTypeAdapter) this.seed.readAnnotation(XmlJavaTypeAdapter.class);
        if (xmlJavaTypeAdapter2 != null && isApplicable(xmlJavaTypeAdapter2, t)) {
            return xmlJavaTypeAdapter2;
        }
        XmlJavaTypeAdapters xmlJavaTypeAdapters = (XmlJavaTypeAdapters) reader().getPackageAnnotation(XmlJavaTypeAdapters.class, this.parent.clazz, this.seed);
        if (xmlJavaTypeAdapters != null) {
            for (XmlJavaTypeAdapter xmlJavaTypeAdapter3 : xmlJavaTypeAdapters.value()) {
                if (isApplicable(xmlJavaTypeAdapter3, t)) {
                    return xmlJavaTypeAdapter3;
                }
            }
        }
        XmlJavaTypeAdapter xmlJavaTypeAdapter4 = (XmlJavaTypeAdapter) reader().getPackageAnnotation(XmlJavaTypeAdapter.class, this.parent.clazz, this.seed);
        if (isApplicable(xmlJavaTypeAdapter4, t)) {
            return xmlJavaTypeAdapter4;
        }
        C cAsDecl = nav().asDecl(t);
        if (cAsDecl == null || (xmlJavaTypeAdapter = (XmlJavaTypeAdapter) reader().getClassAnnotation(XmlJavaTypeAdapter.class, cAsDecl, this.seed)) == null || !isApplicable(xmlJavaTypeAdapter, t)) {
            return null;
        }
        return xmlJavaTypeAdapter;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public Adapter<T, C> getAdapter() {
        return this.adapter;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final String displayName() {
        return nav().getClassName(this.parent.getClazz()) + '#' + getName();
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final ID id() {
        return this.id;
    }

    private ID calcId() {
        if (this.seed.hasAnnotation(XmlID.class)) {
            if (!nav().isSameType(getIndividualType(), nav().ref2(String.class))) {
                this.parent.builder.reportError(new IllegalAnnotationException(Messages.ID_MUST_BE_STRING.format(getName()), this.seed));
            }
            return ID.ID;
        }
        if (this.seed.hasAnnotation(XmlIDREF.class)) {
            return ID.IDREF;
        }
        return ID.NONE;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final MimeType getExpectedMimeType() {
        return this.expectedMimeType;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final boolean inlineBinaryData() {
        return this.inlineBinary;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final QName getSchemaType() {
        return this.schemaType;
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public final boolean isCollection() {
        return this.isCollection;
    }

    protected void link() {
        if (this.id == ID.IDREF) {
            for (TypeInfo<T, C> typeInfo : ref()) {
                if (!typeInfo.canBeReferencedByIDREF()) {
                    this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_IDREF.format(this.parent.builder.nav.getTypeName(typeInfo.getType())), this));
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.parent;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.seed.getLocation();
    }

    protected final QName calcXmlName(XmlElement xmlElement) {
        if (xmlElement != null) {
            return calcXmlName(xmlElement.namespace(), xmlElement.name());
        }
        return calcXmlName("##default", "##default");
    }

    protected final QName calcXmlName(XmlElementWrapper xmlElementWrapper) {
        if (xmlElementWrapper != null) {
            return calcXmlName(xmlElementWrapper.namespace(), xmlElementWrapper.name());
        }
        return calcXmlName("##default", "##default");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private javax.xml.namespace.QName calcXmlName(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            com.sun.xml.bind.v2.TODO.checkSpec()
            int r0 = r6.length()
            java.lang.String r1 = "##default"
            if (r0 == 0) goto L11
            boolean r0 = r6.equals(r1)
            if (r0 == 0) goto L17
        L11:
            com.sun.xml.bind.v2.model.impl.PropertySeed<T, C, F, M> r6 = r4.seed
            java.lang.String r6 = r6.getName()
        L17:
            boolean r0 = r5.equals(r1)
            if (r0 == 0) goto L68
            com.sun.xml.bind.v2.model.annotation.AnnotationReader r0 = r4.reader()
            java.lang.Class<javax.xml.bind.annotation.XmlSchema> r1 = javax.xml.bind.annotation.XmlSchema.class
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r2 = r4.parent
            java.lang.Object r2 = r2.getClazz()
            java.lang.annotation.Annotation r0 = r0.getPackageAnnotation(r1, r2, r4)
            javax.xml.bind.annotation.XmlSchema r0 = (javax.xml.bind.annotation.XmlSchema) r0
            java.lang.String r1 = ""
            if (r0 == 0) goto L67
            int[] r2 = com.sun.xml.bind.v2.model.impl.PropertyInfoImpl.AnonymousClass1.$SwitchMap$javax$xml$bind$annotation$XmlNsForm
            javax.xml.bind.annotation.XmlNsForm r3 = r0.elementFormDefault()
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 1
            if (r2 == r3) goto L49
            r0 = 2
            if (r2 == r0) goto L67
            r0 = 3
            if (r2 == r0) goto L67
            goto L68
        L49:
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r5 = r4.parent
            javax.xml.namespace.QName r5 = r5.getTypeName()
            if (r5 == 0) goto L56
            java.lang.String r5 = r5.getNamespaceURI()
            goto L5a
        L56:
            java.lang.String r5 = r0.namespace()
        L5a:
            int r0 = r5.length()
            if (r0 != 0) goto L68
            com.sun.xml.bind.v2.model.impl.ClassInfoImpl<T, C, F, M> r5 = r4.parent
            com.sun.xml.bind.v2.model.impl.ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> r5 = r5.builder
            java.lang.String r5 = r5.defaultNsUri
            goto L68
        L67:
            r5 = r1
        L68:
            javax.xml.namespace.QName r0 = new javax.xml.namespace.QName
            java.lang.String r5 = r5.intern()
            java.lang.String r6 = r6.intern()
            r0.<init>(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.model.impl.PropertyInfoImpl.calcXmlName(java.lang.String, java.lang.String):javax.xml.namespace.QName");
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.model.impl.PropertyInfoImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$xml$bind$annotation$XmlNsForm;

        static {
            int[] iArr = new int[XmlNsForm.values().length];
            $SwitchMap$javax$xml$bind$annotation$XmlNsForm = iArr;
            try {
                iArr[XmlNsForm.QUALIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlNsForm[XmlNsForm.UNQUALIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlNsForm[XmlNsForm.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(PropertyInfoImpl propertyInfoImpl) {
        return getName().compareTo(propertyInfoImpl.getName());
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public final <A extends Annotation> A readAnnotation(Class<A> cls) {
        return (A) this.seed.readAnnotation(cls);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public final boolean hasAnnotation(Class<? extends Annotation> cls) {
        return this.seed.hasAnnotation(cls);
    }
}
