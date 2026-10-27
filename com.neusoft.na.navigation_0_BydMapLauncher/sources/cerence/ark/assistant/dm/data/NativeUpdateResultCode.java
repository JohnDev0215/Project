package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public enum NativeUpdateResultCode {
    UPDATE_OK,
    UPDATE_STOPPED,
    UPDATE_CONNECTION_ERROR,
    UPDATE_REMOTE_ERROR,
    UPDATE_CHECKSUM_ERROR,
    UPDATE_FILESYSTEM_ERROR,
    UPDATE_INTERNAL_ERROR,
    UPDATE_RECOVERY_NOT_FOUND_ERROR,
    UPDATE_ERROR,
    UPDATER_FATAL_ERROR,
    UPDATE_ERROR_UNKNOWN;

    public static NativeUpdateResultCode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return UPDATE_ERROR_UNKNOWN;
        }
        return values()[i];
    }
}
