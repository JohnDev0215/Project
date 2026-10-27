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
import spi.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0006B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/ProtocolFragmentArgs;", "Landroidx/navigation/NavArgs;", "", "protocolType", "<init>", "(Ljava/lang/String;)V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ProtocolFragmentArgs implements NavArgs {

    @NotNull
    public static final a b = new a(null);

    @NotNull
    public final String a;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ProtocolFragmentArgs() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ProtocolFragmentArgs(@NotNull String protocolType) {
        Intrinsics.checkNotNullParameter(protocolType, "protocolType");
        this.a = protocolType;
    }

    public /* synthetic */ ProtocolFragmentArgs(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    @JvmStatic
    @NotNull
    public static final ProtocolFragmentArgs fromBundle(@NotNull Bundle bundle) {
        String string;
        Objects.requireNonNull(b);
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.setClassLoader(ProtocolFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("protocol_type")) {
            string = bundle.getString("protocol_type");
            if (string == null) {
                throw new IllegalArgumentException("Argument \"protocol_type\" is marked as non-null but was passed a null value.");
            }
        } else {
            string = "";
        }
        return new ProtocolFragmentArgs(string);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ProtocolFragmentArgs) && Intrinsics.areEqual(this.a, ((ProtocolFragmentArgs) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @NotNull
    public final String toString() {
        return e.d("ProtocolFragmentArgs(protocolType=", this.a, ")");
    }
}
