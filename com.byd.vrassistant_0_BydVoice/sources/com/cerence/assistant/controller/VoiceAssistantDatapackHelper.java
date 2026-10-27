package com.cerence.assistant.controller;

import android.util.Log;
import cerence.ark.assistant.framework.data.Language;
import cerence.ark.assistant.library.JsonKey;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.byd.vrdownload.model.LanguageUpdate;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceAssistantDatapackHelper {
    private static final String DATA_DEFAULT_VERSION = "D.0.00.0000A";
    private static final String DATA_LOCK_FILE = "package.lock";
    public static final String INVALID_VALUE = "null";
    private static final String SDK1_PRJ_V = "1";
    private static final String SDK2_PRJ_V = "2";
    private static final String SDK4_PRJ_V = "4";
    private static final String SDK8155_PRJ_V = "3";
    private static final String SDK_DEFAULT_VERSION = "0.00.0000A";
    private static final String TAG = "VoiceAssistantDatapackHelper";

    public static String getVoiceAssistantWorkDatapack(String str, String str2, String str3, String str4) {
        String str5 = TAG;
        Log.w(str5, String.format("vrBasePath: %s, GenPath:%s, intlPackagePath: %s, Lang: %s", str, str2, str3, str4));
        String str6 = "data_" + str4;
        String str7 = str3 + File.separator + str6;
        File file = new File(str7);
        if (!FileUtils.isDir(file)) {
            Log.e(str5, String.format("%s not exists %s", file.getAbsolutePath(), Boolean.valueOf(FileUtils.isDir(file))));
            return "null";
        }
        if (str2.equals(str3)) {
            Log.e(str5, String.format("invalid parameters, duplicated generation package path: %s", str7));
            return file.getAbsolutePath();
        }
        File file2 = new File(str);
        if (!FileUtils.isFileExists(file2)) {
            Log.e(str5, String.format("%s not exists", str));
            return file.getAbsolutePath();
        }
        File file3 = new File(str2 + File.separator + str6);
        String sDKVrVersion = getSDKVrVersion(file);
        String vrBasePackageVersion = getVrBasePackageVersion(file2);
        String vrPackageVersion = getVrPackageVersion(file);
        String vrPackageVersion2 = getVrPackageVersion(file3);
        String vrBasePackageVersion2 = getVrBasePackageVersion(file3);
        Log.d(str5, String.format("sdkVersion: %s, basePackageVersion:%s, intlPackageVersion: %s, genPackageVersion: %s", sDKVrVersion, vrBasePackageVersion, vrPackageVersion, vrPackageVersion2));
        if (sDKVrVersion.equals(SDK_DEFAULT_VERSION)) {
            return "null";
        }
        if (checkVersionCompatible(vrPackageVersion, sDKVrVersion)) {
            Log.w(str5, "internal package is the latest version");
            return file.getAbsolutePath();
        }
        if (!checkVersionCompatible(vrBasePackageVersion, sDKVrVersion)) {
            Log.w(str5, "vr base package is not completed");
            return file.getAbsolutePath();
        }
        if (!checkDatapackIsLocked(file3).booleanValue() && checkVersionCompatible(vrPackageVersion2, vrPackageVersion) && checkVersionCompatible(vrBasePackageVersion2, vrBasePackageVersion)) {
            Log.w(str5, "generation package is the latest version");
            return file3.getAbsolutePath();
        }
        if (!migrateBaseIntlPackage(file2, file, file3, str4).booleanValue()) {
            Log.w(str5, "migration failed");
            return file.getAbsolutePath();
        }
        return file3.getAbsolutePath();
    }

    private static String getVrPackageVersion(File file) {
        if (!FileUtils.isDir(file) || checkDatapackIsLocked(file).booleanValue()) {
            return DATA_DEFAULT_VERSION;
        }
        try {
            return new JSONObject(DomainsAutoController.getVrVersion(file.getAbsolutePath())).getString("vr_dp_version");
        } catch (JSONException e) {
            e.printStackTrace();
            return DATA_DEFAULT_VERSION;
        }
    }

    private static String getVrBasePackageVersion(File file) {
        try {
            String str = file.getAbsolutePath() + File.separator + JsonKey.VERSION_NAME + File.separator + "version.json";
            if (!FileUtils.isFileExists(str)) {
                Log.d(TAG, String.format("getVrBasePackageVersion %s is not exists", str));
                return DATA_DEFAULT_VERSION;
            }
            JSONArray jSONArrayOptJSONArray = new JSONObject(FileIOUtils.readFile2String(str)).optJSONArray(JsonKey.VERSION_COMPONENTS);
            for (int i = 0; i < ((JSONArray) Objects.requireNonNull(jSONArrayOptJSONArray)).length(); i++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i);
                if (jSONObject.getString("name").equals("VR_BASE_VERSION")) {
                    return jSONObject.getString(JsonKey.VERSION_NAME);
                }
            }
            return DATA_DEFAULT_VERSION;
        } catch (Exception e) {
            e.printStackTrace();
            return DATA_DEFAULT_VERSION;
        }
    }

    private static String getSDKVrVersion(File file) {
        try {
            return new JSONObject(DomainsAutoController.getVrVersion(file.getAbsolutePath())).getString("vr_sdk_version");
        } catch (JSONException e) {
            e.printStackTrace();
            return SDK_DEFAULT_VERSION;
        }
    }

    private static Boolean createDataPackLocker(File file) {
        return Boolean.valueOf(FileUtils.createOrExistsFile(new File(file, DATA_LOCK_FILE)));
    }

    private static Boolean deleteDataPackLocker(File file) {
        return Boolean.valueOf(FileUtils.delete(new File(file, DATA_LOCK_FILE)));
    }

    private static Boolean checkDatapackIsLocked(File file) {
        return Boolean.valueOf(FileUtils.isFileExists(new File(file, DATA_LOCK_FILE)));
    }

    private static Boolean migrateBaseIntlPackage(File file, File file2, File file3, String str) {
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("asr_common");
        arrayList3.add("common");
        arrayList3.add("device_model_specified");
        arrayList3.add("dialog");
        arrayList3.add(JsonKey.VERSION_NAME);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("asr");
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add("vocalizer");
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add("asr_paths.json");
        arrayList6.add("clc_paths.json");
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add("prompter_voice_data_path.json");
        HashMap map = new HashMap();
        map.put(Language.ENG_GBR, "eng-USA");
        map.put(Language.ENG_SGP, "eng-USA");
        map.put(Language.ENG_XWW, "eng-USA");
        map.put(Language.ENG_AUS, "eng-USA");
        map.put(Language.SPA_ESP, "spa-XLA");
        map.put(Language.POR_PRT, "por-BRA");
        try {
            if (file3.exists()) {
                arrayList = arrayList7;
                str2 = "vocalizer";
                Log.d(TAG, String.format("delete package: %s", file3.getAbsolutePath()));
                FileUtils.delete(file3);
            } else {
                str2 = "vocalizer";
                arrayList = arrayList7;
            }
            if (file3.mkdirs() && createDataPackLocker(file3).booleanValue()) {
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    File file4 = new File(file, str3);
                    File file5 = new File(file3, str3);
                    Iterator it2 = it;
                    Log.d(TAG, String.format("copy folder/file: %s -> %s", file4.getName(), file5.getName()));
                    if (!FileUtils.copy(file4, file5)) {
                        return false;
                    }
                    it = it2;
                }
                String str4 = "asr_" + str;
                File file6 = new File(file2, str4);
                if (!file6.exists() && map.containsKey(str)) {
                    str4 = "asr_" + ((String) map.get(str));
                    file6 = new File(file2, str4);
                }
                File file7 = new File(file3, str4);
                List<File> listListFilesInDir = FileUtils.listFilesInDir(file6);
                int i = 0;
                while (i < listListFilesInDir.size()) {
                    if (arrayList4.contains(listListFilesInDir.get(i).getName())) {
                        arrayList2 = arrayList4;
                        Log.d(TAG, String.format("skip folder/file: %s", listListFilesInDir.get(i).getName()));
                    } else {
                        arrayList2 = arrayList4;
                        if (!FileUtils.copy(listListFilesInDir.get(i), new File(file7, listListFilesInDir.get(i).getName()))) {
                            return false;
                        }
                    }
                    i++;
                    arrayList4 = arrayList2;
                }
                File file8 = new File(file2, "prompter");
                File file9 = new File(file3, "prompter");
                List<File> listListFilesInDir2 = FileUtils.listFilesInDir(file8);
                for (int i2 = 0; i2 < listListFilesInDir2.size(); i2++) {
                    if (arrayList5.contains(listListFilesInDir2.get(i2).getName())) {
                        Log.d(TAG, String.format("skip folder/file: %s", listListFilesInDir2.get(i2).getName()));
                    } else if (!FileUtils.copy(listListFilesInDir2.get(i2), new File(file9, listListFilesInDir2.get(i2).getName()))) {
                        return false;
                    }
                }
                List<File> listListFilesInDir3 = FileUtils.listFilesInDir(new File(file7, "json"));
                String str5 = file6.getAbsolutePath() + File.separator + "asr";
                for (int i3 = 0; i3 < listListFilesInDir3.size(); i3++) {
                    if (arrayList6.contains(listListFilesInDir3.get(i3).getName())) {
                        String file2String = FileIOUtils.readFile2String(listListFilesInDir3.get(i3));
                        if (file2String.isEmpty()) {
                            Log.e(TAG, String.format("config Json:%s empty", file2String));
                            return false;
                        }
                        String strReplace = file2String.replace("../asr/", str5 + File.separator);
                        if (!FileIOUtils.writeFileFromString(listListFilesInDir3.get(i3), strReplace)) {
                            Log.e(TAG, String.format("config Json:%s replace", strReplace));
                            return false;
                        }
                    }
                }
                List<File> listListFilesInDir4 = FileUtils.listFilesInDir(new File(file9, "json"));
                String str6 = file8.getAbsolutePath() + File.separator + str2;
                int i4 = 0;
                while (i4 < listListFilesInDir4.size()) {
                    ArrayList arrayList8 = arrayList;
                    if (arrayList8.contains(listListFilesInDir4.get(i4).getName())) {
                        String file2String2 = FileIOUtils.readFile2String(listListFilesInDir4.get(i4));
                        if (file2String2.isEmpty()) {
                            Log.e(TAG, String.format("config Json:%s empty", file2String2));
                            return false;
                        }
                        String strReplace2 = file2String2.replace("../vocalizer", str6);
                        if (!FileIOUtils.writeFileFromString(listListFilesInDir4.get(i4), strReplace2)) {
                            Log.e(TAG, String.format("config Json:%s replace", strReplace2));
                            return false;
                        }
                    }
                    i4++;
                    arrayList = arrayList8;
                }
                File file10 = new File(file, str4);
                if (FileUtils.isDir(file10) && !FileUtils.copy(file10, file7)) {
                    Log.e(TAG, String.format("copy %s -> %s failed", file10.getName(), file7.getName()));
                    return false;
                }
                String str7 = file2.getAbsoluteFile() + File.separator + JsonKey.VERSION_NAME + File.separator + "version.json";
                String str8 = file3.getAbsoluteFile() + File.separator + JsonKey.VERSION_NAME + File.separator + "version.json";
                try {
                    JSONObject jSONObject = new JSONObject(FileIOUtils.readFile2String(str7));
                    JSONObject jSONObject2 = new JSONObject(FileIOUtils.readFile2String(str8));
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(JsonKey.VERSION_COMPONENTS);
                    for (int i5 = 0; i5 < ((JSONArray) Objects.requireNonNull(jSONArrayOptJSONArray)).length(); i5++) {
                        String string = jSONArrayOptJSONArray.getJSONObject(i5).getString("name");
                        if (string.equals("DATAPACK_VERSION") || string.startsWith("ONEX_")) {
                            jSONObject2.getJSONArray(JsonKey.VERSION_COMPONENTS).put(jSONArrayOptJSONArray.getJSONObject(i5));
                        }
                    }
                    jSONObject2.put(LanguageUpdate.LANGUAGE, "data_" + str);
                    if (FileIOUtils.writeFileFromString(str8, jSONObject2.toString(4)) && deleteDataPackLocker(file3).booleanValue()) {
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static boolean checkVersionCompatible(String str, String str2) {
        String strSubstring = str.substring(str.length() - 10);
        String strSubstring2 = str2.substring(str2.length() - 10);
        String strSubstring3 = strSubstring.substring(0, 1);
        String strSubstring4 = strSubstring2.substring(0, 1);
        String strSubstring5 = strSubstring.substring(2);
        String strSubstring6 = strSubstring2.substring(2);
        Log.d(TAG, String.format("vv1: %s, vv2: %s, prjV1: %s, prjV2: %s, subV1: %s, subV2: %s", strSubstring, strSubstring2, strSubstring3, strSubstring4, strSubstring5, strSubstring6));
        if ((strSubstring3.equals(SDK2_PRJ_V) && strSubstring4.equals(SDK8155_PRJ_V)) || (strSubstring3.equals(SDK8155_PRJ_V) && strSubstring4.equals(SDK2_PRJ_V))) {
            return false;
        }
        return strSubstring5.equals(strSubstring6);
    }
}
