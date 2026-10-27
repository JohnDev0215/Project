package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.models.ToggleButtonModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ToggleButtonBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/views/library/binders/ToggleButtonBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/models/ToggleButtonModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "defaultButtonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "defaultIconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "handleStateChange", "isActive", "", "setOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setupButtonIdAndTag", "setupStyle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ToggleButtonBinder implements UniversalBinder<BaseButton, ToggleButtonModel> {
    private final Context context;
    private ButtonSizeKey defaultButtonSizeKey;
    private IconSizeKey defaultIconSizeKey;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ToggleButtonModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ToggleButtonModel.class);
    }

    public ToggleButtonBinder(Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.context = context;
        this.styleSheet = styleSheet;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public BaseButton createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BaseButton(context, null, 0, 6, null);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(BaseButton view, ToggleButtonModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        setupStyle(view, model);
        setupButtonIdAndTag(view, model);
    }

    private final void setupButtonIdAndTag(BaseButton view, ToggleButtonModel model) {
        Integer buttonId = model.getButtonId();
        if (buttonId != null) {
            view.setId(buttonId.intValue());
        }
        view.setTag(model.getButtonTag());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void setupStyle(BaseButton view, ToggleButtonModel model) {
        BaseButton.StyleBuilder buttonBuilder;
        ButtonStyle buttonStyle;
        this.defaultIconSizeKey = model.getIconSizeKey();
        this.defaultButtonSizeKey = model.getButtonSizeKey();
        view.getButton().setSingleLine(true);
        ButtonSizeKey buttonSizeKey = null;
        IconSizeKey iconSizeKey = null;
        if (model.getText() == null) {
            StyleSheet styleSheet = this.styleSheet;
            IconSizeKey iconSizeKey2 = this.defaultIconSizeKey;
            if (iconSizeKey2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("defaultIconSizeKey");
            } else {
                iconSizeKey = iconSizeKey2;
            }
            buttonBuilder = styleSheet.getIconBuilder(iconSizeKey, IconStyleKey.SOLID);
        } else {
            StyleSheet styleSheet2 = this.styleSheet;
            ButtonSizeKey buttonSizeKey2 = this.defaultButtonSizeKey;
            if (buttonSizeKey2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("defaultButtonSizeKey");
            } else {
                buttonSizeKey = buttonSizeKey2;
            }
            buttonBuilder = styleSheet2.getButtonBuilder(buttonSizeKey, ButtonStyleKey.BORDERLESS);
        }
        if (buttonBuilder != null) {
            if (model.getText() == null) {
                buttonStyle = this.styleSheet.getIconStyle(IconStyleKey.SOLID);
                Intrinsics.checkNotNull(buttonStyle);
            } else {
                buttonStyle = this.styleSheet.getButtonStyle(ButtonStyleKey.SOLID);
                Intrinsics.checkNotNull(buttonStyle);
            }
            buttonBuilder.withActiveStyle(buttonStyle);
            Integer iconId = model.getIconId();
            if (iconId != null) {
                Drawable drawable = AppCompatResources.getDrawable(this.context, iconId.intValue());
                if (drawable != null) {
                    buttonBuilder.withIcon(drawable);
                }
            }
            String text = model.getText();
            if (text != null) {
                buttonBuilder.withText(text);
            }
            buttonBuilder.applyStyle(view);
        }
        if (model.getMaxButtonWidth() != null) {
            view.setMaxWidth(model.getMaxButtonWidth().intValue());
        }
    }

    public final void setOnClickListener(BaseButton view, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        view.setOnClickListener(onClickListener);
    }

    public final void handleStateChange(BaseButton view, boolean isActive) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setActive(isActive);
    }
}
