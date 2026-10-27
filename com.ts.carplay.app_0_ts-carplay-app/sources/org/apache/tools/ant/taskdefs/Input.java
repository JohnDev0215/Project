package org.apache.tools.ant.taskdefs;

import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.input.InputRequest;
import org.apache.tools.ant.input.MultipleChoiceInputRequest;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class Input extends Task {
    private String validargs = null;
    private String message = "";
    private String addproperty = null;
    private String defaultvalue = null;

    public void setValidargs(String validargs) {
        this.validargs = validargs;
    }

    public void setAddproperty(String addproperty) {
        this.addproperty = addproperty;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public void addText(String msg) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.message);
        stringBuffer.append(getProject().replaceProperties(msg));
        this.message = stringBuffer.toString();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        InputRequest request;
        if (this.addproperty != null && getProject().getProperty(this.addproperty) != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("skipping ");
            stringBuffer.append(getTaskName());
            stringBuffer.append(" as property ");
            stringBuffer.append(this.addproperty);
            stringBuffer.append(" has already been set.");
            log(stringBuffer.toString());
            return;
        }
        String str = this.validargs;
        if (str != null) {
            Vector accept = StringUtils.split(str, 44);
            request = new MultipleChoiceInputRequest(this.message, accept);
        } else {
            request = new InputRequest(this.message);
        }
        getProject().getInputHandler().handleInput(request);
        String value = request.getInput();
        if ((value == null || value.trim().length() == 0) && this.defaultvalue != null) {
            value = this.defaultvalue;
        }
        if (this.addproperty != null && value != null) {
            getProject().setNewProperty(this.addproperty, value);
        }
    }
}
