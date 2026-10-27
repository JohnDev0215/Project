package com.facebook.react.devsupport;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public interface DevSupportManagerFactory {
    DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i, @Nullable Map<String, Object> map, @Nullable SurfaceDelegateFactory surfaceDelegateFactory);
}
