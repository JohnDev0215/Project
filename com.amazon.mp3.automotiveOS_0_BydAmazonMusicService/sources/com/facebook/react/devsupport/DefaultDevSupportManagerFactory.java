package com.facebook.react.devsupport;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultDevSupportManagerFactory implements DevSupportManagerFactory {
    @Override // com.facebook.react.devsupport.DevSupportManagerFactory
    public DevSupportManager create(Context context, ReactInstanceDevHelper reactInstanceDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i, @Nullable Map<String, Object> map, @Nullable SurfaceDelegateFactory surfaceDelegateFactory) {
        if (!z) {
            return new DisabledDevSupportManager();
        }
        try {
            return (DevSupportManager) Class.forName("com.facebook.react.devsupport.BridgeDevSupportManager").getConstructor(Context.class, ReactInstanceDevHelper.class, String.class, Boolean.TYPE, RedBoxHandler.class, DevBundleDownloadListener.class, Integer.TYPE, Map.class, SurfaceDelegateFactory.class).newInstance(context, reactInstanceDevHelper, str, Boolean.TRUE, redBoxHandler, devBundleDownloadListener, Integer.valueOf(i), map, surfaceDelegateFactory);
        } catch (Exception e) {
            throw new RuntimeException("Requested enabled DevSupportManager, but BridgeDevSupportManager class was not found or could not be created", e);
        }
    }
}
