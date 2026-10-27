package com.byd.ime.subtype.show;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.inputmethod.dictionarypack.MetadataDbHelper;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.byd.ime.BydFeatureFlags;
import com.byd.ime.subtype.SubtypeManager;
import com.byd.ime.subtype.model.SubtypeCacheModel;
import com.byd.ime.subtype.model.SubtypeConfigData;
import com.byd.ime.subtype.model.SubtypeItem;
import com.byd.ime.subtype.model.SubtypeStoreModel;
import com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 \u001e2\u00020\u0001:\u0005\u001d\u001e\u001f !B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0011J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u0014H\u0016J\u0006\u0010\u001a\u001a\u00020\u0011J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter;", "Landroid/view/View$OnClickListener;", "()V", "mCallBack", "Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$CallBack;", "mContext", "Landroid/content/Context;", "mRes", "Landroid/content/res/Resources;", "mSubTypeAdapter", "Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$SubTypeAdapter;", "mSubtypeCacheModel", "Lcom/byd/ime/subtype/model/SubtypeCacheModel;", "mTmpUserSelectLocalList", "", "", "clickOpen", "", "doConfirmAndUpdateData", "findAndInitContentView", "Landroid/view/View;", "init", "context", "onBackPressed", "onClick", "v", "saveUserSelectLocal", "setCallback", "callBack", "CallBack", "Companion", "FooterViewHolder", "SubTypeAdapter", "SubTypeViewHolder", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeSettingsActivityPresenter implements View.OnClickListener {
    public static final int SUBTYPE_ADAPTER_TYPE_FOOTER = 2;
    public static final int SUBTYPE_ADAPTER_TYPE_NORMAL = 1;
    private CallBack mCallBack;
    private Context mContext;
    private Resources mRes;
    private SubTypeAdapter mSubTypeAdapter;
    private SubtypeCacheModel mSubtypeCacheModel;
    private List<String> mTmpUserSelectLocalList = new ArrayList();

    /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$CallBack;", "", "doClose", "", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface CallBack {
        void doClose();
    }

    public final void clickOpen() {
    }

    public final void setCallback(CallBack callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        this.mCallBack = callBack;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        this.mSubtypeCacheModel = SubtypeManager.INSTANCE.getInstance().getMSubtypeCacheModel();
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        this.mRes = resources;
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        if (subtypeCacheModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
            subtypeCacheModel = null;
        }
        List<SubtypeItem> selectItemList = subtypeCacheModel.getSelectItemList();
        int size = selectItemList.size();
        for (int i = 0; i < size; i++) {
            this.mTmpUserSelectLocalList.add(selectItemList.get(i).getLocal());
        }
    }

    public final View findAndInitContentView() {
        int i = BydFeatureFlags.INSTANCE.isRightRudderCar() ? R.layout.subtype_settings_actiivty_right_rudder : R.layout.subtype_settings_actiivty;
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        View parentView = LayoutInflater.from(context).inflate(i, (ViewGroup) null, false);
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter = this;
        parentView.findViewById(R.id.activity_layout).setOnClickListener(subtypeSettingsActivityPresenter);
        parentView.findViewById(R.id.all_sub_layout).setOnClickListener(subtypeSettingsActivityPresenter);
        parentView.findViewById(R.id.confirm).setOnClickListener(subtypeSettingsActivityPresenter);
        parentView.findViewById(R.id.cancel).setOnClickListener(subtypeSettingsActivityPresenter);
        View viewFindViewById = parentView.findViewById(R.id.subtype_recyclerview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "parentView.findViewById(R.id.subtype_recyclerview)");
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        this.mSubTypeAdapter = new SubTypeAdapter();
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context2 = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context2, 1, false));
        SubTypeAdapter subTypeAdapter = this.mSubTypeAdapter;
        if (subTypeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubTypeAdapter");
            subTypeAdapter = null;
        }
        recyclerView.setAdapter(subTypeAdapter);
        recyclerView.setItemAnimator(null);
        Intrinsics.checkNotNullExpressionValue(parentView, "parentView");
        return parentView;
    }

    public final void onBackPressed() {
        CallBack callBack = this.mCallBack;
        if (callBack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCallBack");
            callBack = null;
        }
        callBack.doClose();
    }

    /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, d2 = {"Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$SubTypeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "(Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "subTypeViewHolder", "index", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", MetadataDbHelper.TYPE_COLUMN, "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SubTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public SubTypeAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int position) {
            return position == getItemCount() - 1 ? 2 : 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Context context = null;
            if (type == 2) {
                Context context2 = SubtypeSettingsActivityPresenter.this.mContext;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context = context2;
                }
                View viewInflate = LayoutInflater.from(context).inflate(R.layout.all_subtype_footer_item_layout, parent, false);
                Intrinsics.checkNotNullExpressionValue(viewInflate, "from(mContext)\n         …em_layout, parent, false)");
                return new FooterViewHolder(SubtypeSettingsActivityPresenter.this, viewInflate);
            }
            Context context3 = SubtypeSettingsActivityPresenter.this.mContext;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context = context3;
            }
            View viewInflate2 = LayoutInflater.from(context).inflate(R.layout.all_subtype_item_layout, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate2, "from(mContext)\n         …em_layout, parent, false)");
            return new SubTypeViewHolder(SubtypeSettingsActivityPresenter.this, viewInflate2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder subTypeViewHolder, final int index) {
            Intrinsics.checkNotNullParameter(subTypeViewHolder, "subTypeViewHolder");
            if (index >= getItemCount() - 1) {
                return;
            }
            SubTypeViewHolder subTypeViewHolder2 = (SubTypeViewHolder) subTypeViewHolder;
            subTypeViewHolder2.getCheckImage().setOnCheckedChangeListener(null);
            SubtypeCacheModel subtypeCacheModel = SubtypeSettingsActivityPresenter.this.mSubtypeCacheModel;
            if (subtypeCacheModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                subtypeCacheModel = null;
            }
            final SubtypeItem subtypeItem = subtypeCacheModel.getAllSubtypeItemList().get(index);
            final boolean zContains = SubtypeSettingsActivityPresenter.this.mTmpUserSelectLocalList.contains(subtypeItem.getLocal());
            subTypeViewHolder2.getDisplayName().setText(subtypeItem.getDisplayNameInSystemLocale());
            if (Intrinsics.areEqual(subtypeItem.getLocal(), SubtypeConfigData.DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN)) {
                subTypeViewHolder2.getCheckImage().setEnabled(false);
                Switch checkImage = subTypeViewHolder2.getCheckImage();
                Resources resources = SubtypeSettingsActivityPresenter.this.mRes;
                if (resources == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mRes");
                    resources = null;
                }
                checkImage.setBackground(resources.getDrawable(R.drawable.check_state_default_check, null));
            } else {
                subTypeViewHolder2.getCheckImage().setEnabled(true);
                subTypeViewHolder2.updateCheckImage(zContains);
            }
            View subTypeParent = subTypeViewHolder2.getSubTypeParent();
            final SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter = SubtypeSettingsActivityPresenter.this;
            subTypeParent.setOnClickListener(new View.OnClickListener() { // from class: com.byd.ime.subtype.show.-$$Lambda$SubtypeSettingsActivityPresenter$SubTypeAdapter$AR3ue4NvBGSeGK8PspFHmqnMa3M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SubtypeSettingsActivityPresenter.SubTypeAdapter.m9onBindViewHolder$lambda0(subtypeItem, zContains, subtypeSettingsActivityPresenter, this, index, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
        public static final void m9onBindViewHolder$lambda0(SubtypeItem subtypeItem, boolean z, SubtypeSettingsActivityPresenter this$0, SubTypeAdapter this$1, int i, View view) {
            Intrinsics.checkNotNullParameter(subtypeItem, "$subtypeItem");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            if (Intrinsics.areEqual(subtypeItem.getLocal(), SubtypeConfigData.DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN)) {
                return;
            }
            if (z) {
                this$0.mTmpUserSelectLocalList.remove(subtypeItem.getLocal());
            } else {
                this$0.mTmpUserSelectLocalList.add(subtypeItem.getLocal());
            }
            this$1.notifyItemChanged(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            SubtypeCacheModel subtypeCacheModel = SubtypeSettingsActivityPresenter.this.mSubtypeCacheModel;
            if (subtypeCacheModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                subtypeCacheModel = null;
            }
            return subtypeCacheModel.getAllSubtypeItemList().size() + 1;
        }
    }

    /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$SubTypeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter;Landroid/view/View;)V", "checkImage", "Landroid/widget/Switch;", "getCheckImage", "()Landroid/widget/Switch;", "setCheckImage", "(Landroid/widget/Switch;)V", "displayName", "Landroid/widget/TextView;", "getDisplayName", "()Landroid/widget/TextView;", "setDisplayName", "(Landroid/widget/TextView;)V", "subTypeParent", "getSubTypeParent", "()Landroid/view/View;", "setSubTypeParent", "(Landroid/view/View;)V", "updateCheckImage", "", "check", "", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SubTypeViewHolder extends RecyclerView.ViewHolder {
        private Switch checkImage;
        private TextView displayName;
        private View subTypeParent;
        final /* synthetic */ SubtypeSettingsActivityPresenter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubTypeViewHolder(SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = subtypeSettingsActivityPresenter;
            View viewFindViewById = itemView.findViewById(R.id.subtype_parent);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.subtype_parent)");
            this.subTypeParent = viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.state_check);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.state_check)");
            this.checkImage = (Switch) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.display_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.display_name)");
            this.displayName = (TextView) viewFindViewById3;
        }

        public final View getSubTypeParent() {
            return this.subTypeParent;
        }

        public final void setSubTypeParent(View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.subTypeParent = view;
        }

        public final Switch getCheckImage() {
            return this.checkImage;
        }

        public final void setCheckImage(Switch r2) {
            Intrinsics.checkNotNullParameter(r2, "<set-?>");
            this.checkImage = r2;
        }

        public final TextView getDisplayName() {
            return this.displayName;
        }

        public final void setDisplayName(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.displayName = textView;
        }

        public final void updateCheckImage(boolean check) {
            if (check) {
                Switch r4 = this.checkImage;
                Resources resources = this.this$0.mRes;
                if (resources == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mRes");
                    resources = null;
                }
                r4.setBackground(resources.getDrawable(R.drawable.check_state_check, null));
                return;
            }
            Switch r42 = this.checkImage;
            Resources resources2 = this.this$0.mRes;
            if (resources2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRes");
                resources2 = null;
            }
            r42.setBackground(resources2.getDrawable(R.drawable.check_state_normal, null));
        }
    }

    /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter$FooterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/byd/ime/subtype/show/SubtypeSettingsActivityPresenter;Landroid/view/View;)V", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class FooterViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ SubtypeSettingsActivityPresenter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FooterViewHolder(SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = subtypeSettingsActivityPresenter;
        }
    }

    public final void saveUserSelectLocal() {
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        Context context = null;
        if (subtypeCacheModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
            subtypeCacheModel = null;
        }
        subtypeCacheModel.clearSelectSubTypeItemLists();
        int size = this.mTmpUserSelectLocalList.size();
        for (int i = 0; i < size; i++) {
            String str = this.mTmpUserSelectLocalList.get(i);
            SubtypeCacheModel subtypeCacheModel2 = this.mSubtypeCacheModel;
            if (subtypeCacheModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                subtypeCacheModel2 = null;
            }
            SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal = subtypeCacheModel2.indexSubTypeItemFromAllSubtypeBySubtypeLocal(str);
            if (subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal != null) {
                SubtypeCacheModel subtypeCacheModel3 = this.mSubtypeCacheModel;
                if (subtypeCacheModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                    subtypeCacheModel3 = null;
                }
                subtypeCacheModel3.addSubTypeItemToSelect(subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal);
            }
        }
        List<String> list = this.mTmpUserSelectLocalList;
        SubtypeCacheModel subtypeCacheModel4 = this.mSubtypeCacheModel;
        if (subtypeCacheModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
            subtypeCacheModel4 = null;
        }
        if (!list.contains(subtypeCacheModel4.getMCurrentSubtypeLocal())) {
            SubtypeCacheModel subtypeCacheModel5 = this.mSubtypeCacheModel;
            if (subtypeCacheModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                subtypeCacheModel5 = null;
            }
            SubtypeItem systemLanguageSubtype = subtypeCacheModel5.getSystemLanguageSubtype();
            if (systemLanguageSubtype != null) {
                String local = systemLanguageSubtype.getLocal();
                if (this.mTmpUserSelectLocalList.contains(local)) {
                    SubtypeManager.INSTANCE.getInstance().setCurrentSubtype(local);
                    SubtypeManager companion = SubtypeManager.INSTANCE.getInstance();
                    InputMethodSubtype rawSubtype = systemLanguageSubtype.getMRichInputMethodSubtype().getRawSubtype();
                    Intrinsics.checkNotNullExpressionValue(rawSubtype, "subtypeItem.mRichInputMethodSubtype.rawSubtype");
                    companion.switchLanguage(rawSubtype);
                } else {
                    SubtypeManager.INSTANCE.getInstance().setCurrentSubtype(SubtypeConfigData.DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN);
                    SubtypeManager companion2 = SubtypeManager.INSTANCE.getInstance();
                    SubtypeCacheModel subtypeCacheModel6 = this.mSubtypeCacheModel;
                    if (subtypeCacheModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                        subtypeCacheModel6 = null;
                    }
                    SubtypeItem defaultSubtypeEN = subtypeCacheModel6.getDefaultSubtypeEN();
                    Intrinsics.checkNotNull(defaultSubtypeEN);
                    RichInputMethodSubtype mRichInputMethodSubtype = defaultSubtypeEN.getMRichInputMethodSubtype();
                    Intrinsics.checkNotNull(mRichInputMethodSubtype);
                    InputMethodSubtype rawSubtype2 = mRichInputMethodSubtype.getRawSubtype();
                    Intrinsics.checkNotNullExpressionValue(rawSubtype2, "mSubtypeCacheModel.getDe…ethodSubtype!!.rawSubtype");
                    companion2.switchLanguage(rawSubtype2);
                }
            } else {
                SubtypeManager.INSTANCE.getInstance().setCurrentSubtype(SubtypeConfigData.DEFAULT_SUBTYPE_LOCAL_LANGUAGE_EN);
                SubtypeManager companion3 = SubtypeManager.INSTANCE.getInstance();
                SubtypeCacheModel subtypeCacheModel7 = this.mSubtypeCacheModel;
                if (subtypeCacheModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
                    subtypeCacheModel7 = null;
                }
                SubtypeItem defaultSubtypeEN2 = subtypeCacheModel7.getDefaultSubtypeEN();
                Intrinsics.checkNotNull(defaultSubtypeEN2);
                RichInputMethodSubtype mRichInputMethodSubtype2 = defaultSubtypeEN2.getMRichInputMethodSubtype();
                Intrinsics.checkNotNull(mRichInputMethodSubtype2);
                InputMethodSubtype rawSubtype3 = mRichInputMethodSubtype2.getRawSubtype();
                Intrinsics.checkNotNullExpressionValue(rawSubtype3, "mSubtypeCacheModel.getDe…ethodSubtype!!.rawSubtype");
                companion3.switchLanguage(rawSubtype3);
            }
        }
        SubtypeCacheModel subtypeCacheModel8 = this.mSubtypeCacheModel;
        if (subtypeCacheModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
            subtypeCacheModel8 = null;
        }
        subtypeCacheModel8.sortSelectItemListListByAllOrder();
        SubtypeStoreModel.Companion companion4 = SubtypeStoreModel.INSTANCE;
        SubtypeCacheModel subtypeCacheModel9 = this.mSubtypeCacheModel;
        if (subtypeCacheModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubtypeCacheModel");
            subtypeCacheModel9 = null;
        }
        List<SubtypeItem> selectItemList = subtypeCacheModel9.getSelectItemList();
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context2;
        }
        companion4.saveUserSelectItemToStore(selectItemList, context);
    }

    /* JADX INFO: renamed from: com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1", f = "SubtypeSettingsActivityPresenter.kt", i = {}, l = {197, 200}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SubtypeSettingsActivityPresenter.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C00081(SubtypeSettingsActivityPresenter.this, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(SubtypeSettingsActivityPresenter.this, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1$1", f = "SubtypeSettingsActivityPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SubtypeSettingsActivityPresenter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00081(SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter, Continuation<? super C00081> continuation) {
                super(2, continuation);
                this.this$0 = subtypeSettingsActivityPresenter;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00081(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.saveUserSelectLocal();
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SubtypeSettingsActivityPresenter.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter$doConfirmAndUpdateData$1$2", f = "SubtypeSettingsActivityPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SubtypeSettingsActivityPresenter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = subtypeSettingsActivityPresenter;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CallBack callBack = this.this$0.mCallBack;
                if (callBack == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCallBack");
                    callBack = null;
                }
                callBack.doClose();
                return Unit.INSTANCE;
            }
        }
    }

    private final void doConfirmAndUpdateData() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(new CoroutineName("SettingsActivity")), null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNull(v);
        CallBack callBack = null;
        switch (v.getId()) {
            case R.id.activity_layout /* 2131296271 */:
                CallBack callBack2 = this.mCallBack;
                if (callBack2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCallBack");
                } else {
                    callBack = callBack2;
                }
                callBack.doClose();
                break;
            case R.id.all_sub_layout /* 2131296277 */:
                clickOpen();
                break;
            case R.id.cancel /* 2131296301 */:
                CallBack callBack3 = this.mCallBack;
                if (callBack3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCallBack");
                } else {
                    callBack = callBack3;
                }
                callBack.doClose();
                break;
            case R.id.confirm /* 2131296303 */:
                doConfirmAndUpdateData();
                break;
        }
    }
}
