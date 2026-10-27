package com.facebook.drawee.interfaces;

import android.net.Uri;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface SimpleDraweeControllerBuilder {
    DraweeController build();

    SimpleDraweeControllerBuilder setOldController(@Nullable DraweeController oldController);

    SimpleDraweeControllerBuilder setUri(Uri uri);
}
