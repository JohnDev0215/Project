package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PixelOffset implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("X")
    private Integer x = 0;

    @SerializedName("Y")
    private Integer y = 0;

    public PixelOffset() {
    }

    public PixelOffset(Integer num, Integer num2) {
        setX(num);
        setY(num2);
    }

    public Integer getX() {
        return this.x;
    }

    public PixelOffset setX(Integer num) {
        if (num == null) {
            throw new NullPointerException("x cannot be null");
        }
        this.x = num;
        return this;
    }

    public Integer getY() {
        return this.y;
    }

    public PixelOffset setY(Integer num) {
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
        PixelOffset pixelOffset = (PixelOffset) obj;
        return Objects.equals(this.x, pixelOffset.x) && Objects.equals(this.y, pixelOffset.y);
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
