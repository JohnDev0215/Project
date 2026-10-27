package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.view.KatanaRecyclerviewBinding;
import com.amazon.mp3.fragment.view.recyclerview.adapter.TwoLinesListPreferenceAdapter;
import com.amazon.mp3.fragment.view.recyclerview.listener.RecyclerViewItemClickListener;
import com.amazon.mp3.view.AnchoredPopupWindowBuilder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TwoLinesPreferenceRecyclerViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 62\u00020\u0001:\u00016B/\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b4\u00105J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR(\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u00067"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "setupCurrentState", "setupRecyclerViewItemListener", "setupRecyclerView", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "twoListPrefModels", "Ljava/util/List;", "getTwoListPrefModels", "()Ljava/util/List;", "setTwoListPrefModels", "(Ljava/util/List;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Lcom/amazon/mp3/fragment/view/recyclerview/listener/RecyclerViewItemClickListener;", "mRecyclerViewItemClickListener", "Lcom/amazon/mp3/fragment/view/recyclerview/listener/RecyclerViewItemClickListener;", "getMRecyclerViewItemClickListener", "()Lcom/amazon/mp3/fragment/view/recyclerview/listener/RecyclerViewItemClickListener;", "setMRecyclerViewItemClickListener", "(Lcom/amazon/mp3/fragment/view/recyclerview/listener/RecyclerViewItemClickListener;)V", "Lcom/amazon/mp3/fragment/view/recyclerview/adapter/TwoLinesListPreferenceAdapter;", "mTwoLinesListPreferenceAdapter", "Lcom/amazon/mp3/fragment/view/recyclerview/adapter/TwoLinesListPreferenceAdapter;", "getMTwoLinesListPreferenceAdapter", "()Lcom/amazon/mp3/fragment/view/recyclerview/adapter/TwoLinesListPreferenceAdapter;", "setMTwoLinesListPreferenceAdapter", "(Lcom/amazon/mp3/fragment/view/recyclerview/adapter/TwoLinesListPreferenceAdapter;)V", "", "currentCheckedPosition", "I", "getCurrentCheckedPosition", "()I", "setCurrentCheckedPosition", "(I)V", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;Ljava/util/List;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TwoLinesPreferenceRecyclerViewModel extends BaseViewModel {
    private final Context context;
    private int currentCheckedPosition;
    private final KatanaRecyclerviewBinding mBinding;
    public RecyclerViewItemClickListener mRecyclerViewItemClickListener;
    public TwoLinesListPreferenceAdapter mTwoLinesListPreferenceAdapter;
    private List<TwoLinesListPreferenceModel> twoListPrefModels;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final List<TwoLinesListPreferenceModel> getTwoListPrefModels() {
        return this.twoListPrefModels;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TwoLinesPreferenceRecyclerViewModel(Context context, KatanaRecyclerviewBinding mBinding, List<TwoLinesListPreferenceModel> twoListPrefModels, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(twoListPrefModels, "twoListPrefModels");
        this.context = context;
        this.mBinding = mBinding;
        this.twoListPrefModels = twoListPrefModels;
        this.viewModelCallbacks = viewModelCallbacks;
        setupCurrentState();
        setupRecyclerViewItemListener();
        setupRecyclerView();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    public final RecyclerViewItemClickListener getMRecyclerViewItemClickListener() {
        RecyclerViewItemClickListener recyclerViewItemClickListener = this.mRecyclerViewItemClickListener;
        if (recyclerViewItemClickListener != null) {
            return recyclerViewItemClickListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mRecyclerViewItemClickListener");
        return null;
    }

    public final void setMRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        Intrinsics.checkNotNullParameter(recyclerViewItemClickListener, "<set-?>");
        this.mRecyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    public final TwoLinesListPreferenceAdapter getMTwoLinesListPreferenceAdapter() {
        TwoLinesListPreferenceAdapter twoLinesListPreferenceAdapter = this.mTwoLinesListPreferenceAdapter;
        if (twoLinesListPreferenceAdapter != null) {
            return twoLinesListPreferenceAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mTwoLinesListPreferenceAdapter");
        return null;
    }

    public final void setMTwoLinesListPreferenceAdapter(TwoLinesListPreferenceAdapter twoLinesListPreferenceAdapter) {
        Intrinsics.checkNotNullParameter(twoLinesListPreferenceAdapter, "<set-?>");
        this.mTwoLinesListPreferenceAdapter = twoLinesListPreferenceAdapter;
    }

    public final int getCurrentCheckedPosition() {
        return this.currentCheckedPosition;
    }

    public final void setCurrentCheckedPosition(int i) {
        this.currentCheckedPosition = i;
    }

    private final void setupCurrentState() {
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel : this.twoListPrefModels) {
            if (twoLinesListPreferenceModel.getIsChecked()) {
                this.currentCheckedPosition = this.twoListPrefModels.indexOf(twoLinesListPreferenceModel);
            }
        }
    }

    private final void setupRecyclerViewItemListener() {
        setMRecyclerViewItemClickListener(new RecyclerViewItemClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.TwoLinesPreferenceRecyclerViewModel.setupRecyclerViewItemListener.1
            @Override // com.amazon.mp3.fragment.view.recyclerview.listener.RecyclerViewItemClickListener
            public void onClick(View view, int position) {
                Intrinsics.checkNotNullParameter(view, "view");
                if (view.getId() == R.id.info_icon) {
                    String description = TwoLinesPreferenceRecyclerViewModel.this.getTwoListPrefModels().get(position).getDescription();
                    if (description == null || description.length() == 0) {
                        return;
                    }
                    AnchoredPopupWindowBuilder.INSTANCE.with(TwoLinesPreferenceRecyclerViewModel.this.getContext()).setAnchorView(view).setContentView(R.layout.popup_info).setMessage(description).setGravity(48).setMargin(TwoLinesPreferenceRecyclerViewModel.this.getContext().getResources().getDimensionPixelSize(R.dimen.spacing_4dp)).build().show();
                    return;
                }
                if (position != TwoLinesPreferenceRecyclerViewModel.this.getCurrentCheckedPosition()) {
                    TwoLinesPreferenceRecyclerViewModel.this.getTwoListPrefModels().get(TwoLinesPreferenceRecyclerViewModel.this.getCurrentCheckedPosition()).setChecked(false);
                    TwoLinesPreferenceRecyclerViewModel.this.getTwoListPrefModels().get(position).setChecked(true);
                    TwoLinesPreferenceRecyclerViewModel.this.getMTwoLinesListPreferenceAdapter().notifyItemChanged(TwoLinesPreferenceRecyclerViewModel.this.getCurrentCheckedPosition());
                    TwoLinesPreferenceRecyclerViewModel.this.getMTwoLinesListPreferenceAdapter().notifyItemChanged(position);
                    TwoLinesPreferenceRecyclerViewModel.this.setCurrentCheckedPosition(position);
                    ViewModelCallbacks viewModelCallbacks = TwoLinesPreferenceRecyclerViewModel.this.getViewModelCallbacks();
                    if (viewModelCallbacks == null) {
                        return;
                    }
                    viewModelCallbacks.onEventOccurred(1, 1, TwoLinesPreferenceRecyclerViewModel.this);
                }
            }
        });
    }

    private final void setupRecyclerView() {
        setMTwoLinesListPreferenceAdapter(new TwoLinesListPreferenceAdapter(getContext(), getMRecyclerViewItemClickListener()));
        getMTwoLinesListPreferenceAdapter().setItems(this.twoListPrefModels);
        this.mBinding.getMRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.getMRecyclerView().setNestedScrollingEnabled(true);
        this.mBinding.getMRecyclerView().setAdapter(getMTwoLinesListPreferenceAdapter());
    }
}
