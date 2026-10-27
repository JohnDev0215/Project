package com.amazon.digitalmusicxp.callbacks;

import kotlin.Metadata;

/* JADX INFO: compiled from: ChangeStateResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/digitalmusicxp/callbacks/ChangeStateResult;", "T", "", "onChangeFailed", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onChangedSuccess", "newState", "(Ljava/lang/Object;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ChangeStateResult<T> {
    void onChangeFailed(Exception e);

    void onChangedSuccess(T newState);
}
