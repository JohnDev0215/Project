package com.byd.overseaappstore.ui.view.fragment;

import android.os.Bundle;
import androidx.navigation.NavArgs;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\bB\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/TopicDetailFragmentArgs;", "Landroidx/navigation/NavArgs;", "", "topicGroupId", "", "topicGroupTitle", "<init>", "(ILjava/lang/String;)V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TopicDetailFragmentArgs implements NavArgs {

    @NotNull
    public static final a c = new a(null);

    /* JADX INFO: renamed from: a, reason: from toString */
    public final int topicGroupId;

    /* JADX INFO: renamed from: b, reason: from toString */
    @NotNull
    public final String topicGroupTitle;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TopicDetailFragmentArgs() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public TopicDetailFragmentArgs(int i, @NotNull String topicGroupTitle) {
        Intrinsics.checkNotNullParameter(topicGroupTitle, "topicGroupTitle");
        this.topicGroupId = i;
        this.topicGroupTitle = topicGroupTitle;
    }

    public /* synthetic */ TopicDetailFragmentArgs(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str);
    }

    @JvmStatic
    @NotNull
    public static final TopicDetailFragmentArgs fromBundle(@NotNull Bundle bundle) {
        String string;
        Objects.requireNonNull(c);
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.setClassLoader(TopicDetailFragmentArgs.class.getClassLoader());
        int i = bundle.containsKey("topic_group_id") ? bundle.getInt("topic_group_id") : 0;
        if (bundle.containsKey("topic_group_title")) {
            string = bundle.getString("topic_group_title");
            if (string == null) {
                throw new IllegalArgumentException("Argument \"topic_group_title\" is marked as non-null but was passed a null value.");
            }
        } else {
            string = "";
        }
        return new TopicDetailFragmentArgs(i, string);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TopicDetailFragmentArgs)) {
            return false;
        }
        TopicDetailFragmentArgs topicDetailFragmentArgs = (TopicDetailFragmentArgs) obj;
        return this.topicGroupId == topicDetailFragmentArgs.topicGroupId && Intrinsics.areEqual(this.topicGroupTitle, topicDetailFragmentArgs.topicGroupTitle);
    }

    public final int hashCode() {
        return this.topicGroupTitle.hashCode() + (Integer.hashCode(this.topicGroupId) * 31);
    }

    @NotNull
    public final String toString() {
        return "TopicDetailFragmentArgs(topicGroupId=" + this.topicGroupId + ", topicGroupTitle=" + this.topicGroupTitle + ")";
    }
}
