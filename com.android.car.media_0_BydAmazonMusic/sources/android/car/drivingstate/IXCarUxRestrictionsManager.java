package android.car.drivingstate;

import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarUxRestrictionsManager extends IProvider {
    XCarUxRestrictions getCurrentUxRestrictions(int i);

    String getRestrictionMode();

    void initGearBox();

    void onCarDisconnected();

    void onGearBoxChangedTest(boolean z);

    void registerUxRestrictionsChangeListener(IXCarUxRestrictionsChangeListener iXCarUxRestrictionsChangeListener, int i);

    boolean setRestrictionMode(String str);

    void unregisterUxRestrictionsChangeListener(IXCarUxRestrictionsChangeListener iXCarUxRestrictionsChangeListener);
}
