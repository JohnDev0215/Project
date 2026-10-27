package org.apache.tools.ant.taskdefs.email;

import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.mail.MailMessage;

/* JADX INFO: loaded from: classes.dex */
public class EmailTask extends Task {
    public static final String AUTO = "auto";
    public static final String MIME = "mime";
    public static final String PLAIN = "plain";
    public static final String UU = "uu";
    private String encoding = AUTO;
    private String host = MailMessage.DEFAULT_HOST;
    private int port = 25;
    private String subject = null;
    private Message message = null;
    private boolean failOnError = true;
    private boolean includeFileNames = false;
    private String messageMimeType = null;
    private EmailAddress from = null;
    private Vector replyToList = new Vector();
    private Vector toList = new Vector();
    private Vector ccList = new Vector();
    private Vector bccList = new Vector();
    private Vector files = new Vector();
    private Vector filesets = new Vector();
    private String charset = null;
    private String user = null;
    private String password = null;
    private boolean SSL = false;

    public static class Encoding extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{EmailTask.AUTO, EmailTask.MIME, EmailTask.UU, EmailTask.PLAIN};
        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSSL(boolean SSL) {
        this.SSL = SSL;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding.getValue();
    }

    public void setMailport(int port) {
        this.port = port;
    }

    public void setMailhost(String host) {
        this.host = host;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        if (this.message != null) {
            throw new BuildException("Only one message can be sent in an email");
        }
        Message message2 = new Message(message);
        this.message = message2;
        message2.setProject(getProject());
    }

    public void setMessageFile(File file) {
        if (this.message != null) {
            throw new BuildException("Only one message can be sent in an email");
        }
        Message message = new Message(file);
        this.message = message;
        message.setProject(getProject());
    }

    public void setMessageMimeType(String type) {
        this.messageMimeType = type;
    }

    public void addMessage(Message message) throws BuildException {
        if (this.message != null) {
            throw new BuildException("Only one message can be sent in an email");
        }
        this.message = message;
    }

    public void addFrom(EmailAddress address) {
        if (this.from != null) {
            throw new BuildException("Emails can only be from one address");
        }
        this.from = address;
    }

    public void setFrom(String address) {
        if (this.from != null) {
            throw new BuildException("Emails can only be from one address");
        }
        this.from = new EmailAddress(address);
    }

    public void addReplyTo(EmailAddress address) {
        this.replyToList.add(address);
    }

    public void setReplyTo(String address) {
        this.replyToList.add(new EmailAddress(address));
    }

    public void addTo(EmailAddress address) {
        this.toList.addElement(address);
    }

    public void setToList(String list) {
        StringTokenizer tokens = new StringTokenizer(list, ",");
        while (tokens.hasMoreTokens()) {
            this.toList.addElement(new EmailAddress(tokens.nextToken()));
        }
    }

    public void addCc(EmailAddress address) {
        this.ccList.addElement(address);
    }

    public void setCcList(String list) {
        StringTokenizer tokens = new StringTokenizer(list, ",");
        while (tokens.hasMoreTokens()) {
            this.ccList.addElement(new EmailAddress(tokens.nextToken()));
        }
    }

    public void addBcc(EmailAddress address) {
        this.bccList.addElement(address);
    }

    public void setBccList(String list) {
        StringTokenizer tokens = new StringTokenizer(list, ",");
        while (tokens.hasMoreTokens()) {
            this.bccList.addElement(new EmailAddress(tokens.nextToken()));
        }
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public void setFiles(String filenames) {
        StringTokenizer t = new StringTokenizer(filenames, ", ");
        while (t.hasMoreTokens()) {
            this.files.addElement(getProject().resolveFile(t.nextToken()));
        }
    }

    public void addFileset(FileSet fs) {
        this.filesets.addElement(fs);
    }

    public void setIncludefilenames(boolean includeFileNames) {
        this.includeFileNames = includeFileNames;
    }

    public boolean getIncludeFileNames() {
        return this.includeFileNames;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        boolean z;
        Mailer mailer;
        Throwable th;
        Mailer mailer2;
        Message message = this.message;
        Vector vector = (Vector) this.files.clone();
        Mailer plainMailer = null;
        try {
            if (this.encoding.equals(MIME) || this.encoding.equals(AUTO)) {
                try {
                    mailer = (Mailer) Class.forName("org.apache.tools.ant.taskdefs.email.MimeMailer").newInstance();
                    try {
                        log("Using MIME mail", 3);
                        z = true;
                        plainMailer = mailer;
                    } catch (Throwable th2) {
                        th = th2;
                        z = true;
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Failed to initialise MIME mail: ");
                        stringBuffer.append(th.getMessage());
                        log(stringBuffer.toString(), 1);
                        plainMailer = mailer;
                    }
                } catch (Throwable th3) {
                    z = false;
                    mailer = null;
                    th = th3;
                }
            } else {
                z = false;
            }
            if (!z && ((this.user != null || this.password != null) && (this.encoding.equals(UU) || this.encoding.equals(PLAIN)))) {
                throw new BuildException("SMTP auth only possible with MIME mail");
            }
            if (!z && this.SSL && (this.encoding.equals(UU) || this.encoding.equals(PLAIN))) {
                throw new BuildException("SSL only possible with MIME mail");
            }
            if (this.encoding.equals(UU) || (this.encoding.equals(AUTO) && !z)) {
                try {
                    mailer2 = (Mailer) Class.forName("org.apache.tools.ant.taskdefs.email.UUMailer").newInstance();
                } catch (Throwable th4) {
                }
                try {
                    log("Using UU mail", 3);
                    z = true;
                    plainMailer = mailer2;
                } catch (Throwable th5) {
                    z = true;
                    plainMailer = mailer2;
                    log("Failed to initialise UU mail", 1);
                }
            }
            if (this.encoding.equals(PLAIN) || (this.encoding.equals(AUTO) && !z)) {
                plainMailer = new PlainMailer();
                log("Using plain mail", 3);
            }
            if (plainMailer == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Failed to initialise encoding: ");
                stringBuffer2.append(this.encoding);
                throw new BuildException(stringBuffer2.toString());
            }
            if (this.message == null) {
                Message message2 = new Message();
                this.message = message2;
                message2.setProject(getProject());
            }
            if (this.from == null || this.from.getAddress() == null) {
                throw new BuildException("A from element is required");
            }
            if (this.toList.isEmpty() && this.ccList.isEmpty() && this.bccList.isEmpty()) {
                throw new BuildException("At least one of to,cc or bcc must be supplied");
            }
            if (this.messageMimeType != null) {
                if (this.message.isMimeTypeSpecified()) {
                    throw new BuildException("The mime type can only be specified in one location");
                }
                this.message.setMimeType(this.messageMimeType);
            }
            if (this.charset != null) {
                if (this.message.getCharset() != null) {
                    throw new BuildException("The charset can only be specified in one location");
                }
                this.message.setCharset(this.charset);
            }
            Enumeration enumerationElements = this.filesets.elements();
            while (enumerationElements.hasMoreElements()) {
                DirectoryScanner directoryScanner = ((FileSet) enumerationElements.nextElement()).getDirectoryScanner(getProject());
                String[] includedFiles = directoryScanner.getIncludedFiles();
                File basedir = directoryScanner.getBasedir();
                for (String str : includedFiles) {
                    this.files.addElement(new File(basedir, str));
                }
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Sending email: ");
            stringBuffer3.append(this.subject);
            log(stringBuffer3.toString(), 2);
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("From ");
            stringBuffer4.append(this.from);
            log(stringBuffer4.toString(), 3);
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("ReplyTo ");
            stringBuffer5.append(this.replyToList);
            log(stringBuffer5.toString(), 3);
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("To ");
            stringBuffer6.append(this.toList);
            log(stringBuffer6.toString(), 3);
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append("Cc ");
            stringBuffer7.append(this.ccList);
            log(stringBuffer7.toString(), 3);
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("Bcc ");
            stringBuffer8.append(this.bccList);
            log(stringBuffer8.toString(), 3);
            plainMailer.setHost(this.host);
            plainMailer.setPort(this.port);
            plainMailer.setUser(this.user);
            plainMailer.setPassword(this.password);
            plainMailer.setSSL(this.SSL);
            plainMailer.setMessage(this.message);
            plainMailer.setFrom(this.from);
            plainMailer.setReplyToList(this.replyToList);
            plainMailer.setToList(this.toList);
            plainMailer.setCcList(this.ccList);
            plainMailer.setBccList(this.bccList);
            plainMailer.setFiles(this.files);
            plainMailer.setSubject(this.subject);
            plainMailer.setTask(this);
            plainMailer.setIncludeFileNames(this.includeFileNames);
            plainMailer.send();
            int size = this.files.size();
            StringBuffer stringBuffer9 = new StringBuffer();
            stringBuffer9.append("Sent email with ");
            stringBuffer9.append(size);
            stringBuffer9.append(" attachment");
            stringBuffer9.append(size == 1 ? "" : "s");
            log(stringBuffer9.toString(), 2);
        } catch (Exception e) {
            log("Failed to send email", 1);
            if (this.failOnError) {
                throw new BuildException(e);
            }
        } catch (BuildException e2) {
            log("Failed to send email", 1);
            if (this.failOnError) {
                throw e2;
            }
        } finally {
            this.message = message;
            this.files = vector;
        }
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return this.charset;
    }
}
