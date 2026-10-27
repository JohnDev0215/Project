package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class AbstractTypeChecker {

    @NotNull
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();

    @JvmField
    public static boolean RUN_SLOW_ASSERTIONS;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            iArr[TypeVariance.INV.ordinal()] = 1;
            iArr[TypeVariance.OUT.ordinal()] = 2;
            iArr[TypeVariance.IN.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AbstractTypeCheckerContext.LowerCapturedTypePolicy.values().length];
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            iArr2[AbstractTypeCheckerContext.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private AbstractTypeChecker() {
    }

    private final Boolean checkSubtypeForIntegerLiteralType(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        if (!typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && !typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return null;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return Boolean.TRUE;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker)) {
            if (m1350xd35c7e25(typeSystemContext, abstractTypeCheckerContext, simpleTypeMarker, simpleTypeMarker2, false)) {
                return Boolean.TRUE;
            }
        } else if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker2) && (m1349xabd2962a(typeSystemContext, simpleTypeMarker) || m1350xd35c7e25(typeSystemContext, abstractTypeCheckerContext, simpleTypeMarker2, simpleTypeMarker, true))) {
            return Boolean.TRUE;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isIntegerLiteralTypeInIntersectionComponents, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final boolean m1349xabd2962a(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext r4, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r5) {
        /*
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r5 = r4.typeConstructor(r5)
            boolean r0 = r5 instanceof kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L46
            java.util.Collection r5 = r4.supertypes(r5)
            boolean r0 = r5 instanceof java.util.Collection
            if (r0 == 0) goto L1a
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L1a
        L18:
            r4 = r2
            goto L43
        L1a:
            java.util.Iterator r5 = r5.iterator()
        L1e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L18
            java.lang.Object r0 = r5.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r0 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r0
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r0 = r4.asSimpleType(r0)
            if (r0 != 0) goto L32
            r0 = 0
            goto L3a
        L32:
            boolean r0 = r4.isIntegerLiteralType(r0)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L3a:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r0 == 0) goto L1e
            r4 = r1
        L43:
            if (r4 == 0) goto L46
            goto L47
        L46:
            r1 = r2
        L47:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.m1349xabd2962a(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):boolean");
    }

    /* JADX INFO: renamed from: checkSubtypeForIntegerLiteralType$lambda-7$isTypeInIntegerLiteralType, reason: not valid java name */
    private static final boolean m1350xd35c7e25(TypeSystemContext typeSystemContext, AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2, boolean z) {
        Collection<KotlinTypeMarker> collectionPossibleIntegerTypes = typeSystemContext.possibleIntegerTypes(simpleTypeMarker);
        if (!(collectionPossibleIntegerTypes instanceof Collection) || !collectionPossibleIntegerTypes.isEmpty()) {
            for (KotlinTypeMarker kotlinTypeMarker : collectionPossibleIntegerTypes) {
                if (Intrinsics.areEqual(typeSystemContext.typeConstructor(kotlinTypeMarker), typeSystemContext.typeConstructor(simpleTypeMarker2)) || (z && isSubtypeOf$default(INSTANCE, abstractTypeCheckerContext, simpleTypeMarker2, kotlinTypeMarker, false, 8, null))) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Boolean checkSubtypeForSpecialCases(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        boolean zStrictEqualTypes;
        SimpleTypeMarker simpleTypeMarkerOriginal;
        TypeParameterMarker typeParameterForArgumentInBaseIfItEqualToTarget;
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        boolean z = false;
        if (typeSystemContext.isError(simpleTypeMarker) || typeSystemContext.isError(simpleTypeMarker2)) {
            if (abstractTypeCheckerContext.isErrorTypeEqualsToAnything()) {
                return Boolean.TRUE;
            }
            if (typeSystemContext.isMarkedNullable(simpleTypeMarker) && !typeSystemContext.isMarkedNullable(simpleTypeMarker2)) {
                return Boolean.FALSE;
            }
            zStrictEqualTypes = AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(typeSystemContext, typeSystemContext.withNullability(simpleTypeMarker, false), typeSystemContext.withNullability(simpleTypeMarker2, false));
        } else {
            if (!typeSystemContext.isStubType(simpleTypeMarker) && !typeSystemContext.isStubType(simpleTypeMarker2)) {
                DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker2);
                if (definitelyNotNullTypeMarkerAsDefinitelyNotNullType == null || (simpleTypeMarkerOriginal = typeSystemContext.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType)) == null) {
                    simpleTypeMarkerOriginal = simpleTypeMarker2;
                }
                CapturedTypeMarker capturedTypeMarkerAsCapturedType = typeSystemContext.asCapturedType(simpleTypeMarkerOriginal);
                KotlinTypeMarker kotlinTypeMarkerLowerType = capturedTypeMarkerAsCapturedType == null ? null : typeSystemContext.lowerType(capturedTypeMarkerAsCapturedType);
                if (capturedTypeMarkerAsCapturedType != null && kotlinTypeMarkerLowerType != null) {
                    if (typeSystemContext.isMarkedNullable(simpleTypeMarker2)) {
                        kotlinTypeMarkerLowerType = typeSystemContext.withNullability(kotlinTypeMarkerLowerType, true);
                    } else if (typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker2)) {
                        kotlinTypeMarkerLowerType = typeSystemContext.makeDefinitelyNotNullOrNotNull(kotlinTypeMarkerLowerType);
                    }
                    KotlinTypeMarker kotlinTypeMarker = kotlinTypeMarkerLowerType;
                    int i = WhenMappings.$EnumSwitchMapping$1[abstractTypeCheckerContext.getLowerCapturedTypePolicy(simpleTypeMarker, capturedTypeMarkerAsCapturedType).ordinal()];
                    if (i == 1) {
                        zStrictEqualTypes = isSubtypeOf$default(INSTANCE, abstractTypeCheckerContext, simpleTypeMarker, kotlinTypeMarker, false, 8, null);
                    } else if (i == 2 && isSubtypeOf$default(INSTANCE, abstractTypeCheckerContext, simpleTypeMarker, kotlinTypeMarker, false, 8, null)) {
                        return Boolean.TRUE;
                    }
                }
                TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker2);
                if (!typeSystemContext.isIntersection(typeConstructorMarkerTypeConstructor)) {
                    if ((simpleTypeMarker instanceof CapturedTypeMarker) && (typeParameterForArgumentInBaseIfItEqualToTarget = INSTANCE.getTypeParameterForArgumentInBaseIfItEqualToTarget(abstractTypeCheckerContext.getTypeSystemContext(), simpleTypeMarker2, simpleTypeMarker)) != null && typeSystemContext.hasRecursiveBounds(typeParameterForArgumentInBaseIfItEqualToTarget, typeSystemContext.typeConstructor(simpleTypeMarker2))) {
                        return Boolean.TRUE;
                    }
                    return null;
                }
                typeSystemContext.isMarkedNullable(simpleTypeMarker2);
                Collection<KotlinTypeMarker> collectionSupertypes = typeSystemContext.supertypes(typeConstructorMarkerTypeConstructor);
                if ((collectionSupertypes instanceof Collection) && collectionSupertypes.isEmpty()) {
                    z = true;
                } else {
                    Iterator<T> it = collectionSupertypes.iterator();
                    while (it.hasNext()) {
                        if (!isSubtypeOf$default(INSTANCE, abstractTypeCheckerContext, simpleTypeMarker, (KotlinTypeMarker) it.next(), false, 8, null)) {
                            break;
                        }
                    }
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            zStrictEqualTypes = abstractTypeCheckerContext.isStubTypeEqualsToAnything();
        }
        return Boolean.valueOf(zStrictEqualTypes);
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicySubstitutionSupertypePolicy;
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        List<SimpleTypeMarker> listFastCorrespondingSupertypes = typeSystemContext.fastCorrespondingSupertypes(simpleTypeMarker, typeConstructorMarker);
        if (listFastCorrespondingSupertypes == null) {
            if (!typeSystemContext.isClassTypeConstructor(typeConstructorMarker) && typeSystemContext.isClassType(simpleTypeMarker)) {
                return CollectionsKt.emptyList();
            }
            if (typeSystemContext.isCommonFinalClassConstructor(typeConstructorMarker)) {
                if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker), typeConstructorMarker)) {
                    return CollectionsKt.emptyList();
                }
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments = typeSystemContext.captureFromArguments(simpleTypeMarker, CaptureStatus.FOR_SUBTYPING);
                if (simpleTypeMarkerCaptureFromArguments != null) {
                    simpleTypeMarker = simpleTypeMarkerCaptureFromArguments;
                }
                return CollectionsKt.listOf(simpleTypeMarker);
            }
            listFastCorrespondingSupertypes = new SmartList<>();
            abstractTypeCheckerContext.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
            Intrinsics.checkNotNull(supertypesDeque);
            Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
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
                        supertypesPolicySubstitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                    } else {
                        supertypesPolicySubstitutionSupertypePolicy = typeSystemContext.argumentsCount(simpleTypeMarkerCaptureFromArguments2) == 0 ? AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE : abstractTypeCheckerContext.substitutionSupertypePolicy(simpleTypeMarkerCaptureFromArguments2);
                    }
                    if (!(!Intrinsics.areEqual(supertypesPolicySubstitutionSupertypePolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicySubstitutionSupertypePolicy = null;
                    }
                    if (supertypesPolicySubstitutionSupertypePolicy != null) {
                        TypeSystemContext typeSystemContext2 = abstractTypeCheckerContext.getTypeSystemContext();
                        Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                        while (it.hasNext()) {
                            supertypesDeque.add(supertypesPolicySubstitutionSupertypePolicy.mo1351transformType(abstractTypeCheckerContext, it.next()));
                        }
                    }
                }
            }
            abstractTypeCheckerContext.clear();
        }
        return listFastCorrespondingSupertypes;
    }

    private final List<SimpleTypeMarker> collectAndFilter(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(abstractTypeCheckerContext, collectAllSupertypesWithGivenTypeConstructor(abstractTypeCheckerContext, simpleTypeMarker, typeConstructorMarker));
    }

    private final boolean completeIsSubTypeOf(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        KotlinTypeMarker kotlinTypeMarkerPrepareType = abstractTypeCheckerContext.prepareType(abstractTypeCheckerContext.refineType(kotlinTypeMarker));
        KotlinTypeMarker kotlinTypeMarkerPrepareType2 = abstractTypeCheckerContext.prepareType(abstractTypeCheckerContext.refineType(kotlinTypeMarker2));
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        Boolean boolCheckSubtypeForSpecialCases = abstractTypeChecker.checkSubtypeForSpecialCases(abstractTypeCheckerContext, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2));
        if (boolCheckSubtypeForSpecialCases == null) {
            Boolean boolAddSubtypeConstraint = abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
            return boolAddSubtypeConstraint == null ? abstractTypeChecker.isSubtypeOfForSingleClassifierType(abstractTypeCheckerContext, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2)) : boolAddSubtypeConstraint.booleanValue();
        }
        boolean zBooleanValue = boolCheckSubtypeForSpecialCases.booleanValue();
        abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
        return zBooleanValue;
    }

    private final TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        int iArgumentsCount = typeSystemContext.argumentsCount(kotlinTypeMarker);
        if (iArgumentsCount > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                TypeArgumentMarker argument = typeSystemContext.getArgument(kotlinTypeMarker, i);
                if (!(!typeSystemContext.isStarProjection(argument))) {
                    argument = null;
                }
                if (argument != null) {
                    if (Intrinsics.areEqual(typeSystemContext.getType(argument), kotlinTypeMarker2)) {
                        return typeSystemContext.getParameter(typeSystemContext.typeConstructor(kotlinTypeMarker), i);
                    }
                    TypeParameterMarker typeParameterForArgumentInBaseIfItEqualToTarget = getTypeParameterForArgumentInBaseIfItEqualToTarget(typeSystemContext, typeSystemContext.getType(argument), kotlinTypeMarker2);
                    if (typeParameterForArgumentInBaseIfItEqualToTarget != null) {
                        return typeParameterForArgumentInBaseIfItEqualToTarget;
                    }
                }
                if (i2 >= iArgumentsCount) {
                    break;
                }
                i = i2;
            }
        }
        return null;
    }

    private final boolean hasNothingSupertype(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker) {
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeSystemContext.isClassTypeConstructor(typeConstructorMarkerTypeConstructor)) {
            return typeSystemContext.isNothingConstructor(typeConstructorMarkerTypeConstructor);
        }
        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        abstractTypeCheckerContext.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker current = supertypesDeque.pop();
            Intrinsics.checkNotNullExpressionValue(current, "current");
            if (supertypesSet.add(current)) {
                AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy = typeSystemContext.isClassType(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy == null) {
                    continue;
                } else {
                    TypeSystemContext typeSystemContext2 = abstractTypeCheckerContext.getTypeSystemContext();
                    Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        SimpleTypeMarker simpleTypeMarkerMo1351transformType = supertypesPolicy.mo1351transformType(abstractTypeCheckerContext, it.next());
                        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarkerMo1351transformType))) {
                            abstractTypeCheckerContext.clear();
                            return true;
                        }
                        supertypesDeque.add(simpleTypeMarkerMo1351transformType);
                    }
                }
            }
        }
        abstractTypeCheckerContext.clear();
        return false;
    }

    private final boolean isCommonDenotableType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker) {
        return typeSystemContext.isDenotable(typeSystemContext.typeConstructor(kotlinTypeMarker)) && !typeSystemContext.isDynamic(kotlinTypeMarker) && !typeSystemContext.isDefinitelyNotNullType(kotlinTypeMarker) && Intrinsics.areEqual(typeSystemContext.typeConstructor(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarker)), typeSystemContext.typeConstructor(typeSystemContext.upperBoundIfFlexible(kotlinTypeMarker)));
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(abstractTypeCheckerContext, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    private final boolean isSubtypeOfForSingleClassifierType(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        boolean z;
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        if (RUN_SLOW_ASSERTIONS) {
            if (!typeSystemContext.isSingleClassifierType(simpleTypeMarker) && !typeSystemContext.isIntersection(typeSystemContext.typeConstructor(simpleTypeMarker))) {
                abstractTypeCheckerContext.isAllowedTypeVariableBridge(simpleTypeMarker);
            }
            if (!typeSystemContext.isSingleClassifierType(simpleTypeMarker2)) {
                abstractTypeCheckerContext.isAllowedTypeVariableBridge(simpleTypeMarker2);
            }
        }
        boolean z2 = false;
        if (!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(abstractTypeCheckerContext, simpleTypeMarker, simpleTypeMarker2)) {
            return false;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        Boolean boolCheckSubtypeForIntegerLiteralType = abstractTypeChecker.checkSubtypeForIntegerLiteralType(abstractTypeCheckerContext, typeSystemContext.lowerBoundIfFlexible(simpleTypeMarker), typeSystemContext.upperBoundIfFlexible(simpleTypeMarker2));
        if (boolCheckSubtypeForIntegerLiteralType != null) {
            boolean zBooleanValue = boolCheckSubtypeForIntegerLiteralType.booleanValue();
            AbstractTypeCheckerContext.addSubtypeConstraint$default(abstractTypeCheckerContext, simpleTypeMarker, simpleTypeMarker2, false, 4, null);
            return zBooleanValue;
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker2);
        boolean z3 = true;
        if ((typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker), typeConstructorMarkerTypeConstructor) && typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor) == 0) || typeSystemContext.isAnyConstructor(typeSystemContext.typeConstructor(simpleTypeMarker2))) {
            return true;
        }
        List<SimpleTypeMarker> listFindCorrespondingSupertypes = abstractTypeChecker.findCorrespondingSupertypes(abstractTypeCheckerContext, simpleTypeMarker, typeConstructorMarkerTypeConstructor);
        ArrayList<SimpleTypeMarker> arrayList = new ArrayList(CollectionsKt.b(listFindCorrespondingSupertypes));
        for (SimpleTypeMarker simpleTypeMarker3 : listFindCorrespondingSupertypes) {
            SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(abstractTypeCheckerContext.prepareType(simpleTypeMarker3));
            if (simpleTypeMarkerAsSimpleType != null) {
                simpleTypeMarker3 = simpleTypeMarkerAsSimpleType;
            }
            arrayList.add(simpleTypeMarker3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return INSTANCE.hasNothingSupertype(abstractTypeCheckerContext, simpleTypeMarker);
        }
        if (size == 1) {
            return INSTANCE.isSubtypeForSameConstructor(abstractTypeCheckerContext, typeSystemContext.asArgumentList((SimpleTypeMarker) CollectionsKt.first((List) arrayList)), simpleTypeMarker2);
        }
        ArgumentList argumentList = new ArgumentList(typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor));
        int iParametersCount = typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor);
        if (iParametersCount > 0) {
            int i = 0;
            z = false;
            while (true) {
                int i2 = i + 1;
                z = (z || typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructorMarkerTypeConstructor, i)) != TypeVariance.OUT) ? z3 : z2;
                if (!z) {
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
                    for (SimpleTypeMarker simpleTypeMarker4 : arrayList) {
                        TypeArgumentMarker argumentOrNull = typeSystemContext.getArgumentOrNull(simpleTypeMarker4, i);
                        KotlinTypeMarker type = null;
                        if (argumentOrNull != null) {
                            TypeArgumentMarker typeArgumentMarker = typeSystemContext.getVariance(argumentOrNull) == TypeVariance.INV ? argumentOrNull : null;
                            if (typeArgumentMarker != null) {
                                type = typeSystemContext.getType(typeArgumentMarker);
                            }
                        }
                        KotlinTypeMarker kotlinTypeMarker = type;
                        if (kotlinTypeMarker == null) {
                            throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker4 + ", subType: " + simpleTypeMarker + ", superType: " + simpleTypeMarker2).toString());
                        }
                        arrayList2.add(kotlinTypeMarker);
                    }
                    argumentList.add(typeSystemContext.asTypeArgument(typeSystemContext.intersectTypes(arrayList2)));
                }
                if (i2 >= iParametersCount) {
                    break;
                }
                i = i2;
                z2 = false;
                z3 = true;
            }
        } else {
            z = false;
        }
        if (!z && INSTANCE.isSubtypeForSameConstructor(abstractTypeCheckerContext, argumentList, simpleTypeMarker2)) {
            return true;
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (INSTANCE.isSubtypeForSameConstructor(abstractTypeCheckerContext, typeSystemContext.asArgumentList((SimpleTypeMarker) it.next()), simpleTypeMarker2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, TypeConstructorMarker typeConstructorMarker) {
        SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(kotlinTypeMarker);
        if (simpleTypeMarkerAsSimpleType instanceof CapturedTypeMarker) {
            CapturedTypeMarker capturedTypeMarker = (CapturedTypeMarker) simpleTypeMarkerAsSimpleType;
            if (!typeSystemContext.isStarProjection(typeSystemContext.projection(typeSystemContext.typeConstructor(capturedTypeMarker))) || typeSystemContext.captureStatus(capturedTypeMarker) != CaptureStatus.FOR_SUBTYPING) {
                return false;
            }
            TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(kotlinTypeMarker2);
            TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker = typeConstructorMarkerTypeConstructor instanceof TypeVariableTypeConstructorMarker ? (TypeVariableTypeConstructorMarker) typeConstructorMarkerTypeConstructor : null;
            if (typeVariableTypeConstructorMarker == null) {
                return false;
            }
            TypeParameterMarker typeParameter = typeSystemContext.getTypeParameter(typeVariableTypeConstructorMarker);
            return Intrinsics.areEqual(typeParameter != null ? Boolean.valueOf(typeSystemContext.hasRecursiveBounds(typeParameter, typeConstructorMarker)) : null, Boolean.TRUE);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(AbstractTypeCheckerContext abstractTypeCheckerContext, List<? extends SimpleTypeMarker> list) {
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
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
                if (!(typeSystemContext.asFlexibleType(typeSystemContext.getType(typeSystemContext.get(typeArgumentListMarkerAsArgumentList, i))) == null)) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        return arrayList.isEmpty() ^ true ? arrayList : list;
    }

    @Nullable
    public final TypeVariance effectiveVariance(@NotNull TypeVariance declared, @NotNull TypeVariance useSite) {
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

    public final boolean equalTypes(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker a, @NotNull KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        TypeSystemContext typeSystemContext = context.getTypeSystemContext();
        if (a == b) {
            return true;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        if (abstractTypeChecker.isCommonDenotableType(typeSystemContext, a) && abstractTypeChecker.isCommonDenotableType(typeSystemContext, b)) {
            KotlinTypeMarker kotlinTypeMarkerRefineType = context.refineType(a);
            KotlinTypeMarker kotlinTypeMarkerRefineType2 = context.refineType(b);
            SimpleTypeMarker simpleTypeMarkerLowerBoundIfFlexible = typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerRefineType);
            if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(kotlinTypeMarkerRefineType), typeSystemContext.typeConstructor(kotlinTypeMarkerRefineType2))) {
                return false;
            }
            if (typeSystemContext.argumentsCount(simpleTypeMarkerLowerBoundIfFlexible) == 0) {
                return typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerRefineType) || typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerRefineType2) || typeSystemContext.isMarkedNullable(simpleTypeMarkerLowerBoundIfFlexible) == typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerRefineType2));
            }
        }
        return isSubtypeOf$default(abstractTypeChecker, context, a, b, false, 8, null) && isSubtypeOf$default(abstractTypeChecker, context, b, a, false, 8, null);
    }

    @NotNull
    public final List<SimpleTypeMarker> findCorrespondingSupertypes(@NotNull AbstractTypeCheckerContext context, @NotNull SimpleTypeMarker subType, @NotNull TypeConstructorMarker superConstructor) {
        AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superConstructor, "superConstructor");
        TypeSystemContext typeSystemContext = context.getTypeSystemContext();
        if (typeSystemContext.isClassType(subType)) {
            return INSTANCE.collectAndFilter(context, subType, superConstructor);
        }
        if (!typeSystemContext.isClassTypeConstructor(superConstructor) && !typeSystemContext.isIntegerLiteralTypeConstructor(superConstructor)) {
            return INSTANCE.collectAllSupertypesWithGivenTypeConstructor(context, subType, superConstructor);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList();
        context.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = context.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set<SimpleTypeMarker> supertypesSet = context.getSupertypesSet();
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
                    supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                } else {
                    supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy != null) {
                    TypeSystemContext typeSystemContext2 = context.getTypeSystemContext();
                    Iterator<KotlinTypeMarker> it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(current)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(supertypesPolicy.mo1351transformType(context, it.next()));
                    }
                }
            }
        }
        context.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleTypeMarker it2 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            CollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(context, it2, superConstructor));
        }
        return arrayList;
    }

    public final boolean isSubtypeForSameConstructor(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, @NotNull TypeArgumentListMarker capturedSubArguments, @NotNull SimpleTypeMarker superType) {
        boolean zEqualTypes;
        boolean z;
        int i;
        Object obj;
        AbstractTypeChecker abstractTypeChecker;
        AbstractTypeCheckerContext abstractTypeCheckerContext2;
        KotlinTypeMarker kotlinTypeMarker;
        Intrinsics.checkNotNullParameter(abstractTypeCheckerContext, "<this>");
        Intrinsics.checkNotNullParameter(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(superType, "superType");
        TypeSystemContext typeSystemContext = abstractTypeCheckerContext.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(superType);
        int size = typeSystemContext.size(capturedSubArguments);
        int iParametersCount = typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor);
        if (size != iParametersCount || size != typeSystemContext.argumentsCount(superType)) {
            return false;
        }
        if (iParametersCount > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                TypeArgumentMarker argument = typeSystemContext.getArgument(superType, i2);
                if (!typeSystemContext.isStarProjection(argument)) {
                    KotlinTypeMarker type = typeSystemContext.getType(argument);
                    TypeArgumentMarker typeArgumentMarker = typeSystemContext.get(capturedSubArguments, i2);
                    typeSystemContext.getVariance(typeArgumentMarker);
                    TypeVariance typeVariance = TypeVariance.INV;
                    KotlinTypeMarker type2 = typeSystemContext.getType(typeArgumentMarker);
                    AbstractTypeChecker abstractTypeChecker2 = INSTANCE;
                    TypeVariance typeVarianceEffectiveVariance = abstractTypeChecker2.effectiveVariance(typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructorMarkerTypeConstructor, i2)), typeSystemContext.getVariance(argument));
                    if (typeVarianceEffectiveVariance == null) {
                        return abstractTypeCheckerContext.isErrorTypeEqualsToAnything();
                    }
                    if (!(typeVarianceEffectiveVariance == typeVariance && (abstractTypeChecker2.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type2, type, typeConstructorMarkerTypeConstructor) || abstractTypeChecker2.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type, type2, typeConstructorMarkerTypeConstructor)))) {
                        if (abstractTypeCheckerContext.argumentsDepth > 100) {
                            throw new IllegalStateException(Intrinsics.stringPlus("Arguments depth is too high. Some related argument: ", type2).toString());
                        }
                        abstractTypeCheckerContext.argumentsDepth++;
                        int i4 = WhenMappings.$EnumSwitchMapping$0[typeVarianceEffectiveVariance.ordinal()];
                        if (i4 != 1) {
                            if (i4 == 2) {
                                z = false;
                                i = 8;
                                obj = null;
                                abstractTypeChecker = abstractTypeChecker2;
                                abstractTypeCheckerContext2 = abstractTypeCheckerContext;
                                kotlinTypeMarker = type2;
                            } else {
                                if (i4 != 3) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                z = false;
                                i = 8;
                                obj = null;
                                abstractTypeChecker = abstractTypeChecker2;
                                abstractTypeCheckerContext2 = abstractTypeCheckerContext;
                                kotlinTypeMarker = type;
                                type = type2;
                            }
                            zEqualTypes = isSubtypeOf$default(abstractTypeChecker, abstractTypeCheckerContext2, kotlinTypeMarker, type, z, i, obj);
                        } else {
                            zEqualTypes = abstractTypeChecker2.equalTypes(abstractTypeCheckerContext, type2, type);
                        }
                        abstractTypeCheckerContext.argumentsDepth--;
                        if (!zEqualTypes) {
                            return false;
                        }
                    }
                }
                if (i3 >= iParametersCount) {
                    break;
                }
                i2 = i3;
            }
        }
        return true;
    }

    public final boolean isSubtypeOf(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        if (subType == superType) {
            return true;
        }
        if (context.customIsSubtypeOf(subType, superType)) {
            return completeIsSubTypeOf(context, subType, superType, z);
        }
        return false;
    }
}
