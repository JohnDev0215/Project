package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.LazyFileOutputStream;
import org.apache.tools.ant.util.StringUtils;
import org.apache.tools.ant.util.TeeOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Redirector {
    private static final String DEFAULT_ENCODING = System.getProperty("file.encoding");
    private boolean alwaysLog;
    private boolean append;
    private boolean appendProperties;
    private PropertyOutputStream baos;
    private boolean createEmptyFiles;
    private File[] error;
    private PropertyOutputStream errorBaos;
    private String errorEncoding;
    private Vector errorFilterChains;
    private PrintStream errorPrintStream;
    private String errorProperty;
    private OutputStream errorStream;
    private File[] input;
    private String inputEncoding;
    private Vector inputFilterChains;
    private InputStream inputStream;
    private String inputString;
    private boolean logError;
    private ProjectComponent managingTask;
    private File[] out;
    private PrintStream outPrintStream;
    private String outputEncoding;
    private Vector outputFilterChains;
    private String outputProperty;
    private OutputStream outputStream;
    private ThreadGroup threadGroup;

    private class PropertyOutputStream extends ByteArrayOutputStream {
        boolean closed = false;
        String property;

        PropertyOutputStream(String property) {
            this.property = property;
        }

        @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (!Redirector.this.append || !Redirector.this.appendProperties) {
                    Redirector.this.setPropertyFromBAOS(this, this.property);
                    this.closed = true;
                }
            }
        }
    }

    public Redirector(Task managingTask) {
        this((ProjectComponent) managingTask);
    }

    public Redirector(ProjectComponent managingTask) {
        this.logError = false;
        this.baos = null;
        this.errorBaos = null;
        this.append = false;
        this.alwaysLog = false;
        this.createEmptyFiles = true;
        this.outputStream = null;
        this.errorStream = null;
        this.inputStream = null;
        this.outPrintStream = null;
        this.errorPrintStream = null;
        String str = DEFAULT_ENCODING;
        this.outputEncoding = str;
        this.errorEncoding = str;
        this.inputEncoding = str;
        this.appendProperties = true;
        this.threadGroup = new ThreadGroup("redirector");
        this.managingTask = managingTask;
    }

    public void setInput(File input) {
        setInput(input == null ? null : new File[]{input});
    }

    public synchronized void setInput(File[] input) {
        this.input = input;
    }

    public synchronized void setInputString(String inputString) {
        this.inputString = inputString;
    }

    void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutput(File out) {
        setOutput(out == null ? null : new File[]{out});
    }

    public synchronized void setOutput(File[] out) {
        this.out = out;
    }

    public synchronized void setOutputEncoding(String outputEncoding) {
        if (outputEncoding == null) {
            throw new IllegalArgumentException("outputEncoding must not be null");
        }
        this.outputEncoding = outputEncoding;
    }

    public synchronized void setErrorEncoding(String errorEncoding) {
        if (errorEncoding == null) {
            throw new IllegalArgumentException("errorEncoding must not be null");
        }
        this.errorEncoding = errorEncoding;
    }

    public synchronized void setInputEncoding(String inputEncoding) {
        if (inputEncoding == null) {
            throw new IllegalArgumentException("inputEncoding must not be null");
        }
        this.inputEncoding = inputEncoding;
    }

    public synchronized void setLogError(boolean logError) {
        this.logError = logError;
    }

    public synchronized void setAppendProperties(boolean appendProperties) {
        this.appendProperties = appendProperties;
    }

    public void setError(File error) {
        setError(error == null ? null : new File[]{error});
    }

    public synchronized void setError(File[] error) {
        this.error = error;
    }

    public synchronized void setOutputProperty(String outputProperty) {
        if (outputProperty != null) {
            if (!outputProperty.equals(this.outputProperty)) {
            }
        }
        this.outputProperty = outputProperty;
        this.baos = null;
    }

    public synchronized void setAppend(boolean append) {
        this.append = append;
    }

    public synchronized void setAlwaysLog(boolean alwaysLog) {
        this.alwaysLog = alwaysLog;
    }

    public synchronized void setCreateEmptyFiles(boolean createEmptyFiles) {
        this.createEmptyFiles = createEmptyFiles;
    }

    public synchronized void setErrorProperty(String errorProperty) {
        if (errorProperty != null) {
            if (!errorProperty.equals(this.errorProperty)) {
            }
        }
        this.errorProperty = errorProperty;
        this.errorBaos = null;
    }

    public synchronized void setInputFilterChains(Vector inputFilterChains) {
        this.inputFilterChains = inputFilterChains;
    }

    public synchronized void setOutputFilterChains(Vector outputFilterChains) {
        this.outputFilterChains = outputFilterChains;
    }

    public synchronized void setErrorFilterChains(Vector errorFilterChains) {
        this.errorFilterChains = errorFilterChains;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPropertyFromBAOS(ByteArrayOutputStream baos, String propertyName) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(Execute.toString(baos)));
        StringBuffer val = new StringBuffer();
        while (true) {
            String line = in.readLine();
            if (line != null) {
                if (val.length() != 0) {
                    val.append(StringUtils.LINE_SEP);
                }
                val.append(line);
            } else {
                this.managingTask.getProject().setNewProperty(propertyName, val.toString());
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x023d A[Catch: all -> 0x02e9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0006, B:7:0x000b, B:11:0x001b, B:12:0x002f, B:14:0x0034, B:16:0x0038, B:17:0x0059, B:21:0x006d, B:23:0x0072, B:25:0x0076, B:27:0x007b, B:31:0x008b, B:42:0x00c7, B:44:0x00cb, B:46:0x00cf, B:47:0x00f0, B:49:0x00fb, B:52:0x0101, B:54:0x010a, B:56:0x010f, B:58:0x0113, B:65:0x012e, B:67:0x0133, B:74:0x014d, B:76:0x0153, B:80:0x0165, B:82:0x017b, B:84:0x0183, B:85:0x0195, B:90:0x01c4, B:92:0x01c8, B:96:0x01da, B:98:0x01f0, B:100:0x01f8, B:101:0x020a, B:106:0x0239, B:108:0x023d, B:110:0x0242, B:114:0x0258, B:115:0x0262, B:116:0x026c, B:123:0x02a9, B:125:0x02ad, B:127:0x02b1, B:129:0x02b9, B:130:0x02be, B:131:0x02cb, B:133:0x02df, B:134:0x02e6, B:118:0x0275, B:119:0x027a, B:120:0x027b, B:122:0x027f, B:104:0x0231, B:105:0x0238, B:94:0x01d0, B:88:0x01bc, B:89:0x01c3, B:78:0x015b, B:69:0x0137, B:73:0x014b, B:72:0x0144, B:60:0x0117, B:64:0x012c, B:63:0x0125, B:55:0x010d, B:32:0x00a0, B:34:0x00a4, B:36:0x00a8, B:37:0x00b1, B:40:0x00bf, B:41:0x00c6, B:20:0x0066, B:22:0x0070), top: B:142:0x0001, inners: #0, #2, #3, #4, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x027b A[Catch: all -> 0x02e9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0006, B:7:0x000b, B:11:0x001b, B:12:0x002f, B:14:0x0034, B:16:0x0038, B:17:0x0059, B:21:0x006d, B:23:0x0072, B:25:0x0076, B:27:0x007b, B:31:0x008b, B:42:0x00c7, B:44:0x00cb, B:46:0x00cf, B:47:0x00f0, B:49:0x00fb, B:52:0x0101, B:54:0x010a, B:56:0x010f, B:58:0x0113, B:65:0x012e, B:67:0x0133, B:74:0x014d, B:76:0x0153, B:80:0x0165, B:82:0x017b, B:84:0x0183, B:85:0x0195, B:90:0x01c4, B:92:0x01c8, B:96:0x01da, B:98:0x01f0, B:100:0x01f8, B:101:0x020a, B:106:0x0239, B:108:0x023d, B:110:0x0242, B:114:0x0258, B:115:0x0262, B:116:0x026c, B:123:0x02a9, B:125:0x02ad, B:127:0x02b1, B:129:0x02b9, B:130:0x02be, B:131:0x02cb, B:133:0x02df, B:134:0x02e6, B:118:0x0275, B:119:0x027a, B:120:0x027b, B:122:0x027f, B:104:0x0231, B:105:0x0238, B:94:0x01d0, B:88:0x01bc, B:89:0x01c3, B:78:0x015b, B:69:0x0137, B:73:0x014b, B:72:0x0144, B:60:0x0117, B:64:0x012c, B:63:0x0125, B:55:0x010d, B:32:0x00a0, B:34:0x00a4, B:36:0x00a8, B:37:0x00b1, B:40:0x00bf, B:41:0x00c6, B:20:0x0066, B:22:0x0070), top: B:142:0x0001, inners: #0, #2, #3, #4, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x027f A[Catch: all -> 0x02e9, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0006, B:7:0x000b, B:11:0x001b, B:12:0x002f, B:14:0x0034, B:16:0x0038, B:17:0x0059, B:21:0x006d, B:23:0x0072, B:25:0x0076, B:27:0x007b, B:31:0x008b, B:42:0x00c7, B:44:0x00cb, B:46:0x00cf, B:47:0x00f0, B:49:0x00fb, B:52:0x0101, B:54:0x010a, B:56:0x010f, B:58:0x0113, B:65:0x012e, B:67:0x0133, B:74:0x014d, B:76:0x0153, B:80:0x0165, B:82:0x017b, B:84:0x0183, B:85:0x0195, B:90:0x01c4, B:92:0x01c8, B:96:0x01da, B:98:0x01f0, B:100:0x01f8, B:101:0x020a, B:106:0x0239, B:108:0x023d, B:110:0x0242, B:114:0x0258, B:115:0x0262, B:116:0x026c, B:123:0x02a9, B:125:0x02ad, B:127:0x02b1, B:129:0x02b9, B:130:0x02be, B:131:0x02cb, B:133:0x02df, B:134:0x02e6, B:118:0x0275, B:119:0x027a, B:120:0x027b, B:122:0x027f, B:104:0x0231, B:105:0x0238, B:94:0x01d0, B:88:0x01bc, B:89:0x01c3, B:78:0x015b, B:69:0x0137, B:73:0x014b, B:72:0x0144, B:60:0x0117, B:64:0x012c, B:63:0x0125, B:55:0x010d, B:32:0x00a0, B:34:0x00a4, B:36:0x00a8, B:37:0x00b1, B:40:0x00bf, B:41:0x00c6, B:20:0x0066, B:22:0x0070), top: B:142:0x0001, inners: #0, #2, #3, #4, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void createStreams() {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Redirector.createStreams():void");
    }

    public synchronized ExecuteStreamHandler createHandler() throws BuildException {
        createStreams();
        return new PumpStreamHandler(this.outputStream, this.errorStream, this.inputStream);
    }

    protected synchronized void handleOutput(String output) {
        if (this.outPrintStream == null) {
            this.outPrintStream = new PrintStream(this.outputStream);
        }
        this.outPrintStream.print(output);
    }

    protected synchronized int handleInput(byte[] buffer, int offset, int length) throws IOException {
        if (this.inputStream == null) {
            return this.managingTask.getProject().defaultInput(buffer, offset, length);
        }
        return this.inputStream.read(buffer, offset, length);
    }

    protected synchronized void handleFlush(String output) {
        if (this.outPrintStream == null) {
            this.outPrintStream = new PrintStream(this.outputStream);
        }
        this.outPrintStream.print(output);
        this.outPrintStream.flush();
    }

    protected synchronized void handleErrorOutput(String output) {
        if (this.errorPrintStream == null) {
            this.errorPrintStream = new PrintStream(this.errorStream);
        }
        this.errorPrintStream.print(output);
    }

    protected synchronized void handleErrorFlush(String output) {
        if (this.errorPrintStream == null) {
            this.errorPrintStream = new PrintStream(this.errorStream);
        }
        this.errorPrintStream.print(output);
    }

    public synchronized OutputStream getOutputStream() {
        return this.outputStream;
    }

    public synchronized OutputStream getErrorStream() {
        return this.errorStream;
    }

    public synchronized InputStream getInputStream() {
        return this.inputStream;
    }

    public synchronized void complete() throws IOException {
        System.out.flush();
        System.err.flush();
        if (this.inputStream != null) {
            this.inputStream.close();
        }
        this.outputStream.flush();
        this.outputStream.close();
        this.errorStream.flush();
        this.errorStream.close();
        while (this.threadGroup.activeCount() > 0) {
            try {
                ProjectComponent projectComponent = this.managingTask;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("waiting for ");
                stringBuffer.append(this.threadGroup.activeCount());
                stringBuffer.append(" Threads:");
                projectComponent.log(stringBuffer.toString(), 4);
                Thread[] thread = new Thread[this.threadGroup.activeCount()];
                this.threadGroup.enumerate(thread);
                for (int i = 0; i < thread.length && thread[i] != null; i++) {
                    try {
                        this.managingTask.log(thread[i].toString(), 4);
                    } catch (NullPointerException e) {
                    }
                }
                Thread.sleep(1000L);
            } catch (InterruptedException e2) {
            }
        }
        setProperties();
        this.inputStream = null;
        this.outputStream = null;
        this.errorStream = null;
        this.outPrintStream = null;
        this.errorPrintStream = null;
    }

    public synchronized void setProperties() {
        if (this.baos != null) {
            try {
                this.baos.close();
            } catch (IOException e) {
            }
        }
        if (this.errorBaos != null) {
            try {
                this.errorBaos.close();
            } catch (IOException e2) {
            }
        }
    }

    private OutputStream foldFiles(File[] file, String logHead, int loglevel) {
        OutputStream result = new LazyFileOutputStream(file[0], this.append, this.createEmptyFiles);
        ProjectComponent projectComponent = this.managingTask;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(logHead);
        stringBuffer.append(file[0]);
        projectComponent.log(stringBuffer.toString(), loglevel);
        char[] c = new char[logHead.length()];
        Arrays.fill(c, ' ');
        String indent = new String(c);
        for (int i = 1; i < file.length; i++) {
            this.outputStream = new TeeOutputStream(this.outputStream, new LazyFileOutputStream(file[i], this.append, this.createEmptyFiles));
            ProjectComponent projectComponent2 = this.managingTask;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(indent);
            stringBuffer2.append(file[i]);
            projectComponent2.log(stringBuffer2.toString(), loglevel);
        }
        return result;
    }
}
