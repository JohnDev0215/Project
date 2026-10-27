package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.find.model.search.SearchItem;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CatalogContentPlaybackHandler<T extends SearchItem & CatalogContent> implements ContentAccessUtil.CatalogActionListener<T> {
    protected Context mContext;
    private PrimeCollectionTask.Task mTask;

    public abstract void startPlayback(final T content, final PrimeCollectionTask.Task task);

    public CatalogContentPlaybackHandler(final Context context) {
        this.mContext = context;
    }

    public void checkCanStartPlayback(final T catalogContent, final PrimeCollectionTask.Task task) {
        this.mTask = task;
        ContentAccessUtil.checkCanContinueCatalogAction(this.mContext, catalogContent, ContentAccessUtil.ContentAccessOperation.STREAM, this);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final T item, final boolean canContinue) {
        if (canContinue) {
            startPlayback(item, this.mTask);
        }
    }

    public boolean isPlaybackTask(final PrimeCollectionTask.Task task) {
        return isFullscreenPlayback(task) || PrimeCollectionTask.Task.PLAYBACK.equals(task);
    }

    protected boolean isFullscreenPlayback(final PrimeCollectionTask.Task task) {
        return task != null && (task.equals(PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN) || task.equals(PrimeCollectionTask.Task.PLAYBACK_SHUFFLE));
    }
}
