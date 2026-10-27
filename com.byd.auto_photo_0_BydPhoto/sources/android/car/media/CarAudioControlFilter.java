package android.car.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CarAudioControlFilter implements Parcelable {
    public static final Parcelable.Creator<CarAudioControlFilter> CREATOR = new Parcelable.Creator<CarAudioControlFilter>() { // from class: android.car.media.CarAudioControlFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarAudioControlFilter createFromParcel(Parcel parcel) {
            return new CarAudioControlFilter(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarAudioControlFilter[] newArray(int i) {
            return new CarAudioControlFilter[i];
        }
    };
    private CopyOnWriteArrayList<Integer> mFilters;

    CarAudioControlFilter() {
        this.mFilters = new CopyOnWriteArrayList<>();
    }

    CarAudioControlFilter(Parcel parcel) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.mFilters = copyOnWriteArrayList;
        parcel.readList(copyOnWriteArrayList, Integer.class.getClassLoader());
    }

    public boolean add(int i) {
        if (contains(i)) {
            return false;
        }
        return this.mFilters.add(Integer.valueOf(i));
    }

    public void clear() {
        this.mFilters.clear();
    }

    public boolean contains(int i) {
        return this.mFilters.contains(Integer.valueOf(i));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int get(int i) {
        return this.mFilters.get(i).intValue();
    }

    public int getFilterCount() {
        return this.mFilters.size();
    }

    public boolean isEmpty() {
        return this.mFilters.isEmpty();
    }

    public boolean remove(int i) {
        return this.mFilters.remove(Integer.valueOf(i));
    }

    public String toString() {
        return "CarAudioControlFilter " + this.mFilters.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mFilters);
    }
}
