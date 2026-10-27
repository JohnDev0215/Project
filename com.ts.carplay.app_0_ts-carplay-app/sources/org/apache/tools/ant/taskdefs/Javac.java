package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.compilers.CompilerAdapter;
import org.apache.tools.ant.taskdefs.compilers.CompilerAdapterFactory;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.GlobPatternMapper;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.SourceFileScanner;
import org.apache.tools.ant.util.facade.FacadeTaskHelper;

/* JADX INFO: loaded from: classes.dex */
public class Javac extends MatchingTask {
    private static final String FAIL_MSG = "Compile failed; see the compiler error output for details.";
    private Path bootclasspath;
    private Path compileClasspath;
    private Path compileSourcepath;
    private String debugLevel;
    private File destDir;
    private String encoding;
    private Path extdirs;
    private FacadeTaskHelper facade;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String source;
    private Path src;
    private String target;
    private File tmpDir;
    private boolean debug = false;
    private boolean optimize = false;
    private boolean deprecation = false;
    private boolean depend = false;
    private boolean verbose = false;
    private boolean includeAntRuntime = true;
    private boolean includeJavaRuntime = false;
    private boolean fork = false;
    private String forkedExecutable = null;
    private boolean nowarn = false;
    protected boolean failOnError = true;
    protected boolean listFiles = false;
    protected File[] compileList = new File[0];

    public Javac() {
        this.facade = null;
        if (JavaEnvUtils.isJavaVersion("1.1")) {
            this.facade = new FacadeTaskHelper("javac1.1");
            return;
        }
        if (JavaEnvUtils.isJavaVersion("1.2")) {
            this.facade = new FacadeTaskHelper("javac1.2");
            return;
        }
        if (JavaEnvUtils.isJavaVersion("1.3")) {
            this.facade = new FacadeTaskHelper("javac1.3");
            return;
        }
        if (JavaEnvUtils.isJavaVersion("1.4")) {
            this.facade = new FacadeTaskHelper("javac1.4");
        } else if (JavaEnvUtils.isJavaVersion(JavaEnvUtils.JAVA_1_5)) {
            this.facade = new FacadeTaskHelper("javac1.5");
        } else {
            this.facade = new FacadeTaskHelper("classic");
        }
    }

    public String getDebugLevel() {
        return this.debugLevel;
    }

    public void setDebugLevel(String v) {
        this.debugLevel = v;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String v) {
        this.source = v;
    }

    public Path createSrc() {
        if (this.src == null) {
            this.src = new Path(getProject());
        }
        return this.src.createPath();
    }

    protected Path recreateSrc() {
        this.src = null;
        return createSrc();
    }

    public void setSrcdir(Path srcDir) {
        Path path = this.src;
        if (path == null) {
            this.src = srcDir;
        } else {
            path.append(srcDir);
        }
    }

    public Path getSrcdir() {
        return this.src;
    }

    public void setDestdir(File destDir) {
        this.destDir = destDir;
    }

    public File getDestdir() {
        return this.destDir;
    }

    public void setSourcepath(Path sourcepath) {
        Path path = this.compileSourcepath;
        if (path == null) {
            this.compileSourcepath = sourcepath;
        } else {
            path.append(sourcepath);
        }
    }

    public Path getSourcepath() {
        return this.compileSourcepath;
    }

    public Path createSourcepath() {
        if (this.compileSourcepath == null) {
            this.compileSourcepath = new Path(getProject());
        }
        return this.compileSourcepath.createPath();
    }

    public void setSourcepathRef(Reference r) {
        createSourcepath().setRefid(r);
    }

    public void setClasspath(Path classpath) {
        Path path = this.compileClasspath;
        if (path == null) {
            this.compileClasspath = classpath;
        } else {
            path.append(classpath);
        }
    }

    public Path getClasspath() {
        return this.compileClasspath;
    }

    public Path createClasspath() {
        if (this.compileClasspath == null) {
            this.compileClasspath = new Path(getProject());
        }
        return this.compileClasspath.createPath();
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public void setBootclasspath(Path bootclasspath) {
        Path path = this.bootclasspath;
        if (path == null) {
            this.bootclasspath = bootclasspath;
        } else {
            path.append(bootclasspath);
        }
    }

    public Path getBootclasspath() {
        return this.bootclasspath;
    }

    public Path createBootclasspath() {
        if (this.bootclasspath == null) {
            this.bootclasspath = new Path(getProject());
        }
        return this.bootclasspath.createPath();
    }

    public void setBootClasspathRef(Reference r) {
        createBootclasspath().setRefid(r);
    }

    public void setExtdirs(Path extdirs) {
        Path path = this.extdirs;
        if (path == null) {
            this.extdirs = extdirs;
        } else {
            path.append(extdirs);
        }
    }

    public Path getExtdirs() {
        return this.extdirs;
    }

    public Path createExtdirs() {
        if (this.extdirs == null) {
            this.extdirs = new Path(getProject());
        }
        return this.extdirs.createPath();
    }

    public void setListfiles(boolean list) {
        this.listFiles = list;
    }

    public boolean getListfiles() {
        return this.listFiles;
    }

    public void setFailonerror(boolean fail) {
        this.failOnError = fail;
    }

    public void setProceed(boolean proceed) {
        this.failOnError = !proceed;
    }

    public boolean getFailonerror() {
        return this.failOnError;
    }

    public void setDeprecation(boolean deprecation) {
        this.deprecation = deprecation;
    }

    public boolean getDeprecation() {
        return this.deprecation;
    }

    public void setMemoryInitialSize(String memoryInitialSize) {
        this.memoryInitialSize = memoryInitialSize;
    }

    public String getMemoryInitialSize() {
        return this.memoryInitialSize;
    }

    public void setMemoryMaximumSize(String memoryMaximumSize) {
        this.memoryMaximumSize = memoryMaximumSize;
    }

    public String getMemoryMaximumSize() {
        return this.memoryMaximumSize;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getDebug() {
        return this.debug;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public boolean getOptimize() {
        return this.optimize;
    }

    public void setDepend(boolean depend) {
        this.depend = depend;
    }

    public boolean getDepend() {
        return this.depend;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean getVerbose() {
        return this.verbose;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return this.target;
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

    public void setFork(boolean f) {
        this.fork = f;
    }

    public void setExecutable(String forkExec) {
        this.forkedExecutable = forkExec;
    }

    public String getExecutable() {
        return this.forkedExecutable;
    }

    public boolean isForkedJavac() {
        return this.fork || "extJavac".equals(getCompiler());
    }

    public String getJavacExecutable() {
        if (this.forkedExecutable == null && isForkedJavac()) {
            this.forkedExecutable = getSystemJavac();
        } else if (this.forkedExecutable != null && !isForkedJavac()) {
            this.forkedExecutable = null;
        }
        return this.forkedExecutable;
    }

    public void setNowarn(boolean flag) {
        this.nowarn = flag;
    }

    public boolean getNowarn() {
        return this.nowarn;
    }

    public ImplementationSpecificArgument createCompilerArg() {
        ImplementationSpecificArgument arg = new ImplementationSpecificArgument();
        this.facade.addImplementationArgument(arg);
        return arg;
    }

    public String[] getCurrentCompilerArgs() {
        String chosen = this.facade.getExplicitChoice();
        this.facade.setImplementation(getCompiler());
        try {
            return this.facade.getArgs();
        } finally {
            this.facade.setImplementation(chosen);
        }
    }

    public void setTempdir(File tmpDir) {
        this.tmpDir = tmpDir;
    }

    public File getTempdir() {
        return this.tmpDir;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        checkParameters();
        resetFileLists();
        String[] list = this.src.list();
        for (String str : list) {
            File srcDir = getProject().resolveFile(str);
            if (!srcDir.exists()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("srcdir \"");
                stringBuffer.append(srcDir.getPath());
                stringBuffer.append("\" does not exist!");
                throw new BuildException(stringBuffer.toString(), getLocation());
            }
            DirectoryScanner ds = getDirectoryScanner(srcDir);
            String[] files = ds.getIncludedFiles();
            File file = this.destDir;
            if (file == null) {
                file = srcDir;
            }
            scanDir(srcDir, file, files);
        }
        compile();
    }

    protected void resetFileLists() {
        this.compileList = new File[0];
    }

    protected void scanDir(File srcDir, File destDir, String[] files) {
        GlobPatternMapper m = new GlobPatternMapper();
        m.setFrom("*.java");
        m.setTo("*.class");
        SourceFileScanner sfs = new SourceFileScanner(this);
        File[] newFiles = sfs.restrictAsFiles(files, srcDir, destDir, m);
        if (newFiles.length > 0) {
            File[] fileArr = this.compileList;
            File[] newCompileList = new File[fileArr.length + newFiles.length];
            System.arraycopy(fileArr, 0, newCompileList, 0, fileArr.length);
            System.arraycopy(newFiles, 0, newCompileList, this.compileList.length, newFiles.length);
            this.compileList = newCompileList;
        }
    }

    public File[] getFileList() {
        return this.compileList;
    }

    protected boolean isJdkCompiler(String compilerImpl) {
        return "modern".equals(compilerImpl) || "classic".equals(compilerImpl) || "javac1.1".equals(compilerImpl) || "javac1.2".equals(compilerImpl) || "javac1.3".equals(compilerImpl) || "javac1.4".equals(compilerImpl) || "javac1.5".equals(compilerImpl);
    }

    protected String getSystemJavac() {
        return JavaEnvUtils.getJdkExecutable("javac");
    }

    public void setCompiler(String compiler) {
        this.facade.setImplementation(compiler);
    }

    public String getCompiler() {
        String compilerImpl = getCompilerVersion();
        if (this.fork) {
            if (isJdkCompiler(compilerImpl)) {
                return "extJavac";
            }
            log("Since compiler setting isn't classic or modern,ignoring fork setting.", 1);
            return compilerImpl;
        }
        return compilerImpl;
    }

    public String getCompilerVersion() {
        this.facade.setMagicValue(getProject().getProperty("build.compiler"));
        return this.facade.getImplementation();
    }

    protected void checkParameters() throws BuildException {
        Path path = this.src;
        if (path == null) {
            throw new BuildException("srcdir attribute must be set!", getLocation());
        }
        if (path.size() == 0) {
            throw new BuildException("srcdir attribute must be set!", getLocation());
        }
        File file = this.destDir;
        if (file != null && !file.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("destination directory \"");
            stringBuffer.append(this.destDir);
            stringBuffer.append("\" does not exist ");
            stringBuffer.append("or is not a directory");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
    }

    protected void compile() {
        String compilerImpl = getCompiler();
        if (this.compileList.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Compiling ");
            stringBuffer.append(this.compileList.length);
            stringBuffer.append(" source file");
            String string = "";
            stringBuffer.append(this.compileList.length == 1 ? "" : "s");
            if (this.destDir != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(" to ");
                stringBuffer2.append(this.destDir);
                string = stringBuffer2.toString();
            }
            stringBuffer.append(string);
            log(stringBuffer.toString());
            if (this.listFiles) {
                int i = 0;
                while (true) {
                    File[] fileArr = this.compileList;
                    if (i >= fileArr.length) {
                        break;
                    }
                    String filename = fileArr[i].getAbsolutePath();
                    log(filename);
                    i++;
                }
            }
            CompilerAdapter adapter = CompilerAdapterFactory.getCompiler(compilerImpl, this);
            adapter.setJavac(this);
            if (!adapter.execute()) {
                if (this.failOnError) {
                    throw new BuildException(FAIL_MSG, getLocation());
                }
                log(FAIL_MSG, 0);
            }
        }
    }

    public class ImplementationSpecificArgument extends org.apache.tools.ant.util.facade.ImplementationSpecificArgument {
        public ImplementationSpecificArgument() {
        }

        public void setCompiler(String impl) {
            super.setImplementation(impl);
        }
    }
}
