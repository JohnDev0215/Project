package com.amazon.music.media.auto;

import Touch.SyncInterface.v1_0.WidgetContext;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: AutoBrowserConstants.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0017\u001a\n \u0016*\u0004\u0018\u00010\u00150\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/media/auto/AutoBrowserConstants;", "", "Landroid/content/Context;", "context", "", "resetWidgetAndEntitiesCount", "", "NUMBER_OF_ENTITIES_PER_WIDGET", "I", "getNUMBER_OF_ENTITIES_PER_WIDGET", "()I", "setNUMBER_OF_ENTITIES_PER_WIDGET", "(I)V", "", "LTouch/SyncInterface/v1_0/WidgetContext;", "SUPPORTED_WIDGET_TYPES_NAVIAPP", "Ljava/util/List;", "getSUPPORTED_WIDGET_TYPES_NAVIAPP", "()Ljava/util/List;", "SUPPORTED_WIDGET_TYPES", "getSUPPORTED_WIDGET_TYPES", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class AutoBrowserConstants {
    public static final AutoBrowserConstants INSTANCE = new AutoBrowserConstants();
    private static int NUMBER_OF_ENTITIES_PER_WIDGET = 6;
    private static final List<WidgetContext> SUPPORTED_WIDGET_TYPES;
    private static final List<WidgetContext> SUPPORTED_WIDGET_TYPES_NAVIAPP;
    private static final String TAG;

    /* JADX INFO: compiled from: AutoBrowserConstants.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubDeviceType.values().length];
            iArr[SubDeviceType.GOOGLE_ASSISTANT.ordinal()] = 1;
            iArr[SubDeviceType.WAZE_ANDROID.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private AutoBrowserConstants() {
    }

    static {
        WidgetContext widgetContext = WidgetContext.SHOVELER_RECENTLY_PLAYED;
        WidgetContext widgetContext2 = WidgetContext.SHOVELER_PLAYLISTS;
        WidgetContext widgetContext3 = WidgetContext.SHOVELER_MADE_FOR_YOU;
        SUPPORTED_WIDGET_TYPES_NAVIAPP = CollectionsKt__CollectionsKt.listOf((Object[]) new WidgetContext[]{widgetContext, widgetContext2, widgetContext3});
        SUPPORTED_WIDGET_TYPES = CollectionsKt__CollectionsKt.listOf((Object[]) new WidgetContext[]{widgetContext2, WidgetContext.SHOVELER_ARTISTS, WidgetContext.SHOVELER_LIBRARY_TRACKS, WidgetContext.SHOVELER_ALBUMS, WidgetContext.SHOVELER_MY_PRESETS, widgetContext3, widgetContext});
        TAG = AutoBrowserConstants.class.getSimpleName();
    }

    public final int getNUMBER_OF_ENTITIES_PER_WIDGET() {
        return NUMBER_OF_ENTITIES_PER_WIDGET;
    }

    public final void setNUMBER_OF_ENTITIES_PER_WIDGET(int i) {
        NUMBER_OF_ENTITIES_PER_WIDGET = i;
    }

    public final List<WidgetContext> getSUPPORTED_WIDGET_TYPES_NAVIAPP() {
        return SUPPORTED_WIDGET_TYPES_NAVIAPP;
    }

    public final List<WidgetContext> getSUPPORTED_WIDGET_TYPES() {
        return SUPPORTED_WIDGET_TYPES;
    }

    public final void resetWidgetAndEntitiesCount(Context context) {
        int iCoerceIn;
        Intrinsics.checkNotNullParameter(context, "context");
        SubDeviceType latestSubDeviceType = SubDeviceTypeProvider.INSTANCE.getLatestSubDeviceType();
        try {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            Resources resourcesForApplication = packageManager.getResourcesForApplication("com.android.car.media");
            Intrinsics.checkNotNullExpressionValue(resourcesForApplication, "packageManager.getResour…ion(NBC_RESOURCE_PACKAGE)");
            iCoerceIn = RangesKt___RangesKt.coerceIn(resourcesForApplication.getInteger(resourcesForApplication.getIdentifier("num_browse_columns", "integer", "com.android.car.media")), 2, 8) * 2;
        } catch (Exception unused) {
            iCoerceIn = 6;
        }
        int i = latestSubDeviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[latestSubDeviceType.ordinal()];
        if (i == 1) {
            iCoerceIn = 20;
        } else if (i == 2) {
            iCoerceIn = 4;
        }
        NUMBER_OF_ENTITIES_PER_WIDGET = iCoerceIn;
    }
}
