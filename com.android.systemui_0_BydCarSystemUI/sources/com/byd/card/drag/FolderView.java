package com.byd.card.drag;

import android.app.UiStyleConfig;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.R;
import com.byd.card.bean.Constant;
import com.byd.card.bean.Folder;
import com.byd.card.callback.SoftKeyBoardListener;
import com.byd.card.db.ApplicationDao;
import com.byd.card.db.CardDatabase;
import com.byd.card.db.FolderDao;
import com.byd.card.drag.CombineView;
import com.byd.card.floatcard.Executors;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.view.activity.MoreAppActivity;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class FolderView extends RelativeLayout implements View.OnFocusChangeListener, TextView.OnEditorActionListener, TextWatcher, View.OnClickListener {
    public static final int SCROLLER_LEFT = 1;
    public static final int SCROLLER_RIGHT = 2;
    private String TAG;
    private Controller controller;
    private JiggleModeActivator folderJma;
    private boolean isInit;
    private LayoutCalculator lc;
    private List<ApplicationInfo> list;
    private MoreAppActivity mActivity;
    private boolean mAddIcon;
    Animation.AnimationListener mAnimationListener;
    private ImageView mClear;
    private int mDefaultInputType;
    private DotView mDotView;
    private RelativeLayout mEditLayout;
    private RelativeLayout mFolderBoard;
    private LinearLayout mFolderContainer;
    private int mFolderHeight;
    private EditText mFolderName;
    private String mFolderStr;
    private int mFolderWidth;
    private MoreAppFolderInfo mInfo;
    private boolean mNameIsEdit;
    private boolean mNotFirstOpen;
    private String mOriginalName;
    private PageScrollView mPageScrollView;
    private int mRemoverAppIndex;
    public SpringAnimation mScrollAnimation;
    private boolean messed;
    private boolean needUpload;
    private Vector<ApplicationInfo[]> pages;
    private ObjectPool pp;
    private int selectedPageIndex;
    private View view;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
    }

    public void initLayout() {
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    public FolderView(MoreAppActivity activity) {
        super(activity);
        this.TAG = "FolderView";
        this.messed = false;
        this.isInit = false;
        this.mDotView = null;
        this.mFolderBoard = null;
        this.mActivity = null;
        this.list = new ArrayList();
        this.needUpload = false;
        this.mFolderWidth = 0;
        this.mFolderHeight = 0;
        this.selectedPageIndex = 1;
        this.mAddIcon = false;
        this.mRemoverAppIndex = 0;
        this.mEditLayout = null;
        this.mFolderStr = "";
        this.folderJma = new JiggleModeActivator() { // from class: com.byd.card.drag.FolderView.5
            @Override // com.byd.card.drag.JiggleModeActivator
            public boolean isJigglable() {
                return FolderView.this.messed;
            }

            @Override // com.byd.card.drag.JiggleModeActivator
            public void jiggle() {
                FolderView.this.messed = true;
                FolderView.this.hideAddAppIcon();
                setState(2);
                for (int i = 1; i < FolderView.this.mFolderContainer.getChildCount() - 1; i++) {
                    FolderView.this.getPage(i).jiggle();
                }
            }

            @Override // com.byd.card.drag.JiggleModeActivator
            public void unjiggle() {
                FolderView.this.messed = false;
                setState(0);
                for (int childCount = FolderView.this.mFolderContainer.getChildCount() - 2; childCount >= 1; childCount--) {
                    IPageView page = FolderView.this.getPage(childCount);
                    page.unJiggle();
                    if (page.getIconsCount() == 0) {
                        FolderView.this.mFolderContainer.removeViewAt(childCount);
                        if (FolderView.this.getCurrentPageIndex() == childCount && FolderView.this.getCurrentPageIndex() == FolderView.this.getPageCount() + 1) {
                            FolderView.this.scrollToLeft();
                        }
                    }
                }
                FolderView.this.mDotView.setPages(FolderView.this.getPageCount());
                if (FolderView.this.getPageCount() > 1) {
                    FolderView.this.mDotView.setVisibility(0);
                } else {
                    FolderView.this.mDotView.setVisibility(8);
                }
            }
        };
        this.controller = new Controller() { // from class: com.byd.card.drag.FolderView.6
            @Override // com.byd.card.drag.Controller
            public void onAppClick(ApplicationInfo app, View view) {
            }

            @Override // com.byd.card.drag.Controller
            public void initData(final List<ApplicationInfo> list) {
                new AsyncTask<String, String, String>() { // from class: com.byd.card.drag.FolderView.6.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(String result) {
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public String doInBackground(String... params) {
                        Collections.sort(list, new Comparator<ApplicationInfo>() { // from class: com.byd.card.drag.FolderView.6.1.1
                            @Override // java.util.Comparator
                            public int compare(ApplicationInfo arg0, ApplicationInfo arg1) {
                                return arg0.getFolderPosition() - arg1.getFolderPosition();
                            }
                        });
                        return null;
                    }
                }.execute("");
            }
        };
        this.mActivity = activity;
    }

    private void init(LayoutCalculator lc, int screenHeight, int width) {
        this.mFolderWidth = (lc.itemWidth + this.mActivity.getResources().getDimensionPixelOffset(R.dimen.dp_24)) * 3;
        if (AppUtils.isLandSpace(this.mActivity)) {
            this.mFolderHeight = this.mActivity.getResources().getDimensionPixelOffset(R.dimen.dp_520);
        } else {
            this.mFolderHeight = this.mActivity.getResources().getDimensionPixelOffset(R.dimen.dp_1044);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mFolderWidth, this.mFolderHeight);
        this.pages = new Vector<>();
        layoutParams.addRule(13);
        int uiStyle = UiStyleConfig.getInstance(this.mActivity).getUiStyle();
        if (uiStyle == 1) {
            this.view = View.inflate(getContext(), R.layout.denza_more_app_folder, null);
        } else if (uiStyle != 2) {
            this.view = View.inflate(getContext(), R.layout.more_app_folder, null);
        }
        this.mFolderName = (EditText) this.view.findViewById(R.id.folder_name);
        this.mClear = (ImageView) this.view.findViewById(R.id.clear_folder_name);
        this.mFolderName.setOnFocusChangeListener(this);
        this.mFolderName.setOnEditorActionListener(this);
        this.mFolderName.addTextChangedListener(this);
        this.mFolderName.setOnClickListener(this);
        this.mClear.setOnClickListener(this);
        this.mFolderContainer = (LinearLayout) this.view.findViewById(R.id.folder_container);
        setScrollView((PageScrollView) this.view.findViewById(R.id.folder_pageView));
        this.mDotView = (DotView) this.view.findViewById(R.id.dotView);
        this.mFolderBoard = (RelativeLayout) this.view.findViewById(R.id.folder_springboard);
        RelativeLayout relativeLayout = (RelativeLayout) this.view.findViewById(R.id.edit_layout);
        this.mEditLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        ObjectPool objectPool = new ObjectPool(this.mActivity, lc);
        this.pp = objectPool;
        this.mDotView.init(lc, objectPool);
        this.mFolderContainer.getViewTreeObserver().addOnGlobalLayoutListener(new OnLayoutReady());
        addView(this.view, layoutParams);
        this.mDefaultInputType = this.mFolderName.getInputType();
    }

    public View getFolderBoardView() {
        return this.mFolderBoard;
    }

    public int getTranslateLeft() {
        return this.view.getLeft();
    }

    public int getTranslateTop() {
        return this.view.getTop() + this.lc.dpToPixel(50);
    }

    public void onReady(LayoutCalculator lc, ObjectPool pp, int screenHeight, int width, List<ApplicationInfo> appList) {
        this.mNotFirstOpen = false;
        this.lc = lc;
        if (!this.isInit) {
            init(lc, screenHeight, width);
            this.isInit = true;
        }
        String title = this.mInfo.getTitle();
        this.mFolderStr = title;
        if (Constant.FolderType.DEFAULT_FOLDER.equals(title)) {
            this.mFolderStr = this.mActivity.getString(R.string.folder_not_name);
        }
        if (Constant.FolderType.TOOLS_FOLDER.equals(this.mFolderStr)) {
            this.mFolderStr = this.mActivity.getString(R.string.folder_name_utils);
        }
        this.mFolderName.setText(this.mFolderStr);
        this.list = appList;
        loaded();
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        this.mNameIsEdit = hasFocus;
        if (hasFocus) {
            String string = ((Editable) Objects.requireNonNull(this.mFolderName.getText())).toString();
            this.mOriginalName = string;
            if (!TextUtils.isEmpty(string)) {
                this.mClear.setVisibility(0);
            } else {
                this.mClear.setVisibility(8);
            }
            this.mEditLayout.setBackground(this.mActivity.getDrawable(R.drawable.launcher_folder_edit_bg));
            SoftKeyBoardListener.openKeyboard(v, this.mActivity);
            this.mFolderName.setSelectAllOnFocus(true);
            this.mFolderName.selectAll();
            this.mFolderName.setInputType(this.mDefaultInputType);
            this.mActivity.cancelTimer();
            return;
        }
        this.mEditLayout.setBackground(null);
        this.mClear.setVisibility(8);
        String strTrim = this.mFolderName.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            this.mFolderName.setText(this.mFolderStr);
            this.mFolderName.setInputType(0);
        }
        if (!Constant.FolderType.DEFAULT_FOLDER.equals(strTrim) && !Constant.FolderType.TOOLS_FOLDER.equals(strTrim) && !TextUtils.equals(this.mOriginalName, strTrim)) {
            updateFolderTitleToDB(strTrim);
        }
        this.mActivity.startAD();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId != 6) {
            return false;
        }
        doneEditingFolderName(true);
        ((InputMethodManager) this.mActivity.getSystemService("input_method")).hideSoftInputFromWindow(this.view.getWindowToken(), 2);
        return true;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!TextUtils.isEmpty(s) && this.mFolderName.isFocused()) {
            this.mClear.setVisibility(0);
        } else {
            this.mClear.setVisibility(8);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null && s.length() >= 50 && this.mNotFirstOpen) {
            ToastUtils.show(this.mActivity.getApplicationContext(), this.mActivity.getString(R.string.folder_reset_name));
        }
        if (!TextUtils.isEmpty(s) && this.mFolderName.isFocused()) {
            this.mClear.setVisibility(0);
        } else {
            this.mClear.setVisibility(8);
        }
        this.mNotFirstOpen = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.clear_folder_name) {
            this.mFolderName.setText("");
            this.mClear.setVisibility(8);
        } else if (id == R.id.edit_layout || id == R.id.folder_name) {
            this.mFolderName.setFocusable(true);
            this.mFolderName.setFocusableInTouchMode(true);
            this.mFolderName.requestFocus();
            SoftKeyBoardListener.openKeyboard(this.mFolderName, this.mActivity);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isShouldHideKeyboard(ev)) {
            SoftKeyBoardListener.closeKeyboard(this.mFolderName, this.mActivity);
            this.mFolderName.clearFocus();
        } else {
            MoreAppActivity moreAppActivity = this.mActivity;
            if (moreAppActivity != null) {
                moreAppActivity.cancelTimer();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(MotionEvent event) {
        int[] iArr = {0, 0};
        this.mEditLayout.getLocationInWindow(iArr);
        int dimensionPixelSize = iArr[0] - this.mActivity.getResources().getDimensionPixelSize(R.dimen.dp_24);
        int dimensionPixelSize2 = iArr[1] - this.mActivity.getResources().getDimensionPixelSize(R.dimen.dp_75);
        return event.getX() <= ((float) dimensionPixelSize) || event.getX() >= ((float) (this.mEditLayout.getWidth() + dimensionPixelSize)) || event.getY() <= ((float) dimensionPixelSize2) || event.getY() >= ((float) (this.mEditLayout.getHeight() + dimensionPixelSize2));
    }

    public void invalidateView() {
        int childCount = this.mFolderContainer.getChildCount();
        int i = this.selectedPageIndex;
        if (childCount > i) {
            this.mFolderContainer.getChildAt(i).invalidate();
        }
    }

    private class OnLayoutReady implements ViewTreeObserver.OnGlobalLayoutListener {
        private OnLayoutReady() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FolderView.this.mFolderContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            FolderView.this.controller.initData(FolderView.this.list);
        }
    }

    public void loaded() {
        this.pages.clear();
        this.mFolderContainer.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mFolderWidth, -1);
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        linearLayout.setBackgroundDrawable(null);
        this.mFolderContainer.addView(linearLayout, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.mActivity);
        linearLayout2.setBackgroundDrawable(null);
        this.mFolderContainer.addView(linearLayout2, layoutParams);
        ensurePages(1);
        this.lc.setFolderWidth(this.mFolderWidth);
        this.lc.setFolderHeight(this.mFolderHeight);
        List<ApplicationInfo> list = this.list;
        list.add(list.size(), newAddBtn());
        for (int i = 0; i < this.list.size(); i++) {
            int i2 = (i / (LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS)) + 1;
            int i3 = i % (LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS);
            ensurePages(i2);
            ApplicationInfo[] applicationInfoArr = this.pages.get(i2 - 1);
            if (i < this.list.size() - 1 && i3 < applicationInfoArr.length && applicationInfoArr[i3] == null) {
                applicationInfoArr[i3] = this.list.get(i);
            }
        }
        List<ApplicationInfo> list2 = this.list;
        list2.remove(list2.size() - 1);
        if (AppUtils.isRtl(this.mActivity)) {
            for (int size = this.pages.size() - 1; size >= 0; size--) {
                SpringBoardPage springBoardPage = new SpringBoardPage(this.mActivity);
                springBoardPage.init(this.lc, this.pp);
                springBoardPage.setIcons(this.pages.get(size));
                LinearLayout linearLayout3 = this.mFolderContainer;
                linearLayout3.addView(springBoardPage, linearLayout3.getChildCount() - 1, layoutParams);
            }
        } else {
            for (int i4 = 0; i4 < this.pages.size(); i4++) {
                SpringBoardPage springBoardPage2 = new SpringBoardPage(this.mActivity);
                springBoardPage2.init(this.lc, this.pp);
                springBoardPage2.setIcons(this.pages.get(i4));
                LinearLayout linearLayout4 = this.mFolderContainer;
                linearLayout4.addView(springBoardPage2, linearLayout4.getChildCount() - 1, layoutParams);
            }
        }
        this.mPageScrollView.post(new Runnable() { // from class: com.byd.card.drag.FolderView.1
            @Override // java.lang.Runnable
            public void run() {
                if (AppUtils.isRtl(FolderView.this.mActivity)) {
                    FolderView.this.mPageScrollView.scrollTo(FolderView.this.getPageCount() * FolderView.this.mFolderWidth, 0);
                } else {
                    FolderView.this.mPageScrollView.scrollTo(FolderView.this.mFolderWidth, 0);
                }
                FolderView.this.mPageScrollView.setVisibility(0);
            }
        });
        this.mDotView.setPages(getPageCount());
        if (AppUtils.isRtl(this.mActivity)) {
            this.mDotView.setCurrentPage(getPageCount() - 1);
        } else {
            this.mDotView.setCurrentPage(0);
        }
        if (getPageCount() > 1) {
            this.mDotView.setVisibility(0);
        } else {
            this.mDotView.setVisibility(8);
        }
        this.mAddIcon = false;
    }

    public int getPageCount() {
        return this.mFolderContainer.getChildCount() - 2;
    }

    public int getPageCountExcludeAddIcon() {
        MoreAppFolderInfo moreAppFolderInfo = this.mInfo;
        if (moreAppFolderInfo == null) {
            return 1;
        }
        return ((moreAppFolderInfo.getIcons().size() - 1) / (LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS)) + 1;
    }

    private void ensurePages(int count) {
        if (this.pages.size() < count) {
            addPage();
        }
    }

    private ApplicationInfo[] addPage() {
        ApplicationInfo[] applicationInfoArr = new ApplicationInfo[LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS];
        this.pages.add(applicationInfoArr);
        return applicationInfoArr;
    }

    public int getFolderWidth() {
        return this.mFolderWidth;
    }

    public PageScrollView getPageScrollView() {
        return this.mPageScrollView;
    }

    public void scrollToCurrent(int index) {
        this.mPageScrollView.smoothScrollTo(this.mFolderWidth * index, 0);
        this.mDotView.setCurrentPage(index - 1);
        this.selectedPageIndex = index;
    }

    public void smoothScrollToCurrent(int index) {
        startScrollAnimation(index);
        this.mDotView.setCurrentPage(index - 1);
        this.selectedPageIndex = index;
    }

    private void startScrollAnimation(int pageIndex) {
        if (this.mScrollAnimation == null) {
            this.mScrollAnimation = new SpringAnimation(this.mPageScrollView, SpringAnimation.SCROLL_X).setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(500.0f));
        }
        if (this.mScrollAnimation.isRunning()) {
            this.mScrollAnimation.cancel();
        }
        this.mScrollAnimation.animateToFinalPosition(pageIndex * this.mFolderWidth);
    }

    public void cancelScrollAnimation() {
        SpringAnimation springAnimation = this.mScrollAnimation;
        if (springAnimation == null || !springAnimation.isRunning()) {
            return;
        }
        this.mScrollAnimation.cancel();
    }

    public int scrollToLeft() {
        IPageView page = getPage(this.selectedPageIndex);
        if (page != null) {
            page.deselect();
        }
        int i = this.selectedPageIndex - 1;
        this.selectedPageIndex = i;
        if (i <= 1) {
            this.selectedPageIndex = 1;
        }
        this.mPageScrollView.smoothScrollTo(this.selectedPageIndex * this.mFolderWidth, 0);
        this.mDotView.setCurrentPage(this.selectedPageIndex - 1);
        return this.selectedPageIndex;
    }

    public int scrollToRight() {
        if (getPage(this.selectedPageIndex) != null) {
            getPage(this.selectedPageIndex).deselect();
        }
        int i = this.selectedPageIndex + 1;
        this.selectedPageIndex = i;
        if (i > this.mFolderContainer.getChildCount() - 2) {
            this.selectedPageIndex = this.mFolderContainer.getChildCount() - 2;
        }
        this.mPageScrollView.smoothScrollTo(this.selectedPageIndex * this.mFolderWidth, 0);
        this.mDotView.setCurrentPage(this.selectedPageIndex - 1);
        return this.selectedPageIndex;
    }

    public void iconToLeftPage() {
        IPageView page = getPage(this.selectedPageIndex);
        if (AppUtils.isRtl(this.mActivity)) {
            if (this.selectedPageIndex <= getPageCount() || page.getIconsCount() >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
                ApplicationInfo icon = page.getIcon(page.getIconsCount() - 1);
                IPageView page2 = getPage(this.selectedPageIndex + 1);
                page.removeApp(page.getIconsCount() - 1);
                page2.updateLayout(icon, 1);
                return;
            }
            return;
        }
        if (this.selectedPageIndex != 1 || page.getIconsCount() >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
            ApplicationInfo icon2 = page.getIcon(page.getIconsCount() - 1);
            IPageView page3 = getPage(this.selectedPageIndex + 1);
            page.removeApp(page.getIconsCount() - 1);
            page3.updateLayout(icon2, 1);
        }
    }

    public void iconToRightPage() {
        if (this.selectedPageIndex != this.mFolderContainer.getChildCount() - 2) {
            IPageView page = getPage(this.selectedPageIndex + 1);
            getPage(this.selectedPageIndex).updateLayout(page.getIcon(0), 2);
            page.removeApp(0);
        }
    }

    public void moveIconDesk() {
        if (getPageCount() == this.selectedPageIndex || getPageCount() == 1) {
            return;
        }
        int i = this.selectedPageIndex;
        while (i < getPageCount()) {
            IPageView page = getPage(i);
            i++;
            IPageView page2 = getPage(i);
            ApplicationInfo icon = page2.getIcon(0);
            page.setMoveTo(page.getIconsCount() - 1);
            page.updateLayout(icon, 2);
            page2.removeApp(0);
        }
    }

    public void moveIntoFolder(MoreAppFolderInfo moreAppFolderInfo, ApplicationInfo hookApp) {
        int iconsCount = getPage(getPageCount()).getIconsCount();
        if (iconsCount >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
            addNewPage();
        }
        List<ApplicationInfo> icons = moreAppFolderInfo.getIcons();
        if (icons != null && hookApp != null) {
            iconsCount = icons.size();
            for (int i = 0; i < icons.size(); i++) {
                ApplicationInfo applicationInfo = icons.get(i);
                if (applicationInfo != null && hookApp.getPackageName().equals(applicationInfo.getPackageName())) {
                    iconsCount--;
                }
            }
        }
        if (iconsCount >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
            if (AppUtils.isRtl(this.mActivity)) {
                scrollToCurrent(1);
            } else {
                scrollToCurrent(getPageCount());
            }
        }
    }

    public void isAddNewPage() {
        int iconsCount;
        if (AppUtils.isRtl(this.mActivity)) {
            iconsCount = getPage(1).getIconsCount();
        } else {
            iconsCount = getPage(getPageCount()).getIconsCount();
        }
        if (iconsCount >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
            addNewPage();
        }
    }

    public IPageView getPage(int index) {
        if (index <= 0 || index > getPageCount()) {
            return null;
        }
        View childAt = this.mFolderContainer.getChildAt(index);
        if (childAt instanceof SpringBoardPage) {
            return (SpringBoardPage) childAt;
        }
        return null;
    }

    public void doneEditingFolderName(boolean commit) {
        String strTrim = ((Editable) Objects.requireNonNull(this.mFolderName.getText())).toString().trim();
        if (strTrim.isEmpty()) {
            strTrim = this.mFolderStr;
        } else {
            this.mFolderStr = strTrim;
        }
        if (this.mActivity.getString(R.string.folder_not_name).equals(strTrim)) {
            this.mFolderStr = this.mActivity.getString(R.string.folder_not_name);
        }
        if (this.mActivity.getString(R.string.folder_name_utils).equals(strTrim)) {
            this.mFolderStr = this.mActivity.getString(R.string.folder_name_utils);
        }
        this.mInfo.setTitle(strTrim);
        this.mFolderName.clearFocus();
    }

    private void updateFolderTitleToDB(final String newTitle) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.FolderView.2
            @Override // java.lang.Runnable
            public void run() {
                FolderDao folderDao = CardDatabase.getInstance().getFolderDao();
                Folder folderById = folderDao.getFolderById(FolderView.this.mInfo.folderId);
                if (folderById == null || folderById.getTitle().isEmpty() || newTitle.isEmpty() || folderById.getTitle().equals(newTitle)) {
                    return;
                }
                folderById.setTitle(newTitle);
                folderDao.updateFolder(folderById);
            }
        });
    }

    public void updateAPPIcon(List<ApplicationInfo> appInfo) {
        this.mInfo.setIcons(appInfo);
    }

    public void updateFolderPosition(final List<ApplicationInfo> infos) {
        if (infos == null) {
            return;
        }
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.FolderView.3
            @Override // java.lang.Runnable
            public void run() {
                ApplicationInfo appByActivity;
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                if (infos.size() > 1) {
                    for (int i = 0; i < infos.size(); i++) {
                        ApplicationInfo applicationInfo = (ApplicationInfo) infos.get(i);
                        if (applicationInfo != null && (appByActivity = workSpaceItemDao.getAppByActivity(applicationInfo.activity)) != null) {
                            appByActivity.folderPosition = i;
                            workSpaceItemDao.update(appByActivity);
                        }
                    }
                }
            }
        });
    }

    public void updateFolderId(List<ApplicationInfo> infos) {
        if (infos == null || infos.size() < 3) {
            return;
        }
        List list = (List) infos.stream().filter(new Predicate() { // from class: com.byd.card.drag.-$$Lambda$FolderView$e0uEEfG7X7gQEZ5KZSrLg9HEPkA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((ApplicationInfo) obj);
            }
        }).collect(Collectors.toList());
        if (list.size() < 3) {
            return;
        }
        final List list2 = (List) list.stream().filter(new Predicate() { // from class: com.byd.card.drag.-$$Lambda$FolderView$TuRoH9xKdA6lBENIEVbqE26dvng
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ApplicationInfo) obj).getIndex().equals("1");
            }
        }).collect(Collectors.toList());
        if (list2.size() < 3) {
            return;
        }
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.FolderView.4
            @Override // java.lang.Runnable
            public void run() {
                int i;
                int i2 = 0;
                while (true) {
                    if (i2 >= list2.size()) {
                        i = -1;
                        break;
                    } else {
                        if (i2 < list2.size() - 1 && ((ApplicationInfo) list2.get(i2)).folderId == ((ApplicationInfo) list2.get(i2 + 1)).folderId) {
                            i = ((ApplicationInfo) list2.get(i2)).folderId;
                            break;
                        }
                        i2++;
                    }
                }
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                if (i == -1) {
                    ApplicationInfo appByActivity = workSpaceItemDao.getAppByActivity(((ApplicationInfo) list2.get(1)).activity);
                    appByActivity.folderId = ((ApplicationInfo) list2.get(0)).folderId;
                    workSpaceItemDao.update(appByActivity);
                    return;
                }
                for (ApplicationInfo applicationInfo : list2) {
                    if (applicationInfo.folderId != i) {
                        ApplicationInfo appByActivity2 = workSpaceItemDao.getAppByActivity(applicationInfo.activity);
                        appByActivity2.folderId = i;
                        workSpaceItemDao.update(appByActivity2);
                        return;
                    }
                }
            }
        });
    }

    public void showAddAppIcon() {
        IPageView page;
        if (this.mAddIcon) {
            return;
        }
        ApplicationInfo applicationInfo = new ApplicationInfo();
        int uiStyle = UiStyleConfig.getInstance(this.mActivity).getUiStyle();
        if (uiStyle == 1) {
            applicationInfo.setIcon(BitmapUtils.changedBitmap(this.mActivity, SkinManager.getInstance().getMipmapDrawable(R.mipmap.denza_folder_add_app), "add_app"));
        } else if (uiStyle != 2) {
            applicationInfo.setIcon(BitmapUtils.changedBitmap(this.mActivity, SkinManager.getInstance().getMipmapDrawable(R.mipmap.img_center_addapp), "add_app"));
        }
        applicationInfo.setTitle(this.mActivity.getString(R.string.add_app));
        applicationInfo.setIndex("-1");
        if (AppUtils.isRtl(this.mActivity)) {
            page = getPage(1);
        } else {
            page = getPage(this.mFolderContainer.getChildCount() - 2);
        }
        page.addAppIcon(applicationInfo, true);
        this.mAddIcon = true;
        if (getPageCount() > getPageCountExcludeAddIcon()) {
            int pageCount = getPageCount();
            this.mDotView.setPages(pageCount);
            if (pageCount <= 1 || this.mDotView.getVisibility() == 0) {
                return;
            }
            this.mDotView.setVisibility(0);
        }
    }

    public void hideAddAppIcon() {
        IPageView page;
        if (this.mAddIcon) {
            if (AppUtils.isRtl(this.mActivity)) {
                page = getPage(1);
            } else {
                page = getPage(this.mFolderContainer.getChildCount() - 2);
            }
            page.removeApp(page.getIconsCount() - 1);
            this.mAddIcon = false;
            if (getPageCount() > getPageCountExcludeAddIcon()) {
                int pageCountExcludeAddIcon = getPageCountExcludeAddIcon();
                this.mDotView.setPages(pageCountExcludeAddIcon);
                if (pageCountExcludeAddIcon >= 2 || this.mDotView.getVisibility() == 8) {
                    return;
                }
                this.mDotView.setVisibility(8);
            }
        }
    }

    public MoreAppFolderInfo getInfo() {
        return this.mInfo;
    }

    public void setInfo(MoreAppFolderInfo info) {
        if (this.mInfo != null) {
            this.mInfo = null;
        }
        this.mInfo = info;
    }

    public boolean isFolderJigglabel() {
        return this.folderJma.isJigglable();
    }

    public void folderJiggle() {
        this.folderJma.jiggle();
    }

    public void folderUnJiggle() {
        this.folderJma.unjiggle();
    }

    public int getCurrentPageIndex() {
        if (this.mPageScrollView.getScrollX() == 0) {
            return 1;
        }
        int scrollX = this.mPageScrollView.getScrollX();
        int i = this.mFolderWidth;
        if (scrollX % i > (i * 2) / 3.0f) {
            return (this.mPageScrollView.getScrollX() / this.mFolderWidth) + 1;
        }
        return this.mPageScrollView.getScrollX() / this.mFolderWidth;
    }

    public IPageView getContentView() {
        if (getCurrentPageIndex() <= 0 || getCurrentPageIndex() > getPageCount()) {
            return null;
        }
        View childAt = this.mFolderContainer.getChildAt(getCurrentPageIndex());
        if (childAt instanceof SpringBoardPage) {
            return (SpringBoardPage) childAt;
        }
        return null;
    }

    public PageScrollView getScrollView() {
        return this.mPageScrollView;
    }

    public void setScrollView(PageScrollView mScrollView) {
        this.mPageScrollView = mScrollView;
    }

    public void setScrollOnTouchListener(View.OnTouchListener scrollContainer_OnTouch) {
        PageScrollView pageScrollView = this.mPageScrollView;
        if (pageScrollView != null) {
            pageScrollView.setOnTouchListener(scrollContainer_OnTouch);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mPageScrollView.onTouchEvent(event)) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    public void setAppMoveAnimationListener(Animation.AnimationListener animationListener) {
        for (int i = 1; i < getPageCount(); i++) {
            if (getPage(i) != null) {
                getPage(i).setAppMoveAnimationListener(animationListener);
            }
        }
    }

    public void setAppMoveListener(CombineView.AppMoveListener listener) {
        for (int i = 1; i < getPageCount(); i++) {
            if (getPage(i) != null) {
                getPage(i).setAppMoveListener(listener);
            }
        }
    }

    public boolean getNeedMoveAnimation() {
        return getContentView().getNeedMoveAnimation();
    }

    public SpringBoardPage addNewPage() {
        if (getPage(this.mFolderContainer.getChildCount() - 2).getIconsCount() == 0) {
            return null;
        }
        SpringBoardPage springBoardPage = new SpringBoardPage(this.mActivity);
        springBoardPage.init(this.lc, this.pp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mFolderWidth, -1);
        if (AppUtils.isRtl(this.mActivity)) {
            this.mFolderContainer.addView(springBoardPage, 1, layoutParams);
        } else {
            LinearLayout linearLayout = this.mFolderContainer;
            linearLayout.addView(springBoardPage, linearLayout.getChildCount() - 1, layoutParams);
        }
        this.mDotView.setPages(getPageCount());
        if (getPageCount() > 1) {
            this.mDotView.setVisibility(0);
        } else {
            this.mDotView.setVisibility(8);
        }
        return springBoardPage;
    }

    public int uninstallApp(ApplicationInfo app) {
        int aPPIcon = getAPPIcon(app);
        if (aPPIcon < 0) {
            return -1;
        }
        int i = this.mRemoverAppIndex;
        getPage(aPPIcon).removeApp(i);
        while (aPPIcon < getPageCount()) {
            IPageView page = getPage(aPPIcon);
            aPPIcon++;
            IPageView page2 = getPage(aPPIcon);
            ApplicationInfo icon = page2.getIcon(0);
            page.setMoveTo(page.getIconsCount() - 1);
            page.updateLayout(icon, 2);
            page2.removeApp(0);
        }
        this.mRemoverAppIndex = 0;
        showAddAppIcon();
        return i;
    }

    private int getAPPIcon(ApplicationInfo app) {
        for (int i = 1; i <= getPageCount(); i++) {
            IPageView page = getPage(i);
            for (int i2 = 0; i2 < page.getIconsCount(); i2++) {
                if (TextUtils.equals(app.getPackageName(), page.getIcon(i2).getPackageName())) {
                    this.mRemoverAppIndex = i2;
                    return i;
                }
            }
        }
        return -1;
    }

    private ApplicationInfo newAddBtn() {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        int uiStyle = UiStyleConfig.getInstance(this.mActivity).getUiStyle();
        if (uiStyle == 1) {
            applicationInfo.setIcon(BitmapUtils.changedBitmap(this.mActivity, SkinManager.getInstance().getMipmapDrawable(R.mipmap.denza_folder_add_app), "add_app"));
        } else if (uiStyle != 2) {
            applicationInfo.setIcon(BitmapUtils.changedBitmap(this.mActivity, SkinManager.getInstance().getDrawable(R.mipmap.img_center_addapp), "add_app"));
        }
        applicationInfo.setTitle(this.mActivity.getString(R.string.add_app));
        applicationInfo.setIndex("-1");
        return applicationInfo;
    }

    public boolean folderNameIsFocus() {
        return this.mNameIsEdit;
    }

    public void closeInputSoft() {
        SoftKeyBoardListener.closeKeyboard(this.mFolderName, this.mActivity);
        this.mFolderName.setFocusableInTouchMode(false);
        this.mFolderName.clearFocus();
    }

    public void setFolderNameClickable(boolean enable) {
        EditText editText = this.mFolderName;
        if (editText != null) {
            editText.setEnabled(enable);
        }
    }
}
