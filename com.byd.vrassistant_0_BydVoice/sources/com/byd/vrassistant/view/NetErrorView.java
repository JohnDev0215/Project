package com.byd.vrassistant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.byd.vrassistant.base.ConstraintLayoutLifecycle;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.ui.databinding.ViewNetErrorBinding;
import com.byd.vrassistant.utils.TextViewUtils;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetErrorView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0013H\u0014J\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0007R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/byd/vrassistant/view/NetErrorView;", "Lcom/byd/vrassistant/base/ConstraintLayoutLifecycle;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/byd/vrassistant/ui/databinding/ViewNetErrorBinding;", "getBinding", "()Lcom/byd/vrassistant/ui/databinding/ViewNetErrorBinding;", "binding$delegate", "Lkotlin/Lazy;", "commonViewModel", "Lcom/byd/vrassistant/viewmodel/CommonViewModel;", "mContext", "initView", "", "initViewModel", "setContext", "setSkin", "type", "vr-ui_8155Release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NetErrorView extends ConstraintLayoutLifecycle {

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding;
    private final CommonViewModel commonViewModel;
    private Context mContext;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NetErrorView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NetErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initView() {
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initViewModel() {
    }

    public /* synthetic */ NetErrorView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetErrorView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewModel appViewModel = ViewModelHelper.getAppViewModel(CommonViewModel.class);
        Intrinsics.checkNotNullExpressionValue(appViewModel, "getAppViewModel(\n       …ewModel::class.java\n    )");
        CommonViewModel commonViewModel = (CommonViewModel) appViewModel;
        this.commonViewModel = commonViewModel;
        this.mContext = context;
        this.binding = LazyKt.lazy(new Function0<ViewNetErrorBinding>() { // from class: com.byd.vrassistant.view.NetErrorView$binding$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewNetErrorBinding invoke() {
                ViewNetErrorBinding viewNetErrorBindingInflate = ViewNetErrorBinding.inflate(LayoutInflater.from(context), this, true);
                Intrinsics.checkNotNullExpressionValue(viewNetErrorBindingInflate, "inflate(LayoutInflater.from(context), this, true)");
                return viewNetErrorBindingInflate;
            }
        });
        NetErrorView netErrorView = this;
        commonViewModel.getLocaleLiveData().observe(netErrorView, new Observer() { // from class: com.byd.vrassistant.view.NetErrorView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NetErrorView.m199_init_$lambda0(this.f$0, (Locale) obj);
            }
        });
        commonViewModel.contextLiveData.observe(netErrorView, new Observer() { // from class: com.byd.vrassistant.view.NetErrorView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setContext((Context) obj);
            }
        });
        commonViewModel.getSkinType().observe(netErrorView, new Observer() { // from class: com.byd.vrassistant.view.NetErrorView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setSkin(((Integer) obj).intValue());
            }
        });
        getBinding().btnGoSetting.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.NetErrorView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NetErrorView.m200_init_$lambda1(view);
            }
        });
    }

    private final ViewNetErrorBinding getBinding() {
        return (ViewNetErrorBinding) this.binding.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m199_init_$lambda0(NetErrorView this$0, Locale locale) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextViewUtils.getInstance().updateLocale(this$0, locale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m200_init_$lambda1(View view) {
        BridgeHelper.getSystemSetting().openWifiPage();
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
    }

    public final void setSkin(int type) {
        if (type == 1) {
            getBinding().ivNetErr.setImageResource(R.mipmap.img_net_err);
            getBinding().tvNetErr.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_85_light));
            getBinding().btnGoSetting.setBackgroundResource(R.drawable.bg_btn_net_set);
            getBinding().btnGoSetting.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            return;
        }
        getBinding().ivNetErr.setImageResource(R.mipmap.img_net_err_dark);
        getBinding().tvNetErr.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_60_dark));
        getBinding().btnGoSetting.setBackgroundResource(R.drawable.bg_btn_net_set_dark);
        getBinding().btnGoSetting.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_light));
    }
}
