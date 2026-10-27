package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "kotlin.jvm.PlatformType", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke"}, k = 3, mv = {1, 5, 1})
public final class KClassImpl$Data$supertypes$2 extends Lambda implements Function0<List<? extends KTypeImpl>> {
    public final /* synthetic */ KClassImpl.Data this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassImpl$Data$supertypes$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    @Override // kotlin.jvm.functions.Function0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<? extends kotlin.reflect.jvm.internal.KTypeImpl> invoke() {
        /*
            r6 = this;
            kotlin.reflect.jvm.internal.KClassImpl$Data r0 = r6.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = r0.getDescriptor()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r0.getTypeConstructor()
            java.lang.String r1 = "descriptor.typeConstructor"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.Collection r0 = r0.mo1346getSupertypes()
            java.lang.String r1 = "descriptor.typeConstructor.supertypes"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L25:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L44
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
            kotlin.reflect.jvm.internal.KTypeImpl r3 = new kotlin.reflect.jvm.internal.KTypeImpl
            java.lang.String r4 = "kotlinType"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1 r4 = new kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1
            r4.<init>()
            r3.<init>(r2, r4)
            r1.add(r3)
            goto L25
        L44:
            kotlin.reflect.jvm.internal.KClassImpl$Data r0 = r6.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = r0.getDescriptor()
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isSpecialClassWithNoSupertypes(r0)
            if (r0 != 0) goto Lad
            boolean r0 = r1.isEmpty()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L5a
        L58:
            r2 = r3
            goto L8e
        L5a:
            java.util.Iterator r0 = r1.iterator()
        L5e:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r0.next()
            kotlin.reflect.jvm.internal.KTypeImpl r4 = (kotlin.reflect.jvm.internal.KTypeImpl) r4
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = r4.getType()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getClassDescriptorForType(r4)
            java.lang.String r5 = "DescriptorUtils.getClassDescriptorForType(it.type)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r4 = r4.getKind()
            java.lang.String r5 = "DescriptorUtils.getClass…ptorForType(it.type).kind"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r5 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.INTERFACE
            if (r4 == r5) goto L8b
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r5 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ANNOTATION_CLASS
            if (r4 != r5) goto L89
            goto L8b
        L89:
            r4 = r2
            goto L8c
        L8b:
            r4 = r3
        L8c:
            if (r4 != 0) goto L5e
        L8e:
            if (r2 == 0) goto Lad
            kotlin.reflect.jvm.internal.KTypeImpl r0 = new kotlin.reflect.jvm.internal.KTypeImpl
            kotlin.reflect.jvm.internal.KClassImpl$Data r2 = r6.this$0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = r2.getDescriptor()
            kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r2 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getBuiltIns(r2)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = r2.getAnyType()
            java.lang.String r3 = "descriptor.builtIns.anyType"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3 r3 = new kotlin.jvm.functions.Function0<java.lang.reflect.Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3
                static {
                    /*
                        kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3 r0 = new kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3) kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3.INSTANCE kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ java.lang.reflect.Type invoke() {
                    /*
                        r1 = this;
                        java.lang.reflect.Type r0 = r1.invoke()
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.invoke():java.lang.Object");
                }

                @Override // kotlin.jvm.functions.Function0
                @org.jetbrains.annotations.NotNull
                public final java.lang.reflect.Type invoke() {
                    /*
                        r1 = this;
                        java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.AnonymousClass3.invoke():java.lang.reflect.Type");
                }
            }
            r0.<init>(r2, r3)
            r1.add(r0)
        Lad:
            java.util.List r0 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.invoke():java.util.List");
    }
}
