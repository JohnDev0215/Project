package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.DownloadStateModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: GroupDownloadStateModelConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/converters/GroupDownloadStateModelConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "data", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "convert", "(Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/DownloadStateModel;", "", "inInitialState", "Z", "<init>", "(Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class GroupDownloadStateModelConverter implements SingleBaseModelConverter<Integer> {
    private final boolean inInitialState;

    public GroupDownloadStateModelConverter(boolean z) {
        this.inInitialState = z;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public DownloadStateModel convert(Integer data) {
        DownloadStateModel.DownloadState downloadState;
        boolean z = true;
        if (!((data != null && data.intValue() == 4) || (data != null && data.intValue() == 1)) && (data == null || data.intValue() != 3)) {
            z = false;
        }
        if (z) {
            downloadState = DownloadStateModel.DownloadState.DOWNLOADING;
        } else if (data != null && data.intValue() == 0) {
            downloadState = DownloadStateModel.DownloadState.DOWNLOADED;
        } else if (data != null && data.intValue() == 5) {
            downloadState = DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD;
        } else if (data != null && data.intValue() == 2) {
            if (this.inInitialState) {
                downloadState = DownloadStateModel.DownloadState.ERROR_DOWNLOAD_RETURN;
            } else {
                downloadState = DownloadStateModel.DownloadState.ERROR_DOWNLOADING;
            }
        } else {
            downloadState = DownloadStateModel.DownloadState.NO_STATE;
        }
        return new DownloadStateModel(downloadState, 0);
    }
}
