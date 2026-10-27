package p;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class t23 {
    static {
        int iMyUid = Process.myUid();
        int iMyPid = Process.myPid();
        StringBuilder sb = new StringBuilder(39);
        sb.append("UID: [");
        sb.append(iMyUid);
        sb.append("]  PID: [");
        sb.append(iMyPid);
        sb.append("] ");
        String string = sb.toString();
        if ("SplitInstallInfoProvider".length() != 0) {
            string.concat("SplitInstallInfoProvider");
        } else {
            new String(string);
        }
    }
}
