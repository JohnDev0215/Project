package com.neusoft.dlt;

/* JADX INFO: loaded from: classes.dex */
public enum Error {
    OK(0, R.string.error_ok),
    NoLogSinkEnabled(1, R.string.error_no_log_sink_enabled),
    MissingFilePermissions(2, R.string.error_log_path_permissions),
    PortAlreadyInUse(3, R.string.error_port_already_in_use),
    LogSinkError(4, R.string.error_logsink_error),
    InvalidLogPath(5, R.string.error_invalid_log_path);

    private int errorCode;
    private int msgId;

    Error(int i, int i2) {
        this.errorCode = i;
        this.msgId = i2;
    }

    public int getMsgId() {
        return this.msgId;
    }

    public Error fromCode(int i) {
        Error[] errorArrValues = values();
        if (i < errorArrValues.length) {
            return errorArrValues[i];
        }
        return null;
    }
}
