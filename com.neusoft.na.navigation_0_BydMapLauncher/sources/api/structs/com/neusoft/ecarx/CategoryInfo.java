package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912130;

    @SerializedName("CategoryName")
    private String categoryName = "";

    @SerializedName("CategoryId")
    private Integer categoryId = 0;

    @SerializedName("CategoryLevel")
    private Integer categoryLevel = 0;

    public CategoryInfo() {
    }

    public CategoryInfo(String str, Integer num, Integer num2) {
        setCategoryName(str);
        setCategoryId(num);
        setCategoryLevel(num2);
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public CategoryInfo setCategoryName(String str) {
        if (str == null) {
            throw new NullPointerException("categoryName cannot be null");
        }
        this.categoryName = str;
        return this;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public CategoryInfo setCategoryId(Integer num) {
        if (num == null) {
            throw new NullPointerException("categoryId cannot be null");
        }
        this.categoryId = num;
        return this;
    }

    public Integer getCategoryLevel() {
        return this.categoryLevel;
    }

    public CategoryInfo setCategoryLevel(Integer num) {
        if (num == null) {
            throw new NullPointerException("categoryLevel cannot be null");
        }
        this.categoryLevel = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CategoryInfo categoryInfo = (CategoryInfo) obj;
        return Objects.equals(this.categoryName, categoryInfo.categoryName) && Objects.equals(this.categoryId, categoryInfo.categoryId) && Objects.equals(this.categoryLevel, categoryInfo.categoryLevel);
    }

    public int hashCode() {
        return Objects.hash(this.categoryName, this.categoryId, this.categoryLevel);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
