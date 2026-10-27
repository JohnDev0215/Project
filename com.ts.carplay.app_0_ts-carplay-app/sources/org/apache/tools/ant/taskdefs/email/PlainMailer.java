package org.apache.tools.ant.taskdefs.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.mail.MailMessage;

/* JADX INFO: loaded from: classes.dex */
class PlainMailer extends Mailer {
    PlainMailer() {
    }

    @Override // org.apache.tools.ant.taskdefs.email.Mailer
    public void send() {
        try {
            MailMessage mailMessage = new MailMessage(this.host, this.port);
            mailMessage.from(this.from.toString());
            Enumeration e = this.replyToList.elements();
            while (e.hasMoreElements()) {
                mailMessage.replyto(e.nextElement().toString());
            }
            Enumeration e2 = this.toList.elements();
            while (e2.hasMoreElements()) {
                mailMessage.to(e2.nextElement().toString());
            }
            Enumeration e3 = this.ccList.elements();
            while (e3.hasMoreElements()) {
                mailMessage.cc(e3.nextElement().toString());
            }
            Enumeration e4 = this.bccList.elements();
            while (e4.hasMoreElements()) {
                mailMessage.bcc(e4.nextElement().toString());
            }
            if (this.subject != null) {
                mailMessage.setSubject(this.subject);
            }
            mailMessage.setHeader("Date", getDate());
            if (this.message.getCharset() != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.message.getMimeType());
                stringBuffer.append("; charset=\"");
                stringBuffer.append(this.message.getCharset());
                stringBuffer.append("\"");
                mailMessage.setHeader("Content-Type", stringBuffer.toString());
            } else {
                mailMessage.setHeader("Content-Type", this.message.getMimeType());
            }
            PrintStream out = mailMessage.getPrintStream();
            this.message.print(out);
            Enumeration e5 = this.files.elements();
            while (e5.hasMoreElements()) {
                File file = (File) e5.nextElement();
                attach(file, out);
            }
            mailMessage.sendAndClose();
        } catch (IOException ioe) {
            throw new BuildException("IO error sending mail", ioe);
        }
    }

    protected void attach(File file, PrintStream printStream) throws IOException {
        if (!file.exists() || !file.canRead()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("File \"");
            stringBuffer.append(file.getName());
            stringBuffer.append("\" does not exist or is not ");
            stringBuffer.append("readable.");
            throw new BuildException(stringBuffer.toString());
        }
        if (this.includeFileNames) {
            printStream.println();
            String name = file.getName();
            int length = name.length();
            printStream.println(name);
            for (int i = 0; i < length; i++) {
                printStream.print('=');
            }
            printStream.println();
        }
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 1024);
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 != -1) {
                    printStream.write(bArr, 0, i2);
                } else {
                    return;
                }
            }
        } finally {
            fileInputStream.close();
        }
    }
}
