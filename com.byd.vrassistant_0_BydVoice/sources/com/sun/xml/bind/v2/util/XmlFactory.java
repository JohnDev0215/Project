package com.sun.xml.bind.v2.util;

import com.sun.xml.bind.v2.Messages;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: classes3.dex */
public class XmlFactory {
    public static final String ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
    public static final String ACCESS_EXTERNAL_SCHEMA = "http://javax.xml.XMLConstants/property/accessExternalSchema";
    private static final String DISABLE_XML_SECURITY = "com.sun.xml.bind.disableXmlSecurity";
    private static final Logger LOGGER = Logger.getLogger(XmlFactory.class.getName());
    private static final boolean XML_SECURITY_DISABLED = ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: com.sun.xml.bind.v2.util.XmlFactory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Boolean run() {
            return Boolean.valueOf(Boolean.getBoolean("com.sun.xml.bind.disableXmlSecurity"));
        }
    })).booleanValue();

    private static boolean isXMLSecurityDisabled(boolean z) {
        return XML_SECURITY_DISABLED || z;
    }

    public static SchemaFactory createSchemaFactory(String str, boolean z) throws IllegalStateException {
        try {
            SchemaFactory schemaFactoryNewInstance = SchemaFactory.newInstance(str);
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "SchemaFactory instance: {0}", schemaFactoryNewInstance);
            }
            schemaFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(z));
            return schemaFactoryNewInstance;
        } catch (AbstractMethodError e) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e);
            throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(new Object[0]), e);
        } catch (SAXNotRecognizedException e2) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e2);
            throw new IllegalStateException(e2);
        } catch (SAXNotSupportedException e3) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e3);
            throw new IllegalStateException(e3);
        }
    }

    public static SAXParserFactory createParserFactory(boolean z) throws IllegalStateException {
        try {
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "SAXParserFactory instance: {0}", sAXParserFactoryNewInstance);
            }
            boolean z2 = true;
            sAXParserFactoryNewInstance.setNamespaceAware(true);
            if (isXMLSecurityDisabled(z)) {
                z2 = false;
            }
            sAXParserFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", z2);
            return sAXParserFactoryNewInstance;
        } catch (AbstractMethodError e) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e);
            throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(new Object[0]), e);
        } catch (ParserConfigurationException e2) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e2);
            throw new IllegalStateException(e2);
        } catch (SAXNotRecognizedException e3) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e3);
            throw new IllegalStateException(e3);
        } catch (SAXNotSupportedException e4) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e4);
            throw new IllegalStateException(e4);
        }
    }

    public static XPathFactory createXPathFactory(boolean z) throws IllegalStateException {
        try {
            XPathFactory xPathFactoryNewInstance = XPathFactory.newInstance();
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "XPathFactory instance: {0}", xPathFactoryNewInstance);
            }
            xPathFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(z));
            return xPathFactoryNewInstance;
        } catch (AbstractMethodError e) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e);
            throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(new Object[0]), e);
        } catch (XPathFactoryConfigurationException e2) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e2);
            throw new IllegalStateException(e2);
        }
    }

    public static TransformerFactory createTransformerFactory(boolean z) throws IllegalStateException {
        try {
            TransformerFactory transformerFactoryNewInstance = TransformerFactory.newInstance();
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "TransformerFactory instance: {0}", transformerFactoryNewInstance);
            }
            transformerFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(z));
            return transformerFactoryNewInstance;
        } catch (AbstractMethodError e) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e);
            throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(new Object[0]), e);
        } catch (TransformerConfigurationException e2) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e2);
            throw new IllegalStateException(e2);
        }
    }

    public static DocumentBuilderFactory createDocumentBuilderFactory(boolean z) throws IllegalStateException {
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "DocumentBuilderFactory instance: {0}", documentBuilderFactoryNewInstance);
            }
            boolean z2 = true;
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            if (isXMLSecurityDisabled(z)) {
                z2 = false;
            }
            documentBuilderFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", z2);
            return documentBuilderFactoryNewInstance;
        } catch (AbstractMethodError e) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e);
            throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(new Object[0]), e);
        } catch (ParserConfigurationException e2) {
            LOGGER.log(Level.SEVERE, (String) null, (Throwable) e2);
            throw new IllegalStateException(e2);
        }
    }

    public static SchemaFactory allowExternalAccess(SchemaFactory schemaFactory, String str, boolean z) {
        if (isXMLSecurityDisabled(z)) {
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, Messages.JAXP_XML_SECURITY_DISABLED.format(new Object[0]));
            }
            return schemaFactory;
        }
        if (System.getProperty("javax.xml.accessExternalSchema") != null) {
            Logger logger2 = LOGGER;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, Messages.JAXP_EXTERNAL_ACCESS_CONFIGURED.format(new Object[0]));
            }
            return schemaFactory;
        }
        try {
            schemaFactory.setProperty(ACCESS_EXTERNAL_SCHEMA, str);
            Logger logger3 = LOGGER;
            if (logger3.isLoggable(Level.FINE)) {
                logger3.log(Level.FINE, Messages.JAXP_SUPPORTED_PROPERTY.format(ACCESS_EXTERNAL_SCHEMA));
            }
        } catch (SAXException e) {
            Logger logger4 = LOGGER;
            if (logger4.isLoggable(Level.CONFIG)) {
                logger4.log(Level.CONFIG, Messages.JAXP_UNSUPPORTED_PROPERTY.format(ACCESS_EXTERNAL_SCHEMA), (Throwable) e);
            }
        }
        return schemaFactory;
    }

    public static SchemaFactory allowExternalDTDAccess(SchemaFactory schemaFactory, String str, boolean z) {
        if (isXMLSecurityDisabled(z)) {
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, Messages.JAXP_XML_SECURITY_DISABLED.format(new Object[0]));
            }
            return schemaFactory;
        }
        if (System.getProperty("javax.xml.accessExternalDTD") != null) {
            Logger logger2 = LOGGER;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, Messages.JAXP_EXTERNAL_ACCESS_CONFIGURED.format(new Object[0]));
            }
            return schemaFactory;
        }
        try {
            schemaFactory.setProperty(ACCESS_EXTERNAL_DTD, str);
            Logger logger3 = LOGGER;
            if (logger3.isLoggable(Level.FINE)) {
                logger3.log(Level.FINE, Messages.JAXP_SUPPORTED_PROPERTY.format(ACCESS_EXTERNAL_DTD));
            }
        } catch (SAXException e) {
            Logger logger4 = LOGGER;
            if (logger4.isLoggable(Level.CONFIG)) {
                logger4.log(Level.CONFIG, Messages.JAXP_UNSUPPORTED_PROPERTY.format(ACCESS_EXTERNAL_DTD), (Throwable) e);
            }
        }
        return schemaFactory;
    }
}
