package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExitStatusException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.ConditionBase;

/* JADX INFO: loaded from: classes.dex */
public class Exit extends Task {
    private String ifCondition;
    private String message;
    private NestedCondition nestedCondition;
    private Integer status;
    private String unlessCondition;

    /* JADX INFO: renamed from: org.apache.tools.ant.taskdefs.Exit$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private class NestedCondition extends ConditionBase implements Condition {
        private NestedCondition() {
        }

        /* synthetic */ NestedCondition(Exit x0, AnonymousClass1 x1) {
            this();
        }

        @Override // org.apache.tools.ant.taskdefs.condition.Condition
        public boolean eval() {
            if (countConditions() != 1) {
                throw new BuildException("A single nested condition is required.");
            }
            return ((Condition) getConditions().nextElement()).eval();
        }
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public void setIf(String c) {
        this.ifCondition = c;
    }

    public void setUnless(String c) {
        this.unlessCondition = c;
    }

    public void setStatus(int i) {
        this.status = new Integer(i);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String text;
        boolean fail = nestedConditionPresent() ? testNestedCondition() : testIfCondition() && testUnlessCondition();
        if (fail) {
            String text2 = null;
            String str = this.message;
            if (str != null && str.trim().length() > 0) {
                text2 = this.message.trim();
            } else {
                String str2 = this.ifCondition;
                if (str2 != null && str2.length() > 0 && getProject().getProperty(this.ifCondition) != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("if=");
                    stringBuffer.append(this.ifCondition);
                    text2 = stringBuffer.toString();
                }
                String str3 = this.unlessCondition;
                if (str3 != null && str3.length() > 0 && getProject().getProperty(this.unlessCondition) == null) {
                    if (text2 == null) {
                        text = "";
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append(text2);
                        stringBuffer2.append(" and ");
                        text = stringBuffer2.toString();
                    }
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(text);
                    stringBuffer3.append("unless=");
                    stringBuffer3.append(this.unlessCondition);
                    text2 = stringBuffer3.toString();
                }
                if (nestedConditionPresent()) {
                    text2 = "condition satisfied";
                } else if (text2 == null) {
                    text2 = "No message";
                }
            }
            if (this.status != null) {
                throw new ExitStatusException(text2, this.status.intValue());
            }
        }
    }

    public void addText(String msg) {
        if (this.message == null) {
            this.message = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.message);
        stringBuffer.append(getProject().replaceProperties(msg));
        this.message = stringBuffer.toString();
    }

    public ConditionBase createCondition() {
        if (this.nestedCondition != null) {
            throw new BuildException("Only one nested condition is allowed.");
        }
        NestedCondition nestedCondition = new NestedCondition(this, null);
        this.nestedCondition = nestedCondition;
        return nestedCondition;
    }

    private boolean testIfCondition() {
        String str = this.ifCondition;
        return str == null || "".equals(str) || getProject().getProperty(this.ifCondition) != null;
    }

    private boolean testUnlessCondition() {
        String str = this.unlessCondition;
        return str == null || "".equals(str) || getProject().getProperty(this.unlessCondition) == null;
    }

    private boolean testNestedCondition() {
        boolean result = nestedConditionPresent();
        if ((!result || this.ifCondition == null) && this.unlessCondition == null) {
            return result && this.nestedCondition.eval();
        }
        throw new BuildException("Nested conditions not permitted in conjunction with if/unless attributes");
    }

    private boolean nestedConditionPresent() {
        return this.nestedCondition != null;
    }
}
