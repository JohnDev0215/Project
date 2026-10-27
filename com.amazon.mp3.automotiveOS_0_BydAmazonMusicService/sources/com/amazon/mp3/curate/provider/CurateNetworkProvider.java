package com.amazon.mp3.curate.provider;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.dialog.fragment.NetworkErrorDialogHandler;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.curate.provider.NetworkProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateNetworkProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateNetworkProvider;", "Lcom/amazon/music/curate/provider/NetworkProvider;", "()V", "ifConnected", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "callback", "Lkotlin/Function0;", "isConnected", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateNetworkProvider implements NetworkProvider {
    @Override // com.amazon.music.curate.provider.NetworkProvider
    public void ifConnected(Activity activity, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!ConnectivityUtil.hasAnyInternetConnection(activity)) {
            new NetworkErrorDialogHandler(activity).showNetworkErrorDialog();
        } else {
            callback.invoke();
        }
    }

    @Override // com.amazon.music.curate.provider.NetworkProvider
    public boolean isConnected() {
        return ConnectivityUtil.hasAnyInternetConnection();
    }
}
