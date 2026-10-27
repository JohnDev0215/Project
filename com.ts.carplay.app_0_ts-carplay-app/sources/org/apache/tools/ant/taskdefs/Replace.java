package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Replace extends MatchingTask {
    private int fileCount;
    private int replaceCount;
    private File src = null;
    private NestedString token = null;
    private NestedString value = new NestedString();
    private File propertyFile = null;
    private File replaceFilterFile = null;
    private Properties properties = null;
    private Vector replacefilters = new Vector();
    private File dir = null;
    private boolean summary = false;
    private String encoding = null;
    private FileUtils fileUtils = FileUtils.newFileUtils();

    public class NestedString {
        private StringBuffer buf = new StringBuffer();

        public NestedString() {
        }

        public void addText(String val) {
            this.buf.append(val);
        }

        public String getText() {
            return this.buf.substring(0);
        }
    }

    public class Replacefilter {
        private String property;
        private String token;
        private String value;

        public Replacefilter() {
        }

        public void validate() throws BuildException {
            String str = this.token;
            if (str == null) {
                throw new BuildException("token is a mandatory attribute of replacefilter.");
            }
            if ("".equals(str)) {
                throw new BuildException("The token attribute must not be an empty string.");
            }
            if (this.value != null && this.property != null) {
                throw new BuildException("Either value or property can be specified, but a replacefilter element cannot have both.");
            }
            String message = this.property;
            if (message != null) {
                if (Replace.this.propertyFile != null) {
                    if (Replace.this.properties == null || Replace.this.properties.getProperty(this.property) == null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("property \"");
                        stringBuffer.append(this.property);
                        stringBuffer.append("\" was not found in ");
                        stringBuffer.append(Replace.this.propertyFile.getPath());
                        String message2 = stringBuffer.toString();
                        throw new BuildException(message2);
                    }
                    return;
                }
                throw new BuildException("The replacefilter's property attribute can only be used with the replacetask's propertyFile attribute.");
            }
        }

        public String getReplaceValue() {
            if (this.property != null) {
                return Replace.this.properties.getProperty(this.property);
            }
            String str = this.value;
            if (str == null) {
                if (Replace.this.value != null) {
                    return Replace.this.value.getText();
                }
                return new String("");
            }
            return str;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getProperty() {
            return this.property;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Vector savedFilters = (Vector) this.replacefilters.clone();
        Properties properties = this.properties;
        Properties savedProperties = properties == null ? null : (Properties) properties.clone();
        try {
            if (this.replaceFilterFile != null) {
                Properties props = getProperties(this.replaceFilterFile);
                Enumeration e = props.keys();
                while (e.hasMoreElements()) {
                    String token = e.nextElement().toString();
                    Replacefilter replaceFilter = createReplacefilter();
                    replaceFilter.setToken(token);
                    replaceFilter.setValue(props.getProperty(token));
                }
            }
            validateAttributes();
            if (this.propertyFile != null) {
                this.properties = getProperties(this.propertyFile);
            }
            validateReplacefilters();
            this.fileCount = 0;
            this.replaceCount = 0;
            if (this.src != null) {
                processFile(this.src);
            }
            if (this.dir != null) {
                DirectoryScanner ds = super.getDirectoryScanner(this.dir);
                String[] srcs = ds.getIncludedFiles();
                for (String str : srcs) {
                    File file = new File(this.dir, str);
                    processFile(file);
                }
            }
            if (this.summary) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Replaced ");
                stringBuffer.append(this.replaceCount);
                stringBuffer.append(" occurrences in ");
                stringBuffer.append(this.fileCount);
                stringBuffer.append(" files.");
                log(stringBuffer.toString(), 2);
            }
        } finally {
            this.replacefilters = savedFilters;
            this.properties = savedProperties;
        }
    }

    public void validateAttributes() throws BuildException {
        if (this.src == null && this.dir == null) {
            throw new BuildException("Either the file or the dir attribute must be specified", getLocation());
        }
        File file = this.propertyFile;
        if (file != null && !file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Property file ");
            stringBuffer.append(this.propertyFile.getPath());
            stringBuffer.append(" does not exist.");
            String message = stringBuffer.toString();
            throw new BuildException(message, getLocation());
        }
        if (this.token == null && this.replacefilters.size() == 0) {
            throw new BuildException("Either token or a nested replacefilter must be specified", getLocation());
        }
        NestedString nestedString = this.token;
        if (nestedString != null && "".equals(nestedString.getText())) {
            throw new BuildException("The token attribute must not be an empty string.", getLocation());
        }
    }

    public void validateReplacefilters() throws BuildException {
        for (int i = 0; i < this.replacefilters.size(); i++) {
            Replacefilter element = (Replacefilter) this.replacefilters.elementAt(i);
            element.validate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Properties getProperties(java.io.File r8) throws java.io.IOException, org.apache.tools.ant.BuildException {
        /*
            r7 = this;
            java.lang.String r0 = "Property file ("
            java.util.Properties r1 = new java.util.Properties
            r1.<init>()
            r2 = 0
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L20 java.io.FileNotFoundException -> L44
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L20 java.io.FileNotFoundException -> L44
            r2 = r4
            r1.load(r2)     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L20 java.io.FileNotFoundException -> L44
            r0 = r3
            r2.close()     // Catch: java.io.IOException -> L18
            goto L1a
        L18:
            r4 = move-exception
        L1a:
            return r1
        L1d:
            r0 = move-exception
            r4 = r3
        L1f:
            goto L6a
        L20:
            r4 = move-exception
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L3f
            r5.<init>()     // Catch: java.lang.Throwable -> L3f
            r5.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = r8.getPath()     // Catch: java.lang.Throwable -> L3f
            r5.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = ") cannot be loaded."
            r5.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L3f
            org.apache.tools.ant.BuildException r5 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L3f
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: java.lang.Throwable -> L3f
        L3f:
            r0 = move-exception
            r6 = r4
            r4 = r3
            r3 = r6
            goto L6a
        L44:
            r4 = move-exception
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L68
            r5.<init>()     // Catch: java.lang.Throwable -> L68
            r5.append(r0)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = r8.getPath()     // Catch: java.lang.Throwable -> L68
            r5.append(r0)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = ") not found."
            r5.append(r0)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L68
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L63
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L63
            throw r3     // Catch: java.lang.Throwable -> L63
        L63:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L6a
        L68:
            r0 = move-exception
            goto L1f
        L6a:
            if (r2 == 0) goto L71
            r2.close()     // Catch: java.io.IOException -> L70
            goto L71
        L70:
            r5 = move-exception
        L71:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Replace.getProperties(java.io.File):java.util.Properties");
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0158 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processFile(java.io.File r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Replace.processFile(java.io.File):void");
    }

    private String processReplacefilters(String buffer, String filename) {
        String newString = new String(buffer);
        for (int i = 0; i < this.replacefilters.size(); i++) {
            Replacefilter filter = (Replacefilter) this.replacefilters.elementAt(i);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Replacing in ");
            stringBuffer.append(filename);
            stringBuffer.append(": ");
            stringBuffer.append(filter.getToken());
            stringBuffer.append(" --> ");
            stringBuffer.append(filter.getReplaceValue());
            log(stringBuffer.toString(), 3);
            newString = stringReplace(newString, filter.getToken(), filter.getReplaceValue(), true);
        }
        return newString;
    }

    public void setFile(File file) {
        this.src = file;
    }

    public void setSummary(boolean summary) {
        this.summary = summary;
    }

    public void setReplaceFilterFile(File filename) {
        this.replaceFilterFile = filename;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void setToken(String token) {
        createReplaceToken().addText(token);
    }

    public void setValue(String value) {
        createReplaceValue().addText(value);
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public NestedString createReplaceToken() {
        if (this.token == null) {
            this.token = new NestedString();
        }
        return this.token;
    }

    public NestedString createReplaceValue() {
        return this.value;
    }

    public void setPropertyFile(File filename) {
        this.propertyFile = filename;
    }

    public Replacefilter createReplacefilter() {
        Replacefilter filter = new Replacefilter();
        this.replacefilters.addElement(filter);
        return filter;
    }

    private String stringReplace(String str, String str1, String str2, boolean countReplaces) {
        StringBuffer ret = new StringBuffer();
        int start = 0;
        int found = str.indexOf(str1);
        while (found >= 0) {
            if (found > start) {
                ret.append(str.substring(start, found));
            }
            if (str2 != null) {
                ret.append(str2);
            }
            start = found + str1.length();
            found = str.indexOf(str1, start);
            if (countReplaces) {
                this.replaceCount++;
            }
        }
        if (str.length() > start) {
            ret.append(str.substring(start, str.length()));
        }
        return ret.toString();
    }
}
