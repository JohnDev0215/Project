package com.amazon.music.router;

import android.content.Context;
import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public interface AsyncDestinationHandler<T extends Destination> extends DestinationHandler<T> {
    void navigateTo(Context context, T t, Runnable runnable);
}
