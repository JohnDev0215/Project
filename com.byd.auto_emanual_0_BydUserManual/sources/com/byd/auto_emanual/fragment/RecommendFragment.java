package com.byd.auto_emanual.fragment;

import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.LocalPathUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.VideoPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class RecommendFragment extends BaseFragment {
    private static final int MSG_DATA_LOAD_SUCCESS = 1;
    private static final int MSG_START_PLAY = 2;
    private static final String TAG = "RecommendFragment";
    private static final int TIME_START_PLAY_DELAY = 200;
    private ImageView imageFeature;
    private ImageView imageScene;
    private LinearLayout layoutFeature;
    private LinearLayout layoutScene;
    private TextView nameFeature;
    private TextView nameScene;
    private NestedScrollView nestedScrollView;
    private int orientation;
    private Point point;
    private RecyclerView recycleViewHorizontalFeature;
    private RecyclerView recycleViewHorizontalScene;
    private RecyclerView recycleViewVerticalFeature;
    private RecyclerView recycleViewVerticalScene;
    private View root;
    private View signFeature;
    private View signScene;
    private RelativeLayout titleFeature;
    private RelativeLayout titleScene;
    private String videoPathFeature;
    private String videoPathScene;
    private VideoPlayer videoPlayerFeature;
    private View videoPlayerFeatureContainer;
    private View videoPlayerFeatureFace1;
    private View videoPlayerFeatureFace2;
    private VideoPlayer videoPlayerScene;
    private View videoPlayerSceneContainer;
    private View videoPlayerSceneFace1;
    private View videoPlayerSceneFace2;
    private boolean isStartVideoPlayerFeature = true;
    private boolean isStartVideoPlayerScene = false;
    private UIHandler uiHandler = new UIHandler(Looper.getMainLooper());

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.orientation = getResources().getConfiguration().orientation;
        MLog.v(TAG, "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MLog.v(TAG, "onCreateView");
        this.root = inflater.inflate(R.layout.recommend_fragment, container, false);
        updateDefaultColumnAndRowByConfig();
        initTitle();
        initNestedScrollView();
        initLinearLayout();
        initVideoPlayer();
        initRecycleView();
        initNetState();
        return this.root;
    }

    private void initNetState() {
        this.netErrorState = (RelativeLayout) this.root.findViewById(R.id.net_error_state);
        this.netLoadingState = (RelativeLayout) this.root.findViewById(R.id.net_loading_state);
        updateNetState();
    }

    private void initRecycleView() {
        this.recycleViewVerticalFeature = initRecycleViewVertical(R.id.recycler_view_vertical_feature);
        this.recycleViewHorizontalFeature = initRecycleViewHorizontal(R.id.recycler_view_horizontal_feature);
        this.recycleViewVerticalScene = initRecycleViewVertical(R.id.recycler_view_vertical_scene);
        this.recycleViewHorizontalScene = initRecycleViewHorizontal(R.id.recycler_view_horizontal_scene);
    }

    private RecyclerView initRecycleViewHorizontal(int viewId) {
        RecyclerView recyclerView = (RecyclerView) this.root.findViewById(viewId);
        RecommendHorizontalAdapter recommendHorizontalAdapter = new RecommendHorizontalAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(MApp.context));
        recyclerView.setAdapter(recommendHorizontalAdapter);
        return recyclerView;
    }

    private RecyclerView initRecycleViewVertical(int viewId) {
        RecyclerView recyclerView = (RecyclerView) this.root.findViewById(viewId);
        RecommendVerticalAdapter recommendVerticalAdapter = new RecommendVerticalAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MApp.context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recommendVerticalAdapter);
        return recyclerView;
    }

    private void initLinearLayout() {
        this.layoutFeature = (LinearLayout) this.root.findViewById(R.id.layout_feature);
        this.layoutScene = (LinearLayout) this.root.findViewById(R.id.layout_scene);
    }

    private void initVideoPlayer() {
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.recommend_item_header_margin_start);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.recommend_item_header_margin_top);
        int color = getResources().getColor(R.color.content_item_header_text, null);
        VideoPlayer videoPlayer = (VideoPlayer) this.root.findViewById(R.id.video_player_feature);
        this.videoPlayerFeature = videoPlayer;
        videoPlayer.setBackVisibility(8);
        this.videoPlayerFeature.setVideoTitle(dimensionPixelOffset, dimensionPixelOffset2, color, 1);
        this.videoPlayerFeature.setPlayControllerVisibility(8);
        this.videoPlayerFeature.setMuteVisibility(0);
        this.videoPlayerFeature.setVolume(0.0f);
        ViewUtil.setViewCorner(this.videoPlayerFeature, getResources().getDimension(R.dimen.recommend_video_radius));
        this.videoPlayerFeatureFace1 = this.root.findViewById(R.id.video_player_feature_face_1);
        this.videoPlayerFeatureFace2 = this.root.findViewById(R.id.video_player_feature_face_2);
        View viewFindViewById = this.root.findViewById(R.id.video_player_feature_container);
        this.videoPlayerFeatureContainer = viewFindViewById;
        updatePlayerByConfig(viewFindViewById);
        VideoPlayer videoPlayer2 = (VideoPlayer) this.root.findViewById(R.id.video_player_scene);
        this.videoPlayerScene = videoPlayer2;
        videoPlayer2.setBackVisibility(8);
        this.videoPlayerScene.setVideoTitle(dimensionPixelOffset, dimensionPixelOffset2, color, 1);
        this.videoPlayerScene.setPlayControllerVisibility(8);
        this.videoPlayerScene.setMuteVisibility(0);
        this.videoPlayerScene.setVolume(0.0f);
        ViewUtil.setViewCorner(this.videoPlayerScene, getResources().getDimension(R.dimen.recommend_video_radius));
        this.videoPlayerSceneFace1 = this.root.findViewById(R.id.video_player_scene_face_1);
        this.videoPlayerSceneFace2 = this.root.findViewById(R.id.video_player_scene_face_2);
        View viewFindViewById2 = this.root.findViewById(R.id.video_player_scene_container);
        this.videoPlayerSceneContainer = viewFindViewById2;
        updatePlayerByConfig(viewFindViewById2);
    }

    private void initNestedScrollView() {
        updateDisplayPointByConfig();
        NestedScrollView nestedScrollView = (NestedScrollView) this.root.findViewById(R.id.nested_scroll_view);
        this.nestedScrollView = nestedScrollView;
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$tcXWZoY-quBuW2vmD3jskHpCCgE
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                this.f$0.lambda$initNestedScrollView$0$RecommendFragment(view, i, i2, i3, i4);
            }
        });
    }

    public /* synthetic */ void lambda$initNestedScrollView$0$RecommendFragment(View view, int i, int i2, int i3, int i4) {
        changeTitleDisplay(this.point);
        MLog.v(TAG, "point = " + this.point);
        int[] iArr = {0, 0};
        this.videoPlayerFeature.getLocationInWindow(iArr);
        this.isStartVideoPlayerFeature = iArr[1] > 0;
        int[] iArr2 = {0, 0};
        this.videoPlayerScene.getLocationInWindow(iArr2);
        this.isStartVideoPlayerScene = iArr2[1] < this.point.y / 2 && iArr2[1] > 0;
        startPlay();
    }

    private void changeTitleDisplay(Point point) {
        int[] iArr = {0, 0};
        LinearLayout linearLayout = this.layoutScene;
        if (linearLayout == null) {
            return;
        }
        linearLayout.getLocationInWindow(iArr);
        if (iArr[1] < point.y / 2) {
            isFeatureSelected(false);
            isSceneSelected(true);
        } else {
            isFeatureSelected(true);
            isSceneSelected(false);
        }
    }

    private void initTitle() {
        this.titleFeature = (RelativeLayout) this.root.findViewById(R.id.recommend_title_feature);
        TextView textView = (TextView) this.root.findViewById(R.id.feature_title_name);
        this.nameFeature = textView;
        textView.setTag(getString(R.string.recommend_title_feature_tag));
        this.imageFeature = (ImageView) this.root.findViewById(R.id.feature_title_image);
        this.signFeature = this.root.findViewById(R.id.feature_title_sign);
        this.titleFeature.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$gRSAhQAYBoOR0CyV29WwGbypGHM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initTitle$1$RecommendFragment(view);
            }
        });
        isFeatureSelected(true);
        this.titleScene = (RelativeLayout) this.root.findViewById(R.id.recommend_title_scene);
        TextView textView2 = (TextView) this.root.findViewById(R.id.scene_title_name);
        this.nameScene = textView2;
        textView2.setTag(getString(R.string.recommend_title_scene_tag));
        this.imageScene = (ImageView) this.root.findViewById(R.id.scene_title_image);
        this.signScene = this.root.findViewById(R.id.scene_title_sign);
        this.titleScene.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$6u1C9FiqZKzN2THWmiq7scrkuws
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initTitle$2$RecommendFragment(view);
            }
        });
        isSceneSelected(false);
        updateTitleByConfig(getResources().getConfiguration());
    }

    public /* synthetic */ void lambda$initTitle$1$RecommendFragment(View view) {
        if (this.nestedScrollView.getVisibility() == 0) {
            this.nestedScrollView.smoothScrollTo(this.layoutFeature.getLeft(), this.layoutFeature.getTop());
        } else {
            isFeatureSelected(true);
            isSceneSelected(false);
        }
    }

    public /* synthetic */ void lambda$initTitle$2$RecommendFragment(View view) {
        if (this.nestedScrollView.getVisibility() == 0) {
            this.nestedScrollView.smoothScrollTo(this.layoutScene.getLeft(), this.layoutScene.getTop());
        } else {
            isFeatureSelected(false);
            isSceneSelected(true);
        }
    }

    private void isFeatureSelected(boolean isSelected) {
        if (isSelected) {
            this.nameFeature.setTypeface(Typeface.DEFAULT_BOLD);
            this.nameFeature.setTextColor(this.context.getResources().getColor(R.color.recommend_item_header_text_selected, null));
            this.imageFeature.setBackground(ContextCompat.getDrawable(this.context, R.drawable.icon_recommend_feature_selected));
            this.signFeature.setBackgroundColor(this.context.getResources().getColor(R.color.recommend_title_sign_selected, null));
            return;
        }
        this.nameFeature.setTypeface(Typeface.DEFAULT);
        this.nameFeature.setTextColor(this.context.getResources().getColor(R.color.recommend_item_header_text_normal, null));
        this.imageFeature.setBackground(ContextCompat.getDrawable(this.context, R.drawable.icon_recommend_feature_normal));
        this.signFeature.setBackgroundColor(MApp.context.getResources().getColor(R.color.recommend_title_sign_normal, null));
    }

    private void isSceneSelected(boolean isSelected) {
        if (isSelected) {
            this.nameScene.setTypeface(Typeface.DEFAULT_BOLD);
            this.nameScene.setTextColor(this.context.getResources().getColor(R.color.recommend_item_header_text_selected, null));
            this.imageScene.setBackground(ContextCompat.getDrawable(this.context, R.drawable.icon_recommend_scene_selected));
            this.signScene.setBackgroundColor(MApp.context.getResources().getColor(R.color.recommend_title_sign_selected, null));
            return;
        }
        this.nameScene.setTypeface(Typeface.DEFAULT);
        this.nameScene.setTextColor(this.context.getResources().getColor(R.color.recommend_item_header_text_normal, null));
        this.imageScene.setBackground(ContextCompat.getDrawable(this.context, R.drawable.icon_recommend_scene_normal));
        this.signScene.setBackgroundColor(MApp.context.getResources().getColor(R.color.recommend_title_sign_normal, null));
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MLog.v(TAG, "onResume");
        if (isHidden()) {
            return;
        }
        startPlay();
    }

    private void startPlay() {
        String str = TAG;
        MLog.v(str, "isStartVideoPlayerFeature = " + this.isStartVideoPlayerFeature + ", isStartVideoPlayerScene = " + this.isStartVideoPlayerScene);
        this.uiHandler.removeMessages(2);
        if (this.isStartVideoPlayerFeature) {
            MLog.v(str, "111");
            pauseVideoPlayerScene();
            if (this.videoPlayerFeature != null) {
                if (!TextUtils.isEmpty(this.videoPathFeature)) {
                    this.videoPlayerFeature.setTopVideoPath(this.videoPathFeature);
                    this.videoPlayerFeature.setDataPath(this.videoPathFeature);
                }
                UIHandler uIHandler = this.uiHandler;
                uIHandler.sendMessageDelayed(uIHandler.obtainMessage(2, this.videoPlayerFeature), 200L);
                return;
            }
            return;
        }
        if (this.isStartVideoPlayerScene) {
            MLog.v(str, "222");
            pauseVideoPlayerFeature();
            if (this.videoPlayerScene != null) {
                if (!TextUtils.isEmpty(this.videoPathScene)) {
                    this.videoPlayerScene.setTopVideoPath(this.videoPathScene);
                    this.videoPlayerScene.setDataPath(this.videoPathScene);
                }
                UIHandler uIHandler2 = this.uiHandler;
                uIHandler2.sendMessageDelayed(uIHandler2.obtainMessage(2, this.videoPlayerScene), 200L);
                return;
            }
            return;
        }
        MLog.v(str, "333");
        pauseVideoPlayerFeature();
        pauseVideoPlayerScene();
    }

    private void pausePlay() {
        MLog.d(TAG, "pausePlay");
        this.uiHandler.removeMessages(2);
        pauseVideoPlayerScene();
        pauseVideoPlayerFeature();
    }

    private void pauseVideoPlayerScene() {
        VideoPlayer videoPlayer = this.videoPlayerScene;
        if (videoPlayer == null) {
            return;
        }
        videoPlayer.setTopVideoPath("");
        this.videoPlayerScene.pause();
    }

    private void pauseVideoPlayerFeature() {
        VideoPlayer videoPlayer = this.videoPlayerFeature;
        if (videoPlayer == null) {
            return;
        }
        videoPlayer.setTopVideoPath("");
        this.videoPlayerFeature.pause();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        pausePlay();
        super.onPause();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MLog.v(TAG, "onHiddenChanged hidden = " + hidden);
        if (hidden) {
            pausePlay();
        } else {
            startPlay();
        }
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData) {
        super.showData(pageData);
        this.nestedScrollView.setVisibility(0);
        setVideoPlayerData(pageData.getClassifyData());
        setRecycleViewData(pageData.getClassifyData());
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void showError() {
        super.showError();
        this.nestedScrollView.setVisibility(4);
    }

    private void setVideoPlayerData(List<ClassifyData> classifyData) {
        if (classifyData == null || classifyData.size() <= 0 || classifyData.get(0) == null || classifyData.get(0).getContentItems() == null || classifyData.get(0).getContentItems().size() <= 0 || classifyData.get(0).getContentItems().get(0) == null) {
            MLog.v(TAG, "no video data to show");
            return;
        }
        for (final ClassifyData classifyData2 : classifyData) {
            String modelType = classifyData2.getModelType();
            final ContentItem contentItem = classifyData2.getContentItems().get(0);
            MLog.d(TAG, "setVideoPlayerData:" + contentItem.getPath());
            if (this.nameFeature.getTag().equals(modelType)) {
                String path = contentItem.getPath();
                this.videoPathFeature = path;
                this.videoPlayerFeature.setTopVideoPath(path);
                NormalDataAdapter.cacheData.put(classifyData2.getClassify(), classifyData2);
                this.videoPlayerFeature.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$hAxJo51qzUWZ2__Y5GS-WyT4lsU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setVideoPlayerData$3$RecommendFragment(contentItem);
                    }
                }, 100);
                this.videoPlayerFeature.setVideoTitle(contentItem.getHeader());
                this.videoPlayerFeature.setLooping(true);
                this.videoPlayerFeatureFace1.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$92lwTVwmTFQU2NOhEcO2ypzm7rc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setVideoPlayerData$4$RecommendFragment(classifyData2, view);
                    }
                });
                this.videoPlayerFeatureFace2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$84FHu5_Uuo8zsH0vAnJTMyItFBs
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setVideoPlayerData$5$RecommendFragment(classifyData2, view);
                    }
                });
                LocalPathUtil.getLocalPath(this.context, contentItem.getCoverPath(), null);
            } else if (this.nameScene.getTag().equals(modelType)) {
                this.videoPathScene = contentItem.getPath();
                this.videoPlayerScene.setTopVideoPath("");
                NormalDataAdapter.cacheData.put(classifyData2.getClassify(), classifyData2);
                this.videoPlayerScene.setDataPath(contentItem.getPath());
                this.videoPlayerScene.setVideoTitle(contentItem.getHeader());
                this.videoPlayerScene.setLooping(true);
                this.videoPlayerSceneFace1.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$ILu3tfQWihzgLiXmK28U7Zv8wLQ
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setVideoPlayerData$6$RecommendFragment(classifyData2, view);
                    }
                });
                this.videoPlayerSceneFace2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$XvorFx3iyDhXHqFhpWnEN37tUzc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setVideoPlayerData$7$RecommendFragment(classifyData2, view);
                    }
                });
                LocalPathUtil.getLocalPath(this.context, contentItem.getCoverPath(), null);
            }
        }
    }

    public /* synthetic */ void lambda$setVideoPlayerData$3$RecommendFragment(ContentItem contentItem) {
        this.videoPlayerFeature.setDataPath(contentItem.getPath());
    }

    public /* synthetic */ void lambda$setVideoPlayerData$4$RecommendFragment(ClassifyData classifyData, View view) {
        doClickVideoPlayer(classifyData);
    }

    public /* synthetic */ void lambda$setVideoPlayerData$5$RecommendFragment(ClassifyData classifyData, View view) {
        doClickVideoPlayer(classifyData);
    }

    public /* synthetic */ void lambda$setVideoPlayerData$6$RecommendFragment(ClassifyData classifyData, View view) {
        doClickVideoPlayer(classifyData);
    }

    public /* synthetic */ void lambda$setVideoPlayerData$7$RecommendFragment(ClassifyData classifyData, View view) {
        doClickVideoPlayer(classifyData);
    }

    private void doClickVideoPlayer(ClassifyData data) {
        ContentItem contentItem = data.getContentItems().get(0);
        NormalDataAdapter.startMediaActivity(data.getClassify(), contentItem.getHeader(), contentItem.getCoverPath(), contentItem.getPath(), ContentItem.ContentItemType.VIDEO, 0, data.getClassify());
    }

    private void setRecycleViewData(List<ClassifyData> classifyData) {
        for (ClassifyData classifyData2 : classifyData) {
            String modelType = classifyData2.getModelType();
            if (this.nameFeature.getTag().equals(modelType)) {
                setAdapterData((RecommendVerticalAdapter) this.recycleViewVerticalFeature.getAdapter(), classifyData2);
                setAdapterData((RecommendHorizontalAdapter) this.recycleViewHorizontalFeature.getAdapter(), classifyData2);
            } else if (this.nameScene.getTag().equals(modelType)) {
                setAdapterData((RecommendVerticalAdapter) this.recycleViewVerticalScene.getAdapter(), classifyData2);
                setAdapterData((RecommendHorizontalAdapter) this.recycleViewHorizontalScene.getAdapter(), classifyData2);
            }
        }
    }

    private void setAdapterData(IAdapter adapter, ClassifyData classifyData) {
        if (adapter != null) {
            adapter.setData(classifyData, this.orientation);
        }
    }

    static class UIHandler extends Handler {
        public UIHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 1) {
                if (i != 2) {
                    MLog.v(RecommendFragment.TAG, "no this message.");
                } else {
                    ((VideoPlayer) msg.obj).start();
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateTitleByConfig(newConfig);
        updateDisplayPointByConfig();
        this.orientation = getResources().getConfiguration().orientation;
        updateDefaultColumnAndRowByConfig();
        updatePlayerByConfig(this.videoPlayerFeatureContainer);
        updatePlayerByConfig(this.videoPlayerSceneContainer);
        updateScrollViewByConfig();
        updateDataAdapter();
        updateNetState();
    }

    private void updateTitleByConfig(Configuration configuration) {
        this.nameFeature.setText(getString(R.string.recommend_title_feature));
        this.nameScene.setText(getString(R.string.recommend_title_scene));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.titleFeature.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.titleScene.getLayoutParams();
        if (configuration.getLocales().toLanguageTags().startsWith(Locale.ENGLISH.toLanguageTag())) {
            layoutParams.width = (int) getResources().getDimension(R.dimen.recommend_title_width_en);
            layoutParams2.width = (int) getResources().getDimension(R.dimen.recommend_title_width_en);
        } else if (configuration.getLocales().toLanguageTags().startsWith(Locale.CHINESE.toLanguageTag())) {
            layoutParams.width = (int) getResources().getDimension(R.dimen.recommend_title_width);
            layoutParams2.width = (int) getResources().getDimension(R.dimen.recommend_title_width);
        }
        this.titleFeature.setLayoutParams(layoutParams);
        this.titleScene.setLayoutParams(layoutParams2);
    }

    private void updateDefaultColumnAndRowByConfig() {
        if (ViewUtil.isPort(this.orientation)) {
            RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT = 0;
            HorizontalAdapter.DEFAULT_HORIZONTAL_COLUMN_COUNT = 2;
        } else {
            RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT = 2;
            HorizontalAdapter.DEFAULT_HORIZONTAL_COLUMN_COUNT = 4;
        }
    }

    private void updatePlayerByConfig(View videoPlayer) {
        ViewGroup.LayoutParams layoutParams = videoPlayer.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            layoutParams.width = this.context.getResources().getDimensionPixelOffset(R.dimen.recommend_video_width_port);
            layoutParams.height = this.context.getResources().getDimensionPixelOffset(R.dimen.recommend_video_height_port);
        } else {
            layoutParams.width = this.context.getResources().getDimensionPixelOffset(R.dimen.recommend_video_width);
            layoutParams.height = this.context.getResources().getDimensionPixelOffset(R.dimen.recommend_video_height);
        }
        videoPlayer.setLayoutParams(layoutParams);
    }

    private void updateDataAdapter() {
        ArrayList arrayList = new ArrayList();
        ClassifyData classifyData = NormalDataAdapter.cacheData.get(this.nameFeature.getText());
        if (classifyData != null) {
            arrayList.add(classifyData);
            setRecycleViewData(arrayList);
        }
        arrayList.clear();
        ClassifyData classifyData2 = NormalDataAdapter.cacheData.get(this.nameScene.getText());
        if (classifyData2 != null) {
            arrayList.add(classifyData2);
            setRecycleViewData(arrayList);
        }
    }

    private void updateDisplayPointByConfig() {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        this.point = point;
        defaultDisplay.getSize(point);
        changeTitleDisplay(this.point);
    }

    private void updateScrollViewByConfig() {
        if (this.nameFeature.getTypeface().equals(Typeface.DEFAULT_BOLD)) {
            this.titleFeature.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$vgfSEVydUnIeGavQzJ7zvmP0lQQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateScrollViewByConfig$8$RecommendFragment();
                }
            }, 200L);
        } else if (this.nameScene.getTypeface().equals(Typeface.DEFAULT_BOLD)) {
            this.titleScene.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendFragment$mnVdLEhj9kX29_MSbdnsqfloEdk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateScrollViewByConfig$9$RecommendFragment();
                }
            }, 200L);
        }
    }

    public /* synthetic */ void lambda$updateScrollViewByConfig$8$RecommendFragment() {
        this.titleFeature.performClick();
    }

    public /* synthetic */ void lambda$updateScrollViewByConfig$9$RecommendFragment() {
        this.titleScene.performClick();
    }

    public void updateNetState() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        TextView textView = (TextView) this.root.findViewById(R.id.error_text);
        textView.setText(R.string.state_net_error);
        textView.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        ((ImageView) this.root.findViewById(R.id.error_icon)).setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.state_net_error));
        TextView textView2 = (TextView) this.root.findViewById(R.id.loading_text);
        textView2.setText(R.string.state_loading);
        textView2.setTextColor(ResourceUtil.getColor(this.context, R.attr.state_text));
        this.loadingIcon = (ImageView) this.root.findViewById(R.id.loading_icon);
        this.loadingIcon.setImageDrawable(ResourceUtil.getDrawable(this.context, R.attr.loading_54));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.netErrorState.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.netLoadingState.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top_port);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top_port);
        } else {
            dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.state_net_error_top);
            dimensionPixelOffset2 = this.context.getResources().getDimensionPixelOffset(R.dimen.state_loading_top);
        }
        layoutParams.setMargins(layoutParams.leftMargin, dimensionPixelOffset, layoutParams.rightMargin, layoutParams.bottomMargin);
        layoutParams2.setMargins(layoutParams2.leftMargin, dimensionPixelOffset2, layoutParams2.rightMargin, layoutParams2.bottomMargin);
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void loadData() {
        if (notLoadWhenNotShowVideo()) {
            return;
        }
        super.loadData();
        this.presenter.loadData(Page.RECOMMEND);
        new Presenter(null).loadData(Page.JSON_DATA);
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment
    public boolean notLoadWhenNotShowVideo() {
        return Utils.notShowVideo(MApp.context);
    }
}
