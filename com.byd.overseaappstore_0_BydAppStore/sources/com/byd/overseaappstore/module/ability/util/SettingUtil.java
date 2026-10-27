package com.byd.overseaappstore.module.ability.util;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.n4;

/* JADX INFO: loaded from: classes.dex */
public final class SettingUtil {

    @NotNull
    public static final SettingUtil a = new SettingUtil();

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.SettingUtil", f = "SettingUtil.kt", i = {}, l = {41}, m = "getUseByWifiState", n = {}, s = {})
    public static final class a extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return SettingUtil.this.a(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.SettingUtil$saveProtocolAgreeState$1", f = "SettingUtil.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public int a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ boolean c;

        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.SettingUtil$saveProtocolAgreeState$1$1", f = "SettingUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ boolean b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(boolean z, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.b, continuation);
                aVar.a = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
                return ((a) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                ((MutablePreferences) this.a).set(n4.e, Boxing.boxBoolean(this.b));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Context context, boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.b = context;
            this.c = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.b, this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DataStore<Preferences> dataStoreA = n4.a(this.b);
                a aVar = new a(this.c, null);
                this.a = 1;
                obj = PreferencesKt.edit(dataStoreA, aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Boolean bool = (Boolean) ((Preferences) obj).get(n4.e);
            return Boxing.boxBoolean(bool != null ? bool.booleanValue() : false);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.SettingUtil", f = "SettingUtil.kt", i = {}, l = {29}, m = "saveUseByWifiState", n = {}, s = {})
    public static final class c extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return SettingUtil.this.c(null, false, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.SettingUtil$saveUseByWifiState$2", f = "SettingUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class d extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object a;
        public final /* synthetic */ boolean b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(boolean z, Continuation<? super d> continuation) {
            super(2, continuation);
            this.b = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            d dVar = new d(this.b, continuation);
            dVar.a = obj;
            return dVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
            return ((d) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            ((MutablePreferences) this.a).set(n4.c, Boxing.boxBoolean(this.b));
            return Unit.INSTANCE;
        }
    }

    private SettingUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull android.content.Context r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.byd.overseaappstore.module.ability.util.SettingUtil.a
            if (r0 == 0) goto L13
            r0 = r6
            com.byd.overseaappstore.module.ability.util.SettingUtil$a r0 = (com.byd.overseaappstore.module.ability.util.SettingUtil.a) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            com.byd.overseaappstore.module.ability.util.SettingUtil$a r0 = new com.byd.overseaappstore.module.ability.util.SettingUtil$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4a
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.datastore.core.DataStore r5 = spi.n4.a(r5)
            kotlinx.coroutines.flow.Flow r5 = r5.getData()
            com.byd.overseaappstore.module.ability.util.SettingUtil$getUseByWifiState$$inlined$map$1 r6 = new com.byd.overseaappstore.module.ability.util.SettingUtil$getUseByWifiState$$inlined$map$1
            r6.<init>()
            r0.c = r3
            java.lang.Object r6 = kotlinx.coroutines.flow.FlowKt.firstOrNull(r6, r0)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            if (r6 == 0) goto L52
            boolean r3 = r6.booleanValue()
        L52:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.SettingUtil.a(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean b(@NotNull Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new b(context, z, null), 1, null)).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull android.content.Context r5, boolean r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.byd.overseaappstore.module.ability.util.SettingUtil.c
            if (r0 == 0) goto L13
            r0 = r7
            com.byd.overseaappstore.module.ability.util.SettingUtil$c r0 = (com.byd.overseaappstore.module.ability.util.SettingUtil.c) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            com.byd.overseaappstore.module.ability.util.SettingUtil$c r0 = new com.byd.overseaappstore.module.ability.util.SettingUtil$c
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.datastore.core.DataStore r5 = spi.n4.a(r5)
            com.byd.overseaappstore.module.ability.util.SettingUtil$d r7 = new com.byd.overseaappstore.module.ability.util.SettingUtil$d
            r2 = 0
            r7.<init>(r6, r2)
            r0.c = r3
            java.lang.Object r7 = androidx.datastore.preferences.core.PreferencesKt.edit(r5, r7, r0)
            if (r7 != r1) goto L47
            return r1
        L47:
            androidx.datastore.preferences.core.Preferences r7 = (androidx.datastore.preferences.core.Preferences) r7
            androidx.datastore.preferences.core.Preferences$Key<java.lang.Boolean> r5 = spi.n4.c
            java.lang.Object r5 = r7.get(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            if (r5 == 0) goto L57
            boolean r3 = r5.booleanValue()
        L57:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.SettingUtil.c(android.content.Context, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
