package com.byd.ime.subtype;

import android.content.res.Configuration;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.RichInputMethodManager;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.byd.ime.subtype.model.SubtypeCacheModel;
import com.byd.ime.subtype.model.SubtypeConfigData;
import com.byd.ime.subtype.model.SubtypeItem;
import com.byd.ime.subtype.model.SubtypeStoreModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;

/* JADX INFO: compiled from: SubtypeManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0006\u0010\f\u001a\u00020\tJ\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u000bJ\b\u0010\u0013\u001a\u00020\u000bH\u0002J\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0004J$\u0010\u0019\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0006H\u0002J\u000e\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u000bJ\u0006\u0010!\u001a\u00020\u000bJ\b\u0010\"\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/byd/ime/subtype/SubtypeManager;", "", "()V", "mLatinIME", "Lcom/android/inputmethod/latin/LatinIME;", "mSelectList", "", "Lcom/byd/ime/subtype/model/SubtypeItem;", "mSubtypeCacheModel", "Lcom/byd/ime/subtype/model/SubtypeCacheModel;", "firstLoadDataFromStorage", "", "getCacheSubtypeModel", "loadAllDataAndSelectFromStore", "loadSubtypeItemFromIME", "onConfigurationChanged", "conf", "Landroid/content/res/Configuration;", "onCreate", "orderAndSaveSelectAndSystemLanguageDataToStore", "setCurrentSubtype", SubtypeConfigData.JSON_KEY_SUBTYPE_LOCAL, "", "setLatinIME", "latinIME", "sortAllSubtypeItemList", "allSubtypeItemList", "orderList", "Lcom/byd/ime/subtype/model/SubtypeConfigData;", "switchLanguage", "subtype", "Landroid/view/inputmethod/InputMethodSubtype;", "switchToCurrentSubtype", "switchToNextInputSubtype", "updateDataWhenStemLanguageChange", "Companion", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SubtypeManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<SubtypeManager>() { // from class: com.byd.ime.subtype.SubtypeManager$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SubtypeManager invoke() {
            return new SubtypeManager();
        }
    });
    private LatinIME mLatinIME;
    private SubtypeCacheModel mSubtypeCacheModel = new SubtypeCacheModel();
    private List<SubtypeItem> mSelectList = new ArrayList();

    public static final SubtypeManager getInstance() {
        return INSTANCE.getInstance();
    }

    /* JADX INFO: compiled from: SubtypeManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/byd/ime/subtype/SubtypeManager$Companion;", "", "()V", "instance", "Lcom/byd/ime/subtype/SubtypeManager;", "getInstance$annotations", "getInstance", "()Lcom/byd/ime/subtype/SubtypeManager;", "instance$delegate", "Lkotlin/Lazy;", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final SubtypeManager getInstance() {
            return (SubtypeManager) SubtypeManager.instance$delegate.getValue();
        }
    }

    public final void setLatinIME(LatinIME latinIME) {
        Intrinsics.checkNotNullParameter(latinIME, "latinIME");
        this.mLatinIME = latinIME;
    }

    public final void onCreate() {
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        String string = Locale.getDefault().toString();
        Intrinsics.checkNotNullExpressionValue(string, "getDefault().toString()");
        subtypeCacheModel.setMSystemLanguage(string);
        SubtypeDebugger.logI("onCreate mSystemLanguage=" + this.mSubtypeCacheModel.getMSystemLanguage());
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        LatinIME latinIME = this.mLatinIME;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        if (companion.isFirstInit(latinIME)) {
            firstLoadDataFromStorage();
        } else {
            loadAllDataAndSelectFromStore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadSubtypeItemFromIME() throws JSONException {
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        LatinIME latinIME = this.mLatinIME;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        List<SubtypeConfigData> jsonToSubtypeData = companion.parseJsonToSubtypeData(latinIME);
        InputMethodInfo inputMethodInfoOfThisIme = RichInputMethodManager.getInstance().getInputMethodInfoOfThisIme();
        int subtypeCount = inputMethodInfoOfThisIme.getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            InputMethodSubtype subtypeAt = inputMethodInfoOfThisIme.getSubtypeAt(i);
            Intrinsics.checkNotNullExpressionValue(subtypeAt, "imi.getSubtypeAt(i)");
            RichInputMethodSubtype richInputMethodSubtype = new RichInputMethodSubtype(subtypeAt);
            String subtypeLocal = subtypeAt.getLocale();
            SubtypeConfigData.Companion companion2 = SubtypeConfigData.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(subtypeLocal, "subtypeLocal");
            SubtypeConfigData subtypeConfigDataIndexItemBySubtypeLocal = companion2.indexItemBySubtypeLocal(subtypeLocal, jsonToSubtypeData);
            SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal = this.mSubtypeCacheModel.indexSubTypeItemFromAllSubtypeBySubtypeLocal(subtypeLocal);
            if (subtypeConfigDataIndexItemBySubtypeLocal != null && subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal == null) {
                SubtypeItem subtypeItem = new SubtypeItem(richInputMethodSubtype);
                subtypeItem.setSubtypeConfigData(subtypeConfigDataIndexItemBySubtypeLocal);
                this.mSubtypeCacheModel.addSubTypeItemToAllSubtype(subtypeItem);
            }
        }
        sortAllSubtypeItemList(this.mSubtypeCacheModel.getAllSubtypeItemList(), jsonToSubtypeData);
    }

    private final void sortAllSubtypeItemList(List<SubtypeItem> allSubtypeItemList, List<SubtypeConfigData> orderList) {
        for (int size = orderList.size() - 1; -1 < size; size--) {
            SubtypeConfigData subtypeConfigData = orderList.get(size);
            int size2 = allSubtypeItemList.size();
            int i = 0;
            while (true) {
                if (i < size2) {
                    if (Intrinsics.areEqual(subtypeConfigData.getSubtypeLocal(), allSubtypeItemList.get(i).getLocal())) {
                        allSubtypeItemList.add(0, allSubtypeItemList.remove(i));
                        break;
                    }
                    i++;
                }
            }
        }
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal = subtypeCacheModel.indexSubTypeItemFromAllSubtypeBySubtypeLocal(subtypeCacheModel.getMSystemLanguage());
        if (Intrinsics.areEqual(subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal, this.mSubtypeCacheModel.getDefaultSubtypeEN()) || subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal == null) {
            return;
        }
        allSubtypeItemList.remove(subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal);
        allSubtypeItemList.add(1, subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal);
    }

    private final void firstLoadDataFromStorage() {
        SubtypeDebugger.logI("firstLoadDataFromStorage");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(new CoroutineName("SubtypeModel")), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$firstLoadDataFromStorage$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubtypeManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$firstLoadDataFromStorage$1", f = "SubtypeManager.kt", i = {}, l = {101}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SubtypeManager.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$firstLoadDataFromStorage$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SubtypeManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$firstLoadDataFromStorage$1$1", f = "SubtypeManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SubtypeManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00051(SubtypeManager subtypeManager, Continuation<? super C00051> continuation) {
                super(2, continuation);
                this.this$0 = subtypeManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00051(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws JSONException {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.loadSubtypeItemFromIME();
                SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage = this.this$0.mSubtypeCacheModel.indexSubTypeItemFromAllSubtypeByLanguage(this.this$0.mSubtypeCacheModel.getMSystemLanguage());
                if (subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage != null) {
                    this.this$0.mSubtypeCacheModel.addSubTypeItemToSelect(subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage);
                }
                SubtypeItem defaultSubtypeEN = this.this$0.mSubtypeCacheModel.getDefaultSubtypeEN();
                if (defaultSubtypeEN != null) {
                    this.this$0.mSubtypeCacheModel.addSubTypeItemToSelect(defaultSubtypeEN);
                }
                if (subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage != null) {
                    this.this$0.setCurrentSubtype(subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage.getLocal());
                } else if (defaultSubtypeEN != null) {
                    this.this$0.setCurrentSubtype(defaultSubtypeEN.getLocal());
                }
                this.this$0.orderAndSaveSelectAndSystemLanguageDataToStore();
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C00051(SubtypeManager.this, null), this) == coroutine_suspended) {
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
    }

    /* JADX INFO: renamed from: getCacheSubtypeModel, reason: from getter */
    public final SubtypeCacheModel getMSubtypeCacheModel() {
        return this.mSubtypeCacheModel;
    }

    private final void loadAllDataAndSelectFromStore() {
        String string = Locale.getDefault().toString();
        Intrinsics.checkNotNullExpressionValue(string, "getDefault().toString()");
        this.mSubtypeCacheModel.setMSystemLanguage(string);
        SubtypeDebugger.logI("loadAllDataAndSelectFromStore language=" + string);
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        LatinIME latinIME = this.mLatinIME;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        if (Intrinsics.areEqual(string, companion.getSystemLanguageFromStore(latinIME))) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(new CoroutineName("SubtypeModel")), null, null, new C00131(null), 3, null);
        } else {
            updateDataWhenStemLanguageChange();
        }
    }

    /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$loadAllDataAndSelectFromStore$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubtypeManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$loadAllDataAndSelectFromStore$1", f = "SubtypeManager.kt", i = {}, l = {143}, m = "invokeSuspend", n = {}, s = {})
    static final class C00131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00131(Continuation<? super C00131> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SubtypeManager.this.new C00131(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$loadAllDataAndSelectFromStore$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SubtypeManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$loadAllDataAndSelectFromStore$1$1", f = "SubtypeManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SubtypeManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00061(SubtypeManager subtypeManager, Continuation<? super C00061> continuation) {
                super(2, continuation);
                this.this$0 = subtypeManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00061(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws JSONException {
                SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.loadSubtypeItemFromIME();
                SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
                LatinIME latinIME = this.this$0.mLatinIME;
                LatinIME latinIME2 = null;
                if (latinIME == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
                    latinIME = null;
                }
                List<String> userSelectFromStore = companion.getUserSelectFromStore(latinIME);
                int size = userSelectFromStore.size();
                for (int i = 0; i < size; i++) {
                    SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal2 = this.this$0.mSubtypeCacheModel.indexSubTypeItemFromAllSubtypeBySubtypeLocal(userSelectFromStore.get(i));
                    if (subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal2 != null) {
                        this.this$0.mSubtypeCacheModel.addSubTypeItemToSelect(subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal2);
                    }
                }
                SubtypeStoreModel.Companion companion2 = SubtypeStoreModel.INSTANCE;
                LatinIME latinIME3 = this.this$0.mLatinIME;
                if (latinIME3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
                } else {
                    latinIME2 = latinIME3;
                }
                String subtypeLocalFromStore = companion2.getSubtypeLocalFromStore(latinIME2);
                if (subtypeLocalFromStore != null && (subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal = this.this$0.mSubtypeCacheModel.indexSubTypeItemFromAllSubtypeBySubtypeLocal(subtypeLocalFromStore)) != null) {
                    this.this$0.setCurrentSubtype(subtypeItemIndexSubTypeItemFromAllSubtypeBySubtypeLocal.getLocal());
                }
                this.this$0.orderAndSaveSelectAndSystemLanguageDataToStore();
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C00061(SubtypeManager.this, null), this) == coroutine_suspended) {
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
    }

    private final void updateDataWhenStemLanguageChange() {
        SubtypeDebugger.logI("updateDataWhenStemLanguageChange");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(new CoroutineName("SubtypeModel")), null, null, new C00141(null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$updateDataWhenStemLanguageChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubtypeManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$updateDataWhenStemLanguageChange$1", f = "SubtypeManager.kt", i = {}, l = {179}, m = "invokeSuspend", n = {}, s = {})
    static final class C00141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00141(Continuation<? super C00141> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SubtypeManager.this.new C00141(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.byd.ime.subtype.SubtypeManager$updateDataWhenStemLanguageChange$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SubtypeManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
        @DebugMetadata(c = "com.byd.ime.subtype.SubtypeManager$updateDataWhenStemLanguageChange$1$1", f = "SubtypeManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SubtypeManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00071(SubtypeManager subtypeManager, Continuation<? super C00071> continuation) {
                super(2, continuation);
                this.this$0 = subtypeManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00071(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws JSONException {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.mSubtypeCacheModel.clearData();
                this.this$0.loadSubtypeItemFromIME();
                SubtypeItem subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage = this.this$0.mSubtypeCacheModel.indexSubTypeItemFromAllSubtypeByLanguage(this.this$0.mSubtypeCacheModel.getMSystemLanguage());
                if (subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage != null) {
                    this.this$0.mSubtypeCacheModel.addSubTypeItemToSelect(subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage);
                }
                SubtypeItem defaultSubtypeEN = this.this$0.mSubtypeCacheModel.getDefaultSubtypeEN();
                if (defaultSubtypeEN != null) {
                    this.this$0.mSubtypeCacheModel.addSubTypeItemToSelect(defaultSubtypeEN);
                }
                if (subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage != null) {
                    this.this$0.setCurrentSubtype(subtypeItemIndexSubTypeItemFromAllSubtypeByLanguage.getLocal());
                } else if (defaultSubtypeEN != null) {
                    this.this$0.setCurrentSubtype(defaultSubtypeEN.getLocal());
                }
                this.this$0.orderAndSaveSelectAndSystemLanguageDataToStore();
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C00071(SubtypeManager.this, null), this) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void orderAndSaveSelectAndSystemLanguageDataToStore() {
        this.mSubtypeCacheModel.sortSelectItemListListByAllOrder();
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        String mSystemLanguage = this.mSubtypeCacheModel.getMSystemLanguage();
        LatinIME latinIME = this.mLatinIME;
        LatinIME latinIME2 = null;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        companion.saveSystemLanguage(mSystemLanguage, latinIME);
        SubtypeStoreModel.Companion companion2 = SubtypeStoreModel.INSTANCE;
        List<SubtypeItem> selectItemList = this.mSubtypeCacheModel.getSelectItemList();
        LatinIME latinIME3 = this.mLatinIME;
        if (latinIME3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
        } else {
            latinIME2 = latinIME3;
        }
        companion2.saveUserSelectItemToStore(selectItemList, latinIME2);
    }

    public final void onConfigurationChanged(Configuration conf) {
        Intrinsics.checkNotNullParameter(conf, "conf");
        SubtypeDebugger.logI("onConfigurationChanged local =" + conf.getLocales().get(0));
        String string = conf.getLocales().get(0).toString();
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        LatinIME latinIME = this.mLatinIME;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        if (Intrinsics.areEqual(string, companion.getSystemLanguageFromStore(latinIME))) {
            return;
        }
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        String string2 = Locale.getDefault().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "getDefault().toString()");
        subtypeCacheModel.setMSystemLanguage(string2);
        SubtypeDebugger.logI("mSystemLanguage =" + this.mSubtypeCacheModel.getMSystemLanguage());
        updateDataWhenStemLanguageChange();
    }

    public final void switchToNextInputSubtype() {
        InputMethodSubtype rawSubtype = this.mSubtypeCacheModel.getNextSelectItem().getMRichInputMethodSubtype().getRawSubtype();
        Intrinsics.checkNotNullExpressionValue(rawSubtype, "mSubtypeCacheModel.getNe…tMethodSubtype.rawSubtype");
        switchLanguage(rawSubtype);
    }

    public final void switchToCurrentSubtype() {
        SubtypeItem currentSubtypeItem = this.mSubtypeCacheModel.getCurrentSubtypeItem();
        if (currentSubtypeItem != null) {
            InputMethodSubtype rawSubtype = currentSubtypeItem.getMRichInputMethodSubtype().getRawSubtype();
            Intrinsics.checkNotNullExpressionValue(rawSubtype, "subtypeItem.mRichInputMethodSubtype.rawSubtype");
            switchLanguage(rawSubtype);
        }
    }

    public final void setCurrentSubtype(String subtypeLocal) {
        Intrinsics.checkNotNullParameter(subtypeLocal, "subtypeLocal");
        this.mSubtypeCacheModel.setMCurrentSubtypeLocal(subtypeLocal);
        SubtypeDebugger.logI("setCurrentSubtype mCurrentSubtypeLanguage=" + this.mSubtypeCacheModel.getMCurrentSubtypeLocal());
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        String mCurrentSubtypeLocal = this.mSubtypeCacheModel.getMCurrentSubtypeLocal();
        LatinIME latinIME = this.mLatinIME;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        companion.saveSubtypeLocalToStore(mCurrentSubtypeLocal, latinIME);
    }

    public final void switchLanguage(InputMethodSubtype subtype) {
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        LatinIME latinIME = this.mLatinIME;
        LatinIME latinIME2 = null;
        if (latinIME == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
            latinIME = null;
        }
        latinIME.switchLanguage(subtype);
        SubtypeCacheModel subtypeCacheModel = this.mSubtypeCacheModel;
        String locale = subtype.getLocale();
        Intrinsics.checkNotNullExpressionValue(locale, "subtype.locale");
        subtypeCacheModel.setMCurrentSubtypeLocal(locale);
        SubtypeDebugger.logI("switchLanguage mCurrentSubtypeLanguage=" + this.mSubtypeCacheModel.getMCurrentSubtypeLocal());
        SubtypeStoreModel.Companion companion = SubtypeStoreModel.INSTANCE;
        String mCurrentSubtypeLocal = this.mSubtypeCacheModel.getMCurrentSubtypeLocal();
        LatinIME latinIME3 = this.mLatinIME;
        if (latinIME3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLatinIME");
        } else {
            latinIME2 = latinIME3;
        }
        companion.saveSubtypeLocalToStore(mCurrentSubtypeLocal, latinIME2);
    }
}
