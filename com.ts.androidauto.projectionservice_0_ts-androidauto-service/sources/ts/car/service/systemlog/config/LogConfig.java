package ts.car.service.systemlog.config;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes2.dex */
public class LogConfig {
    public boolean isAbsolutePath;
    public int logFileNum;
    public int logFileSize;
    public int logLevel;
    public String apid = AndroidAutoConst.DEFAULT_SCREEN_NAME;
    public String ctid = AndroidAutoConst.DEFAULT_SCREEN_NAME;
    public String logLoopback = AndroidAutoConst.DEFAULT_SCREEN_NAME;
    public String logPath = AndroidAutoConst.DEFAULT_SCREEN_NAME;

    public String toString() {
        return "ProviderConfig{apid='" + this.apid + "', ctid='" + this.ctid + "', logFileSize=" + this.logFileSize + ", logFileNum=" + this.logFileNum + ", logLevel=" + this.logLevel + ", logLoopback='" + this.logLoopback + "', logPath='" + this.logPath + "', isAbsolutePath=" + this.isAbsolutePath + '}';
    }
}
