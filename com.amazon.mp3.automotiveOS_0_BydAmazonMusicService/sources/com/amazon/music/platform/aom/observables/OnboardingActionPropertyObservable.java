package com.amazon.music.platform.aom.observables;

import com.amazon.music.platform.aom.onboarding.OnboardingAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: OnboardingActionPropertyObservable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R+\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/aom/observables/OnboardingActionPropertyObservable;", "Lcom/amazon/music/platform/aom/observables/PropertyObservable;", "Lcom/amazon/music/platform/aom/onboarding/OnboardingAction;", "newAction", "", "updateValue", "<set-?>", "onboardingAction$delegate", "Lkotlin/properties/ReadWriteProperty;", "getOnboardingAction", "()Lcom/amazon/music/platform/aom/onboarding/OnboardingAction;", "setOnboardingAction", "(Lcom/amazon/music/platform/aom/onboarding/OnboardingAction;)V", "onboardingAction", "<init>", "()V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final class OnboardingActionPropertyObservable extends PropertyObservable<OnboardingAction> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(OnboardingActionPropertyObservable.class, "onboardingAction", "getOnboardingAction()Lcom/amazon/music/platform/aom/onboarding/OnboardingAction;", 0))};

    /* JADX INFO: renamed from: onboardingAction$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty onboardingAction;

    public OnboardingActionPropertyObservable() {
        Delegates delegates = Delegates.INSTANCE;
        final OnboardingAction onboardingAction = OnboardingAction.NONE;
        this.onboardingAction = new ObservableProperty<OnboardingAction>(onboardingAction) { // from class: com.amazon.music.platform.aom.observables.OnboardingActionPropertyObservable$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, OnboardingAction oldValue, OnboardingAction newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                this.notifyObservers(newValue);
            }
        };
    }

    private final void setOnboardingAction(OnboardingAction onboardingAction) {
        this.onboardingAction.setValue(this, $$delegatedProperties[0], onboardingAction);
    }

    public final void updateValue(OnboardingAction newAction) {
        Intrinsics.checkNotNullParameter(newAction, "newAction");
        setOnboardingAction(newAction);
    }
}
