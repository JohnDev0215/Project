package org.apache.tools.ant;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.tools.ant.util.LoaderUtils;

/* JADX INFO: loaded from: classes.dex */
public final class Diagnostics {
    private static final String TEST_CLASS = "org.apache.tools.ant.taskdefs.optional.Test";
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$org$apache$tools$ant$Main;

    private Diagnostics() {
    }

    public static boolean isOptionalAvailable() {
        try {
            Class.forName(TEST_CLASS);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void validateVersion() throws BuildException {
        Class clsClass$;
        try {
            Class<?> cls = Class.forName(TEST_CLASS);
            if (class$org$apache$tools$ant$Main == null) {
                clsClass$ = class$("org.apache.tools.ant.Main");
                class$org$apache$tools$ant$Main = clsClass$;
            } else {
                clsClass$ = class$org$apache$tools$ant$Main;
            }
            String coreVersion = getImplementationVersion(clsClass$);
            String optionalVersion = getImplementationVersion(cls);
            if (coreVersion != null && !coreVersion.equals(optionalVersion)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid implementation version between Ant core and Ant optional tasks.\n core    : ");
                stringBuffer.append(coreVersion);
                stringBuffer.append("\n");
                stringBuffer.append(" optional: ");
                stringBuffer.append(optionalVersion);
                throw new BuildException(stringBuffer.toString());
            }
        } catch (ClassNotFoundException e) {
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public static File[] listLibraries() {
        String home = System.getProperty("ant.home");
        if (home == null) {
            return null;
        }
        File libDir = new File(home, "lib");
        FilenameFilter filter = new FilenameFilter() { // from class: org.apache.tools.ant.Diagnostics.1
            @Override // java.io.FilenameFilter
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        };
        String[] filenames = libDir.list(filter);
        if (filenames == null) {
            return null;
        }
        File[] files = new File[filenames.length];
        for (int i = 0; i < filenames.length; i++) {
            files[i] = new File(libDir, filenames[i]);
        }
        return files;
    }

    public static void main(String[] args) {
        doReport(System.out);
    }

    private static String getImplementationVersion(Class clazz) {
        Class clsClass$;
        try {
            if (class$java$lang$Class == null) {
                clsClass$ = class$("java.lang.Class");
                class$java$lang$Class = clsClass$;
            } else {
                clsClass$ = class$java$lang$Class;
            }
            Method method = clsClass$.getMethod("getPackage", new Class[0]);
            Object pkg = method.invoke(clazz, null);
            if (pkg == null) {
                return null;
            }
            Method method2 = pkg.getClass().getMethod("getImplementationVersion", new Class[0]);
            Object version = method2.invoke(pkg, null);
            return (String) version;
        } catch (Exception e) {
            return "?.?";
        }
    }

    private static String getXmlParserName() {
        SAXParser saxParser = getSAXParser();
        if (saxParser == null) {
            return "Could not create an XML Parser";
        }
        String saxParserName = saxParser.getClass().getName();
        return saxParserName;
    }

    private static SAXParser getSAXParser() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        if (saxParserFactory == null) {
            return null;
        }
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            return saxParser;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getXMLParserLocation() {
        SAXParser saxParser = getSAXParser();
        if (saxParser == null) {
            return null;
        }
        String location = getClassLocation(saxParser.getClass());
        return location;
    }

    private static String getClassLocation(Class clazz) {
        File f = LoaderUtils.getClassSource(clazz);
        if (f == null) {
            return null;
        }
        return f.getAbsolutePath();
    }

    public static void doReport(PrintStream out) {
        out.println("------- Ant diagnostics report -------");
        out.println(Main.getAntVersion());
        out.println();
        out.println("-------------------------------------------");
        out.println(" Implementation Version (JDK1.2+ only)");
        out.println("-------------------------------------------");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("core tasks     : ");
        Class clsClass$ = class$org$apache$tools$ant$Main;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.Main");
            class$org$apache$tools$ant$Main = clsClass$;
        }
        stringBuffer.append(getImplementationVersion(clsClass$));
        out.println(stringBuffer.toString());
        try {
            Class<?> cls = Class.forName(TEST_CLASS);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("optional tasks : ");
            stringBuffer2.append(getImplementationVersion(cls));
            out.println(stringBuffer2.toString());
        } catch (ClassNotFoundException e) {
            out.println("optional tasks : not available");
        }
        out.println();
        out.println("-------------------------------------------");
        out.println(" ANT_HOME/lib jar listing");
        out.println("-------------------------------------------");
        doReportLibraries(out);
        out.println();
        out.println("-------------------------------------------");
        out.println(" Tasks availability");
        out.println("-------------------------------------------");
        doReportTasksAvailability(out);
        out.println();
        out.println("-------------------------------------------");
        out.println(" org.apache.env.Which diagnostics");
        out.println("-------------------------------------------");
        doReportWhich(out);
        out.println();
        out.println("-------------------------------------------");
        out.println(" XML Parser information");
        out.println("-------------------------------------------");
        doReportParserInfo(out);
        out.println();
        out.println("-------------------------------------------");
        out.println(" System properties");
        out.println("-------------------------------------------");
        doReportSystemProperties(out);
        out.println();
    }

    private static void doReportSystemProperties(PrintStream out) {
        Enumeration<?> enumerationPropertyNames = System.getProperties().propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String key = (String) enumerationPropertyNames.nextElement();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(key);
            stringBuffer.append(" : ");
            stringBuffer.append(System.getProperty(key));
            out.println(stringBuffer.toString());
        }
    }

    private static void doReportLibraries(PrintStream out) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ant.home: ");
        stringBuffer.append(System.getProperty("ant.home"));
        out.println(stringBuffer.toString());
        File[] libs = listLibraries();
        if (libs == null) {
            out.println("Unable to list libraries.");
            return;
        }
        for (int i = 0; i < libs.length; i++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(libs[i].getName());
            stringBuffer2.append(" (");
            stringBuffer2.append(libs[i].length());
            stringBuffer2.append(" bytes)");
            out.println(stringBuffer2.toString());
        }
    }

    private static void doReportWhich(PrintStream out) {
        InvocationTargetException e;
        Class<?> cls;
        Class<?> clsClass$;
        Throwable error = null;
        try {
            try {
                cls = Class.forName("org.apache.env.Which");
            } catch (InvocationTargetException e2) {
                e = e2;
            } catch (Throwable th) {
                e = th;
            }
            try {
                try {
                    Class<?>[] clsArr = new Class[1];
                    if (array$Ljava$lang$String == null) {
                        clsClass$ = class$("[Ljava.lang.String;");
                        array$Ljava$lang$String = clsClass$;
                    } else {
                        clsClass$ = array$Ljava$lang$String;
                    }
                    clsArr[0] = clsClass$;
                    Method method = cls.getMethod("main", clsArr);
                    try {
                        method.invoke(null, new String[0]);
                    } catch (Throwable th2) {
                        e = th2;
                        error = e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                }
            } catch (InvocationTargetException e3) {
                e = e3;
                error = e.getTargetException() == null ? e : e.getTargetException();
            }
        } catch (ClassNotFoundException e4) {
            out.println("Not available.");
            out.println("Download it at http://xml.apache.org/commons/");
        }
        if (error != null) {
            out.println("Error while running org.apache.env.Which");
            error.printStackTrace();
        }
    }

    private static void doReportTasksAvailability(PrintStream out) {
        Class clsClass$ = class$org$apache$tools$ant$Main;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.Main");
            class$org$apache$tools$ant$Main = clsClass$;
        }
        InputStream is = clsClass$.getResourceAsStream("/org/apache/tools/ant/taskdefs/defaults.properties");
        if (is == null) {
            out.println("None available");
            return;
        }
        Properties props = new Properties();
        try {
            props.load(is);
            Enumeration keys = props.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String classname = props.getProperty(key);
                try {
                    Class.forName(classname);
                    props.remove(key);
                } catch (ClassNotFoundException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(key);
                    stringBuffer.append(" : Not Available");
                    out.println(stringBuffer.toString());
                } catch (NoClassDefFoundError e2) {
                    String pkg = e2.getMessage().replace('/', '.');
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(key);
                    stringBuffer2.append(" : Missing dependency ");
                    stringBuffer2.append(pkg);
                    out.println(stringBuffer2.toString());
                } catch (Error e3) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(key);
                    stringBuffer3.append(" : Initialization error");
                    out.println(stringBuffer3.toString());
                }
            }
            if (props.size() == 0) {
                out.println("All defined tasks are available");
            }
        } catch (IOException e4) {
            out.println(e4.getMessage());
        }
    }

    private static void doReportParserInfo(PrintStream out) {
        String parserName = getXmlParserName();
        String parserLocation = getXMLParserLocation();
        if (parserName == null) {
            parserName = "unknown";
        }
        if (parserLocation == null) {
            parserLocation = "unknown";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("XML Parser : ");
        stringBuffer.append(parserName);
        out.println(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("XML Parser Location: ");
        stringBuffer2.append(parserLocation);
        out.println(stringBuffer2.toString());
    }
}
