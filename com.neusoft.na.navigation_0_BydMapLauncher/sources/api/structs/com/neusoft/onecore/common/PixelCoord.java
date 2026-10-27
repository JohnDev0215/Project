package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PixelCoord implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("X")
    private Integer x = 0;

    @SerializedName("Y")
    private Integer y = 0;

    public PixelCoord() {
    }

    public PixelCoord(Integer num, Integer num2) {
        setX(num);
        setY(num2);
    }

    public Integer getX() {
        return this.x;
    }

    public PixelCoord setX(Integer num) {
        if (num == null) {
            throw new NullPointerException("x cannot be null");
        }
        this.x = num;
        return this;
    }

    public Integer getY() {
        return this.y;
    }

    public PixelCoord setY(Integer num) {
        if (num == null) {
            throw new NullPointerException("y cannot be null");
        }
        this.y = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PixelCoord pixelCoord = (PixelCoord) obj;
        return Objects.equals(this.x, pixelCoord.x) && Objects.equals(this.y, pixelCoord.y);
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
