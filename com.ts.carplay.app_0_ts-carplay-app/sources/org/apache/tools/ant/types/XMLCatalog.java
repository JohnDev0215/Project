package org.apache.tools.ant.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JAXPUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: classes.dex */
public class XMLCatalog extends DataType implements Cloneable, EntityResolver, URIResolver {
    public static final String APACHE_RESOLVER = "org.apache.tools.ant.types.resolver.ApacheCatalogResolver";
    public static final String CATALOG_RESOLVER = "org.apache.xml.resolver.tools.CatalogResolver";
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$tools$ant$types$XMLCatalog;
    private static FileUtils fileUtils = FileUtils.newFileUtils();
    private Path catalogPath;
    private Path classpath;
    private Vector elements = new Vector();
    private CatalogResolver catalogResolver = null;

    private interface CatalogResolver extends URIResolver, EntityResolver {
        @Override // javax.xml.transform.URIResolver
        Source resolve(String str, String str2) throws TransformerException;

        @Override // org.xml.sax.EntityResolver
        InputSource resolveEntity(String str, String str2);
    }

    public XMLCatalog() {
        setChecked(false);
    }

    private Vector getElements() {
        return getRef().elements;
    }

    private Path getClasspath() {
        return getRef().classpath;
    }

    private void setElements(Vector aVector) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.elements = aVector;
    }

    public Path createClasspath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        setChecked(false);
        return this.classpath.createPath();
    }

    public void setClasspath(Path classpath) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        Path path = this.classpath;
        if (path == null) {
            this.classpath = classpath;
        } else {
            path.append(classpath);
        }
        setChecked(false);
    }

    public void setClasspathRef(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createClasspath().setRefid(r);
        setChecked(false);
    }

    public Path createCatalogPath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.catalogPath == null) {
            this.catalogPath = new Path(getProject());
        }
        setChecked(false);
        return this.catalogPath.createPath();
    }

    public void setCatalogPathRef(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createCatalogPath().setRefid(r);
        setChecked(false);
    }

    public Path getCatalogPath() {
        return getRef().catalogPath;
    }

    public void addDTD(ResourceLocation dtd) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        getElements().addElement(dtd);
        setChecked(false);
    }

    public void addEntity(ResourceLocation entity) throws BuildException {
        addDTD(entity);
    }

    public void addConfiguredXMLCatalog(XMLCatalog catalog) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        Vector newElements = catalog.getElements();
        Vector ourElements = getElements();
        Enumeration e = newElements.elements();
        while (e.hasMoreElements()) {
            ourElements.addElement(e.nextElement());
        }
        Path nestedClasspath = catalog.getClasspath();
        createClasspath().append(nestedClasspath);
        Path nestedCatalogPath = catalog.getCatalogPath();
        createCatalogPath().append(nestedCatalogPath);
        setChecked(false);
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (!this.elements.isEmpty()) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (isReference()) {
            return getRef().resolveEntity(publicId, systemId);
        }
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, getProject());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("resolveEntity: '");
        stringBuffer.append(publicId);
        stringBuffer.append("': '");
        stringBuffer.append(systemId);
        stringBuffer.append("'");
        log(stringBuffer.toString(), 4);
        InputSource inputSource = getCatalogResolver().resolveEntity(publicId, systemId);
        if (inputSource == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("No matching catalog entry found, parser will use: '");
            stringBuffer2.append(systemId);
            stringBuffer2.append("'");
            log(stringBuffer2.toString(), 4);
        }
        return inputSource;
    }

    @Override // javax.xml.transform.URIResolver
    public Source resolve(String href, String base) throws TransformerException, SAXException {
        URL baseURL;
        if (isReference()) {
            return getRef().resolve(href, base);
        }
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, getProject());
        }
        String uri = removeFragment(href);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("resolve: '");
        stringBuffer.append(uri);
        stringBuffer.append("' with base: '");
        stringBuffer.append(base);
        stringBuffer.append("'");
        log(stringBuffer.toString(), 4);
        SAXSource source = (SAXSource) getCatalogResolver().resolve(uri, base);
        if (source == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("No matching catalog entry found, parser will use: '");
            stringBuffer2.append(href);
            stringBuffer2.append("'");
            log(stringBuffer2.toString(), 4);
            source = new SAXSource();
            try {
                if (base == null) {
                    baseURL = fileUtils.getFileURL(getProject().getBaseDir());
                } else {
                    baseURL = new URL(base);
                }
                URL url = uri.length() == 0 ? baseURL : new URL(baseURL, uri);
                source.setInputSource(new InputSource(url.toString()));
            } catch (MalformedURLException e) {
                source.setInputSource(new InputSource(uri));
            }
        }
        setEntityResolver(source);
        return source;
    }

    private XMLCatalog getRef() {
        if (!isReference()) {
            return this;
        }
        Class clsClass$ = class$org$apache$tools$ant$types$XMLCatalog;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.types.XMLCatalog");
            class$org$apache$tools$ant$types$XMLCatalog = clsClass$;
        }
        return (XMLCatalog) getCheckedRef(clsClass$, "xmlcatalog");
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private CatalogResolver getCatalogResolver() {
        if (this.catalogResolver == null) {
            AntClassLoader loader = getProject().createClassLoader(Path.systemClasspath);
            try {
                ClassLoader apacheResolverLoader = Class.forName(APACHE_RESOLVER, true, loader).getClassLoader();
                ClassLoader baseResolverLoader = Class.forName(CATALOG_RESOLVER, true, apacheResolverLoader).getClassLoader();
                Class<?> cls = Class.forName(APACHE_RESOLVER, true, baseResolverLoader);
                Object obj = cls.newInstance();
                this.catalogResolver = new ExternalResolver(cls, obj);
            } catch (Throwable ex) {
                this.catalogResolver = new InternalResolver();
                if (getCatalogPath() != null && getCatalogPath().list().length != 0) {
                    log("Warning: catalogpath listing external catalogs will be ignored", 1);
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Failed to load Apache resolver: ");
                stringBuffer.append(ex);
                log(stringBuffer.toString(), 4);
            }
        }
        return this.catalogResolver;
    }

    private void setEntityResolver(SAXSource source) throws TransformerException, SAXException {
        XMLReader reader = source.getXMLReader();
        if (reader == null) {
            SAXParserFactory spFactory = SAXParserFactory.newInstance();
            spFactory.setNamespaceAware(true);
            try {
                reader = spFactory.newSAXParser().getXMLReader();
            } catch (ParserConfigurationException ex) {
                throw new TransformerException(ex);
            } catch (SAXException ex2) {
                throw new TransformerException(ex2);
            }
        }
        reader.setEntityResolver(this);
        source.setXMLReader(reader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourceLocation findMatchingEntry(String publicId) {
        Enumeration e = getElements().elements();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof ResourceLocation) {
                ResourceLocation element = (ResourceLocation) o;
                if (element.getPublicId().equals(publicId)) {
                    return element;
                }
            }
        }
        return null;
    }

    private String removeFragment(String uri) {
        int hashPos = uri.indexOf("#");
        if (hashPos < 0) {
            return uri;
        }
        String result = uri.substring(0, hashPos);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource filesystemLookup(ResourceLocation matchingEntry) throws MalformedURLException {
        URL baseURL;
        String fileName;
        String uri = matchingEntry.getLocation().replace(File.separatorChar, '/');
        if (matchingEntry.getBase() != null) {
            baseURL = matchingEntry.getBase();
        } else {
            try {
                baseURL = fileUtils.getFileURL(getProject().getBaseDir());
            } catch (MalformedURLException e) {
                throw new BuildException("Project basedir cannot be converted to a URL");
            }
        }
        InputSource source = null;
        URL url = null;
        try {
            url = new URL(baseURL, uri);
        } catch (MalformedURLException e2) {
            File testFile = new File(uri);
            if (testFile.exists() && testFile.canRead()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("uri : '");
                stringBuffer.append(uri);
                stringBuffer.append("' matches a readable file");
                log(stringBuffer.toString(), 4);
                try {
                    url = fileUtils.getFileURL(testFile);
                } catch (MalformedURLException e3) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("could not find an URL for :");
                    stringBuffer2.append(testFile.getAbsolutePath());
                    throw new BuildException(stringBuffer2.toString());
                }
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("uri : '");
                stringBuffer3.append(uri);
                stringBuffer3.append("' does not match a readable file");
                log(stringBuffer3.toString(), 4);
            }
        }
        if (url == null || (fileName = fileUtils.fromURI(url.toString())) == null) {
            return null;
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("fileName ");
        stringBuffer4.append(fileName);
        log(stringBuffer4.toString(), 4);
        File resFile = new File(fileName);
        if (!resFile.exists() || !resFile.canRead()) {
            return null;
        }
        try {
            source = new InputSource(new FileInputStream(resFile));
            String sysid = JAXPUtils.getSystemId(resFile);
            source.setSystemId(sysid);
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("catalog entry matched a readable file: '");
            stringBuffer5.append(sysid);
            stringBuffer5.append("'");
            log(stringBuffer5.toString(), 4);
            return source;
        } catch (IOException e4) {
            return source;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource classpathLookup(ResourceLocation matchingEntry) {
        Path cp;
        Path cp2 = this.classpath;
        if (cp2 != null) {
            cp = this.classpath.concatSystemClasspath("ignore");
        } else {
            cp = new Path(getProject()).concatSystemClasspath("last");
        }
        AntClassLoader loader = getProject().createClassLoader(cp);
        InputStream is = loader.getResourceAsStream(matchingEntry.getLocation());
        if (is == null) {
            return null;
        }
        InputSource source = new InputSource(is);
        URL entryURL = loader.getResource(matchingEntry.getLocation());
        String sysid = entryURL.toExternalForm();
        source.setSystemId(sysid);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("catalog entry matched a resource in the classpath: '");
        stringBuffer.append(sysid);
        stringBuffer.append("'");
        log(stringBuffer.toString(), 4);
        return source;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputSource urlLookup(ResourceLocation matchingEntry) throws MalformedURLException {
        URL baseURL;
        String uri = matchingEntry.getLocation();
        if (matchingEntry.getBase() != null) {
            baseURL = matchingEntry.getBase();
        } else {
            try {
                baseURL = fileUtils.getFileURL(getProject().getBaseDir());
            } catch (MalformedURLException e) {
                throw new BuildException("Project basedir cannot be converted to a URL");
            }
        }
        InputSource source = null;
        URL url = null;
        try {
            url = new URL(baseURL, uri);
        } catch (MalformedURLException e2) {
        }
        if (url == null) {
            return null;
        }
        try {
            InputStream is = url.openStream();
            if (is == null) {
                return null;
            }
            source = new InputSource(is);
            String sysid = url.toExternalForm();
            source.setSystemId(sysid);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("catalog entry matched as a URL: '");
            stringBuffer.append(sysid);
            stringBuffer.append("'");
            log(stringBuffer.toString(), 4);
            return source;
        } catch (IOException e3) {
            return source;
        }
    }

    private class InternalResolver implements CatalogResolver {
        public InternalResolver() {
            XMLCatalog.this.log("Apache resolver library not found, internal resolver will be used", 3);
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) throws MalformedURLException {
            ResourceLocation matchingEntry = XMLCatalog.this.findMatchingEntry(publicId);
            if (matchingEntry == null) {
                return null;
            }
            XMLCatalog xMLCatalog = XMLCatalog.this;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Matching catalog entry found for publicId: '");
            stringBuffer.append(matchingEntry.getPublicId());
            stringBuffer.append("' location: '");
            stringBuffer.append(matchingEntry.getLocation());
            stringBuffer.append("'");
            xMLCatalog.log(stringBuffer.toString(), 4);
            InputSource result = XMLCatalog.this.filesystemLookup(matchingEntry);
            if (result == null) {
                result = XMLCatalog.this.classpathLookup(matchingEntry);
            }
            if (result == null) {
                return XMLCatalog.this.urlLookup(matchingEntry);
            }
            return result;
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, javax.xml.transform.URIResolver
        public Source resolve(String href, String base) throws TransformerException, MalformedURLException {
            ResourceLocation matchingEntry = XMLCatalog.this.findMatchingEntry(href);
            if (matchingEntry == null) {
                return null;
            }
            XMLCatalog xMLCatalog = XMLCatalog.this;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Matching catalog entry found for uri: '");
            stringBuffer.append(matchingEntry.getPublicId());
            stringBuffer.append("' location: '");
            stringBuffer.append(matchingEntry.getLocation());
            stringBuffer.append("'");
            xMLCatalog.log(stringBuffer.toString(), 4);
            ResourceLocation entryCopy = matchingEntry;
            if (base != null) {
                try {
                    URL baseURL = new URL(base);
                    entryCopy = new ResourceLocation();
                    entryCopy.setBase(baseURL);
                } catch (MalformedURLException e) {
                }
            }
            entryCopy.setPublicId(matchingEntry.getPublicId());
            entryCopy.setLocation(matchingEntry.getLocation());
            InputSource source = XMLCatalog.this.filesystemLookup(entryCopy);
            if (source == null) {
                source = XMLCatalog.this.classpathLookup(entryCopy);
            }
            if (source == null) {
                source = XMLCatalog.this.urlLookup(entryCopy);
            }
            if (source == null) {
                return null;
            }
            SAXSource result = new SAXSource(source);
            return result;
        }
    }

    private class ExternalResolver implements CatalogResolver {
        private boolean externalCatalogsProcessed = false;
        private Method parseCatalog;
        private Method resolve;
        private Method resolveEntity;
        private Object resolverImpl;
        private Method setXMLCatalog;

        public ExternalResolver(Class resolverImplClass, Object resolverImpl) {
            Class<?> clsClass$;
            Class<?> clsClass$2;
            Class<?> clsClass$3;
            Class<?> clsClass$4;
            Class<?> clsClass$5;
            Class<?> clsClass$6;
            this.setXMLCatalog = null;
            this.parseCatalog = null;
            this.resolveEntity = null;
            this.resolve = null;
            this.resolverImpl = null;
            this.resolverImpl = resolverImpl;
            try {
                Class<?>[] clsArr = new Class[1];
                if (XMLCatalog.class$org$apache$tools$ant$types$XMLCatalog == null) {
                    clsClass$ = XMLCatalog.class$("org.apache.tools.ant.types.XMLCatalog");
                    XMLCatalog.class$org$apache$tools$ant$types$XMLCatalog = clsClass$;
                } else {
                    clsClass$ = XMLCatalog.class$org$apache$tools$ant$types$XMLCatalog;
                }
                clsArr[0] = clsClass$;
                this.setXMLCatalog = resolverImplClass.getMethod("setXMLCatalog", clsArr);
                Class<?>[] clsArr2 = new Class[1];
                if (XMLCatalog.class$java$lang$String == null) {
                    clsClass$2 = XMLCatalog.class$("java.lang.String");
                    XMLCatalog.class$java$lang$String = clsClass$2;
                } else {
                    clsClass$2 = XMLCatalog.class$java$lang$String;
                }
                clsArr2[0] = clsClass$2;
                this.parseCatalog = resolverImplClass.getMethod("parseCatalog", clsArr2);
                Class<?>[] clsArr3 = new Class[2];
                if (XMLCatalog.class$java$lang$String == null) {
                    clsClass$3 = XMLCatalog.class$("java.lang.String");
                    XMLCatalog.class$java$lang$String = clsClass$3;
                } else {
                    clsClass$3 = XMLCatalog.class$java$lang$String;
                }
                clsArr3[0] = clsClass$3;
                if (XMLCatalog.class$java$lang$String == null) {
                    clsClass$4 = XMLCatalog.class$("java.lang.String");
                    XMLCatalog.class$java$lang$String = clsClass$4;
                } else {
                    clsClass$4 = XMLCatalog.class$java$lang$String;
                }
                clsArr3[1] = clsClass$4;
                this.resolveEntity = resolverImplClass.getMethod("resolveEntity", clsArr3);
                Class<?>[] clsArr4 = new Class[2];
                if (XMLCatalog.class$java$lang$String == null) {
                    clsClass$5 = XMLCatalog.class$("java.lang.String");
                    XMLCatalog.class$java$lang$String = clsClass$5;
                } else {
                    clsClass$5 = XMLCatalog.class$java$lang$String;
                }
                clsArr4[0] = clsClass$5;
                if (XMLCatalog.class$java$lang$String == null) {
                    clsClass$6 = XMLCatalog.class$("java.lang.String");
                    XMLCatalog.class$java$lang$String = clsClass$6;
                } else {
                    clsClass$6 = XMLCatalog.class$java$lang$String;
                }
                clsArr4[1] = clsClass$6;
                this.resolve = resolverImplClass.getMethod("resolve", clsArr4);
                XMLCatalog.this.log("Apache resolver library found, xml-commons resolver will be used", 3);
            } catch (NoSuchMethodException ex) {
                throw new BuildException(ex);
            }
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) throws MalformedURLException {
            processExternalCatalogs();
            ResourceLocation matchingEntry = XMLCatalog.this.findMatchingEntry(publicId);
            if (matchingEntry != null) {
                XMLCatalog xMLCatalog = XMLCatalog.this;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Matching catalog entry found for publicId: '");
                stringBuffer.append(matchingEntry.getPublicId());
                stringBuffer.append("' location: '");
                stringBuffer.append(matchingEntry.getLocation());
                stringBuffer.append("'");
                xMLCatalog.log(stringBuffer.toString(), 4);
                InputSource result = XMLCatalog.this.filesystemLookup(matchingEntry);
                if (result == null) {
                    result = XMLCatalog.this.classpathLookup(matchingEntry);
                }
                if (result == null) {
                    try {
                        return (InputSource) this.resolveEntity.invoke(this.resolverImpl, publicId, systemId);
                    } catch (Exception ex) {
                        throw new BuildException(ex);
                    }
                }
                return result;
            }
            try {
                return (InputSource) this.resolveEntity.invoke(this.resolverImpl, publicId, systemId);
            } catch (Exception ex2) {
                throw new BuildException(ex2);
            }
        }

        @Override // org.apache.tools.ant.types.XMLCatalog.CatalogResolver, javax.xml.transform.URIResolver
        public Source resolve(String href, String base) throws TransformerException, MalformedURLException {
            processExternalCatalogs();
            ResourceLocation matchingEntry = XMLCatalog.this.findMatchingEntry(href);
            if (matchingEntry != null) {
                XMLCatalog xMLCatalog = XMLCatalog.this;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Matching catalog entry found for uri: '");
                stringBuffer.append(matchingEntry.getPublicId());
                stringBuffer.append("' location: '");
                stringBuffer.append(matchingEntry.getLocation());
                stringBuffer.append("'");
                xMLCatalog.log(stringBuffer.toString(), 4);
                ResourceLocation entryCopy = matchingEntry;
                if (base != null) {
                    try {
                        URL baseURL = new URL(base);
                        entryCopy = new ResourceLocation();
                        entryCopy.setBase(baseURL);
                    } catch (MalformedURLException e) {
                    }
                }
                entryCopy.setPublicId(matchingEntry.getPublicId());
                entryCopy.setLocation(matchingEntry.getLocation());
                InputSource source = XMLCatalog.this.filesystemLookup(entryCopy);
                if (source == null) {
                    source = XMLCatalog.this.classpathLookup(entryCopy);
                }
                if (source != null) {
                    SAXSource result = new SAXSource(source);
                    return result;
                }
                try {
                    SAXSource result2 = (SAXSource) this.resolve.invoke(this.resolverImpl, href, base);
                    return result2;
                } catch (Exception ex) {
                    throw new BuildException(ex);
                }
            }
            try {
                SAXSource result3 = (SAXSource) this.resolve.invoke(this.resolverImpl, href, base);
                return result3;
            } catch (Exception ex2) {
                throw new BuildException(ex2);
            }
        }

        private void processExternalCatalogs() {
            if (!this.externalCatalogsProcessed) {
                try {
                    this.setXMLCatalog.invoke(this.resolverImpl, XMLCatalog.this);
                    Path catPath = XMLCatalog.this.getCatalogPath();
                    if (catPath != null) {
                        XMLCatalog xMLCatalog = XMLCatalog.this;
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Using catalogpath '");
                        stringBuffer.append(XMLCatalog.this.getCatalogPath());
                        stringBuffer.append("'");
                        xMLCatalog.log(stringBuffer.toString(), 4);
                        String[] catPathList = XMLCatalog.this.getCatalogPath().list();
                        for (String str : catPathList) {
                            File catFile = new File(str);
                            XMLCatalog xMLCatalog2 = XMLCatalog.this;
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Parsing ");
                            stringBuffer2.append(catFile);
                            xMLCatalog2.log(stringBuffer2.toString(), 4);
                            try {
                                this.parseCatalog.invoke(this.resolverImpl, catFile.getPath());
                            } catch (Exception ex) {
                                throw new BuildException(ex);
                            }
                        }
                    }
                } catch (Exception ex2) {
                    throw new BuildException(ex2);
                }
            }
            this.externalCatalogsProcessed = true;
        }
    }
}
