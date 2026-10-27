package com.amazon.mp3.library.refinement.datasource;

import com.amazon.mp3.library.refinement.Refinable;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinableDataSource.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u00032\u00020\u0003J\b\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "", "Q", "", "count", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public interface RefinableDataSource<R extends Refinable, Q> {
    int count();
}
