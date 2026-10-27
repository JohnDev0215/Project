package android.car.test;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface ITestViewControl extends IProvider {
    void hide();

    Boolean isGearBoxP();

    boolean isLogOpen();

    void onClicked(Context context);

    void onCreate(Application application);
}
