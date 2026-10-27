package com.byd.automultipletheme.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.adapter.PreviewImageAdapter;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.bean.PreviewImage;
import com.byd.automultipletheme.bean.Wallpaper;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.ClickEventUtil;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.base.BaseDialogFragment;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.ActivityPreviewImageBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.fragment.LegitimateDialogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: PreviewImageActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0014H\u0014J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010\u001f\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/byd/automultipletheme/activity/PreviewImageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/PreviewImageAdapter;", "autoTheme", "Lcom/byd/automultipletheme/bean/AutoTheme;", "autoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "binding", "Lcom/byd/automultipletheme/databinding/ActivityPreviewImageBinding;", "dataType", "", IntentString.DATA_LIST, "", "", IntentString.DATA_POSITION, "showAction", "", "applyRudder", "", "initData", "initViews", "moveFloatView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "updateUseBtn", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PreviewImageActivity extends AppCompatActivity {
    private PreviewImageAdapter adapter;
    private AutoTheme autoTheme;
    private AutoWallpaper autoWallpaper;
    private ActivityPreviewImageBinding binding;
    private int dataType;
    private int position;
    private boolean showAction = true;
    private List<String> list = new ArrayList();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPreviewImageBinding activityPreviewImageBindingInflate = ActivityPreviewImageBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityPreviewImageBindingInflate, "inflate(layoutInflater)");
        this.binding = activityPreviewImageBindingInflate;
        applyRudder();
        ActivityPreviewImageBinding activityPreviewImageBinding = this.binding;
        if (activityPreviewImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding = null;
        }
        setContentView(activityPreviewImageBinding.getRoot());
        BarUtils.setNavBarVisibility((Activity) this, false);
        EventBus.getDefault().register(this);
        initViews();
        initData();
    }

    private final void applyRudder() {
        ConstraintSet constraintSet = new ConstraintSet();
        ActivityPreviewImageBinding activityPreviewImageBinding = this.binding;
        ActivityPreviewImageBinding activityPreviewImageBinding2 = null;
        if (activityPreviewImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding = null;
        }
        constraintSet.clone(activityPreviewImageBinding.getRoot());
        ActivityPreviewImageBinding activityPreviewImageBinding3 = this.binding;
        if (activityPreviewImageBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding3 = null;
        }
        constraintSet.clear(activityPreviewImageBinding3.closeBtn.getId(), 1);
        ActivityPreviewImageBinding activityPreviewImageBinding4 = this.binding;
        if (activityPreviewImageBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding4 = null;
        }
        constraintSet.connect(activityPreviewImageBinding4.closeBtn.getId(), 3, 0, 3);
        if (AutoUtils.INSTANCE.isRightRudder()) {
            ActivityPreviewImageBinding activityPreviewImageBinding5 = this.binding;
            if (activityPreviewImageBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding5 = null;
            }
            constraintSet.connect(activityPreviewImageBinding5.closeBtn.getId(), 2, 0, 2);
        } else {
            ActivityPreviewImageBinding activityPreviewImageBinding6 = this.binding;
            if (activityPreviewImageBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding6 = null;
            }
            constraintSet.connect(activityPreviewImageBinding6.closeBtn.getId(), 1, 0, 1);
        }
        ActivityPreviewImageBinding activityPreviewImageBinding7 = this.binding;
        if (activityPreviewImageBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPreviewImageBinding2 = activityPreviewImageBinding7;
        }
        constraintSet.applyTo(activityPreviewImageBinding2.getRoot());
    }

    private final void initData() {
        ActivityPreviewImageBinding activityPreviewImageBinding = null;
        if (this.dataType == 2) {
            AutoWallpaper autoWallpaper = (AutoWallpaper) getIntent().getSerializableExtra(IntentString.DATA_STR);
            this.autoWallpaper = autoWallpaper;
            if (autoWallpaper != null) {
                if (autoWallpaper.getRsType() == 15) {
                    this.list.clear();
                    this.list.add("WALLPAPER_TYPE_DAY_NIGHT");
                } else {
                    this.list.clear();
                    if (ScreenUtils.isLandscape()) {
                        this.list.addAll(autoWallpaper.getImgUrl().getLand());
                    } else {
                        this.list.addAll(autoWallpaper.getImgUrl().getPort());
                    }
                }
                PreviewImageAdapter previewImageAdapter = this.adapter;
                if (previewImageAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    previewImageAdapter = null;
                }
                previewImageAdapter.setData((ArrayList) this.list);
                if (autoWallpaper.getRsId() == DBUtils.INSTANCE.getCurrentWallpaperId()) {
                    updateUseBtn();
                }
            }
        } else {
            AutoTheme autoTheme = (AutoTheme) getIntent().getSerializableExtra(IntentString.DATA_STR);
            this.autoTheme = autoTheme;
            if (autoTheme != null) {
                if (ScreenUtils.isLandscape()) {
                    int modelType = AutoUtils.INSTANCE.getModelType();
                    if (modelType == 2) {
                        this.list.addAll(autoTheme.getImgUrl().getOcean().getLand());
                    } else if (modelType == 3) {
                        this.list.addAll(autoTheme.getImgUrl().getDenza().getLand());
                    } else {
                        this.list.addAll(autoTheme.getImgUrl().getDynasty().getLand());
                    }
                } else {
                    int modelType2 = AutoUtils.INSTANCE.getModelType();
                    if (modelType2 == 2) {
                        this.list.addAll(autoTheme.getImgUrl().getOcean().getPort());
                    } else if (modelType2 == 3) {
                        this.list.addAll(autoTheme.getImgUrl().getDenza().getPort());
                    } else {
                        this.list.addAll(autoTheme.getImgUrl().getDynasty().getPort());
                    }
                }
                PreviewImageAdapter previewImageAdapter2 = this.adapter;
                if (previewImageAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    previewImageAdapter2 = null;
                }
                previewImageAdapter2.setData((ArrayList) this.list);
                if (autoTheme.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                    updateUseBtn();
                }
            }
        }
        ActivityPreviewImageBinding activityPreviewImageBinding2 = this.binding;
        if (activityPreviewImageBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPreviewImageBinding = activityPreviewImageBinding2;
        }
        activityPreviewImageBinding.previewList.setCurrentItem(this.position, false);
    }

    private final void updateUseBtn() {
        ActivityPreviewImageBinding activityPreviewImageBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            ActivityPreviewImageBinding activityPreviewImageBinding2 = this.binding;
            if (activityPreviewImageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding2 = null;
            }
            activityPreviewImageBinding2.useNowBtn.setText(R.string.str_using);
            ActivityPreviewImageBinding activityPreviewImageBinding3 = this.binding;
            if (activityPreviewImageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding3 = null;
            }
            activityPreviewImageBinding3.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.select_color_detail_denza));
            ActivityPreviewImageBinding activityPreviewImageBinding4 = this.binding;
            if (activityPreviewImageBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityPreviewImageBinding = activityPreviewImageBinding4;
            }
            activityPreviewImageBinding.useNowBtn.setEnabled(false);
            return;
        }
        ActivityPreviewImageBinding activityPreviewImageBinding5 = this.binding;
        if (activityPreviewImageBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding5 = null;
        }
        activityPreviewImageBinding5.useNowBtn.setText(R.string.str_using);
        ActivityPreviewImageBinding activityPreviewImageBinding6 = this.binding;
        if (activityPreviewImageBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding6 = null;
        }
        activityPreviewImageBinding6.useNowBtn.setEnabled(false);
        ActivityPreviewImageBinding activityPreviewImageBinding7 = this.binding;
        if (activityPreviewImageBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPreviewImageBinding = activityPreviewImageBinding7;
        }
        activityPreviewImageBinding.useNowBtn.setTextColor(ContextCompat.getColor(this, R.color.textColorSelected));
    }

    private final void initViews() {
        ActivityPreviewImageBinding activityPreviewImageBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            ActivityPreviewImageBinding activityPreviewImageBinding2 = this.binding;
            if (activityPreviewImageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding2 = null;
            }
            activityPreviewImageBinding2.useNowBtn.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_item_button_detail_bg_denza));
            ActivityPreviewImageBinding activityPreviewImageBinding3 = this.binding;
            if (activityPreviewImageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding3 = null;
            }
            activityPreviewImageBinding3.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.use_color_detail_denza));
        }
        this.dataType = getIntent().getIntExtra(IntentString.DATA_TYPE, 0);
        this.position = getIntent().getIntExtra(IntentString.DATA_POSITION, 0);
        this.adapter = new PreviewImageAdapter(this.dataType);
        ActivityPreviewImageBinding activityPreviewImageBinding4 = this.binding;
        if (activityPreviewImageBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding4 = null;
        }
        ViewPager2 viewPager2 = activityPreviewImageBinding4.previewList;
        PreviewImageAdapter previewImageAdapter = this.adapter;
        if (previewImageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            previewImageAdapter = null;
        }
        viewPager2.setAdapter(previewImageAdapter);
        ActivityPreviewImageBinding activityPreviewImageBinding5 = this.binding;
        if (activityPreviewImageBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding5 = null;
        }
        TextView textView = activityPreviewImageBinding5.itemPosition;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.position + 1);
        PreviewImageAdapter previewImageAdapter2 = this.adapter;
        if (previewImageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            previewImageAdapter2 = null;
        }
        objArr[1] = Integer.valueOf(previewImageAdapter2.getItemCount());
        String str = String.format("%d/%d", Arrays.copyOf(objArr, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        PreviewImageAdapter previewImageAdapter3 = this.adapter;
        if (previewImageAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            previewImageAdapter3 = null;
        }
        previewImageAdapter3.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity.initViews.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) {
                invoke(view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                LogUtils.INSTANCE.d(view, Integer.valueOf(i));
                PreviewImageActivity.this.moveFloatView();
            }
        });
        ActivityPreviewImageBinding activityPreviewImageBinding6 = this.binding;
        if (activityPreviewImageBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding6 = null;
        }
        activityPreviewImageBinding6.previewList.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity.initViews.2
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                ActivityPreviewImageBinding activityPreviewImageBinding7 = PreviewImageActivity.this.binding;
                PreviewImageAdapter previewImageAdapter4 = null;
                if (activityPreviewImageBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityPreviewImageBinding7 = null;
                }
                TextView textView2 = activityPreviewImageBinding7.itemPosition;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = new Object[2];
                objArr2[0] = Integer.valueOf(position + 1);
                PreviewImageAdapter previewImageAdapter5 = PreviewImageActivity.this.adapter;
                if (previewImageAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    previewImageAdapter4 = previewImageAdapter5;
                }
                objArr2[1] = Integer.valueOf(previewImageAdapter4.getItemCount());
                String str2 = String.format("%d/%d", Arrays.copyOf(objArr2, 2));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                textView2.setText(str2);
            }
        });
        ActivityPreviewImageBinding activityPreviewImageBinding7 = this.binding;
        if (activityPreviewImageBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding7 = null;
        }
        activityPreviewImageBinding7.closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewImageActivity.m45initViews$lambda2(this.f$0, view);
            }
        });
        ActivityPreviewImageBinding activityPreviewImageBinding8 = this.binding;
        if (activityPreviewImageBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPreviewImageBinding = activityPreviewImageBinding8;
        }
        activityPreviewImageBinding.useNowBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewImageActivity.m46initViews$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-2, reason: not valid java name */
    public static final void m45initViews$lambda2(PreviewImageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m46initViews$lambda3(final PreviewImageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
            if (LimitedClickUtils.INSTANCE.canClick()) {
                if (this$0.dataType == 2) {
                    AutoWallpaper autoWallpaper = this$0.autoWallpaper;
                    Intrinsics.checkNotNull(autoWallpaper);
                    if (autoWallpaper.getRsId() == DBUtils.INSTANCE.getCurrentWallpaperId()) {
                        ToastUtils.INSTANCE.showShort(R.string.str_using);
                        return;
                    }
                    AutoWallpaper autoWallpaper2 = this$0.autoWallpaper;
                    Intrinsics.checkNotNull(autoWallpaper2);
                    if (autoWallpaper2.getRsType() == 15) {
                        if (Intrinsics.areEqual(Const.ALLOW_ALWAYS, SPUtils.getInstance().getString(Const.ACTION_LEGITIMATE, Const.NOT_ALLOW))) {
                            BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
                            FragmentManager supportFragmentManager = this$0.getSupportFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                            AutoWallpaper autoWallpaper3 = this$0.autoWallpaper;
                            Intrinsics.checkNotNull(autoWallpaper3);
                            String string = this$0.getString(R.string.str_dialog_tips);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
                            String string2 = this$0.getString(R.string.str_dialog_tips_ok);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
                            String string3 = this$0.getString(R.string.str_dialog_tips_cancel);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
                            companion.showDialog(supportFragmentManager, autoWallpaper3, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity$initViews$4$1
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                                    FragmentManager supportFragmentManager2 = this.this$0.getSupportFragmentManager();
                                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
                                    AutoWallpaper autoWallpaper4 = this.this$0.autoWallpaper;
                                    Intrinsics.checkNotNull(autoWallpaper4);
                                    companion2.showDialog(supportFragmentManager2, autoWallpaper4);
                                }
                            });
                            return;
                        }
                        LegitimateDialogFragment.Companion companion2 = LegitimateDialogFragment.INSTANCE;
                        FragmentManager supportFragmentManager2 = this$0.getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
                        AutoWallpaper autoWallpaper4 = this$0.autoWallpaper;
                        Intrinsics.checkNotNull(autoWallpaper4);
                        companion2.showDialog(supportFragmentManager2, autoWallpaper4);
                        return;
                    }
                    ApplyingWindowFragment.Companion companion3 = ApplyingWindowFragment.INSTANCE;
                    FragmentManager supportFragmentManager3 = this$0.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager3, "supportFragmentManager");
                    AutoWallpaper autoWallpaper5 = this$0.autoWallpaper;
                    Intrinsics.checkNotNull(autoWallpaper5);
                    companion3.showDialog(supportFragmentManager3, autoWallpaper5);
                    return;
                }
                if (AutoUtils.INSTANCE.isPauseGear()) {
                    AutoTheme autoTheme = this$0.autoTheme;
                    Intrinsics.checkNotNull(autoTheme);
                    if (autoTheme.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                        ToastUtils.INSTANCE.showShort(R.string.str_using);
                        return;
                    }
                    ApplyingWindowFragment.Companion companion4 = ApplyingWindowFragment.INSTANCE;
                    FragmentManager supportFragmentManager4 = this$0.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager4, "supportFragmentManager");
                    AutoTheme autoTheme2 = this$0.autoTheme;
                    Intrinsics.checkNotNull(autoTheme2);
                    companion4.showDialog(supportFragmentManager4, autoTheme2);
                    return;
                }
                ToastUtils.INSTANCE.showShort(R.string.str_safety_reminder);
                return;
            }
            return;
        }
        ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveFloatView() {
        float f;
        float y;
        boolean z = this.showAction;
        float height = 0.0f;
        ActivityPreviewImageBinding activityPreviewImageBinding = null;
        if (z) {
            this.showAction = !z;
            ActivityPreviewImageBinding activityPreviewImageBinding2 = this.binding;
            if (activityPreviewImageBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding2 = null;
            }
            height = activityPreviewImageBinding2.floatView.getHeight();
            ActivityPreviewImageBinding activityPreviewImageBinding3 = this.binding;
            if (activityPreviewImageBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding3 = null;
            }
            f = -activityPreviewImageBinding3.floatView.getY();
            ActivityPreviewImageBinding activityPreviewImageBinding4 = this.binding;
            if (activityPreviewImageBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityPreviewImageBinding4 = null;
            }
            y = activityPreviewImageBinding4.useNowBtn.getY();
        } else {
            this.showAction = !z;
            f = 0.0f;
            y = 0.0f;
        }
        ActivityPreviewImageBinding activityPreviewImageBinding5 = this.binding;
        if (activityPreviewImageBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding5 = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityPreviewImageBinding5.floatView, "translationY", height);
        ActivityPreviewImageBinding activityPreviewImageBinding6 = this.binding;
        if (activityPreviewImageBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPreviewImageBinding6 = null;
        }
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(activityPreviewImageBinding6.closeBtn, "translationY", f);
        ActivityPreviewImageBinding activityPreviewImageBinding7 = this.binding;
        if (activityPreviewImageBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPreviewImageBinding = activityPreviewImageBinding7;
        }
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(activityPreviewImageBinding.useNowBtn, "translationY", y);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2).with(objectAnimatorOfFloat3);
        animatorSet.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.SUCCESS) && event.getEventResult() == 0) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            ClickEventUtil.getInstance().sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
            ClickEventUtil.getInstance().sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
            LogUtils.INSTANCE.i("set success, finish");
            finish();
        }
        if (Intrinsics.areEqual(event.getEventType(), EventString.LEGITIMATE_DIALOG)) {
            final AutoWallpaper autoWallpaper = new AutoWallpaper(new PreviewImage(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}), new Wallpaper(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}))), 110000011, "沙漠风暴", 15);
            BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            String string = getString(R.string.str_dialog_tips);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
            String string2 = getString(R.string.str_dialog_tips_ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
            String string3 = getString(R.string.str_dialog_tips_cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
            companion.showDialog(supportFragmentManager, autoWallpaper, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.activity.PreviewImageActivity.onEvent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                    FragmentManager supportFragmentManager2 = PreviewImageActivity.this.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
                    companion2.showDialog(supportFragmentManager2, autoWallpaper);
                }
            });
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
