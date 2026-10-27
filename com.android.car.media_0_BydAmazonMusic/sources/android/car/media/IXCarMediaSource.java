package android.car.media;

import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarMediaSource extends IProvider {
    String getDisplayName();

    void reConnectMediaSource();
}
