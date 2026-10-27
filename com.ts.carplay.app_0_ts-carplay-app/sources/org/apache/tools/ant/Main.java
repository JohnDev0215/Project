package org.apache.tools.ant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.input.DefaultInputHandler;
import org.apache.tools.ant.input.InputHandler;
import org.apache.tools.ant.launch.AntMain;

/* JADX INFO: loaded from: classes.dex */
public class Main implements AntMain {
    public static final String DEFAULT_BUILD_FILENAME = "build.xml";
    static /* synthetic */ Class class$org$apache$tools$ant$Main;
    private File buildFile;
    private static PrintStream out = System.out;
    private static PrintStream err = System.err;
    private static boolean isLogFileUsed = false;
    private static String antVersion = null;
    private int msgOutputLevel = 2;
    private Vector targets = new Vector();
    private Properties definedProps = new Properties();
    private Vector listeners = new Vector(1);
    private Vector propertyFiles = new Vector(1);
    private boolean allowInput = true;
    private boolean keepGoingMode = false;
    private String loggerClassname = null;
    private String inputHandlerClassname = null;
    private boolean emacsMode = false;
    private boolean readyToRun = false;
    private boolean projectHelp = false;
    private Integer threadPriority = null;

    private static void printMessage(Throwable t) {
        String message = t.getMessage();
        if (message != null) {
            System.err.println(message);
        }
    }

    public static void start(String[] args, Properties additionalUserProperties, ClassLoader coreLoader) throws Throwable {
        Main m = new Main();
        m.startAnt(args, additionalUserProperties, coreLoader);
    }

    public void startAnt(String[] args, Properties additionalUserProperties, ClassLoader coreLoader) throws Throwable {
        try {
            Diagnostics.validateVersion();
            processArgs(args);
        } catch (Throwable exc) {
            handleLogfile();
            printMessage(exc);
            System.exit(1);
        }
        if (additionalUserProperties != null) {
            Enumeration e = additionalUserProperties.keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String property = additionalUserProperties.getProperty(key);
                this.definedProps.put(key, property);
            }
        }
        int exitCode = 1;
        try {
            try {
                runBuild(coreLoader);
                exitCode = 0;
            } catch (BuildException be) {
                try {
                    if (err != System.err) {
                        printMessage(be);
                    }
                    handleLogfile();
                } catch (Throwable th) {
                    th = th;
                    handleLogfile();
                    throw th;
                }
            } catch (Throwable exc2) {
                try {
                    exc2.printStackTrace();
                    printMessage(exc2);
                    handleLogfile();
                } catch (Throwable th2) {
                    th = th2;
                    handleLogfile();
                    throw th;
                }
            }
        } catch (ExitStatusException ese) {
            exitCode = ese.getStatus();
            if (exitCode != 0) {
                throw ese;
            }
        }
        handleLogfile();
        System.exit(exitCode);
    }

    private static void handleLogfile() {
        if (isLogFileUsed) {
            PrintStream printStream = out;
            if (printStream != null) {
                try {
                    printStream.close();
                } catch (Exception e) {
                }
            }
            PrintStream printStream2 = err;
            if (printStream2 != null) {
                try {
                    printStream2.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        start(args, null, null);
    }

    public Main() {
    }

    protected Main(String[] args) throws Throwable {
        processArgs(args);
    }

    /* JADX WARN: Removed duplicated region for block: B:221:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processArgs(java.lang.String[] r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1004
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.Main.processArgs(java.lang.String[]):void");
    }

    private File getParentFile(File file) {
        File parent = file.getParentFile();
        if (parent != null && this.msgOutputLevel >= 3) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Searching in ");
            stringBuffer.append(parent.getAbsolutePath());
            printStream.println(stringBuffer.toString());
        }
        return parent;
    }

    private File findBuildFile(String start, String suffix) throws BuildException {
        if (this.msgOutputLevel >= 2) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Searching for ");
            stringBuffer.append(suffix);
            stringBuffer.append(" ...");
            printStream.println(stringBuffer.toString());
        }
        File parent = new File(new File(start).getAbsolutePath());
        File file = new File(parent, suffix);
        while (!file.exists()) {
            parent = getParentFile(parent);
            if (parent == null) {
                throw new BuildException("Could not locate a build file!");
            }
            file = new File(parent, suffix);
        }
        return file;
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x016a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void runBuild(java.lang.ClassLoader r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.Main.runBuild(java.lang.ClassLoader):void");
    }

    protected void addBuildListeners(Project project) {
        project.addBuildListener(createLogger());
        for (int i = 0; i < this.listeners.size(); i++) {
            String className = (String) this.listeners.elementAt(i);
            try {
                BuildListener listener = (BuildListener) Class.forName(className).newInstance();
                if (project != null) {
                    project.setProjectReference(listener);
                }
                project.addBuildListener(listener);
            } catch (Throwable exc) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to instantiate listener ");
                stringBuffer.append(className);
                throw new BuildException(stringBuffer.toString(), exc);
            }
        }
    }

    private void addInputHandler(Project project) throws BuildException {
        InputHandler handler;
        String str = this.inputHandlerClassname;
        if (str == null) {
            handler = new DefaultInputHandler();
        } else {
            try {
                handler = (InputHandler) Class.forName(str).newInstance();
                if (project != null) {
                    project.setProjectReference(handler);
                }
            } catch (ClassCastException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The specified input handler class ");
                stringBuffer.append(this.inputHandlerClassname);
                stringBuffer.append(" does not implement the InputHandler interface");
                String msg = stringBuffer.toString();
                throw new BuildException(msg);
            } catch (Exception e2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Unable to instantiate specified input handler class ");
                stringBuffer2.append(this.inputHandlerClassname);
                stringBuffer2.append(" : ");
                stringBuffer2.append(e2.getClass().getName());
                String msg2 = stringBuffer2.toString();
                throw new BuildException(msg2);
            }
        }
        project.setInputHandler(handler);
    }

    private BuildLogger createLogger() {
        BuildLogger logger;
        String str = this.loggerClassname;
        if (str != null) {
            try {
                try {
                    try {
                        logger = (BuildLogger) Class.forName(str).newInstance();
                    } catch (Exception e) {
                        e = e;
                        PrintStream printStream = System.err;
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unable to instantiate specified logger class ");
                        stringBuffer.append(this.loggerClassname);
                        stringBuffer.append(" : ");
                        stringBuffer.append(e.getClass().getName());
                        printStream.println(stringBuffer.toString());
                        throw new RuntimeException();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (ClassCastException e3) {
                PrintStream printStream2 = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("The specified logger class ");
                stringBuffer2.append(this.loggerClassname);
                stringBuffer2.append(" does not implement the BuildLogger interface");
                printStream2.println(stringBuffer2.toString());
                throw new RuntimeException();
            }
        } else {
            logger = new DefaultLogger();
        }
        logger.setMessageOutputLevel(this.msgOutputLevel);
        logger.setOutputPrintStream(out);
        logger.setErrorPrintStream(err);
        logger.setEmacsMode(this.emacsMode);
        return logger;
    }

    private static void printUsage() {
        String lSep = System.getProperty("line.separator");
        StringBuffer msg = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ant [options] [target [target2 [target3] ...]]");
        stringBuffer.append(lSep);
        msg.append(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Options: ");
        stringBuffer2.append(lSep);
        msg.append(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("  -help, -h              print this message");
        stringBuffer3.append(lSep);
        msg.append(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("  -projecthelp, -p       print project help information");
        stringBuffer4.append(lSep);
        msg.append(stringBuffer4.toString());
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("  -version               print the version information and exit");
        stringBuffer5.append(lSep);
        msg.append(stringBuffer5.toString());
        StringBuffer stringBuffer6 = new StringBuffer();
        stringBuffer6.append("  -diagnostics           print information that might be helpful to");
        stringBuffer6.append(lSep);
        msg.append(stringBuffer6.toString());
        StringBuffer stringBuffer7 = new StringBuffer();
        stringBuffer7.append("                         diagnose or report problems.");
        stringBuffer7.append(lSep);
        msg.append(stringBuffer7.toString());
        StringBuffer stringBuffer8 = new StringBuffer();
        stringBuffer8.append("  -quiet, -q             be extra quiet");
        stringBuffer8.append(lSep);
        msg.append(stringBuffer8.toString());
        StringBuffer stringBuffer9 = new StringBuffer();
        stringBuffer9.append("  -verbose, -v           be extra verbose");
        stringBuffer9.append(lSep);
        msg.append(stringBuffer9.toString());
        StringBuffer stringBuffer10 = new StringBuffer();
        stringBuffer10.append("  -debug, -d             print debugging information");
        stringBuffer10.append(lSep);
        msg.append(stringBuffer10.toString());
        StringBuffer stringBuffer11 = new StringBuffer();
        stringBuffer11.append("  -emacs, -e             produce logging information without adornments");
        stringBuffer11.append(lSep);
        msg.append(stringBuffer11.toString());
        StringBuffer stringBuffer12 = new StringBuffer();
        stringBuffer12.append("  -lib <path>            specifies a path to search for jars and classes");
        stringBuffer12.append(lSep);
        msg.append(stringBuffer12.toString());
        StringBuffer stringBuffer13 = new StringBuffer();
        stringBuffer13.append("  -logfile <file>        use given file for log");
        stringBuffer13.append(lSep);
        msg.append(stringBuffer13.toString());
        StringBuffer stringBuffer14 = new StringBuffer();
        stringBuffer14.append("    -l     <file>                ''");
        stringBuffer14.append(lSep);
        msg.append(stringBuffer14.toString());
        StringBuffer stringBuffer15 = new StringBuffer();
        stringBuffer15.append("  -logger <classname>    the class which is to perform logging");
        stringBuffer15.append(lSep);
        msg.append(stringBuffer15.toString());
        StringBuffer stringBuffer16 = new StringBuffer();
        stringBuffer16.append("  -listener <classname>  add an instance of class as a project listener");
        stringBuffer16.append(lSep);
        msg.append(stringBuffer16.toString());
        StringBuffer stringBuffer17 = new StringBuffer();
        stringBuffer17.append("  -noinput               do not allow interactive input");
        stringBuffer17.append(lSep);
        msg.append(stringBuffer17.toString());
        StringBuffer stringBuffer18 = new StringBuffer();
        stringBuffer18.append("  -buildfile <file>      use given buildfile");
        stringBuffer18.append(lSep);
        msg.append(stringBuffer18.toString());
        StringBuffer stringBuffer19 = new StringBuffer();
        stringBuffer19.append("    -file    <file>              ''");
        stringBuffer19.append(lSep);
        msg.append(stringBuffer19.toString());
        StringBuffer stringBuffer20 = new StringBuffer();
        stringBuffer20.append("    -f       <file>              ''");
        stringBuffer20.append(lSep);
        msg.append(stringBuffer20.toString());
        StringBuffer stringBuffer21 = new StringBuffer();
        stringBuffer21.append("  -D<property>=<value>   use value for given property");
        stringBuffer21.append(lSep);
        msg.append(stringBuffer21.toString());
        StringBuffer stringBuffer22 = new StringBuffer();
        stringBuffer22.append("  -keep-going, -k        execute all targets that do not depend");
        stringBuffer22.append(lSep);
        msg.append(stringBuffer22.toString());
        StringBuffer stringBuffer23 = new StringBuffer();
        stringBuffer23.append("                         on failed target(s)");
        stringBuffer23.append(lSep);
        msg.append(stringBuffer23.toString());
        StringBuffer stringBuffer24 = new StringBuffer();
        stringBuffer24.append("  -propertyfile <name>   load all properties from file with -D");
        stringBuffer24.append(lSep);
        msg.append(stringBuffer24.toString());
        StringBuffer stringBuffer25 = new StringBuffer();
        stringBuffer25.append("                         properties taking precedence");
        stringBuffer25.append(lSep);
        msg.append(stringBuffer25.toString());
        StringBuffer stringBuffer26 = new StringBuffer();
        stringBuffer26.append("  -inputhandler <class>  the class which will handle input requests");
        stringBuffer26.append(lSep);
        msg.append(stringBuffer26.toString());
        StringBuffer stringBuffer27 = new StringBuffer();
        stringBuffer27.append("  -find <file>           (s)earch for buildfile towards the root of");
        stringBuffer27.append(lSep);
        msg.append(stringBuffer27.toString());
        StringBuffer stringBuffer28 = new StringBuffer();
        stringBuffer28.append("    -s  <file>           the filesystem and use it");
        stringBuffer28.append(lSep);
        msg.append(stringBuffer28.toString());
        StringBuffer stringBuffer29 = new StringBuffer();
        stringBuffer29.append("  -nice  number          A niceness value for the main thread:");
        stringBuffer29.append(lSep);
        stringBuffer29.append("                         1 (lowest) to 10 (highest); 5 is the default");
        stringBuffer29.append(lSep);
        msg.append(stringBuffer29.toString());
        StringBuffer stringBuffer30 = new StringBuffer();
        stringBuffer30.append("  -nouserlib             Run ant without using the jar files from");
        stringBuffer30.append(lSep);
        stringBuffer30.append("                         ${user.home}/.ant/lib");
        stringBuffer30.append(lSep);
        msg.append(stringBuffer30.toString());
        msg.append("  -noclasspath           Run ant without using CLASSPATH");
        System.out.println(msg.toString());
    }

    private static void printVersion() throws BuildException {
        System.out.println(getAntVersion());
    }

    public static synchronized String getAntVersion() throws BuildException {
        Class clsClass$;
        if (antVersion == null) {
            try {
                Properties props = new Properties();
                if (class$org$apache$tools$ant$Main == null) {
                    clsClass$ = class$("org.apache.tools.ant.Main");
                    class$org$apache$tools$ant$Main = clsClass$;
                } else {
                    clsClass$ = class$org$apache$tools$ant$Main;
                }
                InputStream in = clsClass$.getResourceAsStream("/org/apache/tools/ant/version.txt");
                props.load(in);
                in.close();
                StringBuffer msg = new StringBuffer();
                msg.append("Apache Ant version ");
                msg.append(props.getProperty("VERSION"));
                msg.append(" compiled on ");
                msg.append(props.getProperty("DATE"));
                antVersion = msg.toString();
            } catch (IOException ioe) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not load the version information:");
                stringBuffer.append(ioe.getMessage());
                throw new BuildException(stringBuffer.toString());
            } catch (NullPointerException e) {
                throw new BuildException("Could not load the version information.");
            }
        }
        return antVersion;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private static void printDescription(Project project) {
        if (project.getDescription() != null) {
            project.log(project.getDescription());
        }
    }

    private static void printTargets(Project project, boolean printSubTargets) {
        int maxLength = 0;
        Enumeration ptargets = project.getTargets().elements();
        Vector topNames = new Vector();
        Vector topDescriptions = new Vector();
        Vector subNames = new Vector();
        while (ptargets.hasMoreElements()) {
            Target currentTarget = (Target) ptargets.nextElement();
            String targetName = currentTarget.getName();
            if (!targetName.equals("")) {
                String targetDescription = currentTarget.getDescription();
                if (targetDescription == null) {
                    subNames.insertElementAt(targetName, findTargetPosition(subNames, targetName));
                } else {
                    int pos = findTargetPosition(topNames, targetName);
                    topNames.insertElementAt(targetName, pos);
                    topDescriptions.insertElementAt(targetDescription, pos);
                    if (targetName.length() > maxLength) {
                        maxLength = targetName.length();
                    }
                }
            }
        }
        printTargets(project, topNames, topDescriptions, "Main targets:", maxLength);
        if (topNames.size() == 0) {
            printSubTargets = true;
        }
        if (printSubTargets) {
            printTargets(project, subNames, null, "Other targets:", 0);
        }
        String defaultTarget = project.getDefaultTarget();
        if (defaultTarget != null && !"".equals(defaultTarget)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Default target: ");
            stringBuffer.append(defaultTarget);
            project.log(stringBuffer.toString());
        }
    }

    private static int findTargetPosition(Vector names, String name) {
        int res = names.size();
        for (int i = 0; i < names.size() && res == names.size(); i++) {
            if (name.compareTo((String) names.elementAt(i)) < 0) {
                res = i;
            }
        }
        return res;
    }

    private static void printTargets(Project project, Vector names, Vector descriptions, String heading, int maxlen) {
        String lSep = System.getProperty("line.separator");
        String spaces = "    ";
        while (spaces.length() <= maxlen) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(spaces);
            stringBuffer.append(spaces);
            spaces = stringBuffer.toString();
        }
        StringBuffer msg = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(heading);
        stringBuffer2.append(lSep);
        stringBuffer2.append(lSep);
        msg.append(stringBuffer2.toString());
        for (int i = 0; i < names.size(); i++) {
            msg.append(" ");
            msg.append(names.elementAt(i));
            if (descriptions != null) {
                msg.append(spaces.substring(0, (maxlen - ((String) names.elementAt(i)).length()) + 2));
                msg.append(descriptions.elementAt(i));
            }
            msg.append(lSep);
        }
        project.log(msg.toString());
    }
}
