package com.android.systemui.qs;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.text.TextUtils;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.base.BasePresenter;
import com.android.systemui.qs.QSPanelContract;
import com.android.systemui.qs.tileimpl.QSFactoryImpl;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class QSPanelPresenter extends BasePresenter<QSPanelContract.View> implements QSPanelContract.Presenter {
    private static final String TAG = "QSPanelPresenter";
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoEnergyDevice mBYDAutoEnergyDevice;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private List<String> mCarItems;
    private DeviceManager mDeviceManager;
    private QSFactoryImpl mFactory;
    private NetworkController mNetworkController;
    private List<String> mQsItems;
    private List<String> mSceneItems;
    private final Gson gson = new Gson();
    private final List<QSTile> mQsSelectedList = new ArrayList();
    private final List<QSTile> mQsUnselectedList = new ArrayList();
    private final List<QSTile> mCarSelectedList = new ArrayList();
    private final List<QSTile> mCarUnselectedList = new ArrayList();
    private final List<QSTile> mSceneList = new ArrayList();
    private final WifiSignalCallback mSignalCallback = new WifiSignalCallback();
    private final DeviceManager.DeviceManagerListener deviceManagerListener = new DeviceManager.DeviceManagerListener() { // from class: com.android.systemui.qs.QSPanelPresenter.6
        @Override // com.android.systemui.autodevices.DeviceManager.DeviceManagerListener
        public void onRefreshCarControlView() {
            BydLogHelper.logW(QSPanelPresenter.TAG, "car adapter is connect update car control : " + (QSPanelPresenter.this.mView != null));
            if (QSPanelPresenter.this.mView != null) {
                QSPanelPresenter.this.refreshCarList();
            }
        }

        @Override // com.android.systemui.autodevices.DeviceManager.DeviceManagerListener
        public void onRefreshSocView() {
            BydLogHelper.logW(QSPanelPresenter.TAG, "car adapter is connect update soc : " + (QSPanelPresenter.this.mView != null));
            if (QSPanelPresenter.this.mView != null) {
                ((QSPanelContract.View) QSPanelPresenter.this.mView).refreshSoc();
            }
        }

        @Override // com.android.systemui.autodevices.DeviceManager.DeviceManagerListener
        public void onRefreshVolumeAndBrightnessView() {
            BydLogHelper.logW(QSPanelPresenter.TAG, "car adapter is connect update volume and brightness : " + (QSPanelPresenter.this.mView != null));
            if (QSPanelPresenter.this.mView != null) {
                ((QSPanelContract.View) QSPanelPresenter.this.mView).refreshVolumeAndBrightness();
            }
        }
    };
    private final AbsBYDAutoEnergyListener energyDeviceCallback = new AbsBYDAutoEnergyListener() { // from class: com.android.systemui.qs.QSPanelPresenter.7
        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onMandatoryElectricStateChanged(int value) {
            BydLogHelper.logD("  value : " + value);
            ((QSPanelContract.View) QSPanelPresenter.this.mView).updateMandatoryElectricStateChanged(value);
        }
    };
    private final AbsBYDAutoSettingListener settingDeviceCallback = new AbsBYDAutoSettingListener() { // from class: com.android.systemui.qs.QSPanelPresenter.8
        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSOCTargetRangeChange(int value) {
            BydLogHelper.logD("onSOCTargetRangeChange value : " + value);
            ((QSPanelContract.View) QSPanelPresenter.this.mView).updateSocTargetChanged(value);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSOCTargetLowLimitChanged(int value) {
            BydLogHelper.logD("onSOCTargetLowLimitChanged value : " + value);
            ((QSPanelContract.View) QSPanelPresenter.this.mView).updateSOCTargetLowLimitChanged(value);
        }
    };

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void attachView(QSPanelContract.View view) {
        super.attachView(view);
        BydLogHelper.logW(TAG, "QSPanelPresenter attachView");
        EventBus.getDefault().register(this);
        this.mFactory = (QSFactoryImpl) Dependency.get(QSFactoryImpl.class);
        this.mDeviceManager = DeviceManager.getInstance();
        this.mBYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(view.getContext());
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(view.getContext());
        this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(view.getContext());
        this.mBYDAutoEnergyDevice.registerListener(this.energyDeviceCallback);
        this.mBYDAutoSettingDevice.registerListener(this.settingDeviceCallback);
        DeviceManager.getInstance().addCallback(this.deviceManagerListener);
        NetworkController networkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mNetworkController = networkController;
        networkController.addCallback((NetworkController.SignalCallback) this.mSignalCallback);
    }

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void detachView() {
        super.detachView();
        BydLogHelper.logD("QSPanelPresenter detachView");
        EventBus.getDefault().unregister(this);
        resetList(this.mQsSelectedList);
        resetList(this.mQsUnselectedList);
        resetList(this.mSceneList);
        resetList(this.mCarSelectedList);
        resetList(this.mCarUnselectedList);
        this.mBYDAutoEnergyDevice.unregisterListener(this.energyDeviceCallback);
        this.mBYDAutoSettingDevice.unregisterListener(this.settingDeviceCallback);
        this.mNetworkController.removeCallback((NetworkController.SignalCallback) this.mSignalCallback);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(QSEditEvent event) {
        String categoryLabel = event.getCategoryLabel();
        BydLogHelper.logW(TAG, "onEvent type : " + categoryLabel);
        if (TextUtils.isEmpty(categoryLabel)) {
            return;
        }
        if (categoryLabel.startsWith(((QSPanelContract.View) this.mView).getContext().getString(R.string.qs_link_name))) {
            setQsData(true);
        } else if (categoryLabel.startsWith(((QSPanelContract.View) this.mView).getContext().getString(R.string.car_control_name))) {
            setCarData(true);
        } else if (categoryLabel.startsWith(((QSPanelContract.View) this.mView).getContext().getString(R.string.scene_mode_name))) {
            setSceneData();
        }
    }

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void initData() {
        BydLogHelper.logW(TAG, "QSPanelPresenter initData");
        setSceneData();
        if (this.mView != 0) {
            BydLogHelper.logW(TAG, "QSPanelPresenter initData DeviceManager init");
            DeviceManager.getInstance().init(((QSPanelContract.View) this.mView).getContext());
        }
        DeviceManager.getInstance().saveCarControlList();
        DeviceManager.getInstance().refreshCarControlView();
        DeviceManager.getInstance().refreshSocView();
        DeviceManager.getInstance().refreshVolumeAndBrightnessView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQsData(boolean fromEdit) {
        setQsSelectList();
        setQsUnSelectList();
        ((QSPanelContract.View) this.mView).refreshQSList(this.mQsSelectedList, fromEdit);
    }

    private void setQsSelectList() {
        boolean z;
        QSTileImpl qSTileImpl;
        String string = SharedPreferencesUtil.getString(((QSPanelContract.View) this.mView).getContext(), ((QSPanelContract.View) this.mView).getCategoryLabelQs(), null);
        BydLogHelper.logD("setQsSelectList selectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            this.mQsItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSPanelPresenter.1
            }.getType());
            BydLogHelper.logD("setQsSelectList selFromSaved true");
            z = true;
        } else {
            this.mQsItems = Arrays.asList(((QSPanelContract.View) this.mView).getContext().getResources().getStringArray(R.array.link_qs_items));
            z = false;
            BydLogHelper.logD("setQsSelectList selFromSaved false");
        }
        resetList(this.mQsSelectedList);
        for (String str : this.mQsItems) {
            if (this.mQsSelectedList.size() < 6 && (qSTileImpl = (QSTileImpl) this.mFactory.createTile(str, z)) != null) {
                BydLogHelper.logD("setQsSelectList s : " + str);
                if (((QSPanelContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data).equals(qSTileImpl.getTileLabel())) {
                    boolean zIsShowTelephoneData = this.mNetworkController.isShowTelephoneData();
                    BydLogHelper.logD("setQsSelectList isShowTelephoneData : " + zIsShowTelephoneData);
                    if (zIsShowTelephoneData) {
                        this.mQsSelectedList.add(qSTileImpl);
                        qSTileImpl.setLongClickFlag(true);
                    }
                } else {
                    this.mQsSelectedList.add(qSTileImpl);
                    qSTileImpl.setLongClickFlag(true);
                }
            }
        }
    }

    private void setQsUnSelectList() {
        boolean z;
        boolean z2;
        String string = SharedPreferencesUtil.getString(((QSPanelContract.View) this.mView).getContext(), Constants.TileId.CATEGORY_ID_LINK_UNSELECT, null);
        BydLogHelper.logD("setQsUnSelectList unSelectedStr : " + string);
        boolean z3 = !TextUtils.isEmpty(string);
        resetList(this.mQsUnselectedList);
        String[] stringArray = ((QSPanelContract.View) this.mView).getContext().getResources().getStringArray(R.array.link_qs_items);
        List<String> arrayList = new ArrayList();
        BydLogHelper.logD("setQsUnSelectList", "unSelectedItem unSelFromSaved : " + z3);
        if (z3) {
            arrayList = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSPanelPresenter.2
            }.getType());
        }
        for (String str : arrayList) {
            BydLogHelper.logD("setQsUnSelectList", "unSelectedItem saveItem : " + str);
            QSTileImpl qSTileImpl = (QSTileImpl) this.mFactory.createTile(str, true);
            if (qSTileImpl != null) {
                BydLogHelper.logD("setQsUnSelectList", "unSelectedItem tile.getTileLabel() : " + qSTileImpl.getTileLabel());
                if (((QSPanelContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data).equals(qSTileImpl.getTileLabel())) {
                    boolean zIsShowTelephoneData = this.mNetworkController.isShowTelephoneData();
                    BydLogHelper.logD("setQsUnSelectList isShowTelephoneData : " + zIsShowTelephoneData);
                    if (zIsShowTelephoneData) {
                        this.mQsUnselectedList.add(qSTileImpl);
                        qSTileImpl.setLongClickFlag(true);
                    }
                } else {
                    this.mQsUnselectedList.add(qSTileImpl);
                    qSTileImpl.setLongClickFlag(true);
                }
            }
        }
        for (String str2 : stringArray) {
            BydLogHelper.logD("setQsUnSelectList", "unSelectedItem allItem : " + str2);
            Iterator<QSTile> it = this.mQsUnselectedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                QSTile next = it.next();
                BydLogHelper.logD("setQsUnSelectList", "unSelectedItem UnselectedList tileLabel : " + next.getTileLabel());
                if (str2.equals(next.getTileLabel())) {
                    z = true;
                    break;
                }
            }
            Iterator<QSTile> it2 = this.mQsSelectedList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                QSTile next2 = it2.next();
                BydLogHelper.logD("setQsUnSelectList", "unSelectedItem SelectedList tileLabel : " + next2.getTileLabel());
                if (str2.equals(next2.getTileLabel())) {
                    z2 = true;
                    break;
                }
            }
            BydLogHelper.logD("setQsUnSelectList", "isContainSelectedList : " + z2 + " , isContainUnselectedList : " + z);
            if (!z2 && !z) {
                BydLogHelper.logD("setQsUnSelectList", "add : " + str2);
                QSTileImpl qSTileImpl2 = (QSTileImpl) this.mFactory.createTile(str2, z3);
                if (qSTileImpl2 != null) {
                    if (str2.equals(((QSPanelContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data))) {
                        boolean zIsShowTelephoneData2 = this.mNetworkController.isShowTelephoneData();
                        BydLogHelper.logD("setQsUnSelectList", "isShowTelephoneData : " + zIsShowTelephoneData2);
                        if (zIsShowTelephoneData2) {
                            if (this.mQsSelectedList.size() < 6) {
                                this.mQsSelectedList.add(qSTileImpl2);
                                qSTileImpl2.setLongClickFlag(true);
                            } else {
                                this.mQsUnselectedList.add(qSTileImpl2);
                                qSTileImpl2.setLongClickFlag(true);
                            }
                        }
                    } else if (this.mQsSelectedList.size() < 6) {
                        this.mQsSelectedList.add(qSTileImpl2);
                        qSTileImpl2.setLongClickFlag(true);
                    } else {
                        this.mQsUnselectedList.add(qSTileImpl2);
                        qSTileImpl2.setLongClickFlag(true);
                    }
                }
            }
        }
    }

    private void setCarData(boolean fromEdit) {
        setCarSelectedList();
        setCarUnSelectedList();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mCarSelectedList.size(); i++) {
            arrayList.add(this.mCarSelectedList.get(i).getTileId());
        }
        SharedPreferencesUtil.putString(((QSPanelContract.View) this.mView).getContext(), Constants.TileId.CATEGORY_ID_CAR_SELECT, new Gson().toJson(arrayList));
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.mCarUnselectedList.size(); i2++) {
            arrayList2.add(this.mCarUnselectedList.get(i2).getTileId());
        }
        SharedPreferencesUtil.putString(((QSPanelContract.View) this.mView).getContext(), Constants.TileId.CATEGORY_ID_CAR_UNSELECT, new Gson().toJson(arrayList2));
        ((QSPanelContract.View) this.mView).refreshCarSelectList(this.mCarSelectedList, fromEdit);
        ((QSPanelContract.View) this.mView).refreshCarUnselectedList(this.mCarUnselectedList, fromEdit);
    }

    private void setCarSelectedList() {
        boolean z;
        String string = SharedPreferencesUtil.getString(((QSPanelContract.View) this.mView).getContext(), ((QSPanelContract.View) this.mView).getCategoryLabelCar(), null);
        BydLogHelper.logW(TAG, "setCarSelectedList : " + string);
        boolean zIsEmpty = TextUtils.isEmpty(string);
        BydLogHelper.logD("setCarSelectedList", "isEmpty : " + zIsEmpty);
        if (!zIsEmpty) {
            this.mCarItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSPanelPresenter.3
            }.getType());
            z = true;
        } else {
            this.mCarItems = Arrays.asList(((QSPanelContract.View) this.mView).getContext().getResources().getStringArray(R.array.car_control_items));
            z = false;
        }
        resetList(this.mCarSelectedList);
        Iterator<String> it = this.mCarItems.iterator();
        while (it.hasNext()) {
            String next = it.next();
            BydLogHelper.logD("setCarSelectedList", "mCarList.size() : " + this.mCarSelectedList.size());
            if (this.mCarSelectedList.size() >= 6) {
                return;
            }
            BydLogHelper.logD("setCarSelectedList", "SelectedItem : " + next);
            if (z) {
                if (QSIconConstants.tileName.containsKey(next)) {
                    next = QSIconConstants.tileName.get(next);
                }
                BydLogHelper.logD("setCarSelectedList", "SelectedItem : " + next);
            }
            if (this.mDeviceManager.getCarControlList().contains(next)) {
                QSTileImpl qSTileImpl = (QSTileImpl) this.mFactory.createCarTile(next, z);
                if (qSTileImpl != null) {
                    this.mCarSelectedList.add(qSTileImpl);
                }
                qSTileImpl.setLongClickFlag(true);
            } else {
                BydLogHelper.logD("setCarSelectedList", "SelectedList non support : " + next);
            }
        }
    }

    private void setCarUnSelectedList() {
        boolean z;
        boolean z2;
        boolean z3;
        QSTileImpl qSTileImpl;
        String string = SharedPreferencesUtil.getString(((QSPanelContract.View) this.mView).getContext(), Constants.TileId.CATEGORY_ID_CAR_UNSELECT, null);
        BydLogHelper.logW(TAG, "setCarUnSelectedList unSelectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            BydLogHelper.logD("setCarUnSelectedList unSelFromSaved true");
            z = true;
        } else {
            BydLogHelper.logD("setCarUnSelectedList unSelFromSaved false");
            z = false;
        }
        resetList(this.mCarUnselectedList);
        String[] stringArray = ((QSPanelContract.View) this.mView).getContext().getResources().getStringArray(R.array.car_control_items);
        List<String> arrayList = new ArrayList();
        BydLogHelper.logD("setCarUnSelectedList", "unSelFromSaved : " + z);
        if (z) {
            arrayList = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSPanelPresenter.4
            }.getType());
        }
        for (String str : arrayList) {
            if (QSIconConstants.tileName.containsKey(str)) {
                str = QSIconConstants.tileName.get(str);
            }
            BydLogHelper.logD("setCarUnSelectedList", "saveItem : " + str);
            if (this.mDeviceManager.getCarControlList().contains(str)) {
                QSTileImpl qSTileImpl2 = (QSTileImpl) this.mFactory.createCarTile(str, true);
                if (qSTileImpl2 != null) {
                    this.mCarUnselectedList.add(qSTileImpl2);
                }
                qSTileImpl2.setLongClickFlag(true);
            }
        }
        for (String str2 : stringArray) {
            BydLogHelper.logD("setCarUnSelectedList", "allItem : " + str2);
            Iterator<QSTile> it = this.mCarUnselectedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                QSTile next = it.next();
                BydLogHelper.logD("setCarUnSelectedList", "UnselectedList tileLabel : " + next.getTileLabel());
                if (next.getTileLabel().equals(str2)) {
                    z2 = true;
                    break;
                }
            }
            Iterator<QSTile> it2 = this.mCarSelectedList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z3 = false;
                    break;
                }
                QSTile next2 = it2.next();
                BydLogHelper.logD("setCarUnSelectedList", "SelectedList tileLabel : " + next2.getTileLabel());
                if (next2.getTileLabel().equals(str2)) {
                    z3 = true;
                    break;
                }
            }
            BydLogHelper.logD("setCarUnSelectedList", "isContainSelectedList : " + z3 + " , isContainUnselectedList : " + z2);
            if (!z3 && !z2) {
                BydLogHelper.logD("setCarUnSelectedList", "add : " + str2);
                if (this.mDeviceManager.getCarControlList().contains(str2) && (qSTileImpl = (QSTileImpl) this.mFactory.createCarTile(str2, false)) != null) {
                    if (str2.equals(((QSPanelContract.View) this.mView).getContext().getString(R.string.car_title_hud))) {
                        BydLogHelper.logD("setCarUnSelectedList", "mSelectedList.size() : " + this.mCarSelectedList.size());
                        if (this.mCarSelectedList.size() >= 6) {
                            this.mCarUnselectedList.add(0, this.mCarSelectedList.get(5));
                            List<QSTile> list = this.mCarSelectedList;
                            list.remove(list.get(5));
                        }
                        this.mCarSelectedList.add(0, qSTileImpl);
                    } else if (str2.equals(((QSPanelContract.View) this.mView).getContext().getString(R.string.transport_mode))) {
                        BydLogHelper.logD("setCarUnSelectedList", "mSelectedList.size() : " + this.mCarSelectedList.size());
                        if (this.mCarSelectedList.size() >= 6) {
                            this.mCarUnselectedList.add(0, this.mCarSelectedList.get(5));
                            List<QSTile> list2 = this.mCarSelectedList;
                            list2.remove(list2.get(5));
                        }
                        if (this.mCarSelectedList.size() > 0) {
                            this.mCarSelectedList.add(1, qSTileImpl);
                        } else {
                            this.mCarSelectedList.add(qSTileImpl);
                        }
                    } else {
                        BydLogHelper.logD("setCarUnSelectedList", "mCarUnselectedList add : " + qSTileImpl);
                        this.mCarUnselectedList.add(qSTileImpl);
                    }
                    qSTileImpl.setLongClickFlag(true);
                }
            }
        }
    }

    private void resetList(List<QSTile> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            QSTile qSTile = list.get(i);
            if (qSTile != null) {
                qSTile.release();
            }
        }
        list.clear();
    }

    public void refreshCarList() {
        setQsData(false);
        setCarData(false);
    }

    private void setSceneData() {
        boolean z;
        String string = SharedPreferencesUtil.getString(((QSPanelContract.View) this.mView).getContext(), ((QSPanelContract.View) this.mView).getCategoryLabelScene(), null);
        BydLogHelper.logD(string);
        if (!TextUtils.isEmpty(string)) {
            this.mSceneItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSPanelPresenter.5
            }.getType());
            z = true;
        } else {
            this.mSceneItems = Arrays.asList(((QSPanelContract.View) this.mView).getContext().getResources().getStringArray(R.array.scene_mode_all_items));
            z = false;
        }
        resetList(this.mSceneList);
        Iterator<String> it = this.mSceneItems.iterator();
        while (it.hasNext()) {
            QSTile qSTileCreateSceneTile = this.mFactory.createSceneTile(it.next(), z);
            if (qSTileCreateSceneTile != null) {
                this.mSceneList.add(qSTileCreateSceneTile);
                if (this.mSceneList.size() == 2) {
                    break;
                }
            }
        }
        ((QSPanelContract.View) this.mView).refreshSceneModeList(this.mSceneList);
    }

    private final class WifiSignalCallback implements NetworkController.SignalCallback {
        private WifiSignalCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void showTelephoneData(boolean show) {
            BydLogHelper.logD("WifiSignalCallback showTelephoneData show : " + show);
            QSPanelPresenter.this.setQsData(true);
        }
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public int getMandatoryElectricState() {
        int mandatoryElectricState = this.mBYDAutoEnergyDevice.getMandatoryElectricState();
        BydLogHelper.logD("getMandatoryElectricState value : " + mandatoryElectricState);
        return mandatoryElectricState;
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public void setMandatoryElectricState(int value) {
        BydLogHelper.logD("setMandatoryElectricState value : " + value);
        this.mBYDAutoEnergyDevice.setMandatoryElectricState(value);
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public int getSOCTarget() {
        int sOCTarget = this.mBYDAutoSettingDevice.getSOCTarget();
        BydLogHelper.logD("getSOCTarget value : " + sOCTarget);
        return sOCTarget;
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public void setSOCTarget(int value) {
        BydLogHelper.logD("setSOCTarget value : " + value);
        this.mBYDAutoSettingDevice.setSOCTarget(value);
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public int getSocTargetRange() {
        int socTargetRange = this.mBYDAutoSettingDevice.getSocTargetRange();
        BydLogHelper.logD("getSocTargetRange socTargetRange : " + socTargetRange);
        return socTargetRange;
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public int getSocTargetLowLimit() {
        int socTargetLowLimit = this.mBYDAutoSettingDevice.getSocTargetLowLimit();
        BydLogHelper.logD("getSocTargetLowLimit socTargetLowLimit : " + socTargetLowLimit);
        return socTargetLowLimit;
    }

    @Override // com.android.systemui.qs.QSPanelContract.Presenter
    public int hasMessage(int id) {
        int iHasMessage = this.mBYDAutoBodyworkDevice.hasMessage(id);
        BydLogHelper.logD("hasMessage onlineState id : " + id + " ---- hasMessage:" + iHasMessage);
        return iHasMessage;
    }
}
