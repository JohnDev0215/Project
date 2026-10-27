package com.byd.automultipletheme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.activity.PreviewImageActivity;
import com.byd.automultipletheme.adapter.ThemeDetailAdapter;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.adapter.LinearSpaceItemDecoration;
import com.byd.automultipletheme.databinding.FragmentThemeDetailBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThemeDetailFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016J\u001a\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/byd/automultipletheme/fragment/ThemeDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/ThemeDetailAdapter;", "autoTheme", "Lcom/byd/automultipletheme/bean/AutoTheme;", "bindings", "Lcom/byd/automultipletheme/databinding/FragmentThemeDetailBinding;", "dataType", "", IntentString.DATA_LIST, "", "", "initData", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "showPreviewImagePage", IntentString.DATA_POSITION, "updateUseBtn", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThemeDetailFragment extends Fragment {
    private ThemeDetailAdapter adapter;
    private AutoTheme autoTheme;
    private FragmentThemeDetailBinding bindings;
    private int dataType;
    private List<String> list = new ArrayList();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentThemeDetailBinding fragmentThemeDetailBindingInflate = FragmentThemeDetailBinding.inflate(inflater);
        Intrinsics.checkNotNullExpressionValue(fragmentThemeDetailBindingInflate, "inflate(inflater)");
        this.bindings = fragmentThemeDetailBindingInflate;
        if (fragmentThemeDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBindingInflate = null;
        }
        RelativeLayout root = fragmentThemeDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "bindings.root");
        return root;
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
            this.autoTheme = (AutoTheme) arguments.get(IntentString.DATA_STR);
        }
        FragmentThemeDetailBinding fragmentThemeDetailBinding = this.bindings;
        FragmentThemeDetailBinding fragmentThemeDetailBinding2 = null;
        if (fragmentThemeDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding = null;
        }
        fragmentThemeDetailBinding.backView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.ThemeDetailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ThemeDetailFragment.m99onViewCreated$lambda1(this.f$0, view2);
            }
        });
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentThemeDetailBinding fragmentThemeDetailBinding3 = this.bindings;
            if (fragmentThemeDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding3 = null;
            }
            fragmentThemeDetailBinding3.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.img_main_bg_denza));
            FragmentThemeDetailBinding fragmentThemeDetailBinding4 = this.bindings;
            if (fragmentThemeDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding4 = null;
            }
            fragmentThemeDetailBinding4.backView.setImageResource(R.mipmap.ic_back_denza);
            FragmentThemeDetailBinding fragmentThemeDetailBinding5 = this.bindings;
            if (fragmentThemeDetailBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding5 = null;
            }
            fragmentThemeDetailBinding5.titleView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.item_unselected_denza));
            FragmentThemeDetailBinding fragmentThemeDetailBinding6 = this.bindings;
            if (fragmentThemeDetailBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding6 = null;
            }
            fragmentThemeDetailBinding6.useNowBtn.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_item_button_detail_bg_denza));
            FragmentThemeDetailBinding fragmentThemeDetailBinding7 = this.bindings;
            if (fragmentThemeDetailBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding7 = null;
            }
            fragmentThemeDetailBinding7.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.use_color_detail_denza));
        }
        this.adapter = new ThemeDetailAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        FragmentThemeDetailBinding fragmentThemeDetailBinding8 = this.bindings;
        if (fragmentThemeDetailBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding8 = null;
        }
        fragmentThemeDetailBinding8.themeList.setLayoutManager(linearLayoutManager);
        FragmentThemeDetailBinding fragmentThemeDetailBinding9 = this.bindings;
        if (fragmentThemeDetailBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding9 = null;
        }
        RecyclerView recyclerView = fragmentThemeDetailBinding9.themeList;
        ThemeDetailAdapter themeDetailAdapter = this.adapter;
        if (themeDetailAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeDetailAdapter = null;
        }
        recyclerView.setAdapter(themeDetailAdapter);
        FragmentThemeDetailBinding fragmentThemeDetailBinding10 = this.bindings;
        if (fragmentThemeDetailBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding10 = null;
        }
        fragmentThemeDetailBinding10.themeList.addItemDecoration(new LinearSpaceItemDecoration(0, 0, 0, 20));
        ThemeDetailAdapter themeDetailAdapter2 = this.adapter;
        if (themeDetailAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeDetailAdapter2 = null;
        }
        themeDetailAdapter2.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.ThemeDetailFragment.onViewCreated.3
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
                ThemeDetailFragment.this.showPreviewImagePage(i);
            }
        });
        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            FragmentThemeDetailBinding fragmentThemeDetailBinding11 = this.bindings;
            if (fragmentThemeDetailBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding11 = null;
            }
            fragmentThemeDetailBinding11.backView.setImageResource(R.mipmap.ic_back_ar);
        } else {
            FragmentThemeDetailBinding fragmentThemeDetailBinding12 = this.bindings;
            if (fragmentThemeDetailBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding12 = null;
            }
            fragmentThemeDetailBinding12.backView.setImageResource(R.mipmap.ic_back);
        }
        FragmentThemeDetailBinding fragmentThemeDetailBinding13 = this.bindings;
        if (fragmentThemeDetailBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding13 = null;
        }
        fragmentThemeDetailBinding13.useNowBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.ThemeDetailFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ThemeDetailFragment.m100onViewCreated$lambda2(this.f$0, view2);
            }
        });
        initData();
        ThemeDetailAdapter themeDetailAdapter3 = this.adapter;
        if (themeDetailAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeDetailAdapter3 = null;
        }
        if (themeDetailAdapter3.getItemCount() == 1) {
            FragmentThemeDetailBinding fragmentThemeDetailBinding14 = this.bindings;
            if (fragmentThemeDetailBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding14 = null;
            }
            ViewGroup.LayoutParams layoutParams = fragmentThemeDetailBinding14.themeList.getLayoutParams();
            Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = -2;
            layoutParams2.addRule(13, -1);
            FragmentThemeDetailBinding fragmentThemeDetailBinding15 = this.bindings;
            if (fragmentThemeDetailBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
            } else {
                fragmentThemeDetailBinding2 = fragmentThemeDetailBinding15;
            }
            fragmentThemeDetailBinding2.themeList.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-1, reason: not valid java name */
    public static final void m99onViewCreated$lambda1(ThemeDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m100onViewCreated$lambda2(ThemeDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AutoUtils.INSTANCE.isPauseGear()) {
            if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
                AutoTheme autoTheme = this$0.autoTheme;
                Intrinsics.checkNotNull(autoTheme);
                if (autoTheme.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                    ToastUtils.INSTANCE.showShort(R.string.str_using);
                    return;
                }
                ApplyingWindowFragment.Companion companion = ApplyingWindowFragment.INSTANCE;
                FragmentManager childFragmentManager = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                AutoTheme autoTheme2 = this$0.autoTheme;
                Intrinsics.checkNotNull(autoTheme2);
                companion.showDialog(childFragmentManager, autoTheme2);
                return;
            }
            ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
            return;
        }
        ToastUtils.INSTANCE.showShort(R.string.str_safety_reminder);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreviewImagePage(int position) {
        Intent intent = new Intent(getActivity(), (Class<?>) PreviewImageActivity.class);
        intent.putExtra(IntentString.DATA_STR, this.autoTheme);
        intent.putExtra(IntentString.DATA_TYPE, this.dataType);
        intent.putExtra(IntentString.DATA_POSITION, position);
        startActivity(intent);
    }

    private final void initData() {
        AutoTheme autoTheme = this.autoTheme;
        if (autoTheme != null) {
            FragmentThemeDetailBinding fragmentThemeDetailBinding = this.bindings;
            ThemeDetailAdapter themeDetailAdapter = null;
            if (fragmentThemeDetailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding = null;
            }
            fragmentThemeDetailBinding.titleView.setText(AutoUtils.INSTANCE.getThemeName(autoTheme.getRsId()));
            if (ScreenUtils.isLandscape()) {
                this.list.clear();
                int modelType = AutoUtils.INSTANCE.getModelType();
                if (modelType == 2) {
                    this.list.addAll(autoTheme.getImgUrl().getOcean().getLand());
                } else if (modelType == 3) {
                    this.list.addAll(autoTheme.getImgUrl().getDenza().getLand());
                } else {
                    this.list.addAll(autoTheme.getImgUrl().getDynasty().getLand());
                }
                ThemeDetailAdapter themeDetailAdapter2 = this.adapter;
                if (themeDetailAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    themeDetailAdapter = themeDetailAdapter2;
                }
                themeDetailAdapter.setData((ArrayList) this.list);
            } else {
                this.list.clear();
                int modelType2 = AutoUtils.INSTANCE.getModelType();
                if (modelType2 == 2) {
                    this.list.addAll(autoTheme.getImgUrl().getOcean().getPort());
                } else if (modelType2 == 3) {
                    this.list.addAll(autoTheme.getImgUrl().getDenza().getPort());
                } else {
                    this.list.addAll(autoTheme.getImgUrl().getDynasty().getPort());
                }
                ThemeDetailAdapter themeDetailAdapter3 = this.adapter;
                if (themeDetailAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    themeDetailAdapter = themeDetailAdapter3;
                }
                themeDetailAdapter.setData((ArrayList) this.list);
            }
            if (autoTheme.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                updateUseBtn();
            }
        }
    }

    private final void updateUseBtn() {
        FragmentThemeDetailBinding fragmentThemeDetailBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentThemeDetailBinding fragmentThemeDetailBinding2 = this.bindings;
            if (fragmentThemeDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding2 = null;
            }
            fragmentThemeDetailBinding2.useNowBtn.setText(R.string.str_using);
            FragmentThemeDetailBinding fragmentThemeDetailBinding3 = this.bindings;
            if (fragmentThemeDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
                fragmentThemeDetailBinding3 = null;
            }
            fragmentThemeDetailBinding3.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.select_color_detail_denza));
            FragmentThemeDetailBinding fragmentThemeDetailBinding4 = this.bindings;
            if (fragmentThemeDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindings");
            } else {
                fragmentThemeDetailBinding = fragmentThemeDetailBinding4;
            }
            fragmentThemeDetailBinding.useNowBtn.setEnabled(false);
            return;
        }
        FragmentThemeDetailBinding fragmentThemeDetailBinding5 = this.bindings;
        if (fragmentThemeDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding5 = null;
        }
        fragmentThemeDetailBinding5.useNowBtn.setText(R.string.str_using);
        FragmentThemeDetailBinding fragmentThemeDetailBinding6 = this.bindings;
        if (fragmentThemeDetailBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
            fragmentThemeDetailBinding6 = null;
        }
        fragmentThemeDetailBinding6.useNowBtn.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.textColorSelected));
        FragmentThemeDetailBinding fragmentThemeDetailBinding7 = this.bindings;
        if (fragmentThemeDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindings");
        } else {
            fragmentThemeDetailBinding = fragmentThemeDetailBinding7;
        }
        fragmentThemeDetailBinding.useNowBtn.setEnabled(false);
    }
}
