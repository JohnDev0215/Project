package io.branch.referral;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class BranchPreinstall {
    BranchPreinstall() {
    }

    public static void getPreinstallSystemData(Branch branch, Context context) {
        if (branch != null) {
            String strCheckForBranchPreinstallInSystem = checkForBranchPreinstallInSystem();
            if (TextUtils.isEmpty(strCheckForBranchPreinstallInSystem)) {
                return;
            }
            readBranchFile(strCheckForBranchPreinstallInSystem, branch, context);
        }
    }

    private static String checkForBranchPreinstallInSystem() {
        try {
            return (String) SystemProperties.class.getMethod("get", String.class).invoke(null, "io.branch.preinstall.apps.path");
        } catch (Exception unused) {
            return null;
        }
    }

    private static void readBranchFile(final String str, final Branch branch, final Context context) {
        new Thread(new Runnable() { // from class: io.branch.referral.BranchPreinstall.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(str)));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    }
                    bufferedReader.close();
                    JSONObject jSONObject = new JSONObject(sb.toString().trim());
                    if (!TextUtils.isEmpty(jSONObject.toString())) {
                        BranchPreinstall.getBranchFileContent(jSONObject, branch, context);
                        return;
                    }
                    throw new FileNotFoundException();
                } catch (FileNotFoundException | IOException | JSONException unused) {
                }
            }
        }).start();
    }

    public static void getBranchFileContent(JSONObject jSONObject, Branch branch, Context context) {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (next.equals("apps") && (jSONObject.get(next) instanceof JSONObject) && jSONObject.getJSONObject(next).get(SystemObserver.getPackageName(context)) != null) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next).getJSONObject(SystemObserver.getPackageName(context));
                    Iterator<String> itKeys2 = jSONObject2.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        Defines$PreinstallKey defines$PreinstallKey = Defines$PreinstallKey.campaign;
                        if (next2.equals(defines$PreinstallKey.getKey()) && TextUtils.isEmpty(PrefHelper.getInstance(context).getInstallMetaData(defines$PreinstallKey.getKey()))) {
                            branch.setPreinstallCampaign(jSONObject2.get(next2).toString());
                        } else {
                            Defines$PreinstallKey defines$PreinstallKey2 = Defines$PreinstallKey.partner;
                            if (next2.equals(defines$PreinstallKey2.getKey()) && TextUtils.isEmpty(PrefHelper.getInstance(context).getInstallMetaData(defines$PreinstallKey2.getKey()))) {
                                branch.setPreinstallPartner(jSONObject2.get(next2).toString());
                            } else {
                                branch.setRequestMetadata(next2, jSONObject2.get(next2).toString());
                            }
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
