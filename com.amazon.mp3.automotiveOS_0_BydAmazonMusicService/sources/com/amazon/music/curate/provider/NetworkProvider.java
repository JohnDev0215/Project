package com.amazon.music.curate.provider;

import android.app.Activity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: NetworkProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/music/curate/provider/NetworkProvider;", "", "ifConnected", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "callback", "Lkotlin/Function0;", "isConnected", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface NetworkProvider {
    void ifConnected(Activity activity, Function0<Unit> callback);

    boolean isConnected();
}
