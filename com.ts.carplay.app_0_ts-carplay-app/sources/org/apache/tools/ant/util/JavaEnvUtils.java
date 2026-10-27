package org.apache.tools.ant.util;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.condition.Os;

/* JADX INFO: loaded from: classes.dex */
public final class JavaEnvUtils {
    public static final String JAVA_1_0 = "1.0";
    public static final String JAVA_1_1 = "1.1";
    public static final String JAVA_1_2 = "1.2";
    public static final String JAVA_1_3 = "1.3";
    public static final String JAVA_1_4 = "1.4";
    public static final String JAVA_1_5 = "1.5";
    private static String javaVersion;
    private static int javaVersionNumber;
    private static Vector jrePackages;
    private static boolean kaffeDetected;
    private static final boolean isDos = Os.isFamily("dos");
    private static final boolean isNetware = Os.isName("netware");
    private static final boolean isAix = Os.isName("aix");
    private static final String javaHome = System.getProperty("java.home");
    private static final FileUtils fileUtils = FileUtils.newFileUtils();

    private JavaEnvUtils() {
    }

    static {
        try {
            javaVersion = "1.0";
            javaVersionNumber = 10;
            Class.forName("java.lang.Void");
            javaVersion = "1.1";
            javaVersionNumber++;
            Class.forName("java.lang.ThreadLocal");
            javaVersion = "1.2";
            javaVersionNumber++;
            Class.forName("java.lang.StrictMath");
            javaVersion = "1.3";
            javaVersionNumber++;
            Class.forName("java.lang.CharSequence");
            javaVersion = "1.4";
            javaVersionNumber++;
            Class.forName("java.lang.Readable");
            javaVersion = JAVA_1_5;
            javaVersionNumber++;
        } catch (Throwable th) {
        }
        kaffeDetected = false;
        try {
            Class.forName("kaffe.util.NotImplemented");
            kaffeDetected = true;
        } catch (Throwable th2) {
        }
    }

    public static String getJavaVersion() {
        return javaVersion;
    }

    public static boolean isJavaVersion(String version) {
        return javaVersion.equals(version);
    }

    public static boolean isKaffe() {
        return kaffeDetected;
    }

    public static String getJreExecutable(String command) {
        if (isNetware) {
            return command;
        }
        File jExecutable = null;
        if (isAix) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(javaHome);
            stringBuffer.append("/sh");
            jExecutable = findInDir(stringBuffer.toString(), command);
        }
        if (jExecutable == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(javaHome);
            stringBuffer2.append("/bin");
            jExecutable = findInDir(stringBuffer2.toString(), command);
        }
        if (jExecutable != null) {
            return jExecutable.getAbsolutePath();
        }
        return addExtension(command);
    }

    public static String getJdkExecutable(String command) {
        if (isNetware) {
            return command;
        }
        File jExecutable = null;
        if (isAix) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(javaHome);
            stringBuffer.append("/../sh");
            jExecutable = findInDir(stringBuffer.toString(), command);
        }
        if (jExecutable == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(javaHome);
            stringBuffer2.append("/../bin");
            jExecutable = findInDir(stringBuffer2.toString(), command);
        }
        if (jExecutable != null) {
            return jExecutable.getAbsolutePath();
        }
        return getJreExecutable(command);
    }

    private static String addExtension(String command) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(command);
        stringBuffer.append(isDos ? ".exe" : "");
        return stringBuffer.toString();
    }

    private static File findInDir(String dirName, String commandName) {
        File dir = fileUtils.normalize(dirName);
        if (!dir.exists()) {
            return null;
        }
        File executable = new File(dir, addExtension(commandName));
        if (executable.exists()) {
            return executable;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void buildJrePackages() {
        Vector vector = new Vector();
        jrePackages = vector;
        switch (javaVersionNumber) {
            case 12:
                jrePackages.addElement("com.sun.java");
                jrePackages.addElement("com.sun.image");
                break;
            case 13:
                jrePackages.addElement("org.omg");
                jrePackages.addElement("com.sun.corba");
                jrePackages.addElement("com.sun.jndi");
                jrePackages.addElement("com.sun.media");
                jrePackages.addElement("com.sun.naming");
                jrePackages.addElement("com.sun.org.omg");
                jrePackages.addElement("com.sun.rmi");
                jrePackages.addElement("sunw.io");
                jrePackages.addElement("sunw.util");
                jrePackages.addElement("com.sun.java");
                jrePackages.addElement("com.sun.image");
                break;
            case 14:
            case 15:
                vector.addElement("org.apache.crimson");
                jrePackages.addElement("org.apache.xalan");
                jrePackages.addElement("org.apache.xml");
                jrePackages.addElement("org.apache.xpath");
                jrePackages.addElement("org.ietf.jgss");
                jrePackages.addElement("org.w3c.dom");
                jrePackages.addElement("org.xml.sax");
                jrePackages.addElement("org.omg");
                jrePackages.addElement("com.sun.corba");
                jrePackages.addElement("com.sun.jndi");
                jrePackages.addElement("com.sun.media");
                jrePackages.addElement("com.sun.naming");
                jrePackages.addElement("com.sun.org.omg");
                jrePackages.addElement("com.sun.rmi");
                jrePackages.addElement("sunw.io");
                jrePackages.addElement("sunw.util");
                jrePackages.addElement("com.sun.java");
                jrePackages.addElement("com.sun.image");
                break;
        }
        jrePackages.addElement("sun");
        jrePackages.addElement("java");
        jrePackages.addElement("javax");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Vector getJrePackageTestCases() {
        Vector tests = new Vector();
        tests.addElement("java.lang.Object");
        switch (javaVersionNumber) {
            case 12:
                tests.addElement("javax.accessibility.Accessible");
                tests.addElement("sun.misc.BASE64Encoder");
                tests.addElement("com.sun.image.codec.jpeg.JPEGCodec");
                break;
            case 13:
                tests.addElement("org.omg.CORBA.Any");
                tests.addElement("com.sun.corba.se.internal.corba.AnyImpl");
                tests.addElement("com.sun.jndi.ldap.LdapURL");
                tests.addElement("com.sun.media.sound.Printer");
                tests.addElement("com.sun.naming.internal.VersionHelper");
                tests.addElement("com.sun.org.omg.CORBA.Initializer");
                tests.addElement("sunw.io.Serializable");
                tests.addElement("sunw.util.EventListener");
                tests.addElement("javax.accessibility.Accessible");
                tests.addElement("sun.misc.BASE64Encoder");
                tests.addElement("com.sun.image.codec.jpeg.JPEGCodec");
                break;
            case 14:
            case 15:
                tests.addElement("sun.audio.AudioPlayer");
                tests.addElement("org.apache.crimson.parser.ContentModel");
                tests.addElement("org.apache.xalan.processor.ProcessorImport");
                tests.addElement("org.apache.xml.utils.URI");
                tests.addElement("org.apache.xpath.XPathFactory");
                tests.addElement("org.ietf.jgss.Oid");
                tests.addElement("org.w3c.dom.Attr");
                tests.addElement("org.xml.sax.XMLReader");
                tests.addElement("org.omg.CORBA.Any");
                tests.addElement("com.sun.corba.se.internal.corba.AnyImpl");
                tests.addElement("com.sun.jndi.ldap.LdapURL");
                tests.addElement("com.sun.media.sound.Printer");
                tests.addElement("com.sun.naming.internal.VersionHelper");
                tests.addElement("com.sun.org.omg.CORBA.Initializer");
                tests.addElement("sunw.io.Serializable");
                tests.addElement("sunw.util.EventListener");
                tests.addElement("javax.accessibility.Accessible");
                tests.addElement("sun.misc.BASE64Encoder");
                tests.addElement("com.sun.image.codec.jpeg.JPEGCodec");
                break;
        }
        tests.addElement("sun.reflect.SerializationConstructorAccessorImpl");
        tests.addElement("sun.net.www.http.HttpClient");
        tests.addElement("sun.audio.AudioPlayer");
        return tests;
    }

    public static Vector getJrePackages() {
        if (jrePackages == null) {
            buildJrePackages();
        }
        return jrePackages;
    }
}
