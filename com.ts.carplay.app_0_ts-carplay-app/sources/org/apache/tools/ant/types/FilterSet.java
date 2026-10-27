package org.apache.tools.ant.types;

import java.io.File;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class FilterSet extends DataType implements Cloneable {
    public static final String DEFAULT_TOKEN_END = "@";
    public static final String DEFAULT_TOKEN_START = "@";
    static /* synthetic */ Class class$org$apache$tools$ant$types$FilterSet;
    private boolean duplicateToken;
    private String endOfToken;
    private Hashtable filterHash;
    private Vector filters;
    private Vector passedTokens;
    private boolean recurse;
    private String startOfToken;

    public static class Filter {
        String token;
        String value;

        public Filter(String token, String value) {
            setToken(token);
            setValue(value);
        }

        public Filter() {
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getToken() {
            return this.token;
        }

        public String getValue() {
            return this.value;
        }
    }

    public class FiltersFile {
        public FiltersFile() {
        }

        public void setFile(File file) {
            FilterSet.this.readFiltersFromFile(file);
        }
    }

    public FilterSet() {
        this.startOfToken = "@";
        this.endOfToken = "@";
        this.duplicateToken = false;
        this.recurse = true;
        this.filterHash = null;
        this.filters = new Vector();
    }

    protected FilterSet(FilterSet filterset) {
        this.startOfToken = "@";
        this.endOfToken = "@";
        this.duplicateToken = false;
        this.recurse = true;
        this.filterHash = null;
        this.filters = new Vector();
        this.filters = (Vector) filterset.getFilters().clone();
    }

    protected synchronized Vector getFilters() {
        if (isReference()) {
            return getRef().getFilters();
        }
        return this.filters;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected FilterSet getRef() {
        Class clsClass$ = class$org$apache$tools$ant$types$FilterSet;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.types.FilterSet");
            class$org$apache$tools$ant$types$FilterSet = clsClass$;
        }
        return (FilterSet) getCheckedRef(clsClass$, "filterset");
    }

    public synchronized Hashtable getFilterHash() {
        if (this.filterHash == null) {
            this.filterHash = new Hashtable(getFilters().size());
            Enumeration e = getFilters().elements();
            while (e.hasMoreElements()) {
                Filter filter = (Filter) e.nextElement();
                this.filterHash.put(filter.getToken(), filter.getValue());
            }
        }
        return this.filterHash;
    }

    public void setFiltersfile(File filtersFile) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        readFiltersFromFile(filtersFile);
    }

    public void setBeginToken(String startOfToken) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (startOfToken == null || "".equals(startOfToken)) {
            throw new BuildException("beginToken must not be empty");
        }
        this.startOfToken = startOfToken;
    }

    public String getBeginToken() {
        if (isReference()) {
            return getRef().getBeginToken();
        }
        return this.startOfToken;
    }

    public void setEndToken(String endOfToken) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (endOfToken == null || "".equals(endOfToken)) {
            throw new BuildException("endToken must not be empty");
        }
        this.endOfToken = endOfToken;
    }

    public String getEndToken() {
        if (isReference()) {
            return getRef().getEndToken();
        }
        return this.endOfToken;
    }

    public void setRecurse(boolean recurse) {
        this.recurse = recurse;
    }

    public boolean isRecurse() {
        return this.recurse;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void readFiltersFromFile(java.io.File r8) throws org.apache.tools.ant.BuildException {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isReference()     // Catch: java.lang.Throwable -> Lc4
            if (r0 != 0) goto Lbf
            boolean r0 = r8.exists()     // Catch: java.lang.Throwable -> Lc4
            if (r0 == 0) goto La3
            boolean r0 = r8.isFile()     // Catch: java.lang.Throwable -> Lc4
            if (r0 == 0) goto L8c
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> Lc4
            r0.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r1 = "Reading filters from "
            r0.append(r1)     // Catch: java.lang.Throwable -> Lc4
            r0.append(r8)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lc4
            r1 = 3
            r7.log(r0, r1)     // Catch: java.lang.Throwable -> Lc4
            r0 = 0
            r1 = 0
            java.util.Properties r2 = new java.util.Properties     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r1 = r2
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r0 = r2
            r1.load(r0)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.util.Enumeration r2 = r1.propertyNames()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.util.Vector r3 = r7.getFilters()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
        L42:
            boolean r4 = r2.hasMoreElements()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            if (r4 != 0) goto L55
        L4a:
            r0.close()     // Catch: java.io.IOException -> L4e java.lang.Throwable -> Lc4
            goto L50
        L4e:
            r2 = move-exception
        L50:
            monitor-exit(r7)
            return
        L55:
            java.lang.Object r4 = r2.nextElement()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            java.lang.String r5 = r1.getProperty(r4)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            org.apache.tools.ant.types.FilterSet$Filter r6 = new org.apache.tools.ant.types.FilterSet$Filter     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r6.<init>(r4, r5)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            r3.addElement(r6)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6a
            goto L42
        L68:
            r2 = move-exception
            goto L83
        L6a:
            r1 = move-exception
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L82
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L82
            r3.<init>()     // Catch: java.lang.Throwable -> L82
            java.lang.String r4 = "Could not read filters from file: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L82
            r3.append(r8)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L82
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L82
            throw r2     // Catch: java.lang.Throwable -> L82
        L82:
            r2 = move-exception
        L83:
            if (r0 == 0) goto L8a
            r0.close()     // Catch: java.io.IOException -> L89 java.lang.Throwable -> Lc4
            goto L8a
        L89:
            r3 = move-exception
        L8a:
            throw r2     // Catch: java.lang.Throwable -> Lc4
        L8c:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> Lc4
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> Lc4
            r1.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r2 = "Must specify a file not a directory in the filtersfile attribute:"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc4
            r1.append(r8)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc4
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lc4
            throw r0     // Catch: java.lang.Throwable -> Lc4
        La3:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> Lc4
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> Lc4
            r1.<init>()     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r2 = "Could not read filters from file "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc4
            r1.append(r8)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r2 = " as it doesn't exist."
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc4
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc4
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lc4
            throw r0     // Catch: java.lang.Throwable -> Lc4
        Lbf:
            org.apache.tools.ant.BuildException r0 = r7.tooManyAttributes()     // Catch: java.lang.Throwable -> Lc4
            throw r0     // Catch: java.lang.Throwable -> Lc4
        Lc4:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.types.FilterSet.readFiltersFromFile(java.io.File):void");
    }

    public synchronized String replaceTokens(String line) {
        this.passedTokens = null;
        return iReplaceTokens(line);
    }

    private synchronized String iReplaceTokens(String line) {
        int endIndex;
        String beginToken = getBeginToken();
        String endToken = getEndToken();
        int index = line.indexOf(beginToken);
        if (index <= -1) {
            return line;
        }
        Hashtable tokens = getFilterHash();
        try {
            StringBuffer b = new StringBuffer();
            int i = 0;
            do {
                int endIndex2 = line.indexOf(endToken, beginToken.length() + index + 1);
                if (endIndex2 == -1) {
                    break;
                }
                String token = line.substring(beginToken.length() + index, endIndex2);
                b.append(line.substring(i, index));
                if (tokens.containsKey(token)) {
                    String value = (String) tokens.get(token);
                    if (this.recurse && !value.equals(token)) {
                        value = replaceTokens(value, token);
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Replacing: ");
                    stringBuffer.append(beginToken);
                    stringBuffer.append(token);
                    stringBuffer.append(endToken);
                    stringBuffer.append(" -> ");
                    stringBuffer.append(value);
                    log(stringBuffer.toString(), 3);
                    b.append(value);
                    i = beginToken.length() + index + token.length() + endToken.length();
                } else {
                    b.append(beginToken);
                    int i2 = beginToken.length() + index;
                    i = i2;
                }
                endIndex = line.indexOf(beginToken, i);
                index = endIndex;
            } while (endIndex > -1);
            b.append(line.substring(i));
            return b.toString();
        } catch (StringIndexOutOfBoundsException e) {
            return line;
        }
    }

    private synchronized String replaceTokens(String line, String parent) throws BuildException {
        String beginToken = getBeginToken();
        String endToken = getEndToken();
        if (this.passedTokens == null) {
            this.passedTokens = new Vector();
        }
        if (this.passedTokens.contains(parent) && !this.duplicateToken) {
            this.duplicateToken = true;
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Infinite loop in tokens. Currently known tokens : ");
            stringBuffer.append(this.passedTokens.toString());
            stringBuffer.append("\nProblem token : ");
            stringBuffer.append(beginToken);
            stringBuffer.append(parent);
            stringBuffer.append(endToken);
            stringBuffer.append(" called from ");
            stringBuffer.append(beginToken);
            stringBuffer.append(this.passedTokens.lastElement().toString());
            stringBuffer.append(endToken);
            printStream.println(stringBuffer.toString());
            return parent;
        }
        this.passedTokens.addElement(parent);
        String value = iReplaceTokens(line);
        if (value.indexOf(beginToken) == -1 && !this.duplicateToken) {
            this.duplicateToken = false;
            this.passedTokens = null;
        } else if (this.duplicateToken && this.passedTokens.size() > 0) {
            value = (String) this.passedTokens.remove(this.passedTokens.size() - 1);
            if (this.passedTokens.size() == 0) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(beginToken);
                stringBuffer2.append(value);
                stringBuffer2.append(endToken);
                value = stringBuffer2.toString();
                this.duplicateToken = false;
            }
        }
        return value;
    }

    public synchronized void addFilter(Filter filter) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        this.filters.addElement(filter);
        this.filterHash = null;
    }

    public FiltersFile createFiltersfile() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        return new FiltersFile();
    }

    public synchronized void addFilter(String token, String value) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        addFilter(new Filter(token, value));
    }

    public synchronized void addConfiguredFilterSet(FilterSet filterSet) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        Enumeration e = filterSet.getFilters().elements();
        while (e.hasMoreElements()) {
            addFilter((Filter) e.nextElement());
        }
    }

    public synchronized boolean hasFilters() {
        return getFilters().size() > 0;
    }

    public synchronized Object clone() throws BuildException {
        if (isReference()) {
            return getRef().clone();
        }
        try {
            FilterSet fs = (FilterSet) super.clone();
            fs.filters = (Vector) getFilters().clone();
            fs.setProject(getProject());
            return fs;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }
}
