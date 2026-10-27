package com.ts.car.input;

import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ConfigUtils {
    private static final String CONFIG = "/system/etc/data/keycode-ui.xml";
    public static final int CONFIG_DELIVER = 3;
    public static final int CONFIG_IGNORE = 0;
    public static final int CONFIG_INJECT = 1;
    public static final int CONFIG_NONE = -1;
    public static final int CONFIG_POST = 2;
    public static final HashMap<String, Integer> KEYCODE_MAP;
    public static final String LONG_PRESS_SUFFIX = "_LONG";
    public static final int PRIORITY_DEFAULT = 0;
    private static final String REGULAR_16 = "^0[x|X]";
    public static final HashMap<String, Integer> SCENE_MAP;
    private static final String TAG = "ConfigUtils";
    private static final String TAG_KEY = "key";
    private static final String TAG_UNIT = "unit";
    public static final int TYPE_UNKNOWN = -1;
    private String key;
    private int keycode;
    private int policy;
    private int priority;
    private static HashMap<String, ArrayList<SceneTypePolicy>> mCache = null;
    private static HashMap<Integer, ArrayList<SceneTypePolicy>> mLongPressMap = new HashMap<>();
    private static HashMap<Integer, ArrayList<SceneTypePolicy>> mSceneMap = new HashMap<>();
    private static HashMap<Integer, ArrayList<SceneTypePolicy>> mSceneLongPressMap = new HashMap<>();
    private static ArrayList<Integer> mSceneList = new ArrayList<>();
    private static ArrayList<Integer> mSceneLongPress = new ArrayList<>();
    private static HashSet mLongPressSet = new HashSet();

    static {
        HardKeyManagerProxy.getInstance();
        KEYCODE_MAP = HardKeyManagerProxy.KEYCODE_MAP;
        SCENE_MAP = HardKeyManagerProxy.SCENE_MAP;
    }

    public static String getKeyName(int key) {
        String keyName = null;
        for (Map.Entry<String, Integer> itemKey : KEYCODE_MAP.entrySet()) {
            int keycode = itemKey.getValue().intValue();
            if (keycode == key) {
                if (keyName == null) {
                    String keyName2 = itemKey.getKey();
                    keyName = keyName2;
                } else {
                    keyName = keyName.length() < itemKey.getKey().length() ? keyName : itemKey.getKey();
                }
            }
        }
        Log.d(TAG, key + " name is " + keyName);
        return keyName;
    }

    public static HashSet<Integer> getKeyCodes() {
        HashSet<Integer> codes = new HashSet<>();
        for (Map.Entry<String, Integer> itemKey : KEYCODE_MAP.entrySet()) {
            int keycode = itemKey.getValue().intValue();
            codes.add(Integer.valueOf(keycode));
        }
        return codes;
    }

    public ConfigUtils() {
        parseConfig();
    }

    private void parseConfig() {
        File cfgFile = new File(CONFIG);
        if (!cfgFile.exists()) {
            LogUtils.log(TAG, "configFile: " + cfgFile.getAbsolutePath() + " isn't exist!");
            return;
        }
        try {
            InputStream in = new FileInputStream(cfgFile);
            try {
                mCache = readConfigXml(in);
            } finally {
                closeSilently(in);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        HashMap<String, ArrayList<SceneTypePolicy>> map = mCache;
        if (map == null || map.isEmpty()) {
            sb.append("empty");
            return sb.toString();
        }
        for (Map.Entry<String, ArrayList<SceneTypePolicy>> item : mCache.entrySet()) {
            String key = item.getKey();
            sb.append("key " + key + "\n");
            ArrayList<SceneTypePolicy> units = mCache.get(key);
            if (units != null) {
                for (int i = 0; i < units.size(); i++) {
                    int scene = units.get(i).getSceneType();
                    int priority = units.get(i).getPriority();
                    int policy = units.get(i).getPolicy();
                    sb.append("unit name:[scene=" + scene + ",priority=" + priority + ",policy=" + policy + "]");
                }
            }
        }
        return sb.toString();
    }

    private HashMap<String, ArrayList<SceneTypePolicy>> readConfigXml(InputStream in) throws XmlPullParserException, IOException {
        ArrayList<SceneTypePolicy> itemsLong;
        ArrayList<SceneTypePolicy> arrayList;
        HashMap<String, ArrayList<SceneTypePolicy>> map = new HashMap<>();
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, "utf-8");
            ArrayList<SceneTypePolicy> items = null;
            ArrayList<SceneTypePolicy> itemsLong2 = null;
            ArrayList<SceneTypePolicy> temp = null;
            boolean keyLongPressConfig = false;
            for (int type = parser.getEventType(); type != 1; type = parser.next()) {
                if (type == 2) {
                    String tag = parser.getName();
                    if (TAG_KEY.equals(tag)) {
                        String attributeValue = parser.getAttributeValue(null, "name");
                        this.key = attributeValue;
                        if (KEYCODE_MAP.containsKey(attributeValue)) {
                            this.keycode = KEYCODE_MAP.get(this.key).intValue();
                        }
                        LogUtils.log(TAG, "key %s", this.key);
                        temp = map.get(this.key);
                        if (temp == null) {
                            temp = new ArrayList<>();
                            map.put(this.key, temp);
                        }
                        if (this.key.endsWith(LONG_PRESS_SUFFIX)) {
                            mLongPressSet.add(Integer.valueOf(this.keycode));
                            keyLongPressConfig = true;
                            itemsLong2 = mLongPressMap.get(Integer.valueOf(this.keycode));
                            if (itemsLong2 == null) {
                                itemsLong2 = new ArrayList<>();
                                mLongPressMap.put(Integer.valueOf(this.keycode), itemsLong2);
                            }
                        } else {
                            keyLongPressConfig = false;
                        }
                    } else {
                        if (!TAG_UNIT.equals(tag)) {
                            itemsLong = itemsLong2;
                            arrayList = temp;
                        } else {
                            String namePair = parser.getAttributeValue(0).replace(" ", ConfigParser.EMPTY);
                            String value = parser.nextText();
                            String[] array = namePair.split(",");
                            if (array.length < 2) {
                                LogUtils.loge(TAG, "Wrong config format! the scene type must appear with its priority!");
                                itemsLong = itemsLong2;
                                arrayList = temp;
                            } else {
                                String name = array[0];
                                String prio = array[1];
                                this.priority = StringUtils.str2Int(prio);
                                this.policy = StringUtils.str2Int(value);
                                int sceneType = parsetoIntType(name);
                                itemsLong = itemsLong2;
                                StringBuilder sb = new StringBuilder();
                                arrayList = temp;
                                sb.append("sceneType : ");
                                sb.append(sceneType);
                                sb.append(",priority : ");
                                sb.append(this.priority);
                                sb.append(",policy : ");
                                sb.append(this.policy);
                                sb.append(",keyLongPressConfig :");
                                sb.append(keyLongPressConfig);
                                LogUtils.log(TAG, sb.toString());
                                int i = this.priority;
                                int type2 = this.policy;
                                SceneTypePolicy sceneTypePolicy = new SceneTypePolicy(sceneType, i, type2, this.keycode);
                                saveSceneTypePolicy(items, sceneType, keyLongPressConfig, sceneTypePolicy);
                                LogUtils.log(TAG, "name : " + name + ",policy :" + this.policy);
                            }
                        }
                        itemsLong2 = itemsLong;
                        temp = arrayList;
                    }
                } else {
                    if (type != 3) {
                        itemsLong = itemsLong2;
                        arrayList = temp;
                    } else {
                        String endTag = parser.getName();
                        if (!TAG_KEY.equals(endTag)) {
                            itemsLong = itemsLong2;
                            arrayList = temp;
                        } else {
                            items = null;
                        }
                    }
                    itemsLong2 = itemsLong;
                    temp = arrayList;
                }
            }
        } catch (IOException | XmlPullParserException ex) {
            ex.printStackTrace();
        }
        return map;
    }

    private void saveSceneTypePolicy(ArrayList<SceneTypePolicy> list, int sceneType, boolean longPress, SceneTypePolicy scePolicy) {
        HashMap<Integer, ArrayList<SceneTypePolicy>> sceneMap = getSceneMap(longPress);
        ArrayList<SceneTypePolicy> list2 = sceneMap.get(Integer.valueOf(sceneType));
        ArrayList<SceneTypePolicy> list3 = list2;
        if (list3 == null) {
            list3 = new ArrayList<>();
        }
        Log.d(TAG, "saveSceneTypePolicy longPress: " + longPress + "sceneType : " + sceneType + ",scePolicy.prio:" + scePolicy.getPriority() + ",scePolicy.policy:" + scePolicy.getPolicy());
        list3.add(scePolicy);
        sceneMap.put(Integer.valueOf(sceneType), list3);
        ArrayList<Integer> sceneList = getAllSceneList(longPress);
        sceneList.add(Integer.valueOf(sceneType));
    }

    public static int parsetoIntType(String name) {
        int sceneTypeValue = HardKeyManagerProxy.SCENE_UNKNOWN;
        if (!TextUtils.isEmpty(name) && SCENE_MAP.get(name.toUpperCase(Locale.US)) != null) {
            return SCENE_MAP.get(name.toUpperCase(Locale.US)).intValue();
        }
        return sceneTypeValue;
    }

    private void closeSilently(AutoCloseable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
                LogUtils.log(TAG, "Exception ignored");
            }
        }
    }

    public static HashMap<Integer, ArrayList<SceneTypePolicy>> getSceneMap(boolean longPress) {
        if (longPress) {
            return mSceneLongPressMap;
        }
        return mSceneMap;
    }

    public static ArrayList<Integer> getAllSceneList(boolean longPress) {
        return longPress ? mSceneLongPress : mSceneList;
    }

    public static HashMap<String, ArrayList<SceneTypePolicy>> getCache() {
        return mCache;
    }
}
