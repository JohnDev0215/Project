package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
@AnyThread
public class ConfigStorageClient {

    @GuardedBy("ConfigStorageClient.class")
    private static final Map<String, ConfigStorageClient> clientInstances = new HashMap();
    private final Context context;
    private final String fileName;

    private ConfigStorageClient(Context context, String str) {
        this.context = context;
        this.fileName = str;
    }

    public synchronized Void write(ConfigContainer configContainer) throws IOException {
        FileOutputStream fileOutputStreamOpenFileOutput = this.context.openFileOutput(this.fileName, 0);
        try {
            fileOutputStreamOpenFileOutput.write(configContainer.toString().getBytes(ParserUtil.UTF_8));
        } finally {
            fileOutputStreamOpenFileOutput.close();
        }
        return null;
    }

    @Nullable
    public synchronized ConfigContainer read() throws IOException {
        FileInputStream fileInputStreamOpenFileInput;
        Throwable th;
        try {
            fileInputStreamOpenFileInput = this.context.openFileInput(this.fileName);
        } catch (FileNotFoundException | JSONException unused) {
            fileInputStreamOpenFileInput = null;
        } catch (Throwable th2) {
            fileInputStreamOpenFileInput = null;
            th = th2;
        }
        try {
            int iAvailable = fileInputStreamOpenFileInput.available();
            byte[] bArr = new byte[iAvailable];
            fileInputStreamOpenFileInput.read(bArr, 0, iAvailable);
            ConfigContainer configContainerCopyOf = ConfigContainer.copyOf(new JSONObject(new String(bArr, ParserUtil.UTF_8)));
            fileInputStreamOpenFileInput.close();
            return configContainerCopyOf;
        } catch (FileNotFoundException | JSONException unused2) {
            if (fileInputStreamOpenFileInput != null) {
                fileInputStreamOpenFileInput.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStreamOpenFileInput != null) {
                fileInputStreamOpenFileInput.close();
            }
            throw th;
        }
    }

    public synchronized Void clear() {
        this.context.deleteFile(this.fileName);
        return null;
    }

    public static synchronized ConfigStorageClient getInstance(Context context, String str) {
        Map<String, ConfigStorageClient> map;
        map = clientInstances;
        if (!map.containsKey(str)) {
            map.put(str, new ConfigStorageClient(context, str));
        }
        return map.get(str);
    }

    String getFileName() {
        return this.fileName;
    }
}
