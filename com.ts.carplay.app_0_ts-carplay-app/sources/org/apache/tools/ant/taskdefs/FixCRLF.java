package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class FixCRLF extends MatchingTask {
    private static final int ADD = 1;
    private static final int ASIS = 0;
    private static final int CR = 1;
    private static final int CRLF = 3;
    private static final char CTRLZ = 26;
    private static final int INBUFLEN = 8192;
    private static final int IN_CHAR_CONST = 2;
    private static final int IN_MULTI_COMMENT = 5;
    private static final int IN_SINGLE_COMMENT = 4;
    private static final int IN_STR_CONST = 3;
    private static final int LF = 2;
    private static final int LINEBUFLEN = 200;
    private static final int LOOKING = 1;
    private static final int NOTJAVA = 0;
    private static final int REMOVE = -1;
    private static final int SPACES = -1;
    private static final int TABS = 1;
    private static final int UNDEF = -1;
    private int ctrlz;
    private int eol;
    private String eolstr;
    private File srcDir;
    private int tablength = 8;
    private String spaces = "        ";
    private StringBuffer linebuf = new StringBuffer(1024);
    private StringBuffer linebuf2 = new StringBuffer(1024);
    private boolean javafiles = false;
    private boolean fixlast = true;
    private File destDir = null;
    private FileUtils fileUtils = FileUtils.newFileUtils();
    private String encoding = null;
    private int tabs = 0;

    public FixCRLF() {
        if (Os.isFamily("mac")) {
            this.ctrlz = -1;
            this.eol = 1;
            this.eolstr = "\r";
        } else if (Os.isFamily("dos")) {
            this.ctrlz = 0;
            this.eol = 3;
            this.eolstr = Manifest.EOL;
        } else {
            this.ctrlz = -1;
            this.eol = 2;
            this.eolstr = "\n";
        }
    }

    public void setSrcdir(File srcDir) {
        this.srcDir = srcDir;
    }

    public void setDestdir(File destDir) {
        this.destDir = destDir;
    }

    public void setJavafiles(boolean javafiles) {
        this.javafiles = javafiles;
    }

    public void setEol(CrLf attr) {
        String option = attr.getValue();
        if (option.equals("asis")) {
            this.eol = 0;
            return;
        }
        if (option.equals("cr") || option.equals("mac")) {
            this.eol = 1;
            this.eolstr = "\r";
        } else if (option.equals("lf") || option.equals("unix")) {
            this.eol = 2;
            this.eolstr = "\n";
        } else {
            this.eol = 3;
            this.eolstr = Manifest.EOL;
        }
    }

    public void setCr(AddAsisRemove attr) {
        log("DEPRECATED: The cr attribute has been deprecated,", 1);
        log("Please use the eol attribute instead", 1);
        String option = attr.getValue();
        CrLf c = new CrLf();
        if (option.equals("remove")) {
            c.setValue("lf");
        } else if (option.equals("asis")) {
            c.setValue("asis");
        } else {
            c.setValue("crlf");
        }
        setEol(c);
    }

    public void setTab(AddAsisRemove attr) {
        String option = attr.getValue();
        if (option.equals("remove")) {
            this.tabs = -1;
        } else if (option.equals("asis")) {
            this.tabs = 0;
        } else {
            this.tabs = 1;
        }
    }

    public void setTablength(int tlength) throws BuildException {
        if (tlength < 2 || tlength > 80) {
            throw new BuildException("tablength must be between 2 and 80", getLocation());
        }
        this.tablength = tlength;
        StringBuffer sp = new StringBuffer();
        for (int i = 0; i < this.tablength; i++) {
            sp.append(' ');
        }
        this.spaces = sp.toString();
    }

    public void setEof(AddAsisRemove attr) {
        String option = attr.getValue();
        if (option.equals("remove")) {
            this.ctrlz = -1;
        } else if (option.equals("asis")) {
            this.ctrlz = 0;
        } else {
            this.ctrlz = 1;
        }
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setFixlast(boolean fixlast) {
        this.fixlast = fixlast;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        File file = this.srcDir;
        if (file == null) {
            throw new BuildException("srcdir attribute must be set!");
        }
        if (!file.exists()) {
            throw new BuildException("srcdir does not exist!");
        }
        if (!this.srcDir.isDirectory()) {
            throw new BuildException("srcdir is not a directory!");
        }
        File file2 = this.destDir;
        if (file2 != null) {
            if (!file2.exists()) {
                throw new BuildException("destdir does not exist!");
            }
            if (!this.destDir.isDirectory()) {
                throw new BuildException("destdir is not a directory!");
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("options: eol=");
        int i = this.eol;
        String str = "asis";
        stringBuffer.append(i == 0 ? "asis" : i == 1 ? "cr" : i == 2 ? "lf" : "crlf");
        stringBuffer.append(" tab=");
        int i2 = this.tabs;
        stringBuffer.append(i2 == 1 ? "add" : i2 == 0 ? "asis" : "remove");
        stringBuffer.append(" eof=");
        int i3 = this.ctrlz;
        if (i3 == 1) {
            str = "add";
        } else if (i3 != 0) {
            str = "remove";
        }
        stringBuffer.append(str);
        stringBuffer.append(" tablength=");
        stringBuffer.append(this.tablength);
        stringBuffer.append(" encoding=");
        String str2 = this.encoding;
        if (str2 == null) {
            str2 = "default";
        }
        stringBuffer.append(str2);
        log(stringBuffer.toString(), 3);
        DirectoryScanner ds = super.getDirectoryScanner(this.srcDir);
        String[] files = ds.getIncludedFiles();
        for (String str3 : files) {
            processFile(str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Reader getReader(File f) throws IOException {
        return this.encoding == null ? new FileReader(f) : new InputStreamReader(new FileInputStream(f), this.encoding);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x021a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.apache.tools.ant.taskdefs.FixCRLF$OneLiner] */
    /* JADX WARN: Type inference failed for: r5v1, types: [org.apache.tools.ant.taskdefs.FixCRLF$OneLiner] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processFile(java.lang.String r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 572
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.FixCRLF.processFile(java.lang.String):void");
    }

    private void nextStateChange(OneLiner.BufferLine bufline) throws BuildException {
        int eol = bufline.length();
        int ptr = bufline.getNext();
        while (ptr < eol) {
            int ptr2 = ptr + 1;
            int ptr3 = bufline.getChar(ptr);
            if (ptr3 == 34) {
                bufline.setState(3);
                bufline.setLookahead(ptr2 - 1);
                return;
            }
            if (ptr3 == 39) {
                bufline.setState(2);
                bufline.setLookahead(ptr2 - 1);
                return;
            }
            if (ptr3 == 47 && ptr2 < eol) {
                if (bufline.getChar(ptr2) == '*') {
                    bufline.setState(5);
                    bufline.setLookahead(ptr2 - 1);
                    return;
                } else if (bufline.getChar(ptr2) == '/') {
                    bufline.setState(4);
                    bufline.setLookahead(ptr2 - 1);
                    return;
                }
            }
            ptr = ptr2;
        }
        bufline.setLookahead(ptr);
    }

    private void endOfCharConst(OneLiner.BufferLine bufline, char terminator) throws BuildException {
        int ptr = bufline.getNext();
        int eol = bufline.length();
        int ptr2 = ptr + 1;
        while (ptr2 < eol) {
            int ptr3 = ptr2 + 1;
            char c = bufline.getChar(ptr2);
            if (c == '\\') {
                ptr2 = ptr3 + 1;
            } else if (c != terminator) {
                ptr2 = ptr3;
            } else {
                bufline.setLookahead(ptr3);
                return;
            }
        }
        throw new BuildException("endOfCharConst: unterminated char constant");
    }

    private void notInConstant(OneLiner.BufferLine bufline, int end, BufferedWriter outWriter) {
        int place;
        String line = bufline.substring(bufline.getNext(), end);
        int col = bufline.getColumn();
        this.linebuf = new StringBuffer();
        int col2 = col;
        int place2 = 0;
        while (true) {
            int iIndexOf = line.indexOf(9, place2);
            if (iIndexOf < 0) {
                break;
            }
            this.linebuf.append(line.substring(place2, iIndexOf));
            int col3 = col2 + (iIndexOf - place2);
            int i = this.tablength;
            int tabspaces = i - (col3 % i);
            this.linebuf.append(this.spaces.substring(0, tabspaces));
            col2 = col3 + tabspaces;
            place2 = iIndexOf + 1;
        }
        this.linebuf.append(line.substring(place2, line.length()));
        String linestring = new String(this.linebuf.substring(0));
        if (this.tabs != -1) {
            this.linebuf2 = new StringBuffer();
            int col4 = bufline.getColumn();
            int placediff = col4 + 0;
            int i2 = this.tablength;
            int nextStop = (i2 - (col4 % i2)) + col4;
            if (nextStop - col4 < 2) {
                this.linebuf2.append(linestring.substring(0, nextStop - placediff));
                int place3 = nextStop - placediff;
                int place4 = this.tablength;
                nextStop += place4;
                place = place3;
            } else {
                place = 0;
            }
            while (nextStop - placediff <= linestring.length()) {
                int tabCol = nextStop;
                do {
                    tabCol--;
                    if (tabCol - placediff < place) {
                        break;
                    }
                } while (linestring.charAt(tabCol - placediff) == ' ');
                if (nextStop - tabCol <= 2) {
                    this.linebuf2.append(linestring.substring(place, nextStop - placediff));
                } else {
                    tabCol++;
                    this.linebuf2.append(linestring.substring(place, tabCol - placediff));
                    this.linebuf2.append('\t');
                }
                place = nextStop - placediff;
                nextStop += this.tablength;
            }
            this.linebuf2.append(linestring.substring(place, linestring.length()));
            try {
                outWriter.write(this.linebuf2.substring(0));
            } catch (IOException e) {
                throw new BuildException(e);
            }
        } else {
            try {
                outWriter.write(linestring);
            } catch (IOException e2) {
                throw new BuildException(e2);
            }
        }
        bufline.setColumn(bufline.getColumn() + linestring.length());
        bufline.setNext(end);
    }

    class OneLiner implements Enumeration {
        private BufferedReader reader;
        private File srcFile;
        private int state;
        private StringBuffer eolStr = new StringBuffer(200);
        private StringBuffer eofStr = new StringBuffer();
        private StringBuffer line = new StringBuffer();
        private boolean reachedEof = false;

        public OneLiner(File file) throws BuildException {
            this.state = FixCRLF.this.javafiles ? 1 : 0;
            this.srcFile = file;
            try {
                this.reader = new BufferedReader(FixCRLF.this.getReader(file), 8192);
                nextLine();
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(file);
                stringBuffer.append(": ");
                stringBuffer.append(e.getMessage());
                throw new BuildException(stringBuffer.toString(), e, FixCRLF.this.getLocation());
            }
        }

        protected void nextLine() throws BuildException {
            int eolcount = 0;
            this.eolStr = new StringBuffer();
            this.line = new StringBuffer();
            try {
                int ch = this.reader.read();
                while (ch != -1 && ch != 13 && ch != 10) {
                    this.line.append((char) ch);
                    ch = this.reader.read();
                }
                if (ch == -1 && this.line.length() == 0) {
                    this.reachedEof = true;
                    return;
                }
                char c = (char) ch;
                if (c == '\n') {
                    eolcount = 0 + 1;
                    this.eolStr.append('\n');
                } else if (c == '\r') {
                    eolcount = 0 + 1;
                    this.eolStr.append('\r');
                    this.reader.mark(2);
                    int i = this.reader.read();
                    if (i != -1) {
                        if (i != 10) {
                            if (i == 13 && ((char) this.reader.read()) == '\n') {
                                eolcount += 2;
                                this.eolStr.append(Manifest.EOL);
                            } else {
                                this.reader.reset();
                            }
                        } else {
                            eolcount++;
                            this.eolStr.append('\n');
                        }
                    }
                }
                if (eolcount == 0) {
                    int i2 = this.line.length();
                    do {
                        i2--;
                        if (i2 < 0) {
                            break;
                        }
                    } while (this.line.charAt(i2) == 26);
                    if (i2 < this.line.length() - 1) {
                        this.eofStr.append(this.line.toString().substring(i2 + 1));
                        if (i2 < 0) {
                            this.line.setLength(0);
                            this.reachedEof = true;
                        } else {
                            this.line.setLength(i2 + 1);
                        }
                    }
                }
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.srcFile);
                stringBuffer.append(": ");
                stringBuffer.append(e.getMessage());
                throw new BuildException(stringBuffer.toString(), e, FixCRLF.this.getLocation());
            }
        }

        public String getEofStr() {
            return this.eofStr.substring(0);
        }

        public int getState() {
            return this.state;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return !this.reachedEof;
        }

        @Override // java.util.Enumeration
        public Object nextElement() throws NoSuchElementException {
            if (!hasMoreElements()) {
                throw new NoSuchElementException("OneLiner");
            }
            BufferLine tmpLine = new BufferLine(this.line.toString(), this.eolStr.substring(0));
            nextLine();
            return tmpLine;
        }

        public void close() throws IOException {
            BufferedReader bufferedReader = this.reader;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        class BufferLine {
            private int column;
            private String eolStr;
            private String line;
            private int lookahead = -1;
            private int next;

            public BufferLine(String line, String eolStr) throws BuildException {
                this.next = 0;
                this.column = 0;
                this.next = 0;
                this.column = 0;
                this.line = line;
                this.eolStr = eolStr;
            }

            public int getNext() {
                return this.next;
            }

            public void setNext(int next) {
                this.next = next;
            }

            public int getLookahead() {
                return this.lookahead;
            }

            public void setLookahead(int lookahead) {
                this.lookahead = lookahead;
            }

            public char getChar(int i) {
                return this.line.charAt(i);
            }

            public char getNextChar() {
                return getChar(this.next);
            }

            public char getNextCharInc() {
                int i = this.next;
                this.next = i + 1;
                return getChar(i);
            }

            public int getColumn() {
                return this.column;
            }

            public void setColumn(int col) {
                this.column = col;
            }

            public int incColumn() {
                int i = this.column;
                this.column = i + 1;
                return i;
            }

            public int length() {
                return this.line.length();
            }

            public int getEolLength() {
                return this.eolStr.length();
            }

            public String getLineString() {
                return this.line;
            }

            public String getEol() {
                return this.eolStr;
            }

            public String substring(int begin) {
                return this.line.substring(begin);
            }

            public String substring(int begin, int end) {
                return this.line.substring(begin, end);
            }

            public void setState(int state) {
                OneLiner.this.setState(state);
            }

            public int getState() {
                return OneLiner.this.getState();
            }
        }
    }

    public static class AddAsisRemove extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"add", "asis", "remove"};
        }
    }

    public static class CrLf extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"asis", "cr", "lf", "crlf", "mac", "unix", "dos"};
        }
    }
}
