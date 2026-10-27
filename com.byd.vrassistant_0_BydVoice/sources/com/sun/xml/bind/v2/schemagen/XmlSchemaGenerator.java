package com.sun.xml.bind.v2.schemagen;

import com.byd.common.bean.WuwInfoBean;
import com.byd.mediacenter.api.media.BuildConfig;
import com.sun.xml.bind.api.CompositeStructure;
import com.sun.xml.bind.api.ErrorListener;
import com.sun.xml.bind.v2.TODO;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.core.Adapter;
import com.sun.xml.bind.v2.model.core.ArrayInfo;
import com.sun.xml.bind.v2.model.core.AttributePropertyInfo;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.EnumConstant;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.MapPropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.NonElementRef;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.ReferencePropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.model.core.ValuePropertyInfo;
import com.sun.xml.bind.v2.model.core.WildcardMode;
import com.sun.xml.bind.v2.model.impl.ClassInfoImpl;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.SwaRefAdapter;
import com.sun.xml.bind.v2.schemagen.Tree;
import com.sun.xml.bind.v2.schemagen.episode.Bindings;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;
import com.sun.xml.bind.v2.schemagen.xmlschema.AttrDecls;
import com.sun.xml.bind.v2.schemagen.xmlschema.AttributeType;
import com.sun.xml.bind.v2.schemagen.xmlschema.ComplexExtension;
import com.sun.xml.bind.v2.schemagen.xmlschema.ComplexType;
import com.sun.xml.bind.v2.schemagen.xmlschema.ComplexTypeHost;
import com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer;
import com.sun.xml.bind.v2.schemagen.xmlschema.ExplicitGroup;
import com.sun.xml.bind.v2.schemagen.xmlschema.Import;
import com.sun.xml.bind.v2.schemagen.xmlschema.LocalAttribute;
import com.sun.xml.bind.v2.schemagen.xmlschema.LocalElement;
import com.sun.xml.bind.v2.schemagen.xmlschema.Schema;
import com.sun.xml.bind.v2.schemagen.xmlschema.SimpleExtension;
import com.sun.xml.bind.v2.schemagen.xmlschema.SimpleRestriction;
import com.sun.xml.bind.v2.schemagen.xmlschema.SimpleType;
import com.sun.xml.bind.v2.schemagen.xmlschema.SimpleTypeHost;
import com.sun.xml.bind.v2.schemagen.xmlschema.TopLevelAttribute;
import com.sun.xml.bind.v2.schemagen.xmlschema.TopLevelElement;
import com.sun.xml.bind.v2.schemagen.xmlschema.TypeHost;
import com.sun.xml.bind.v2.util.CollisionCheckStack;
import com.sun.xml.bind.v2.util.StackRecorder;
import com.sun.xml.txw2.TXW;
import com.sun.xml.txw2.TxwException;
import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.output.ResultFactory;
import com.sun.xml.txw2.output.XmlSerializer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeType;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public final class XmlSchemaGenerator<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String newline = "\n";
    private final NonElement<T, C> anyType;
    private ErrorListener errorListener;
    private Navigator<T, C, F, M> navigator;
    private final NonElement<T, C> stringType;
    private final TypeInfoSet<T, C, F, M> types;
    private static final Logger logger = com.sun.xml.bind.Util.getClassLogger();
    private static final Comparator<String> NAMESPACE_COMPARATOR = new Comparator<String>() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.1
        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return -str.compareTo(str2);
        }
    };
    private final Map<String, XmlSchemaGenerator<T, C, F, M>.Namespace> namespaces = new TreeMap(NAMESPACE_COMPARATOR);
    private final CollisionCheckStack<ClassInfo<T, C>> collisionChecker = new CollisionCheckStack<>();

    private static String fixNull(String str) {
        return str == null ? "" : str;
    }

    public XmlSchemaGenerator(Navigator<T, C, F, M> navigator, TypeInfoSet<T, C, F, M> typeInfoSet) {
        this.navigator = navigator;
        this.types = typeInfoSet;
        this.stringType = typeInfoSet.getTypeInfo(navigator.ref(String.class));
        this.anyType = typeInfoSet.getAnyTypeInfo();
        Iterator<? extends ClassInfo<T, C>> it = typeInfoSet.beans().values().iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        Iterator<? extends ElementInfo<T, C>> it2 = typeInfoSet.getElementMappings(null).values().iterator();
        while (it2.hasNext()) {
            add(it2.next());
        }
        Iterator<? extends EnumLeafInfo<T, C>> it3 = typeInfoSet.enums().values().iterator();
        while (it3.hasNext()) {
            add(it3.next());
        }
        Iterator<? extends ArrayInfo<T, C>> it4 = typeInfoSet.arrays().values().iterator();
        while (it4.hasNext()) {
            add(it4.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public XmlSchemaGenerator<T, C, F, M>.Namespace getNamespace(String str) {
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace = this.namespaces.get(str);
        if (namespace != null) {
            return namespace;
        }
        Map<String, XmlSchemaGenerator<T, C, F, M>.Namespace> map = this.namespaces;
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace2 = new Namespace(str);
        map.put(str, namespace2);
        return namespace2;
    }

    public void add(ClassInfo<T, C> classInfo) {
        String namespaceURI;
        if (classInfo.getClazz() == this.navigator.asDecl(CompositeStructure.class)) {
            return;
        }
        if (classInfo.isElement()) {
            namespaceURI = classInfo.getElementName().getNamespaceURI();
            XmlSchemaGenerator<T, C, F, M>.Namespace namespace = getNamespace(namespaceURI);
            ((Namespace) namespace).classes.add(classInfo);
            namespace.addDependencyTo(classInfo.getTypeName());
            add(classInfo.getElementName(), false, classInfo);
        } else {
            namespaceURI = null;
        }
        QName typeName = classInfo.getTypeName();
        if (typeName != null) {
            namespaceURI = typeName.getNamespaceURI();
        } else if (namespaceURI == null) {
            return;
        }
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace2 = getNamespace(namespaceURI);
        ((Namespace) namespace2).classes.add(classInfo);
        for (PropertyInfo<T, C> propertyInfo : classInfo.getProperties()) {
            namespace2.processForeignNamespaces(propertyInfo, 1);
            if (propertyInfo instanceof AttributePropertyInfo) {
                AttributePropertyInfo<T, C> attributePropertyInfo = (AttributePropertyInfo) propertyInfo;
                String namespaceURI2 = attributePropertyInfo.getXmlName().getNamespaceURI();
                if (namespaceURI2.length() > 0) {
                    getNamespace(namespaceURI2).addGlobalAttribute(attributePropertyInfo);
                    namespace2.addDependencyTo(attributePropertyInfo.getXmlName());
                }
            }
            if (propertyInfo instanceof ElementPropertyInfo) {
                for (TypeRef<T, C> typeRef : ((ElementPropertyInfo) propertyInfo).getTypes()) {
                    String namespaceURI3 = typeRef.getTagName().getNamespaceURI();
                    if (namespaceURI3.length() > 0 && !namespaceURI3.equals(namespace2.uri)) {
                        getNamespace(namespaceURI3).addGlobalElement(typeRef);
                        namespace2.addDependencyTo(typeRef.getTagName());
                    }
                }
            }
            if (generateSwaRefAdapter(propertyInfo)) {
                ((Namespace) namespace2).useSwaRef = true;
            }
            if (propertyInfo.getExpectedMimeType() != null) {
                ((Namespace) namespace2).useMimeNs = true;
            }
        }
        ClassInfo<T, C> baseClass2 = classInfo.getBaseClass2();
        if (baseClass2 != null) {
            add(baseClass2);
            namespace2.addDependencyTo(baseClass2.getTypeName());
        }
    }

    public void add(ElementInfo<T, C> elementInfo) {
        ElementInfo<T, C> elementInfo2;
        QName elementName = elementInfo.getElementName();
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace = getNamespace(elementName.getNamespaceURI());
        if (elementInfo.getScope() != null) {
            elementInfo2 = this.types.getElementInfo(elementInfo.getScope().getClazz(), elementName);
        } else {
            elementInfo2 = this.types.getElementInfo(null, elementName);
        }
        XmlElement xmlElement = (XmlElement) elementInfo2.getProperty().readAnnotation(XmlElement.class);
        boolean zNillable = xmlElement == null ? false : xmlElement.nillable();
        MultiMap multiMap = ((Namespace) namespace).elementDecls;
        String localPart = elementName.getLocalPart();
        namespace.getClass();
        multiMap.put(localPart, new Namespace.ElementWithType(zNillable, elementInfo.getContentType()));
        namespace.processForeignNamespaces(elementInfo.getProperty(), 1);
    }

    public void add(EnumLeafInfo<T, C> enumLeafInfo) {
        String namespaceURI;
        if (enumLeafInfo.isElement()) {
            namespaceURI = enumLeafInfo.getElementName().getNamespaceURI();
            XmlSchemaGenerator<T, C, F, M>.Namespace namespace = getNamespace(namespaceURI);
            ((Namespace) namespace).enums.add(enumLeafInfo);
            namespace.addDependencyTo(enumLeafInfo.getTypeName());
            add(enumLeafInfo.getElementName(), false, enumLeafInfo);
        } else {
            namespaceURI = null;
        }
        QName typeName = enumLeafInfo.getTypeName();
        if (typeName != null) {
            namespaceURI = typeName.getNamespaceURI();
        } else if (namespaceURI == null) {
            return;
        }
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace2 = getNamespace(namespaceURI);
        ((Namespace) namespace2).enums.add(enumLeafInfo);
        namespace2.addDependencyTo(enumLeafInfo.getBaseType().getTypeName());
    }

    public void add(ArrayInfo<T, C> arrayInfo) {
        XmlSchemaGenerator<T, C, F, M>.Namespace namespace = getNamespace(arrayInfo.getTypeName().getNamespaceURI());
        ((Namespace) namespace).arrays.add(arrayInfo);
        namespace.addDependencyTo(arrayInfo.getItemType().getTypeName());
    }

    public void add(QName qName, boolean z, NonElement<T, C> nonElement) {
        if (nonElement == null || nonElement.getType2() != this.navigator.ref(CompositeStructure.class)) {
            XmlSchemaGenerator<T, C, F, M>.Namespace namespace = getNamespace(qName.getNamespaceURI());
            MultiMap multiMap = ((Namespace) namespace).elementDecls;
            String localPart = qName.getLocalPart();
            namespace.getClass();
            multiMap.put(localPart, new Namespace.ElementWithType(z, nonElement));
            if (nonElement != null) {
                namespace.addDependencyTo(nonElement.getTypeName());
            }
        }
    }

    public void writeEpisodeFile(XmlSerializer xmlSerializer) {
        String str;
        Bindings bindings = (Bindings) TXW.create(Bindings.class, xmlSerializer);
        if (this.namespaces.containsKey("")) {
            bindings._namespace(WellKnownNamespace.JAXB, "jaxb");
        }
        bindings.version("2.1");
        for (Map.Entry<String, XmlSchemaGenerator<T, C, F, M>.Namespace> entry : this.namespaces.entrySet()) {
            Bindings bindings2 = bindings.bindings();
            String key = entry.getKey();
            if (key.equals("")) {
                str = "";
            } else {
                bindings2._namespace(key, "tns");
                str = "tns:";
            }
            bindings2.scd("x-schema::".concat(key.equals("") ? "" : "tns"));
            bindings2.schemaBindings().map(false);
            for (ClassInfo classInfo : ((Namespace) entry.getValue()).classes) {
                if (classInfo.getTypeName() != null) {
                    if (classInfo.getTypeName().getNamespaceURI().equals(key)) {
                        Bindings bindings3 = bindings2.bindings();
                        bindings3.scd("~" + str + classInfo.getTypeName().getLocalPart());
                        bindings3.klass().ref(classInfo.getName());
                    }
                    if (classInfo.isElement() && classInfo.getElementName().getNamespaceURI().equals(key)) {
                        Bindings bindings4 = bindings2.bindings();
                        bindings4.scd(str + classInfo.getElementName().getLocalPart());
                        bindings4.klass().ref(classInfo.getName());
                    }
                }
            }
            for (EnumLeafInfo enumLeafInfo : ((Namespace) entry.getValue()).enums) {
                if (enumLeafInfo.getTypeName() != null) {
                    Bindings bindings5 = bindings2.bindings();
                    bindings5.scd("~" + str + enumLeafInfo.getTypeName().getLocalPart());
                    bindings5.klass().ref(this.navigator.getClassName((C) enumLeafInfo.getClazz()));
                }
            }
            bindings2.commit(true);
        }
        bindings.commit();
    }

    public void write(SchemaOutputResolver schemaOutputResolver, ErrorListener errorListener) throws IOException {
        if (schemaOutputResolver == null) {
            throw new IllegalArgumentException();
        }
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.log(Level.FINE, "Writing XML Schema for " + toString(), (Throwable) new StackRecorder());
        }
        FoolProofResolver foolProofResolver = new FoolProofResolver(schemaOutputResolver);
        this.errorListener = errorListener;
        Map<String, String> schemaLocations = this.types.getSchemaLocations();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        this.namespaces.remove(WellKnownNamespace.XML_SCHEMA);
        for (XmlSchemaGenerator<T, C, F, M>.Namespace namespace : this.namespaces.values()) {
            String str = schemaLocations.get(namespace.uri);
            if (str != null) {
                map2.put(namespace, str);
            } else {
                Result resultCreateOutput = foolProofResolver.createOutput(namespace.uri, "schema" + (map.size() + 1) + ".xsd");
                if (resultCreateOutput != null) {
                    map.put(namespace, resultCreateOutput);
                    map2.put(namespace, resultCreateOutput.getSystemId());
                }
            }
            namespace.resetWritten();
        }
        for (Map.Entry entry : map.entrySet()) {
            Result result = (Result) entry.getValue();
            ((Namespace) entry.getKey()).writeTo(result, map2);
            if (result instanceof StreamResult) {
                StreamResult streamResult = (StreamResult) result;
                OutputStream outputStream = streamResult.getOutputStream();
                if (outputStream != null) {
                    outputStream.close();
                } else {
                    Writer writer = streamResult.getWriter();
                    if (writer != null) {
                        writer.close();
                    }
                }
            }
        }
    }

    private class Namespace {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Form attributeFormDefault;
        private final MultiMap<String, XmlSchemaGenerator<T, C, F, M>.Namespace.ElementDeclaration> elementDecls;
        private Form elementFormDefault;
        private boolean selfReference;
        final String uri;
        private boolean useMimeNs;
        private boolean useSwaRef;
        private final Set<XmlSchemaGenerator<T, C, F, M>.Namespace> depends = new LinkedHashSet();
        private final Set<ClassInfo<T, C>> classes = new LinkedHashSet();
        private final Set<EnumLeafInfo<T, C>> enums = new LinkedHashSet();
        private final Set<ArrayInfo<T, C>> arrays = new LinkedHashSet();
        private final MultiMap<String, AttributePropertyInfo<T, C>> attributeDecls = new MultiMap<>(null);
        private final Set<ClassInfo> written = new HashSet();

        public Namespace(String str) {
            this.elementDecls = new MultiMap<>(new ElementWithType(true, XmlSchemaGenerator.this.anyType));
            this.uri = str;
            XmlSchemaGenerator.this.namespaces.put(str, this);
        }

        void resetWritten() {
            this.written.clear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processForeignNamespaces(PropertyInfo<T, C> propertyInfo, int i) {
            for (TypeInfo<T, C> typeInfo : propertyInfo.ref()) {
                if ((typeInfo instanceof ClassInfo) && i > 0) {
                    Iterator<? extends PropertyInfo<T, C>> it = ((ClassInfo) typeInfo).getProperties().iterator();
                    while (it.hasNext()) {
                        i--;
                        processForeignNamespaces(it.next(), i);
                    }
                }
                if (typeInfo instanceof Element) {
                    addDependencyTo(((Element) typeInfo).getElementName());
                }
                if (typeInfo instanceof NonElement) {
                    addDependencyTo(((NonElement) typeInfo).getTypeName());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDependencyTo(QName qName) {
            if (qName == null) {
                return;
            }
            String namespaceURI = qName.getNamespaceURI();
            if (namespaceURI.equals(WellKnownNamespace.XML_SCHEMA)) {
                return;
            }
            if (!namespaceURI.equals(this.uri)) {
                this.depends.add(XmlSchemaGenerator.this.getNamespace(namespaceURI));
            } else {
                this.selfReference = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeTo(Result result, Map<XmlSchemaGenerator<T, C, F, M>.Namespace, String> map) throws IOException {
            try {
                Schema schema = (Schema) TXW.create(Schema.class, ResultFactory.createSerializer(result));
                Map<String, String> xmlNs = XmlSchemaGenerator.this.types.getXmlNs(this.uri);
                for (Map.Entry<String, String> entry : xmlNs.entrySet()) {
                    schema._namespace(entry.getValue(), entry.getKey());
                }
                if (this.useSwaRef) {
                    schema._namespace(WellKnownNamespace.SWA_URI, "swaRef");
                }
                if (this.useMimeNs) {
                    schema._namespace(WellKnownNamespace.XML_MIME_URI, "xmime");
                }
                Form form = Form.get(XmlSchemaGenerator.this.types.getAttributeFormDefault(this.uri));
                this.attributeFormDefault = form;
                form.declare("attributeFormDefault", schema);
                Form form2 = Form.get(XmlSchemaGenerator.this.types.getElementFormDefault(this.uri));
                this.elementFormDefault = form2;
                form2.declare("elementFormDefault", schema);
                if (!xmlNs.containsValue(WellKnownNamespace.XML_SCHEMA) && !xmlNs.containsKey("xs")) {
                    schema._namespace(WellKnownNamespace.XML_SCHEMA, "xs");
                }
                schema.version(BuildConfig.VERSION_NAME);
                if (this.uri.length() != 0) {
                    schema.targetNamespace(this.uri);
                }
                Iterator<XmlSchemaGenerator<T, C, F, M>.Namespace> it = this.depends.iterator();
                while (it.hasNext()) {
                    schema._namespace(it.next().uri);
                }
                if (this.selfReference && this.uri.length() != 0) {
                    schema._namespace(this.uri, "tns");
                }
                schema._pcdata(XmlSchemaGenerator.newline);
                for (XmlSchemaGenerator<T, C, F, M>.Namespace namespace : this.depends) {
                    Import import_import = schema._import();
                    if (namespace.uri.length() != 0) {
                        import_import.namespace(namespace.uri);
                    }
                    String str = map.get(namespace);
                    if (str != null && !str.equals("")) {
                        import_import.schemaLocation(XmlSchemaGenerator.relativize(str, result.getSystemId()));
                    }
                    schema._pcdata(XmlSchemaGenerator.newline);
                }
                if (this.useSwaRef) {
                    schema._import().namespace(WellKnownNamespace.SWA_URI).schemaLocation("http://ws-i.org/profiles/basic/1.1/swaref.xsd");
                }
                if (this.useMimeNs) {
                    schema._import().namespace(WellKnownNamespace.XML_MIME_URI).schemaLocation(WellKnownNamespace.XML_MIME_URI);
                }
                for (Map.Entry<String, XmlSchemaGenerator<T, C, F, M>.Namespace.ElementDeclaration> entry2 : this.elementDecls.entrySet()) {
                    entry2.getValue().writeTo(entry2.getKey(), schema);
                    schema._pcdata(XmlSchemaGenerator.newline);
                }
                for (ClassInfo<T, C> classInfo : this.classes) {
                    if (classInfo.getTypeName() != null) {
                        if (this.uri.equals(classInfo.getTypeName().getNamespaceURI())) {
                            writeClass(classInfo, schema);
                        }
                        schema._pcdata(XmlSchemaGenerator.newline);
                    }
                }
                for (EnumLeafInfo<T, C> enumLeafInfo : this.enums) {
                    if (enumLeafInfo.getTypeName() != null) {
                        if (this.uri.equals(enumLeafInfo.getTypeName().getNamespaceURI())) {
                            writeEnum(enumLeafInfo, schema);
                        }
                        schema._pcdata(XmlSchemaGenerator.newline);
                    }
                }
                Iterator<ArrayInfo<T, C>> it2 = this.arrays.iterator();
                while (it2.hasNext()) {
                    writeArray(it2.next(), schema);
                    schema._pcdata(XmlSchemaGenerator.newline);
                }
                for (Map.Entry<String, AttributePropertyInfo<T, C>> entry3 : this.attributeDecls.entrySet()) {
                    TopLevelAttribute topLevelAttributeAttribute = schema.attribute();
                    topLevelAttributeAttribute.name(entry3.getKey());
                    if (entry3.getValue() == null) {
                        writeTypeRef(topLevelAttributeAttribute, XmlSchemaGenerator.this.stringType, "type");
                    } else {
                        writeAttributeTypeRef(entry3.getValue(), topLevelAttributeAttribute);
                    }
                    schema._pcdata(XmlSchemaGenerator.newline);
                }
                schema.commit();
            } catch (TxwException e) {
                XmlSchemaGenerator.logger.log(Level.INFO, e.getMessage(), (Throwable) e);
                throw new IOException(e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeTypeRef(TypeHost typeHost, NonElementRef<T, C> nonElementRef, String str) {
            int i = AnonymousClass2.$SwitchMap$com$sun$xml$bind$v2$model$core$ID[nonElementRef.getSource().id().ordinal()];
            if (i == 1) {
                typeHost._attribute(str, new QName(WellKnownNamespace.XML_SCHEMA, "ID"));
                return;
            }
            if (i == 2) {
                typeHost._attribute(str, new QName(WellKnownNamespace.XML_SCHEMA, "IDREF"));
                return;
            }
            if (i != 3) {
                throw new IllegalStateException();
            }
            MimeType expectedMimeType = nonElementRef.getSource().getExpectedMimeType();
            if (expectedMimeType != null) {
                typeHost._attribute(new QName(WellKnownNamespace.XML_MIME_URI, "expectedContentTypes", "xmime"), expectedMimeType.toString());
            }
            if (XmlSchemaGenerator.this.generateSwaRefAdapter(nonElementRef)) {
                typeHost._attribute(str, new QName(WellKnownNamespace.SWA_URI, "swaRef", "ref"));
            } else if (nonElementRef.getSource().getSchemaType() != null) {
                typeHost._attribute(str, nonElementRef.getSource().getSchemaType());
            } else {
                writeTypeRef(typeHost, nonElementRef.getTarget(), str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void writeTypeRef(com.sun.xml.bind.v2.schemagen.xmlschema.TypeHost r5, com.sun.xml.bind.v2.model.core.NonElement<T, C> r6, java.lang.String r7) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.sun.xml.bind.v2.model.core.MaybeElement
                r1 = 0
                if (r0 == 0) goto L13
                r0 = r6
                com.sun.xml.bind.v2.model.core.MaybeElement r0 = (com.sun.xml.bind.v2.model.core.MaybeElement) r0
                boolean r2 = r0.isElement()
                if (r2 == 0) goto L13
                com.sun.xml.bind.v2.model.core.Element r0 = r0.asElement()
                goto L14
            L13:
                r0 = r1
            L14:
                boolean r2 = r6 instanceof com.sun.xml.bind.v2.model.core.Element
                if (r2 == 0) goto L1b
                r0 = r6
                com.sun.xml.bind.v2.model.core.Element r0 = (com.sun.xml.bind.v2.model.core.Element) r0
            L1b:
                javax.xml.namespace.QName r2 = r6.getTypeName()
                if (r2 != 0) goto L8d
                if (r0 == 0) goto L3e
                javax.xml.namespace.QName r7 = r0.getElementName()
                if (r7 == 0) goto L3e
                r5.block()
                boolean r7 = r6 instanceof com.sun.xml.bind.v2.model.core.ClassInfo
                if (r7 == 0) goto L36
                com.sun.xml.bind.v2.model.core.ClassInfo r6 = (com.sun.xml.bind.v2.model.core.ClassInfo) r6
                r4.writeClass(r6, r5)
                goto L94
            L36:
                com.sun.xml.bind.v2.model.core.EnumLeafInfo r6 = (com.sun.xml.bind.v2.model.core.EnumLeafInfo) r6
                com.sun.xml.bind.v2.schemagen.xmlschema.SimpleTypeHost r5 = (com.sun.xml.bind.v2.schemagen.xmlschema.SimpleTypeHost) r5
                r4.writeEnum(r6, r5)
                goto L94
            L3e:
                r5.block()
                boolean r7 = r6 instanceof com.sun.xml.bind.v2.model.core.ClassInfo
                if (r7 == 0) goto L85
                com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator r7 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.this
                com.sun.xml.bind.v2.util.CollisionCheckStack r7 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.access$1600(r7)
                com.sun.xml.bind.v2.model.core.ClassInfo r6 = (com.sun.xml.bind.v2.model.core.ClassInfo) r6
                boolean r7 = r7.push(r6)
                if (r7 == 0) goto L78
                com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator r5 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.this
                com.sun.xml.bind.api.ErrorListener r5 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.access$1700(r5)
                org.xml.sax.SAXParseException r6 = new org.xml.sax.SAXParseException
                com.sun.xml.bind.v2.schemagen.Messages r7 = com.sun.xml.bind.v2.schemagen.Messages.ANONYMOUS_TYPE_CYCLE
                r0 = 1
                java.lang.Object[] r0 = new java.lang.Object[r0]
                com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator r2 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.this
                com.sun.xml.bind.v2.util.CollisionCheckStack r2 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.access$1600(r2)
                java.lang.String r2 = r2.getCycleString()
                r3 = 0
                r0[r3] = r2
                java.lang.String r7 = r7.format(r0)
                r6.<init>(r7, r1)
                r5.warning(r6)
                goto L7b
            L78:
                r4.writeClass(r6, r5)
            L7b:
                com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator r5 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.this
                com.sun.xml.bind.v2.util.CollisionCheckStack r5 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.access$1600(r5)
                r5.pop()
                goto L94
            L85:
                com.sun.xml.bind.v2.model.core.EnumLeafInfo r6 = (com.sun.xml.bind.v2.model.core.EnumLeafInfo) r6
                com.sun.xml.bind.v2.schemagen.xmlschema.SimpleTypeHost r5 = (com.sun.xml.bind.v2.schemagen.xmlschema.SimpleTypeHost) r5
                r4.writeEnum(r6, r5)
                goto L94
            L8d:
                javax.xml.namespace.QName r6 = r6.getTypeName()
                r5._attribute(r7, r6)
            L94:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.writeTypeRef(com.sun.xml.bind.v2.schemagen.xmlschema.TypeHost, com.sun.xml.bind.v2.model.core.NonElement, java.lang.String):void");
        }

        private void writeArray(ArrayInfo<T, C> arrayInfo, Schema schema) {
            ComplexType complexTypeName = schema.complexType().name(arrayInfo.getTypeName().getLocalPart());
            complexTypeName._final("#all");
            LocalElement localElementName = complexTypeName.sequence().element().name("item");
            localElementName.type(arrayInfo.getItemType().getTypeName());
            localElementName.minOccurs(0).maxOccurs("unbounded");
            localElementName.nillable(true);
            complexTypeName.commit();
        }

        private void writeEnum(EnumLeafInfo<T, C> enumLeafInfo, SimpleTypeHost simpleTypeHost) {
            SimpleType simpleType = simpleTypeHost.simpleType();
            writeName(enumLeafInfo, simpleType);
            SimpleRestriction simpleRestrictionRestriction = simpleType.restriction();
            writeTypeRef(simpleRestrictionRestriction, enumLeafInfo.getBaseType(), "base");
            Iterator<? extends EnumConstant> it = enumLeafInfo.getConstants().iterator();
            while (it.hasNext()) {
                simpleRestrictionRestriction.enumeration().value(it.next().getLexicalValue());
            }
            simpleType.commit();
        }

        private void writeClass(ClassInfo<T, C> classInfo, TypeHost typeHost) {
            AttrDecls attrDecls;
            ComplexExtension complexExtension;
            if (this.written.contains(classInfo)) {
                return;
            }
            this.written.add(classInfo);
            if (containsValueProp(classInfo)) {
                if (classInfo.getProperties().size() == 1) {
                    ValuePropertyInfo valuePropertyInfo = (ValuePropertyInfo) classInfo.getProperties().get(0);
                    SimpleType simpleType = ((SimpleTypeHost) typeHost).simpleType();
                    writeName(classInfo, simpleType);
                    if (valuePropertyInfo.isCollection()) {
                        writeTypeRef(simpleType.list(), valuePropertyInfo.getTarget(), "itemType");
                        return;
                    } else {
                        writeTypeRef(simpleType.restriction(), valuePropertyInfo.getTarget(), "base");
                        return;
                    }
                }
                ComplexType complexType = ((ComplexTypeHost) typeHost).complexType();
                writeName(classInfo, complexType);
                if (classInfo.isFinal()) {
                    complexType._final("extension restriction");
                }
                SimpleExtension simpleExtensionExtension = complexType.simpleContent().extension();
                simpleExtensionExtension.block();
                for (PropertyInfo<T, C> propertyInfo : classInfo.getProperties()) {
                    int i = AnonymousClass2.$SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[propertyInfo.kind().ordinal()];
                    if (i == 1) {
                        handleAttributeProp((AttributePropertyInfo) propertyInfo, simpleExtensionExtension);
                    } else if (i == 2) {
                        TODO.checkSpec("what if vp.isCollection() == true?");
                        simpleExtensionExtension.base(((ValuePropertyInfo) propertyInfo).getTarget().getTypeName());
                    } else {
                        throw new IllegalStateException();
                    }
                }
                simpleExtensionExtension.commit();
                TODO.schemaGenerator("figure out what to do if bc != null");
                TODO.checkSpec("handle sec 8.9.5.2, bullet #4");
                return;
            }
            ComplexType complexType2 = ((ComplexTypeHost) typeHost).complexType();
            writeName(classInfo, complexType2);
            if (classInfo.isFinal()) {
                complexType2._final("extension restriction");
            }
            if (classInfo.isAbstract()) {
                complexType2._abstract(true);
            }
            ClassInfo<T, C> baseClass2 = classInfo.getBaseClass2();
            if (baseClass2 == null) {
                ComplexType complexType3 = complexType2;
                attrDecls = complexType3;
                complexExtension = complexType3;
            } else if (baseClass2.hasValueProperty()) {
                SimpleExtension simpleExtensionExtension2 = complexType2.simpleContent().extension();
                simpleExtensionExtension2.base(baseClass2.getTypeName());
                complexExtension = null;
                attrDecls = simpleExtensionExtension2;
            } else {
                ComplexExtension complexExtensionExtension = complexType2.complexContent().extension();
                complexExtensionExtension.base(baseClass2.getTypeName());
                complexExtension = complexExtensionExtension;
                attrDecls = complexExtensionExtension;
            }
            if (complexExtension != null) {
                ArrayList arrayList = new ArrayList();
                for (PropertyInfo<T, C> propertyInfo2 : classInfo.getProperties()) {
                    if ((propertyInfo2 instanceof ReferencePropertyInfo) && ((ReferencePropertyInfo) propertyInfo2).isMixed()) {
                        complexType2.mixed(true);
                    }
                    Tree treeBuildPropertyContentModel = buildPropertyContentModel(propertyInfo2);
                    if (treeBuildPropertyContentModel != null) {
                        arrayList.add(treeBuildPropertyContentModel);
                    }
                }
                Tree.makeGroup(classInfo.isOrdered() ? GroupKind.SEQUENCE : GroupKind.ALL, arrayList).write(complexExtension);
            }
            for (PropertyInfo<T, C> propertyInfo3 : classInfo.getProperties()) {
                if (propertyInfo3 instanceof AttributePropertyInfo) {
                    handleAttributeProp((AttributePropertyInfo) propertyInfo3, attrDecls);
                }
            }
            if (classInfo.hasAttributeWildcard()) {
                attrDecls.anyAttribute().namespace("##other").processContents("skip");
            }
            complexType2.commit();
        }

        private void writeName(NonElement<T, C> nonElement, TypedXmlWriter typedXmlWriter) {
            QName typeName = nonElement.getTypeName();
            if (typeName != null) {
                typedXmlWriter._attribute("name", typeName.getLocalPart());
            }
        }

        private boolean containsValueProp(ClassInfo<T, C> classInfo) {
            Iterator<? extends PropertyInfo<T, C>> it = classInfo.getProperties().iterator();
            while (it.hasNext()) {
                if (it.next() instanceof ValuePropertyInfo) {
                    return true;
                }
            }
            return false;
        }

        private Tree buildPropertyContentModel(PropertyInfo<T, C> propertyInfo) {
            int i = AnonymousClass2.$SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[propertyInfo.kind().ordinal()];
            if (i == 1) {
                return null;
            }
            if (i == 2) {
                throw new IllegalStateException();
            }
            if (i == 3) {
                return handleElementProp((ElementPropertyInfo) propertyInfo);
            }
            if (i == 4) {
                return handleReferenceProp((ReferencePropertyInfo) propertyInfo);
            }
            if (i == 5) {
                return handleMapProp((MapPropertyInfo) propertyInfo);
            }
            throw new IllegalStateException();
        }

        private Tree handleElementProp(final ElementPropertyInfo<T, C> elementPropertyInfo) {
            if (elementPropertyInfo.isValueList()) {
                return new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.1
                    @Override // com.sun.xml.bind.v2.schemagen.Tree
                    protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                        TypeRef<T, C> typeRef = elementPropertyInfo.getTypes().get(0);
                        LocalElement localElementElement = contentModelContainer.element();
                        localElementElement.block();
                        QName tagName = typeRef.getTagName();
                        localElementElement.name(tagName.getLocalPart());
                        Namespace.this.writeTypeRef(localElementElement.simpleType().list(), typeRef, "itemType");
                        Namespace.this.elementFormDefault.writeForm(localElementElement, tagName);
                        writeOccurs(localElementElement, z || !elementPropertyInfo.isRequired(), z2);
                    }
                };
            }
            ArrayList arrayList = new ArrayList();
            for (final TypeRef<T, C> typeRef : elementPropertyInfo.getTypes()) {
                arrayList.add(new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.2
                    @Override // com.sun.xml.bind.v2.schemagen.Tree
                    protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                        LocalElement localElementElement = contentModelContainer.element();
                        QName tagName = typeRef.getTagName();
                        PropertyInfo<T, C> source = typeRef.getSource();
                        ClassInfoImpl classInfoImpl = null;
                        if (Namespace.this.canBeDirectElementRef(typeRef, tagName, source == null ? null : source.parent())) {
                            if (!typeRef.getTarget().isSimpleType() && (typeRef.getTarget() instanceof ClassInfo) && XmlSchemaGenerator.this.collisionChecker.findDuplicate((ClassInfo) typeRef.getTarget())) {
                                localElementElement.ref(new QName(Namespace.this.uri, tagName.getLocalPart()));
                            } else {
                                QName elementName = typeRef.getTarget() instanceof Element ? ((Element) typeRef.getTarget()).getElementName() : null;
                                Collection<? extends TypeInfo<T, C>> collectionRef = source.ref();
                                if (collectionRef != null && !collectionRef.isEmpty() && elementName != null) {
                                    Iterator<? extends TypeInfo<T, C>> it = collectionRef.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        TypeInfo<T, C> next = it.next();
                                        if (next == null || (next instanceof ClassInfoImpl)) {
                                            ClassInfoImpl classInfoImpl2 = (ClassInfoImpl) next;
                                            if (elementName.equals(classInfoImpl2.getElementName())) {
                                                classInfoImpl = classInfoImpl2;
                                                break;
                                            }
                                        }
                                    }
                                    if (classInfoImpl != null) {
                                        if (tagName.getNamespaceURI() != null && tagName.getNamespaceURI().trim().length() != 0) {
                                            localElementElement.ref(new QName(tagName.getNamespaceURI(), tagName.getLocalPart()));
                                        } else {
                                            localElementElement.ref(new QName(classInfoImpl.getElementName().getNamespaceURI(), tagName.getLocalPart()));
                                        }
                                    } else {
                                        localElementElement.ref(new QName("", tagName.getLocalPart()));
                                    }
                                } else {
                                    localElementElement.ref(tagName);
                                }
                            }
                        } else {
                            localElementElement.name(tagName.getLocalPart());
                            Namespace.this.writeTypeRef(localElementElement, typeRef, "type");
                            Namespace.this.elementFormDefault.writeForm(localElementElement, tagName);
                        }
                        if (typeRef.isNillable()) {
                            localElementElement.nillable(true);
                        }
                        if (typeRef.getDefaultValue() != null) {
                            localElementElement._default(typeRef.getDefaultValue());
                        }
                        writeOccurs(localElementElement, z, z2);
                    }
                });
            }
            final Tree treeMakeRepeated = Tree.makeGroup(GroupKind.CHOICE, arrayList).makeOptional(!elementPropertyInfo.isRequired()).makeRepeated(elementPropertyInfo.isCollection());
            final QName xmlName = elementPropertyInfo.getXmlName();
            return xmlName != null ? new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.3
                @Override // com.sun.xml.bind.v2.schemagen.Tree
                protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                    LocalElement localElementElement = contentModelContainer.element();
                    if (xmlName.getNamespaceURI().length() > 0 && !xmlName.getNamespaceURI().equals(Namespace.this.uri)) {
                        localElementElement.ref(new QName(xmlName.getNamespaceURI(), xmlName.getLocalPart()));
                        return;
                    }
                    localElementElement.name(xmlName.getLocalPart());
                    Namespace.this.elementFormDefault.writeForm(localElementElement, xmlName);
                    if (elementPropertyInfo.isCollectionNillable()) {
                        localElementElement.nillable(true);
                    }
                    writeOccurs(localElementElement, !elementPropertyInfo.isCollectionRequired(), z2);
                    treeMakeRepeated.write(localElementElement.complexType());
                }
            } : treeMakeRepeated;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean canBeDirectElementRef(TypeRef<T, C> typeRef, QName qName, TypeInfo typeInfo) {
            ClassInfo classInfo;
            QName elementName;
            if (typeRef.isNillable() || typeRef.getDefaultValue() != null) {
                return false;
            }
            Element element = null;
            if (typeRef.getTarget() instanceof Element) {
                Element element2 = (Element) typeRef.getTarget();
                elementName = element2.getElementName();
                element = element2;
                classInfo = element2 instanceof ClassInfo ? (ClassInfo) element2 : null;
            } else {
                classInfo = null;
                elementName = null;
            }
            String namespaceURI = qName.getNamespaceURI();
            if (!namespaceURI.equals(this.uri) && namespaceURI.length() > 0 && (!(typeInfo instanceof ClassInfo) || ((ClassInfo) typeInfo).getTypeName() != null)) {
                return true;
            }
            if (classInfo != null && elementName != null && element.getScope() == null && elementName.getNamespaceURI() == null && elementName.equals(qName)) {
                return true;
            }
            return (element == null || elementName == null || !elementName.equals(qName)) ? false : true;
        }

        private void handleAttributeProp(AttributePropertyInfo<T, C> attributePropertyInfo, AttrDecls attrDecls) {
            LocalAttribute localAttributeAttribute = attrDecls.attribute();
            if (attributePropertyInfo.getXmlName().getNamespaceURI().equals("")) {
                localAttributeAttribute.name(attributePropertyInfo.getXmlName().getLocalPart());
                writeAttributeTypeRef(attributePropertyInfo, localAttributeAttribute);
                this.attributeFormDefault.writeForm(localAttributeAttribute, attributePropertyInfo.getXmlName());
            } else {
                localAttributeAttribute.ref(attributePropertyInfo.getXmlName());
            }
            if (attributePropertyInfo.isRequired()) {
                localAttributeAttribute.use("required");
            }
        }

        private void writeAttributeTypeRef(AttributePropertyInfo<T, C> attributePropertyInfo, AttributeType attributeType) {
            if (attributePropertyInfo.isCollection()) {
                writeTypeRef(attributeType.simpleType().list(), attributePropertyInfo, "itemType");
            } else {
                writeTypeRef(attributeType, attributePropertyInfo, "type");
            }
        }

        private Tree handleReferenceProp(final ReferencePropertyInfo<T, C> referencePropertyInfo) {
            ArrayList arrayList = new ArrayList();
            for (final Element<T, C> element : referencePropertyInfo.getElements()) {
                arrayList.add(new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.4
                    /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
                    @Override // com.sun.xml.bind.v2.schemagen.Tree
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    protected void write(com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer r5, boolean r6, boolean r7) {
                        /*
                            r4 = this;
                            com.sun.xml.bind.v2.schemagen.xmlschema.LocalElement r5 = r5.element()
                            com.sun.xml.bind.v2.model.core.Element r0 = r2
                            javax.xml.namespace.QName r0 = r0.getElementName()
                            com.sun.xml.bind.v2.model.core.Element r1 = r2
                            com.sun.xml.bind.v2.model.core.ClassInfo r1 = r1.getScope()
                            if (r1 == 0) goto L71
                            java.lang.String r1 = r0.getNamespaceURI()
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$Namespace r2 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.this
                            java.lang.String r2 = r2.uri
                            boolean r1 = r1.equals(r2)
                            java.lang.String r2 = r0.getNamespaceURI()
                            java.lang.String r3 = ""
                            boolean r2 = r2.equals(r3)
                            if (r1 != 0) goto L2c
                            if (r2 == 0) goto L71
                        L2c:
                            if (r2 == 0) goto L3e
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$Namespace r1 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.this
                            com.sun.xml.bind.v2.schemagen.Form r1 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.access$1900(r1)
                            boolean r1 = r1.isEffectivelyQualified
                            if (r1 == 0) goto L4d
                            java.lang.String r1 = "unqualified"
                            r5.form(r1)
                            goto L4d
                        L3e:
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$Namespace r1 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.this
                            com.sun.xml.bind.v2.schemagen.Form r1 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.access$1900(r1)
                            boolean r1 = r1.isEffectivelyQualified
                            if (r1 != 0) goto L4d
                            java.lang.String r1 = "qualified"
                            r5.form(r1)
                        L4d:
                            java.lang.String r1 = r0.getLocalPart()
                            r5.name(r1)
                            com.sun.xml.bind.v2.model.core.Element r1 = r2
                            boolean r2 = r1 instanceof com.sun.xml.bind.v2.model.core.ClassInfo
                            java.lang.String r3 = "type"
                            if (r2 == 0) goto L64
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$Namespace r2 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.this
                            com.sun.xml.bind.v2.model.core.ClassInfo r1 = (com.sun.xml.bind.v2.model.core.ClassInfo) r1
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.access$2100(r2, r5, r1, r3)
                            goto L6f
                        L64:
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$Namespace r2 = com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.this
                            com.sun.xml.bind.v2.model.core.ElementInfo r1 = (com.sun.xml.bind.v2.model.core.ElementInfo) r1
                            com.sun.xml.bind.v2.model.core.NonElement r1 = r1.getContentType()
                            com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.access$2100(r2, r5, r1, r3)
                        L6f:
                            r1 = 1
                            goto L72
                        L71:
                            r1 = 0
                        L72:
                            if (r1 != 0) goto L77
                            r5.ref(r0)
                        L77:
                            r4.writeOccurs(r5, r6, r7)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.AnonymousClass4.write(com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer, boolean, boolean):void");
                    }
                });
            }
            final WildcardMode wildcard = referencePropertyInfo.getWildcard();
            if (wildcard != null) {
                arrayList.add(new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.5
                    @Override // com.sun.xml.bind.v2.schemagen.Tree
                    protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                        Any any = contentModelContainer.any();
                        String processContentsModeName = XmlSchemaGenerator.getProcessContentsModeName(wildcard);
                        if (processContentsModeName != null) {
                            any.processContents(processContentsModeName);
                        }
                        any.namespace("##other");
                        writeOccurs(any, z, z2);
                    }
                });
            }
            final Tree treeMakeOptional = Tree.makeGroup(GroupKind.CHOICE, arrayList).makeRepeated(referencePropertyInfo.isCollection()).makeOptional(!referencePropertyInfo.isRequired());
            final QName xmlName = referencePropertyInfo.getXmlName();
            return xmlName != null ? new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.6
                @Override // com.sun.xml.bind.v2.schemagen.Tree
                protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                    LocalElement localElementName = contentModelContainer.element().name(xmlName.getLocalPart());
                    Namespace.this.elementFormDefault.writeForm(localElementName, xmlName);
                    if (referencePropertyInfo.isCollectionNillable()) {
                        localElementName.nillable(true);
                    }
                    writeOccurs(localElementName, true, z2);
                    treeMakeOptional.write(localElementName.complexType());
                }
            } : treeMakeOptional;
        }

        private Tree handleMapProp(final MapPropertyInfo<T, C> mapPropertyInfo) {
            return new Tree.Term() { // from class: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.7
                @Override // com.sun.xml.bind.v2.schemagen.Tree
                protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
                    QName xmlName = mapPropertyInfo.getXmlName();
                    LocalElement localElementElement = contentModelContainer.element();
                    Namespace.this.elementFormDefault.writeForm(localElementElement, xmlName);
                    if (mapPropertyInfo.isCollectionNillable()) {
                        localElementElement.nillable(true);
                    }
                    LocalElement localElementName = localElementElement.name(xmlName.getLocalPart());
                    writeOccurs(localElementName, z, z2);
                    LocalElement localElementElement2 = localElementName.complexType().sequence().element();
                    localElementElement2.name("entry").minOccurs(0).maxOccurs("unbounded");
                    ExplicitGroup explicitGroupSequence = localElementElement2.complexType().sequence();
                    Namespace.this.writeKeyOrValue(explicitGroupSequence, "key", mapPropertyInfo.getKeyType());
                    Namespace.this.writeKeyOrValue(explicitGroupSequence, WuwInfoBean.ValueKey, mapPropertyInfo.getValueType());
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeKeyOrValue(ExplicitGroup explicitGroup, String str, NonElement<T, C> nonElement) {
            LocalElement localElementName = explicitGroup.element().name(str);
            localElementName.minOccurs(0);
            writeTypeRef(localElementName, nonElement, "type");
        }

        public void addGlobalAttribute(AttributePropertyInfo<T, C> attributePropertyInfo) {
            this.attributeDecls.put(attributePropertyInfo.getXmlName().getLocalPart(), attributePropertyInfo);
            addDependencyTo(attributePropertyInfo.getTarget().getTypeName());
        }

        public void addGlobalElement(TypeRef<T, C> typeRef) {
            this.elementDecls.put(typeRef.getTagName().getLocalPart(), new ElementWithType(false, typeRef.getTarget()));
            addDependencyTo(typeRef.getTarget().getTypeName());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[classes=");
            sb.append(this.classes);
            sb.append(",elementDecls=").append(this.elementDecls);
            sb.append(",enums=").append(this.enums);
            return super.toString();
        }

        abstract class ElementDeclaration {
            public abstract boolean equals(Object obj);

            public abstract int hashCode();

            public abstract void writeTo(String str, Schema schema);

            ElementDeclaration() {
            }
        }

        class ElementWithType extends XmlSchemaGenerator<T, C, F, M>.Namespace.ElementDeclaration {
            private final boolean nillable;
            private final NonElement<T, C> type;

            public ElementWithType(boolean z, NonElement<T, C> nonElement) {
                super();
                this.type = nonElement;
                this.nillable = z;
            }

            @Override // com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.ElementDeclaration
            public void writeTo(String str, Schema schema) {
                TopLevelElement topLevelElementName = schema.element().name(str);
                if (this.nillable) {
                    topLevelElementName.nillable(true);
                }
                NonElement<T, C> nonElement = this.type;
                if (nonElement != null) {
                    Namespace.this.writeTypeRef(topLevelElementName, nonElement, "type");
                } else {
                    topLevelElementName.complexType();
                }
                topLevelElementName.commit();
            }

            @Override // com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.ElementDeclaration
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                return this.type.equals(((ElementWithType) obj).type);
            }

            @Override // com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator.Namespace.ElementDeclaration
            public int hashCode() {
                return this.type.hashCode();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean generateSwaRefAdapter(NonElementRef<T, C> nonElementRef) {
        return generateSwaRefAdapter(nonElementRef.getSource());
    }

    private boolean generateSwaRefAdapter(PropertyInfo<T, C> propertyInfo) {
        C cAsDecl;
        Adapter<T, C> adapter = propertyInfo.getAdapter();
        if (adapter == null || (cAsDecl = this.navigator.asDecl(SwaRefAdapter.class)) == null) {
            return false;
        }
        return cAsDecl.equals(adapter.adapterType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (XmlSchemaGenerator<T, C, F, M>.Namespace namespace : this.namespaces.values()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(namespace.uri).append(SignatureVisitor.INSTANCEOF).append(namespace);
        }
        return super.toString() + TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + ((Object) sb) + ']';
    }

    /* JADX INFO: renamed from: com.sun.xml.bind.v2.schemagen.XmlSchemaGenerator$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$core$ID;
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$xml$bind$v2$model$core$WildcardMode;

        static {
            int[] iArr = new int[WildcardMode.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$core$WildcardMode = iArr;
            try {
                iArr[WildcardMode.LAX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$WildcardMode[WildcardMode.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$WildcardMode[WildcardMode.STRICT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[PropertyKind.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind = iArr2;
            try {
                iArr2[PropertyKind.ATTRIBUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.ELEMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$PropertyKind[PropertyKind.MAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[ID.values().length];
            $SwitchMap$com$sun$xml$bind$v2$model$core$ID = iArr3;
            try {
                iArr3[ID.ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$ID[ID.IDREF.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$xml$bind$v2$model$core$ID[ID.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getProcessContentsModeName(WildcardMode wildcardMode) {
        int i = AnonymousClass2.$SwitchMap$com$sun$xml$bind$v2$model$core$WildcardMode[wildcardMode.ordinal()];
        if (i == 1 || i == 2) {
            return wildcardMode.name().toLowerCase();
        }
        if (i == 3) {
            return null;
        }
        throw new IllegalStateException();
    }

    protected static String relativize(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        try {
            URI uri = new URI(Util.escapeURI(str));
            URI uri2 = new URI(Util.escapeURI(str2));
            if (!uri.isOpaque() && !uri2.isOpaque() && Util.equalsIgnoreCase(uri.getScheme(), uri2.getScheme()) && Util.equal(uri.getAuthority(), uri2.getAuthority())) {
                String path = uri.getPath();
                String path2 = uri2.getPath();
                if (!path2.endsWith("/")) {
                    path2 = Util.normalizeUriPath(path2);
                }
                if (path.equals(path2)) {
                    return ".";
                }
                String strCalculateRelativePath = calculateRelativePath(path, path2, fixNull(uri.getScheme()).equals("file"));
                if (strCalculateRelativePath == null) {
                    return str;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(strCalculateRelativePath);
                if (uri.getQuery() != null) {
                    sb.append('?').append(uri.getQuery());
                }
                if (uri.getFragment() != null) {
                    sb.append('#').append(uri.getFragment());
                }
                return sb.toString();
            }
            return str;
        } catch (URISyntaxException unused) {
            throw new InternalError("Error escaping one of these uris:\n\t" + str + "\n\t" + str2);
        }
    }

    private static String calculateRelativePath(String str, String str2, boolean z) {
        boolean z2 = File.pathSeparatorChar == ';';
        if (str2 == null) {
            return null;
        }
        if ((z && z2 && startsWithIgnoreCase(str, str2)) || str.startsWith(str2)) {
            return str.substring(str2.length());
        }
        return "../" + calculateRelativePath(str, Util.getParentUriPath(str2), z);
    }

    private static boolean startsWithIgnoreCase(String str, String str2) {
        return str.toUpperCase().startsWith(str2.toUpperCase());
    }
}
