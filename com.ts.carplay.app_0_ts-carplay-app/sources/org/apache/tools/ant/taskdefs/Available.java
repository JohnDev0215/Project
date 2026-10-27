package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class Available extends Task implements Condition {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String classname;
    private Path classpath;
    private File file;
    private String filename;
    private Path filepath;
    private AntClassLoader loader;
    private String property;
    private String resource;
    private FileDir type;
    private String value = "true";
    private boolean isTask = false;
    private boolean ignoreSystemclasses = false;

    public void setClasspath(Path classpath) {
        createClasspath().append(classpath);
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public void setFilepath(Path filepath) {
        createFilepath().append(filepath);
    }

    public Path createFilepath() {
        if (this.filepath == null) {
            this.filepath = new Path(getProject());
        }
        return this.filepath.createPath();
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setClassname(String classname) {
        if (!"".equals(classname)) {
            this.classname = classname;
        }
    }

    public void setFile(File file) {
        this.file = file;
        this.filename = FILE_UTILS.removeLeadingPath(getProject().getBaseDir(), file);
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setType(String type) {
        log("DEPRECATED - The setType(String) method has been deprecated. Use setType(Available.FileDir) instead.", 1);
        FileDir fileDir = new FileDir();
        this.type = fileDir;
        fileDir.setValue(type);
    }

    public void setType(FileDir type) {
        this.type = type;
    }

    public void setIgnoresystemclasses(boolean ignore) {
        this.ignoreSystemclasses = ignore;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property == null) {
            throw new BuildException("property attribute is required", getLocation());
        }
        this.isTask = true;
        try {
            if (eval()) {
                String oldvalue = getProject().getProperty(this.property);
                if (oldvalue != null && !oldvalue.equals(this.value)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("DEPRECATED - <available> used to override an existing property.");
                    stringBuffer.append(StringUtils.LINE_SEP);
                    stringBuffer.append("  Build file should not reuse the same property");
                    stringBuffer.append(" name for different values.");
                    log(stringBuffer.toString(), 1);
                }
                getProject().setProperty(this.property, this.value);
            }
        } finally {
            this.isTask = false;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.classname == null && this.file == null && this.resource == null) {
            throw new BuildException("At least one of (classname|file|resource) is required", getLocation());
        }
        if (this.type != null && this.file == null) {
            throw new BuildException("The type attribute is only valid when specifying the file attribute.", getLocation());
        }
        Path path = this.classpath;
        if (path != null) {
            path.setProject(getProject());
            this.loader = getProject().createClassLoader(this.classpath);
        }
        String appendix = "";
        if (this.isTask) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" to set property ");
            stringBuffer.append(this.property);
            appendix = stringBuffer.toString();
        } else {
            setTaskName("available");
        }
        String str = this.classname;
        if (str != null && !checkClass(str)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to load class ");
            stringBuffer2.append(this.classname);
            stringBuffer2.append(appendix);
            log(stringBuffer2.toString(), 3);
            return false;
        }
        if (this.file != null && !checkFile()) {
            StringBuffer buf = new StringBuffer("Unable to find ");
            FileDir fileDir = this.type;
            if (fileDir != null) {
                buf.append(fileDir);
                buf.append(' ');
            }
            buf.append(this.filename);
            buf.append(appendix);
            log(buf.toString(), 3);
            return false;
        }
        String str2 = this.resource;
        if (str2 != null && !checkResource(str2)) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Unable to load resource ");
            stringBuffer3.append(this.resource);
            stringBuffer3.append(appendix);
            log(stringBuffer3.toString(), 3);
            return false;
        }
        AntClassLoader antClassLoader = this.loader;
        if (antClassLoader != null) {
            antClassLoader.cleanup();
            this.loader = null;
        }
        if (!this.isTask) {
            setTaskName(null);
            return true;
        }
        return true;
    }

    private boolean checkFile() {
        File grandParent;
        Path path = this.filepath;
        if (path == null) {
            return checkFile(this.file, this.filename);
        }
        String[] paths = path.list();
        for (int i = 0; i < paths.length; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Searching ");
            stringBuffer.append(paths[i]);
            log(stringBuffer.toString(), 4);
            File path2 = new File(paths[i]);
            if (path2.exists() && this.filename.equals(paths[i])) {
                FileDir fileDir = this.type;
                if (fileDir == null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Found: ");
                    stringBuffer2.append(path2);
                    log(stringBuffer2.toString(), 3);
                    return true;
                }
                if (fileDir.isDir() && path2.isDirectory()) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Found directory: ");
                    stringBuffer3.append(path2);
                    log(stringBuffer3.toString(), 3);
                    return true;
                }
                if (!this.type.isFile() || !path2.isFile()) {
                    return false;
                }
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Found file: ");
                stringBuffer4.append(path2);
                log(stringBuffer4.toString(), 3);
                return true;
            }
            File parent = path2.getParentFile();
            if (parent != null && parent.exists() && this.filename.equals(parent.getAbsolutePath())) {
                FileDir fileDir2 = this.type;
                if (fileDir2 == null) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append("Found: ");
                    stringBuffer5.append(parent);
                    log(stringBuffer5.toString(), 3);
                    return true;
                }
                if (!fileDir2.isDir()) {
                    return false;
                }
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append("Found directory: ");
                stringBuffer6.append(parent);
                log(stringBuffer6.toString(), 3);
                return true;
            }
            if (path2.exists() && path2.isDirectory()) {
                File file = new File(path2, this.filename);
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append(this.filename);
                stringBuffer7.append(" in ");
                stringBuffer7.append(path2);
                if (checkFile(file, stringBuffer7.toString())) {
                    return true;
                }
            }
            if (parent != null && parent.exists()) {
                File file2 = new File(parent, this.filename);
                StringBuffer stringBuffer8 = new StringBuffer();
                stringBuffer8.append(this.filename);
                stringBuffer8.append(" in ");
                stringBuffer8.append(parent);
                if (checkFile(file2, stringBuffer8.toString())) {
                    return true;
                }
            }
            if (parent != null && (grandParent = parent.getParentFile()) != null && grandParent.exists()) {
                File file3 = new File(grandParent, this.filename);
                StringBuffer stringBuffer9 = new StringBuffer();
                stringBuffer9.append(this.filename);
                stringBuffer9.append(" in ");
                stringBuffer9.append(grandParent);
                if (checkFile(file3, stringBuffer9.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkFile(File f, String text) {
        FileDir fileDir = this.type;
        if (fileDir != null) {
            if (fileDir.isDir()) {
                if (f.isDirectory()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Found directory: ");
                    stringBuffer.append(text);
                    log(stringBuffer.toString(), 3);
                }
                return f.isDirectory();
            }
            if (this.type.isFile()) {
                if (f.isFile()) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Found file: ");
                    stringBuffer2.append(text);
                    log(stringBuffer2.toString(), 3);
                }
                return f.isFile();
            }
        }
        if (f.exists()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Found: ");
            stringBuffer3.append(text);
            log(stringBuffer3.toString(), 3);
        }
        return f.exists();
    }

    private boolean checkResource(String resource) {
        AntClassLoader antClassLoader = this.loader;
        if (antClassLoader != null) {
            return antClassLoader.getResourceAsStream(resource) != null;
        }
        ClassLoader cL = getClass().getClassLoader();
        return cL != null ? cL.getResourceAsStream(resource) != null : ClassLoader.getSystemResourceAsStream(resource) != null;
    }

    private boolean checkClass(String classname) {
        try {
            if (this.ignoreSystemclasses) {
                AntClassLoader antClassLoaderCreateClassLoader = getProject().createClassLoader(this.classpath);
                this.loader = antClassLoaderCreateClassLoader;
                antClassLoaderCreateClassLoader.setParentFirst(false);
                this.loader.addJavaLibraries();
                if (this.loader == null) {
                    return false;
                }
                try {
                    this.loader.findClass(classname);
                } catch (SecurityException e) {
                    return true;
                }
            } else if (this.loader != null) {
                this.loader.loadClass(classname);
            } else {
                ClassLoader l = getClass().getClassLoader();
                if (l != null) {
                    Class.forName(classname, true, l);
                } else {
                    Class.forName(classname);
                }
            }
            return true;
        } catch (ClassNotFoundException e2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("class \"");
            stringBuffer.append(classname);
            stringBuffer.append("\" was not found");
            log(stringBuffer.toString(), 4);
            return false;
        } catch (NoClassDefFoundError e3) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not load dependent class \"");
            stringBuffer2.append(e3.getMessage());
            stringBuffer2.append("\" for class \"");
            stringBuffer2.append(classname);
            stringBuffer2.append("\"");
            log(stringBuffer2.toString(), 4);
            return false;
        }
    }

    public static class FileDir extends EnumeratedAttribute {
        private static final String[] VALUES = {TypeSelector.FileType.FILE, TypeSelector.FileType.DIR};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return VALUES;
        }

        public boolean isDir() {
            return TypeSelector.FileType.DIR.equalsIgnoreCase(getValue());
        }

        public boolean isFile() {
            return TypeSelector.FileType.FILE.equalsIgnoreCase(getValue());
        }
    }
}
