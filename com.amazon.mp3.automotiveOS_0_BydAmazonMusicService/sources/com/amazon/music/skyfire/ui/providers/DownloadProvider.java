package com.amazon.music.skyfire.ui.providers;

import android.content.Context;
import com.amazon.music.views.library.views.LibraryAddableView;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "", "downloadTrack", "", "context", "Landroid/content/Context;", "view", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "asin", "", "shouldShowDownloadButton", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DownloadProvider {
    void downloadTrack(Context context, LibraryAddableView view, String asin);

    boolean shouldShowDownloadButton(Context context, String asin);
}
