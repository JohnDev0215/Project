package com.ts.car.dcm.dccontroller.configdata;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class StaticConfiguration implements IConfiguration {
    private static final String DEFAULT_CONFIG_PATH = "/system/etc/dcm/ts_dcm.conf";
    private static final String TAG = "StaticConfiguration";
    private HashMap<String, String> mConfigList = new HashMap<>();

    private void loadDefaultStaticConfiguration() {
        BufferedReader br = null;
        try {
            try {
                try {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(DEFAULT_CONFIG_PATH), "UTF-8"));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        String line2 = line.trim();
                        if (!line2.isEmpty() && line2.charAt(0) != '#') {
                            String[] keyValue = line2.split("=", 2);
                            if (keyValue.length == 2) {
                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();
                                if (!key.isEmpty() && !value.isEmpty()) {
                                    this.mConfigList.put(key, value);
                                }
                            }
                        }
                    }
                    br.close();
                } catch (IOException ex) {
                    Log.e(TAG, "loadDefaultStaticConfiguration, failed. ex:" + ex);
                    reset();
                    if (br == null) {
                    } else {
                        br.close();
                    }
                }
            } catch (Throwable th) {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
        }
    }

    public void reset() {
        HashMap<String, String> map = this.mConfigList;
        if (map != null) {
            map.clear();
        }
    }

    public StaticConfiguration(Context context) {
        loadDefaultStaticConfiguration();
        Log.d(TAG, "StaticConfiguration, loading:" + this.mConfigList);
        if (this.mConfigList.isEmpty()) {
            Log.e(TAG, "StaticConfiguration, Failed to load from ts_dcm.conf.");
        }
    }

    @Override // com.ts.car.dcm.dccontroller.configdata.IConfiguration
    public HashMap<String, String> getConfigInfo() {
        return this.mConfigList;
    }

    @Override // com.ts.car.dcm.dccontroller.configdata.IConfiguration
    public boolean saveConfigInfo(String key, String value) {
        return false;
    }
}
