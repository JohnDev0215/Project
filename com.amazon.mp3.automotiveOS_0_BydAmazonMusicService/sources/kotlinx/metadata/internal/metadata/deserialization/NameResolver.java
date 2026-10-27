package kotlinx.metadata.internal.metadata.deserialization;

import kotlin.Metadata;

/* JADX INFO: compiled from: NameResolver.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "", "getQualifiedClassName", "", "index", "", "getString", "isLocalClassName", "", "metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface NameResolver {
    String getQualifiedClassName(int index);

    String getString(int index);

    boolean isLocalClassName(int index);
}
