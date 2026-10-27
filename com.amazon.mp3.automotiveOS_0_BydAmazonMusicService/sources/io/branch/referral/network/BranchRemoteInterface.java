package io.branch.referral.network;

import android.content.Context;
import androidx.annotation.Nullable;
import io.branch.referral.Branch;
import io.branch.referral.Defines$Jsonkey;
import io.branch.referral.PrefHelper;
import io.branch.referral.ServerResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BranchRemoteInterface {
    public abstract BranchResponse doRestfulGet(String str) throws BranchRemoteException;

    public abstract BranchResponse doRestfulPost(String str, JSONObject jSONObject) throws BranchRemoteException;

    public final ServerResponse make_restful_get(String str, JSONObject jSONObject, String str2, String str3) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, -114);
        }
        String str4 = str + convertJSONtoString(jSONObject);
        long jCurrentTimeMillis = System.currentTimeMillis();
        PrefHelper.Debug("getting " + str4);
        try {
            try {
                BranchResponse branchResponseDoRestfulGet = doRestfulGet(str4);
                ServerResponse serverResponseProcessEntityForJSON = processEntityForJSON(branchResponseDoRestfulGet.responseData, branchResponseDoRestfulGet.responseCode, str2);
                if (Branch.getInstance() != null) {
                    int iCurrentTimeMillis = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                    Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis));
                }
                return serverResponseProcessEntityForJSON;
            } catch (BranchRemoteException e) {
                if (e.branchErrorCode == -111) {
                    ServerResponse serverResponse = new ServerResponse(str2, -111);
                    if (Branch.getInstance() != null) {
                        int iCurrentTimeMillis2 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                        Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis2));
                    }
                    return serverResponse;
                }
                ServerResponse serverResponse2 = new ServerResponse(str2, -113);
                if (Branch.getInstance() != null) {
                    int iCurrentTimeMillis3 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                    Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis3));
                }
                return serverResponse2;
            }
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                int iCurrentTimeMillis4 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis4));
            }
            throw th;
        }
    }

    public final ServerResponse make_restful_post(JSONObject jSONObject, String str, String str2, String str3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, -114);
        }
        PrefHelper.Debug("posting to " + str);
        PrefHelper.Debug("Post value = " + jSONObject.toString());
        try {
            try {
                BranchResponse branchResponseDoRestfulPost = doRestfulPost(str, jSONObject);
                ServerResponse serverResponseProcessEntityForJSON = processEntityForJSON(branchResponseDoRestfulPost.responseData, branchResponseDoRestfulPost.responseCode, str2);
                if (Branch.getInstance() != null) {
                    int iCurrentTimeMillis = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                    Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis));
                }
                return serverResponseProcessEntityForJSON;
            } catch (BranchRemoteException e) {
                if (e.branchErrorCode == -111) {
                    ServerResponse serverResponse = new ServerResponse(str2, -111);
                    if (Branch.getInstance() != null) {
                        int iCurrentTimeMillis2 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                        Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis2));
                    }
                    return serverResponse;
                }
                ServerResponse serverResponse2 = new ServerResponse(str2, -113);
                if (Branch.getInstance() != null) {
                    int iCurrentTimeMillis3 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                    Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis3));
                }
                return serverResponse2;
            }
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                int iCurrentTimeMillis4 = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf(iCurrentTimeMillis4));
            }
            throw th;
        }
    }

    public static final BranchRemoteInterface getDefaultBranchRemoteInterface(Context context) {
        return new BranchRemoteInterfaceUrlConnection(context);
    }

    private ServerResponse processEntityForJSON(String str, int i, String str2) {
        ServerResponse serverResponse = new ServerResponse(str2, i);
        PrefHelper.Debug("returned " + str);
        if (str != null) {
            try {
                try {
                    serverResponse.setPost(new JSONObject(str));
                } catch (JSONException e) {
                    PrefHelper.Debug("JSON exception: " + e.getMessage());
                }
            } catch (JSONException unused) {
                serverResponse.setPost(new JSONArray(str));
            }
        }
        return serverResponse;
    }

    private boolean addCommonParams(JSONObject jSONObject, String str) {
        try {
            if (!jSONObject.has(Defines$Jsonkey.UserData.getKey())) {
                jSONObject.put(Defines$Jsonkey.SDK.getKey(), "android4.1.1");
            }
            if (str.equals("bnc_no_value")) {
                return false;
            }
            jSONObject.put(Defines$Jsonkey.BranchKey.getKey(), str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    private String convertJSONtoString(JSONObject jSONObject) {
        JSONArray jSONArrayNames;
        StringBuilder sb = new StringBuilder();
        if (jSONObject != null && (jSONArrayNames = jSONObject.names()) != null) {
            int length = jSONArrayNames.length();
            boolean z = true;
            for (int i = 0; i < length; i++) {
                try {
                    String string = jSONArrayNames.getString(i);
                    if (z) {
                        sb.append("?");
                        z = false;
                    } else {
                        sb.append("&");
                    }
                    String string2 = jSONObject.getString(string);
                    sb.append(string);
                    sb.append("=");
                    sb.append(string2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return sb.toString();
    }

    public static class BranchResponse {
        private final int responseCode;
        private final String responseData;

        public BranchResponse(@Nullable String str, int i) {
            this.responseData = str;
            this.responseCode = i;
        }
    }

    public static class BranchRemoteException extends Exception {
        private int branchErrorCode;

        public BranchRemoteException(int i) {
            this.branchErrorCode = i;
        }
    }
}
