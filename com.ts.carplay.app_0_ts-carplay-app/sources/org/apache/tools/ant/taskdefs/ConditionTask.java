package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.ConditionBase;

/* JADX INFO: loaded from: classes.dex */
public class ConditionTask extends ConditionBase {
    private String property = null;
    private String value = "true";
    private String alternative = null;

    public void setProperty(String p) {
        this.property = p;
    }

    public void setValue(String v) {
        this.value = v;
    }

    public void setElse(String e) {
        this.alternative = e;
    }

    public void execute() throws BuildException {
        if (countConditions() > 1) {
            throw new BuildException("You must not nest more than one condition into <condition>");
        }
        if (countConditions() < 1) {
            throw new BuildException("You must nest a condition into <condition>");
        }
        if (this.property == null) {
            throw new BuildException("The property attribute is required.");
        }
        Condition c = (Condition) getConditions().nextElement();
        if (c.eval()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Condition true; setting ");
            stringBuffer.append(this.property);
            stringBuffer.append(" to ");
            stringBuffer.append(this.value);
            log(stringBuffer.toString(), 4);
            getProject().setNewProperty(this.property, this.value);
            return;
        }
        if (this.alternative != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Condition false; setting ");
            stringBuffer2.append(this.property);
            stringBuffer2.append(" to ");
            stringBuffer2.append(this.alternative);
            log(stringBuffer2.toString(), 4);
            getProject().setNewProperty(this.property, this.alternative);
            return;
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Condition false; not setting ");
        stringBuffer3.append(this.property);
        log(stringBuffer3.toString(), 4);
    }
}
