package com.amazon.mp3.service.metrics.mts;

import android.content.Context;
import com.amazon.mp3.service.metrics.mts.MusicUploadFactory;
import com.amazon.music.metrics.mts.MTSEventUploader;
import com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnonymousMusicUploadFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/service/metrics/mts/AnonymousMusicUploadFactory;", "Lcom/amazon/mp3/service/metrics/mts/MusicUploadFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createUploader", "Lcom/amazon/music/metrics/mts/MTSEventUploader;", "AnonymousMusicUploadConfig", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AnonymousMusicUploadFactory extends MusicUploadFactory {
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnonymousMusicUploadFactory(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.mp3.service.metrics.mts.MusicUploadFactory, com.amazon.music.metrics.mts.MTSEventUploaderFactory
    public MTSEventUploader createUploader() {
        return new MTSReportClientActionsV3Uploader(new AnonymousMusicUploadConfig(this.context));
    }

    /* JADX INFO: compiled from: AnonymousMusicUploadFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/service/metrics/mts/AnonymousMusicUploadFactory$AnonymousMusicUploadConfig;", "Lcom/amazon/mp3/service/metrics/mts/MusicUploadFactory$MusicUploadConfig;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "doesCustomerHaveCloudPlayerAccount", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousMusicUploadConfig extends MusicUploadFactory.MusicUploadConfig {
        private final Context context;

        @Override // com.amazon.mp3.service.metrics.mts.MusicUploadFactory.MusicUploadConfig, com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public boolean doesCustomerHaveCloudPlayerAccount() {
            return true;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousMusicUploadConfig(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }
    }
}
