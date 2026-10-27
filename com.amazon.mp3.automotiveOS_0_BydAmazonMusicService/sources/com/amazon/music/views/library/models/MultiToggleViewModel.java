package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiToggleViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\u0004\b'\u0010(R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001f\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010!\u001a\u00020 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006)"}, d2 = {"Lcom/amazon/music/views/library/models/MultiToggleViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "referenceId", "Ljava/lang/String;", "getReferenceId", "()Ljava/lang/String;", "", "onlyIconButtons", "Z", "getOnlyIconButtons", "()Z", "", "defaultActiveButton", "I", "getDefaultActiveButton", "()I", "layoutWidth", "getLayoutWidth", "", "Lcom/amazon/music/views/library/models/ToggleButtonModel;", "buttons", "Ljava/util/List;", "getButtons", "()Ljava/util/List;", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "setButtonSizeKey", "(Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;)V", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "getIconSizeKey", "()Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "setIconSizeKey", "(Lcom/amazon/music/views/library/styles/keys/IconSizeKey;)V", "<init>", "(Ljava/lang/String;ZIILjava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class MultiToggleViewModel extends BaseViewModel {
    private ButtonSizeKey buttonSizeKey;
    private final List<ToggleButtonModel> buttons;
    private final int defaultActiveButton;
    private IconSizeKey iconSizeKey;
    private final int layoutWidth;
    private final boolean onlyIconButtons;
    private final String referenceId;

    public MultiToggleViewModel() {
        this(null, false, 0, 0, null, 31, null);
    }

    public /* synthetic */ MultiToggleViewModel(String str, boolean z, int i, int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? false : z, (i3 & 4) == 0 ? i : 0, (i3 & 8) != 0 ? -2 : i2, (i3 & 16) != 0 ? null : list);
    }

    public final int getDefaultActiveButton() {
        return this.defaultActiveButton;
    }

    public final int getLayoutWidth() {
        return this.layoutWidth;
    }

    public final List<ToggleButtonModel> getButtons() {
        return this.buttons;
    }

    public MultiToggleViewModel(String str, boolean z, int i, int i2, List<ToggleButtonModel> list) {
        super(str, null, 2, null);
        this.referenceId = str;
        this.onlyIconButtons = z;
        this.defaultActiveButton = i;
        this.layoutWidth = i2;
        this.buttons = list;
        this.buttonSizeKey = ButtonSizeKey.SMALL;
        this.iconSizeKey = IconSizeKey.MEDIUM;
        if (list == null || list.isEmpty()) {
            return;
        }
        ToggleButtonModel toggleButtonModel = list.get(0);
        setButtonSizeKey(toggleButtonModel.getButtonSizeKey());
        setIconSizeKey(toggleButtonModel.getIconSizeKey());
    }

    public final ButtonSizeKey getButtonSizeKey() {
        return this.buttonSizeKey;
    }

    public final void setButtonSizeKey(ButtonSizeKey buttonSizeKey) {
        Intrinsics.checkNotNullParameter(buttonSizeKey, "<set-?>");
        this.buttonSizeKey = buttonSizeKey;
    }

    public final IconSizeKey getIconSizeKey() {
        return this.iconSizeKey;
    }

    public final void setIconSizeKey(IconSizeKey iconSizeKey) {
        Intrinsics.checkNotNullParameter(iconSizeKey, "<set-?>");
        this.iconSizeKey = iconSizeKey;
    }
}
