package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Color implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName("Red")
    private Integer red = 0;

    @SerializedName("Green")
    private Integer green = 0;

    @SerializedName("Blue")
    private Integer blue = 0;

    @SerializedName("Alpha")
    private Integer alpha = 0;

    public Color() {
    }

    public Color(Integer num, Integer num2, Integer num3, Integer num4) {
        setRed(num);
        setGreen(num2);
        setBlue(num3);
        setAlpha(num4);
    }

    public Integer getRed() {
        return this.red;
    }

    public Color setRed(Integer num) {
        if (num == null) {
            throw new NullPointerException("red cannot be null");
        }
        this.red = num;
        return this;
    }

    public Integer getGreen() {
        return this.green;
    }

    public Color setGreen(Integer num) {
        if (num == null) {
            throw new NullPointerException("green cannot be null");
        }
        this.green = num;
        return this;
    }

    public Integer getBlue() {
        return this.blue;
    }

    public Color setBlue(Integer num) {
        if (num == null) {
            throw new NullPointerException("blue cannot be null");
        }
        this.blue = num;
        return this;
    }

    public Integer getAlpha() {
        return this.alpha;
    }

    public Color setAlpha(Integer num) {
        if (num == null) {
            throw new NullPointerException("alpha cannot be null");
        }
        this.alpha = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Color color = (Color) obj;
        return Objects.equals(this.red, color.red) && Objects.equals(this.green, color.green) && Objects.equals(this.blue, color.blue) && Objects.equals(this.alpha, color.alpha);
    }

    public int hashCode() {
        return Objects.hash(this.red, this.green, this.blue, this.alpha);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
