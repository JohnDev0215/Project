package com.amazon.music.media.auto.googleAssistant;

import android.os.Bundle;
import com.amazon.music.media.auto.util.AutoLog;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GoogleAssistantVoiceSearchUtility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/googleAssistant/GoogleAssistantVoiceSearchUtility;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "parseOnPlayFromSearch", "Lcom/amazon/music/media/auto/googleAssistant/GoogleAssistantVoiceSearchQuery;", "bundle", "Landroid/os/Bundle;", SearchIntents.EXTRA_QUERY, "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GoogleAssistantVoiceSearchUtility {
    public static final GoogleAssistantVoiceSearchUtility INSTANCE = new GoogleAssistantVoiceSearchUtility();
    private static final String TAG = GoogleAssistantVoiceSearchUtility.class.getSimpleName();

    private GoogleAssistantVoiceSearchUtility() {
    }

    public final GoogleAssistantVoiceSearchQuery parseOnPlayFromSearch(Bundle bundle, String query) {
        String string;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (bundle == null) {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            string = null;
        } else {
            String string2 = bundle.getString("android.intent.extra.album");
            String string3 = bundle.getString("android.intent.extra.artist");
            String string4 = bundle.getString("android.intent.extra.genre");
            String string5 = bundle.getString("android.intent.extra.playlist");
            String string6 = bundle.getString("android.intent.extra.title");
            string = bundle.getString("android.intent.extra.focus");
            str = string2;
            str2 = string3;
            str3 = string4;
            str4 = string5;
            str5 = string6;
        }
        GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery = new GoogleAssistantVoiceSearchQuery(query, str, str2, str3, str4, str5, string);
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, googleAssistantVoiceSearchQuery.toString());
        return googleAssistantVoiceSearchQuery;
    }
}
