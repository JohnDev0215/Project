package org.apache.tools.ant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.CollectionUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.LoaderUtils;

/* JADX INFO: loaded from: classes.dex */
public class AntClassLoader extends ClassLoader implements SubBuildListener {
    private static final int BUFFER_SIZE = 8192;
    private static final FileUtils FILE_UTILS = FileUtils.newFileUtils();
    private static final int NUMBER_OF_STRINGS = 256;
    static /* synthetic */ Class array$B;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$ClassLoader;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$tools$ant$AntClassLoader;
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    private static Method defineClassProtectionDomain;
    private static Method getProtectionDomain;
    private boolean ignoreBase;
    private boolean isContextLoaderSaved;
    private Vector loaderPackages;
    private ClassLoader parent;
    private boolean parentFirst;
    private Vector pathComponents;
    private Project project;
    private ClassLoader savedContextLoader;
    private Vector systemPackages;
    private Hashtable zipFiles;

    static {
        Class clsClass$;
        Class<?> clsClass$2;
        Class<?> clsClass$3;
        Class clsClass$4;
        getProtectionDomain = null;
        defineClassProtectionDomain = null;
        try {
            if (class$java$lang$Class == null) {
                clsClass$ = class$("java.lang.Class");
                class$java$lang$Class = clsClass$;
            } else {
                clsClass$ = class$java$lang$Class;
            }
            getProtectionDomain = clsClass$.getMethod("getProtectionDomain", new Class[0]);
            Class<?> cls = Class.forName("java.security.ProtectionDomain");
            Class<?>[] clsArr = new Class[5];
            if (class$java$lang$String == null) {
                clsClass$2 = class$("java.lang.String");
                class$java$lang$String = clsClass$2;
            } else {
                clsClass$2 = class$java$lang$String;
            }
            clsArr[0] = clsClass$2;
            if (array$B == null) {
                clsClass$3 = class$("[B");
                array$B = clsClass$3;
            } else {
                clsClass$3 = array$B;
            }
            clsArr[1] = clsClass$3;
            clsArr[2] = Integer.TYPE;
            clsArr[3] = Integer.TYPE;
            clsArr[4] = cls;
            if (class$java$lang$ClassLoader == null) {
                clsClass$4 = class$("java.lang.ClassLoader");
                class$java$lang$ClassLoader = clsClass$4;
            } else {
                clsClass$4 = class$java$lang$ClassLoader;
            }
            defineClassProtectionDomain = clsClass$4.getDeclaredMethod("defineClass", clsArr);
        } catch (Exception e) {
        }
    }

    private class ResourceEnumeration implements Enumeration {
        private URL nextResource;
        private int pathElementsIndex = 0;
        private String resourceName;

        ResourceEnumeration(String name) {
            this.resourceName = name;
            findNextResource();
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.nextResource != null;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            URL ret = this.nextResource;
            findNextResource();
            return ret;
        }

        private void findNextResource() {
            URL url = null;
            while (this.pathElementsIndex < AntClassLoader.this.pathComponents.size() && url == null) {
                try {
                    File pathComponent = (File) AntClassLoader.this.pathComponents.elementAt(this.pathElementsIndex);
                    url = AntClassLoader.this.getResourceURL(pathComponent, this.resourceName);
                    this.pathElementsIndex++;
                } catch (BuildException e) {
                }
            }
            this.nextResource = url;
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public AntClassLoader() {
        this.pathComponents = new Vector();
        this.parentFirst = true;
        this.systemPackages = new Vector();
        this.loaderPackages = new Vector();
        this.ignoreBase = false;
        this.parent = null;
        this.zipFiles = new Hashtable();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(null);
    }

    public AntClassLoader(Project project, Path classpath) {
        this.pathComponents = new Vector();
        this.parentFirst = true;
        this.systemPackages = new Vector();
        this.loaderPackages = new Vector();
        this.ignoreBase = false;
        this.parent = null;
        this.zipFiles = new Hashtable();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(null);
        setProject(project);
        setClassPath(classpath);
    }

    public AntClassLoader(ClassLoader parent, Project project, Path classpath, boolean parentFirst) {
        this(project, classpath);
        if (parent != null) {
            setParent(parent);
        }
        setParentFirst(parentFirst);
        addJavaLibraries();
    }

    public AntClassLoader(Project project, Path classpath, boolean parentFirst) {
        this(null, project, classpath, parentFirst);
    }

    public AntClassLoader(ClassLoader parent, boolean parentFirst) {
        this.pathComponents = new Vector();
        this.parentFirst = true;
        this.systemPackages = new Vector();
        this.loaderPackages = new Vector();
        this.ignoreBase = false;
        this.parent = null;
        this.zipFiles = new Hashtable();
        this.savedContextLoader = null;
        this.isContextLoaderSaved = false;
        setParent(parent);
        this.project = null;
        this.parentFirst = parentFirst;
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null) {
            project.addBuildListener(this);
        }
    }

    public void setClassPath(Path classpath) {
        this.pathComponents.removeAllElements();
        if (classpath != null) {
            Path actualClasspath = classpath.concatSystemClasspath("ignore");
            String[] pathElements = actualClasspath.list();
            for (String str : pathElements) {
                try {
                    addPathElement(str);
                } catch (BuildException e) {
                }
            }
        }
    }

    public void setParent(ClassLoader parent) {
        if (parent == null) {
            Class clsClass$ = class$org$apache$tools$ant$AntClassLoader;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.AntClassLoader");
                class$org$apache$tools$ant$AntClassLoader = clsClass$;
            }
            this.parent = clsClass$.getClassLoader();
            return;
        }
        this.parent = parent;
    }

    public void setParentFirst(boolean parentFirst) {
        this.parentFirst = parentFirst;
    }

    protected void log(String message, int priority) {
        Project project = this.project;
        if (project != null) {
            project.log(message, priority);
        }
    }

    public void setThreadContextLoader() {
        if (this.isContextLoaderSaved) {
            throw new BuildException("Context loader has not been reset");
        }
        if (LoaderUtils.isContextLoaderAvailable()) {
            this.savedContextLoader = LoaderUtils.getContextClassLoader();
            ClassLoader loader = this;
            Project project = this.project;
            if (project != null && "only".equals(project.getProperty("build.sysclasspath"))) {
                loader = getClass().getClassLoader();
            }
            LoaderUtils.setContextClassLoader(loader);
            this.isContextLoaderSaved = true;
        }
    }

    public void resetThreadContextLoader() {
        if (LoaderUtils.isContextLoaderAvailable() && this.isContextLoaderSaved) {
            LoaderUtils.setContextClassLoader(this.savedContextLoader);
            this.savedContextLoader = null;
            this.isContextLoaderSaved = false;
        }
    }

    public void addPathElement(String pathElement) throws BuildException {
        Project project = this.project;
        File pathComponent = project != null ? project.resolveFile(pathElement) : new File(pathElement);
        try {
            addPathFile(pathComponent);
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    protected void addPathFile(File pathComponent) throws IOException {
        this.pathComponents.addElement(pathComponent);
    }

    public String getClasspath() {
        StringBuffer sb = new StringBuffer();
        boolean firstPass = true;
        Enumeration componentEnum = this.pathComponents.elements();
        while (componentEnum.hasMoreElements()) {
            if (!firstPass) {
                sb.append(System.getProperty("path.separator"));
            } else {
                firstPass = false;
            }
            sb.append(((File) componentEnum.nextElement()).getAbsolutePath());
        }
        return sb.toString();
    }

    public synchronized void setIsolated(boolean isolated) {
        this.ignoreBase = isolated;
    }

    public static void initializeClass(Class theClass) {
        Constructor<?>[] declaredConstructors = theClass.getDeclaredConstructors();
        if (declaredConstructors != null && declaredConstructors.length > 0 && declaredConstructors[0] != null) {
            String[] strs = new String[256];
            try {
                declaredConstructors[0].newInstance(strs);
            } catch (Throwable th) {
            }
        }
    }

    public void addSystemPackageRoot(String packageRoot) {
        Vector vector = this.systemPackages;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(packageRoot);
        stringBuffer.append(packageRoot.endsWith(".") ? "" : ".");
        vector.addElement(stringBuffer.toString());
    }

    public void addLoaderPackageRoot(String packageRoot) {
        Vector vector = this.loaderPackages;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(packageRoot);
        stringBuffer.append(packageRoot.endsWith(".") ? "" : ".");
        vector.addElement(stringBuffer.toString());
    }

    public Class forceLoadClass(String classname) throws ClassNotFoundException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("force loading ");
        stringBuffer.append(classname);
        log(stringBuffer.toString(), 4);
        Class<?> clsFindLoadedClass = findLoadedClass(classname);
        if (clsFindLoadedClass == null) {
            Class theClass = findClass(classname);
            return theClass;
        }
        return clsFindLoadedClass;
    }

    public Class forceLoadSystemClass(String classname) throws ClassNotFoundException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("force system loading ");
        stringBuffer.append(classname);
        log(stringBuffer.toString(), 4);
        Class<?> clsFindLoadedClass = findLoadedClass(classname);
        if (clsFindLoadedClass == null) {
            Class theClass = findBaseClass(classname);
            return theClass;
        }
        return clsFindLoadedClass;
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String name) {
        InputStream resourceStream;
        if (isParentFirst(name)) {
            resourceStream = loadBaseResource(name);
            if (resourceStream != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("ResourceStream for ");
                stringBuffer.append(name);
                stringBuffer.append(" loaded from parent loader");
                log(stringBuffer.toString(), 4);
            } else {
                resourceStream = loadResource(name);
                if (resourceStream != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("ResourceStream for ");
                    stringBuffer2.append(name);
                    stringBuffer2.append(" loaded from ant loader");
                    log(stringBuffer2.toString(), 4);
                }
            }
        } else {
            resourceStream = loadResource(name);
            if (resourceStream != null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("ResourceStream for ");
                stringBuffer3.append(name);
                stringBuffer3.append(" loaded from ant loader");
                log(stringBuffer3.toString(), 4);
            } else {
                resourceStream = loadBaseResource(name);
                if (resourceStream != null) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("ResourceStream for ");
                    stringBuffer4.append(name);
                    stringBuffer4.append(" loaded from parent loader");
                    log(stringBuffer4.toString(), 4);
                }
            }
        }
        if (resourceStream == null) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Couldn't load ResourceStream for ");
            stringBuffer5.append(name);
            log(stringBuffer5.toString(), 4);
        }
        return resourceStream;
    }

    private InputStream loadResource(String name) {
        InputStream stream = null;
        Enumeration e = this.pathComponents.elements();
        while (e.hasMoreElements() && stream == null) {
            File pathComponent = (File) e.nextElement();
            stream = getResourceStream(pathComponent, name);
        }
        return stream;
    }

    private InputStream loadBaseResource(String name) {
        ClassLoader classLoader = this.parent;
        if (classLoader == null) {
            return ClassLoader.getSystemResourceAsStream(name);
        }
        return classLoader.getResourceAsStream(name);
    }

    private InputStream getResourceStream(File file, String resourceName) {
        try {
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Ignoring Exception ");
            stringBuffer.append(e.getClass().getName());
            stringBuffer.append(": ");
            stringBuffer.append(e.getMessage());
            stringBuffer.append(" reading resource ");
            stringBuffer.append(resourceName);
            stringBuffer.append(" from ");
            stringBuffer.append(file);
            log(stringBuffer.toString(), 3);
        }
        if (!file.exists()) {
            return null;
        }
        if (file.isDirectory()) {
            File resource = new File(file, resourceName);
            if (resource.exists()) {
                return new FileInputStream(resource);
            }
        } else {
            ZipFile zipFile = (ZipFile) this.zipFiles.get(file);
            if (zipFile == null) {
                zipFile = new ZipFile(file);
                this.zipFiles.put(file, zipFile);
            }
            ZipEntry entry = zipFile.getEntry(resourceName);
            if (entry != null) {
                return zipFile.getInputStream(entry);
            }
        }
        return null;
    }

    private boolean isParentFirst(String resourceName) {
        boolean useParentFirst;
        boolean useParentFirst2 = this.parentFirst;
        Enumeration e = this.systemPackages.elements();
        while (true) {
            if (!e.hasMoreElements()) {
                useParentFirst = useParentFirst2;
                break;
            }
            String packageName = (String) e.nextElement();
            if (resourceName.startsWith(packageName)) {
                useParentFirst = true;
                break;
            }
        }
        Enumeration e2 = this.loaderPackages.elements();
        while (e2.hasMoreElements()) {
            String packageName2 = (String) e2.nextElement();
            if (resourceName.startsWith(packageName2)) {
                return false;
            }
        }
        return useParentFirst;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String name) {
        URL url = null;
        if (isParentFirst(name)) {
            ClassLoader classLoader = this.parent;
            url = classLoader == null ? super.getResource(name) : classLoader.getResource(name);
        }
        if (url != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Resource ");
            stringBuffer.append(name);
            stringBuffer.append(" loaded from parent loader");
            log(stringBuffer.toString(), 4);
        } else {
            Enumeration e = this.pathComponents.elements();
            while (e.hasMoreElements() && url == null) {
                File pathComponent = (File) e.nextElement();
                url = getResourceURL(pathComponent, name);
                if (url != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Resource ");
                    stringBuffer2.append(name);
                    stringBuffer2.append(" loaded from ant loader");
                    log(stringBuffer2.toString(), 4);
                }
            }
        }
        if (url == null && !isParentFirst(name)) {
            ClassLoader classLoader2 = this.parent;
            url = classLoader2 == null ? super.getResource(name) : classLoader2.getResource(name);
            if (url != null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Resource ");
                stringBuffer3.append(name);
                stringBuffer3.append(" loaded from parent loader");
                log(stringBuffer3.toString(), 4);
            }
        }
        if (url == null) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Couldn't load Resource ");
            stringBuffer4.append(name);
            log(stringBuffer4.toString(), 4);
        }
        return url;
    }

    @Override // java.lang.ClassLoader
    protected Enumeration findResources(String name) throws IOException {
        Enumeration<URL> emptyEnumeration;
        Enumeration mine = new ResourceEnumeration(name);
        ClassLoader classLoader = this.parent;
        if (classLoader != null && classLoader != getParent()) {
            emptyEnumeration = this.parent.getResources(name);
        } else {
            emptyEnumeration = new CollectionUtils.EmptyEnumeration();
        }
        if (isParentFirst(name)) {
            return CollectionUtils.append(emptyEnumeration, mine);
        }
        return CollectionUtils.append(mine, emptyEnumeration);
    }

    protected URL getResourceURL(File file, String resourceName) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            return null;
        }
        if (file.isDirectory()) {
            File resource = new File(file, resourceName);
            if (resource.exists()) {
                try {
                    return FILE_UTILS.getFileURL(resource);
                } catch (MalformedURLException e2) {
                    return null;
                }
            }
        } else {
            ZipFile zipFile = (ZipFile) this.zipFiles.get(file);
            if (zipFile == null) {
                zipFile = new ZipFile(file);
                this.zipFiles.put(file, zipFile);
            }
            ZipEntry entry = zipFile.getEntry(resourceName);
            if (entry != null) {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("jar:");
                    stringBuffer.append(FILE_UTILS.getFileURL(file));
                    stringBuffer.append("!/");
                    stringBuffer.append(entry);
                    return new URL(stringBuffer.toString());
                } catch (MalformedURLException e3) {
                    return null;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009f A[Catch: all -> 0x00a5, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0009, B:10:0x0010, B:22:0x009f, B:15:0x005a, B:13:0x0035, B:18:0x007a, B:20:0x007e, B:25:0x00a4), top: B:31:0x0001, inners: #0, #2 }] */
    @Override // java.lang.ClassLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized java.lang.Class loadClass(java.lang.String r6, boolean r7) throws java.lang.ClassNotFoundException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.Class r0 = r5.findLoadedClass(r6)     // Catch: java.lang.Throwable -> La5
            if (r0 == 0) goto L9
            monitor-exit(r5)
            return r0
        L9:
            boolean r1 = r5.isParentFirst(r6)     // Catch: java.lang.Throwable -> La5
            r2 = 4
            if (r1 == 0) goto L5a
            java.lang.Class r1 = r5.findBaseClass(r6)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            r0 = r1
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            r1.<init>()     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            java.lang.String r3 = "Class "
            r1.append(r3)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            r1.append(r6)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            java.lang.String r3 = " loaded from parent loader "
            r1.append(r3)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            java.lang.String r3 = "(parentFirst)"
            r1.append(r3)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            java.lang.String r1 = r1.toString()     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            r5.log(r1, r2)     // Catch: java.lang.ClassNotFoundException -> L34 java.lang.Throwable -> La5
            goto L59
        L34:
            r1 = move-exception
            java.lang.Class r3 = r5.findClass(r6)     // Catch: java.lang.Throwable -> La5
            r0 = r3
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> La5
            r3.<init>()     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = "Class "
            r3.append(r4)     // Catch: java.lang.Throwable -> La5
            r3.append(r6)     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = " loaded from ant loader "
            r3.append(r4)     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = "(parentFirst)"
            r3.append(r4)     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> La5
            r5.log(r3, r2)     // Catch: java.lang.Throwable -> La5
        L59:
            goto L9d
        L5a:
            java.lang.Class r1 = r5.findClass(r6)     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            r0 = r1
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            r1.<init>()     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            java.lang.String r3 = "Class "
            r1.append(r3)     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            r1.append(r6)     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            java.lang.String r3 = " loaded from ant loader"
            r1.append(r3)     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            java.lang.String r1 = r1.toString()     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            r5.log(r1, r2)     // Catch: java.lang.ClassNotFoundException -> L79 java.lang.Throwable -> La5
            goto L9d
        L79:
            r1 = move-exception
            boolean r3 = r5.ignoreBase     // Catch: java.lang.Throwable -> La5
            if (r3 != 0) goto La4
            java.lang.Class r3 = r5.findBaseClass(r6)     // Catch: java.lang.Throwable -> La5
            r0 = r3
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> La5
            r3.<init>()     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = "Class "
            r3.append(r4)     // Catch: java.lang.Throwable -> La5
            r3.append(r6)     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = " loaded from parent loader"
            r3.append(r4)     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> La5
            r5.log(r3, r2)     // Catch: java.lang.Throwable -> La5
        L9d:
            if (r7 == 0) goto La2
            r5.resolveClass(r0)     // Catch: java.lang.Throwable -> La5
        La2:
            monitor-exit(r5)
            return r0
        La4:
            throw r1     // Catch: java.lang.Throwable -> La5
        La5:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.AntClassLoader.loadClass(java.lang.String, boolean):java.lang.Class");
    }

    private String getClassFilename(String classname) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(classname.replace('.', '/'));
        stringBuffer.append(".class");
        return stringBuffer.toString();
    }

    protected Class defineClassFromData(File container, byte[] classData, String classname) throws IOException {
        Class clsClass$;
        if (defineClassProtectionDomain != null) {
            try {
                Method method = getProtectionDomain;
                if (class$org$apache$tools$ant$Project == null) {
                    clsClass$ = class$("org.apache.tools.ant.Project");
                    class$org$apache$tools$ant$Project = clsClass$;
                } else {
                    clsClass$ = class$org$apache$tools$ant$Project;
                }
                Object domain = method.invoke(clsClass$, new Object[0]);
                Object[] args = {classname, classData, new Integer(0), new Integer(classData.length), domain};
                return (Class) defineClassProtectionDomain.invoke(this, args);
            } catch (InvocationTargetException ite) {
                Throwable t = ite.getTargetException();
                if (t instanceof ClassFormatError) {
                    throw ((ClassFormatError) t);
                }
                if (t instanceof NoClassDefFoundError) {
                    throw ((NoClassDefFoundError) t);
                }
                if (t instanceof SecurityException) {
                    throw ((SecurityException) t);
                }
                throw new IOException(t.toString());
            } catch (Exception e) {
                throw new IOException(e.toString());
            }
        }
        return defineClass(classname, classData, 0, classData.length);
    }

    private Class getClassFromStream(InputStream stream, String classname, File container) throws IOException, SecurityException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        while (true) {
            int bytesRead = stream.read(buffer, 0, 8192);
            if (bytesRead != -1) {
                baos.write(buffer, 0, bytesRead);
            } else {
                byte[] classData = baos.toByteArray();
                return defineClassFromData(container, classData, classname);
            }
        }
    }

    @Override // java.lang.ClassLoader
    public Class findClass(String name) throws ClassNotFoundException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Finding class ");
        stringBuffer.append(name);
        log(stringBuffer.toString(), 4);
        return findClassInComponents(name);
    }

    protected boolean isInPath(File component) {
        Enumeration e = this.pathComponents.elements();
        while (e.hasMoreElements()) {
            File pathComponent = (File) e.nextElement();
            if (pathComponent.equals(component)) {
                return true;
            }
        }
        return false;
    }

    private Class findClassInComponents(String name) throws ClassNotFoundException {
        InputStream stream = null;
        String classFilename = getClassFilename(name);
        try {
            Enumeration e = this.pathComponents.elements();
            while (e.hasMoreElements()) {
                File pathComponent = (File) e.nextElement();
                try {
                    stream = getResourceStream(pathComponent, classFilename);
                    if (stream != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Loaded from ");
                        stringBuffer.append(pathComponent);
                        stringBuffer.append(" ");
                        stringBuffer.append(classFilename);
                        log(stringBuffer.toString(), 4);
                        return getClassFromStream(stream, name, pathComponent);
                    }
                    continue;
                } catch (IOException ioe) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Exception reading component ");
                    stringBuffer2.append(pathComponent);
                    stringBuffer2.append(" (reason: ");
                    stringBuffer2.append(ioe.getMessage());
                    stringBuffer2.append(")");
                    log(stringBuffer2.toString(), 3);
                } catch (SecurityException se) {
                    throw se;
                }
            }
            throw new ClassNotFoundException(name);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    private Class findBaseClass(String name) throws ClassNotFoundException {
        ClassLoader classLoader = this.parent;
        if (classLoader == null) {
            return findSystemClass(name);
        }
        return classLoader.loadClass(name);
    }

    public synchronized void cleanup() {
        Enumeration e = this.zipFiles.elements();
        while (e.hasMoreElements()) {
            ZipFile zipFile = (ZipFile) e.nextElement();
            try {
                zipFile.close();
            } catch (IOException e2) {
            }
        }
        this.zipFiles = new Hashtable();
        if (this.project != null) {
            this.project.removeBuildListener(this);
        }
        this.project = null;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent event) {
        cleanup();
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildFinished(BuildEvent event) {
        if (event.getProject() == this.project) {
            cleanup();
        }
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) {
    }

    public void addJavaLibraries() {
        Vector packages = JavaEnvUtils.getJrePackages();
        Enumeration e = packages.elements();
        while (e.hasMoreElements()) {
            String packageName = (String) e.nextElement();
            addSystemPackageRoot(packageName);
        }
    }
}
