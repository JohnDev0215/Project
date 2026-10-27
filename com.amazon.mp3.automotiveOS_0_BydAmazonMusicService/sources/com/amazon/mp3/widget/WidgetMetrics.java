package com.amazon.mp3.widget;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.dialog.TryOurWidgetDialogManager;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetMetrics {
    private static final long WIDGET_METRIC_INTERVAL = TimeUnit.HOURS.toMillis(24);
    private Context mContext;

    public enum WidgetMetricsType {
        ENABLED,
        SUPPORTED
    }

    public WidgetMetrics(final Context context) {
        this.mContext = context;
    }

    public enum WidgetClickEvent {
        PLAYER("widgetPlayer"),
        NOW_PLAYING("widgetNowPlaying"),
        ALEXA("widgetAlexa");

        private String desc;

        WidgetClickEvent(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.widget.WidgetMetrics$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$widget$WidgetMetrics$WidgetMetricsType;

        static {
            int[] iArr = new int[WidgetMetricsType.values().length];
            $SwitchMap$com$amazon$mp3$widget$WidgetMetrics$WidgetMetricsType = iArr;
            try {
                iArr[WidgetMetricsType.SUPPORTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$widget$WidgetMetrics$WidgetMetricsType[WidgetMetricsType.ENABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void checkIfShouldSendFlexEvent(final WidgetMetricsType metricsType, final boolean value) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$widget$WidgetMetrics$WidgetMetricsType[metricsType.ordinal()];
        if (i == 1) {
            if (shouldSendWidgetMetrics("supported last sent time")) {
                sendWidgetFlexEvent("widgetIsSupported", String.valueOf(value));
                setWidgetMetricLastSentTime("supported last sent time");
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        if (shouldSendWidgetMetrics("enabled last sent time") || changedWidgetEnabled(value)) {
            sendWidgetFlexEvent("widgetIsEnabled", String.valueOf(value));
            setWidgetMetricLastSentTime("enabled last sent time");
            setWidgetEnabledLastSentValue(value);
        }
    }

    public void sendWidgetMetricEvent(final WidgetClickEvent widgetClickEvent) {
        if (widgetClickEvent != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.WIDGET_INTERACTION).withContentInfo(getWidgetContentInfo(widgetClickEvent)).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }

    private List<Map<String, String>> getWidgetContentInfo(final WidgetClickEvent widgetClickEvent) {
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", widgetClickEvent.getDesc());
        arrayList.add(map);
        return arrayList;
    }

    private void sendWidgetFlexEvent(final String flexTitle, final String value) {
        MetricsLogger.sendEvent(FlexEvent.builder(flexTitle).withFlexStr1(value).build());
    }

    private void setWidgetMetricLastSentTime(final String preferenceKey) {
        SharedPreferences.Editor editorEdit = getSharedPrefs().edit();
        editorEdit.putLong(preferenceKey, System.currentTimeMillis());
        editorEdit.apply();
    }

    private void setWidgetEnabledLastSentValue(final boolean value) {
        SharedPreferences.Editor editorEdit = getSharedPrefs().edit();
        editorEdit.putBoolean("enabled last sent value", value);
        editorEdit.apply();
    }

    private boolean changedWidgetEnabled(final boolean currentValue) {
        return getSharedPrefs().getBoolean("enabled last sent value", false) != currentValue;
    }

    private boolean shouldSendWidgetMetrics(final String preferenceKey) {
        return System.currentTimeMillis() - getSharedPrefs().getLong(preferenceKey, -1L) > WIDGET_METRIC_INTERVAL;
    }

    private SharedPreferences getSharedPrefs() {
        return this.mContext.getApplicationContext().getSharedPreferences(TryOurWidgetDialogManager.SHARED_PREF_NAME, 0);
    }
}
