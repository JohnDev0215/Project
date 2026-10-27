package ts.car.service.carlife.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CarLifeConfig implements Parcelable {
    public static final String TAG = CarLifeConfig.class.getSimpleName();
    private static int mSurfaceViewWidth = DefaultValue.SURFACE_VIEW_WIDTH;
    private static int mSurfaceViewHeight = 1080;
    private static int mSurfaceViewFrameRate = 30;
    public static final Parcelable.Creator<CarLifeConfig> CREATOR = new Parcelable.Creator<CarLifeConfig>() { // from class: ts.car.service.carlife.data.CarLifeConfig.1
        @Override // android.os.Parcelable.Creator
        public CarLifeConfig createFromParcel(Parcel source) {
            CarLifeConfig info = new CarLifeConfig(source);
            return info;
        }

        @Override // android.os.Parcelable.Creator
        public CarLifeConfig[] newArray(int size) {
            return new CarLifeConfig[size];
        }
    };

    private static final class DefaultValue {
        public static final int SURFACE_VIEW_FRAMERATE = 30;
        public static final int SURFACE_VIEW_HEIGHT = 1080;
        public static final int SURFACE_VIEW_WIDTH = 1920;

        private DefaultValue() {
        }
    }

    public int getSurfaceViewWidth() {
        if (mSurfaceViewWidth <= 0) {
            mSurfaceViewWidth = DefaultValue.SURFACE_VIEW_WIDTH;
        }
        return mSurfaceViewWidth;
    }

    public int getSurfaceViewHeight() {
        if (mSurfaceViewHeight <= 0) {
            mSurfaceViewHeight = 1080;
        }
        return mSurfaceViewHeight;
    }

    public int getSurfaceViewFrameRate() {
        if (mSurfaceViewFrameRate <= 0) {
            mSurfaceViewFrameRate = 30;
        }
        return mSurfaceViewFrameRate;
    }

    public CarLifeConfig() {
        initData();
    }

    public CarLifeConfig(Builder builder) {
        if (builder == null) {
            return;
        }
        initData();
        mSurfaceViewWidth = Builder.mSurfaceViewWidth;
        mSurfaceViewHeight = Builder.mSurfaceViewHeight;
        mSurfaceViewFrameRate = Builder.mSurfaceViewFrameRate;
    }

    public CarLifeConfig(Parcel in) {
        if (in == null) {
            return;
        }
        initData();
        mSurfaceViewWidth = in.readInt();
        mSurfaceViewHeight = in.readInt();
        mSurfaceViewFrameRate = in.readInt();
    }

    public void copy(CarLifeConfig config) {
        if (config == null) {
            return;
        }
        initData();
        mSurfaceViewWidth = mSurfaceViewWidth;
        mSurfaceViewHeight = mSurfaceViewHeight;
        mSurfaceViewFrameRate = mSurfaceViewFrameRate;
    }

    private void initData() {
        mSurfaceViewWidth = DefaultValue.SURFACE_VIEW_WIDTH;
        mSurfaceViewHeight = 1080;
        mSurfaceViewFrameRate = 30;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSurfaceViewWidth);
        dest.writeInt(mSurfaceViewHeight);
        dest.writeInt(mSurfaceViewFrameRate);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CarLifeConfig{ ");
        sb.append("mSurfaceViewWidth=");
        sb.append(mSurfaceViewWidth);
        sb.append(" ");
        sb.append("mSurfaceViewHeight=");
        sb.append(mSurfaceViewHeight);
        sb.append(" ");
        sb.append("mSurfaceViewFrameRate=");
        sb.append(mSurfaceViewFrameRate);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }

    public static class Builder {
        private static int mSurfaceViewWidth = 0;
        private static int mSurfaceViewHeight = 0;
        private static int mSurfaceViewFrameRate = 0;

        public CarLifeConfig build() {
            return new CarLifeConfig(this);
        }

        public Builder configSurfaceViewWidth(int width) {
            mSurfaceViewWidth = width;
            return this;
        }

        public Builder configSurfaceViewHeight(int height) {
            mSurfaceViewHeight = height;
            return this;
        }

        public Builder configSurfaceViewFrameRate(int frameRate) {
            mSurfaceViewFrameRate = frameRate;
            return this;
        }
    }
}
