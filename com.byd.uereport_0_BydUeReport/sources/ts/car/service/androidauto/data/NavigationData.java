package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class NavigationData {
    private int mEvent;
    private byte[] mImage;
    private String mRoad;
    private int mTurnAngle;
    private int mTurnNumber;
    private int mTurnSide;

    public int getEvent() {
        return this.mEvent;
    }

    public byte[] getImage() {
        return this.mImage;
    }

    public String getRoad() {
        return this.mRoad;
    }

    public int getTurnAngle() {
        return this.mTurnAngle;
    }

    public int getTurnNumber() {
        return this.mTurnNumber;
    }

    public int getTurnSide() {
        return this.mTurnSide;
    }

    public void setEvent(int i8) {
        this.mEvent = i8;
    }

    public void setImage(byte[] bArr) {
        this.mImage = bArr;
    }

    public void setRoad(String str) {
        this.mRoad = str;
    }

    public void setTurnAngle(int i8) {
        this.mTurnAngle = i8;
    }

    public void setTurnNumber(int i8) {
        this.mTurnNumber = i8;
    }

    public void setTurnSide(int i8) {
        this.mTurnSide = i8;
    }

    public String toString() {
        return this.mRoad + " " + this.mTurnSide + " " + this.mEvent + " " + this.mImage + " " + this.mTurnAngle + " " + this.mTurnNumber;
    }
}
