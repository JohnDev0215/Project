package com.amazon.music.platform.aom.hints;

import com.amazon.music.platform.aom.Logger;
import com.amazon.music.platform.aom.hints.dae.DAE;
import com.amazon.music.platform.aom.hints.dae.EventType;
import com.amazon.music.platform.aom.hints.guiContext.GUIContext;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextNowPlayingItemType;
import com.amazon.music.platform.aom.hints.provider.GUIContextProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: AlexaContextualHintsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J)\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00172\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AlexaContextualHintsProvider;", "", "alexaHintsRepository", "Lcom/amazon/music/platform/aom/hints/AlexaHintsRepository;", "(Lcom/amazon/music/platform/aom/hints/AlexaHintsRepository;)V", "getConcreteHints", "", "", "templatedHints", "guiContext", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContext;", "limit", "", "getContextualHints", "guiContextProvider", "Lcom/amazon/music/platform/aom/hints/provider/GUIContextProvider;", "(Lcom/amazon/music/platform/aom/hints/provider/GUIContextProvider;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetadataKeyString", "guiMetadataKey", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContextMetadataKey;", "isDAEEqual", "", "input", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "resultEventType", "Lcom/amazon/music/platform/aom/hints/dae/EventType;", "shouldFetchHints", "dae", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AlexaContextualHintsProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private final AlexaHintsRepository alexaHintsRepository;

    /* JADX INFO: renamed from: com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getContextualHints$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlexaContextualHintsProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider", f = "AlexaContextualHintsProvider.kt", i = {0, 0, 0}, l = {41}, m = "getContextualHints", n = {"this", "guiContext", "limit"}, s = {"L$0", "L$1", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AlexaContextualHintsProvider.this.getContextualHints(null, 0, this);
        }
    }

    /* JADX INFO: compiled from: AlexaContextualHintsProvider.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AlexaContextualHintsProvider$Companion;", "", "()V", "DEFAULT_NUM_HINTS_TO_RETURN", "", "HINT_MAX_LEN", "TAG", "", "getTAG", "()Ljava/lang/String;", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AlexaContextualHintsProvider.TAG;
        }
    }

    public AlexaContextualHintsProvider(AlexaHintsRepository alexaHintsRepository) {
        Intrinsics.checkNotNullParameter(alexaHintsRepository, "alexaHintsRepository");
        this.alexaHintsRepository = alexaHintsRepository;
    }

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(AlexaContextualHintsProvider.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    public static /* synthetic */ Object getContextualHints$default(AlexaContextualHintsProvider alexaContextualHintsProvider, GUIContextProvider gUIContextProvider, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 4;
        }
        return alexaContextualHintsProvider.getContextualHints(gUIContextProvider, i, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getContextualHints(com.amazon.music.platform.aom.hints.provider.GUIContextProvider r5, int r6, kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getContextualHints$1 r0 = (com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getContextualHints$1 r0 = new com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getContextualHints$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            int r6 = r0.I$0
            java.lang.Object r5 = r0.L$1
            com.amazon.music.platform.aom.hints.guiContext.GUIContext r5 = (com.amazon.music.platform.aom.hints.guiContext.GUIContext) r5
            java.lang.Object r0 = r0.L$0
            com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider r0 = (com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L65
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.music.platform.aom.hints.guiContext.GUIContext r5 = r5.getGUIContext()
            com.amazon.music.platform.aom.hints.dae.DAEConstructorFactory$Companion r7 = com.amazon.music.platform.aom.hints.dae.DAEConstructorFactory.INSTANCE
            com.amazon.music.platform.aom.hints.dae.DAE r7 = r7.getDAE(r5)
            boolean r2 = r4.shouldFetchHints(r7, r5)
            if (r2 != 0) goto L53
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
            return r5
        L53:
            com.amazon.music.platform.aom.hints.AlexaHintsRepository r2 = r4.alexaHintsRepository
            r0.L$0 = r4
            r0.L$1 = r5
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r7 = r2.getTemplatedHints$MusicPlatformAOM_release(r7, r0)
            if (r7 != r1) goto L64
            return r1
        L64:
            r0 = r4
        L65:
            com.amazon.music.platform.aom.hints.AlexaHints r7 = (com.amazon.music.platform.aom.hints.AlexaHints) r7
            if (r7 == 0) goto La1
            java.util.List r7 = r7.getHints()
            if (r7 == 0) goto La1
            java.util.List r7 = kotlin.collections.CollectionsKt.shuffled(r7)
            if (r7 == 0) goto La1
            java.util.List r5 = r0.getConcreteHints(r7, r5, r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r5 = r5.iterator()
        L82:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto La5
            java.lang.Object r7 = r5.next()
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.length()
            r1 = 0
            if (r3 > r0) goto L9b
            r2 = 61
            if (r0 >= r2) goto L9b
            r1 = r3
        L9b:
            if (r1 == 0) goto L82
            r6.add(r7)
            goto L82
        La1:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        La5:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.getContextualHints(com.amazon.music.platform.aom.hints.provider.GUIContextProvider, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List<String> getConcreteHints(List<String> templatedHints, final GUIContext guiContext, int limit) {
        ArrayList arrayList = new ArrayList();
        for (final String str : templatedHints) {
            if (arrayList.size() == limit) {
                break;
            }
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            String strReplace = new Regex("\\{\\{.*?\\}\\}").replace(str, new Function1<MatchResult, CharSequence>() { // from class: com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getConcreteHints$concreteHint$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[PHI: r11
  0x002d: PHI (r11v13 java.lang.String) = (r11v7 java.lang.String), (r11v11 java.lang.String), (r11v17 java.lang.String) binds: [B:28:0x006e, B:19:0x004d, B:9:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.CharSequence invoke(kotlin.text.MatchResult r11) {
                    /*
                        r10 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                        java.lang.String r0 = r11.getValue()
                        com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider r1 = r10.this$0
                        com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey r2 = com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey.ARTIST_NAME
                        java.lang.String r1 = com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.access$getMetadataKeyString(r1, r2)
                        boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        java.lang.String r3 = ""
                        if (r1 == 0) goto L2f
                        com.amazon.music.platform.aom.hints.guiContext.GUIContext r11 = r2
                        if (r11 == 0) goto L9e
                        java.util.Map r11 = r11.getMetadata()
                        if (r11 == 0) goto L9e
                        java.lang.Object r11 = r11.get(r2)
                        java.lang.String r11 = (java.lang.String) r11
                        if (r11 != 0) goto L2d
                        goto L9e
                    L2d:
                        r3 = r11
                        goto L9e
                    L2f:
                        com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider r1 = r10.this$0
                        com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey r2 = com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey.PLAYLIST_NAME
                        java.lang.String r1 = com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.access$getMetadataKeyString(r1, r2)
                        boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        if (r1 == 0) goto L50
                        com.amazon.music.platform.aom.hints.guiContext.GUIContext r11 = r2
                        if (r11 == 0) goto L9e
                        java.util.Map r11 = r11.getMetadata()
                        if (r11 == 0) goto L9e
                        java.lang.Object r11 = r11.get(r2)
                        java.lang.String r11 = (java.lang.String) r11
                        if (r11 != 0) goto L2d
                        goto L9e
                    L50:
                        com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider r1 = r10.this$0
                        com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey r2 = com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey.GENRE_NAME
                        java.lang.String r1 = com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.access$getMetadataKeyString(r1, r2)
                        boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        if (r0 == 0) goto L71
                        com.amazon.music.platform.aom.hints.guiContext.GUIContext r11 = r2
                        if (r11 == 0) goto L9e
                        java.util.Map r11 = r11.getMetadata()
                        if (r11 == 0) goto L9e
                        java.lang.Object r11 = r11.get(r2)
                        java.lang.String r11 = (java.lang.String) r11
                        if (r11 != 0) goto L2d
                        goto L9e
                    L71:
                        com.amazon.music.platform.aom.Logger r4 = com.amazon.music.platform.aom.Logger.INSTANCE
                        com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$Companion r0 = com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider.INSTANCE
                        java.lang.String r5 = r0.getTAG()
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        r0.<init>()
                        java.lang.String r1 = "getConcreteHints : Unknown placeholder "
                        r0.append(r1)
                        java.lang.String r11 = r11.getValue()
                        r0.append(r11)
                        java.lang.String r11 = " found in templated hint "
                        r0.append(r11)
                        java.lang.String r11 = r3
                        r0.append(r11)
                        java.lang.String r6 = r0.toString()
                        r7 = 0
                        r8 = 4
                        r9 = 0
                        com.amazon.music.platform.aom.Logger.error$default(r4, r5, r6, r7, r8, r9)
                    L9e:
                        kotlin.jvm.internal.Ref$BooleanRef r11 = r4
                        int r0 = r3.length()
                        if (r0 <= 0) goto La8
                        r0 = 1
                        goto La9
                    La8:
                        r0 = 0
                    La9:
                        r11.element = r0
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider$getConcreteHints$concreteHint$1.invoke(kotlin.text.MatchResult):java.lang.CharSequence");
                }
            });
            if (booleanRef.element) {
                arrayList.add(strReplace);
            } else {
                Logger.error$default(Logger.INSTANCE, TAG, "getConcreteHints : Placeholder replacement not successful for templated hint " + str, null, 4, null);
            }
        }
        return arrayList;
    }

    private final boolean shouldFetchHints(DAE dae, GUIContext guiContext) {
        Map<GUIContextMetadataKey, String> metadata;
        if (isDAEEqual(dae, EventType.CAR_MODE)) {
            return false;
        }
        if (isDAEEqual(dae, EventType.NOW_PLAYING)) {
            return !Intrinsics.areEqual((guiContext == null || (metadata = guiContext.getMetadata()) == null) ? null : metadata.get(GUIContextMetadataKey.INTERNAL_NOW_PLAYING_ITEM_TYPE), GUIContextNowPlayingItemType.VIDEO.name());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMetadataKeyString(GUIContextMetadataKey guiMetadataKey) {
        return "{{" + guiMetadataKey.name() + "}}";
    }

    private final boolean isDAEEqual(DAE input, EventType resultEventType) {
        return Intrinsics.areEqual(input.getEventType(), resultEventType.name());
    }
}
