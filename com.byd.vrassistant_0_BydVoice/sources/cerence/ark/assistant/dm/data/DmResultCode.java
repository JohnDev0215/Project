package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes.dex */
public enum DmResultCode {
    OK,
    ERROR_UNKNOWN,
    ERROR_INITIALIZE_TWICE,
    ERROR_NOT_INITIALIZED,
    ERROR_NOT_STARTED,
    ERROR_CONFIG,
    ERROR_INVALID_PARAM,
    ERROR_OOM,
    ERROR_PROMPT,
    ERROR_DATA_PATH_INVALID,
    ERROR_WRITABLE_PATH_INVALID,
    ERROR_DATAPACK_INVALID,
    ERROR_DATAPACK_VERSION_INCOMPATIBLE,
    ERROR_LICENSE_EXPIRED;

    public static DmResultCode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return ERROR_UNKNOWN;
        }
        return values()[i];
    }
}
