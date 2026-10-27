package com.google.android.material.slider;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class BaseSlider$SliderState extends View.BaseSavedState {
    public static final Parcelable.Creator<BaseSlider$SliderState> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f4678e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f4679g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    ArrayList<Float> f4680h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f4681i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f4682j;

    static class a implements Parcelable.Creator<BaseSlider$SliderState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BaseSlider$SliderState createFromParcel(Parcel parcel) {
            return new BaseSlider$SliderState(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BaseSlider$SliderState[] newArray(int i7) {
            return new BaseSlider$SliderState[i7];
        }
    }

    /* synthetic */ BaseSlider$SliderState(Parcel parcel, com.google.android.material.slider.a aVar) {
        this(parcel);
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeFloat(this.f4678e);
        parcel.writeFloat(this.f4679g);
        parcel.writeList(this.f4680h);
        parcel.writeFloat(this.f4681i);
        parcel.writeBooleanArray(new boolean[]{this.f4682j});
    }

    private BaseSlider$SliderState(Parcel parcel) {
        super(parcel);
        this.f4678e = parcel.readFloat();
        this.f4679g = parcel.readFloat();
        ArrayList<Float> arrayList = new ArrayList<>();
        this.f4680h = arrayList;
        parcel.readList(arrayList, Float.class.getClassLoader());
        this.f4681i = parcel.readFloat();
        this.f4682j = parcel.createBooleanArray()[0];
    }
}
