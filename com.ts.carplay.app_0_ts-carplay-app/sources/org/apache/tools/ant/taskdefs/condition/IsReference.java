package org.apache.tools.ant.taskdefs.condition;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class IsReference extends ProjectComponent implements Condition {
    private Reference ref;
    private String type;

    public void setRefid(Reference r) {
        this.ref = r;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.ref == null) {
            throw new BuildException("No reference specified for isreference condition");
        }
        Object o = getProject().getReference(this.ref.getRefId());
        if (o == null) {
            return false;
        }
        if (this.type == null) {
            return true;
        }
        Class typeClass = (Class) getProject().getDataTypeDefinitions().get(this.type);
        if (typeClass == null) {
            typeClass = (Class) getProject().getTaskDefinitions().get(this.type);
        }
        if (typeClass == null) {
            return false;
        }
        return typeClass.isAssignableFrom(o.getClass());
    }
}
