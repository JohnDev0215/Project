package org.apache.tools.ant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.taskdefs.PreSetDef;

/* JADX INFO: loaded from: classes.dex */
public class UnknownElement extends Task {
    private String elementName;
    private String namespace;
    private String qname;
    private Object realThing;
    private List children = null;
    private boolean presetDefed = false;

    public UnknownElement(String elementName) {
        this.elementName = elementName;
    }

    public List getChildren() {
        return this.children;
    }

    public String getTag() {
        return this.elementName;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        if (namespace.equals(ProjectHelper.ANT_CURRENT_URI)) {
            ComponentHelper helper = ComponentHelper.getComponentHelper(getProject());
            namespace = helper.getCurrentAntlibUri();
        }
        this.namespace = namespace;
    }

    public String getQName() {
        return this.qname;
    }

    public void setQName(String qname) {
        this.qname = qname;
    }

    @Override // org.apache.tools.ant.Task
    public RuntimeConfigurable getWrapper() {
        return super.getWrapper();
    }

    @Override // org.apache.tools.ant.Task
    public void maybeConfigure() throws BuildException {
        configure(makeObject(this, getWrapper()));
    }

    public void configure(Object realObject) {
        this.realThing = realObject;
        getWrapper().setProxy(this.realThing);
        Task task = null;
        Object obj = this.realThing;
        if (obj instanceof Task) {
            task = (Task) obj;
            task.setRuntimeConfigurableWrapper(getWrapper());
            getOwningTarget().replaceChild(this, (Task) this.realThing);
        }
        handleChildren(this.realThing, getWrapper());
        if (task != null) {
            task.maybeConfigure();
        } else {
            getWrapper().maybeConfigure(getProject());
        }
    }

    @Override // org.apache.tools.ant.Task
    protected void handleOutput(String output) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleOutput(output);
        } else {
            super.handleOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    protected int handleInput(byte[] buffer, int offset, int length) throws IOException {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            return ((Task) obj).handleInput(buffer, offset, length);
        }
        return super.handleInput(buffer, offset, length);
    }

    @Override // org.apache.tools.ant.Task
    protected void handleFlush(String output) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleFlush(output);
        } else {
            super.handleFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    protected void handleErrorOutput(String output) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleErrorOutput(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    protected void handleErrorFlush(String output) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleErrorOutput(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        Object obj = this.realThing;
        if (obj == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not create task of type: ");
            stringBuffer.append(this.elementName);
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        if (obj instanceof Task) {
            ((Task) obj).execute();
        }
        this.realThing = null;
    }

    public void addChild(UnknownElement child) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(child);
    }

    protected void handleChildren(Object parent, RuntimeConfigurable parentWrapper) throws BuildException {
        if (parent instanceof TypeAdapter) {
            parent = ((TypeAdapter) parent).getProxy();
        }
        String parentUri = getNamespace();
        IntrospectionHelper ih = IntrospectionHelper.getHelper(parent.getClass());
        List<UnknownElement> list = this.children;
        if (list != null) {
            int i = 0;
            for (UnknownElement child : list) {
                RuntimeConfigurable childWrapper = parentWrapper.getChild(i);
                if (!handleChild(parentUri, ih, parent, child, childWrapper)) {
                    if (!(parent instanceof TaskContainer)) {
                        ih.throwNotSupported(getProject(), parent, child.getTag());
                    } else {
                        TaskContainer container = (TaskContainer) parent;
                        container.addTask(child);
                    }
                }
                i++;
            }
        }
    }

    protected String getComponentName() {
        return ProjectHelper.genComponentName(getNamespace(), getTag());
    }

    public void applyPreSet(UnknownElement u) {
        if (this.presetDefed) {
            return;
        }
        getWrapper().applyPreSet(u.getWrapper());
        if (u.children != null) {
            List newChildren = new ArrayList();
            newChildren.addAll(u.children);
            List list = this.children;
            if (list != null) {
                newChildren.addAll(list);
            }
            this.children = newChildren;
        }
        this.presetDefed = true;
    }

    protected Object makeObject(UnknownElement ue, RuntimeConfigurable w) {
        ComponentHelper helper = ComponentHelper.getComponentHelper(getProject());
        String name = ue.getComponentName();
        Object o = helper.createComponent(ue, ue.getNamespace(), name);
        if (o == null) {
            throw getNotFoundException("task or type", name);
        }
        if (o instanceof PreSetDef.PreSetDefinition) {
            PreSetDef.PreSetDefinition def = (PreSetDef.PreSetDefinition) o;
            o = def.createObject(ue.getProject());
            if (o == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("preset ");
                stringBuffer.append(name);
                throw getNotFoundException(stringBuffer.toString(), def.getPreSets().getComponentName());
            }
            ue.applyPreSet(def.getPreSets());
            if (o instanceof Task) {
                Task task = (Task) o;
                task.setTaskType(ue.getTaskType());
                task.setTaskName(ue.getTaskName());
            }
        }
        if (o instanceof Task) {
            Task task2 = (Task) o;
            task2.setOwningTarget(getOwningTarget());
            task2.init();
        }
        return o;
    }

    protected Task makeTask(UnknownElement ue, RuntimeConfigurable w) {
        Task task = getProject().createTask(ue.getTag());
        if (task != null) {
            task.setLocation(getLocation());
            task.setOwningTarget(getOwningTarget());
            task.init();
        }
        return task;
    }

    protected BuildException getNotFoundException(String what, String elementName) {
        String lSep = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Could not create ");
        stringBuffer.append(what);
        stringBuffer.append(" of type: ");
        stringBuffer.append(elementName);
        stringBuffer.append(".");
        stringBuffer.append(lSep);
        stringBuffer.append(lSep);
        stringBuffer.append("Ant could not find the task or a class this ");
        stringBuffer.append("task relies upon.");
        stringBuffer.append(lSep);
        stringBuffer.append(lSep);
        stringBuffer.append("This is common and has a number of causes; the usual ");
        stringBuffer.append(lSep);
        stringBuffer.append("solutions are to read the manual pages then download and");
        stringBuffer.append(lSep);
        stringBuffer.append("install needed JAR files, or fix the build file: ");
        stringBuffer.append(lSep);
        stringBuffer.append(" - You have misspelt '");
        stringBuffer.append(elementName);
        stringBuffer.append("'.");
        stringBuffer.append(lSep);
        stringBuffer.append("   Fix: check your spelling.");
        stringBuffer.append(lSep);
        stringBuffer.append(" - The task needs an external JAR file to execute");
        stringBuffer.append(lSep);
        stringBuffer.append("     and this is not found at the right place in the classpath.");
        stringBuffer.append(lSep);
        stringBuffer.append("   Fix: check the documentation for dependencies.");
        stringBuffer.append(lSep);
        stringBuffer.append("   Fix: declare the task.");
        stringBuffer.append(lSep);
        stringBuffer.append(" - The task is an Ant optional task and the JAR file and/or libraries");
        stringBuffer.append(lSep);
        stringBuffer.append("     implementing the functionality were not found at the time you");
        stringBuffer.append(lSep);
        stringBuffer.append("     yourself built your installation of Ant from the Ant sources.");
        stringBuffer.append(lSep);
        stringBuffer.append("   Fix: Look in the ANT_HOME/lib for the 'ant-' JAR corresponding to the");
        stringBuffer.append(lSep);
        stringBuffer.append("     task and make sure it contains more than merely a META-INF/MANIFEST.MF.");
        stringBuffer.append(lSep);
        stringBuffer.append("     If all it contains is the manifest, then rebuild Ant with the needed");
        stringBuffer.append(lSep);
        stringBuffer.append("     libraries present in ${ant.home}/lib/optional/ , or alternatively,");
        stringBuffer.append(lSep);
        stringBuffer.append("     download a pre-built release version from apache.org");
        stringBuffer.append(lSep);
        stringBuffer.append(" - The build file was written for a later version of Ant");
        stringBuffer.append(lSep);
        stringBuffer.append("   Fix: upgrade to at least the latest release version of Ant");
        stringBuffer.append(lSep);
        stringBuffer.append(" - The task is not an Ant core or optional task ");
        stringBuffer.append(lSep);
        stringBuffer.append("     and needs to be declared using <taskdef>.");
        stringBuffer.append(lSep);
        stringBuffer.append(" - You are attempting to use a task defined using ");
        stringBuffer.append(lSep);
        stringBuffer.append("    <presetdef> or <macrodef> but have spelt wrong or not ");
        stringBuffer.append(lSep);
        stringBuffer.append("   defined it at the point of use");
        stringBuffer.append(lSep);
        stringBuffer.append(lSep);
        stringBuffer.append("Remember that for JAR files to be visible to Ant tasks implemented");
        stringBuffer.append(lSep);
        stringBuffer.append("in ANT_HOME/lib, the files must be in the same directory or on the");
        stringBuffer.append(lSep);
        stringBuffer.append("classpath");
        stringBuffer.append(lSep);
        stringBuffer.append(lSep);
        stringBuffer.append("Please neither file bug reports on this problem, nor email the");
        stringBuffer.append(lSep);
        stringBuffer.append("Ant mailing lists, until all of these causes have been explored,");
        stringBuffer.append(lSep);
        stringBuffer.append("as this is not an Ant bug.");
        String msg = stringBuffer.toString();
        return new BuildException(msg, getLocation());
    }

    @Override // org.apache.tools.ant.Task
    public String getTaskName() {
        Object obj = this.realThing;
        return (obj == null || !(obj instanceof Task)) ? super.getTaskName() : ((Task) obj).getTaskName();
    }

    public Task getTask() {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            return (Task) obj;
        }
        return null;
    }

    public Object getRealThing() {
        return this.realThing;
    }

    private boolean handleChild(String parentUri, IntrospectionHelper ih, Object parent, UnknownElement child, RuntimeConfigurable childWrapper) {
        String childName = ProjectHelper.genComponentName(child.getNamespace(), child.getTag());
        if (ih.supportsNestedElement(parentUri, childName)) {
            IntrospectionHelper.Creator creator = ih.getElementCreator(getProject(), parentUri, parent, childName, child);
            creator.setPolyType(childWrapper.getPolyType());
            Object realChild = creator.create();
            if (realChild instanceof PreSetDef.PreSetDefinition) {
                PreSetDef.PreSetDefinition def = (PreSetDef.PreSetDefinition) realChild;
                realChild = creator.getRealObject();
                child.applyPreSet(def.getPreSets());
            }
            childWrapper.setCreator(creator);
            childWrapper.setProxy(realChild);
            if (realChild instanceof Task) {
                Task childTask = (Task) realChild;
                childTask.setRuntimeConfigurableWrapper(childWrapper);
                childTask.setTaskName(childName);
                childTask.setTaskType(childName);
                childTask.setLocation(child.getLocation());
            }
            child.handleChildren(realChild, childWrapper);
            return true;
        }
        return false;
    }

    public boolean similar(Object obj) {
        if (obj == null || !getClass().getName().equals(obj.getClass().getName())) {
            return false;
        }
        UnknownElement other = (UnknownElement) obj;
        if (!equalsString(this.elementName, other.elementName) || !this.namespace.equals(other.namespace) || !this.qname.equals(other.qname) || !getWrapper().getAttributeMap().equals(other.getWrapper().getAttributeMap()) || !getWrapper().getText().toString().equals(other.getWrapper().getText().toString())) {
            return false;
        }
        List list = this.children;
        if (list == null || list.size() == 0) {
            List list2 = other.children;
            return list2 == null || list2.size() == 0;
        }
        if (other.children == null || this.children.size() != other.children.size()) {
            return false;
        }
        for (int i = 0; i < this.children.size(); i++) {
            UnknownElement child = (UnknownElement) this.children.get(i);
            if (!child.similar(other.children.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean equalsString(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }
}
