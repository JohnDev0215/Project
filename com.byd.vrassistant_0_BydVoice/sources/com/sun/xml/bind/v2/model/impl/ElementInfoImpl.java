package com.sun.xml.bind.v2.model.impl;

import com.byd.common.bean.WuwInfoBean;
import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.v2.model.annotation.AnnotationSource;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.activation.MimeType;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class ElementInfoImpl<T, C, F, M> extends TypeInfoImpl<T, C, F, M> implements ElementInfo<T, C> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Adapter<T, C> adapter;
    private final XmlElementDecl anno;
    private final NonElement<T, C> contentType;
    private final T elementType;
    private final MimeType expectedMimeType;
    private final ID id;
    private final boolean inlineBinary;
    private final boolean isCollection;
    private final M method;
    private final ElementInfoImpl<T, C, F, M>.PropertyImpl property;
    private final QName schemaType;
    private final ClassInfo<T, C> scope;
    private ElementInfoImpl<T, C, F, M> substitutionHead;
    private FinalArrayList<ElementInfoImpl<T, C, F, M>> substitutionMembers;
    private final T tOfJAXBElementT;
    private final QName tagName;

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final boolean canBeReferencedByIDREF() {
        return false;
    }

    protected class PropertyImpl implements ElementPropertyInfo<T, C>, TypeRef<T, C>, AnnotationSource {
        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public String displayName() {
            return "JAXBElement#value";
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public String getName() {
            return WuwInfoBean.ValueKey;
        }

        @Override // com.sun.xml.bind.v2.model.core.NonElementRef
        public PropertyInfo<T, C> getSource() {
            return this;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public boolean isCollectionNillable() {
            return true;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public boolean isCollectionRequired() {
            return false;
        }

        @Override // com.sun.xml.bind.v2.model.core.TypeRef
        public boolean isNillable() {
            return true;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public boolean isRequired() {
            return true;
        }

        protected PropertyImpl() {
        }

        @Override // com.sun.xml.bind.v2.model.core.NonElementRef
        public NonElement<T, C> getTarget() {
            return ElementInfoImpl.this.contentType;
        }

        @Override // com.sun.xml.bind.v2.model.core.TypeRef
        public QName getTagName() {
            return ElementInfoImpl.this.tagName;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public List<? extends TypeRef<T, C>> getTypes() {
            return Collections.singletonList(this);
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public List<? extends NonElement<T, C>> ref() {
            return Collections.singletonList(ElementInfoImpl.this.contentType);
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public QName getXmlName() {
            return ElementInfoImpl.this.tagName;
        }

        @Override // com.sun.xml.bind.v2.model.core.TypeRef
        public String getDefaultValue() {
            String strDefaultValue = ElementInfoImpl.this.anno.defaultValue();
            if (strDefaultValue.equals("\u0000")) {
                return null;
            }
            return strDefaultValue;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public ElementInfoImpl<T, C, F, M> parent() {
            return ElementInfoImpl.this;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public boolean isCollection() {
            return ElementInfoImpl.this.isCollection;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
        public boolean isValueList() {
            return ElementInfoImpl.this.isCollection;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public PropertyKind kind() {
            return PropertyKind.ELEMENT;
        }

        @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo, com.sun.xml.bind.v2.model.core.PropertyInfo
        public Adapter<T, C> getAdapter() {
            return ElementInfoImpl.this.adapter;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public ID id() {
            return ElementInfoImpl.this.id;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public MimeType getExpectedMimeType() {
            return ElementInfoImpl.this.expectedMimeType;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public QName getSchemaType() {
            return ElementInfoImpl.this.schemaType;
        }

        @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
        public boolean inlineBinaryData() {
            return ElementInfoImpl.this.inlineBinary;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
        public <A extends Annotation> A readAnnotation(Class<A> cls) {
            return (A) ElementInfoImpl.this.reader().getMethodAnnotation(cls, ElementInfoImpl.this.method, ElementInfoImpl.this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
        public boolean hasAnnotation(Class<? extends Annotation> cls) {
            return ElementInfoImpl.this.reader().hasMethodAnnotation(cls, ElementInfoImpl.this.method);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ElementInfoImpl(com.sun.xml.bind.v2.model.impl.ModelBuilder<T, C, F, M> r11, com.sun.xml.bind.v2.model.impl.RegistryInfoImpl<T, C, F, M> r12, M r13) throws com.sun.xml.bind.v2.runtime.IllegalAnnotationException {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.model.impl.ElementInfoImpl.<init>(com.sun.xml.bind.v2.model.impl.ModelBuilder, com.sun.xml.bind.v2.model.impl.RegistryInfoImpl, java.lang.Object):void");
    }

    final QName parseElementName(XmlElementDecl xmlElementDecl) {
        String strName = xmlElementDecl.name();
        String strNamespace = xmlElementDecl.namespace();
        if (strNamespace.equals("##default")) {
            XmlSchema xmlSchema = (XmlSchema) reader().getPackageAnnotation(XmlSchema.class, nav().getDeclaringClassForMethod(this.method), this);
            if (xmlSchema != null) {
                strNamespace = xmlSchema.namespace();
            } else {
                strNamespace = this.builder.defaultNsUri;
            }
        }
        return new QName(strNamespace.intern(), strName.intern());
    }

    protected ElementInfoImpl<T, C, F, M>.PropertyImpl createPropertyImpl() {
        return new PropertyImpl();
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    public ElementPropertyInfo<T, C> getProperty() {
        return this.property;
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    public NonElement<T, C> getContentType() {
        return this.contentType;
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    public T getContentInMemoryType() {
        Adapter<T, C> adapter = this.adapter;
        if (adapter == null) {
            return this.tOfJAXBElementT;
        }
        return adapter.customType;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public QName getElementName() {
        return this.tagName;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    /* JADX INFO: renamed from: getType */
    public T getType2() {
        return this.elementType;
    }

    private ID calcId() {
        if (reader().hasMethodAnnotation(XmlID.class, this.method)) {
            return ID.ID;
        }
        if (reader().hasMethodAnnotation(XmlIDREF.class, this.method)) {
            return ID.IDREF;
        }
        return ID.NONE;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public ClassInfo<T, C> getScope() {
        return this.scope;
    }

    @Override // com.sun.xml.bind.v2.model.core.Element
    public ElementInfo<T, C> getSubstitutionHead() {
        return this.substitutionHead;
    }

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    public Collection<? extends ElementInfoImpl<T, C, F, M>> getSubstitutionMembers() {
        FinalArrayList<ElementInfoImpl<T, C, F, M>> finalArrayList = this.substitutionMembers;
        return finalArrayList == null ? Collections.emptyList() : finalArrayList;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoImpl
    void link() {
        if (this.anno.substitutionHeadName().length() != 0) {
            QName qName = new QName(this.anno.substitutionHeadNamespace(), this.anno.substitutionHeadName());
            ElementInfoImpl<T, C, F, M> elementInfo = this.owner.getElementInfo((Object) null, qName);
            this.substitutionHead = elementInfo;
            if (elementInfo == null) {
                this.builder.reportError(new IllegalAnnotationException(Messages.NON_EXISTENT_ELEMENT_MAPPING.format(qName.getNamespaceURI(), qName.getLocalPart()), this.anno));
            } else {
                elementInfo.addSubstitutionMember(this);
            }
        } else {
            this.substitutionHead = null;
        }
        super.link();
    }

    private void addSubstitutionMember(ElementInfoImpl<T, C, F, M> elementInfoImpl) {
        if (this.substitutionMembers == null) {
            this.substitutionMembers = new FinalArrayList<>();
        }
        this.substitutionMembers.add(elementInfoImpl);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return nav().getMethodLocation(this.method);
    }
}
