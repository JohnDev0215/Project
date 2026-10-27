package com.byd.ime.subtype.show;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.inputmethod.dictionarypack.MetadataDbHelper;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.R;
import com.byd.ime.subtype.SubtypeManager;
import com.byd.ime.subtype.model.SubtypeItem;
import com.byd.ime.subtype.show.MultiInputMethodSubtypePopupWindow;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiInputMethodSubtypePopupWindow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow;", "Landroid/widget/PopupWindow;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "mRes", "Landroid/content/res/Resources;", "mSubTypeAdapter", "Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow$SubTypeAdapter;", "mSubtypeItemList", "", "Lcom/byd/ime/subtype/model/SubtypeItem;", "mSubtypeManager", "Lcom/byd/ime/subtype/SubtypeManager;", "init", "", "showInEndBottom", "parent", "Landroid/view/View;", "Companion", "HeaderViewHolder", "SubTypeAdapter", "SubTypeViewHolder", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MultiInputMethodSubtypePopupWindow extends PopupWindow {
    public static final int SUBTYPE_ADAPTER_TYPE_HEADER = 2;
    public static final int SUBTYPE_ADAPTER_TYPE_NORMAL = 1;
    private Context mContext;
    private Resources mRes;
    private SubTypeAdapter mSubTypeAdapter;
    private List<SubtypeItem> mSubtypeItemList;
    private SubtypeManager mSubtypeManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiInputMethodSubtypePopupWindow(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        this.mSubtypeItemList = new ArrayList();
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        this.mRes = resources;
        SubtypeManager companion = SubtypeManager.INSTANCE.getInstance();
        this.mSubtypeManager = companion;
        this.mSubtypeItemList = companion.getMSubtypeCacheModel().getSelectItemList();
        init();
    }

    private final void init() {
        SubTypeAdapter subTypeAdapter = null;
        setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.select_subtype_window, (ViewGroup) null, false));
        View viewFindViewById = getContentView().findViewById(R.id.subtype_recyclerview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "contentView.findViewById….id.subtype_recyclerview)");
        MaxHeightRecyclerView maxHeightRecyclerView = (MaxHeightRecyclerView) viewFindViewById;
        maxHeightRecyclerView.setMMaxHeight(this.mRes.getDimensionPixelSize(R.dimen.sub_language_popup_max_height));
        setBackgroundDrawable(new ColorDrawable(0));
        setTouchable(true);
        setOutsideTouchable(true);
        this.mSubTypeAdapter = new SubTypeAdapter();
        maxHeightRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        SubTypeAdapter subTypeAdapter2 = this.mSubTypeAdapter;
        if (subTypeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSubTypeAdapter");
        } else {
            subTypeAdapter = subTypeAdapter2;
        }
        maxHeightRecyclerView.setAdapter(subTypeAdapter);
        int iIndexCurrentLocalFromSelectSubtype = this.mSubtypeManager.getMSubtypeCacheModel().indexCurrentLocalFromSelectSubtype();
        RecyclerView.LayoutManager layoutManager = maxHeightRecyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        ((LinearLayoutManager) layoutManager).scrollToPosition(iIndexCurrentLocalFromSelectSubtype);
    }

    /* JADX INFO: compiled from: MultiInputMethodSubtypePopupWindow.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow$SubTypeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow;Landroid/view/View;)V", "displayName", "Landroid/widget/TextView;", "getDisplayName", "()Landroid/widget/TextView;", "setDisplayName", "(Landroid/widget/TextView;)V", "subTypeParent", "getSubTypeParent", "()Landroid/view/View;", "setSubTypeParent", "(Landroid/view/View;)V", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SubTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView displayName;
        private View subTypeParent;
        final /* synthetic */ MultiInputMethodSubtypePopupWindow this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubTypeViewHolder(MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = multiInputMethodSubtypePopupWindow;
            View viewFindViewById = itemView.findViewById(R.id.subtype_parent);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.subtype_parent)");
            this.subTypeParent = viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.display_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.display_name)");
            this.displayName = (TextView) viewFindViewById2;
        }

        public final View getSubTypeParent() {
            return this.subTypeParent;
        }

        public final void setSubTypeParent(View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.subTypeParent = view;
        }

        public final TextView getDisplayName() {
            return this.displayName;
        }

        public final void setDisplayName(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.displayName = textView;
        }
    }

    /* JADX INFO: compiled from: MultiInputMethodSubtypePopupWindow.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016¨\u0006\u000f"}, d2 = {"Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow$SubTypeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "(Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "subTypeViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", MetadataDbHelper.TYPE_COLUMN, "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class SubTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int position) {
            return position == 0 ? 2 : 1;
        }

        public SubTypeAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            if (type == 2) {
                View viewInflate = LayoutInflater.from(MultiInputMethodSubtypePopupWindow.this.mContext).inflate(R.layout.to_all_subtype_item_layout, parent, false);
                Intrinsics.checkNotNullExpressionValue(viewInflate, "from(mContext)\n         …em_layout, parent, false)");
                final MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow = MultiInputMethodSubtypePopupWindow.this;
                viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.byd.ime.subtype.show.-$$Lambda$MultiInputMethodSubtypePopupWindow$SubTypeAdapter$_dlsB5rQ_Hpzf-H_fZA-L3hW6Qo
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MultiInputMethodSubtypePopupWindow.SubTypeAdapter.m8onCreateViewHolder$lambda0(multiInputMethodSubtypePopupWindow, view);
                    }
                });
                return new HeaderViewHolder(MultiInputMethodSubtypePopupWindow.this, viewInflate);
            }
            View viewInflate2 = LayoutInflater.from(MultiInputMethodSubtypePopupWindow.this.mContext).inflate(R.layout.select_subtype_item_layout, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate2, "from(mContext)\n         …em_layout, parent, false)");
            return new SubTypeViewHolder(MultiInputMethodSubtypePopupWindow.this, viewInflate2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onCreateViewHolder$lambda-0, reason: not valid java name */
        public static final void m8onCreateViewHolder$lambda0(MultiInputMethodSubtypePopupWindow this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismiss();
            AllSubtypeDialog allSubtypeDialog = new AllSubtypeDialog(this$0.mContext, R.style.DialogActivityStyle);
            Context context = this$0.mContext;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.android.inputmethod.latin.LatinIME");
            ((LatinIME) context).showOptionDialog(allSubtypeDialog);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder subTypeViewHolder, int position) {
            Intrinsics.checkNotNullParameter(subTypeViewHolder, "subTypeViewHolder");
            if (position == 0) {
                return;
            }
            final int i = position - 1;
            SubTypeViewHolder subTypeViewHolder2 = (SubTypeViewHolder) subTypeViewHolder;
            SubtypeItem subtypeItem = (SubtypeItem) MultiInputMethodSubtypePopupWindow.this.mSubtypeItemList.get(i);
            subTypeViewHolder2.getDisplayName().setText(subtypeItem.getLanguageDisplayName());
            if (Intrinsics.areEqual(subtypeItem.getLocal(), MultiInputMethodSubtypePopupWindow.this.mSubtypeManager.getMSubtypeCacheModel().getMCurrentSubtypeLocal())) {
                subTypeViewHolder2.getDisplayName().setSelected(true);
                subTypeViewHolder2.getDisplayName().setTextColor(MultiInputMethodSubtypePopupWindow.this.mRes.getColor(R.color.select_subtype_current_text_color, null));
            } else {
                subTypeViewHolder2.getDisplayName().setSelected(false);
                subTypeViewHolder2.getDisplayName().setTextColor(MultiInputMethodSubtypePopupWindow.this.mRes.getColor(R.color.select_subtype_normal_text_color, null));
            }
            View subTypeParent = subTypeViewHolder2.getSubTypeParent();
            final MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow = MultiInputMethodSubtypePopupWindow.this;
            subTypeParent.setOnClickListener(new View.OnClickListener() { // from class: com.byd.ime.subtype.show.-$$Lambda$MultiInputMethodSubtypePopupWindow$SubTypeAdapter$76T-Gg69RaN9CUOZzwDti59fOys
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MultiInputMethodSubtypePopupWindow.SubTypeAdapter.m7onBindViewHolder$lambda1(multiInputMethodSubtypePopupWindow, i, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBindViewHolder$lambda-1, reason: not valid java name */
        public static final void m7onBindViewHolder$lambda1(MultiInputMethodSubtypePopupWindow this$0, int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            SubtypeManager subtypeManager = this$0.mSubtypeManager;
            InputMethodSubtype rawSubtype = ((SubtypeItem) this$0.mSubtypeItemList.get(i)).getMRichInputMethodSubtype().getRawSubtype();
            Intrinsics.checkNotNullExpressionValue(rawSubtype, "mSubtypeItemList[index].…tMethodSubtype.rawSubtype");
            subtypeManager.switchLanguage(rawSubtype);
            this$0.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MultiInputMethodSubtypePopupWindow.this.mSubtypeItemList.size() + 1;
        }
    }

    /* JADX INFO: compiled from: MultiInputMethodSubtypePopupWindow.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/byd/ime/subtype/show/MultiInputMethodSubtypePopupWindow;Landroid/view/View;)V", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class HeaderViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ MultiInputMethodSubtypePopupWindow this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(MultiInputMethodSubtypePopupWindow multiInputMethodSubtypePopupWindow, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = multiInputMethodSubtypePopupWindow;
        }
    }

    public final void showInEndBottom(View parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        showAtLocation(parent, 8388693, this.mContext.getResources().getDimensionPixelSize(R.dimen.sub_language_popup_x), this.mContext.getResources().getDimensionPixelSize(R.dimen.sub_language_popup_y));
    }
}
