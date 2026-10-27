package com.byd.card.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.CarController;
import com.byd.card.bean.MediaCardMenuBean;
import com.byd.card.floatcard.Executors;
import com.byd.card.floatcard.MediaCardPopupAdapter;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.utils.DensityUtil;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class MediaMenuPopWindow extends PopupWindow {
    private static final int MAX_ITEM_COUNT = 6;
    private static final int[] MENU_ORDER_ARRAY = {103, 104, 11, 12, 13, 101, 102, 8, 1, 106, 107, 108, 109, 105, 110, 111, 112, 113, 114, 115, 116};
    private static final String TAG = "MediaMenuPopWindow";
    private Context context;
    private MediaCardPopupAdapter mAdapter;
    protected CPAAControlCallback mCarControlCallback = new CPAAControlCallback();
    private CarController mCarController;
    private int mGapHeight;
    private MaxHeightRecyclerView mRecyclerView;
    private View mView;
    private BlurView mViewBg;
    private int mViewHeight;
    private int mViewWidth;

    public MediaMenuPopWindow(Context context) {
        this.context = context;
        this.mViewWidth = (int) context.getResources().getDimension(R.dimen.dp_252);
        this.mGapHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_32);
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        carController.addCallback(this.mCarControlCallback);
        setWidth(-2);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.media_card_popup, (ViewGroup) null);
        this.mView = viewInflate;
        BlurView blurView = (BlurView) viewInflate.findViewById(R.id.media_pop_bg);
        this.mViewBg = blurView;
        blurView.setMaskBackgroundColor(0);
        this.mRecyclerView = (MaxHeightRecyclerView) this.mView.findViewById(R.id.media_card_popup_rv);
        MediaCardPopupAdapter mediaCardPopupAdapter = new MediaCardPopupAdapter(context);
        this.mAdapter = mediaCardPopupAdapter;
        mediaCardPopupAdapter.setOnClickItemListener(new MediaCardPopupAdapter.OnClickItemListener() { // from class: com.byd.card.widget.-$$Lambda$MediaMenuPopWindow$QnupOWOqc_rF--mrh6qv0Zps49U
            @Override // com.byd.card.floatcard.MediaCardPopupAdapter.OnClickItemListener
            public final void itemClick() {
                this.f$0.dismiss();
            }
        });
        this.mRecyclerView.setAdapter(this.mAdapter);
        setContentView(this.mView);
    }

    protected class CPAAControlCallback implements CarController.Callback {
        protected CPAAControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnCarPlay(boolean connected) {
            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.widget.MediaMenuPopWindow.CPAAControlCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaMenuPopWindow.this.dismiss();
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onSessionStatusChangedOnAndroidAuto(boolean connected) {
            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.widget.MediaMenuPopWindow.CPAAControlCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    MediaMenuPopWindow.this.dismiss();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MediaCardMenuBean> initData(List<Integer> data, int currentMode, int currentSource, boolean hasSdCard, boolean hasUsb) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= data.size()) {
                break;
            }
            int iIntValue = data.get(i2).intValue();
            if (iIntValue != 0) {
                if (iIntValue == 1) {
                    arrayList.add(new MediaCardMenuBean("", 1, currentMode == 1 && currentSource == 0, 0));
                    i = i2;
                } else {
                    arrayList.add(new MediaCardMenuBean("", iIntValue, iIntValue == currentMode));
                }
            }
            i2++;
        }
        if (i >= 0) {
            if (hasUsb) {
                arrayList.add(i + 1, new MediaCardMenuBean("", 1, currentMode == 1 && currentSource == 1, 1));
            }
            if (hasSdCard) {
                int i3 = i + 1;
                if (currentMode == 1 && currentSource == 2) {
                    z = true;
                }
                arrayList.add(i3, new MediaCardMenuBean("", 1, z, 2));
            }
        }
        this.mViewHeight = (int) (Math.min(arrayList.size(), 6) * this.context.getResources().getDimension(R.dimen.dp_60));
        return arrayList;
    }

    public void initCheckedItem(View parent) {
        if (parent == null) {
            Log.w(TAG, "parent is null");
        } else {
            MediaControl.controlMedia(this.context, -1, 72, null, new AnonymousClass1(parent));
        }
    }

    /* JADX INFO: renamed from: com.byd.card.widget.MediaMenuPopWindow$1, reason: invalid class name */
    class AnonymousClass1 implements MediaControl.Callback {
        final /* synthetic */ View val$parent;

        AnonymousClass1(final View val$parent) {
            this.val$parent = val$parent;
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int mode, int action, MediaControl.Result result) {
            if (!result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE)) {
                Log.w(MediaMenuPopWindow.TAG, "onControlResult fail ret = " + result.getResult().getString(MediaControl.PARAM_REASON));
                return;
            }
            int[] intArray = result.getResult().getIntArray(MediaControl.PARAM_SUPPORT_MODE_LIST);
            if (intArray != null && intArray.length > 0) {
                final List listSortMusicMode = MediaMenuPopWindow.this.sortMusicMode(intArray);
                if (listSortMusicMode.size() <= 0) {
                    Log.w(MediaMenuPopWindow.TAG, "onControlResult orderList empty!");
                    return;
                }
                final boolean z = result.getResult().getBoolean(MediaControl.PARAM_SD_PLUGIN);
                final boolean z2 = result.getResult().getBoolean(MediaControl.PARAM_USB_PLUGIN);
                final int i = result.getResult().getInt(MediaControl.PARAM_CURRENT_MODE);
                final int i2 = result.getResult().getInt(MediaControl.PARAM_MEDIA_TYPE, 0);
                Log.i(MediaMenuPopWindow.TAG, "onControlResult hasSdCard = " + z + ", hasUsb = " + z2 + ", currentMode = " + i + ", currentSource = " + i2);
                final View view = this.val$parent;
                view.post(new Runnable() { // from class: com.byd.card.widget.-$$Lambda$MediaMenuPopWindow$1$27ncGtYX_Oa60prgFqPGEivBpQI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onControlResult$0$MediaMenuPopWindow$1(listSortMusicMode, i, i2, z, z2, view);
                    }
                });
                return;
            }
            Log.w(MediaMenuPopWindow.TAG, "onControlResult intArray null!");
        }

        public /* synthetic */ void lambda$onControlResult$0$MediaMenuPopWindow$1(List list, int i, int i2, boolean z, boolean z2, View view) {
            List<MediaCardMenuBean> listInitData = MediaMenuPopWindow.this.initData(list, i, i2, z, z2);
            MediaMenuPopWindow.this.mAdapter.updateAdapter(listInitData);
            int size = listInitData.size();
            int i3 = 0;
            if (size <= 6) {
                MediaMenuPopWindow.this.mRecyclerView.setVerticalScrollBarEnabled(false);
            } else {
                MediaMenuPopWindow.this.mRecyclerView.setVerticalScrollBarEnabled(true);
                ((CustomLayoutManager) MediaMenuPopWindow.this.mRecyclerView.getLayoutManager()).setHeight(MediaMenuPopWindow.this.mViewHeight, size * MediaMenuPopWindow.this.context.getResources().getDimensionPixelOffset(R.dimen.dp_60), MediaMenuPopWindow.this.context.getResources().getDimensionPixelOffset(R.dimen.dp_146), MediaMenuPopWindow.this.context.getResources().getDimensionPixelSize(R.dimen.dp_24));
                while (true) {
                    if (i3 >= listInitData.size()) {
                        break;
                    }
                    if (listInitData.get(i3).isChecked()) {
                        MediaMenuPopWindow.this.mRecyclerView.scrollToPosition(i3);
                        break;
                    }
                    i3++;
                }
            }
            MediaMenuPopWindow.this.show(view, listInitData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Integer> sortMusicMode(int[] musicSource) {
        List<Integer> list = (List) Arrays.stream(MENU_ORDER_ARRAY).boxed().collect(Collectors.toList());
        final List list2 = (List) Arrays.stream(musicSource).boxed().collect(Collectors.toList());
        list.removeIf(new Predicate() { // from class: com.byd.card.widget.-$$Lambda$MediaMenuPopWindow$Ih7a2GpDmj0blCmTFNEEzsWsY3g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MediaMenuPopWindow.lambda$sortMusicMode$0(list2, (Integer) obj);
            }
        });
        Log.d(TAG, "sortMusicSource musicSourceList = " + list2 + ", orderList = " + list);
        return list;
    }

    static /* synthetic */ boolean lambda$sortMusicMode$0(List list, Integer num) {
        return !list.contains(num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show(View parent, List<MediaCardMenuBean> mediaMenuList) {
        float dimension;
        int width;
        int i;
        int dimensionPixelSize;
        int navigationBarHeight;
        if (AppUtils.isLandSpace(this.context)) {
            dimension = this.context.getResources().getDimension(R.dimen.dp_116);
        } else {
            dimension = this.context.getResources().getDimension(R.dimen.dp_158);
        }
        int i2 = (int) (-dimension);
        int x = (int) parent.getX();
        int width2 = parent.getWidth() - this.mViewWidth;
        if (mediaMenuList != null) {
            if (parent.getWidth() + x > this.mViewWidth) {
                width = parent.getWidth() + x;
                i = this.mViewWidth;
            } else {
                width = parent.getWidth();
                i = this.mViewWidth;
            }
            int i3 = width - i;
            if (AppUtils.isLandSpace(this.context)) {
                int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen.dp_720);
                dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.dp_1280);
                navigationBarHeight = ((dimensionPixelSize2 - DensityUtil.getNavigationBarHeight(this.context)) - parent.getHeight()) - this.mGapHeight;
            } else {
                int dimensionPixelSize3 = this.context.getResources().getDimensionPixelSize(R.dimen.dp_1280);
                dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.dp_720);
                navigationBarHeight = (((dimensionPixelSize3 - DensityUtil.getNavigationBarHeight(this.context)) - parent.getHeight()) - this.mGapHeight) + this.context.getResources().getDimensionPixelSize(R.dimen.dp_16);
            }
            if (AppUtils.isRightRudder()) {
                this.mViewBg.setBlurViewLayout(this.mViewWidth, this.mViewHeight, 1);
            } else {
                this.mViewBg.setBlurViewLayout(this.mViewWidth, this.mViewHeight, 0);
            }
            this.mViewBg.setBlurViewRadius(this.context.getResources().getDimensionPixelOffset(R.dimen.dp_12));
            if (AppUtils.isLandSpace(this.context)) {
                this.mViewBg.setBlurView(BitmapUtils.getScreenshot1(this.context, new Rect(i3, navigationBarHeight - this.mViewHeight, this.mViewWidth + i3, navigationBarHeight), this.mViewWidth, this.mViewHeight));
            } else {
                int i4 = dimensionPixelSize - i3;
                this.mViewBg.setBlurView(BitmapUtils.getScreenshot1(this.context, new Rect(i4 - this.mViewWidth, navigationBarHeight - this.mViewHeight, i4, navigationBarHeight), this.mViewWidth, this.mViewHeight));
            }
        }
        if (AppUtils.isRtl(this.context)) {
            showAsDropDown(parent, -parent.getWidth(), i2 - this.mViewHeight);
        } else if (AppUtils.isRightRudder()) {
            showAsDropDown(parent, width2, i2 - this.mViewHeight);
        } else {
            showAsDropDown(parent, width2, i2 - this.mViewHeight);
        }
    }
}
