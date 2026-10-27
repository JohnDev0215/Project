package com.drakeet.multitype;

import com.byd.mediacenter.api.media.MediaControl;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: compiled from: ClassLinkerBridge.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000f*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000fB-\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR \u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/drakeet/multitype/ClassLinkerBridge;", "T", "Lcom/drakeet/multitype/Linker;", "javaClassLinker", "Lcom/drakeet/multitype/JavaClassLinker;", "delegates", "", "Lcom/drakeet/multitype/ItemViewDelegate;", "(Lcom/drakeet/multitype/JavaClassLinker;[Lcom/drakeet/multitype/ItemViewDelegate;)V", "[Lcom/drakeet/multitype/ItemViewDelegate;", "index", "", MediaControl.PARAM_POSITION, "item", "(ILjava/lang/Object;)I", "Companion", "multitype"}, k = 1, mv = {1, 4, 2})
public final class ClassLinkerBridge<T> implements Linker<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ItemViewDelegate<T, ?>[] delegates;
    private final JavaClassLinker<T> javaClassLinker;

    private ClassLinkerBridge(JavaClassLinker<T> javaClassLinker, ItemViewDelegate<T, ?>[] itemViewDelegateArr) {
        this.javaClassLinker = javaClassLinker;
        this.delegates = itemViewDelegateArr;
    }

    public /* synthetic */ ClassLinkerBridge(JavaClassLinker javaClassLinker, ItemViewDelegate[] itemViewDelegateArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(javaClassLinker, itemViewDelegateArr);
    }

    @Override // com.drakeet.multitype.Linker
    public int index(int position, T item) {
        Class<? extends ItemViewDelegate<T, ?>> clsIndex = this.javaClassLinker.index(position, item);
        ItemViewDelegate<T, ?>[] itemViewDelegateArr = this.delegates;
        int length = itemViewDelegateArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(itemViewDelegateArr[i].getClass(), clsIndex)) {
                break;
            }
            i++;
        }
        if (i != -1) {
            return i;
        }
        StringBuilder sb = new StringBuilder("The delegates'(");
        String string = Arrays.toString(this.delegates);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        throw new IndexOutOfBoundsException(sb.append(string).append(") you registered do not contain this ").append(clsIndex.getName()).append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH).toString());
    }

    /* JADX INFO: compiled from: ClassLinkerBridge.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0016\u0010\b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u0005\u0012\u0002\b\u00030\n0\t¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/drakeet/multitype/ClassLinkerBridge$Companion;", "", "()V", "toLinker", "Lcom/drakeet/multitype/Linker;", "T", "javaClassLinker", "Lcom/drakeet/multitype/JavaClassLinker;", "delegates", "", "Lcom/drakeet/multitype/ItemViewDelegate;", "(Lcom/drakeet/multitype/JavaClassLinker;[Lcom/drakeet/multitype/ItemViewDelegate;)Lcom/drakeet/multitype/Linker;", "multitype"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> Linker<T> toLinker(JavaClassLinker<T> javaClassLinker, ItemViewDelegate<T, ?>[] delegates) {
            Intrinsics.checkNotNullParameter(javaClassLinker, "javaClassLinker");
            Intrinsics.checkNotNullParameter(delegates, "delegates");
            return new ClassLinkerBridge(javaClassLinker, delegates, null);
        }
    }
}
