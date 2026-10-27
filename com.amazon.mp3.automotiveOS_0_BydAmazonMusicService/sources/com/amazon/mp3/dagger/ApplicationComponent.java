package com.amazon.mp3.dagger;

import android.app.Application;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.init.CarDetectionLaunchInitializationTask;
import com.amazon.mp3.starlight.subcomponent.NowPlayingComponent;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import kotlin.Metadata;

/* JADX INFO: compiled from: ApplicationComponent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH&¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/dagger/ApplicationComponent;", "Ldagger/android/AndroidInjector;", "Ldagger/android/support/DaggerApplication;", "Lcom/amazon/mp3/AmazonApplication;", "application", "", "inject", "Lcom/amazon/mp3/starlight/subcomponent/NowPlayingComponent$Factory;", "nowPlayingComponent", "Lcom/amazon/mp3/init/CarDetectionLaunchInitializationTask;", "task", "injectCarDetection", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    /* JADX INFO: compiled from: ApplicationComponent.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/dagger/ApplicationComponent$Builder;", "", "application", "Landroid/app/Application;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/mp3/dagger/ApplicationComponent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Builder {
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(AmazonApplication application);

    void injectCarDetection(CarDetectionLaunchInitializationTask task);

    NowPlayingComponent.Factory nowPlayingComponent();
}
