package com.amazon.mp3.library.refinement.filter;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.RefinementType;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import kotlin.Metadata;

/* JADX INFO: compiled from: Filter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u0003*\u0012\b\u0002\u0010\u0006*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00052\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/library/refinement/filter/Filter;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "", "V", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "T", "Lcom/amazon/mp3/library/refinement/RefinementType;", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public interface Filter<R extends Refinable, V, T extends RefinableDataSource<R, ?>> extends RefinementType<R, T> {
}
