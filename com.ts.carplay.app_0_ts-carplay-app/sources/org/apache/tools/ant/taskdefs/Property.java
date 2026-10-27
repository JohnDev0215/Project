package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class Property extends Task {
    protected Path classpath;
    protected String env;
    private Project fallback;
    protected File file;
    protected String name;
    protected String prefix;
    protected Reference ref;
    protected String resource;
    protected URL url;
    protected boolean userProperty;
    protected String value;

    public Property() {
        this(false);
    }

    protected Property(boolean userProperty) {
        this(userProperty, null);
    }

    protected Property(boolean userProperty, Project fallback) {
        this.userProperty = userProperty;
        this.fallback = fallback;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(File location) {
        setValue(location.getAbsolutePath());
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        if (!prefix.endsWith(".")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.prefix);
            stringBuffer.append(".");
            this.prefix = stringBuffer.toString();
        }
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setRefid(Reference ref) {
        this.ref = ref;
    }

    public Reference getRefid() {
        return this.ref;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return this.resource;
    }

    public void setEnvironment(String env) {
        this.env = env;
    }

    public String getEnvironment() {
        return this.env;
    }

    public void setClasspath(Path classpath) {
        Path path = this.classpath;
        if (path == null) {
            this.classpath = classpath;
        } else {
            path.append(classpath);
        }
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

    public Path getClasspath() {
        return this.classpath;
    }

    public void setUserProperty(boolean userProperty) {
        log("DEPRECATED: Ignoring request to set user property in Property task.", 1);
    }

    public String toString() {
        String str = this.value;
        return str == null ? "" : str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        Reference reference;
        String str;
        if (getProject() == null) {
            throw new IllegalStateException("project has not been set");
        }
        if (this.name != null) {
            if (this.value == null && this.ref == null) {
                throw new BuildException("You must specify value, location or refid with the name attribute", getLocation());
            }
        } else if (this.url == null && this.file == null && this.resource == null && this.env == null) {
            throw new BuildException("You must specify url, file, resource or environment when not using the name attribute", getLocation());
        }
        if (this.url == null && this.file == null && this.resource == null && this.prefix != null) {
            throw new BuildException("Prefix is only valid when loading from a url, file or resource", getLocation());
        }
        String str2 = this.name;
        if (str2 != null && (str = this.value) != null) {
            addProperty(str2, str);
        }
        File file = this.file;
        if (file != null) {
            loadFile(file);
        }
        URL url = this.url;
        if (url != null) {
            loadUrl(url);
        }
        String str3 = this.resource;
        if (str3 != null) {
            loadResource(str3);
        }
        String str4 = this.env;
        if (str4 != null) {
            loadEnvironment(str4);
        }
        String str5 = this.name;
        if (str5 != null && (reference = this.ref) != null) {
            try {
                addProperty(str5, reference.getReferencedObject(getProject()).toString());
            } catch (BuildException be) {
                Project project = this.fallback;
                if (project != null) {
                    addProperty(this.name, this.ref.getReferencedObject(project).toString());
                    return;
                }
                throw be;
            }
        }
    }

    protected void loadUrl(URL url) throws BuildException {
        Properties props = new Properties();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Loading ");
        stringBuffer.append(url);
        log(stringBuffer.toString(), 3);
        try {
            InputStream is = url.openStream();
            try {
                props.load(is);
                addProperties(props);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        } catch (IOException ex) {
            throw new BuildException(ex, getLocation());
        }
    }

    protected void loadFile(File file) throws BuildException {
        Properties props = new Properties();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Loading ");
        stringBuffer.append(file.getAbsolutePath());
        log(stringBuffer.toString(), 3);
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                try {
                    props.load(fis);
                    fis.close();
                    addProperties(props);
                    return;
                } catch (Throwable th) {
                    fis.close();
                    throw th;
                }
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to find property file: ");
            stringBuffer2.append(file.getAbsolutePath());
            log(stringBuffer2.toString(), 3);
        } catch (IOException ex) {
            throw new BuildException(ex, getLocation());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void loadResource(java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Resource Loading "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r2 = 3
            r5.log(r1, r2)
            r1 = 0
            r2 = 0
            org.apache.tools.ant.types.Path r3 = r5.classpath     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            if (r3 == 0) goto L2c
            org.apache.tools.ant.Project r3 = r5.getProject()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            org.apache.tools.ant.types.Path r4 = r5.classpath     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            org.apache.tools.ant.AntClassLoader r3 = r3.createClassLoader(r4)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r2 = r3
            goto L35
        L2c:
            java.lang.Class r3 = r5.getClass()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r2 = r3
        L35:
            if (r2 != 0) goto L3d
            java.io.InputStream r3 = java.lang.ClassLoader.getSystemResourceAsStream(r6)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r1 = r3
            goto L42
        L3d:
            java.io.InputStream r3 = r2.getResourceAsStream(r6)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r1 = r3
        L42:
            if (r1 == 0) goto L4b
            r0.load(r1)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r5.addProperties(r0)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            goto L60
        L4b:
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r3.<init>()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            java.lang.String r4 = "Unable to find resource "
            r3.append(r4)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r3.append(r6)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r4 = 1
            r5.log(r3, r4)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
        L60:
            if (r1 == 0) goto L68
            r1.close()     // Catch: java.io.IOException -> L67
            goto L68
        L67:
            r3 = move-exception
        L68:
            return
        L6b:
            r3 = move-exception
            goto L79
        L6d:
            r2 = move-exception
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L78
            org.apache.tools.ant.Location r4 = r5.getLocation()     // Catch: java.lang.Throwable -> L78
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L78
            throw r3     // Catch: java.lang.Throwable -> L78
        L78:
            r3 = move-exception
        L79:
            if (r1 == 0) goto L80
            r1.close()     // Catch: java.io.IOException -> L7f
            goto L80
        L7f:
            r4 = move-exception
        L80:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Property.loadResource(java.lang.String):void");
    }

    protected void loadEnvironment(String prefix) {
        Properties props = new Properties();
        if (!prefix.endsWith(".")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(prefix);
            stringBuffer.append(".");
            prefix = stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Loading Environment ");
        stringBuffer2.append(prefix);
        log(stringBuffer2.toString(), 3);
        Vector osEnv = Execute.getProcEnvironment();
        Enumeration e = osEnv.elements();
        while (e.hasMoreElements()) {
            String entry = (String) e.nextElement();
            int pos = entry.indexOf(61);
            if (pos == -1) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Ignoring: ");
                stringBuffer3.append(entry);
                log(stringBuffer3.toString(), 1);
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(prefix);
                stringBuffer4.append(entry.substring(0, pos));
                props.put(stringBuffer4.toString(), entry.substring(pos + 1));
            }
        }
        addProperties(props);
    }

    protected void addProperties(Properties props) {
        resolveAllProperties(props);
        Enumeration e = props.keys();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String value = props.getProperty(name);
            String v = getProject().replaceProperties(value);
            if (this.prefix != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.prefix);
                stringBuffer.append(name);
                name = stringBuffer.toString();
            }
            addProperty(name, v);
        }
    }

    protected void addProperty(String n, String v) {
        if (this.userProperty) {
            if (getProject().getUserProperty(n) == null) {
                getProject().setInheritedProperty(n, v);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Override ignored for ");
            stringBuffer.append(n);
            log(stringBuffer.toString(), 3);
            return;
        }
        getProject().setNewProperty(n, v);
    }

    private void resolveAllProperties(Properties props) throws BuildException {
        Enumeration e = props.keys();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            Stack referencesSeen = new Stack();
            resolve(props, name, referencesSeen);
        }
    }

    private void resolve(Properties props, String name, Stack referencesSeen) throws BuildException {
        String propertyName;
        if (referencesSeen.contains(name)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Property ");
            stringBuffer.append(name);
            stringBuffer.append(" was circularly ");
            stringBuffer.append("defined.");
            throw new BuildException(stringBuffer.toString());
        }
        String value = props.getProperty(name);
        Vector fragments = new Vector();
        Vector propertyRefs = new Vector();
        ProjectHelper.parsePropertyString(value, fragments, propertyRefs);
        if (propertyRefs.size() != 0) {
            referencesSeen.push(name);
            StringBuffer sb = new StringBuffer();
            Enumeration i = fragments.elements();
            Enumeration j = propertyRefs.elements();
            while (i.hasMoreElements()) {
                String fragment = (String) i.nextElement();
                if (fragment == null && (fragment = getProject().getProperty((propertyName = (String) j.nextElement()))) == null) {
                    if (props.containsKey(propertyName)) {
                        resolve(props, propertyName, referencesSeen);
                        fragment = props.getProperty(propertyName);
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("${");
                        stringBuffer2.append(propertyName);
                        stringBuffer2.append("}");
                        fragment = stringBuffer2.toString();
                    }
                }
                sb.append(fragment);
            }
            String value2 = sb.toString();
            props.put(name, value2);
            referencesSeen.pop();
        }
    }
}
