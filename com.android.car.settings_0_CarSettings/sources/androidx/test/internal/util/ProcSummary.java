package androidx.test.internal.util;

import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes5.dex */
public final class ProcSummary {
    public final String cmdline;
    public final String name;
    public final String parent;
    public final String pid;
    public final String realUid;
    public final long startTime;

    private ProcSummary(Builder b) {
        this.name = (String) Checks.checkNotNull(b.name);
        this.pid = (String) Checks.checkNotNull(b.pid);
        this.realUid = (String) Checks.checkNotNull(b.realUid);
        this.parent = (String) Checks.checkNotNull(b.parent);
        this.cmdline = (String) Checks.checkNotNull(b.cmdline);
        this.startTime = b.startTime;
    }

    public static ProcSummary summarize(String pid) {
        File statFile = new File(new File("/proc", pid), "stat");
        String statContent = readToString(statFile);
        File statusFile = new File(new File("/proc", pid), NotificationCompat.CATEGORY_STATUS);
        String statusContent = readToString(statusFile);
        File cmdlineFile = new File(new File("/proc", pid), "cmdline");
        String cmdline = readToString(cmdlineFile);
        return parse(statContent, statusContent, cmdline);
    }

    private static final String readToString(File path) {
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[1024];
        InputStreamReader isr = null;
        try {
            try {
                isr = new InputStreamReader(new FileInputStream(path));
                while (true) {
                    int read = isr.read(buff, 0, buff.length);
                    if (read == -1) {
                        break;
                    }
                    sb.append(buff, 0, read);
                }
                String string = sb.toString();
                try {
                    isr.close();
                } catch (IOException e) {
                }
                return string;
            } catch (IOException ioe) {
                String strValueOf = String.valueOf(path);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 16);
                sb2.append("Could not read: ");
                sb2.append(strValueOf);
                throw new SummaryException(sb2.toString(), ioe);
            } catch (RuntimeException re) {
                String strValueOf2 = String.valueOf(path);
                StringBuilder sb3 = new StringBuilder(String.valueOf(strValueOf2).length() + 15);
                sb3.append("Error reading: ");
                sb3.append(strValueOf2);
                throw new SummaryException(sb3.toString(), re);
            }
        } catch (Throwable th) {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    public static class SummaryException extends RuntimeException {
        public SummaryException(String msg, Throwable cause) {
            super(msg, cause);
        }

        public SummaryException(String msg) {
            super(msg);
        }
    }

    static ProcSummary parse(String statLine, String statusContent, String cmdline) {
        String[] stats = statLine.substring(statLine.lastIndexOf(41) + 2).split(" ", -1);
        String statusContent2 = statusContent.substring(statusContent.indexOf("\nUid:") + 1);
        String[] uids = statusContent2.substring(0, statusContent2.indexOf(10)).split("\\s", -1);
        Builder b = new Builder().withPid(statLine.substring(0, statLine.indexOf(32))).withName(statLine.substring(statLine.indexOf(40) + 1, statLine.lastIndexOf(41))).withParent(stats[1]).withRealUid(uids[1]).withCmdline(cmdline.trim().replace((char) 0, ' ')).withStartTime(Long.parseLong(stats[19]));
        return b.build();
    }

    static class Builder {
        private String cmdline;
        private String name;
        private String parent;
        private String pid;
        private String realUid;
        private long startTime;

        Builder() {
        }

        Builder withParent(String ppid) {
            try {
                Integer.parseInt(ppid);
                this.parent = ppid;
                return this;
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(ppid);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "not a pid: ".concat(strValueOf) : new String("not a pid: "));
            }
        }

        Builder withCmdline(String cmdline) {
            this.cmdline = cmdline;
            return this;
        }

        Builder withName(String name) {
            this.name = name;
            return this;
        }

        Builder withPid(String pid) {
            try {
                Integer.parseInt(pid);
                this.pid = pid;
                return this;
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(pid);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "not a pid: ".concat(strValueOf) : new String("not a pid: "));
            }
        }

        Builder withRealUid(String uid) {
            try {
                Integer.parseInt(uid);
                this.realUid = uid;
                return this;
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(uid);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "not a uid: ".concat(strValueOf) : new String("not a uid: "));
            }
        }

        Builder withStartTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        ProcSummary build() {
            return new ProcSummary(this);
        }
    }

    public String toString() {
        return String.format("ProcSummary(name: '%s', cmdline: '%s', pid: '%s', parent: '%s', realUid: '%s', startTime: %d)", this.name, this.cmdline, this.pid, this.parent, this.realUid, Long.valueOf(this.startTime));
    }

    public int hashCode() {
        return this.pid.hashCode();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof ProcSummary)) {
            return false;
        }
        ProcSummary ops = (ProcSummary) o;
        return ops.name.equals(this.name) && ops.pid.equals(this.pid) && ops.parent.equals(this.parent) && ops.realUid.equals(this.realUid) && ops.cmdline.equals(this.cmdline) && ops.startTime == this.startTime;
    }
}
