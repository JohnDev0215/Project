package org.apache.tools.ant.types;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class CommandlineJava implements Cloneable {
    private String vmVersion;
    private Commandline vmCommand = new Commandline();
    private Commandline javaCommand = new Commandline();
    private SysProperties sysProperties = new SysProperties();
    private Path classpath = null;
    private Path bootclasspath = null;
    private String maxMemory = null;
    private Assertions assertions = null;
    private boolean executeJar = false;

    public static class SysProperties extends Environment implements Cloneable {
        Properties sys = null;
        private Vector propertySets = new Vector();

        @Override // org.apache.tools.ant.types.Environment
        public String[] getVariables() throws BuildException {
            List definitions = new LinkedList();
            ListIterator list = definitions.listIterator();
            addDefinitionsToList(list);
            if (definitions.size() == 0) {
                return null;
            }
            return (String[]) definitions.toArray(new String[0]);
        }

        public void addDefinitionsToList(ListIterator listIt) {
            String[] props = super.getVariables();
            if (props != null) {
                for (String str : props) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("-D");
                    stringBuffer.append(str);
                    listIt.add(stringBuffer.toString());
                }
            }
            Properties propertySetProperties = mergePropertySets();
            Enumeration e = propertySetProperties.keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = propertySetProperties.getProperty(key);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("-D");
                stringBuffer2.append(key);
                stringBuffer2.append("=");
                stringBuffer2.append(value);
                listIt.add(stringBuffer2.toString());
            }
        }

        public int size() {
            Properties p = mergePropertySets();
            return this.variables.size() + p.size();
        }

        public void setSystem() throws BuildException {
            try {
                this.sys = System.getProperties();
                Properties p = new Properties();
                Enumeration<?> enumerationPropertyNames = this.sys.propertyNames();
                while (enumerationPropertyNames.hasMoreElements()) {
                    String name = (String) enumerationPropertyNames.nextElement();
                    p.put(name, this.sys.getProperty(name));
                }
                p.putAll(mergePropertySets());
                Enumeration e = this.variables.elements();
                while (e.hasMoreElements()) {
                    Environment.Variable v = (Environment.Variable) e.nextElement();
                    v.validate();
                    p.put(v.getKey(), v.getValue());
                }
                System.setProperties(p);
            } catch (SecurityException e2) {
                throw new BuildException("Cannot modify system properties", e2);
            }
        }

        public void restoreSystem() throws BuildException {
            Properties properties = this.sys;
            if (properties == null) {
                throw new BuildException("Unbalanced nesting of SysProperties");
            }
            try {
                System.setProperties(properties);
                this.sys = null;
            } catch (SecurityException e) {
                throw new BuildException("Cannot modify system properties", e);
            }
        }

        public Object clone() throws CloneNotSupportedException {
            try {
                SysProperties c = (SysProperties) super.clone();
                c.variables = (Vector) this.variables.clone();
                c.propertySets = (Vector) this.propertySets.clone();
                return c;
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

        public void addSyspropertyset(PropertySet ps) {
            this.propertySets.addElement(ps);
        }

        public void addSysproperties(SysProperties ps) {
            this.variables.addAll(ps.variables);
            this.propertySets.addAll(ps.propertySets);
        }

        private Properties mergePropertySets() {
            Properties p = new Properties();
            Enumeration e = this.propertySets.elements();
            while (e.hasMoreElements()) {
                PropertySet ps = (PropertySet) e.nextElement();
                p.putAll(ps.getProperties());
            }
            return p;
        }
    }

    public CommandlineJava() {
        setVm(JavaEnvUtils.getJreExecutable("java"));
        setVmversion(JavaEnvUtils.getJavaVersion());
    }

    public Commandline.Argument createArgument() {
        return this.javaCommand.createArgument();
    }

    public Commandline.Argument createVmArgument() {
        return this.vmCommand.createArgument();
    }

    public void addSysproperty(Environment.Variable sysp) {
        this.sysProperties.addVariable(sysp);
    }

    public void addSyspropertyset(PropertySet sysp) {
        this.sysProperties.addSyspropertyset(sysp);
    }

    public void addSysproperties(SysProperties sysp) {
        this.sysProperties.addSysproperties(sysp);
    }

    public void setVm(String vm) {
        this.vmCommand.setExecutable(vm);
    }

    public void setVmversion(String value) {
        this.vmVersion = value;
    }

    public Assertions getAssertions() {
        return this.assertions;
    }

    public void setAssertions(Assertions assertions) {
        this.assertions = assertions;
    }

    public void setJar(String jarpathname) {
        this.javaCommand.setExecutable(jarpathname);
        this.executeJar = true;
    }

    public String getJar() {
        if (this.executeJar) {
            return this.javaCommand.getExecutable();
        }
        return null;
    }

    public void setClassname(String classname) {
        this.javaCommand.setExecutable(classname);
        this.executeJar = false;
    }

    public String getClassname() {
        if (!this.executeJar) {
            return this.javaCommand.getExecutable();
        }
        return null;
    }

    public Path createClasspath(Project p) {
        if (this.classpath == null) {
            this.classpath = new Path(p);
        }
        return this.classpath;
    }

    public Path createBootclasspath(Project p) {
        if (this.bootclasspath == null) {
            this.bootclasspath = new Path(p);
        }
        return this.bootclasspath;
    }

    public String getVmversion() {
        return this.vmVersion;
    }

    public String[] getCommandline() {
        List commands = new LinkedList();
        ListIterator listIterator = commands.listIterator();
        addCommandsToList(listIterator);
        return (String[]) commands.toArray(new String[0]);
    }

    private void addCommandsToList(ListIterator listIterator) {
        getActualVMCommand().addCommandToList(listIterator);
        this.sysProperties.addDefinitionsToList(listIterator);
        if (haveBootclasspath(true)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-Xbootclasspath:");
            stringBuffer.append(this.bootclasspath.toString());
            listIterator.add(stringBuffer.toString());
        }
        if (haveClasspath()) {
            listIterator.add("-classpath");
            listIterator.add(this.classpath.concatSystemClasspath("ignore").toString());
        }
        if (getAssertions() != null) {
            getAssertions().applyAssertions(listIterator);
        }
        if (this.executeJar) {
            listIterator.add("-jar");
        }
        this.javaCommand.addCommandToList(listIterator);
    }

    public void setMaxmemory(String max) {
        this.maxMemory = max;
    }

    public String toString() {
        return Commandline.toString(getCommandline());
    }

    public String describeCommand() {
        return Commandline.describeCommand(getCommandline());
    }

    public String describeJavaCommand() {
        return Commandline.describeCommand(getJavaCommand());
    }

    protected Commandline getActualVMCommand() {
        Commandline actualVMCommand = (Commandline) this.vmCommand.clone();
        if (this.maxMemory != null) {
            if (this.vmVersion.startsWith("1.1")) {
                Commandline.Argument argumentCreateArgument = actualVMCommand.createArgument();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("-mx");
                stringBuffer.append(this.maxMemory);
                argumentCreateArgument.setValue(stringBuffer.toString());
            } else {
                Commandline.Argument argumentCreateArgument2 = actualVMCommand.createArgument();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("-Xmx");
                stringBuffer2.append(this.maxMemory);
                argumentCreateArgument2.setValue(stringBuffer2.toString());
            }
        }
        return actualVMCommand;
    }

    public int size() {
        int size = getActualVMCommand().size() + this.javaCommand.size() + this.sysProperties.size();
        if (haveClasspath()) {
            size += 2;
        }
        if (haveBootclasspath(true)) {
            size++;
        }
        if (this.executeJar) {
            size++;
        }
        if (getAssertions() != null) {
            return size + getAssertions().size();
        }
        return size;
    }

    public Commandline getJavaCommand() {
        return this.javaCommand;
    }

    public Commandline getVmCommand() {
        return getActualVMCommand();
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public Path getBootclasspath() {
        return this.bootclasspath;
    }

    public void setSystemProperties() throws BuildException {
        this.sysProperties.setSystem();
    }

    public void restoreSystemProperties() throws BuildException {
        this.sysProperties.restoreSystem();
    }

    public SysProperties getSystemProperties() {
        return this.sysProperties;
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            CommandlineJava c = (CommandlineJava) super.clone();
            c.vmCommand = (Commandline) this.vmCommand.clone();
            c.javaCommand = (Commandline) this.javaCommand.clone();
            c.sysProperties = (SysProperties) this.sysProperties.clone();
            if (this.classpath != null) {
                c.classpath = (Path) this.classpath.clone();
            }
            if (this.bootclasspath != null) {
                c.bootclasspath = (Path) this.bootclasspath.clone();
            }
            if (this.assertions != null) {
                c.assertions = (Assertions) this.assertions.clone();
            }
            return c;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    public void clearJavaArgs() {
        this.javaCommand.clearArgs();
    }

    protected boolean haveClasspath() {
        Path path = this.classpath;
        Path fullClasspath = path != null ? path.concatSystemClasspath("ignore") : null;
        return fullClasspath != null && fullClasspath.toString().trim().length() > 0;
    }

    protected boolean haveBootclasspath(boolean log) {
        Path path = this.bootclasspath;
        if (path != null && path.toString().trim().length() > 0) {
            if (!this.bootclasspath.toString().equals(this.bootclasspath.concatSystemClasspath("ignore").toString())) {
                if (log) {
                    this.bootclasspath.log("Ignoring bootclasspath as build.sysclasspath has been set.");
                    return false;
                }
                return false;
            }
            if (this.vmVersion.startsWith("1.1")) {
                if (log) {
                    this.bootclasspath.log("Ignoring bootclasspath as the target VM doesn't support it.");
                    return false;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
