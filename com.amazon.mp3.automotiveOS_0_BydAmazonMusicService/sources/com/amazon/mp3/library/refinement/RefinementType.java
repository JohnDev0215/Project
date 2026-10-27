package com.amazon.mp3.library.refinement;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementType.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u0002H\u0001\u0012\u0002\b\u00030\u00042\u00020\u0005J\u0015\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0001H&¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0001H&¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/refinement/RefinementType;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "T", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "", "apply", "dataSource", "(Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;)Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "remove", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RefinementType<R extends Refinable, T extends RefinableDataSource<R, ?>> {
    T apply(T dataSource);

    T remove(T dataSource);
}
