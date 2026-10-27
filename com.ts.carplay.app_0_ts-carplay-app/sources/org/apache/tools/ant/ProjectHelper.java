package org.apache.tools.ant;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.apache.tools.ant.util.LoaderUtils;
import org.xml.sax.AttributeList;

/* JADX INFO: loaded from: classes.dex */
public class ProjectHelper {
    public static final String ANTLIB_URI = "antlib:";
    public static final String ANT_CORE_URI = "antlib:org.apache.tools.ant";
    public static final String ANT_CURRENT_URI = "ant:current";
    public static final String ANT_TYPE = "ant-type";
    public static final String HELPER_PROPERTY = "org.apache.tools.ant.ProjectHelper";
    public static final String SERVICE_ID = "META-INF/services/org.apache.tools.ant.ProjectHelper";
    private Vector importStack = new Vector();

    public static void configureProject(Project project, File buildFile) throws ClassNotFoundException, BuildException {
        ProjectHelper helper = getProjectHelper();
        project.addReference("ant.projectHelper", helper);
        helper.parse(project, buildFile);
    }

    public Vector getImportStack() {
        return this.importStack;
    }

    public void parse(Project project, Object source) throws BuildException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ProjectHelper.parse() must be implemented in a helper plugin ");
        stringBuffer.append(getClass().getName());
        throw new BuildException(stringBuffer.toString());
    }

    public static ProjectHelper getProjectHelper() throws ClassNotFoundException, BuildException {
        InputStreamReader isr;
        ProjectHelper helper = null;
        String helperClass = System.getProperty(HELPER_PROPERTY);
        if (helperClass != null) {
            try {
                helper = newHelper(helperClass);
            } catch (SecurityException e) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to load ProjectHelper class \"");
                stringBuffer.append(helperClass);
                stringBuffer.append(" specified in system property ");
                stringBuffer.append(HELPER_PROPERTY);
                printStream.println(stringBuffer.toString());
            }
        }
        if (helper == null) {
            try {
                ClassLoader classLoader = LoaderUtils.getContextClassLoader();
                InputStream is = null;
                if (classLoader != null) {
                    is = classLoader.getResourceAsStream(SERVICE_ID);
                }
                if (is == null) {
                    is = ClassLoader.getSystemResourceAsStream(SERVICE_ID);
                }
                if (is != null) {
                    try {
                        isr = new InputStreamReader(is, "UTF-8");
                    } catch (UnsupportedEncodingException e2) {
                        isr = new InputStreamReader(is);
                    }
                    BufferedReader rd = new BufferedReader(isr);
                    String helperClassName = rd.readLine();
                    rd.close();
                    if (helperClassName != null && !"".equals(helperClassName)) {
                        helper = newHelper(helperClassName);
                    }
                }
            } catch (Exception e3) {
                System.out.println("Unable to load ProjectHelper from service \"META-INF/services/org.apache.tools.ant.ProjectHelper");
            }
        }
        if (helper != null) {
            return helper;
        }
        try {
            return new ProjectHelper2();
        } catch (Throwable e4) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to load default ProjectHelper due to ");
            stringBuffer2.append(e4.getClass().getName());
            stringBuffer2.append(": ");
            stringBuffer2.append(e4.getMessage());
            String message = stringBuffer2.toString();
            throw new BuildException(message, e4);
        }
    }

    private static ProjectHelper newHelper(String helperClass) throws ClassNotFoundException, BuildException {
        ClassLoader classLoader = LoaderUtils.getContextClassLoader();
        Class<?> clsLoadClass = null;
        if (classLoader != null) {
            try {
                try {
                    clsLoadClass = classLoader.loadClass(helperClass);
                } catch (ClassNotFoundException e) {
                }
            } catch (Exception e2) {
                throw new BuildException(e2);
            }
        }
        if (clsLoadClass == null) {
            clsLoadClass = Class.forName(helperClass);
        }
        return (ProjectHelper) clsLoadClass.newInstance();
    }

    public static ClassLoader getContextClassLoader() {
        if (!LoaderUtils.isContextLoaderAvailable()) {
            return null;
        }
        return LoaderUtils.getContextClassLoader();
    }

    public static void configure(Object target, AttributeList attrs, Project project) throws BuildException {
        if (target instanceof TypeAdapter) {
            target = ((TypeAdapter) target).getProxy();
        }
        IntrospectionHelper ih = IntrospectionHelper.getHelper(target.getClass());
        project.addBuildListener(ih);
        for (int i = 0; i < attrs.getLength(); i++) {
            String value = replaceProperties(project, attrs.getValue(i), project.getProperties());
            try {
                ih.setAttribute(project, target, attrs.getName(i).toLowerCase(Locale.US), value);
            } catch (BuildException be) {
                if (!attrs.getName(i).equals("id")) {
                    throw be;
                }
            }
        }
    }

    public static void addText(Project project, Object target, char[] buf, int start, int count) throws BuildException {
        addText(project, target, new String(buf, start, count));
    }

    public static void addText(Project project, Object target, String text) throws BuildException {
        if (text == null) {
            return;
        }
        if (target instanceof TypeAdapter) {
            target = ((TypeAdapter) target).getProxy();
        }
        IntrospectionHelper.getHelper(target.getClass()).addText(project, target, text);
    }

    public static void storeChild(Project project, Object parent, Object child, String tag) {
        IntrospectionHelper ih = IntrospectionHelper.getHelper(parent.getClass());
        ih.storeElement(project, parent, child, tag);
    }

    public static String replaceProperties(Project project, String value) throws BuildException {
        return project.replaceProperties(value);
    }

    public static String replaceProperties(Project project, String value, Hashtable keys) throws BuildException {
        PropertyHelper ph = PropertyHelper.getPropertyHelper(project);
        return ph.replaceProperties(null, value, keys);
    }

    public static void parsePropertyString(String value, Vector fragments, Vector propertyRefs) throws BuildException {
        PropertyHelper.parsePropertyStringDefault(value, fragments, propertyRefs);
    }

    public static String genComponentName(String uri, String name) {
        if (uri == null || uri.equals("") || uri.equals(ANT_CORE_URI)) {
            return name;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(uri);
        stringBuffer.append(":");
        stringBuffer.append(name);
        return stringBuffer.toString();
    }

    public static String extractUriFromComponentName(String componentName) {
        int index;
        if (componentName == null || (index = componentName.lastIndexOf(58)) == -1) {
            return "";
        }
        return componentName.substring(0, index);
    }

    public static String extractNameFromComponentName(String componentName) {
        int index = componentName.lastIndexOf(58);
        if (index == -1) {
            return componentName;
        }
        return componentName.substring(index + 1);
    }

    public static BuildException addLocationToBuildException(BuildException ex, Location newLocation) {
        if (ex.getLocation() == null || ex.getMessage() == null) {
            return ex;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The following error occurred while executing this line:");
        stringBuffer.append(System.getProperty("line.separator"));
        stringBuffer.append(ex.getLocation().toString());
        stringBuffer.append(ex.getMessage());
        String errorMessage = stringBuffer.toString();
        if (newLocation == null) {
            return new BuildException(errorMessage, ex);
        }
        return new BuildException(errorMessage, ex, newLocation);
    }
}
