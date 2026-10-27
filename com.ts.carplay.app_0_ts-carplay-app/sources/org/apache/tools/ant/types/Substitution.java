package org.apache.tools.ant.types;

import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class Substitution extends DataType {
    public static final String DATA_TYPE_NAME = "substitition";
    private String expression = null;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression(Project p) {
        if (isReference()) {
            return getRef(p).getExpression(p);
        }
        return this.expression;
    }

    public Substitution getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (!(o instanceof Substitution)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a substitution");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return (Substitution) o;
    }
}
