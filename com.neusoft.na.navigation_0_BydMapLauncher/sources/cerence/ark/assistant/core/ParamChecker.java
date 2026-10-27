package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.data.RecoveryConfig;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.Utils;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class ParamChecker {
    private static final String TAG = "ParamChecker";

    private ParamChecker() {
    }

    public static Result<Void, ErrorCode> checkConfigValid(Config config) {
        if (config == null) {
            Logger.error(TAG, ErrorMessage.CONFIG_NULL);
            return Utils.buildErrorResult(ErrorCode.CONFIG_NULL);
        }
        if (TextUtils.isEmpty(config.getUserId())) {
            Logger.error(TAG, "UserId must not be empty");
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        if (TextUtils.isEmpty(config.getDeviceId())) {
            Logger.error(TAG, "DeviceId must not be empty");
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        if (TextUtils.isEmpty(config.getDataPath())) {
            Logger.error(TAG, "Data path must not be empty");
            return Utils.buildErrorResult(ErrorCode.DATAPACK_PATH_INVALID);
        }
        File file = new File(config.getDataPath());
        if (!file.exists()) {
            Logger.error(TAG, Utils.format("The data dir %s does not exist", config.getDataPath()));
            return Utils.buildErrorResult(ErrorCode.DATAPACK_PATH_INVALID);
        }
        if (!file.isDirectory()) {
            Logger.error(TAG, Utils.format("The data path %s must be directory", config.getDataPath()));
            return Utils.buildErrorResult(ErrorCode.DATAPACK_PATH_INVALID);
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            Logger.error(TAG, Utils.format("Empty datapack %s directory", config.getDataPath()));
            return Utils.buildErrorResult(ErrorCode.DATAPACK_PATH_INVALID);
        }
        if (!TextUtils.isEmpty(config.getWritablePath())) {
            File file2 = new File(config.getWritablePath());
            if (!file2.exists() && !file2.mkdirs()) {
                Logger.warn(TAG, "Create writable path failed");
            } else if (!file2.isDirectory()) {
                Logger.error(TAG, "The writable path must be directory");
                return Utils.buildErrorResult(ErrorCode.WRITABLE_PATH_INVALID);
            }
        }
        if (!TextUtils.isEmpty(config.getUpdateStorageDir())) {
            File file3 = new File(config.getUpdateStorageDir());
            if (!file3.exists() && !file3.mkdirs()) {
                Logger.error(TAG, "Create update path failed");
                return Utils.buildErrorResult(ErrorCode.UPDATE_STORAGE_PATH_INVALID);
            }
            if (!file3.isDirectory()) {
                Logger.error(TAG, "The update path must be directory");
                return Utils.buildErrorResult(ErrorCode.UPDATE_STORAGE_PATH_INVALID);
            }
        }
        return new Result.Builder().buildSuccess();
    }

    public static boolean checkRecoveryConfigValid(RecoveryConfig recoveryConfig) {
        if (recoveryConfig == null) {
            Logger.error(TAG, Utils.format("%s: recoveryConfig is null", "checkRecoveryConfigValid"));
            return false;
        }
        if (TextUtils.isEmpty(recoveryConfig.getAttribute())) {
            Logger.error(TAG, Utils.format("%s: recovery attribute must not be empty", "checkRecoveryConfigValid"));
            return false;
        }
        if (TextUtils.isEmpty(recoveryConfig.getRecoveryConfigDir())) {
            Logger.error(TAG, Utils.format("%s: recovery config path must not be empty", "checkRecoveryConfigValid"));
            return false;
        }
        if (TextUtils.isEmpty(recoveryConfig.getUpdateStorageDir())) {
            Logger.error(TAG, Utils.format("%s: update path must not be empty", "checkRecoveryConfigValid"));
            return false;
        }
        File file = new File(recoveryConfig.getRecoveryConfigDir());
        if (!file.exists()) {
            Logger.error(TAG, Utils.format("%s: recovery config path does not exist", "checkRecoveryConfigValid"));
            return false;
        }
        if (!file.isDirectory()) {
            Logger.error(TAG, Utils.format("%s: The recovery config path must be directory", "checkRecoveryConfigValid"));
            return false;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            Logger.error(TAG, Utils.format("%s: Empty recovery directory", "checkRecoveryConfigValid"));
            return false;
        }
        File file2 = new File(recoveryConfig.getUpdateStorageDir());
        if (!file2.exists() && !file2.mkdirs()) {
            Logger.error(TAG, Utils.format("%s: Create update path fail", "checkRecoveryConfigValid"));
            return false;
        }
        if (file2.isDirectory()) {
            return true;
        }
        Logger.error(TAG, Utils.format("%s:The update path must be directory", "checkRecoveryConfigValid"));
        return false;
    }
}
