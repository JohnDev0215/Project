package org.apache.tools.ant;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class BuildException extends RuntimeException {
    private Throwable cause;
    private Location location;

    public BuildException() {
        this.location = Location.UNKNOWN_LOCATION;
    }

    public BuildException(String message) {
        super(message);
        this.location = Location.UNKNOWN_LOCATION;
    }

    public BuildException(String message, Throwable cause) {
        super(message);
        this.location = Location.UNKNOWN_LOCATION;
        this.cause = cause;
    }

    public BuildException(String msg, Throwable cause, Location location) {
        this(msg, cause);
        this.location = location;
    }

    public BuildException(Throwable cause) {
        super(cause.toString());
        this.location = Location.UNKNOWN_LOCATION;
        this.cause = cause;
    }

    public BuildException(String message, Location location) {
        super(message);
        this.location = Location.UNKNOWN_LOCATION;
        this.location = location;
    }

    public BuildException(Throwable cause, Location location) {
        this(cause);
        this.location = location;
    }

    public Throwable getException() {
        return this.cause;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getException();
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.location.toString());
        stringBuffer.append(getMessage());
        return stringBuffer.toString();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream ps) {
        synchronized (ps) {
            super.printStackTrace(ps);
            if (this.cause != null) {
                ps.println("--- Nested Exception ---");
                this.cause.printStackTrace(ps);
            }
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter pw) {
        synchronized (pw) {
            super.printStackTrace(pw);
            if (this.cause != null) {
                pw.println("--- Nested Exception ---");
                this.cause.printStackTrace(pw);
            }
        }
    }
}
