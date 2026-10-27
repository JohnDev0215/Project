package com.byd.card.floatcard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.Utilities;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.statusbar.policy.CarController;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.CardDatabase;
import com.byd.card.floatcard.CardAdapter;
import com.byd.card.floatcard.RecyclerViewTouchListener;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.CarPlayAndAutoManager;
import com.byd.card.utils.SingleThreadPoolUtils;
import com.byd.card.view.broadcast.AutoMapWidgetProvider;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class DragCardView extends RelativeLayout implements CardAdapter.MyAdapterListener {
    private static final String TAG = "DragCardView";
    private OnAddClickListener mAddClickListener;
    private boolean mCanScrollHorizontally;
    protected CPAAControlCallback mCarControlCallback;
    private CarController mCarController;
    private CardAdapter mCardAdapter;
    private final Context mContext;
    private DragManager mDragManager;
    private final PointF mDragTouchPoint;
    private boolean mEditStatus;
    private FrameLayout mFrameLayout;
    private final CopyOnWriteArrayList<Object> mInitData;
    private RVItemDecoration mItemDecoration;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mLongClickable;
    private int mOrientation;
    private RecyclerView mRecyclerView;
    private RecyclerViewTouchListener mRecyclerViewTouchListener;
    private WindowManager mWindowManager;

    public interface OnAddClickListener {
        void onAddClick(View view);
    }

    public void setOnAddClickListener(OnAddClickListener addClickListener) {
        this.mAddClickListener = addClickListener;
    }

    public DragCardView(Context context) {
        super(context);
        this.mDragTouchPoint = new PointF();
        this.mInitData = new CopyOnWriteArrayList<>();
        this.mCanScrollHorizontally = true;
        this.mLongClickable = true;
        this.mCarControlCallback = new CPAAControlCallback();
        this.mContext = context;
        this.mOrientation = context.getResources().getConfiguration().orientation;
        init();
        initAnimationViewGrop();
    }

    @Override // android.view.View
    public void setLongClickable(boolean clickable) {
        this.mLongClickable = clickable;
    }

    public void setDeleteViewVisibility(boolean show) {
        CardAdapter cardAdapter = this.mCardAdapter;
        if (cardAdapter == null || show == cardAdapter.isDeleteShow()) {
            return;
        }
        this.mCardAdapter.setShowDelete(show);
        this.mCardAdapter.notifyDataSetChanged();
    }

    public void addCarPlayOrAuto(final AppInfo appInfo) {
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DragCardView.this.mInitData) {
                    if (DragCardView.this.mCardAdapter != null) {
                        DragCardView.this.mCardAdapter.addCarPlayOrAuto(appInfo);
                    }
                }
            }
        });
    }

    public void removeCarPlayOrAuto(final AppInfo appInfo) {
        if (this.mCardAdapter != null) {
            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (DragCardView.this.mInitData) {
                        CopyOnWriteArrayList<Object> data = DragCardView.this.mCardAdapter.getData();
                        for (int i = 0; i < data.size(); i++) {
                            Object obj = data.get(i);
                            if ((obj instanceof AppInfo) && appInfo.packageName.equals(((AppInfo) obj).packageName)) {
                                DragCardView.this.mRecyclerView.smoothScrollToPosition(0);
                                DragCardView.this.mRecyclerView.setHasFixedSize(true);
                                data.remove(i);
                                DragCardView.this.mCardAdapter.notifyItemRemoved(i);
                                DragCardView.this.mCardAdapter.notifyDataSetChanged();
                                Log.d(DragCardView.TAG, "removeCarPlayOrAuto remove " + appInfo.packageName);
                                AppUtils.updateMediaCard(DragCardView.this.mContext);
                                DragCardView.this.mCardAdapter.removeCarplayOrAuto();
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSystemUIMapWidget() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$DragCardView$yJ9PVRAnsOq1HxubRJ_fwRH_P-I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateSystemUIMapWidget$0$DragCardView();
            }
        }, 200L);
    }

    public /* synthetic */ void lambda$updateSystemUIMapWidget$0$DragCardView() {
        Intent intent = new Intent(AutoMapWidgetProvider.UPDATE_ACTION);
        intent.setComponent(new ComponentName(this.mContext.getPackageName(), AutoMapWidgetProvider.class.getName()));
        this.mContext.sendBroadcast(intent);
        Log.i(TAG, "updateSystemUIMapWidget");
    }

    public void addWidgetItem(final String pkg) {
        Log.i(TAG, "addWidgetItem pkg = " + pkg);
        if (TextUtils.isEmpty(pkg)) {
            return;
        }
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DragCardView.this.mInitData) {
                    if (DragCardView.this.mCardAdapter != null) {
                        String str = pkg;
                        byte b = -1;
                        int iHashCode = str.hashCode();
                        if (iHashCode != -2086107553) {
                            if (iHashCode != -809562372) {
                                if (iHashCode == 1996350279 && str.equals(Constant.PackageName.T_MAP_PKG)) {
                                    b = 2;
                                }
                            } else if (str.equals(Constant.PackageName.ZNAV_MAP_PKG)) {
                                b = 1;
                            }
                        } else if (str.equals(Constant.PackageName.AUTO_MAP_PKG)) {
                            b = 0;
                        }
                        if (b == 0 || b == 1 || b == 2) {
                            CardAdapter cardAdapter = DragCardView.this.mCardAdapter;
                            DragCardView dragCardView = DragCardView.this;
                            cardAdapter.addWidget(dragCardView.getWidgetProvider(dragCardView.mContext, SystemUIApplication.getContext().getPackageName()));
                            DragCardView.this.updateSystemUIMapWidget();
                        } else {
                            CardAdapter cardAdapter2 = DragCardView.this.mCardAdapter;
                            DragCardView dragCardView2 = DragCardView.this;
                            cardAdapter2.addWidget(dragCardView2.getWidgetProvider(dragCardView2.mContext, pkg));
                        }
                    }
                }
            }
        });
    }

    public void removeWidgetItem(final String pkg) {
        final boolean z;
        Log.i(TAG, "removeWidgetItem pkg = " + pkg);
        if (TextUtils.isEmpty(pkg) || this.mCardAdapter == null) {
            return;
        }
        pkg.hashCode();
        z = true;
        switch (pkg) {
            case "com.byd.automap":
            case "net.zmap.znav.byd8155":
            case "com.tmap.auto.byd":
                pkg = SystemUIApplication.getContext().getPackageName();
                break;
            default:
                z = false;
                break;
        }
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DragCardView.this.mInitData) {
                    if ((!z || (!AppUtils.isInstalled(DragCardView.this.mContext, Constant.PackageName.AUTO_MAP_PKG) && !AppUtils.isInstalled(DragCardView.this.mContext, Constant.PackageName.ZNAV_MAP_PKG))) && !AppUtils.isInstalled(DragCardView.this.mContext, Constant.PackageName.T_MAP_PKG)) {
                        CopyOnWriteArrayList<Object> data = DragCardView.this.mCardAdapter.getData();
                        for (int i = 0; i < data.size(); i++) {
                            Object obj = data.get(i);
                            if ((obj instanceof AppWidgetProviderInfo) && pkg.equals(((AppWidgetProviderInfo) obj).provider.getPackageName())) {
                                DragCardView.this.mRecyclerView.smoothScrollToPosition(0);
                                DragCardView.this.mRecyclerView.setHasFixedSize(true);
                                data.remove(i);
                                DragCardView.this.mCardAdapter.notifyItemRemoved(i);
                                DragCardView.this.mCardAdapter.notifyDataSetChanged();
                                Log.d(DragCardView.TAG, "removeWidgetItem remove " + pkg);
                                AppUtils.updateMediaCard(DragCardView.this.mContext);
                                DragCardView.this.mCardAdapter.saveItemToSp();
                            }
                        }
                        return;
                    }
                    DragCardView.this.updateSystemUIMapWidget();
                    Log.w(DragCardView.TAG, "removeWidgetItem cannot remove! still has one map!");
                }
            }
        });
    }

    public void updateForbidden(boolean isPGear) {
        this.mCardAdapter.updateForbidden(isPGear);
    }

    public void removeCardApp(final String pkg) {
        Log.i(TAG, "removeCardApp pkg = " + pkg);
        if (this.mCardAdapter == null || pkg == null) {
            return;
        }
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$DragCardView$MOCBEVh2SpdxHA4W7bJP_H9ieSc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeCardApp$1$DragCardView(pkg);
            }
        });
    }

    public /* synthetic */ void lambda$removeCardApp$1$DragCardView(String str) {
        synchronized (this.mInitData) {
            CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
            int i = 0;
            while (true) {
                if (i >= data.size()) {
                    break;
                }
                Object obj = data.get(i);
                if ((obj instanceof AppOrShortcutItem) && str.equals(((AppOrShortcutItem) obj).getItemIdentify())) {
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(i);
                    if (viewHolderFindViewHolderForAdapterPosition instanceof CardAppViewHolder) {
                        viewHolderFindViewHolderForAdapterPosition.setIsRecyclable(true);
                    }
                    data.remove(i);
                    this.mCardAdapter.notifyItemRemoved(i);
                    Log.d(TAG, "removeCardApp remove " + str);
                    this.mRecyclerView.scrollToPosition(data.size() - 1);
                    AppUtils.updateMediaCard(this.mContext);
                    this.mCardAdapter.saveItemToSp();
                } else {
                    i++;
                }
            }
        }
    }

    public void removeCardShortcuts(final String origin) {
        Log.i(TAG, "removeCardShortcuts origin = " + origin);
        if (this.mCardAdapter == null || TextUtils.isEmpty(origin)) {
            return;
        }
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$DragCardView$AS60ir0G-zmAVCkYaDQYIigA80g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeCardShortcuts$2$DragCardView(origin);
            }
        });
    }

    public /* synthetic */ void lambda$removeCardShortcuts$2$DragCardView(String str) {
        synchronized (this.mInitData) {
            CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
            boolean z = false;
            for (int size = data.size() - 1; size >= 0; size--) {
                Object obj = data.get(size);
                if ((obj instanceof ShortcutInfo) && str.equals(((ShortcutInfo) obj).getOrigin())) {
                    data.remove(obj);
                    Log.d(TAG, "removeCardShortcuts remove " + obj);
                    z = true;
                }
            }
            Log.d(TAG, "removeCardShortcuts hasDelete = " + z);
            if (z) {
                this.mCardAdapter.notifyDataSetChanged();
                this.mRecyclerView.scrollToPosition(data.size() - 1);
                AppUtils.updateMediaCard(this.mContext);
                this.mCardAdapter.saveItemToSp();
            }
        }
    }

    public void addCardApp(final AppOrShortcutItem item) {
        Log.i(TAG, "addCardApp item = " + item);
        if (this.mCardAdapter == null || item == null) {
            return;
        }
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DragCardView.this.mInitData) {
                    CopyOnWriteArrayList<Object> data = DragCardView.this.mCardAdapter.getData();
                    data.add(data.size(), item);
                    DragCardView.this.mCardAdapter.notifyItemInserted(data.size() - 1);
                    DragCardView.this.mRecyclerView.scrollToPosition(data.size() - 1);
                    DragCardView.this.mRecyclerView.removeItemDecoration(DragCardView.this.mItemDecoration);
                    DragCardView.this.mRecyclerView.addItemDecoration(DragCardView.this.mItemDecoration);
                    DragCardView.this.mCardAdapter.saveItemToSp();
                    ForbiddenManager.updateForbidden(item, ForbiddenManager.isPGear());
                }
            }
        });
    }

    public void setCanScrollHorizontally(boolean canScrollHorizontally) {
        this.mCanScrollHorizontally = canScrollHorizontally;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration newConfig) {
        ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
        if (newConfig.orientation == 2) {
            layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_125);
        } else {
            layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_160);
        }
        this.mRecyclerView.setLayoutParams(layoutParams);
        if (this.mOrientation != newConfig.orientation && this.mCardAdapter != null) {
            this.mOrientation = newConfig.orientation;
        }
        this.mRecyclerView.setAdapter(this.mCardAdapter);
    }

    public void updateView() {
        CardAdapter cardAdapter = this.mCardAdapter;
        if (cardAdapter != null) {
            cardAdapter.notifyDataSetChanged();
            AppUtils.updateMediaCard(this.mContext);
        }
    }

    private void init() {
        initView();
        initData();
    }

    public void moveToFirst() {
        this.mRecyclerView.smoothScrollToPosition(0);
        setDeleteViewVisibility(false);
    }

    public void moveBluetoothCardBack() {
        this.mCardAdapter.moveBluetoothCardBack();
    }

    public void moveBluetoothCardFront() {
        this.mCardAdapter.moveBluetoothCardFront();
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.6
            @Override // java.lang.Runnable
            public void run() {
                DragCardView.this.mRecyclerView.scrollToPosition(0);
            }
        });
    }

    private void initView() {
        if (getChildCount() != 0) {
            removeAllViews();
            this.mRecyclerView = null;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.card_list_layout, (ViewGroup) null);
        this.mRecyclerView = (RecyclerView) viewInflate.findViewById(R.id.recycler_card);
        addView(viewInflate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, 0 == true ? 1 : 0) { // from class: com.byd.card.floatcard.DragCardView.7
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return DragCardView.this.mCanScrollHorizontally;
            }
        };
        if (AppUtils.isRightRudder()) {
            this.mRecyclerView.setLayoutDirection(1);
        } else {
            this.mRecyclerView.setLayoutDirection(0);
        }
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        RVItemDecoration rVItemDecoration = new RVItemDecoration(this.mContext);
        this.mItemDecoration = rVItemDecoration;
        this.mRecyclerView.addItemDecoration(rVItemDecoration);
        this.mRecyclerView.setEdgeEffectFactory(new RecyclerEdgeEffectCompat());
        this.mRecyclerView.setMotionEventSplittingEnabled(false);
        initRecyclerViewTouchListener();
        this.mRecyclerView.addOnItemTouchListener(this.mRecyclerViewTouchListener);
        this.mRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);
        CardAdapter cardAdapter = new CardAdapter(this.mContext, this);
        this.mCardAdapter = cardAdapter;
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() { // from class: com.byd.card.floatcard.DragCardView.8
            @Override // com.byd.card.floatcard.CardAdapter.OnItemClickListener
            public void onItemClick(View view, Object appOrShortcut, int touchX, int touchY) {
                Log.d(DragCardView.TAG, "onItemClick " + appOrShortcut);
                if (appOrShortcut instanceof AppInfo) {
                    AppInfo appInfo = (AppInfo) appOrShortcut;
                    if ("com.ts.carplay.app".equals(appInfo.packageName) || "com.ts.androidauto.app".equals(appInfo.packageName)) {
                        CarPlayAndAutoManager.getInstance().cardJumpCPAA(DragCardView.this.mContext, appInfo);
                        return;
                    } else {
                        DragCardView.this.clickApp(view, appInfo);
                        return;
                    }
                }
                if (appOrShortcut instanceof ShortcutInfo) {
                    DragCardView.this.clickShortcut(view, (ShortcutInfo) appOrShortcut);
                }
            }
        });
        this.mRecyclerView.setAdapter(this.mCardAdapter);
        DragManager dragManager = new DragManager(this.mRecyclerView, this.mCardAdapter, this.mContext);
        this.mDragManager = dragManager;
        this.mRecyclerView.setOnDragListener(dragManager);
        this.mRecyclerView.setItemAnimator(new CardItemAnimator());
    }

    private void initRecyclerViewTouchListener() {
        this.mRecyclerViewTouchListener = new RecyclerViewTouchListener(this.mContext, this.mRecyclerView, new RecyclerViewTouchListener.OnItemClickListener() { // from class: com.byd.card.floatcard.DragCardView.9
            @Override // com.byd.card.floatcard.RecyclerViewTouchListener.OnItemClickListener
            public void onItemLongClick(View view, int position) {
                Log.i(DragCardView.TAG, "onItemLongClick position = " + position);
                if (HfpManage.getInstance().getCallState() >= 1) {
                    ToastUtils.show(DragCardView.this.mContext, DragCardView.this.mContext.getString(R.string.forbid_add_tip));
                    return;
                }
                CardAdapter cardAdapter = (CardAdapter) DragCardView.this.mRecyclerView.getAdapter();
                if (cardAdapter == null) {
                    Log.w(DragCardView.TAG, "onItemLongClick cardAdapter is null");
                    return;
                }
                CopyOnWriteArrayList<Object> data = cardAdapter.getData();
                if (data != null && data.size() > position) {
                    DragCardView.this.zoomIn(view, position, data);
                    DragCardView.this.setDeleteShow(true);
                } else {
                    Log.w(DragCardView.TAG, "onItemLongClick data is null or size <= position");
                }
            }

            @Override // com.byd.card.floatcard.RecyclerViewTouchListener.OnItemClickListener
            public void onItemSingleTapUp(View view, int position) {
                Log.i(DragCardView.TAG, "onItemSingleTapUp position = " + position);
            }

            @Override // com.byd.card.floatcard.RecyclerViewTouchListener.OnItemClickListener
            public void onDragPoint(MotionEvent event) {
                DragCardView.this.mDragTouchPoint.set(event.getX(), event.getY());
                if (DragCardView.this.mDragManager != null) {
                    DragCardView.this.mDragManager.updateDragTouchPoint(event);
                }
            }

            @Override // com.byd.card.floatcard.RecyclerViewTouchListener.OnItemClickListener
            public boolean onItemTouchUp(View view, int position) {
                Log.i(DragCardView.TAG, "onItemTouchUp position = " + position);
                if (position >= 0) {
                    if (DragCardView.this.mEditStatus) {
                        CardAdapter cardAdapter = (CardAdapter) DragCardView.this.mRecyclerView.getAdapter();
                        if (cardAdapter == null) {
                            Log.w(DragCardView.TAG, "onItemTouchUp cardAdapter is null, return false");
                            return false;
                        }
                        CopyOnWriteArrayList<Object> data = cardAdapter.getData();
                        if (data == null || data.size() <= position) {
                            Log.w(DragCardView.TAG, "onItemTouchUp data is null or size <= position, return false");
                            return false;
                        }
                        boolean z = data.get(position) instanceof AppWidgetProviderInfo;
                        Log.i(DragCardView.TAG, "onItemTouchUp result = " + z);
                        return z;
                    }
                    Log.i(DragCardView.TAG, "onItemTouchUp return false");
                    return false;
                }
                Log.i(DragCardView.TAG, "onItemTouchUp position < 0, return false");
                return false;
            }
        });
    }

    private boolean isCarPlayOrAuto(Object object) {
        if (!(object instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) object;
        return Constant.Activity.CARPLAY_ACTIVITY.equals(appInfo.activity) || Constant.Activity.AUTO_ACTIVITY.equals(appInfo.activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToSelectLocation() {
        TopActivityManager.getInstance(this.mContext).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.floatcard.DragCardView.10
            @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
            public void onTopActivityCallback(ComponentName componentName) {
                if (componentName != null) {
                    DragCardView.this.mRecyclerView.scrollToPosition(DragCardView.this.getItemForPosition(componentName.getPackageName()));
                } else {
                    Log.w(DragCardView.TAG, "scrollToSelectLocation componentName is null");
                }
            }
        });
    }

    public void setDeleteShow(boolean show) {
        CopyOnWriteArrayList<Object> data;
        CardAdapter cardAdapter;
        CardAdapter cardAdapter2 = this.mCardAdapter;
        if (cardAdapter2 != null) {
            data = cardAdapter2.getData();
            this.mCardAdapter.setShowDelete(show);
        } else {
            data = null;
        }
        if (data == null) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            MyViewHolder myViewHolder = (MyViewHolder) this.mRecyclerView.findViewHolderForAdapterPosition(i);
            if (myViewHolder == null && (cardAdapter = this.mCardAdapter) != null) {
                cardAdapter.notifyItemChanged(i);
            } else if ((myViewHolder instanceof CardAppViewHolder) && !isCarPlayOrAuto(data.get(i))) {
                if (show) {
                    ((CardAppViewHolder) myViewHolder).delete.setVisibility(0);
                } else {
                    ((CardAppViewHolder) myViewHolder).delete.setVisibility(4);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItemForPosition(String pkg) {
        if (TextUtils.isEmpty(pkg)) {
            return 0;
        }
        synchronized (this.mInitData) {
            CopyOnWriteArrayList<Object> copyOnWriteArrayList = this.mInitData;
            if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() != 0) {
                for (int i = 0; i < this.mInitData.size(); i++) {
                    if ((this.mInitData.get(i) instanceof AppWidgetProviderInfo) && pkg.equals(((AppWidgetProviderInfo) this.mInitData.get(i)).provider.getPackageName())) {
                        return i;
                    }
                }
                return 0;
            }
            return 0;
        }
    }

    private void initData() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.11
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DragCardView.this.mInitData) {
                    DragCardView.this.mInitData.clear();
                    DragCardView.this.initCardData();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCardData() {
        List<CardItemBean> cardList = SpUtils.getCardList(this.mContext);
        if (cardList.isEmpty()) {
            Context context = this.mContext;
            List<AppWidgetProviderInfo> widgetProviders = getWidgetProviders(context, AppUtils.initWidgetComponentName(context));
            this.mInitData.addAll(widgetProviders);
            Log.i(TAG, "initCardData add original WidgetProvider! size = " + widgetProviders.size() + " providers = " + widgetProviders);
        } else {
            Log.i(TAG, "initCardData add card from sp");
            for (CardItemBean cardItemBean : cardList) {
                if (cardItemBean != null) {
                    String type = cardItemBean.getType();
                    if (CardItemBean.TYPE_CARD_APP.equals(type)) {
                        try {
                            AppInfo appInfoByPackage = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage(cardItemBean.getPackageName());
                            if (appInfoByPackage != null) {
                                if (appInfoByPackage.packageName.equals("com.ts.carplay.app")) {
                                    if (((CarController) Dependency.get(CarController.class)).getCarPlayConnectionStatus()) {
                                        this.mInitData.add(appInfoByPackage);
                                        Log.i(TAG, "initCardData add app CARPLAY");
                                    }
                                } else if (appInfoByPackage.packageName.equals("com.ts.androidauto.app")) {
                                    if (((CarController) Dependency.get(CarController.class)).getAndroidAutoConnectionStatus()) {
                                        this.mInitData.add(appInfoByPackage);
                                        Log.i(TAG, "initCardData add app ANDROID_AUTO");
                                    }
                                } else {
                                    this.mInitData.add(appInfoByPackage);
                                    Log.i(TAG, "initCardData add app " + appInfoByPackage.packageName);
                                }
                            }
                        } catch (Exception e) {
                            Log.w(TAG, "initCardData getAppInfoByActivity exception : " + e.getMessage());
                        }
                    } else if (CardItemBean.TYPE_CARD_SHORTCUT.equals(type)) {
                        ShortcutInfo shortcutByIdentify = CardDatabase.getInstance().getShortcutInfoDao().getShortcutByIdentify(AppUtils.extractShortcutIdentify(cardItemBean.getPackageName()), AppUtils.extractShortcutOrigin(cardItemBean.getPackageName()));
                        if (shortcutByIdentify != null) {
                            this.mInitData.add(shortcutByIdentify);
                        }
                    } else if (CardItemBean.TYPE_CARD_WIDGET.equals(type)) {
                        String packageName = cardItemBean.getPackageName();
                        if (AppUtils.isInstalled(this.mContext, packageName)) {
                            AppWidgetProviderInfo widgetProvider = getWidgetProvider(this.mContext, packageName);
                            if (widgetProvider != null) {
                                this.mInitData.add(widgetProvider);
                                Log.i(TAG, "initCardData add widget success! packageName = " + packageName);
                            }
                        } else {
                            Log.w(TAG, "initCardData add widget fail! package not found! packageName = " + packageName);
                            checkReplaceMyCarWidget(packageName);
                        }
                    }
                }
            }
        }
        this.mInitData.add(new AddViewBean());
        if (this.mCardAdapter != null) {
            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.12
                @Override // java.lang.Runnable
                public void run() {
                    DragCardView.this.mCardAdapter.updateData(DragCardView.this.mInitData);
                    DragCardView.this.scrollToSelectLocation();
                    Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DragCardView.this.mCarController = (CarController) Dependency.get(CarController.class);
                            boolean androidAutoConnectionStatus = DragCardView.this.mCarController.getAndroidAutoConnectionStatus();
                            AppInfo appInfoByPackage2 = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage("com.ts.androidauto.app");
                            if (androidAutoConnectionStatus) {
                                DragCardView.this.addCarPlayOrAuto(appInfoByPackage2);
                            }
                            boolean carPlayConnectionStatus = DragCardView.this.mCarController.getCarPlayConnectionStatus();
                            AppInfo appInfoByPackage3 = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage("com.ts.carplay.app");
                            if (carPlayConnectionStatus) {
                                DragCardView.this.addCarPlayOrAuto(appInfoByPackage3);
                            }
                            DragCardView.this.mCarController.addCallback(DragCardView.this.mCarControlCallback);
                        }
                    });
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkReplaceMyCarWidget(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "com.byd.mycar"
            boolean r1 = android.text.TextUtils.equals(r4, r0)
            java.lang.String r2 = "com.byd.mycar.ts"
            if (r1 == 0) goto L14
            android.content.Context r0 = r3.mContext
            boolean r0 = com.byd.card.utils.AppUtils.isInstalled(r0, r2)
            if (r0 == 0) goto L23
            r0 = r2
            goto L24
        L14:
            boolean r1 = android.text.TextUtils.equals(r4, r2)
            if (r1 == 0) goto L23
            android.content.Context r1 = r3.mContext
            boolean r1 = com.byd.card.utils.AppUtils.isInstalled(r1, r0)
            if (r1 == 0) goto L23
            goto L24
        L23:
            r0 = 0
        L24:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L59
            android.content.Context r1 = r3.mContext
            android.appwidget.AppWidgetProviderInfo r1 = r3.getWidgetProvider(r1, r0)
            if (r1 == 0) goto L59
            java.util.concurrent.CopyOnWriteArrayList<java.lang.Object> r2 = r3.mInitData
            r2.add(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "checkReplaceMyCarWidget replace "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r1 = " to "
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "DragCardView"
            android.util.Log.i(r0, r4)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.floatcard.DragCardView.checkReplaceMyCarWidget(java.lang.String):void");
    }

    protected class CPAAControlCallback implements CarController.Callback {
        protected CPAAControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnCarPlay(final boolean connected) {
            Log.w(DragCardView.TAG, "onSessionStatusChangedOnCarPlay connected : " + connected);
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CARPLAY_STATUS));
            final AppInfo appInfoByPackage = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage("com.ts.carplay.app");
            SingleThreadPoolUtils.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.CPAAControlCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    if (connected) {
                        DragCardView.this.addCarPlayOrAuto(appInfoByPackage);
                    } else {
                        DragCardView.this.removeCarPlayOrAuto(appInfoByPackage);
                    }
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnAndroidAuto(final boolean connected) {
            Log.w(DragCardView.TAG, "onSessionStatusChangedOnAndroidAuto connected : " + connected);
            final AppInfo appInfoByPackage = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage("com.ts.androidauto.app");
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.AUTO_STATUS));
            SingleThreadPoolUtils.execute(new Runnable() { // from class: com.byd.card.floatcard.DragCardView.CPAAControlCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    if (connected) {
                        DragCardView.this.addCarPlayOrAuto(appInfoByPackage);
                    } else {
                        DragCardView.this.removeCarPlayOrAuto(appInfoByPackage);
                    }
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onRequestVideoFocusChanged(int requestType) {
            Log.d(DragCardView.TAG, "onRequestVideoFocusChanged requestType : " + requestType);
            if (requestType == 0) {
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
            }
        }
    }

    public boolean isPackageEnabled(String packageName, UserHandle user) {
        return ((LauncherApps) this.mContext.getSystemService(LauncherApps.class)).isPackageEnabled(packageName, user);
    }

    public AppWidgetProviderInfo getWidgetProvider(Context context, String packageName) {
        List<AppWidgetProviderInfo> installedProviders = AppWidgetManager.getInstance(context).getInstalledProviders();
        for (AppWidgetProviderInfo appWidgetProviderInfo : installedProviders) {
            if (packageName.equals(appWidgetProviderInfo.provider.getPackageName())) {
                return appWidgetProviderInfo;
            }
        }
        Log.w(TAG, "getWidgetProvider providerInfo is null! package = " + packageName + ", installedProviders = " + installedProviders);
        return null;
    }

    public List<AppWidgetProviderInfo> getWidgetProviders(Context context, List<ComponentName> componentNameList) {
        List<AppWidgetProviderInfo> installedProviders = AppWidgetManager.getInstance(context).getInstalledProviders();
        AppWidgetProviderInfo[] appWidgetProviderInfoArr = new AppWidgetProviderInfo[componentNameList.size()];
        for (AppWidgetProviderInfo appWidgetProviderInfo : installedProviders) {
            Log.i(TAG, "getWidgetProviders installedProviders providerInfo = " + appWidgetProviderInfo);
            Iterator<ComponentName> it = componentNameList.iterator();
            while (it.hasNext()) {
                if (appWidgetProviderInfo.provider.equals(it.next())) {
                    int iIndexOf = componentNameList.indexOf(appWidgetProviderInfo.provider);
                    appWidgetProviderInfoArr[iIndexOf] = appWidgetProviderInfo;
                    Log.i(TAG, "getWidgetProviders index " + iIndexOf + " providerInfo = " + appWidgetProviderInfo);
                }
            }
        }
        return Arrays.asList(appWidgetProviderInfoArr);
    }

    public void addCardApp(List<AppOrShortcutItem> appInfoList) {
        synchronized (this.mInitData) {
            CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            CopyOnWriteArrayList<AppOrShortcutItem> copyOnWriteArrayList2 = new CopyOnWriteArrayList(appInfoList);
            for (Object obj : data) {
                if (obj instanceof AppInfo) {
                    AppInfo appInfo = (AppInfo) obj;
                    if (copyOnWriteArrayList2.contains(appInfo)) {
                        copyOnWriteArrayList2.remove(appInfo);
                    } else if (!"com.ts.androidauto.app".equals(appInfo.packageName) && !"com.ts.carplay.app".equals(appInfo.packageName)) {
                        copyOnWriteArrayList.add(obj);
                    }
                } else if (obj instanceof ShortcutInfo) {
                    ShortcutInfo shortcutInfo = (ShortcutInfo) obj;
                    if (copyOnWriteArrayList2.contains(shortcutInfo)) {
                        copyOnWriteArrayList2.remove(shortcutInfo);
                    } else {
                        copyOnWriteArrayList.add(obj);
                    }
                }
            }
            data.removeAll(copyOnWriteArrayList);
            for (AppOrShortcutItem appOrShortcutItem : copyOnWriteArrayList2) {
                if (!data.contains(appOrShortcutItem)) {
                    if (data.size() < 2) {
                        data.add(appOrShortcutItem);
                    } else {
                        data.add(data.size() - 1, appOrShortcutItem);
                    }
                }
            }
            setDeleteViewVisibility(false);
            this.mCardAdapter.saveItemToSp();
        }
    }

    @Override // com.byd.card.floatcard.CardAdapter.MyAdapterListener
    public void onAddClick(View view, Object obj) {
        OnAddClickListener onAddClickListener = this.mAddClickListener;
        if (onAddClickListener != null) {
            onAddClickListener.onAddClick(view);
        }
    }

    @Override // com.byd.card.floatcard.CardAdapter.MyAdapterListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        if (!this.mLongClickable || viewHolder == null) {
            return;
        }
        View view = viewHolder.itemView;
        float x = view.getX();
        float y = view.getY();
        MyDragShadowBuilder myDragShadowBuilder = new MyDragShadowBuilder(view, new Point((int) (this.mDragTouchPoint.x - x), (int) (this.mDragTouchPoint.y - y)));
        Point point = new Point();
        Point point2 = new Point();
        myDragShadowBuilder.onProvideShadowMetrics(point, point2);
        if (point.x < 0 || point.y < 0 || point2.x < 0 || point2.y < 0) {
            return;
        }
        view.startDragAndDrop(new ClipData(new ClipDescription("", new String[]{""}), new ClipData.Item("")), myDragShadowBuilder, viewHolder, 512);
    }

    public static class RVItemDecoration extends RecyclerView.ItemDecoration {
        private final Context context;

        public RVItemDecoration(Context context) {
            this.context = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (parent.getAdapter() == null) {
                Log.w(DragCardView.TAG, "getItemOffsets adapter is null");
                return;
            }
            if (AppUtils.isLandSpace(this.context)) {
                if (AppUtils.isRightRudder()) {
                    if (childAdapterPosition == r5.getItemCount() - 1) {
                        outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                        return;
                    } else {
                        if (childAdapterPosition == 0) {
                            outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                            return;
                        }
                        return;
                    }
                }
                if (AppUtils.isRtl(this.context)) {
                    if (childAdapterPosition == r5.getItemCount() - 1) {
                        outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                        return;
                    } else {
                        if (childAdapterPosition == 0) {
                            outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_8);
                            return;
                        }
                        return;
                    }
                }
                if (childAdapterPosition == r5.getItemCount() - 1) {
                    outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                    return;
                } else {
                    if (childAdapterPosition == 0) {
                        outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                        return;
                    }
                    return;
                }
            }
            if (AppUtils.isRightRudder()) {
                if (childAdapterPosition == 0) {
                    outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                    return;
                } else {
                    if (childAdapterPosition == r5.getItemCount() - 1) {
                        outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                        return;
                    }
                    return;
                }
            }
            if (AppUtils.isRtl(this.context)) {
                if (childAdapterPosition == 0) {
                    outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_8);
                    return;
                } else {
                    if (childAdapterPosition == r5.getItemCount() - 1) {
                        outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
                        return;
                    }
                    return;
                }
            }
            if (childAdapterPosition == 0) {
                outRect.left = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
            } else if (childAdapterPosition == r5.getItemCount() - 1) {
                outRect.right = this.context.getResources().getDimensionPixelSize(R.dimen.dp_24);
            }
        }
    }

    public View getMediaCard() {
        View view = null;
        if (this.mRecyclerView != null) {
            synchronized (this.mInitData) {
                CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    Object obj = data.get(i);
                    if ((obj instanceof AppWidgetProviderInfo) && "com.byd.localmusic".equals(((AppWidgetProviderInfo) obj).provider.getPackageName())) {
                        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(i);
                        if (viewHolderFindViewHolderForAdapterPosition != null) {
                            view = viewHolderFindViewHolderForAdapterPosition.itemView;
                        }
                        return view;
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zoomIn(final View view, final int position, final List<Object> data) {
        AnimatorSet animatorSet = new AnimatorSet();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(200L), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(200L));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.floatcard.DragCardView.13
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                DragCardView.this.zoomOut(view, position, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zoomOut(View view, final int position, final List<Object> data) {
        AnimatorSet animatorSet = new AnimatorSet();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(200L), ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(200L));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.floatcard.DragCardView.14
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (position >= data.size()) {
                    Log.w(DragCardView.TAG, "zoomOut position >= data.size");
                    return;
                }
                Object obj = data.get(position);
                if (obj instanceof AppWidgetProviderInfo) {
                    AppWidgetProviderInfo appWidgetProviderInfo = (AppWidgetProviderInfo) obj;
                    if ("com.neusoft.na.navigation".equals(appWidgetProviderInfo.provider.getPackageName()) || "com.huawei.maps.auto.app".equals(appWidgetProviderInfo.provider.getPackageName()) || Constant.WidgetName.AUTO_MAP_FAKE_WIDGET.equals(appWidgetProviderInfo.provider.getClassName())) {
                        ToastUtils.show(DragCardView.this.mContext, DragCardView.this.mContext.getString(R.string.card_not_edit));
                        return;
                    }
                }
                if (obj instanceof AddViewBean) {
                    ToastUtils.show(DragCardView.this.mContext, DragCardView.this.mContext.getString(R.string.card_not_edit));
                    return;
                }
                if (obj instanceof AppInfo) {
                    AppInfo appInfo = (AppInfo) obj;
                    if (Constant.Activity.AUTO_ACTIVITY.equals(appInfo.activity) || Constant.Activity.CARPLAY_ACTIVITY.equals(appInfo.activity)) {
                        ToastUtils.show(DragCardView.this.mContext, DragCardView.this.mContext.getString(R.string.card_not_edit));
                        return;
                    }
                }
                DragCardView.this.onStartDrag(DragCardView.this.mRecyclerView.findViewHolderForAdapterPosition(position));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickApp(View view, AppInfo appInfo) {
        if (appInfo.isForbidden()) {
            ToastUtils.show(this.mContext, R.string.open_app_on_p_gear);
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        initOrientationParams();
        this.mFrameLayout.setVisibility(0);
        this.mWindowManager.updateViewLayout(this.mFrameLayout, this.mLayoutParams);
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
            AppUtils.openApplication(this.mContext, appInfo, null, this.mFrameLayout, view, convertViewToBitmap(view, iArr[0], iArr[1]), 156, 144, ((int) Utilities.dp2Px(this.mContext, 1.0f)) + iArr[0], iArr[1] + ((int) Utilities.dp2Px(this.mContext, 72.0f)), true);
            return;
        }
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
        AppUtils.openApplication(this.mContext, appInfo, null, this.mFrameLayout, view, convertViewToBitmap(view, iArr[0], iArr[1]), 134, 112, iArr[0] - ((int) Utilities.dp2Px(this.mContext, 38.0f)), iArr[1] - ((int) Utilities.dp2Px(this.mContext, 16.0f)), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickShortcut(View view, ShortcutInfo shortcutInfo) {
        if (shortcutInfo.isForbidden()) {
            ToastUtils.show(this.mContext, R.string.open_app_on_p_gear);
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        initOrientationParams();
        this.mFrameLayout.setVisibility(0);
        this.mWindowManager.updateViewLayout(this.mFrameLayout, this.mLayoutParams);
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
            AppUtils.openShortcut(this.mContext, shortcutInfo, null, this.mFrameLayout, view, convertViewToBitmap(view, iArr[0], iArr[1]), 156, 144, ((int) Utilities.dp2Px(this.mContext, 1.0f)) + iArr[0], iArr[1] + ((int) Utilities.dp2Px(this.mContext, 72.0f)), true);
            return;
        }
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
        AppUtils.openShortcut(this.mContext, shortcutInfo, null, this.mFrameLayout, view, convertViewToBitmap(view, iArr[0], iArr[1]), 134, 112, iArr[0] - ((int) Utilities.dp2Px(this.mContext, 38.0f)), iArr[1] - ((int) Utilities.dp2Px(this.mContext, 16.0f)), true);
    }

    private void initAnimationViewGrop() {
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.format = 1;
        this.mLayoutParams.flags = 262664;
        this.mLayoutParams.windowAnimations = 0;
        this.mLayoutParams.type = 2009;
        this.mLayoutParams.setTitle("Card Animation Layout");
        this.mFrameLayout = new FrameLayout(this.mContext);
        initOrientationParams();
        this.mWindowManager.addView(this.mFrameLayout, this.mLayoutParams);
        this.mFrameLayout.setVisibility(4);
    }

    private void initOrientationParams() {
        if (this.mContext.getResources().getConfiguration().orientation != 1) {
            this.mLayoutParams.x = (int) Utilities.dp2Px(this.mContext, 69.0f);
            this.mLayoutParams.y = (int) Utilities.dp2Px(this.mContext, 84.0f);
            this.mLayoutParams.width = 1920 - ((int) Utilities.dp2Px(this.mContext, 69.0f));
            this.mLayoutParams.height = 1080 - ((int) Utilities.dp2Px(this.mContext, 140.0f));
            return;
        }
        this.mLayoutParams.x = (int) Utilities.dp2Px(this.mContext, 40.0f);
        this.mLayoutParams.y = (int) Utilities.dp2Px(this.mContext, 84.0f);
        this.mLayoutParams.width = 1080 - ((int) Utilities.dp2Px(this.mContext, 80.0f));
        this.mLayoutParams.height = 1920;
    }

    private Bitmap convertViewToBitmap(View view, int x, int y) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        view.layout(x, y, width, height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        view.draw(canvas);
        canvas.save();
        return bitmapCreateBitmap;
    }

    private void clickAddItem(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(100L), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(100L), ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(100L), ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(100L));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.byd.card.floatcard.DragCardView.15
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                DragCardView.this.mAddClickListener.onAddClick(view);
            }
        });
    }

    private boolean containCPAA() {
        if (this.mCardAdapter == null) {
            return false;
        }
        synchronized (this.mInitData) {
            CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) instanceof AppInfo) {
                    String packageName = ((AppInfo) data.get(i)).getPackageName();
                    if ("com.ts.carplay.app".equals(packageName) || "com.ts.androidauto.app".equals(packageName)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void setAddBtnStatus(boolean show) {
        Log.d(TAG, "setAddBtnStatus show = " + show);
        boolean z = !show;
        this.mEditStatus = z;
        CardAdapter cardAdapter = this.mCardAdapter;
        if (cardAdapter != null) {
            cardAdapter.setEditStatus(z);
            CopyOnWriteArrayList<Object> data = this.mCardAdapter.getData();
            if (show) {
                Iterator<Object> it = data.iterator();
                while (it.hasNext()) {
                    if (it.next() instanceof AddViewBean) {
                        return;
                    }
                }
                data.add(data.size(), new AddViewBean());
                this.mCardAdapter.notifyItemInserted(data.size());
                this.mRecyclerView.removeItemDecoration(this.mItemDecoration);
                this.mRecyclerView.addItemDecoration(this.mItemDecoration);
                this.mRecyclerView.scrollToPosition(data.size() - 1);
                return;
            }
            Iterator<Object> it2 = data.iterator();
            while (it2.hasNext()) {
                if (it2.next() instanceof AddViewBean) {
                    data.remove(data.size() - 1);
                    this.mCardAdapter.notifyDataSetChanged();
                    this.mRecyclerView.scrollToPosition(data.size() - 1);
                    return;
                }
            }
        }
    }
}
