package cerence.ark.assistant.dm;

import cerence.ark.assistant.dm.data.DmTokenInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface DmTokenProvider {
    void onAbort();

    DmTokenInfo onAuthenticate();

    boolean onConfigure(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8);

    void onDestroy();

    int onGetTokenProviderType();

    DmTokenInfo onRefresh(String str);
}
