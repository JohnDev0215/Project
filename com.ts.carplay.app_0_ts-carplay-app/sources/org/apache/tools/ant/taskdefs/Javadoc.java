package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Javadoc extends Task {
    static final String[] SCOPE_ELEMENTS = {"overview", "packages", "types", "constructors", "methods", "fields"};
    private String noqualifier;
    private Commandline cmd = new Commandline();
    private boolean failOnError = false;
    private Path sourcePath = null;
    private File destDir = null;
    private Vector sourceFiles = new Vector();
    private Vector packageNames = new Vector();
    private Vector excludePackageNames = new Vector(1);
    private boolean author = true;
    private boolean version = true;
    private DocletInfo doclet = null;
    private Path classpath = null;
    private Path bootclasspath = null;
    private String group = null;
    private String packageList = null;
    private Vector links = new Vector();
    private Vector groups = new Vector();
    private Vector tags = new Vector();
    private boolean useDefaultExcludes = true;
    private Html doctitle = null;
    private Html header = null;
    private Html footer = null;
    private Html bottom = null;
    private boolean useExternalFile = false;
    private FileUtils fileUtils = FileUtils.newFileUtils();
    private String source = null;
    private boolean linksource = false;
    private boolean breakiterator = false;
    private boolean includeNoSourcePackages = false;
    private boolean old = false;
    private String executable = null;
    private Vector fileSets = new Vector();
    private Vector packageSets = new Vector();

    public class DocletParam {
        private String name;
        private String value;

        public DocletParam() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static class ExtensionInfo extends ProjectComponent {
        private String name;
        private Path path;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setPath(Path path) {
            Path path2 = this.path;
            if (path2 == null) {
                this.path = path;
            } else {
                path2.append(path);
            }
        }

        public Path getPath() {
            return this.path;
        }

        public Path createPath() {
            if (this.path == null) {
                this.path = new Path(getProject());
            }
            return this.path.createPath();
        }

        public void setPathRef(Reference r) {
            createPath().setRefid(r);
        }
    }

    public class DocletInfo extends ExtensionInfo {
        private Vector params = new Vector();

        public DocletInfo() {
        }

        public DocletParam createParam() {
            DocletParam param = Javadoc.this.new DocletParam();
            this.params.addElement(param);
            return param;
        }

        public Enumeration getParams() {
            return this.params.elements();
        }
    }

    public static class PackageName {
        private String name;

        public void setName(String name) {
            this.name = name.trim();
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return getName();
        }
    }

    public static class SourceFile {
        private File file;

        public SourceFile() {
        }

        public SourceFile(File file) {
            this.file = file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public File getFile() {
            return this.file;
        }
    }

    public static class Html {
        private StringBuffer text = new StringBuffer();

        public void addText(String t) {
            this.text.append(t);
        }

        public String getText() {
            return this.text.substring(0);
        }
    }

    public static class AccessType extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"protected", "public", "package", "private"};
        }
    }

    private void addArgIf(boolean b, String arg) {
        if (b) {
            this.cmd.createArgument().setValue(arg);
        }
    }

    private void addArgIfNotEmpty(String key, String value) {
        if (value != null && value.length() != 0) {
            this.cmd.createArgument().setValue(key);
            this.cmd.createArgument().setValue(value);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Warning: Leaving out empty argument '");
        stringBuffer.append(key);
        stringBuffer.append("'");
        log(stringBuffer.toString(), 1);
    }

    public void setUseExternalFile(boolean b) {
        this.useExternalFile = b;
    }

    public void setDefaultexcludes(boolean useDefaultExcludes) {
        this.useDefaultExcludes = useDefaultExcludes;
    }

    public void setMaxmemory(String max) {
        Commandline.Argument argumentCreateArgument = this.cmd.createArgument();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-J-Xmx");
        stringBuffer.append(max);
        argumentCreateArgument.setValue(stringBuffer.toString());
    }

    public void setAdditionalparam(String add) {
        this.cmd.createArgument().setLine(add);
    }

    public Commandline.Argument createArg() {
        return this.cmd.createArgument();
    }

    public void setSourcepath(Path src) {
        Path path = this.sourcePath;
        if (path == null) {
            this.sourcePath = src;
        } else {
            path.append(src);
        }
    }

    public Path createSourcepath() {
        if (this.sourcePath == null) {
            this.sourcePath = new Path(getProject());
        }
        return this.sourcePath.createPath();
    }

    public void setSourcepathRef(Reference r) {
        createSourcepath().setRefid(r);
    }

    public void setDestdir(File dir) {
        this.destDir = dir;
        this.cmd.createArgument().setValue("-d");
        this.cmd.createArgument().setFile(this.destDir);
    }

    public void setSourcefiles(String src) {
        StringTokenizer tok = new StringTokenizer(src, ",");
        while (tok.hasMoreTokens()) {
            String f = tok.nextToken();
            SourceFile sf = new SourceFile();
            sf.setFile(getProject().resolveFile(f.trim()));
            addSource(sf);
        }
    }

    public void addSource(SourceFile sf) {
        this.sourceFiles.addElement(sf);
    }

    public void setPackagenames(String packages) {
        StringTokenizer tok = new StringTokenizer(packages, ",");
        while (tok.hasMoreTokens()) {
            String p = tok.nextToken();
            PackageName pn = new PackageName();
            pn.setName(p);
            addPackage(pn);
        }
    }

    public void addPackage(PackageName pn) {
        this.packageNames.addElement(pn);
    }

    public void setExcludePackageNames(String packages) {
        StringTokenizer tok = new StringTokenizer(packages, ",");
        while (tok.hasMoreTokens()) {
            String p = tok.nextToken();
            PackageName pn = new PackageName();
            pn.setName(p);
            addExcludePackage(pn);
        }
    }

    public void addExcludePackage(PackageName pn) {
        this.excludePackageNames.addElement(pn);
    }

    public void setOverview(File f) {
        this.cmd.createArgument().setValue("-overview");
        this.cmd.createArgument().setFile(f);
    }

    public void setPublic(boolean b) {
        addArgIf(b, "-public");
    }

    public void setProtected(boolean b) {
        addArgIf(b, "-protected");
    }

    public void setPackage(boolean b) {
        addArgIf(b, "-package");
    }

    public void setPrivate(boolean b) {
        addArgIf(b, "-private");
    }

    public void setAccess(AccessType at) {
        Commandline.Argument argumentCreateArgument = this.cmd.createArgument();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-");
        stringBuffer.append(at.getValue());
        argumentCreateArgument.setValue(stringBuffer.toString());
    }

    public void setDoclet(String docletName) {
        if (this.doclet == null) {
            DocletInfo docletInfo = new DocletInfo();
            this.doclet = docletInfo;
            docletInfo.setProject(getProject());
        }
        this.doclet.setName(docletName);
    }

    public void setDocletPath(Path docletPath) {
        if (this.doclet == null) {
            DocletInfo docletInfo = new DocletInfo();
            this.doclet = docletInfo;
            docletInfo.setProject(getProject());
        }
        this.doclet.setPath(docletPath);
    }

    public void setDocletPathRef(Reference r) {
        if (this.doclet == null) {
            DocletInfo docletInfo = new DocletInfo();
            this.doclet = docletInfo;
            docletInfo.setProject(getProject());
        }
        this.doclet.createPath().setRefid(r);
    }

    public DocletInfo createDoclet() {
        DocletInfo docletInfo = new DocletInfo();
        this.doclet = docletInfo;
        return docletInfo;
    }

    public void addTaglet(ExtensionInfo tagletInfo) {
        this.tags.addElement(tagletInfo);
    }

    public void setOld(boolean b) {
        this.old = b;
    }

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 == null) {
            this.classpath = path;
        } else {
            path2.append(path);
        }
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

    public void setBootclasspath(Path path) {
        Path path2 = this.bootclasspath;
        if (path2 == null) {
            this.bootclasspath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createBootclasspath() {
        if (this.bootclasspath == null) {
            this.bootclasspath = new Path(getProject());
        }
        return this.bootclasspath.createPath();
    }

    public void setBootClasspathRef(Reference r) {
        createBootclasspath().setRefid(r);
    }

    public void setExtdirs(String path) {
        this.cmd.createArgument().setValue("-extdirs");
        this.cmd.createArgument().setValue(path);
    }

    public void setExtdirs(Path path) {
        this.cmd.createArgument().setValue("-extdirs");
        this.cmd.createArgument().setPath(path);
    }

    public void setVerbose(boolean b) {
        addArgIf(b, "-verbose");
    }

    public void setLocale(String locale) {
        this.cmd.createArgument(true).setValue(locale);
        this.cmd.createArgument(true).setValue("-locale");
    }

    public void setEncoding(String enc) {
        this.cmd.createArgument().setValue("-encoding");
        this.cmd.createArgument().setValue(enc);
    }

    public void setVersion(boolean b) {
        this.version = b;
    }

    public void setUse(boolean b) {
        addArgIf(b, "-use");
    }

    public void setAuthor(boolean b) {
        this.author = b;
    }

    public void setSplitindex(boolean b) {
        addArgIf(b, "-splitindex");
    }

    public void setWindowtitle(String title) {
        addArgIfNotEmpty("-windowtitle", title);
    }

    public void setDoctitle(String doctitle) {
        Html h = new Html();
        h.addText(doctitle);
        addDoctitle(h);
    }

    public void addDoctitle(Html text) {
        this.doctitle = text;
    }

    public void setHeader(String header) {
        Html h = new Html();
        h.addText(header);
        addHeader(h);
    }

    public void addHeader(Html text) {
        this.header = text;
    }

    public void setFooter(String footer) {
        Html h = new Html();
        h.addText(footer);
        addFooter(h);
    }

    public void addFooter(Html text) {
        this.footer = text;
    }

    public void setBottom(String bottom) {
        Html h = new Html();
        h.addText(bottom);
        addBottom(h);
    }

    public void addBottom(Html text) {
        this.bottom = text;
    }

    public void setLinkoffline(String src) {
        LinkArgument le = createLink();
        le.setOffline(true);
        if (src.trim().length() == 0) {
            throw new BuildException("The linkoffline attribute must include a URL and a package-list file location separated by a space");
        }
        StringTokenizer tok = new StringTokenizer(src, " ", false);
        le.setHref(tok.nextToken());
        if (!tok.hasMoreTokens()) {
            throw new BuildException("The linkoffline attribute must include a URL and a package-list file location separated by a space");
        }
        le.setPackagelistLoc(getProject().resolveFile(tok.nextToken()));
    }

    public void setGroup(String src) {
        this.group = src;
    }

    public void setLink(String src) {
        createLink().setHref(src);
    }

    public void setNodeprecated(boolean b) {
        addArgIf(b, "-nodeprecated");
    }

    public void setNodeprecatedlist(boolean b) {
        addArgIf(b, "-nodeprecatedlist");
    }

    public void setNotree(boolean b) {
        addArgIf(b, "-notree");
    }

    public void setNoindex(boolean b) {
        addArgIf(b, "-noindex");
    }

    public void setNohelp(boolean b) {
        addArgIf(b, "-nohelp");
    }

    public void setNonavbar(boolean b) {
        addArgIf(b, "-nonavbar");
    }

    public void setSerialwarn(boolean b) {
        addArgIf(b, "-serialwarn");
    }

    public void setStylesheetfile(File f) {
        this.cmd.createArgument().setValue("-stylesheetfile");
        this.cmd.createArgument().setFile(f);
    }

    public void setHelpfile(File f) {
        this.cmd.createArgument().setValue("-helpfile");
        this.cmd.createArgument().setFile(f);
    }

    public void setDocencoding(String enc) {
        this.cmd.createArgument().setValue("-docencoding");
        this.cmd.createArgument().setValue(enc);
    }

    public void setPackageList(String src) {
        this.packageList = src;
    }

    public LinkArgument createLink() {
        LinkArgument la = new LinkArgument();
        this.links.addElement(la);
        return la;
    }

    public class LinkArgument {
        private String href;
        private File packagelistLoc;
        private boolean offline = false;
        private boolean resolveLink = false;

        public LinkArgument() {
        }

        public void setHref(String hr) {
            this.href = hr;
        }

        public String getHref() {
            return this.href;
        }

        public void setPackagelistLoc(File src) {
            this.packagelistLoc = src;
        }

        public File getPackagelistLoc() {
            return this.packagelistLoc;
        }

        public void setOffline(boolean offline) {
            this.offline = offline;
        }

        public boolean isLinkOffline() {
            return this.offline;
        }

        public void setResolveLink(boolean resolve) {
            this.resolveLink = resolve;
        }

        public boolean shouldResolveLink() {
            return this.resolveLink;
        }
    }

    public TagArgument createTag() {
        TagArgument ta = new TagArgument();
        this.tags.addElement(ta);
        return ta;
    }

    public class TagArgument extends FileSet {
        private String name = null;
        private String description = null;
        private boolean enabled = true;
        private String scope = "a";

        public TagArgument() {
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override // org.apache.tools.ant.types.DataType
        public void setDescription(String description) {
            this.description = description;
        }

        public void setScope(String verboseScope) throws BuildException {
            String verboseScope2 = verboseScope.toLowerCase(Locale.US);
            boolean[] elements = new boolean[Javadoc.SCOPE_ELEMENTS.length];
            boolean gotAll = false;
            boolean gotNotAll = false;
            StringTokenizer tok = new StringTokenizer(verboseScope2, ",");
            while (tok.hasMoreTokens()) {
                String next = tok.nextToken().trim();
                if (next.equals("all")) {
                    if (gotAll) {
                        getProject().log("Repeated tag scope element: all", 3);
                    }
                    gotAll = true;
                } else {
                    int i = 0;
                    while (i < Javadoc.SCOPE_ELEMENTS.length && !next.equals(Javadoc.SCOPE_ELEMENTS[i])) {
                        i++;
                    }
                    if (i == Javadoc.SCOPE_ELEMENTS.length) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unrecognised scope element: ");
                        stringBuffer.append(next);
                        throw new BuildException(stringBuffer.toString());
                    }
                    if (elements[i]) {
                        Project project = getProject();
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Repeated tag scope element: ");
                        stringBuffer2.append(next);
                        project.log(stringBuffer2.toString(), 3);
                    }
                    elements[i] = true;
                    gotNotAll = true;
                }
            }
            if (gotNotAll && gotAll) {
                throw new BuildException("Mixture of \"all\" and other scope elements in tag parameter.");
            }
            if (!gotNotAll && !gotAll) {
                throw new BuildException("No scope elements specified in tag parameter.");
            }
            if (gotAll) {
                this.scope = "a";
                return;
            }
            StringBuffer buff = new StringBuffer(elements.length);
            for (int i2 = 0; i2 < elements.length; i2++) {
                if (elements[i2]) {
                    buff.append(Javadoc.SCOPE_ELEMENTS[i2].charAt(0));
                }
            }
            this.scope = buff.toString();
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getParameter() throws BuildException {
            String str = this.name;
            if (str != null) {
                if (!str.equals("")) {
                    if (this.description != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(this.name);
                        stringBuffer.append(":");
                        stringBuffer.append(this.enabled ? "" : "X");
                        stringBuffer.append(this.scope);
                        stringBuffer.append(":");
                        stringBuffer.append(this.description);
                        return stringBuffer.toString();
                    }
                    return this.name;
                }
            }
            throw new BuildException("No name specified for custom tag.");
        }
    }

    public GroupArgument createGroup() {
        GroupArgument ga = new GroupArgument();
        this.groups.addElement(ga);
        return ga;
    }

    public class GroupArgument {
        private Vector packages = new Vector();
        private Html title;

        public GroupArgument() {
        }

        public void setTitle(String src) {
            Html h = new Html();
            h.addText(src);
            addTitle(h);
        }

        public void addTitle(Html text) {
            this.title = text;
        }

        public String getTitle() {
            Html html = this.title;
            if (html != null) {
                return html.getText();
            }
            return null;
        }

        public void setPackages(String src) {
            StringTokenizer tok = new StringTokenizer(src, ",");
            while (tok.hasMoreTokens()) {
                String p = tok.nextToken();
                PackageName pn = new PackageName();
                pn.setName(p);
                addPackage(pn);
            }
        }

        public void addPackage(PackageName pn) {
            this.packages.addElement(pn);
        }

        public String getPackages() {
            StringBuffer p = new StringBuffer();
            for (int i = 0; i < this.packages.size(); i++) {
                if (i > 0) {
                    p.append(":");
                }
                p.append(this.packages.elementAt(i).toString());
            }
            return p.toString();
        }
    }

    public void setCharset(String src) {
        addArgIfNotEmpty("-charset", src);
    }

    public void setFailonerror(boolean b) {
        this.failOnError = b;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public void addPackageset(DirSet packageSet) {
        this.packageSets.addElement(packageSet);
    }

    public void addFileset(FileSet fs) {
        this.fileSets.addElement(fs);
    }

    public void setLinksource(boolean b) {
        this.linksource = b;
    }

    public void setBreakiterator(boolean b) {
        this.breakiterator = b;
    }

    public void setNoqualifier(String noqualifier) {
        this.noqualifier = noqualifier;
    }

    public void setIncludeNoSourcePackages(boolean b) {
        this.includeNoSourcePackages = b;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x06ab  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x02c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0384 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:370:0x02fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x02f6 A[SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1941
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Javadoc.execute():void");
    }

    private void addFileSets(Vector sf) {
        Enumeration e = this.fileSets.elements();
        while (e.hasMoreElements()) {
            FileSet fs = (FileSet) e.nextElement();
            if (!fs.hasPatterns() && !fs.hasSelectors()) {
                fs = (FileSet) fs.clone();
                fs.createInclude().setName("**/*.java");
                if (this.includeNoSourcePackages) {
                    fs.createInclude().setName("**/package.html");
                }
            }
            File baseDir = fs.getDir(getProject());
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            String[] files = ds.getIncludedFiles();
            for (String str : files) {
                sf.addElement(new SourceFile(new File(baseDir, str)));
            }
        }
    }

    private void parsePackages(Vector pn, Path sp) throws Throwable {
        Vector addedPackages = new Vector();
        Vector dirSets = (Vector) this.packageSets.clone();
        if (this.sourcePath != null && this.packageNames.size() > 0) {
            PatternSet ps = new PatternSet();
            Enumeration e = this.packageNames.elements();
            while (e.hasMoreElements()) {
                PackageName p = (PackageName) e.nextElement();
                String pkg = p.getName().replace('.', '/');
                if (pkg.endsWith("*")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(pkg);
                    stringBuffer.append("*");
                    pkg = stringBuffer.toString();
                }
                ps.createInclude().setName(pkg);
            }
            Enumeration e2 = this.excludePackageNames.elements();
            while (e2.hasMoreElements()) {
                PackageName p2 = (PackageName) e2.nextElement();
                String pkg2 = p2.getName().replace('.', '/');
                if (pkg2.endsWith("*")) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(pkg2);
                    stringBuffer2.append("*");
                    pkg2 = stringBuffer2.toString();
                }
                ps.createExclude().setName(pkg2);
            }
            String[] pathElements = this.sourcePath.list();
            for (String str : pathElements) {
                DirSet ds = new DirSet();
                ds.setDefaultexcludes(this.useDefaultExcludes);
                ds.setDir(new File(str));
                ds.createPatternSet().addConfiguredPatternset(ps);
                dirSets.addElement(ds);
            }
        }
        Enumeration e3 = dirSets.elements();
        while (e3.hasMoreElements()) {
            DirSet ds2 = (DirSet) e3.nextElement();
            File baseDir = ds2.getDir(getProject());
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("scanning ");
            stringBuffer3.append(baseDir);
            stringBuffer3.append(" for packages.");
            log(stringBuffer3.toString(), 4);
            DirectoryScanner dsc = ds2.getDirectoryScanner(getProject());
            String[] dirs = dsc.getIncludedDirectories();
            boolean containsPackages = false;
            for (int i = 0; i < dirs.length; i++) {
                File pd = new File(baseDir, dirs[i]);
                String[] files = pd.list(new FilenameFilter() { // from class: org.apache.tools.ant.taskdefs.Javadoc.1
                    @Override // java.io.FilenameFilter
                    public boolean accept(File dir1, String name) {
                        return name.endsWith(".java") || (Javadoc.this.includeNoSourcePackages && name.equals("package.html"));
                    }
                });
                if (files.length > 0) {
                    containsPackages = true;
                    String packageName = dirs[i].replace(File.separatorChar, '.');
                    if (!addedPackages.contains(packageName)) {
                        addedPackages.addElement(packageName);
                        pn.addElement(packageName);
                    }
                }
            }
            if (containsPackages) {
                sp.createPathElement().setLocation(baseDir);
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(baseDir);
                stringBuffer4.append(" doesn't contain any packages, dropping it.");
                log(stringBuffer4.toString(), 3);
            }
        }
    }

    private class JavadocOutputStream extends LogOutputStream {
        private String queuedLine;
        private final /* synthetic */ Javadoc this$0;

        JavadocOutputStream(Javadoc this$0, int level) {
            super((Task) this$0, level);
            this.this$0 = this$0;
            this.queuedLine = null;
        }

        @Override // org.apache.tools.ant.taskdefs.LogOutputStream
        protected void processLine(String line, int messageLevel) {
            if (messageLevel == 2 && line.startsWith("Generating ")) {
                String str = this.queuedLine;
                if (str != null) {
                    super.processLine(str, 3);
                }
                this.queuedLine = line;
                return;
            }
            if (this.queuedLine != null) {
                if (line.startsWith("Building ")) {
                    super.processLine(this.queuedLine, 3);
                } else {
                    super.processLine(this.queuedLine, 2);
                }
                this.queuedLine = null;
            }
            super.processLine(line, messageLevel);
        }

        protected void logFlush() {
            String str = this.queuedLine;
            if (str != null) {
                super.processLine(str, 3);
                this.queuedLine = null;
            }
        }
    }

    protected String expand(String content) {
        return getProject().replaceProperties(content);
    }
}
