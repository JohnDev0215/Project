package com.amazon.music.views.library.deeplinks;

import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentMetadataOnClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J*\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH&¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "", "onContentClicked", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onContentListClicked", "contentMetadataList", "", "positionToStart", "", "contentListTitle", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContentMetadataOnClickListener {
    void onContentClicked(ContentMetadata contentMetadata);

    void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle);

    /* JADX INFO: compiled from: ContentMetadataOnClickListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void onContentListClicked$default(ContentMetadataOnClickListener contentMetadataOnClickListener, List list, int i, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onContentListClicked");
            }
            if ((i2 & 4) != 0) {
                str = null;
            }
            contentMetadataOnClickListener.onContentListClicked(list, i, str);
        }
    }
}
