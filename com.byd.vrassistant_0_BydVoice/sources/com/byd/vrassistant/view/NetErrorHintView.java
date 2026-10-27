package com.byd.vrassistant.view;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.base.ConstraintLayoutLifecycle;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.ui.databinding.ViewNetErrorHintBinding;
import com.byd.vrassistant.utils.TextViewUtils;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetErrorHintView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0014R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/byd/vrassistant/view/NetErrorHintView;", "Lcom/byd/vrassistant/base/ConstraintLayoutLifecycle;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/byd/vrassistant/ui/databinding/ViewNetErrorHintBinding;", "getBinding", "()Lcom/byd/vrassistant/ui/databinding/ViewNetErrorHintBinding;", "binding$delegate", "Lkotlin/Lazy;", "commonViewModel", "Lcom/byd/vrassistant/viewmodel/CommonViewModel;", "getCommonViewModel", "()Lcom/byd/vrassistant/viewmodel/CommonViewModel;", "commonViewModel$delegate", "initView", "", "initViewModel", "Companion", "vr-ui_8155Release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NetErrorHintView extends ConstraintLayoutLifecycle {
    public static final String TAG = "NEU_NetErrorHintView";

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding;

    /* JADX INFO: renamed from: commonViewModel$delegate, reason: from kotlin metadata */
    private final Lazy commonViewModel;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NetErrorHintView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NetErrorHintView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initView() {
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initViewModel() {
    }

    public /* synthetic */ NetErrorHintView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetErrorHintView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.binding = LazyKt.lazy(new Function0<ViewNetErrorHintBinding>() { // from class: com.byd.vrassistant.view.NetErrorHintView$binding$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewNetErrorHintBinding invoke() {
                ViewNetErrorHintBinding viewNetErrorHintBindingInflate = ViewNetErrorHintBinding.inflate(LayoutInflater.from(context), this, true);
                Intrinsics.checkNotNullExpressionValue(viewNetErrorHintBindingInflate, "inflate(LayoutInflater.from(context), this, true)");
                return viewNetErrorHintBindingInflate;
            }
        });
        this.commonViewModel = LazyKt.lazy(new Function0<CommonViewModel>() { // from class: com.byd.vrassistant.view.NetErrorHintView$commonViewModel$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommonViewModel invoke() {
                ComponentCallbacks2 application = ContextHolder.getApplication();
                Intrinsics.checkNotNull(application, "null cannot be cast to non-null type androidx.lifecycle.ViewModelStoreOwner");
                ViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) application).get(CommonViewModel.class);
                Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider((Conte…monViewModel::class.java]");
                return (CommonViewModel) viewModel;
            }
        });
        LayoutInflater.from(context).inflate(R.layout.view_net_error_hint, (ViewGroup) this, true);
        getCommonViewModel().getLocaleLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.NetErrorHintView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetErrorHintView.m195_init_$lambda0(this.f$0, (Locale) obj);
            }
        });
        getBinding().tvNetErrorHint.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.NetErrorHintView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NetErrorHintView.m196_init_$lambda1(view);
            }
        });
        getBinding().ivArrow.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.NetErrorHintView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NetErrorHintView.m197_init_$lambda2(view);
            }
        });
    }

    private final ViewNetErrorHintBinding getBinding() {
        return (ViewNetErrorHintBinding) this.binding.getValue();
    }

    private final CommonViewModel getCommonViewModel() {
        return (CommonViewModel) this.commonViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m195_init_$lambda0(NetErrorHintView this$0, Locale locale) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextViewUtils.getInstance().updateLocale(this$0, locale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m196_init_$lambda1(View view) {
        Log.i(TAG, "tvNetErrorHint onclick: go setting page");
        BridgeHelper.getSystemSetting().openWifiPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m197_init_$lambda2(View view) {
        Log.i(TAG, "ivArrow onclick: go setting page");
        BridgeHelper.getSystemSetting().openWifiPage();
    }
}
