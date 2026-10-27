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

    public byte[] getImage() {
        return this.mImage;
    }

    public int getEvent() {
        return this.mEvent;
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

    public String getRoad() {
        return this.mRoad;
    }

    public void setEvent(int i) {
        this.mEvent = i;
    }

    public void setImage(byte[] bArr) {
        this.mImage = bArr;
    }

    public void setRoad(String str) {
        this.mRoad = str;
    }

    public void setTurnAngle(int i) {
        this.mTurnAngle = i;
    }

    public void setTurnNumber(int i) {
        this.mTurnNumber = i;
    }

    public void setTurnSide(int i) {
        this.mTurnSide = i;
    }

    public String toString() {
        return this.mRoad + " " + this.mTurnSide + " " + this.mEvent + " " + this.mImage + " " + this.mTurnAngle + " " + this.mTurnNumber;
    }
}
