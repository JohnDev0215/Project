package org.apache.tools.ant.taskdefs.compilers;

import java.io.File;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultCompilerAdapter implements CompilerAdapter {
    protected static final String lSep = System.getProperty("line.separator");
    protected Javac attributes;
    protected Path bootclasspath;
    protected Path compileClasspath;
    protected File[] compileList;
    protected Path compileSourcepath;
    protected File destDir;
    protected String encoding;
    protected Path extdirs;
    protected boolean includeAntRuntime;
    protected boolean includeJavaRuntime;
    protected Location location;
    protected String memoryInitialSize;
    protected String memoryMaximumSize;
    protected Project project;
    protected Path src;
    protected String target;
    protected boolean debug = false;
    protected boolean optimize = false;
    protected boolean deprecation = false;
    protected boolean depend = false;
    protected boolean verbose = false;
    private FileUtils fileUtils = FileUtils.newFileUtils();

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public void setJavac(Javac attributes) {
        this.attributes = attributes;
        this.src = attributes.getSrcdir();
        this.destDir = attributes.getDestdir();
        this.encoding = attributes.getEncoding();
        this.debug = attributes.getDebug();
        this.optimize = attributes.getOptimize();
        this.deprecation = attributes.getDeprecation();
        this.depend = attributes.getDepend();
        this.verbose = attributes.getVerbose();
        this.target = attributes.getTarget();
        this.bootclasspath = attributes.getBootclasspath();
        this.extdirs = attributes.getExtdirs();
        this.compileList = attributes.getFileList();
        this.compileClasspath = attributes.getClasspath();
        this.compileSourcepath = attributes.getSourcepath();
        this.project = attributes.getProject();
        this.location = attributes.getLocation();
        this.includeAntRuntime = attributes.getIncludeantruntime();
        this.includeJavaRuntime = attributes.getIncludejavaruntime();
        this.memoryInitialSize = attributes.getMemoryInitialSize();
        this.memoryMaximumSize = attributes.getMemoryMaximumSize();
    }

    public Javac getJavac() {
        return this.attributes;
    }

    protected Project getProject() {
        return this.project;
    }

    protected Path getCompileClasspath() {
        Path classpath = new Path(this.project);
        File file = this.destDir;
        if (file != null) {
            classpath.setLocation(file);
        }
        Path cp = this.compileClasspath;
        if (cp == null) {
            cp = new Path(this.project);
        }
        if (this.includeAntRuntime) {
            classpath.addExisting(cp.concatSystemClasspath("last"));
        } else {
            classpath.addExisting(cp.concatSystemClasspath("ignore"));
        }
        if (this.includeJavaRuntime) {
            classpath.addJavaRuntime();
        }
        return classpath;
    }

    protected Commandline setupJavacCommandlineSwitches(Commandline cmd) {
        return setupJavacCommandlineSwitches(cmd, false);
    }

    protected Commandline setupJavacCommandlineSwitches(Commandline cmd, boolean useDebugLevel) {
        Path sourcepath;
        String debugLevel;
        Path classpath = getCompileClasspath();
        if (this.compileSourcepath != null) {
            sourcepath = this.compileSourcepath;
        } else {
            sourcepath = this.src;
        }
        String memoryParameterPrefix = assumeJava11() ? "-J-" : "-J-X";
        if (this.memoryInitialSize != null) {
            if (!this.attributes.isForkedJavac()) {
                this.attributes.log("Since fork is false, ignoring memoryInitialSize setting.", 1);
            } else {
                Commandline.Argument argumentCreateArgument = cmd.createArgument();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(memoryParameterPrefix);
                stringBuffer.append("ms");
                stringBuffer.append(this.memoryInitialSize);
                argumentCreateArgument.setValue(stringBuffer.toString());
            }
        }
        if (this.memoryMaximumSize != null) {
            if (!this.attributes.isForkedJavac()) {
                this.attributes.log("Since fork is false, ignoring memoryMaximumSize setting.", 1);
            } else {
                Commandline.Argument argumentCreateArgument2 = cmd.createArgument();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(memoryParameterPrefix);
                stringBuffer2.append("mx");
                stringBuffer2.append(this.memoryMaximumSize);
                argumentCreateArgument2.setValue(stringBuffer2.toString());
            }
        }
        if (this.attributes.getNowarn()) {
            cmd.createArgument().setValue("-nowarn");
        }
        if (this.deprecation) {
            cmd.createArgument().setValue("-deprecation");
        }
        if (this.destDir != null) {
            cmd.createArgument().setValue("-d");
            cmd.createArgument().setFile(this.destDir);
        }
        cmd.createArgument().setValue("-classpath");
        if (assumeJava11()) {
            Path cp = new Path(this.project);
            Path path = this.bootclasspath;
            if (path != null) {
                cp.append(path);
            }
            Path path2 = this.extdirs;
            if (path2 != null) {
                cp.addExtdirs(path2);
            }
            cp.append(classpath);
            cp.append(sourcepath);
            cmd.createArgument().setPath(cp);
        } else {
            cmd.createArgument().setPath(classpath);
            if (sourcepath.size() > 0) {
                cmd.createArgument().setValue("-sourcepath");
                cmd.createArgument().setPath(sourcepath);
            }
            if (this.target != null) {
                cmd.createArgument().setValue("-target");
                cmd.createArgument().setValue(this.target);
            }
            Path path3 = this.bootclasspath;
            if (path3 != null && path3.size() > 0) {
                cmd.createArgument().setValue("-bootclasspath");
                cmd.createArgument().setPath(this.bootclasspath);
            }
            Path path4 = this.extdirs;
            if (path4 != null && path4.size() > 0) {
                cmd.createArgument().setValue("-extdirs");
                cmd.createArgument().setPath(this.extdirs);
            }
        }
        if (this.encoding != null) {
            cmd.createArgument().setValue("-encoding");
            cmd.createArgument().setValue(this.encoding);
        }
        if (this.debug) {
            if (!useDebugLevel || assumeJava11() || (debugLevel = this.attributes.getDebugLevel()) == null) {
                cmd.createArgument().setValue("-g");
            } else {
                Commandline.Argument argumentCreateArgument3 = cmd.createArgument();
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("-g:");
                stringBuffer3.append(debugLevel);
                argumentCreateArgument3.setValue(stringBuffer3.toString());
            }
        } else if (getNoDebugArgument() != null) {
            cmd.createArgument().setValue(getNoDebugArgument());
        }
        if (this.optimize) {
            cmd.createArgument().setValue("-O");
        }
        if (this.depend) {
            if (assumeJava11()) {
                cmd.createArgument().setValue("-depend");
            } else if (assumeJava12()) {
                cmd.createArgument().setValue("-Xdepend");
            } else {
                this.attributes.log("depend attribute is not supported by the modern compiler", 1);
            }
        }
        if (this.verbose) {
            cmd.createArgument().setValue("-verbose");
        }
        addCurrentCompilerArgs(cmd);
        return cmd;
    }

    protected Commandline setupModernJavacCommandlineSwitches(Commandline cmd) {
        setupJavacCommandlineSwitches(cmd, true);
        if (this.attributes.getSource() != null && !assumeJava13()) {
            cmd.createArgument().setValue("-source");
            String source = this.attributes.getSource();
            if ((assumeJava14() || assumeJava15()) && (source.equals("1.1") || source.equals("1.2"))) {
                cmd.createArgument().setValue("1.3");
            } else {
                cmd.createArgument().setValue(source);
            }
        } else if (assumeJava15() && this.attributes.getTarget() != null) {
            String t = this.attributes.getTarget();
            if (t.equals("1.1") || t.equals("1.2") || t.equals("1.3") || t.equals("1.4")) {
                String s = t;
                if (t.equals("1.1")) {
                    s = "1.2";
                }
                this.attributes.log("", 1);
                this.attributes.log("          WARNING", 1);
                this.attributes.log("", 1);
                this.attributes.log("The -source switch defaults to 1.5 in JDK 1.5.", 1);
                Javac javac = this.attributes;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("If you specify -target ");
                stringBuffer.append(t);
                stringBuffer.append(" you now must also specify -source ");
                stringBuffer.append(s);
                stringBuffer.append(".");
                javac.log(stringBuffer.toString(), 1);
                Javac javac2 = this.attributes;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Ant will implicitly add -source ");
                stringBuffer2.append(s);
                stringBuffer2.append(" for you.  Please change your build file.");
                javac2.log(stringBuffer2.toString(), 1);
                cmd.createArgument().setValue("-source");
                cmd.createArgument().setValue(s);
            }
        }
        return cmd;
    }

    protected Commandline setupModernJavacCommand() {
        Commandline cmd = new Commandline();
        setupModernJavacCommandlineSwitches(cmd);
        logAndAddFilesToCompile(cmd);
        return cmd;
    }

    protected Commandline setupJavacCommand() {
        return setupJavacCommand(false);
    }

    protected Commandline setupJavacCommand(boolean debugLevelCheck) {
        Commandline cmd = new Commandline();
        setupJavacCommandlineSwitches(cmd, debugLevelCheck);
        logAndAddFilesToCompile(cmd);
        return cmd;
    }

    protected void logAndAddFilesToCompile(Commandline cmd) {
        Javac javac = this.attributes;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Compilation ");
        stringBuffer.append(cmd.describeArguments());
        javac.log(stringBuffer.toString(), 3);
        StringBuffer niceSourceList = new StringBuffer("File");
        if (this.compileList.length != 1) {
            niceSourceList.append("s");
        }
        niceSourceList.append(" to be compiled:");
        niceSourceList.append(lSep);
        int i = 0;
        while (true) {
            File[] fileArr = this.compileList;
            if (i < fileArr.length) {
                String arg = fileArr[i].getAbsolutePath();
                cmd.createArgument().setValue(arg);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("    ");
                stringBuffer2.append(arg);
                stringBuffer2.append(lSep);
                niceSourceList.append(stringBuffer2.toString());
                i++;
            } else {
                this.attributes.log(niceSourceList.toString(), 3);
                return;
            }
        }
    }

    protected int executeExternalCompile(String[] args, int firstFileName) {
        return executeExternalCompile(args, firstFileName, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int executeExternalCompile(java.lang.String[] r9, int r10, boolean r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.compilers.DefaultCompilerAdapter.executeExternalCompile(java.lang.String[], int, boolean):int");
    }

    protected void addExtdirsToClasspath(Path classpath) {
        classpath.addExtdirs(this.extdirs);
    }

    protected void addCurrentCompilerArgs(Commandline cmd) {
        cmd.addArguments(getJavac().getCurrentCompilerArgs());
    }

    protected boolean assumeJava11() {
        return "javac1.1".equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.1")) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.1"));
    }

    protected boolean assumeJava12() {
        return "javac1.2".equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.2")) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.2"));
    }

    protected boolean assumeJava13() {
        return "javac1.3".equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.3")) || (("modern".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.3")) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.3")));
    }

    protected boolean assumeJava14() {
        return "javac1.4".equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.4")) || (("modern".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.4")) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion("1.4")));
    }

    protected boolean assumeJava15() {
        return "javac1.5".equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(JavaEnvUtils.JAVA_1_5)) || (("modern".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(JavaEnvUtils.JAVA_1_5)) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(JavaEnvUtils.JAVA_1_5)));
    }

    protected String getNoDebugArgument() {
        if (assumeJava11()) {
            return null;
        }
        return "-g:none";
    }
}
