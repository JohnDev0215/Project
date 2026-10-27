package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;

/* JADX INFO: loaded from: classes4.dex */
public class Archive {
    private boolean deflateHint;
    private String inputFileName;
    private InputStream inputStream;
    private FileOutputStream logFile;
    private int logLevel = 1;
    private String outputFileName;
    private final JarOutputStream outputStream;
    private boolean overrideDeflateHint;
    private boolean removePackFile;

    public Archive(String str, String str2) throws IOException {
        this.inputFileName = str;
        this.outputFileName = str2;
        this.inputStream = new FileInputStream(str);
        this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
    }

    public Archive(InputStream inputStream, JarOutputStream jarOutputStream) throws IOException {
        this.inputStream = inputStream;
        this.outputStream = jarOutputStream;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:0|2|90|3|(2:5|(1:7)(2:8|9))|10|(1:12)(1:13)|14|(1:16)|91|17|(3:19|(2:21|93)(1:94)|22)|92|23|(9:25|(2:26|(4:28|(2:31|29)|95|32)(0))|50|82|51|52|(2:78|54)|55|(3:57|(1:59)|(1:100)(2:62|63))(1:64))(2:33|(10:36|(1:38)(1:39)|40|(1:42)|43|(1:45)|46|(3:96|48|99)(1:98)|97|34))|86|50|82|51|52|(0)|55|(0)(0)|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void unpack() throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.Archive.unpack():void");
    }

    private boolean available(InputStream inputStream) throws IOException {
        inputStream.mark(1);
        int i = inputStream.read();
        inputStream.reset();
        return i != -1;
    }

    public void setRemovePackFile(boolean z) {
        this.removePackFile = z;
    }

    public void setVerbose(boolean z) {
        if (z) {
            this.logLevel = 2;
        } else if (this.logLevel == 2) {
            this.logLevel = 1;
        }
    }

    public void setQuiet(boolean z) {
        if (z) {
            this.logLevel = 0;
        } else if (this.logLevel == 0) {
            this.logLevel = 0;
        }
    }

    public void setLogFile(String str) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str);
    }

    public void setLogFile(String str, boolean z) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str, z);
    }

    public void setDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
