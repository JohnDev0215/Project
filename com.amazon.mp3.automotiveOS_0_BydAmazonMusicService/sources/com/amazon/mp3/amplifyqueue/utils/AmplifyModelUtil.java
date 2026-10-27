package com.amazon.mp3.amplifyqueue.utils;

import com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings;
import com.amazon.mp3.amplifyqueue.model.DevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmplifyModelUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/AmplifyModelUtil;", "", "()V", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmplifyModelUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: AmplifyModelUtil.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/AmplifyModelUtil$Companion;", "", "()V", "overrideCustomerDeviceSettings", "Lcom/amazon/mp3/amplifyqueue/model/CustomerDeviceSettings;", "oldCustomerDeviceSettings", "newCustomerDeviceSettings", "overrideDevicePlaybackState", "Lcom/amazon/mp3/amplifyqueue/model/DevicePlaybackState;", "oldDevicePlaybackState", "newDevicePlaybackState", "overrideQueueSequenceSlice", "Lcom/amazon/mp3/amplifyqueue/model/QueueSequenceSlice;", "oldQueueSequenceSlice", "newSequenceSlice", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QueueSequenceSlice overrideQueueSequenceSlice(QueueSequenceSlice oldQueueSequenceSlice, QueueSequenceSlice newSequenceSlice) {
            Intrinsics.checkNotNullParameter(oldQueueSequenceSlice, "oldQueueSequenceSlice");
            Intrinsics.checkNotNullParameter(newSequenceSlice, "newSequenceSlice");
            QueueSequenceSlice queueSequenceSliceBuild = oldQueueSequenceSlice.copyOfBuilder().entityReferences(newSequenceSlice.getEntityReferences()).sequenceVersion(newSequenceSlice.getSequenceVersion()).sliceOrdinal(newSequenceSlice.getSliceOrdinal()).isLastSlice(newSequenceSlice.getIsLastSlice()).updatedAt(newSequenceSlice.getUpdatedAt()).build();
            Intrinsics.checkNotNullExpressionValue(queueSequenceSliceBuild, "oldQueueSequenceSlice.co…                 .build()");
            return queueSequenceSliceBuild;
        }

        public final DevicePlaybackState overrideDevicePlaybackState(DevicePlaybackState oldDevicePlaybackState, DevicePlaybackState newDevicePlaybackState) {
            Intrinsics.checkNotNullParameter(oldDevicePlaybackState, "oldDevicePlaybackState");
            Intrinsics.checkNotNullParameter(newDevicePlaybackState, "newDevicePlaybackState");
            DevicePlaybackState devicePlaybackStateBuild = oldDevicePlaybackState.copyOfBuilder().deviceCurrentPlaybackState(newDevicePlaybackState.getDeviceCurrentPlaybackState()).queueId(newDevicePlaybackState.getQueueId()).deviceType(newDevicePlaybackState.getDeviceType()).loopMode(newDevicePlaybackState.getLoopMode()).shuffleMode(newDevicePlaybackState.getShuffleMode()).sequenceName(newDevicePlaybackState.getSequenceName()).sequenceVersion(newDevicePlaybackState.getSequenceVersion()).updatedAt(newDevicePlaybackState.getUpdatedAt()).createdAt(newDevicePlaybackState.getCreatedAt()).playbackState(newDevicePlaybackState.getPlaybackState()).djMode(newDevicePlaybackState.getDjMode()).playbackContinuationMode(newDevicePlaybackState.getPlaybackContinuationMode()).build();
            Intrinsics.checkNotNullExpressionValue(devicePlaybackStateBuild, "oldDevicePlaybackState.c…                 .build()");
            return devicePlaybackStateBuild;
        }

        public final CustomerDeviceSettings overrideCustomerDeviceSettings(CustomerDeviceSettings oldCustomerDeviceSettings, CustomerDeviceSettings newCustomerDeviceSettings) {
            Intrinsics.checkNotNullParameter(oldCustomerDeviceSettings, "oldCustomerDeviceSettings");
            Intrinsics.checkNotNullParameter(newCustomerDeviceSettings, "newCustomerDeviceSettings");
            CustomerDeviceSettings customerDeviceSettingsBuild = oldCustomerDeviceSettings.copyOfBuilder().adContext(newCustomerDeviceSettings.getAdContext()).customerId(newCustomerDeviceSettings.getCustomerId()).deviceId(newCustomerDeviceSettings.getDeviceId()).deviceType(newCustomerDeviceSettings.getDeviceType()).languageOfPreference(newCustomerDeviceSettings.getLanguageOfPreference()).locale(newCustomerDeviceSettings.getLocale()).parentalControls(newCustomerDeviceSettings.getParentalControls()).sessionId(newCustomerDeviceSettings.getSessionId()).supportedFeatures(newCustomerDeviceSettings.getSupportedFeatures()).timezone(newCustomerDeviceSettings.getTimezone()).updatedAt(newCustomerDeviceSettings.getUpdatedAt()).build();
            Intrinsics.checkNotNullExpressionValue(customerDeviceSettingsBuild, "oldCustomerDeviceSetting…                 .build()");
            return customerDeviceSettingsBuild;
        }
    }
}
