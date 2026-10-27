package org.apache.tools.ant.types;

import java.io.File;
import java.util.Enumeration;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class PatternSet extends DataType implements Cloneable {
    private Vector includeList = new Vector();
    private Vector excludeList = new Vector();
    private Vector includesFileList = new Vector();
    private Vector excludesFileList = new Vector();

    public class NameEntry {
        private String ifCond;
        private String name;
        private String unlessCond;

        public NameEntry() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIf(String cond) {
            this.ifCond = cond;
        }

        public void setUnless(String cond) {
            this.unlessCond = cond;
        }

        public String getName() {
            return this.name;
        }

        public String evalName(Project p) {
            if (valid(p)) {
                return this.name;
            }
            return null;
        }

        private boolean valid(Project p) {
            String str = this.ifCond;
            if (str != null && p.getProperty(str) == null) {
                return false;
            }
            String str2 = this.unlessCond;
            return str2 == null || p.getProperty(str2) == null;
        }

        public String toString() {
            String str = this.name;
            if (str == null) {
                throw new BuildException("Missing attribute \"name\" for a pattern");
            }
            StringBuffer buf = new StringBuffer(str);
            if (this.ifCond != null || this.unlessCond != null) {
                buf.append(":");
                String connector = "";
                if (this.ifCond != null) {
                    buf.append("if->");
                    buf.append(this.ifCond);
                    connector = ";";
                }
                if (this.unlessCond != null) {
                    buf.append(connector);
                    buf.append("unless->");
                    buf.append(this.unlessCond);
                }
            }
            String connector2 = buf.toString();
            return connector2;
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (!this.includeList.isEmpty() || !this.excludeList.isEmpty()) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    public void addConfiguredPatternset(PatternSet p) throws Throwable {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        String[] nestedIncludes = p.getIncludePatterns(getProject());
        String[] nestedExcludes = p.getExcludePatterns(getProject());
        if (nestedIncludes != null) {
            for (String str : nestedIncludes) {
                createInclude().setName(str);
            }
        }
        if (nestedExcludes != null) {
            for (String str2 : nestedExcludes) {
                createExclude().setName(str2);
            }
        }
    }

    public NameEntry createInclude() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return addPatternToList(this.includeList);
    }

    public NameEntry createIncludesFile() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return addPatternToList(this.includesFileList);
    }

    public NameEntry createExclude() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return addPatternToList(this.excludeList);
    }

    public NameEntry createExcludesFile() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return addPatternToList(this.excludesFileList);
    }

    public void setIncludes(String includes) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (includes != null && includes.length() > 0) {
            StringTokenizer tok = new StringTokenizer(includes, ", ", false);
            while (tok.hasMoreTokens()) {
                createInclude().setName(tok.nextToken());
            }
        }
    }

    public void setExcludes(String excludes) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (excludes != null && excludes.length() > 0) {
            StringTokenizer tok = new StringTokenizer(excludes, ", ", false);
            while (tok.hasMoreTokens()) {
                createExclude().setName(tok.nextToken());
            }
        }
    }

    private NameEntry addPatternToList(Vector list) {
        NameEntry result = new NameEntry();
        list.addElement(result);
        return result;
    }

    public void setIncludesfile(File includesFile) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createIncludesFile().setName(includesFile.getAbsolutePath());
    }

    public void setExcludesfile(File excludesFile) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createExcludesFile().setName(excludesFile.getAbsolutePath());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readPatterns(java.io.File r5, java.util.Vector r6, org.apache.tools.ant.Project r7) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r0 = r2
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
        L12:
            if (r1 != 0) goto L1f
        L16:
            r0.close()     // Catch: java.io.IOException -> L1a
            goto L1c
        L1a:
            r2 = move-exception
        L1c:
            return
        L1f:
            int r2 = r1.length()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            if (r2 <= 0) goto L31
            java.lang.String r2 = r7.replaceProperties(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r1 = r2
            org.apache.tools.ant.types.PatternSet$NameEntry r2 = r4.addPatternToList(r6)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r2.setName(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
        L31:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39
            r1 = r2
            goto L12
        L37:
            r2 = move-exception
            goto L52
        L39:
            r1 = move-exception
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L51
            r2.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r3 = "An error occurred while reading from pattern file: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L51
            r2.append(r5)     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L51
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L51
            r3.<init>(r2, r1)     // Catch: java.lang.Throwable -> L51
            throw r3     // Catch: java.lang.Throwable -> L51
        L51:
            r2 = move-exception
        L52:
            if (r0 == 0) goto L59
            r0.close()     // Catch: java.io.IOException -> L58
            goto L59
        L58:
            r3 = move-exception
        L59:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.types.PatternSet.readPatterns(java.io.File, java.util.Vector, org.apache.tools.ant.Project):void");
    }

    public void append(PatternSet other, Project p) {
        if (isReference()) {
            throw new BuildException("Cannot append to a reference");
        }
        String[] incl = other.getIncludePatterns(p);
        if (incl != null) {
            for (String str : incl) {
                createInclude().setName(str);
            }
        }
        String[] excl = other.getExcludePatterns(p);
        if (excl != null) {
            for (String str2 : excl) {
                createExclude().setName(str2);
            }
        }
    }

    public String[] getIncludePatterns(Project p) throws Throwable {
        if (isReference()) {
            return getRef(p).getIncludePatterns(p);
        }
        readFiles(p);
        return makeArray(this.includeList, p);
    }

    public String[] getExcludePatterns(Project p) throws Throwable {
        if (isReference()) {
            return getRef(p).getExcludePatterns(p);
        }
        readFiles(p);
        return makeArray(this.excludeList, p);
    }

    boolean hasPatterns(Project p) {
        if (isReference()) {
            return getRef(p).hasPatterns(p);
        }
        return this.includesFileList.size() > 0 || this.excludesFileList.size() > 0 || this.includeList.size() > 0 || this.excludeList.size() > 0;
    }

    private PatternSet getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (!(o instanceof PatternSet)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a patternset");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return (PatternSet) o;
    }

    private String[] makeArray(Vector list, Project p) {
        if (list.size() == 0) {
            return null;
        }
        Vector tmpNames = new Vector();
        Enumeration e = list.elements();
        while (e.hasMoreElements()) {
            NameEntry ne = (NameEntry) e.nextElement();
            String pattern = ne.evalName(p);
            if (pattern != null && pattern.length() > 0) {
                tmpNames.addElement(pattern);
            }
        }
        String[] result = new String[tmpNames.size()];
        tmpNames.copyInto(result);
        return result;
    }

    private void readFiles(Project p) throws Throwable {
        if (this.includesFileList.size() > 0) {
            Enumeration e = this.includesFileList.elements();
            while (e.hasMoreElements()) {
                NameEntry ne = (NameEntry) e.nextElement();
                String fileName = ne.evalName(p);
                if (fileName != null) {
                    File inclFile = p.resolveFile(fileName);
                    if (!inclFile.exists()) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Includesfile ");
                        stringBuffer.append(inclFile.getAbsolutePath());
                        stringBuffer.append(" not found.");
                        throw new BuildException(stringBuffer.toString());
                    }
                    readPatterns(inclFile, this.includeList, p);
                }
            }
            this.includesFileList.removeAllElements();
        }
        if (this.excludesFileList.size() > 0) {
            Enumeration e2 = this.excludesFileList.elements();
            while (e2.hasMoreElements()) {
                NameEntry ne2 = (NameEntry) e2.nextElement();
                String fileName2 = ne2.evalName(p);
                if (fileName2 != null) {
                    File exclFile = p.resolveFile(fileName2);
                    if (!exclFile.exists()) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Excludesfile ");
                        stringBuffer2.append(exclFile.getAbsolutePath());
                        stringBuffer2.append(" not found.");
                        throw new BuildException(stringBuffer2.toString());
                    }
                    readPatterns(exclFile, this.excludeList, p);
                }
            }
            this.excludesFileList.removeAllElements();
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("patternSet{ includes: ");
        stringBuffer.append(this.includeList);
        stringBuffer.append(" excludes: ");
        stringBuffer.append(this.excludeList);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    public Object clone() {
        if (isReference()) {
            return getRef(getProject()).clone();
        }
        try {
            PatternSet ps = (PatternSet) super.clone();
            ps.includeList = (Vector) this.includeList.clone();
            ps.excludeList = (Vector) this.excludeList.clone();
            ps.includesFileList = (Vector) this.includesFileList.clone();
            ps.excludesFileList = (Vector) this.excludesFileList.clone();
            return ps;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }
}
