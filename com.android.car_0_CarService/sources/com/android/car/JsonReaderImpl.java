package com.android.car;

import android.content.Context;
import com.android.car.CarConfigurationService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* JADX INFO: loaded from: classes3.dex */
public class JsonReaderImpl implements CarConfigurationService.JsonReader {
    private static final int BUF_SIZE = 4096;
    private static final String JSON_FILE_ENCODING = "UTF-8";

    @Override // com.android.car.CarConfigurationService.JsonReader
    public String jsonFileToString(Context context, int resId) {
        Reader reader;
        char[] buffer;
        StringBuilder stringBuilder;
        InputStream is = context.getResources().openRawResource(resId);
        try {
            reader = new BufferedReader(new InputStreamReader(is, JSON_FILE_ENCODING));
            try {
                buffer = new char[4096];
                stringBuilder = new StringBuilder();
            } finally {
            }
        } catch (IOException e) {
            return null;
        }
        while (true) {
            int bufferedContent = reader.read(buffer);
            if (bufferedContent != -1) {
                stringBuilder.append(buffer, 0, bufferedContent);
            } else {
                String string = stringBuilder.toString();
                reader.close();
                return string;
            }
            return null;
        }
    }
}
