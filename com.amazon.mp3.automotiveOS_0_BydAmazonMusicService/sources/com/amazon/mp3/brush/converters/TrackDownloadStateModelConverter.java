package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.DownloadStateModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: TrackDownloadStateModelConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0005\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/brush/converters/TrackDownloadStateModelConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "data", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "convert", "(Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TrackDownloadStateModelConverter implements SingleBaseModelConverter<Integer> {

    /* JADX INFO: compiled from: TrackDownloadStateModelConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadStateModel.DownloadState.values().length];
            iArr[DownloadStateModel.DownloadState.DOWNLOADING.ordinal()] = 1;
            iArr[DownloadStateModel.DownloadState.DOWNLOADED.ordinal()] = 2;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOAD_RETURN.ordinal()] = 3;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOADING.ordinal()] = 4;
            iArr[DownloadStateModel.DownloadState.QUEUING.ordinal()] = 5;
            iArr[DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public DownloadStateModel convert(Integer data) {
        DownloadStateModel.DownloadState downloadState;
        boolean z = true;
        if (data != null && data.intValue() == 1) {
            downloadState = DownloadStateModel.DownloadState.QUEUING;
        } else {
            if ((data == null || data.intValue() != 4) && (data == null || data.intValue() != 3)) {
                z = false;
            }
            if (z) {
                downloadState = DownloadStateModel.DownloadState.DOWNLOADING;
            } else if (data != null && data.intValue() == 0) {
                downloadState = DownloadStateModel.DownloadState.DOWNLOADED;
            } else if (data != null && data.intValue() == 5) {
                downloadState = DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD;
            } else {
                downloadState = (data != null && data.intValue() == 2) ? DownloadStateModel.DownloadState.ERROR_DOWNLOADING : DownloadStateModel.DownloadState.NO_STATE;
            }
        }
        return new DownloadStateModel(downloadState, 0);
    }

    public final int convert(DownloadStateModel downloadStateModel) {
        DownloadStateModel.DownloadState downloadState = downloadStateModel == null ? null : downloadStateModel.getDownloadState();
        switch (downloadState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()]) {
            case 1:
                return 4;
            case 2:
                return 0;
            case 3:
            case 4:
                return 2;
            case 5:
                return 1;
            case 6:
                return 5;
            default:
                return -1;
        }
    }
}
