package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public enum DmResultCode {
    ACE_OK,
    ACE_WRITABLE_PATH_INVALID,
    ACE_DATAPACK_PATH_INVALID,
    ACE_DATAPACK_VERSION_INCOMPATIBLE,
    ACE_DATAPACK_INVALID,
    ACE_PARAMETER_INVALID,
    ACE_ALREADY_INITIALIZED,
    ACE_NOT_INITIALIZED,
    ACE_ALREADY_STARTED,
    ACE_NOT_STARTED,
    ACE_OOM,
    ACE_UPDATER_ALREADY_INITIALIZED,
    ACE_UPDATER_NOT_INITIALIZED,
    ACE_RECOVERY_MODE_ALREADY_INITIALIZED,
    ACE_RECOVERY_MODE_NOT_INITIALIZED,
    ACE_UNKNOWN;

    public static DmResultCode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return ACE_UNKNOWN;
        }
        return values()[i];
    }
}
