package com.android.systemui.qs;

import android.os.Handler;
import android.text.TextUtils;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.base.BasePresenter;
import com.android.systemui.qs.QSEditContract;
import com.android.systemui.qs.tileimpl.QSFactoryImpl;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.qs.tiles.SceneCommonTile;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QSEditPresenter extends BasePresenter<QSEditContract.View> implements QSEditContract.Presenter {
    private static final String TAG = "QSEditPresenter";
    private int mCategoryLabelType;
    private String mCategory_id_selected;
    private String mCategory_id_unselected;
    private String mCategory_label_selected;
    private String mCategory_label_unselected;
    private DeviceManager mDeviceManager;
    private QSFactoryImpl mFactory;
    private NetworkController mNetworkController;
    private int mSelectedDef;
    private List<String> mSelectedItems;
    private String mTitleName;
    private int mUnselectedDef;
    private List<String> mUnselectedItems;
    private final Gson gson = new Gson();
    private final List<QSTile> mSelectedList = new ArrayList();
    private final List<QSTile> mUnselectedList = new ArrayList();
    private int mSpanCount = 3;
    private int mSpanCountUnselect = 6;
    private final WifiSignalCallback mSignalCallback = new WifiSignalCallback();
    private final DeviceManager.DeviceManagerListener deviceManagerListener = new DeviceManager.DeviceManagerListener() { // from class: com.android.systemui.qs.QSEditPresenter.8
        @Override // com.android.systemui.autodevices.DeviceManager.DeviceManagerListener
        public void onRefreshCarControlView() {
            BydLogHelper.logW(QSEditPresenter.TAG, "onRefreshCarControlView");
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QSEditPresenter.8.1
                @Override // java.lang.Runnable
                public void run() {
                    QSEditPresenter.this.refreshCarList();
                }
            });
        }
    };

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void attachView(QSEditContract.View view) {
        super.attachView(view);
        BydLogHelper.logW(TAG, "QSEditPresenter attachView");
        this.mDeviceManager = DeviceManager.getInstance();
        this.mFactory = (QSFactoryImpl) Dependency.get(QSFactoryImpl.class);
        initParameter();
        this.mDeviceManager.addCallback(this.deviceManagerListener);
        NetworkController networkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mNetworkController = networkController;
        networkController.addCallback((NetworkController.SignalCallback) this.mSignalCallback);
    }

    public void initParameter() {
        int categoryLabelType = ((QSEditContract.View) this.mView).getCategoryLabelType();
        this.mCategoryLabelType = categoryLabelType;
        if (categoryLabelType == 0) {
            this.mTitleName = ((QSEditContract.View) this.mView).getContext().getString(R.string.qs_link_name);
            this.mSelectedDef = R.array.link_qs_items;
            this.mUnselectedDef = R.array.link_qs_items;
            this.mCategory_id_selected = Constants.TileId.CATEGORY_ID_LINK_SELECT;
            this.mCategory_id_unselected = Constants.TileId.CATEGORY_ID_LINK_UNSELECT;
            this.mSpanCount = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_qs);
            this.mSpanCountUnselect = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_qs_unselect);
        } else if (categoryLabelType == 1) {
            this.mTitleName = ((QSEditContract.View) this.mView).getContext().getString(R.string.car_control_name);
            this.mSelectedDef = R.array.car_control_items;
            this.mUnselectedDef = R.array.car_control_items;
            this.mCategory_id_selected = Constants.TileId.CATEGORY_ID_CAR_SELECT;
            this.mCategory_id_unselected = Constants.TileId.CATEGORY_ID_CAR_UNSELECT;
            this.mSpanCount = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_qs);
            this.mSpanCountUnselect = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_qs_unselect);
        } else {
            this.mTitleName = ((QSEditContract.View) this.mView).getContext().getString(R.string.scene_mode_name);
            this.mSelectedDef = R.array.scene_mode_all_items;
            this.mUnselectedDef = R.array.scene_mode_all_items;
            this.mSpanCount = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_scene);
            this.mSpanCountUnselect = ((QSEditContract.View) this.mView).getContext().getResources().getInteger(R.integer.panel_count_scene_unselect);
            this.mCategory_id_selected = Constants.TileId.CATEGORY_ID_SCENE_SELECT;
            this.mCategory_id_unselected = Constants.TileId.CATEGORY_ID_SCENE_UNSELECT;
        }
        this.mCategory_label_selected = this.mTitleName + ((QSEditContract.View) this.mView).getContext().getString(R.string.SPLIT) + ((QSEditContract.View) this.mView).getContext().getString(R.string.label_selected);
        this.mCategory_label_unselected = this.mTitleName + ((QSEditContract.View) this.mView).getContext().getString(R.string.SPLIT) + ((QSEditContract.View) this.mView).getContext().getString(R.string.label_unselected);
    }

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void initData() {
        BydLogHelper.logW(TAG, "initData mCategoryLabelType:" + this.mCategoryLabelType);
        int i = this.mCategoryLabelType;
        if (i == 1) {
            updateCarSelectedList();
            updateCarUnSelectedList();
        } else if (i == 2) {
            updateSceneList();
        } else {
            updateQsSelectList();
            updateQsUnSelectList();
        }
        ((QSEditContract.View) this.mView).refreshList(this.mSelectedList, this.mUnselectedList);
        ((QSEditContract.View) this.mView).setTitle(this.mTitleName);
    }

    private void updateCarSelectedList() {
        boolean z;
        String string = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_selected, null);
        BydLogHelper.logW(TAG, "updateCarSelectedList selectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            this.mSelectedItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.1
            }.getType());
            z = true;
            BydLogHelper.logD("updateCarSelectedList selFromSaved true");
        } else {
            this.mSelectedItems = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(this.mSelectedDef));
            z = false;
            BydLogHelper.logD("updateCarSelectedList selFromSaved false");
        }
        resetList(this.mSelectedList);
        Iterator<String> it = this.mSelectedItems.iterator();
        while (it.hasNext()) {
            String next = it.next();
            BydLogHelper.logD("updateCarSelectedList", "category_label_type_car SelectedItem : " + next);
            if (z) {
                if (QSIconConstants.tileName.containsKey(next)) {
                    next = QSIconConstants.tileName.get(next);
                }
                BydLogHelper.logD("updateCarSelectedList", "category_label_type_car SelectedItem : " + next);
            }
            if (this.mSelectedList.size() < 6) {
                if (this.mDeviceManager.getCarControlList().contains(next)) {
                    QSTile qSTileCreateCarTile = this.mFactory.createCarTile(next, z);
                    if (qSTileCreateCarTile != null) {
                        this.mSelectedList.add(qSTileCreateCarTile);
                    }
                } else {
                    BydLogHelper.logD("updateCarSelectedList", "category_label_type_car SelectedList non support : " + next);
                }
            }
        }
    }

    private void updateCarUnSelectedList() {
        boolean z;
        boolean z2;
        boolean z3;
        QSTile tile;
        QSTile tile2;
        String string = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_unselected, null);
        BydLogHelper.logW(TAG, "updateCarUnSelectedList unSelectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            this.mUnselectedItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.2
            }.getType());
            BydLogHelper.logD("updateCarUnSelectedList unSelFromSaved true");
            z = true;
        } else {
            this.mUnselectedItems = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(this.mUnselectedDef));
            BydLogHelper.logD("updateCarUnSelectedList unSelFromSaved false");
            z = false;
        }
        resetList(this.mUnselectedList);
        List<String> listAsList = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(R.array.car_control_items));
        List<String> arrayList = new ArrayList();
        BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem unSelFromSaved : " + z);
        if (z) {
            arrayList = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.3
            }.getType());
        }
        for (String str : arrayList) {
            BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem saveItem : " + str);
            if (QSIconConstants.tileName.containsKey(str)) {
                str = QSIconConstants.tileName.get(str);
            }
            BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem saveItem : " + str);
            if (this.mDeviceManager.getCarControlList().contains(str) && (tile2 = getTile(str, true)) != null) {
                this.mUnselectedList.add(tile2);
            }
        }
        for (String str2 : listAsList) {
            BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem allItem : " + str2);
            Iterator<QSTile> it = this.mUnselectedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                QSTile next = it.next();
                BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem UnselectedList tileLabel : " + next.getTileLabel());
                if (next.getTileLabel().equals(str2)) {
                    z2 = true;
                    break;
                }
            }
            Iterator<QSTile> it2 = this.mSelectedList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z3 = false;
                    break;
                }
                QSTile next2 = it2.next();
                BydLogHelper.logD("updateCarUnSelectedList", "category_label_type_car unSelectedItem SelectedList tileLabel : " + next2.getTileLabel());
                if (next2.getTileLabel().equals(str2)) {
                    z3 = true;
                    break;
                }
            }
            BydLogHelper.logD("updateCarUnSelectedList", "isContainSelectedList : " + z3 + " , isContainUnselectedList : " + z2);
            if (!z3 && !z2) {
                BydLogHelper.logD("updateCarUnSelectedList", "add : " + str2);
                if (this.mDeviceManager.getCarControlList().contains(str2) && (tile = getTile(str2, false)) != null) {
                    if (str2.equals(((QSEditContract.View) this.mView).getContext().getString(R.string.car_title_hud))) {
                        BydLogHelper.logD("setCarUnSelectedList", "mSelectedList.size() : " + this.mSelectedList.size());
                        if (this.mSelectedList.size() >= 6) {
                            this.mUnselectedList.add(0, this.mSelectedList.get(5));
                            List<QSTile> list = this.mSelectedList;
                            list.remove(list.get(5));
                        }
                        this.mSelectedList.add(0, tile);
                    } else if (str2.equals(((QSEditContract.View) this.mView).getContext().getString(R.string.transport_mode))) {
                        BydLogHelper.logD("setCarUnSelectedList", "mSelectedList.size() : " + this.mSelectedList.size());
                        if (this.mSelectedList.size() >= 6) {
                            this.mUnselectedList.add(0, this.mSelectedList.get(5));
                            List<QSTile> list2 = this.mSelectedList;
                            list2.remove(list2.get(5));
                        }
                        if (this.mSelectedList.size() > 0) {
                            this.mSelectedList.add(1, tile);
                        } else {
                            this.mSelectedList.add(tile);
                        }
                    } else {
                        BydLogHelper.logD("setCarUnSelectedList", "mUnselectedList add : " + tile);
                        if (this.mSelectedList.size() < 6) {
                            this.mSelectedList.add(tile);
                        } else {
                            this.mUnselectedList.add(tile);
                        }
                    }
                }
            }
        }
    }

    private void updateSceneList() {
        BydLogHelper.logD("updateSceneList");
        Type type = new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.4
        }.getType();
        List<String> listAsList = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(R.array.scene_mode_all_items));
        String string = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_selected, null);
        String string2 = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_unselected, null);
        BydLogHelper.logD("selectedStr : " + string);
        BydLogHelper.logD("unSelectedStr : " + string2);
        if (string != null) {
            this.mSelectedItems = (List) this.gson.fromJson(string, type);
        } else {
            this.mSelectedItems = new ArrayList();
        }
        if (string2 != null) {
            this.mUnselectedItems = (List) this.gson.fromJson(string2, type);
        } else {
            this.mUnselectedItems = new ArrayList();
        }
        int size = listAsList.size();
        BydLogHelper.logD("total " + size + " ,selected " + this.mSelectedItems.size() + " ,unselect " + this.mUnselectedItems.size());
        boolean zIsEmpty = this.mSelectedItems.isEmpty();
        boolean zIsEmpty2 = this.mUnselectedItems.isEmpty();
        if (!zIsEmpty) {
            Iterator<String> it = this.mSelectedItems.iterator();
            while (it.hasNext()) {
                QSTile tile = getTile(it.next(), true);
                if (tile != null) {
                    this.mSelectedList.add(tile);
                }
            }
        }
        if (!zIsEmpty2) {
            Iterator<String> it2 = this.mUnselectedItems.iterator();
            while (it2.hasNext()) {
                QSTile tile2 = getTile(it2.next(), true);
                if (tile2 != null) {
                    this.mUnselectedList.add(tile2);
                }
            }
        }
        if (size != this.mSelectedItems.size() + this.mUnselectedItems.size()) {
            for (String str : listAsList) {
                String str2 = QSIconConstants.tileId.get(str);
                BydLogHelper.logD("item = " + str + " ,id = " + str2);
                QSTile tile3 = getTile(str, true);
                if (tile3 != null) {
                    if (zIsEmpty && zIsEmpty2) {
                        if (this.mSelectedList.size() < 2) {
                            this.mSelectedList.add(tile3);
                        } else {
                            this.mUnselectedList.add(tile3);
                        }
                    } else if (!this.mSelectedItems.contains(str2) && !this.mUnselectedItems.contains(str2)) {
                        this.mUnselectedList.add(tile3);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshQsList() {
        BydLogHelper.logD("refreshQsList = " + this.mCategoryLabelType);
        if (this.mCategoryLabelType == 0) {
            updateQsSelectList();
            updateQsUnSelectList();
            ((QSEditContract.View) this.mView).refreshList(this.mSelectedList, this.mUnselectedList);
            ((QSEditContract.View) this.mView).setTitle(this.mTitleName);
        }
    }

    private void updateQsSelectList() {
        boolean z;
        QSTile tile;
        String string = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_selected, null);
        BydLogHelper.logD("updateQsSelectList selectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            this.mSelectedItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.5
            }.getType());
            z = true;
            BydLogHelper.logD("updateQsSelectList selFromSaved true");
        } else {
            this.mSelectedItems = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(this.mSelectedDef));
            z = false;
            BydLogHelper.logD("updateQsSelectList selFromSaved false");
        }
        resetList(this.mSelectedList);
        for (String str : this.mSelectedItems) {
            if (this.mSelectedList.size() < 6 && (tile = getTile(str, z)) != null) {
                BydLogHelper.logD("updateQsSelectList s : " + str);
                if (((QSEditContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data).equals(tile.getTileLabel())) {
                    boolean zIsShowTelephoneData = this.mNetworkController.isShowTelephoneData();
                    BydLogHelper.logD("updateQsSelectList isShowTelephoneData : " + zIsShowTelephoneData);
                    if (zIsShowTelephoneData) {
                        this.mSelectedList.add(tile);
                    }
                } else {
                    this.mSelectedList.add(tile);
                }
            }
        }
    }

    private void updateQsUnSelectList() {
        boolean z;
        boolean z2;
        boolean z3;
        String string = SharedPreferencesUtil.getString(((QSEditContract.View) this.mView).getContext(), this.mCategory_id_unselected, null);
        BydLogHelper.logD("updateQsUnSelectList unSelectedStr : " + string);
        if (!TextUtils.isEmpty(string)) {
            this.mUnselectedItems = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.6
            }.getType());
            z = true;
        } else {
            this.mUnselectedItems = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(this.mUnselectedDef));
            z = false;
        }
        resetList(this.mUnselectedList);
        List<String> listAsList = Arrays.asList(((QSEditContract.View) this.mView).getContext().getResources().getStringArray(R.array.link_qs_items));
        List<String> arrayList = new ArrayList();
        BydLogHelper.logD("updateQsUnSelectList", "unSelectedItem unSelFromSaved : " + z);
        if (z) {
            arrayList = (List) this.gson.fromJson(string, new TypeToken<List<String>>() { // from class: com.android.systemui.qs.QSEditPresenter.7
            }.getType());
        }
        for (String str : arrayList) {
            BydLogHelper.logD("updateQsUnSelectList", "unSelectedItem saveItem : " + str);
            QSTile tile = getTile(str, true);
            if (tile != null) {
                if (((QSEditContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data).equals(tile.getTileLabel())) {
                    boolean zIsShowTelephoneData = this.mNetworkController.isShowTelephoneData();
                    BydLogHelper.logD("updateQsUnSelectList isShowTelephoneData : " + zIsShowTelephoneData);
                    if (zIsShowTelephoneData) {
                        this.mUnselectedList.add(tile);
                    }
                } else {
                    this.mUnselectedList.add(tile);
                }
            }
        }
        for (String str2 : listAsList) {
            BydLogHelper.logD("updateQsUnSelectList", "unSelectedItem allItem : " + str2);
            Iterator<QSTile> it = this.mUnselectedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                QSTile next = it.next();
                BydLogHelper.logD("updateQsUnSelectList", "unSelectedItem UnselectedList tileLabel : " + next.getTileLabel());
                if (str2.equals(next.getTileLabel())) {
                    z2 = true;
                    break;
                }
            }
            Iterator<QSTile> it2 = this.mSelectedList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z3 = false;
                    break;
                }
                QSTile next2 = it2.next();
                BydLogHelper.logD("updateQsUnSelectList", "unSelectedItem SelectedList tileLabel : " + next2.getTileLabel());
                if (str2.equals(next2.getTileLabel())) {
                    z3 = true;
                    break;
                }
            }
            BydLogHelper.logD("updateQsUnSelectList", "isContainSelectedList : " + z3 + " , isContainUnselectedList : " + z2);
            if (!z3 && !z2) {
                BydLogHelper.logD("updateQsUnSelectList", "add : " + str2);
                QSTile tile2 = getTile(str2, false);
                if (tile2 != null) {
                    if (str2.equals(((QSEditContract.View) this.mView).getContext().getString(R.string.qs_title_telephone_data))) {
                        boolean zIsShowTelephoneData2 = this.mNetworkController.isShowTelephoneData();
                        BydLogHelper.logD("updateQsUnSelectList", "isShowTelephoneData : " + zIsShowTelephoneData2);
                        if (zIsShowTelephoneData2) {
                            if (this.mSelectedList.size() < 6) {
                                this.mSelectedList.add(tile2);
                            } else {
                                this.mUnselectedList.add(tile2);
                            }
                        }
                    } else if (this.mSelectedList.size() < 6) {
                        this.mSelectedList.add(tile2);
                    } else {
                        this.mUnselectedList.add(tile2);
                    }
                }
            }
        }
    }

    private QSTile getTile(String s, boolean fromSaved) {
        int i = this.mCategoryLabelType;
        if (i == 0) {
            return this.mFactory.createTile(s, fromSaved);
        }
        if (i == 1) {
            return this.mFactory.createCarTile(s, fromSaved);
        }
        SceneCommonTile sceneCommonTile = (SceneCommonTile) this.mFactory.createSceneTile(s, fromSaved);
        if (sceneCommonTile != null) {
            sceneCommonTile.setShowCustomImg(true);
        }
        return sceneCommonTile;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public String getSelectedName() {
        return this.mCategory_label_selected;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public String getUnselectedName() {
        return this.mCategory_label_unselected;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public String getSelectedId() {
        return this.mCategory_id_selected;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public String getUnselectedId() {
        return this.mCategory_id_unselected;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public int getSpanCount() {
        return this.mSpanCount;
    }

    @Override // com.android.systemui.qs.QSEditContract.Presenter
    public int getSpanCountUnselect() {
        return this.mSpanCountUnselect;
    }

    public void refreshCarList() {
        BydLogHelper.logW(TAG, "refreshCarList mCategoryLabelType:" + this.mCategoryLabelType);
        if (this.mCategoryLabelType == 1) {
            updateCarSelectedList();
            updateCarUnSelectedList();
            ((QSEditContract.View) this.mView).refreshList(this.mSelectedList, this.mUnselectedList);
            ((QSEditContract.View) this.mView).setTitle(this.mTitleName);
        }
    }

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void detachView() {
        super.detachView();
        BydLogHelper.logD("detachView");
        resetList(this.mSelectedList);
        resetList(this.mUnselectedList);
        this.mDeviceManager.removeCallback(this.deviceManagerListener);
        this.mNetworkController.removeCallback((NetworkController.SignalCallback) this.mSignalCallback);
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

    private final class WifiSignalCallback implements NetworkController.SignalCallback {
        private WifiSignalCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void showTelephoneData(boolean show) {
            BydLogHelper.logD("WifiSignalCallback showTelephoneData show : " + show);
            QSEditPresenter.this.refreshQsList();
        }
    }
}
