package spi;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class qq {

    @Embedded
    @NotNull
    public final pq a;

    @Relation(entity = oq.class, entityColumn = "primePackageName", parentColumn = "packageName")
    @Nullable
    public final List<oq> b;

    public qq(@NotNull pq softwareLocal, @Nullable List<oq> list) {
        Intrinsics.checkNotNullParameter(softwareLocal, "softwareLocal");
        this.a = softwareLocal;
        this.b = list;
    }
}
