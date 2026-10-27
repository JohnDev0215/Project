package com.byd.auto_emanual;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.fragment.Classify;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.fragment.NormalDataAdapter;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.fragment.ZipInfo;
import com.byd.auto_emanual.js.DiLinkDev;
import com.byd.auto_emanual.util.DialogUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.UIStyleUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.PlayController;
import com.byd.auto_emanual.video.VideoPlayer;
import com.byd.auto_emanual.video.util.ZoomInOutHelper;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MediaActivity extends AppCompatActivity implements VideoPlayer.BackClickListener, IStopOrGo {
    private static final String TAG = "MediaActivity";
    private ClassifyAdapter classifyAdapter;
    private RelativeLayout classifyContainer;
    private View classifyFooter;
    private View classifyFooterPort;
    private LinearLayoutManager classifyLayoutManager;
    private View classifyLine;
    private ConnectivityManager connectivityManager;
    private final DiLinkDev deLinkDev = new DiLinkDev();
    private TextView enterManual;
    private ImageView iconMediaTextBack;
    private RecyclerView imageText;
    private ImageTextAdapter imageTextAdapter;
    private RelativeLayout imageTextBack;
    private LinearLayout imageTextContainer;
    private TextView imageTextTitle;
    private boolean isForeground;
    private boolean isPlaying;
    private int orientation;
    private RecyclerView recyclerViewClassify;
    private boolean reloadDataByNetChanged;
    private int selectedPosition;
    private String source;
    private AlertDialog stopOrGoDialog;
    private String title;
    private TextView titleClassify;
    private ContentItem.ContentItemType type;
    private VideoPlayer videoPlayer;
    private RelativeLayout videoPlayerParent;
    private WebView webView;
    private RelativeLayout webViewContainer;

    static /* synthetic */ boolean lambda$initWebView$2(View view) {
        return true;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public Activity getSelf() {
        return this;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(UIStyleUtil.getTheme());
        MLog.d(TAG, "onCreate");
        this.orientation = getResources().getConfiguration().orientation;
        ActivityManager.getInstance().addActivity(this);
        GearboxListener.getInstance().register(this);
        registerNetworkCallback();
        setContentView(R.layout.activity_media_view);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.videoPlayerParent = (RelativeLayout) findViewById(R.id.video_player_parent);
        initVideoPlayer();
        initImageText();
        initWebView();
        initClassify(intent);
        updateColorByConfig();
        updateContent(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initClassify(intent);
        updateContent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContent(Intent intent) {
        getParamFromIntent(intent);
        updateContentImpl(true);
    }

    public void updateContentImpl(boolean isReset) {
        if (this.type.equals(ContentItem.ContentItemType.VIDEO)) {
            updateVideoPlayer(isReset);
        } else {
            updateImageText();
        }
    }

    private void getParamFromIntent(Intent intent) {
        this.type = ContentItem.ContentItemType.valueOf(intent.getSerializableExtra(Parameters.CONTENT_TYPE).toString());
        String stringExtra = intent.getStringExtra("1");
        this.title = stringExtra;
        if (!TextUtils.isEmpty(stringExtra)) {
            this.title = this.title.replace("\n", " ");
        }
        this.source = intent.getStringExtra(Parameters.CONTENT_ITEM_PATH);
        MLog.d(TAG, "getParamFromIntent\nselectedPosition = " + this.selectedPosition + "\ntype = " + this.type + "\ntitle = " + this.title + "\nsource = " + this.source);
    }

    private void updateImageText() {
        String str;
        this.videoPlayer.reset();
        this.videoPlayer.setVisibility(8);
        this.imageTextContainer.setVisibility(0);
        this.imageTextTitle.setText(this.title);
        if (this.type.equals(ContentItem.ContentItemType.IMAGE)) {
            this.imageText.setVisibility(0);
            this.webViewContainer.setVisibility(8);
            this.imageTextAdapter.setData(HtmlUtil.parse(this.source));
            return;
        }
        if (this.type.equals(ContentItem.ContentItemType.HTML)) {
            this.imageText.setVisibility(8);
            this.webViewContainer.setVisibility(0);
            String str2 = "content" + File.separator + "pages" + File.separator + this.source;
            if (TextUtils.isEmpty(NormalDataAdapter.localAddress) || TextUtils.isEmpty(str2)) {
                str = NormalDataAdapter.netAddress + str2;
            } else {
                String str3 = NormalDataAdapter.localAddress + File.separator + str2;
                if (str3.contains(".html#")) {
                    str3 = str3.split("html#")[0] + "html";
                    MLog.d(TAG, "set localPath = " + str3);
                }
                if (!new File(str3).exists()) {
                    str = NormalDataAdapter.netAddress + str2;
                } else {
                    str = ManualFragment.LOCAL_FILE_PREFIX + NormalDataAdapter.localAddress + File.separator + str2;
                }
            }
            MLog.v(TAG, "updateImageText " + ContentItem.ContentItemType.HTML + " " + str);
            this.webView.loadUrl(str);
        }
    }

    private void initImageText() {
        this.imageTextContainer = (LinearLayout) findViewById(R.id.media_image_text_container);
        this.imageTextTitle = (TextView) findViewById(R.id.media_image_text_title);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.media_image_text_back);
        this.imageTextBack = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MediaActivity$64ZD3XA-y-I2oP1nMxemGxdSsHk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initImageText$0$MediaActivity(view);
            }
        });
        this.iconMediaTextBack = (ImageView) findViewById(R.id.icon_media_text_back);
        this.imageText = (RecyclerView) findViewById(R.id.media_image_text);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.imageTextAdapter = new ImageTextAdapter();
        this.imageText.setLayoutManager(linearLayoutManager);
        this.imageText.setAdapter(this.imageTextAdapter);
        updateImageTextByConfig();
    }

    public /* synthetic */ void lambda$initImageText$0$MediaActivity(View view) {
        onBackClick();
    }

    private void initWebView() {
        TextView textView = (TextView) findViewById(R.id.media_enter_manual);
        this.enterManual = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MediaActivity$GkWY93lFVLNcd9__pyllhOFcZ_4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initWebView$1$MediaActivity(view);
            }
        });
        this.webViewContainer = (RelativeLayout) findViewById(R.id.web_view_container);
        WebView webView = (WebView) findViewById(R.id.media_web_view);
        this.webView = webView;
        WebSettings settings = webView.getSettings();
        settings.setBlockNetworkImage(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAllowFileAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        this.webView.setBackgroundColor(0);
        this.webView.setOverScrollMode(2);
        this.webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$MediaActivity$A4U_b4I1PrE90FbHBnha_WeyHeM
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return MediaActivity.lambda$initWebView$2(view);
            }
        });
        this.webView.addJavascriptInterface(this.deLinkDev, DiLinkDev.JS_INTERFACE_NAME);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.byd.auto_emanual.MediaActivity.1
        });
        updateWebViewByConfig(getResources().getConfiguration());
        updateLocalAddress();
    }

    public /* synthetic */ void lambda$initWebView$1$MediaActivity(View view) {
        doEnterManualClick();
    }

    private void doEnterManualClick() {
        SharedPrefUtil.save(this, Parameters.CONTENT_ENTER_MANUAL_URL, this.source);
        this.videoPlayerParent.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MediaActivity$r1VR3dn6dDE6qeRopk0QSoBRNlk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.finish();
            }
        }, 300L);
    }

    private void updateLocalAddress() {
        ZipInfo zipInfo;
        if (!TextUtils.isEmpty(NormalDataAdapter.localAddress) || (zipInfo = ClassifyDbUtil.getInstance(getApplicationContext()).queryByPage(Page.MANUAL).getZipInfo()) == null) {
            return;
        }
        NormalDataAdapter.localAddress = zipInfo.getTargetFile();
    }

    private void updateWebViewByConfig(Configuration newConfig) {
        int i = newConfig.orientation;
        this.deLinkDev.setOrientation(i);
        this.webView.loadUrl("javascript:setOrientation(" + i + ")");
        int i2 = (newConfig.uiMode & 48) == 32 ? 1 : 0;
        this.deLinkDev.setTheme(i2);
        this.webView.loadUrl("javascript:setTheme(" + i2 + ")");
        String standardLanguageCode = Utils.getStandardLanguageCode();
        this.deLinkDev.setLanguage(standardLanguageCode);
        this.webView.loadUrl("javascript:setLanguage(" + standardLanguageCode + ")");
        MLog.d(TAG, "updateWebViewByConfig orientation = " + i + ", theme = " + i2 + ", language = " + standardLanguageCode);
    }

    private void updateImageTextByConfig() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.imageTextContainer.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.media_image_text_margin_end_port);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.video_player_margin_bottom_port);
            layoutParams.removeRule(16);
        } else {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.media_image_text_margin_end);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.media_image_text_margin_bottom);
            layoutParams.addRule(16, R.id.classify_container);
        }
        layoutParams.setMarginEnd(dimensionPixelOffset);
        layoutParams.bottomMargin = dimensionPixelOffset2;
        this.imageTextContainer.setLayoutParams(layoutParams);
        if (ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
            this.iconMediaTextBack.setRotation(180.0f);
        } else {
            this.iconMediaTextBack.setRotation(0.0f);
        }
    }

    private void initVideoPlayer() {
        VideoPlayer videoPlayer = (VideoPlayer) findViewById(R.id.video_player);
        this.videoPlayer = videoPlayer;
        videoPlayer.setBackClickListener(this);
        updatePlayByConfig();
    }

    private void updatePlayByConfig() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        int dimensionPixelOffset3;
        int dimensionPixelOffset4;
        int dimensionPixelOffset5;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.videoPlayer.getLayoutParams();
        if (PlayController.ZOOM_IN.equals(this.videoPlayer.getZoomTag())) {
            if (ViewUtil.isPort(this.orientation)) {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.video_player_margin_top_port);
                dimensionPixelOffset4 = getResources().getDimensionPixelOffset(R.dimen.video_player_margin_bottom_port);
                dimensionPixelOffset5 = getResources().getDimensionPixelOffset(R.dimen.video_player_width_port);
                layoutParams.removeRule(16);
                layoutParams.topMargin = dimensionPixelOffset;
                layoutParams.bottomMargin = dimensionPixelOffset4;
                layoutParams.width = dimensionPixelOffset5;
                this.videoPlayer.setLayoutParams(layoutParams);
                ZoomInOutHelper.updateVideoViewSize(this.videoPlayer, PlayController.ZOOM_IN);
            }
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.video_player_margin_top);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.video_player_margin_bottom);
            dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.video_player_width);
            layoutParams.addRule(16, R.id.classify_container);
        } else {
            dimensionPixelOffset = layoutParams.topMargin;
            dimensionPixelOffset2 = layoutParams.bottomMargin;
            dimensionPixelOffset3 = layoutParams.width;
            if (ViewUtil.isPort(this.orientation)) {
                layoutParams.removeRule(16);
            } else {
                layoutParams.addRule(16, R.id.classify_container);
            }
        }
        dimensionPixelOffset4 = dimensionPixelOffset2;
        dimensionPixelOffset5 = dimensionPixelOffset3;
        layoutParams.topMargin = dimensionPixelOffset;
        layoutParams.bottomMargin = dimensionPixelOffset4;
        layoutParams.width = dimensionPixelOffset5;
        this.videoPlayer.setLayoutParams(layoutParams);
        ZoomInOutHelper.updateVideoViewSize(this.videoPlayer, PlayController.ZOOM_IN);
    }

    private void initClassify(Intent intent) {
        this.classifyContainer = (RelativeLayout) findViewById(R.id.classify_container);
        String stringExtra = intent.getStringExtra(Parameters.CONTENT_CLASSIFY);
        this.selectedPosition = intent.getIntExtra(Parameters.CONTENT_POSITION, 0);
        this.titleClassify = (TextView) findViewById(R.id.classify_title);
        this.recyclerViewClassify = (RecyclerView) findViewById(R.id.classify_recycler_view);
        this.classifyFooter = findViewById(R.id.classify_footer_background);
        this.classifyFooterPort = findViewById(R.id.classify_footer_background_port);
        ClassifyAdapter classifyAdapter = new ClassifyAdapter(this, this.orientation);
        this.classifyAdapter = classifyAdapter;
        classifyAdapter.setClickListener(new ClickListener());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.classifyLayoutManager = linearLayoutManager;
        this.recyclerViewClassify.setLayoutManager(linearLayoutManager);
        this.recyclerViewClassify.setAdapter(this.classifyAdapter);
        ClassifyData classifyData = NormalDataAdapter.cacheData.get(stringExtra);
        if (classifyData != null && classifyData.getContentItems() != null) {
            this.titleClassify.setText(getString(R.string.classify_string, new Object[]{getClassifyText(stringExtra), Integer.valueOf(NormalDataAdapter.cacheData.get(stringExtra).getContentItems().size())}));
            this.classifyAdapter.setData(NormalDataAdapter.cacheData.get(stringExtra).getContentItems(), this.selectedPosition, this.orientation);
        } else {
            this.titleClassify.setText(getString(R.string.classify_string, new Object[]{getClassifyText(stringExtra), 0}));
            this.classifyAdapter.setData(new ArrayList(), this.selectedPosition, this.orientation);
        }
        this.classifyLine = findViewById(R.id.classify_line);
        updateClassifyByConfig();
    }

    private void updateClassifyByConfig() {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        int dimensionPixelOffset3;
        int dimensionPixelOffset4;
        this.classifyLayoutManager.setOrientation(!ViewUtil.isPort(this.orientation) ? 1 : 0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.classifyContainer.getLayoutParams();
        if (ViewUtil.isPort(this.orientation)) {
            layoutParams.removeRule(17);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.classify_margin_top_port);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.classify_margin_start_port);
            dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.classify_margin_bottom_port);
            dimensionPixelOffset4 = 0;
        } else {
            layoutParams.addRule(17, R.id.fix_point);
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.classify_margin_top);
            dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.classify_margin_start);
            dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.classify_margin_bottom);
            dimensionPixelOffset4 = getResources().getDimensionPixelOffset(R.dimen.classify_margin_end);
        }
        layoutParams.topMargin = dimensionPixelOffset;
        layoutParams.bottomMargin = dimensionPixelOffset3;
        layoutParams.setMarginStart(dimensionPixelOffset2);
        layoutParams.setMarginEnd(dimensionPixelOffset4);
        this.classifyContainer.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.recyclerViewClassify.getLayoutParams();
        layoutParams2.setMarginEnd(ViewUtil.isPort(this.orientation) ? 0 : getResources().getDimensionPixelOffset(R.dimen.classify_margin_start));
        this.recyclerViewClassify.setLayoutParams(layoutParams2);
        if (ViewUtil.isPort(this.orientation)) {
            this.classifyLine.getLayoutParams().width = getResources().getDimensionPixelOffset(R.dimen.classify_line_length_port);
            this.recyclerViewClassify.setHorizontalScrollBarEnabled(true);
        } else {
            this.classifyLine.getLayoutParams().width = getResources().getDimensionPixelOffset(R.dimen.classify_line_length);
            this.recyclerViewClassify.setVerticalScrollBarEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (ViewUtil.isPort(this.orientation)) {
                RecyclerView recyclerView = this.recyclerViewClassify;
                recyclerView.setHorizontalScrollbarThumbDrawable(ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.classify_scrollbar_color));
            } else {
                RecyclerView recyclerView2 = this.recyclerViewClassify;
                recyclerView2.setVerticalScrollbarThumbDrawable(ContextCompat.getDrawable(recyclerView2.getContext(), R.drawable.classify_scrollbar_color));
            }
        }
        updateClassifyFooter();
    }

    private void updateClassifyFooter() {
        if (ViewUtil.isPort(this.orientation)) {
            this.classifyFooter.setVisibility(4);
            this.classifyFooterPort.setVisibility(0);
            View view = this.classifyFooterPort;
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.classify_footer_background_port));
            return;
        }
        this.classifyFooter.setVisibility(0);
        View view2 = this.classifyFooter;
        view2.setBackground(ContextCompat.getDrawable(view2.getContext(), R.drawable.classify_footer_background));
        this.classifyFooterPort.setVisibility(4);
    }

    private String getClassifyText(String classify) {
        if (Classify.FEATURE.equals(classify)) {
            return getString(R.string.recommend_title_feature);
        }
        if (Classify.START.equals(classify)) {
            return getString(R.string.recommend_title_scene);
        }
        return Classify.HOT.equals(classify) ? getString(R.string.search_hot_text) : classify;
    }

    private void updateVideoPlayer(boolean isReset) {
        this.imageTextContainer.setVisibility(8);
        this.videoPlayer.setVisibility(0);
        if (isReset) {
            this.videoPlayer.setTopVideoPath("");
            this.videoPlayer.reset();
            this.videoPlayer.setVideoViewForeground(ContextCompat.getDrawable(this, R.drawable.video_loading_background));
        }
        this.videoPlayer.setVideoTitle(this.title);
        this.videoPlayer.setTopVideoPath(this.source);
        this.videoPlayer.setDataPath(this.source);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        VideoPlayer videoPlayer;
        super.onResume();
        MLog.d(TAG, "onResume = " + this.isPlaying);
        if (this.type.equals(ContentItem.ContentItemType.VIDEO) && (videoPlayer = this.videoPlayer) != null && this.isPlaying) {
            videoPlayer.start();
        }
        this.recyclerViewClassify.scrollToPosition(this.selectedPosition);
        this.reloadDataByNetChanged = true;
        updateContentImpl(false);
        showStopOrGo();
        this.isForeground = true;
    }

    private void showStopOrGo() {
        RelativeLayout relativeLayout = this.videoPlayerParent;
        if (relativeLayout == null || this.stopOrGoDialog != null) {
            return;
        }
        relativeLayout.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.-$$Lambda$MediaActivity$j65HGHUVZeVvQDuhA2mQsm8vwDk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showStopOrGo$3$MediaActivity();
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$showStopOrGo$3$MediaActivity() {
        this.stopOrGoDialog = DialogUtil.showStopOrGo(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        VideoPlayer videoPlayer;
        MLog.v(TAG, "onPause");
        if (this.type.equals(ContentItem.ContentItemType.VIDEO) && (videoPlayer = this.videoPlayer) != null) {
            this.isPlaying = videoPlayer.isPlaying();
            this.videoPlayer.pause();
        }
        super.onPause();
        this.reloadDataByNetChanged = false;
        this.isForeground = false;
    }

    @Override // com.byd.auto_emanual.video.VideoPlayer.BackClickListener
    public void onBackClick() {
        super.onBackPressed();
        MLog.v(TAG, "onBackClick");
    }

    class ClickListener {
        ClickListener() {
        }

        public void onClick(Intent intent) {
            MediaActivity.this.updateContent(intent);
        }
    }

    private void registerNetworkCallback() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        this.connectivityManager = connectivityManager;
        connectivityManager.registerDefaultNetworkCallback(NetStatusCallback.getInstanceWithMediaActivity(this));
    }

    private void unRegisterNetworkCallback() {
        ConnectivityManager connectivityManager = this.connectivityManager;
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(NetStatusCallback.getInstanceNoMediaActivity());
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unRegisterNetworkCallback();
        this.videoPlayer.release();
        ActivityManager.getInstance().deleteActivity(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.orientation = getResources().getConfiguration().orientation;
        updatePlayByConfig();
        updateImageTextByConfig();
        updateWebViewByConfig(newConfig);
        updateClassifyByConfig();
        this.classifyAdapter.updateOrientation(this.orientation);
        updateColorByConfig();
        this.enterManual.setText(getString(R.string.enter_manual));
        updateDialogByConfig();
    }

    private void updateDialogByConfig() {
        AlertDialog alertDialog = this.stopOrGoDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.stopOrGoDialog);
        }
        AlertDialog alertDialog2 = this.stopOrGoDialog;
        if (alertDialog2 == null || !alertDialog2.isShowing()) {
            return;
        }
        DialogUtil.updateDialogTextByConfig(this.stopOrGoDialog, -1, R.string.stop_or_go_detail, -1, -1, -1);
    }

    private void updateColorByConfig() {
        if (ViewUtil.isPort(this.orientation)) {
            RelativeLayout relativeLayout = this.videoPlayerParent;
            relativeLayout.setBackground(ResourceUtil.getDrawable(relativeLayout.getContext(), R.attr.background_port));
        } else {
            RelativeLayout relativeLayout2 = this.videoPlayerParent;
            relativeLayout2.setBackground(ResourceUtil.getDrawable(relativeLayout2.getContext(), R.attr.background_horizontal));
        }
        TextView textView = this.enterManual;
        textView.setBackground(ContextCompat.getDrawable(textView.getContext(), R.drawable.button_confirm_background_shape_selector));
        this.titleClassify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.classify_title_text));
        this.classifyLine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.classify_line));
        this.iconMediaTextBack.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_media_text_back));
        this.imageTextTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.media_image_text_title_text_color));
        this.imageTextAdapter.notifyDataSetChanged();
    }

    public boolean isReloadDataByNetChanged() {
        return this.reloadDataByNetChanged;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && this.videoPlayer.getZoomTag().equals(PlayController.ZOOM_OUT)) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public boolean isForeground() {
        return this.isForeground;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public AlertDialog getStopOrGoDialog() {
        return this.stopOrGoDialog;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public void setStopOrGoDialog(AlertDialog dialog) {
        this.stopOrGoDialog = dialog;
    }

    @Override // com.byd.auto_emanual.IStopOrGo
    public void showStopOrGoDialog(boolean showStopOrGo) {
        if (showStopOrGo) {
            showStopOrGo();
        } else {
            this.stopOrGoDialog = null;
        }
    }
}
