package io.branch.referral;

import android.content.Context;
import android.net.TrafficStats;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class UniversalResourceAnalyser {
    private static UniversalResourceAnalyser instance;
    private static JSONObject skipURLFormats;
    private final JSONObject DEFAULT_SKIP_URL_LIST;
    private final ArrayList<String> acceptURLFormats;

    public static UniversalResourceAnalyser getInstance(Context context) {
        if (instance == null) {
            instance = new UniversalResourceAnalyser(context);
        }
        return instance;
    }

    private UniversalResourceAnalyser(Context context) {
        JSONObject jSONObject = new JSONObject();
        this.DEFAULT_SKIP_URL_LIST = jSONObject;
        try {
            jSONObject.putOpt("version", 0);
            JSONArray jSONArray = new JSONArray();
            jSONObject.putOpt("uri_skip_list", jSONArray);
            jSONArray.put("^fb\\d+:");
            jSONArray.put("^li\\d+:");
            jSONArray.put("^pdk\\d+:");
            jSONArray.put("^twitterkit-.*:");
            jSONArray.put("^com\\.googleusercontent\\.apps\\.\\d+-.*:\\/oauth");
            jSONArray.put("^(?i)(?!(http|https):).*(:|:.*\\b)(password|o?auth|o?auth.?token|access|access.?token)\\b");
            jSONArray.put("^(?i)((http|https):\\/\\/).*[\\/|?|#].*\\b(password|o?auth|o?auth.?token|access|access.?token)\\b");
        } catch (JSONException unused) {
        }
        skipURLFormats = retrieveSkipURLFormats(context);
        this.acceptURLFormats = new ArrayList<>();
    }

    private JSONObject retrieveSkipURLFormats(Context context) {
        PrefHelper prefHelper = PrefHelper.getInstance(context);
        JSONObject jSONObject = new JSONObject();
        String string = prefHelper.getString("skip_url_format_key");
        if (TextUtils.isEmpty(string) || "bnc_no_value".equals(string)) {
            return this.DEFAULT_SKIP_URL_LIST;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return jSONObject;
        }
    }

    void checkAndUpdateSkipURLFormats(Context context) {
        try {
            new UrlSkipListUpdateTask(context).executeTask(new Void[0]);
        } catch (Throwable unused) {
        }
    }

    String getStrippedURL(String str) {
        String string;
        try {
            JSONArray jSONArrayOptJSONArray = skipURLFormats.optJSONArray("uri_skip_list");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    try {
                        string = jSONArrayOptJSONArray.getString(i);
                    } catch (JSONException unused) {
                    }
                    if (Pattern.compile(string).matcher(str).find()) {
                        break;
                    }
                }
                string = null;
            } else {
                string = null;
            }
            if (string == null) {
                if (this.acceptURLFormats.size() <= 0) {
                    return str;
                }
                Iterator<String> it = this.acceptURLFormats.iterator();
                while (it.hasNext()) {
                    if (str.matches(it.next())) {
                        return str;
                    }
                }
            }
            return string;
        } catch (Exception unused2) {
            return str;
        }
    }

    private static class UrlSkipListUpdateTask extends BranchAsyncTask<Void, Void, JSONObject> {
        private final int TIME_OUT;
        private final PrefHelper prefHelper;

        private UrlSkipListUpdateTask(Context context) {
            this.TIME_OUT = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.prefHelper = PrefHelper.getInstance(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void... voidArr) {
            HttpsURLConnection httpsURLConnection;
            TrafficStats.setThreadStatsTag(0);
            JSONObject jSONObject = new JSONObject();
            HttpsURLConnection httpsURLConnection2 = null;
            try {
                httpsURLConnection = (HttpsURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL("https://cdn.branch.io/sdk/uriskiplist_v#.json".replace("#", Integer.toString(UniversalResourceAnalyser.skipURLFormats.optInt("version") + 1))).openConnection()));
            } catch (Throwable unused) {
            }
            try {
                httpsURLConnection.setConnectTimeout(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                httpsURLConnection.setReadTimeout(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                if (httpsURLConnection.getResponseCode() == 200 && httpsURLConnection.getInputStream() != null) {
                    jSONObject = new JSONObject(new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream())).readLine());
                }
                httpsURLConnection.disconnect();
            } catch (Throwable unused2) {
                httpsURLConnection2 = httpsURLConnection;
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
            }
            return jSONObject;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject.optInt("version") > UniversalResourceAnalyser.skipURLFormats.optInt("version")) {
                JSONObject unused = UniversalResourceAnalyser.skipURLFormats = jSONObject;
                this.prefHelper.setString("skip_url_format_key", UniversalResourceAnalyser.skipURLFormats.toString());
            }
        }
    }
}
