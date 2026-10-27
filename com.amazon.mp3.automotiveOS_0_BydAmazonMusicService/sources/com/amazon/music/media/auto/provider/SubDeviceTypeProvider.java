package com.amazon.music.media.auto.provider;

import com.amazon.music.media.auto.util.AutoLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubDeviceTypeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b!\u0010\"J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0012\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u000b\u001a\u00020\nR(\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001f\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lcom/amazon/music/media/auto/provider/SubDeviceTypeProvider;", "", "Lcom/amazon/music/media/auto/provider/SubDeviceType;", "subDeviceType", "", "initializeSubDeviceType", "getLatestSubDeviceType", "isMultiLevelMediaBrowserHierarchy", "isSingleLevelMediaBrowserHierarchy", "isMediaTreeSupported", "", "clear", "Ljava/util/concurrent/CopyOnWriteArrayList;", "subDeviceTypes", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getSubDeviceTypes", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setSubDeviceTypes", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "", "unknownSubDeviceTypes", "[Lcom/amazon/music/media/auto/provider/SubDeviceType;", "getUnknownSubDeviceTypes", "()[Lcom/amazon/music/media/auto/provider/SubDeviceType;", "", "stopCastingSubDeviceList", "Ljava/util/List;", "getStopCastingSubDeviceList", "()Ljava/util/List;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class SubDeviceTypeProvider {
    public static final SubDeviceTypeProvider INSTANCE = new SubDeviceTypeProvider();
    private static CopyOnWriteArrayList<SubDeviceType> subDeviceTypes = new CopyOnWriteArrayList<>();
    private static final SubDeviceType[] unknownSubDeviceTypes = {SubDeviceType.UNKNOWN_CLIENT_SIGNATURE, SubDeviceType.UNKNOWN_INVALID_CLIENT_APP, SubDeviceType.UNKNOWN_SYSTEM_APP, SubDeviceType.UNKNOWN_PACKAGE_INFO, SubDeviceType.UNKNOWN_PACKAGE_MANAGER, SubDeviceType.UNKNOWN_SYSTEM_APP_CALLING_ID, SubDeviceType.UNKNOWN_VALID_PACKAGE, SubDeviceType.ALEXA_ANDROID, SubDeviceType.ALEXA_FIRE_OS};
    private static final List<SubDeviceType> stopCastingSubDeviceList = CollectionsKt__CollectionsKt.listOf((Object[]) new SubDeviceType[]{SubDeviceType.CAR_MODE, SubDeviceType.CAR_BLUETOOTH, SubDeviceType.VOLVO_OS, SubDeviceType.ANDROID_AUTOMOTIVE_OS, SubDeviceType.ANDROID_AUTO, SubDeviceType.WAZE_ANDROID});
    private static final String TAG = SubDeviceTypeProvider.class.getSimpleName();

    private SubDeviceTypeProvider() {
    }

    public final CopyOnWriteArrayList<SubDeviceType> getSubDeviceTypes() {
        return subDeviceTypes;
    }

    public final SubDeviceType[] getUnknownSubDeviceTypes() {
        return unknownSubDeviceTypes;
    }

    public final List<SubDeviceType> getStopCastingSubDeviceList() {
        return stopCastingSubDeviceList;
    }

    public final boolean initializeSubDeviceType(SubDeviceType subDeviceType) {
        boolean zAdd;
        Intrinsics.checkNotNullParameter(subDeviceType, "subDeviceType");
        synchronized (subDeviceTypes) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("Initialize subDeviceType: ", subDeviceType));
            zAdd = INSTANCE.getSubDeviceTypes().add(subDeviceType);
        }
        return zAdd;
    }

    public final SubDeviceType getLatestSubDeviceType() {
        SubDeviceType subDeviceType;
        try {
            synchronized (subDeviceTypes) {
                subDeviceType = (SubDeviceType) CollectionsKt___CollectionsKt.lastOrNull((List) INSTANCE.getSubDeviceTypes());
            }
            return subDeviceType;
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Unable to get the latest subDevice type", e);
            return null;
        }
    }

    public final boolean isMultiLevelMediaBrowserHierarchy() {
        return !isSingleLevelMediaBrowserHierarchy() || subDeviceTypes.contains(SubDeviceType.ANDROID_AUTO);
    }

    public final boolean isSingleLevelMediaBrowserHierarchy() {
        return subDeviceTypes.contains(SubDeviceType.WAZE_ANDROID) || subDeviceTypes.contains(SubDeviceType.GOOGLE_ASSISTANT);
    }

    public static /* synthetic */ boolean isMediaTreeSupported$default(SubDeviceTypeProvider subDeviceTypeProvider, SubDeviceType subDeviceType, int i, Object obj) {
        if ((i & 1) != 0) {
            subDeviceType = subDeviceTypeProvider.getLatestSubDeviceType();
        }
        return subDeviceTypeProvider.isMediaTreeSupported(subDeviceType);
    }

    public final boolean isMediaTreeSupported(SubDeviceType subDeviceType) {
        return !ArraysKt___ArraysKt.contains(unknownSubDeviceTypes, subDeviceType);
    }

    public final void clear() {
        synchronized (subDeviceTypes) {
            SubDeviceTypeProvider subDeviceTypeProvider = INSTANCE;
            if (!subDeviceTypeProvider.getSubDeviceTypes().isEmpty()) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "Clear subDeviceTypes");
                subDeviceTypeProvider.getSubDeviceTypes().clear();
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
