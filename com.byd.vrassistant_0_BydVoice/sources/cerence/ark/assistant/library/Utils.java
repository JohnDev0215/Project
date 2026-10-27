package cerence.ark.assistant.library;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.AssistantState;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String LOG_AUDIO_DIR_NAME = "audio";
    private static final String LOG_DIR = "log/assistant";
    private static final String TAG = "Utils";
    private static int sClientPid;
    private static Context sContext;
    private static int sSpeechServicePid;

    public static boolean isValidThreshold(int i) {
        return i >= 1 && i <= 9999;
    }

    private Utils() {
    }

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static String getAudioLogDir(String str) {
        return String.format("%s/%s/%s", str, LOG_DIR, LOG_AUDIO_DIR_NAME);
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

    public static String createResponseJson(int i, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", i);
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
            Log.e(TAG, String.format("create new file [%s] failed", file.getAbsolutePath()), e);
            return false;
        }
    }

    public static String buildMessageLog(int i, String str, String str2) {
        return String.format("messageId = %s, domainName = %s, requestName = %s", Integer.valueOf(i), str, str2);
    }

    public static String buildErrorMsg(String str, String str2) {
        return String.format("%s: %s", str, str2);
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static <T> void notifySuccess(IResultCallback<T> iResultCallback) {
        if (iResultCallback != null) {
            iResultCallback.onResult(new Result.Builder().buildSuccessResult());
        }
    }

    public static <T> void notifySuccess(T t, IResultCallback<T> iResultCallback) {
        if (iResultCallback != null) {
            iResultCallback.onResult(new Result.Builder().buildSuccessResult(t));
        }
    }

    public static <T> void notifyFailure(String str, String str2, String str3, IResultCallback<T> iResultCallback) {
        notifyFailure(str, str2, str3, null, iResultCallback);
    }

    public static <T> void notifyFailure(String str, String str2, String str3, Exception exc, IResultCallback<T> iResultCallback) {
        Object[] objArr = new Object[2];
        if (str2 == null) {
            str2 = "";
        }
        objArr[0] = str2;
        objArr[1] = str3 != null ? str3 : "";
        String str4 = String.format("%s: %s", objArr);
        if (exc == null) {
            Logger.error(str, str4);
        } else {
            Logger.error(str, str4, exc);
        }
        if (iResultCallback != null) {
            iResultCallback.onResult(new Result.Builder().buildErrorResult(str3));
        }
    }

    public static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String format2Argv(String str, String str2) {
        return format("%s: %s", str, str2);
    }
}
