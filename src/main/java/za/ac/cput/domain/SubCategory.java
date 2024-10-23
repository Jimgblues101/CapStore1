package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * SubCategory.java
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 26-Sep-24
 */
@Getter
@Entity(name = "sub_category")
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("productReference")
    private Product product;

    public SubCategory() {
    }

    public SubCategory(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.product = builder.product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, product);
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "ID=" + id +
                ", Category=" + (category != null ? category.getName() : "null") +
                ", Products=" + (product != null ? product.getName() : 0) +
                '}';
    }

    public static class Builder {
        private Long id;
        private Category category;
        private Product product;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setProduct(Product products) {
            this.product = products;
            return this;
        }

        public Builder copy(SubCategory subCategory) {
            this.id = subCategory.id;
            this.category = subCategory.category;
            this.product = subCategory.product;
            return this;
        }

        public SubCategory build() {
            return new SubCategory(this);
        }
    }
}
