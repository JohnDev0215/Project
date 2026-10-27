package com.ts.car.input;

import android.util.SparseArray;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ConfigParser {
    private static final String CODE_FORMAT = "utf-8";
    private static final String COMBINE_KEYS = "combine_keys";
    public static final String COMBINE_KEY_INTERVAL = "combine_key_interval";
    private static final String COMBINE_KEY_MAPS = "combine_key_maps";
    private static final String COMBINE_TIMEOUT = "combine_timeout";
    private static final String CONFIG_LONGPRESS = "/system/etc/data/keycode_config";
    private static final String CONFIG_LONGPRESS_JSON = "/system/etc/data/keycode_config.json";
    private static final String DEFAULT_LONGPRESS_TIME = "DEFAULT_LONGPRESS_TIME";
    public static final String EMPTY = "";
    public static final long INVALID_INTERVAL = -1;
    private static final String KEYCODE = "keycode";
    private static final String KEYCODE_LONGPRESS_TIMEOUT = "keycode_longpress_timeout";
    private static final String KEYCODE_SCENETYPE_MAP = "keycode_scenetype_map";
    private static final String KEYCODE_TRANSFER = "keycode_transfer";
    private static final String LONGPRESS_SCENETYPE_MAP = "longpress_scenetype_map";
    private static final String LONGPRESS_SPLITE = "longpress_split";
    private static final String LONGPRESS_TO_SHORTPRESS = "longpress_transformto_shortpress";
    private static final String LONG_PRESS_DISPATCH_SHORT_DOWN = "longpress_dispatch_shortdown";
    private static final String MAX_TIME = "max_time";
    private static final String SCENETYPE = "scenetype";
    private static final String TAG = "ConfigParser";
    long longpressDefault;
    private static volatile HashMap<Integer, ArrayList<Integer>> mKeycodeMap = new HashMap<>();
    private static volatile HashMap<Integer, LongpressConfiguration> mConfigurationMap = new HashMap<>();
    private static volatile List<Integer> mKeycodeList = new ArrayList();
    private static volatile List<Integer> mLongPressDispatchShortDown = new ArrayList();
    public static List<Integer> sCombineKeyList = new ArrayList();
    public static SparseArray<Long> TIME_SETS = new SparseArray<>();
    static Configuration sConfiguration = new Configuration();

    public ConfigParser() {
        parseConfig();
    }

    private void parseConfig() {
        File configFile = new File(CONFIG_LONGPRESS_JSON);
        if (!configFile.exists()) {
            configFile = new File(CONFIG_LONGPRESS);
        }
        if (configFile.exists() && configFile.isFile()) {
            String configStr = readTextFile(configFile);
            LogUtils.log(TAG, "configStr : " + configStr);
            parse(configStr);
        }
    }

    public static Configuration getConfiguration() {
        return sConfiguration;
    }

    private Configuration parse(String text) {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        Object object6;
        try {
            JSONObject js = new JSONObject(text);
            if (js.has(KEYCODE_LONGPRESS_TIMEOUT) && (object6 = js.getJSONObject(KEYCODE_LONGPRESS_TIMEOUT)) != null) {
                parseLongpressTimeout((JSONObject) object6);
            }
            if (js.has(KEYCODE_SCENETYPE_MAP) && (object5 = js.getJSONArray(KEYCODE_SCENETYPE_MAP)) != null) {
                parseKeyCodeSceneType(object5);
            }
            if (js.has(LONGPRESS_SCENETYPE_MAP) && (object4 = js.getJSONArray(LONGPRESS_SCENETYPE_MAP)) != null) {
                LogUtils.log(TAG, "LONGPRESS_SCENETYPE_MAP toString: " + object4.toString());
                parseLongPressSceneType(object4);
            }
            if (js.has(LONGPRESS_TO_SHORTPRESS) && (object3 = js.getJSONArray(LONGPRESS_TO_SHORTPRESS)) != null) {
                LogUtils.log(TAG, "LONGPRESS_TO_SHORTPRESS toString: " + object3.toString());
                parseLongpressToShortpress(object3);
            }
            if (js.has(COMBINE_KEY_MAPS) && (object2 = js.getJSONArray(COMBINE_KEY_MAPS)) != null) {
                LogUtils.log(TAG, "object toString: " + object2.toString());
                sConfiguration.addKeyCodeCombine(parseKeyCodeCombine(object2));
            }
            if (js.has(COMBINE_KEY_INTERVAL) && (object = js.get(COMBINE_KEY_INTERVAL)) != null) {
                LogUtils.log(TAG, "object toString: " + object.toString());
                sConfiguration.addProperty(COMBINE_KEY_INTERVAL, String.valueOf(object));
            }
            if (js.has(LONG_PRESS_DISPATCH_SHORT_DOWN)) {
                Object object7 = js.get(LONG_PRESS_DISPATCH_SHORT_DOWN);
                LogUtils.log(TAG, "LONG_PRESS_DISPATCH_SHORT_DOWN: " + object7);
                if (object7 != null) {
                    LogUtils.log(TAG, "LONG_PRESS_DISPATCH_SHORT_DOWN toString: " + object7.toString());
                    parseLongpressToShortdown(object7);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LogUtils.log(TAG, sConfiguration.toString());
        return sConfiguration;
    }

    private List<Integer> parseLongpressToShortpress(Object obj) throws JSONException {
        if (obj == null) {
            return mKeycodeList;
        }
        JSONArray js = (JSONArray) obj;
        int length = js.length();
        for (int i = 0; i < length; i++) {
            for (int index = 0; index < js.length(); index++) {
                String key = js.getString(index);
                if (ConfigUtils.KEYCODE_MAP.containsKey(key)) {
                    int keycode = ConfigUtils.KEYCODE_MAP.get(key).intValue();
                    LogUtils.log(TAG, "    parseLongpressToShortpress key: " + key + ", keycode : " + keycode);
                    mKeycodeList.add(Integer.valueOf(keycode));
                } else {
                    LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + key);
                }
            }
        }
        return mKeycodeList;
    }

    private List<Integer> parseLongpressToShortdown(Object obj) throws JSONException {
        if (obj == null) {
            return mLongPressDispatchShortDown;
        }
        JSONArray js = (JSONArray) obj;
        int length = js.length();
        for (int i = 0; i < length; i++) {
            for (int index = 0; index < js.length(); index++) {
                String key = js.getString(index);
                LogUtils.log(TAG, "    parseLongpressToShortdown key: " + key);
                if (ConfigUtils.KEYCODE_MAP.containsKey(key)) {
                    int keycode = ConfigUtils.KEYCODE_MAP.get(key).intValue();
                    LogUtils.log(TAG, "    parseLongpressToShortdown key: " + key + ", keycode : " + keycode);
                    mLongPressDispatchShortDown.add(Integer.valueOf(keycode));
                } else {
                    LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + key);
                }
            }
        }
        return mLongPressDispatchShortDown;
    }

    private HashMap<Integer, LongpressConfiguration> parseLongPressSceneType(Object obj) throws JSONException {
        if (obj == null) {
            return mConfigurationMap;
        }
        JSONArray js = (JSONArray) obj;
        int length = js.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsObj = js.getJSONObject(i);
            String key = jsObj.getString(KEYCODE);
            if (ConfigUtils.KEYCODE_MAP.containsKey(key)) {
                int keycode = ConfigUtils.KEYCODE_MAP.get(key).intValue();
                LogUtils.log(TAG, "parseLongPressSceneType keycode : " + keycode);
                LongpressConfiguration configuration = new LongpressConfiguration(keycode);
                JSONArray jsSceneType = jsObj.getJSONArray(SCENETYPE);
                new ArrayList();
                for (int index = 0; index < jsSceneType.length(); index++) {
                    String scene = jsSceneType.getString(index);
                    int seneType = ConfigUtils.parsetoIntType(jsSceneType.getString(index));
                    LogUtils.log(TAG, "    parseLongPressSceneType scene: " + scene + ", seneType : " + seneType);
                    configuration.addScenes(seneType);
                }
                int longpressSplit = jsObj.getInt(LONGPRESS_SPLITE);
                configuration.setLongpressSplit(longpressSplit);
                int maxtime = jsObj.getInt(MAX_TIME);
                configuration.setMaxtime(maxtime);
                mConfigurationMap.put(Integer.valueOf(keycode), configuration);
            } else {
                LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + key);
            }
        }
        return mConfigurationMap;
    }

    private HashMap<Integer, ArrayList<Integer>> parseKeyCodeSceneType(Object obj) throws JSONException {
        if (obj == null) {
            return mKeycodeMap;
        }
        JSONArray js = (JSONArray) obj;
        int length = js.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsObj = js.getJSONObject(i);
            String key = jsObj.getString(KEYCODE);
            if (ConfigUtils.KEYCODE_MAP.containsKey(key)) {
                int keycode = ConfigUtils.KEYCODE_MAP.get(key).intValue();
                LogUtils.log(TAG, "parseKeyCodeSceneType keycode : " + keycode);
                JSONArray jsSceneType = jsObj.getJSONArray(SCENETYPE);
                ArrayList<Integer> listScene = new ArrayList<>();
                for (int index = 0; index < jsSceneType.length(); index++) {
                    String scene = jsSceneType.getString(index);
                    int seneType = ConfigUtils.parsetoIntType(jsSceneType.getString(index));
                    LogUtils.log(TAG, "parseKeyCodeSceneType scene: " + scene + "seneType : " + seneType);
                    listScene.add(Integer.valueOf(seneType));
                }
                mKeycodeMap.put(Integer.valueOf(keycode), listScene);
            } else {
                LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + key);
            }
        }
        return mKeycodeMap;
    }

    public static List<Integer> getCombineKeys() {
        return sCombineKeyList;
    }

    private List<KeyCodeCombine> parseKeyCodeCombine(Object obj) throws JSONException {
        JSONArray js;
        JSONArray js2;
        List list = new ArrayList();
        if (obj == null) {
            return list;
        }
        JSONArray js3 = (JSONArray) obj;
        int length = js3.length();
        int i = 0;
        while (i < length) {
            JSONObject jsObj = js3.getJSONObject(i);
            Long combineTimeout = Long.valueOf(jsObj.getLong(COMBINE_TIMEOUT));
            String keyTransfer = jsObj.getString(KEYCODE_TRANSFER);
            if (ConfigUtils.KEYCODE_MAP.containsKey(keyTransfer)) {
                int transferCode = ConfigUtils.KEYCODE_MAP.get(keyTransfer).intValue();
                sCombineKeyList.add(Integer.valueOf(transferCode));
                KeyCodeCombine combine = new KeyCodeCombine(transferCode, combineTimeout);
                LogUtils.log(TAG, "parseKeyCodeCombine combineTimeout: " + combineTimeout + ",transferCode : " + transferCode);
                JSONArray jsCombineKey = jsObj.getJSONArray(COMBINE_KEYS);
                int index = 0;
                while (index < jsCombineKey.length()) {
                    String keyCombine = jsCombineKey.getString(index);
                    if (ConfigUtils.KEYCODE_MAP.containsKey(keyCombine)) {
                        int code = ConfigUtils.KEYCODE_MAP.get(keyCombine).intValue();
                        combine.addCombineCode(code);
                        StringBuilder sb = new StringBuilder();
                        js2 = js3;
                        sb.append("parseKeyCodeCombine code: ");
                        sb.append(code);
                        LogUtils.log(TAG, sb.toString());
                    } else {
                        js2 = js3;
                        LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + keyCombine);
                    }
                    index++;
                    js3 = js2;
                }
                js = js3;
                list.add(combine);
            } else {
                js = js3;
                LogUtils.log(TAG, "no such keycode in the KEYCODE_MAP: " + keyTransfer);
            }
            i++;
            js3 = js;
        }
        return list;
    }

    private void parseLongpressTimeout(JSONObject jsonObject) {
        this.longpressDefault = jsonObject.optLong(DEFAULT_LONGPRESS_TIME);
        LogUtils.log(TAG, "config parser defaultLongPressTime: " + this.longpressDefault);
        KeycodeUtil.setDefaultLongpressTimeout(this.longpressDefault);
        for (String key : ConfigUtils.KEYCODE_MAP.keySet()) {
            if (!key.endsWith(ConfigUtils.LONG_PRESS_SUFFIX) && jsonObject.has(key)) {
                Object value = jsonObject.opt(key);
                if (!anyEmpty(value)) {
                    int keycode = ConfigUtils.KEYCODE_MAP.get(key).intValue();
                    Long longPressTime = Long.valueOf(value.toString());
                    TIME_SETS.put(keycode, longPressTime);
                    LogUtils.log(TAG, "config parser keycode: " + keycode + ",longPressTime: " + longPressTime);
                }
            }
        }
    }

    public static boolean anyEmpty(Object... values) {
        if (values == null) {
            return true;
        }
        for (Object value : values) {
            if (isEmpty(value)) {
                return true;
            }
        }
        return false;
    }

    private String readTextFile(File file) {
        BufferedReader bufferReader;
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = null;
        if (file != null && file.exists()) {
            try {
                try {
                    if (file.isFile()) {
                        try {
                            try {
                                try {
                                    reader = new InputStreamReader(new FileInputStream(file), CODE_FORMAT);
                                    bufferReader = new BufferedReader(reader);
                                } catch (FileNotFoundException ex) {
                                    ex.printStackTrace();
                                    if (reader != null) {
                                        reader.close();
                                    }
                                    return builder.toString();
                                }
                            } catch (IOException ex2) {
                                ex2.printStackTrace();
                                if (reader != null) {
                                    reader.close();
                                }
                                return builder.toString();
                            }
                        } catch (UnsupportedEncodingException ex3) {
                            ex3.printStackTrace();
                            if (reader != null) {
                                reader.close();
                            }
                            return builder.toString();
                        }
                        while (true) {
                            String str = bufferReader.readLine();
                            if (str == null) {
                                break;
                            }
                            builder.append(str);
                        }
                        bufferReader.close();
                        String string = builder.toString();
                        try {
                            reader.close();
                        } catch (IOException ex4) {
                            ex4.printStackTrace();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex5) {
                            ex5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException ex6) {
                ex6.printStackTrace();
            }
        }
        return builder.toString();
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if ((value instanceof String) && ((String) value).length() <= 0) {
            return true;
        }
        return false;
    }

    public static HashMap<Integer, ArrayList<Integer>> getPassThroughKeymap() {
        return mKeycodeMap;
    }

    public static HashMap<Integer, LongpressConfiguration> getLongConfigurationMap() {
        return mConfigurationMap;
    }

    public static List<Integer> getShortKeycodeList() {
        return mKeycodeList;
    }

    public static List<Integer> getLongPressDispatchShortDownList() {
        return mLongPressDispatchShortDown;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("defaultLongpress : " + this.longpressDefault);
        for (Map.Entry<Integer, ArrayList<Integer>> item : mKeycodeMap.entrySet()) {
            int keycode = item.getKey().intValue();
            sb.append("    keycode: " + keycode);
            ArrayList<Integer> list = item.getValue();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    int scene = list.get(i).intValue();
                    sb.append("        scene: " + scene);
                }
            }
        }
        return sb.toString();
    }
}
