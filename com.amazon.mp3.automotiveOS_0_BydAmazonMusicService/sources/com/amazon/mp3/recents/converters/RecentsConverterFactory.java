package com.amazon.mp3.recents.converters;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.recentlyplayed.views.RecentsVerticalTileConverter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsConverterFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/recents/converters/RecentsConverterFactory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "recentsConverter", "Lcom/amazon/mp3/recents/converters/RecentsPageGridConverter;", "getRecentsConverter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsConverterFactory {
    private final Context context;
    private final RecentsPageGridConverter recentsConverter;

    public RecentsConverterFactory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        RecentsHorizontalTileConverter recentsHorizontalTileConverter = new RecentsHorizontalTileConverter(context);
        RecentsVerticalTileConverter recentsVerticalTileConverter = new RecentsVerticalTileConverter(context);
        String string = context.getString(R.string.dmusic_recent_activity_tab);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…usic_recent_activity_tab)");
        RecentsSectionGridConverter recentsSectionGridConverter = new RecentsSectionGridConverter(recentsHorizontalTileConverter, string);
        String string2 = context.getString(R.string.dmusic_recently_played);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.dmusic_recently_played)");
        this.recentsConverter = new RecentsPageGridConverter(new RecentsShovelerConverter(recentsVerticalTileConverter, string2), recentsSectionGridConverter);
    }

    public final RecentsPageGridConverter getRecentsConverter() {
        return this.recentsConverter;
    }
}
