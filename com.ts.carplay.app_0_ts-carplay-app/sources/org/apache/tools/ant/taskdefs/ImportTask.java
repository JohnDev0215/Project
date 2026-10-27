package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class ImportTask extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.newFileUtils();
    private String file;
    private boolean optional;

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.file == null) {
            throw new BuildException("import requires file attribute");
        }
        if (getOwningTarget() == null || !"".equals(getOwningTarget().getName())) {
            throw new BuildException("import only allowed as a top-level task");
        }
        ProjectHelper helper = (ProjectHelper) getProject().getReference("ant.projectHelper");
        Vector importStack = helper.getImportStack();
        if (importStack.size() == 0) {
            throw new BuildException("import requires support in ProjectHelper");
        }
        if (getLocation() == null || getLocation().getFileName() == null) {
            throw new BuildException("Unable to get location of import task");
        }
        File buildFile = new File(new File(getLocation().getFileName()).getAbsolutePath());
        Project project = getProject();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Importing file ");
        stringBuffer.append(this.file);
        stringBuffer.append(" from ");
        stringBuffer.append(buildFile.getAbsolutePath());
        project.log(stringBuffer.toString(), 3);
        File buildFileParent = new File(buildFile.getParent());
        File importedFile = FILE_UTILS.resolveFile(buildFileParent, this.file);
        if (!importedFile.exists()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Cannot find ");
            stringBuffer2.append(this.file);
            stringBuffer2.append(" imported from ");
            stringBuffer2.append(buildFile.getAbsolutePath());
            String message = stringBuffer2.toString();
            if (this.optional) {
                getProject().log(message, 3);
                return;
            }
            throw new BuildException(message);
        }
        if (importStack.contains(importedFile)) {
            Project project2 = getProject();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Skipped already imported file:\n   ");
            stringBuffer3.append(importedFile);
            stringBuffer3.append("\n");
            project2.log(stringBuffer3.toString(), 3);
            return;
        }
        try {
            helper.parse(getProject(), importedFile);
        } catch (BuildException ex) {
            throw ProjectHelper.addLocationToBuildException(ex, getLocation());
        }
    }
}
