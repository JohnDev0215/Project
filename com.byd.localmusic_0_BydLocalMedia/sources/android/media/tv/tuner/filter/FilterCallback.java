package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public interface FilterCallback {
    void onFilterEvent(Filter filter, FilterEvent[] filterEventArr);

    void onFilterStatusChanged(Filter filter, int i);
}
