package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/binders/DownloadIconOnClickListener;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "()V", "onClick", "", "context", "Landroid/content/Context;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "downloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class DownloadIconOnClickListener implements ActionIconOnClickListener {
    @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
    public void onClick(Context context, ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public abstract void onClick(Context context, ContentMetadata metadata, DownloadStateModel.DownloadState downloadState);
}
