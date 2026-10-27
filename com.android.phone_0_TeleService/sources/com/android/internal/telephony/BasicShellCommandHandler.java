package com.android.internal.telephony;

import android.os.Binder;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasicShellCommandHandler {
    private int mArgPos;
    private String[] mArgs;
    private String mCmd;
    private String mCurArgData;
    private FileDescriptor mErr;
    private PrintWriter mErrPrintWriter;
    private FileOutputStream mFileErr;
    private FileOutputStream mFileOut;
    private FileDescriptor mOut;
    private PrintWriter mOutPrintWriter;
    private Binder mTarget;

    public abstract int onCommand(String str);

    public abstract void onHelp();

    public void init(Binder binder, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, int i) {
        this.mTarget = binder;
        this.mOut = fileDescriptor2;
        this.mErr = fileDescriptor3;
        this.mArgs = strArr;
        this.mCmd = null;
        this.mArgPos = i;
        this.mCurArgData = null;
        this.mFileOut = null;
        this.mFileErr = null;
        this.mOutPrintWriter = null;
        this.mErrPrintWriter = null;
    }

    public int exec(Binder binder, FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr) {
        int i;
        String str;
        StringBuilder sb;
        if (strArr == null || strArr.length <= 0) {
            i = 0;
            str = null;
        } else {
            str = strArr[0];
            i = 1;
        }
        init(binder, fileDescriptor, fileDescriptor2, fileDescriptor3, strArr, i);
        this.mCmd = str;
        RuntimeException runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.e("ShellCommand", "Starting command " + this.mCmd + " on " + this.mTarget, runtimeException);
        Log.e("ShellCommand", "Calling uid=" + Binder.getCallingUid() + " pid=" + Binder.getCallingPid());
        int iOnCommand = -1;
        try {
            iOnCommand = onCommand(this.mCmd);
            Log.e("ShellCommand", "Executed command " + this.mCmd + " on " + this.mTarget);
            Log.e("ShellCommand", "Flushing output streams on " + this.mTarget);
            PrintWriter printWriter = this.mOutPrintWriter;
            if (printWriter != null) {
                printWriter.flush();
            }
            PrintWriter printWriter2 = this.mErrPrintWriter;
            if (printWriter2 != null) {
                printWriter2.flush();
            }
            sb = new StringBuilder();
        } catch (Throwable th) {
            try {
                PrintWriter errPrintWriter = getErrPrintWriter();
                errPrintWriter.println();
                errPrintWriter.println("Exception occurred while executing '" + this.mCmd + "':");
                th.printStackTrace(errPrintWriter);
                Log.e("ShellCommand", "Flushing output streams on " + this.mTarget);
                PrintWriter printWriter3 = this.mOutPrintWriter;
                if (printWriter3 != null) {
                    printWriter3.flush();
                }
                PrintWriter printWriter4 = this.mErrPrintWriter;
                if (printWriter4 != null) {
                    printWriter4.flush();
                }
                sb = new StringBuilder();
            } catch (Throwable th2) {
                Log.e("ShellCommand", "Flushing output streams on " + this.mTarget);
                PrintWriter printWriter5 = this.mOutPrintWriter;
                if (printWriter5 != null) {
                    printWriter5.flush();
                }
                PrintWriter printWriter6 = this.mErrPrintWriter;
                if (printWriter6 != null) {
                    printWriter6.flush();
                }
                Log.e("ShellCommand", "Sending command result on " + this.mTarget);
                throw th2;
            }
        }
        sb.append("Sending command result on ");
        sb.append(this.mTarget);
        Log.e("ShellCommand", sb.toString());
        Log.e("ShellCommand", "Finished command " + this.mCmd + " on " + this.mTarget);
        return iOnCommand;
    }

    public OutputStream getRawOutputStream() {
        if (this.mFileOut == null) {
            this.mFileOut = new FileOutputStream(this.mOut);
        }
        return this.mFileOut;
    }

    public PrintWriter getOutPrintWriter() {
        if (this.mOutPrintWriter == null) {
            this.mOutPrintWriter = new PrintWriter(getRawOutputStream());
        }
        return this.mOutPrintWriter;
    }

    public OutputStream getRawErrorStream() {
        if (this.mFileErr == null) {
            this.mFileErr = new FileOutputStream(this.mErr);
        }
        return this.mFileErr;
    }

    public PrintWriter getErrPrintWriter() {
        if (this.mErr == null) {
            return getOutPrintWriter();
        }
        if (this.mErrPrintWriter == null) {
            this.mErrPrintWriter = new PrintWriter(getRawErrorStream());
        }
        return this.mErrPrintWriter;
    }

    public String getNextOption() {
        if (this.mCurArgData != null) {
            throw new IllegalArgumentException("No argument expected after \"" + this.mArgs[this.mArgPos - 1] + "\"");
        }
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i >= strArr.length) {
            return null;
        }
        String str = strArr[i];
        if (!str.startsWith("-")) {
            return null;
        }
        this.mArgPos++;
        if (str.equals("--")) {
            return null;
        }
        if (str.length() > 1 && str.charAt(1) != '-') {
            if (str.length() > 2) {
                this.mCurArgData = str.substring(2);
                return str.substring(0, 2);
            }
            this.mCurArgData = null;
            return str;
        }
        this.mCurArgData = null;
        return str;
    }

    public String getNextArg() {
        String str = this.mCurArgData;
        if (str != null) {
            this.mCurArgData = null;
            return str;
        }
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i >= strArr.length) {
            return null;
        }
        this.mArgPos = i + 1;
        return strArr[i];
    }

    public String peekNextArg() {
        String str = this.mCurArgData;
        if (str != null) {
            return str;
        }
        int i = this.mArgPos;
        String[] strArr = this.mArgs;
        if (i < strArr.length) {
            return strArr[i];
        }
        return null;
    }

    public String getNextArgRequired() {
        String nextArg = getNextArg();
        if (nextArg != null) {
            return nextArg;
        }
        throw new IllegalArgumentException("Argument expected after \"" + this.mArgs[this.mArgPos - 1] + "\"");
    }

    public int handleDefaultCommands(String str) {
        if (str == null || "help".equals(str) || "-h".equals(str)) {
            onHelp();
            return -1;
        }
        getOutPrintWriter().println("Unknown command: " + str);
        return -1;
    }
}
