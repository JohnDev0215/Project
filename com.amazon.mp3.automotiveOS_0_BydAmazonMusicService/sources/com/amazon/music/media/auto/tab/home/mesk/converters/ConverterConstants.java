package com.amazon.music.media.auto.tab.home.mesk.converters;

import com.amazon.music.media.auto.ui.PresentationViewType;
import com.amazon.music.media.auto.ui.WidgetsExpandType;
import kotlin.Metadata;

/* JADX INFO: compiled from: ConverterConstants.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/ConverterConstants;", "", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "SECONDARY_PRESENTATION_VIEW_TYPE", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "getSECONDARY_PRESENTATION_VIEW_TYPE", "()Lcom/amazon/music/media/auto/ui/PresentationViewType;", "Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "WIDGETS_EXPAND_TYPE", "Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "getWIDGETS_EXPAND_TYPE", "()Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class ConverterConstants {
    public static final ConverterConstants INSTANCE = new ConverterConstants();
    private static final PresentationViewType SECONDARY_PRESENTATION_VIEW_TYPE = PresentationViewType.LIST;
    private static final WidgetsExpandType WIDGETS_EXPAND_TYPE = WidgetsExpandType.SEE_MORE;

    private ConverterConstants() {
    }

    public final PresentationViewType getSECONDARY_PRESENTATION_VIEW_TYPE() {
        return SECONDARY_PRESENTATION_VIEW_TYPE;
    }

    public final WidgetsExpandType getWIDGETS_EXPAND_TYPE() {
        return WIDGETS_EXPAND_TYPE;
    }
}
