package com.sun.xml.bind.v2.model.impl;

import android.R;
import com.byd.vrdownload.model.LanguageUpdate;
import com.sun.istack.ByteArrayDataSource;
import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.TODO;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.model.runtime.RuntimeBuiltinLeafInfo;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.Pcdata;
import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.util.ByteArrayOutputStreamEx;
import com.sun.xml.bind.v2.util.DataSourceSource;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.bind.MarshalException;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import org.apache.commons.compress.utils.CharsetNames;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RuntimeBuiltinLeafInfoImpl<T> extends BuiltinLeafInfoImpl<Type, Class> implements RuntimeBuiltinLeafInfo, Transducer<T> {
    private static final String DATE = "date";
    public static final String MAP_ANYURI_TO_URI = "mapAnyUriToUri";
    public static final RuntimeBuiltinLeafInfoImpl<String> STRING;
    public static final String USE_OLD_GMONTH_MAPPING = "jaxb.ri.useOldGmonthMapping";
    public static final List<RuntimeBuiltinLeafInfoImpl<?>> builtinBeanInfos;
    private static final Map<QName, Integer> xmlGregorianCalendarFieldRef;
    private static final Map<QName, String> xmlGregorianCalendarFormatString;
    private static final Logger logger = com.sun.xml.bind.Util.getClassLogger();
    public static final Map<Type, RuntimeBuiltinLeafInfoImpl<?>> LEAVES = new HashMap();

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void declareNamespace(T t, XMLSerializer xMLSerializer) throws AccessorException {
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo, com.sun.xml.bind.v2.model.runtime.RuntimeNonElement
    public final Transducer getTransducer() {
        return this;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public QName getTypeName(T t) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public final boolean isDefault() {
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public boolean useNamespace() {
        return false;
    }

    static {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.1
            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty(RuntimeBuiltinLeafInfoImpl.MAP_ANYURI_TO_URI);
            }
        });
        StringImplImpl stringImplImpl = new StringImplImpl(String.class, str == null ? new QName[]{createXS("string"), createXS("anySimpleType"), createXS("normalizedString"), createXS("anyURI"), createXS("token"), createXS(LanguageUpdate.LANGUAGE), createXS("Name"), createXS("NCName"), createXS("NMTOKEN"), createXS("ENTITY")} : new QName[]{createXS("string"), createXS("anySimpleType"), createXS("normalizedString"), createXS("token"), createXS(LanguageUpdate.LANGUAGE), createXS("Name"), createXS("NCName"), createXS("NMTOKEN"), createXS("ENTITY")});
        STRING = stringImplImpl;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringImpl<Character>(Character.class, createXS("unsignedShort")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.2
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Character parse(CharSequence charSequence) {
                return Character.valueOf((char) DatatypeConverterImpl._parseInt(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Character ch) {
                return Integer.toString(ch.charValue());
            }
        });
        arrayList.add(new StringImpl<Calendar>(Calendar.class, DatatypeConstants.DATETIME) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.3
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Calendar parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseDateTime(charSequence.toString());
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Calendar calendar) {
                return DatatypeConverterImpl._printDateTime(calendar);
            }
        });
        arrayList.add(new StringImpl<GregorianCalendar>(GregorianCalendar.class, DatatypeConstants.DATETIME) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.4
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public GregorianCalendar parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseDateTime(charSequence.toString());
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(GregorianCalendar gregorianCalendar) {
                return DatatypeConverterImpl._printDateTime(gregorianCalendar);
            }
        });
        arrayList.add(new StringImpl<Date>(Date.class, DatatypeConstants.DATETIME) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.5
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Date parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseDateTime(charSequence.toString()).getTime();
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Date date) {
                QName schemaType = XMLSerializer.getInstance().getSchemaType();
                GregorianCalendar gregorianCalendar = new GregorianCalendar(0, 0, 0);
                gregorianCalendar.setTime(date);
                if (schemaType != null && WellKnownNamespace.XML_SCHEMA.equals(schemaType.getNamespaceURI()) && RuntimeBuiltinLeafInfoImpl.DATE.equals(schemaType.getLocalPart())) {
                    return DatatypeConverterImpl._printDate(gregorianCalendar);
                }
                return DatatypeConverterImpl._printDateTime(gregorianCalendar);
            }
        });
        arrayList.add(new StringImpl<File>(File.class, createXS("string")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.6
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public File parse(CharSequence charSequence) {
                return new File(WhiteSpaceProcessor.trim(charSequence).toString());
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(File file) {
                return file.getPath();
            }
        });
        arrayList.add(new StringImpl<URL>(URL.class, createXS("anyURI")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.7
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public URL parse(CharSequence charSequence) throws SAXException {
                TODO.checkSpec("JSR222 Issue #42");
                try {
                    return new URL(WhiteSpaceProcessor.trim(charSequence).toString());
                } catch (MalformedURLException e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(URL url) {
                return url.toExternalForm();
            }
        });
        if (str == null) {
            arrayList.add(new StringImpl<URI>(URI.class, createXS("string")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.8
                @Override // com.sun.xml.bind.v2.runtime.Transducer
                public URI parse(CharSequence charSequence) throws SAXException {
                    try {
                        return new URI(charSequence.toString());
                    } catch (URISyntaxException e) {
                        UnmarshallingContext.getInstance().handleError(e);
                        return null;
                    }
                }

                @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
                public String print(URI uri) {
                    return uri.toString();
                }
            });
        }
        arrayList.add(new StringImpl<Class>(Class.class, createXS("string")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.9
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Class parse(CharSequence charSequence) throws SAXException {
                TODO.checkSpec("JSR222 Issue #42");
                try {
                    String string = WhiteSpaceProcessor.trim(charSequence).toString();
                    ClassLoader contextClassLoader = UnmarshallingContext.getInstance().classLoader;
                    if (contextClassLoader == null) {
                        contextClassLoader = Thread.currentThread().getContextClassLoader();
                    }
                    if (contextClassLoader != null) {
                        return contextClassLoader.loadClass(string);
                    }
                    return Class.forName(string);
                } catch (ClassNotFoundException e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Class cls) {
                return cls.getName();
            }
        });
        arrayList.add(new PcdataImpl<Image>(Image.class, createXS("base64Binary")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.10
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Image parse(CharSequence charSequence) throws SAXException {
                InputStream byteArrayInputStream;
                try {
                    if (charSequence instanceof Base64Data) {
                        byteArrayInputStream = ((Base64Data) charSequence).getInputStream();
                    } else {
                        byteArrayInputStream = new ByteArrayInputStream(RuntimeBuiltinLeafInfoImpl.decodeBase64(charSequence));
                    }
                    try {
                        return ImageIO.read(byteArrayInputStream);
                    } finally {
                        byteArrayInputStream.close();
                    }
                } catch (IOException e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            private BufferedImage convertToBufferedImage(Image image) throws IOException {
                if (image instanceof BufferedImage) {
                    return (BufferedImage) image;
                }
                MediaTracker mediaTracker = new MediaTracker(new Component() { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.10.1
                });
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth((ImageObserver) null), image.getHeight((ImageObserver) null), 2);
                    bufferedImage.createGraphics().drawImage(image, 0, 0, (ImageObserver) null);
                    return bufferedImage;
                } catch (InterruptedException e) {
                    throw new IOException(e.getMessage());
                }
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.PcdataImpl, com.sun.xml.bind.v2.runtime.Transducer
            public Base64Data print(Image image) {
                ByteArrayOutputStreamEx byteArrayOutputStreamEx = new ByteArrayOutputStreamEx();
                XMLSerializer xMLSerializer = XMLSerializer.getInstance();
                String xMIMEContentType = xMLSerializer.getXMIMEContentType();
                if (xMIMEContentType == null || xMIMEContentType.startsWith("image/*")) {
                    xMIMEContentType = "image/png";
                }
                try {
                    Iterator imageWritersByMIMEType = ImageIO.getImageWritersByMIMEType(xMIMEContentType);
                    if (imageWritersByMIMEType.hasNext()) {
                        ImageWriter imageWriter = (ImageWriter) imageWritersByMIMEType.next();
                        ImageOutputStream imageOutputStreamCreateImageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStreamEx);
                        imageWriter.setOutput(imageOutputStreamCreateImageOutputStream);
                        imageWriter.write(convertToBufferedImage(image));
                        imageOutputStreamCreateImageOutputStream.close();
                        imageWriter.dispose();
                        Base64Data base64Data = new Base64Data();
                        byteArrayOutputStreamEx.set(base64Data, xMIMEContentType);
                        return base64Data;
                    }
                    xMLSerializer.handleEvent(new ValidationEventImpl(1, Messages.NO_IMAGE_WRITER.format(xMIMEContentType), xMLSerializer.getCurrentLocation(null)));
                    throw new RuntimeException("no encoder for MIME type " + xMIMEContentType);
                } catch (IOException e) {
                    xMLSerializer.handleError(e);
                    throw new RuntimeException(e);
                }
            }
        });
        arrayList.add(new PcdataImpl<DataHandler>(DataHandler.class, createXS("base64Binary")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.11
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public DataHandler parse(CharSequence charSequence) {
                if (charSequence instanceof Base64Data) {
                    return ((Base64Data) charSequence).getDataHandler();
                }
                return new DataHandler(new ByteArrayDataSource(RuntimeBuiltinLeafInfoImpl.decodeBase64(charSequence), UnmarshallingContext.getInstance().getXMIMEContentType()));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.PcdataImpl, com.sun.xml.bind.v2.runtime.Transducer
            public Base64Data print(DataHandler dataHandler) {
                Base64Data base64Data = new Base64Data();
                base64Data.set(dataHandler);
                return base64Data;
            }
        });
        arrayList.add(new PcdataImpl<Source>(Source.class, createXS("base64Binary")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.12
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Source parse(CharSequence charSequence) throws SAXException {
                try {
                    if (charSequence instanceof Base64Data) {
                        return new DataSourceSource(((Base64Data) charSequence).getDataHandler());
                    }
                    return new DataSourceSource(new ByteArrayDataSource(RuntimeBuiltinLeafInfoImpl.decodeBase64(charSequence), UnmarshallingContext.getInstance().getXMIMEContentType()));
                } catch (MimeTypeParseException e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.PcdataImpl, com.sun.xml.bind.v2.runtime.Transducer
            public Base64Data print(Source source) {
                MimeType mimeType;
                DataSource dataSource;
                String contentType;
                XMLSerializer xMLSerializer = XMLSerializer.getInstance();
                Base64Data base64Data = new Base64Data();
                String xMIMEContentType = xMLSerializer.getXMIMEContentType();
                if (xMIMEContentType != null) {
                    try {
                        mimeType = new MimeType(xMIMEContentType);
                    } catch (MimeTypeParseException e) {
                        xMLSerializer.handleError(e);
                        mimeType = null;
                    }
                } else {
                    mimeType = null;
                }
                if ((source instanceof DataSourceSource) && (contentType = (dataSource = ((DataSourceSource) source).getDataSource()).getContentType()) != null && (xMIMEContentType == null || xMIMEContentType.equals(contentType))) {
                    base64Data.set(new DataHandler(dataSource));
                    return base64Data;
                }
                String parameter = mimeType != null ? mimeType.getParameter("charset") : null;
                if (parameter == null) {
                    parameter = CharsetNames.UTF_8;
                }
                try {
                    ByteArrayOutputStreamEx byteArrayOutputStreamEx = new ByteArrayOutputStreamEx();
                    Transformer identityTransformer = xMLSerializer.getIdentityTransformer();
                    String outputProperty = identityTransformer.getOutputProperty("encoding");
                    identityTransformer.setOutputProperty("encoding", parameter);
                    identityTransformer.transform(source, new StreamResult(new OutputStreamWriter(byteArrayOutputStreamEx, parameter)));
                    identityTransformer.setOutputProperty("encoding", outputProperty);
                    byteArrayOutputStreamEx.set(base64Data, "application/xml; charset=" + parameter);
                    return base64Data;
                } catch (UnsupportedEncodingException e2) {
                    xMLSerializer.handleError(e2);
                    base64Data.set(new byte[0], "application/xml");
                    return base64Data;
                } catch (TransformerException e3) {
                    xMLSerializer.handleError(e3);
                    base64Data.set(new byte[0], "application/xml");
                    return base64Data;
                }
            }
        });
        arrayList.add(new StringImpl<XMLGregorianCalendar>(XMLGregorianCalendar.class, createXS("anySimpleType"), DatatypeConstants.DATE, DatatypeConstants.DATETIME, DatatypeConstants.TIME, DatatypeConstants.GMONTH, DatatypeConstants.GDAY, DatatypeConstants.GYEAR, DatatypeConstants.GYEARMONTH, DatatypeConstants.GMONTHDAY) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.13
            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(XMLGregorianCalendar xMLGregorianCalendar) {
                XMLSerializer xMLSerializer = XMLSerializer.getInstance();
                QName schemaType = xMLSerializer.getSchemaType();
                if (schemaType != null) {
                    try {
                        RuntimeBuiltinLeafInfoImpl.checkXmlGregorianCalendarFieldRef(schemaType, xMLGregorianCalendar);
                        String str2 = (String) RuntimeBuiltinLeafInfoImpl.xmlGregorianCalendarFormatString.get(schemaType);
                        if (str2 != null) {
                            return format(str2, xMLGregorianCalendar);
                        }
                    } catch (MarshalException e) {
                        xMLSerializer.handleEvent(new ValidationEventImpl(0, e.getMessage(), xMLSerializer.getCurrentLocation(null)));
                        return "";
                    }
                }
                return xMLGregorianCalendar.toXMLFormat();
            }

            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public XMLGregorianCalendar parse(CharSequence charSequence) throws SAXException {
                try {
                    return DatatypeConverterImpl.getDatatypeFactory().newXMLGregorianCalendar(charSequence.toString().trim());
                } catch (Exception e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            private String format(String str2, XMLGregorianCalendar xMLGregorianCalendar) {
                StringBuilder sb = new StringBuilder();
                int length = str2.length();
                int i = 0;
                while (i < length) {
                    int i2 = i + 1;
                    char cCharAt = str2.charAt(i);
                    if (cCharAt != '%') {
                        sb.append(cCharAt);
                        i = i2;
                    } else {
                        i = i2 + 1;
                        char cCharAt2 = str2.charAt(i2);
                        if (cCharAt2 == 'D') {
                            printNumber(sb, xMLGregorianCalendar.getDay(), 2);
                        } else if (cCharAt2 == 'M') {
                            printNumber(sb, xMLGregorianCalendar.getMonth(), 2);
                        } else if (cCharAt2 == 'Y') {
                            printNumber(sb, xMLGregorianCalendar.getEonAndYear(), 4);
                        } else if (cCharAt2 == 'h') {
                            printNumber(sb, xMLGregorianCalendar.getHour(), 2);
                        } else if (cCharAt2 == 'm') {
                            printNumber(sb, xMLGregorianCalendar.getMinute(), 2);
                        } else if (cCharAt2 == 's') {
                            printNumber(sb, xMLGregorianCalendar.getSecond(), 2);
                            if (xMLGregorianCalendar.getFractionalSecond() != null) {
                                String plainString = xMLGregorianCalendar.getFractionalSecond().toPlainString();
                                sb.append(plainString.substring(1, plainString.length()));
                            }
                        } else if (cCharAt2 == 'z') {
                            int timezone = xMLGregorianCalendar.getTimezone();
                            if (timezone == 0) {
                                sb.append('Z');
                            } else if (timezone != Integer.MIN_VALUE) {
                                if (timezone < 0) {
                                    sb.append(SignatureVisitor.SUPER);
                                    timezone *= -1;
                                } else {
                                    sb.append(SignatureVisitor.EXTENDS);
                                }
                                printNumber(sb, timezone / 60, 2);
                                sb.append(':');
                                printNumber(sb, timezone % 60, 2);
                            }
                        } else {
                            throw new InternalError();
                        }
                    }
                }
                return sb.toString();
            }

            private void printNumber(StringBuilder sb, BigInteger bigInteger, int i) {
                String string = bigInteger.toString();
                for (int length = string.length(); length < i; length++) {
                    sb.append('0');
                }
                sb.append(string);
            }

            private void printNumber(StringBuilder sb, int i, int i2) {
                String strValueOf = String.valueOf(i);
                for (int length = strValueOf.length(); length < i2; length++) {
                    sb.append('0');
                }
                sb.append(strValueOf);
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl, com.sun.xml.bind.v2.runtime.Transducer
            public QName getTypeName(XMLGregorianCalendar xMLGregorianCalendar) {
                return xMLGregorianCalendar.getXMLSchemaType();
            }
        });
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(stringImplImpl);
        arrayList2.add(new StringImpl<Boolean>(Boolean.class, createXS("boolean")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.14
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Boolean parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseBoolean(charSequence);
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Boolean bool) {
                return bool.toString();
            }
        });
        arrayList2.add(new PcdataImpl<byte[]>(byte[].class, createXS("base64Binary"), createXS("hexBinary")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.15
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public byte[] parse(CharSequence charSequence) {
                return RuntimeBuiltinLeafInfoImpl.decodeBase64(charSequence);
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.PcdataImpl, com.sun.xml.bind.v2.runtime.Transducer
            public Base64Data print(byte[] bArr) {
                XMLSerializer xMLSerializer = XMLSerializer.getInstance();
                Base64Data base64Data = new Base64Data();
                base64Data.set(bArr, xMLSerializer.getXMIMEContentType());
                return base64Data;
            }
        });
        arrayList2.add(new StringImpl<Byte>(Byte.class, createXS("byte")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.16
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Byte parse(CharSequence charSequence) {
                return Byte.valueOf(DatatypeConverterImpl._parseByte(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Byte b) {
                return DatatypeConverterImpl._printByte(b.byteValue());
            }
        });
        arrayList2.add(new StringImpl<Short>(Short.class, createXS("short"), createXS("unsignedByte")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.17
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Short parse(CharSequence charSequence) {
                return Short.valueOf(DatatypeConverterImpl._parseShort(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Short sh) {
                return DatatypeConverterImpl._printShort(sh.shortValue());
            }
        });
        arrayList2.add(new StringImpl<Integer>(Integer.class, createXS("int"), createXS("unsignedShort")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.18
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Integer parse(CharSequence charSequence) {
                return Integer.valueOf(DatatypeConverterImpl._parseInt(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Integer num) {
                return DatatypeConverterImpl._printInt(num.intValue());
            }
        });
        arrayList2.add(new StringImpl<Long>(Long.class, createXS("long"), createXS("unsignedInt")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.19
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Long parse(CharSequence charSequence) {
                return Long.valueOf(DatatypeConverterImpl._parseLong(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Long l) {
                return DatatypeConverterImpl._printLong(l.longValue());
            }
        });
        arrayList2.add(new StringImpl<Float>(Float.class, createXS("float")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.20
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Float parse(CharSequence charSequence) {
                return Float.valueOf(DatatypeConverterImpl._parseFloat(charSequence.toString()));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Float f) {
                return DatatypeConverterImpl._printFloat(f.floatValue());
            }
        });
        arrayList2.add(new StringImpl<Double>(Double.class, createXS("double")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.21
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Double parse(CharSequence charSequence) {
                return Double.valueOf(DatatypeConverterImpl._parseDouble(charSequence));
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Double d) {
                return DatatypeConverterImpl._printDouble(d.doubleValue());
            }
        });
        arrayList2.add(new StringImpl<BigInteger>(BigInteger.class, createXS("integer"), createXS("positiveInteger"), createXS("negativeInteger"), createXS("nonPositiveInteger"), createXS("nonNegativeInteger"), createXS("unsignedLong")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.22
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public BigInteger parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseInteger(charSequence);
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(BigInteger bigInteger) {
                return DatatypeConverterImpl._printInteger(bigInteger);
            }
        });
        arrayList2.add(new StringImpl<BigDecimal>(BigDecimal.class, createXS("decimal")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.23
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public BigDecimal parse(CharSequence charSequence) {
                return DatatypeConverterImpl._parseDecimal(charSequence.toString());
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(BigDecimal bigDecimal) {
                return DatatypeConverterImpl._printDecimal(bigDecimal);
            }
        });
        arrayList2.add(new StringImpl<QName>(QName.class, createXS("QName")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.24
            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl, com.sun.xml.bind.v2.runtime.Transducer
            public boolean useNamespace() {
                return true;
            }

            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public QName parse(CharSequence charSequence) throws SAXException {
                try {
                    return DatatypeConverterImpl._parseQName(charSequence.toString(), UnmarshallingContext.getInstance());
                } catch (IllegalArgumentException e) {
                    UnmarshallingContext.getInstance().handleError(e);
                    return null;
                }
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(QName qName) {
                return DatatypeConverterImpl._printQName(qName, XMLSerializer.getInstance().getNamespaceContext());
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl, com.sun.xml.bind.v2.runtime.Transducer
            public void declareNamespace(QName qName, XMLSerializer xMLSerializer) {
                xMLSerializer.getNamespaceContext().declareNamespace(qName.getNamespaceURI(), qName.getPrefix(), false);
            }
        });
        if (str != null) {
            arrayList2.add(new StringImpl<URI>(URI.class, createXS("anyURI")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.25
                @Override // com.sun.xml.bind.v2.runtime.Transducer
                public URI parse(CharSequence charSequence) throws SAXException {
                    try {
                        return new URI(charSequence.toString());
                    } catch (URISyntaxException e) {
                        UnmarshallingContext.getInstance().handleError(e);
                        return null;
                    }
                }

                @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
                public String print(URI uri) {
                    return uri.toString();
                }
            });
        }
        arrayList2.add(new StringImpl<Duration>(Duration.class, createXS("duration")) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.26
            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Duration duration) {
                return duration.toString();
            }

            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Duration parse(CharSequence charSequence) {
                TODO.checkSpec("JSR222 Issue #42");
                return DatatypeConverterImpl.getDatatypeFactory().newDuration(charSequence.toString());
            }
        });
        arrayList2.add(new StringImpl<Void>(Void.class, new QName[0]) { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.27
            @Override // com.sun.xml.bind.v2.runtime.Transducer
            public Void parse(CharSequence charSequence) {
                return null;
            }

            @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
            public String print(Void r1) {
                return "";
            }
        });
        ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
        arrayList3.addAll(arrayList);
        try {
            arrayList3.add(new UUIDImpl());
        } catch (LinkageError unused) {
        }
        arrayList3.addAll(arrayList2);
        builtinBeanInfos = Collections.unmodifiableList(arrayList3);
        HashMap map = new HashMap();
        xmlGregorianCalendarFormatString = map;
        map.put(DatatypeConstants.DATETIME, "%Y-%M-%DT%h:%m:%s%z");
        map.put(DatatypeConstants.DATE, "%Y-%M-%D%z");
        map.put(DatatypeConstants.TIME, "%h:%m:%s%z");
        if (((String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.28
            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty(RuntimeBuiltinLeafInfoImpl.USE_OLD_GMONTH_MAPPING);
            }
        })) == null) {
            map.put(DatatypeConstants.GMONTH, "--%M%z");
        } else {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, "Old GMonth mapping used.");
            }
            map.put(DatatypeConstants.GMONTH, "--%M--%z");
        }
        map.put(DatatypeConstants.GDAY, "---%D%z");
        map.put(DatatypeConstants.GYEAR, "%Y%z");
        map.put(DatatypeConstants.GYEARMONTH, "%Y-%M%z");
        map.put(DatatypeConstants.GMONTHDAY, "--%M-%D%z");
        HashMap map2 = new HashMap();
        xmlGregorianCalendarFieldRef = map2;
        map2.put(DatatypeConstants.DATETIME, 17895697);
        map2.put(DatatypeConstants.DATE, 17895424);
        map2.put(DatatypeConstants.TIME, 16777489);
        map2.put(DatatypeConstants.GDAY, 16781312);
        map2.put(DatatypeConstants.GMONTH, Integer.valueOf(R.attr.theme));
        map2.put(DatatypeConstants.GYEAR, Integer.valueOf(R.raw.loaderror));
        map2.put(DatatypeConstants.GYEARMONTH, Integer.valueOf(R.bool.config_sendPackageName));
        map2.put(DatatypeConstants.GMONTHDAY, 16846848);
    }

    private RuntimeBuiltinLeafInfoImpl(Class cls, QName... qNameArr) {
        super(cls, qNameArr);
        LEAVES.put(cls, this);
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeLeafInfo
    public final Class getClazz() {
        return (Class) getType();
    }

    private static abstract class StringImpl<T> extends RuntimeBuiltinLeafInfoImpl<T> {
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public abstract String print(T t) throws AccessorException;

        protected StringImpl(Class cls, QName... qNameArr) {
            super(cls, qNameArr);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public void writeText(XMLSerializer xMLSerializer, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            xMLSerializer.text(print((Object) t), str);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public void writeLeafElement(XMLSerializer xMLSerializer, Name name, T t, String str) throws XMLStreamException, SAXException, IOException, AccessorException {
            xMLSerializer.leafElement(name, print((Object) t), str);
        }
    }

    private static abstract class PcdataImpl<T> extends RuntimeBuiltinLeafInfoImpl<T> {
        public abstract Pcdata print(T t) throws AccessorException;

        protected PcdataImpl(Class cls, QName... qNameArr) {
            super(cls, qNameArr);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public final void writeText(XMLSerializer xMLSerializer, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
            xMLSerializer.text(print((Object) t), str);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public final void writeLeafElement(XMLSerializer xMLSerializer, Name name, T t, String str) throws XMLStreamException, SAXException, IOException, AccessorException {
            xMLSerializer.leafElement(name, print((Object) t), str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static QName createXS(String str) {
        return new QName(WellKnownNamespace.XML_SCHEMA, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] decodeBase64(CharSequence charSequence) {
        if (charSequence instanceof Base64Data) {
            return ((Base64Data) charSequence).getExact();
        }
        return DatatypeConverterImpl._parseBase64Binary(charSequence.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkXmlGregorianCalendarFieldRef(QName qName, XMLGregorianCalendar xMLGregorianCalendar) throws MarshalException {
        StringBuilder sb = new StringBuilder();
        int iIntValue = xmlGregorianCalendarFieldRef.get(qName).intValue();
        int i = 0;
        while (iIntValue != 0) {
            int i2 = iIntValue & 1;
            iIntValue >>>= 4;
            i++;
            if (i2 == 1) {
                switch (i) {
                    case 1:
                        if (xMLGregorianCalendar.getSecond() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_SEC);
                        }
                        break;
                    case 2:
                        if (xMLGregorianCalendar.getMinute() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_MIN);
                        }
                        break;
                    case 3:
                        if (xMLGregorianCalendar.getHour() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_HR);
                        }
                        break;
                    case 4:
                        if (xMLGregorianCalendar.getDay() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_DAY);
                        }
                        break;
                    case 5:
                        if (xMLGregorianCalendar.getMonth() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_MONTH);
                        }
                        break;
                    case 6:
                        if (xMLGregorianCalendar.getYear() == Integer.MIN_VALUE) {
                            sb.append("  ").append(Messages.XMLGREGORIANCALENDAR_YEAR);
                        }
                        break;
                }
            }
        }
        if (sb.length() > 0) {
            throw new MarshalException(Messages.XMLGREGORIANCALENDAR_INVALID.format(qName.getLocalPart()) + sb.toString());
        }
    }

    private static class UUIDImpl extends StringImpl<UUID> {
        public UUIDImpl() {
            super(UUID.class, RuntimeBuiltinLeafInfoImpl.createXS("string"));
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public UUID parse(CharSequence charSequence) throws SAXException {
            TODO.checkSpec("JSR222 Issue #42");
            try {
                return UUID.fromString(WhiteSpaceProcessor.trim(charSequence).toString());
            } catch (IllegalArgumentException e) {
                UnmarshallingContext.getInstance().handleError(e);
                return null;
            }
        }

        @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
        public String print(UUID uuid) {
            return uuid.toString();
        }
    }

    private static class StringImplImpl extends StringImpl<String> {
        @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
        public String print(String str) {
            return str;
        }

        public StringImplImpl(Class cls, QName[] qNameArr) {
            super(cls, qNameArr);
        }

        @Override // com.sun.xml.bind.v2.runtime.Transducer
        public String parse(CharSequence charSequence) {
            return charSequence.toString();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
        public final void writeText(XMLSerializer xMLSerializer, String str, String str2) throws SAXException, XMLStreamException, IOException {
            xMLSerializer.text(str, str2);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
        @Override // com.sun.xml.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl.StringImpl, com.sun.xml.bind.v2.runtime.Transducer
        public final void writeLeafElement(XMLSerializer xMLSerializer, Name name, String str, String str2) throws XMLStreamException, SAXException, IOException {
            xMLSerializer.leafElement(name, str, str2);
        }
    }
}
