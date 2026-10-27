package org.apache.tools.ant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.tools.ant.util.CollectionUtils;

/* JADX INFO: loaded from: classes.dex */
public class Target implements TaskContainer {
    private String name;
    private Project project;
    private String ifCondition = "";
    private String unlessCondition = "";
    private List dependencies = null;
    private List children = new ArrayList();
    private Location location = Location.UNKNOWN_LOCATION;
    private String description = null;

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setDepends(String depS) {
        if (depS.length() > 0) {
            StringTokenizer tok = new StringTokenizer(depS, ",", true);
            while (tok.hasMoreTokens()) {
                String token = tok.nextToken().trim();
                if (token.equals("") || token.equals(",")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Syntax Error: Depend attribute for target \"");
                    stringBuffer.append(getName());
                    stringBuffer.append("\" has an empty string for dependency.");
                    throw new BuildException(stringBuffer.toString());
                }
                addDependency(token);
                if (tok.hasMoreTokens()) {
                    String token2 = tok.nextToken();
                    if (!tok.hasMoreTokens() || !token2.equals(",")) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Syntax Error: Depend attribute for target \"");
                        stringBuffer2.append(getName());
                        stringBuffer2.append("\" ends with a , character");
                        throw new BuildException(stringBuffer2.toString());
                    }
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task task) {
        this.children.add(task);
    }

    public void addDataType(RuntimeConfigurable r) {
        this.children.add(r);
    }

    public Task[] getTasks() {
        List tasks = new ArrayList(this.children.size());
        for (Object o : this.children) {
            if (o instanceof Task) {
                tasks.add(o);
            }
        }
        return (Task[]) tasks.toArray(new Task[tasks.size()]);
    }

    public void addDependency(String dependency) {
        if (this.dependencies == null) {
            this.dependencies = new ArrayList(2);
        }
        this.dependencies.add(dependency);
    }

    public Enumeration getDependencies() {
        List list = this.dependencies;
        if (list != null) {
            return Collections.enumeration(list);
        }
        return new CollectionUtils.EmptyEnumeration();
    }

    public boolean dependsOn(String other) {
        Project p = getProject();
        Hashtable t = p == null ? null : p.getTargets();
        return p != null && p.topoSort(getName(), t, false).contains(t.get(other));
    }

    public void setIf(String property) {
        this.ifCondition = property == null ? "" : property;
    }

    public String getIf() {
        if ("".equals(this.ifCondition)) {
            return null;
        }
        return this.ifCondition;
    }

    public void setUnless(String property) {
        this.unlessCondition = property == null ? "" : property;
    }

    public String getUnless() {
        if ("".equals(this.unlessCondition)) {
            return null;
        }
        return this.unlessCondition;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.name;
    }

    public void execute() throws BuildException {
        if (testIfCondition() && testUnlessCondition()) {
            for (int taskPosition = 0; taskPosition < this.children.size(); taskPosition++) {
                Object o = this.children.get(taskPosition);
                if (o instanceof Task) {
                    Task task = (Task) o;
                    task.perform();
                } else {
                    RuntimeConfigurable r = (RuntimeConfigurable) o;
                    r.maybeConfigure(this.project);
                }
            }
            return;
        }
        if (!testIfCondition()) {
            Project project = this.project;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Skipped because property '");
            stringBuffer.append(this.project.replaceProperties(this.ifCondition));
            stringBuffer.append("' not set.");
            project.log(this, stringBuffer.toString(), 3);
            return;
        }
        Project project2 = this.project;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Skipped because property '");
        stringBuffer2.append(this.project.replaceProperties(this.unlessCondition));
        stringBuffer2.append("' set.");
        project2.log(this, stringBuffer2.toString(), 3);
    }

    public final void performTasks() throws Throwable {
        RuntimeException thrown = null;
        this.project.fireTargetStarted(this);
        try {
            execute();
            this.project.fireTargetFinished(this, null);
        } catch (RuntimeException exc) {
            thrown = exc;
            try {
                throw exc;
            } catch (Throwable th) {
                th = th;
                this.project.fireTargetFinished(this, thrown);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            this.project.fireTargetFinished(this, thrown);
            throw th;
        }
    }

    void replaceChild(Task el, RuntimeConfigurable o) {
        while (true) {
            int iIndexOf = this.children.indexOf(el);
            if (iIndexOf >= 0) {
                this.children.set(iIndexOf, o);
            } else {
                return;
            }
        }
    }

    void replaceChild(Task el, Task o) {
        while (true) {
            int iIndexOf = this.children.indexOf(el);
            if (iIndexOf >= 0) {
                this.children.set(iIndexOf, o);
            } else {
                return;
            }
        }
    }

    private boolean testIfCondition() {
        if ("".equals(this.ifCondition)) {
            return true;
        }
        String test = this.project.replaceProperties(this.ifCondition);
        return this.project.getProperty(test) != null;
    }

    private boolean testUnlessCondition() {
        if ("".equals(this.unlessCondition)) {
            return true;
        }
        String test = this.project.replaceProperties(this.unlessCondition);
        return this.project.getProperty(test) == null;
    }
}
