package com.example.spring_boot_rest_api.goods;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "good")
public class Good {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Long price;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(
            mappedBy = "parent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Good> children = new ArrayList<>();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "parent")
    private Good parent;

    public Good() {
    }

    public Good(String id, String name, Long amount, String type, Long price, String parentId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Relation fields getters/setters

    public List<Good> getChildren() {
        return children;
    }

    public void setChildren(List<Good> children) {
        this.children = children;
    }

    public Good getParent() {
        return parent;
    }

    public void setParent(Good parent) {
        this.parent = parent;

        if (parent == null) {
            this.parentId = null;
        } else {
            this.parentId = parent.getId();
        }
    }

    // Service methods

    public void addChild(Good good) {
        children.add(good);
        good.setParent(this);
    }

    public void removeChild(Good good) {
        children.remove(good);
        good.setParent(null);
    }

    public void changeParent(Good newParent) {
        this.parent.getChildren().remove(this);
        this.setParent(newParent);
        newParent.getChildren().add(this);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", parentId='" + parentId + '\'' +
                ", date=" + date +
                ", children=" + children +
                ", parent=" + parent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        return id != null && id.equals(((Good) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
