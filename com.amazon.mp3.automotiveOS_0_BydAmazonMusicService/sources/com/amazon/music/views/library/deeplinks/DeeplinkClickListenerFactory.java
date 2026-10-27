package com.amazon.music.views.library.deeplinks;

import android.content.Context;
import android.view.View;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeeplinkClickListenerFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J(\u0010\t\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/deeplinks/DeeplinkClickListenerFactory;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "context", "Landroid/content/Context;", "deeplinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "contentMetadataOnClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "(Landroid/content/Context;Lcom/amazon/music/deeplink/DeeplinksManager;Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;)V", "create", "Lcom/amazon/music/views/library/deeplinks/DeeplinkClickListener;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataList", "", "position", "", "title", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeeplinkClickListenerFactory implements ClickListenerFactory {
    private final ContentMetadataOnClickListener contentMetadataOnClickListener;
    private final Context context;
    private final DeeplinksManager deeplinksManager;

    public DeeplinkClickListenerFactory(Context context, DeeplinksManager deeplinksManager, ContentMetadataOnClickListener contentMetadataOnClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deeplinksManager, "deeplinksManager");
        Intrinsics.checkNotNullParameter(contentMetadataOnClickListener, "contentMetadataOnClickListener");
        this.context = context;
        this.deeplinksManager = deeplinksManager;
        this.contentMetadataOnClickListener = contentMetadataOnClickListener;
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public /* bridge */ /* synthetic */ View.OnClickListener create(List list, int i, String str) {
        return create((List<? extends ContentMetadata>) list, i, str);
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public DeeplinkClickListener create(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return new DeeplinkClickListener(this.context, this.deeplinksManager, contentMetadata, this.contentMetadataOnClickListener);
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public DeeplinkClickListener create(List<? extends ContentMetadata> contentMetadataList, int position, String title) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        return new DeeplinkClickListener(this.context, this.deeplinksManager, contentMetadataList, position, this.contentMetadataOnClickListener, title);
    }
}
