package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.types.PropertySet;

/* JADX INFO: loaded from: classes.dex */
public class CallTarget extends Task {
    private Ant callee;
    private boolean inheritAll = true;
    private boolean inheritRefs = false;
    private boolean targetSet = false;

    public void setInheritAll(boolean inherit) {
        this.inheritAll = inherit;
    }

    public void setInheritRefs(boolean inheritRefs) {
        this.inheritRefs = inheritRefs;
    }

    @Override // org.apache.tools.ant.Task
    public void init() {
        Ant ant = (Ant) getProject().createTask("ant");
        this.callee = ant;
        ant.setOwningTarget(getOwningTarget());
        this.callee.setTaskName(getTaskName());
        this.callee.setLocation(getLocation());
        this.callee.init();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.callee == null) {
            init();
        }
        if (!this.targetSet) {
            throw new BuildException("Attribute target or at least one nested target is required.", getLocation());
        }
        this.callee.setAntfile(getProject().getProperty("ant.file"));
        this.callee.setInheritAll(this.inheritAll);
        this.callee.setInheritRefs(this.inheritRefs);
        this.callee.execute();
    }

    public Property createParam() {
        if (this.callee == null) {
            init();
        }
        return this.callee.createProperty();
    }

    public void addReference(Ant.Reference r) {
        if (this.callee == null) {
            init();
        }
        this.callee.addReference(r);
    }

    public void addPropertyset(PropertySet ps) {
        if (this.callee == null) {
            init();
        }
        this.callee.addPropertyset(ps);
    }

    public void setTarget(String target) {
        if (this.callee == null) {
            init();
        }
        this.callee.setTarget(target);
        this.targetSet = true;
    }

    public void addConfiguredTarget(Ant.TargetElement t) {
        if (this.callee == null) {
            init();
        }
        this.callee.addConfiguredTarget(t);
        this.targetSet = true;
    }

    @Override // org.apache.tools.ant.Task
    public void handleOutput(String output) {
        Ant ant = this.callee;
        if (ant != null) {
            ant.handleOutput(output);
        } else {
            super.handleOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] buffer, int offset, int length) throws IOException {
        Ant ant = this.callee;
        if (ant != null) {
            return ant.handleInput(buffer, offset, length);
        }
        return super.handleInput(buffer, offset, length);
    }

    @Override // org.apache.tools.ant.Task
    public void handleFlush(String output) {
        Ant ant = this.callee;
        if (ant != null) {
            ant.handleFlush(output);
        } else {
            super.handleFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorOutput(String output) {
        Ant ant = this.callee;
        if (ant != null) {
            ant.handleErrorOutput(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorFlush(String output) {
        Ant ant = this.callee;
        if (ant != null) {
            ant.handleErrorFlush(output);
        } else {
            super.handleErrorFlush(output);
        }
    }
}
