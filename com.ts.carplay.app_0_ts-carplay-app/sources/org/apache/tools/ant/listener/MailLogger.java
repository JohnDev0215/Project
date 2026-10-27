package org.apache.tools.ant.listener;

import com.iflytek.speech.libissmvw;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.email.EmailAddress;
import org.apache.tools.ant.taskdefs.email.Mailer;
import org.apache.tools.ant.taskdefs.email.Message;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.ant.util.StringUtils;
import org.apache.tools.mail.MailMessage;

/* JADX INFO: loaded from: classes.dex */
public class MailLogger extends DefaultLogger {
    private StringBuffer buffer = new StringBuffer();

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent event) {
        MailLogger mailLogger = this;
        String str = "";
        super.buildFinished(event);
        Project project = event.getProject();
        Hashtable properties = project.getProperties();
        Properties fileProperties = new Properties();
        String filename = (String) properties.get("MailLogger.properties.file");
        String str2 = null;
        if (filename != null) {
            InputStream is = null;
            try {
                is = new FileInputStream(filename);
                fileProperties.load(is);
                try {
                    is.close();
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th) {
                if (is == null) {
                    throw th;
                }
                try {
                    is.close();
                    throw th;
                } catch (IOException e4) {
                    throw th;
                }
            }
        }
        Enumeration e5 = fileProperties.keys();
        while (e5.hasMoreElements()) {
            String key = (String) e5.nextElement();
            String value = fileProperties.getProperty(key);
            properties.put(key, project.replaceProperties(value));
            mailLogger = this;
            str = str;
            str2 = str2;
        }
        boolean success = event.getException() == null;
        String prefix = success ? "success" : "failure";
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(prefix);
            stringBuffer.append(".notify");
            boolean notify = Project.toBoolean(mailLogger.getValue(properties, stringBuffer.toString(), libissmvw.ISS_MVW_PARAM_VALUE_ON));
            if (!notify) {
                return;
            }
            String mailhost = mailLogger.getValue(properties, "mailhost", MailMessage.DEFAULT_HOST);
            int port = Integer.parseInt(mailLogger.getValue(properties, "port", String.valueOf(25)));
            String user = mailLogger.getValue(properties, "user", str);
            String password = mailLogger.getValue(properties, "password", str);
            boolean ssl = Project.toBoolean(mailLogger.getValue(properties, "ssl", libissmvw.ISS_MVW_PARAM_VALUE_OFF));
            String from = mailLogger.getValue(properties, "from", str2);
            String replytoList = mailLogger.getValue(properties, "replyto", str);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(prefix);
            stringBuffer2.append(".to");
            String toList = mailLogger.getValue(properties, stringBuffer2.toString(), null);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(prefix);
            stringBuffer3.append(".subject");
            String subject = mailLogger.getValue(properties, stringBuffer3.toString(), success ? "Build Success" : "Build Failure");
            if (!user.equals(str)) {
                sendMimeMail(event.getProject(), mailhost, port, user, password, ssl, from, replytoList, toList, subject, mailLogger.buffer.substring(0));
                return;
            }
            try {
                if (password.equals(str) && !ssl) {
                    sendMail(mailhost, port, from, replytoList, toList, subject, mailLogger.buffer.substring(0));
                    return;
                }
                try {
                    sendMimeMail(event.getProject(), mailhost, port, user, password, ssl, from, replytoList, toList, subject, mailLogger.buffer.substring(0));
                    return;
                } catch (Exception e6) {
                    e = e6;
                }
            } catch (Exception e7) {
                e = e7;
            }
        } catch (Exception e8) {
            e = e8;
        }
        System.out.println("MailLogger failed to send e-mail!");
        e.printStackTrace(System.err);
    }

    @Override // org.apache.tools.ant.DefaultLogger
    protected void log(String message) {
        StringBuffer stringBuffer = this.buffer;
        stringBuffer.append(message);
        stringBuffer.append(StringUtils.LINE_SEP);
    }

    private String getValue(Hashtable properties, String name, String defaultValue) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("MailLogger.");
        stringBuffer.append(name);
        String propertyName = stringBuffer.toString();
        String value = (String) properties.get(propertyName);
        if (value == null) {
            value = defaultValue;
        }
        if (value == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Missing required parameter: ");
            stringBuffer2.append(propertyName);
            throw new Exception(stringBuffer2.toString());
        }
        return value;
    }

    private void sendMail(String mailhost, int port, String from, String replyToList, String toList, String subject, String message) throws IOException {
        MailMessage mailMessage = new MailMessage(mailhost, port);
        mailMessage.setHeader("Date", DateUtils.getDateForHeader());
        mailMessage.from(from);
        if (!replyToList.equals("")) {
            StringTokenizer t = new StringTokenizer(replyToList, ", ", false);
            while (t.hasMoreTokens()) {
                mailMessage.replyto(t.nextToken());
            }
        }
        StringTokenizer t2 = new StringTokenizer(toList, ", ", false);
        while (t2.hasMoreTokens()) {
            mailMessage.to(t2.nextToken());
        }
        mailMessage.setSubject(subject);
        PrintStream ps = mailMessage.getPrintStream();
        ps.println(message);
        mailMessage.sendAndClose();
    }

    private void sendMimeMail(Project project, String host, int port, String user, String password, boolean ssl, String from, String replyToString, String toString, String subject, String message) {
        try {
            Mailer mailer = (Mailer) Class.forName("org.apache.tools.ant.taskdefs.email.MimeMailer").newInstance();
            Vector replyToList = vectorizeEmailAddresses(replyToString);
            mailer.setHost(host);
            mailer.setPort(port);
            mailer.setUser(user);
            mailer.setPassword(password);
            mailer.setSSL(ssl);
            Message mymessage = new Message(message);
            mymessage.setProject(project);
            mailer.setMessage(mymessage);
            mailer.setFrom(new EmailAddress(from));
            mailer.setReplyToList(replyToList);
            Vector toList = vectorizeEmailAddresses(toString);
            mailer.setToList(toList);
            mailer.setCcList(new Vector());
            mailer.setBccList(new Vector());
            mailer.setFiles(new Vector());
            mailer.setSubject(subject);
            mailer.send();
        } catch (Throwable e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to initialise MIME mail: ");
            stringBuffer.append(e.getMessage());
            log(stringBuffer.toString());
        }
    }

    private Vector vectorizeEmailAddresses(String listString) {
        Vector emailList = new Vector();
        StringTokenizer tokens = new StringTokenizer(listString, ",");
        while (tokens.hasMoreTokens()) {
            emailList.addElement(new EmailAddress(tokens.nextToken()));
        }
        return emailList;
    }
}
