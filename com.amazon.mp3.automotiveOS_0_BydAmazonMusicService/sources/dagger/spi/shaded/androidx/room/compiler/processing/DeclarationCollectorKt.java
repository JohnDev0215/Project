package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: compiled from: DeclarationCollector.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\f\u0010\u000b\u001a\u00020\b*\u00020\u0002H\u0002¨\u0006\f"}, d2 = {"collectAllMethods", "Lkotlin/sequences/Sequence;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "xTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "collectFieldsIncludingPrivateSupers", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "isAccessibleFrom", "", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "", "isStaticInterfaceMethod", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class DeclarationCollectorKt {

    /* JADX INFO: renamed from: dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DeclarationCollector.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "", "<anonymous>"}, k = 3, mv = {1, 7, 1})
    @DebugMetadata(c = "dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1", f = "DeclarationCollector.kt", i = {}, l = {37}, m = "invokeSuspend", n = {}, s = {})
    static final class C04691 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super XFieldElement>, Continuation<? super Unit>, Object> {
        final /* synthetic */ XTypeElement $xTypeElement;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04691(XTypeElement xTypeElement, Continuation<? super C04691> continuation) {
            super(2, continuation);
            this.$xTypeElement = xTypeElement;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04691 c04691 = new C04691(this.$xTypeElement, continuation);
            c04691.L$0 = obj;
            return c04691;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(SequenceScope<? super XFieldElement> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C04691) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope = (SequenceScope) this.L$0;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                XTypeElement xTypeElement = this.$xTypeElement;
                this.label = 1;
                if (invokeSuspend$yieldAllFields(sequenceScope, linkedHashSet, xTypeElement, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static final java.lang.Object invokeSuspend$yieldAllFields(kotlin.sequences.SequenceScope<? super dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement> r8, java.util.Set<java.lang.String> r9, dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
            /*
                boolean r0 = r11 instanceof dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1
                if (r0 == 0) goto L13
                r0 = r11
                dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1 r0 = (dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1 r0 = new dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1
                r0.<init>(r11)
            L18:
                java.lang.Object r11 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L4b
                if (r2 == r4) goto L35
                if (r2 != r3) goto L2d
                kotlin.ResultKt.throwOnFailure(r11)
                goto Lba
            L2d:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L35:
                java.lang.Object r8 = r0.L$3
                java.util.Iterator r8 = (java.util.Iterator) r8
                java.lang.Object r9 = r0.L$2
                dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement r9 = (dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement) r9
                java.lang.Object r10 = r0.L$1
                java.util.Set r10 = (java.util.Set) r10
                java.lang.Object r2 = r0.L$0
                kotlin.sequences.SequenceScope r2 = (kotlin.sequences.SequenceScope) r2
                kotlin.ResultKt.throwOnFailure(r11)
                r11 = r9
                r9 = r2
                goto L7f
            L4b:
                kotlin.ResultKt.throwOnFailure(r11)
                java.util.List r11 = r10.getDeclaredFields()
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Iterator r11 = r11.iterator()
            L5b:
                boolean r5 = r11.hasNext()
                if (r5 == 0) goto L76
                java.lang.Object r5 = r11.next()
                r6 = r5
                dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement r6 = (dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement) r6
                java.lang.String r6 = r6.getName()
                boolean r6 = r9.add(r6)
                if (r6 == 0) goto L5b
                r2.add(r5)
                goto L5b
            L76:
                java.util.Iterator r11 = r2.iterator()
                r7 = r9
                r9 = r8
                r8 = r11
                r11 = r10
                r10 = r7
            L7f:
                boolean r2 = r8.hasNext()
                if (r2 == 0) goto L9c
                java.lang.Object r2 = r8.next()
                dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement r2 = (dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement) r2
                r0.L$0 = r9
                r0.L$1 = r10
                r0.L$2 = r11
                r0.L$3 = r8
                r0.label = r4
                java.lang.Object r2 = r9.yield(r2, r0)
                if (r2 != r1) goto L7f
                return r1
            L9c:
                dagger.spi.shaded.androidx.room.compiler.processing.XType r8 = r11.getSuperClass()
                if (r8 == 0) goto Lba
                dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement r8 = r8.getTypeElement()
                if (r8 == 0) goto Lba
                r11 = 0
                r0.L$0 = r11
                r0.L$1 = r11
                r0.L$2 = r11
                r0.L$3 = r11
                r0.label = r3
                java.lang.Object r8 = invokeSuspend$yieldAllFields(r9, r10, r8, r0)
                if (r8 != r1) goto Lba
                return r1
            Lba:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt.C04691.invokeSuspend$yieldAllFields(kotlin.sequences.SequenceScope, java.util.Set, dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public static final Sequence<XFieldElement> collectFieldsIncludingPrivateSupers(XTypeElement xTypeElement) {
        Intrinsics.checkNotNullParameter(xTypeElement, "xTypeElement");
        return SequencesKt__SequenceBuilderKt.sequence(new C04691(xTypeElement, null));
    }

    /* JADX INFO: renamed from: dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectAllMethods$1, reason: invalid class name */
    /* JADX INFO: compiled from: DeclarationCollector.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "", "<anonymous>"}, k = 3, mv = {1, 7, 1})
    @DebugMetadata(c = "dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectAllMethods$1", f = "DeclarationCollector.kt", i = {0, 1}, l = {86, 104}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super XMethodElement>, Continuation<? super Unit>, Object> {
        final /* synthetic */ XTypeElement $xTypeElement;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(XTypeElement xTypeElement, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$xTypeElement = xTypeElement;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$xTypeElement, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(SequenceScope<? super XMethodElement> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00f9  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00ea -> B:39:0x00f3). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final void invokeSuspend$collectAllMethodsByName(XTypeElement xTypeElement, Set<XTypeElement> set, Map<String, LinkedHashSet<XMethodElement>> map, XTypeElement xTypeElement2) {
            XTypeElement typeElement;
            for (XTypeElement xTypeElement3 : xTypeElement2.getSuperInterfaceElements()) {
                if (set.add(xTypeElement3)) {
                    invokeSuspend$collectAllMethodsByName(xTypeElement, set, map, xTypeElement3);
                }
            }
            XType superClass = xTypeElement2.getSuperClass();
            if (superClass != null && (typeElement = superClass.getTypeElement()) != null) {
                invokeSuspend$collectAllMethodsByName(xTypeElement, set, map, typeElement);
            }
            if (!Intrinsics.areEqual(xTypeElement2, xTypeElement)) {
                List<XMethodElement> declaredMethods = xTypeElement2.getDeclaredMethods();
                ArrayList arrayList = new ArrayList();
                for (Object obj : declaredMethods) {
                    if (DeclarationCollectorKt.isAccessibleFrom((XMethodElement) obj, xTypeElement.getPackageName())) {
                        arrayList.add(obj);
                    }
                }
                ArrayList<XMethodElement> arrayList2 = new ArrayList();
                for (Object obj2 : arrayList) {
                    if (!DeclarationCollectorKt.isStaticInterfaceMethod((XMethodElement) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                for (XMethodElement xMethodElement : arrayList2) {
                    String name = xMethodElement.getName();
                    LinkedHashSet<XMethodElement> linkedHashSet = map.get(name);
                    if (linkedHashSet == null) {
                        linkedHashSet = new LinkedHashSet<>();
                        map.put(name, linkedHashSet);
                    }
                    linkedHashSet.add(xMethodElement);
                }
                return;
            }
            for (XMethodElement xMethodElement2 : xTypeElement2.getDeclaredMethods()) {
                String name2 = xMethodElement2.getName();
                LinkedHashSet<XMethodElement> linkedHashSet2 = map.get(name2);
                if (linkedHashSet2 == null) {
                    linkedHashSet2 = new LinkedHashSet<>();
                    map.put(name2, linkedHashSet2);
                }
                linkedHashSet2.add(xMethodElement2);
            }
        }
    }

    public static final Sequence<XMethodElement> collectAllMethods(XTypeElement xTypeElement) {
        Intrinsics.checkNotNullParameter(xTypeElement, "xTypeElement");
        return SequencesKt__SequenceBuilderKt.sequence(new AnonymousClass1(xTypeElement, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAccessibleFrom(XMethodElement xMethodElement, String str) {
        if (xMethodElement.isPublic() || xMethodElement.isProtected()) {
            return true;
        }
        if (xMethodElement.isPrivate()) {
            return false;
        }
        return Intrinsics.areEqual(str, xMethodElement.getClosestMemberContainer().getClassName().packageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isStaticInterfaceMethod(XMethodElement xMethodElement) {
        if (xMethodElement.isStatic()) {
            XMemberContainer enclosingElement = xMethodElement.getEnclosingElement();
            XTypeElement xTypeElement = enclosingElement instanceof XTypeElement ? (XTypeElement) enclosingElement : null;
            if (xTypeElement != null && xTypeElement.isInterface()) {
                return true;
            }
        }
        return false;
    }
}
