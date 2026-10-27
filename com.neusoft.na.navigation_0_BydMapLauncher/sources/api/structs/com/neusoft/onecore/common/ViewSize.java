package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ViewSize implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("HorizontalSize")
    private Integer horizontalSize = 0;

    @SerializedName("VerticalSize")
    private Integer verticalSize = 0;

    public ViewSize() {
    }

    public ViewSize(Integer num, Integer num2) {
        setHorizontalSize(num);
        setVerticalSize(num2);
    }

    public Integer getHorizontalSize() {
        return this.horizontalSize;
    }

    public ViewSize setHorizontalSize(Integer num) {
        if (num == null) {
            throw new NullPointerException("horizontalSize cannot be null");
        }
        this.horizontalSize = num;
        return this;
    }

    public Integer getVerticalSize() {
        return this.verticalSize;
    }

    public ViewSize setVerticalSize(Integer num) {
        if (num == null) {
            throw new NullPointerException("verticalSize cannot be null");
        }
        this.verticalSize = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ViewSize viewSize = (ViewSize) obj;
        return Objects.equals(this.horizontalSize, viewSize.horizontalSize) && Objects.equals(this.verticalSize, viewSize.verticalSize);
    }

    public int hashCode() {
        return Objects.hash(this.horizontalSize, this.verticalSize);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
