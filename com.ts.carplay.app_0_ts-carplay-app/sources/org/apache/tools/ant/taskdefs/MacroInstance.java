package org.apache.tools.ant.taskdefs;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicAttribute;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.taskdefs.MacroDef;

/* JADX INFO: loaded from: classes.dex */
public class MacroInstance extends Task implements DynamicAttribute, TaskContainer {
    private static final int STATE_EXPECT_BRACKET = 1;
    private static final int STATE_EXPECT_NAME = 2;
    private static final int STATE_NORMAL = 0;
    private Hashtable localProperties;
    private MacroDef macroDef;
    private Map presentElements;
    private Map map = new HashMap();
    private Map nsElements = null;
    private String text = null;
    private String implicitTag = null;
    private List unknownElements = new ArrayList();

    public void setMacroDef(MacroDef macroDef) {
        this.macroDef = macroDef;
    }

    public MacroDef getMacroDef() {
        return this.macroDef;
    }

    @Override // org.apache.tools.ant.DynamicAttribute
    public void setDynamicAttribute(String name, String value) {
        this.map.put(name, value);
    }

    public Object createDynamicElement(String name) throws BuildException {
        throw new BuildException("Not implemented any more");
    }

    private Map getNsElements() {
        if (this.nsElements == null) {
            this.nsElements = new HashMap();
            for (Map.Entry entry : this.macroDef.getElements().entrySet()) {
                this.nsElements.put((String) entry.getKey(), entry.getValue());
                MacroDef.TemplateElement te = (MacroDef.TemplateElement) entry.getValue();
                if (te.isImplicit()) {
                    this.implicitTag = te.getName();
                }
            }
        }
        return this.nsElements;
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task nestedTask) {
        this.unknownElements.add(nestedTask);
    }

    private void processTasks() {
        if (this.implicitTag != null) {
            return;
        }
        for (UnknownElement ue : this.unknownElements) {
            String name = ProjectHelper.extractNameFromComponentName(ue.getTag()).toLowerCase(Locale.US);
            if (getNsElements().get(name) == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("unsupported element ");
                stringBuffer.append(name);
                throw new BuildException(stringBuffer.toString());
            }
            if (this.presentElements.get(name) != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Element ");
                stringBuffer2.append(name);
                stringBuffer2.append(" already present");
                throw new BuildException(stringBuffer2.toString());
            }
            this.presentElements.put(name, ue.getChildren());
        }
    }

    public static class Element implements TaskContainer {
        private List unknownElements = new ArrayList();

        @Override // org.apache.tools.ant.TaskContainer
        public void addTask(Task nestedTask) {
            this.unknownElements.add(nestedTask);
        }

        public List getUnknownElements() {
            return this.unknownElements;
        }
    }

    private String macroSubs(String s, Map macroMapping) {
        if (s == null) {
            return null;
        }
        StringBuffer ret = new StringBuffer();
        StringBuffer macroName = null;
        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (state != 0) {
                if (state != 1) {
                    if (state == 2) {
                        if (ch == '}') {
                            state = 0;
                            String name = macroName.toString().toLowerCase(Locale.US);
                            String value = (String) macroMapping.get(name);
                            if (value == null) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("@{");
                                stringBuffer.append(name);
                                stringBuffer.append("}");
                                ret.append(stringBuffer.toString());
                            } else {
                                ret.append(value);
                            }
                            macroName = null;
                        } else {
                            macroName.append(ch);
                        }
                    }
                } else if (ch == '{') {
                    state = 2;
                    macroName = new StringBuffer();
                } else if (ch == '@') {
                    state = 0;
                    ret.append('@');
                } else {
                    state = 0;
                    ret.append('@');
                    ret.append(ch);
                }
            } else if (ch == '@') {
                state = 1;
            } else {
                ret.append(ch);
            }
        }
        if (state == 1) {
            ret.append('@');
        } else if (state == 2) {
            ret.append("@{");
            ret.append(macroName.toString());
        }
        return ret.toString();
    }

    public void addText(String text) {
        this.text = text;
    }

    private UnknownElement copy(UnknownElement ue) {
        UnknownElement ret = new UnknownElement(ue.getTag());
        ret.setNamespace(ue.getNamespace());
        ret.setProject(getProject());
        ret.setQName(ue.getQName());
        ret.setTaskType(ue.getTaskType());
        ret.setTaskName(ue.getTaskName());
        ret.setLocation(ue.getLocation());
        if (getOwningTarget() == null) {
            Target t = new Target();
            t.setProject(getProject());
            ret.setOwningTarget(t);
        } else {
            ret.setOwningTarget(getOwningTarget());
        }
        RuntimeConfigurable rc = new RuntimeConfigurable(ret, ue.getTaskName());
        rc.setPolyType(ue.getWrapper().getPolyType());
        Map map = ue.getWrapper().getAttributeMap();
        for (Map.Entry entry : map.entrySet()) {
            rc.setAttribute((String) entry.getKey(), macroSubs((String) entry.getValue(), this.localProperties));
        }
        rc.addText(macroSubs(ue.getWrapper().getText().toString(), this.localProperties));
        Enumeration e = ue.getWrapper().getChildren();
        while (e.hasMoreElements()) {
            RuntimeConfigurable r = (RuntimeConfigurable) e.nextElement();
            UnknownElement unknownElement = (UnknownElement) r.getProxy();
            String tag = unknownElement.getTaskType();
            if (tag != null) {
                tag = tag.toLowerCase(Locale.US);
            }
            MacroDef.TemplateElement templateElement = (MacroDef.TemplateElement) getNsElements().get(tag);
            if (templateElement == null) {
                UnknownElement child = copy(unknownElement);
                rc.addChild(child.getWrapper());
                ret.addChild(child);
            } else if (templateElement.isImplicit()) {
                if (this.unknownElements.size() == 0 && !templateElement.isOptional()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Missing nested elements for implicit element ");
                    stringBuffer.append(templateElement.getName());
                    throw new BuildException(stringBuffer.toString());
                }
                for (UnknownElement child2 : this.unknownElements) {
                    rc.addChild(child2.getWrapper());
                    ret.addChild(child2);
                }
            } else {
                List<UnknownElement> list = (List) this.presentElements.get(tag);
                if (list == null) {
                    if (!templateElement.isOptional()) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Required nested element ");
                        stringBuffer2.append(templateElement.getName());
                        stringBuffer2.append(" missing");
                        throw new BuildException(stringBuffer2.toString());
                    }
                } else {
                    for (UnknownElement child3 : list) {
                        rc.addChild(child3.getWrapper());
                        ret.addChild(child3);
                    }
                }
            }
        }
        return ret;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        this.presentElements = new HashMap();
        getNsElements();
        processTasks();
        this.localProperties = new Hashtable();
        Set copyKeys = new HashSet(this.map.keySet());
        for (MacroDef.Attribute attribute : this.macroDef.getAttributes()) {
            String value = (String) this.map.get(attribute.getName());
            if (value == null && "description".equals(attribute.getName())) {
                value = getDescription();
            }
            if (value == null) {
                value = macroSubs(attribute.getDefault(), this.localProperties);
            }
            if (value == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("required attribute ");
                stringBuffer.append(attribute.getName());
                stringBuffer.append(" not set");
                throw new BuildException(stringBuffer.toString());
            }
            this.localProperties.put(attribute.getName(), value);
            copyKeys.remove(attribute.getName());
        }
        if (copyKeys.contains("id")) {
            copyKeys.remove("id");
        }
        if (this.macroDef.getText() != null) {
            if (this.text == null) {
                if (!this.macroDef.getText().getOptional()) {
                    throw new BuildException("required text missing");
                }
                this.text = "";
            }
            if (this.macroDef.getText().getTrim()) {
                this.text = this.text.trim();
            }
            this.localProperties.put(this.macroDef.getText().getName(), this.text);
        } else {
            String str = this.text;
            if (str != null && !str.trim().equals("")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("The \"");
                stringBuffer2.append(getTaskName());
                stringBuffer2.append("\" macro does not support");
                stringBuffer2.append(" nested text data.");
                throw new BuildException(stringBuffer2.toString());
            }
        }
        if (copyKeys.size() != 0) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Unknown attribute");
            stringBuffer3.append(copyKeys.size() > 1 ? "s " : " ");
            stringBuffer3.append(copyKeys);
            throw new BuildException(stringBuffer3.toString());
        }
        UnknownElement c = copy(this.macroDef.getNestedTask());
        c.init();
        try {
            c.perform();
            this.presentElements = null;
            this.localProperties = null;
        } catch (BuildException ex) {
            throw ProjectHelper.addLocationToBuildException(ex, getLocation());
        }
    }
}
