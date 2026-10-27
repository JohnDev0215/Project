package com.neusoft.na.ehp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.neusoft.na.ehp.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class ConfigUtil {
    private static final String TAG = "NeuEhpConfigUtil";
    private Context mContext;

    public ConfigUtil(Context context) {
        this.mContext = context;
    }

    public boolean initConfig(Context context) {
        FileUtil fileUtil = new FileUtil();
        fileUtil.getPrivateTempDir(context).mkdirs();
        File privateCacheDir = fileUtil.getPrivateCacheDir(context);
        if (privateCacheDir != null && !privateCacheDir.isDirectory()) {
            privateCacheDir.mkdirs();
        }
        File file = new File(fileUtil.getAssetsInstallDir(context), "etc");
        File file2 = new File(fileUtil.getAssetsInstallDir(context), "version.txt");
        File file3 = new File(file, "nais/na_ehp_dbu.ini");
        File file4 = new File(file, "nais/na_serv_ehp.ini");
        if (!file.exists() || isHasNewConfig(file2)) {
            Log.i(TAG, "has new config file");
            AssetInstaller assetInstaller = new AssetInstaller(context.getAssets(), file.getParentFile());
            Log.d(TAG, "Installing config resources...");
            boolean zInstall = assetInstaller.install();
            testReadCode(this.mContext, file3);
            testReadCode(this.mContext, file4);
            testWriteCode(this.mContext, file3);
            testWriteCode(this.mContext, file4);
            if (zInstall) {
                try {
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(BuildConfig.VERSION_NAME.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return zInstall;
        }
        testReadCode(this.mContext, file3);
        testReadCode(this.mContext, file4);
        return true;
    }

    private boolean isHasNewConfig(File file) {
        String str;
        Log.i(TAG, "isHasNewConfig");
        if (!file.exists()) {
            return true;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            do {
                int i = fileInputStream.read(bArr);
                if (i > -1) {
                    str = new String(bArr, 0, i);
                    Log.i(TAG, "isHasNewConfig: " + str);
                } else {
                    fileInputStream.close();
                    return true;
                }
            } while (!BuildConfig.VERSION_NAME.equals(str));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void testReadCode(Context context, File file) {
        Log.i(TAG, "testReadCode: " + file.getName());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Log.i(TAG, "initConfig length: " + fileInputStream.read(new byte[1024]));
            fileInputStream.close();
        } catch (Exception e) {
            Log.e(TAG, "initConfig: testCode:" + e.getMessage());
            e.printStackTrace();
            deleteDirectory(getAssetPath(context));
        }
    }

    private void testWriteCode(Context context, File file) {
        Log.i(TAG, "testWriteCode: " + file.getName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write("#cache file test when first launch".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, "initConfig testCode:" + e.getMessage());
            e.printStackTrace();
            deleteDirectory(getAssetPath(context));
        }
    }

    public synchronized String getConfig() {
        StringBuilder sb;
        FileUtil fileUtil = new FileUtil();
        File file = new File(fileUtil.getAssetsInstallDir(this.mContext) + File.separator + "car" + File.separator + "tmp");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(fileUtil.getAssetsInstallDir(this.mContext) + File.separator + "car" + File.separator + "cache");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        sb = new StringBuilder();
        sb.append("--main.path_share=" + fileUtil.getAssetsInstallDir(this.mContext) + "/share/").append("\n");
        sb.append("--main.path_temp=" + fileUtil.getPrivateTempDir(this.mContext) + "/").append("\n");
        sb.append("--main.path_cache=" + fileUtil.getPrivateCacheDir(this.mContext) + "/").append("\n");
        sb.append("--main.resource_set=byd").append("\n");
        sb.append("--main.config=" + fileUtil.getAssetsInstallDir(this.mContext) + "/etc/nais/na_serv_ehp.ini").append("\n");
        sb.append("--location.where_am_i_Gnss_UrId=OFF").append("\n");
        File naviExternConfigDir = getNaviExternConfigDir();
        if (naviExternConfigDir.exists()) {
            sb.append("--main.config+=" + naviExternConfigDir.getAbsolutePath() + File.separator + "na_serv_ehp.ini").append("\n");
        }
        sb.append("--EhpService.deploy=" + fileUtil.getAssetsInstallDir(this.mContext) + "/etc/nais/na_serv_ehp_deploy.json").append("\n");
        sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so")).append("\n");
        sb.append("--nds.database=" + getDbPath() + "/ROOT.NDS").append("\n");
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("InputCfgValue", 0);
        sb.append("--AdasManager.Isa_TrafficInfo_Jam_Approach_Distance = " + sharedPreferences.getString("JamApproachDistance", "100")).append("\n");
        sb.append("--AdasManager.Isa_TrafficInfo_Jam_Disable_Time =" + sharedPreferences.getString("JamDisableTime", "5")).append("\n");
        sb.append("--AdasManager.Isa_TrafficInfo_Construction_Approach_Distance = " + sharedPreferences.getString("ConstructionApproachDistance", "100")).append("\n");
        sb.append("--AdasManager.Isa_TrafficInfo_Construction_Disable_Time = " + sharedPreferences.getString("ConstructionDisableTime", "5")).append("\n");
        sb.append("--AdasManager.Isa_TrafficInfo_Accident_Approach_Distance = " + sharedPreferences.getString("AccidentApproachDistance", "200")).append("\n");
        sb.append("--AdasManager.Isa_TrafficInfo_Accident_Disable_Time = " + sharedPreferences.getString("AccidentDisableTime", "5")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Roundabout_Approach_Distance = " + sharedPreferences.getString("RoundaboutApproachDistance", "100")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Roundabout_Approach_Time = " + sharedPreferences.getString("RoundaboutApproachTime", "5")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Roundabout_Disable_Time = " + sharedPreferences.getString("RoundaboutDisableTime", "3")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Junction_Approach_Distance = " + sharedPreferences.getString("JunctionApproachDistance", "40")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Junction_Approach_Time = " + sharedPreferences.getString("JunctionApproachTime", "5")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Junction_Disable_Time = " + sharedPreferences.getString("JunctionDisableTime", "3")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_TrafficLight_Approach_Distance = " + sharedPreferences.getString("TrafficLightApproachDistance", "90")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_TrafficLight_Approach_Time = " + sharedPreferences.getString("TrafficLightApproachTime", "0")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_TrafficLight_Disable_Time = " + sharedPreferences.getString("TrafficLightDisableTime", "3")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Stop_Approach_Distance = " + sharedPreferences.getString("StopApproachDistance", "50")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Stop_Approach_Time = " + sharedPreferences.getString("StopApproachTime", "0")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Stop_Disable_Time = " + sharedPreferences.getString("StopDisableTime", "3")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Yield_Approach_Distance = " + sharedPreferences.getString("YieldApproachDistance", "50")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Yield_Approach_Time = " + sharedPreferences.getString("YieldApproachTime", "0")).append("\n");
        sb.append("--AdasManager.Isa_TrafficSign_Yield_Disable_Time = " + sharedPreferences.getString("YieldDisableTime", "3")).append("\n");
        Log.d(TAG, "ehp start >> " + sb.toString());
        return sb.toString();
    }

    private String getSharedLibraryPath(String str) {
        return Build.VERSION.SDK_INT < 19 ? this.mContext.getApplicationInfo().nativeLibraryDir + "/" + str : str;
    }

    private File getNaviExternConfigDir() {
        return new File("/sdcard/isaexternconfig");
    }

    public String getDbPath() {
        String line;
        String str = "/sdcard/";
        if (new File("/sdcard/isaexternconfig/userTest.ini").exists()) {
            return "/sdcard/";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("etc/nais/na_ehp_dbu.ini")));
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
            } while (!line.replace(" ", ExtensionRequestData.EMPTY_VALUE).startsWith("path_mapdata"));
            String[] strArrSplit = line.split("=");
            if (strArrSplit.length > 1) {
                str = strArrSplit[1];
            }
        } catch (IOException e) {
            Log.e(TAG, "getDbPath: " + e.getMessage());
        }
        return str + "NeusoftISA/db";
    }

    public String getAssetPath(Context context) {
        return new FileUtil().getAssetsInstallDir(context).getAbsolutePath();
    }

    public void deleteAll(Context context) {
        deleteDirectory(getAssetPath(context));
    }

    private boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteDirectory = true;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isFile()) {
                zDeleteDirectory = deleteFile(fileArrListFiles[i].getAbsolutePath());
                if (!zDeleteDirectory) {
                    break;
                }
            } else {
                if (fileArrListFiles[i].isDirectory() && !(zDeleteDirectory = deleteDirectory(fileArrListFiles[i].getAbsolutePath()))) {
                    break;
                }
            }
        }
        return zDeleteDirectory;
    }

    private static boolean deleteFile(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }
}
