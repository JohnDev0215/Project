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
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0006B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/FeatureFragmentArgs;", "Landroidx/navigation/NavArgs;", "", "softwareId", "<init>", "(I)V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FeatureFragmentArgs implements NavArgs {

    @NotNull
    public static final a b = new a(null);
    public final int a;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FeatureFragmentArgs() {
        this(0, 1, null);
    }

    public FeatureFragmentArgs(int i) {
        this.a = i;
    }

    public /* synthetic */ FeatureFragmentArgs(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    @JvmStatic
    @NotNull
    public static final FeatureFragmentArgs fromBundle(@NotNull Bundle bundle) {
        Objects.requireNonNull(b);
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.setClassLoader(FeatureFragmentArgs.class.getClassLoader());
        return new FeatureFragmentArgs(bundle.containsKey("software_id") ? bundle.getInt("software_id") : 0);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FeatureFragmentArgs) && this.a == ((FeatureFragmentArgs) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @NotNull
    public final String toString() {
        return xi.e("FeatureFragmentArgs(softwareId=", this.a, ")");
    }
}
