package com.byd.vrassistant.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.controller.event.FirstPageEvent;
import cerence.ark.assistant.controller.event.NaviOneResultSubcomfirmEvent;
import cerence.ark.assistant.controller.event.NaviOneResultSubdeclineEvent;
import cerence.ark.assistant.controller.event.NextPageEvent;
import cerence.ark.assistant.controller.event.PickListItemSelectedEvent;
import cerence.ark.assistant.controller.struct.DrivingModeInfo;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.controller.struct.RoadModeInfo;
import com.byd.vrassistant.base.LinearLayoutLifecycle;
import com.byd.vrassistant.bean.ResultFrom;
import com.byd.vrassistant.bean.VoiceBarListBean;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TextViewUtils;
import com.byd.vrassistant.view.viewBinder.ContactViewBinder;
import com.byd.vrassistant.view.viewBinder.DriveModeViewBinder;
import com.byd.vrassistant.view.viewBinder.OnItemClickListener;
import com.byd.vrassistant.view.viewBinder.PoiViewBinder;
import com.byd.vrassistant.view.viewBinder.RoadModeViewBinder;
import com.byd.vrassistant.viewInf.pickList.IPickListView;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.PickListViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.drakeet.multitype.ItemViewBinder;
import com.drakeet.multitype.MultiTypeAdapter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PickListView extends LinearLayoutLifecycle implements View.OnClickListener, IPickListView {
    private static final String TAG = "NEU_PickListView";
    MultiTypeAdapter adapter;
    CommonViewModel commonViewModel;
    protected Context mContext;
    private Group mControlGroup;
    protected TextView mIvCancel;
    protected TextView mIvExit;
    protected TextView mIvFirstPage;
    protected TextView mIvNextPage;
    protected TextView mIvOk;
    ImageView mIvVoice;
    protected RecyclerView mRecyclerView;
    private Group mSingleControlGroup;
    protected TextView mTvPageNum;
    PickListViewModel pickListViewModel;
    protected TextView tvResultFrom;
    ViewManagerViewModel viewManagerViewModel;

    @Override // com.byd.vrassistant.viewInf.common.IViewBase
    public View getView() {
        return this;
    }

    public PickListView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public PickListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PickListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.byd.vrassistant.base.LinearLayoutLifecycle
    public void initView() {
        RLog.i(TAG, "initView");
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.view_pick_list, (ViewGroup) this, true);
        this.mIvFirstPage = (TextView) viewInflate.findViewById(R.id.tv_first);
        this.mIvNextPage = (TextView) viewInflate.findViewById(R.id.tv_next);
        this.mIvExit = (TextView) viewInflate.findViewById(R.id.tv_exit);
        this.mIvOk = (TextView) viewInflate.findViewById(R.id.btn_ok);
        this.mIvCancel = (TextView) viewInflate.findViewById(R.id.btn_cancel);
        this.mTvPageNum = (TextView) viewInflate.findViewById(R.id.tv_page);
        this.mIvVoice = (ImageView) viewInflate.findViewById(R.id.iv_img);
        this.mControlGroup = (Group) viewInflate.findViewById(R.id.group_three);
        this.mSingleControlGroup = (Group) viewInflate.findViewById(R.id.group_two);
        this.tvResultFrom = (TextView) viewInflate.findViewById(R.id.tv_result_from);
        this.mControlGroup.setVisibility(8);
        this.mSingleControlGroup.setVisibility(8);
        this.mIvFirstPage.setOnClickListener(this);
        this.mIvNextPage.setOnClickListener(this);
        this.mIvExit.setOnClickListener(this);
        this.mIvOk.setOnClickListener(this);
        this.mIvCancel.setOnClickListener(this);
        this.tvResultFrom.setOnClickListener(this);
        this.mRecyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv_voice_bar_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.adapter = new MultiTypeAdapter();
        linearLayoutManager.setOrientation(1);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.adapter);
        OnItemClickListener onItemClickListener = new OnItemClickListener() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda9
            @Override // com.byd.vrassistant.view.viewBinder.OnItemClickListener
            public final void onClick(View view, int i) {
                PickListView.lambda$initView$0(view, i);
            }
        };
        this.adapter.register(VoiceBarListBean.class, (ItemViewBinder) new ContactViewBinder(onItemClickListener));
        this.adapter.register(DrivingModeInfo.class, (ItemViewBinder) new DriveModeViewBinder(onItemClickListener));
        this.adapter.register(PlaceInfo.class, (ItemViewBinder) new PoiViewBinder(onItemClickListener));
        this.adapter.register(RoadModeInfo.class, (ItemViewBinder) new RoadModeViewBinder(onItemClickListener));
    }

    static /* synthetic */ void lambda$initView$0(View view, int i) {
        RLog.i(TAG, "onItemClick_position==" + i);
        ArkAssistant.get().sendAppEvent(new PickListItemSelectedEvent.Builder().setItemOrdinal(i + 1).build(), null);
    }

    @Override // com.byd.vrassistant.base.LinearLayoutLifecycle
    public void initViewModel() {
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
        this.pickListViewModel = (PickListViewModel) ViewModelHelper.getAppViewModel(PickListViewModel.class);
        this.commonViewModel.getContextLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setContext((Context) obj);
            }
        });
        this.commonViewModel.getLocaleLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m201lambda$initViewModel$1$combydvrassistantviewPickListView((Locale) obj);
            }
        });
        this.commonViewModel.getSkinType().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setSkin(((Integer) obj).intValue());
            }
        });
        this.pickListViewModel.getListData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m202lambda$initViewModel$2$combydvrassistantviewPickListView((List) obj);
            }
        });
        this.pickListViewModel.getListSize().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.updateControlVisibility(((Integer) obj).intValue());
            }
        });
        this.pickListViewModel.getPageInfo().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.updatePageUi((Pair) obj);
            }
        });
        this.pickListViewModel.getResultFrom().observe(this, new Observer() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setResultFrom((ResultFrom) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initViewModel$1$com-byd-vrassistant-view-PickListView, reason: not valid java name */
    /* synthetic */ void m201lambda$initViewModel$1$combydvrassistantviewPickListView(Locale locale) {
        TextViewUtils.getInstance().updateLocale(this, locale);
    }

    /* JADX INFO: renamed from: lambda$initViewModel$2$com-byd-vrassistant-view-PickListView, reason: not valid java name */
    /* synthetic */ void m202lambda$initViewModel$2$combydvrassistantviewPickListView(List list) {
        this.adapter.setItems(list);
        this.adapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSkin(int i) {
        if (i == 1) {
            setBtnBg(R.mipmap.bg_voice_btn);
            this.mIvOk.setBackgroundResource(R.mipmap.bg_ok);
            this.mIvCancel.setBackgroundResource(R.mipmap.bg_cancel);
            this.mIvOk.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            this.mIvCancel.setTextColor(ContextCompat.getColor(getContext(), R.color.cancel_text));
            this.mIvVoice.setImageResource(R.mipmap.iv_voice_img);
            setBtnTxtColor(ContextCompat.getColor(getContext(), R.color.list_text_85_light));
            this.tvResultFrom.setTextColor(getResources().getColor(R.color.tv_picklist_info));
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.info);
            if (drawable != null) {
                drawable.setBounds(0, 0, 20, 20);
            }
            this.tvResultFrom.setCompoundDrawables(null, null, drawable, null);
        } else if (i == 2) {
            setBtnBg(R.mipmap.bg_voice_btn_dark);
            this.mIvOk.setBackgroundResource(R.mipmap.bg_ok);
            this.mIvCancel.setBackgroundResource(R.mipmap.bg_cancel_dark);
            this.mIvOk.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            this.mIvCancel.setTextColor(ContextCompat.getColor(getContext(), R.color.cancel_text_dark));
            setBtnTxtColor(ContextCompat.getColor(getContext(), R.color.list_text_85_dark));
            this.mIvVoice.setImageResource(R.mipmap.iv_voice_img_dark);
            this.tvResultFrom.setTextColor(getResources().getColor(R.color.tv_picklist_info_dark));
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.info_night);
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, 20, 20);
            }
            this.tvResultFrom.setCompoundDrawables(null, null, drawable2, null);
        }
        if (this.adapter.getItemCount() > 0) {
            this.adapter.notifyDataSetChanged();
        }
    }

    public void setBtnBg(int i) {
        this.mIvFirstPage.setBackgroundResource(i);
        this.mIvNextPage.setBackgroundResource(i);
        this.mIvExit.setBackgroundResource(i);
    }

    public void setBtnTxtColor(int i) {
        this.mIvFirstPage.setTextColor(i);
        this.mIvNextPage.setTextColor(i);
        this.mIvExit.setTextColor(i);
    }

    public void setResultFrom(ResultFrom resultFrom) {
        if (Objects.requireNonNull(resultFrom) == ResultFrom.GOOGLE) {
            this.tvResultFrom.setText(this.mContext.getString(R.string.result_from));
            this.tvResultFrom.setVisibility(0);
        } else {
            this.tvResultFrom.setText(R.string.blank);
            this.tvResultFrom.setVisibility(8);
        }
    }

    public void updateControlVisibility(int i) {
        Log.i(TAG, "updateControlVisibility: " + i);
        if (i == 1) {
            this.mControlGroup.setVisibility(8);
            this.mSingleControlGroup.setVisibility(0);
            this.mIvVoice.setVisibility(0);
        } else if (i > 3) {
            this.mSingleControlGroup.setVisibility(8);
            this.mControlGroup.setVisibility(0);
            this.mIvVoice.setVisibility(0);
        } else {
            this.mSingleControlGroup.setVisibility(8);
            this.mControlGroup.setVisibility(8);
            this.mIvVoice.setVisibility(8);
        }
    }

    public void setContext(Context context) {
        Log.i(TAG, "setContext: ");
        this.mContext = context;
        this.mIvFirstPage.setText(context.getString(R.string.text_first));
        this.mIvCancel.setText(this.mContext.getString(R.string.text_cancel));
        this.mIvNextPage.setText(this.mContext.getString(R.string.text_next));
        this.mIvExit.setText(this.mContext.getString(R.string.text_exit));
        this.mIvOk.setText(this.mContext.getString(R.string.text_ok));
        this.tvResultFrom.setText(this.mContext.getString(R.string.result_from));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_next) {
            RLog.i(TAG, "onClick tv_next");
            ArkAssistant.get().sendAppEvent(new NextPageEvent.Builder().build(), new IResultCallback() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda7
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    RLog.d(PickListView.TAG, "send NextPage dialog event is success = " + result.isSuccess());
                }
            });
        }
        if (id == R.id.tv_first) {
            RLog.i(TAG, "onClick tv_first");
            ArkAssistant.get().sendAppEvent(new FirstPageEvent.Builder().build(), new IResultCallback() { // from class: com.byd.vrassistant.view.PickListView$$ExternalSyntheticLambda8
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    RLog.d(PickListView.TAG, "send FirstPage dialog event is success = " + result.isSuccess());
                }
            });
        }
        if (id == R.id.tv_exit) {
            RLog.i(TAG, "onClick tv_exit");
            ViewStateManger.get().executeCancelPtt();
            this.viewManagerViewModel.removeViewBlew();
        }
        if (id == R.id.btn_cancel) {
            RLog.i(TAG, "onClick btn_cancel");
            ArkAssistant.get().sendAppEvent(new NaviOneResultSubdeclineEvent.Builder().build(), null);
            this.viewManagerViewModel.removeViewBlew();
        }
        if (id == R.id.btn_ok) {
            RLog.i(TAG, "onClick btn_ok");
            ArkAssistant.get().sendAppEvent(new NaviOneResultSubcomfirmEvent.Builder().build(), null);
            this.viewManagerViewModel.removeViewBlew();
        }
        if (id == R.id.tv_result_from) {
            RLog.i(TAG, "onClick tv_result_from");
            ViewStateManger.get().executeCancelPtt();
            this.viewManagerViewModel.removeViewBlew();
            this.viewManagerViewModel.setShowGoogleIngo(true);
        }
    }

    public void updatePageUi(Pair<Integer, Integer> pair) {
        RLog.i(TAG, "updatePageUi_info==" + pair);
        this.mTvPageNum.setText(String.format(Locale.ENGLISH, "%d/%d", pair.first, pair.second));
    }
}
