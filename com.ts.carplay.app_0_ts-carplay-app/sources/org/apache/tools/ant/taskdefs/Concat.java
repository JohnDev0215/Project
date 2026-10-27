package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.taskdefs.FixCRLF;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Concat extends Task {
    private static final int BUFFER_SIZE = 8192;
    private static FileUtils fileUtils = FileUtils.newFileUtils();
    private TextElement footer;
    private TextElement header;
    private StringBuffer textBuffer;
    private File destinationFile = null;
    private boolean append = false;
    private String encoding = null;
    private String outputEncoding = null;
    private boolean binary = false;
    private Vector sources = new Vector();
    private Vector filterChains = null;
    private boolean forceOverwrite = true;
    private boolean fixLastLine = false;
    private String eolString = System.getProperty("line.separator");
    private Writer outputWriter = null;
    private Vector sourceFiles = new Vector();

    /* JADX INFO: renamed from: org.apache.tools.ant.taskdefs.Concat$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    public void setDestfile(File destinationFile) {
        this.destinationFile = destinationFile;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
        if (this.outputEncoding == null) {
            this.outputEncoding = encoding;
        }
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public void setForce(boolean force) {
        this.forceOverwrite = force;
    }

    public Path createPath() {
        Path path = new Path(getProject());
        this.sources.addElement(path);
        return path;
    }

    public void addFileset(FileSet set) {
        this.sources.addElement(set);
    }

    public void addFilelist(FileList list) {
        this.sources.addElement(list);
    }

    public void addFilterChain(FilterChain filterChain) {
        if (this.filterChains == null) {
            this.filterChains = new Vector();
        }
        this.filterChains.addElement(filterChain);
    }

    public void addText(String text) {
        if (this.textBuffer == null) {
            this.textBuffer = new StringBuffer(text.length());
        }
        this.textBuffer.append(text);
    }

    public void addHeader(TextElement header) {
        this.header = header;
    }

    public void addFooter(TextElement footer) {
        this.footer = footer;
    }

    public void setFixLastLine(boolean fixLastLine) {
        this.fixLastLine = fixLastLine;
    }

    public void setEol(FixCRLF.CrLf crlf) {
        String s = crlf.getValue();
        if (s.equals("cr") || s.equals("mac")) {
            this.eolString = "\r";
            return;
        }
        if (s.equals("lf") || s.equals("unix")) {
            this.eolString = "\n";
        } else if (s.equals("crlf") || s.equals("dos")) {
            this.eolString = Manifest.EOL;
        }
    }

    public void setWriter(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    public void setBinary(boolean binary) {
        this.binary = binary;
    }

    private void checkAndExecute() throws Exception {
        sanitizeText();
        if (this.binary) {
            if (this.destinationFile == null) {
                throw new BuildException("DestFile attribute is required for binary concatenation");
            }
            if (this.textBuffer != null) {
                throw new BuildException("Nested text is incompatible with binary concatenation");
            }
            if (this.encoding != null || this.outputEncoding != null) {
                throw new BuildException("Seting input or output encoding is incompatible with binary concatenation");
            }
            if (this.filterChains != null) {
                throw new BuildException("Setting filters is incompatible with binary concatenation");
            }
            if (this.fixLastLine) {
                throw new BuildException("Setting fixlastline is incompatible with binary concatenation");
            }
            if (this.header != null || this.footer != null) {
                throw new BuildException("Nested header or footer is incompatible with binary concatenation");
            }
        }
        if (this.destinationFile != null && this.outputWriter != null) {
            throw new BuildException("Cannot specify both a destination file and an output writer");
        }
        if (this.sources.size() == 0 && this.textBuffer == null) {
            throw new BuildException("At least one file must be provided, or some text.");
        }
        if (this.sources.size() > 0 && this.textBuffer != null) {
            throw new BuildException("Cannot include inline text when using filesets.");
        }
        Enumeration e = this.sources.elements();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof Path) {
                Path path = (Path) o;
                checkAddFiles(null, path.list());
            } else if (o instanceof FileSet) {
                FileSet fileSet = (FileSet) o;
                DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
                checkAddFiles(fileSet.getDir(getProject()), scanner.getIncludedFiles());
            } else if (o instanceof FileList) {
                FileList fileList = (FileList) o;
                checkAddFiles(fileList.getDir(getProject()), fileList.getFiles(getProject()));
            }
        }
        if (this.destinationFile != null && !this.forceOverwrite && this.sourceFiles.size() > 0 && this.destinationFile.exists()) {
            boolean outofdate = false;
            int i = 0;
            while (true) {
                if (i >= this.sourceFiles.size()) {
                    break;
                }
                File file = (File) this.sourceFiles.elementAt(i);
                if (file.lastModified() <= this.destinationFile.lastModified()) {
                    i++;
                } else {
                    outofdate = true;
                    break;
                }
            }
            if (!outofdate) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.destinationFile);
                stringBuffer.append(" is up-to-date.");
                log(stringBuffer.toString(), 3);
                return;
            }
        }
        if (this.textBuffer == null && this.sourceFiles.size() == 0 && this.header == null && this.footer == null) {
            log("No existing files and no nested text, doing nothing", 2);
        } else if (this.binary) {
            binaryCat();
        } else {
            cat();
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        try {
            checkAndExecute();
        } finally {
            resetTask();
        }
    }

    public void reset() {
        this.append = false;
        this.forceOverwrite = true;
        this.destinationFile = null;
        this.encoding = null;
        this.outputEncoding = null;
        this.fixLastLine = false;
        this.sources.removeAllElements();
        this.sourceFiles.removeAllElements();
        this.filterChains = null;
        this.footer = null;
        this.header = null;
    }

    private void resetTask() {
        this.sourceFiles.clear();
    }

    private void checkAddFiles(File base, String[] filenames) {
        for (String str : filenames) {
            File file = new File(base, str);
            if (!file.exists()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("File ");
                stringBuffer.append(file);
                stringBuffer.append(" does not exist.");
                log(stringBuffer.toString(), 0);
            } else {
                File file2 = this.destinationFile;
                if (file2 != null && fileUtils.fileNameEquals(file2, file)) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Input file \"");
                    stringBuffer2.append(file);
                    stringBuffer2.append("\" ");
                    stringBuffer2.append("is the same as the output file.");
                    throw new BuildException(stringBuffer2.toString());
                }
                this.sourceFiles.addElement(file);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a6, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a7, code lost:
    
        r9 = new java.lang.StringBuffer();
        r9.append("Unable to close ");
        r9.append(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00bb, code lost:
    
        throw new org.apache.tools.ant.BuildException(r9.toString(), r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void binaryCat() throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Concat.binaryCat():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0112 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void cat() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Concat.cat():void");
    }

    private void concatenate(char[] buffer, Writer writer, Reader in) throws IOException {
        if (this.filterChains != null) {
            ChainReaderHelper helper = new ChainReaderHelper();
            helper.setBufferSize(8192);
            helper.setPrimaryReader(in);
            helper.setFilterChains(this.filterChains);
            helper.setProject(getProject());
            in = new BufferedReader(helper.getAssembledReader());
        }
        while (true) {
            int nRead = in.read(buffer, 0, buffer.length);
            if (nRead != -1) {
                writer.write(buffer, 0, nRead);
            } else {
                writer.flush();
                return;
            }
        }
    }

    private void sanitizeText() {
        StringBuffer stringBuffer = this.textBuffer;
        if (stringBuffer != null && stringBuffer.substring(0).trim().length() == 0) {
            this.textBuffer = null;
        }
    }

    public static class TextElement extends ProjectComponent {
        private String value = "";
        private boolean trimLeading = false;
        private boolean trim = false;
        private boolean filtering = true;
        private String encoding = null;

        public void setFiltering(boolean filtering) {
            this.filtering = filtering;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean getFiltering() {
            return this.filtering;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void setFile(java.io.File r7) throws java.lang.Throwable {
            /*
                r6 = this;
                boolean r0 = r7.exists()
                if (r0 == 0) goto L51
                r0 = 0
                r1 = 0
                java.lang.String r2 = r6.encoding     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                if (r2 != 0) goto L18
                java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r3.<init>(r7)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r0 = r2
                goto L2a
            L18:
                java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r4.<init>(r7)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                java.lang.String r5 = r6.encoding     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r0 = r2
            L2a:
                org.apache.tools.ant.taskdefs.Concat.access$200()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                java.lang.String r2 = org.apache.tools.ant.util.FileUtils.readFully(r0)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r6.value = r2     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
                r0.close()     // Catch: java.lang.Throwable -> L39
                goto L3b
            L39:
                r2 = move-exception
            L3b:
                return
            L3e:
                r2 = move-exception
                goto L48
            L40:
                r1 = move-exception
                org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L47
                r2.<init>(r1)     // Catch: java.lang.Throwable -> L47
                throw r2     // Catch: java.lang.Throwable -> L47
            L47:
                r2 = move-exception
            L48:
                if (r0 == 0) goto L4f
                r0.close()     // Catch: java.lang.Throwable -> L4e
                goto L4f
            L4e:
                r3 = move-exception
            L4f:
                throw r2
            L51:
                org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException
                java.lang.StringBuffer r1 = new java.lang.StringBuffer
                r1.<init>()
                java.lang.String r2 = "File "
                r1.append(r2)
                r1.append(r7)
                java.lang.String r2 = " does not exist."
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Concat.TextElement.setFile(java.io.File):void");
        }

        public void addText(String value) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.value);
            stringBuffer.append(getProject().replaceProperties(value));
            this.value = stringBuffer.toString();
        }

        public void setTrimLeading(boolean strip) {
            this.trimLeading = strip;
        }

        public void setTrim(boolean trim) {
            this.trim = trim;
        }

        public String getValue() {
            if (this.value == null) {
                this.value = "";
            }
            if (this.value.trim().length() == 0) {
                this.value = "";
            }
            if (this.trimLeading) {
                char[] current = this.value.toCharArray();
                StringBuffer b = new StringBuffer(current.length);
                boolean startOfLine = true;
                int pos = 0;
                while (pos < current.length) {
                    int pos2 = pos + 1;
                    char ch = current[pos];
                    if (startOfLine) {
                        if (ch == ' ' || ch == '\t') {
                            pos = pos2;
                        } else {
                            startOfLine = false;
                        }
                    }
                    b.append(ch);
                    if (ch == '\n' || ch == '\r') {
                        startOfLine = true;
                        pos = pos2;
                    } else {
                        pos = pos2;
                    }
                }
                this.value = b.toString();
            }
            if (this.trim) {
                this.value = this.value.trim();
            }
            return this.value;
        }
    }

    private class MultiReader extends Reader {
        private char[] lastChars;
        private int lastPos;
        private boolean needAddSeparator;
        private int pos;
        private Reader reader;

        private MultiReader() {
            this.pos = 0;
            this.reader = null;
            this.lastPos = 0;
            this.lastChars = new char[Concat.this.eolString.length()];
            this.needAddSeparator = false;
        }

        /* synthetic */ MultiReader(Concat x0, AnonymousClass1 x1) {
            this();
        }

        private Reader getReader() throws IOException {
            if (this.reader == null) {
                Concat concat = Concat.this;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Concating file ");
                stringBuffer.append(Concat.this.sourceFiles.elementAt(this.pos));
                concat.log(stringBuffer.toString(), 3);
                if (Concat.this.encoding == null) {
                    this.reader = new BufferedReader(new FileReader((File) Concat.this.sourceFiles.elementAt(this.pos)));
                } else {
                    this.reader = new BufferedReader(new InputStreamReader(new FileInputStream((File) Concat.this.sourceFiles.elementAt(this.pos)), Concat.this.encoding));
                }
                int i = 0;
                while (true) {
                    char[] cArr = this.lastChars;
                    if (i >= cArr.length) {
                        break;
                    }
                    cArr[i] = 0;
                    i++;
                }
            }
            return this.reader;
        }

        /* JADX WARN: Incorrect condition in loop: B:9:0x0035 */
        @Override // java.io.Reader
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int read() throws java.io.IOException {
            /*
                r4 = this;
                boolean r0 = r4.needAddSeparator
                r1 = 0
                if (r0 == 0) goto L28
                org.apache.tools.ant.taskdefs.Concat r0 = org.apache.tools.ant.taskdefs.Concat.this
                java.lang.String r0 = org.apache.tools.ant.taskdefs.Concat.access$300(r0)
                int r2 = r4.lastPos
                int r3 = r2 + 1
                r4.lastPos = r3
                char r0 = r0.charAt(r2)
                int r2 = r4.lastPos
                org.apache.tools.ant.taskdefs.Concat r3 = org.apache.tools.ant.taskdefs.Concat.this
                java.lang.String r3 = org.apache.tools.ant.taskdefs.Concat.access$300(r3)
                int r3 = r3.length()
                if (r2 < r3) goto L27
                r4.lastPos = r1
                r4.needAddSeparator = r1
            L27:
                return r0
            L28:
                int r0 = r4.pos
                org.apache.tools.ant.taskdefs.Concat r2 = org.apache.tools.ant.taskdefs.Concat.this
                java.util.Vector r2 = org.apache.tools.ant.taskdefs.Concat.access$400(r2)
                int r2 = r2.size()
                r3 = -1
                if (r0 < r2) goto L38
                return r3
            L38:
                java.io.Reader r0 = r4.getReader()
                int r0 = r0.read()
                if (r0 != r3) goto L63
                java.io.Reader r2 = r4.reader
                r2.close()
                r2 = 0
                r4.reader = r2
                org.apache.tools.ant.taskdefs.Concat r2 = org.apache.tools.ant.taskdefs.Concat.this
                boolean r2 = org.apache.tools.ant.taskdefs.Concat.access$600(r2)
                r3 = 1
                if (r2 == 0) goto L5d
                boolean r2 = r4.isMissingEndOfLine()
                if (r2 == 0) goto L5d
                r4.needAddSeparator = r3
                r4.lastPos = r1
            L5d:
                int r2 = r4.pos
                int r2 = r2 + r3
                r4.pos = r2
                goto L28
            L63:
                char r1 = (char) r0
                r4.addLastChar(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Concat.MultiReader.read():int");
        }

        @Override // java.io.Reader
        public int read(char[] cbuf, int off, int len) throws IOException {
            int amountRead = 0;
            while (true) {
                if (this.pos < Concat.this.sourceFiles.size() || this.needAddSeparator) {
                    if (this.needAddSeparator) {
                        String str = Concat.this.eolString;
                        int i = this.lastPos;
                        this.lastPos = i + 1;
                        cbuf[off] = str.charAt(i);
                        if (this.lastPos >= Concat.this.eolString.length()) {
                            this.lastPos = 0;
                            this.needAddSeparator = false;
                            this.pos++;
                        }
                        len--;
                        off++;
                        amountRead++;
                        if (len == 0) {
                            return amountRead;
                        }
                    } else {
                        int nRead = getReader().read(cbuf, off, len);
                        if (nRead != -1 && nRead != 0) {
                            if (Concat.this.fixLastLine) {
                                for (int i2 = nRead; i2 > nRead - this.lastChars.length && i2 > 0; i2--) {
                                    addLastChar(cbuf[(off + i2) - 1]);
                                }
                            }
                            len -= nRead;
                            off += nRead;
                            amountRead += nRead;
                            if (len == 0) {
                                return amountRead;
                            }
                        } else {
                            this.reader.close();
                            this.reader = null;
                            if (Concat.this.fixLastLine && isMissingEndOfLine()) {
                                this.needAddSeparator = true;
                                this.lastPos = 0;
                            } else {
                                this.pos++;
                            }
                        }
                    }
                } else {
                    if (amountRead == 0) {
                        return -1;
                    }
                    return amountRead;
                }
            }
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Reader reader = this.reader;
            if (reader != null) {
                reader.close();
            }
        }

        private void addLastChar(char ch) {
            for (int i = this.lastChars.length - 2; i >= 0; i--) {
                char[] cArr = this.lastChars;
                cArr[i] = cArr[i + 1];
            }
            this.lastChars[r1.length - 1] = ch;
        }

        private boolean isMissingEndOfLine() {
            int i = 0;
            while (true) {
                char[] cArr = this.lastChars;
                if (i < cArr.length) {
                    if (cArr[i] == Concat.this.eolString.charAt(i)) {
                        i++;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}
