package com.amazon.mp3.init;

import com.amazon.mp3.auto.carmode.detection.CarDetector;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class CarDetectionLaunchInitializationTask_MembersInjector implements MembersInjector<CarDetectionLaunchInitializationTask> {
    public static void injectCarDetector(CarDetectionLaunchInitializationTask instance, CarDetector carDetector) {
        instance.carDetector = carDetector;
    }
}
