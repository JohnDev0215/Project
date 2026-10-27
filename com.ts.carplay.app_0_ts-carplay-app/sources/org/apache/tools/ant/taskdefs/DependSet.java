package org.apache.tools.ant.taskdefs;

import java.util.Vector;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class DependSet extends MatchingTask {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private Vector sourceFileSets = new Vector();
    private Vector sourceFileLists = new Vector();
    private Vector targetFileSets = new Vector();
    private Vector targetFileLists = new Vector();

    public void addSrcfileset(FileSet fs) {
        this.sourceFileSets.addElement(fs);
    }

    public void addSrcfilelist(FileList fl) {
        this.sourceFileLists.addElement(fl);
    }

    public void addTargetfileset(FileSet fs) {
        this.targetFileSets.addElement(fs);
    }

    public void addTargetfilelist(FileList fl) {
        this.targetFileLists.addElement(fl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0157, code lost:
    
        r8 = r17;
        r13 = r19;
        r7 = r20;
        r12 = r21;
     */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 847
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.DependSet.execute():void");
    }
}
