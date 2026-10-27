package android.car;

import android.app.Application;
import android.content.res.Configuration;
import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarApplicationStub extends IProvider {
    void onConfigurationChanged(Configuration configuration);

    void onCreate(Application application);
}
