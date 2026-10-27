package com.amazon.music.media.auto.tab.home.mesk.converters;

import CoreInterface.v1_0.Method;
import Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod;
import Touch.SyncInterface.v1_0.WidgetContext;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MESKHomePageConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u0002J\u0016\u0010\u0014\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/MESKHomePageConverter;", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "entityConverterFactory", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverterFactory;", "widgetConverterFactory", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverterFactory;", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverterFactory;Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverterFactory;)V", "convertResponse", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "meskResponse", "", "LCoreInterface/v1_0/Method;", "convertWidgetSyncElement", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "widgetSyncElement", "LTouch/SyncInterface/v1_0/WidgetSyncElement;", "convertWidgetSyncElements", "widgetSyncElements", "convertWidgetSyncElementsForNaviApps", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class MESKHomePageConverter {
    private static final String TAG = MESKHomePageConverter.class.getSimpleName();
    private final ContentPlaybackUtil contentPlaybackUtil;
    private final EntityConverterFactory entityConverterFactory;
    private final WidgetConverterFactory widgetConverterFactory;

    public MESKHomePageConverter(ContentPlaybackUtil contentPlaybackUtil, EntityConverterFactory entityConverterFactory, WidgetConverterFactory widgetConverterFactory) {
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        Intrinsics.checkNotNullParameter(entityConverterFactory, "entityConverterFactory");
        Intrinsics.checkNotNullParameter(widgetConverterFactory, "widgetConverterFactory");
        this.contentPlaybackUtil = contentPlaybackUtil;
        this.entityConverterFactory = entityConverterFactory;
        this.widgetConverterFactory = widgetConverterFactory;
    }

    public final AutoTabItem convertResponse(List<? extends Method> meskResponse) {
        Intrinsics.checkNotNullParameter(meskResponse, "meskResponse");
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "Converting MESK response for home tab");
        ArrayList arrayList = new ArrayList();
        for (Object obj : meskResponse) {
            if (obj instanceof SetWidgetSyncElementsMethod) {
                arrayList.add(obj);
            }
        }
        SetWidgetSyncElementsMethod setWidgetSyncElementsMethod = (SetWidgetSyncElementsMethod) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList);
        if (setWidgetSyncElementsMethod != null) {
            List<WidgetSyncElement> widgetSyncElements = setWidgetSyncElementsMethod.elements();
            if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
                Intrinsics.checkNotNullExpressionValue(widgetSyncElements, "widgetSyncElements");
                return convertWidgetSyncElements(widgetSyncElements);
            }
            Intrinsics.checkNotNullExpressionValue(widgetSyncElements, "widgetSyncElements");
            return convertWidgetSyncElementsForNaviApps(widgetSyncElements);
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.error(TAG3, "None of the methods in the input list are instance of SetWidgetSyncElementsMethod class");
        return null;
    }

    private final AutoWidgetItem convertWidgetSyncElement(WidgetSyncElement widgetSyncElement) {
        if (widgetSyncElement.items().isEmpty()) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, Intrinsics.stringPlus("This converter needs at least 1 entity to convert to a AutoWidgetItem", widgetSyncElement));
            return null;
        }
        return this.widgetConverterFactory.getWidgetConverter(widgetSyncElement).convertWidget(widgetSyncElement, this.contentPlaybackUtil, this.entityConverterFactory);
    }

    private final AutoTabItem convertWidgetSyncElementsForNaviApps(List<? extends WidgetSyncElement> widgetSyncElements) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : widgetSyncElements) {
            if (!(((WidgetSyncElement) obj).widgetContext() == WidgetContext.SHOVELER_RECENTLY_PLAYED)) {
                arrayList.add(obj);
            }
        }
        return convertWidgetSyncElements(CollectionsKt___CollectionsKt.take(arrayList, 5));
    }

    private final AutoTabItem convertWidgetSyncElements(List<? extends WidgetSyncElement> widgetSyncElements) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = widgetSyncElements.iterator();
        while (it.hasNext()) {
            AutoWidgetItem autoWidgetItemConvertWidgetSyncElement = convertWidgetSyncElement((WidgetSyncElement) it.next());
            if (autoWidgetItemConvertWidgetSyncElement != null) {
                arrayList.add(autoWidgetItemConvertWidgetSyncElement);
            }
        }
        return new AutoTabItem(ConverterConstants.INSTANCE.getWIDGETS_EXPAND_TYPE(), arrayList);
    }
}
