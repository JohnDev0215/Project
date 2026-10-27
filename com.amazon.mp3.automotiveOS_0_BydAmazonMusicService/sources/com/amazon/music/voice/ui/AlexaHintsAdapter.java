package com.amazon.music.voice.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.voice.R$id;
import com.amazon.music.voice.R$layout;
import com.amazon.music.voice.ui.AlexaHintsAdapter;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaHintsAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001eB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\b2\n\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR0\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/voice/ui/AlexaHintsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/voice/ui/AlexaHintsAdapter$ViewHolder;", "alexaUIStyleProvider", "Lcom/amazon/music/voice/ui/AlexaUIStyleProvider;", "itemClickListener", "Lkotlin/Function1;", "", "", "(Lcom/amazon/music/voice/ui/AlexaUIStyleProvider;Lkotlin/jvm/functions/Function1;)V", "getAlexaUIStyleProvider", "()Lcom/amazon/music/voice/ui/AlexaUIStyleProvider;", "value", "", "hints", "getHints", "()Ljava/util/List;", "setHints", "(Ljava/util/List;)V", "getItemClickListener", "()Lkotlin/jvm/functions/Function1;", "getItemCount", "", "onBindViewHolder", "viewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaHintsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final AlexaUIStyleProvider alexaUIStyleProvider;
    private List<String> hints;
    private final Function1<String, Unit> itemClickListener;

    public final AlexaUIStyleProvider getAlexaUIStyleProvider() {
        return this.alexaUIStyleProvider;
    }

    public final Function1<String, Unit> getItemClickListener() {
        return this.itemClickListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AlexaHintsAdapter(AlexaUIStyleProvider alexaUIStyleProvider, Function1<? super String, Unit> itemClickListener) {
        Intrinsics.checkNotNullParameter(alexaUIStyleProvider, "alexaUIStyleProvider");
        Intrinsics.checkNotNullParameter(itemClickListener, "itemClickListener");
        this.alexaUIStyleProvider = alexaUIStyleProvider;
        this.itemClickListener = itemClickListener;
        this.hints = CollectionsKt__CollectionsKt.emptyList();
    }

    public final List<String> getHints() {
        return this.hints;
    }

    public final void setHints(List<String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.hints = value;
        notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: AlexaHintsAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/voice/ui/AlexaHintsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/amazon/music/voice/ui/AlexaHintsAdapter;Landroid/view/View;)V", "hintButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "bind", "", "hintText", "", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final BaseButton hintButton;
        final /* synthetic */ AlexaHintsAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(final AlexaHintsAdapter this$0, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = this$0;
            View viewFindViewById = itemView.findViewById(R$id.hint_button);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.hint_button)");
            BaseButton baseButton = (BaseButton) viewFindViewById;
            this.hintButton = baseButton;
            BaseButton.StyleBuilder hintButtonStyleBuilder = this$0.getAlexaUIStyleProvider().getHintButtonStyleBuilder();
            if (hintButtonStyleBuilder != null) {
                hintButtonStyleBuilder.applyStyle(baseButton);
            }
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.voice.ui.AlexaHintsAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlexaHintsAdapter.ViewHolder.m2461_init_$lambda0(this$0, this, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
        public static final void m2461_init_$lambda0(AlexaHintsAdapter this$0, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.getItemClickListener().invoke(this$1.hintButton.getButton().getText().toString());
        }

        public final void bind(String hintText) {
            Intrinsics.checkNotNullParameter(hintText, "hintText");
            this.hintButton.setText(hintText);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R$layout.alexa_hint_item_view, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.bind(this.hints.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.hints.size();
    }
}
