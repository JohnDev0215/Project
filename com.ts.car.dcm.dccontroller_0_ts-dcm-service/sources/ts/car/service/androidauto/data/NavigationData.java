package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes.dex */
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

    public void setEvent(int event) {
        this.mEvent = event;
    }

    public void setImage(byte[] image) {
        this.mImage = image;
    }

    public void setRoad(String road) {
        this.mRoad = road;
    }

    public void setTurnAngle(int turnAngle) {
        this.mTurnAngle = turnAngle;
    }

    public void setTurnNumber(int turnNumber) {
        this.mTurnNumber = turnNumber;
    }

    public void setTurnSide(int turnSide) {
        this.mTurnSide = turnSide;
    }

    public String toString() {
        return this.mRoad + " " + this.mTurnSide + " " + this.mEvent + " " + this.mImage + " " + this.mTurnAngle + " " + this.mTurnNumber;
    }
}
