package org.apache.tools.ant.types;

import java.io.File;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PathTokenizer;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.rmic.KaffeRmic;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class Path extends DataType implements Cloneable {
    private Vector elements;
    public static Path systemClasspath = new Path(null, System.getProperty("java.class.path"));
    public static Path systemBootClasspath = new Path(null, System.getProperty("sun.boot.class.path"));

    public class PathElement {
        private String[] parts;

        public PathElement() {
        }

        public void setLocation(File loc) {
            this.parts = new String[]{Path.translateFile(loc.getAbsolutePath())};
        }

        public void setPath(String path) {
            this.parts = Path.translatePath(Path.this.getProject(), path);
        }

        public String[] getParts() {
            return this.parts;
        }
    }

    public Path(Project p, String path) {
        this(p);
        createPathElement().setPath(path);
    }

    public Path(Project project) {
        setProject(project);
        this.elements = new Vector();
    }

    public void setLocation(File location) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createPathElement().setLocation(location);
    }

    public void setPath(String path) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createPathElement().setPath(path);
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (!this.elements.isEmpty()) {
            throw tooManyAttributes();
        }
        this.elements.addElement(r);
        super.setRefid(r);
    }

    public PathElement createPathElement() throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        PathElement pe = new PathElement();
        this.elements.addElement(pe);
        return pe;
    }

    public void addFileset(FileSet fs) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.elements.addElement(fs);
        setChecked(false);
    }

    public void addFilelist(FileList fl) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.elements.addElement(fl);
        setChecked(false);
    }

    public void addDirset(DirSet dset) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.elements.addElement(dset);
        setChecked(false);
    }

    public void add(Path path) throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.elements.addElement(path);
        setChecked(false);
    }

    public Path createPath() throws BuildException {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        Path p = new Path(getProject());
        this.elements.addElement(p);
        setChecked(false);
        return p;
    }

    public void append(Path other) {
        if (other == null) {
            return;
        }
        String[] l = other.list();
        for (int i = 0; i < l.length; i++) {
            if (this.elements.indexOf(l[i]) == -1) {
                this.elements.addElement(l[i]);
            }
        }
    }

    public void addExisting(Path source) {
        addExisting(source, false);
    }

    public void addExisting(Path source, boolean tryUserDir) {
        File f;
        String[] list = source.list();
        File userDir = tryUserDir ? new File(System.getProperty("user.dir")) : null;
        for (int i = 0; i < list.length; i++) {
            if (getProject() != null) {
                f = getProject().resolveFile(list[i]);
            } else {
                f = new File(list[i]);
            }
            if (tryUserDir && !f.exists()) {
                f = new File(userDir, list[i]);
            }
            if (f.exists()) {
                setLocation(f);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("dropping ");
                stringBuffer.append(f);
                stringBuffer.append(" from path as it doesn't exist");
                log(stringBuffer.toString(), 3);
            }
        }
    }

    public String[] list() {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, getProject());
        }
        Vector result = new Vector(this.elements.size() * 2);
        for (int i = 0; i < this.elements.size(); i++) {
            Object o = this.elements.elementAt(i);
            if (o instanceof Reference) {
                Reference r = (Reference) o;
                o = r.getReferencedObject(getProject());
                if (!(o instanceof Path)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(r.getRefId());
                    stringBuffer.append(" doesn't denote a path ");
                    stringBuffer.append(o);
                    String msg = stringBuffer.toString();
                    throw new BuildException(msg);
                }
            }
            if (o instanceof String) {
                addUnlessPresent(result, (String) o);
            } else if (o instanceof PathElement) {
                String[] parts = ((PathElement) o).getParts();
                if (parts == null) {
                    throw new BuildException("You must either set location or path on <pathelement>");
                }
                for (String str : parts) {
                    addUnlessPresent(result, str);
                }
            } else if (o instanceof Path) {
                Path p = (Path) o;
                if (p.getProject() == null) {
                    p.setProject(getProject());
                }
                for (String str2 : p.list()) {
                    addUnlessPresent(result, str2);
                }
            } else if (o instanceof DirSet) {
                DirSet dset = (DirSet) o;
                addUnlessPresent(result, dset.getDir(getProject()), dset.getDirectoryScanner(getProject()).getIncludedDirectories());
            } else if (o instanceof FileSet) {
                FileSet fs = (FileSet) o;
                addUnlessPresent(result, fs.getDir(getProject()), fs.getDirectoryScanner(getProject()).getIncludedFiles());
            } else if (o instanceof FileList) {
                FileList fl = (FileList) o;
                addUnlessPresent(result, fl.getDir(getProject()), fl.getFiles(getProject()));
            }
        }
        String[] res = new String[result.size()];
        result.copyInto(res);
        return res;
    }

    public String toString() {
        String[] list = list();
        if (list.length == 0) {
            return "";
        }
        StringBuffer result = new StringBuffer(list[0].toString());
        for (int i = 1; i < list.length; i++) {
            result.append(File.pathSeparatorChar);
            result.append(list[i]);
        }
        return result.toString();
    }

    public static String[] translatePath(Project project, String source) {
        Vector result = new Vector();
        if (source == null) {
            return new String[0];
        }
        PathTokenizer tok = new PathTokenizer(source);
        StringBuffer element = new StringBuffer();
        while (tok.hasMoreTokens()) {
            String pathElement = tok.nextToken();
            try {
                element.append(resolveFile(project, pathElement));
            } catch (BuildException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Dropping path element ");
                stringBuffer.append(pathElement);
                stringBuffer.append(" as it is not valid relative to the project");
                project.log(stringBuffer.toString(), 3);
            }
            for (int i = 0; i < element.length(); i++) {
                translateFileSep(element, i);
            }
            result.addElement(element.toString());
            element = new StringBuffer();
        }
        String[] res = new String[result.size()];
        result.copyInto(res);
        return res;
    }

    public static String translateFile(String source) {
        if (source == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(source);
        for (int i = 0; i < result.length(); i++) {
            translateFileSep(result, i);
        }
        return result.toString();
    }

    protected static boolean translateFileSep(StringBuffer buffer, int pos) {
        if (buffer.charAt(pos) == '/' || buffer.charAt(pos) == '\\') {
            buffer.setCharAt(pos, File.separatorChar);
            return true;
        }
        return false;
    }

    public int size() {
        return list().length;
    }

    public Object clone() {
        try {
            Path p = (Path) super.clone();
            p.elements = (Vector) this.elements.clone();
            return p;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    protected void dieOnCircularReference(Stack stk, Project p) throws BuildException {
        if (isChecked()) {
            return;
        }
        Enumeration e = this.elements.elements();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof Reference) {
                o = ((Reference) o).getReferencedObject(p);
            }
            if (o instanceof DataType) {
                if (stk.contains(o)) {
                    throw circularReference();
                }
                stk.push(o);
                ((DataType) o).dieOnCircularReference(stk, p);
                stk.pop();
            }
        }
        setChecked(true);
    }

    private static String resolveFile(Project project, String relativeName) {
        if (project != null) {
            File f = project.resolveFile(relativeName);
            return f.getAbsolutePath();
        }
        return relativeName;
    }

    private static void addUnlessPresent(Vector v, String s) {
        if (v.indexOf(s) == -1) {
            v.addElement(s);
        }
    }

    private static void addUnlessPresent(Vector v, File dir, String[] s) {
        for (String str : s) {
            File d = new File(dir, str);
            String absolutePath = d.getAbsolutePath();
            addUnlessPresent(v, translateFile(absolutePath));
        }
    }

    public Path concatSystemClasspath() {
        return concatSystemClasspath("last");
    }

    public Path concatSystemClasspath(String defValue) {
        String o;
        Path result = new Path(getProject());
        String order = defValue;
        if (getProject() != null && (o = getProject().getProperty("build.sysclasspath")) != null) {
            order = o;
        }
        if (order.equals("only")) {
            result.addExisting(systemClasspath, true);
        } else if (order.equals("first")) {
            result.addExisting(systemClasspath, true);
            result.addExisting(this);
        } else if (order.equals("ignore")) {
            result.addExisting(this);
        } else {
            if (!order.equals("last")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("invalid value for build.sysclasspath: ");
                stringBuffer.append(order);
                log(stringBuffer.toString(), 1);
            }
            result.addExisting(this);
            result.addExisting(systemClasspath, true);
        }
        return result;
    }

    public void addJavaRuntime() {
        if ("Kaffe".equals(System.getProperty("java.vm.name"))) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(System.getProperty("java.home"));
            stringBuffer.append(File.separator);
            stringBuffer.append("share");
            stringBuffer.append(File.separator);
            stringBuffer.append(KaffeRmic.COMPILER_NAME);
            File kaffeShare = new File(stringBuffer.toString());
            if (kaffeShare.isDirectory()) {
                FileSet kaffeJarFiles = new FileSet();
                kaffeJarFiles.setDir(kaffeShare);
                kaffeJarFiles.setIncludes("*.jar");
                addFileset(kaffeJarFiles);
            }
        } else if ("GNU libgcj".equals(System.getProperty("java.vm.name"))) {
            addExisting(systemBootClasspath);
        }
        if (System.getProperty("java.vendor").toLowerCase(Locale.US).indexOf("microsoft") >= 0) {
            FileSet msZipFiles = new FileSet();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(System.getProperty("java.home"));
            stringBuffer2.append(File.separator);
            stringBuffer2.append("Packages");
            msZipFiles.setDir(new File(stringBuffer2.toString()));
            msZipFiles.setIncludes("*.ZIP");
            addFileset(msZipFiles);
            return;
        }
        if (JavaEnvUtils.isJavaVersion("1.1")) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(System.getProperty("java.home"));
            stringBuffer3.append(File.separator);
            stringBuffer3.append("lib");
            stringBuffer3.append(File.separator);
            stringBuffer3.append("classes.zip");
            addExisting(new Path(null, stringBuffer3.toString()));
            return;
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(System.getProperty("java.home"));
        stringBuffer4.append(File.separator);
        stringBuffer4.append("lib");
        stringBuffer4.append(File.separator);
        stringBuffer4.append("rt.jar");
        addExisting(new Path(null, stringBuffer4.toString()));
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append(System.getProperty("java.home"));
        stringBuffer5.append(File.separator);
        stringBuffer5.append("jre");
        stringBuffer5.append(File.separator);
        stringBuffer5.append("lib");
        stringBuffer5.append(File.separator);
        stringBuffer5.append("rt.jar");
        addExisting(new Path(null, stringBuffer5.toString()));
        String[] secJars = {"jce", "jsse"};
        for (int i = 0; i < secJars.length; i++) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(System.getProperty("java.home"));
            stringBuffer6.append(File.separator);
            stringBuffer6.append("lib");
            stringBuffer6.append(File.separator);
            stringBuffer6.append(secJars[i]);
            stringBuffer6.append(".jar");
            addExisting(new Path(null, stringBuffer6.toString()));
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(System.getProperty("java.home"));
            stringBuffer7.append(File.separator);
            stringBuffer7.append("..");
            stringBuffer7.append(File.separator);
            stringBuffer7.append("Classes");
            stringBuffer7.append(File.separator);
            stringBuffer7.append(secJars[i]);
            stringBuffer7.append(".jar");
            addExisting(new Path(null, stringBuffer7.toString()));
        }
        String[] ibmJars = {"core", "graphics", "security", "server", "xml"};
        for (String str : ibmJars) {
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append(System.getProperty("java.home"));
            stringBuffer8.append(File.separator);
            stringBuffer8.append("lib");
            stringBuffer8.append(File.separator);
            stringBuffer8.append(str);
            stringBuffer8.append(".jar");
            addExisting(new Path(null, stringBuffer8.toString()));
        }
        StringBuffer stringBuffer9 = new StringBuffer();
        stringBuffer9.append(System.getProperty("java.home"));
        stringBuffer9.append(File.separator);
        stringBuffer9.append("..");
        stringBuffer9.append(File.separator);
        stringBuffer9.append("Classes");
        stringBuffer9.append(File.separator);
        stringBuffer9.append("classes.jar");
        addExisting(new Path(null, stringBuffer9.toString()));
        StringBuffer stringBuffer10 = new StringBuffer();
        stringBuffer10.append(System.getProperty("java.home"));
        stringBuffer10.append(File.separator);
        stringBuffer10.append("..");
        stringBuffer10.append(File.separator);
        stringBuffer10.append("Classes");
        stringBuffer10.append(File.separator);
        stringBuffer10.append("ui.jar");
        addExisting(new Path(null, stringBuffer10.toString()));
    }

    public void addExtdirs(Path extdirs) {
        if (extdirs == null) {
            String extProp = System.getProperty("java.ext.dirs");
            if (extProp != null) {
                extdirs = new Path(getProject(), extProp);
            } else {
                return;
            }
        }
        String[] dirs = extdirs.list();
        for (String str : dirs) {
            File dir = getProject().resolveFile(str);
            if (dir.exists() && dir.isDirectory()) {
                FileSet fs = new FileSet();
                fs.setDir(dir);
                fs.setIncludes("*");
                addFileset(fs);
            }
        }
    }
}
