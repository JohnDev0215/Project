package com.amazon.music.curate.model;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualRowHeader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/curate/model/VisualRowHeader;", "Lcom/amazon/music/curate/model/VisualModel;", "title", "", "subTitle", "button", "Lcom/amazon/music/curate/model/VisualRowHeader$Button;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/curate/model/VisualRowHeader$Button;)V", "getButton", "()Lcom/amazon/music/curate/model/VisualRowHeader$Button;", "getSubTitle", "()Ljava/lang/String;", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Button", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class VisualRowHeader implements VisualModel {
    private final Button button;
    private final String subTitle;
    private final String title;

    public static /* synthetic */ VisualRowHeader copy$default(VisualRowHeader visualRowHeader, String str, String str2, Button button, int i, Object obj) {
        if ((i & 1) != 0) {
            str = visualRowHeader.title;
        }
        if ((i & 2) != 0) {
            str2 = visualRowHeader.subTitle;
        }
        if ((i & 4) != 0) {
            button = visualRowHeader.button;
        }
        return visualRowHeader.copy(str, str2, button);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSubTitle() {
        return this.subTitle;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Button getButton() {
        return this.button;
    }

    public final VisualRowHeader copy(String title, String subTitle, Button button) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new VisualRowHeader(title, subTitle, button);
    }

    public String toString() {
        return "VisualRowHeader(title=" + this.title + ", subTitle=" + ((Object) this.subTitle) + ", button=" + this.button + ')';
    }

    public VisualRowHeader(String title, String str, Button button) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.subTitle = str;
        this.button = button;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final Button getButton() {
        return this.button;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(VisualRowHeader.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            VisualRowHeader visualRowHeader = (VisualRowHeader) other;
            return Intrinsics.areEqual(this.title, visualRowHeader.title) && Intrinsics.areEqual(this.subTitle, visualRowHeader.subTitle) && Intrinsics.areEqual(this.button, visualRowHeader.button);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.curate.model.VisualRowHeader");
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.subTitle;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Button button = this.button;
        return iHashCode2 + (button != null ? button.hashCode() : 0);
    }

    /* JADX INFO: compiled from: VisualRowHeader.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/model/VisualRowHeader$Button;", "", "text", "", "onClick", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class Button {
        private final Function0<Unit> onClick;
        private final String text;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Button copy$default(Button button, String str, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                str = button.text;
            }
            if ((i & 2) != 0) {
                function0 = button.onClick;
            }
            return button.copy(str, function0);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final Function0<Unit> component2() {
            return this.onClick;
        }

        public final Button copy(String text, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            return new Button(text, onClick);
        }

        public String toString() {
            return "Button(text=" + this.text + ", onClick=" + this.onClick + ')';
        }

        public Button(String text, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            this.text = text;
            this.onClick = onClick;
        }

        public final String getText() {
            return this.text;
        }

        public final Function0<Unit> getOnClick() {
            return this.onClick;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(Button.class, other == null ? null : other.getClass())) {
                return false;
            }
            if (other != null) {
                Button button = (Button) other;
                return Intrinsics.areEqual(this.text, button.text) && Intrinsics.areEqual(this.onClick, button.onClick);
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.curate.model.VisualRowHeader.Button");
        }

        public int hashCode() {
            return (this.text.hashCode() * 31) + this.onClick.hashCode();
        }
    }
}
