package com.amazon.mp3.configuration;

import android.content.Context;
import android.os.Build;
import androidx.work.Data;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class WakeWordModelProvider {
    private static final String TAG = "WakeWordModelProvider";
    private static final Map<String, String> TERRITORY_MODEL_MAP;

    static {
        HashMap map = new HashMap();
        TERRITORY_MODEL_MAP = map;
        map.put(MusicTerritory.US.value, "U_250k.en-US.alexa.bin");
        map.put(MusicTerritory.UK.value, "U_250k.en-GB.alexa.bin");
        map.put(MusicTerritory.GERMANY.value, "U_250k.de-DE.alexa.bin");
        map.put(MusicTerritory.CANADA.value, "U_250k.en-US.alexa.bin");
        map.put(MusicTerritory.JAPAN.value, "U_250k.ja-JP.alexa.bin");
        map.put(MusicTerritory.AUSTRALIA.value, "U_250k.en-AU.alexa.bin");
        map.put(MusicTerritory.NEW_ZEALAND.value, "U_250k.en-US.alexa.bin");
        map.put(MusicTerritory.INDIA.value, "U_250k.en-IN.alexa.bin");
        map.put(MusicTerritory.FRANCE.value, "U_250k.fr-FR.alexa.bin");
        map.put(MusicTerritory.ITALY.value, "U_250k.it-IT.alexa.bin");
        map.put(MusicTerritory.SPAIN.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.BRAZIL.value, "W_250k.pt-BR.alexa.bin");
        map.put(MusicTerritory.IRELAND.value, "U_250k.en-GB.alexa.bin");
        map.put(MusicTerritory.MEXICO.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.NETHERLANDS.value, "U_250k.en-GB.alexa.bin");
        map.put(MusicTerritory.COLUMBIA.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.PERU.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.CHILE.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.ARGENTINA.value, "U_250k.es-ES.alexa.bin");
        map.put(MusicTerritory.COSTA_RICA.value, "U_250k.es-ES.alexa.bin");
    }

    public static byte[] getModelFromTerritory(final Context context, final String territory) {
        return getModel(context, getModelNameForTerritory(territory));
    }

    static String getModelNameForTerritory(final String territory) {
        Map<String, String> map = TERRITORY_MODEL_MAP;
        return map.containsKey(territory) ? map.get(territory) : "U_250k.en-US.alexa.bin";
    }

    private static byte[] getModel(final Context context, final String fileName) {
        try {
            String str = getVoiceModelDir() + fileName;
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArrConvertStreamToByteArray = convertStreamToByteArray(inputStreamOpen);
            Log.debug(TAG, "The model file is: " + str);
            inputStreamOpen.close();
            return bArrConvertStreamToByteArray;
        } catch (IOException e) {
            Log.error(TAG, "Received an IOException when trying to open model. " + e);
            return null;
        }
    }

    private static byte[] convertStreamToByteArray(final InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Data.MAX_DATA_BYTES];
        while (true) {
            int i = inputStream.read(bArr, 0, Data.MAX_DATA_BYTES);
            if (i > 0) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private static String getVoiceModelDir() {
        return getPreferredABI().contains("x86") ? "x86/" : "arm/";
    }

    private static String getPreferredABI() {
        return Build.SUPPORTED_ABIS[0];
    }
}
