package at.htl.farm.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "Farm.findAll", query = "select f from Farm f"),
        @NamedQuery(name = "Farm.findById", query = "select f from Farm f where f.id = ?1")
})
public class Farm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Animal> animals;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Field> fields;

    //region Constructor
    public Farm() {
    }

    public Farm(String location) {
        this.location = location;
    }
    //endregion

    //region Getter & Setter
    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addAnimal(Animal a) {
        if (animals == null)
            animals = new LinkedList<>();
        animals.add(a);
    }

    public void removeAnimal(Animal a) {
        if (animals != null && animals.contains(a)) {
            animals.remove(a);
        }
    }

    public void addField(Field f) {
        if (fields == null)
            fields = new LinkedList<>();
        fields.add(f);
    }

    public void removeField(Field f) {
        if (fields != null && fields.contains(f)) {
            fields.remove(f);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Field> getFields() {
        return fields;
    }
    //endregion
}
