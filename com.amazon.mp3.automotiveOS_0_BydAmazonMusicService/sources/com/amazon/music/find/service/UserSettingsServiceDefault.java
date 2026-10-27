package com.amazon.music.find.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserSettingsServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/service/UserSettingsServiceDefault;", "Lcom/amazon/music/find/service/UserSettingsService;", "context", "Landroid/content/Context;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "getClearSearchHistoryDateKey", "", "getLastClearSearchHistoryDate", "", "updateLastClearSearchHistoryDate", "", "lastModifiedAt", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserSettingsServiceDefault implements UserSettingsService {
    public static final String CLEAR_SEARCH_HISTORY_DATE = "clear_search_history_date";
    public static final String FIND_PREF_FILE_KEY = "com.amazon.mp3.find";
    private final Context context;
    private final SearchFeaturesProvider searchFeaturesProvider;

    public UserSettingsServiceDefault(Context context, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.context = context;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    private final String getClearSearchHistoryDateKey() {
        return Intrinsics.stringPlus("clear_search_history_date_", this.searchFeaturesProvider.getCurrentProfileMusicDirectedId());
    }

    @Override // com.amazon.music.find.service.UserSettingsService
    public long getLastClearSearchHistoryDate() {
        return this.context.getSharedPreferences(FIND_PREF_FILE_KEY, 0).getLong(getClearSearchHistoryDateKey(), 0L);
    }

    @Override // com.amazon.music.find.service.UserSettingsService
    public void updateLastClearSearchHistoryDate(long lastModifiedAt) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(FIND_PREF_FILE_KEY, 0);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(getClearSearchHistoryDateKey(), lastModifiedAt);
        editorEdit.apply();
    }
}
