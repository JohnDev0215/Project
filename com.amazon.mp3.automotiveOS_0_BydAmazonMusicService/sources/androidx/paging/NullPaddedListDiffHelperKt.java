package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: NullPaddedListDiffHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0000\u001a:\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\f\u001a\u00020\u0001H\u0000\u001a,\u0010\r\u001a\u00020\u000e*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\f\u001a\u00020\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0000¨\u0006\u0010"}, d2 = {"computeDiff", "Landroidx/paging/NullPaddedDiffResult;", "T", "", "Landroidx/paging/NullPaddedList;", "newList", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "dispatchDiff", "", "callback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "diffResult", "transformAnchorIndex", "", "oldPosition", "paging-runtime_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class NullPaddedListDiffHelperKt {
    public static final <T> NullPaddedDiffResult computeDiff(final NullPaddedList<T> nullPaddedList, final NullPaddedList<T> newList, final DiffUtil.ItemCallback<T> diffCallback) {
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        final int storageCount = nullPaddedList.getStorageCount();
        final int storageCount2 = newList.getStorageCount();
        DiffUtil.Callback callback = new DiffUtil.Callback() { // from class: androidx.paging.NullPaddedListDiffHelperKt$computeDiff$diffResult$1
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                Object fromStorage = nullPaddedList.getFromStorage(oldItemPosition);
                Object fromStorage2 = newList.getFromStorage(newItemPosition);
                if (fromStorage == fromStorage2) {
                    return Boolean.TRUE;
                }
                return diffCallback.getChangePayload(fromStorage, fromStorage2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            /* JADX INFO: renamed from: getOldListSize, reason: from getter */
            public int get$oldSize() {
                return storageCount;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            /* JADX INFO: renamed from: getNewListSize, reason: from getter */
            public int get$newSize() {
                return storageCount2;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                Object fromStorage = nullPaddedList.getFromStorage(oldItemPosition);
                Object fromStorage2 = newList.getFromStorage(newItemPosition);
                if (fromStorage == fromStorage2) {
                    return true;
                }
                return diffCallback.areItemsTheSame(fromStorage, fromStorage2);
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Object fromStorage = nullPaddedList.getFromStorage(oldItemPosition);
                Object fromStorage2 = newList.getFromStorage(newItemPosition);
                if (fromStorage == fromStorage2) {
                    return true;
                }
                return diffCallback.areContentsTheSame(fromStorage, fromStorage2);
            }
        };
        boolean z = true;
        DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(callback, true);
        Intrinsics.checkNotNullExpressionValue(diffResultCalculateDiff, "NullPaddedList<T>.comput…    },\n        true\n    )");
        Iterable iterableUntil = RangesKt___RangesKt.until(0, nullPaddedList.getStorageCount());
        if ((iterableUntil instanceof Collection) && ((Collection) iterableUntil).isEmpty()) {
            z = false;
        } else {
            Iterator<T> it = iterableUntil.iterator();
            while (it.hasNext()) {
                if (diffResultCalculateDiff.convertOldPositionToNew(((IntIterator) it).nextInt()) != -1) {
                    break;
                }
            }
            z = false;
        }
        return new NullPaddedDiffResult(diffResultCalculateDiff, z);
    }

    public static final <T> void dispatchDiff(NullPaddedList<T> nullPaddedList, ListUpdateCallback callback, NullPaddedList<T> newList, NullPaddedDiffResult diffResult) {
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        if (diffResult.getHasOverlap()) {
            OverlappingListsDiffDispatcher.INSTANCE.dispatchDiff(nullPaddedList, newList, callback, diffResult);
        } else {
            DistinctListsDiffDispatcher.INSTANCE.dispatchDiff(callback, nullPaddedList, newList);
        }
    }

    public static final int transformAnchorIndex(NullPaddedList<?> nullPaddedList, NullPaddedDiffResult diffResult, NullPaddedList<?> newList, int i) {
        int iConvertOldPositionToNew;
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        Intrinsics.checkNotNullParameter(newList, "newList");
        if (!diffResult.getHasOverlap()) {
            return RangesKt___RangesKt.coerceIn(i, (ClosedRange<Integer>) RangesKt___RangesKt.until(0, newList.getSize()));
        }
        int placeholdersBefore = i - nullPaddedList.getPlaceholdersBefore();
        if (placeholdersBefore >= 0 && placeholdersBefore < nullPaddedList.getStorageCount()) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                int i4 = ((i2 / 2) * (i2 % 2 == 1 ? -1 : 1)) + placeholdersBefore;
                if (i4 >= 0 && i4 < nullPaddedList.getStorageCount() && (iConvertOldPositionToNew = diffResult.getDiff().convertOldPositionToNew(i4)) != -1) {
                    return iConvertOldPositionToNew + newList.getPlaceholdersBefore();
                }
                if (i3 > 29) {
                    break;
                }
                i2 = i3;
            }
        }
        return RangesKt___RangesKt.coerceIn(i, (ClosedRange<Integer>) RangesKt___RangesKt.until(0, newList.getSize()));
    }
}
