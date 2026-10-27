package com.amazon.avod.location;

import android.location.Location;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
interface LocationResolver {
    boolean isLocationValid(@Nullable Location location);
}
