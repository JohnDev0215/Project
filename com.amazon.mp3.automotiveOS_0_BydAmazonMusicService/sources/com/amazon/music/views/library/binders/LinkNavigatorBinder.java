package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.enums.IconType;
import com.amazon.music.views.library.models.enums.LinkNavigatorType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.LinkNavigator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: LinkNavigatorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u001f\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/views/library/binders/LinkNavigatorBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/LinkNavigator;", "Lcom/amazon/music/views/library/models/LinkNavigatorModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;)V", "context", "Landroid/content/Context;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindStyle", "createView", "getIconResource", "", "iconType", "Lcom/amazon/music/views/library/models/enums/IconType;", "isActive", "", "(Lcom/amazon/music/views/library/models/enums/IconType;Z)Ljava/lang/Integer;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LinkNavigatorBinder implements UniversalBinder<LinkNavigator, LinkNavigatorModel> {
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private Context context;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: LinkNavigatorBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IconType.values().length];
            iArr[IconType.RADIO.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<LinkNavigatorModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(LinkNavigatorModel.class);
    }

    public LinkNavigatorBinder(StyleSheet styleSheet, BrushUriClickListenerProvider brushUriClickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        this.styleSheet = styleSheet;
        this.brushUriClickListenerProvider = brushUriClickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public LinkNavigator createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new LinkNavigator(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final LinkNavigator view, final LinkNavigatorModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        String text = model.getText();
        if (text != null) {
            view.setText(text, model.getDeemphasizedText());
        }
        bindStyle(view, model);
        if (view.isClickable()) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.LinkNavigatorBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LinkNavigatorBinder.m2339bind$lambda1(model, view, this, view2);
                }
            });
        } else {
            view.setOnClickListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2339bind$lambda1(LinkNavigatorModel model, LinkNavigator view, LinkNavigatorBinder this$0, View view2) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        model.setActive(!model.getIsActive());
        view.setIconState(model.getIsActive());
        this$0.brushUriClickListenerProvider.onClick(model.getTarget(), model.getViewReferenceId(), model.getTargetType());
    }

    private final void bindStyle(LinkNavigator view, LinkNavigatorModel model) {
        Integer iconResource;
        if (model.getLinkNavigatorType() == LinkNavigatorType.LARGE) {
            view.setHeight(view.getStyleSheet().getLargeListItemHeight());
        }
        view.setIconVisibility(model.getIconType() != null);
        IconType iconType = model.getIconType();
        if (iconType != null && (iconResource = getIconResource(iconType, false)) != null) {
            Drawable iconDrawable = view.getContext().getResources().getDrawable(iconResource.intValue(), null);
            Integer iconResource2 = getIconResource(iconType, true);
            Drawable drawable = iconResource2 != null ? view.getContext().getResources().getDrawable(iconResource2.intValue(), null) : null;
            Intrinsics.checkNotNullExpressionValue(iconDrawable, "iconDrawable");
            view.setIconType(iconDrawable, drawable);
            view.setIconState(model.getIsActive());
        }
        view.setEnabled(model.getIsEnabled());
        view.setNewBadgeVisibility(model.getShouldShowNewBadge());
    }

    private final Integer getIconResource(IconType iconType, boolean isActive) {
        if (WhenMappings.$EnumSwitchMapping$0[iconType.ordinal()] == 1) {
            return Integer.valueOf(isActive ? R$drawable.ic_action_radiocheck : R$drawable.ic_action_radiouncheck);
        }
        if (isActive) {
            return null;
        }
        return Integer.valueOf(R$drawable.ic_chevron_right);
    }
}
