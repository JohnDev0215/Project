package org.apache.tools.ant.types;

import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataType extends ProjectComponent {
    protected boolean checked = true;
    protected String description;
    protected Reference ref;

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isReference() {
        return this.ref != null;
    }

    public void setRefid(Reference ref) {
        this.ref = ref;
        this.checked = false;
    }

    protected void dieOnCircularReference(Stack stack, Project project) throws BuildException {
        if (this.checked || !isReference()) {
            return;
        }
        Object o = this.ref.getReferencedObject(project);
        if (o instanceof DataType) {
            if (stack.contains(o)) {
                throw circularReference();
            }
            stack.push(o);
            ((DataType) o).dieOnCircularReference(stack, project);
            stack.pop();
        }
        this.checked = true;
    }

    protected Object getCheckedRef(Class requiredClass, String dataTypeName) {
        if (!this.checked) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, getProject());
        }
        Object o = this.ref.getReferencedObject(getProject());
        if (!requiredClass.isAssignableFrom(o.getClass())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.ref.getRefId());
            stringBuffer.append(" doesn't denote a ");
            stringBuffer.append(dataTypeName);
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return o;
    }

    protected BuildException tooManyAttributes() {
        return new BuildException("You must not specify more than one attribute when using refid");
    }

    protected BuildException noChildrenAllowed() {
        return new BuildException("You must not specify nested elements when using refid");
    }

    protected BuildException circularReference() {
        return new BuildException("This data type contains a circular reference.");
    }

    protected boolean isChecked() {
        return this.checked;
    }

    protected void setChecked(boolean checked) {
        this.checked = checked;
    }

    protected Reference getRefid() {
        return this.ref;
    }

    protected void checkAttributesAllowed() {
        if (isReference()) {
            throw tooManyAttributes();
        }
    }

    protected void checkChildrenAllowed() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
    }
}
