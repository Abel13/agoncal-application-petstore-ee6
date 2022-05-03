package org.agoncal.application.petstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Entity
@NamedQueries({
        // TODO fetch doesn't work with GlassFish
//        @NamedQuery(name = Product.FIND_BY_CATEGORY_NAME, query = "SELECT p FROM Product p LEFT JOIN FETCH p.items LEFT JOIN FETCH p.category WHERE p.category.name = :pname"),
        @NamedQuery(name = Product.FIND_BY_CATEGORY_NAME, query = "SELECT p FROM Product p WHERE p.category.name = :pname"),
        @NamedQuery(name = Product.FIND_ALL, query = "SELECT p FROM Product p")
})
@XmlRootElement
@ToString(includeFieldNames = true)
public class Product {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    
    @Column(nullable = false, length = 30)
    @NotNull
    @Size(min = 1, max = 30)
    @Getter
    @Setter
    private String name;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
    @XmlTransient
    @Getter
    @Setter
    private Category category;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    @XmlTransient
    @Getter
    @Setter
    @ToString.Exclude private List<Item> items;
    
    // ======================================
    // =             Constants              =
    // ======================================

    public static final String FIND_BY_CATEGORY_NAME = "Product.findByCategoryName";
    public static final String FIND_ALL = "Product.findAll";

    // ======================================
    // =            Constructors            =
    // ======================================

    public Product() {
    }

    public Product(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public void addItem(Item item) {
        if (items == null)
            items = new ArrayList<Item>();
        items.add(item);
    }

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}