package com.amazon.music.voice.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ControllerTransaction implements Parcelable {
    private final ViewController controller;
    private final ControllerTransition popTransition;
    private final ControllerTransition pushTransition;
    private final String tag;
    private static final Logger LOG = LoggerFactory.getLogger(ControllerTransaction.class.getSimpleName());
    public static final Parcelable.Creator<ControllerTransaction> CREATOR = new Parcelable.Creator<ControllerTransaction>() { // from class: com.amazon.music.voice.ui.ControllerTransaction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ControllerTransaction createFromParcel(Parcel parcel) {
            return new ControllerTransaction(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ControllerTransaction[] newArray(int i) {
            return new ControllerTransaction[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ControllerTransaction(@NonNull ViewController viewController, @Nullable ControllerTransition controllerTransition, @Nullable ControllerTransition controllerTransition2, @Nullable String str) {
        this.controller = viewController;
        this.popTransition = controllerTransition;
        this.pushTransition = controllerTransition2;
        this.tag = str;
    }

    ControllerTransaction(Parcel parcel) {
        try {
            ViewController viewController = (ViewController) Class.forName(parcel.readString()).newInstance();
            this.controller = viewController;
            ClassLoader classLoader = ControllerTransaction.class.getClassLoader();
            viewController.restoreState(parcel.readBundle(classLoader));
            this.popTransition = (ControllerTransition) parcel.readParcelable(classLoader);
            this.pushTransition = (ControllerTransition) parcel.readParcelable(classLoader);
            this.tag = parcel.readString();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOG.error(e.toString() + " when calling constructor from a Parcel.", e);
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public ViewController getController() {
        return this.controller;
    }

    @Nullable
    public ControllerTransition getPopTransition() {
        return this.popTransition;
    }

    @Nullable
    public ControllerTransition getPushTransition() {
        return this.pushTransition;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.controller.getClass().getName());
        parcel.writeBundle(this.controller.saveState());
        parcel.writeParcelable(this.popTransition, i);
        parcel.writeParcelable(this.pushTransition, i);
        parcel.writeString(this.tag);
    }
}
