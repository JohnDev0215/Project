package cerence.ark.assistant.library;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.dcc.DccConstant;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.library.ipctransaction.IAssembler;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.library.ipctransaction.ipclist.ListAssembler;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.RemoteResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class Utils {
    private static final String LOG_AUDIO_DIR_NAME = "audio";
    private static final String LOG_DIR = "log/assistant";
    private static final String TAG = "Utils";
    private static int sClientPid;
    private static Context sContext;
    private static int sSpeechServicePid;
    private static long sUid;
    private static final int CURRENT_PID = Process.myPid();
    private static final ConcurrentHashMap<String, IAssembler> sAssemblerMap = new ConcurrentHashMap<>(16);

    public static boolean isValidThreshold(int i) {
        return i >= 1 && i <= 9999;
    }

    private Utils() {
    }

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static String getAudioLogDir(String str) {
        return format("%s/%s/%s", str, LOG_DIR, "audio");
    }

    public static Context getContext() {
        return sContext;
    }

    public static void setClientPid(int i) {
        sClientPid = i;
    }

    public static void setSpeechServicePid(int i) {
        sSpeechServicePid = i;
    }

    public static boolean isSameProcess() {
        return sClientPid == sSpeechServicePid;
    }

    public static boolean checkSelfPermission(String str) {
        return getContext().checkSelfPermission(str) == 0;
    }

    public static boolean checkSelfPermission(Context context, String str) {
        return context.checkSelfPermission(str) == 0;
    }

    public static void notifyAssistantStateChanged(AssistantState assistantState) {
        Intent intent = new Intent(Constant.ACTION_ASSISTANT_STATE_CHANGED);
        intent.putExtra(Constant.KEY_ASSISTANT_STATE, (Parcelable) assistantState);
        getContext().sendBroadcast(intent);
    }

    public static String createResponseJson(long j, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", j);
        jSONObject.put("name", str);
        jSONObject.put("type", JsonKey.MESSAGE_TYPE_RESPONSE);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(JsonKey.MESSAGE_RESULTS, new JSONObject(str2));
        } else {
            jSONObject.put(JsonKey.MESSAGE_RESULTS, new JSONObject());
        }
        return jSONObject.toString();
    }

    public static String createEvent(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("params", new JSONObject(str2));
        } else {
            jSONObject.put("params", new JSONObject());
        }
        return jSONObject.toString();
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            Log.e(TAG, format("create new file [%s] failed", file.getAbsolutePath()), e);
            return false;
        }
    }

    public static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String formatException(String str) {
        return format("%s: exception", str);
    }

    public static String format2Argv(String str, String str2) {
        return format("%s: %s", str, str2);
    }

    public static void logMethodBegin(String str, String str2) {
        Logger.info(str, format("%s: begin", str2));
    }

    public static void logMethodEnd(String str, String str2) {
        Logger.info(str, format("%s: end", str2));
    }

    public static String buildMessageLog(long j, String str, String str2) {
        return format("messageId = %s, domainName = %s, requestName = %s", Long.valueOf(j), str, str2);
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static <T> void notifySuccess(T t, IResultCallback<T, ErrorCode> iResultCallback) {
        if (iResultCallback == null) {
            return;
        }
        iResultCallback.onResult(new Result.Builder().setData(t).buildSuccess());
    }

    public static <T> void notifySuccess(IResultCallback<T, ErrorCode> iResultCallback) {
        if (iResultCallback == null) {
            return;
        }
        iResultCallback.onResult(new Result.Builder().buildSuccess());
    }

    public static <T> void notifyError(ErrorCode errorCode, IResultCallback<T, ErrorCode> iResultCallback) {
        if (iResultCallback == null) {
            return;
        }
        iResultCallback.onResult(buildErrorResult(errorCode));
    }

    public static <T> Result<T, ErrorCode> buildErrorResult(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return new Result.Builder().setErrorMessage(getErrorMessage(errorCode)).setErrorCode(errorCode).buildError();
    }

    private static String getErrorMessage(ErrorCode errorCode) {
        String strName = ErrorCode.UNKNOWN.name();
        if (errorCode == null) {
            return strName;
        }
        switch (AnonymousClass1.$SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[errorCode.ordinal()]) {
            case 1:
                return ErrorMessage.WRITABLE_PATH_INVALID;
            case 2:
                return ErrorMessage.DATAPACK_PATH_INVALID;
            case 3:
                return ErrorMessage.DATAPACK_VERSION_INCOMPATIBLE;
            case 4:
                return ErrorMessage.DATAPACK_INVALID;
            case 5:
                return ErrorMessage.PARAMETER_INVALID;
            case 6:
                return ErrorMessage.ASSISTANT_ALREADY_INITIALIZED;
            case 7:
                return ErrorMessage.ASSISTANT_NOT_INITIALIZED;
            case 8:
                return ErrorMessage.ASSISTANT_NOT_STARTED;
            case 9:
                return ErrorMessage.OOM;
            case 10:
                return ErrorMessage.ASSISTANT_ALREADY_STARTED;
            case 11:
                return ErrorMessage.BIND_SPEECH_SERVICE_ERROR;
            case 12:
                return ErrorMessage.AUDIO_RECORD_PERMISSION_DENIED;
            case 13:
                return ErrorMessage.AUDIO_RECORD_INIT_FAIL;
            case 14:
                return ErrorMessage.AUDIO_PLAYER_INIT_FAIL;
            case 15:
                return ErrorMessage.VOICE_CONFIG_INVALID;
            case 16:
                return ErrorMessage.VOICE_NOT_FOUND;
            case 17:
                return ErrorMessage.PROMPTER_VOICES_EMPTY;
            case 18:
                return ErrorMessage.INVALID_OPERATION;
            case 19:
                return ErrorMessage.VIS_NOT_READY;
            case 20:
                return ErrorMessage.UPDATE_STORAGE_PATH_INVALID;
            case 21:
                return ErrorMessage.CONFIG_NULL;
            case 22:
                return ErrorMessage.ASSISTANT_CONFIGURATION_NULL;
            case 23:
                return ErrorMessage.LANGUAGE_NULL;
            default:
                return errorCode.name();
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.library.Utils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode;

        static {
            int[] iArr = new int[ErrorCode.values().length];
            $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode = iArr;
            try {
                iArr[ErrorCode.WRITABLE_PATH_INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.DATAPACK_PATH_INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.DATAPACK_VERSION_INCOMPATIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.DATAPACK_INVALID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.PARAMETER_INVALID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.ASSISTANT_ALREADY_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.ASSISTANT_NOT_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.ASSISTANT_NOT_STARTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.OOM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.ASSISTANT_ALREADY_STARTED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.BIND_SPEECH_SERVICE_ERROR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.AUDIO_RECORD_PERMISSION_DENIED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.AUDIO_RECORD_INIT_FAIL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.AUDIO_PLAYER_INIT_FAIL.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.VOICE_CONFIG_INVALID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.VOICE_NOT_FOUND.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.PROMPTER_VOICES_EMPTY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.INVALID_OPERATION.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.VIS_NOT_READY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.UPDATE_STORAGE_PATH_INVALID.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.CONFIG_NULL.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.ASSISTANT_CONFIGURATION_NULL.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$ErrorCode[ErrorCode.LANGUAGE_NULL.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public static boolean isInitialized(AssistantState assistantState) {
        return assistantState == AssistantState.INITIALIZED || assistantState == AssistantState.RUNNING;
    }

    public static synchronized String generateUid() {
        StringBuilder sbAppend;
        long j;
        sbAppend = new StringBuilder().append(CURRENT_PID).append(DccConstant.DCC_SEPARATOR);
        j = sUid;
        sUid = 1 + j;
        return sbAppend.append(j).toString();
    }

    public static void processStringAssembler(String str, String str2, int i, int i2, IAssemblerListener<String> iAssemblerListener) {
        IAssembler stringAssembler;
        if (i == 0) {
            stringAssembler = new StringAssembler();
            sAssemblerMap.put(str2, stringAssembler);
        } else {
            stringAssembler = sAssemblerMap.get(str2);
        }
        processAssembler(stringAssembler, str, str2, i, i2, iAssemblerListener);
    }

    public static <T> void processListAssembler(List<T> list, String str, int i, int i2, IAssemblerListener<List<T>> iAssemblerListener) {
        IAssembler listAssembler;
        if (i == 0) {
            listAssembler = new ListAssembler();
            sAssemblerMap.put(str, listAssembler);
        } else {
            listAssembler = sAssemblerMap.get(str);
        }
        processAssembler(listAssembler, list, str, i, i2, iAssemblerListener);
    }

    private static <T> void processAssembler(IAssembler<T> iAssembler, T t, String str, int i, int i2, IAssemblerListener<T> iAssemblerListener) {
        if (iAssembler == null) {
            Logger.error(TAG, format("processAssembler: assembler is null, uid = %s", str));
            if (iAssemblerListener != null) {
                iAssemblerListener.onError();
                return;
            }
            return;
        }
        if (i == i2 - 1) {
            T tAssemble = iAssembler.assemble(t);
            sAssemblerMap.remove(str);
            if (iAssemblerListener != null) {
                iAssemblerListener.onFinal(tAssemble);
                return;
            }
            return;
        }
        iAssembler.assemble(t);
        if (iAssemblerListener != null) {
            iAssemblerListener.onPartial(t);
        }
    }

    public static void cleanAssemblerMap() {
        sAssemblerMap.clear();
    }

    public static RemoteResult buildErrorRemoteResult(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return RemoteResult.builder().setErrorMessage(getErrorMessage(errorCode)).setErrorCode(errorCode).buildError();
    }

    public static RemoteResult convertToRemoteResult(Result<Void, ErrorCode> result) {
        if (result.isSuccess()) {
            return RemoteResult.builder().buildSuccess();
        }
        return RemoteResult.builder().setErrorMessage(result.getErrorMessage()).setErrorCode(result.getErrorCode()).buildError();
    }

    public static Result<Void, ErrorCode> convertToResult(RemoteResult remoteResult) {
        if (remoteResult.isSuccess()) {
            return new Result.Builder().buildSuccess();
        }
        return new Result.Builder().setErrorMessage(remoteResult.getErrorMessage()).setErrorCode(remoteResult.getErrorCode()).buildError();
    }

    public static String getTag(String str, Class cls, String... strArr) {
        return str + "." + cls.getSimpleName() + "." + TextUtils.join(".", strArr);
    }
}
