package com.neusoft.na.dltdroid;

import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Error.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/neusoft/na/dltdroid/Error;", "", "errorCode", "", "msgId", "(Ljava/lang/String;III)V", "getErrorCode", "()I", "getMsgId", ResultCodeEnum.OK, "NoLogSinkEnabled", "MissingFilePermissions", "PortAlreadyInUse", "LogSinkError", "InvalidLogPath", "Companion", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum Error {
    OK(0, R.string.error_ok),
    NoLogSinkEnabled(1, R.string.error_no_log_sink_enabled),
    MissingFilePermissions(2, R.string.error_log_path_permissions),
    PortAlreadyInUse(3, R.string.error_port_already_in_use),
    LogSinkError(4, R.string.error_logsink_error),
    InvalidLogPath(5, R.string.error_invalid_log_path);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int errorCode;
    private final int msgId;

    Error(int i, int i2) {
        this.errorCode = i;
        this.msgId = i2;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final int getMsgId() {
        return this.msgId;
    }

    /* JADX INFO: compiled from: Error.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/dltdroid/Error$Companion;", "", "()V", "fromCode", "Lcom/neusoft/na/dltdroid/Error;", "errorCode", "", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Error fromCode(int errorCode) {
            for (Error error : Error.values()) {
                if (error.getErrorCode() == errorCode) {
                    return error;
                }
            }
            return null;
        }
    }
}
