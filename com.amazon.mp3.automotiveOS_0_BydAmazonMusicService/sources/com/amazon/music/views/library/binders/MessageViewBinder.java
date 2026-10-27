package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.views.library.models.MessageViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.MessageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MessageViewBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/binders/MessageViewBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/MessageView;", "Lcom/amazon/music/views/library/models/MessageViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MessageViewBinder implements UniversalBinder<MessageView, MessageViewModel> {
    private final StyleSheet styleSheet;

    public MessageViewBinder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<MessageViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(MessageViewModel.class);
    }

    public MessageViewBinder(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
    }

    public /* synthetic */ MessageViewBinder(StyleSheet styleSheet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public MessageView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new MessageView(context);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(MessageView view, MessageViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setMessageText(model.getMessage());
        view.setTextStyle(this.styleSheet);
    }
}
