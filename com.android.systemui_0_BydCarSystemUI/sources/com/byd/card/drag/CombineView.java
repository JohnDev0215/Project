package com.byd.card.drag;

import android.animation.ValueAnimator;
import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.systemui.R;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.bean.Folder;
import com.byd.card.bean.MessageEvent;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.callback.SoftKeyBoardListener;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.AppContentView;
import com.byd.card.drag.IconMover;
import com.byd.card.floatcard.DBUtils;
import com.byd.card.floatcard.Executors;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.utils.CarPlayAndAutoManager;
import com.byd.card.utils.DensityUtil;
import com.byd.card.view.activity.MoreAppActivity;
import com.byd.card.view.broadcast.AppInstallBroadcastReceiver;
import com.byd.card.view.dialog.FolderAddAppDialog;
import com.byd.card.view.dialog.UninstallDialog;
import com.byd.card.widget.BlurView;
import com.byd.card.widget.IconPopWindow;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ScreenUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class CombineView {
    private static final int DELAY_DEFAULT = 50;
    private static final int DELAY_PER = 50;
    private static final String TAG = "combineView";
    private float downSlideY;
    private FolderAddAppDialog folderAddAppDialog;
    private Handler handler;
    private FolderScrollView mAppScrollView;
    private Vector<ApplicationInfo[]> mApps;
    private RelativeLayout mContainer;
    private Context mContext;
    private int mFolderLayoutBgHeight;
    private int mFolderLayoutBgWidth;
    private FolderView mFolderView;
    private GestureDetector mGestureDetector;
    private IconMover mIconMover;
    private LayoutCalculator mLayoutCalculator;
    private ObjectPool mObjectPool;
    private IconPopWindow mPopWindow;
    private int mScreenHeight;
    private int mScreenWidth;
    private ApplicationInfo mSelectApp;
    private RelativeLayout mTouchController;
    private UninstallDialog mUninstallDialog;
    private ViewGroup mViewGroup;
    private float moveHeight;
    private float preTouchY;
    private int previousY;
    private LinearLayout scrollView;
    private int startY;
    private float touchSlop;
    private TextView mAppText = null;
    private LinearLayout mAppStore = null;
    private List<ApplicationInfo> mAllItemList = new ArrayList();
    private int mSelectedPageIndex = 1;
    private HitResult mHitResultTwo = new HitResult();
    private HitResult mHitResultThree = new HitResult();
    private boolean mNeedUpload = false;
    private OnLayoutReady mOnLayoutReady = null;
    private AppContentView mAppContentView = null;
    private BlurView mConstraintLayout = null;
    private int mWindowWidth = 0;
    private MoreAppActivity mView = null;
    private BlurView mFolderLayoutBg = null;
    private RelativeLayout mFolderLayout = null;
    private CancellableQueueTimer mCancelEditDetacher = null;
    private CancellableQueueTimer mCancelFolderEditDetacher = null;
    private final int FOLDER_SCROLL_LEFT = 660;
    private final int PAGE_SCROLL_LEFT = -1;
    private final int PAGE_SCROLL_RIGHT = 1;
    private final int APP_ICON_MOVE = 0;
    private boolean mIsOpenFolder = false;
    private VelocityTracker mVelocityTracker = null;
    private boolean isLongPress = false;
    private final int FOLDER_NO_MOVE = 0;
    private final int FOLDER_MOVE_LEFT = 1;
    private final int FOLDER_MOVE_RIGHT = 2;
    private int mMoveType = 0;
    private boolean appMoveAnimationEnd = false;
    private Animation.AnimationListener mAppMoveAnimationListener = new Animation.AnimationListener() { // from class: com.byd.card.drag.CombineView.1
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            CombineView.this.appMoveAnimationEnd = false;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            new CancellableQueueTimer(CombineView.this.handler, CombineView.this.appCount * 50, new Runnable() { // from class: com.byd.card.drag.CombineView.1.1
                @Override // java.lang.Runnable
                public void run() {
                    CombineView.this.appMoveAnimationEnd = true;
                    if (!CombineView.this.mIsOpenFolder || CombineView.this.mIconMover.isMoving()) {
                        return;
                    }
                    CombineView.this.mFolderView.folderUnJiggle();
                }
            });
        }
    };
    int appCount = 0;
    private AppMoveListener mAppMoveListener = new AppMoveListener() { // from class: com.byd.card.drag.CombineView.2
        @Override // com.byd.card.drag.CombineView.AppMoveListener
        public void onAppMove(int appCnt) {
            CombineView.this.appMoveAnimationEnd = false;
            CombineView.this.appCount = appCnt;
        }
    };
    private boolean enterAnimationComplete = false;
    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() { // from class: com.byd.card.drag.CombineView.6
        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent ev) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent e) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent e) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (CombineView.this.mFolderView == null) {
                return false;
            }
            CombineView.this.mFolderView.cancelScrollAnimation();
            CombineView.this.mFolderView.getScrollView().smoothScrollBy((int) (velocityX * 0.9f), 0);
            CombineView.this.mMoveType = e2.getX() - e1.getX() < 0.0f ? 1 : 2;
            return true;
        }
    };
    boolean isFolderScrollEnd = true;
    private Runnable checkFolderScrollRunnable = new Runnable() { // from class: com.byd.card.drag.CombineView.7
        @Override // java.lang.Runnable
        public void run() {
            CombineView.this.isFolderScrollEnd = true;
        }
    };
    private View.OnTouchListener scrollContainer_OnTouch = new AnonymousClass8();
    private JiggleModeActivator jma = new JiggleModeActivator() { // from class: com.byd.card.drag.CombineView.11
        @Override // com.byd.card.drag.JiggleModeActivator
        public boolean isJigglable() {
            int scrollX = CombineView.this.scrollView.getScrollX();
            return scrollX >= CombineView.this.mScreenWidth && scrollX % CombineView.this.mScreenWidth == 0;
        }

        @Override // com.byd.card.drag.JiggleModeActivator
        public void jiggle() {
            setState(2);
            if (CombineView.this.mFolderView != null) {
                CombineView.this.mFolderView.folderJiggle();
                return;
            }
            if (CombineView.this.mAppContentView != null) {
                CombineView.this.mAppContentView.jiggle();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) CombineView.this.mAppContentView.getLayoutParams();
                CombineView.this.mAppContentView.setAppInfo(CombineView.this.mAllItemList);
                layoutParams.height = CombineView.this.mAppContentView.getViewHeight();
                CombineView.this.mAppContentView.setLayoutParams(layoutParams);
            }
        }

        @Override // com.byd.card.drag.JiggleModeActivator
        public void unjiggle() {
            setState(0);
            if (CombineView.this.mAppContentView != null) {
                CombineView.this.mAppContentView.unJiggle();
            }
        }
    };
    private boolean mFolderViewAnimating = false;
    boolean isIconMoving = false;
    boolean folderViewClearUp = true;
    private Runnable checkIconMovingRunnable = new Runnable() { // from class: com.byd.card.drag.CombineView.14
        @Override // java.lang.Runnable
        public void run() {
            CombineView combineView = CombineView.this;
            combineView.isIconMoving = combineView.mIconMover.isMoving();
        }
    };
    private Runnable folderViewClearUpRunnable = new Runnable() { // from class: com.byd.card.drag.CombineView.15
        @Override // java.lang.Runnable
        public void run() {
            if (CombineView.this.mFolderView != null && (!CombineView.this.jma.isJiggling() || !CombineView.this.mIconMover.isMoving())) {
                CombineView.this.mFolderView.folderUnJiggle();
            }
            CombineView.this.folderViewClearUp = false;
        }
    };
    private Controller controller = new Controller() { // from class: com.byd.card.drag.CombineView.17
        @Override // com.byd.card.drag.Controller
        public void initData(final List<ApplicationInfo> list) {
            new AsyncTask<String, String, String>() { // from class: com.byd.card.drag.CombineView.17.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public String doInBackground(String... params) {
                    ArrayList arrayList = new ArrayList();
                    for (ApplicationInfo applicationInfo : CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps()) {
                        if (!"com.byd.auto_camera".equals(applicationInfo.mPackageName) || AppUtils.isShowCamera()) {
                            if (!"com.byd.auto_photo".equals(applicationInfo.mPackageName) || !Constant.HONG_KONG_CODE.equals(AppUtils.getCountryCode())) {
                                if ("com.byd.avm".equals(applicationInfo.mPackageName)) {
                                    String str = SystemProperties.get(Constant.AVM_ISP, "-1");
                                    String str2 = SystemProperties.get(Constant.AVM_NAME, "-1");
                                    Log.w(CombineView.TAG, "avmIsp = " + str + ", avmName = " + str2);
                                    if ("-1".equals(str) || "-1".equals(str2)) {
                                    }
                                }
                                if (!arrayList.contains(Integer.valueOf(applicationInfo.folderId))) {
                                    if (applicationInfo.folderId == 0) {
                                        list.add(CombineView.this.turnAppBean(applicationInfo));
                                    } else {
                                        List<ApplicationInfo> listByFolderId = CardDatabase.getInstance().getWorkSpaceItemDao().getListByFolderId(applicationInfo.folderId);
                                        Folder folderById = CardDatabase.getInstance().getFolderDao().getFolderById(applicationInfo.folderId);
                                        ArrayList arrayList2 = new ArrayList();
                                        Iterator<ApplicationInfo> it = listByFolderId.iterator();
                                        while (it.hasNext()) {
                                            arrayList2.add(CombineView.this.turnAppBean(it.next()));
                                        }
                                        MoreAppFolderInfo moreAppFolderInfo = new MoreAppFolderInfo();
                                        moreAppFolderInfo.folderId = applicationInfo.folderId;
                                        moreAppFolderInfo.setIndex("1");
                                        moreAppFolderInfo.setIcons(arrayList2);
                                        if (folderById != null) {
                                            if (folderById.title.equals(Constant.FolderType.DEFAULT_FOLDER)) {
                                                folderById.title = CombineView.this.mContext.getString(R.string.folder_not_name);
                                            }
                                            if (folderById.title.equals(Constant.FolderType.TOOLS_FOLDER)) {
                                                folderById.title = CombineView.this.mContext.getString(R.string.folder_name_utils);
                                            }
                                            moreAppFolderInfo.setTitle(folderById.getTitle());
                                        }
                                        list.add(moreAppFolderInfo);
                                        arrayList.add(Integer.valueOf(applicationInfo.folderId));
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String result) {
                    CombineView.this.loaded();
                    CombineView.this.updateSlide();
                }
            }.execute("");
        }

        @Override // com.byd.card.drag.Controller
        public void onAppClick(ApplicationInfo app, View view) {
            if (app == null) {
                BydLogHelper.logE(CombineView.TAG, "onAppClick app is null");
                return;
            }
            if (app.getIndex().equals("-1")) {
                final MoreAppFolderInfo info = CombineView.this.mFolderView.getInfo();
                if (info != null) {
                    if (CombineView.this.folderAddAppDialog != null) {
                        CombineView.this.folderAddAppDialog = null;
                    }
                    int uiStyle = UiStyleConfig.getInstance(CombineView.this.mContext).getUiStyle();
                    if (uiStyle != 1) {
                        if (uiStyle != 2) {
                            if (AppUtils.isRightRudder()) {
                                CombineView.this.folderAddAppDialog = new FolderAddAppDialog(CombineView.this.mView, R.layout.dialog_folder_add_app_right, info);
                            } else {
                                CombineView.this.folderAddAppDialog = new FolderAddAppDialog(CombineView.this.mView, R.layout.dialog_folder_add_app, info);
                            }
                        }
                    } else if (AppUtils.isRightRudder()) {
                        CombineView.this.folderAddAppDialog = new FolderAddAppDialog(CombineView.this.mView, R.layout.denza_dialog_folder_add_app_right, info);
                    } else {
                        CombineView.this.folderAddAppDialog = new FolderAddAppDialog(CombineView.this.mView, R.layout.denza_dialog_folder_add_app, info);
                    }
                    CombineView.this.folderAddAppDialog.setAppInfoList(info.getIcons());
                    CombineView.this.folderAddAppDialog.setOnCheckedChangedListener(new FolderAddAppDialog.OnCheckedChangedListener() { // from class: com.byd.card.drag.CombineView.17.2
                        @Override // com.byd.card.view.dialog.FolderAddAppDialog.OnCheckedChangedListener
                        public void onCheckedChanged(List<ApplicationInfo> checkList, List<ApplicationInfo> list) {
                            info.setIcons(checkList);
                            CombineView.this.mFolderView.setInfo(info);
                            CombineView.this.mFolderView.loaded();
                            CombineView.this.mFolderView.showAddAppIcon();
                            CombineView.this.mFolderView.smoothScrollToCurrent(CombineView.this.mFolderView.getPageCount());
                            CombineView.this.updateListUI(checkList, list);
                            DBUtils.getInstance().updateAllList(checkList, CombineView.this.folderAddAppDialog.getCancelList(), info);
                        }
                    });
                    CombineView.this.folderAddAppDialog.show();
                    return;
                }
                BydLogHelper.logE("onAppClick folderInfo is null");
                return;
            }
            CombineView combineView = CombineView.this;
            combineView.openApp(app, combineView.mViewGroup, view);
        }
    };
    public Runnable cancelEditDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.21
        @Override // java.lang.Runnable
        public void run() {
            if (CombineView.this.jma.isJiggling()) {
                CombineView.this.jma.unjiggle();
            }
        }
    };
    public Runnable cancelFolderEditDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.22
        @Override // java.lang.Runnable
        public void run() {
            if (CombineView.this.mFolderView == null || !CombineView.this.mFolderView.isFolderJigglabel()) {
                return;
            }
            CombineView.this.mFolderView.folderUnJiggle();
        }
    };
    private Rect topRect = new Rect();
    private int currentY = 0;
    private int deltaY = 0;
    private boolean startScroll = false;

    public interface AppMoveListener {
        void onAppMove(int appCnt);
    }

    public void uninstallShortcuts() {
    }

    public CombineView(Context context) {
        this.mContext = context;
        if (AppUtils.isLandSpace(context)) {
            this.mFolderLayoutBgWidth = (int) context.getResources().getDimension(R.dimen.dp_1232);
            this.mFolderLayoutBgHeight = (int) context.getResources().getDimension(R.dimen.dp_570);
        } else {
            this.mFolderLayoutBgWidth = (int) context.getResources().getDimension(R.dimen.dp_672);
            this.mFolderLayoutBgHeight = (int) context.getResources().getDimension(R.dimen.dp_1128);
        }
    }

    public void updateForbidden(boolean isPGear) {
        this.mAppContentView.updateForbiddenApp(isPGear);
        FolderView folderView = this.mFolderView;
        if (folderView == null || !this.mIsOpenFolder) {
            return;
        }
        folderView.invalidateView();
    }

    public void initView(MoreAppActivity view, ViewGroup viewGroup, int windowWidth) {
        this.mView = view;
        this.scrollView = (LinearLayout) view.findViewById(R.id.container);
        this.mAppText = (TextView) view.findViewById(R.id.my_app);
        this.mAppStore = (LinearLayout) view.findViewById(R.id.app_market_btn);
        this.mConstraintLayout = (BlurView) view.findViewById(R.id.id_ConstraintLayout);
        this.mContainer = (RelativeLayout) view.findViewById(R.id.springboard_container);
        this.mGestureDetector = new GestureDetector(this.mContext, this.gestureListener);
        this.mAppScrollView = (FolderScrollView) view.findViewById(R.id.app_scrollview);
        this.mContainer.setOnTouchListener(this.scrollContainer_OnTouch);
        this.mLayoutCalculator = new LayoutCalculator(this.mContext);
        this.mObjectPool = new ObjectPool(this.mContext, this.mLayoutCalculator);
        BlurView blurView = (BlurView) view.findViewById(R.id.blur_folder_layout);
        this.mFolderLayoutBg = blurView;
        blurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_16));
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.folder_layout);
        this.mFolderLayout = relativeLayout;
        this.mViewGroup = viewGroup;
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.drag.CombineView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (CombineView.this.mFolderView != null) {
                    int translateTop = AppUtils.isLandSpace(CombineView.this.mContext) ? CombineView.this.mFolderView.getTranslateTop() : (int) (CombineView.this.mFolderView.getTranslateTop() + CombineView.this.mContext.getResources().getDimension(R.dimen.dp_280));
                    if (event.getX() < CombineView.this.mFolderView.getTranslateLeft() || event.getY() < translateTop || event.getX() > CombineView.this.mFolderView.getScrollView().getWidth() + CombineView.this.mFolderView.getTranslateLeft() || event.getY() > CombineView.this.mFolderView.getScrollView().getHeight() + translateTop) {
                        if (event.getAction() == 1) {
                            if (CombineView.this.mFolderView.folderNameIsFocus()) {
                                CombineView.this.mFolderView.closeInputSoft();
                            } else {
                                CombineView.this.closeFolder();
                                CombineView.this.mFolderView.setFolderNameClickable(false);
                                CombineView.this.mConstraintLayout.setOnTouchListener(null);
                            }
                        }
                        return true;
                    }
                }
                return event.getAction() == 2;
            }
        });
        this.handler = new Handler();
        this.touchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        this.mIconMover = new IconMover(this.mContext, view, this.mLayoutCalculator, this.mObjectPool, this.handler, this.mConstraintLayout.getWidth() - this.mContainer.getWidth());
        this.mOnLayoutReady = new OnLayoutReady();
        AppContentView appContentView = new AppContentView(this.mContext);
        this.mAppContentView = appContentView;
        appContentView.setOnUpdateAppContentLayoutListener(new AppContentView.OnUpdateAppContentLayoutListener() { // from class: com.byd.card.drag.CombineView.4
            @Override // com.byd.card.drag.AppContentView.OnUpdateAppContentLayoutListener
            public void onHeightChanged() {
                CombineView.this.updateScrollHeight();
            }
        });
        this.mAppContentView.setAppMoveAnimationListener(this.mAppMoveAnimationListener);
        this.mAppContentView.setAppMoveListener(this.mAppMoveListener);
        this.scrollView.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnLayoutReady);
        this.mWindowWidth = windowWidth;
        this.enterAnimationComplete = false;
        this.mPopWindow = new IconPopWindow(this.mView);
    }

    public void setEnterAnimationComplete(boolean end) {
        this.enterAnimationComplete = end;
    }

    private class OnLayoutReady implements ViewTreeObserver.OnGlobalLayoutListener {
        private OnLayoutReady() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            CombineView.this.scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            CombineView.this.layoutReady();
            CombineView.this.setupScrollView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void layoutReady() {
        this.mApps = new Vector<>();
        this.mScreenWidth = this.mContainer.getWidth();
        this.mScreenHeight = this.mContainer.getHeight();
        this.mIconMover.setAppWidth(this.mWindowWidth - this.mScreenWidth, this.mContainer.getTop());
        this.mLayoutCalculator.layoutReady(this.mContainer);
        this.mLayoutCalculator.setMoreAppLeft(this.mContainer.getLeft());
        this.mLayoutCalculator.setMoreAppRight(this.mContainer.getRight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupScrollView() {
        loaded();
        this.mAllItemList.clear();
        this.controller.initData(this.mAllItemList);
    }

    public void loaded() {
        this.mApps.clear();
        this.scrollView.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mScreenWidth, -1);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setBackgroundDrawable(null);
        this.scrollView.addView(linearLayout, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.mContext);
        linearLayout2.setBackgroundDrawable(null);
        this.scrollView.addView(linearLayout2, layoutParams);
        this.mAppContentView.init(this.mLayoutCalculator, this.mObjectPool);
        this.mAppContentView.setAppInfo(this.mAllItemList);
        layoutParams.height = this.mAppContentView.getViewHeight();
        this.scrollView.addView(this.mAppContentView, r1.getChildCount() - 1, layoutParams);
        this.mContainer.post(new Runnable() { // from class: com.byd.card.drag.CombineView.5
            @Override // java.lang.Runnable
            public void run() {
                CombineView.this.scrollView.scrollTo(CombineView.this.mScreenWidth, 0);
                CombineView.this.mContainer.setVisibility(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToCurrent(int index) {
        this.mAppScrollView.smoothScrollTo(0, index * this.mScreenHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double getDistance(float x1, float y1, float x2, float y2) {
        float f = x2 - x1;
        float f2 = y2 - y1;
        return Math.sqrt((f * f) + (f2 * f2));
    }

    public void stop() {
        BlurView blurView = this.mConstraintLayout;
        if (blurView != null) {
            blurView.setOnTouchListener(null);
        }
        closeFolder();
        if (this.jma.isJiggling()) {
            this.jma.unjiggle();
        }
        this.mSelectedPageIndex = 1;
        CancellableQueueTimer cancellableQueueTimer = this.mCancelEditDetacher;
        if (cancellableQueueTimer != null) {
            cancellableQueueTimer.cancel();
        }
        CancellableQueueTimer cancellableQueueTimer2 = this.mCancelFolderEditDetacher;
        if (cancellableQueueTimer2 != null) {
            cancellableQueueTimer2.cancel();
        }
    }

    public void backPressed() {
        if (this.jma.isJiggling()) {
            this.jma.unjiggle();
        } else {
            FolderView folderView = this.mFolderView;
            if (folderView != null) {
                if (folderView.isFolderJigglabel()) {
                    this.mFolderView.folderUnJiggle();
                }
                this.mConstraintLayout.setOnTouchListener(null);
                closeFolder();
            }
        }
        CancellableQueueTimer cancellableQueueTimer = this.mCancelEditDetacher;
        if (cancellableQueueTimer != null) {
            cancellableQueueTimer.cancel();
        }
        CancellableQueueTimer cancellableQueueTimer2 = this.mCancelFolderEditDetacher;
        if (cancellableQueueTimer2 != null) {
            cancellableQueueTimer2.cancel();
        }
    }

    /* JADX INFO: renamed from: com.byd.card.drag.CombineView$8, reason: invalid class name */
    class AnonymousClass8 implements View.OnTouchListener {
        IPageView currentPage;
        private PageScrollView folderScrollView;
        boolean gdIntercept;
        private CancellableQueueTimer jiggleModeWaiter;
        private CancellableQueueTimer moveIconWaiter;
        private CancellableQueueTimer moveIntoFolderWaiter;
        private CancellableQueueTimer moveToDesktopWaiter;
        private CancellableQueueTimer moveToScrollWaiter;
        private int scrollPointX;
        private int scrollPointY;
        private CancellableQueueTimer startDragWaiter;
        private float x;
        private float y;
        private int startIndex = -1;
        private HitResult oldHitTest2 = new HitResult();
        private boolean isFolderActionDown = false;
        private boolean isDesktopActionDown = false;
        private boolean mFolderToDesk = false;
        private boolean mDeskToFolder = false;
        private int mMoreAppX = 0;
        private int mMoreAppY = 0;
        private int mFolderAppX = 0;
        private int mFolderAppY = 0;
        private float mOpenFolderX = 0.0f;
        private float mOpenFolderY = 0.0f;
        private Runnable jiggleDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.1
            @Override // java.lang.Runnable
            public void run() {
                if (CombineView.this.mFolderView != null) {
                    CombineView.this.mFolderView.folderJiggle();
                    IPageView page = CombineView.this.getPage();
                    if (page != null) {
                        CombineView.this.startLongClickAnimation(page.getSelectedApp(), false);
                        CombineView.this.appMoveAnimationEnd = true;
                        CombineView.this.showAppInfoPopup(CombineView.this.mFolderView.getFolderBoardView(), page);
                        CombineView.this.mSelectApp = page.getSelectedApp();
                    }
                } else {
                    ApplicationInfo selectedApp = CombineView.this.mAppContentView.getSelectedApp();
                    CombineView.this.mSelectApp = selectedApp;
                    CombineView.this.startLongClickAnimation(selectedApp, true);
                    if (selectedApp != null && !(selectedApp instanceof MoreAppFolderInfo)) {
                        CombineView.this.appMoveAnimationEnd = true;
                        CombineView.this.showAppInfoPopup(CombineView.this.mAppScrollView, AnonymousClass8.this.y);
                    }
                    CombineView.this.jma.jiggle();
                }
                CombineView.this.appCount = 0;
                CombineView.this.isLongPress = true;
            }
        };
        private Runnable scrollToLeftDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.2
            @Override // java.lang.Runnable
            public void run() {
                if (CombineView.this.mFolderView != null) {
                    CombineView.this.isFolderScrollEnd = true;
                    CombineView.this.mSelectedPageIndex = CombineView.this.mFolderView.scrollToLeft();
                    if (AnonymousClass8.this.currentPage.getSelectedIndex() != 0) {
                        AnonymousClass8.this.currentPage.setMoveTo(0);
                    }
                    CombineView.this.mFolderView.iconToLeftPage();
                }
            }
        };
        private Runnable scrollToRightDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.3
            @Override // java.lang.Runnable
            public void run() {
                if (CombineView.this.mFolderView != null) {
                    CombineView.this.isFolderScrollEnd = false;
                    CombineView.this.mFolderView.iconToRightPage();
                    CombineView.this.mSelectedPageIndex = CombineView.this.mFolderView.scrollToRight();
                    CombineView.this.handler.postDelayed(CombineView.this.checkFolderScrollRunnable, 500L);
                    if (AnonymousClass8.this.currentPage.getSelectedIndex() != AnonymousClass8.this.currentPage.getIconsCount() - 1) {
                        AnonymousClass8.this.currentPage.setMoveTo(AnonymousClass8.this.currentPage.getIconsCount() - 1);
                    }
                }
            }
        };
        private Runnable moveIconDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.4
            HitResult oldHitTest = new HitResult();

            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass8.this.oldHitTest2.index = CombineView.this.mHitResultTwo.index;
                AnonymousClass8.this.oldHitTest2.inIcon = CombineView.this.mHitResultTwo.inIcon;
                if (CombineView.this.mHitResultTwo.index < 0) {
                    CombineView.this.mIconMover.setIndex(CombineView.this.mIconMover.getsIndex());
                    CombineView.this.mIconMover.setPageIndex(CombineView.this.mIconMover.getsPageIndex());
                    AnonymousClass8.this.moveIconWaiter = null;
                    return;
                }
                if (this.oldHitTest.index != CombineView.this.mHitResultTwo.index || this.oldHitTest.inIcon != CombineView.this.mHitResultTwo.inIcon) {
                    if (CombineView.this.mIconMover.isAboveFolder()) {
                        CombineView.this.mIconMover.bisideFolder();
                        CombineView.this.mAppContentView.removeFolderBound();
                        if (AnonymousClass8.this.moveIntoFolderWaiter != null) {
                            AnonymousClass8.this.moveIntoFolderWaiter.cancel();
                            AnonymousClass8.this.moveIntoFolderWaiter = null;
                        }
                    }
                    this.oldHitTest.index = CombineView.this.mHitResultTwo.index;
                    this.oldHitTest.inIcon = CombineView.this.mHitResultTwo.inIcon;
                }
                if (!CombineView.this.mHitResultTwo.inIcon) {
                    if (CombineView.this.mIconMover.isAboveFolder()) {
                        CombineView.this.mIconMover.bisideFolder();
                        AnonymousClass8.this.currentPage.removeFolderBound();
                        if (AnonymousClass8.this.moveIntoFolderWaiter != null) {
                            AnonymousClass8.this.moveIntoFolderWaiter.cancel();
                            AnonymousClass8.this.moveIntoFolderWaiter = null;
                        }
                    }
                    if (CombineView.this.mFolderView == null) {
                        if (CombineView.this.mAppContentView.setMoveTo(CombineView.this.mHitResultTwo.index)) {
                            if (CombineView.this.mFolderView != null) {
                                CombineView.this.mFolderView.initLayout();
                            }
                            CombineView.this.mIconMover.setIndex(CombineView.this.mHitResultTwo.index);
                            CombineView.this.mIconMover.setPageIndex(CombineView.this.mSelectedPageIndex);
                        } else {
                            CombineView.this.mIconMover.setIndex(CombineView.this.mIconMover.getsIndex());
                            CombineView.this.mIconMover.setPageIndex(CombineView.this.mIconMover.getsPageIndex());
                        }
                    } else if (AnonymousClass8.this.currentPage == null || !AnonymousClass8.this.currentPage.setMoveTo(CombineView.this.mHitResultTwo.index)) {
                        CombineView.this.mIconMover.setIndex(CombineView.this.mIconMover.getsIndex());
                        CombineView.this.mIconMover.setPageIndex(CombineView.this.mIconMover.getsPageIndex());
                    } else {
                        if (CombineView.this.mFolderView != null) {
                            CombineView.this.mFolderView.initLayout();
                        }
                        CombineView.this.mIconMover.setIndex(CombineView.this.mHitResultTwo.index);
                        CombineView.this.mIconMover.setPageIndex(CombineView.this.mSelectedPageIndex);
                    }
                } else if (CombineView.this.mFolderView != null) {
                    if (!CombineView.this.mIconMover.isAboveFolder()) {
                        CombineView.this.mIconMover.aboveFolder();
                        CombineView.this.mIconMover.setIndex(CombineView.this.mHitResultTwo.index);
                        CombineView.this.mIconMover.setPageIndex(CombineView.this.mSelectedPageIndex);
                        AnonymousClass8.this.currentPage.createFolderBound(CombineView.this.mHitResultTwo.index);
                        if (AnonymousClass8.this.moveIntoFolderWaiter != null) {
                            AnonymousClass8.this.moveIntoFolderWaiter.cancel();
                            AnonymousClass8.this.moveIntoFolderWaiter = null;
                        }
                        AnonymousClass8.this.moveIntoFolderWaiter = new CancellableQueueTimer(CombineView.this.handler, 500, AnonymousClass8.this.moveIntoFolderDetacher);
                    }
                } else if (!CombineView.this.mIconMover.isAboveFolder()) {
                    CombineView.this.mIconMover.aboveFolder();
                    CombineView.this.mIconMover.setIndex(CombineView.this.mHitResultTwo.index);
                    CombineView.this.mIconMover.setPageIndex(CombineView.this.mSelectedPageIndex);
                    CombineView.this.mAppContentView.createFolderBound(CombineView.this.mHitResultTwo.index);
                    if (AnonymousClass8.this.moveIntoFolderWaiter == null) {
                        AnonymousClass8.this.moveIntoFolderWaiter = new CancellableQueueTimer(CombineView.this.handler, 500, AnonymousClass8.this.moveIntoFolderDetacher);
                    }
                }
                AnonymousClass8.this.moveIconWaiter = null;
            }
        };
        private Runnable moveIntoFolderDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.5
            @Override // java.lang.Runnable
            public void run() {
                ApplicationInfo icon;
                if (CombineView.this.mHitResultTwo.index >= 0 && (icon = CombineView.this.mAppContentView.getIcon(CombineView.this.mHitResultTwo.index)) != null && icon.getType() == 2) {
                    MoreAppFolderInfo moreAppFolderInfo = (MoreAppFolderInfo) icon;
                    if (!AnonymousClass8.this.mFolderToDesk) {
                        AnonymousClass8.this.mDeskToFolder = true;
                    }
                    AnonymousClass8.this.mFolderToDesk = false;
                    CombineView.this.openFolder(moreAppFolderInfo, true, true, AnonymousClass8.this.mOpenFolderX, AnonymousClass8.this.mOpenFolderY);
                }
                AnonymousClass8.this.moveIntoFolderWaiter = null;
            }
        };
        private Runnable moveToDesktopDetacher = new Runnable() { // from class: com.byd.card.drag.CombineView.8.6
            @Override // java.lang.Runnable
            public void run() {
                if (CombineView.this.mFolderView != null) {
                    CombineView.this.mFolderView.moveIconDesk();
                }
                CombineView.this.closeFolder();
                if (!AnonymousClass8.this.mDeskToFolder) {
                    AnonymousClass8.this.mFolderToDesk = true;
                }
                AnonymousClass8.this.currentPage.clearUp(null);
            }
        };

        AnonymousClass8() {
        }

        /* JADX WARN: Removed duplicated region for block: B:102:0x026e  */
        /* JADX WARN: Removed duplicated region for block: B:105:0x027b  */
        /* JADX WARN: Removed duplicated region for block: B:145:0x044a  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0263 A[PHI: r10
  0x0263: PHI (r10v103 int) = (r10v99 int), (r10v111 int) binds: [B:96:0x0261, B:93:0x0241] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x0265 A[PHI: r10
  0x0265: PHI (r10v100 int) = (r10v99 int), (r10v111 int) binds: [B:96:0x0261, B:93:0x0241] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouchFolder(android.view.View r10, final android.view.MotionEvent r11) {
            /*
                Method dump skipped, instruction units count: 1213
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.CombineView.AnonymousClass8.onTouchFolder(android.view.View, android.view.MotionEvent):boolean");
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, final MotionEvent ev) {
            CombineView.this.mHitResultTwo.index = -1;
            CombineView.this.mHitResultTwo.inIcon = false;
            if (!CombineView.this.enterAnimationComplete) {
                return false;
            }
            if (CombineView.this.mView != null && SoftKeyBoardListener.isKeyBoardShow(CombineView.this.mView)) {
                InputMethodManager inputMethodManager = (InputMethodManager) CombineView.this.mContext.getSystemService("input_method");
                if (CombineView.this.mView.getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(CombineView.this.mView.getCurrentFocus().getWindowToken(), 0);
                }
            }
            if (CombineView.this.mFolderView == null || !CombineView.this.mIsOpenFolder) {
                CombineView.this.addVelocityTrackerEvent(ev);
                int action = ev.getAction();
                if (action == 0) {
                    CombineView.this.isFolderScrollEnd = true;
                    CombineView.this.mView.cancelTimer();
                    if (CombineView.this.mIconMover.isMoving()) {
                        return false;
                    }
                    CombineView.this.initSlideParam(ev);
                    CombineView.this.isLongPress = false;
                    this.oldHitTest2.index = -1;
                    this.oldHitTest2.inIcon = false;
                    CombineView.this.mIconMover.setAboveFolder(false);
                    this.x = ev.getX();
                    CombineView combineView = CombineView.this;
                    float y = ev.getY();
                    this.y = y;
                    combineView.downSlideY = y;
                    this.scrollPointY = CombineView.this.mAppScrollView.getScrollY();
                    this.startIndex = 1;
                    this.isDesktopActionDown = true;
                    this.isFolderActionDown = false;
                    if (CombineView.this.mAppContentView != null) {
                        CombineView.this.mAppContentView.hitTest3((int) this.x, ((int) this.y) + this.scrollPointY, CombineView.this.mHitResultThree);
                        if (CombineView.this.mHitResultThree.index >= 0) {
                            CombineView.this.mAppContentView.select(CombineView.this.mHitResultThree.index);
                        }
                    }
                    this.gdIntercept = CombineView.this.mGestureDetector.onTouchEvent(ev);
                    if (!CombineView.this.jma.isJiggling() && this.jiggleModeWaiter == null) {
                        this.jiggleModeWaiter = new CancellableQueueTimer(CombineView.this.handler, 500, this.jiggleDetacher);
                    }
                    CombineView.this.preTouchY = ev.getY();
                    return true;
                }
                if (action != 1) {
                    if (action == 2) {
                        if (!this.isDesktopActionDown) {
                            this.x = ev.getX();
                            this.y = ev.getY();
                            this.scrollPointY = CombineView.this.mAppScrollView.getScrollY();
                            this.isFolderActionDown = false;
                            this.isDesktopActionDown = true;
                            CombineView.this.preTouchY = ev.getY();
                        }
                        if (this.gdIntercept) {
                            this.gdIntercept = CombineView.this.mGestureDetector.onTouchEvent(ev);
                        } else if (CombineView.this.jma.isJiggling()) {
                            if (CombineView.this.isLongPress && CombineView.getDistance(ev.getX(), ev.getY(), this.x, this.y) > CombineView.this.touchSlop && CombineView.this.mAppContentView != null) {
                                detachIcon(CombineView.this.mAppContentView, this.startIndex, CombineView.this.mAppContentView.getSelectedIndex(), false);
                            }
                            if (CombineView.getDistance(ev.getX(), ev.getY(), this.x, this.y) > CombineView.this.touchSlop) {
                                CombineView.this.dismissAppInfoPopup();
                                if (CombineView.this.mAppContentView != null) {
                                    CombineView.this.mAppContentView.deselect();
                                }
                                CancellableQueueTimer cancellableQueueTimer = this.startDragWaiter;
                                if (cancellableQueueTimer != null) {
                                    cancellableQueueTimer.cancel();
                                    this.startDragWaiter = null;
                                }
                                if (!CombineView.this.mIconMover.isMoving()) {
                                    CombineView.this.slideAppContentView(ev);
                                } else {
                                    boolean zMoreAppMover = moreAppMover(v, ev);
                                    CombineView.this.preTouchY = ev.getY();
                                    return zMoreAppMover;
                                }
                            }
                        } else {
                            if (CombineView.getDistance(ev.getX(), ev.getY(), this.x, this.y) > CombineView.this.touchSlop) {
                                CancellableQueueTimer cancellableQueueTimer2 = this.jiggleModeWaiter;
                                if (cancellableQueueTimer2 != null) {
                                    cancellableQueueTimer2.cancel();
                                    this.jiggleModeWaiter = null;
                                }
                                if (CombineView.this.mAppContentView != null && CombineView.this.mAppContentView.getSelectedIndex() >= 0) {
                                    CombineView.this.mAppContentView.deselect();
                                }
                            }
                            CombineView.this.slideAppContentView(ev);
                        }
                    } else if (action == 3) {
                        if (CombineView.this.mAppContentView != null) {
                            CombineView.this.mAppContentView.deselect();
                        }
                        if (CombineView.this.mView.isFinishing()) {
                            Log.d(CombineView.TAG, "onTouch ACTION_CANCEL isFinishing!");
                        }
                    }
                    return false;
                }
                CancellableQueueTimer cancellableQueueTimer3 = this.jiggleModeWaiter;
                if (cancellableQueueTimer3 != null) {
                    cancellableQueueTimer3.cancel();
                    this.jiggleModeWaiter = null;
                }
                CancellableQueueTimer cancellableQueueTimer4 = this.moveToScrollWaiter;
                if (cancellableQueueTimer4 != null) {
                    cancellableQueueTimer4.cancel();
                    this.moveToScrollWaiter = null;
                }
                CancellableQueueTimer cancellableQueueTimer5 = this.startDragWaiter;
                if (cancellableQueueTimer5 != null) {
                    cancellableQueueTimer5.cancel();
                    this.startDragWaiter = null;
                }
                this.gdIntercept = CombineView.this.mGestureDetector.onTouchEvent(ev);
                moreAppUp(ev);
                CombineView.this.updateAppContentUI();
                this.startIndex = -1;
                if (!CombineView.this.mIsOpenFolder) {
                    CombineView.this.mView.startAD();
                }
                CombineView.this.endSlideAppContentView(ev);
                return true;
            }
            return onTouchFolder(v, ev);
        }

        private void detachIcon(IPageView page, int pageIndex, int index, boolean isFolder) {
            ApplicationInfo icon;
            if (index >= 0 && (icon = page.getIcon(index)) != null) {
                page.deselect();
                Point iconLocation = page.getIconLocation(index);
                if (!CombineView.this.mIconMover.isMoving()) {
                    if (isFolder) {
                        if (ScreenUtil.isScreenLandscape(CombineView.this.mContext)) {
                            CombineView.this.mIconMover.startMoving(icon, true, index, (iconLocation.x - CombineView.this.mLayoutCalculator.iconWidth) - (CombineView.this.mLayoutCalculator.bcMarginLeft * 2), iconLocation.y - (CombineView.this.mLayoutCalculator.iconHeight / 6), (int) this.x, (int) this.y);
                        } else {
                            CombineView.this.mIconMover.startMoving(icon, true, index, iconLocation.x + CombineView.this.mLayoutCalculator.iconWidth, iconLocation.y - CombineView.this.mLayoutCalculator.iconHeight, (int) this.x, (int) this.y);
                        }
                    } else {
                        CombineView.this.mIconMover.startMoving(icon, false, index, iconLocation.x, iconLocation.y - CombineView.this.mAppScrollView.getScrollY(), (int) this.x, (int) this.y);
                        CombineView.this.mIconMover.setPageIndex(pageIndex);
                        CombineView.this.mIconMover.setsPageIndex(pageIndex);
                    }
                    CombineView.this.mIconMover.setIndex(index);
                    CombineView.this.mIconMover.setsIndex(index);
                    page.setIconIntoPage(index, null);
                    return;
                }
                page.setIconIntoPage(index, null);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0129 A[PHI: r11
  0x0129: PHI (r11v20 int) = (r11v15 int), (r11v137 int) binds: [B:27:0x0113, B:14:0x0071] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean moreAppMover(android.view.View r11, android.view.MotionEvent r12) {
            /*
                Method dump skipped, instruction units count: 1020
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.CombineView.AnonymousClass8.moreAppMover(android.view.View, android.view.MotionEvent):boolean");
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x008e  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x00a2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean folderMover(android.view.MotionEvent r8) {
            /*
                Method dump skipped, instruction units count: 1300
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.CombineView.AnonymousClass8.folderMover(android.view.MotionEvent):boolean");
        }

        private int getCurrentFolderIconCount() {
            MoreAppFolderInfo info;
            List<ApplicationInfo> icons;
            if (CombineView.this.mFolderView == null || (info = CombineView.this.mFolderView.getInfo()) == null || (icons = info.getIcons()) == null) {
                return 0;
            }
            int size = icons.size();
            for (ApplicationInfo applicationInfo : icons) {
                if (applicationInfo != null && CombineView.this.mIconMover.hook() != null && applicationInfo.getPackageName().equals(CombineView.this.mIconMover.hook().getPackageName())) {
                    return size - 1;
                }
            }
            return size;
        }

        private void isToDesk() {
            if (ScreenUtil.isScreenLandscape(CombineView.this.mContext)) {
                if (this.mFolderAppX < CombineView.this.mFolderView.getTranslateLeft() - (CombineView.this.mLayoutCalculator.iconWidth / 4) || this.mFolderAppY < CombineView.this.mFolderView.getTranslateTop() - CombineView.this.mLayoutCalculator.iconHeight || this.mFolderAppX > ((double) (this.folderScrollView.getWidth() + CombineView.this.mFolderView.getTranslateLeft())) + (((double) CombineView.this.mLayoutCalculator.iconWidth) * 1.2d) || this.mFolderAppY > this.folderScrollView.getHeight() + (CombineView.this.mLayoutCalculator.iconHeight / 2)) {
                    if (this.moveToDesktopWaiter == null) {
                        CancellableQueueTimer cancellableQueueTimer = this.moveToScrollWaiter;
                        if (cancellableQueueTimer != null) {
                            cancellableQueueTimer.cancel();
                        }
                        this.moveToDesktopWaiter = new CancellableQueueTimer(CombineView.this.handler, 500, this.moveToDesktopDetacher);
                        return;
                    }
                    return;
                }
                CancellableQueueTimer cancellableQueueTimer2 = this.moveToDesktopWaiter;
                if (cancellableQueueTimer2 != null) {
                    cancellableQueueTimer2.cancel();
                    this.moveToDesktopWaiter = null;
                    return;
                }
                return;
            }
            if (this.mFolderAppX < CombineView.this.mFolderView.getTranslateLeft() || this.mFolderAppY < CombineView.this.mFolderView.getTranslateTop() - (CombineView.this.mLayoutCalculator.iconHeight * 1.5f) || this.mFolderAppX > CombineView.this.mFolderView.getFolderWidth() + CombineView.this.mFolderView.getTranslateLeft() + CombineView.this.mLayoutCalculator.iconWidth || this.mFolderAppY > this.folderScrollView.getHeight() + (CombineView.this.mLayoutCalculator.iconHeight / 2)) {
                if (this.moveToDesktopWaiter == null) {
                    CancellableQueueTimer cancellableQueueTimer3 = this.moveToScrollWaiter;
                    if (cancellableQueueTimer3 != null) {
                        cancellableQueueTimer3.cancel();
                    }
                    this.moveToDesktopWaiter = new CancellableQueueTimer(CombineView.this.handler, 500, this.moveToDesktopDetacher);
                    return;
                }
                return;
            }
            CancellableQueueTimer cancellableQueueTimer4 = this.moveToDesktopWaiter;
            if (cancellableQueueTimer4 != null) {
                cancellableQueueTimer4.cancel();
                this.moveToDesktopWaiter = null;
            }
        }

        private void folderUp(MotionEvent ev) {
            ApplicationInfo selectedApp;
            CombineView.this.mIconMover.showTitle();
            if (CombineView.this.isOverScrolling()) {
                CombineView combineView = CombineView.this;
                combineView.scrollToCurrent(combineView.mSelectedPageIndex);
            }
            IPageView page = CombineView.this.getPage();
            if (page != null) {
                if (page.getSelectedIndex() >= 0 && (selectedApp = page.getSelectedApp()) != null && !CombineView.this.mFolderView.isFolderJigglabel()) {
                    CombineView.this.startClickAnimation(selectedApp, false);
                    CombineView.this.controller.onAppClick(selectedApp, CombineView.this.mFolderView);
                }
                page.deselect();
            }
            if (!this.gdIntercept && !CombineView.this.mIconMover.isMoving() && CombineView.this.mFolderView.isFolderJigglabel()) {
                CombineView.this.mFolderView.showAddAppIcon();
                CombineView.this.mFolderView.folderUnJiggle();
            }
            if (CombineView.this.mIconMover.isMoving()) {
                CombineView.this.mIconMover.hidePanel();
                final IPageView page2 = CombineView.this.getPage();
                if (page2 != null) {
                    Point iconLocation = page2.getIconLocation(CombineView.this.mIconMover.getIndex());
                    final ApplicationInfo applicationInfoHook = CombineView.this.mIconMover.hook();
                    CombineView.this.mIconMover.stopMoving(iconLocation.x + CombineView.this.mFolderView.getTranslateLeft(), (iconLocation.y + CombineView.this.mFolderView.getTranslateTop()) - this.scrollPointY, new IconMover.OnMovingStopped() { // from class: com.byd.card.drag.CombineView.8.7
                        @Override // com.byd.card.drag.IconMover.OnMovingStopped
                        public void movingStopped(ApplicationInfo appInfo) {
                            if (CombineView.this.mFolderView != null && !CombineView.this.isIconMoving && CombineView.this.folderViewClearUp) {
                                if (AppUtils.isRtl(CombineView.this.mContext)) {
                                    CombineView.this.mFolderView.getPage(1).clearUp(applicationInfoHook);
                                } else {
                                    CombineView.this.mFolderView.getPage(CombineView.this.mFolderView.getPageCount()).clearUp(applicationInfoHook);
                                }
                            } else if (!CombineView.this.isFolderScrollEnd) {
                                if (AppUtils.isRtl(CombineView.this.mContext)) {
                                    CombineView.this.mFolderView.getPage(1).clearUp(applicationInfoHook);
                                } else {
                                    CombineView.this.mFolderView.getPage(CombineView.this.mFolderView.getPageCount()).clearUp(applicationInfoHook);
                                }
                            } else {
                                page2.clearUp(applicationInfoHook);
                            }
                            CombineView.this.handler.removeCallbacks(CombineView.this.checkIconMovingRunnable);
                            if (AnonymousClass8.this.mFolderToDesk) {
                                CombineView.this.mAppContentView.invalidate();
                                CombineView.this.updateScrollHeight();
                                CombineView.this.mAppContentView.deletedFolder(applicationInfoHook);
                            } else {
                                CombineView.this.mContainer.setOnTouchListener(null);
                            }
                            CombineView.this.mContainer.setOnTouchListener(null);
                            CombineView.this.mConstraintLayout.setOnTouchListener(CombineView.this.scrollContainer_OnTouch);
                            if (CombineView.this.mFolderView != null) {
                                ArrayList arrayList = new ArrayList();
                                for (int i = 1; i <= CombineView.this.mFolderView.getPageCount(); i++) {
                                    IPageView page3 = CombineView.this.mFolderView.getPage(i);
                                    for (int i2 = 0; i2 < page3.getIconsCount(); i2++) {
                                        arrayList.add(page3.getIcon(i2));
                                    }
                                }
                                CombineView.this.mFolderView.getInfo().setIcons(arrayList);
                                CombineView.this.mFolderView.updateFolderPosition(arrayList);
                                CombineView.this.mFolderView.updateFolderId(arrayList);
                            }
                            DBUtils.getInstance().updatePositionToDB(CombineView.this.mAllItemList, applicationInfoHook);
                            if (CombineView.this.mFolderView != null && CombineView.this.mFolderView.isFolderJigglabel()) {
                                CombineView.this.mFolderView.isAddNewPage();
                                CombineView.this.mFolderView.showAddAppIcon();
                                new CancellableQueueTimer(CombineView.this.handler, ((CombineView.this.appCount == 0 ? CombineView.this.getPage().getIconsCount() : CombineView.this.appCount) * 50) + 50, new Runnable() { // from class: com.byd.card.drag.CombineView.8.7.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (page2.getNeedMoveAnimation()) {
                                            return;
                                        }
                                        if (CombineView.this.mIsOpenFolder) {
                                            CombineView.this.mFolderView.folderUnJiggle();
                                        }
                                        if (CombineView.this.jma.isJiggling()) {
                                            CombineView.this.jma.unjiggle();
                                        }
                                    }
                                });
                            }
                            AnonymousClass8.this.mFolderToDesk = false;
                            AnonymousClass8.this.mDeskToFolder = false;
                        }
                    });
                    return;
                }
                return;
            }
            if (CombineView.this.mFolderView != null) {
                CombineView.this.mFolderView.showAddAppIcon();
            }
            if (CombineView.this.mIconMover != null) {
                CombineView.this.mIconMover.hidePanel();
            }
        }

        private void moreAppUp(MotionEvent ev) {
            if (CombineView.this.mIconMover != null) {
                CombineView.this.mIconMover.showTitle();
            }
            this.isDesktopActionDown = false;
            this.isFolderActionDown = false;
            if (CombineView.this.mAppContentView == null) {
                return;
            }
            if (CombineView.this.mAppContentView.getSelectedIndex() >= 0) {
                ApplicationInfo selectedApp = CombineView.this.mAppContentView.getSelectedApp();
                if (selectedApp != null && !CombineView.this.jma.isJiggling()) {
                    if (selectedApp.getType() == 2) {
                        CombineView.this.startClickAnimation(selectedApp, true);
                        CombineView.this.openFolder((MoreAppFolderInfo) selectedApp, false, false, ev.getRawX(), ev.getRawY());
                    } else {
                        CombineView.this.startClickAnimation(selectedApp, true);
                        CombineView.this.controller.onAppClick(selectedApp, CombineView.this.mAppContentView);
                    }
                } else if (selectedApp != null && selectedApp.getType() == 2) {
                    CombineView.this.openFolder((MoreAppFolderInfo) selectedApp, false, false, ev.getRawX(), ev.getRawY());
                }
            }
            if (!this.gdIntercept && CombineView.this.jma.isJiggling()) {
                CombineView.this.jma.unjiggle();
            }
            CombineView.this.mAppContentView.deselect();
            if (CombineView.this.mIconMover.isMoving()) {
                Point iconLocation = CombineView.this.mAppContentView.getIconLocation(CombineView.this.mIconMover.getIndex());
                final ApplicationInfo applicationInfoHook = CombineView.this.mIconMover.hook();
                final int index = CombineView.this.mIconMover.getIndex();
                if (CombineView.this.mIconMover.isAboveFolder()) {
                    CombineView.this.mIconMover.setAboveFolder(false);
                    CombineView.this.mAppContentView.removeFolderBound();
                    CombineView.this.mIconMover.moveIntoFolder(((CombineView.this.mIconMover.getPageIndex() - CombineView.this.mSelectedPageIndex) * CombineView.this.mScreenWidth) + iconLocation.x, iconLocation.y, new IconMover.OnMovingStopped() { // from class: com.byd.card.drag.CombineView.8.9
                        @Override // com.byd.card.drag.IconMover.OnMovingStopped
                        public void movingStopped(ApplicationInfo appInfo) {
                            CombineView.this.mConstraintLayout.setOnTouchListener(null);
                            CombineView.this.mAppContentView.addToFolder(index, applicationInfoHook);
                            CombineView.this.mAppContentView.updateViewHeight();
                            CombineView.this.updateScrollHeight();
                            if (AnonymousClass8.this.mFolderToDesk) {
                                CombineView.this.mAppContentView.deletedFolder();
                            }
                            AnonymousClass8.this.mFolderToDesk = false;
                            AnonymousClass8.this.mDeskToFolder = false;
                        }
                    });
                } else {
                    CombineView.this.mIconMover.stopMoving(((CombineView.this.mIconMover.getPageIndex() - CombineView.this.mSelectedPageIndex) * CombineView.this.mScreenWidth) + iconLocation.x, iconLocation.y, new IconMover.OnMovingStopped() { // from class: com.byd.card.drag.CombineView.8.8
                        @Override // com.byd.card.drag.IconMover.OnMovingStopped
                        public void movingStopped(ApplicationInfo appInfo) {
                            CombineView.this.mConstraintLayout.setOnTouchListener(null);
                            CombineView.this.mAppContentView.clearUp(applicationInfoHook);
                            CombineView.this.mAppContentView.invalidate();
                            CombineView.this.updateScrollHeight();
                            if (AnonymousClass8.this.mFolderToDesk) {
                                CombineView.this.mAppContentView.deletedFolder(applicationInfoHook);
                                Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.CombineView.8.8.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        CombineView.this.mAppContentView.updateAppFolderId(applicationInfoHook);
                                    }
                                });
                            }
                            AnonymousClass8.this.mFolderToDesk = false;
                            AnonymousClass8.this.mDeskToFolder = false;
                            CombineView.this.mAppContentView.init(CombineView.this.mLayoutCalculator, CombineView.this.mObjectPool);
                            if (CombineView.this.mAllItemList.size() % 4 == 0) {
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) CombineView.this.mAppContentView.getLayoutParams();
                                CombineView.this.mAppContentView.setAppInfo(CombineView.this.mAllItemList);
                                layoutParams.height = CombineView.this.mAppContentView.getViewHeight();
                                CombineView.this.mAppContentView.setLayoutParams(layoutParams);
                            }
                        }
                    });
                }
            }
            DBUtils.getInstance().updatePositionToDB(CombineView.this.mAllItemList, CombineView.this.mIconMover.hook());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startClickAnimation(final ApplicationInfo info, final boolean inMyapp) {
        if (info != null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setFloatValues(1.0f, 0.9f, 1.0f);
            valueAnimator.setDuration(100L);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.card.drag.CombineView.9
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator animation) {
                    info.setIconScale(((Float) animation.getAnimatedValue()).floatValue());
                    if (inMyapp) {
                        if (CombineView.this.mAppContentView != null) {
                            CombineView.this.mAppContentView.invalidate();
                        }
                    } else {
                        IPageView page = CombineView.this.getPage();
                        if (page == null || !(page instanceof SpringBoardPage)) {
                            return;
                        }
                        ((SpringBoardPage) page).invalidate();
                    }
                }
            });
            valueAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLongClickAnimation(final ApplicationInfo info, final boolean inMyapp) {
        if (info != null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setFloatValues(1.0f, 0.7f, 1.0f);
            valueAnimator.setDuration(300L);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.card.drag.CombineView.10
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator animation) {
                    info.setIconScale(((Float) animation.getAnimatedValue()).floatValue());
                    if (inMyapp) {
                        if (CombineView.this.mAppContentView != null) {
                            CombineView.this.mAppContentView.invalidate();
                        }
                    } else {
                        IPageView page = CombineView.this.getPage();
                        if (page == null || !(page instanceof SpringBoardPage)) {
                            return;
                        }
                        ((SpringBoardPage) page).invalidate();
                    }
                }
            });
            valueAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOverScrolling() {
        int scrollX = this.scrollView.getScrollX();
        return scrollX < this.mScreenWidth || scrollX > (this.scrollView.getChildCount() + (-2)) * this.mScreenWidth;
    }

    public IPageView getPage() {
        FolderView folderView = this.mFolderView;
        if (folderView != null) {
            return folderView.getContentView();
        }
        return null;
    }

    public void closeFolder() {
        FolderView folderView = this.mFolderView;
        if (folderView != null) {
            folderView.hideAddAppIcon();
        }
        FolderView folderView2 = this.mFolderView;
        if (folderView2 == null || this.mFolderViewAnimating) {
            return;
        }
        if (folderView2.getPageCount() > 1) {
            this.mFolderView.scrollToCurrent(1);
        }
        if (this.mFolderView.getContentView() != null) {
            this.mFolderView.getContentView().clearUp(null);
        }
        ArrayList arrayList = new ArrayList();
        if (AppUtils.isRtl(this.mContext)) {
            for (int pageCount = this.mFolderView.getPageCount(); pageCount > 0; pageCount--) {
                IPageView page = this.mFolderView.getPage(pageCount);
                for (int i = 0; i < page.getIconsCount(); i++) {
                    arrayList.add(page.getIcon(i));
                }
            }
        } else {
            for (int i2 = 1; i2 <= this.mFolderView.getPageCount(); i2++) {
                IPageView page2 = this.mFolderView.getPage(i2);
                for (int i3 = 0; i3 < page2.getIconsCount(); i3++) {
                    arrayList.add(page2.getIcon(i3));
                }
            }
        }
        if (arrayList.size() == 1) {
            this.mAppContentView.removeFolder((ApplicationInfo) arrayList.get(0));
        } else if (arrayList.size() > 1) {
            this.mFolderView.updateAPPIcon(arrayList);
        }
        Animation animationCreateAnimationCloseFolder = this.mObjectPool.createAnimationCloseFolder(this.mScreenWidth, this.mScreenHeight);
        this.mAppText.setVisibility(0);
        animationCreateAnimationCloseFolder.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.CombineView.12
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                CombineView.this.mFolderView.doneEditingFolderName(true);
                CombineView.this.mFolderViewAnimating = true;
                CombineView.this.mIsOpenFolder = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CombineView.this.mFolderViewAnimating = false;
                CombineView.this.mContainer.setOnTouchListener(CombineView.this.scrollContainer_OnTouch);
                boolean zIsFolderJigglabel = CombineView.this.mFolderView.isFolderJigglabel();
                CombineView.this.mFolderLayout.removeView(CombineView.this.mFolderView);
                CombineView.this.mFolderLayout.setVisibility(8);
                CombineView.this.mFolderLayoutBg.setVisibility(8);
                CombineView.this.mFolderView = null;
                if (zIsFolderJigglabel) {
                    CombineView.this.jma.jiggle();
                }
                CombineView.this.mAppContentView.clearUp(null);
                if (CombineView.this.mCancelFolderEditDetacher != null) {
                    CombineView.this.mCancelFolderEditDetacher.cancel();
                }
            }
        });
        this.mFolderView.startAnimation(animationCreateAnimationCloseFolder);
        Animation animationCreateAnimationPageShow = this.mObjectPool.createAnimationPageShow(true);
        this.mAppContentView.startAnimation(animationCreateAnimationPageShow);
        animationCreateAnimationPageShow.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.CombineView.13
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CombineView.this.mAppContentView.invalidate();
                CombineView.this.updateScrollHeight();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppContentUI() {
        List<ApplicationInfo> icons;
        int i;
        FolderView folderView = this.mFolderView;
        if (folderView == null || (icons = folderView.getInfo().getIcons()) == null) {
            return;
        }
        if (icons.size() == 1) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.mAllItemList.size()) {
                    break;
                }
                ApplicationInfo applicationInfo = this.mAllItemList.get(i2);
                if (applicationInfo != null && applicationInfo.mPackageName != null && icons.get(0).folderId == applicationInfo.folderId) {
                    icons.get(0).folderId = 0;
                    this.mAllItemList.remove(i2);
                    this.mAllItemList.add(i2, icons.get(0));
                    break;
                }
                i2++;
            }
        } else if (icons.size() == 2) {
            return;
        }
        List list = (List) icons.stream().filter(new Predicate() { // from class: com.byd.card.drag.-$$Lambda$CombineView$e0uEEfG7X7gQEZ5KZSrLg9HEPkA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((ApplicationInfo) obj);
            }
        }).collect(Collectors.toList());
        if (list.size() < 3) {
            return;
        }
        ApplicationInfo applicationInfo2 = null;
        int i3 = 0;
        while (true) {
            if (i3 >= list.size()) {
                i = -1;
                break;
            } else {
                if (i3 < list.size() - 1 && ((ApplicationInfo) list.get(i3)).folderId == ((ApplicationInfo) list.get(i3 + 1)).folderId) {
                    i = ((ApplicationInfo) list.get(i3)).folderId;
                    break;
                }
                i3++;
            }
        }
        if (i == -1) {
            applicationInfo2 = (ApplicationInfo) list.get(1);
            applicationInfo2.folderId = ((ApplicationInfo) list.get(0)).folderId;
        } else {
            Iterator it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    ApplicationInfo applicationInfo3 = (ApplicationInfo) it.next();
                    if (applicationInfo3.folderId != i) {
                        applicationInfo3.folderId = i;
                        applicationInfo2 = applicationInfo3;
                        break;
                    }
                }
            }
        }
        for (int i4 = 0; i4 < this.mAllItemList.size(); i4++) {
            ApplicationInfo applicationInfo4 = this.mAllItemList.get(i4);
            if (applicationInfo4 != null && applicationInfo4.activity != null) {
                if (applicationInfo2 == null || applicationInfo2.activity == null) {
                    return;
                }
                if (applicationInfo4.activity.equals(applicationInfo2.activity)) {
                    this.mAllItemList.get(i4).folderId = applicationInfo2.folderId;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openFolder(final MoreAppFolderInfo folderInfo, boolean isMover, final boolean intoFolder, float x, float y) {
        if (this.mFolderView == null) {
            this.isIconMoving = false;
            this.folderViewClearUp = true;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            FolderView folderView = folderInfo.getFolderView(this.mView);
            this.mFolderView = folderView;
            if (!isMover && folderView != null) {
                folderView.playSoundEffect(0);
            }
            this.mFolderLayoutBg.setBlurView(BitmapUtils.getScreenshot1(this.mContext, new Rect((int) this.mContext.getResources().getDimension(R.dimen.dp_24), (int) this.mContext.getResources().getDimension(R.dimen.dp_72), DensityUtil.getScreenWidth(this.mContext) - ((int) this.mContext.getResources().getDimension(R.dimen.dp_24)), DensityUtil.getScreenHeight(this.mContext)), this.mFolderLayoutBgWidth, this.mFolderLayoutBgHeight));
            int uiStyle = UiStyleConfig.getInstance(this.mContext).getUiStyle();
            if (uiStyle == 1) {
                this.mFolderLayoutBg.setMaskBackgroundColor(this.mContext.getColor(R.color.denza_color_mask_bg));
            } else if (uiStyle != 2) {
                this.mFolderLayoutBg.setMaskBackgroundColor(this.mContext.getColor(R.color.color_folder_blur));
            }
            this.mFolderLayoutBg.setMaskAlpha(0.8f);
            this.mFolderLayoutBg.setVisibility(0);
            this.mFolderLayout.setVisibility(0);
            if (this.mFolderView.getParent() == null) {
                this.mFolderLayout.addView(this.mFolderView, layoutParams);
            }
            this.mFolderView.onReady(this.mLayoutCalculator, this.mObjectPool, this.mConstraintLayout.getHeight(), this.mConstraintLayout.getWidth(), folderInfo.getIcons());
            this.mFolderView.startAnimation(this.mObjectPool.createAnimationOpenFolder((int) x, (int) y, this.mConstraintLayout.getWidth(), this.mConstraintLayout.getHeight()));
            if (!this.mIconMover.isMoving()) {
                this.mContainer.setOnTouchListener(null);
            }
            this.mConstraintLayout.setOnTouchListener(this.scrollContainer_OnTouch);
            Animation animationCreateAnimationPageShow = this.mObjectPool.createAnimationPageShow(false);
            if (this.mFolderView.getPageCount() > 1 && isMover && !intoFolder) {
                this.mFolderView.scrollToCurrent(1);
            }
            animationCreateAnimationPageShow.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.CombineView.16
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CombineView.this.mFolderView.setFolderNameClickable(true);
                    if (intoFolder) {
                        CombineView.this.mFolderView.moveIntoFolder(folderInfo, CombineView.this.mSelectApp);
                    } else {
                        CombineView.this.mContainer.setOnTouchListener(null);
                    }
                    if (!CombineView.this.mIconMover.isMoving()) {
                        CombineView.this.mFolderView.showAddAppIcon();
                    }
                    if (!CombineView.this.jma.isJiggling() || !CombineView.this.mIconMover.isMoving()) {
                        CombineView.this.mFolderView.folderUnJiggle();
                    } else {
                        CombineView.this.mFolderView.folderJiggle();
                    }
                    CombineView.this.mAppText.setVisibility(4);
                    CombineView.this.mAppStore.setVisibility(8);
                    CombineView.this.mAppContentView.clearUp(null);
                }
            });
            this.mAppContentView.startAnimation(animationCreateAnimationPageShow);
            this.mAppContentView.removeFolderBound();
            this.mFolderView.setAppMoveAnimationListener(this.mAppMoveAnimationListener);
            this.mFolderView.setAppMoveListener(this.mAppMoveListener);
            this.mIsOpenFolder = true;
            if (!this.mIconMover.isMoving()) {
                this.mFolderView.showAddAppIcon();
            }
            this.handler.postDelayed(this.checkIconMovingRunnable, 1000L);
            this.handler.postDelayed(this.folderViewClearUpRunnable, 350L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListUI(List<ApplicationInfo> checkList, List<ApplicationInfo> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<ApplicationInfo> it = this.mAllItemList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().activity);
        }
        if (checkList.size() >= 1) {
            for (ApplicationInfo applicationInfo : checkList) {
                if (arrayList.contains(applicationInfo.activity)) {
                    arrayList2.add(this.mAllItemList.get(arrayList.indexOf(applicationInfo.activity)));
                }
            }
        }
        this.mAllItemList.removeAll(arrayList2);
        for (ApplicationInfo applicationInfo2 : list) {
            if (!arrayList.contains(applicationInfo2.activity)) {
                this.mAllItemList.add(applicationInfo2);
            }
        }
        for (int i = 0; i < this.mAllItemList.size(); i++) {
            ApplicationInfo applicationInfo3 = this.mAllItemList.get(i);
            if (applicationInfo3 != null && applicationInfo3.getType() == 2) {
                List<ApplicationInfo> icons = ((MoreAppFolderInfo) applicationInfo3).getIcons();
                if (icons.size() == 1) {
                    this.mAppContentView.deletedFolder(icons.get(0));
                    closeFolder();
                } else if (icons.size() == 0) {
                    this.mAppContentView.deletedFolder(null);
                    closeFolder();
                } else {
                    this.mAppContentView.clearUp(null);
                }
            }
        }
        this.mAppContentView.setAppInfo(this.mAllItemList);
        this.mAppContentView.invalidate();
        updateScrollHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScrollHeight() {
        for (int i = 0; i < this.scrollView.getChildCount(); i++) {
            View childAt = this.scrollView.getChildAt(i);
            if (childAt instanceof LinearLayout) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                layoutParams.height = this.mAppContentView.getViewHeight();
                childAt.setLayoutParams(layoutParams);
            } else if (childAt instanceof AppContentView) {
                ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
                layoutParams2.height = this.mAppContentView.getViewHeight();
                childAt.setLayoutParams(layoutParams2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAppInner(ApplicationInfo applicationInfo, ViewGroup viewGroup, View view) {
        AppInfo appInfoByPackage = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage(applicationInfo.getPackageName());
        if (appInfoByPackage == null) {
            return;
        }
        Drawable drawableAdapterMultiTheme = BitmapUtils.adapterMultiTheme(appInfoByPackage.packageName, appInfoByPackage.getIcons());
        if (drawableAdapterMultiTheme == null) {
            Log.w(TAG, "openApp drawable is null");
            return;
        }
        this.mView.setSelectedAppInfo(applicationInfo);
        appInfoByPackage.setForbidden(applicationInfo.isForbidden());
        CarPlayAndAutoManager.getInstance().jumpApplication(this.mContext, appInfoByPackage, this.mView, viewGroup, view, ((BitmapDrawable) drawableAdapterMultiTheme).getBitmap(), 72, 72, applicationInfo.x, applicationInfo.y, false);
        if ("com.ts.carplay.app".equals(appInfoByPackage.packageName) || "com.ts.androidauto.app".equals(appInfoByPackage.packageName)) {
            this.mView.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openApp(final ApplicationInfo applicationInfo, final ViewGroup viewGroup, final View view) {
        view.playSoundEffect(0);
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.CombineView.18
            @Override // java.lang.Runnable
            public void run() {
                if (applicationInfo.getType() == 3) {
                    CombineView.this.openShortcut(applicationInfo, viewGroup, view);
                } else {
                    CombineView.this.openAppInner(applicationInfo, viewGroup, view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openShortcut(ApplicationInfo applicationInfo, ViewGroup viewGroup, View view) {
        String strExtractShortcutIdentify = AppUtils.extractShortcutIdentify(applicationInfo.getPackageName());
        if (TextUtils.isEmpty(strExtractShortcutIdentify)) {
            Log.e(TAG, "openShortcut extract fail!");
            return;
        }
        String strExtractShortcutOrigin = AppUtils.extractShortcutOrigin(applicationInfo.getPackageName());
        if (TextUtils.isEmpty(strExtractShortcutOrigin)) {
            Log.e(TAG, "openShortcut extract fail!");
            return;
        }
        ShortcutInfo shortcutByIdentify = CardDatabase.getInstance().getShortcutInfoDao().getShortcutByIdentify(strExtractShortcutIdentify, strExtractShortcutOrigin);
        if (shortcutByIdentify == null) {
            Log.e(TAG, "openShortcut shortcutInfo is null");
            return;
        }
        Drawable drawableBytes2Drawable = BitmapUtils.bytes2Drawable(shortcutByIdentify.getIcon());
        if (drawableBytes2Drawable == null) {
            Log.w(TAG, "openShortcut drawable is null");
            return;
        }
        this.mView.setSelectedAppInfo(applicationInfo);
        shortcutByIdentify.setForbidden(applicationInfo.isForbidden());
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
        AppUtils.openShortcut(this.mContext, shortcutByIdentify, this.mView, viewGroup, view, ((BitmapDrawable) drawableBytes2Drawable).getBitmap(), 72, 72, applicationInfo.x, applicationInfo.y, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApplicationInfo turnAppBean(ApplicationInfo app) {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.folderId = app.folderId;
        applicationInfo.setIndex(app.getIndex());
        applicationInfo.setTitle(app.getTitle());
        applicationInfo.setIcon(BitmapFactory.decodeByteArray(app.getIconBytes(), 0, app.getIconBytes().length));
        applicationInfo.setPackageName(app.getPackageName());
        applicationInfo.setActivity(app.getActivity());
        applicationInfo.setVersionCode(app.getVersionCode());
        applicationInfo.setVersionName(app.getVersionName());
        applicationInfo.setPosition(app.getPosition());
        applicationInfo.setFolderPosition(app.getFolderPosition());
        applicationInfo.setType(app.type);
        return applicationInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAppInfoPopup(View view, float rawY) {
        final ApplicationInfo selectedApp = this.mAppContentView.getSelectedApp();
        if (selectedApp == null || selectedApp.mPackageName.equals("com.ts.androidauto.app") || selectedApp.mPackageName.equals("com.ts.carplay.app")) {
            Log.d(TAG, "showAppInfoPopup applicationInfo is null");
            return;
        }
        this.mPopWindow.setIconPopClickListener(new IconPopWindow.IconPopClickListener() { // from class: com.byd.card.drag.CombineView.19
            @Override // com.byd.card.widget.IconPopWindow.IconPopClickListener
            public void onPopupItemClick(View view2) {
                String packageName;
                int id = view2.getId();
                if (id == R.id.app_info) {
                    Context context = CombineView.this.mContext;
                    if (selectedApp.getType() == 3) {
                        packageName = AppUtils.extractShortcutOrigin(selectedApp.getPackageName());
                    } else {
                        packageName = selectedApp.getPackageName();
                    }
                    AppUtils.jumpApplicationDetail(context, packageName);
                    CombineView.this.mView.cancelTimer();
                } else if (id == R.id.delete_app) {
                    Log.d(CombineView.TAG, "showAppInfoPopup onPopupItemClick  delete app! applicationInfo = " + selectedApp);
                    if (CombineView.this.mUninstallDialog != null) {
                        CombineView.this.mUninstallDialog = null;
                    }
                    int uiStyle = UiStyleConfig.getInstance(CombineView.this.mContext).getUiStyle();
                    if (uiStyle != 1) {
                        if (uiStyle != 2) {
                            if (AppUtils.isRightRudder()) {
                                CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.dialog_uninstall_right, selectedApp);
                            } else {
                                CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.dialog_uninstall, selectedApp);
                            }
                        }
                    } else if (AppUtils.isRightRudder()) {
                        CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.denza_dialog_uninstall_right, selectedApp);
                    } else {
                        CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.denza_dialog_uninstall, selectedApp);
                    }
                    CombineView.this.mUninstallDialog.show();
                    if (!CombineView.this.jma.isJiggling()) {
                        CombineView.this.jma.jiggle();
                    }
                }
                CombineView.this.mPopWindow.dismiss();
            }
        });
        int dimensionPixelSize = this.mView.getResources().getDimensionPixelSize(R.dimen.dp_72) + this.mView.getResources().getDimensionPixelSize(R.dimen.dp_24) + this.mView.getResources().getDimensionPixelSize(R.dimen.dp_35) + this.mView.getResources().getDimensionPixelSize(R.dimen.dp_6);
        if (AppUtils.isSystemApp(this.mView, selectedApp.getPackageName())) {
            if (rawY < dimensionPixelSize) {
                IconPopWindow iconPopWindow = this.mPopWindow;
                AppContentView appContentView = this.mAppContentView;
                iconPopWindow.show(appContentView, view, false, 1, appContentView);
                return;
            } else {
                IconPopWindow iconPopWindow2 = this.mPopWindow;
                AppContentView appContentView2 = this.mAppContentView;
                iconPopWindow2.show(appContentView2, view, false, 0, appContentView2);
                return;
            }
        }
        if (rawY < dimensionPixelSize) {
            IconPopWindow iconPopWindow3 = this.mPopWindow;
            AppContentView appContentView3 = this.mAppContentView;
            iconPopWindow3.show(appContentView3, view, true, 1, appContentView3);
        } else {
            IconPopWindow iconPopWindow4 = this.mPopWindow;
            AppContentView appContentView4 = this.mAppContentView;
            iconPopWindow4.show(appContentView4, view, true, 0, appContentView4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAppInfoPopup(View view, IPageView currentPage) {
        final ApplicationInfo selectedApp = currentPage.getSelectedApp();
        if (selectedApp == null || selectedApp.mPackageName.equals("com.ts.carplay.app") || selectedApp.mPackageName.equals("com.ts.androidauto.app")) {
            BydLogHelper.logD(TAG, "showAppInfoPopup applicationInfo is null");
            return;
        }
        this.mPopWindow.setIconPopClickListener(new IconPopWindow.IconPopClickListener() { // from class: com.byd.card.drag.CombineView.20
            @Override // com.byd.card.widget.IconPopWindow.IconPopClickListener
            public void onPopupItemClick(View view2) {
                String packageName;
                int id = view2.getId();
                if (id == R.id.app_info) {
                    Context context = CombineView.this.mContext;
                    if (selectedApp.getType() == 3) {
                        packageName = AppUtils.extractShortcutOrigin(selectedApp.getPackageName());
                    } else {
                        packageName = selectedApp.getPackageName();
                    }
                    AppUtils.jumpApplicationDetail(context, packageName);
                    CombineView.this.mView.cancelTimer();
                } else if (id == R.id.delete_app) {
                    if (CombineView.this.mUninstallDialog != null) {
                        CombineView.this.mUninstallDialog = null;
                    }
                    if (AppUtils.isRightRudder()) {
                        CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.dialog_uninstall_right, selectedApp);
                    } else {
                        CombineView.this.mUninstallDialog = new UninstallDialog(CombineView.this.mView, R.layout.dialog_uninstall, selectedApp);
                    }
                    CombineView.this.mUninstallDialog.show();
                    if (CombineView.this.mFolderView != null) {
                        CombineView.this.mFolderView.folderJiggle();
                    }
                }
                CombineView.this.mPopWindow.dismiss();
            }
        });
        Point iconLocation = currentPage.getIconLocation(currentPage.getSelectedIndex());
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.dp_176);
        if (AppUtils.isSystemApp(this.mView, selectedApp.getPackageName())) {
            if (iconLocation.y < dimension) {
                this.mPopWindow.folderShow(view, false, 1, currentPage);
                return;
            } else {
                this.mPopWindow.folderShow(view, false, 0, currentPage);
                return;
            }
        }
        if (iconLocation.y < dimension) {
            this.mPopWindow.folderShow(view, true, 1, currentPage);
        } else {
            this.mPopWindow.folderShow(view, true, 0, currentPage);
        }
    }

    public void dismissAppInfoPopup() {
        IconPopWindow iconPopWindow = this.mPopWindow;
        if (iconPopWindow != null) {
            iconPopWindow.dismiss();
        }
    }

    public void dismissFolderDialog() {
        FolderAddAppDialog folderAddAppDialog = this.folderAddAppDialog;
        if (folderAddAppDialog != null) {
            folderAddAppDialog.dismissDialog();
        }
    }

    public void cancelUninstallDialog() {
        UninstallDialog uninstallDialog = this.mUninstallDialog;
        if (uninstallDialog != null) {
            uninstallDialog.cancel();
        }
    }

    public void cancelJiggle() {
        JiggleModeActivator jiggleModeActivator = this.jma;
        if (jiggleModeActivator != null) {
            jiggleModeActivator.unjiggle();
        }
    }

    public void updateApp(int type, ApplicationInfo applicationInfo) {
        Log.i(TAG, "updateApp type = " + type + " applicationInfo = " + applicationInfo);
        if (this.mAppContentView == null) {
            Log.i(TAG, "updateApp mAppContentView null!");
            return;
        }
        if (type == AppInstallBroadcastReceiver.REMOVED_APP) {
            FolderView folderView = this.mFolderView;
            if (folderView != null && this.mIsOpenFolder) {
                uninstallRemoveFolder(folderView, applicationInfo);
                int iUninstallApp = this.mFolderView.uninstallApp(applicationInfo);
                if (iUninstallApp >= 0) {
                    int i = ((LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS) - iUninstallApp) * ((int) SpringBoardPage.FOLDER_MOVE_TIME);
                    if (this.mCancelFolderEditDetacher == null) {
                        this.mCancelFolderEditDetacher = new CancellableQueueTimer(this.handler, i, this.cancelFolderEditDetacher);
                    }
                }
            } else {
                this.mAppContentView.deleteAPP(applicationInfo);
                int iconsCount = this.mAppContentView.getIconsCount() * ((int) AppContentView.APP_MOVE_TIME);
                if (this.mCancelEditDetacher == null) {
                    this.mCancelEditDetacher = new CancellableQueueTimer(this.handler, iconsCount, this.cancelEditDetacher);
                }
            }
        } else if (type == AppInstallBroadcastReceiver.ADD_APP) {
            loaded();
            this.mAllItemList.clear();
            this.controller.initData(this.mAllItemList);
        } else if (type == AppInstallBroadcastReceiver.REMOVED_SHORTCUTS) {
            loaded();
            this.mAllItemList.clear();
            this.controller.initData(this.mAllItemList);
            closeFolder();
        }
        updateScrollHeight();
        this.mAppContentView.invalidate();
    }

    private void uninstallRemoveFolder(FolderView folderView, ApplicationInfo removeApp) {
        List<ApplicationInfo> icons = folderView.getInfo().getIcons();
        if (icons == null || icons.size() != 2) {
            return;
        }
        ApplicationInfo applicationInfo = icons.get(0);
        ApplicationInfo applicationInfo2 = icons.get(1);
        if (applicationInfo != null && TextUtils.equals(applicationInfo.getPackageName(), removeApp.getPackageName())) {
            this.mAppContentView.removeFolder(applicationInfo2);
            DBUtils.getInstance().removeFolder(applicationInfo2);
            closeFolder();
        } else {
            if (applicationInfo2 == null || !TextUtils.equals(applicationInfo2.getPackageName(), removeApp.getPackageName())) {
                return;
            }
            this.mAppContentView.removeFolder(applicationInfo);
            DBUtils.getInstance().removeFolder(applicationInfo);
            closeFolder();
        }
    }

    public void refreshAppListUI() {
        Log.i(TAG, "refreshAppListUI mAppContentView = " + this.mAppContentView + ", mFolderView = " + this.mFolderView);
        AppContentView appContentView = this.mAppContentView;
        if (appContentView != null) {
            appContentView.invalidate();
        }
        FolderView folderView = this.mFolderView;
        if (folderView != null) {
            folderView.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVelocityTrackerEvent(MotionEvent event) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(event);
    }

    private int getTouchVelocityY() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            return 0;
        }
        velocityTracker.computeCurrentVelocity(1000);
        return Math.abs((int) this.mVelocityTracker.getYVelocity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSlideParam(final MotionEvent ev) {
        int y = (int) ev.getY();
        this.startY = y;
        this.previousY = y;
        this.startScroll = false;
        this.moveHeight = 0.0f;
        this.topRect.set(this.mAppContentView.getLeft(), this.mAppContentView.getTop(), this.mAppContentView.getRight(), this.mAppContentView.getBottom());
    }

    public void updateSlide() {
        this.topRect.set(this.mAppContentView.getLeft(), this.mAppContentView.getTop(), this.mAppContentView.getRight(), this.mAppContentView.getViewHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void slideAppContentView(final MotionEvent ev) {
        int y = (int) ev.getY();
        this.currentY = y;
        this.deltaY = y - this.previousY;
        this.previousY = y;
        if ((!this.mAppScrollView.canScrollVertically(-1) && this.currentY - this.startY > 0) || (!this.mAppScrollView.canScrollVertically(1) && this.currentY - this.startY < 0)) {
            float f = this.currentY - this.startY;
            float f2 = 0.0f;
            if (f < 0.0f) {
                f *= -1.0f;
            }
            float height = this.mAppContentView.getHeight();
            if (height == 0.0f) {
                f2 = 0.5f;
            } else if (f <= height) {
                f2 = (height - f) / height;
            }
            if (this.currentY - this.startY < 0) {
                f2 = 1.0f - f2;
            }
            this.moveHeight += this.deltaY * ((float) (((double) ((float) (((double) f2) * 0.2d))) + 0.2d));
            this.startScroll = true;
            this.mAppContentView.layout(this.topRect.left, (int) (this.topRect.top + this.moveHeight), this.topRect.right, (int) (this.topRect.bottom + this.moveHeight));
        }
        if ((!this.mAppScrollView.canScrollVertically(-1) || this.currentY - this.startY <= 0) && (!this.mAppScrollView.canScrollVertically(1) || this.currentY - this.startY >= 0)) {
            return;
        }
        this.deltaY = (int) (this.deltaY + (this.currentY - this.startY <= 0 ? -0.5f : 0.5f));
        if (getTouchVelocityY() > 2000) {
            this.mAppScrollView.smoothScrollBy(0, (-this.deltaY) * 5);
        } else {
            this.mAppScrollView.smoothScrollBy(0, -this.deltaY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endSlideAppContentView(final MotionEvent ev) {
        if (this.startScroll) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.mAppContentView.getTop(), this.topRect.top);
            translateAnimation.setDuration(200L);
            translateAnimation.setFillAfter(true);
            this.mAppContentView.startAnimation(translateAnimation);
            this.mAppContentView.layout(this.topRect.left, this.topRect.top, this.topRect.right, this.topRect.bottom);
            if (getTouchVelocityY() > 2000) {
                if (ev.getY() - this.downSlideY < 0.0f) {
                    this.mAppScrollView.smoothScrollBy(0, getTouchVelocityY() / 5);
                } else {
                    this.mAppScrollView.smoothScrollBy(0, (-getTouchVelocityY()) / 5);
                }
            }
        }
    }

    public boolean isOpenFolder() {
        return this.mIsOpenFolder;
    }
}
