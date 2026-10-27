package org.apache.tools.ant.types;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.regexp.RegexpMatcher;
import org.apache.tools.ant.util.regexp.RegexpMatcherFactory;

/* JADX INFO: loaded from: classes.dex */
public class PropertySet extends DataType {
    static /* synthetic */ Class class$org$apache$tools$ant$types$PropertySet;
    private Set cachedNames;
    private Mapper mapper;
    private boolean dynamic = true;
    private boolean negate = false;
    private Vector ptyRefs = new Vector();
    private Vector setRefs = new Vector();
    private boolean noAttributeSet = true;

    public static class PropertyRef {
        private String builtin;
        private int count;
        private String name;
        private String prefix;
        private String regex;

        public void setName(String name) {
            assertValid(FilenameSelector.NAME_KEY, name);
            this.name = name;
        }

        public void setRegex(String regex) {
            assertValid("regex", regex);
            this.regex = regex;
        }

        public void setPrefix(String prefix) {
            assertValid("prefix", prefix);
            this.prefix = prefix;
        }

        public void setBuiltin(BuiltinPropertySetName b) {
            String pBuiltIn = b.getValue();
            assertValid("builtin", pBuiltIn);
            this.builtin = pBuiltIn;
        }

        private void assertValid(String attr, String value) {
            if (value == null || value.length() < 1) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid attribute: ");
                stringBuffer.append(attr);
                throw new BuildException(stringBuffer.toString());
            }
            int i = this.count + 1;
            this.count = i;
            if (i != 1) {
                throw new BuildException("Attributes name, regex, and prefix are mutually exclusive");
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("name=");
            stringBuffer.append(this.name);
            stringBuffer.append(", regex=");
            stringBuffer.append(this.regex);
            stringBuffer.append(", prefix=");
            stringBuffer.append(this.prefix);
            stringBuffer.append(", builtin=");
            stringBuffer.append(this.builtin);
            return stringBuffer.toString();
        }
    }

    public void appendName(String name) {
        PropertyRef r = new PropertyRef();
        r.setName(name);
        addPropertyref(r);
    }

    public void appendRegex(String regex) {
        PropertyRef r = new PropertyRef();
        r.setRegex(regex);
        addPropertyref(r);
    }

    public void appendPrefix(String prefix) {
        PropertyRef r = new PropertyRef();
        r.setPrefix(prefix);
        addPropertyref(r);
    }

    public void appendBuiltin(BuiltinPropertySetName b) {
        PropertyRef r = new PropertyRef();
        r.setBuiltin(b);
        addPropertyref(r);
    }

    public void setMapper(String type, String from, String to) {
        Mapper m = createMapper();
        Mapper.MapperType mapperType = new Mapper.MapperType();
        mapperType.setValue(type);
        m.setFrom(from);
        m.setTo(to);
    }

    public void addPropertyref(PropertyRef ref) {
        assertNotReference();
        this.ptyRefs.addElement(ref);
    }

    public void addPropertyset(PropertySet ref) {
        assertNotReference();
        this.setRefs.addElement(ref);
    }

    public Mapper createMapper() {
        assertNotReference();
        if (this.mapper != null) {
            throw new BuildException("Too many <mapper>s!");
        }
        Mapper mapper = new Mapper(getProject());
        this.mapper = mapper;
        return mapper;
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    public void setDynamic(boolean dynamic) {
        assertNotReference();
        this.dynamic = dynamic;
    }

    public void setNegate(boolean negate) {
        assertNotReference();
        this.negate = negate;
    }

    public boolean getDynamic() {
        return isReference() ? getRef().dynamic : this.dynamic;
    }

    public Mapper getMapper() {
        return isReference() ? getRef().mapper : this.mapper;
    }

    private Hashtable getAllSystemProperties() {
        Hashtable ret = new Hashtable();
        Enumeration<?> enumerationPropertyNames = System.getProperties().propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String name = (String) enumerationPropertyNames.nextElement();
            ret.put(name, System.getProperties().getProperty(name));
        }
        return ret;
    }

    public Properties getProperties() {
        Set set;
        Set<String> set2;
        FileNameMapper implementation;
        String[] strArrMapFileName;
        if (isReference()) {
            return getRef().getProperties();
        }
        Project project = getProject();
        Hashtable allSystemProperties = project == null ? getAllSystemProperties() : project.getProperties();
        Enumeration enumerationElements = this.setRefs.elements();
        while (enumerationElements.hasMoreElements()) {
            allSystemProperties.putAll(((PropertySet) enumerationElements.nextElement()).getProperties());
        }
        if (getDynamic() || this.cachedNames == null) {
            Set hashSet = new HashSet();
            addPropertyNames(hashSet, allSystemProperties);
            Enumeration enumerationElements2 = this.setRefs.elements();
            while (enumerationElements2.hasMoreElements()) {
                hashSet.addAll(((PropertySet) enumerationElements2.nextElement()).getProperties().keySet());
            }
            if (!this.negate) {
                set = hashSet;
            } else {
                HashSet hashSet2 = new HashSet(allSystemProperties.keySet());
                hashSet2.removeAll(hashSet);
                set = hashSet2;
            }
            set2 = set;
            if (!getDynamic()) {
                this.cachedNames = set;
                set2 = set;
            }
        } else {
            set2 = this.cachedNames;
        }
        Mapper mapper = getMapper();
        if (mapper == null) {
            implementation = null;
        } else {
            implementation = mapper.getImplementation();
        }
        Properties properties = new Properties();
        for (String str : set2) {
            String str2 = (String) allSystemProperties.get(str);
            if (implementation != null && (strArrMapFileName = implementation.mapFileName(str)) != null) {
                str = strArrMapFileName[0];
            }
            properties.setProperty(str, str2);
        }
        return properties;
    }

    private void addPropertyNames(Set names, Hashtable properties) {
        Project prj = getProject();
        Enumeration e = this.ptyRefs.elements();
        while (e.hasMoreElements()) {
            PropertyRef r = (PropertyRef) e.nextElement();
            if (r.name == null) {
                if (r.prefix == null) {
                    if (r.regex == null) {
                        if (r.builtin != null) {
                            if (!r.builtin.equals("all")) {
                                if (!r.builtin.equals("system")) {
                                    if (r.builtin.equals("commandline")) {
                                        names.addAll(getProject().getUserProperties().keySet());
                                    } else {
                                        throw new BuildException("Impossible: Invalid builtin attribute!");
                                    }
                                } else {
                                    names.addAll(System.getProperties().keySet());
                                }
                            } else {
                                names.addAll(properties.keySet());
                            }
                        } else {
                            throw new BuildException("Impossible: Invalid PropertyRef!");
                        }
                    } else {
                        RegexpMatcherFactory matchMaker = new RegexpMatcherFactory();
                        RegexpMatcher matcher = matchMaker.newRegexpMatcher();
                        matcher.setPattern(r.regex);
                        Enumeration p = properties.keys();
                        while (p.hasMoreElements()) {
                            String name = (String) p.nextElement();
                            if (matcher.matches(name)) {
                                names.add(name);
                            }
                        }
                    }
                } else {
                    Enumeration p2 = properties.keys();
                    while (p2.hasMoreElements()) {
                        String name2 = (String) p2.nextElement();
                        if (name2.startsWith(r.prefix)) {
                            names.add(name2);
                        }
                    }
                }
            } else if (prj != null && prj.getProperty(r.name) != null) {
                names.add(r.name);
            }
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected PropertySet getRef() {
        Class clsClass$ = class$org$apache$tools$ant$types$PropertySet;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.types.PropertySet");
            class$org$apache$tools$ant$types$PropertySet = clsClass$;
        }
        return (PropertySet) getCheckedRef(clsClass$, "propertyset");
    }

    @Override // org.apache.tools.ant.types.DataType
    public final void setRefid(Reference r) {
        if (!this.noAttributeSet) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    protected final void assertNotReference() {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.noAttributeSet = false;
    }

    public static class BuiltinPropertySetName extends EnumeratedAttribute {
        static final String ALL = "all";
        static final String COMMANDLINE = "commandline";
        static final String SYSTEM = "system";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{ALL, SYSTEM, COMMANDLINE};
        }
    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        TreeMap sorted = new TreeMap(getProperties());
        for (Map.Entry e : sorted.entrySet()) {
            if (b.length() != 0) {
                b.append(", ");
            }
            b.append(e.getKey().toString());
            b.append("=");
            b.append(e.getValue().toString());
        }
        return b.toString();
    }
}
