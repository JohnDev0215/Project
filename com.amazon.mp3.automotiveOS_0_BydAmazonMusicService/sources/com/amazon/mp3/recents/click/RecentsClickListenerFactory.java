package com.amazon.mp3.recents.click;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.recents.metrics.RecentsPlaylistsMetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsClickListenerFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/recents/click/RecentsClickListenerFactory;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "context", "Landroid/content/Context;", "navigationProvider", "Lcom/amazon/mp3/fragment/navigation/NavigationProvider;", "metricsLogger", "Lcom/amazon/mp3/recents/metrics/RecentsPlaylistsMetricsLogger;", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/navigation/NavigationProvider;Lcom/amazon/mp3/recents/metrics/RecentsPlaylistsMetricsLogger;Landroidx/fragment/app/Fragment;)V", "TAG", "", "create", "Landroid/view/View$OnClickListener;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataList", "", "position", "", "contentListTitle", "createClickListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsClickListenerFactory implements ClickListenerFactory {
    private final String TAG;
    private final Context context;
    private final Fragment fragment;
    private final RecentsPlaylistsMetricsLogger metricsLogger;
    private final NavigationProvider navigationProvider;

    public RecentsClickListenerFactory(Context context, NavigationProvider navigationProvider, RecentsPlaylistsMetricsLogger metricsLogger, Fragment fragment) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(metricsLogger, "metricsLogger");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.context = context;
        this.navigationProvider = navigationProvider;
        this.metricsLogger = metricsLogger;
        this.fragment = fragment;
        String simpleName = RecentsClickListenerFactory.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "RecentsClickListenerFactory::class.java.simpleName");
        this.TAG = simpleName;
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return createClickListener(contentMetadata);
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(List<? extends ContentMetadata> contentMetadataList, int position, String contentListTitle) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        return createClickListener(contentMetadataList.get(position));
    }

    private final View.OnClickListener createClickListener(ContentMetadata contentMetadata) {
        if (contentMetadata instanceof PlaylistMetadata) {
            return new PlaylistClickListener(this.context, this.navigationProvider, (PlaylistMetadata) contentMetadata, this.metricsLogger, this.fragment);
        }
        Log.error(this.TAG, "Unsupported content metadata: " + ((Object) contentMetadata.getClass().getName()) + " creating NoOpClickListener");
        return new NoOpClickListener();
    }
}
