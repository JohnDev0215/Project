package com.byd.ime.subtype.model;

import android.content.Context;
import androidx.preference.PreferenceManager;
import com.byd.ime.subtype.SubtypeDebugger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SubtypeStoreModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeStoreModel;", "", "()V", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeStoreModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PR_SUBTYPE_LANGUAGE_KEY = "prior_subtype_language";
    private static final String PR_SYSTEM_LANGUAGE_KEY = "prior_system_language";
    private static final String PR_SYSTEM_LANGUAGE_VALUE_DEFAULT = "not_set";
    private static final String USER_SELECT_SUBTYPE_KEY = "select_subtype_sp_key";

    /* JADX INFO: compiled from: SubtypeStoreModel.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\u0018\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\u001b\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/byd/ime/subtype/model/SubtypeStoreModel$Companion;", "", "()V", "PR_SUBTYPE_LANGUAGE_KEY", "", "PR_SYSTEM_LANGUAGE_KEY", "PR_SYSTEM_LANGUAGE_VALUE_DEFAULT", "USER_SELECT_SUBTYPE_KEY", "clearUserSelectFromStore", "", "context", "Landroid/content/Context;", "getSubtypeLocalFromStore", "getSystemLanguageFromStore", "getUserSelectFromStore", "", "isFirstInit", "", "parseJsonToSubtypeData", "Lcom/byd/ime/subtype/model/SubtypeConfigData;", "saveSubtypeLocalToStore", SubtypeConfigData.JSON_KEY_SUBTYPE_LOCAL, "saveSystemLanguage", "systemLanguage", "saveUserSelectItemToStore", "userSelectLocalList", "Lcom/byd/ime/subtype/model/SubtypeItem;", "saveUserSelectToStore", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<SubtypeConfigData> parseJsonToSubtypeData(Context context) throws JSONException {
            Intrinsics.checkNotNullParameter(context, "context");
            ArrayList arrayList = new ArrayList();
            try {
                InputStream inputStreamOpen = context.getResources().getAssets().open(SubtypeConfigData.JSON_FILE_NAME);
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                String str = new String(bArr, Charsets.UTF_8);
                inputStreamOpen.close();
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    SubtypeConfigData subtypeConfigData = new SubtypeConfigData();
                    String string = jSONObject.getString(SubtypeConfigData.JSON_KEY_LANGUAGE);
                    Intrinsics.checkNotNullExpressionValue(string, "jSONObject.getString(Sub…igData.JSON_KEY_LANGUAGE)");
                    subtypeConfigData.setLanguage(string);
                    String string2 = jSONObject.getString(SubtypeConfigData.JSON_KEY_SUBTYPE_LOCAL);
                    Intrinsics.checkNotNullExpressionValue(string2, "jSONObject.getString(Sub…a.JSON_KEY_SUBTYPE_LOCAL)");
                    subtypeConfigData.setSubtypeLocal(string2);
                    subtypeConfigData.setState(jSONObject.getInt(SubtypeConfigData.JSON_KEY_STATE));
                    arrayList.add(subtypeConfigData);
                }
            } catch (IOException e) {
                SubtypeDebugger.logE("parseJsonToSubtypeData e=" + e);
            }
            return arrayList;
        }

        public final void saveSystemLanguage(String systemLanguage, Context context) {
            Intrinsics.checkNotNullParameter(systemLanguage, "systemLanguage");
            Intrinsics.checkNotNullParameter(context, "context");
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(SubtypeStoreModel.PR_SYSTEM_LANGUAGE_KEY, systemLanguage).apply();
        }

        public final void saveSubtypeLocalToStore(String subtypeLocal, Context context) {
            Intrinsics.checkNotNullParameter(subtypeLocal, "subtypeLocal");
            Intrinsics.checkNotNullParameter(context, "context");
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(SubtypeStoreModel.PR_SUBTYPE_LANGUAGE_KEY, subtypeLocal).apply();
        }

        public final String getSystemLanguageFromStore(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return PreferenceManager.getDefaultSharedPreferences(context).getString(SubtypeStoreModel.PR_SYSTEM_LANGUAGE_KEY, SubtypeStoreModel.PR_SYSTEM_LANGUAGE_VALUE_DEFAULT);
        }

        public final String getSubtypeLocalFromStore(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return PreferenceManager.getDefaultSharedPreferences(context).getString(SubtypeStoreModel.PR_SUBTYPE_LANGUAGE_KEY, SubtypeStoreModel.PR_SYSTEM_LANGUAGE_VALUE_DEFAULT);
        }

        public final boolean isFirstInit(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return Intrinsics.areEqual(getSystemLanguageFromStore(context), SubtypeStoreModel.PR_SYSTEM_LANGUAGE_VALUE_DEFAULT);
        }

        private final void saveUserSelectToStore(List<String> userSelectLocalList, Context context) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(userSelectLocalList);
            PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(SubtypeStoreModel.USER_SELECT_SUBTYPE_KEY, hashSet).apply();
        }

        public final void saveUserSelectItemToStore(List<SubtypeItem> userSelectLocalList, Context context) {
            Intrinsics.checkNotNullParameter(userSelectLocalList, "userSelectLocalList");
            Intrinsics.checkNotNullParameter(context, "context");
            ArrayList arrayList = new ArrayList();
            int size = userSelectLocalList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(userSelectLocalList.get(i).getLocal());
            }
            saveUserSelectToStore(arrayList, context);
        }

        public final List<String> getUserSelectFromStore(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Set<String> stringSet = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(SubtypeStoreModel.USER_SELECT_SUBTYPE_KEY, new HashSet());
            Intrinsics.checkNotNull(stringSet, "null cannot be cast to non-null type java.util.HashSet<kotlin.String>{ kotlin.collections.TypeAliasesKt.HashSet<kotlin.String> }");
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((HashSet) stringSet);
            return arrayList;
        }

        public final void clearUserSelectFromStore(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(SubtypeStoreModel.USER_SELECT_SUBTYPE_KEY).apply();
        }
    }
}
