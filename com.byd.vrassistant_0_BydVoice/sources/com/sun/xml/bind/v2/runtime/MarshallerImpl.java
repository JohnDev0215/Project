package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.DataWriter;
import com.sun.xml.bind.marshaller.DumbEscapeHandler;
import com.sun.xml.bind.marshaller.MinimumEscapeHandler;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.marshaller.NioEscapeHandler;
import com.sun.xml.bind.marshaller.SAX2DOMEx;
import com.sun.xml.bind.marshaller.XMLWriter;
import com.sun.xml.bind.v2.runtime.output.C14nXmlOutput;
import com.sun.xml.bind.v2.runtime.output.Encoded;
import com.sun.xml.bind.v2.runtime.output.ForkXmlOutput;
import com.sun.xml.bind.v2.runtime.output.IndentingUTF8XmlOutput;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput;
import com.sun.xml.bind.v2.runtime.output.XMLEventWriterOutput;
import com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput;
import com.sun.xml.bind.v2.runtime.output.XmlOutput;
import com.sun.xml.bind.v2.util.FatalAdapter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.helpers.AbstractMarshallerImpl;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.ValidatorHandler;
import org.apache.commons.compress.utils.CharsetNames;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class MarshallerImpl extends AbstractMarshallerImpl implements ValidationEventHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final String C14N = "com.sun.xml.bind.c14n";
    protected static final String ENCODING_HANDLER = "com.sun.xml.bind.characterEscapeHandler";
    protected static final String ENCODING_HANDLER2 = "com.sun.xml.bind.marshaller.CharacterEscapeHandler";
    protected static final String INDENT_STRING = "com.sun.xml.bind.indentString";
    private static final Logger LOGGER = Logger.getLogger(MarshallerImpl.class.getName());
    protected static final String OBJECT_IDENTITY_CYCLE_DETECTION = "com.sun.xml.bind.objectIdentitityCycleDetection";
    protected static final String PREFIX_MAPPER = "com.sun.xml.bind.namespacePrefixMapper";
    protected static final String XMLDECLARATION = "com.sun.xml.bind.xmlDeclaration";
    protected static final String XML_HEADERS = "com.sun.xml.bind.xmlHeaders";
    private boolean c14nSupport;
    final JAXBContextImpl context;
    private Schema schema;
    private Closeable toBeClosed;
    private Flushable toBeFlushed;
    private String indent = "    ";
    private NamespacePrefixMapper prefixMapper = null;
    private CharacterEscapeHandler escapeHandler = null;
    private String header = null;
    private Marshaller.Listener externalListener = null;
    protected final XMLSerializer serializer = new XMLSerializer(this);

    @Override // javax.xml.bind.ValidationEventHandler
    public boolean handleEvent(ValidationEvent validationEvent) {
        return false;
    }

    public MarshallerImpl(JAXBContextImpl jAXBContextImpl, AssociationMap associationMap) {
        this.context = jAXBContextImpl;
        this.c14nSupport = jAXBContextImpl.c14nSupport;
        try {
            setEventHandler(this);
        } catch (JAXBException e) {
            throw new AssertionError(e);
        }
    }

    public JAXBContextImpl getContext() {
        return this.context;
    }

    public void marshal(Object obj, OutputStream outputStream, NamespaceContext namespaceContext) throws JAXBException {
        write(obj, createWriter(outputStream), new StAXPostInitAction(namespaceContext, this.serializer));
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void marshal(Object obj, XMLStreamWriter xMLStreamWriter) throws JAXBException {
        write(obj, XMLStreamWriterOutput.create(xMLStreamWriter, this.context, this.escapeHandler), new StAXPostInitAction(xMLStreamWriter, this.serializer));
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void marshal(Object obj, XMLEventWriter xMLEventWriter) throws JAXBException {
        write(obj, new XMLEventWriterOutput(xMLEventWriter), new StAXPostInitAction(xMLEventWriter, this.serializer));
    }

    public void marshal(Object obj, XmlOutput xmlOutput) throws JAXBException {
        write(obj, xmlOutput, null);
    }

    final XmlOutput createXmlOutput(Result result) throws JAXBException {
        if (result instanceof SAXResult) {
            return new SAXOutput(((SAXResult) result).getHandler());
        }
        if (result instanceof DOMResult) {
            DOMResult dOMResult = (DOMResult) result;
            Node node = dOMResult.getNode();
            if (node == null) {
                Document documentCreateDom = JAXBContextImpl.createDom(getContext().disableSecurityProcessing);
                dOMResult.setNode(documentCreateDom);
                return new SAXOutput(new SAX2DOMEx(documentCreateDom));
            }
            return new SAXOutput(new SAX2DOMEx(node));
        }
        if (result instanceof StreamResult) {
            StreamResult streamResult = (StreamResult) result;
            if (streamResult.getWriter() != null) {
                return createWriter(streamResult.getWriter());
            }
            if (streamResult.getOutputStream() != null) {
                return createWriter(streamResult.getOutputStream());
            }
            if (streamResult.getSystemId() != null) {
                String systemId = streamResult.getSystemId();
                try {
                    systemId = new URI(systemId).getPath();
                } catch (URISyntaxException unused) {
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(systemId);
                    this.toBeClosed = fileOutputStream;
                    return createWriter(fileOutputStream);
                } catch (IOException e) {
                    throw new MarshalException(e);
                }
            }
        }
        throw new MarshalException(Messages.UNSUPPORTED_RESULT.format(new Object[0]));
    }

    final Runnable createPostInitAction(Result result) {
        if (result instanceof DOMResult) {
            return new DomPostInitAction(((DOMResult) result).getNode(), this.serializer);
        }
        return null;
    }

    @Override // javax.xml.bind.Marshaller
    public void marshal(Object obj, Result result) throws JAXBException {
        write(obj, createXmlOutput(result), createPostInitAction(result));
    }

    protected final <T> void write(Name name, JaxBeanInfo<T> jaxBeanInfo, T t, XmlOutput xmlOutput, Runnable runnable) throws JAXBException {
        try {
            try {
                try {
                    try {
                        prewrite(xmlOutput, true, runnable);
                        this.serializer.startElement(name, null);
                        if (jaxBeanInfo.jaxbType == Void.class || jaxBeanInfo.jaxbType == Void.TYPE) {
                            this.serializer.endNamespaceDecls(null);
                            this.serializer.endAttributes();
                        } else if (t == null) {
                            this.serializer.writeXsiNilTrue();
                        } else {
                            this.serializer.childAsXsiType(t, "root", jaxBeanInfo, false);
                        }
                        this.serializer.endElement();
                        postwrite();
                    } catch (SAXException e) {
                        throw new MarshalException(e);
                    } catch (XMLStreamException e2) {
                        throw new MarshalException((Throwable) e2);
                    }
                } catch (IOException e3) {
                    throw new MarshalException(e3);
                }
            } finally {
                cleanUp();
            }
        } finally {
            this.serializer.close();
        }
    }

    private void write(Object obj, XmlOutput xmlOutput, Runnable runnable) throws JAXBException {
        try {
            if (obj == null) {
                throw new IllegalArgumentException(Messages.NOT_MARSHALLABLE.format(new Object[0]));
            }
            Schema schema = this.schema;
            if (schema != null) {
                ValidatorHandler validatorHandlerNewValidatorHandler = schema.newValidatorHandler();
                validatorHandlerNewValidatorHandler.setErrorHandler(new FatalAdapter(this.serializer));
                XMLFilterImpl xMLFilterImpl = new XMLFilterImpl() { // from class: com.sun.xml.bind.v2.runtime.MarshallerImpl.1
                    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
                    public void startPrefixMapping(String str, String str2) throws SAXException {
                        super.startPrefixMapping(str.intern(), str2.intern());
                    }
                };
                xMLFilterImpl.setContentHandler(validatorHandlerNewValidatorHandler);
                xmlOutput = new ForkXmlOutput(new SAXOutput(xMLFilterImpl) { // from class: com.sun.xml.bind.v2.runtime.MarshallerImpl.2
                    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
                    @Override // com.sun.xml.bind.v2.runtime.output.SAXOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
                    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
                        super.startDocument(xMLSerializer, false, iArr, namespaceContextImpl);
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
                    @Override // com.sun.xml.bind.v2.runtime.output.SAXOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
                    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
                        super.endDocument(false);
                    }
                }, xmlOutput);
            }
            try {
                try {
                    prewrite(xmlOutput, isFragment(), runnable);
                    this.serializer.childAsRoot(obj);
                    postwrite();
                    cleanUp();
                } catch (IOException e) {
                    throw new MarshalException(e);
                } catch (SAXException e2) {
                    throw new MarshalException(e2);
                } catch (XMLStreamException e3) {
                    throw new MarshalException((Throwable) e3);
                }
            } finally {
                this.serializer.close();
            }
        } catch (Throwable th) {
            cleanUp();
            throw th;
        }
    }

    private void cleanUp() {
        Flushable flushable = this.toBeFlushed;
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), (Throwable) e);
            }
        }
        Closeable closeable = this.toBeClosed;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                LOGGER.log(Level.SEVERE, e2.getMessage(), (Throwable) e2);
            }
        }
        this.toBeFlushed = null;
        this.toBeClosed = null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void prewrite(XmlOutput xmlOutput, boolean z, Runnable runnable) throws SAXException, XMLStreamException, IOException {
        String[] contextualNamespaceDecls;
        this.serializer.startDocument(xmlOutput, z, getSchemaLocation(), getNoNSSchemaLocation());
        if (runnable != null) {
            runnable.run();
        }
        NamespacePrefixMapper namespacePrefixMapper = this.prefixMapper;
        if (namespacePrefixMapper != null && (contextualNamespaceDecls = namespacePrefixMapper.getContextualNamespaceDecls()) != null) {
            for (int i = 0; i < contextualNamespaceDecls.length; i += 2) {
                String str = contextualNamespaceDecls[i];
                String str2 = contextualNamespaceDecls[i + 1];
                if (str2 != null && str != null) {
                    this.serializer.addInscopeBinding(str2, str);
                }
            }
        }
        this.serializer.setPrefixMapper(this.prefixMapper);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void postwrite() throws SAXException, XMLStreamException, IOException {
        this.serializer.endDocument();
        this.serializer.reconcileID();
    }

    CharacterEscapeHandler getEscapeHandler() {
        return this.escapeHandler;
    }

    protected CharacterEscapeHandler createEscapeHandler(String str) {
        CharacterEscapeHandler characterEscapeHandler = this.escapeHandler;
        if (characterEscapeHandler != null) {
            return characterEscapeHandler;
        }
        if (str.startsWith("UTF")) {
            return MinimumEscapeHandler.theInstance;
        }
        try {
            return new NioEscapeHandler(getJavaEncoding(str));
        } catch (Throwable unused) {
            return DumbEscapeHandler.theInstance;
        }
    }

    public XmlOutput createWriter(Writer writer, String str) {
        XMLWriter xMLWriter;
        if (!(writer instanceof BufferedWriter)) {
            writer = new BufferedWriter(writer);
        }
        this.toBeFlushed = writer;
        CharacterEscapeHandler characterEscapeHandlerCreateEscapeHandler = createEscapeHandler(str);
        if (isFormattedOutput()) {
            DataWriter dataWriter = new DataWriter(writer, str, characterEscapeHandlerCreateEscapeHandler);
            dataWriter.setIndentStep(this.indent);
            xMLWriter = dataWriter;
        } else {
            xMLWriter = new XMLWriter(writer, str, characterEscapeHandlerCreateEscapeHandler);
        }
        xMLWriter.setXmlDecl(!isFragment());
        xMLWriter.setHeader(this.header);
        return new SAXOutput(xMLWriter);
    }

    public XmlOutput createWriter(Writer writer) {
        return createWriter(writer, getEncoding());
    }

    public XmlOutput createWriter(OutputStream outputStream) throws JAXBException {
        return createWriter(outputStream, getEncoding());
    }

    public XmlOutput createWriter(OutputStream outputStream, String str) throws JAXBException {
        UTF8XmlOutput uTF8XmlOutput;
        if (str.equals(CharsetNames.UTF_8)) {
            Encoded[] uTF8NameTable = this.context.getUTF8NameTable();
            CharacterEscapeHandler characterEscapeHandlerCreateEscapeHandler = createEscapeHandler(str);
            if (isFormattedOutput()) {
                uTF8XmlOutput = new IndentingUTF8XmlOutput(outputStream, this.indent, uTF8NameTable, characterEscapeHandlerCreateEscapeHandler);
            } else if (this.c14nSupport) {
                uTF8XmlOutput = new C14nXmlOutput(outputStream, uTF8NameTable, this.context.c14nSupport, characterEscapeHandlerCreateEscapeHandler);
            } else {
                uTF8XmlOutput = new UTF8XmlOutput(outputStream, uTF8NameTable, characterEscapeHandlerCreateEscapeHandler);
            }
            String str2 = this.header;
            if (str2 != null) {
                uTF8XmlOutput.setHeader(str2);
            }
            return uTF8XmlOutput;
        }
        try {
            return createWriter(new OutputStreamWriter(outputStream, getJavaEncoding(str)), str);
        } catch (UnsupportedEncodingException e) {
            throw new MarshalException(Messages.UNSUPPORTED_ENCODING.format(str), e);
        }
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public Object getProperty(String str) throws PropertyException {
        if (INDENT_STRING.equals(str)) {
            return this.indent;
        }
        if (ENCODING_HANDLER.equals(str) || ENCODING_HANDLER2.equals(str)) {
            return this.escapeHandler;
        }
        if (PREFIX_MAPPER.equals(str)) {
            return this.prefixMapper;
        }
        if (XMLDECLARATION.equals(str)) {
            return Boolean.valueOf(!isFragment());
        }
        if (XML_HEADERS.equals(str)) {
            return this.header;
        }
        if ("com.sun.xml.bind.c14n".equals(str)) {
            return Boolean.valueOf(this.c14nSupport);
        }
        if (OBJECT_IDENTITY_CYCLE_DETECTION.equals(str)) {
            return Boolean.valueOf(this.serializer.getObjectIdentityCycleDetection());
        }
        return super.getProperty(str);
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void setProperty(String str, Object obj) throws PropertyException {
        if (INDENT_STRING.equals(str)) {
            checkString(str, obj);
            this.indent = (String) obj;
            return;
        }
        if (ENCODING_HANDLER.equals(str) || ENCODING_HANDLER2.equals(str)) {
            if (!(obj instanceof CharacterEscapeHandler)) {
                throw new PropertyException(Messages.MUST_BE_X.format(str, CharacterEscapeHandler.class.getName(), obj.getClass().getName()));
            }
            this.escapeHandler = (CharacterEscapeHandler) obj;
            return;
        }
        if (PREFIX_MAPPER.equals(str)) {
            if (!(obj instanceof NamespacePrefixMapper)) {
                throw new PropertyException(Messages.MUST_BE_X.format(str, NamespacePrefixMapper.class.getName(), obj.getClass().getName()));
            }
            this.prefixMapper = (NamespacePrefixMapper) obj;
            return;
        }
        if (XMLDECLARATION.equals(str)) {
            checkBoolean(str, obj);
            super.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.valueOf(!((Boolean) obj).booleanValue()));
            return;
        }
        if (XML_HEADERS.equals(str)) {
            checkString(str, obj);
            this.header = (String) obj;
        } else if ("com.sun.xml.bind.c14n".equals(str)) {
            checkBoolean(str, obj);
            this.c14nSupport = ((Boolean) obj).booleanValue();
        } else if (OBJECT_IDENTITY_CYCLE_DETECTION.equals(str)) {
            checkBoolean(str, obj);
            this.serializer.setObjectIdentityCycleDetection(((Boolean) obj).booleanValue());
        } else {
            super.setProperty(str, obj);
        }
    }

    private void checkBoolean(String str, Object obj) throws PropertyException {
        if (!(obj instanceof Boolean)) {
            throw new PropertyException(Messages.MUST_BE_X.format(str, Boolean.class.getName(), obj.getClass().getName()));
        }
    }

    private void checkString(String str, Object obj) throws PropertyException {
        if (!(obj instanceof String)) {
            throw new PropertyException(Messages.MUST_BE_X.format(str, String.class.getName(), obj.getClass().getName()));
        }
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public <A extends XmlAdapter> void setAdapter(Class<A> cls, A a) {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        this.serializer.putAdapter(cls, a);
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public <A extends XmlAdapter> A getAdapter(Class<A> cls) {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        if (this.serializer.containsAdapter(cls)) {
            return (A) this.serializer.getAdapter(cls);
        }
        return null;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void setAttachmentMarshaller(AttachmentMarshaller attachmentMarshaller) {
        this.serializer.attachmentMarshaller = attachmentMarshaller;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public AttachmentMarshaller getAttachmentMarshaller() {
        return this.serializer.attachmentMarshaller;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public Schema getSchema() {
        return this.schema;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public Marshaller.Listener getListener() {
        return this.externalListener;
    }

    @Override // javax.xml.bind.helpers.AbstractMarshallerImpl, javax.xml.bind.Marshaller
    public void setListener(Marshaller.Listener listener) {
        this.externalListener = listener;
    }
}
