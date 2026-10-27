package org.apache.tools.ant.taskdefs.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.tools.ant.BuildException;
import sun.misc.UUEncoder;

/* JADX INFO: loaded from: classes.dex */
class UUMailer extends PlainMailer {
    UUMailer() {
    }

    @Override // org.apache.tools.ant.taskdefs.email.PlainMailer
    protected void attach(File file, PrintStream out) throws IOException {
        if (!file.exists() || !file.canRead()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("File \"");
            stringBuffer.append(file.getName());
            stringBuffer.append("\" does not exist or is not ");
            stringBuffer.append("readable.");
            throw new BuildException(stringBuffer.toString());
        }
        FileInputStream finstr = new FileInputStream(file);
        try {
            BufferedInputStream in = new BufferedInputStream(finstr);
            UUEncoder encoder = new UUEncoder(file.getName());
            encoder.encode(in, out);
        } finally {
            finstr.close();
        }
    }
}
