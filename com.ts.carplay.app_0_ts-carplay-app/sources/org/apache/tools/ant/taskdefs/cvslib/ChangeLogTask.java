package org.apache.tools.ant.taskdefs.cvslib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.DateUtils;

/* JADX INFO: loaded from: classes.dex */
public class ChangeLogTask extends AbstractCvsTask {
    private File m_destfile;
    private File m_dir;
    private Date m_start;
    private Date m_stop;
    private File m_usersFile;
    private Vector m_cvsUsers = new Vector();
    private final Vector m_filesets = new Vector();

    public void setDir(File dir) {
        this.m_dir = dir;
    }

    public void setDestfile(File destfile) {
        this.m_destfile = destfile;
    }

    public void setUsersfile(File usersFile) {
        this.m_usersFile = usersFile;
    }

    public void addUser(CvsUser user) {
        this.m_cvsUsers.addElement(user);
    }

    public void setStart(Date start) {
        this.m_start = start;
    }

    public void setEnd(Date stop) {
        this.m_stop = stop;
    }

    public void setDaysinpast(int days) {
        long time = System.currentTimeMillis() - ((((((long) days) * 24) * 60) * 60) * 1000);
        setStart(new Date(time));
    }

    public void addFileset(FileSet fileSet) {
        this.m_filesets.addElement(fileSet);
    }

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File savedDir = this.m_dir;
        try {
            validate();
            Properties userList = new Properties();
            loadUserlist(userList);
            Enumeration e = this.m_cvsUsers.elements();
            while (e.hasMoreElements()) {
                CvsUser user = (CvsUser) e.nextElement();
                user.validate();
                userList.put(user.getUserID(), user.getDisplayname());
            }
            setCommand("log");
            if (getTag() != null) {
                CvsVersion myCvsVersion = new CvsVersion();
                myCvsVersion.setProject(getProject());
                myCvsVersion.setTaskName("cvsversion");
                myCvsVersion.setCvsRoot(getCvsRoot());
                myCvsVersion.setCvsRsh(getCvsRsh());
                myCvsVersion.setPassfile(getPassFile());
                myCvsVersion.setDest(this.m_dir);
                myCvsVersion.execute();
                if (myCvsVersion.supportsCvsLogWithSOption()) {
                    addCommandArgument("-S");
                }
            }
            if (this.m_start != null) {
                SimpleDateFormat outputDate = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(">=");
                stringBuffer.append(outputDate.format(this.m_start));
                String dateRange = stringBuffer.toString();
                addCommandArgument("-d");
                addCommandArgument(dateRange);
            }
            if (!this.m_filesets.isEmpty()) {
                Enumeration e2 = this.m_filesets.elements();
                while (e2.hasMoreElements()) {
                    FileSet fileSet = (FileSet) e2.nextElement();
                    DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
                    String[] files = scanner.getIncludedFiles();
                    for (String str : files) {
                        addCommandArgument(str);
                    }
                }
            }
            ChangeLogParser parser = new ChangeLogParser();
            RedirectingStreamHandler handler = new RedirectingStreamHandler(parser);
            log(getCommand(), 3);
            setDest(this.m_dir);
            setExecuteStreamHandler(handler);
            try {
                super.execute();
                CVSEntry[] entrySet = parser.getEntrySetAsArray();
                CVSEntry[] filteredEntrySet = filterEntrySet(entrySet);
                replaceAuthorIdWithName(userList, filteredEntrySet);
                writeChangeLog(filteredEntrySet);
            } finally {
                String errors = handler.getErrors();
                if (errors != null) {
                    log(errors, 0);
                }
            }
        } finally {
            this.m_dir = savedDir;
        }
    }

    private void validate() throws BuildException {
        if (this.m_dir == null) {
            this.m_dir = getProject().getBaseDir();
        }
        if (this.m_destfile == null) {
            throw new BuildException("Destfile must be set.");
        }
        if (!this.m_dir.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot find base dir ");
            stringBuffer.append(this.m_dir.getAbsolutePath());
            String message = stringBuffer.toString();
            throw new BuildException(message);
        }
        File file = this.m_usersFile;
        if (file != null && !file.exists()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Cannot find user lookup list ");
            stringBuffer2.append(this.m_usersFile.getAbsolutePath());
            String message2 = stringBuffer2.toString();
            throw new BuildException(message2);
        }
    }

    private void loadUserlist(Properties userList) throws BuildException {
        if (this.m_usersFile != null) {
            try {
                userList.load(new FileInputStream(this.m_usersFile));
            } catch (IOException ioe) {
                throw new BuildException(ioe.toString(), ioe);
            }
        }
    }

    private CVSEntry[] filterEntrySet(CVSEntry[] entrySet) {
        Date date;
        Vector results = new Vector();
        for (CVSEntry cvsEntry : entrySet) {
            Date date2 = cvsEntry.getDate();
            Date date3 = this.m_start;
            if ((date3 == null || !date3.after(date2)) && ((date = this.m_stop) == null || !date.before(date2))) {
                results.addElement(cvsEntry);
            }
        }
        CVSEntry[] resultArray = new CVSEntry[results.size()];
        results.copyInto(resultArray);
        return resultArray;
    }

    private void replaceAuthorIdWithName(Properties userList, CVSEntry[] entrySet) {
        for (CVSEntry entry : entrySet) {
            if (userList.containsKey(entry.getAuthor())) {
                entry.setAuthor(userList.getProperty(entry.getAuthor()));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void writeChangeLog(org.apache.tools.ant.taskdefs.cvslib.CVSEntry[] r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            java.io.File r3 = r7.m_destfile     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            r0 = r2
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            java.lang.String r4 = "UTF-8"
            r3.<init>(r0, r4)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L32 java.io.UnsupportedEncodingException -> L42
            org.apache.tools.ant.taskdefs.cvslib.ChangeLogWriter r3 = new org.apache.tools.ant.taskdefs.cvslib.ChangeLogWriter     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b java.io.UnsupportedEncodingException -> L42
            r3.<init>()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b java.io.UnsupportedEncodingException -> L42
            r1 = r3
            r1.printChangeLog(r2, r8)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2b java.io.UnsupportedEncodingException -> L42
            r0.close()     // Catch: java.io.IOException -> L25
            goto L27
        L25:
            r3 = move-exception
        L27:
            goto L59
        L29:
            r3 = move-exception
            goto L5b
        L2b:
            r1 = move-exception
            goto L36
        L2d:
            r3 = move-exception
            r2 = r1
            r6 = r2
            r1 = r6
            goto L5b
        L32:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L36:
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L40
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L40
            r3.<init>(r4, r1)     // Catch: java.lang.Throwable -> L40
            throw r3     // Catch: java.lang.Throwable -> L40
        L40:
            r3 = move-exception
            goto L5b
        L42:
            r2 = move-exception
            org.apache.tools.ant.Project r3 = r7.getProject()     // Catch: java.lang.Throwable -> L5a
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L5a
            r5 = 0
            r3.log(r4, r5)     // Catch: java.lang.Throwable -> L5a
            if (r0 == 0) goto L57
            r0.close()     // Catch: java.io.IOException -> L56
            goto L57
        L56:
            r3 = move-exception
        L57:
        L59:
            return
        L5a:
            r3 = move-exception
        L5b:
            if (r0 == 0) goto L62
            r0.close()     // Catch: java.io.IOException -> L61
            goto L62
        L61:
            r4 = move-exception
        L62:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.cvslib.ChangeLogTask.writeChangeLog(org.apache.tools.ant.taskdefs.cvslib.CVSEntry[]):void");
    }
}
