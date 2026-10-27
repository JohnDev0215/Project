package com.amazon.music.explore.widgets.listeners;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.music.explore.widgets.metadata.ExploreMetadata;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreClickListenerFactory implements ClickListenerFactory {
    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(List<? extends ContentMetadata> list, int i, @Nullable String str) {
        return null;
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(ContentMetadata contentMetadata) {
        ExploreMetadata exploreMetadata = (ExploreMetadata) contentMetadata;
        return new ExploreClickListener(exploreMetadata.getOwnerId(), exploreMetadata.getMethodsDispatcher(), exploreMetadata.getOnItemSelected());
    }
}
