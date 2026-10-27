package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.music.pager.Pager;
import java.io.Serializable;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public interface PagerProvider<T> extends Serializable {
    Pager<? extends Collection<? extends T>> createPager(Context context);

    PaginatedDataType getDataType();

    PaginatedRankType getRankType();
}
