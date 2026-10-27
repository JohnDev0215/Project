package org.apache.tools.ant.types;

import java.io.File;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class FileList extends DataType {
    private File dir;
    private Vector filenames;

    public FileList() {
        this.filenames = new Vector();
    }

    protected FileList(FileList filelist) {
        this.filenames = new Vector();
        this.dir = filelist.dir;
        this.filenames = filelist.filenames;
        setProject(filelist.getProject());
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (this.dir != null || this.filenames.size() != 0) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    public void setDir(File dir) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.dir = dir;
    }

    public File getDir(Project p) {
        if (isReference()) {
            return getRef(p).getDir(p);
        }
        return this.dir;
    }

    public void setFiles(String filenames) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (filenames != null && filenames.length() > 0) {
            StringTokenizer tok = new StringTokenizer(filenames, ", \t\n\r\f", false);
            while (tok.hasMoreTokens()) {
                this.filenames.addElement(tok.nextToken());
            }
        }
    }

    public String[] getFiles(Project p) {
        if (isReference()) {
            return getRef(p).getFiles(p);
        }
        if (this.dir == null) {
            throw new BuildException("No directory specified for filelist.");
        }
        if (this.filenames.size() == 0) {
            throw new BuildException("No files specified for filelist.");
        }
        String[] result = new String[this.filenames.size()];
        this.filenames.copyInto(result);
        return result;
    }

    protected FileList getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (!(o instanceof FileList)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a filelist");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return (FileList) o;
    }

    public static class FileName {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public void addConfiguredFile(FileName name) {
        if (name.getName() == null) {
            throw new BuildException("No name specified in nested file element");
        }
        this.filenames.addElement(name.getName());
    }
}
