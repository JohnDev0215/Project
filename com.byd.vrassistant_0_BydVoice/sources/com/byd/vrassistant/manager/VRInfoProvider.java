package com.byd.vrassistant.manager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.byd.common.bean.WuwInfoBean;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class VRInfoProvider extends ContentProvider {
    public static final String BYDWakeupKey = "BYDWakeUpWord";
    private static final String TAG = "NEU_VRInfoProvider";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        RLog.e(TAG, "call : method=" + str + ",arg=" + str2);
        Bundle bundle2 = new Bundle();
        if (!VoiceAssistantManager.get().getIsVoiceAssistantStared()) {
            bundle2.putString(WuwInfoBean.ReturnKey, "false");
            return bundle2;
        }
        if (WuwInfoBean.GetSubWakeUpWord.equals(str)) {
            bundle2.putString(WuwInfoBean.ValueKey, VoiceAssistantManager.get().getSubWakeUpWord());
            bundle2.putString(WuwInfoBean.ReturnKey, "true");
        } else if (WuwInfoBean.SetSubWakeUpWord.equals(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str2);
            VoiceAssistantManager.get().setVoiceAssistantWUW(arrayList);
            VoiceAssistantManager.get().setSubWUWValue(str2);
            bundle2.putString(WuwInfoBean.ReturnKey, "true");
        } else {
            bundle2.putString(WuwInfoBean.ReturnKey, "false");
        }
        return bundle2;
    }
}
