package org.apache.tools.ant.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

/* JADX INFO: loaded from: classes.dex */
public class AntXMLContext {
    private File buildFile;
    private File buildFileParent;
    private String currentProjectName;
    private Locator locator;
    private Project project;
    private Vector targetVector = new Vector();
    private Target implicitTarget = new Target();
    private Target currentTarget = null;
    private Vector wStack = new Vector();
    private boolean ignoreProjectTag = false;
    private Map prefixMapping = new HashMap();
    private Map currentTargets = null;

    public AntXMLContext(Project project) {
        this.project = project;
        this.implicitTarget.setProject(project);
        this.implicitTarget.setName("");
        this.targetVector.addElement(this.implicitTarget);
    }

    public void setBuildFile(File buildFile) {
        this.buildFile = buildFile;
        this.buildFileParent = new File(buildFile.getParent());
        this.implicitTarget.setLocation(new Location(buildFile.getAbsolutePath()));
    }

    public File getBuildFile() {
        return this.buildFile;
    }

    public File getBuildFileParent() {
        return this.buildFileParent;
    }

    public Project getProject() {
        return this.project;
    }

    public String getCurrentProjectName() {
        return this.currentProjectName;
    }

    public void setCurrentProjectName(String name) {
        this.currentProjectName = name;
    }

    public RuntimeConfigurable currentWrapper() {
        if (this.wStack.size() < 1) {
            return null;
        }
        Vector vector = this.wStack;
        return (RuntimeConfigurable) vector.elementAt(vector.size() - 1);
    }

    public RuntimeConfigurable parentWrapper() {
        if (this.wStack.size() < 2) {
            return null;
        }
        Vector vector = this.wStack;
        return (RuntimeConfigurable) vector.elementAt(vector.size() - 2);
    }

    public void pushWrapper(RuntimeConfigurable wrapper) {
        this.wStack.addElement(wrapper);
    }

    public void popWrapper() {
        if (this.wStack.size() > 0) {
            this.wStack.removeElementAt(r0.size() - 1);
        }
    }

    public Vector getWrapperStack() {
        return this.wStack;
    }

    public void addTarget(Target target) {
        this.targetVector.addElement(target);
        this.currentTarget = target;
    }

    public Target getCurrentTarget() {
        return this.currentTarget;
    }

    public Target getImplicitTarget() {
        return this.implicitTarget;
    }

    public void setCurrentTarget(Target target) {
        this.currentTarget = target;
    }

    public void setImplicitTarget(Target target) {
        this.implicitTarget = target;
    }

    public Vector getTargets() {
        return this.targetVector;
    }

    public void configureId(Object element, Attributes attr) {
        String id = attr.getValue("id");
        if (id != null) {
            this.project.addReference(id, element);
        }
    }

    public Locator getLocator() {
        return this.locator;
    }

    public void setLocator(Locator locator) {
        this.locator = locator;
    }

    public boolean isIgnoringProjectTag() {
        return this.ignoreProjectTag;
    }

    public void setIgnoreProjectTag(boolean flag) {
        this.ignoreProjectTag = flag;
    }

    public void startPrefixMapping(String prefix, String uri) {
        List list = (List) this.prefixMapping.get(prefix);
        if (list == null) {
            list = new ArrayList();
            this.prefixMapping.put(prefix, list);
        }
        list.add(uri);
    }

    public void endPrefixMapping(String prefix) {
        List list = (List) this.prefixMapping.get(prefix);
        if (list == null || list.size() == 0) {
            return;
        }
        list.remove(list.size() - 1);
    }

    public String getPrefixMapping(String prefix) {
        List list = (List) this.prefixMapping.get(prefix);
        if (list == null || list.size() == 0) {
            return null;
        }
        return (String) list.get(list.size() - 1);
    }

    public Map getCurrentTargets() {
        return this.currentTargets;
    }

    public void setCurrentTargets(Map currentTargets) {
        this.currentTargets = currentTargets;
    }
}
