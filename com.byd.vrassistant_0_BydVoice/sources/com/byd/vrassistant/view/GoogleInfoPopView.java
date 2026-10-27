package com.byd.vrassistant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.base.ConstraintLayoutLifecycle;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.BrowserUtils;
import com.byd.vrassistant.utils.TextViewUtils;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleInfoPopView extends ConstraintLayoutLifecycle {
    View bgDialog;
    View bgGi;
    CommonViewModel commonViewModel;
    Context mContext;
    TextView tvContent;
    TextView tvMore;
    TextView tvOk;
    TextView tvTitle;
    View vHori;
    ViewManagerViewModel viewManagerViewModel;

    public GoogleInfoPopView(Context context) {
        super(context);
    }

    public GoogleInfoPopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GoogleInfoPopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.view_googleinfo, (ViewGroup) this, true);
        this.tvTitle = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.tvContent = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.tvOk = (TextView) viewInflate.findViewById(R.id.tv_ok);
        this.tvMore = (TextView) viewInflate.findViewById(R.id.tv_more);
        this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m190lambda$initView$0$combydvrassistantviewGoogleInfoPopView(view);
            }
        });
        this.tvMore.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m191lambda$initView$1$combydvrassistantviewGoogleInfoPopView(view);
            }
        });
        this.bgDialog = viewInflate.findViewById(R.id.bg_dialog);
        this.vHori = viewInflate.findViewById(R.id.v_divider);
        View viewFindViewById = viewInflate.findViewById(R.id.bg_gi);
        this.bgGi = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m192lambda$initView$2$combydvrassistantviewGoogleInfoPopView(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-byd-vrassistant-view-GoogleInfoPopView, reason: not valid java name */
    /* synthetic */ void m190lambda$initView$0$combydvrassistantviewGoogleInfoPopView(View view) {
        this.viewManagerViewModel.setShowGoogleIngo(false);
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-byd-vrassistant-view-GoogleInfoPopView, reason: not valid java name */
    /* synthetic */ void m191lambda$initView$1$combydvrassistantviewGoogleInfoPopView(View view) {
        this.viewManagerViewModel.setShowGoogleIngo(false);
        BrowserUtils.openWebPage(this.mContext, Constant.GOOGLE_INFO_URL);
    }

    /* JADX INFO: renamed from: lambda$initView$2$com-byd-vrassistant-view-GoogleInfoPopView, reason: not valid java name */
    /* synthetic */ void m192lambda$initView$2$combydvrassistantviewGoogleInfoPopView(View view) {
        this.viewManagerViewModel.setShowGoogleIngo(false);
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initViewModel() {
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
        this.commonViewModel.getSysLocaleLd().observe(this, new Observer() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m193x497bb05c((Locale) obj);
            }
        });
        this.commonViewModel.getContextLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setContext((Context) obj);
            }
        });
        this.commonViewModel.getSkinType().observe(this, new Observer() { // from class: com.byd.vrassistant.view.GoogleInfoPopView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setSkin(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initViewModel$3$com-byd-vrassistant-view-GoogleInfoPopView, reason: not valid java name */
    /* synthetic */ void m193x497bb05c(Locale locale) {
        TextViewUtils.getInstance().updateLocale(this, locale);
        setLayoutDirection(BridgeHelper.getVrAreaStatus().isRightDriver() ? 1 : 0);
    }

    public void setSkin(int i) {
        if (i == 1) {
            this.tvTitle.setTextColor(getResources().getColor(R.color.tv_g_title));
            this.tvContent.setTextColor(getResources().getColor(R.color.tv_g_content));
            this.tvOk.setTextColor(getResources().getColor(R.color.tv_g_ok));
            this.tvMore.setTextColor(getResources().getColor(R.color.tv_g_more));
            this.bgDialog.setBackground(ContextCompat.getDrawable(getContext(), R.mipmap.bg_dialog));
            this.vHori.setBackgroundColor(getResources().getColor(R.color.v_g_divider));
            return;
        }
        if (i == 2) {
            this.tvTitle.setTextColor(getResources().getColor(R.color.tv_g_title_night));
            this.tvContent.setTextColor(getResources().getColor(R.color.tv_g_content_night));
            this.tvOk.setTextColor(getResources().getColor(R.color.tv_g_ok));
            this.tvMore.setTextColor(getResources().getColor(R.color.tv_g_more_night));
            this.bgDialog.setBackground(ContextCompat.getDrawable(getContext(), R.mipmap.bg_dialog_dark));
            this.vHori.setBackgroundColor(getResources().getColor(R.color.v_g_divider_night));
        }
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}
