package android.car.media;

import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarMediaStateListener extends IProvider {
    public static final String TAG = "XCarMediaManager";

    void onCarDisconnected();

    void setCarMediaService(IXCarMediaService iXCarMediaService);
}
