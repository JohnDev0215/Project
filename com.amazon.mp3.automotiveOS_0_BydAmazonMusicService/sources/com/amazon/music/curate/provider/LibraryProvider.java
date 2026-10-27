package com.amazon.music.curate.provider;

import androidx.lifecycle.LiveData;
import com.amazon.music.curate.model.Track;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/curate/provider/LibraryProvider;", "", "addToLibrary", "", "track", "Lcom/amazon/music/curate/model/Track;", "getLibraryStatus", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/music/curate/provider/LibraryStatus;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LibraryProvider {
    void addToLibrary(Track track);

    LiveData<LibraryStatus> getLibraryStatus(Track track);
}
