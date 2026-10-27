package com.byd.automultipletheme.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.databinding.FragmentLegitimateDialogBinding;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: LegitimateDialogFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\u001a\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/byd/automultipletheme/fragment/LegitimateDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/FragmentLegitimateDialogBinding;", "wallpaperType", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "applyRudder", "", "initArguments", "initViews", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LegitimateDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "LegitimateDialogFragment";
    private static final String rsType_tag = "rsType_tag";
    private FragmentLegitimateDialogBinding binding;
    private AutoWallpaper wallpaperType;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                Intrinsics.checkNotNullExpressionValue(window, "window");
                window.setLayout(getResources().getDimensionPixelSize(R.dimen.dp_560), getResources().getDimensionPixelSize(R.dimen.dp_300));
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg_denza);
                } else {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg);
                }
            }
            dialog.setCanceledOnTouchOutside(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBindingInflate = FragmentLegitimateDialogBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentLegitimateDialogBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentLegitimateDialogBindingInflate;
        applyRudder();
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding = this.binding;
        if (fragmentLegitimateDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding = null;
        }
        ConstraintLayout root = fragmentLegitimateDialogBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initArguments();
        initViews();
    }

    private final void initArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(rsType_tag);
            Objects.requireNonNull(serializable, "null cannot be cast to non-null type com.byd.automultipletheme.bean.AutoWallpaper");
            this.wallpaperType = (AutoWallpaper) serializable;
        }
    }

    private final void initViews() {
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding2 = this.binding;
            if (fragmentLegitimateDialogBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding2 = null;
            }
            fragmentLegitimateDialogBinding2.allowOne.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_dialog_btn_ok_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding3 = this.binding;
            if (fragmentLegitimateDialogBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding3 = null;
            }
            fragmentLegitimateDialogBinding3.allowOne.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding4 = this.binding;
            if (fragmentLegitimateDialogBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding4 = null;
            }
            fragmentLegitimateDialogBinding4.allowEvery.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_dialog_btn_cancel_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding5 = this.binding;
            if (fragmentLegitimateDialogBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding5 = null;
            }
            fragmentLegitimateDialogBinding5.allowEvery.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding6 = this.binding;
            if (fragmentLegitimateDialogBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding6 = null;
            }
            fragmentLegitimateDialogBinding6.btnReject.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_dialog_btn_cancel_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding7 = this.binding;
            if (fragmentLegitimateDialogBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding7 = null;
            }
            fragmentLegitimateDialogBinding7.btnReject.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding8 = this.binding;
            if (fragmentLegitimateDialogBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding8 = null;
            }
            fragmentLegitimateDialogBinding8.dialogContent.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
        }
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding9 = this.binding;
        if (fragmentLegitimateDialogBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding9 = null;
        }
        fragmentLegitimateDialogBinding9.dialogTitle.setText(getString(R.string.str_legitimate_title));
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding10 = this.binding;
        if (fragmentLegitimateDialogBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding10 = null;
        }
        fragmentLegitimateDialogBinding10.dialogContent.setText(getString(R.string.str_legitimate_content));
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding11 = this.binding;
        if (fragmentLegitimateDialogBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding11 = null;
        }
        fragmentLegitimateDialogBinding11.allowOne.setText(getString(R.string.str_legitimate_allow_one));
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding12 = this.binding;
        if (fragmentLegitimateDialogBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding12 = null;
        }
        fragmentLegitimateDialogBinding12.allowEvery.setText(getString(R.string.str_legitimate_allow_always));
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding13 = this.binding;
        if (fragmentLegitimateDialogBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding13 = null;
        }
        fragmentLegitimateDialogBinding13.btnReject.setText(getString(R.string.str_legitimate_reject));
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding14 = this.binding;
        if (fragmentLegitimateDialogBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding14 = null;
        }
        fragmentLegitimateDialogBinding14.dialogContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.automultipletheme.fragment.LegitimateDialogFragment.initViews.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding15 = LegitimateDialogFragment.this.binding;
                FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding16 = null;
                if (fragmentLegitimateDialogBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentLegitimateDialogBinding15 = null;
                }
                fragmentLegitimateDialogBinding15.dialogContent.getViewTreeObserver().removeOnPreDrawListener(this);
                FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding17 = LegitimateDialogFragment.this.binding;
                if (fragmentLegitimateDialogBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentLegitimateDialogBinding17 = null;
                }
                if (fragmentLegitimateDialogBinding17.dialogContent.getLineCount() > 2) {
                    FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding18 = LegitimateDialogFragment.this.binding;
                    if (fragmentLegitimateDialogBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentLegitimateDialogBinding16 = fragmentLegitimateDialogBinding18;
                    }
                    fragmentLegitimateDialogBinding16.dialogContent.setGravity(GravityCompat.START);
                    return false;
                }
                FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding19 = LegitimateDialogFragment.this.binding;
                if (fragmentLegitimateDialogBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentLegitimateDialogBinding16 = fragmentLegitimateDialogBinding19;
                }
                fragmentLegitimateDialogBinding16.dialogContent.setGravity(1);
                return false;
            }
        });
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding15 = this.binding;
        if (fragmentLegitimateDialogBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding15 = null;
        }
        fragmentLegitimateDialogBinding15.allowOne.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.LegitimateDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LegitimateDialogFragment.m93initViews$lambda3(this.f$0, view);
            }
        });
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding16 = this.binding;
        if (fragmentLegitimateDialogBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLegitimateDialogBinding16 = null;
        }
        fragmentLegitimateDialogBinding16.allowEvery.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.LegitimateDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LegitimateDialogFragment.m94initViews$lambda4(this.f$0, view);
            }
        });
        FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding17 = this.binding;
        if (fragmentLegitimateDialogBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLegitimateDialogBinding = fragmentLegitimateDialogBinding17;
        }
        fragmentLegitimateDialogBinding.btnReject.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.LegitimateDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LegitimateDialogFragment.m95initViews$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m93initViews$lambda3(LegitimateDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SPUtils.getInstance().put(Const.ACTION_LEGITIMATE, Const.ALLOW_ONCE);
        this$0.dismiss();
        EventBus.getDefault().post(new EventBusBean(EventString.LEGITIMATE_DIALOG, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-4, reason: not valid java name */
    public static final void m94initViews$lambda4(LegitimateDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SPUtils.getInstance().put(Const.ACTION_LEGITIMATE, Const.ALLOW_ALWAYS);
        this$0.dismiss();
        EventBus.getDefault().post(new EventBusBean(EventString.LEGITIMATE_DIALOG, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-5, reason: not valid java name */
    public static final void m95initViews$lambda5(LegitimateDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SPUtils.getInstance().put(Const.ACTION_LEGITIMATE, Const.NOT_ALLOW);
        this$0.dismiss();
    }

    private final void applyRudder() {
        if (AutoUtils.INSTANCE.isRightRudder()) {
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding = this.binding;
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding2 = null;
            if (fragmentLegitimateDialogBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding = null;
            }
            constraintSet.clone(fragmentLegitimateDialogBinding.getRoot());
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding3 = this.binding;
            if (fragmentLegitimateDialogBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding3 = null;
            }
            constraintSet.clear(fragmentLegitimateDialogBinding3.allowOne.getId(), 2);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding4 = this.binding;
            if (fragmentLegitimateDialogBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding4 = null;
            }
            int id = fragmentLegitimateDialogBinding4.allowOne.getId();
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding5 = this.binding;
            if (fragmentLegitimateDialogBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding5 = null;
            }
            constraintSet.connect(id, 1, fragmentLegitimateDialogBinding5.allowEvery.getId(), 2);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding6 = this.binding;
            if (fragmentLegitimateDialogBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding6 = null;
            }
            constraintSet.clear(fragmentLegitimateDialogBinding6.allowOne.getId(), 1);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding7 = this.binding;
            if (fragmentLegitimateDialogBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding7 = null;
            }
            constraintSet.connect(fragmentLegitimateDialogBinding7.allowOne.getId(), 2, 0, 2, 36);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding8 = this.binding;
            if (fragmentLegitimateDialogBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding8 = null;
            }
            constraintSet.clear(fragmentLegitimateDialogBinding8.btnReject.getId(), 1);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding9 = this.binding;
            if (fragmentLegitimateDialogBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding9 = null;
            }
            int id2 = fragmentLegitimateDialogBinding9.btnReject.getId();
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding10 = this.binding;
            if (fragmentLegitimateDialogBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding10 = null;
            }
            constraintSet.connect(id2, 2, fragmentLegitimateDialogBinding10.allowEvery.getId(), 1);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding11 = this.binding;
            if (fragmentLegitimateDialogBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding11 = null;
            }
            constraintSet.clear(fragmentLegitimateDialogBinding11.btnReject.getId(), 2);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding12 = this.binding;
            if (fragmentLegitimateDialogBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLegitimateDialogBinding12 = null;
            }
            constraintSet.connect(fragmentLegitimateDialogBinding12.btnReject.getId(), 1, 0, 1, 36);
            FragmentLegitimateDialogBinding fragmentLegitimateDialogBinding13 = this.binding;
            if (fragmentLegitimateDialogBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentLegitimateDialogBinding2 = fragmentLegitimateDialogBinding13;
            }
            constraintSet.applyTo(fragmentLegitimateDialogBinding2.getRoot());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: compiled from: LegitimateDialogFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/byd/automultipletheme/fragment/LegitimateDialogFragment$Companion;", "", "()V", "TAG", "", LegitimateDialogFragment.rsType_tag, "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "rsType", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, AutoWallpaper rsType) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(rsType, "rsType");
            LegitimateDialogFragment legitimateDialogFragment = new LegitimateDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(LegitimateDialogFragment.rsType_tag, rsType);
            legitimateDialogFragment.setArguments(bundle);
            legitimateDialogFragment.show(fragmentManager, LegitimateDialogFragment.TAG);
        }
    }
}
