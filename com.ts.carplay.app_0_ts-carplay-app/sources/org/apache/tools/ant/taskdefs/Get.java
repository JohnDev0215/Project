package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Get extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File dest;
    private URL source;
    private boolean verbose = false;
    private boolean useTimestamp = false;
    private boolean ignoreErrors = false;
    private String uname = null;
    private String pword = null;

    public interface DownloadProgress {
        void beginDownload();

        void endDownload();

        void onTick();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        DownloadProgress progress = null;
        if (this.verbose) {
            progress = new VerboseProgress(System.out);
        }
        try {
            doGet(2, progress);
        } catch (IOException ioe) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Error getting ");
            stringBuffer.append(this.source);
            stringBuffer.append(" to ");
            stringBuffer.append(this.dest);
            log(stringBuffer.toString());
            if (!this.ignoreErrors) {
                throw new BuildException(ioe, getLocation());
            }
        }
    }

    public boolean doGet(int logLevel, DownloadProgress progress) throws IOException {
        DownloadProgress progress2;
        long timestamp;
        boolean hasTimestamp;
        if (this.source == null) {
            throw new BuildException("src attribute is required", getLocation());
        }
        File file = this.dest;
        if (file == null) {
            throw new BuildException("dest attribute is required", getLocation());
        }
        if (file.exists() && this.dest.isDirectory()) {
            throw new BuildException("The specified destination is a directory", getLocation());
        }
        if (this.dest.exists() && !this.dest.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't write to ");
            stringBuffer.append(this.dest.getAbsolutePath());
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        if (progress != null) {
            progress2 = progress;
        } else {
            progress2 = new NullProgress();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Getting: ");
        stringBuffer2.append(this.source);
        log(stringBuffer2.toString(), logLevel);
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("To: ");
        stringBuffer3.append(this.dest.getAbsolutePath());
        log(stringBuffer3.toString(), logLevel);
        if (this.useTimestamp && this.dest.exists()) {
            long timestamp2 = this.dest.lastModified();
            if (this.verbose) {
                Date t = new Date(timestamp2);
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("local file date : ");
                stringBuffer4.append(t.toString());
                log(stringBuffer4.toString(), logLevel);
            }
            timestamp = timestamp2;
            hasTimestamp = true;
        } else {
            timestamp = 0;
            hasTimestamp = false;
        }
        URLConnection connection = this.source.openConnection();
        if (hasTimestamp) {
            connection.setIfModifiedSince(timestamp);
        }
        if (this.uname != null || this.pword != null) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(this.uname);
            stringBuffer5.append(":");
            stringBuffer5.append(this.pword);
            String up = stringBuffer5.toString();
            Base64Converter encoder = new Base64Converter();
            String encoding = encoder.encode(up.getBytes());
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Basic ");
            stringBuffer6.append(encoding);
            connection.setRequestProperty("Authorization", stringBuffer6.toString());
        }
        connection.connect();
        if (connection instanceof HttpURLConnection) {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            long lastModified = httpConnection.getLastModified();
            if (httpConnection.getResponseCode() == 304 || (lastModified != 0 && hasTimestamp && timestamp > lastModified)) {
                log("Not modified - so not downloaded", logLevel);
                return false;
            }
            if (httpConnection.getResponseCode() == 401) {
                if (this.ignoreErrors) {
                    log("HTTP Authorization failure", logLevel);
                    return false;
                }
                throw new BuildException("HTTP Authorization failure");
            }
        }
        InputStream is = null;
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                is = connection.getInputStream();
                break;
            } catch (IOException ex) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("Error opening connection ");
                stringBuffer7.append(ex);
                log(stringBuffer7.toString(), logLevel);
                i++;
            }
        }
        if (is == null) {
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("Can't get ");
            stringBuffer8.append(this.source);
            stringBuffer8.append(" to ");
            stringBuffer8.append(this.dest);
            log(stringBuffer8.toString(), logLevel);
            if (this.ignoreErrors) {
                return false;
            }
            StringBuffer stringBuffer9 = new StringBuffer();
            stringBuffer9.append("Can't get ");
            stringBuffer9.append(this.source);
            stringBuffer9.append(" to ");
            stringBuffer9.append(this.dest);
            throw new BuildException(stringBuffer9.toString(), getLocation());
        }
        FileOutputStream fos = new FileOutputStream(this.dest);
        progress2.beginDownload();
        boolean finished = false;
        try {
            byte[] buffer = new byte[102400];
            while (true) {
                int length = is.read(buffer);
                if (length < 0) {
                    break;
                }
                fos.write(buffer, 0, length);
                progress2.onTick();
            }
            finished = true;
            progress2.endDownload();
            if (this.useTimestamp) {
                long remoteTimestamp = connection.getLastModified();
                if (this.verbose) {
                    Date t2 = new Date(remoteTimestamp);
                    StringBuffer stringBuffer10 = new StringBuffer();
                    stringBuffer10.append("last modified = ");
                    stringBuffer10.append(t2.toString());
                    stringBuffer10.append(remoteTimestamp == 0 ? " - using current time instead" : "");
                    log(stringBuffer10.toString(), logLevel);
                }
                if (remoteTimestamp != 0) {
                    FILE_UTILS.setFileLastModified(this.dest, remoteTimestamp);
                    return true;
                }
                return true;
            }
            return true;
        } finally {
            FileUtils.close(fos);
            FileUtils.close(is);
            if (!finished) {
                this.dest.delete();
            }
        }
    }

    public void setSrc(URL u) {
        this.source = u;
    }

    public void setDest(File dest) {
        this.dest = dest;
    }

    public void setVerbose(boolean v) {
        this.verbose = v;
    }

    public void setIgnoreErrors(boolean v) {
        this.ignoreErrors = v;
    }

    public void setUseTimestamp(boolean v) {
        this.useTimestamp = v;
    }

    public void setUsername(String u) {
        this.uname = u;
    }

    public void setPassword(String p) {
        this.pword = p;
    }

    protected static class Base64Converter {
        public final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

        protected Base64Converter() {
        }

        public String encode(String s) {
            return encode(s.getBytes());
        }

        public String encode(byte[] octetString) {
            char[] out = new char[(((octetString.length - 1) / 3) + 1) * 4];
            int bits6 = 0;
            int outIndex = 0;
            while (outIndex + 3 <= octetString.length) {
                int bits62 = outIndex + 1;
                int i = bits62 + 1;
                int bits24 = ((octetString[outIndex] & 255) << 16) | ((octetString[bits62] & 255) << 8);
                int i2 = i + 1;
                int bits242 = octetString[i] | bits24;
                int bits63 = (bits242 & 16515072) >> 18;
                int outIndex2 = bits6 + 1;
                char[] cArr = this.alphabet;
                out[bits6] = cArr[bits63];
                int bits64 = (bits242 & 258048) >> 12;
                int bits65 = outIndex2 + 1;
                out[outIndex2] = cArr[bits64];
                int bits66 = (bits242 & 4032) >> 6;
                int outIndex3 = bits65 + 1;
                out[bits65] = cArr[bits66];
                int bits67 = bits242 & 63;
                out[outIndex3] = cArr[bits67];
                bits6 = outIndex3 + 1;
                outIndex = i2;
            }
            if (octetString.length - outIndex == 2) {
                int bits243 = ((octetString[outIndex + 1] & 255) << 8) | ((octetString[outIndex] & 255) << 16);
                int bits68 = (bits243 & 16515072) >> 18;
                int bits69 = bits6 + 1;
                char[] cArr2 = this.alphabet;
                out[bits6] = cArr2[bits68];
                int bits610 = (bits243 & 258048) >> 12;
                int outIndex4 = bits69 + 1;
                out[bits69] = cArr2[bits610];
                int bits611 = (bits243 & 4032) >> 6;
                int bits612 = outIndex4 + 1;
                out[outIndex4] = cArr2[bits611];
                int i3 = bits612 + 1;
                out[bits612] = '=';
            } else if (octetString.length - outIndex == 1) {
                int bits244 = (octetString[outIndex] & 255) << 16;
                int bits613 = (bits244 & 16515072) >> 18;
                int bits614 = bits6 + 1;
                char[] cArr3 = this.alphabet;
                out[bits6] = cArr3[bits613];
                int bits615 = (bits244 & 258048) >> 12;
                int outIndex5 = bits614 + 1;
                out[bits614] = cArr3[bits615];
                int outIndex6 = outIndex5 + 1;
                out[outIndex5] = '=';
                int i4 = outIndex6 + 1;
                out[outIndex6] = '=';
            }
            return new String(out);
        }
    }

    public static class NullProgress implements DownloadProgress {
        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void beginDownload() {
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void onTick() {
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void endDownload() {
        }
    }

    public static class VerboseProgress implements DownloadProgress {
        private int dots = 0;
        PrintStream out;

        public VerboseProgress(PrintStream out) {
            this.out = out;
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void beginDownload() {
            this.dots = 0;
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void onTick() {
            this.out.print(".");
            int i = this.dots;
            this.dots = i + 1;
            if (i > 50) {
                this.out.flush();
                this.dots = 0;
            }
        }

        @Override // org.apache.tools.ant.taskdefs.Get.DownloadProgress
        public void endDownload() {
            this.out.println();
            this.out.flush();
        }
    }
}
