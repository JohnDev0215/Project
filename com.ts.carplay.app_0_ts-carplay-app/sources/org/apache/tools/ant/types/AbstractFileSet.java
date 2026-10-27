package org.apache.tools.ant.types;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.FileScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.AndSelector;
import org.apache.tools.ant.types.selectors.ContainsRegexpSelector;
import org.apache.tools.ant.types.selectors.ContainsSelector;
import org.apache.tools.ant.types.selectors.DateSelector;
import org.apache.tools.ant.types.selectors.DependSelector;
import org.apache.tools.ant.types.selectors.DepthSelector;
import org.apache.tools.ant.types.selectors.DifferentSelector;
import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.MajoritySelector;
import org.apache.tools.ant.types.selectors.NoneSelector;
import org.apache.tools.ant.types.selectors.NotSelector;
import org.apache.tools.ant.types.selectors.OrSelector;
import org.apache.tools.ant.types.selectors.PresentSelector;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.apache.tools.ant.types.selectors.SelectorContainer;
import org.apache.tools.ant.types.selectors.SelectorScanner;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractFileSet extends DataType implements Cloneable, SelectorContainer {
    private Vector additionalPatterns;
    private PatternSet defaultPatterns;
    private File dir;
    private boolean followSymlinks;
    private boolean isCaseSensitive;
    private Vector selectors;
    private boolean useDefaultExcludes;

    public AbstractFileSet() {
        this.defaultPatterns = new PatternSet();
        this.additionalPatterns = new Vector();
        this.selectors = new Vector();
        this.useDefaultExcludes = true;
        this.isCaseSensitive = true;
        this.followSymlinks = true;
    }

    protected AbstractFileSet(AbstractFileSet fileset) {
        this.defaultPatterns = new PatternSet();
        this.additionalPatterns = new Vector();
        this.selectors = new Vector();
        this.useDefaultExcludes = true;
        this.isCaseSensitive = true;
        this.followSymlinks = true;
        this.dir = fileset.dir;
        this.defaultPatterns = fileset.defaultPatterns;
        this.additionalPatterns = fileset.additionalPatterns;
        this.selectors = fileset.selectors;
        this.useDefaultExcludes = fileset.useDefaultExcludes;
        this.isCaseSensitive = fileset.isCaseSensitive;
        this.followSymlinks = fileset.followSymlinks;
        setProject(fileset.getProject());
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (this.dir != null || this.defaultPatterns.hasPatterns(getProject())) {
            throw tooManyAttributes();
        }
        if (!this.additionalPatterns.isEmpty()) {
            throw noChildrenAllowed();
        }
        if (!this.selectors.isEmpty()) {
            throw noChildrenAllowed();
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
        return isReference() ? getRef(p).getDir(p) : this.dir;
    }

    public PatternSet createPatternSet() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        PatternSet patterns = new PatternSet();
        this.additionalPatterns.addElement(patterns);
        return patterns;
    }

    public PatternSet.NameEntry createInclude() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createInclude();
    }

    public PatternSet.NameEntry createIncludesFile() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createIncludesFile();
    }

    public PatternSet.NameEntry createExclude() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createExclude();
    }

    public PatternSet.NameEntry createExcludesFile() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return this.defaultPatterns.createExcludesFile();
    }

    public void setFile(File file) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        setDir(file.getParentFile());
        createInclude().setName(file.getName());
    }

    public void setIncludes(String includes) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.defaultPatterns.setIncludes(includes);
    }

    public void setExcludes(String excludes) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.defaultPatterns.setExcludes(excludes);
    }

    public void setIncludesfile(File incl) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.defaultPatterns.setIncludesfile(incl);
    }

    public void setExcludesfile(File excl) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.defaultPatterns.setExcludesfile(excl);
    }

    public void setDefaultexcludes(boolean useDefaultExcludes) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.useDefaultExcludes = useDefaultExcludes;
    }

    public boolean getDefaultexcludes() {
        return isReference() ? getRef(getProject()).getDefaultexcludes() : this.useDefaultExcludes;
    }

    public void setCaseSensitive(boolean isCaseSensitive) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.isCaseSensitive = isCaseSensitive;
    }

    public void setFollowSymlinks(boolean followSymlinks) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.followSymlinks = followSymlinks;
    }

    public boolean isFollowSymlinks() {
        return isReference() ? getRef(getProject()).isFollowSymlinks() : this.followSymlinks;
    }

    protected String getDataTypeName() {
        Project p = getProject();
        if (p != null) {
            Hashtable typedefs = p.getDataTypeDefinitions();
            Enumeration e = typedefs.keys();
            while (e.hasMoreElements()) {
                String typeName = (String) e.nextElement();
                if (((Class) typedefs.get(typeName)) == getClass()) {
                    return typeName;
                }
            }
        }
        String classname = getClass().getName();
        return classname.substring(classname.lastIndexOf(46) + 1);
    }

    public DirectoryScanner getDirectoryScanner(Project p) {
        if (isReference()) {
            return getRef(p).getDirectoryScanner(p);
        }
        File file = this.dir;
        if (file == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No directory specified for ");
            stringBuffer.append(getDataTypeName());
            stringBuffer.append(".");
            throw new BuildException(stringBuffer.toString());
        }
        if (!file.exists()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.dir.getAbsolutePath());
            stringBuffer2.append(" not found.");
            throw new BuildException(stringBuffer2.toString());
        }
        if (!this.dir.isDirectory()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(this.dir.getAbsolutePath());
            stringBuffer3.append(" is not a directory.");
            throw new BuildException(stringBuffer3.toString());
        }
        DirectoryScanner ds = new DirectoryScanner();
        setupDirectoryScanner(ds, p);
        ds.setFollowSymlinks(this.followSymlinks);
        ds.scan();
        return ds;
    }

    public void setupDirectoryScanner(FileScanner ds, Project p) {
        if (isReference()) {
            getRef(p).setupDirectoryScanner(ds, p);
            return;
        }
        if (ds == null) {
            throw new IllegalArgumentException("ds cannot be null");
        }
        ds.setBasedir(this.dir);
        int count = this.additionalPatterns.size();
        for (int i = 0; i < count; i++) {
            Object o = this.additionalPatterns.elementAt(i);
            this.defaultPatterns.append((PatternSet) o, p);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getDataTypeName());
        stringBuffer.append(": Setup scanner in dir ");
        stringBuffer.append(this.dir);
        stringBuffer.append(" with ");
        stringBuffer.append(this.defaultPatterns);
        p.log(stringBuffer.toString(), 4);
        ds.setIncludes(this.defaultPatterns.getIncludePatterns(p));
        ds.setExcludes(this.defaultPatterns.getExcludePatterns(p));
        if (ds instanceof SelectorScanner) {
            SelectorScanner ss = (SelectorScanner) ds;
            ss.setSelectors(getSelectors(p));
        }
        if (this.useDefaultExcludes) {
            ds.addDefaultExcludes();
        }
        ds.setCaseSensitive(this.isCaseSensitive);
    }

    protected AbstractFileSet getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (!getClass().isAssignableFrom(o.getClass())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a ");
            stringBuffer.append(getDataTypeName());
            throw new BuildException(stringBuffer.toString());
        }
        return (AbstractFileSet) o;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public boolean hasSelectors() {
        return (!isReference() || getProject() == null) ? !this.selectors.isEmpty() : getRef(getProject()).hasSelectors();
    }

    public boolean hasPatterns() {
        if (isReference() && getProject() != null) {
            return getRef(getProject()).hasPatterns();
        }
        if (this.defaultPatterns.hasPatterns(getProject())) {
            return true;
        }
        Enumeration e = this.additionalPatterns.elements();
        while (e.hasMoreElements()) {
            PatternSet ps = (PatternSet) e.nextElement();
            if (ps.hasPatterns(getProject())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public int selectorCount() {
        return (!isReference() || getProject() == null) ? this.selectors.size() : getRef(getProject()).selectorCount();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public FileSelector[] getSelectors(Project p) {
        if (isReference()) {
            return getRef(p).getSelectors(p);
        }
        Vector vector = this.selectors;
        return (FileSelector[]) vector.toArray(new FileSelector[vector.size()]);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public Enumeration selectorElements() {
        return (!isReference() || getProject() == null) ? this.selectors.elements() : getRef(getProject()).selectorElements();
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void appendSelector(FileSelector selector) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.selectors.addElement(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSelector(SelectSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addAnd(AndSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addOr(OrSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNot(NotSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNone(NoneSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addMajority(MajoritySelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDate(DateSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSize(SizeSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDifferent(DifferentSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addFilename(FilenameSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addType(TypeSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addCustom(ExtendSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContains(ContainsSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addPresent(PresentSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepth(DepthSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepend(DependSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContainsRegexp(ContainsRegexpSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void addModified(ModifiedSelector selector) {
        appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorContainer
    public void add(FileSelector selector) {
        appendSelector(selector);
    }

    public String toString() {
        DirectoryScanner ds = getDirectoryScanner(getProject());
        String[] files = ds.getIncludedFiles();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.length; i++) {
            if (i > 0) {
                sb.append(';');
            }
            sb.append(files[i]);
        }
        return sb.toString();
    }

    public Object clone() {
        if (isReference()) {
            return getRef(getProject()).clone();
        }
        try {
            AbstractFileSet fs = (AbstractFileSet) super.clone();
            fs.defaultPatterns = (PatternSet) this.defaultPatterns.clone();
            fs.additionalPatterns = new Vector(this.additionalPatterns.size());
            Enumeration e = this.additionalPatterns.elements();
            while (e.hasMoreElements()) {
                fs.additionalPatterns.addElement(((PatternSet) e.nextElement()).clone());
            }
            fs.selectors = (Vector) fs.selectors.clone();
            return fs;
        } catch (CloneNotSupportedException e2) {
            throw new BuildException(e2);
        }
    }
}
