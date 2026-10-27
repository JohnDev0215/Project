package com.byd.automultipletheme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.activity.PreviewImageActivity;
import com.byd.automultipletheme.adapter.WallpaperDetailAdapter;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.bean.PreviewImage;
import com.byd.automultipletheme.bean.Wallpaper;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.adapter.LinearSpaceItemDecoration;
import com.byd.automultipletheme.common.base.BaseDialogFragment;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.FragmentWallpaperDetailBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.fragment.LegitimateDialogFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WallpaperDetailFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\u001a\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\nH\u0002J\b\u0010\"\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/byd/automultipletheme/fragment/WallpaperDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/WallpaperDetailAdapter;", "autoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "bindings", "Lcom/byd/automultipletheme/databinding/FragmentWallpaperDetailBinding;", "dataType", "", IntentString.DATA_LIST, "", "", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onResume", "onViewCreated", "view", "showPreviewImagePage", IntentString.DATA_POSITION, "updateUseBtn", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperDetailFragment extends Fragment {
    private WallpaperDetailAdapter adapter;
    private AutoWallpaper autoWallpaper;
    private FragmentWallpaperDetailBinding bindings;
    private int dataType = -1;
    private List<String> list = new ArrayList();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBindingInflate = FragmentWallpaperDetailBinding.inflate(inflater);
        Intrinsics.checkNotNullExpressionValue(fragmentWallpaperDetailBindingInflate, "inflate(inflater)");
        this.bindings = fragmentWallpaperDetailBindingInflate;
        if (fragmentWallpaperDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBindingInflate = null;
        }
        RelativeLayout root = fragmentWallpaperDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "bindings.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Object obj = arguments.get(IntentString.DATA_TYPE);
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
            this.dataType = ((Integer) obj).intValue();
            this.autoWallpaper = (AutoWallpaper) arguments.get(IntentString.DATA_STR);
        }
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding = this.bindings;
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding2 = null;
        if (fragmentWallpaperDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding = null;
        }
        fragmentWallpaperDetailBinding.backView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.WallpaperDetailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WallpaperDetailFragment.m103onViewCreated$lambda1(this.f$0, view2);
            }
        });
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding3 = this.bindings;
            if (fragmentWallpaperDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding3 = null;
            }
            fragmentWallpaperDetailBinding3.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.img_main_bg_denza));
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding4 = this.bindings;
            if (fragmentWallpaperDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding4 = null;
            }
            fragmentWallpaperDetailBinding4.backView.setImageResource(R.mipmap.ic_back_denza);
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding5 = this.bindings;
            if (fragmentWallpaperDetailBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding5 = null;
            }
            fragmentWallpaperDetailBinding5.titleView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.item_unselected_denza));
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding6 = this.bindings;
            if (fragmentWallpaperDetailBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding6 = null;
            }
            fragmentWallpaperDetailBinding6.useNowBtn.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_item_button_detail_bg_denza));
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding7 = this.bindings;
            if (fragmentWallpaperDetailBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding7 = null;
            }
            fragmentWallpaperDetailBinding7.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.use_color_detail_denza));
        }
        this.adapter = new WallpaperDetailAdapter();
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding8 = this.bindings;
        if (fragmentWallpaperDetailBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding8 = null;
        }
        fragmentWallpaperDetailBinding8.wallpaperList.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding9 = this.bindings;
        if (fragmentWallpaperDetailBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding9 = null;
        }
        RecyclerView recyclerView = fragmentWallpaperDetailBinding9.wallpaperList;
        WallpaperDetailAdapter wallpaperDetailAdapter = this.adapter;
        if (wallpaperDetailAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperDetailAdapter = null;
        }
        recyclerView.setAdapter(wallpaperDetailAdapter);
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding10 = this.bindings;
        if (fragmentWallpaperDetailBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding10 = null;
        }
        fragmentWallpaperDetailBinding10.wallpaperList.addItemDecoration(new LinearSpaceItemDecoration(0, 0, 0, 20));
        WallpaperDetailAdapter wallpaperDetailAdapter2 = this.adapter;
        if (wallpaperDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperDetailAdapter2 = null;
        }
        wallpaperDetailAdapter2.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperDetailFragment.onViewCreated.3
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view2, Integer num) {
                invoke(view2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view2, int i) {
                Intrinsics.checkNotNullParameter(view2, "<anonymous parameter 0>");
                WallpaperDetailFragment.this.showPreviewImagePage(i);
            }
        });
        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding11 = this.bindings;
            if (fragmentWallpaperDetailBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding11 = null;
            }
            fragmentWallpaperDetailBinding11.backView.setImageResource(R.mipmap.ic_back_ar);
        } else {
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding12 = this.bindings;
            if (fragmentWallpaperDetailBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding12 = null;
            }
            fragmentWallpaperDetailBinding12.backView.setImageResource(R.mipmap.ic_back);
        }
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding13 = this.bindings;
        if (fragmentWallpaperDetailBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
        } else {
            fragmentWallpaperDetailBinding2 = fragmentWallpaperDetailBinding13;
        }
        fragmentWallpaperDetailBinding2.useNowBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.WallpaperDetailFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WallpaperDetailFragment.m104onViewCreated$lambda2(this.f$0, view2);
            }
        });
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-1, reason: not valid java name */
    public static final void m103onViewCreated$lambda1(WallpaperDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m104onViewCreated$lambda2(final WallpaperDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
            if (LimitedClickUtils.INSTANCE.canClick()) {
                AutoWallpaper autoWallpaper = this$0.autoWallpaper;
                Intrinsics.checkNotNull(autoWallpaper);
                if (autoWallpaper.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                    ToastUtils.INSTANCE.showShort(R.string.str_using);
                    return;
                }
                AutoWallpaper autoWallpaper2 = this$0.autoWallpaper;
                Intrinsics.checkNotNull(autoWallpaper2);
                if (autoWallpaper2.getRsType() == 15) {
                    if (Intrinsics.areEqual(Const.ALLOW_ALWAYS, SPUtils.getInstance().getString(Const.ACTION_LEGITIMATE, Const.NOT_ALLOW))) {
                        BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
                        FragmentManager childFragmentManager = this$0.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                        AutoWallpaper autoWallpaper3 = this$0.autoWallpaper;
                        Intrinsics.checkNotNull(autoWallpaper3);
                        String string = this$0.getString(R.string.str_dialog_tips);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
                        String string2 = this$0.getString(R.string.str_dialog_tips_ok);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
                        String string3 = this$0.getString(R.string.str_dialog_tips_cancel);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
                        companion.showDialog(childFragmentManager, autoWallpaper3, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperDetailFragment$onViewCreated$4$1
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
                                FragmentManager childFragmentManager2 = this.this$0.getChildFragmentManager();
                                Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                                AutoWallpaper autoWallpaper4 = this.this$0.autoWallpaper;
                                Intrinsics.checkNotNull(autoWallpaper4);
                                companion2.showDialog(childFragmentManager2, autoWallpaper4);
                            }
                        });
                        return;
                    }
                    LegitimateDialogFragment.Companion companion2 = LegitimateDialogFragment.INSTANCE;
                    FragmentManager childFragmentManager2 = this$0.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                    AutoWallpaper autoWallpaper4 = this$0.autoWallpaper;
                    Intrinsics.checkNotNull(autoWallpaper4);
                    companion2.showDialog(childFragmentManager2, autoWallpaper4);
                    return;
                }
                ApplyingWindowFragment.Companion companion3 = ApplyingWindowFragment.INSTANCE;
                FragmentManager childFragmentManager3 = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager3, "childFragmentManager");
                AutoWallpaper autoWallpaper5 = this$0.autoWallpaper;
                Intrinsics.checkNotNull(autoWallpaper5);
                companion3.showDialog(childFragmentManager3, autoWallpaper5);
                return;
            }
            return;
        }
        ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
    }

    private final void initData() {
        AutoWallpaper autoWallpaper = this.autoWallpaper;
        if (autoWallpaper != null) {
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding = this.bindings;
            WallpaperDetailAdapter wallpaperDetailAdapter = null;
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding2 = null;
            WallpaperDetailAdapter wallpaperDetailAdapter2 = null;
            if (fragmentWallpaperDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding = null;
            }
            fragmentWallpaperDetailBinding.titleView.setText(AutoUtils.INSTANCE.getWallpaperName(autoWallpaper.getRsId()));
            if (autoWallpaper.getRsType() == 15) {
                this.list.clear();
                this.list.add("WALLPAPER_TYPE_DAY_NIGHT");
                WallpaperDetailAdapter wallpaperDetailAdapter3 = this.adapter;
                if (wallpaperDetailAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    wallpaperDetailAdapter3 = null;
                }
                wallpaperDetailAdapter3.setData((ArrayList) this.list);
                FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding3 = this.bindings;
                if (fragmentWallpaperDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindings");
                } else {
                    fragmentWallpaperDetailBinding2 = fragmentWallpaperDetailBinding3;
                }
                fragmentWallpaperDetailBinding2.titleView.setText(R.string.str_time_wallpaper);
            } else if (ScreenUtils.isLandscape()) {
                this.list.clear();
                this.list.addAll(autoWallpaper.getImgUrl().getLand());
                WallpaperDetailAdapter wallpaperDetailAdapter4 = this.adapter;
                if (wallpaperDetailAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    wallpaperDetailAdapter2 = wallpaperDetailAdapter4;
                }
                wallpaperDetailAdapter2.setData((ArrayList) this.list);
            } else {
                this.list.clear();
                this.list.addAll(autoWallpaper.getImgUrl().getPort());
                WallpaperDetailAdapter wallpaperDetailAdapter5 = this.adapter;
                if (wallpaperDetailAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    wallpaperDetailAdapter = wallpaperDetailAdapter5;
                }
                wallpaperDetailAdapter.setData((ArrayList) this.list);
            }
            if (autoWallpaper.getRsId() == DBUtils.INSTANCE.getCurrentWallpaperId()) {
                updateUseBtn();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initData();
    }

    private final void updateUseBtn() {
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding2 = this.bindings;
            if (fragmentWallpaperDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding2 = null;
            }
            fragmentWallpaperDetailBinding2.useNowBtn.setText(R.string.str_using);
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding3 = this.bindings;
            if (fragmentWallpaperDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentWallpaperDetailBinding3 = null;
            }
            fragmentWallpaperDetailBinding3.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.select_color_detail_denza));
            FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding4 = this.bindings;
            if (fragmentWallpaperDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
            } else {
                fragmentWallpaperDetailBinding = fragmentWallpaperDetailBinding4;
            }
            fragmentWallpaperDetailBinding.useNowBtn.setEnabled(false);
            return;
        }
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding5 = this.bindings;
        if (fragmentWallpaperDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding5 = null;
        }
        fragmentWallpaperDetailBinding5.useNowBtn.setText(R.string.str_using);
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding6 = this.bindings;
        if (fragmentWallpaperDetailBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentWallpaperDetailBinding6 = null;
        }
        fragmentWallpaperDetailBinding6.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.textColorSelected));
        FragmentWallpaperDetailBinding fragmentWallpaperDetailBinding7 = this.bindings;
        if (fragmentWallpaperDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
        } else {
            fragmentWallpaperDetailBinding = fragmentWallpaperDetailBinding7;
        }
        fragmentWallpaperDetailBinding.useNowBtn.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreviewImagePage(int position) {
        Intent intent = new Intent(getActivity(), (Class<?>) PreviewImageActivity.class);
        intent.putExtra(IntentString.DATA_STR, this.autoWallpaper);
        intent.putExtra(IntentString.DATA_TYPE, this.dataType);
        intent.putExtra(IntentString.DATA_POSITION, position);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.LEGITIMATE_DIALOG)) {
            final AutoWallpaper autoWallpaper = new AutoWallpaper(new PreviewImage(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}), new Wallpaper(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}))), 110000011, "沙漠风暴", 15);
            BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            String string = getString(R.string.str_dialog_tips);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
            String string2 = getString(R.string.str_dialog_tips_ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
            String string3 = getString(R.string.str_dialog_tips_cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
            companion.showDialog(childFragmentManager, autoWallpaper, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperDetailFragment.onEvent.1
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
                    FragmentManager childFragmentManager2 = WallpaperDetailFragment.this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                    companion2.showDialog(childFragmentManager2, autoWallpaper);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
