package com.amazon.music.find.binder;

import android.content.Context;
import com.amazon.music.find.model.SearchResponseMessage;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.find.view.SearchResponseMessageView;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SearchResponseMessageBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/binder/SearchResponseMessageBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/find/view/SearchResponseMessageView;", "Lcom/amazon/music/find/model/SearchResponseMessage;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "spellCorrectionListenerClickProvider", "Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchResponseMessageBinder implements UniversalBinder<SearchResponseMessageView, SearchResponseMessage> {
    private final SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SearchResponseMessage> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SearchResponseMessage.class);
    }

    public SearchResponseMessageBinder(StyleSheet styleSheet, SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.spellCorrectionListenerClickProvider = spellCorrectionListenerClickProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SearchResponseMessageView view, SearchResponseMessage model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (model.getNoResultFound()) {
            view.showNoResultFoundMessage(model.getPrimaryMessage(), model.getSecondaryMessage(), model.getPrimaryMessageFontStyle(), model.getSecondaryMessageFontStyle(), model.getSpacerBetweenMessages(), model.getShouldCenterMessage());
        } else {
            if (model.getSpellCorrections() == null || model.getSecondaryLinkText() == null) {
                return;
            }
            view.showSearchResponseMessage(model.getSpellCorrections(), model.getSecondaryLinkText(), this.spellCorrectionListenerClickProvider, model.getPrimaryMessage(), model.getSecondaryMessage(), model.getPrimaryMessageFontStyle(), model.getSecondaryMessageFontStyle(), model.getSpacerBetweenMessages(), model.getShouldCenterMessage());
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SearchResponseMessageView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SearchResponseMessageView(context, this.styleSheet);
    }
}
