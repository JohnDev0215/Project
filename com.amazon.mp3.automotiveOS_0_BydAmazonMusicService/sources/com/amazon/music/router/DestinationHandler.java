package com.amazon.music.router;

import android.content.Context;
import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public interface DestinationHandler<T extends Destination> {
    void navigateTo(Context context, T t);
}
