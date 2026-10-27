package com.byd.overseaappstore.module.ability.data.upper;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/TopicGroupTitle;", "", "tip", "", "language", "", "(Ljava/lang/String;S)V", "getLanguage", "()S", "getTip", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class TopicGroupTitle {
    private final short language;

    @NotNull
    private final String tip;

    public TopicGroupTitle(@NotNull String tip, short s) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        this.tip = tip;
        this.language = s;
    }

    public static /* synthetic */ TopicGroupTitle copy$default(TopicGroupTitle topicGroupTitle, String str, short s, int i, Object obj) {
        if ((i & 1) != 0) {
            str = topicGroupTitle.tip;
        }
        if ((i & 2) != 0) {
            s = topicGroupTitle.language;
        }
        return topicGroupTitle.copy(str, s);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTip() {
        return this.tip;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final short getLanguage() {
        return this.language;
    }

    @NotNull
    public final TopicGroupTitle copy(@NotNull String tip, short language) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        return new TopicGroupTitle(tip, language);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TopicGroupTitle)) {
            return false;
        }
        TopicGroupTitle topicGroupTitle = (TopicGroupTitle) other;
        return Intrinsics.areEqual(this.tip, topicGroupTitle.tip) && this.language == topicGroupTitle.language;
    }

    public final short getLanguage() {
        return this.language;
    }

    @NotNull
    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return Short.hashCode(this.language) + (this.tip.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "TopicGroupTitle(tip=" + this.tip + ", language=" + ((int) this.language) + ")";
    }
}
