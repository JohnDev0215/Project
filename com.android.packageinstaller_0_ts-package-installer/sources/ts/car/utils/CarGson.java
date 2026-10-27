package ts.car.utils;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CarGson {
    private static final String TAG = "TSPS_CarGson";
    private HashMap<String, Boolean> mConfigFilesNotExitMap = new HashMap<>();

    public <T> T getObjectFromJsonFile(String str, Class<T> cls) {
        String jsonFile = readJsonFile(str);
        if (!TextUtils.isEmpty(jsonFile)) {
            try {
                return (T) new Gson().fromJson(jsonFile, (Class) cls);
            } catch (Exception e) {
                Log.e(TAG, "readPermissionConfig fromJson error : " + jsonFile, e);
                return null;
            }
        }
        return null;
    }

    public <T> T getObjectFromJsonFile(String str) {
        String jsonFile = readJsonFile(str);
        if (!TextUtils.isEmpty(jsonFile)) {
            try {
                Log.i(TAG, "getObjectFromJsonFile json : " + jsonFile);
                return (T) new Gson().fromJson(jsonFile, new TypeToken<T>() { // from class: ts.car.utils.CarGson.1
                }.getType());
            } catch (Exception e) {
                Log.e(TAG, "readPermissionConfig fromJson error : " + jsonFile, e);
                return null;
            }
        }
        return null;
    }

    public boolean writeObjectToJsonFile(String fileDir, Object object) {
        if (object == null || TextUtils.isEmpty(fileDir)) {
            return false;
        }
        try {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            return writeToJsonFile(fileDir, json);
        } catch (Exception exception) {
            Log.e(TAG, "writeObjectToJsonFile error : ", exception);
            return false;
        }
    }

    private String readJsonFile(String dir) {
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        File jsonFile = new File(dir);
        if (this.mConfigFilesNotExitMap.getOrDefault(dir, false).booleanValue()) {
            return null;
        }
        if (!jsonFile.exists()) {
            this.mConfigFilesNotExitMap.put(dir, true);
            Log.w(TAG, "readJsonFile <" + dir + "> not exist");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream(jsonFile);
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return stringBuilder.toString();
                }
                stringBuilder.append(readLine);
            }
        } catch (IOException exception) {
            Log.e(TAG, "readJsonFile IOException", exception);
            return null;
        } finally {
            closeQuietly(inputStream);
            closeQuietly(inputStreamReader);
            closeQuietly(bufferedReader);
        }
    }

    private boolean writeToJsonFile(String filePath, String content) {
        Log.d(TAG, "writeToJsonFile() filePath = " + filePath);
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(content)) {
            return false;
        }
        if (TextUtils.isEmpty(content)) {
            Log.w(TAG, "writeToJsonFile() saveContent is empty");
            return false;
        }
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            byte[] bytesArray = content.getBytes();
            fos = new FileOutputStream(filePath);
            fos.write(bytesArray);
            fos.flush();
            Log.i(TAG, "writeToJsonFile() Success!!!");
            return true;
        } catch (IOException exception) {
            Log.w(TAG, "writeToJsonFile() IOException: " + exception);
            return false;
        } finally {
            closeQuietly(fos);
        }
    }

    private void closeQuietly(AutoCloseable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
                Log.d(TAG, "Exception ignored");
            }
        }
    }
}
