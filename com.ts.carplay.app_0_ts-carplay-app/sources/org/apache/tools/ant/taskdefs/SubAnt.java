package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class SubAnt extends Task {
    private Path buildpath;
    private Ant ant = null;
    private String target = null;
    private String antfile = Main.DEFAULT_BUILD_FILENAME;
    private File genericantfile = null;
    private boolean verbose = false;
    private boolean inheritAll = false;
    private boolean inheritRefs = false;
    private boolean failOnError = true;
    private String output = null;
    private Vector properties = new Vector();
    private Vector references = new Vector();
    private Vector propertySets = new Vector();

    @Override // org.apache.tools.ant.Task
    public void handleOutput(String output) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleOutput(output);
        } else {
            super.handleOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] buffer, int offset, int length) throws IOException {
        Ant ant = this.ant;
        if (ant != null) {
            return ant.handleInput(buffer, offset, length);
        }
        return super.handleInput(buffer, offset, length);
    }

    @Override // org.apache.tools.ant.Task
    public void handleFlush(String output) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleFlush(output);
        } else {
            super.handleFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorOutput(String output) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleErrorOutput(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void handleErrorFlush(String output) {
        Ant ant = this.ant;
        if (ant != null) {
            ant.handleErrorFlush(output);
        } else {
            super.handleErrorFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        Path path = this.buildpath;
        if (path == null) {
            throw new BuildException("No buildpath specified");
        }
        String[] filenames = path.list();
        int count = filenames.length;
        if (count < 1) {
            log("No sub-builds to iterate on", 1);
            return;
        }
        BuildException buildException = null;
        for (String str : filenames) {
            File file = null;
            String subdirPath = null;
            Throwable thrownException = null;
            File directory = null;
            try {
                file = new File(str);
                if (file.isDirectory()) {
                    if (this.verbose) {
                        subdirPath = file.getPath();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Entering directory: ");
                        stringBuffer.append(subdirPath);
                        stringBuffer.append("\n");
                        log(stringBuffer.toString(), 2);
                    }
                    if (this.genericantfile != null) {
                        directory = file;
                        file = this.genericantfile;
                    } else {
                        file = new File(file, this.antfile);
                    }
                }
                execute(file, directory);
                if (this.verbose && subdirPath != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Leaving directory: ");
                    stringBuffer2.append(subdirPath);
                    stringBuffer2.append("\n");
                    log(stringBuffer2.toString(), 2);
                }
            } catch (RuntimeException ex) {
                if (!getProject().isKeepGoingMode()) {
                    if (this.verbose && 0 != 0) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Leaving directory: ");
                        stringBuffer3.append((String) null);
                        stringBuffer3.append("\n");
                        log(stringBuffer3.toString(), 2);
                    }
                    throw ex;
                }
                thrownException = ex;
            } catch (Throwable ex2) {
                if (!getProject().isKeepGoingMode()) {
                    if (this.verbose && 0 != 0) {
                        StringBuffer stringBuffer4 = new StringBuffer();
                        stringBuffer4.append("Leaving directory: ");
                        stringBuffer4.append((String) null);
                        stringBuffer4.append("\n");
                        log(stringBuffer4.toString(), 2);
                    }
                    throw new BuildException(ex2);
                }
                thrownException = ex2;
            }
            if (thrownException != null) {
                if (thrownException instanceof BuildException) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append("File '");
                    stringBuffer5.append(file);
                    stringBuffer5.append("' failed with message '");
                    stringBuffer5.append(thrownException.getMessage());
                    stringBuffer5.append("'.");
                    log(stringBuffer5.toString(), 0);
                    if (buildException == null) {
                        buildException = (BuildException) thrownException;
                    }
                } else {
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("Target '");
                    stringBuffer6.append(file);
                    stringBuffer6.append("' failed with message '");
                    stringBuffer6.append(thrownException.getMessage());
                    stringBuffer6.append("'.");
                    log(stringBuffer6.toString(), 0);
                    thrownException.printStackTrace(System.err);
                    if (buildException == null) {
                        buildException = new BuildException(thrownException);
                    }
                }
                if (this.verbose && subdirPath != null) {
                    StringBuffer stringBuffer7 = new StringBuffer();
                    stringBuffer7.append("Leaving directory: ");
                    stringBuffer7.append(subdirPath);
                    stringBuffer7.append("\n");
                    log(stringBuffer7.toString(), 2);
                }
            }
        }
        if (buildException != null) {
            throw buildException;
        }
    }

    private void execute(File file, File directory) throws Throwable {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid file: ");
            stringBuffer.append(file);
            String msg = stringBuffer.toString();
            if (this.failOnError) {
                throw new BuildException(msg);
            }
            log(msg, 1);
            return;
        }
        this.ant = createAntTask(directory);
        String antfilename = file.getAbsolutePath();
        this.ant.setAntfile(antfilename);
        try {
            this.ant.execute();
            this.ant = null;
        } catch (BuildException e) {
            try {
                if (this.failOnError) {
                    throw e;
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Failure for target '");
                stringBuffer2.append(this.target);
                stringBuffer2.append("' of: ");
                stringBuffer2.append(antfilename);
                stringBuffer2.append("\n");
                stringBuffer2.append(e.getMessage());
                log(stringBuffer2.toString(), 1);
                this.ant = null;
            } catch (Throwable th) {
                th = th;
                this.ant = null;
                throw th;
            }
        } catch (Throwable e2) {
            try {
                if (this.failOnError) {
                    throw new BuildException(e2);
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Failure for target '");
                stringBuffer3.append(this.target);
                stringBuffer3.append("' of: ");
                stringBuffer3.append(antfilename);
                stringBuffer3.append("\n");
                stringBuffer3.append(e2.toString());
                log(stringBuffer3.toString(), 1);
                this.ant = null;
            } catch (Throwable th2) {
                th = th2;
                this.ant = null;
                throw th;
            }
        }
    }

    public void setAntfile(String antfile) {
        this.antfile = antfile;
    }

    public void setGenericAntfile(File afile) {
        this.genericantfile = afile;
    }

    public void setFailonerror(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setVerbose(boolean on) {
        this.verbose = on;
    }

    public void setOutput(String s) {
        this.output = s;
    }

    public void setInheritall(boolean b) {
        this.inheritAll = b;
    }

    public void setInheritrefs(boolean b) {
        this.inheritRefs = b;
    }

    public void addProperty(Property p) {
        this.properties.addElement(p);
    }

    public void addReference(Ant.Reference r) {
        this.references.addElement(r);
    }

    public void addPropertyset(PropertySet ps) {
        this.propertySets.addElement(ps);
    }

    public void addDirset(DirSet set) {
        getBuildpath().addDirset(set);
    }

    public void addFileset(FileSet set) {
        getBuildpath().addFileset(set);
    }

    public void addFilelist(FileList list) {
        getBuildpath().addFilelist(list);
    }

    public void setBuildpath(Path s) {
        getBuildpath().append(s);
    }

    public Path createBuildpath() {
        return getBuildpath().createPath();
    }

    public Path.PathElement createBuildpathElement() {
        return getBuildpath().createPathElement();
    }

    private Path getBuildpath() {
        if (this.buildpath == null) {
            this.buildpath = new Path(getProject());
        }
        return this.buildpath;
    }

    public void setBuildpathRef(Reference r) {
        createBuildpath().setRefid(r);
    }

    private Ant createAntTask(File directory) {
        Ant ant = (Ant) getProject().createTask("ant");
        ant.setOwningTarget(getOwningTarget());
        ant.setTaskName(getTaskName());
        ant.init();
        String str = this.target;
        if (str != null && str.length() > 0) {
            ant.setTarget(this.target);
        }
        String str2 = this.output;
        if (str2 != null) {
            ant.setOutput(str2);
        }
        if (directory != null) {
            ant.setDir(directory);
        }
        ant.setInheritAll(this.inheritAll);
        Enumeration i = this.properties.elements();
        while (i.hasMoreElements()) {
            copyProperty(ant.createProperty(), (Property) i.nextElement());
        }
        Enumeration i2 = this.propertySets.elements();
        while (i2.hasMoreElements()) {
            ant.addPropertyset((PropertySet) i2.nextElement());
        }
        ant.setInheritRefs(this.inheritRefs);
        Enumeration i3 = this.references.elements();
        while (i3.hasMoreElements()) {
            ant.addReference((Ant.Reference) i3.nextElement());
        }
        return ant;
    }

    private static void copyProperty(Property to, Property from) {
        to.setName(from.getName());
        if (from.getValue() != null) {
            to.setValue(from.getValue());
        }
        if (from.getFile() != null) {
            to.setFile(from.getFile());
        }
        if (from.getResource() != null) {
            to.setResource(from.getResource());
        }
        if (from.getPrefix() != null) {
            to.setPrefix(from.getPrefix());
        }
        if (from.getRefid() != null) {
            to.setRefid(from.getRefid());
        }
        if (from.getEnvironment() != null) {
            to.setEnvironment(from.getEnvironment());
        }
        if (from.getClasspath() != null) {
            to.setClasspath(from.getClasspath());
        }
    }
}
