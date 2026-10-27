package com.byd.card.view.activity;

import android.app.UiStyleConfig;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.adapter.AddAppPopAdapter;
import com.byd.card.base.BaseMvpActivity;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.callback.NotifyListenerManager;
import com.byd.card.contract.AddCardContract;
import com.byd.card.floatcard.Executors;
import com.byd.card.presenter.AddCardPresenter;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.utils.DensityUtil;
import com.byd.card.widget.BlurView;
import com.byd.card.widget.SpacesItemDecoration;
import com.byd.card.widget.ViewUtils;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class AddCardPopActivity extends BaseMvpActivity<AddCardPresenter> implements AddCardContract.View, View.OnClickListener, AddAppPopAdapter.CheckChangeListener {
    private static final String TAG = "AddCardPopActivity";
    private AddAppPopAdapter addAppAdapter;
    private Bitmap backgroundBitmap;
    private ImageView mClose;
    private TextView mFinish;
    private List<AppOrShortcutItem> mInfoList = new ArrayList();
    private int mOrientation;
    private RecyclerView recyclerView;
    private BlurView rootView;
    private SpacesItemDecoration spacesItemDecoration;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.card.base.BaseMvpActivity
    public AddCardPresenter createPresenter() {
        return new AddCardPresenter();
    }

    @Override // com.byd.card.base.BaseMvpActivity
    protected int getLayoutId() {
        int uiStyle = UiStyleConfig.getInstance(this).getUiStyle();
        return uiStyle != 1 ? uiStyle != 2 ? AppUtils.isRightRudder() ? R.layout.activity_add_card_pop_right : R.layout.activity_add_card_pop : AppUtils.isRightRudder() ? R.layout.activity_add_card_pop_right : R.layout.activity_add_card_pop : AppUtils.isRightRudder() ? R.layout.denza_activity_add_card_pop_right : R.layout.denza_activity_add_card_pop;
    }

    @Override // com.byd.card.base.BaseMvpActivity
    protected void initView() {
        EventBus.getDefault().register(this);
        initUISetting(this);
        setBackgroundBitmap();
        initView(this);
        initData();
        this.mOrientation = getResources().getConfiguration().orientation;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (Constant.EventBusMessage.CANCEL_APP_CARD.equals(event.tag)) {
            cancelSelectApp(event.pkg);
        } else if (Constant.EventBusMessage.HIDE_ADD_CARD_DIALOG.equals(event.tag) || Constant.EventBusMessage.CLICK_UI_MODE.equals(event.tag)) {
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mOrientation != newConfig.orientation) {
            ToastUtils.cancel();
            this.mOrientation = newConfig.orientation;
            setContentView(getLayoutId());
            initUISetting(this);
            initView(this);
            setBackgroundBitmap();
            onUpdateView(this.mInfoList);
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume HIDE_ADD_CARD_EDIT");
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_ADD_CARD_EDIT));
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop SHOW_ADD_CARD_EDIT");
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SHOW_ADD_CARD_EDIT));
    }

    @Override // com.byd.card.base.BaseMvpActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BitmapUtils.setAddAppBlurBitmap(null);
    }

    private void initUISetting(Context context) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 48;
        attributes.x = 0;
        attributes.y = (int) context.getResources().getDimension(R.dimen.dp_72);
        if (AppUtils.isLandSpace(context)) {
            attributes.width = (int) context.getResources().getDimension(R.dimen.dp_1232);
            attributes.height = (int) context.getResources().getDimension(R.dimen.dp_456);
        } else {
            attributes.width = (int) context.getResources().getDimension(R.dimen.dp_672);
            attributes.height = (int) context.getResources().getDimension(R.dimen.dp_972);
        }
        int uiStyle = UiStyleConfig.getInstance(this).getUiStyle();
        if (uiStyle == 1) {
            window.setBackgroundDrawable(getDrawable(R.drawable.denza_blur_shadow_bg));
        } else if (uiStyle != 2) {
            window.setBackgroundDrawable(getDrawable(R.drawable.blur_shadow_bg));
        }
        window.setElevation(16.0f);
        window.clearFlags(2);
        window.setAttributes(attributes);
    }

    private void initData() {
        ((AddCardPresenter) this.mPresenter).queryData();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.BACK_KEY_DOWN));
    }

    @Override // com.byd.card.contract.AddCardContract.View
    public void onUpdateView(List<AppOrShortcutItem> list) {
        this.mInfoList = list;
        this.addAppAdapter.updateAdapter(list);
        this.addAppAdapter.notifyDataSetChanged();
    }

    private void initView(Context context) {
        BlurView blurView = (BlurView) findViewById(R.id.add_card_pop_root);
        this.rootView = blurView;
        blurView.setBlurViewRadius(context.getResources().getDimensionPixelOffset(R.dimen.dp_16));
        this.recyclerView = (RecyclerView) findViewById(R.id.add_card_recycle_view);
        this.mFinish = (TextView) findViewById(R.id.add_finish);
        this.mClose = (ImageView) findViewById(R.id.close_edit_activity);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration();
        this.spacesItemDecoration = spacesItemDecoration;
        this.recyclerView.addItemDecoration(spacesItemDecoration);
        AddAppPopAdapter addAppPopAdapter = new AddAppPopAdapter(this);
        this.addAppAdapter = addAppPopAdapter;
        addAppPopAdapter.setCheckChangeListener(this);
        this.mFinish.setOnClickListener(this);
        this.mClose.setOnClickListener(this);
        this.recyclerView.setAdapter(this.addAppAdapter);
        ViewUtils.setRecyclerViewOverScroll(this.recyclerView);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.add_finish || id == R.id.close_edit_activity) {
            NotifyListenerManager.getInstance().notifyContext(this.addAppAdapter.getCheckedList(), "add_card");
        }
        finish();
    }

    @Override // com.byd.card.adapter.AddAppPopAdapter.CheckChangeListener
    public void onLimitHint(List<AppOrShortcutItem> checkedList) {
        ToastUtils.show(getApplicationContext(), getString(R.string.launcher_add_app_most));
    }

    private void cancelSelectApp(String packageName) {
        AddAppPopAdapter addAppPopAdapter = this.addAppAdapter;
        if (addAppPopAdapter != null) {
            addAppPopAdapter.cancelSelectApp(packageName);
        }
    }

    private void setBackgroundBitmap() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.activity.AddCardPopActivity.1
            @Override // java.lang.Runnable
            public void run() {
                int dimensionPixelOffset;
                int dimensionPixelOffset2;
                AddCardPopActivity.this.backgroundBitmap = BitmapUtils.getAddAppBlurBitmap();
                if (AddCardPopActivity.this.backgroundBitmap == null) {
                    int dimensionPixelOffset3 = AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_24);
                    int statusBarHeight = DensityUtil.getStatusBarHeight(AddCardPopActivity.this) + AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_16);
                    if (AppUtils.isLandSpace(AddCardPopActivity.this)) {
                        dimensionPixelOffset = AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_1232);
                        dimensionPixelOffset2 = AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_456);
                    } else {
                        dimensionPixelOffset = AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_672);
                        dimensionPixelOffset2 = AddCardPopActivity.this.getResources().getDimensionPixelOffset(R.dimen.dp_972);
                    }
                    AddCardPopActivity addCardPopActivity = AddCardPopActivity.this;
                    addCardPopActivity.backgroundBitmap = BitmapUtils.getScreenshot1(addCardPopActivity, new Rect(dimensionPixelOffset3, statusBarHeight, dimensionPixelOffset3 + dimensionPixelOffset, statusBarHeight + dimensionPixelOffset2), dimensionPixelOffset, dimensionPixelOffset2);
                    BitmapUtils.setAddAppBlurBitmap(AddCardPopActivity.this.backgroundBitmap);
                }
                AddCardPopActivity.this.runOnUiThread(new Runnable() { // from class: com.byd.card.view.activity.AddCardPopActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AddCardPopActivity.this.rootView.setBlurView(AddCardPopActivity.this.backgroundBitmap);
                    }
                });
            }
        });
    }
}
