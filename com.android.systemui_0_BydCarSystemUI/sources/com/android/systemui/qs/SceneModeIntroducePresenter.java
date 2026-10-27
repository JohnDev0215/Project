package com.android.systemui.qs;

import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.base.BasePresenter;
import com.android.systemui.qs.SceneModeIntroduceContract;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SceneModeIntroducePresenter extends BasePresenter<SceneModeIntroduceContract.View> implements SceneModeIntroduceContract.Presenter {
    private HashMap<String, String> contentMap;
    private Gson gson = new Gson();
    private List<SceneModeIntroduceBean> mList;
    private int mSelectedDef;
    private List<String> mSelectedItems;
    private int mUnselectedDef;
    private List<String> mUnselectedItems;

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void attachView(SceneModeIntroduceContract.View view) {
        super.attachView(view);
        this.mSelectedDef = R.array.scene_mode_selected_items;
        this.mUnselectedDef = R.array.scene_mode_unselected_items;
        HashMap<String, String> map = new HashMap<>();
        this.contentMap = map;
        map.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_rest), SystemUIApplication.mSystemUIApplication.getString(R.string.scene_respite_introduce));
        this.contentMap.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_black), SystemUIApplication.mSystemUIApplication.getString(R.string.scene_wipe_introduce));
    }

    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void initData() {
        this.mList = new ArrayList();
        this.mSelectedItems = Arrays.asList(((SceneModeIntroduceContract.View) this.mView).getContext().getResources().getStringArray(this.mSelectedDef));
        this.mUnselectedItems = Arrays.asList(((SceneModeIntroduceContract.View) this.mView).getContext().getResources().getStringArray(this.mUnselectedDef));
        for (String str : this.mSelectedItems) {
            if (this.contentMap.containsKey(str)) {
                this.mList.add(new SceneModeIntroduceBean(str, this.contentMap.get(str)));
            }
        }
        for (String str2 : this.mUnselectedItems) {
            if (this.contentMap.containsKey(str2)) {
                this.mList.add(new SceneModeIntroduceBean(str2, this.contentMap.get(str2)));
            }
        }
        ((SceneModeIntroduceContract.View) this.mView).refreshList(this.mList);
    }
}
