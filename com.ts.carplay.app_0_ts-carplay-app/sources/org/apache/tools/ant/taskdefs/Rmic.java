package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.rmic.KaffeRmic;
import org.apache.tools.ant.taskdefs.rmic.RmicAdapter;
import org.apache.tools.ant.taskdefs.rmic.RmicAdapterFactory;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.SourceFileScanner;
import org.apache.tools.ant.util.facade.FacadeTaskHelper;

/* JADX INFO: loaded from: classes.dex */
public class Rmic extends MatchingTask {
    private static final String FAIL_MSG = "Rmic failed; see the compiler error output for details.";
    static /* synthetic */ Class class$java$rmi$Remote;
    private File baseDir;
    private String classname;
    private Path compileClasspath;
    private Path extDirs;
    private FacadeTaskHelper facade;
    private String idlOpts;
    private String iiopOpts;
    private File sourceBase;
    private String stubVersion;
    private boolean verify = false;
    private boolean filtering = false;
    private boolean iiop = false;
    private boolean idl = false;
    private boolean debug = false;
    private boolean includeAntRuntime = true;
    private boolean includeJavaRuntime = false;
    private Vector compileList = new Vector();
    private ClassLoader loader = null;
    private FileUtils fileUtils = FileUtils.newFileUtils();

    public Rmic() {
        try {
            Class.forName("kaffe.rmi.rmic.RMIC");
            this.facade = new FacadeTaskHelper(KaffeRmic.COMPILER_NAME);
        } catch (ClassNotFoundException e) {
            this.facade = new FacadeTaskHelper("sun");
        }
    }

    public void setBase(File base) {
        this.baseDir = base;
    }

    public File getBase() {
        return this.baseDir;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setSourceBase(File sourceBase) {
        this.sourceBase = sourceBase;
    }

    public File getSourceBase() {
        return this.sourceBase;
    }

    public void setStubVersion(String stubVersion) {
        this.stubVersion = stubVersion;
    }

    public String getStubVersion() {
        return this.stubVersion;
    }

    public void setFiltering(boolean filter) {
        this.filtering = filter;
    }

    public boolean getFiltering() {
        return this.filtering;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getDebug() {
        return this.debug;
    }

    public void setClasspath(Path classpath) {
        Path path = this.compileClasspath;
        if (path == null) {
            this.compileClasspath = classpath;
        } else {
            path.append(classpath);
        }
    }

    public Path createClasspath() {
        if (this.compileClasspath == null) {
            this.compileClasspath = new Path(getProject());
        }
        return this.compileClasspath.createPath();
    }

    public void setClasspathRef(Reference pathRef) {
        createClasspath().setRefid(pathRef);
    }

    public Path getClasspath() {
        return this.compileClasspath;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public boolean getVerify() {
        return this.verify;
    }

    public void setIiop(boolean iiop) {
        this.iiop = iiop;
    }

    public boolean getIiop() {
        return this.iiop;
    }

    public void setIiopopts(String iiopOpts) {
        this.iiopOpts = iiopOpts;
    }

    public String getIiopopts() {
        return this.iiopOpts;
    }

    public void setIdl(boolean idl) {
        this.idl = idl;
    }

    public boolean getIdl() {
        return this.idl;
    }

    public void setIdlopts(String idlOpts) {
        this.idlOpts = idlOpts;
    }

    public String getIdlopts() {
        return this.idlOpts;
    }

    public Vector getFileList() {
        return this.compileList;
    }

    public void setIncludeantruntime(boolean include) {
        this.includeAntRuntime = include;
    }

    public boolean getIncludeantruntime() {
        return this.includeAntRuntime;
    }

    public void setIncludejavaruntime(boolean include) {
        this.includeJavaRuntime = include;
    }

    public boolean getIncludejavaruntime() {
        return this.includeJavaRuntime;
    }

    public void setExtdirs(Path extDirs) {
        Path path = this.extDirs;
        if (path == null) {
            this.extDirs = extDirs;
        } else {
            path.append(extDirs);
        }
    }

    public Path createExtdirs() {
        if (this.extDirs == null) {
            this.extDirs = new Path(getProject());
        }
        return this.extDirs.createPath();
    }

    public Path getExtdirs() {
        return this.extDirs;
    }

    public Vector getCompileList() {
        return this.compileList;
    }

    public void setCompiler(String compiler) {
        if (compiler.length() > 0) {
            this.facade.setImplementation(compiler);
        }
    }

    public String getCompiler() {
        this.facade.setMagicValue(getProject().getProperty("build.rmic"));
        return this.facade.getImplementation();
    }

    public ImplementationSpecificArgument createCompilerArg() {
        ImplementationSpecificArgument arg = new ImplementationSpecificArgument();
        this.facade.addImplementationArgument(arg);
        return arg;
    }

    public String[] getCurrentCompilerArgs() {
        getCompiler();
        return this.facade.getArgs();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file = this.baseDir;
        if (file == null) {
            throw new BuildException("base attribute must be set!", getLocation());
        }
        if (!file.exists()) {
            throw new BuildException("base does not exist!", getLocation());
        }
        if (this.verify) {
            log("Verify has been turned on.", 3);
        }
        RmicAdapter adapter = RmicAdapterFactory.getRmic(getCompiler(), this);
        adapter.setRmic(this);
        Path classpath = adapter.getClasspath();
        this.loader = getProject().createClassLoader(classpath);
        try {
            if (this.classname == null) {
                DirectoryScanner ds = getDirectoryScanner(this.baseDir);
                String[] files = ds.getIncludedFiles();
                scanDir(this.baseDir, files, adapter.getMapper());
            } else {
                File file2 = this.baseDir;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.classname.replace('.', File.separatorChar));
                stringBuffer.append(".class");
                scanDir(file2, new String[]{stringBuffer.toString()}, adapter.getMapper());
            }
            int fileCount = this.compileList.size();
            if (fileCount > 0) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("RMI Compiling ");
                stringBuffer2.append(fileCount);
                stringBuffer2.append(" class");
                stringBuffer2.append(fileCount > 1 ? "es" : "");
                stringBuffer2.append(" to ");
                stringBuffer2.append(this.baseDir);
                log(stringBuffer2.toString(), 2);
                if (!adapter.execute()) {
                    throw new BuildException(FAIL_MSG, getLocation());
                }
            }
            if (this.sourceBase != null && !this.baseDir.equals(this.sourceBase) && fileCount > 0) {
                if (this.idl) {
                    log("Cannot determine sourcefiles in idl mode, ", 1);
                    log("sourcebase attribute will be ignored.", 1);
                } else {
                    for (int j = 0; j < fileCount; j++) {
                        moveGeneratedFile(this.baseDir, this.sourceBase, (String) this.compileList.elementAt(j), adapter);
                    }
                }
            }
        } finally {
            this.compileList.removeAllElements();
        }
    }

    private void moveGeneratedFile(File baseDir, File sourceBaseFile, String classname, RmicAdapter adapter) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(classname.replace('.', File.separatorChar));
        stringBuffer.append(".class");
        String classFileName = stringBuffer.toString();
        String[] generatedFiles = adapter.getMapper().mapFileName(classFileName);
        for (String generatedFile : generatedFiles) {
            if (generatedFile.endsWith(".class")) {
                int pos = generatedFile.length() - ".class".length();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(generatedFile.substring(0, pos));
                stringBuffer2.append(".java");
                String sourceFileName = stringBuffer2.toString();
                File oldFile = new File(baseDir, sourceFileName);
                if (oldFile.exists()) {
                    File newFile = new File(sourceBaseFile, sourceFileName);
                    try {
                        if (this.filtering) {
                            this.fileUtils.copyFile(oldFile, newFile, new FilterSetCollection(getProject().getGlobalFilterSet()));
                        } else {
                            this.fileUtils.copyFile(oldFile, newFile);
                        }
                        oldFile.delete();
                    } catch (IOException ioe) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Failed to copy ");
                        stringBuffer3.append(oldFile);
                        stringBuffer3.append(" to ");
                        stringBuffer3.append(newFile);
                        stringBuffer3.append(" due to ");
                        stringBuffer3.append(ioe.getMessage());
                        String msg = stringBuffer3.toString();
                        throw new BuildException(msg, ioe, getLocation());
                    }
                }
            }
        }
    }

    protected void scanDir(File baseDir, String[] files, FileNameMapper mapper) {
        String str;
        String[] newFiles = files;
        if (this.idl) {
            log("will leave uptodate test to rmic implementation in idl mode.", 3);
        } else if (this.iiop && (str = this.iiopOpts) != null && str.indexOf("-always") > -1) {
            log("no uptodate test as -always option has been specified", 3);
        } else {
            SourceFileScanner sfs = new SourceFileScanner(this);
            newFiles = sfs.restrict(files, baseDir, baseDir, mapper);
        }
        for (String str2 : newFiles) {
            String classname = str2.replace(File.separatorChar, '.');
            this.compileList.addElement(classname.substring(0, classname.lastIndexOf(".class")));
        }
    }

    public boolean isValidRmiRemote(String classname) {
        try {
            try {
                Class<?> clsLoadClass = this.loader.loadClass(classname);
                try {
                    if (clsLoadClass.isInterface() && !this.iiop && !this.idl) {
                        return false;
                    }
                    return isValidRmiRemote(clsLoadClass);
                } catch (NoClassDefFoundError e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unable to verify class ");
                    stringBuffer.append(classname);
                    stringBuffer.append(". It is not defined.");
                    log(stringBuffer.toString(), 1);
                    return false;
                } catch (Throwable th) {
                    t = th;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Unable to verify class ");
                    stringBuffer2.append(classname);
                    stringBuffer2.append(". Loading caused Exception: ");
                    stringBuffer2.append(t.getMessage());
                    log(stringBuffer2.toString(), 1);
                    return false;
                }
            } catch (NoClassDefFoundError e2) {
            } catch (Throwable th2) {
                t = th2;
            }
        } catch (ClassNotFoundException e3) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Unable to verify class ");
            stringBuffer3.append(classname);
            stringBuffer3.append(". It could not be found.");
            log(stringBuffer3.toString(), 1);
            return false;
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public Class getRemoteInterface(Class testClass) {
        Class<?>[] interfaces;
        Class clsClass$ = class$java$rmi$Remote;
        if (clsClass$ == null) {
            clsClass$ = class$("java.rmi.Remote");
            class$java$rmi$Remote = clsClass$;
        }
        if (clsClass$.isAssignableFrom(testClass) && (interfaces = testClass.getInterfaces()) != null) {
            for (int i = 0; i < interfaces.length; i++) {
                Class clsClass$2 = class$java$rmi$Remote;
                if (clsClass$2 == null) {
                    clsClass$2 = class$("java.rmi.Remote");
                    class$java$rmi$Remote = clsClass$2;
                }
                if (clsClass$2.isAssignableFrom(interfaces[i])) {
                    return interfaces[i];
                }
            }
            return null;
        }
        return null;
    }

    private boolean isValidRmiRemote(Class testClass) {
        return getRemoteInterface(testClass) != null;
    }

    public ClassLoader getLoader() {
        return this.loader;
    }

    public class ImplementationSpecificArgument extends org.apache.tools.ant.util.facade.ImplementationSpecificArgument {
        public ImplementationSpecificArgument() {
        }

        public void setCompiler(String impl) {
            super.setImplementation(impl);
        }
    }
}
