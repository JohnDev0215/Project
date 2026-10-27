package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SearchHisUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kg2 {
    public static final ArrayList<AutocompleteMatch> a = new ArrayList<>();

    public static ArrayList<AutocompleteMatch> a() {
        ArrayList<AutocompleteMatch> arrayList = a;
        if (arrayList.size() == 0) {
            String string = xz.a.a.getString("searchHistory", "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                        boolean z = jSONObject.getBoolean("mIsSearchType");
                        String string2 = jSONObject.getString("mDisplayText");
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(new AutocompleteMatch.a(0, 0));
                        arrayList.add(new AutocompleteMatch(-1, null, z, 0, 0, string2, arrayList2, jSONObject.getString("mDescription"), arrayList2, null, null, GURL.a(jSONObject.getString("mUrlSerialize")), GURL.emptyGURL(), null, jSONObject.getBoolean("mIsDeletable"), jSONObject.getString("mPostContentType"), null, jSONObject.getInt("mGroupId"), null, null, false, null, null));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return arrayList;
    }

    public static void b(AutocompleteMatch autocompleteMatch) {
        ArrayList<AutocompleteMatch> arrayList;
        boolean z;
        int i = 0;
        while (true) {
            arrayList = a;
            if (i >= arrayList.size()) {
                z = false;
                break;
            } else {
                if (autocompleteMatch.y.equals(arrayList.get(i).y)) {
                    z = true;
                    break;
                }
                i++;
            }
        }
        if (z) {
            return;
        }
        arrayList.add(0, autocompleteMatch);
        c(xz.a.a.edit());
    }

    public static void c(SharedPreferences.Editor editor) {
        editor.remove("searchHistory");
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (true) {
            ArrayList<AutocompleteMatch> arrayList = a;
            if (i >= arrayList.size()) {
                editor.putString("searchHistory", jSONArray.toString()).apply();
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("mUrlSerialize", arrayList.get(i).y);
                jSONObject.put("mType", arrayList.get(i).a);
                jSONObject.put("mDisplayText", arrayList.get(i).d);
                jSONObject.put("mIsSearchType", arrayList.get(i).c);
                jSONObject.put("mDescription", arrayList.get(i).f);
                jSONObject.put("mIsDeletable", arrayList.get(i).o);
                jSONObject.put("mPostContentType", TextUtils.isEmpty(arrayList.get(i).p) ? "" : arrayList.get(i).p);
                jSONObject.put("mGroupId", arrayList.get(i).r);
            } catch (JSONException e) {
                Log.e("searchHistory", "list2Json: " + e);
            }
            jSONArray.put(jSONObject);
            i++;
        }
    }
}
