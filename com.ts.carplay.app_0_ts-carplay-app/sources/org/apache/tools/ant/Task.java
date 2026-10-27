package org.apache.tools.ant;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public abstract class Task extends ProjectComponent {
    protected String description;
    private boolean invalid;
    protected Location location = Location.UNKNOWN_LOCATION;
    private UnknownElement replacement;
    protected Target target;
    protected String taskName;
    protected String taskType;
    protected RuntimeConfigurable wrapper;

    public void setOwningTarget(Target target) {
        this.target = target;
    }

    public Target getOwningTarget() {
        return this.target;
    }

    public void setTaskName(String name) {
        this.taskName = name;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskType(String type) {
        this.taskType = type;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public void init() throws BuildException {
    }

    public void execute() throws BuildException {
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public RuntimeConfigurable getRuntimeConfigurableWrapper() {
        if (this.wrapper == null) {
            this.wrapper = new RuntimeConfigurable(this, getTaskName());
        }
        return this.wrapper;
    }

    public void setRuntimeConfigurableWrapper(RuntimeConfigurable wrapper) {
        this.wrapper = wrapper;
    }

    public void maybeConfigure() throws BuildException {
        if (!this.invalid) {
            RuntimeConfigurable runtimeConfigurable = this.wrapper;
            if (runtimeConfigurable != null) {
                runtimeConfigurable.maybeConfigure(getProject());
                return;
            }
            return;
        }
        getReplacement();
    }

    public void reconfigure() {
        RuntimeConfigurable runtimeConfigurable = this.wrapper;
        if (runtimeConfigurable != null) {
            runtimeConfigurable.reconfigure(getProject());
        }
    }

    protected void handleOutput(String output) {
        log(output, 2);
    }

    protected void handleFlush(String output) {
        handleOutput(output);
    }

    protected int handleInput(byte[] buffer, int offset, int length) throws IOException {
        return getProject().defaultInput(buffer, offset, length);
    }

    protected void handleErrorOutput(String output) {
        log(output, 1);
    }

    protected void handleErrorFlush(String output) {
        handleErrorOutput(output);
    }

    @Override // org.apache.tools.ant.ProjectComponent
    public void log(String msg) {
        log(msg, 2);
    }

    @Override // org.apache.tools.ant.ProjectComponent
    public void log(String msg, int msgLevel) {
        getProject().log(this, msg, msgLevel);
    }

    public final void perform() {
        if (this.invalid) {
            UnknownElement ue = getReplacement();
            Task task = ue.getTask();
            task.perform();
            return;
        }
        getProject().fireTaskStarted(this);
        Throwable reason = null;
        BuildException be = null;
        try {
            maybeConfigure();
            execute();
            getProject().fireTaskFinished(this, null);
        } catch (Error ex) {
            reason = ex;
            try {
                throw ex;
            } catch (Throwable th) {
                ex = th;
                getProject().fireTaskFinished(this, reason);
                throw ex;
            }
        } catch (BuildException ex2) {
            try {
                if (ex2.getLocation() == Location.UNKNOWN_LOCATION) {
                    ex2.setLocation(getLocation());
                }
                reason = ex2;
                throw ex2;
            } catch (Throwable th2) {
                ex = th2;
                getProject().fireTaskFinished(this, reason);
                throw ex;
            }
        } catch (Exception ex3) {
            reason = ex3;
            try {
                be = new BuildException(ex3);
                be.setLocation(getLocation());
                throw be;
            } catch (Throwable th3) {
                ex = th3;
                getProject().fireTaskFinished(this, reason);
                throw ex;
            }
        } catch (Throwable th4) {
            ex = th4;
            getProject().fireTaskFinished(this, reason);
            throw ex;
        }
    }

    final void markInvalid() {
        this.invalid = true;
    }

    protected final boolean isInvalid() {
        return this.invalid;
    }

    private UnknownElement getReplacement() {
        if (this.replacement == null) {
            UnknownElement unknownElement = new UnknownElement(this.taskType);
            this.replacement = unknownElement;
            unknownElement.setProject(getProject());
            this.replacement.setTaskType(this.taskType);
            this.replacement.setTaskName(this.taskName);
            this.replacement.setLocation(this.location);
            this.replacement.setOwningTarget(this.target);
            this.replacement.setRuntimeConfigurableWrapper(this.wrapper);
            this.wrapper.setProxy(this.replacement);
            replaceChildren(this.wrapper, this.replacement);
            this.target.replaceChild(this, this.replacement);
            this.replacement.maybeConfigure();
        }
        return this.replacement;
    }

    private void replaceChildren(RuntimeConfigurable wrapper, UnknownElement unknownElement) {
        Enumeration e = wrapper.getChildren();
        while (e.hasMoreElements()) {
            RuntimeConfigurable runtimeConfigurable = (RuntimeConfigurable) e.nextElement();
            UnknownElement unknownElement2 = new UnknownElement(runtimeConfigurable.getElementTag());
            unknownElement.addChild(unknownElement2);
            unknownElement2.setProject(getProject());
            unknownElement2.setRuntimeConfigurableWrapper(runtimeConfigurable);
            runtimeConfigurable.setProxy(unknownElement2);
            replaceChildren(runtimeConfigurable, unknownElement2);
        }
    }

    public String getTaskType() {
        return this.taskType;
    }

    protected RuntimeConfigurable getWrapper() {
        return this.wrapper;
    }
}
