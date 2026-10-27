package org.apache.tools.ant;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class PropertyHelper {
    private PropertyHelper next;
    private Project project;
    private Hashtable properties = new Hashtable();
    private Hashtable userProperties = new Hashtable();
    private Hashtable inheritedProperties = new Hashtable();

    protected PropertyHelper() {
    }

    public void setProject(Project p) {
        this.project = p;
    }

    public void setNext(PropertyHelper next) {
        this.next = next;
    }

    public PropertyHelper getNext() {
        return this.next;
    }

    public static synchronized PropertyHelper getPropertyHelper(Project project) {
        PropertyHelper helper = (PropertyHelper) project.getReference("ant.PropertyHelper");
        if (helper != null) {
            return helper;
        }
        PropertyHelper helper2 = new PropertyHelper();
        helper2.setProject(project);
        project.addReference("ant.PropertyHelper", helper2);
        return helper2;
    }

    public boolean setPropertyHook(String ns, String name, Object value, boolean inherited, boolean user, boolean isNew) {
        if (getNext() != null) {
            boolean subst = getNext().setPropertyHook(ns, name, value, inherited, user, isNew);
            if (subst) {
                return true;
            }
            return false;
        }
        return false;
    }

    public Object getPropertyHook(String ns, String name, boolean user) {
        Object o;
        if (getNext() != null && (o = getNext().getPropertyHook(ns, name, user)) != null) {
            return o;
        }
        if (!name.startsWith("toString:")) {
            return null;
        }
        Object v = this.project.getReference(name.substring("toString:".length()));
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public void parsePropertyString(String value, Vector fragments, Vector propertyRefs) throws BuildException {
        parsePropertyStringDefault(value, fragments, propertyRefs);
    }

    public String replaceProperties(String ns, String value, Hashtable keys) throws BuildException {
        String string;
        if (value == null) {
            return null;
        }
        Vector fragments = new Vector();
        Vector propertyRefs = new Vector();
        parsePropertyString(value, fragments, propertyRefs);
        StringBuffer sb = new StringBuffer();
        Enumeration i = fragments.elements();
        Enumeration j = propertyRefs.elements();
        while (i.hasMoreElements()) {
            String fragment = (String) i.nextElement();
            if (fragment == null) {
                String propertyName = (String) j.nextElement();
                Object replacement = null;
                if (keys != null) {
                    replacement = keys.get(propertyName);
                }
                if (replacement == null) {
                    replacement = getProperty(ns, propertyName);
                }
                if (replacement == null) {
                    Project project = this.project;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Property ${");
                    stringBuffer.append(propertyName);
                    stringBuffer.append("} has not been set");
                    project.log(stringBuffer.toString(), 3);
                }
                if (replacement != null) {
                    string = replacement.toString();
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("${");
                    stringBuffer2.append(propertyName);
                    stringBuffer2.append("}");
                    string = stringBuffer2.toString();
                }
                fragment = string;
            }
            sb.append(fragment);
        }
        return sb.toString();
    }

    public synchronized boolean setProperty(String ns, String name, Object value, boolean verbose) {
        if (this.userProperties.get(name) != null) {
            if (verbose) {
                Project project = this.project;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Override ignored for user property ");
                stringBuffer.append(name);
                project.log(stringBuffer.toString(), 3);
            }
            return false;
        }
        boolean done = setPropertyHook(ns, name, value, false, false, false);
        if (done) {
            return true;
        }
        if (this.properties.get(name) != null && verbose) {
            Project project2 = this.project;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Overriding previous definition of property ");
            stringBuffer2.append(name);
            project2.log(stringBuffer2.toString(), 3);
        }
        if (verbose) {
            Project project3 = this.project;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Setting project property: ");
            stringBuffer3.append(name);
            stringBuffer3.append(" -> ");
            stringBuffer3.append(value);
            project3.log(stringBuffer3.toString(), 4);
        }
        this.properties.put(name, value);
        return true;
    }

    public synchronized void setNewProperty(String ns, String name, Object value) {
        if (this.properties.get(name) != null) {
            Project project = this.project;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Override ignored for property ");
            stringBuffer.append(name);
            project.log(stringBuffer.toString(), 3);
            return;
        }
        boolean done = setPropertyHook(ns, name, value, false, false, true);
        if (done) {
            return;
        }
        Project project2 = this.project;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Setting project property: ");
        stringBuffer2.append(name);
        stringBuffer2.append(" -> ");
        stringBuffer2.append(value);
        project2.log(stringBuffer2.toString(), 4);
        if (name != null && value != null) {
            this.properties.put(name, value);
        }
    }

    public synchronized void setUserProperty(String ns, String name, Object value) {
        Project project = this.project;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Setting ro project property: ");
        stringBuffer.append(name);
        stringBuffer.append(" -> ");
        stringBuffer.append(value);
        project.log(stringBuffer.toString(), 4);
        this.userProperties.put(name, value);
        boolean done = setPropertyHook(ns, name, value, false, true, false);
        if (done) {
            return;
        }
        this.properties.put(name, value);
    }

    public synchronized void setInheritedProperty(String ns, String name, Object value) {
        this.inheritedProperties.put(name, value);
        Project project = this.project;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Setting ro project property: ");
        stringBuffer.append(name);
        stringBuffer.append(" -> ");
        stringBuffer.append(value);
        project.log(stringBuffer.toString(), 4);
        this.userProperties.put(name, value);
        boolean done = setPropertyHook(ns, name, value, true, false, false);
        if (done) {
            return;
        }
        this.properties.put(name, value);
    }

    public synchronized Object getProperty(String ns, String name) {
        if (name == null) {
            return null;
        }
        Object o = getPropertyHook(ns, name, false);
        if (o != null) {
            return o;
        }
        return this.properties.get(name);
    }

    public synchronized Object getUserProperty(String ns, String name) {
        if (name == null) {
            return null;
        }
        Object o = getPropertyHook(ns, name, true);
        if (o != null) {
            return o;
        }
        return this.userProperties.get(name);
    }

    public Hashtable getProperties() {
        return new Hashtable(this.properties);
    }

    public Hashtable getUserProperties() {
        return new Hashtable(this.userProperties);
    }

    public void copyInheritedProperties(Project other) {
        Enumeration e = this.inheritedProperties.keys();
        while (e.hasMoreElements()) {
            String arg = e.nextElement().toString();
            if (other.getUserProperty(arg) == null) {
                Object value = this.inheritedProperties.get(arg);
                other.setInheritedProperty(arg, value.toString());
            }
        }
    }

    public void copyUserProperties(Project other) {
        Enumeration e = this.userProperties.keys();
        while (e.hasMoreElements()) {
            Object arg = e.nextElement();
            if (!this.inheritedProperties.containsKey(arg)) {
                Object value = this.userProperties.get(arg);
                other.setUserProperty(arg.toString(), value.toString());
            }
        }
    }

    static void parsePropertyStringDefault(String value, Vector fragments, Vector propertyRefs) throws BuildException {
        int prev = 0;
        while (true) {
            int iIndexOf = value.indexOf("$", prev);
            if (iIndexOf >= 0) {
                if (iIndexOf > 0) {
                    fragments.addElement(value.substring(prev, iIndexOf));
                }
                if (iIndexOf == value.length() - 1) {
                    fragments.addElement("$");
                    prev = iIndexOf + 1;
                } else if (value.charAt(iIndexOf + 1) != '{') {
                    if (value.charAt(iIndexOf + 1) == '$') {
                        fragments.addElement("$");
                        prev = iIndexOf + 2;
                    } else {
                        fragments.addElement(value.substring(iIndexOf, iIndexOf + 2));
                        prev = iIndexOf + 2;
                    }
                } else {
                    int endName = value.indexOf(125, iIndexOf);
                    if (endName < 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Syntax error in property: ");
                        stringBuffer.append(value);
                        throw new BuildException(stringBuffer.toString());
                    }
                    String propertyName = value.substring(iIndexOf + 2, endName);
                    fragments.addElement(null);
                    propertyRefs.addElement(propertyName);
                    prev = endName + 1;
                }
            } else {
                if (prev < value.length()) {
                    fragments.addElement(value.substring(prev));
                    return;
                }
                return;
            }
        }
    }
}
