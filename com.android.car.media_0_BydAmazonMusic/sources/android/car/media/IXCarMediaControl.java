package android.car.media;

import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarMediaControl extends IProvider {
    void changeShuffle();

    int getPlayState();

    void next();

    void pause();

    void play();

    void previous();

    void replay();

    void stop();
}
