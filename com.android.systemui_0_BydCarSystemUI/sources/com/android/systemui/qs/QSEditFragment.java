package com.android.systemui.qs;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.base.BaseFragment;
import com.android.systemui.qs.QSEditContract;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.statusbar.phone.BlurViewController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.StyleUtil;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QSEditFragment extends BaseFragment<QSEditPresenter> implements View.OnClickListener, QSEditContract.View {
    public static final String TAG = "QSEditFragment";
    private static final int UPDATE_TRANSPORT_MODE_STATE = 1;
    private int countMax;
    private ImageView img_back;
    private BlurViewController mBlurViewController;
    private CarController mCarController;
    private int mCategoryLabelType;
    private QSPanelAdapter mSelectedAdapter;
    private RecyclerView mSelectedRV;
    private QSPanelAdapter mUnselectedAdapter;
    private RecyclerView mUnselectedRV;
    private View rl_recyclerview_container;
    private StatusBar statusBar;
    private TextView tv_back;
    private TextView tv_right;
    private final H mHandler = new H();
    private final CarController.Callback mCarControlCallback = new CarController.Callback() { // from class: com.android.systemui.qs.QSEditFragment.3
        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onTransportModeChanged(int state) {
            BydLogHelper.logD("onTransportModeChanged state : " + state + " , mCategoryLabelType = " + QSEditFragment.this.mCategoryLabelType);
            if (QSEditFragment.this.mCategoryLabelType == 1) {
                QSEditFragment.this.mHandler.removeMessages(1);
                Message message = new Message();
                message.what = 1;
                message.arg1 = state;
                QSEditFragment.this.mHandler.sendMessageDelayed(message, 100L);
            }
        }
    };

    @Override // com.android.systemui.base.BaseFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BydLogHelper.logD("QSEditFragment onCreateView");
        Bundle arguments = getArguments();
        this.mCategoryLabelType = 0;
        if (arguments != null) {
            this.mCategoryLabelType = arguments.getInt(QuickQSPanel.category_label_type_key, 0);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.android.systemui.base.BaseFragment
    protected int getLayoutId() {
        int i = this.mCategoryLabelType;
        if (i == 2) {
            this.countMax = 2;
            return R.layout.activity_qs_edit_scene;
        }
        if (i == 1) {
            this.countMax = 6;
            if (AppUtils.isRightRudder()) {
                return StyleUtil.getResLayout(R.layout.activity_qs_edit_car_right_rudder, "activity_qs_edit_car_right_rudder");
            }
            return StyleUtil.getResLayout(R.layout.activity_qs_edit_car, "activity_qs_edit_car");
        }
        this.countMax = 6;
        if (AppUtils.isRightRudder()) {
            return StyleUtil.getResLayout(R.layout.activity_qs_edit_right_rudder, "activity_qs_edit_right_rudder");
        }
        return StyleUtil.getResLayout(R.layout.activity_qs_edit, "activity_qs_edit");
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void attachView() {
        BydLogHelper.logD("QSEditFragment attachView");
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        carController.addCallback(this.mCarControlCallback);
        super.attachView();
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void detachView() {
        BydLogHelper.logD("QSEditFragment detachView");
        this.mCarController.removeCallback(this.mCarControlCallback);
        super.detachView();
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void initView(View view) {
        this.mSelectedRV = (RecyclerView) view.findViewById(R.id.rv_items_selected);
        this.mUnselectedRV = (RecyclerView) view.findViewById(R.id.rv_items_unselected);
        this.img_back = (ImageView) view.findViewById(R.id.img_back);
        this.tv_right = (TextView) view.findViewById(R.id.tv_right);
        this.tv_back = (TextView) view.findViewById(R.id.tv_back);
        this.img_back.setOnClickListener(this);
        this.tv_right.setOnClickListener(this);
        this.tv_back.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_drawer_mask);
        BlurViewController blurViewController = (BlurViewController) Dependency.get(BlurViewController.class);
        this.mBlurViewController = blurViewController;
        if (blurViewController.getBlurBitmap() != null) {
            imageView.setImageBitmap(this.mBlurViewController.getBlurBitmap());
        }
        View viewFindViewById = view.findViewById(R.id.rl_recyclerview_container);
        this.rl_recyclerview_container = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void initData() {
        this.mSelectedRV.addOnItemTouchListener(new OnRecyclerItemClickListener(this.mSelectedRV) { // from class: com.android.systemui.qs.QSEditFragment.1
            @Override // com.android.systemui.qs.OnRecyclerItemClickListener
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override // com.android.systemui.qs.OnRecyclerItemClickListener
            public void onItemLongClick(RecyclerView recyclerView, RecyclerView.ViewHolder vh) {
                int adapterPosition;
                QSTile qSTile;
                if (QSEditFragment.this.mSelectedAdapter.getData().size() <= 1 || QSEditFragment.this.mSelectedAdapter.getData().size() <= (adapterPosition = vh.getAdapterPosition()) || (qSTile = QSEditFragment.this.mSelectedAdapter.getData().get(adapterPosition)) == null || qSTile.disableDrag()) {
                    return;
                }
                RecycleViewOnDragListener.startDrag(recyclerView, vh);
            }
        });
        this.mSelectedAdapter = new QSPanelAdapter(((QSEditPresenter) this.mPresenter).getSelectedName(), ((QSEditPresenter) this.mPresenter).getSelectedId());
        this.mSelectedRV.setLayoutManager(new GridLayoutManager(getContext(), ((QSEditPresenter) this.mPresenter).getSpanCount()));
        this.mSelectedRV.setAdapter(this.mSelectedAdapter);
        this.mSelectedAdapter.setCountLimited(true);
        this.mSelectedAdapter.setMaxCount(this.countMax);
        this.mSelectedAdapter.setFlagType(true);
        this.mUnselectedRV.addOnItemTouchListener(new OnRecyclerItemClickListener(this.mUnselectedRV) { // from class: com.android.systemui.qs.QSEditFragment.2
            @Override // com.android.systemui.qs.OnRecyclerItemClickListener
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override // com.android.systemui.qs.OnRecyclerItemClickListener
            public void onItemLongClick(RecyclerView recyclerView, RecyclerView.ViewHolder vh) {
                RecycleViewOnDragListener.startDrag(recyclerView, vh);
            }
        });
        QSPanelAdapter qSPanelAdapter = new QSPanelAdapter(((QSEditPresenter) this.mPresenter).getUnselectedName(), ((QSEditPresenter) this.mPresenter).getUnselectedId());
        this.mUnselectedAdapter = qSPanelAdapter;
        qSPanelAdapter.setFlagType(true);
        this.mUnselectedRV.setLayoutManager(new GridLayoutManager(getContext(), ((QSEditPresenter) this.mPresenter).getSpanCountUnselect()));
        this.mUnselectedRV.setAdapter(this.mUnselectedAdapter);
        RecycleViewOnDragListener recycleViewOnDragListener = new RecycleViewOnDragListener();
        this.mSelectedRV.setOnDragListener(recycleViewOnDragListener);
        this.mUnselectedRV.setOnDragListener(recycleViewOnDragListener);
        super.initData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back /* 2131296551 */:
            case R.id.tv_back /* 2131297044 */:
                this.statusBar.backStack(TAG);
                break;
            case R.id.rl_recyclerview_container /* 2131296815 */:
                this.statusBar.closePanelsIfNeed();
                break;
            case R.id.tv_right /* 2131297063 */:
                this.statusBar.showSceneModeIntroduce();
                break;
        }
    }

    @Override // com.android.systemui.qs.QSEditContract.View
    public void refreshList(List<QSTile> selected, List<QSTile> unselected) {
        BydLogHelper.logD("refreshList SelectedName : " + ((QSEditPresenter) this.mPresenter).getSelectedName() + " , SelectedId = " + ((QSEditPresenter) this.mPresenter).getSelectedId());
        this.mSelectedAdapter.refreshList(selected);
        this.mUnselectedAdapter.refreshList(unselected);
    }

    @Override // com.android.systemui.qs.QSEditContract.View
    public int getCategoryLabelType() {
        return this.mCategoryLabelType;
    }

    @Override // com.android.systemui.qs.QSEditContract.View
    public void setTitle(String name) {
        if (TextUtils.equals(name, getContext().getString(R.string.scene_mode_name))) {
            this.tv_right.setVisibility(0);
        } else {
            this.tv_right.setVisibility(8);
        }
        this.tv_back.setText(name);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(getLayoutId(), (ViewGroup) null);
        ViewGroup viewGroup = (ViewGroup) getView();
        viewGroup.removeAllViews();
        viewGroup.addView(viewInflate);
        ((QSEditPresenter) this.mPresenter).initParameter();
        initView(viewInflate);
        initData();
    }

    private final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int i = msg.arg1;
                BydLogHelper.logD("handleMessage state : " + i);
                QSEditFragment.this.updateTransportModeState(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTransportModeState(final int state) {
        BydLogHelper.logD("updateTransportModeState state : " + state);
        if (state == 3 || state == 2 || state == 1) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.QSEditFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    boolean z;
                    String string = QSEditFragment.this.mContext.getString(R.string.transport_mode);
                    List<QSTile> arrayList = new ArrayList<>();
                    if (QSEditFragment.this.mSelectedAdapter != null) {
                        arrayList = QSEditFragment.this.mSelectedAdapter.getData();
                    }
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            z = false;
                            break;
                        } else {
                            if (arrayList.get(i2).getTileLabel().equals(string)) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                    }
                    List<QSTile> arrayList2 = new ArrayList<>();
                    if (QSEditFragment.this.mUnselectedAdapter != null) {
                        arrayList2 = QSEditFragment.this.mUnselectedAdapter.getData();
                    }
                    if (!z) {
                        while (true) {
                            if (i >= arrayList2.size()) {
                                break;
                            }
                            if (arrayList2.get(i).getTileLabel().equals(string)) {
                                z = true;
                                break;
                            }
                            i++;
                        }
                    }
                    BydLogHelper.logD("updateTransportModeState", "isContainTransportMode = " + z);
                    int i3 = state;
                    if (i3 == 3 && z) {
                        DeviceManager.getInstance().removeCarControlList(string);
                        ((QSEditPresenter) QSEditFragment.this.mPresenter).refreshCarList();
                    } else if ((i3 == 1 || i3 == 2) && !z) {
                        DeviceManager.getInstance().addCarControlList(string);
                        ((QSEditPresenter) QSEditFragment.this.mPresenter).refreshCarList();
                    }
                }
            });
        }
    }
}
