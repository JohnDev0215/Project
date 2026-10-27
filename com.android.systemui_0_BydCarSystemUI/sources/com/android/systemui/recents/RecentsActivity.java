package com.android.systemui.recents;

import android.app.ActivityOptions;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.base.BaseActivity;
import com.android.systemui.recents.RecentAdapter;
import com.android.systemui.recents.RecentsContract;
import com.android.systemui.recents.event.RecentEvent;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.android.systemui.util.BlurUtil;
import com.android.systemui.util.FastClickUtil;
import com.android.systemui.util.ScreenShotUtil;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.widget.RecentRecyclerSpace;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class RecentsActivity extends BaseActivity<RecentPresenter> implements View.OnClickListener, RecentAdapter.OnItemSwipedListener, RecentAdapter.OnItemClickListener, RecentsContract.View {
    private RecentAdapter adapter;
    private Bitmap blurBitmap;
    private ImageView img_drawer_overlay;
    private ImageView img_no_data;
    private boolean isClearing;
    private boolean isExitAnimationing;
    private GridLayoutManager layoutManager;
    private LinearLayout ll_clear;
    private ImageView mBlurView;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.recents.RecentsActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothHeadsetClientCall parcelableExtra;
            if (!intent.getAction().equals("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED") || (parcelableExtra = intent.getParcelableExtra("android.bluetooth.headsetclient.extra.CALL")) == null) {
                return;
            }
            int state = parcelableExtra.getState();
            BydLogHelper.logD("state = " + state);
            if (state == 0 || state == 4 || state == 3) {
                RecentsActivity.this.finish();
            }
        }
    };
    private boolean mItemClickAnimShow;
    private int mOrientationOld;
    private RelativeLayout rl_root;
    private RecyclerView rv_recent;
    private TextView tv_no_data;

    @Override // com.android.systemui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_root_in, R.anim.anim_root_out);
        EventBus.getDefault().register(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    @Override // com.android.systemui.base.BaseActivity
    protected int getLayoutResID() {
        return StyleUtil.getResLayout(R.layout.recents, "recents");
    }

    @Override // com.android.systemui.base.BaseActivity
    protected void initData() {
        super.initData();
        this.mOrientationOld = getResources().getConfiguration().orientation;
        try {
            setBlurBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.android.systemui.base.BaseActivity
    protected void initView() {
        int dimensionPixelSize;
        this.mBlurView = (ImageView) findViewById(R.id.img_drawer_mask);
        this.img_drawer_overlay = (ImageView) findViewById(R.id.img_drawer_overlay);
        this.rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        this.img_no_data = (ImageView) findViewById(R.id.img_no_data);
        this.tv_no_data = (TextView) findViewById(R.id.tv_no_data);
        this.ll_clear = (LinearLayout) findViewById(R.id.ll_clear);
        this.rl_root.setOnClickListener(this);
        this.ll_clear.setOnClickListener(this);
        this.adapter = new RecentAdapter(this);
        this.rv_recent = (RecyclerView) findViewById(R.id.rv_recent);
        RecentItemTouchCallBack recentItemTouchCallBack = new RecentItemTouchCallBack();
        recentItemTouchCallBack.setOnItemTouchListener(this.adapter);
        new ItemTouchHelper(recentItemTouchCallBack).attachToRecyclerView(this.rv_recent);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        this.layoutManager = gridLayoutManager;
        gridLayoutManager.setOrientation(0);
        this.rv_recent.setLayoutManager(this.layoutManager);
        int dimension = (int) getResources().getDimension(R.dimen.dp_24);
        if (getResources().getConfiguration().orientation == 2) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_80);
        } else {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_32);
        }
        this.rv_recent.addItemDecoration(new RecentRecyclerSpace(dimensionPixelSize, dimension, dimensionPixelSize, 0));
        this.rv_recent.setAdapter(this.adapter);
        this.adapter.setOnItemSwipedListener(this);
        this.adapter.setOnItemClickListener(this);
        this.rv_recent.addOnItemTouchListener(new RecentRecyclerItemClickListener(this.rv_recent) { // from class: com.android.systemui.recents.RecentsActivity.2
            @Override // com.android.systemui.recents.RecentRecyclerItemClickListener
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override // com.android.systemui.recents.RecentRecyclerItemClickListener, androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override // com.android.systemui.recents.RecentRecyclerItemClickListener
            public void onEmptyClick() {
                RecentsActivity.this.exitAnim();
            }
        });
    }

    private void resetView(boolean noData) {
        if (noData) {
            this.rv_recent.setVisibility(8);
            this.ll_clear.setVisibility(8);
            this.img_no_data.setVisibility(0);
            this.tv_no_data.setVisibility(0);
            return;
        }
        this.rv_recent.setVisibility(0);
        this.img_no_data.setVisibility(8);
        this.tv_no_data.setVisibility(8);
        enterClearBtnEnlargeAnim();
    }

    private void enterClearBtnEnlargeAnim() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.3
            @Override // java.lang.Runnable
            public void run() {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(RecentsActivity.this, R.anim.anim_clear_btn_enlarge);
                animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.systemui.recents.RecentsActivity.3.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                        RecentsActivity.this.ll_clear.setVisibility(0);
                    }
                });
                RecentsActivity.this.ll_clear.startAnimation(animationLoadAnimation);
            }
        }, 350L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitAnim() {
        RecentAdapter recentAdapter = this.adapter;
        if (recentAdapter != null && recentAdapter.getItemCount() > 0) {
            if (this.isExitAnimationing) {
                BydLogHelper.logD("isExitAnimationing is true");
                return;
            }
            this.isExitAnimationing = true;
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_item_out);
            new LayoutAnimationController(animationLoadAnimation).setOrder(0);
            animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.systemui.recents.RecentsActivity.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    RecentsActivity.this.isExitAnimationing = false;
                    RecentsActivity.this.finish();
                }
            });
            this.rv_recent.startAnimation(animationLoadAnimation);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    RecentsActivity.this.exitClearBtnReduceAnim2();
                }
            }, 100L);
            return;
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override // com.android.systemui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        unregisterReceiver(this.mBroadcastReceiver);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.mItemClickAnimShow) {
            return;
        }
        overridePendingTransition(R.anim.anim_root_close_in, R.anim.anim_root_close_out);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        SplitScreenHelpManager.getInstance(this).hideSplitNavView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_clear) {
            Log.i("RecentsActivity", "click clear btn");
            if (FastClickUtil.isFastDoubleClick(500L)) {
                BydLogHelper.logD("onClick is fast click");
                return;
            } else {
                removeAllRecentTasks();
                return;
            }
        }
        if (id != R.id.rl_root) {
            return;
        }
        Log.i("RecentsActivity", "click root bg");
        if (FastClickUtil.isFastDoubleClick(500L)) {
            BydLogHelper.logD("onClick is fast click");
        } else {
            exitAnim();
        }
    }

    private void exitClearBtnReduceAnim() {
        this.ll_clear.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_clear_btn_reduce));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitClearBtnReduceAnim2() {
        this.ll_clear.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_clear_btn_reduce2));
    }

    public void removeAllRecentTasks() {
        if (this.isClearing) {
            BydLogHelper.logD("isClearing is true");
            return;
        }
        this.isClearing = true;
        exitClearBtnReduceAnim();
        this.adapter.clearRecent();
        delayFinish();
    }

    private void delayFinish() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.6
            @Override // java.lang.Runnable
            public void run() {
                RecentsActivity.this.isClearing = false;
                long jUptimeMillis = SystemClock.uptimeMillis();
                RecentsActivity.this.sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
                RecentsActivity.this.sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
                RecentsActivity.this.finish();
            }
        }, 300L);
    }

    public void sendEvent(int keyCode, int action, int flags, long down, long when) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257), 0);
    }

    @Override // com.android.systemui.recents.RecentAdapter.OnItemSwipedListener
    public void onClearFinished() {
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009f  */
    @Override // com.android.systemui.recents.RecentAdapter.OnItemSwipedListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onItemSwipedUp(final int r5, final com.android.systemui.recents.RecentTaskBean r6, final boolean r7) {
        /*
            r4 = this;
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "onItemSwipedUp packageName = "
            java.lang.StringBuilder r5 = r5.append(r0)
            android.content.ComponentName r0 = r6.getComponentName()
            java.lang.String r0 = r0.getPackageName()
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r5)
            android.content.ComponentName r5 = r6.getComponentName()
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r0 = "com.telenav.scoutivi.spotify.byd"
            boolean r5 = android.text.TextUtils.equals(r0, r5)
            r1 = 1
            if (r5 == 0) goto L37
            java.lang.String r5 = "byd.intent.action.KILL_SPOTIFY"
            r4.sendBroadcastFromRecent(r0, r5)
        L35:
            r5 = r1
            goto L77
        L37:
            android.content.ComponentName r5 = r6.getComponentName()
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r0 = "com.byd.localmusic"
            boolean r5 = android.text.TextUtils.equals(r0, r5)
            if (r5 == 0) goto L4f
            r5 = -1
            r0 = 254(0xfe, float:3.56E-43)
            r2 = 0
            com.byd.mediacenter.api.media.MediaControl.controlMedia(r4, r5, r0, r2, r2)
            goto L35
        L4f:
            android.content.ComponentName r5 = r6.getComponentName()
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r0 = "com.byd.scenemode"
            boolean r5 = android.text.TextUtils.equals(r0, r5)
            if (r5 == 0) goto L65
            java.lang.String r5 = "byd.intent.action.kill_scene_mode"
            r4.sendBroadcastFromRecent(r0, r5)
            goto L35
        L65:
            android.content.ComponentName r5 = r6.getComponentName()
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r0 = "com.byd.bluetoothcall"
            boolean r5 = android.text.TextUtils.equals(r0, r5)
            if (r5 == 0) goto L76
            goto L35
        L76:
            r5 = 0
        L77:
            if (r5 == 0) goto L8c
            com.android.systemui.Dependency$DependencyKey<android.os.Handler> r5 = com.android.systemui.Dependency.MAIN_HANDLER
            java.lang.Object r5 = com.android.systemui.Dependency.get(r5)
            android.os.Handler r5 = (android.os.Handler) r5
            com.android.systemui.recents.RecentsActivity$7 r0 = new com.android.systemui.recents.RecentsActivity$7
            r0.<init>()
            r2 = 80
            r5.postDelayed(r0, r2)
            goto L8f
        L8c:
            r4.removeTaskCommon(r6)
        L8f:
            java.util.HashMap<java.lang.String, java.lang.Boolean> r5 = com.android.systemui.Constants.recentShowLockMap
            android.content.ComponentName r0 = r6.getComponentName()
            java.lang.String r0 = r0.getPackageName()
            boolean r5 = r5.containsKey(r0)
            if (r5 == 0) goto Lac
            java.util.HashMap<java.lang.String, java.lang.Boolean> r5 = com.android.systemui.Constants.recentShowLockMap
            android.content.ComponentName r6 = r6.getComponentName()
            java.lang.String r6 = r6.getPackageName()
            r5.remove(r6)
        Lac:
            if (r7 == 0) goto Lb9
            com.android.systemui.recents.RecentAdapter r5 = r4.adapter
            int r5 = r5.getItemCount()
            if (r5 >= r1) goto Lb9
            r4.finish()
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.recents.RecentsActivity.onItemSwipedUp(int, com.android.systemui.recents.RecentTaskBean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTaskCommon(final RecentTaskBean bean) {
        ComponentName componentName = bean.getComponentName();
        LegacyRecentsImpl.getTaskLoader().deleteTaskData(bean.getTaskKey());
        if (componentName != null) {
            final String packageName = componentName.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return;
            }
            if (Constants.MEDIA_APP_PACKAGES.containsKey(packageName)) {
                sendForegroundBroadcast(packageName, Constants.SYS_KILL_PROCESS);
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.8
                    @Override // java.lang.Runnable
                    public void run() {
                        int taskId = bean.getTaskId();
                        BydLogHelper.logD("kill process packageName = " + packageName + " , taskId = " + taskId);
                        ActivityManagerWrapper.getInstance().removeTask(taskId);
                        String str = Constants.MEDIA_APP_PACKAGES.get(packageName);
                        BydLogHelper.logD("kill process service name = " + str);
                        ActivityManagerWrapper.getInstance().killProcess(str);
                    }
                }, 300L);
                return;
            }
            ActivityManagerWrapper.getInstance().removeTask(bean.getTaskId());
            if (AppUtils.isSystemApp(this, packageName)) {
                return;
            }
            if ("com.gpack.agent".equals(packageName)) {
                BydLogHelper.logD("kill process processName = " + bean.getProcessName());
                ActivityManagerWrapper.getInstance().killProcessByGPack(bean.getProcessName());
            } else {
                BydLogHelper.logD("kill process packageName = " + packageName);
                ActivityManagerWrapper.getInstance().killProcess(packageName);
            }
        }
    }

    private synchronized void sendBroadcastFromRecent(String pkg, String action) {
        BydLogHelper.logV("broadcast:" + action);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setPackage(pkg);
        sendBroadcast(intent);
    }

    private void sendForegroundBroadcast(String pkg, String action) {
        BydLogHelper.logD("sendForegroundBroadcast broadcast:" + action);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setPackage(pkg);
        intent.setFlags(268435456);
        sendBroadcast(intent);
    }

    @Override // com.android.systemui.recents.RecentAdapter.OnItemSwipedListener
    public void onItemSwipedDown(int position, RecentTaskBean bean) {
        Constants.recentShowLockMap.put(bean.getComponentName().getPackageName(), Boolean.valueOf(bean.isShowLock()));
    }

    @Override // com.android.systemui.recents.RecentAdapter.OnItemClickListener
    public void onItemClick(RecyclerView recyclerView, View view, int position, RecentTaskBean bean) {
        BydLogHelper.logD("onItemClick:" + bean.getAppName());
        this.mItemClickAnimShow = true;
        ActivityOptions activityOptionsMakeScaleUpAnimation = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        activityOptionsMakeScaleUpAnimation.setLaunchDisplayId(view.getRootView().getDisplay().getDisplayId());
        ActivityManagerWrapper.getInstance().startActivityFromRecents(bean.getTaskId(), activityOptionsMakeScaleUpAnimation);
        finish();
    }

    @Override // com.android.systemui.recents.RecentAdapter.OnItemClickListener
    public void onSplitClick(RecentTaskBean recentTaskBean) {
        finish();
        handleSplit(recentTaskBean);
    }

    private void handleSplit(RecentTaskBean recentTaskBean) {
        boolean zIsShowHelpActivity = SplitScreenHelpManager.getInstance(getContext()).isShowHelpActivity();
        BydLogHelper.logD("handleSplit isShowHelpActivity : " + zIsShowHelpActivity);
        if (DividerUtils.getInstance().isFirstStartSplitScreen(this) && !zIsShowHelpActivity) {
            BydLogHelper.logD("is first start split screen");
            SplitScreenHelpManager.getInstance(getContext()).showHelpView();
        } else {
            DividerUtils.getInstance().splitScreen(getContext(), recentTaskBean);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishEvent(RecentEvent event) {
        BydLogHelper.logD("onFinishEvent: RecentEvent");
        finish();
    }

    public void setBlurBackground() {
        new Thread(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.9
            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(RecentsActivity.this);
                if (bitmapTakeScreenShot == null) {
                    Log.d(RecentsActivity.this.TAG, "setBlurBackground bitmap == null");
                    bitmapTakeScreenShot = BitmapFactory.decodeResource(RecentsActivity.this.getResources(), R.mipmap.img_app_bg);
                }
                RecentsActivity recentsActivity = RecentsActivity.this;
                recentsActivity.blurBitmap = BlurUtil.blur((Context) recentsActivity, bitmapTakeScreenShot, 25);
                RecentsActivity.this.setImg();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImg() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.recents.RecentsActivity.10
            @Override // java.lang.Runnable
            public void run() {
                RecentsActivity.this.setBlurBg();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBlurBg() {
        Bitmap bitmap;
        ImageView imageView = this.mBlurView;
        if (imageView == null || (bitmap = this.blurBitmap) == null) {
            return;
        }
        try {
            imageView.setImageBitmap(bitmap);
            this.mBlurView.setVisibility(0);
            ImageView imageView2 = this.img_drawer_overlay;
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.android.systemui.recents.RecentsContract.View
    public void refreshList(List<RecentTaskBean> list) {
        this.mItemClickAnimShow = false;
        this.isClearing = false;
        if (list != null && list.size() > 0) {
            resetView(false);
            this.adapter.refreshList(list);
            this.isExitAnimationing = false;
            return;
        }
        resetView(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mOrientationOld != newConfig.orientation) {
            BydLogHelper.logD("orientation changed , newConfig:" + newConfig.orientation);
            this.mOrientationOld = newConfig.orientation;
            setContentView(getLayoutResID());
            initView();
            setBlurBg();
            if (this.mPresenter != 0) {
                ((RecentPresenter) this.mPresenter).preloadPlan(LegacyRecentsImpl.getTaskLoader());
            }
        }
    }
}
