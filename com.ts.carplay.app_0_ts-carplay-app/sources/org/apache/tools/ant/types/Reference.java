package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class Reference {
    private Project project;
    private String refid;

    public Reference() {
    }

    public Reference(String id) {
        setRefId(id);
    }

    public Reference(Project p, String id) {
        setRefId(id);
        setProject(p);
    }

    public void setRefId(String id) {
        this.refid = id;
    }

    public String getRefId() {
        return this.refid;
    }

    public void setProject(Project p) {
        this.project = p;
    }

    public Project getProject() {
        return this.project;
    }

    public Object getReferencedObject(Project fallback) throws BuildException {
        String str = this.refid;
        if (str == null) {
            throw new BuildException("No reference specified");
        }
        Project project = this.project;
        Object o = project == null ? fallback.getReference(str) : project.getReference(str);
        if (o == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Reference ");
            stringBuffer.append(this.refid);
            stringBuffer.append(" not found.");
            throw new BuildException(stringBuffer.toString());
        }
        return o;
    }

    public Object getReferencedObject() throws BuildException {
        Project project = this.project;
        if (project == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No project set on reference to ");
            stringBuffer.append(this.refid);
            throw new BuildException(stringBuffer.toString());
        }
        return getReferencedObject(project);
    }
}
