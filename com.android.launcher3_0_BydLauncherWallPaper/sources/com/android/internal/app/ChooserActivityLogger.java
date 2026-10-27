package com.android.internal.app;

import com.android.internal.logging.InstanceId;
import com.android.internal.logging.UiEventLogger;

/* JADX INFO: loaded from: classes4.dex */
public interface ChooserActivityLogger {
    InstanceId getInstanceId();

    void log(UiEventLogger.UiEventEnum uiEventEnum, InstanceId instanceId);

    void logShareStarted(int i, String str, String str2, int i2, int i3, boolean z, int i4, String str3);

    void logShareTargetSelected(int i, String str, int i2);

    default int typeFromPreviewInt(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 2;
        }
        return 1;
    }

    default void logSharesheetTriggered() {
        log(SharesheetStandardEvent.SHARESHEET_TRIGGERED, getInstanceId());
    }

    default void logSharesheetAppLoadComplete() {
        log(SharesheetStandardEvent.SHARESHEET_APP_LOAD_COMPLETE, getInstanceId());
    }

    default void logSharesheetDirectLoadComplete() {
        log(SharesheetStandardEvent.SHARESHEET_DIRECT_LOAD_COMPLETE, getInstanceId());
    }

    default void logSharesheetDirectLoadTimeout() {
        log(SharesheetStandardEvent.SHARESHEET_DIRECT_LOAD_TIMEOUT, getInstanceId());
    }

    default void logShareheetProfileChanged() {
        log(SharesheetStandardEvent.SHARESHEET_PROFILE_CHANGED, getInstanceId());
    }

    default void logSharesheetExpansionChanged(boolean z) {
        SharesheetStandardEvent sharesheetStandardEvent;
        if (z) {
            sharesheetStandardEvent = SharesheetStandardEvent.SHARESHEET_COLLAPSED;
        } else {
            sharesheetStandardEvent = SharesheetStandardEvent.SHARESHEET_EXPANDED;
        }
        log(sharesheetStandardEvent, getInstanceId());
    }

    public enum SharesheetStartedEvent implements UiEventLogger.UiEventEnum {
        SHARE_STARTED(228);

        private final int mId;

        SharesheetStartedEvent(int i) {
            this.mId = i;
        }

        @Override // com.android.internal.logging.UiEventLogger.UiEventEnum
        public int getId() {
            return this.mId;
        }
    }

    public enum SharesheetTargetSelectedEvent implements UiEventLogger.UiEventEnum {
        INVALID(0),
        SHARESHEET_SERVICE_TARGET_SELECTED(232),
        SHARESHEET_APP_TARGET_SELECTED(233),
        SHARESHEET_STANDARD_TARGET_SELECTED(234),
        SHARESHEET_COPY_TARGET_SELECTED(235);

        private final int mId;

        SharesheetTargetSelectedEvent(int i) {
            this.mId = i;
        }

        @Override // com.android.internal.logging.UiEventLogger.UiEventEnum
        public int getId() {
            return this.mId;
        }

        public static SharesheetTargetSelectedEvent fromTargetType(int i) {
            if (i == 1) {
                return SHARESHEET_SERVICE_TARGET_SELECTED;
            }
            if (i == 2) {
                return SHARESHEET_APP_TARGET_SELECTED;
            }
            if (i == 3) {
                return SHARESHEET_STANDARD_TARGET_SELECTED;
            }
            if (i == 4) {
                return SHARESHEET_COPY_TARGET_SELECTED;
            }
            return INVALID;
        }
    }

    public enum SharesheetStandardEvent implements UiEventLogger.UiEventEnum {
        INVALID(0),
        SHARESHEET_TRIGGERED(227),
        SHARESHEET_PROFILE_CHANGED(229),
        SHARESHEET_EXPANDED(230),
        SHARESHEET_COLLAPSED(231),
        SHARESHEET_APP_LOAD_COMPLETE(322),
        SHARESHEET_DIRECT_LOAD_COMPLETE(323),
        SHARESHEET_DIRECT_LOAD_TIMEOUT(324);

        private final int mId;

        SharesheetStandardEvent(int i) {
            this.mId = i;
        }

        @Override // com.android.internal.logging.UiEventLogger.UiEventEnum
        public int getId() {
            return this.mId;
        }
    }

    default int typeFromIntentString(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        switch (str) {
        }
        return 0;
    }
}
