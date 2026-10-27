package com.amazon.music.views.library.deeplinks;

import android.content.Context;
import android.view.View;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DeeplinkClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB?\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/deeplinks/DeeplinkClickListener;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "deeplinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataOnClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "(Landroid/content/Context;Lcom/amazon/music/deeplink/DeeplinksManager;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;)V", "contentMetadataList", "", "position", "", "title", "", "(Landroid/content/Context;Lcom/amazon/music/deeplink/DeeplinksManager;Ljava/util/List;ILcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;Ljava/lang/String;)V", "viewId", "onClick", "", "v", "Landroid/view/View;", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeeplinkClickListener implements View.OnClickListener {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) DeeplinkClickListener.class);
    private final List<ContentMetadata> contentMetadataList;
    private final ContentMetadataOnClickListener contentMetadataOnClickListener;
    private final Context context;
    private final DeeplinksManager deeplinksManager;
    private final int position;
    private final String title;
    private final String viewId;

    public DeeplinkClickListener(Context context, DeeplinksManager deeplinksManager, ContentMetadata contentMetadata, ContentMetadataOnClickListener contentMetadataOnClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deeplinksManager, "deeplinksManager");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(contentMetadataOnClickListener, "contentMetadataOnClickListener");
        this.context = context;
        this.deeplinksManager = deeplinksManager;
        this.viewId = contentMetadata.getViewId();
        this.contentMetadataList = CollectionsKt__CollectionsJVMKt.listOf(contentMetadata);
        this.position = 0;
        this.contentMetadataOnClickListener = contentMetadataOnClickListener;
        this.title = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeeplinkClickListener(Context context, DeeplinksManager deeplinksManager, List<? extends ContentMetadata> contentMetadataList, int i, ContentMetadataOnClickListener contentMetadataOnClickListener, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deeplinksManager, "deeplinksManager");
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(contentMetadataOnClickListener, "contentMetadataOnClickListener");
        this.context = context;
        this.deeplinksManager = deeplinksManager;
        this.viewId = ((ContentMetadata) contentMetadataList.get(i)).getViewId();
        this.contentMetadataList = contentMetadataList;
        this.position = i;
        this.contentMetadataOnClickListener = contentMetadataOnClickListener;
        this.title = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        try {
            ContentMetadata contentMetadata = this.contentMetadataList.get(this.position);
            if (this.contentMetadataList.size() > 1) {
                ContentMetadataOnClickListener contentMetadataOnClickListener = this.contentMetadataOnClickListener;
                if (contentMetadataOnClickListener != null) {
                    List<ContentMetadata> list = this.contentMetadataList;
                    contentMetadataOnClickListener.onContentListClicked(list, list.indexOf(contentMetadata), this.title);
                }
            } else {
                ContentMetadataOnClickListener contentMetadataOnClickListener2 = this.contentMetadataOnClickListener;
                if (contentMetadataOnClickListener2 != null) {
                    contentMetadataOnClickListener2.onContentClicked(contentMetadata);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), (Throwable) e);
            v.setEnabled(false);
            MetricsHolder.getManager().handleEvent(FlexEvent.builder("INVALID_BRUSH_TARGET").withFlexStr2(this.viewId).build());
        }
    }
}
