package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicConfigurator;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class XSLTProcess extends MatchingTask implements XSLTLogger {
    private static final String TRAX_LIAISON_CLASS = "org.apache.tools.ant.taskdefs.optional.TraXLiaison";
    private static final String XALAN_LIAISON_CLASS = "org.apache.tools.ant.taskdefs.optional.XalanLiaison";
    private static final String XSLP_LIAISON_CLASS = "org.apache.tools.ant.taskdefs.optional.XslpLiaison";
    private XSLTLiaison liaison;
    private String processor;
    private File destDir = null;
    private File baseDir = null;
    private String xslFile = null;
    private String targetExtension = ".html";
    private Vector params = new Vector();
    private File inFile = null;
    private File outFile = null;
    private Path classpath = null;
    private boolean stylesheetLoaded = false;
    private boolean force = false;
    private Vector outputProperties = new Vector();
    private XMLCatalog xmlCatalog = new XMLCatalog();
    private boolean performDirectoryScan = true;
    private Factory factory = null;
    private boolean reuseLoadedStylesheet = true;
    private AntClassLoader loader = null;
    private Mapper mapperElement = null;
    private FileUtils fileUtils = FileUtils.newFileUtils();

    /* JADX INFO: renamed from: org.apache.tools.ant.taskdefs.XSLTProcess$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    public void setScanIncludedDirectories(boolean b) {
        this.performDirectoryScan = b;
    }

    public void setReloadStylesheet(boolean b) {
        this.reuseLoadedStylesheet = !b;
    }

    public void addMapper(Mapper mapper) {
        if (this.mapperElement != null) {
            throw new BuildException("Cannot define more than one mapper", getLocation());
        }
        this.mapperElement = mapper;
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0164  */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.XSLTProcess.execute():void");
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public void setBasedir(File dir) {
        this.baseDir = dir;
    }

    public void setDestdir(File dir) {
        this.destDir = dir;
    }

    public void setExtension(String name) {
        this.targetExtension = name;
    }

    public void setStyle(String xslFile) {
        this.xslFile = xslFile;
    }

    public void setClasspath(Path classpath) {
        createClasspath().append(classpath);
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void addConfiguredXMLCatalog(XMLCatalog xmlCatalog) {
        this.xmlCatalog.addConfiguredXMLCatalog(xmlCatalog);
    }

    private void resolveProcessor(String proc) throws Exception {
        if (proc.equals("trax")) {
            Class clazz = loadClass(TRAX_LIAISON_CLASS);
            this.liaison = (XSLTLiaison) clazz.newInstance();
            return;
        }
        if (proc.equals("xslp")) {
            log("DEPRECATED - xslp processor is deprecated. Use trax instead.");
            Class clazz2 = loadClass(XSLP_LIAISON_CLASS);
            this.liaison = (XSLTLiaison) clazz2.newInstance();
        } else {
            if (proc.equals("xalan")) {
                log("DEPRECATED - xalan processor is deprecated. Use trax instead.");
                Class clazz3 = loadClass(XALAN_LIAISON_CLASS);
                this.liaison = (XSLTLiaison) clazz3.newInstance();
                return;
            }
            this.liaison = (XSLTLiaison) loadClass(proc).newInstance();
        }
    }

    private Class loadClass(String classname) throws Exception {
        if (this.classpath == null) {
            return Class.forName(classname);
        }
        AntClassLoader antClassLoaderCreateClassLoader = getProject().createClassLoader(this.classpath);
        this.loader = antClassLoaderCreateClassLoader;
        antClassLoaderCreateClassLoader.setThreadContextLoader();
        return Class.forName(classname, true, this.loader);
    }

    public void setOut(File outFile) {
        this.outFile = outFile;
    }

    public void setIn(File inFile) {
        this.inFile = inFile;
    }

    private void process(File baseDir, String xmlFile, File destDir, File stylesheet) throws BuildException {
        FileNameMapper mapper;
        File outFile = null;
        try {
            long styleSheetLastModified = stylesheet.lastModified();
            File inFile = new File(baseDir, xmlFile);
            if (inFile.isDirectory()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Skipping ");
                stringBuffer.append(inFile);
                stringBuffer.append(" it is a directory.");
                log(stringBuffer.toString(), 3);
                return;
            }
            if (this.mapperElement != null) {
                mapper = this.mapperElement.getImplementation();
            } else {
                mapper = new StyleMapper(this, null);
            }
            String[] outFileName = mapper.mapFileName(xmlFile);
            if (outFileName != null && outFileName.length != 0) {
                if (outFileName != null && outFileName.length <= 1) {
                    File outFile2 = new File(destDir, outFileName[0]);
                    if (this.force || inFile.lastModified() > outFile2.lastModified() || styleSheetLastModified > outFile2.lastModified()) {
                        ensureDirectoryFor(outFile2);
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Processing ");
                        stringBuffer2.append(inFile);
                        stringBuffer2.append(" to ");
                        stringBuffer2.append(outFile2);
                        log(stringBuffer2.toString());
                        configureLiaison(stylesheet);
                        this.liaison.transform(inFile, outFile2);
                        return;
                    }
                    return;
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Skipping ");
                stringBuffer3.append(inFile);
                stringBuffer3.append(" its mapping is ambiguos.");
                log(stringBuffer3.toString(), 3);
                return;
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Skipping ");
            stringBuffer4.append(inFile);
            stringBuffer4.append(" it cannot get mapped to output.");
            log(stringBuffer4.toString(), 3);
        } catch (Exception ex) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Failed to process ");
            stringBuffer5.append((Object) null);
            log(stringBuffer5.toString(), 2);
            if (0 != 0) {
                outFile.delete();
            }
            throw new BuildException(ex);
        }
    }

    private void process(File inFile, File outFile, File stylesheet) throws BuildException {
        try {
            long styleSheetLastModified = stylesheet.lastModified();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("In file ");
            stringBuffer.append(inFile);
            stringBuffer.append(" time: ");
            stringBuffer.append(inFile.lastModified());
            log(stringBuffer.toString(), 4);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Out file ");
            stringBuffer2.append(outFile);
            stringBuffer2.append(" time: ");
            stringBuffer2.append(outFile.lastModified());
            log(stringBuffer2.toString(), 4);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Style file ");
            stringBuffer3.append(this.xslFile);
            stringBuffer3.append(" time: ");
            stringBuffer3.append(styleSheetLastModified);
            log(stringBuffer3.toString(), 4);
            if (!this.force && inFile.lastModified() < outFile.lastModified() && styleSheetLastModified < outFile.lastModified()) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Skipping input file ");
                stringBuffer4.append(inFile);
                stringBuffer4.append(" because it is older than output file ");
                stringBuffer4.append(outFile);
                stringBuffer4.append(" and so is the stylesheet ");
                stringBuffer4.append(stylesheet);
                log(stringBuffer4.toString(), 4);
                return;
            }
            ensureDirectoryFor(outFile);
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Processing ");
            stringBuffer5.append(inFile);
            stringBuffer5.append(" to ");
            stringBuffer5.append(outFile);
            log(stringBuffer5.toString(), 2);
            configureLiaison(stylesheet);
            this.liaison.transform(inFile, outFile);
        } catch (Exception ex) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Failed to process ");
            stringBuffer6.append(inFile);
            log(stringBuffer6.toString(), 2);
            if (outFile != null) {
                outFile.delete();
            }
            throw new BuildException(ex);
        }
    }

    private void ensureDirectoryFor(File targetFile) throws BuildException {
        File directory = this.fileUtils.getParentFile(targetFile);
        if (!directory.exists() && !directory.mkdirs()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to create directory: ");
            stringBuffer.append(directory.getAbsolutePath());
            throw new BuildException(stringBuffer.toString());
        }
    }

    public Factory getFactory() {
        return this.factory;
    }

    public XMLCatalog getXMLCatalog() {
        return this.xmlCatalog;
    }

    public Enumeration getOutputProperties() {
        return this.outputProperties.elements();
    }

    protected XSLTLiaison getLiaison() {
        BuildException buildException;
        if (this.liaison == null) {
            String str = this.processor;
            if (str != null) {
                try {
                    resolveProcessor(str);
                } catch (Exception e) {
                    throw new BuildException(e);
                }
            } else {
                try {
                    resolveProcessor("trax");
                } finally {
                    try {
                    } finally {
                        try {
                        } catch (Throwable e3) {
                        }
                    }
                }
            }
        }
        return this.liaison;
    }

    public Param createParam() {
        Param p = new Param();
        this.params.addElement(p);
        return p;
    }

    public static class Param {
        private String ifProperty;
        private Project project;
        private String unlessProperty;
        private String name = null;
        private String expression = null;

        public void setProject(Project project) {
            this.project = project;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getName() throws BuildException {
            String str = this.name;
            if (str == null) {
                throw new BuildException("Name attribute is missing.");
            }
            return str;
        }

        public String getExpression() throws BuildException {
            String str = this.expression;
            if (str == null) {
                throw new BuildException("Expression attribute is missing.");
            }
            return str;
        }

        public void setIf(String ifProperty) {
            this.ifProperty = ifProperty;
        }

        public void setUnless(String unlessProperty) {
            this.unlessProperty = unlessProperty;
        }

        public boolean shouldUse() {
            String str = this.ifProperty;
            if (str != null && this.project.getProperty(str) == null) {
                return false;
            }
            String str2 = this.unlessProperty;
            return str2 == null || this.project.getProperty(str2) == null;
        }
    }

    public OutputProperty createOutputProperty() {
        OutputProperty p = new OutputProperty();
        this.outputProperties.addElement(p);
        return p;
    }

    public static class OutputProperty {
        private String name;
        private String value;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        super.init();
        this.xmlCatalog.setProject(getProject());
    }

    protected void configureLiaison(File stylesheet) throws BuildException {
        if (this.stylesheetLoaded && this.reuseLoadedStylesheet) {
            return;
        }
        this.stylesheetLoaded = true;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Loading stylesheet ");
            stringBuffer.append(stylesheet);
            log(stringBuffer.toString(), 2);
            this.liaison.setStylesheet(stylesheet);
            Enumeration e = this.params.elements();
            while (e.hasMoreElements()) {
                Param p = (Param) e.nextElement();
                if (p.shouldUse()) {
                    this.liaison.addParam(p.getName(), p.getExpression());
                }
            }
            if (this.liaison instanceof XSLTLiaison2) {
                ((XSLTLiaison2) this.liaison).configure(this);
            }
        } catch (Exception ex) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Failed to transform using stylesheet ");
            stringBuffer2.append(stylesheet);
            log(stringBuffer2.toString(), 2);
            throw new BuildException(ex);
        }
    }

    public Factory createFactory() throws BuildException {
        if (this.factory != null) {
            throw new BuildException("'factory' element must be unique");
        }
        Factory factory = new Factory();
        this.factory = factory;
        return factory;
    }

    public static class Factory {
        private Vector attributes = new Vector();
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addAttribute(Attribute attr) {
            this.attributes.addElement(attr);
        }

        public Enumeration getAttributes() {
            return this.attributes.elements();
        }

        public static class Attribute implements DynamicConfigurator {
            private String name;
            private Object value;

            public String getName() {
                return this.name;
            }

            public Object getValue() {
                return this.value;
            }

            @Override // org.apache.tools.ant.DynamicElement
            public Object createDynamicElement(String name) throws BuildException {
                return null;
            }

            @Override // org.apache.tools.ant.DynamicAttribute
            public void setDynamicAttribute(String name, String value) throws BuildException {
                if (FilenameSelector.NAME_KEY.equalsIgnoreCase(name)) {
                    this.name = value;
                    return;
                }
                if (SizeSelector.SIZE_KEY.equalsIgnoreCase(name)) {
                    if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
                        this.value = new Boolean(value);
                        return;
                    }
                    try {
                        this.value = new Integer(value);
                        return;
                    } catch (NumberFormatException e) {
                        this.value = value;
                        return;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unsupported attribute: ");
                stringBuffer.append(name);
                throw new BuildException(stringBuffer.toString());
            }
        }
    }

    private class StyleMapper implements FileNameMapper {
        private StyleMapper() {
        }

        /* synthetic */ StyleMapper(XSLTProcess x0, AnonymousClass1 x1) {
            this();
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setFrom(String from) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setTo(String to) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public String[] mapFileName(String xmlFile) {
            int dotPos = xmlFile.lastIndexOf(46);
            if (dotPos > 0) {
                xmlFile = xmlFile.substring(0, dotPos);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(xmlFile);
            stringBuffer.append(XSLTProcess.this.targetExtension);
            return new String[]{stringBuffer.toString()};
        }
    }
}
