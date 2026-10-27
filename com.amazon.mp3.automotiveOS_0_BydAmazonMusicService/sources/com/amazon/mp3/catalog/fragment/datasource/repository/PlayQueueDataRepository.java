package com.amazon.mp3.catalog.fragment.datasource.repository;

import android.content.Context;
import com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO;
import com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao;
import com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlayQueueDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;", "context", "Landroid/content/Context;", "isSequencerCloudQueue", "", "isCarModePage", "(Landroid/content/Context;ZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueDataRepository extends EditablePageDataRepository {
    public /* synthetic */ PlayQueueDataRepository(Context context, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, z, (i & 4) != 0 ? false : z2);
    }

    public PlayQueueDataRepository(Context context, boolean z, boolean z2) {
        EditablePageViewModelDao nowPlayingModelDAO;
        Intrinsics.checkNotNullParameter(context, "context");
        if (z) {
            nowPlayingModelDAO = new AmplifyPageModelDAO(z2);
        } else {
            nowPlayingModelDAO = new NowPlayingModelDAO(context, z2);
        }
        super(nowPlayingModelDAO);
    }
}
