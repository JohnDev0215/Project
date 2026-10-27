package com.amazon.music.views.library.models;

import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ButtonType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/views/library/models/ButtonType;", "", "()V", "ADD_SONGS", "Lcom/amazon/music/views/library/models/ButtonType$ADD_SONGS;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ButtonType {
    public /* synthetic */ ButtonType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ButtonType.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/models/ButtonType$ADD_SONGS;", "Lcom/amazon/music/views/library/models/ButtonType;", "buttonString", "", "buttonStyleKey", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonMargin", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "(Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;)V", "getButtonMargin", "()Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "getButtonSizeKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "getButtonString", "()Ljava/lang/String;", "getButtonStyleKey", "()Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ADD_SONGS extends ButtonType {
        private final SpacerKey buttonMargin;
        private final ButtonSizeKey buttonSizeKey;
        private final String buttonString;
        private final ButtonStyleKey buttonStyleKey;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ADD_SONGS(String buttonString, ButtonStyleKey buttonStyleKey, ButtonSizeKey buttonSizeKey, SpacerKey buttonMargin) {
            super(null);
            Intrinsics.checkNotNullParameter(buttonString, "buttonString");
            Intrinsics.checkNotNullParameter(buttonStyleKey, "buttonStyleKey");
            Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
            Intrinsics.checkNotNullParameter(buttonMargin, "buttonMargin");
            this.buttonString = buttonString;
            this.buttonStyleKey = buttonStyleKey;
            this.buttonSizeKey = buttonSizeKey;
            this.buttonMargin = buttonMargin;
        }

        public final SpacerKey getButtonMargin() {
            return this.buttonMargin;
        }

        public final ButtonSizeKey getButtonSizeKey() {
            return this.buttonSizeKey;
        }

        public final String getButtonString() {
            return this.buttonString;
        }

        public final ButtonStyleKey getButtonStyleKey() {
            return this.buttonStyleKey;
        }
    }

    private ButtonType() {
    }
}
