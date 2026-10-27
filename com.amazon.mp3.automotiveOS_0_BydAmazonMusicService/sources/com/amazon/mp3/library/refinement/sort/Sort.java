package com.amazon.mp3.library.refinement.sort;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.RefinementType;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import kotlin.Metadata;

/* JADX INFO: compiled from: Sort.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u0012\b\u0001\u0010\u0004*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/library/refinement/sort/Sort;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "T", "Lcom/amazon/mp3/library/refinement/RefinementType;", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public interface Sort<R extends Refinable, T extends RefinableDataSource<R, ?>> extends RefinementType<R, T> {
}
