package com.amazon.music.views.library.metadata;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H&J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "update", "", "other", "", "areItemsTheSame", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface UpdatableContentMetadata extends ContentMetadata {
    boolean areItemsTheSame(Object other);

    void update(ContentMetadata metadata);

    /* JADX INFO: compiled from: ContentMetadata.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean areItemsTheSame(UpdatableContentMetadata updatableContentMetadata, Object obj) {
            Intrinsics.checkNotNullParameter(updatableContentMetadata, "this");
            if (!(obj instanceof ContentMetadata) || !Intrinsics.areEqual(updatableContentMetadata.getClass(), obj.getClass())) {
                return false;
            }
            ContentMetadata contentMetadata = (ContentMetadata) obj;
            return Intrinsics.areEqual(updatableContentMetadata.getUri(), contentMetadata.getUri()) && Intrinsics.areEqual(updatableContentMetadata.getViewId(), contentMetadata.getViewId());
        }

        public static boolean areEqual(UpdatableContentMetadata updatableContentMetadata, Object obj) {
            Intrinsics.checkNotNullParameter(updatableContentMetadata, "this");
            if (!updatableContentMetadata.areItemsTheSame(obj)) {
                return false;
            }
            List<String> tags = updatableContentMetadata.getTags();
            if (obj != null) {
                return Intrinsics.areEqual(tags, ((ContentMetadata) obj).getTags());
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ContentMetadata");
        }
    }
}
