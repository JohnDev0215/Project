package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class ManifestTask extends Task {
    private String encoding;
    private File manifestFile;
    private Mode mode;
    private Manifest nestedManifest = new Manifest();

    public static class Mode extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"update", "replace"};
        }
    }

    public ManifestTask() {
        Mode mode = new Mode();
        this.mode = mode;
        mode.setValue("replace");
    }

    public void addConfiguredSection(Manifest.Section section) throws ManifestException {
        this.nestedManifest.addConfiguredSection(section);
    }

    public void addConfiguredAttribute(Manifest.Attribute attribute) throws ManifestException {
        this.nestedManifest.addConfiguredAttribute(attribute);
    }

    public void setFile(File f) {
        this.manifestFile = f;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setMode(Mode m) {
        this.mode = m;
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ManifestTask.execute():void");
    }
}
