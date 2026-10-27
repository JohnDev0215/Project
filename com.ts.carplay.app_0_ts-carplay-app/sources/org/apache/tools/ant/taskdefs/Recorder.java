package org.apache.tools.ant.taskdefs;

import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class Recorder extends Task {
    private static Hashtable recorderEntries = new Hashtable();
    private String filename = null;
    private Boolean append = null;
    private Boolean start = null;
    private int loglevel = -1;
    private boolean emacsMode = false;

    public void setName(String fname) {
        this.filename = fname;
    }

    public void setAction(ActionChoices action) {
        if (action.getValue().equalsIgnoreCase("start")) {
            this.start = Boolean.TRUE;
        } else {
            this.start = Boolean.FALSE;
        }
    }

    public void setAppend(boolean append) {
        this.append = append ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setEmacsMode(boolean emacsMode) {
        this.emacsMode = emacsMode;
    }

    public void setLoglevel(VerbosityLevelChoices level) {
        String lev = level.getValue();
        if (lev.equalsIgnoreCase("error")) {
            this.loglevel = 0;
            return;
        }
        if (lev.equalsIgnoreCase("warn")) {
            this.loglevel = 1;
            return;
        }
        if (lev.equalsIgnoreCase("info")) {
            this.loglevel = 2;
        } else if (lev.equalsIgnoreCase("verbose")) {
            this.loglevel = 3;
        } else if (lev.equalsIgnoreCase("debug")) {
            this.loglevel = 4;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.filename == null) {
            throw new BuildException("No filename specified");
        }
        Project project = getProject();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("setting a recorder for name ");
        stringBuffer.append(this.filename);
        project.log(stringBuffer.toString(), 4);
        RecorderEntry recorder = getRecorder(this.filename, getProject());
        recorder.setMessageOutputLevel(this.loglevel);
        Boolean bool = this.start;
        if (bool != null) {
            if (bool.booleanValue()) {
                recorder.reopenFile();
                recorder.setRecordState(this.start);
            } else {
                recorder.setRecordState(this.start);
                recorder.closeFile();
            }
        }
        recorder.setEmacsMode(this.emacsMode);
    }

    public static class ActionChoices extends EnumeratedAttribute {
        private static final String[] VALUES = {"start", "stop"};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return VALUES;
        }
    }

    public static class VerbosityLevelChoices extends EnumeratedAttribute {
        private static final String[] VALUES = {"error", "warn", "info", "verbose", "debug"};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return VALUES;
        }
    }

    protected RecorderEntry getRecorder(String name, Project proj) throws BuildException {
        Object o = recorderEntries.get(name);
        if (o == null) {
            RecorderEntry entry = new RecorderEntry(name);
            Boolean bool = this.append;
            if (bool == null) {
                entry.openFile(false);
            } else {
                entry.openFile(bool.booleanValue());
            }
            entry.setProject(proj);
            recorderEntries.put(name, entry);
            return entry;
        }
        return (RecorderEntry) o;
    }
}
