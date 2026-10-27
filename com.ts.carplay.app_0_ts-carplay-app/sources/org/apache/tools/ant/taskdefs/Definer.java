package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public abstract class Definer extends DefBase {
    private static ResourceStack resourceStack = new ResourceStack();
    private String adaptTo;
    private Class adaptToClass;
    private String adapter;
    private Class adapterClass;
    private String classname;
    private File file;
    private String name;
    private String resource;
    private int format = 0;
    private boolean definerSet = false;
    private int onError = 0;

    private static class ResourceStack extends ThreadLocal {
        private ResourceStack() {
        }

        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new HashMap();
        }

        Map getStack() {
            return (Map) get();
        }
    }

    public static class OnError extends EnumeratedAttribute {
        public static final int FAIL = 0;
        public static final int IGNORE = 2;
        public static final int REPORT = 1;

        public OnError() {
        }

        public OnError(String value) {
            setValue(value);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"fail", "report", "ignore"};
        }
    }

    public static class Format extends EnumeratedAttribute {
        public static final int PROPERTIES = 0;
        public static final int XML = 1;

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"properties", "xml"};
        }
    }

    public void setOnError(OnError onError) {
        this.onError = onError.getIndex();
    }

    public void setFormat(Format format) {
        this.format = format.getIndex();
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public String getResource() {
        return this.resource;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        Enumeration urls;
        ClassLoader al = createLoader();
        if (!this.definerSet) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("name, file or resource attribute of ");
            stringBuffer.append(getTaskName());
            stringBuffer.append(" is undefined");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        String str = this.name;
        if (str != null) {
            String str2 = this.classname;
            if (str2 == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("classname attribute of ");
                stringBuffer2.append(getTaskName());
                stringBuffer2.append(" element ");
                stringBuffer2.append("is undefined");
                throw new BuildException(stringBuffer2.toString(), getLocation());
            }
            addDefinition(al, str, str2);
            return;
        }
        if (this.classname != null) {
            throw new BuildException("You must not specify classname together with file or resource.", getLocation());
        }
        if (this.file != null) {
            final URL url = fileToURL();
            if (url == null) {
                return;
            } else {
                urls = new Enumeration() { // from class: org.apache.tools.ant.taskdefs.Definer.1
                    private boolean more = true;

                    @Override // java.util.Enumeration
                    public boolean hasMoreElements() {
                        return this.more;
                    }

                    @Override // java.util.Enumeration
                    public Object nextElement() throws NoSuchElementException {
                        if (this.more) {
                            this.more = false;
                            return url;
                        }
                        throw new NoSuchElementException();
                    }
                };
            }
        } else {
            urls = resourceToURLs(al);
        }
        while (urls.hasMoreElements()) {
            URL url2 = (URL) urls.nextElement();
            int format = this.format;
            if (url2.toString().toLowerCase(Locale.US).endsWith(".xml")) {
                format = 1;
            }
            if (format == 0) {
                loadProperties(al, url2);
                return;
            }
            if (resourceStack.getStack().get(url2) != null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Warning: Recursive loading of ");
                stringBuffer3.append(url2);
                stringBuffer3.append(" ignored");
                stringBuffer3.append(" at ");
                stringBuffer3.append(getLocation());
                stringBuffer3.append(" originally loaded at ");
                stringBuffer3.append(resourceStack.getStack().get(url2));
                log(stringBuffer3.toString(), 1);
            } else {
                try {
                    resourceStack.getStack().put(url2, getLocation());
                    loadAntlib(al, url2);
                } finally {
                    resourceStack.getStack().remove(url2);
                }
            }
        }
    }

    private URL fileToURL() {
        if (!this.file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("File ");
            stringBuffer.append(this.file);
            stringBuffer.append(" does not exist");
            log(stringBuffer.toString(), 1);
            return null;
        }
        if (!this.file.isFile()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("File ");
            stringBuffer2.append(this.file);
            stringBuffer2.append(" is not a file");
            log(stringBuffer2.toString(), 1);
            return null;
        }
        try {
            return this.file.toURL();
        } catch (Exception ex) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("File ");
            stringBuffer3.append(this.file);
            stringBuffer3.append(" cannot use as URL: ");
            stringBuffer3.append(ex.toString());
            log(stringBuffer3.toString(), 1);
            return null;
        }
    }

    private Enumeration resourceToURLs(ClassLoader classLoader) {
        try {
            Enumeration<URL> resources = classLoader.getResources(this.resource);
            if (!resources.hasMoreElements() && this.onError != 2) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not load definitions from resource ");
                stringBuffer.append(this.resource);
                stringBuffer.append(". It could not be found.");
                log(stringBuffer.toString(), 1);
            }
            return resources;
        } catch (IOException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not fetch resources named ");
            stringBuffer2.append(this.resource);
            throw new BuildException(stringBuffer2.toString(), e, getLocation());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void loadProperties(java.lang.ClassLoader r6, java.net.URL r7) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.InputStream r2 = r7.openStream()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r0 = r2
            if (r0 != 0) goto L28
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r2.<init>()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r3 = "Could not load definitions from "
            r2.append(r3)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r2.append(r7)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r3 = 1
            r5.log(r2, r3)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            if (r0 == 0) goto L26
            r0.close()     // Catch: java.io.IOException -> L25
            goto L26
        L25:
            r2 = move-exception
        L26:
            return
        L28:
            java.util.Properties r2 = new java.util.Properties     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r2.<init>()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r1 = r2
            r1.load(r0)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.util.Enumeration r2 = r1.keys()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
        L36:
            boolean r3 = r2.hasMoreElements()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            if (r3 != 0) goto L47
        L3d:
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.io.IOException -> L43
            goto L44
        L43:
            r2 = move-exception
        L44:
            return
        L47:
            java.lang.Object r3 = r2.nextElement()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r5.name = r3     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r3 = r1.getProperty(r3)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r5.classname = r3     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r4 = r5.name     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r5.addDefinition(r6, r4, r3)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            goto L36
        L5b:
            r2 = move-exception
            goto L69
        L5d:
            r1 = move-exception
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L68
            org.apache.tools.ant.Location r3 = r5.getLocation()     // Catch: java.lang.Throwable -> L68
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L68
            throw r2     // Catch: java.lang.Throwable -> L68
        L68:
            r2 = move-exception
        L69:
            if (r0 == 0) goto L70
            r0.close()     // Catch: java.io.IOException -> L6f
            goto L70
        L6f:
            r3 = move-exception
        L70:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Definer.loadProperties(java.lang.ClassLoader, java.net.URL):void");
    }

    private void loadAntlib(ClassLoader classLoader, URL url) {
        try {
            Antlib antlib = Antlib.createAntlib(getProject(), url, getURI());
            antlib.setClassLoader(classLoader);
            antlib.setURI(getURI());
            antlib.perform();
        } catch (BuildException ex) {
            throw ProjectHelper.addLocationToBuildException(ex, getLocation());
        }
    }

    public void setFile(File file) {
        if (this.definerSet) {
            tooManyDefinitions();
        }
        this.definerSet = true;
        this.file = file;
    }

    public void setResource(String res) {
        if (this.definerSet) {
            tooManyDefinitions();
        }
        this.definerSet = true;
        this.resource = res;
    }

    public void setName(String name) {
        if (this.definerSet) {
            tooManyDefinitions();
        }
        this.definerSet = true;
        this.name = name;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    protected void setAdapterClass(Class adapterClass) {
        this.adapterClass = adapterClass;
    }

    public void setAdaptTo(String adaptTo) {
        this.adaptTo = adaptTo;
    }

    protected void setAdaptToClass(Class adaptToClass) {
        this.adaptToClass = adaptToClass;
    }

    protected void addDefinition(ClassLoader al, String name, String classname) throws BuildException {
        Class<?> cls = null;
        try {
            try {
                try {
                    String name2 = ProjectHelper.genComponentName(getURI(), name);
                    if (this.onError != 2) {
                        cls = Class.forName(classname, true, al);
                    }
                    if (this.adapter != null) {
                        this.adapterClass = Class.forName(this.adapter, true, al);
                    }
                    if (this.adaptTo != null) {
                        this.adaptToClass = Class.forName(this.adaptTo, true, al);
                    }
                    AntTypeDefinition def = new AntTypeDefinition();
                    def.setName(name2);
                    def.setClassName(classname);
                    def.setClass(cls);
                    def.setAdapterClass(this.adapterClass);
                    def.setAdaptToClass(this.adaptToClass);
                    def.setClassLoader(al);
                    if (cls != null) {
                        def.checkClass(getProject());
                    }
                    ComponentHelper.getComponentHelper(getProject()).addDataTypeDefinition(def);
                } catch (ClassNotFoundException cnfe) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(getTaskName());
                    stringBuffer.append(" class ");
                    stringBuffer.append(classname);
                    stringBuffer.append(" cannot be found");
                    String msg = stringBuffer.toString();
                    throw new BuildException(msg, cnfe, getLocation());
                }
            } catch (NoClassDefFoundError ncdfe) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(getTaskName());
                stringBuffer2.append(" A class needed by class ");
                stringBuffer2.append(classname);
                stringBuffer2.append(" cannot be found: ");
                stringBuffer2.append(ncdfe.getMessage());
                String msg2 = stringBuffer2.toString();
                throw new BuildException(msg2, ncdfe, getLocation());
            }
        } catch (BuildException ex) {
            int i = this.onError;
            if (i != 0) {
                if (i == 1) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(ex.getLocation());
                    stringBuffer3.append("Warning: ");
                    stringBuffer3.append(ex.getMessage());
                    log(stringBuffer3.toString(), 1);
                    return;
                }
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(ex.getLocation());
                stringBuffer4.append(ex.getMessage());
                log(stringBuffer4.toString(), 4);
                return;
            }
            throw ex;
        }
    }

    private void tooManyDefinitions() {
        throw new BuildException("Only one of the attributes name,file,resource can be set", getLocation());
    }
}
