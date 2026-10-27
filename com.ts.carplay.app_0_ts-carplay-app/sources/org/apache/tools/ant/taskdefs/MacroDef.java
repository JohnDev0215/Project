package org.apache.tools.ant.taskdefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;

/* JADX INFO: loaded from: classes.dex */
public class MacroDef extends AntlibDefinition {
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$MacroInstance;
    private String name;
    private NestedSequential nestedSequential;
    private List attributes = new ArrayList();
    private Map elements = new HashMap();
    private String textName = null;
    private Text text = null;
    private boolean hasImplicitElement = false;

    public void setName(String name) {
        this.name = name;
    }

    public void addConfiguredText(Text text) {
        if (this.text != null) {
            throw new BuildException("Only one nested text element allowed");
        }
        if (text.getName() == null) {
            throw new BuildException("the text nested element needed a \"name\" attribute");
        }
        for (Attribute attribute : this.attributes) {
            if (text.getName().equals(attribute.getName())) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("the name \"");
                stringBuffer.append(text.getName());
                stringBuffer.append("\" is already used as an attribute");
                throw new BuildException(stringBuffer.toString());
            }
        }
        this.text = text;
        this.textName = text.getName();
    }

    public Text getText() {
        return this.text;
    }

    public NestedSequential createSequential() {
        if (this.nestedSequential != null) {
            throw new BuildException("Only one sequential allowed");
        }
        NestedSequential nestedSequential = new NestedSequential();
        this.nestedSequential = nestedSequential;
        return nestedSequential;
    }

    public static class NestedSequential implements TaskContainer {
        private List nested = new ArrayList();

        @Override // org.apache.tools.ant.TaskContainer
        public void addTask(Task task) {
            this.nested.add(task);
        }

        public List getNested() {
            return this.nested;
        }

        public boolean similar(NestedSequential other) {
            if (this.nested.size() != other.nested.size()) {
                return false;
            }
            for (int i = 0; i < this.nested.size(); i++) {
                UnknownElement me = (UnknownElement) this.nested.get(i);
                UnknownElement o = (UnknownElement) other.nested.get(i);
                if (!me.similar(o)) {
                    return false;
                }
            }
            return true;
        }
    }

    public UnknownElement getNestedTask() {
        UnknownElement ret = new UnknownElement("sequential");
        ret.setTaskName("sequential");
        ret.setNamespace("");
        ret.setQName("sequential");
        new RuntimeConfigurable(ret, "sequential");
        for (int i = 0; i < this.nestedSequential.getNested().size(); i++) {
            UnknownElement e = (UnknownElement) this.nestedSequential.getNested().get(i);
            ret.addChild(e);
            ret.getWrapper().addChild(e.getWrapper());
        }
        return ret;
    }

    public List getAttributes() {
        return this.attributes;
    }

    public Map getElements() {
        return this.elements;
    }

    public static boolean isValidNameCharacter(char c) {
        return Character.isLetterOrDigit(c) || c == '.' || c == '-';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidName(String name) {
        if (name.length() == 0) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!isValidNameCharacter(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void addConfiguredAttribute(Attribute attribute) {
        if (attribute.getName() == null) {
            throw new BuildException("the attribute nested element needed a \"name\" attribute");
        }
        if (attribute.getName().equals(this.textName)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("the attribute name \"");
            stringBuffer.append(attribute.getName());
            stringBuffer.append("\" has already been used by the text element");
            throw new BuildException(stringBuffer.toString());
        }
        for (int i = 0; i < this.attributes.size(); i++) {
            if (((Attribute) this.attributes.get(i)).getName().equals(attribute.getName())) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("the attribute ");
                stringBuffer2.append(attribute.getName());
                stringBuffer2.append(" has already been specified");
                throw new BuildException(stringBuffer2.toString());
            }
        }
        this.attributes.add(attribute);
    }

    public void addConfiguredElement(TemplateElement element) {
        if (element.getName() == null) {
            throw new BuildException("the element nested element needed a \"name\" attribute");
        }
        if (this.elements.get(element.getName()) != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("the element ");
            stringBuffer.append(element.getName());
            stringBuffer.append(" has already been specified");
            throw new BuildException(stringBuffer.toString());
        }
        if (this.hasImplicitElement || (element.isImplicit() && this.elements.size() != 0)) {
            throw new BuildException("Only one element allowed when using implicit elements");
        }
        this.hasImplicitElement = element.isImplicit();
        this.elements.put(element.getName(), element);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.nestedSequential == null) {
            throw new BuildException("Missing sequential element");
        }
        if (this.name == null) {
            throw new BuildException("Name not specified");
        }
        this.name = ProjectHelper.genComponentName(getURI(), this.name);
        MyAntTypeDefinition def = new MyAntTypeDefinition(this);
        def.setName(this.name);
        Class clsClass$ = class$org$apache$tools$ant$taskdefs$MacroInstance;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.taskdefs.MacroInstance");
            class$org$apache$tools$ant$taskdefs$MacroInstance = clsClass$;
        }
        def.setClass(clsClass$);
        ComponentHelper helper = ComponentHelper.getComponentHelper(getProject());
        helper.addDataTypeDefinition(def);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public static class Attribute {
        private String defaultValue;
        private String description;
        private String name;

        public void setName(String name) {
            if (!MacroDef.isValidName(name)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Illegal name [");
                stringBuffer.append(name);
                stringBuffer.append("] for attribute");
                throw new BuildException(stringBuffer.toString());
            }
            this.name = name.toLowerCase(Locale.US);
        }

        public String getName() {
            return this.name;
        }

        public void setDefault(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDefault() {
            return this.defaultValue;
        }

        public void setDescription(String desc) {
            this.description = desc;
        }

        public String getDescription() {
            return this.description;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Attribute other = (Attribute) obj;
            String str = this.name;
            if (str == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!str.equals(other.name)) {
                return false;
            }
            String str2 = this.defaultValue;
            if (str2 == null) {
                if (other.defaultValue != null) {
                    return false;
                }
                return true;
            }
            if (!str2.equals(other.defaultValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.defaultValue) + MacroDef.objectHashCode(this.name);
        }
    }

    public static class Text {
        private String description;
        private String name;
        private boolean optional;
        private boolean trim;

        public void setName(String name) {
            if (!MacroDef.isValidName(name)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Illegal name [");
                stringBuffer.append(name);
                stringBuffer.append("] for attribute");
                throw new BuildException(stringBuffer.toString());
            }
            this.name = name.toLowerCase(Locale.US);
        }

        public String getName() {
            return this.name;
        }

        public void setOptional(boolean optional) {
            this.optional = optional;
        }

        public boolean getOptional() {
            return this.optional;
        }

        public void setTrim(boolean trim) {
            this.trim = trim;
        }

        public boolean getTrim() {
            return this.trim;
        }

        public void setDescription(String desc) {
            this.description = desc;
        }

        public String getDescription() {
            return this.description;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Text other = (Text) obj;
            String str = this.name;
            if (str == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!str.equals(other.name)) {
                return false;
            }
            if (this.optional != other.optional || this.trim != other.trim) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.name);
        }
    }

    public static class TemplateElement {
        private String description;
        private String name;
        private boolean optional = false;
        private boolean implicit = false;

        public void setName(String name) {
            if (!MacroDef.isValidName(name)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Illegal name [");
                stringBuffer.append(name);
                stringBuffer.append("] for attribute");
                throw new BuildException(stringBuffer.toString());
            }
            this.name = name.toLowerCase(Locale.US);
        }

        public String getName() {
            return this.name;
        }

        public void setOptional(boolean optional) {
            this.optional = optional;
        }

        public boolean isOptional() {
            return this.optional;
        }

        public void setImplicit(boolean implicit) {
            this.implicit = implicit;
        }

        public boolean isImplicit() {
            return this.implicit;
        }

        public void setDescription(String desc) {
            this.description = desc;
        }

        public String getDescription() {
            return this.description;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            TemplateElement other = (TemplateElement) obj;
            String str = this.name;
            if (str == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!str.equals(other.name)) {
                return false;
            }
            return this.optional == other.optional && this.implicit == other.implicit;
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.name) + (this.optional ? 1 : 0) + (this.implicit ? 1 : 0);
        }
    }

    private boolean sameOrSimilar(Object obj, boolean same) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        MacroDef other = (MacroDef) obj;
        String str = this.name;
        if (str == null) {
            if (other.name == null) {
                return true;
            }
            return false;
        }
        if (!str.equals(other.name)) {
            return false;
        }
        if (other.getLocation() != null && other.getLocation().equals(getLocation()) && !same) {
            return true;
        }
        Text text = this.text;
        if (text == null) {
            if (other.text != null) {
                return false;
            }
        } else if (!text.equals(other.text)) {
            return false;
        }
        if (getURI() == null || getURI().equals("") || getURI().equals(ProjectHelper.ANT_CORE_URI)) {
            if (other.getURI() != null && !other.getURI().equals("") && !other.getURI().equals(ProjectHelper.ANT_CORE_URI)) {
                return false;
            }
        } else if (!getURI().equals(other.getURI())) {
            return false;
        }
        if (this.nestedSequential.similar(other.nestedSequential) && this.attributes.equals(other.attributes) && this.elements.equals(other.elements)) {
            return true;
        }
        return false;
    }

    public boolean similar(Object obj) {
        return sameOrSimilar(obj, false);
    }

    public boolean sameDefinition(Object obj) {
        return sameOrSimilar(obj, true);
    }

    private static class MyAntTypeDefinition extends AntTypeDefinition {
        private MacroDef macroDef;

        public MyAntTypeDefinition(MacroDef macroDef) {
            this.macroDef = macroDef;
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public Object create(Project project) {
            Object o = super.create(project);
            if (o == null) {
                return null;
            }
            ((MacroInstance) o).setMacroDef(this.macroDef);
            return o;
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public boolean sameDefinition(AntTypeDefinition other, Project project) {
            if (!super.sameDefinition(other, project)) {
                return false;
            }
            MyAntTypeDefinition otherDef = (MyAntTypeDefinition) other;
            return this.macroDef.sameDefinition(otherDef.macroDef);
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public boolean similarDefinition(AntTypeDefinition other, Project project) {
            if (!super.similarDefinition(other, project)) {
                return false;
            }
            MyAntTypeDefinition otherDef = (MyAntTypeDefinition) other;
            return this.macroDef.similar(otherDef.macroDef);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int objectHashCode(Object o) {
        if (o == null) {
            return 0;
        }
        return o.hashCode();
    }
}
