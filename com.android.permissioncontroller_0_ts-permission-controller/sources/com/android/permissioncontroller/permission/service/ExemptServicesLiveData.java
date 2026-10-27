package com.android.permissioncontroller.permission.service;

import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.BroadcastReceiverLiveData;
import com.android.permissioncontroller.permission.data.DataRepositoryForPackage;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.HasIntentAction;
import com.android.permissioncontroller.permission.data.ServiceLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0014R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/service/ExemptServicesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "", "user", "Landroid/os/UserHandle;", "(Landroid/os/UserHandle;)V", "serviceLiveDatas", "", "getUser", "()Landroid/os/UserHandle;", "onUpdate", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class ExemptServicesLiveData extends SmartUpdateMediatorLiveData<Map<String, ? extends List<? extends String>>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<SmartUpdateMediatorLiveData<Set<String>>> serviceLiveDatas;
    private final UserHandle user;

    public ExemptServicesLiveData(UserHandle user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.user = user;
        List<SmartUpdateMediatorLiveData<Set<String>>> listListOf = CollectionsKt.listOf((Object[]) new SmartUpdateMediatorLiveData[]{(SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.view.InputMethod", "android.permission.BIND_INPUT_METHOD", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.notification.NotificationListenerService", "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.accessibilityservice.AccessibilityService", "android.permission.BIND_ACCESSIBILITY_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.wallpaper.WallpaperService", "android.permission.BIND_WALLPAPER", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.voice.VoiceInteractionService", "android.permission.BIND_VOICE_INTERACTION", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.attention.AttentionService", "android.permission.BIND_ATTENTION_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.textclassifier.TextClassifierService", "android.permission.BIND_TEXTCLASSIFIER_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.printservice.PrintService", "android.permission.BIND_PRINT_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.dreams.DreamService", "android.permission.BIND_DREAM_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.net.action.RECOMMEND_NETWORKS", "android.permission.BIND_NETWORK_RECOMMENDATION_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.autofill.AutofillService", "android.permission.BIND_AUTOFILL_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.service.autofill.augmented.AugmentedAutofillService", "android.permission.BIND_AUGMENTED_AUTOFILL_SERVICE", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(ServiceLiveData.INSTANCE, "android.app.action.DEVICE_ADMIN_SERVICE", "android.permission.BIND_DEVICE_ADMIN", this.user), (SmartUpdateMediatorLiveData) DataRepositoryKt.get(BroadcastReceiverLiveData.INSTANCE, "android.app.action.DEVICE_ADMIN_ENABLED", "android.permission.BIND_DEVICE_ADMIN", this.user)});
        this.serviceLiveDatas = listListOf;
        List<SmartUpdateMediatorLiveData<Set<String>>> $this$forEach$iv = listListOf;
        for (Object element$iv : $this$forEach$iv) {
            SmartUpdateMediatorLiveData it = (SmartUpdateMediatorLiveData) element$iv;
            addSource(it, new Observer<Set<? extends String>>() { // from class: com.android.permissioncontroller.permission.service.ExemptServicesLiveData$$special$$inlined$forEach$lambda$1
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Set<? extends String> set) {
                    onChanged2((Set<String>) set);
                }

                /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                public final void onChanged2(Set<String> set) {
                    this.this$0.updateIfActive();
                }
            });
        }
    }

    public final UserHandle getUser() {
        return this.user;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Iterable $this$forEach$iv;
        String intentAction;
        Iterable $this$all$iv = this.serviceLiveDatas;
        boolean z = true;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv = it.next();
                SmartUpdateMediatorLiveData it2 = (SmartUpdateMediatorLiveData) element$iv;
                if (!it2.getIsInitialized()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterable $this$forEach$iv2 = this.serviceLiveDatas;
            for (Object element$iv2 : $this$forEach$iv2) {
                SmartUpdateMediatorLiveData serviceLD = (SmartUpdateMediatorLiveData) element$iv2;
                T value = serviceLD.getValue();
                if (value == 0) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "serviceLD.value!!");
                for (Object element$iv3 : (Iterable) value) {
                    String packageName = (String) element$iv3;
                    Object value$iv = linkedHashMap.get(packageName);
                    if (value$iv == null) {
                        $this$forEach$iv = $this$forEach$iv2;
                        ArrayList arrayList = new ArrayList();
                        linkedHashMap.put(packageName, arrayList);
                        value$iv = arrayList;
                    } else {
                        $this$forEach$iv = $this$forEach$iv2;
                    }
                    List list = (List) value$iv;
                    HasIntentAction hasIntentAction = (HasIntentAction) (!(serviceLD instanceof HasIntentAction) ? null : serviceLD);
                    if (hasIntentAction == null || (intentAction = hasIntentAction.getIntentAction()) == null) {
                        intentAction = "???";
                    }
                    list.add(intentAction);
                    $this$forEach$iv2 = $this$forEach$iv;
                }
            }
            setValue(linkedHashMap);
        }
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/service/ExemptServicesLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/service/ExemptServicesLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<UserHandle, ExemptServicesLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public ExemptServicesLiveData newValue(UserHandle key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new ExemptServicesLiveData(key);
        }
    }
}
