package spi;

import android.car.XCarRoute;
import android.car.media.IXCarMediaSource;
import android.car.media.XCarMediaManager;
import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: compiled from: XCarMediaSourceImpl.java */
/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_MEDIA_SOURCE_SERVICE)
public final class bd implements IXCarMediaSource {
    public transient MediaSourceViewModel a;

    @Override // android.car.media.IXCarMediaSource
    public final String getDisplayName() {
        return this.a.c.getValue() == null ? "" : this.a.c.getValue().b.toString();
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
        this.a = MediaSourceViewModel.a(Utils.getApp(), 1);
    }

    @Override // android.car.media.IXCarMediaSource
    public final void reConnectMediaSource() {
        if (this.a == null) {
            this.a = MediaSourceViewModel.a(Utils.getApp(), 1);
        }
        MediaSourceViewModel mediaSourceViewModel = this.a;
        b7 value = mediaSourceViewModel.c.getValue();
        if (mediaSourceViewModel.g == 1 && value != null) {
            Log.d("MediaSourceViewModel", "reConnectMediaSource: 1");
            mediaSourceViewModel.i.b(value);
        }
        XCarMediaManager xCarMediaManager = mediaSourceViewModel.b;
        if (xCarMediaManager == null || value == null) {
            return;
        }
        xCarMediaManager.setMediaSource(value.a, 1);
    }
}
