package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: compiled from: AbstractTypeChecker.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class AbstractTypeChecker {
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();

    @JvmField
    public static boolean RUN_SLOW_ASSERTIONS;

    /* JADX INFO: compiled from: AbstractTypeChecker.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            iArr[TypeVariance.INV.ordinal()] = 1;
            iArr[TypeVariance.OUT.ordinal()] = 2;
            iArr[TypeVariance.IN.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TypeCheckerState.LowerCapturedTypePolicy.values().length];
            iArr2[TypeCheckerState.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            iArr2[TypeCheckerState.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            iArr2[TypeCheckerState.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private AbstractTypeChecker() {
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean isSubtypeOf(TypeCheckerState state, KotlinTypeMarker subType, KotlinTypeMarker superType, boolean z) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        if (subType == superType) {
            return true;
        }
        if (state.customIsSubtypeOf(subType, superType)) {
            return completeIsSubTypeOf(state, subType, superType, z);
        }
        return false;
    }

    public final boolean equalTypes(TypeCheckerState state, KotlinTypeMarker a2, KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        TypeSystemContext typeSystemContext = state.getTypeSystemContext();
        if (a2 == b) {
            return true;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        if (abstractTypeChecker.isCommonDenotableType(typeSystemContext, a2) && abstractTypeChecker.isCommonDenotableType(typeSystemContext, b)) {
            KotlinTypeMarker kotlinTypeMarkerPrepareType = state.prepareType(state.refineType(a2));
            KotlinTypeMarker kotlinTypeMarkerPrepareType2 = state.prepareType(state.refineType(b));
            SimpleTypeMarker simpleTypeMarkerLowerBoundIfFlexible = typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType);
            if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(kotlinTypeMarkerPrepareType), typeSystemContext.typeConstructor(kotlinTypeMarkerPrepareType2))) {
                return false;
            }
            if (typeSystemContext.argumentsCount(simpleTypeMarkerLowerBoundIfFlexible) == 0) {
                return typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerPrepareType) || typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerPrepareType2) || typeSystemContext.isMarkedNullable(simpleTypeMarkerLowerBoundIfFlexible) == typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType2));
            }
        }
        return isSubtypeOf$default(abstractTypeChecker, state, a2, b, false, 8, null) && isSubtypeOf$default(abstractTypeChecker, state, b, a2, false, 8, null);
    }

    private final boolean completeIsSubTypeOf(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        KotlinTypeMarker kotlinTypeMarkerPrepareType = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker));
        KotlinTypeMarker kotlinTypeMarkerPrepareType2 = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker2));
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        Boolean boolCheckSubtypeForSpecialCases = abstractTypeChecker.checkSubtypeForSpecialCases(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2));
        if (boolCheckSubtypeForSpecialCases != null) {
            boolean zBooleanValue = boolCheckSubtypeForSpecialCases.booleanValue();
            typeCheckerState.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
            return zBooleanValue;
        }
        Boolean boolAddSubtypeConstraint = typeCheckerState.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
        if (boolAddSubtypeConstraint != null) {
            return boolAddSubtypeConstraint.booleanValue();
        }
        return abstractTypeChecker.isSubtypeOfForSingleClassifierType(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2));
    }

    private final Boolean checkSubtypeForIntegerLiteralType(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (!typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && !typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return null;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return Boolean.TRUE;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker)) {
            if (m4089xd35c7e25(typeSystemContext, typeCheckerState, simpleTypeMarker, simpleTypeMarker2, false)) {
                return Boolean.TRUE;
            }
        } else if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker2) && (m4088xabd2962a(typeSystemContext, simpleTypeMarker) || m4089xd35c7e25(typeSystemContext, typeCheckerState, simpleTypeMarker2, simpleTypeMarker, true))) {
            return Boolean.TRUE;
        }
        return null;
    }

    /* JADX INFO: renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isTypeInIntegerLiteralType, reason: not valid java name */
    private static final boolean m4089xd35c7e25(TypeSystemContext typeSystemContext, TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2, boolean z) {
        Collection<KotlinTypeMarker> collectionPossibleIntegerTypes = typeSystemContext.possibleIntegerTypes(simpleTypeMarker);
        if (!(collectionPossibleIntegerTypes instanceof Collection) || !collectionPossibleIntegerTypes.isEmpty()) {
            for (KotlinTypeMarker kotlinTypeMarker : collectionPossibleIntegerTypes) {
                if (Intrinsics.areEqual(typeSystemContext.typeConstructor(kotlinTypeMarker), typeSystemContext.typeConstructor(simpleTypeMarker2)) || (z && isSubtypeOf$default(INSTANCE, typeCheckerState, simpleTypeMarker2, kotlinTypeMarker, false, 8, null))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isIntegerLiteralTypeInIntersectionComponents, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final boolean m4088xabd2962a(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext r3, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r4) {
        /*
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r4 = r3.typeConstructor(r4)
            boolean r0 = r4 instanceof kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L3f
            java.util.Collection r4 = r3.supertypes(r4)
            boolean r0 = r4 instanceof java.util.Collection
            if (r0 == 0) goto L1a
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L1a
        L18:
            r3 = r2
            goto L3c
        L1a:
            java.util.Iterator r4 = r4.iterator()
        L1e:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L18
            java.lang.Object r0 = r4.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r0 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r0
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r0 = r3.asSimpleType(r0)
            if (r0 != 0) goto L32
        L30:
            r0 = r2
            goto L39
        L32:
            boolean r0 = r3.isIntegerLiteralType(r0)
            if (r0 != r1) goto L30
            r0 = r1
        L39:
            if (r0 == 0) goto L1e
            r3 = r1
        L3c:
            if (r3 == 0) goto L3f
            goto L40
        L3f:
            r1 = r2
        L40:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.m4088xabd2962a(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):boolean");
    }

    private final boolean hasNothingSupertype(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeSystemContext.isClassTypeConstructor(typeConstructorMarkerTypeConstructor)) {
            return typeSystemContext.isNothingConstructor(typeConstructorMarkerTypeConstructor);
        }
        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        typeCheckerState.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = typeCheckerState.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = typeCheckerState.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                if (typeSystemContext.isClassType(current)) {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                } else {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(supertypesPolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy == null) {
                    continue;
                } else {
                    TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                    Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        SimpleTypeMarker simpleTypeMarkerMo4090transformType = supertypesPolicy.mo4090transformType(typeCheckerState, it.next());
                        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarkerMo4090transformType))) {
                            typeCheckerState.clear();
                            return true;
                        }
                        supertypesDeque.add(simpleTypeMarkerMo4090transformType);
                    }
                }
            }
        }
        typeCheckerState.clear();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0102 A[LOOP:2: B:48:0x00d6->B:60:0x0102, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0110 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState r20, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r21, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r22) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):boolean");
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, TypeConstructorMarker typeConstructorMarker) {
        TypeParameterMarker typeParameter;
        SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(kotlinTypeMarker);
        if (!(simpleTypeMarkerAsSimpleType instanceof CapturedTypeMarker)) {
            return false;
        }
        CapturedTypeMarker capturedTypeMarker = (CapturedTypeMarker) simpleTypeMarkerAsSimpleType;
        if (typeSystemContext.isOldCapturedType(capturedTypeMarker) || !typeSystemContext.isStarProjection(typeSystemContext.projection(typeSystemContext.typeConstructor(capturedTypeMarker))) || typeSystemContext.captureStatus(capturedTypeMarker) != CaptureStatus.FOR_SUBTYPING) {
            return false;
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(kotlinTypeMarker2);
        TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker = typeConstructorMarkerTypeConstructor instanceof TypeVariableTypeConstructorMarker ? (TypeVariableTypeConstructorMarker) typeConstructorMarkerTypeConstructor : null;
        return (typeVariableTypeConstructorMarker == null || (typeParameter = typeSystemContext.getTypeParameter(typeVariableTypeConstructorMarker)) == null || !typeSystemContext.hasRecursiveBounds(typeParameter, typeConstructorMarker)) ? false : true;
    }

    public final boolean isSubtypeForSameConstructor(TypeCheckerState typeCheckerState, TypeArgumentListMarker capturedSubArguments, SimpleTypeMarker superType) {
        boolean zEqualTypes;
        Intrinsics.checkNotNullParameter(typeCheckerState, "<this>");
        Intrinsics.checkNotNullParameter(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(superType, "superType");
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(superType);
        int size = typeSystemContext.size(capturedSubArguments);
        int iParametersCount = typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor);
        if (size != iParametersCount || size != typeSystemContext.argumentsCount(superType)) {
            return false;
        }
        int i = 0;
        while (i < iParametersCount) {
            int i2 = i + 1;
            TypeArgumentMarker argument = typeSystemContext.getArgument(superType, i);
            if (!typeSystemContext.isStarProjection(argument)) {
                KotlinTypeMarker type = typeSystemContext.getType(argument);
                TypeArgumentMarker typeArgumentMarker = typeSystemContext.get(capturedSubArguments, i);
                typeSystemContext.getVariance(typeArgumentMarker);
                TypeVariance typeVariance = TypeVariance.INV;
                KotlinTypeMarker type2 = typeSystemContext.getType(typeArgumentMarker);
                AbstractTypeChecker abstractTypeChecker = INSTANCE;
                TypeVariance typeVarianceEffectiveVariance = abstractTypeChecker.effectiveVariance(typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructorMarkerTypeConstructor, i)), typeSystemContext.getVariance(argument));
                if (typeVarianceEffectiveVariance == null) {
                    return typeCheckerState.isErrorTypeEqualsToAnything();
                }
                if (typeVarianceEffectiveVariance == typeVariance && (abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type2, type, typeConstructorMarkerTypeConstructor) || abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type, type2, typeConstructorMarkerTypeConstructor))) {
                    continue;
                } else if (typeCheckerState.argumentsDepth <= 100) {
                    typeCheckerState.argumentsDepth++;
                    int i3 = WhenMappings.$EnumSwitchMapping$0[typeVarianceEffectiveVariance.ordinal()];
                    if (i3 == 1) {
                        zEqualTypes = abstractTypeChecker.equalTypes(typeCheckerState, type2, type);
                    } else if (i3 == 2) {
                        zEqualTypes = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type2, type, false, 8, null);
                    } else {
                        if (i3 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        zEqualTypes = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type, type2, false, 8, null);
                    }
                    typeCheckerState.argumentsDepth--;
                    if (!zEqualTypes) {
                        return false;
                    }
                } else {
                    throw new IllegalStateException(Intrinsics.stringPlus("Arguments depth is too high. Some related argument: ", type2).toString());
                }
            }
            i = i2;
        }
        return true;
    }

    private final boolean isCommonDenotableType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker) {
        return typeSystemContext.isDenotable(typeSystemContext.typeConstructor(kotlinTypeMarker)) && !typeSystemContext.isDynamic(kotlinTypeMarker) && !typeSystemContext.isDefinitelyNotNullType(kotlinTypeMarker) && Intrinsics.areEqual(typeSystemContext.typeConstructor(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarker)), typeSystemContext.typeConstructor(typeSystemContext.upperBoundIfFlexible(kotlinTypeMarker)));
    }

    public final TypeVariance effectiveVariance(TypeVariance declared, TypeVariance useSite) {
        Intrinsics.checkNotNullParameter(declared, "declared");
        Intrinsics.checkNotNullParameter(useSite, "useSite");
        TypeVariance typeVariance = TypeVariance.INV;
        if (declared == typeVariance) {
            return useSite;
        }
        if (useSite == typeVariance || declared == useSite) {
            return declared;
        }
        return null;
    }

    private final boolean isStubTypeSubtypeOfAnother(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker);
        SimpleTypeMarker simpleTypeMarkerOriginal = definitelyNotNullTypeMarkerAsDefinitelyNotNullType == null ? simpleTypeMarker : typeSystemContext.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType);
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType2 = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker2);
        if (typeSystemContext.typeConstructor(simpleTypeMarkerOriginal) != typeSystemContext.typeConstructor(definitelyNotNullTypeMarkerAsDefinitelyNotNullType2 == null ? simpleTypeMarker2 : typeSystemContext.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType2))) {
            return false;
        }
        if (typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker) || !typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker2)) {
            return !typeSystemContext.isMarkedNullable(simpleTypeMarker) || typeSystemContext.isMarkedNullable(simpleTypeMarker2);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Boolean checkSubtypeForSpecialCases(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState r15, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r16, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r17) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.checkSubtypeForSpecialCases(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):java.lang.Boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        return r9.getParameter(r9.typeConstructor(r10), r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext r9, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r10, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r11) {
        /*
            r8 = this;
            int r0 = r9.argumentsCount(r10)
            r1 = 0
            r2 = r1
        L6:
            r3 = 0
            if (r2 >= r0) goto L5e
            int r4 = r2 + 1
            kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker r5 = r9.getArgument(r10, r2)
            boolean r6 = r9.isStarProjection(r5)
            r7 = 1
            r6 = r6 ^ r7
            if (r6 == 0) goto L18
            r3 = r5
        L18:
            if (r3 != 0) goto L1b
            goto L52
        L1b:
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = r9.getType(r3)
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r5 = r9.lowerBoundIfFlexible(r3)
            boolean r5 = r9.isCapturedType(r5)
            if (r5 == 0) goto L34
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r5 = r9.lowerBoundIfFlexible(r11)
            boolean r5 = r9.isCapturedType(r5)
            if (r5 == 0) goto L34
            goto L35
        L34:
            r7 = r1
        L35:
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r11)
            if (r5 != 0) goto L55
            if (r7 == 0) goto L4c
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r5 = r9.typeConstructor(r3)
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r6 = r9.typeConstructor(r11)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L4c
            goto L55
        L4c:
            kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker r2 = r8.getTypeParameterForArgumentInBaseIfItEqualToTarget(r9, r3, r11)
            if (r2 != 0) goto L54
        L52:
            r2 = r4
            goto L6
        L54:
            return r2
        L55:
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r10 = r9.typeConstructor(r10)
            kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker r9 = r9.getParameter(r10, r2)
            return r9
        L5e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.getTypeParameterForArgumentInBaseIfItEqualToTarget(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker):kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker");
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicySubstitutionSupertypePolicy;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        List<SimpleTypeMarker> listFastCorrespondingSupertypes = typeSystemContext.fastCorrespondingSupertypes(simpleTypeMarker, typeConstructorMarker);
        if (listFastCorrespondingSupertypes == null) {
            if (!typeSystemContext.isClassTypeConstructor(typeConstructorMarker) && typeSystemContext.isClassType(simpleTypeMarker)) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            if (typeSystemContext.isCommonFinalClassConstructor(typeConstructorMarker)) {
                if (typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker), typeConstructorMarker)) {
                    SimpleTypeMarker simpleTypeMarkerCaptureFromArguments = typeSystemContext.captureFromArguments(simpleTypeMarker, CaptureStatus.FOR_SUBTYPING);
                    if (simpleTypeMarkerCaptureFromArguments != null) {
                        simpleTypeMarker = simpleTypeMarkerCaptureFromArguments;
                    }
                    return CollectionsKt__CollectionsJVMKt.listOf(simpleTypeMarker);
                }
                return CollectionsKt__CollectionsKt.emptyList();
            }
            listFastCorrespondingSupertypes = new SmartList<>();
            typeCheckerState.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = typeCheckerState.getSupertypesDeque();
            Intrinsics.checkNotNull(supertypesDeque);
            Set<SimpleTypeMarker> supertypesSet = typeCheckerState.getSupertypesSet();
            Intrinsics.checkNotNull(supertypesSet);
            supertypesDeque.push(simpleTypeMarker);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkNotNullExpressionValue(current, "current");
                if (supertypesSet.add(current)) {
                    SimpleTypeMarker simpleTypeMarkerCaptureFromArguments2 = typeSystemContext.captureFromArguments(current, CaptureStatus.FOR_SUBTYPING);
                    if (simpleTypeMarkerCaptureFromArguments2 == null) {
                        simpleTypeMarkerCaptureFromArguments2 = current;
                    }
                    if (typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarkerCaptureFromArguments2), typeConstructorMarker)) {
                        listFastCorrespondingSupertypes.add(simpleTypeMarkerCaptureFromArguments2);
                        supertypesPolicySubstitutionSupertypePolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                    } else if (typeSystemContext.argumentsCount(simpleTypeMarkerCaptureFromArguments2) == 0) {
                        supertypesPolicySubstitutionSupertypePolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    } else {
                        supertypesPolicySubstitutionSupertypePolicy = typeCheckerState.getTypeSystemContext().substitutionSupertypePolicy(simpleTypeMarkerCaptureFromArguments2);
                    }
                    if (!(!Intrinsics.areEqual(supertypesPolicySubstitutionSupertypePolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicySubstitutionSupertypePolicy = null;
                    }
                    if (supertypesPolicySubstitutionSupertypePolicy != null) {
                        TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                        Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                        while (it.hasNext()) {
                            supertypesDeque.add(supertypesPolicySubstitutionSupertypePolicy.mo4090transformType(typeCheckerState, it.next()));
                        }
                    }
                }
            }
            typeCheckerState.clear();
        }
        return listFastCorrespondingSupertypes;
    }

    private final List<SimpleTypeMarker> collectAndFilter(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(typeCheckerState, collectAllSupertypesWithGivenTypeConstructor(typeCheckerState, simpleTypeMarker, typeConstructorMarker));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(TypeCheckerState typeCheckerState, List<? extends SimpleTypeMarker> list) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (list.size() < 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            TypeArgumentListMarker typeArgumentListMarkerAsArgumentList = typeSystemContext.asArgumentList((SimpleTypeMarker) next);
            int size = typeSystemContext.size(typeArgumentListMarkerAsArgumentList);
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                int i2 = i + 1;
                if (!(typeSystemContext.asFlexibleType(typeSystemContext.getType(typeSystemContext.get(typeArgumentListMarkerAsArgumentList, i))) == null)) {
                    z = false;
                    break;
                }
                i = i2;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        return arrayList.isEmpty() ^ true ? arrayList : list;
    }

    public final List<SimpleTypeMarker> findCorrespondingSupertypes(TypeCheckerState state, SimpleTypeMarker subType, TypeConstructorMarker superConstructor) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superConstructor, "superConstructor");
        TypeSystemContext typeSystemContext = state.getTypeSystemContext();
        if (typeSystemContext.isClassType(subType)) {
            return INSTANCE.collectAndFilter(state, subType, superConstructor);
        }
        if (!typeSystemContext.isClassTypeConstructor(superConstructor) && !typeSystemContext.isIntegerLiteralTypeConstructor(superConstructor)) {
            return INSTANCE.collectAllSupertypesWithGivenTypeConstructor(state, subType, superConstructor);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList();
        state.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = state.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = state.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(subType);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + subType + ". Supertypes = " + CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                if (typeSystemContext.isClassType(current)) {
                    smartList.add(current);
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                } else {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(supertypesPolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE))) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy != null) {
                    TypeSystemContext typeSystemContext2 = state.getTypeSystemContext();
                    Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(supertypesPolicy.mo4090transformType(state, it.next()));
                    }
                }
            }
        }
        state.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleTypeMarker it2 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(state, it2, superConstructor));
        }
        return arrayList;
    }
}
