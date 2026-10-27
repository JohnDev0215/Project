package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;

/* JADX INFO: loaded from: classes.dex */
public class SQLExec extends JDBCTask {
    private int goodSql = 0;
    private int totalSql = 0;
    private Connection conn = null;
    private Vector filesets = new Vector();
    private Statement statement = null;
    private File srcFile = null;
    private String sqlCommand = "";
    private Vector transactions = new Vector();
    private String delimiter = ";";
    private String delimiterType = DelimiterType.NORMAL;
    private boolean print = false;
    private boolean showheaders = true;
    private File output = null;
    private String onError = "abort";
    private String encoding = null;
    private boolean append = false;
    private boolean keepformat = false;
    private boolean escapeProcessing = true;

    public static class DelimiterType extends EnumeratedAttribute {
        public static final String NORMAL = "normal";
        public static final String ROW = "row";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NORMAL, ROW};
        }
    }

    public void setSrc(File srcFile) {
        this.srcFile = srcFile;
    }

    public void addText(String sql) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.sqlCommand);
        stringBuffer.append(sql);
        this.sqlCommand = stringBuffer.toString();
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    public Transaction createTransaction() {
        Transaction t = new Transaction();
        this.transactions.addElement(t);
        return t;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setDelimiterType(DelimiterType delimiterType) {
        this.delimiterType = delimiterType.getValue();
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public void setShowheaders(boolean showheaders) {
        this.showheaders = showheaders;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public void setOnerror(OnError action) {
        this.onError = action.getValue();
    }

    public void setKeepformat(boolean keepformat) {
        this.keepformat = keepformat;
    }

    public void setEscapeProcessing(boolean enable) {
        this.escapeProcessing = enable;
    }

    /* JADX WARN: Removed duplicated region for block: B:95:0x018f A[Catch: SQLException -> 0x019e, all -> 0x01d9, TryCatch #2 {SQLException -> 0x019e, blocks: (B:93:0x018b, B:95:0x018f, B:96:0x0194, B:98:0x0198), top: B:116:0x018b }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0198 A[Catch: SQLException -> 0x019e, all -> 0x01d9, TRY_LEAVE, TryCatch #2 {SQLException -> 0x019e, blocks: (B:93:0x018b, B:95:0x018f, B:96:0x0194, B:98:0x0198), top: B:116:0x018b }] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 479
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.SQLExec.execute():void");
    }

    protected void runStatements(Reader reader, PrintStream out) throws SQLException, IOException {
        StringBuffer sql = new StringBuffer();
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            String line = in.readLine();
            String line2 = line;
            if (line == null) {
                break;
            }
            if (!this.keepformat) {
                line2 = line2.trim();
            }
            String line3 = getProject().replaceProperties(line2);
            if (!this.keepformat) {
                if (!line3.startsWith("//") && !line3.startsWith("--")) {
                    StringTokenizer st = new StringTokenizer(line3);
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        if ("REM".equalsIgnoreCase(token)) {
                        }
                    }
                }
            }
            if (!this.keepformat) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(" ");
                stringBuffer.append(line3);
                sql.append(stringBuffer.toString());
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("\n");
                stringBuffer2.append(line3);
                sql.append(stringBuffer2.toString());
            }
            if (!this.keepformat && line3.indexOf("--") >= 0) {
                sql.append("\n");
            }
            if ((this.delimiterType.equals(DelimiterType.NORMAL) && sql.toString().endsWith(this.delimiter)) || (this.delimiterType.equals(DelimiterType.ROW) && line3.equals(this.delimiter))) {
                execSQL(sql.substring(0, sql.length() - this.delimiter.length()), out);
                sql.replace(0, sql.length(), "");
            }
        }
        if (!sql.equals("")) {
            execSQL(sql.toString(), out);
        }
    }

    protected void execSQL(String str, PrintStream printStream) throws SQLException {
        if ("".equals(str.trim())) {
            return;
        }
        ResultSet resultSet = null;
        try {
            try {
                this.totalSql++;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("SQL: ");
                stringBuffer.append(str);
                log(stringBuffer.toString(), 3);
                boolean zExecute = this.statement.execute(str);
                int updateCount = this.statement.getUpdateCount();
                resultSet = this.statement.getResultSet();
                int i = 0;
                do {
                    if (!zExecute) {
                        if (updateCount != -1) {
                            i += updateCount;
                        }
                    } else if (this.print) {
                        printResults(resultSet, printStream);
                    }
                    zExecute = this.statement.getMoreResults();
                    if (zExecute) {
                        updateCount = this.statement.getUpdateCount();
                        resultSet = this.statement.getResultSet();
                    }
                } while (zExecute);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(i);
                stringBuffer2.append(" rows affected");
                log(stringBuffer2.toString(), 3);
                if (this.print) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append(i);
                    stringBuffer4.append(" rows affected");
                    stringBuffer3.append(stringBuffer4.toString());
                    printStream.println(stringBuffer3);
                }
                for (SQLWarning warnings = this.conn.getWarnings(); warnings != null; warnings = warnings.getNextWarning()) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append(warnings);
                    stringBuffer5.append(" sql warning");
                    log(stringBuffer5.toString(), 3);
                }
                this.conn.clearWarnings();
                this.goodSql++;
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append("Failed to execute: ");
                stringBuffer6.append(str);
                log(stringBuffer6.toString(), 0);
                if (this.onError.equals("continue")) {
                    log(e.toString(), 0);
                    if (resultSet != null) {
                        resultSet.close();
                        return;
                    }
                    return;
                }
                throw e;
            }
        } catch (Throwable th) {
            if (resultSet != null) {
                resultSet.close();
            }
            throw th;
        }
    }

    protected void printResults(PrintStream out) throws SQLException {
        ResultSet rs = this.statement.getResultSet();
        try {
            printResults(rs, out);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    protected void printResults(ResultSet rs, PrintStream out) throws SQLException {
        if (rs != null) {
            log("Processing new result set.", 3);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            StringBuffer line = new StringBuffer();
            if (this.showheaders) {
                for (int col = 1; col < columnCount; col++) {
                    line.append(md.getColumnName(col));
                    line.append(",");
                }
                line.append(md.getColumnName(columnCount));
                out.println(line);
                line = new StringBuffer();
            }
            while (rs.next()) {
                boolean first = true;
                for (int col2 = 1; col2 <= columnCount; col2++) {
                    String columnValue = rs.getString(col2);
                    if (columnValue != null) {
                        columnValue = columnValue.trim();
                    }
                    if (first) {
                        first = false;
                    } else {
                        line.append(",");
                    }
                    line.append(columnValue);
                }
                out.println(line);
                line = new StringBuffer();
            }
        }
        out.println();
    }

    public static class OnError extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"continue", "stop", "abort"};
        }
    }

    public class Transaction {
        private File tSrcFile = null;
        private String tSqlCommand = "";

        public Transaction() {
        }

        public void setSrc(File src) {
            this.tSrcFile = src;
        }

        public void addText(String sql) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.tSqlCommand);
            stringBuffer.append(sql);
            this.tSqlCommand = stringBuffer.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void runTransaction(PrintStream out) throws SQLException, IOException {
            if (this.tSqlCommand.length() != 0) {
                SQLExec.this.log("Executing commands", 2);
                SQLExec.this.runStatements(new StringReader(this.tSqlCommand), out);
            }
            if (this.tSrcFile != null) {
                SQLExec sQLExec = SQLExec.this;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Executing file: ");
                stringBuffer.append(this.tSrcFile.getAbsolutePath());
                sQLExec.log(stringBuffer.toString(), 2);
                Reader reader = SQLExec.this.encoding == null ? new FileReader(this.tSrcFile) : new InputStreamReader(new FileInputStream(this.tSrcFile), SQLExec.this.encoding);
                try {
                    SQLExec.this.runStatements(reader, out);
                } finally {
                    reader.close();
                }
            }
        }
    }
}
